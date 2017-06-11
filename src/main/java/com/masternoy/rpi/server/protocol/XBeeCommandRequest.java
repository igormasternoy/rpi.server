package com.masternoy.rpi.server.protocol;

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class XBeeCommandRequest {
	
	private short length =-1;
	private byte frameType=-1;
	private byte frameId =-1;
	//address is 64-bit array
	private byte[] destAddress= new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,(byte) 0xFF,(byte)0xFF}; //broadcasting address
	// destination address 
	private byte[] destNetworkAddress= new byte[]{(byte) 0xFF, (byte) 0xFE};//broadcasting
	private byte remoteCmdOpt=-1;
	private byte[] atCmdName= new byte[2];
	private byte cmdParam = -1;
	
	public short getLength() {
		return (short) getPayload().readableBytes();
	}
	public byte getFrameType() {
		return frameType;
	}
	public void setFrameType(byte frameType) {
		this.frameType = frameType;
	}
	public byte getFrameId() {
		return frameId;
	}
	public void setFrameId(byte frameId) {
		this.frameId = frameId;
	}
	public byte[]  getDestAddress() {
		return destAddress;
	}
	public void setDestAddress(byte[]  destAddress) {
		this.destAddress = destAddress;
	}
	public byte[]  getDestNetworkAddress() {
		return destNetworkAddress;
	}
	public void setDestNetworkAddress(byte[]  destNetworkAddress) {
		this.destNetworkAddress = destNetworkAddress;
	}
	public byte getRemoteCmdOpt() {
		return remoteCmdOpt;
	}
	public void setRemoteCmdOpt(byte remoteCmdOpt) {
		this.remoteCmdOpt = remoteCmdOpt;
	}
	public byte[] getAtCmdName() {
		return atCmdName;
	}
	public void setAtCmdName(byte[] atCmdName) {
		this.atCmdName = atCmdName;
	}
	public byte getCmdParam() {
		return cmdParam;
	}
	public void setCmdParam(byte cmdParam) {
		this.cmdParam = cmdParam;
	}
	
	public ByteBuf getPayload() {
		ByteBuf byteBuf = Unpooled.buffer(16);//16 Is default
		byteBuf.writeByte(frameType);
		byteBuf.writeByte(frameId);
		byteBuf.writeBytes(destAddress);
		byteBuf.writeBytes(destNetworkAddress);
		byteBuf.writeByte(remoteCmdOpt);
		byteBuf.writeByte(atCmdName[0]);
		byteBuf.writeByte(atCmdName[1]);
		byteBuf.writeByte(cmdParam);
		return byteBuf;
	}
	
	public static byte getCheckSum(byte[] bts) {
		short sum = 0;
		for (byte b : bts) {
			sum += b;
		}
		return (byte) (0xFF - (sum & 0xFF));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XBeeCommandRequest");
		builder.append(" payload: ");
		builder.append(Hex.encodeHexString(getPayload().array()));
		builder.append(" [length=");
		builder.append(length);
		builder.append(", frameType=");
		builder.append(String.format("%02x",frameType));
		builder.append(", frameId=");
		builder.append(String.format("%02x",frameId));
		builder.append(", destAddress=");
		builder.append(String.format("%02x",destAddress));
		builder.append(", destNetworkAddress=");
		builder.append(String.format("%02x",destNetworkAddress));
		builder.append(", remoteCmdOpt=");
		builder.append(String.format("%02x",remoteCmdOpt));
		builder.append(", atCmdName=");
		builder.append(Arrays.toString(atCmdName));
		builder.append(", cmdParam=");
		builder.append(String.format("%02x",cmdParam));
		builder.append("]");
		return builder.toString();
	}
}
