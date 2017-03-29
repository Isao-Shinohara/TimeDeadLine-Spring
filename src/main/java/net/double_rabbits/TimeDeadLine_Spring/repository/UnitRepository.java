package net.double_rabbits.TimeDeadLine_Spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.UnitEntity;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long>
{
	List<UnitEntity> findByRoomId(Long roomId);

	List<UnitEntity> findByRoomIdAndUserId(Long roomId, Long userId);
}
