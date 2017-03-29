package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

@Service
public class UnitService extends BaseService
{
	public void Create(Long roomId)
	{
		List<UnitEntity> unitEntityList = new ArrayList<UnitEntity>();

		for (int i = 0; i < BattleContext.AllUnitNum; i++) {
			unitEntityList.add(new UnitEntity(roomId));
		}

		this.unitRepository.save(unitEntityList);
		this.unitRepository.flush();
	}

	public void Entry(Long roomId, Long userId)
	{
		List<UnitEntity> unitEntityList = this.unitRepository.findByRoomId(roomId);
		int count = 0;
		for (int i = 0; i < BattleContext.AllUnitNum; i++) {
			UnitEntity unitEntity = unitEntityList.get(i);
			if (unitEntity.HasSetUserId()) continue;

			unitEntityList.get(i).setUserId(userId);
			count++;
			if (count >= BattleContext.TemNum / 2) break;
		}

		this.unitRepository.save(unitEntityList);
		this.unitRepository.flush();
	}

	public List<UnitEntity> GetByRoomIdAndUserId(Long roomId, Long userId)
	{
		return this.unitRepository.findByRoomIdAndUserId(roomId, userId);
	}
}
