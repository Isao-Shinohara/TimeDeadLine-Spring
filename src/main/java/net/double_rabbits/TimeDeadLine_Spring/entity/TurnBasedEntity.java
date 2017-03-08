package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TurnBasedEntity extends BaseEntity
{
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private RoomEntity roomEntity;

	private Boolean hasBattleStarted;
	private Boolean hasBattleEnded;
	private Boolean canCountDown;
	private int turn;
	private int remainSeconds;

	public TurnBasedEntity()
	{
		super();
		this.hasBattleStarted = false;
		this.hasBattleEnded = false;
		this.turn = 0;
		this.remainSeconds = 10;
	}

	public void BattleStart()
	{
		this.hasBattleStarted = true;
	}

	public void BattleEnd()
	{
		this.hasBattleEnded = true;
	}

	public Boolean CanCountDown()
	{
		return this.hasBattleStarted && this.canCountDown;
	}

	public void CountDown()
	{
		if (this.remainSeconds <= 0) return;
		this.remainSeconds--;
	}
}
