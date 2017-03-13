package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;
import net.double_rabbits.TimeDeadLine_Spring.network.MsgPackExtensionType;

public class RoundStartController extends BaseController<BaseRequest, BaseResponse>
{
	public RoundStartController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = BaseResponse.class;
	}

	@Override
	public BaseResponse CreateResponse(BaseRequest req)
	{
		RoomEntity roomEntity = this.roomService.roomRepository.findOne(this.sendUserEntity.getRoomId());
		roomEntity.getTurnBasedEntity().RountStart();
		this.roomService.roomRepository.save(roomEntity);
		return new BaseResponse(MsgPackExtensionType.RoundStart);
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		return this.roomService.GetUserEntityListInOwnRoom(this.sendUserEntity);
	}
}
