package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TurnBasedEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleModeType;

@Service
public class RoomService extends BaseService
{
	public RoomEntity Create(UserEntity userEntity, BattleModeType battleModeType, int roomNumber)
	{
		RoomEntity roomEntity = this.roomRepository.findByOwnerUserIdAndRoomNumber(userEntity.getUserId(), roomNumber);
		if (Objects.equals(roomEntity, null)) {
			roomEntity = new RoomEntity(userEntity.getUserId(), battleModeType, roomNumber);
			roomEntity.AddUserEntity(userEntity);

			TurnBasedEntity timeEntity = new TurnBasedEntity();
			timeEntity.setRoomEntity(roomEntity);
			roomEntity.setTurnBasedEntity(timeEntity);

			this.roomRepository.save(roomEntity);

			userEntity.setRoomId(roomEntity.getRoomId());
			this.userRepository.save(userEntity);
		}

		return roomEntity;
	}
}
