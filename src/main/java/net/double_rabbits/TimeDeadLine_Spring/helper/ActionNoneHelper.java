package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

public class ActionNoneHelper extends BaseActionHelper
{
	public ActionNoneHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		super(actionResultEntity, defenseUnitIdList);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		return new ArrayList<ActionResultDetailEntity>();
	}
}
