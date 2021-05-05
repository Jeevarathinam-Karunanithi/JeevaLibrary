package com.example.appengine.java8;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.PrintWriter;
import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import java.nio.file.Files;
import java.nio.*;
import java.nio.file.*;
import com.google.cloud.storage.Bucket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.commons.io.IOUtils;
import com.google.appengine.api.datastore.*; 

// [START gae_flex_storage_app]
@SuppressWarnings("serial")
@WebServlet(name = "UploadPicture", value = "uploadPicture")
//@Controller
@MultipartConfig()
public class UploadPicture extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		HttpSession session = req.getSession(false);
		String imageName =(String)session.getAttribute("sessiontAtr");
			
		PrintWriter out = resp.getWriter();
		Storage storage = getGCSService();
		BlobId blobId = BlobId.of(bucketName,imageName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();;
		final Part filePart = req.getPart("file");
		// resp.getWriter().println(filePart);
		InputStream inp = filePart.getInputStream();
		byte[] byt = IOUtils.toByteArray(inp);
		storage.create(blobInfo, byt);
		storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		String url  = "https://storage.googleapis.com/jeevatraining12.appspot.com/"+ imageName;
        addUrlToDatastore(imageName,url);
		
	}
	
	public void addUrlToDatastore(String urlKey,String url) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity ety = new Entity("profileLink");
		ety.setProperty("urlKey", urlKey); //urlKey is the userName which is a unique identifier for  user's profile Picture link 
		ety.setProperty("url",url);
		datastore.put(ety);
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