package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class TurnBasedResponse extends BaseResponse
{
	protected MsgPackExtensionType msgPackExtensionType = MsgPackExtensionType.TurnBased;
	protected PublishType publicshType = PublishType.RoomUser;

	public Boolean HasBattleStarted;
	public Boolean HasBattleEnded;
	public Boolean IsInputPhase;
	public int Round;
	public int Seconds;
	public int OnePeriodSeconds;

	public TurnBasedResponse()
	{
		super();
	}

	public TurnBasedResponse(TurnBasedEntity turnBasedEntity)
	{
		super();
		this.HasBattleStarted = turnBasedEntity.getHasBattleStarted();
		this.HasBattleEnded = turnBasedEntity.getHasBattleEnded();
		this.IsInputPhase = turnBasedEntity.getIsInputPhase();
		this.Round = turnBasedEntity.getRound();
		this.Seconds = turnBasedEntity.getSeconds();
		this.OnePeriodSeconds = turnBasedEntity.getOnePeriodSeconds();
	}
}
