package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

abstract public class BaseActionHelper
{
	protected ActionResultEntity actionResultEntity;
	protected List<Long> defenseUnitIdList;

	public BaseActionHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList)
	{
		this.actionResultEntity = actionResultEntity;
		this.defenseUnitIdList = defenseUnitIdList;
	}

	abstract public ActionResultDetailEntity Do();

	protected List<UnitEntity> getOpponentUnitEntityList()
	{
		RoomEntity roomEntity = this.actionResultEntity.getRoomEntity();
		return roomEntity.GetOpponentUnitEntityListByUnitId(this.actionResultEntity.getUnitId());
	}

	protected int calcHpByDamage(UnitEntity targetUnitEntity, int minDamage, int maxDamage)
	{
		int damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);
		damage = (int) (damage * this.getDefenseRate(targetUnitEntity));
		int remainHp = targetUnitEntity.getHp() - damage;
		return remainHp > 0 ? remainHp : 0;
	}

	protected float getDefenseRate(UnitEntity targetUnitEntity)
	{
		float defenseRate = 1.0f;
		if (this.defenseUnitIdList.stream().anyMatch(unitId -> unitId == targetUnitEntity.getUnitId())) {
			defenseRate = BattleContext.DefenseRate;
		}
		return defenseRate;
	}
}
