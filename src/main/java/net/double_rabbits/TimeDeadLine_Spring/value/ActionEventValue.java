package net.double_rabbits.TimeDeadLine_Spring.value;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;

public class ActionEventValue
{
	public Long UnitId;
	public ActionType ActionType;
	public List<ActionEventDetailValue> ActionEventDetailValueList = new ArrayList<ActionEventDetailValue>();

	public ActionEventValue(AttackStandyEntity attackStandyEntity, List<ActionEventDetailValue> actionEventDetailValueList)
	{
		this.UnitId = attackStandyEntity.getUnitId();
		this.ActionType = attackStandyEntity.getActionType();
		this.ActionEventDetailValueList = actionEventDetailValueList;
	}
}
