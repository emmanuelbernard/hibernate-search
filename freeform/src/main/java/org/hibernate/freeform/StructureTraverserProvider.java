/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform;

/**
 * Returns a {@link StructureTraverser} for a given payload.
 * An implementation is specific to a given payload type (JSON, Protobuf etc)
 *
 * {@code P} Payload type
 *
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public interface StructureTraverserProvider<P> {

	/**
	 * Return a traverser provider for the given payload.
	 */
	StructureTraverser<P> provideTraverser(P payload);
}
