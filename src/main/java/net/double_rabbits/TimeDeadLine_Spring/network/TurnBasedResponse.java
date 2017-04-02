package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.value.TurnBasedValue;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class TurnBasedResponse extends BaseResponse
{
	public TurnBasedValue TurnBasedValue;

	public TurnBasedResponse(TurnBasedEntity turnBasedEntity)
	{
		this(MsgPackExtensionType.TurnBased, turnBasedEntity);
	}

	public TurnBasedResponse(MsgPackExtensionType msgPackExtensionType, TurnBasedEntity turnBasedEntity)
	{
		super(msgPackExtensionType);
		this.TurnBasedValue = new TurnBasedValue(turnBasedEntity);
	}
}
