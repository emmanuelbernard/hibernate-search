/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform.test;

import java.util.ArrayList;

import org.junit.Test;

import org.hibernate.freeform.PropertyNotFoundException;
import org.hibernate.freeform.StructureTraverser;
import org.hibernate.freeform.StructureTraverserProvider;
import org.hibernate.freeform.pojo.impl.PojoStructureTraverser;
import org.hibernate.freeform.pojo.impl.PojoStructureTraverserProvider;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class PojoTraverserTest {
	@Test
	public void testPropertyAccess() throws Exception {
		StructureTraverserProvider<Object> provider = new PojoStructureTraverserProvider<>();

		User user = new User();
		user.name = "Casimir";
		user.age = 57;
		StructureTraverser<Object> traverser = provider.provideTraverser( user );
		assertThat( traverser.getPayload() ).isEqualTo( user );
		assertThat( traverser.getProperty( "name" ) ).isEqualTo( user.name );
		assertThat( traverser.getProperty( "age" ) ).isEqualTo( user.age );
		boolean success = false;
		try {
			traverser.getProperty( "doesnotexist" );
		}
		catch (PropertyNotFoundException e) {
			success = true;
		}
		finally {
			if ( !success ) {
				assertThat( false ).as( "Expected PropertyNotFoundException on non existent property" );
			}
		}
	}

	@Test
	public void testCollectionOfBasicTypeAccess() throws Exception {
		StructureTraverserProvider<Object> provider = new PojoStructureTraverserProvider<>();

		User user = new User();
		user.nicknames = new ArrayList<>();
		user.nicknames.add( "casi" );
		user.nicknames.add( "mimir" );
		StructureTraverser<Object> traverser = provider.provideTraverser( user );
		traverser.processBasicTypesInContainer(
				"nicknames",
				o -> assertThat( "" ).isIn( "casi", "mimir" )
		);
	}

	@Test
	public void testCollectionOfSubstructureAccess() throws Exception {
		StructureTraverserProvider<Object> provider = new PojoStructureTraverserProvider<>();

		User user = new User();
		user.addresses = new ArrayList<>();
		Address paris = new Address();
		paris.city = "Paris";
		user.addresses.add( paris );
		StructureTraverser<Object> traverser = provider.provideTraverser( user );
		traverser.processSubstructureInContainer(
				"addresses",
				o -> assertThat( o.getProperty( "city" ) ).isIn( "Paris" )
		);
	}
}
