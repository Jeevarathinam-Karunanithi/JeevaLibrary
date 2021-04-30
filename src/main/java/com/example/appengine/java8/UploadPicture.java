package com.example.appengine.java8;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.PrintWriter;
import java.io.IOException;
import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.google.cloud.storage.StorageException;
import java.nio.file.NoSuchFileException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;  
import java.lang.String;

@SuppressWarnings("serial")
@Controller
@MultipartConfig()
public class UploadPicture extends HttpServlet {
@RequestMapping(value = "/uploadPicture")
@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException {
      
       final Part filePart = req.getPart("file");
       File f = new File("cat.png");
       String absolute = f.getAbsolutePath();
       
     
      PrintWriter out = resp.getWriter();
      out.println("   File part and   " + absolute );
        Storage storage = getGCSService();
        BlobId blobId = BlobId.of(bucketName, objectName);       
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        //storage.create(blobInfo, filePart);
        //I was not able to use this as storage.create(blobinfo , Part) is not allowed
        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
}
private static final String bucketName = "jeevatraining12.appspot.com";
private static final String objectName = "New_img";
private static final String filePath =  "E:\\FilesToCloud\\cat.png";
private static Storage storage = null; 

  public static Storage getGCSService()  throws IOException, ServletException{
    ArrayList<String> scopes = new ArrayList<>();

    scopes.add("https://www.googleapis.com/auth/devstorage.read_write");

    final AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();
    final AppIdentityService.GetAccessTokenResult identityAccessToken =
        appIdentity.getAccessToken(scopes);
    String accessTokenStr = identityAccessToken.getAccessToken();
    AccessToken accessToken =
        new AccessToken(accessTokenStr, identityAccessToken.getExpirationTime());

    GoogleCredentials credentials =
        AppEngineCredentials.newBuilder().setAccessToken(accessToken).build();

    return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
  }


}
// [END gae_flex_storage_app]