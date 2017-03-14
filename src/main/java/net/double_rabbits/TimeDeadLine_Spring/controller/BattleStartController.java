package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.network.TurnBasedResponse;

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
		RoomEntity roomEntity = this.roomService.roomRepository.findOne(this.sendUserEntity.getRoomId());
		roomEntity.getTurnBasedEntity().BattleStart();
		this.roomService.roomRepository.save(roomEntity);

		return new TurnBasedResponse(MsgPackExtensionType.BattleStart, roomEntity.getTurnBasedEntity());
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.roomService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
