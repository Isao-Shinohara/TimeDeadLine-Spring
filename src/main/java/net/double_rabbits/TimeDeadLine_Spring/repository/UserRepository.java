package net.double_rabbits.TimeDeadLine_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	UserEntity findBySessionId(String sessionId);

	UserEntity findByUid(String uid);

	UserEntity findByRoomId(Long roomId);
}
