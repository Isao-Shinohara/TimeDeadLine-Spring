package net.double_rabbits.TimeDeadLine_Spring.network;

import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class UnitStatusValue
{
	public Long UnitId;
	public Long UserId;

	public UnitStatusValue(UnitEntity unitEntity)
	{
		this.UnitId = unitEntity.getUnitId();
		this.UserId = unitEntity.getUserId();
	}
}
