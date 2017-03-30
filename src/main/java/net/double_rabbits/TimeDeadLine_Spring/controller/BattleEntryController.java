package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomUserEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleEntryRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleEntryResponse;
import net.double_rabbits.TimeDeadLine_Spring.network.UnitStatusValue;

public class BattleEntryController extends BaseController<BattleEntryRequest, BattleEntryResponse>
{
	public BattleEntryController()
	{
		this.reqClazz = BattleEntryRequest.class;
		this.resClazz = BattleEntryResponse.class;
	}

	@Override
	public BattleEntryResponse CreateResponse(BattleEntryRequest req)
	{
		RoomEntity roomEntity = this.roomService.roomRepository.findOne(req.RoomId);

		this.unitService.Entry(roomEntity, this.sendUserEntity.getUserId());
		List<UnitEntity> unitEntityList = this.unitService.GetUnitByRoomEntity(roomEntity);

		List<UnitStatusValue> unitStatusValueList = new ArrayList<UnitStatusValue>();
		for (UnitEntity unitEntity : unitEntityList) {
			unitStatusValueList.add(new UnitStatusValue(unitEntity));
		}

		return new BattleEntryResponse(unitStatusValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		List<UserEntity> list = new ArrayList<UserEntity>();

		RoomEntity roomEntity = this.service.roomRepository.findOne(req.RoomId);
		for (RoomUserEntity roomUserEntity : roomEntity.getRoomUserEntityList()) {
			UserEntity userEntity = this.service.userRepository.findOne(roomUserEntity.getUserId());
			if (!Objects.equals(userEntity, null)) {
				list.add(userEntity);
			}
		}

		return list;
	}
}
