package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.GetActionResultResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionResultValue;

public class GetActionResultController extends BaseController<BaseRequest, GetActionResultResponse>
{
	public GetActionResultController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = GetActionResultResponse.class;
	}

	@Override
	public GetActionResultResponse CreateResponse(BaseRequest req)
	{
		List<ActionResultValue> actionEventValueList = this.actionService.GetActionResultList(this.sendUserEntity);
		return new GetActionResultResponse(actionEventValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntitySelfList(this.sendUserEntity);
	}
}
