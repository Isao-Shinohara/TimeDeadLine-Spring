package net.double_rabbits.TimeDeadLine_Spring.value;

import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

public class UserValue
{
	public Long UserId;
	public String Uid;
	public String SessionId;

	public UserValue(UserEntity userEntity)
	{
		this.UserId = userEntity.getUserId();
		this.Uid = userEntity.getUid();
		this.SessionId = userEntity.getSessionId();
	}
}
