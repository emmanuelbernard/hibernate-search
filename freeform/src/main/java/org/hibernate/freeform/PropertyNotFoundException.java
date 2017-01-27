/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform;

/**
 * Thrown when a property is not found.
 *
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class PropertyNotFoundException extends RuntimeException {
	public PropertyNotFoundException(String message) {
		super( message );
	}

	public PropertyNotFoundException(String message, Throwable cause) {
		super( message, cause );
	}
}
