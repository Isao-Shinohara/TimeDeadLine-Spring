package net.double_rabbits.TimeDeadLine_Spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.repository.RoomRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;
import net.double_rabbits.TimeDeadLine_Spring.repository.UserRepository;

@Service
@Primary
public class BaseService
{
	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);

	@Autowired
	protected RoomRepository roomRepository;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UnitRepository unitRepository;
}
