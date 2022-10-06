
package org.pac4j.saml.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.pac4j.core.exception.TechnicalException;

import java.util.Properties;

/**
 * Factory returning a well configured {@link VelocityEngine} instance required for
 * generating an HTML form used to POST SAML messages.
 *
 * @author Michael Remond
 */
public class VelocityEngineFactory {

    public static VelocityEngine getEngine() {

        try {
            final var props = new Properties();
            props.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
            props.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");

            final var engine = new VelocityEngine();
            engine.init(props);
            return engine;
        } catch (final Exception e) {
            throw new TechnicalException("Error configuring velocity", e);
        }

    }

}
