package com.sequence;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.lib.Card;
import com.sequence.lib.Game;
import com.sequence.lib.Player;
import com.sequence.req.*;
import com.sequence.res.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.class);

    private Map<WebSocketSession, Player> sessions;
    private Game game;
    private int player;

    public WebSocket() {
        game = new Game();
        player = Constants.PLAYER_RED;
        sessions = new HashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session, null);
        System.out.println("Session " + session.getId() + " put in");
        super.afterConnectionEstablished(session);
        System.out.println("Session " + session.getId() + " connection established");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Player p = sessions.get(session);
        System.out.println("Player p has been established");
        if (p != null) {
            game = new Game();
            System.out.println("Game has been created");
            player = Constants.PLAYER_RED;
            System.out.println("player color is set to RED");
        }
        sessions.remove(session);
        System.out.println("Session " + session.getId() + " has been removed");
        super.afterConnectionClosed(session, status);
        System.out.println("Session " + session.getId() + " connect has been closed");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        ObjectMapper mapper = new ObjectMapper();
        //System.out.println("ObjectMapper mapper created");
        RequestCarrier req = mapper.readValue(message.getPayload(), RequestCarrier.class);
        //System.out.println("RequestCarrier req created and read from mapper");
        //System.out.println("message.getPayload(): " + message.getPayload());

        //System.out.println("req.getRequestType(): " + req.getRequestType());
        if (req.getRequestType().equals(Constants.PING_REQ_TYPE)) {
            //System.out.println("req = PING");
            ResponseCarrier responseCarrier = new ResponseCarrier();
            //System.out.println("ResponseCarrier responseCarrier created");
            responseCarrier.setType(Constants.PING_RES_TYPE);
            //System.out.println("responseCarrier type set to PING");
            responseCarrier.setBody(new PingResponse("pong"));
            //System.out.println("responseCarrier body set to pong");
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            //System.out.println("TextMessage sent");
            return;
        }
        if (req.getRequestType().equals(Constants.JOIN_REQ_TYPE)) {
            System.out.println("req = JOIN");

            if (player == 0) {
                System.out.println("No player color set (player == 0)");
                ResponseCarrier responseCarrier = new ResponseCarrier();
                System.out.println("ResponseCarrier responseCarrier created");
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                System.out.println("responseCarrier type set to ERROR");
                responseCarrier.setBody(new ErrorResponse("Lobby is full. Try again later."));
                System.out.println("Error Message: Lobby is full");
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("Error Message sent");
                return;
            }
            JoinRequest joinRequest = mapper.readValue(req.getBody(), JoinRequest.class);
            LOGGER.info("New player joined the game");
            if (player == Constants.PLAYER_RED) {
                System.out.println("player color set to RED (player == Constants.PLAYER_RED)");
                Player p = new Player(game.getBoard(), game.getDeck(), player);
                System.out.println("RED Player p created");
                game.setRed(p);
                System.out.println("Set RED Player in game");
                sessions.put(session, p);
                System.out.println("Session " + session.getId() + " with Player p has been put");
                ResponseCarrier responseCarrier = new ResponseCarrier();
                System.out.println("responseCarrier created");
                responseCarrier.setType(Constants.JOIN_RES_TYPE);
                System.out.println("responseCarrier type set to JOIN");
                responseCarrier.setBody(new JoinResponse(player));
                System.out.println("responseCarrier setBody message with player's JoinResponse");
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("respnoseCarrier message sent");
                player = Constants.PLAYER_BLUE;
                System.out.println("player color set to BLUE");
            } else {
                System.out.println("player color set to BLUE (player == Constants.PLAYER_BLUE)");
                Player p = new Player(game.getBoard(), game.getDeck(), player);
                System.out.println("BLUE Player p created");
                game.setBlue(p);
                System.out.println("Set BLUE Player in game");
                sessions.put(session, p);
                System.out.println("Session " + session.getId() + " with Player p has been put");
                ResponseCarrier responseCarrier = new ResponseCarrier();
                System.out.println("responseCarrier created");
                responseCarrier.setType(Constants.JOIN_RES_TYPE);
                System.out.println("responseCarrier type set to JOIN");
                responseCarrier.setBody(new JoinResponse(player));
                System.out.println("responseCarrier setBody message with player's JoinResponse");
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("responseCarrier message sent");
                player = 0;
                System.out.println("player color set to NONE (player = 0)");
                game.startGame();
                System.out.println("Start game");
                sessions.forEach((ws, player) -> {
                    ResponseCarrier responseCarrier1 = new ResponseCarrier();
                    responseCarrier1.setType(Constants.START_GAME_RES_TYPE);
                    System.out.println("Set responseCarrier1 to START_GAME_RES_TYPE");
                    if (player.getPlayerMarker() == Constants.PLAYER_RED) {
                        responseCarrier1.setBody(new StartGameResponse(game.getRed().getCardsList(), game.getBoard()));
                        System.out.println("responseCarrier1 body is set to RED Player");
                    } else {
                        responseCarrier1.setBody(new StartGameResponse(game.getBlue().getCardsList(), game.getBoard()));
                        System.out.println("responseCarrier1 body is set to BLUE Player");
                    }
                    try {
                        ws.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier1)));
                        System.out.println("responseCarrier1 message being sent");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("ERROR with responseCarrier1");
                    }
                });
            }
        }

        if (req.getRequestType().equals(Constants.SELECT_CARD_REQ_TYPE)) {
            System.out.println("req = Constants.SELECT_CARD_REQ_TYPE");
            SelectCardRequest selectCardRequest = mapper.readValue(req.getBody(), SelectCardRequest.class);
            System.out.println("selectCardRequest created");
            if (game.isRedsTurn() == (selectCardRequest.getPlayer() == 1)) {
                System.out.println("Player's Turn is identified");
                System.out.println("game.isRedsTurn(): " + game.isRedsTurn());
                System.out.println("selectCardRequest.getPlayer(): " + selectCardRequest.getPlayer());
                System.out.println("selectCardRequest.getPlayer() == 1: " + (selectCardRequest.getPlayer() == 1));
                System.out.println("game.isRedsTurn() == (selectCardRequest.getPlayer() == 1): " + (game.isRedsTurn() == (selectCardRequest.getPlayer() == 1)));
                Player player = game.isRedsTurn() ? game.getRed() : game.getBlue();
                System.out.println("player is " + player.getPlayerMarker());
                Player otherPlayer = !game.isRedsTurn() ? game.getRed() : game.getBlue();
                System.out.println("otherPlayer is " + otherPlayer.getPlayerMarker());
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.SELECT_CARD_RES_TYPE);
                System.out.println("responseCarrier set type to SELECT_CARD_RES_TYPE");
                responseCarrier.setBody(
                        new SelectCardResponse(
                                player.listAvailableSpaces(
                                        new Card(
                                                selectCardRequest.getCardID(),
                                                selectCardRequest.getCardSuitNum(),
                                                selectCardRequest.getCoordinates() // TODO: NEED TO ADD getCoordinates()
                                        ), otherPlayer
                                )
                        )
                );
                System.out.println("responseCarrier set Body with available spaces on board");
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            } else {
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("Player's Turn is not up yet");
            }
        }

        if (req.getRequestType().equals(Constants.SELECT_SPACE_REQ_TYPE)) {
            System.out.println("req is SELECT_SPACE_REQ_TYPE");
            SelectSpaceRequest selectSpaceRequest = mapper.readValue(req.getBody(), SelectSpaceRequest.class);
            System.out.println("selectSpaceRequest mapper readValue");
            if (selectSpaceRequest.getPlayer() != game.getCurrentPlayerNum()) {
                System.out.println("selectSpaceRequest.getPlayer(): " + selectSpaceRequest.getPlayer());
                System.out.println("game.getCurrentPlayerNum():" + game.getCurrentPlayerNum());
                System.out.println("selectSpaceRequest.getPlayer() != game.getCurrentPlayerNum(): " + (selectSpaceRequest.getPlayer() != game.getCurrentPlayerNum()));
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("ERROR: Not your turn");

            }
            game.getCurrentPlayer().removeCard(selectSpaceRequest.getCard());
            System.out.println("Get current player in game");
            game.getCurrentPlayer().addCard(game.getDeck().deal());
            System.out.println("Deal card in player's deck");
            int winner = game.selectSpace(selectSpaceRequest.getCard(), selectSpaceRequest.getX(), selectSpaceRequest.getY());
            System.out.println("Identify a winner: winner = " + winner);
            ResponseCarrier responseCarrier = new ResponseCarrier();
            System.out.println("ResponseCarrier responseCarreier created");
            responseCarrier.setType(Constants.SELECT_SPACE_RES_TYPE);
            System.out.println("responseCarrier set type to SELECT_SPACE_RES_TYPE");
            responseCarrier.setBody(new SelectSpaceResponse(winner, game.getCurrentPlayerNum(),
                    game.getRed().getSequenceCounter(), game.getBlue().getSequenceCounter(), game.getBoard(),
                    game.getOtherPlayer().getCardsList()));
            sessions.forEach((ws, player) -> {
                try {
                    ws.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                    System.out.println("Send message to " + player + " about the winner");
                    // ???
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR with sending message about the winner");
                }
            });
        }

        if (req.getRequestType().equals(Constants.DEAD_CARD_REQ_TYPE)) {
            System.out.println("req is DEAD_CARD_REQ_TYPE");
            System.out.println("Beginning DEAD_CARD_REQ");
            DeadCardRequest deadCardRequest = mapper.readValue(req.getBody(), DeadCardRequest.class);
            System.out.println("deadCardRequest created and reading from req.getBody()");
            System.out.println("deadCardRequest: " + deadCardRequest);
            System.out.println("req.getBody(): " + req.getBody());

            if (deadCardRequest.getPlayer() != game.getCurrentPlayerNum()) {
                System.out.println("deadCardRequest.getPlayer(): " + deadCardRequest.getPlayer());
                System.out.println("game.getCurrentPlayerNum(): " + game.getCurrentPlayerNum());
                System.out.println("deadCardRequest.getPlayer() != game.getCurrentPlayerNum(): " + (deadCardRequest.getPlayer() != game.getCurrentPlayerNum()));
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                System.out.println("ERROR: Not player's turn");
            }
            DeadCardResponse deadCardResponse = game.getCurrentPlayer().checkDeadCards();
            //DeadCardResponse deadCardResponse = new DeadCardResponse(false);
            //System.out.println("DeadCardResponse deadCardResponse set to find dead cards in player " + game.getCurrentPlayer().getPlayerMarker() + " deck");
            ResponseCarrier responseCarrier = new ResponseCarrier();
            System.out.println("ResponseCarrier responseCarrier created");
            responseCarrier.setType(Constants.DEAD_CARD_RES_TYPE);
            System.out.println("responseCarrier set type to DEAD_CARD_RES_TYPE");
            responseCarrier.setBody(deadCardResponse);
            System.out.println("responseCarrier set body to deadCardResponse");
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            System.out.println("responseCarrier message sent");
            System.out.println("Leaving DEAD CARD If-statement");
        }
    }


}
