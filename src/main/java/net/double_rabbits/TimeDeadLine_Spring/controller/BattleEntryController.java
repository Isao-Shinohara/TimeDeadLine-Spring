package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
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
		List<UnitStatusValue> unitStatusValueList = this.unitService.BattleEntry(req.RoomId, this.sendUserEntity.getUserId());
		return new BattleEntryResponse(unitStatusValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
