package net.double_rabbits.TimeDeadLine_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>
{
	RoomEntity findByOwnerUserIdAndRoomNumber(Long ownerUserId, int roomNumber);

	RoomEntity findByOwnerUserId(Long ownerUserId);
}
