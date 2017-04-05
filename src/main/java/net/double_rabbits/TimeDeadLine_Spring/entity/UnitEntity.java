package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;

@Entity
@Data
public class UnitEntity
{
	@Id
	@GeneratedValue
	private Long unitId;
	private Long userId;
	private int characterId;
	private int teamType;
	private int positionType;
	private int hp;
	private int maxHp;

	@ManyToOne
	private RoomEntity roomEntity;

	public UnitEntity()
	{
		super();
		this.userId = 0L;
		this.maxHp = BattleContext.UnitMaxHp;
		this.hp = this.maxHp;
	}

	public UnitEntity(RoomEntity roomEntity)
	{
		this();
		this.roomEntity = roomEntity;
	}

	public void SetUnitData(int index, Long userId)
	{
		this.userId = userId;

		int unitNum = (BattleContext.AllUnitNum / BattleContext.TemNum);
		this.teamType = index / unitNum == 0 ? 1 : 2;
		this.positionType = (index % unitNum) + 1;
	}

	public void SetCpuData(int index)
	{
		this.SetUnitData(index, BattleContext.CpuUserId);
	}

	public boolean HasSetUserId()
	{
		return this.userId > 0;
	}

	public boolean IsAlive()
	{
		return this.hp > 0;
	}

	public boolean IsDead()
	{
		return !this.IsAlive();
	}
}
