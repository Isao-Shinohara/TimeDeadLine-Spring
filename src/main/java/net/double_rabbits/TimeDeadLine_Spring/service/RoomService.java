package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.TimeEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Service
public class RoomService extends BaseService
{
	public RoomEntity Create(UserEntity userEntity, int roomNumber)
	{
		RoomEntity roomEntity = this.roomRepository.findByOwnerUserIdAndRoomNumber(userEntity.getUserId(), roomNumber);
		if (Objects.equals(roomEntity, null)) {
			roomEntity = new RoomEntity(userEntity.getUserId(), roomNumber);
			roomEntity.AddUserEntity(userEntity);

			TimeEntity timeEntity = new TimeEntity();
			timeEntity.setRoomEntity(roomEntity);
			roomEntity.setTimeEntity(timeEntity);

			this.roomRepository.save(roomEntity);

			userEntity.setRoomId(roomEntity.getRoomId());
			this.userRepository.save(userEntity);
		}

		return roomEntity;
	}
}
