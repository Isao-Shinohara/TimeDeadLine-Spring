package net.double_rabbits.TimeDeadLine_Spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.double_rabbits.TimeDeadLine_Spring.core.Dispatcher;
import net.double_rabbits.TimeDeadLine_Spring.core.SendMessageFactory;
import net.double_rabbits.TimeDeadLine_Spring.controller.*;

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
	public TimeController TimeController()
	{
		return new TimeController();
	}
}

