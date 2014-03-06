package org.behaviorismanaged.core.network;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import static com.google.common.base.Throwables.propagate;

public class BaseMessageReceiver implements NetworkMessageFactory {
	private final Map<Long, Supplier<? extends NetworkMessage>> suppliers = new HashMap<>();

	@Override
	public Optional<NetworkMessage> build(long protocolId) {
		return Optional.ofNullable(suppliers.get(protocolId))
				.map(Supplier::get);
	}

	public Set<Long> getRegisteredMessages() {
		return ImmutableSet.copyOf(suppliers.keySet());
	}

	protected void register(long protocolId, Supplier<? extends NetworkMessage> supplier) {
		if (suppliers.containsKey(protocolId)) {
			throw new IllegalArgumentException(String.format(
				"NetworkMessage(%d) already exists", protocolId));
		}

		suppliers.put(protocolId, supplier);
	}

	protected void register(short protocolId, Class<? extends NetworkMessage> klass) {
		try {
			klass.getConstructor().setAccessible(true);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(String.format(
					"%s must have a default constructor", klass.getName()));
		}

		register(protocolId, () -> {
			try {
				return klass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw propagate(e);
			}
		});
	}
}
