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
	public Long RoomId;
	public List<UnitStatusValue> UnitStatusValueList;

	public BattleEntryResponse(Long roomId, List<UnitStatusValue> unitStatusValueList)
	{
		super(MsgPackExtensionType.CreateRoom);
		this.RoomId = roomId;
		this.UnitStatusValueList = unitStatusValueList;
	}
}