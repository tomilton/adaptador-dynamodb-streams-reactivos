package com.nequi.adaptadordynamodbstreamsreactivos.controller;

import com.nequi.adaptadordynamodbstreamsreactivos.domain.Template;
import com.nequi.adaptadordynamodbstreamsreactivos.service.TemplateService;
import com.nequi.adaptadordynamodbstreamsreactivos.util.Result;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/hola")
    public Mono<String> hola() {
        return Mono.just("Hola Milton");
    }

    @PostMapping("/save")
    public Mono<Result> save(@RequestBody Template template) {
        return templateService.createNewTemplate(template);
    }

    @GetMapping("/get/{templateId}")
    public Mono<Template> getTemplate(@PathVariable() String templateId) {
        return templateService.getTemplateById(templateId);
    }

    @PutMapping("/updateTemplateOrCreate")
    public Mono<Result> updateOrCreate(@RequestBody Template template) {
        return templateService.updateExistingOrCreateTemplate(template);
    }

    @DeleteMapping("/delete/{templateId}")
    public Mono<Result> delete(@PathVariable() String templateId) {
        return templateService.deleteTemplateById(templateId);
    }

    @PutMapping("/update")
    public Mono<Result> update(@RequestBody Template template) {
        return templateService.updateExistingTemplate(template);
    }

    @GetMapping("/all")
    public Flux<Template> getAll() {
        return templateService.getAll();
    }

}
