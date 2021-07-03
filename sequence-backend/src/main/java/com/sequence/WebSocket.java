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
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Player p = sessions.get(session);
        if (p != null) {
            game = new Game();
            player = Constants.PLAYER_RED;
        }
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        ObjectMapper mapper = new ObjectMapper();
        RequestCarrier req = mapper.readValue(message.getPayload(), RequestCarrier.class);
        if (req.getRequestType().equals(Constants.PING_REQ_TYPE)) {
            ResponseCarrier responseCarrier = new ResponseCarrier();
            responseCarrier.setType(Constants.PING_RES_TYPE);
            responseCarrier.setBody(new PingResponse("pong"));
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            return;
        }
        if (req.getRequestType().equals(Constants.JOIN_REQ_TYPE)) {
            if (player == 0) {
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("Lobby is full. Try again later."));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                return;
            }
            JoinRequest joinRequest = mapper.readValue(req.getBody(), JoinRequest.class);
            LOGGER.info("New player joined the game");
            if (player == Constants.PLAYER_RED) {
                Player p = new Player(game.getBoard(), game.getDeck(), player);
                game.setRed(p);
                sessions.put(session, p);
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.JOIN_RES_TYPE);
                responseCarrier.setBody(new JoinResponse(player));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                player = Constants.PLAYER_BLUE;
            } else {
                Player p = new Player(game.getBoard(), game.getDeck(), player);
                game.setBlue(p);
                sessions.put(session, p);
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.JOIN_RES_TYPE);
                responseCarrier.setBody(new JoinResponse(player));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                player = 0;
                game.startGame();
                sessions.forEach((ws, player) -> {
                    ResponseCarrier responseCarrier1 = new ResponseCarrier();
                    responseCarrier1.setType(Constants.START_GAME_RES_TYPE);
                    if (player.getPlayerMarker() == Constants.PLAYER_RED) {
                        responseCarrier1.setBody(new StartGameResponse(game.getRed().getCardsList(), game.getBoard()));
                    } else {
                        responseCarrier1.setBody(new StartGameResponse(game.getBlue().getCardsList(), game.getBoard()));
                    }
                    try {
                        ws.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier1)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        if (req.getRequestType().equals(Constants.SELECT_CARD_REQ_TYPE)) {
            SelectCardRequest selectCardRequest = mapper.readValue(req.getBody(), SelectCardRequest.class);
            if (game.isRedsTurn() == (selectCardRequest.getPlayer() == 1)) {
                Player player = game.isRedsTurn() ? game.getRed() : game.getBlue();
                Player otherPlayer = !game.isRedsTurn() ? game.getRed() : game.getBlue();
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.SELECT_CARD_RES_TYPE);
                responseCarrier.setBody(
                        new SelectCardResponse(
                                player.listAvailableSpaces(
                                        new Card(
                                                selectCardRequest.getCardID(),
                                                selectCardRequest.getCardSuitNum()
                                        ), otherPlayer
                                )
                        )
                );
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            } else {
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            }
        }

        if (req.getRequestType().equals(Constants.SELECT_SPACE_REQ_TYPE)) {
            SelectSpaceRequest selectSpaceRequest = mapper.readValue(req.getBody(), SelectSpaceRequest.class);
            if (selectSpaceRequest.getPlayer() != game.getCurrentPlayerNum()) {
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            }
            game.getCurrentPlayer().removeCard(selectSpaceRequest.getCard());
            game.getCurrentPlayer().addCard(game.getDeck().deal());
            int winner = game.selectSpace(selectSpaceRequest.getCard(), selectSpaceRequest.getX(), selectSpaceRequest.getY());
            ResponseCarrier responseCarrier = new ResponseCarrier();
            responseCarrier.setType(Constants.SELECT_SPACE_RES_TYPE);
            responseCarrier.setBody(new SelectSpaceResponse(winner, game.getCurrentPlayerNum(), game.getBoard(),
                    game.getOtherPlayer().getCardsList()));
            sessions.forEach((ws, player) -> {
                try {
                    ws.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        if (req.getRequestType().equals(Constants.DEAD_CARD_REQ_TYPE)) {
            DeadCardRequest deadCardRequest = mapper.readValue(req.getBody(), DeadCardRequest.class);
            if (deadCardRequest.getPlayer() != game.getCurrentPlayerNum()) {
                ResponseCarrier responseCarrier = new ResponseCarrier();
                responseCarrier.setType(Constants.ERROR_RES_TYPE);
                responseCarrier.setBody(new ErrorResponse("It's not your turn!"));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
            }
            DeadCardResponse deadCardResponse = game.getCurrentPlayer().checkDeadCards();
            ResponseCarrier responseCarrier = new ResponseCarrier();
            responseCarrier.setType(Constants.DEAD_CARD_RES_TYPE);
            responseCarrier.setBody(deadCardResponse);
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier)));
        }
    }


}
