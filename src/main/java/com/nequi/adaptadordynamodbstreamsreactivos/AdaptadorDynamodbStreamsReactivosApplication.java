package com.nequi.adaptadordynamodbstreamsreactivos;

import com.nequi.adaptadordynamodbstreamsreactivos.domain.Template;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class AdaptadorDynamodbStreamsReactivosApplication implements CommandLineRunner {

    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;

    public AdaptadorDynamodbStreamsReactivosApplication(DynamoDbAsyncClient asyncClient, DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        this.asyncClient = asyncClient;
        this.enhancedAsyncClient = enhancedAsyncClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdaptadorDynamodbStreamsReactivosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
        listCompletableFuture.thenAccept(tables -> {
            if (null != tables && !tables.contains(Template.class.getSimpleName())) {
                DynamoDbAsyncTable<Template> template = enhancedAsyncClient.table(Template.class.getSimpleName(), TableSchema.fromBean(Template.class));
                template.createTable();
            }
        });
    }


}

