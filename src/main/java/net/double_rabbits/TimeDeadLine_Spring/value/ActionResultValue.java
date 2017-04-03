package net.double_rabbits.TimeDeadLine_Spring.value;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

public class ActionResultValue
{
	public Long UnitId;
	public ActionType ActionType;
	public List<ActionResultDetailValue> ActionResultDetailValueList = new ArrayList<ActionResultDetailValue>();

	public ActionResultValue(ActionResultEntity actionResultEntiy)
	{
		this.UnitId = actionResultEntiy.getUnitId();
		this.ActionType = actionResultEntiy.getActionType();

		actionResultEntiy.getActionResultDetailEntityList().forEach(entity -> {
			this.ActionResultDetailValueList.add(new ActionResultDetailValue(entity));
		});
	}
}
