package net.double_rabbits.TimeDeadLine_Spring.core;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.service.RoomService;
import net.double_rabbits.TimeDeadLine_Spring.service.UserService;

@Component
public class BinaryEchoHandler extends BinaryWebSocketHandler
{
	protected static final Logger logger = LoggerFactory.getLogger(BinaryWebSocketHandler.class);

	@Autowired
	private SessionPool sessionPool;

	@Autowired
	private Dispatcher dispatcher;

	@Autowired
	private SendMessage sendMessage;

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		this.sessionPool.Put(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		logger.info(String.format("[afterConnectionClosed] session: %s", session.getId()));
		UserEntity userEntity = this.userService.GetUserEntityBySessionId(session.getId());
		if (Objects.equals(userEntity, null)) return;

		this.roomService.Delete(userEntity);
		this.userService.Delete(userEntity);
		this.sessionPool.Remove(session);
	}

	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws ClassNotFoundException
	{
		Map<UserEntity, BinaryMessage> map;
		try {
			// Dispatch.
			map = dispatcher.dispatch(session.getId(), message);
			for (Entry<UserEntity, BinaryMessage> entry : map.entrySet()) {
				// Send Message.
				this.sendMessage.Send(entry.getKey(), entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
