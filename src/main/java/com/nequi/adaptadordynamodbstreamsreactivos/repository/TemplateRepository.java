package com.nequi.adaptadordynamodbstreamsreactivos.repository;

import com.nequi.adaptadordynamodbstreamsreactivos.domain.Template;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Service
public class TemplateRepository {

    private final DynamoDbAsyncTable<Template> templateDynamoDbAsyncTable;

    public TemplateRepository(DynamoDbAsyncTable<Template> templateDynamoDbAsyncTable) {
        this.templateDynamoDbAsyncTable = templateDynamoDbAsyncTable;
    }

    // create
    public CompletableFuture<Void> save(Template template) {
        return templateDynamoDbAsyncTable.putItem(template);
    }

    // read
    public CompletableFuture<Template> getTemplateByID(String templateId) {
        return templateDynamoDbAsyncTable.getItem(getKeyBuild(templateId));
    }

    // update
    public CompletableFuture<Template> update(Template template) {
        return templateDynamoDbAsyncTable.updateItem(template);
    }

    // delete
    public CompletableFuture<Template> deleteById(String templateId) {
        return templateDynamoDbAsyncTable.deleteItem(getKeyBuild(templateId));
    }

    // get_all_item
    public PagePublisher<Template> getAll() {
        return templateDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String templateId) {
        return Key.builder()
                .partitionValue(templateId)
                .build();
    }


}
