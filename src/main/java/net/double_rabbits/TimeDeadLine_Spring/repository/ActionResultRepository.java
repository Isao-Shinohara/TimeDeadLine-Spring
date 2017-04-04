package net.double_rabbits.TimeDeadLine_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;

@Repository
public interface ActionResultRepository extends JpaRepository<ActionResultEntity, Long>
{
}
