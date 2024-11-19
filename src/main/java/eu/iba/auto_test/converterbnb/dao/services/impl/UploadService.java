package eu.iba.auto_test.converterbnb.dao.services.impl;

import eu.iba.auto_test.converterbnb.dao.model.Correspondent;
import eu.iba.auto_test.converterbnb.dao.model.Document;
import eu.iba.auto_test.converterbnb.dao.model.DocumentLink;
import eu.iba.auto_test.converterbnb.dao.model.NomenclatureAffair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UploadService {

    private final RestTemplate restTemplate;
    private final String host;
    private final String login;
    private final String password;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UploadService(RestTemplate restTemplate, @Value("${edms.host:https://edms.edo.iba}") String host, @Value("${edms.login:admin}") String login, @Value("${edms.login:admin}") String password) {
        this.restTemplate = restTemplate;
        this.host = host;
        this.login = login;
        this.password = password;
    }

    public void uploadCorrespondent(Correspondent correspondent){
        String url = host + "/api/migration/correspondent";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getToken());
        HttpEntity<Correspondent> entity = new HttpEntity<>(correspondent, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        logger.info("Process correspondent with id: " + correspondent.getId() + " status: " + response.getStatusCode()+" body "+response.getBody());
    }

    public void uploadNomenclatureAffair(NomenclatureAffair nomenclatureAffair){
        String url = host + "/api/migration/nomenclature-affair";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getToken());
        HttpEntity<NomenclatureAffair> entity = new HttpEntity<>(nomenclatureAffair, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        logger.info("Process nomenclature affair with id: " + nomenclatureAffair.getId() + " status: " + response.getStatusCode()+" body "+response.getBody());
    }

    public void uploadDocument(Document document){
        String url = host + "/api/migration/document";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getToken());
        HttpEntity<Document> entity = new HttpEntity<>(document, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        logger.info("Process document with id: " + document.getId() + " status: " + response.getStatusCode()+" body "+response.getBody());
    }


    public void uploadDocumentLink(DocumentLink documentLink){
        String url = host + "/api/migration/document-link";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getToken());
        HttpEntity<DocumentLink> entity = new HttpEntity<>(documentLink, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        logger.info("Process document link with id: " + documentLink.getId() + " status: " + response.getStatusCode()+" body "+response.getBody());
    }

    public String getBearerToken(){
        return "Bearer " + getToken();
    }

    public String getToken(){
        String auth = host + "/auth/basic";
        HttpHeaders JSON_HEADERS = new HttpHeaders();
        JSON_HEADERS.setContentType(MediaType.APPLICATION_JSON);
        JSON_HEADERS.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity body = new HttpEntity<>(new BasicAuthRequest().setLogin(login).setPassword(password),JSON_HEADERS);
        ResponseEntity<TokenResponse> entity = restTemplate.exchange(auth, HttpMethod.POST, body, TokenResponse.class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("error while auth on apim");
        }
        return entity.getBody().getToken();
    }

}
