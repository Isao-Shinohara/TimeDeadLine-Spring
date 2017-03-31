package net.double_rabbits.TimeDeadLine_Spring.controller;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import net.double_rabbits.TimeDeadLine_Spring.core.MsgPackUtil;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.service.BaseService;
import net.double_rabbits.TimeDeadLine_Spring.service.RoomService;
import net.double_rabbits.TimeDeadLine_Spring.service.TurnBasedService;
import net.double_rabbits.TimeDeadLine_Spring.service.UnitService;
import net.double_rabbits.TimeDeadLine_Spring.service.UserService;

abstract public class BaseController<T, U>
{
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected T req;
	protected U res;
	protected Class<T> reqClazz;
	protected Class<U> resClazz;
	protected UserEntity sendUserEntity;

	@Autowired
	protected BaseService service;

	@Autowired
	protected UserService userService;

	@Autowired
	protected RoomService roomService;

	@Autowired
	protected UnitService unitService;

	@Autowired
	protected TurnBasedService turnBasedService;

	@SuppressWarnings("unchecked")
	public void Initialize(UserEntity sendUserEntity, byte[] data) throws JsonParseException, JsonMappingException, IOException
	{
		this.sendUserEntity = sendUserEntity;
		this.req = (T) MsgPackUtil.Deserialize(data, reqClazz);
	}

	public U GetResponse()
	{
		this.res = this.CreateResponse(this.req);
		return this.res;
	}

	public List<UserEntity> GetReceiveUserEntityList()
	{
		return this.CreateReceiveUserEntityList();
	}

	abstract public U CreateResponse(T req);

	abstract public List<UserEntity> CreateReceiveUserEntityList();
}
