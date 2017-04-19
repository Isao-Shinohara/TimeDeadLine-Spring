package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.value.RoomValue;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class JoinRoomResponse extends BaseResponse
{
	public int RequestRoomNumber;
	public RoomValue RoomValue;

	public JoinRoomResponse(int roomNumber, RoomEntity roomEntity)
	{
		super(MsgPackExtensionType.JoinRoom);
		this.RequestRoomNumber = roomNumber;
		this.RoomValue = new RoomValue(roomEntity);
	}
}