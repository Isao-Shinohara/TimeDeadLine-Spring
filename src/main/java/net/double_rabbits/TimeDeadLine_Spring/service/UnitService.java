package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

@Service
public class UnitService extends BaseService
{
	public void Create(Long roomId)
	{
		List<UnitEntity> unitEntityList = new ArrayList<UnitEntity>();

		for (int i = 0; i < 10; i++) {
			unitEntityList.add(new UnitEntity(roomId));
		}

		this.unitRepository.save(unitEntityList);
		this.unitRepository.flush();
	}

	public void Entry(Long roomId, Long userId)
	{
		List<UnitEntity> unitEntityList = this.unitRepository.findByRoomId(roomId);
		for (int i = 0; i < 10; i++) {
			unitEntityList.get(i).setUserId(userId);
		}

		this.unitRepository.save(unitEntityList);
		this.unitRepository.flush();
	}

	public List<UnitEntity> GetByRoomIdAndUserId(Long roomId, Long userId)
	{
		return this.unitRepository.findByRoomIdAndUserId(roomId, userId);
	}
}
