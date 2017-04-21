package net.double_rabbits.TimeDeadLine_Spring.network;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BaseRequest
{
	public Date SendDateTime;

	public BaseRequest()
	{
	}
}
