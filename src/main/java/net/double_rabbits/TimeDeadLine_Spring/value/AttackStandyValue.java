package net.double_rabbits.TimeDeadLine_Spring.value;

import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;

public class AttackStandyValue
{
	public Long UnitId;
	public ActionType ActionType;

	public AttackStandyValue(AttackStandyEntity attackStandyEntity)
	{
		this.UnitId = attackStandyEntity.getUnitId();
		this.ActionType = attackStandyEntity.getActionType();
	}
}
