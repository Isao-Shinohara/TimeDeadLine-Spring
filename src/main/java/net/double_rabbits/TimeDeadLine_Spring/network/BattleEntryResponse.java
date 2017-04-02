package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.value.TurnBasedValue;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BattleEntryResponse extends BaseResponse
{
	public boolean IsReadyForBattle;
	public TurnBasedValue TurnBasedValue;
	public List<UnitStatusValue> UnitStatusValueList;

	public BattleEntryResponse(boolean isReadyForBattle, TurnBasedValue turnBasedValue, List<UnitStatusValue> unitStatusValueList)
	{
		super(MsgPackExtensionType.BattleEntry);
		this.IsReadyForBattle = isReadyForBattle;
		this.TurnBasedValue = turnBasedValue;
		this.UnitStatusValueList = unitStatusValueList;
	}
}