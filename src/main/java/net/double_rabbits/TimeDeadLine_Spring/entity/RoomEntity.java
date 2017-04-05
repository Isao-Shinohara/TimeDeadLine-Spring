package net.double_rabbits.TimeDeadLine_Spring.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.value.ActionType;
import net.double_rabbits.TimeDeadLine_Spring.value.BattleModeType;

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
	private boolean readyForBattle;

	@OneToOne(mappedBy = "roomEntity", cascade = CascadeType.ALL)
	private TurnBasedEntity turnBasedEntity;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<RoomUserEntity> roomUserEntityList;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<UnitEntity> unitEntityList;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<AttackStandyEntity> attackStandyEntityList;
	@OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ActionResultEntity> actionResultEntityList;

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
		this.actionResultEntityList = new ArrayList<ActionResultEntity>();
		this.readyForBattle = false;
	}

	public void AddUserEntity(UserEntity userEntity)
	{
		RoomUserEntity roomUserEntity = new RoomUserEntity(userEntity.getUserId(), this);
		this.roomUserEntityList.add(roomUserEntity);
		this.updateReadyForBattle();
	}

	public void RemoveUserEntity(UserEntity userEntity)
	{
		this.roomUserEntityList.removeIf(roomUserEntity -> {
			return roomUserEntity.getUserId() == userEntity.getUserId();
		});
		this.updateReadyForBattle();
	}

	public boolean HasGotRoundResult()
	{
		return !this.getTurnBasedEntity().getIsInputPhase() && this.getAttackStandyEntityList().size() <= 0;
	}

	public UnitEntity GetUnitEntityByUnitId(Long unitId)
	{
		return this.unitEntityList.stream().filter(entity -> entity.getUnitId() == unitId).findFirst().orElse(null);
	}

	public List<UnitEntity> GetOpponentUnitEntityListByUnitId(Long unitId)
	{
		UnitEntity unitEntity = this.GetUnitEntityByUnitId(unitId);
		return this.unitEntityList.stream().filter(entity -> entity.getUserId() != unitEntity.getUserId()).collect(Collectors.toList());
	}

	public List<UnitEntity> GetOpponentAliveUnitEntityListByUnitId(Long unitId)
	{
		List<UnitEntity> unitEntityList = this.GetOpponentUnitEntityListByUnitId(unitId);
		return unitEntityList.stream().filter(entity -> entity.getHp() > 0).collect(Collectors.toList());
	}

	public List<UnitEntity> GetOpponentDeadUnitEntityListByUnitId(Long unitId)
	{
		List<UnitEntity> unitEntityList = this.GetOpponentUnitEntityListByUnitId(unitId);
		return unitEntityList.stream().filter(entity -> entity.getHp() <= 0).collect(Collectors.toList());
	}

	public List<Long> GetDefenseUnitIdList()
	{
		return this.getAttackStandyEntityList().stream().filter(entity -> entity.getActionType() == ActionType.Defense).map(entity -> entity.getUnitId()).collect(Collectors.toList());
	}

	private void updateReadyForBattle()
	{
		this.readyForBattle = this.roomUserEntityList.size() == this.battleModeType.ordinal();
	}
}
