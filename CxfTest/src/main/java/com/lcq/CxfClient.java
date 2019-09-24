package com.lcq;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class CxfClient {

	public static void main(String[] args) throws IOException {
		
		WebClient client = WebClient.create("http://localhost:9001/consumer");
		
		Response response = client.get();
		
		InputStream in = (InputStream) response.getEntity();
		
		String content = IOUtils.readStringFromStream(in);
		
		System.out.println(content);
	}
}
