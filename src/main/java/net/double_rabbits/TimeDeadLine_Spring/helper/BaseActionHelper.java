package net.double_rabbits.TimeDeadLine_Spring.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultDetailEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.repository.UnitRepository;
import net.double_rabbits.TimeDeadLine_Spring.service.BaseService;

abstract public class BaseActionHelper
{
	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);

	protected ActionResultEntity actionResultEntity;
	protected List<Long> defenseUnitIdList;
	protected UnitRepository unitRepository;

	public BaseActionHelper(ActionResultEntity actionResultEntity, List<Long> defenseUnitIdList, UnitRepository unitRepository)
	{
		this.actionResultEntity = actionResultEntity;
		this.defenseUnitIdList = defenseUnitIdList;
		this.unitRepository = unitRepository;
	}

	abstract public List<ActionResultDetailEntity> Do();

	protected UnitEntity getUnitEntityByUnitId(Long unitId)
	{
		return this.unitRepository.findOne(unitId);
	}

	protected List<UnitEntity> getOpponentUnitEntityList()
	{
		RoomEntity roomEntity = this.actionResultEntity.getRoomEntity();
		return roomEntity.GetOpponentUnitEntityListByUnitId(this.actionResultEntity.getUnitId());
	}

	protected List<UnitEntity> getOpponentAliveUnitEntityList()
	{
		RoomEntity roomEntity = this.actionResultEntity.getRoomEntity();
		return roomEntity.GetOpponentAliveUnitEntityListByUnitId(this.actionResultEntity.getUnitId());
	}

	protected List<UnitEntity> getOpponentDeadUnitEntityList()
	{
		RoomEntity roomEntity = this.actionResultEntity.getRoomEntity();
		return roomEntity.GetOpponentDeadUnitEntityListByUnitId(this.actionResultEntity.getUnitId());
	}

	protected List<ActionResultDetailEntity> getNonActionResultDetailEntityList()
	{
		return new ArrayList<ActionResultDetailEntity>();
	}

	protected int calcHpByDamage(UnitEntity targetUnitEntity, int minDamage, int maxDamage)
	{
		// Calc.
		int damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);
		damage = (int) (damage * this.getDefenseRate(targetUnitEntity));
		int remainHp = targetUnitEntity.getHp() - damage;

		// Save Data.
		targetUnitEntity.setHp(remainHp);
		this.unitRepository.saveAndFlush(targetUnitEntity);

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
