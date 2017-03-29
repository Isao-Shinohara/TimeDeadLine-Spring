package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleModeType;
import net.double_rabbits.TimeDeadLine_Spring.network.CreateRoomRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.CreateRoomResponse;
import net.double_rabbits.TimeDeadLine_Spring.network.UnitStatusValue;

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

		this.unitService.Create(roomEntity.getRoomId());
		this.unitService.Entry(roomEntity.getRoomId(), this.sendUserEntity.getUserId());
		List<UnitEntity> unitEntityList = this.unitService.GetByRoomIdAndUserId(roomEntity.getRoomId(), this.sendUserEntity.getUserId());

		List<UnitStatusValue> unitStatusValueList = new ArrayList<UnitStatusValue>();
		for (UnitEntity unitEntity : unitEntityList) {
			unitStatusValueList.add(new UnitStatusValue(unitEntity));
		}

		return new CreateRoomResponse(roomEntity.getRoomId(), unitStatusValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		List<UserEntity> list = new ArrayList<UserEntity>();
		list.add(this.sendUserEntity);
		return list;
	}
}
