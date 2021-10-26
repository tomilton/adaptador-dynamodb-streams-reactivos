package com.nequi.adaptadordynamodbstreamsreactivos.domain;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Template {

    private String templateID;
    private TemplateOrigRQ templateOrigRQ;
    private TemplateDestRQ templateDestRQ;

    private TemplateOrigRS templateOrigRS;
    private TemplateDestRS templateDestRS;

    private String httpVerb;
    private String protocol;
    private String host;
    private String port;
    private String region;
    private String version;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("templateID")
    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    @DynamoDbAttribute("templateOrigRQ")
    public TemplateOrigRQ getTemplateOrigRQ() {
        return templateOrigRQ;
    }

    public void setTemplateOrigRQ(TemplateOrigRQ templateOrigRQ) {
        this.templateOrigRQ = templateOrigRQ;
    }

    @DynamoDbAttribute("templateDestRQ")
    public TemplateDestRQ getTemplateDestRQ() {
        return templateDestRQ;
    }

    public void setTemplateDestRQ(TemplateDestRQ templateDestRQ) {
        this.templateDestRQ = templateDestRQ;
    }

    @DynamoDbAttribute("templateOrigRS")
    public TemplateOrigRS getTemplateOrigRS() {
        return templateOrigRS;
    }

    public void setTemplateOrigRS(TemplateOrigRS templateOrigRS) {
        this.templateOrigRS = templateOrigRS;
    }


    @DynamoDbAttribute("templateDestRS")
    public TemplateDestRS getTemplateDestRS() {
        return templateDestRS;
    }

    public void setTemplateDestRS(TemplateDestRS templateDestRS) {
        this.templateDestRS = templateDestRS;
    }

    @DynamoDbAttribute("httpVerb")
    public String getHttpVerb() {
        return httpVerb;
    }

    public void setHttpVerb(String httpVerb) {
        this.httpVerb = httpVerb;
    }

    @DynamoDbAttribute("protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @DynamoDbAttribute("host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @DynamoDbAttribute("port")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @DynamoDbAttribute("region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @DynamoDbAttribute("version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}