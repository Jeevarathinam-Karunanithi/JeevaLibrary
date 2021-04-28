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
// [START gae_flex_storage_app]
@SuppressWarnings("serial")
@WebServlet(name = "UploadPicture", value = "uploadPicture")
@MultipartConfig()
public class UploadPicture extends HttpServlet {

  // private static final String BUCKET_NAME = System.getenv("BUCKET_NAME");
  private static final String BUCKET_NAME1 = "jeevatraining12.appspot.com";
  private static Storage storage = null;  

  @Override
  public void init() {
    storage = StorageOptions.getDefaultInstance().getService();
  }

  public static Storage getGCSService() {
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

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
      

        PrintWriter out = resp.getWriter();
        Storage storage = StorageOptions.newBuilder().setProjectId("jeevatraining12").build().getService();
        Page<Bucket> buckets = storage.list();
    
        for (Bucket bucket : buckets.iterateAll()) {
             out.println(bucket.getName());
             }
        
    // Storage storage = StorageOptions.newBuilder().setProjectId("jeevatraining12").build().getService();
    // BlobId blobId = BlobId.of("jeevatraining12.appspot.com", "jeevaImage");
    // BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    // storage.create(blobInfo, Files.readAllBytes(Paths.get("E:\\Servlets\\adddatat.PNG")));

    // System.out.println("Storage  " + storage + " BlobId " + blobId + " blofb " + blobInfo);




    // PrintWriter out = resp.getWriter();
    // final Part filePart = req.getPart("file");
    // final String fileName = filePart.getSubmittedFileName();

    // // // Modify access list to allow all users with link to read file
    // // List<Acl> acls = new ArrayList<>();
    // // acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
    // // // the inputstream is closed by default, so we don't need to close it here
    // Blob blob =
    //     storage.create(
    //         BlobInfo.newBuilder(BUCKET_NAME, fileName).build());
    
    //         out.println(filePart + "  And  " + fileName + "Blob !!!" + storage + "  !!! " + BUCKET_NAME1 + "  Link   " + blob.getMediaLink()); 
    // // // return the public download link
    //  resp.getWriter().print(blob.getMediaLink());
  }
}
// [END gae_flex_storage_app]