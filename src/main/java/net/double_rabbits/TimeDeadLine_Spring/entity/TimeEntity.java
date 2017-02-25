package net.double_rabbits.TimeDeadLine_Spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TimeEntity extends BaseEntity
{
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private RoomEntity roomEntity;

	private int turn;
	private int remainSeconds;

	public TimeEntity()
	{
		super();
		this.turn = 0;
		this.remainSeconds = 10;
	}

	public void CountDown()
	{
		this.remainSeconds--;
	}
}
