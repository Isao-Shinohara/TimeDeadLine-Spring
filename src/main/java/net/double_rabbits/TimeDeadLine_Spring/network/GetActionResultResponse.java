package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionResultValue;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class GetActionResultResponse extends BaseResponse
{
	public List<ActionResultValue> ActionResultValueList;

	public GetActionResultResponse(List<ActionResultValue> actionResultValueList)
	{
		super(MsgPackExtensionType.GetActionResult);
		this.ActionResultValueList = actionResultValueList;
	}
}
