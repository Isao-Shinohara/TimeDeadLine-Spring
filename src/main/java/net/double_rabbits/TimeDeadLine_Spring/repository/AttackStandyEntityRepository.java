package net.double_rabbits.TimeDeadLine_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;

@Repository
public interface AttackStandyEntityRepository extends JpaRepository<AttackStandyEntity, Long>
{
}
