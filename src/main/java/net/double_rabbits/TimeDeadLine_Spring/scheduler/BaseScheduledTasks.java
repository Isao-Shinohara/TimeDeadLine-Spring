package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import net.double_rabbits.TimeDeadLine_Spring.core.BinaryMessageFactory;
import net.double_rabbits.TimeDeadLine_Spring.core.SendMessage;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;
import net.double_rabbits.TimeDeadLine_Spring.repository.AttackStandyEntityRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.RoomRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.UserRepository;
import net.double_rabbits.TimeDeadLine_Spring.service.BaseService;

public class BaseScheduledTasks
{
	protected static final Logger logger = LoggerFactory.getLogger(TimeScheduledTasks.class);

	@Autowired
	private SendMessage sendMessage;

	@Autowired
	protected BinaryMessageFactory binaryMessageFactory;

	@Autowired
	protected BaseService service;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected RoomRepository roomRepository;

	@Autowired
	protected UnitRepository unitRepository;

	@Autowired
	protected AttackStandyEntityRepository attackStandyEntityRepository;

	protected void sendMessage(BaseResponse response, List<UserEntity> receiveUserEntityList)
	{
		Map<UserEntity, BinaryMessage> map = this.binaryMessageFactory.Create("system", receiveUserEntityList, response);
		for (Entry<UserEntity, BinaryMessage> entry : map.entrySet()) {
			this.sendMessage.Send(entry.getKey(), entry.getValue());
		}
	}
}
