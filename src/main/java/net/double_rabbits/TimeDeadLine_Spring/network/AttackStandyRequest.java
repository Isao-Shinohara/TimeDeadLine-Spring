package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AttackStandyRequest extends BaseRequest
{
	public Long UnitId;
	public ActionType ActionType;
}
