package net.double_rabbits.TimeDeadLine_Spring.network;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BaseRequest
{
	public String Uid;

	public BaseRequest()
	{
	}

	public BaseRequest(String uid)
	{
		this.Uid = uid;
	}
}
