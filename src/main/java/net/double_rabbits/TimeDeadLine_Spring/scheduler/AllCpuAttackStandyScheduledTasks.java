package net.double_rabbits.TimeDeadLine_Spring.scheduler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.double_rabbits.TimeDeadLine_Spring.entity.AttackStandyEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.network.AttackStandyResponse;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

@Component
public class AllCpuAttackStandyScheduledTasks extends BaseScheduledTasks
{
	@Scheduled(cron = "* * * * * *")
	public void AllCpuAttackStandy()
	{
		List<RoomEntity> roomEntityList = this.roomRepository.findByBattleModeType(BattleModeType.Single);
		for (RoomEntity roomEntity : roomEntityList) {
			if (!roomEntity.getTurnBasedEntity().getIsInputPhase()) continue;

			// Action.
			roomEntity.GetNotAttackStandyCpuUnitEntityList().forEach(entity -> {
				AttackStandyEntity attackStandyEntity = new AttackStandyEntity(roomEntity, entity.getUnitId(), ActionType.Attack);
				roomEntity.getAttackStandyEntityList().add(attackStandyEntity);
			});
			this.attackStandyEntityRepository.save(roomEntity.getAttackStandyEntityList());

			// Response.
			List<AttackStandyValue> attackStandyValueList = new ArrayList<AttackStandyValue>();
			roomEntity.getAttackStandyEntityList().forEach(e -> {
				attackStandyValueList.add(new AttackStandyValue(e));
			});

			// SendMessage.
			List<UserEntity> receiveUserEntityList = this.userRepository.findByRoomId(roomEntity.getRoomId());
			this.sendMessage(new AttackStandyResponse(attackStandyValueList), receiveUserEntityList);
		}
	}
}
