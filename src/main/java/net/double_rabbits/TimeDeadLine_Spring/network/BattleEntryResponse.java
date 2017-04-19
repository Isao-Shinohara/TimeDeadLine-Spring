package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.entity.RoomEntity;
import net.double_rabbits.TimeDeadLine_Spring.entity.UserEntity;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;
import net.double_rabbits.TimeDeadLine_Spring.value.RoomValue;
import net.double_rabbits.TimeDeadLine_Spring.value.TurnBasedValue;
import net.double_rabbits.TimeDeadLine_Spring.value.UnitStatusValue;
import net.double_rabbits.TimeDeadLine_Spring.value.UserValue;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BattleEntryResponse extends BaseResponse
{
	public UserValue UserValue;
	public RoomValue RoomValue;
	public TurnBasedValue TurnBasedValue;
	public List<UnitStatusValue> UnitStatusValueList;

	public BattleEntryResponse(UserEntity userEntity, RoomEntity roomEntity, TurnBasedValue turnBasedValue, List<UnitStatusValue> unitStatusValueList)
	{
		super(MsgPackExtensionType.BattleEntry);
		this.UserValue = new UserValue(userEntity);
		this.RoomValue = new RoomValue(roomEntity);
		this.TurnBasedValue = turnBasedValue;
		this.UnitStatusValueList = unitStatusValueList;
	}
}