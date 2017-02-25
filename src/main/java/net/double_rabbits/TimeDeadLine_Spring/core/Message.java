package net.double_rabbits.TimeDeadLine_Spring.core;

import org.msgpack.jackson.dataformat.MessagePackExtensionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Message
{
	public String Uid;
	public MessagePackExtensionType Payload;

	public Message()
	{
	}

	public Message(String uid, MessagePackExtensionType payload)
	{
		this.Uid = uid;
		this.Payload = payload;
	}
}