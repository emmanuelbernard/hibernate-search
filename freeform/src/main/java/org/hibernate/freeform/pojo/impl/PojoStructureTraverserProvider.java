/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform.pojo.impl;

import org.hibernate.freeform.StructureTraverser;
import org.hibernate.freeform.StructureTraverserProvider;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class PojoStructureTraverserProvider<P> implements StructureTraverserProvider<P> {
	@Override
	public StructureTraverser<P> provideTraverser(P payload) {
		return new PojoStructureTraverser<>( payload, this );
	}
}
