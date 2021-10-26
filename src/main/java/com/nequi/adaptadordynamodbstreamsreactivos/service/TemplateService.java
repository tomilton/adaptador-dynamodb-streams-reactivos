package com.nequi.adaptadordynamodbstreamsreactivos.service;

import com.nequi.adaptadordynamodbstreamsreactivos.domain.Template;
import com.nequi.adaptadordynamodbstreamsreactivos.repository.TemplateRepository;

import com.nequi.adaptadordynamodbstreamsreactivos.util.Result;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.nequi.adaptadordynamodbstreamsreactivos.util.Result.FAIL;
import static com.nequi.adaptadordynamodbstreamsreactivos.util.Result.SUCCESS;


@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Mono<Result> createNewTemplate(Template template) {

        return Mono.fromFuture(templateRepository.save(template))
                .thenReturn(SUCCESS)
                .onErrorReturn(FAIL);
    }

    public Mono<Template> getTemplateById(String templateId) {
        return Mono.fromFuture(templateRepository.getTemplateByID(templateId))
                .doOnSuccess(Objects::requireNonNull)
                .onErrorReturn(new Template());
    }

    public Mono<Result> updateExistingTemplate(Template template) {
        return Mono.fromFuture(templateRepository.getTemplateByID(template.getTemplateID()))
                .doOnSuccess(Objects::requireNonNull)
                .doOnNext(__ -> templateRepository.updateTemplate(template))
                .doOnSuccess(Objects::requireNonNull)
                .thenReturn(SUCCESS)
                .onErrorReturn(FAIL);
    }

    public Mono<Result> updateExistingOrCreateTemplate(Template template) {
        return Mono.fromFuture(templateRepository.updateTemplate(template))
                .thenReturn(SUCCESS)
                .onErrorReturn(FAIL);
    }

    public Mono<Result> deleteTemplateById(String templateId) {
        return Mono.fromFuture(templateRepository.deleteTemplateById(templateId))
                .doOnSuccess(Objects::requireNonNull)
                .thenReturn(SUCCESS)
                .onErrorReturn(FAIL);
    }

    public Flux<Template> getAll() {
        return Flux.from(templateRepository.getAllTemplate()
                        .items())
                .onErrorReturn(new Template());
    }


}
