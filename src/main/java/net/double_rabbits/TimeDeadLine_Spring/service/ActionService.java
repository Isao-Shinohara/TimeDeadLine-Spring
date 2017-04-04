package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.helper.ActionHelperFactory;
import net.double_rabbits.TimeDeadLine_Spring.helper.BaseActionHelper;
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

	public void DoAction()
	{
		List<RoomEntity> roomEntityList = this.roomRepository.findAll();
		for (RoomEntity roomEntity : roomEntityList) {
			if (roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;
			if (roomEntity.getAttackStandyEntityList().size() <= 0) continue;

			// Do Action.
			roomEntity.getAttackStandyEntityList().forEach(entity -> {
				ActionResultEntity actionResultEntity = new ActionResultEntity(roomEntity, entity);
				BaseActionHelper actionHelper = ActionHelperFactory.Create(actionResultEntity);
				actionResultEntity.getActionResultDetailEntityList().add(actionHelper.Do());
				roomEntity.getActionResultEntityList().add(actionResultEntity);
			});

			roomEntity.getAttackStandyEntityList().clear();
			this.roomRepository.save(roomEntity);
		}
	}

	public List<ActionResultValue> GetActionResultList(UserEntity userEntity)
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
				// TODO Auto-generated catch block
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
