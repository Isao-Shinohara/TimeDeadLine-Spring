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
	public boolean IsStartBattle;
	public List<UnitStatusValue> UnitStatusValueList;

	public BattleEntryResponse(boolean isStartBattle, List<UnitStatusValue> unitStatusValueList)
	{
		super(MsgPackExtensionType.BattleEntry);
		this.IsStartBattle = isStartBattle;
		this.UnitStatusValueList = unitStatusValueList;
	}
}