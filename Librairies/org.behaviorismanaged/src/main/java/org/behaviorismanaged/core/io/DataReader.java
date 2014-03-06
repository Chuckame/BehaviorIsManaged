package org.behaviorismanaged.core.io;

import java.math.BigInteger;

public abstract class DataReader {
	public abstract byte[] readBytes(int n);

	public abstract byte readInt8();
	public abstract short readUInt8();
	public abstract short readInt16();
	public abstract int readUInt16();
	public abstract int readInt32();
	public abstract long readUInt32();
	public abstract long readInt64();
	public abstract BigInteger readUInt64();

	public boolean readBoolean() {
		return readInt8() == 1;
	}

	public float readFloat() {
		return Float.intBitsToFloat(readInt32());
	}

	public double readDouble() {
		return Double.longBitsToDouble(readInt64());
	}

	public char readChar() {
		return (char) readUInt16();
	}

	public String readUTF(int len) {
		char[] chars = new char[len];

		for (int i = 0; i < len; i++) {
			chars[i] = readChar();
		}

		return new String(chars);
	}

	public String readUTF() {
		return readUTF(readUInt16());
	}
}
