/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform;

import java.util.function.Consumer;

/**
 * Offers a property traversal API from a given payload
 *
 * {@code P} payload type.
 *
 * TODO: Discuss with Hibernate Search team the benefit of the traversal centric documentbuilder
 * vs the current metadata centric navigation.
 * 
 *
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public interface StructureTraverser<P> {

	/**
	 * Return the property value associated to {@code propertyName}.
	 * Throws a {@link PropertyNotFoundException} if the property does not exist.
	 *
	 * TODO: field vs getter?
	 */
	Object getProperty(String propertyName) throws PropertyNotFoundException;

	// alternative ot getProperty but with lambda in mind
	void processProperty(String propertyName, Consumer<Object> action) throws PropertyNotFoundException;

	/**
	 * Process containers of basic types
	 */
	//TODO what should be the consumer type ? StructureTraverser<?>, StructureTraverser<P>
	//     cannot be P for classes if P represents the actual class and not the generic notion of Java Class
	void processBasicTypesInContainer(String propertyName, Consumer<Object> action);

	//TODO what should be the consumer type ? StructureTraverser<?>, StructureTraverser<P>
	//     cannot be P for classes if P represents the actual class and not the generic notion of Java Class
	void processSubstructureInContainer(String propertyName, Consumer<StructureTraverser<P>> action);

	/**
	 * Return the underlying payload for the structure or substructure at bay.
	 * This method will likely not be useful.
	 * The only usage anticipated is for "class"-level operations like Hibernate Search ClassBridge.
	 *
	 * TODO: when we return the traverser for a nested structure, can we return the sub-structure as P?
	 */
	P getPayload();
}
