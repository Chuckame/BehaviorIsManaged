package org.behaviorismanaged.core.network;

import java.util.Optional;

public interface NetworkTypeFactory {
	Optional<NetworkType> build(short protocolId);
}
