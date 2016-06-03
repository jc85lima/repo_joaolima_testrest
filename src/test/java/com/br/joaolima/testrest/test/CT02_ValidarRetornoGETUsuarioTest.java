package com.br.joaolima.testrest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.joaolima.testrest.basetest.BaseDataProvider;
import com.br.joaolima.testrest.basetest.BaseTestHTTP;
import com.br.joaolima.testrest.test.dataprovider.UsuarioDataProvider;

public class CT02_ValidarRetornoGETUsuarioTest extends BaseTestHTTP {

	private static final String PATH_DATA = "CT02_ValidarRetornoGETUsuarioTest.json";
	private UsuarioDataProvider data;
	
	@Before
	public void carregarDataProvider(){
		data = new BaseDataProvider().getDataProvider(PATH_DATA, UsuarioDataProvider.class);
	}
	
	@Test
	public void validarStatusCode() {
		String url = data.getUrl();
		int statusEncontrado = getStatusCodeHttpGET(url);
		int statusEsperado = data.getStatusCode();
		Assert.assertEquals("Validar status code da requisição: " + url, statusEsperado, statusEncontrado);
	}
	
	@Test
	public void validarValorFirstnameDoRetorno() {
		String url = data.getUrl();
		String primeiroNomeEncontrado = getRetornoHttpGET(url).get("firstname").getAsString();
		String primeiroNomeEsperado = data.getFirstname();
		Assert.assertEquals("Validar quantidade de registros retornados.", primeiroNomeEsperado, primeiroNomeEncontrado);
	}
}
