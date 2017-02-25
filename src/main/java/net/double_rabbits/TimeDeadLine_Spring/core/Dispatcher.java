package net.double_rabbits.TimeDeadLine_Spring.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.BinaryMessage;
import net.double_rabbits.TimeDeadLine_Spring.controller.BaseController;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;
import net.double_rabbits.TimeDeadLine_Spring.network.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.repository.UserRepository;

public class Dispatcher
{
	protected static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SendMessageFactory sendMessageFactory;

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("unchecked")
	public Map<UserEntity, BinaryMessage> dispatch(String sessionId, BinaryMessage binaryMessage) throws IOException
	{
		try {
			// Message.
			Message message = (Message) MsgPackUtil.Deserialize(binaryMessage.getPayload().array(), Message.class);
			MsgPackExtensionType type = MsgPackExtensionType.values()[message.Payload.getType()];
			String className = String.format("%sController", type.toString());
			BaseController<BaseRequest, BaseResponse> controller = this.context.getBean(className, BaseController.class);

			// UserEntity.
			UserEntity sendUserEntity = this.userRepository.findByUid(message.Uid);
			if (Objects.equals(sendUserEntity, null)) {
				sendUserEntity = new UserEntity(message.Uid, sessionId);
				this.userRepository.save(sendUserEntity);
			}

			// Response.
			controller.Initialize(sendUserEntity, message.Payload.getData());
			BaseResponse response = controller.GetResponse();
			List<UserEntity> receiveUserEntityList = controller.GetReceiveUserEntityList();

			return this.sendMessageFactory.Create(sendUserEntity.getUid(), receiveUserEntityList, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return this.sendMessageFactory.CreateAsEmpty();
	}
}

