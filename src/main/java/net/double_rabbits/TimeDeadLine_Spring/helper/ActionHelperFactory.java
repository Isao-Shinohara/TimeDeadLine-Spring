package net.double_rabbits.TimeDeadLine_Spring.helper;

import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

public class ActionHelperFactory
{
	public static BaseActionHelper Create(ActionResultEntity actionResultEntity)
	{
		switch (actionResultEntity.getActionType())
		{
			case Attack:
				return new ActionAttackHelper(actionResultEntity);
			case Skill:
				return new ActionAttackHelper(actionResultEntity);
			case Defense:
				return new ActionAttackHelper(actionResultEntity);
			case Revival:
				return new ActionAttackHelper(actionResultEntity);
			default:
				return new ActionAttackHelper(actionResultEntity);
		}
	}
}
