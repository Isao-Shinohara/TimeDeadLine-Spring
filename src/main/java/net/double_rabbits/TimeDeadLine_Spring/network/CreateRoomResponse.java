package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class CreateRoomResponse extends BaseResponse
{
	public Long RoomId;

	public CreateRoomResponse(Long roomId)
	{
		super(MsgPackExtensionType.CreateRoom);
		this.RoomId = roomId;
	}
}