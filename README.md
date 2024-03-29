Bitrete Jackrabbit shutdown servlet for Apache Tomcat
=====================================================

Problem
-------
When you deploy Jackrabbit repository into Tomcat as [shared resource (deployment Model 2)](http://jackrabbit.apache.org/deployment-models.html#DeploymentModels-Model2%3ASharedJ2EEResource), and then try shutdown application server the repository remains locked. This may cause the following problems:

* It's unable to backup locked repository.
* Sometimes repository fails to start with RepositoryException and message "Repository is locked".

Suggested solution
------------------
We created a Tomcat shutdown event listener and correctly shutdown Jackrabbit repository.

Building
--------
Execute supplied ant script. It produces ready to deploy war in distr folder.

Configuration
-------------
Our listener has only one parameter 'repository-jndi-name'. You can specify there jackrabbit shared resource name.

Feel free to ask questions about this product info [at] bitrete.ru. ([www.bitrete.ru](http://www.bitrete.ru))
