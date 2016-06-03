package com.br.joaolima.testrest.basetest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class BaseTestHTTP {

	protected int getStatusCodeHttpGET(String url) {
		HttpUriRequest request = new HttpGet(createURI(url));
		HttpResponse httpResponse = null;
		try {
			httpResponse = HttpClientBuilder.create().build().execute(request);
		} catch (IOException ioe) {
			Assert.fail("Falha na requisição HTTP.\n" + ioe.getMessage());
		}
		return httpResponse.getStatusLine().getStatusCode();
	}

	protected List<JsonObject> getRetornoComoListaHttpGET(String url) {
		HttpUriRequest request = new HttpGet(createURI(url));
		String result = getHttpResponseAsString(request);
		Gson gson = new Gson();
		List<JsonObject> elements = gson.fromJson(result, new TypeToken<List<JsonElement>>() {
		}.getType());
		return elements;
	}

	protected JsonObject getRetornoHttpGET(String url) {
		HttpUriRequest request = new HttpGet(createURI(url));
		String result = getHttpResponseAsString(request);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(result, JsonElement.class);
		return element.getAsJsonObject();
	}

	protected int getStatusCodeHttpPOST(String url, StringEntity parameters) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setEntity(parameters);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (IOException e) {
			Assert.fail("Falha na requisição post.\n"+e.getMessage());
		}
		return response.getStatusLine().getStatusCode();
	}

	protected JsonObject getRetornoHttpPOST(String url, StringEntity parameters) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setEntity(parameters);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (IOException e) {
			Assert.fail("Falha na requisição post.\n"+e.getMessage());
		}
		String result = null;
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException pe) {
			Assert.fail("Falha ao realizar parse do retorno da requisição HTTP.\n" + pe.getMessage());
		}
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(result, JsonElement.class);
		return element.getAsJsonObject();
	}

	private String getHttpResponseAsString(HttpUriRequest request) {
		HttpResponse httpResponse = null;
		try {
			httpResponse = HttpClientBuilder.create().build().execute(request);
		} catch (IOException ioe) {
			Assert.fail("Falha na requisição HTTP.\n" + ioe.getMessage());
		}
		String result = null;
		try {
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (ParseException | IOException pe) {
			Assert.fail("Falha ao realizar parse do retorno da requisição HTTP.\n" + pe.getMessage());
		}
		return result;
	}

	private URI createURI(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException urie) {
			Assert.fail("URL inválida: " + url + ".\n" + urie.getMessage());
		}
		return uri;
	}
}
