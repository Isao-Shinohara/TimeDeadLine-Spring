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

	private Boolean canCountDown;
	private Boolean hasBattleStarted;
	private Boolean hasBattleEnded;
	private Boolean isInputPhase;
	private int round;
	private int seconds;
	private int onePeriodSeconds;

	public TurnBasedEntity()
	{
		super();
		this.canCountDown = false;
		this.hasBattleStarted = false;
		this.hasBattleEnded = false;
		this.isInputPhase = false;
		this.round = 0;
		this.seconds = BattleContext.OnePeriodSeconds;
		this.onePeriodSeconds = BattleContext.OnePeriodSeconds;
	}

	public void BattleStart()
	{
		this.hasBattleStarted = true;
	}

	public void BattleEnd()
	{
		this.hasBattleEnded = true;
	}

	public void NextTurn()
	{
		this.round++;
		this.seconds = BattleContext.OnePeriodSeconds;
	}

	public void RountStart()
	{
		this.isInputPhase = true;
	}

	public void CountDown()
	{
		if (!this.hasBattleStarted) return;
		if (this.hasBattleEnded) return;
		if (!this.isInputPhase) return;

		if (this.seconds <= 0) {
			this.battleEnd();
			return;
		}

		// ミリ秒端数調整のためカウントダウン開始のタイミングをわざと1回ずらしている
		if (!this.canCountDown) {
			this.canCountDown = true;
			return;
		}

		this.seconds--;
	}

	private void battleEnd()
	{
		this.canCountDown = false;
		this.isInputPhase = false;

		if (this.round == BattleContext.FinalRound) {
			this.hasBattleEnded = true;
		}
	}
}
