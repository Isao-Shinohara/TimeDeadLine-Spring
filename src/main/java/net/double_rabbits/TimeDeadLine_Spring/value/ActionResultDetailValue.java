package net.double_rabbits.TimeDeadLine_Spring.value;

import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;

public class ActionResultDetailValue
{
	public Long UnitId;
	public int Hp;

	public ActionResultDetailValue(ActionResultDetailEntity actionEventDetailEntity)
	{
		this.UnitId = actionEventDetailEntity.getUnitId();
		this.Hp = actionEventDetailEntity.getHp();
	}
}
