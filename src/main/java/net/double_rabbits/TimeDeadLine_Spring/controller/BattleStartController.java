package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

public class BattleStartController extends BaseController<BaseRequest, TurnBasedResponse>
{
	public BattleStartController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = TurnBasedResponse.class;
	}

	@Override
	public TurnBasedResponse CreateResponse(BaseRequest req)
	{
		RoomEntity roomEntity = this.roomService.BattleStart(this.sendUserEntity);
		return new TurnBasedResponse(MsgPackExtensionType.BattleStart, roomEntity.getTurnBasedEntity());
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
