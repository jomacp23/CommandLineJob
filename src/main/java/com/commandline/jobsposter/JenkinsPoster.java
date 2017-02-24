package com.commandline.jobsposter;

import com.commandline.jobs.JobDemo;
import com.commandline.jobsauthentication.JobAuthentication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Created by jose.m.cruz.perez on 24/02/2017.
 */
public class JenkinsPoster {

    private JobDemo jobDemo;

    public JenkinsPoster(JobDemo jobDemo) {
        this.jobDemo = jobDemo;
    }

    @SuppressWarnings("deprecation")
    public void postJenkinsJob() throws UnsupportedEncodingException {
        System.out.println("---------------------Jenkins Porter--------------------------");

        // Jenkins url
        String jenkinsUrl = String.format("%s:%s", jobDemo.getServer(), jobDemo.getPort());

        System.out.println("url = " + jenkinsUrl);

        // se crea el httpclient
        DefaultHttpClient client = new DefaultHttpClient();

        // se proveen las credenciales
        client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(jobDemo.getUsername(), jobDemo.getPassword()));

        // se genera el schema para el contexto de ejecucion
        BasicScheme basicAuth = new BasicScheme();
        BasicHttpContext context = new BasicHttpContext();
        context.setAttribute("preemptive-auth", basicAuth);

        // Se agrega el interceptor para verificar que el esquema existe, sino lo crea
        client.addRequestInterceptor(new JobAuthentication(), 0);

        // Postea el peticion para iniciar el build
        String buildUrl = jenkinsUrl + "/job/" + jobDemo.getJob() + "/build";

        HttpPost post = new HttpPost(buildUrl);
        post.setHeader("User-Agent", "Mozilla/5.0");

        try {
            // Execute your request with the given context
            HttpResponse response = client.execute(post, context);
            HttpEntity entity = response.getEntity();

            System.out.println("---------> " + EntityUtils.toString(entity));

            EntityUtils.consume(entity);
        } catch (IOException ioe) {
            System.out.println("Error al configurar informacion ----> " + ioe.getMessage());
        } finally {
            client.close();
        }
        System.out.println("----------------------------- Jenkins Porter--------------------------------");
    }
}