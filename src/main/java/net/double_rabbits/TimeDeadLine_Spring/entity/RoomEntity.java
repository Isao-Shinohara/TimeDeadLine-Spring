package net.double_rabbits.TimeDeadLine_Spring.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.network.BattleModeType;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RoomEntity extends BaseEntity
{
	@Id
	@GeneratedValue
	private Long roomId;
	private Long ownerUserId;
	private Long roomUserId;
	private BattleModeType battleModeType;
	private int roomNumber;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RoomUserEntity> roomUserEntityList;
	@OneToOne(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TurnBasedEntity turnBasedEntity;

	public RoomEntity()
	{
		super();
	}

	public RoomEntity(Long ownerUserId, BattleModeType battleModeType, int roomNumber)
	{
		super();
		this.ownerUserId = ownerUserId;
		this.battleModeType = battleModeType;
		this.roomNumber = roomNumber;
		this.roomUserEntityList = new ArrayList<RoomUserEntity>();
	}

	public void AddUserEntity(UserEntity userEntity)
	{
		RoomUserEntity roomUserEntity = new RoomUserEntity(userEntity.getUserId(), this);
		this.roomUserEntityList.add(roomUserEntity);
	}

	public void RemoveUserEntity(UserEntity userEntity)
	{
		this.roomUserEntityList.removeIf(roomUserEntity -> {
			return roomUserEntity.getUserId() == userEntity.getUserId();
		});
	}
}