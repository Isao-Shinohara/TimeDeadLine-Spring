package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionHelperFactory
{
	public static BaseActionHelper Create(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		switch (actionResultEntity.getActionType())
		{
			case Attack:
				return new ActionAttackHelper(actionResultEntity, defenseUnitIdList, unitRepository);
			case Skill:
				return new ActionSkillHelper(actionResultEntity, defenseUnitIdList, unitRepository);
			case Defense:
				return new ActionDefenseHelper(actionResultEntity, defenseUnitIdList, unitRepository);
			case Revival:
				return new ActionRevivalHelper(actionResultEntity, defenseUnitIdList, unitRepository);
			default:
				return new ActionNoneHelper(actionResultEntity, defenseUnitIdList, unitRepository);
		}
	}
}
