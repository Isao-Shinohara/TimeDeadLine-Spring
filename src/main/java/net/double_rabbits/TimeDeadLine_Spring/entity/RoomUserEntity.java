package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RoomUserEntity
{
	@Id
	@GeneratedValue
	private Long roomUserId;
	private Long userId;
	@ManyToOne
	private RoomEntity roomEntity;

	public RoomUserEntity()
	{
		super();
	}

	public RoomUserEntity(Long userId, RoomEntity roomEntity)
	{
		super();
		this.roomEntity = roomEntity;
		this.userId = userId;
	}
}
