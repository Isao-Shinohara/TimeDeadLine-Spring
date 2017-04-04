package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class ActionAttackHelper extends BaseActionHelper
{
	public ActionAttackHelper(ActionResultEntity actionResultEntity)
	{
		super(actionResultEntity);
	}

	@Override
	public ActionResultDetailEntity Do()
	{
		RoomEntity roomEntity = this.actionResultEntity.getRoomEntity();

		// Target.
		List<UnitEntity> opponentUnitEntityList = roomEntity.GetOpponentUnitEntityListByUnitId(this.actionResultEntity.getUnitId());
		Collections.shuffle(opponentUnitEntityList);
		UnitEntity targetUnitEntity = opponentUnitEntityList.get(0);

		// Damage.
		int damage = ThreadLocalRandom.current().nextInt(BattleContext.MinAttackDamage, BattleContext.MaxAttackDamage + 1);
		int remainHp = targetUnitEntity.getHp() - damage;
		remainHp = remainHp > 0 ? remainHp : 0;

		return new ActionResultDetailEntity(targetUnitEntity.getUnitId(), remainHp, this.actionResultEntity);
	}

}
