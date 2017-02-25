package net.double_rabbits.TimeDeadLine_Spring.core;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.msgpack.jackson.dataformat.MessagePackExtensionType;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.web.socket.BinaryMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.double_rabbits.TimeDeadLine_Spring.network.BaseResponse;

public class MsgPackUtil
{

	public static byte[] Serialize(Object obj) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
		return objectMapper.writeValueAsBytes(obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T Deserialize(byte[] src, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
		return (T) objectMapper.readValue(src, clazz);
	}

	public static BinaryMessage CreateBinaryMessage(String uid, BaseResponse response) throws JsonProcessingException
	{
		byte[] bytes = MsgPackUtil.Serialize(response);
		MessagePackExtensionType ext = new MessagePackExtensionType((byte) response.getMsgPackExtensionType().ordinal(), bytes);

		Message resVal = new Message(uid, ext);
		ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
		byte[] resBytes = objectMapper.writeValueAsBytes(resVal);

		return new BinaryMessage(ByteBuffer.wrap(resBytes));
	}
}

