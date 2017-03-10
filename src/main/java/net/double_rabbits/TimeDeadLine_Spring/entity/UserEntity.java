package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity
{
	@Id
	@GeneratedValue
	private Long userId;
	private String uid;
	private String sessionId;
	private Long roomId = 0L;

	public UserEntity()
	{
		super();
	}

	public UserEntity(String uid, String sessionId)
	{
		super();
		this.uid = uid;
		this.sessionId = sessionId;
	}
}
