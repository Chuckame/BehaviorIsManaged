package org.behaviorismanaged.core.network;

import org.behaviorismanaged.core.io.DataReader;

public interface Deserializable {
	void deserialize(DataReader reader);
}
