package com.example.appengine.java8;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;

import java.io.OutputStream;
import java.nio.file.Paths;

@SuppressWarnings("serial")
@WebServlet(name = "DownloadPicture", value = "downloadPicture")
public class DownloadPicture extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String objectName = "photo";
		String destFilePath = "E:\\FilesToCloud\\Image\new.png";
		 Storage storage = getGCSService();

		 Blob blob = storage.get(BlobId.of(bucketName, objectName));
		 blob.downloadTo(Paths.get(destFilePath));
		 OutputStream outStream;
	//	 blob.downloadTo(outStream);
		// storage.create(blob, null)
		
		   
		
	}
	public static final String bucketName = "jeevatraining12.appspot.com";

	public static Storage getGCSService() {
		ArrayList<String> scopes = new ArrayList<>();

		scopes.add("https://www.googleapis.com/auth/devstorage.read_write");

		final AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();
		final AppIdentityService.GetAccessTokenResult identityAccessToken = appIdentity.getAccessToken(scopes);
		String accessTokenStr = identityAccessToken.getAccessToken();
		AccessToken accessToken = new AccessToken(accessTokenStr, identityAccessToken.getExpirationTime());

		GoogleCredentials credentials = AppEngineCredentials.newBuilder().setAccessToken(accessToken).build();

		return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	}
	

}
