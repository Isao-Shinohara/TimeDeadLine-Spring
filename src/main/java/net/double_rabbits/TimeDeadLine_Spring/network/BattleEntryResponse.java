package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BattleEntryResponse extends BaseResponse
{
	public List<UnitStatusValue> UnitStatusValueList;

	public BattleEntryResponse(List<UnitStatusValue> unitStatusValueList)
	{
		super(MsgPackExtensionType.BattleEntry);
		this.UnitStatusValueList = unitStatusValueList;
	}
}