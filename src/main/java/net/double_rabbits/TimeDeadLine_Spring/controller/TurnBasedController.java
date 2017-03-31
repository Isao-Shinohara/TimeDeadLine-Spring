package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.service.TurnBasedService;
import net.double_rabbits.TimeDeadLine_Spring.service.UserService;

public class TurnBasedController
{
	@Autowired
	protected TurnBasedService turnBasedService;

	@Autowired
	protected UserService userService;

	public Map<Long, TurnBasedResponse> CreateResponse()
	{
		ConcurrentHashMap<Long, TurnBasedResponse> map = new ConcurrentHashMap<Long, TurnBasedResponse>();

		List<TurnBasedEntity> turnBasedEntityList = this.turnBasedService.CountDown();
		for (TurnBasedEntity turnBasedEntity : turnBasedEntityList) {
			TurnBasedResponse response = new TurnBasedResponse(turnBasedEntity);
			map.put(turnBasedEntity.getRoomEntity().getRoomId(), response);
		}

		return map;
	}

	public List<UserEntity> CreateReceiveUserEntityList(Long roomId)
	{
		return this.userService.GetUserEntityListByRoomId(roomId);
	}
}
