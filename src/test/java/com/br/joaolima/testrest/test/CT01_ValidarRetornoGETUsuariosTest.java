package com.br.joaolima.testrest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.joaolima.testrest.basetest.BaseDataProvider;
import com.br.joaolima.testrest.basetest.BaseTestHTTP;
import com.br.joaolima.testrest.test.dataprovider.UsuariosDataProvider;

public class CT01_ValidarRetornoGETUsuariosTest extends BaseTestHTTP {

	private static final String PATH_DATA = "CT01_ValidarRetornoGETUsuariosTest.json";
	private UsuariosDataProvider data;
	
	@Before
	public void carregarDataProvider(){
		data = new BaseDataProvider().getDataProvider(PATH_DATA, UsuariosDataProvider.class);
	}
	
	@Test
	public void validarStatusCode() {
		String url = data.getUrl();
		int statusEncontrado = getStatusCodeHttpGET(url);
		int statusEsperado = data.getStatusCode();
		Assert.assertEquals("Validar status code da requisição: " + url, statusEsperado, statusEncontrado);
	}
	
	@Test
	public void validarRetornoQuantidadeRegistros() {
		String url = data.getUrl();
		int quantidadeRegistrosEncontrado = getRetornoComoListaHttpGET(url).size();
		int quantidadeRegistroEsperado = data.getQuantidadeRegistros();
		Assert.assertEquals("Validar quantidade de registros retornados.", quantidadeRegistroEsperado, quantidadeRegistrosEncontrado);
	}
}
