package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionDefenseHelper extends BaseActionHelper
{
	public ActionDefenseHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		super(actionResultEntity, defenseUnitIdList, unitRepository);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Alive or Dead.
		UnitEntity unitEntity = this.getUnitEntityByUnitId(this.actionResultEntity.getUnitId());
		if (unitEntity.IsDead()) return this.getNonActionResultDetailEntityList();

		return this.getNonActionResultDetailEntityList();
	}
}
