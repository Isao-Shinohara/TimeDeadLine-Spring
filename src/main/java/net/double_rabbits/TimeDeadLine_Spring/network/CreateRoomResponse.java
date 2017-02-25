package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class CreateRoomResponse extends BaseResponse
{
	protected MsgPackExtensionType msgPackExtensionType = MsgPackExtensionType.CreateRoom;
	protected PublishType publicshType = PublishType.RoomUser;

	public Long RoomId;

	public CreateRoomResponse()
	{
		super();
	}

	public CreateRoomResponse(Long roomId)
	{
		super();
		this.RoomId = roomId;
	}
}