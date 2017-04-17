package net.double_rabbits.TimeDeadLine_Spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>
{
	RoomEntity findByOwnerUserIdAndRoomNumber(Long ownerUserId, int roomNumber);

	RoomEntity findByOwnerUserId(Long ownerUserId);

	RoomEntity findByRoomNumberAndIsReadyForBattle(int roomNumber, boolean isReadyForBattle);

	List<RoomEntity> findByBattleModeType(BattleModeType battleModeType);
}
