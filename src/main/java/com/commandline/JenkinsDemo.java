package com.commandline;

import com.commandline.jobsconfiguration.JobConfiguration;
import com.commandline.jobsposter.JenkinsPoster;

import com.commandline.jobs.JobDemo;

/**
 * Hello world!
 *
 */
public class JenkinsDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("Inicia la ejecucion del Job");
        JobDemo jobDemo = JobConfiguration.configJob();

        // Se inicia el job
        JenkinsPoster jenkinsPoster = new JenkinsPoster(jobDemo);
        System.out.println("\"Iniciando la ejecucion de postJenkinsJob\"");
        jenkinsPoster.postJenkinsJob();
        System.out.println("Ejecucion finalizada de postJenkinsJob");
    }

}
