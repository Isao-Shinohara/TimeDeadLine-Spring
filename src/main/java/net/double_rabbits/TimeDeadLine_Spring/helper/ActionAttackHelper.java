package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.Collections;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class ActionAttackHelper extends BaseActionHelper
{
	public ActionAttackHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		super(actionResultEntity, defenseUnitIdList);
	}

	@Override
	public ActionResultDetailEntity Do()
	{
		// Target.
		List<UnitEntity> opponentUnitEntityList = this.getOpponentUnitEntityList();
		Collections.shuffle(opponentUnitEntityList);
		UnitEntity targetUnitEntity = opponentUnitEntityList.get(0);

		// Damage.
		int remainHp = this.calcHpByDamage(targetUnitEntity, BattleContext.MinAttackDamage, BattleContext.MaxAttackDamage);

		return new ActionResultDetailEntity(targetUnitEntity.getUnitId(), remainHp, this.actionResultEntity);
	}

}
