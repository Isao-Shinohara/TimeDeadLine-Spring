package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleEntryRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleEntryResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.TurnBasedValue;
import net.double_rabbits.TimeDeadLine_Spring.value.UnitStatusValue;

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
		RoomEntity roomEntity = this.roomService.BattleEntry(req.RoomId, this.sendUserEntity.getUserId());
		TurnBasedValue turnBasedValue = new TurnBasedValue(roomEntity.getTurnBasedEntity());
		List<UnitStatusValue> unitStatusValueList = this.unitService.GetUnitStatusValueList(roomEntity);
		return new BattleEntryResponse(this.sendUserEntity, roomEntity, turnBasedValue, unitStatusValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
