package org.pac4j.oidc.credentials;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import lombok.val;
import org.junit.Test;
import org.pac4j.core.util.TestsConstants;
import org.pac4j.core.util.serializer.JavaSerializer;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link OidcCredentials}.
 *
 * @author Jerome Leleu
 * @since 1.9.2
 */
public final class OidcCredentialsTests implements TestsConstants {

    private static final JavaSerializer serializer = new JavaSerializer();

    private static final String ID_TOKEN = """
eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJpc3MiOiJodHRwczovL2p3dC1pZHAuZXhhbX
BsZS5jb20iLCJzdWIiOiJtYWlsdG86cGVyc29uQGV4YW1wbGUuY29tIiwibmJmIjoxNDQwMTEyMDE1LCJleHAiOjE0NDAxMTU2
MTUsImlhdCI6MTQ0MDExMjAxNSwianRpIjoiaWQxMjM0NTYiLCJ0eXAiOiJodHRwczovL2V4YW1wbGUuY29tL3JlZ2lzdGVyIn0.""";

    @Test
    public void testSerialization() throws ParseException {
        val credentials = new OidcCredentials();
        credentials.setCode(new AuthorizationCode(VALUE));
        credentials.setAccessToken(new BearerAccessToken(VALUE, 0L, Scope.parse("oidc email")));
        credentials.setRefreshToken(new RefreshToken(VALUE));
        credentials.setIdToken(JWTParser.parse(ID_TOKEN));
        var result = serializer.serializeToBytes(credentials);
        val credentials2 = (OidcCredentials) serializer.deserializeFromBytes(result);
        assertEquals(credentials.getAccessToken(), credentials2.getAccessToken());
        assertEquals(credentials.getRefreshToken(), credentials2.getRefreshToken());
        assertEquals(credentials.getIdToken().getParsedString(), credentials2.getIdToken().getParsedString());
    }
}
