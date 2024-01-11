package com.adobe.testing.s3mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@MockBean(classes = { KmsKeyStore.class, ObjectController.class, BucketController.class,
        MultipartController.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewTestFile {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testNewFunctionality() {
        var headers = new HttpHeaders();
        headers.setAccept(List.of(APPLICATION_JSON));
        var response = restTemplate.exchange("/favicon.ico", HttpMethod.GET, new HttpEntity<>(headers),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
