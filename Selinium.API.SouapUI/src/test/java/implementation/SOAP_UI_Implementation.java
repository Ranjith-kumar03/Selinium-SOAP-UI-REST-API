package implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SOAP_UI_Implementation {
	
	
	@Given("^Perofrm SOAP UI API Testing$")
	public void perofrm_SOAP_UI_API_Testing() throws Throwable {
		String endpoint ="http://www.thomas-bayer.com/axis2/services/BLZService";
		
		File requestfile= new File(System.getProperty("user.dir")+"//Request//Request.xml");
		HttpClient client= HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestfile)));
		post.setHeader("content-type","test/xml");
		HttpResponse response= client.execute(post);
		System.out.println("Response from Site is ---"+ response.getStatusLine().getStatusCode());
	   BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		StringBuffer sb= new StringBuffer();
		while ((line=br.readLine()) !=null)
		{
			sb.append(line);
		}
		
		PrintWriter pw= new PrintWriter (System.getProperty("user.dir")+ "//Response//Response.xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	}
	
	
	@Given("^Perform REST API Testing$")
	public void perform_REST_API_Testing() throws Throwable {
		String endpoint ="https://jsonplaceholder.typicode.com/todos/1";
		HttpClient client= HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(endpoint);
	    HttpResponse response= client.execute(request);
	    System.out.println("Response from Site is ---"+ response.getStatusLine().getStatusCode());
	    BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    
	    String line="";
	    StringBuffer sb= new StringBuffer();
	    while((line=br.readLine())!=null)
	    {
	    sb.append(line);
	    }
	    PrintWriter pw= new PrintWriter (System.getProperty("user.dir")+ "//REST_Response//Response.json");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	    
	}

	

}
