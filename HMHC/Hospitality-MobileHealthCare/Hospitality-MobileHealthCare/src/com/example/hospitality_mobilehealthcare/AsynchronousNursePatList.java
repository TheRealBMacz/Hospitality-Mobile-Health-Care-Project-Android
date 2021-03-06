package com.example.hospitality_mobilehealthcare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsynchronousNursePatList extends AsyncTask<Void, Integer, String> {
	ProgressDialog progressDialog;
	InputStream is;
	String url="http://192.168.43.93/MobileHealthCare/listview.php";
	String result;
	PatientlistActivity obj;
	
	public AsynchronousNursePatList(PatientlistActivity patientlistActivity) {
		// TODO Auto-generated constructor stub
		obj=patientlistActivity;
	}
	@Override
	protected String doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		DefaultHttpClient de=new DefaultHttpClient();
		HttpPost h=new HttpPost(url);
		try {
			HttpResponse httpResponse=de.execute(h);
			HttpEntity he=httpResponse.getEntity();
			is=he.getContent();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try{
BufferedReader br=
  new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb=new StringBuilder();
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			is.close();
			result=sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		progressDialog=new ProgressDialog(obj);
		progressDialog.setMessage("Loading...");
		progressDialog.setIndeterminate(false);
		progressDialog.setMax(10000);
		progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
		progressDialog.setProgress(0);
		progressDialog.show();
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		obj.showData(result);
		progressDialog.cancel();
	}

}
