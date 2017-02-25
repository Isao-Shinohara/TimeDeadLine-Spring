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
	private int roomNumber;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RoomUserEntity> roomUserEntityList;
	@OneToOne(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TimeEntity timeEntity;

	public RoomEntity()
	{
		super();
	}

	public RoomEntity(Long ownerUserId, int roomNumber)
	{
		super();
		this.ownerUserId = ownerUserId;
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
