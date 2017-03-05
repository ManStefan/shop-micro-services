====================================================================================================
SOLR server:
https://hub.docker.com/_/solr/

Add in the core you want the fields from the SolrProduct

Configure the application-dev/test/etc for the cores and servers you define in the search-service project

Example of creating a core using custom schema:
docker run -d -P -v //D/conf:/myconfig -p 8983:8983 solr solr-create -c mycore -d /myconfig

===========================DOCKER Setup
So that you can mount volumes in docker, you must share the drive/folder, in docker management console or in Virtual Box