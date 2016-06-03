package com.br.joaolima.testrest.test;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.joaolima.testrest.basetest.BaseDataProvider;
import com.br.joaolima.testrest.basetest.BaseTestHTTP;
import com.br.joaolima.testrest.test.dataprovider.LoginDataProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CT04_ValidarRetornoPOSTLoginComFalhaTest extends BaseTestHTTP {

	private static final String PATH_DATA = "CT04_ValidarRetornoPOSTLoginComFalhaTest.json";
	private LoginDataProvider data;

	@Before
	public void carregarDataProvider() {
		data = new BaseDataProvider().getDataProvider(PATH_DATA, LoginDataProvider.class);
	}

	@Test
	public void validarStatusCode() {
		String url = data.getUrl();
		StringEntity parametros = criarJsonComParametrosPost();
		int statusEncontrado = getStatusCodeHttpPOST(url, parametros);
		int statusEsperado = data.getStatusCode();
		Assert.assertEquals("Validar status code da requisição: " + url, statusEsperado, statusEncontrado);
	}

	@Test
	public void validarMensagemDoRetorno() {
		String url = data.getUrl();
		StringEntity parametros = criarJsonComParametrosPost();
		String mensagemEncontrada = getRetornoHttpPOST(url, parametros).get("status").getAsString();
		String mensagemEsperada = data.getMensagem();
		Assert.assertEquals("Validar mensagem de retorno da requisição.", mensagemEsperada, mensagemEncontrada);
	}

	private StringEntity criarJsonComParametrosPost() {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		json.addProperty("user", data.getUser());
		json.addProperty("password", data.getPassword());
		StringEntity parametros = null;
		try {
			parametros = new StringEntity(gson.toJson(json));
		} catch (UnsupportedEncodingException e) {
			Assert.fail("Falha ao criar o Json com parametros Post.\n" + e.getMessage());
		}
		return parametros;
	}
}
