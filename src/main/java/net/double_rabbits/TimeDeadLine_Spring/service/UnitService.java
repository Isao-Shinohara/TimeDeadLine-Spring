package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.UnitStatusValue;

@Service
public class UnitService extends BaseService
{
	public List<UnitStatusValue> BattleEntry(Long roomId, Long userId)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(roomId);

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

		return this.GetUnitStatusValueList(roomEntity);
	}

	public List<UnitEntity> GetUnitByRoomEntity(RoomEntity roomEntity)
	{
		return this.unitRepository.findByRoomEntity(roomEntity);
	}

	public List<UnitStatusValue> GetUnitStatusValueList(RoomEntity roomEntity)
	{
		List<UnitEntity> unitEntityList = this.unitRepository.findByRoomEntity(roomEntity);

		List<UnitStatusValue> unitStatusValueList = new ArrayList<UnitStatusValue>();
		for (UnitEntity unitEntity : unitEntityList) {
			unitStatusValueList.add(new UnitStatusValue(unitEntity));
		}

		return unitStatusValueList;
	}
}
