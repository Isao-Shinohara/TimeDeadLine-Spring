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
	private int readyForNextTurnNum;
	private boolean isReadyForNextTurn;
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
		this.readyForNextTurnNum++;
		this.isReadyForNextTurn = this.readyForNextTurnNum == this.battleModeType.ordinal();
	}

	public void RountStart()
	{
		this.isInputPhase = true;
		this.readyForRoundStartNum++;
		this.isReadyForRoundStart = this.readyForRoundStartNum == this.battleModeType.ordinal();
	}

	public void CountDown()
	{
		if (!this.hasBattleStarted) return;
		if (this.hasBattleEnded) return;
		if (!this.isReadyForRoundStart) return;

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
		this.readyForNextTurnNum = 0;
		this.readyForRoundStartNum = 0;

		if (this.round == BattleContext.FinalRound) {
			this.hasBattleEnded = true;
		}
	}
}
