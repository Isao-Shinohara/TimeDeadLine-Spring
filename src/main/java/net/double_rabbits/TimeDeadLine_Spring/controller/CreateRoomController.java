package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.CreateRoomRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.CreateRoomResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

public class CreateRoomController extends BaseController<CreateRoomRequest, CreateRoomResponse>
{
	public CreateRoomController()
	{
		this.reqClazz = CreateRoomRequest.class;
		this.resClazz = CreateRoomResponse.class;
	}

	@Override
	public CreateRoomResponse CreateResponse(CreateRoomRequest req)
	{
		RoomEntity roomEntity = this.roomService.Create(this.sendUserEntity, BattleModeType.values()[req.BattleModeType], req.RoomNumber);
		return new CreateRoomResponse(roomEntity);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntitySelfList(this.sendUserEntity);
	}
}
