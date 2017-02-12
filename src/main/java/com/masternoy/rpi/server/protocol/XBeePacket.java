package com.masternoy.rpi.server.protocol;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class XBeePacket {
	private int length = -1;
	private byte frameType = -1;
	private long serialAddress = -1;
	private short sourceNetworkAddress = -1;
	private byte receiveOpts = -1;
	private byte numberOfSampleSets = -1;
	private short digitalSampleSets = -1;
	private byte analogChannelMask = -1;
	private short digitalSampleData = -1;
	private byte analogSampleData = -1;
	private byte pinsADC = -1;
	private byte checkSum = -1;
	private ByteBuf payload;

	public XBeePacket() {
		payload = Unpooled.buffer(Constants.Protocol.MAX_LENGTH);
	}

	public ByteBuf getPayload() {
		return payload;
	}

	public void setPayload(ByteBuf payload) {
		this.payload = payload;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Byte getFrameType() {
		return frameType;
	}

	public void setFrameType(Byte frameType) {
		this.frameType = frameType;
	}

	public Long getSerialAddress() {
		return serialAddress;
	}

	public void setSerialAddress(Long serialAddress) {
		this.serialAddress = serialAddress;
	}

	public Short getSourceNetworkAddress() {
		return sourceNetworkAddress;
	}

	public void setSourceNetworkAddress(Short sourceNetworkAddress) {
		this.sourceNetworkAddress = sourceNetworkAddress;
	}

	public Byte getReceiveOpts() {
		return receiveOpts;
	}

	public void setReceiveOpts(Byte receiveOpts) {
		this.receiveOpts = receiveOpts;
	}

	public Byte getNumberOfSampleSets() {
		return numberOfSampleSets;
	}

	public void setNumberOfSampleSets(Byte numberOfSampleSets) {
		this.numberOfSampleSets = numberOfSampleSets;
	}

	public Short getDigitalSampleSets() {
		return digitalSampleSets;
	}

	public void setDigitalSampleSets(Short digitalSampleSets) {
		this.digitalSampleSets = digitalSampleSets;
	}

	public Byte getAnalogChannelMask() {
		return analogChannelMask;
	}

	public void setAnalogChannelMask(Byte analogChannelMask) {
		this.analogChannelMask = analogChannelMask;
	}

	public Short getDigitalSampleData() {
		return digitalSampleData;
	}

	public void setDigitalSampleData(Short digitalSampleData) {
		this.digitalSampleData = digitalSampleData;
	}

	public Byte getAnalogSampleData() {
		return analogSampleData;
	}

	public void setAnalogSampleData(Byte analogSampleData) {
		this.analogSampleData = analogSampleData;
	}

	public Byte getPinsADC() {
		return pinsADC;
	}

	public void setPinsADC(Byte pinsADC) {
		this.pinsADC = pinsADC;
	}

	public Byte getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Byte checkSum) {
		this.checkSum = checkSum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XBeePacket [length=");
		builder.append(length);
		builder.append(", \npayload=");
		builder.append(Hex.encodeHexString(payload.array()));
		builder.append("]");
		return builder.toString();
	}

}
