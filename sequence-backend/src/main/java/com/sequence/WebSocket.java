package com.sequence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.lib.Game;
import com.sequence.lib.Player;
import com.sequence.req.JoinRequest;
import com.sequence.req.RequestCarrier;
import com.sequence.res.ErrorResponse;
import com.sequence.res.JoinResponse;
import com.sequence.res.ResponseCarrier;
import com.sequence.res.StartGameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.class);

    private final Map<WebSocketSession, Player> sessions = new HashMap<>();

    private Game game;
    private int player;

    public WebSocket() {
        game = new Game();
        player = Constants.PLAYER_RED;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session, null);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        ObjectMapper mapper = new ObjectMapper();
        RequestCarrier req = mapper.readValue(message.getPayload(), RequestCarrier.class);
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
                        responseCarrier1.setBody(new StartGameResponse(game.getRed().getCardsList()));
                    } else {
                        responseCarrier1.setBody(new StartGameResponse(game.getBlue().getCardsList()));
                    }
                    try {
                        ws.sendMessage(new TextMessage(mapper.writeValueAsString(responseCarrier1)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }


}