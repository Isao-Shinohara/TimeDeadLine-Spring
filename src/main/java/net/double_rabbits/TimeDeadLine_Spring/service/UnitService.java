package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

@Service
public class UnitService extends BaseService
{
	public void Entry(RoomEntity roomEntity, Long userId)
	{
		List<UnitEntity> unitEntityList = this.unitRepository.findByRoomEntity(roomEntity);
		int count = 0;
		for (int i = 0; i < BattleContext.AllUnitNum; i++) {
			UnitEntity unitEntity = unitEntityList.get(i);
			if (unitEntity.HasSetUserId()) continue;

			unitEntityList.get(i).setUserId(userId);
			count++;
			if (count >= BattleContext.AllUnitNum / BattleContext.TemNum) break;
		}

		this.unitRepository.save(unitEntityList);
		this.unitRepository.flush();
	}

	public List<UnitEntity> GetUnitByRoomEntity(RoomEntity roomEntity)
	{
		return this.unitRepository.findByRoomEntity(roomEntity);
	}
}
