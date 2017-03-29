package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UnitEntity
{
	@Id
	@GeneratedValue
	private Long unitId;
	private Long roomId;
	private Long userId;

	public UnitEntity()
	{
		super();
		this.userId = 0L;
	}

	public UnitEntity(Long roomId)
	{
		this();
		this.roomId = roomId;
	}

	public boolean HasSetUserId()
	{
		return this.userId > 0;
	}
}
