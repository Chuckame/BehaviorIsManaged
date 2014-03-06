package org.behaviorismanaged.core.network;

import java.util.Optional;

public interface NetworkMessageFactory {
	Optional<NetworkMessage> build(long protocolId);
}
