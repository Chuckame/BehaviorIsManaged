package org.behaviorismanaged.core.network;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import static com.google.common.base.Throwables.propagate;

public class BaseProtocolTypeManager implements NetworkTypeFactory {
	private final Map<Short, Supplier<? extends NetworkType>> suppliers = new HashMap<>();

	@Override
	public Optional<NetworkType> build(short protocolId) {
		return Optional.ofNullable(suppliers.get(protocolId))
				.map(Supplier::get);
	}

	public Set<Short> getRegisteredTypes() {
		return ImmutableSet.copyOf(suppliers.keySet());
	}

	protected void register(short protocolId, Supplier<? extends NetworkType> supplier) {
		if (suppliers.containsKey(protocolId)) {
			throw new IllegalArgumentException(String.format(
					"NetworkType(%d) already exists", protocolId));
		}

		suppliers.put(protocolId, supplier);
	}

	protected void register(short protocolId, Class<? extends NetworkType> klass) {
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
