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
	}

	public UnitEntity(Long roomId)
	{
		super();
		this.roomId = roomId;
	}
}
