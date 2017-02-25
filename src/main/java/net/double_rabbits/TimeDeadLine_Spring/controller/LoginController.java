package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseRequest;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;

public class LoginController extends BaseController<BaseRequest, BaseResponse>
{
	public LoginController()
	{
		this.reqClazz = BaseRequest.class;
		this.resClazz = BaseResponse.class;
	}

	@Override
	public BaseResponse CreateResponse(BaseRequest req)
	{
		return new BaseResponse();
	}

	@Override
	public List<UserEntity> CreateReceiveUserEntityList()
	{
		List<UserEntity> list = new ArrayList<UserEntity>();
		list.add(this.sendUserEntity);
		return list;
	}
}
