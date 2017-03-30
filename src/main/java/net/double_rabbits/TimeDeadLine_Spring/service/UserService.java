package net.double_rabbits.TimeDeadLine_Spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomUserEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Service
public class UserService extends BaseService
{
	public List<UserEntity> GetUserEntityListByRoomId(Long roomId)
	{
		List<UserEntity> list = new ArrayList<UserEntity>();

		RoomEntity roomEntity = this.roomRepository.findOne(roomId);
		for (RoomUserEntity roomUserEntity : roomEntity.getRoomUserEntityList()) {
			UserEntity userEntity = this.userRepository.findOne(roomUserEntity.getUserId());
			if (!Objects.equals(userEntity, null)) {
				list.add(userEntity);
			}
		}

		return list;
	}

	public List<UserEntity> GetUserEntityListInOwnRoom(UserEntity userEntity)
	{
		return this.GetUserEntityListByRoomId(userEntity.getRoomId());
	}
}
