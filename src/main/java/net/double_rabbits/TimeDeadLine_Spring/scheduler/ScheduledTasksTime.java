package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.controller.TimeController;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.TimeResponse;

@Component
public class ScheduledTasksTime extends BaseScheduledTasks
{
	@Autowired
	private TimeController timeController;

	@Scheduled(cron = "* * * * * *")
	public void Notify()
	{
		Map<Long, TimeResponse> map = this.timeController.CreateResponse();
		map.forEach((roomId, response) -> {
			List<UserEntity> receiveUserEntityList = this.timeController.CreateReceiveUserEntityList(roomId);
			this.sendMessage(response, receiveUserEntityList);
		});
	}
}
