package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;

public class ActionRevivalHelper extends BaseActionHelper
{
	public ActionRevivalHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		super(actionResultEntity, defenseUnitIdList, unitRepository);
	}

	@Override
	public List<ActionResultDetailEntity> Do()
	{
		// Alive or Dead.
		UnitEntity unitEntity = this.getUnitEntityByUnitId(this.actionResultEntity.getUnitId());
		if (unitEntity.IsAlive()) return this.getNonActionResultDetailEntityList();

		// Target.
		UnitEntity targetUnitEntity = this.actionResultEntity.getRoomEntity().GetUnitEntityByUnitId(this.actionResultEntity.getUnitId());
		if (targetUnitEntity.IsAlive()) { return this.getNonActionResultDetailEntityList(); }

		// Revival.
		List<ActionResultDetailEntity> actionResultDetailEntityList = new ArrayList<ActionResultDetailEntity>();
		int hp = targetUnitEntity.getMaxHp();
		actionResultDetailEntityList.add(new ActionResultDetailEntity(targetUnitEntity.getUnitId(), hp, this.actionResultEntity));

		// Save Data.
		targetUnitEntity.setHp(hp);
		this.unitRepository.save(targetUnitEntity);

		return actionResultDetailEntityList;
	}
}
