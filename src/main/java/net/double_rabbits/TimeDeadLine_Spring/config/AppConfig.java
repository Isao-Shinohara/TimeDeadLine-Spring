package net.double_rabbits.TimeDeadLine_Spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.double_rabbits.TimeDeadLine_Spring.controller.BattleStartController;
import net.double_rabbits.TimeDeadLine_Spring.controller.CreateRoomController;
import net.double_rabbits.TimeDeadLine_Spring.controller.LoginController;
import net.double_rabbits.TimeDeadLine_Spring.controller.TurnBasedController;
import net.double_rabbits.TimeDeadLine_Spring.core.Dispatcher;
import net.double_rabbits.TimeDeadLine_Spring.core.SendMessageFactory;

@Configuration
public class AppConfig
{
	@Bean
	public Dispatcher dispatcher()
	{
		return new Dispatcher();
	}

	@Bean
	public SendMessageFactory SendMessageFactory()
	{
		return new SendMessageFactory();
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
	public BattleStartController BattleStartController()
	{
		return new BattleStartController();
	}

	@Bean
	public TurnBasedController TurnBasedController()
	{
		return new TurnBasedController();
	}
}
