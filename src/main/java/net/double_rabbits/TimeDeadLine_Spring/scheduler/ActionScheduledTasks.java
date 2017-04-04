package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.service.ActionService;

@Component
public class ActionScheduledTasks extends BaseScheduledTasks
{
	@Autowired
	private ActionService actionService;

	@Scheduled(cron = "* * * * * *")
	public void DoAction()
	{
		this.actionService.DoAction();
	}
}
