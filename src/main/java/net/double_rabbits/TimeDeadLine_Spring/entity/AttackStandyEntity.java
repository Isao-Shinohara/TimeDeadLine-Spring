package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;

@Entity
@Data
public class AttackStandyEntity
{
	@Id
	@GeneratedValue
	private Long attackStandyValueIs;
	private Long unitId;
	private ActionType actionType;

	@ManyToOne
	private RoomEntity roomEntity;

	public AttackStandyEntity(Long unitId, ActionType actionType)
	{
		this.unitId = unitId;
		this.actionType = actionType;
	}
}
