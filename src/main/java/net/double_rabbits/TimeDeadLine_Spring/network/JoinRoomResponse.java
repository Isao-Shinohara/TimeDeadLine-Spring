package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class JoinRoomResponse extends BaseResponse
{
	public Long RoomId;
	public int RoomNumber;

	public JoinRoomResponse(RoomEntity roomEntity, int roomNumber)
	{
		super(MsgPackExtensionType.JoinRoom);
		this.RoomId = roomEntity != null ? roomEntity.getRoomId() : BattleContext.ErrorRoomId;
		this.RoomNumber = roomNumber;
	}
}