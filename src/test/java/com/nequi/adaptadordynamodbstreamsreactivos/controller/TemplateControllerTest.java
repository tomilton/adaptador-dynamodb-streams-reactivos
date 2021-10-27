package com.nequi.adaptadordynamodbstreamsreactivos.controller;

import com.nequi.adaptadordynamodbstreamsreactivos.domain.Template;
import com.nequi.adaptadordynamodbstreamsreactivos.service.TemplateService;
import com.nequi.adaptadordynamodbstreamsreactivos.util.Result;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nequi.adaptadordynamodbstreamsreactivos.util.Result.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TemplateControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TemplateService templateService;

    @Value("${config.base.endpoint}")
    private String url;

    @Test
    public void save() {
        when(templateService.createNewTemplate(any())).thenReturn(Mono.just(SUCCESS));
        webTestClient.post()
                .uri(url + "/save")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(new Template())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Result.class)
                .consumeWith(re -> assertThat(SUCCESS).isEqualByComparingTo(re.getResponseBody()));
    }

    @Test
    public void getTemplate() {
        when(templateService.getTemplateById(any())).thenReturn(Mono.just(new Template()));
        webTestClient.get()
                .uri(url + "/get/111")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Template.class)
                .consumeWith(Assert::assertNotNull);
    }

    @Test
    public void updateOrCreate() {
        when(templateService.updateExistingOrCreateTemplate(any())).thenReturn(Mono.just(SUCCESS));
        webTestClient.put()
                .uri(url + "/updateTemplateOrCreate")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(new Template())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Result.class)
                .consumeWith(re -> assertThat(SUCCESS).isEqualByComparingTo(re.getResponseBody()));
    }

    @Test
    public void delete() {
        when(templateService.deleteTemplateById(any())).thenReturn(Mono.just(SUCCESS));
        webTestClient.delete()
                .uri(url + "/delete/111")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Result.class)
                .consumeWith(re -> assertThat(SUCCESS).isEqualByComparingTo(re.getResponseBody()));
    }


    @Test
    public void update() {
        when(templateService.updateExistingTemplate(any())).thenReturn(Mono.just(SUCCESS));
        webTestClient.put()
                .uri(url + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(new Template())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Result.class)
                .consumeWith(re -> assertThat(SUCCESS).isEqualByComparingTo(re.getResponseBody()));
    }

    @Test
    public void getAll() {
        when(templateService.getAll()).thenReturn(Flux.just(new Template()));
        webTestClient.get()
                .uri(url + "/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Template.class)
                .consumeWith(Assert::assertNotNull);
    }


}