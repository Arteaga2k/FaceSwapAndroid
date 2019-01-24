package com.vision.faceswap;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

public class FileSender {
    private static final String URL = "https://face-swap-server.herokuapp.com/swap";

    public File send(File source, File destination) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>() {{
            add("src", source);
            add("dst", destination);
        }};
        HttpHeaders headers = new HttpHeaders() {{
            setContentType(MediaType.MULTIPART_FORM_DATA);
        }};

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate
                .postForObject(URL, requestEntity, byte[].class);
        final File outputFile = new File(Resources.getResource("result.jpg").getFile());

        Files.write(response, outputFile);

        return outputFile;
    }

}
