package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.AttackStandyResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

@Component
public class AllCpuAttackStandyScheduledTasks extends BaseScheduledTasks
{
	@Scheduled(cron = "* * * * * *")
	public void AllCpuAttackStandy()
	{
		List<RoomEntity> roomEntityList = this.roomRepository.findByBattleModeType(BattleModeType.Single);
		for (RoomEntity roomEntity : roomEntityList) {
			if (!roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;

			// Action.
			roomEntity.GetNotAttackStandyCpuUnitEntityList().forEach(entity -> {
				// Draw lots AttackStandy.
				if (!this.canAttackStandyByDrawingLots(roomEntity)) return;

				// Draw lots ActionType.
				ActionType actionType = getActionTypeByDrawingLots(entity);

				// Add List.
				AttackStandyEntity attackStandyEntity = new AttackStandyEntity(roomEntity, entity.getUnitId(), actionType);
				roomEntity.getAttackStandyEntityList().add(attackStandyEntity);
			});
			this.attackStandyEntityRepository.save(roomEntity.getAttackStandyEntityList());

			// Response.
			List<AttackStandyValue> attackStandyValueList = new ArrayList<AttackStandyValue>();
			roomEntity.getAttackStandyEntityList().forEach(e -> {
				attackStandyValueList.add(new AttackStandyValue(e));
			});

			// SendMessage.
			List<UserEntity> receiveUserEntityList = this.userRepository.findByRoomId(roomEntity.getRoomId());
			this.sendMessage(new AttackStandyResponse(attackStandyValueList), receiveUserEntityList);
		}
	}

	private boolean canAttackStandyByDrawingLots(RoomEntity roomEntity)
	{
		int seconds = roomEntity.getTurnBasedEntity().getSeconds();
		int actionRate = (int) (100 * ((float) (BattleContext.OnePeriodSeconds - seconds + 1) / BattleContext.OnePeriodSeconds));
		int notActionRate = 100 - actionRate;

		int lot = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		return lot > notActionRate;
	}

	private ActionType getActionTypeByDrawingLots(UnitEntity unitEntity)
	{
		if (unitEntity.IsAlive()) {
			return this.getActionTypeByDrawingLotsWhenAlive(unitEntity);
		} else {
			return this.getActionTypeByDrawingLotsWhenDead(unitEntity);
		}
	}

	private ActionType getActionTypeByDrawingLotsWhenAlive(UnitEntity unitEntity)
	{
		int attackRate = 70;
		int skillRate = 15;
		int defenseRate = 10;
		int revivalRate = 5;
		int sumRate = attackRate + skillRate + defenseRate + revivalRate;

		int lot = ThreadLocalRandom.current().nextInt(1, sumRate + 1);

		if (lot <= attackRate) return ActionType.Attack;
		lot -= attackRate;

		if (lot <= skillRate) return ActionType.Skill;
		lot -= skillRate;

		if (lot <= defenseRate) return ActionType.Defense;
		lot -= defenseRate;

		if (lot <= revivalRate) return ActionType.Revival;
		lot -= revivalRate;

		return ActionType.None;
	}

	private ActionType getActionTypeByDrawingLotsWhenDead(UnitEntity unitEntity)
	{
		return ActionType.Revival;
	}
}
