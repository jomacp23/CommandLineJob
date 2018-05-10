package com.commandline.jobsauthentication;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jose.m.cruz.perez on 24/02/2017.
 */
public class JobAuthentication implements HttpRequestInterceptor{

    private static Logger LOGGER = LoggerFactory.getLogger(JobAuthentication.class);

    public void process(HttpRequest request, HttpContext context) throws HttpException {

        AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);

        if (authState.getAuthScheme() == null) {
            AuthScheme authScheme = (AuthScheme) context.getAttribute("preemptive-auth");
            CredentialsProvider credentialsProvider = (CredentialsProvider) context
                .getAttribute(ClientContext.CREDS_PROVIDER);
            HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            if (authScheme != null) {
                Credentials credentials = credentialsProvider
                    .getCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()));
                if (credentials == null) {
                    LOGGER.error("Credenciales para la autentificacion no proporcionadas");
                    throw new HttpException("Credenciales para la autentificacion no proporcionadas");
                }
                authState.setAuthScheme(authScheme);
                authState.setCredentials(credentials);
            }
        }
    }

}
