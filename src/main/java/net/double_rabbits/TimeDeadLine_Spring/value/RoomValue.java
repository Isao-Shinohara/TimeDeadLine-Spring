package net.double_rabbits.TimeDeadLine_Spring.value;

import net.double_rabbits.TimeDeadLine_Spring.config.BattleContext;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;

public class RoomValue
{
	public Long RoomId;
	public Long OwnerUserId;
	public BattleModeType BattleModeType;
	public int RoomNumber;
	public boolean IsReadyForBattle;

	public RoomValue(RoomEntity roomEntity)
	{
		if(roomEntity == null){
			this.RoomId = BattleContext.ErrorRoomId;
			return;
		}
		
		this.RoomId = roomEntity.getRoomId();
		this.OwnerUserId = roomEntity.getOwnerUserId();
		this.BattleModeType = roomEntity.getBattleModeType();
		this.RoomNumber = roomEntity.getRoomNumber();
		this.IsReadyForBattle = roomEntity.isReadyForBattle();
	}
}
