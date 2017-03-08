package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class TurnBasedResponse extends BaseResponse
{
	protected MsgPackExtensionType msgPackExtensionType = MsgPackExtensionType.Time;
	protected PublishType publicshType = PublishType.RoomUser;

	public int RemainSeconds;

	public TurnBasedResponse()
	{
		super();
	}

	public TurnBasedResponse(int remainSeconds)
	{
		super();
		this.RemainSeconds = remainSeconds;
	}
}
