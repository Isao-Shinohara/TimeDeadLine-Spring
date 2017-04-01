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
		this.roomRepository.save(roomEntity);
	}

	public List<AttackStandyEntity> AttackStandy(UserEntity userEntity, Long unitId, ActionType actionType)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());

		AttackStandyEntity attackStandyEntity = new AttackStandyEntity(unitId, actionType);
		roomEntity.getAttackStandyEntityList().add(attackStandyEntity);

		return roomEntity.getAttackStandyEntityList();
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
}
