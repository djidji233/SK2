package service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractHttpService {

    private Gson gson;

    private String URL;

    private HttpClient httpClient;

    protected String token = "";

    public AbstractHttpService(String url) {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        this.URL = url;
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    protected <T> T get(String uri, Type responseType) {
        String result = get(uri);
        return gson.fromJson(result, responseType);
    }

    private String get(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .uri(URI.create(String.format("%s/%s", URL, uri)))
                .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T> T post(String uri, Object data, Class<T> responseType) {
        HttpRequest.BodyPublisher body = Optional.ofNullable(data)
                .map(value -> HttpRequest.BodyPublishers.ofString(gson.toJson(value))).orElse(HttpRequest.BodyPublishers.noBody());
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", token)
                .uri(URI.create(String.format("%s/%s", URL, uri)))
                .build();
        try {
            String response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return gson.fromJson(response, responseType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T> T delete(String uri, Class<T> responseType) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", token)
                .uri(URI.create(String.format("%s/%s", URL, uri)))
                .build();
        try {
            String response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return gson.fromJson(response, responseType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
