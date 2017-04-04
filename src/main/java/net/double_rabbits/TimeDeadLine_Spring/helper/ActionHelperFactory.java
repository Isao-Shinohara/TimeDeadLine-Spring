package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

public class ActionHelperFactory
{
	public static BaseActionHelper Create(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		switch (actionResultEntity.getActionType())
		{
			case Attack:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList);
			case Skill:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList);
			case Defense:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList);
			case Revival:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList);
			default:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList);
		}
	}
}
