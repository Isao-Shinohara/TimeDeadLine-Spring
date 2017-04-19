package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.UnitStatusValue;

@Service
public class UnitService extends BaseService
{
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
