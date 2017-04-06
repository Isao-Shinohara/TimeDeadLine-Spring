package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.entity.ActionResultEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.helper.ActionHelperFactory;
import net.double_rabbits.TimeDeadLine_Spring.helper.BaseActionHelper;

@Component
public class DoAllActionScheduledTasks extends BaseScheduledTasks
{
	@Scheduled(cron = "* * * * * *")
	public void DoAllAction()
	{
		List<RoomEntity> roomEntityList = this.roomRepository.findAll();
		for (RoomEntity roomEntity : roomEntityList) {
			if (roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;
			if (roomEntity.getAttackStandyEntityList().size() <= 0) continue;

			// Do Action.
			roomEntity.getAttackStandyEntityList().forEach(entity -> {
				ActionResultEntity actionResultEntity = new ActionResultEntity(roomEntity, entity);
				BaseActionHelper actionHelper = ActionHelperFactory.Create(actionResultEntity, roomEntity.GetDefenseUnitIdList(), this.unitRepository);
				actionResultEntity.setActionResultDetailEntityList(actionHelper.Do());
				roomEntity.getActionResultEntityList().add(actionResultEntity);
			});

			roomEntity.getAttackStandyEntityList().clear();
			this.roomRepository.save(roomEntity);
		}
	}
}
