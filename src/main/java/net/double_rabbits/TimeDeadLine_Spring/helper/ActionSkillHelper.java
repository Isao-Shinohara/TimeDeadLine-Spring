package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionSkillHelper extends BaseActionHelper
{
	public ActionSkillHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		super(actionResultEntity, defenseUnitIdList, unitRepository);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Alive or Dead.
		UnitEntity unitEntity = this.getUnitEntityByUnitId(this.actionResultEntity.getUnitId());
		if (unitEntity.IsDead()) return this.getNonActionResultDetailEntityList();

		// Target.
		List<UnitEntity> opponentUnitEntityList = this.getOpponentAliveUnitEntityList();
		if (opponentUnitEntityList.size() <= 0) { return this.getNonActionResultDetailEntityList(); }

		// Damage.
		List<ActionResultDetailEntity> actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
		opponentUnitEntityList.forEach(entity -> {
			int remainHp = this.calcHpByDamage(entity, BattleContext.MinSkillDamage, BattleContext.MaxSkillDamage);
			actionResultDetailEntityList.add(new ActionResultDetailEntity(entity.getUnitId(), remainHp, this.actionResultEntity));
		});

		return actionResultDetailEntityList;
	}
}
