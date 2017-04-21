package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

@Service
public class RoomService extends BaseService
{
	public RoomEntity Create(UserEntity userEntity, BattleModeType battleModeType, int roomNumber)
	{
		RoomEntity roomEntity = this.roomRepository.findByOwnerUserIdAndRoomNumber(userEntity.getUserId(), roomNumber);
		if (Objects.equals(roomEntity, null)) {
			// Room.
			roomEntity = new RoomEntity(userEntity.getUserId(), battleModeType, roomNumber);
			roomEntity.AddUserEntity(userEntity);

			// TurnBased
			TurnBasedEntity timeEntity = new TurnBasedEntity();
			timeEntity.setRoomEntity(roomEntity);
			roomEntity.setTurnBasedEntity(timeEntity);
			this.roomRepository.save(roomEntity);

			// User.
			userEntity.setRoomId(roomEntity.getRoomId());
			this.userRepository.save(userEntity);

			// Unit.
			List<UnitEntity> unitEntityList = new ArrayList<UnitEntity>();
			for (int i = 0; i < BattleContext.AllUnitNum; i++) {
				unitEntityList.add(new UnitEntity(roomEntity));
			}
			this.unitRepository.save(unitEntityList);
		}

		return roomEntity;
	}

	public RoomEntity Join(UserEntity userEntity, int roomNumber)
	{
		RoomEntity roomEntity = this.roomRepository.findByRoomNumberAndIsReadyForBattle(roomNumber, false);
		if (!Objects.equals(roomEntity, null)) {
			// Room.
			roomEntity.AddUserEntity(userEntity);
			this.roomRepository.save(roomEntity);

			// User.
			userEntity.setRoomId(roomEntity.getRoomId());
			this.userRepository.save(userEntity);
		}

		return roomEntity;
	}

	public void Delete(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		if (!Objects.equals(roomEntity, null)) {
			if (roomEntity.getRoomUserEntityList().size() <= 1) {
				this.roomRepository.delete(roomEntity);
			} else {
				roomEntity.RemoveUserEntity(userEntity);
				this.roomRepository.save(roomEntity);
			}
		}
	}

	public RoomEntity BattleEntry(Long roomId, Long userId)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(roomId);
		List<UnitEntity> unitEntityList = this.unitRepository.findByRoomEntity(roomEntity);

		// For Human.
		int count = 0;
		for (int i = 0; i < BattleContext.AllUnitNum; i++) {
			UnitEntity unitEntity = unitEntityList.get(i);
			if (unitEntity.HasSetUserId()) continue;

			unitEntity.SetUnitData(i, userId);
			count++;
			if (count >= BattleContext.AllUnitNum / BattleContext.TemNum) break;
		}

		// For CPU when SingleMode.
		if (roomEntity.getBattleModeType() == BattleModeType.Single) {
			for (int i = 0; i < BattleContext.AllUnitNum; i++) {
				UnitEntity unitEntity = unitEntityList.get(i);
				if (unitEntity.HasSetUserId()) continue;

				unitEntity.SetCpuData(i);
			}
		}

		// Set Random CharacterId.
		if (roomEntity.isReadyForBattle()) {
			List<Integer> characterIdList = new ArrayList<Integer>();
			for (int i = 1; i <= BattleContext.AllUnitNum; i++) {
				characterIdList.add(i);
			}
			Collections.shuffle(characterIdList);

			for (int i = 0; i < BattleContext.AllUnitNum; i++) {
				unitEntityList.get(i).setCharacterId(characterIdList.get(i));
			}
		}

		this.unitRepository.save(unitEntityList);

		return roomEntity;
	}

	public RoomEntity BattleStart(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getTurnBasedEntity().BattleStart();
		this.roomRepository.save(roomEntity);

		return roomEntity;
	}
}
