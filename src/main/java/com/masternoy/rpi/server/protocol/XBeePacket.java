package com.masternoy.rpi.server.protocol;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class XBeePacket {
	private short length = -1;
	private byte frameType = -1;
	private byte frameId = -1;
	private byte[] atCommand = new byte[2];
	private byte[] serialAddress = new byte[8];
	private byte[] sourceNetworkAddress = new byte[2];
	private byte receiveOpts = -1;
	private byte numberOfSampleSets = -1;
	private short digitalSampleSets = -1;
	private short digitalChannelMask = -1;
	private byte analogChannelMask = -1;
	private short digitalSampleData = -1;
	private byte analogSampleData = -1;
	private byte checkSum = -1;
	private ByteBuf payload;

	public ByteBuf getPayload() {
		return payload;
	}

	// TODO: [imasternoy] fix via state machine
	public void setPayload(ByteBuf pld) {
		payload = pld;
		if (payload.readableBytes() > 1) {
			setFrameType(payload.readByte());
			if (frameType == (byte) 0x97) { // RESPONSE packet
				setFrameId(payload.readByte());
			}
			payload.readBytes(serialAddress, 0, 8);
			payload.readBytes(sourceNetworkAddress, 0, 2);
			if (frameType == (byte) 0x97) { // RESPONSE packet
				atCommand[0] = payload.readByte();
				atCommand[1] = payload.readByte();
			}
			setReceiveOpts(payload.readByte());
			setNumberOfSampleSets(payload.readByte());
			setDigitalChannelMask(payload.readShort());
			setAnalogChannelMask(payload.readByte());
			setDigitalSampleData(payload.readShort());
			if (getAnalogChannelMask() != 0) {
				// READ UNTIL CHECKSUM
				setAnalogSampleData(payload.readByte());
			}
			setCheckSum(payload.readByte());
		}
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public byte getFrameType() {
		return frameType;
	}

	public void setFrameType(byte frameType) {
		this.frameType = frameType;
	}

	public byte[] getSerialAddress() {
		return serialAddress;
	}

	public Long getSerialAddressAsLong() {
		ByteBuf buf = Unpooled.wrappedBuffer(serialAddress);
		return buf.readLong();
	}

	public Short geNetworkSerialAddressAsLong() {
		ByteBuf buf = Unpooled.wrappedBuffer(sourceNetworkAddress);
		return buf.readShort();
	}

	public String getSourceNetworkSerialAddressAsHexString() {
		ByteBuf buf = Unpooled.wrappedBuffer(sourceNetworkAddress);
		StringBuilder sb = new StringBuilder();
		for (byte c : buf.array()) {
			sb.append(String.format("%02x", c).toUpperCase());
		}
		return sb.toString();
	}

	public String getSerialAddressAsHexString() {
		ByteBuf buf = Unpooled.wrappedBuffer(serialAddress);
		StringBuilder sb = new StringBuilder();
		for (byte c : buf.array()) {
			sb.append(String.format("%02x", c).toUpperCase());
		}
		return sb.toString();
	}

	public void setSerialAddress(byte[] serialAddress) {
		this.serialAddress = serialAddress;
	}

	public byte[] getSourceNetworkAddress() {
		return sourceNetworkAddress;
	}

	public void setSourceNetworkAddress(byte[] sourceNetworkAddress) {
		this.sourceNetworkAddress = sourceNetworkAddress;
	}

	public byte getReceiveOpts() {
		return receiveOpts;
	}

	public void setReceiveOpts(byte receiveOpts) {
		this.receiveOpts = receiveOpts;
	}

	public byte getNumberOfSampleSets() {
		return numberOfSampleSets;
	}

	public void setNumberOfSampleSets(byte numberOfSampleSets) {
		this.numberOfSampleSets = numberOfSampleSets;
	}

	public void setDigitalChannelMask(short digitalChannelMask) {
		this.digitalChannelMask = digitalChannelMask;
	}

	public short getDigitalChannelMask() {
		return digitalChannelMask;
	}

	public short getDigitalSampleSets() {
		return digitalSampleSets;
	}

	public void setDigitalSampleSets(short digitalSampleSets) {
		this.digitalSampleSets = digitalSampleSets;
	}

	public byte getAnalogChannelMask() {
		return analogChannelMask;
	}

	public void setAnalogChannelMask(byte analogChannelMask) {
		this.analogChannelMask = analogChannelMask;
	}

	public short getDigitalSampleData() {
		return digitalSampleData;
	}

	public void setDigitalSampleData(short digitalSampleData) {
		this.digitalSampleData = digitalSampleData;
	}

	public byte getAnalogSampleData() {
		return analogSampleData;
	}

	public void setAnalogSampleData(byte analogSampleData) {
		this.analogSampleData = analogSampleData;
	}

	public byte getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(byte checkSum) {
		this.checkSum = checkSum;
	}

	public byte getFrameId() {
		return frameId;
	}

	public void setFrameId(byte frameId) {
		this.frameId = frameId;
	}

	public byte[] getAtCommand() {
		return atCommand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XBeePacket [length=");
		builder.append(length);
		builder.append(", frameType=");
		builder.append(String.format("%02x", frameType));
		builder.append(", serialAddress=");
		builder.append(getSerialAddressAsHexString());
		builder.append(", sourceNetworkAddress=");
		builder.append(getSourceNetworkSerialAddressAsHexString());
		builder.append(", receiveOpts=");
		builder.append(receiveOpts);
		builder.append(", numberOfSampleSets=");
		builder.append(numberOfSampleSets);
		builder.append(", digitalSampleSets=");
		builder.append(digitalSampleSets);
		builder.append(", digitalChannelMask=");
		builder.append(String.format("%02x", digitalChannelMask));
		builder.append(", analogChannelMask=");
		builder.append(String.format("%02x", analogChannelMask));
		builder.append(", digitalSampleData=");
		builder.append(String.format("%02x", digitalSampleData));
		builder.append(", analogSampleData=");
		builder.append(String.format("%02x", analogSampleData));
		builder.append(", checkSum=");
		builder.append(String.format("%02x", checkSum));
		if (payload != null) {
			builder.append(", payload=");
			builder.append(Hex.encodeHexString(payload.array()));
		}
		builder.append("]");
		return builder.toString();
	}

}
