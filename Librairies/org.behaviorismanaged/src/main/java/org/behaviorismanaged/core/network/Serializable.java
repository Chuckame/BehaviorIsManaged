package org.behaviorismanaged.core.network;

import org.behaviorismanaged.core.io.DataWriter;

public interface Serializable {
	void serialize(DataWriter writer);
}
