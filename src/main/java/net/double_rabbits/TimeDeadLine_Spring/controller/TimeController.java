package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomUserEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.TimeResponse;
import net.double_rabbits.TimeDeadLine_Spring.service.BaseService;

public class TimeController
{
	@Autowired
	protected BaseService service;

	public Map<Long, TimeResponse> CreateResponse()
	{
		ConcurrentHashMap<Long, TimeResponse> map = new ConcurrentHashMap<Long, TimeResponse>();
		List<RoomEntity> roomEntityList = this.service.roomRepository.findAll();

		for (RoomEntity roomEntity : roomEntityList) {
			roomEntity.getTimeEntity().CountDown();
			this.service.roomRepository.save(roomEntity);

			TimeResponse response = new TimeResponse(roomEntity.getTimeEntity().getRemainSeconds());
			map.put(roomEntity.getRoomId(), response);
		}

		return map;
	}

	public List<UserEntity> CreateReceiveUserEntityList(Long roomId)
	{
		List<UserEntity> list = new ArrayList<UserEntity>();

		RoomEntity roomEntity = this.service.roomRepository.findOne(roomId);
		for (RoomUserEntity roomUserEntity : roomEntity.getRoomUserEntityList()) {
			UserEntity userEntity = this.service.userRepository.findOne(roomUserEntity.getUserId());
			if (!Objects.equals(userEntity, null)) {
				list.add(userEntity);
			}
		}

		return list;
	}
}

