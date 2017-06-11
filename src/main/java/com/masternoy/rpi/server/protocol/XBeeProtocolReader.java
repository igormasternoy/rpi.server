package com.masternoy.rpi.server.protocol;

import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ByteProcessor;

/**
 * @author imasternoy
 */
public class XBeeProtocolReader extends ByteToMessageDecoder {
	private static final Logger log = Logger.getLogger(XBeeProtocolReader.class);

	XBeePacket packet;
	State currentState;

	private enum State {
		START, READ_BODY_LENGTH, READ_BODY, END
	}

	public XBeeProtocolReader() {
		currentState = State.START;
	}

	@Override
	protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		Object decoded = decode(ctx, in);
		if (decoded != null) {
			out.add(decoded);
		}
	}

	protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
		if (currentState == State.START) {
			final int eol = findPacketStart(buffer);
			if (eol < 0) {
				log.trace("Packet without signature found");
				return null;
			}
			log.trace("Reader index:" + buffer.readerIndex());
			buffer.readerIndex(eol + 1);
			log.trace("Reader index:" + buffer.readerIndex());
			currentState = State.READ_BODY_LENGTH;
		}

		if (currentState == State.READ_BODY_LENGTH) {
			if (buffer.readableBytes() < 2) {
				log.trace("Not enough bytes to construct packet length");
				return null;
			}
			packet = new XBeePacket();
			// needed for test purposes
			byte[] low = new byte[1];
			byte[] high = new byte[1];
			low[0] = buffer.readByte();
			high[0] = buffer.readByte();
			log.trace(Hex.encodeHexString(low) + "" + Hex.encodeHexString(high));
			//Checksum is the always the last byte
			packet.setLength((short) (assemblyShort(low[0], high[0])+1));
			currentState = State.READ_BODY;
		}

		if (currentState == State.READ_BODY) {
			// LENGTH IS KNOWN
			if (buffer.readableBytes() < packet.getLength()) {
				// PACKET IS NOT FULL
				return null;
			}
			int length = packet.getLength(); //length + checkSumByte
			ByteBuf buf = Unpooled.buffer(length);
			buffer.readBytes(buf, 0, length);
			buf.writerIndex(length);
			packet.setPayload(buf);
			buffer.discardReadBytes();
			currentState = State.START;
			return packet;
		}
		return null;
	}

	private static short assemblyShort(int i2, int i1) {
		return (short) (i1 + (i2 << 8));
	}

	private static int findPacketStart(final ByteBuf buffer) {
		return buffer.forEachByte(new ByteProcessor.IndexOfProcessor(Constants.Protocol.SIGNATURE));
	}

}
