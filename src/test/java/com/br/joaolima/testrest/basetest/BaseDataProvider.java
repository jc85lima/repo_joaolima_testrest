package com.br.joaolima.testrest.basetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;

import com.google.gson.Gson;

public class BaseDataProvider {

	public <T> T getDataProvider(String pathDataProvider, Class<T> classDataProvider) {
		BufferedReader reader = null;
		try {
			File file = new File(ClassLoader.getSystemResource(pathDataProvider).getFile());
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			Assert.fail("Falha na conversão do DataProvider.\nPath:" + pathDataProvider + "\nClasse:"
					+ classDataProvider + ".\n" + e.getMessage());
		}
		Gson gson = new Gson();
		return gson.fromJson(reader, classDataProvider);
	}
}