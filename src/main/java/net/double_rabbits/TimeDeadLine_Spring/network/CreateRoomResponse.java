package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class CreateRoomResponse extends BaseResponse
{
	public Long RoomId;
	public List<PlayerStatusValue> PlayerStatusValueList = new ArrayList<PlayerStatusValue>();

	public CreateRoomResponse(Long roomId, List<PlayerStatusValue> playerStatusValueList)
	{
		super(MsgPackExtensionType.CreateRoom);
		this.RoomId = roomId;
		this.PlayerStatusValueList = playerStatusValueList;
	}
}