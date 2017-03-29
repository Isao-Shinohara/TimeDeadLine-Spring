package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UnitEntity
{
	@Id
	@GeneratedValue
	private Long unitId;
	private Long userId;
	@ManyToOne
	private RoomEntity roomEntity;

	public UnitEntity()
	{
		super();
		this.userId = 0L;
	}

	public UnitEntity(RoomEntity roomEntity)
	{
		this();
		this.roomEntity = roomEntity;
	}

	public boolean HasSetUserId()
	{
		return this.userId > 0;
	}
}
