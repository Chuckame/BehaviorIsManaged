package org.behaviorismanaged.core.network;

public abstract class NetworkType implements Serializable, Deserializable {
	public abstract short getProtocolId();
}
