package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleModeType;

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

	public RoomEntity BattleStart(UserEntity userEntity)
	{
		RoomEntity roomEntity = this.roomRepository.findOne(userEntity.getRoomId());
		roomEntity.getTurnBasedEntity().BattleStart();
		this.roomRepository.save(roomEntity);

		return roomEntity;
	}
}
