package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.service.TurnBasedService;
import net.double_rabbits.TimeDeadLine_Spring.service.UserService;

@Component
public class CountDownScheduledTasks extends BaseScheduledTasks
{
	@Autowired
	private TurnBasedService turnBasedService;

	@Autowired
	private UserService userService;

	@Scheduled(cron = "* * * * * *")
	public void CountDown()
	{
		// CountDown.
		ConcurrentHashMap<Long, TurnBasedResponse> map = new ConcurrentHashMap<Long, TurnBasedResponse>();
		List<TurnBasedEntity> turnBasedEntityList = this.turnBasedService.CountDown();
		for (TurnBasedEntity turnBasedEntity : turnBasedEntityList) {
			TurnBasedResponse response = new TurnBasedResponse(turnBasedEntity);
			map.put(turnBasedEntity.getRoomEntity().getRoomId(), response);
		}

		// SendMessage.
		map.forEach((roomId, response) -> {
			List<UserEntity> receiveUserEntityList = this.userService.GetUserEntityListByRoomId(roomId);
			this.sendMessage(response, receiveUserEntityList);
		});
	}
}
