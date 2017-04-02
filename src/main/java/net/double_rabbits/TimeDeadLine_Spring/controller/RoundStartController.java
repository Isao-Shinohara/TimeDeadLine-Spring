package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

public class RoundStartController extends BaseController<BaseRequest, TurnBasedResponse>
{
	public RoundStartController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = TurnBasedResponse.class;
	}

	@Override
	public TurnBasedResponse CreateResponse(BaseRequest req)
	{
		TurnBasedEntity turnBasedEntity = this.turnBasedService.RoundStart(this.sendUserEntity);
		return new TurnBasedResponse(MsgPackExtensionType.RoundStart, turnBasedEntity);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
