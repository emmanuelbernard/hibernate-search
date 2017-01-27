/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 *
 */
package org.hibernate.freeform;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class AccessFailureException extends RuntimeException {
	public AccessFailureException(String message) {
		super( message );
	}

	public AccessFailureException(String message, Throwable cause) {
		super( message, cause );
	}
}
