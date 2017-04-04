package net.double_rabbits.TimeDeadLine_Spring.helper;

import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

abstract public class BaseActionHelper
{
	protected ActionResultEntity actionResultEntity;

	public BaseActionHelper(ActionResultEntity actionResultEntity)
	{
		this.actionResultEntity = actionResultEntity;
	}

	abstract public ActionResultDetailEntity Do();
}
