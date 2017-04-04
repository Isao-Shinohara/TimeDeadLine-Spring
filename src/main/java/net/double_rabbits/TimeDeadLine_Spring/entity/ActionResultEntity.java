package net.double_rabbits.TimeDeadLine_Spring.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;
import lombok.Data;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;

@Entity
@Proxy(lazy = false)
@Data
public class ActionResultEntity
{
	@Id
	@GeneratedValue
	private Long actionResultId;
	private Long unitId;
	private ActionType actionType;

	@ManyToOne
	private RoomEntity roomEntity;

	@OneToMany(mappedBy = "actionResultEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ActionResultDetailEntity> actionResultDetailEntityList;

	public ActionResultEntity()
	{
		super();
	}

	public ActionResultEntity(RoomEntity roomEntity, AttackStandyEntity attackStandyEntity)
	{
		this();
		this.roomEntity = roomEntity;
		this.unitId = attackStandyEntity.getUnitId();
		this.actionType = attackStandyEntity.getActionType();
		this.actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
	}
}
