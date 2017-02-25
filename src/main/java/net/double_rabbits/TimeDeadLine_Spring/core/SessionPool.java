package net.double_rabbits.TimeDeadLine_Spring.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SessionPool
{
	private Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

	public WebSocketSession Get(String sessionId)
	{
		return this.sessionPool.get(sessionId);
	}

	public void Put(WebSocketSession session)
	{
		this.sessionPool.put(session.getId(), session);
	}

	public void Remove(WebSocketSession session)
	{
		this.sessionPool.remove(session.getId());
	}
}