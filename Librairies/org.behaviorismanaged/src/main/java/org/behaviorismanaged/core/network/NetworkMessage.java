package org.behaviorismanaged.core.network;

public abstract class NetworkMessage implements Serializable, Deserializable {
	public abstract long getProtocolId();
}
