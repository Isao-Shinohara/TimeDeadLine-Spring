package net.double_rabbits.TimeDeadLine_Spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.double_rabbits.TimeDeadLine_Spring.controller.AttackStandyController;
import net.double_rabbits.TimeDeadLine_Spring.controller.BattleEntryController;
import net.double_rabbits.TimeDeadLine_Spring.controller.BattleStartController;
import net.double_rabbits.TimeDeadLine_Spring.controller.CreateRoomController;
import net.double_rabbits.TimeDeadLine_Spring.controller.GetActionResultController;
import net.double_rabbits.TimeDeadLine_Spring.controller.JoinRoomController;
import net.double_rabbits.TimeDeadLine_Spring.controller.LoginController;
import net.double_rabbits.TimeDeadLine_Spring.controller.NextTurnController;
import net.double_rabbits.TimeDeadLine_Spring.controller.RoundStartController;
import net.double_rabbits.TimeDeadLine_Spring.core.BinaryMessageFactory;
import net.double_rabbits.TimeDeadLine_Spring.core.Dispatcher;
import net.double_rabbits.TimeDeadLine_Spring.core.SendMessage;

@Configuration
public class AppConfig
{
	@Bean
	public Dispatcher dispatcher()
	{
		return new Dispatcher();
	}

	@Bean
	public SendMessage SendMessage()
	{
		return new SendMessage();
	}

	@Bean
	public BinaryMessageFactory BinaryMessageFactory()
	{
		return new BinaryMessageFactory();
	}

	@Bean
	public net.double_rabbits.TimeDeadLine_Spring.controller.LoginController LoginController()
	{
		return new LoginController();
	}

	@Bean
	public CreateRoomController CreateRoomController()
	{
		return new CreateRoomController();
	}

	@Bean
	public JoinRoomController JoinRoomController()
	{
		return new JoinRoomController();
	}

	@Bean
	public BattleEntryController BattleEntryController()
	{
		return new BattleEntryController();
	}

	@Bean
	public BattleStartController BattleStartController()
	{
		return new BattleStartController();
	}

	@Bean
	public NextTurnController NextTurnController()
	{
		return new NextTurnController();
	}

	@Bean
	public RoundStartController RoundStartController()
	{
		return new RoundStartController();
	}

	@Bean
	public AttackStandyController AttackStandyController()
	{
		return new AttackStandyController();
	}

	@Bean
	public GetActionResultController GetActionResultController()
	{
		return new GetActionResultController();
	}
}
