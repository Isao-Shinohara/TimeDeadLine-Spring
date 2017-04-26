package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

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
	private BattleModeType battleModeType;
	private int readyForBattleStartNum;
	private int readyForRoundStartNum;
	private boolean isReadyForRoundStart;

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
		this.readyForBattleStartNum = 0;
	}

	public synchronized void BattleStart()
	{
		this.readyForBattleStartNum++;
		this.hasBattleStarted = this.readyForBattleStartNum == this.battleModeType.ordinal();
	}

	public synchronized void BattleEnd()
	{
		this.hasBattleEnded = true;
	}

	public synchronized void NextTurn()
	{
		this.round++;
		this.seconds = BattleContext.OnePeriodSeconds;
	}

	public synchronized void RountStart()
	{
		this.readyForRoundStartNum++;
		this.isReadyForRoundStart = this.readyForRoundStartNum == this.battleModeType.ordinal();
		this.isInputPhase = this.readyForRoundStartNum == this.battleModeType.ordinal();
	}

	public synchronized void CountDown()
	{
		if (!this.hasBattleStarted) return;
		if (this.hasBattleEnded) return;
		if (!this.isReadyForRoundStart) return;

		if (this.seconds <= 0) {
			this.roundEnd();
			return;
		}

		// ミリ秒端数調整のためカウントダウン開始のタイミングをわざと1回ずらしている
		if (!this.canCountDown) {
			this.canCountDown = true;
			return;
		}

		this.seconds--;
	}

	private synchronized void roundEnd()
	{
		this.canCountDown = false;
		this.isReadyForRoundStart = false;
		this.isInputPhase = false;
		this.readyForRoundStartNum = 0;

		if (this.round == BattleContext.FinalRound) {
			this.hasBattleEnded = true;
		}
	}
}
