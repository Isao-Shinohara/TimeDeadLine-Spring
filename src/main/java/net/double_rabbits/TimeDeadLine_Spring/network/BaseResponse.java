package net.double_rabbits.TimeDeadLine_Spring.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class BaseResponse
{
	protected static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);

	protected MsgPackExtensionType msgPackExtensionType = MsgPackExtensionType.None;

	public BaseResponse()
	{
	}

	public BaseResponse(MsgPackExtensionType msgPackExtensionType)
	{
		this.msgPackExtensionType = msgPackExtensionType;
	}
}
