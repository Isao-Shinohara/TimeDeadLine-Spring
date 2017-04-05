package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionAttackHelper extends BaseActionHelper
{
	public ActionAttackHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		super(actionResultEntity, defenseUnitIdList, unitRepository);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Target.
		List<UnitEntity> opponentUnitEntityList = this.getOpponentUnitEntityList();
		Collections.shuffle(opponentUnitEntityList);
		UnitEntity targetUnitEntity = opponentUnitEntityList.get(0);

		// Damage.
		List<ActionResultDetailEntity> actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
		int remainHp = this.calcHpByDamage(targetUnitEntity, BattleContext.MinAttackDamage, BattleContext.MaxAttackDamage);
		actionResultDetailEntityList.add(new ActionResultDetailEntity(targetUnitEntity.getUnitId(), remainHp, this.actionResultEntity));

		return actionResultDetailEntityList;
	}
}
