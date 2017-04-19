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
public class CreateRoomResponse extends BaseResponse
{
	public RoomValue RoomValue;

	public CreateRoomResponse(RoomEntity roomEntity)
	{
		super(MsgPackExtensionType.CreateRoom);
		this.RoomValue = new RoomValue(roomEntity);
	}
}