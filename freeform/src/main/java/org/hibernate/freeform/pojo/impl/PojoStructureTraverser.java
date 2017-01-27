/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform.pojo.impl;

import java.util.Collection;
import java.util.function.Consumer;

import org.hibernate.freeform.AccessFailureException;
import org.hibernate.freeform.PropertyNotFoundException;
import org.hibernate.freeform.StructureTraverser;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class PojoStructureTraverser<P> implements StructureTraverser<P> {
	private final Class<?> pojoType;
	private final P payload;
	private final PojoStructureTraverserProvider<P> provider;

	public PojoStructureTraverser(P payload, PojoStructureTraverserProvider<P> provider) {
		this.payload = payload;
		this.pojoType = payload.getClass();
		this.provider = provider;
	}

	@Override
	public Object getProperty(String propertyName) throws PropertyNotFoundException {
		try {
			return pojoType.getField( propertyName ).get( payload );
		}
		catch (NoSuchFieldException e) {
			throw new PropertyNotFoundException( "Cannot find " + propertyName + " on " + pojoType, e );
		}
		catch (IllegalAccessException e) {
			throw new AccessFailureException( "Cannot access " + propertyName + " on " + pojoType, e );
		}
	}

	@Override
	public void processProperty(String propertyName, Consumer<Object> action) throws PropertyNotFoundException {
		Object object = getProperty( propertyName );
		action.accept( object );
	}

	@Override
	public void processBasicTypesInContainer(String propertyName, Consumer<Object> action) {
		Collection<?> container = (Collection<?>) getProperty( propertyName );
		for ( Object nested : container ) {
			action.accept( nested );
		}
	}

	@Override
	public void processSubstructureInContainer(String propertyName, Consumer<StructureTraverser<P>> action) {
		Collection<P> container = (Collection<P>) getProperty( propertyName );
		for ( P nested : container ) {
			action.accept( provider.provideTraverser( nested ) );
		}
	}
	@Override
	public P getPayload() {
		return payload;
	}
}
