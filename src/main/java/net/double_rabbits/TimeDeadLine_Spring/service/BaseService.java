package net.double_rabbits.TimeDeadLine_Spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import net.double_rabbits.TimeDeadLine_Spring.repository.RoomRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.UserRepository;

public class BaseService
{
	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);

	@Autowired
	public RoomRepository roomRepository;

	@Autowired
	public UserRepository userRepository;
}
