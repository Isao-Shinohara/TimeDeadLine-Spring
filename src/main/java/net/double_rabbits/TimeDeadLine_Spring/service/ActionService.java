package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;

@Service
public class ActionService extends BaseService
{
	public void NextTurn(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getAttackStandyEntityList().clear();
		this.roomRepository.saveAndFlush(roomEntity);
	}

	public void AttackStandy(UserEntity userEntity, Long unitId, ActionType actionType)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());

		AttackStandyEntity attackStandyEntity = new AttackStandyEntity(roomEntity, unitId, actionType);
		roomEntity.getAttackStandyEntityList().add(attackStandyEntity);

		this.roomRepository.saveAndFlush(roomEntity);
	}

	public List<AttackStandyValue> GetAttackStandyValueList(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());

		List<AttackStandyValue> attackStandyValueList = new ArrayList<AttackStandyValue>();
		roomEntity.getAttackStandyEntityList().forEach(entity -> {
			attackStandyValueList.add(new AttackStandyValue(entity));
		});

		return attackStandyValueList;
	}

	public void DoAction()
	{
		List<RoomEntity> roomEntityList = this.roomRepository.findAll();
		for (RoomEntity roomEntity : roomEntityList) {
			if (roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;
			if (roomEntity.getAttackStandyEntityList().size() <= 0) continue;

			// Do Action.
			roomEntity.getAttackStandyEntityList().forEach(entity -> {
				logger.info(String.valueOf(entity.getActionType()));
				logger.info(String.valueOf(entity.getUnitId()));
			});

			roomEntity.getAttackStandyEntityList().clear();
			this.roomRepository.save(roomEntity);
		}
	}
}
