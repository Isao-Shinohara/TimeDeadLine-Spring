package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class ActionSkillHelper extends BaseActionHelper
{
	public ActionSkillHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		super(actionResultEntity, defenseUnitIdList);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Target.
		List<UnitEntity> opponentUnitEntityList = this.getOpponentUnitEntityList();

		// Damage.
		List<ActionResultDetailEntity> actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
		opponentUnitEntityList.forEach(entity -> {
			int remainHp = this.calcHpByDamage(entity, BattleContext.MinSkillDamage, BattleContext.MaxSkillDamage);
			actionResultDetailEntityList.add(new ActionResultDetailEntity(entity.getUnitId(), remainHp, this.actionResultEntity));

		});

		return actionResultDetailEntityList;
	}

}
