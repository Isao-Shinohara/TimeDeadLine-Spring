package net.double_rabbits.TimeDeadLine_Spring.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;
import net.double_rabbits.TimeDeadLine_Spring.repository.UserRepository;

public class SendMessageFactory
{
	protected static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	@Autowired
	UserRepository userRepository;

	public Map<UserEntity, BinaryMessage> Create(String senderUid, List<UserEntity> receiveUserEntityList, BaseResponse response)
	{
		// Response Binary.
		final BinaryMessage resMessage = this.CreateBinaryMessage(senderUid, response);

		// Receive Data.
		ConcurrentHashMap<UserEntity, BinaryMessage> map = new ConcurrentHashMap<UserEntity, BinaryMessage>();
		receiveUserEntityList.forEach(receiveUserEntity -> {
			map.put(receiveUserEntity, resMessage);
		});

		return map;
	}

	public Map<UserEntity, BinaryMessage> CreateAsEmpty()
	{
		return new ConcurrentHashMap<UserEntity, BinaryMessage>();
	}

	private BinaryMessage CreateBinaryMessage(String senderUid, BaseResponse response)
	{
		BinaryMessage binaryMessage = null;

		try {
			binaryMessage = MsgPackUtil.CreateBinaryMessage(senderUid, response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return binaryMessage;
	}
}

