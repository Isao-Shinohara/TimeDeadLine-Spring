package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

public class ActionRevivalHelper extends BaseActionHelper
{
	public ActionRevivalHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		super(actionResultEntity, defenseUnitIdList);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Target.
		UnitEntity targetUnitEntity = this.actionResultEntity.getRoomEntity().GetUnitEntityByUnitId(this.actionResultEntity.getUnitId());

		// Damage.
		List<ActionResultDetailEntity> actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
		actionResultDetailEntityList.add(new ActionResultDetailEntity(targetUnitEntity.getUnitId(), targetUnitEntity.getMaxHp(), this.actionResultEntity));

		return actionResultDetailEntityList;
	}
}
