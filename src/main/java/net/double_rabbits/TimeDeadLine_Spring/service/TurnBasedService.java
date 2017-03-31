package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Service
public class TurnBasedService extends BaseService
{
	public TurnBasedEntity RoundStart(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getTurnBasedEntity().RountStart();
		this.roomRepository.save(roomEntity);

		return roomEntity.getTurnBasedEntity();
	}

	public TurnBasedEntity NextTurn(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getTurnBasedEntity().NextTurn();
		this.roomRepository.save(roomEntity);

		return roomEntity.getTurnBasedEntity();
	}

	public List<TurnBasedEntity> CountDown()
	{
		List<TurnBasedEntity> turnBasedEntityList = new ArrayList<TurnBasedEntity>();

		List<RoomEntity> roomEntityList = this.roomRepository.findAll();
		for (RoomEntity roomEntity : roomEntityList) {
			if (!roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;

			roomEntity.getTurnBasedEntity().CountDown();
			this.roomRepository.save(roomEntity);

			turnBasedEntityList.add(roomEntity.getTurnBasedEntity());
		}

		return turnBasedEntityList;
	}
}
