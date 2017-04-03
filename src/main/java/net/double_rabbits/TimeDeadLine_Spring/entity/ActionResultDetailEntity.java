package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ActionResultDetailEntity
{
	@Id
	@GeneratedValue
	private Long actionResultDetail;
	private Long unitId;
	private int hp;

	@ManyToOne
	private ActionResultEntity actionResultEntity;

	public ActionResultDetailEntity()
	{
		super();
	}

	public ActionResultDetailEntity(Long unitId, int hp, ActionResultEntity actionResultEntity)
	{
		this();
		this.unitId = unitId;
		this.hp = hp;
		this.actionResultEntity = actionResultEntity;
	}
}
