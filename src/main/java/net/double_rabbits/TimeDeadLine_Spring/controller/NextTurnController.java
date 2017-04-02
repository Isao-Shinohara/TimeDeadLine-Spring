package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

public class NextTurnController extends BaseController<BaseRequest, TurnBasedResponse>
{
	public NextTurnController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = TurnBasedResponse.class;
	}

	@Override
	public TurnBasedResponse CreateResponse(BaseRequest req)
	{
		this.actionService.NextTurn(this.sendUserEntity);
		TurnBasedEntity turnBasedEntity = this.turnBasedService.NextTurn(this.sendUserEntity);
		return new TurnBasedResponse(MsgPackExtensionType.NextTurn, turnBasedEntity);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
