package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.AttackStandyRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.AttackStandyResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;

public class AttackStandyController extends BaseController<AttackStandyRequest, AttackStandyResponse>
{
	public AttackStandyController()
	{
		this.reqClazz = AttackStandyRequest.class;
		this.resClazz = AttackStandyResponse.class;
	}

	@Override
	public AttackStandyResponse CreateResponse(AttackStandyRequest req)
	{
		this.actionService.AttackStandy(this.sendUserEntity, req.UnitId, req.ActionType);
		List<AttackStandyValue> attackStandyValueList = this.actionService.GetAttackStandyValueList(this.sendUserEntity);
		return new AttackStandyResponse(attackStandyValueList);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.userService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
