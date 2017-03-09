package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;

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
	private int round;
	private int seconds;

	public TurnBasedEntity()
	{
		super();
		this.hasBattleStarted = false;
		this.hasBattleEnded = false;
		this.canCountDown = false;
		this.round = 1;
		this.seconds = BattleContext.InitialSeconds;
	}

	public void BattleStart()
	{
		this.hasBattleStarted = true;
		this.canCountDown = true;
	}

	public void BattleEnd()
	{
		this.hasBattleEnded = true;
	}

	public void CountDown()
	{
		if (!this.hasBattleStarted) return;
		if (this.hasBattleEnded) return;
		if (!this.canCountDown) return;
		if (this.seconds <= 0) return;
		this.seconds--;
	}
}
