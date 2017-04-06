package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionNoneHelper extends BaseActionHelper
{
	public ActionNoneHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		super(actionResultEntity, defenseUnitIdList, unitRepository);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		return this.getNonActionResultDetailEntityList();
	}
}
