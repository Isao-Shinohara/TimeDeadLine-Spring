package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.service.UserService;

public class TurnBasedController
{
	@Autowired
	protected UserService userService;

	public Map<Long, TurnBasedResponse> CreateResponse()
	{
		ConcurrentHashMap<Long, TurnBasedResponse> map = new ConcurrentHashMap<Long, TurnBasedResponse>();
		List<RoomEntity> roomEntityList = this.userService.roomRepository.findAll();

		for (RoomEntity roomEntity : roomEntityList) {
			if (!roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;

			roomEntity.getTurnBasedEntity().CountDown();
			this.userService.roomRepository.save(roomEntity);

			TurnBasedResponse response = new TurnBasedResponse(roomEntity.getTurnBasedEntity());
			map.put(roomEntity.getRoomId(), response);
		}

		return map;
	}

	public List<UserEntity> CreateReceiveUserEntityList(Long roomId)
	{
		return this.userService.GetUserEntityListByRoomId(roomId);
	}
}
