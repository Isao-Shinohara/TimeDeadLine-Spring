package net.double_rabbits.TimeDeadLine_Spring.service;

import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Service
public class TurnBasedService extends BaseService
{
	public TurnBasedEntity NextTurn(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getTurnBasedEntity().NextTurn();
		this.roomRepository.save(roomEntity);

		return roomEntity.getTurnBasedEntity();
	}
}
