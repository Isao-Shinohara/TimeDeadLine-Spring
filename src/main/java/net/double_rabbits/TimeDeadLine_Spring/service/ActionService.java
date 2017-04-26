package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionResultValue;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;

@Service
public class ActionService extends BaseService
{
	public void NextTurn(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getAttackStandyEntityList().clear();
		roomEntity.getActionResultEntityList().clear();
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

	public synchronized List<ActionResultValue> GetActionResultList(UserEntity userEntity)
	{
		RoomEntity roomEntity;

		// Wait Until Get Result.
		while (true) {
			roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
			if (roomEntity.HasGotRoundResult()) {
				break;
			}

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		List<ActionResultValue> actionResultValueList = new ArrayList<ActionResultValue>();
		roomEntity.getActionResultEntityList().forEach(entity -> {
			actionResultValueList.add(new ActionResultValue(entity));
		});

		return actionResultValueList;
	}
}
