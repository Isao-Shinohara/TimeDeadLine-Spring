package net.double_rabbits.TimeDeadLine_Spring.network;

import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class UnitStatusValue
{
	public Long UnitId;
	public Long UserId;
	public int CharacterId;
	public int TeamType;
	public int PositionType;
	public int Hp;
	public int MaxHp;

	public UnitStatusValue(UnitEntity unitEntity)
	{
		this.UnitId = unitEntity.getUnitId();
		this.UserId = unitEntity.getUserId();
		this.CharacterId = unitEntity.getCharacterId();
		this.TeamType = unitEntity.getTeamType();
		this.PositionType = unitEntity.getPositionType();
		this.Hp = unitEntity.getHp();
		this.MaxHp = unitEntity.getMaxHp();
	}
}
