package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.double_rabbits.TimeDeadLine_Spring.value.AttackStandyValue;
import net.double_rabbits.TimeDeadLine_Spring.value.MsgPackExtensionType;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AttackStandyResponse extends BaseResponse
{
	public List<AttackStandyValue> AttackStandyValueList;

	public AttackStandyResponse(List<AttackStandyValue> attackStandyValueList)
	{
		super(MsgPackExtensionType.AttackStandy);
		this.AttackStandyValueList = attackStandyValueList;
	}
}