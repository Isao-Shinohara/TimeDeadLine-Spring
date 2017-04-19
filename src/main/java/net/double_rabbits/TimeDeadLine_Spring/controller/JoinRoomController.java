package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.JoinRoomRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.JoinRoomResponse;

public class JoinRoomController extends BaseController<JoinRoomRequest, JoinRoomResponse>
{
	public JoinRoomController()
	{
		this.reqClazz = JoinRoomRequest.class;
		this.resClazz = JoinRoomResponse.class;
	}

	@Override
	public JoinRoomResponse CreateResponse(JoinRoomRequest req)
	{
		RoomEntity roomEntity = this.roomService.Join(this.sendUserEntity, req.RoomNumber);
		return new JoinRoomResponse(this.req.RoomNumber, roomEntity);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		if (this.res.RoomValue.RoomId != BattleContext.ErrorRoomId) {
			// Success.
			return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
		} else {
			// Error.
			return this.userService.GetUserEntitySelfList(this.sendUserEntity);
		}
	}
}
