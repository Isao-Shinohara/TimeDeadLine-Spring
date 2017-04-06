package net.double_rabbits.TimeDeadLine_Spring.core;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Component
public class SendMessage
{
	@Autowired
	protected SessionPool sessionPool;

	synchronized public void Send(UserEntity userEntity, BinaryMessage message)
	{
		try {
			this.sessionPool.Get(userEntity.getSessionId()).sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
