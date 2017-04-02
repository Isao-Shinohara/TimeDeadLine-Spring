package net.double_rabbits.TimeDeadLine_Spring.value;

import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;

public class TurnBasedValue
{
	public Boolean HasBattleStarted;
	public Boolean HasBattleEnded;
	public Boolean IsInputPhase;
	public int Round;
	public int Seconds;
	public int OnePeriodSeconds;

	public TurnBasedValue(TurnBasedEntity turnBasedEntity)
	{
		this.HasBattleStarted = turnBasedEntity.getHasBattleStarted();
		this.HasBattleEnded = turnBasedEntity.getHasBattleEnded();
		this.IsInputPhase = turnBasedEntity.getIsInputPhase();
		this.Round = turnBasedEntity.getRound();
		this.Seconds = turnBasedEntity.getSeconds();
		this.OnePeriodSeconds = turnBasedEntity.getOnePeriodSeconds();
	}
}
