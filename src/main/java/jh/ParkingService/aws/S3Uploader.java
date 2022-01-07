package jh.ParkingService.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile multipartFile, String dirName, String reviewerUid) {
        String fileUrl = null;
        String fileName = createFileName(dirName, reviewerUid);

        //지정한 AWS S3 폴더에 있는 파일 초기화
        deleteFile(fileName);

        //MultipartFile 로 넘어온 파일을 S3에 업로드하고 fileUrl에 추가하여 리턴
        //MultipartFile 을 따로 File로 만드는 것이 아닌 InputStream 을 받는 방식
        //Stream 을 받기 때문에 데이터가 Byte형태로 받아와지기 때문에 objectMetadata 를 추가해 데이터에 대한 추가정보를 제공해야함
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));    //외부에 공개할 이미지이므로, 해당 파일에 public read 권한을 추가합니다.
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }
        fileUrl = amazonS3Client.getUrl(bucket, fileName).toString();

        return fileUrl;
    }


    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String dirName, String reviewerUid) {
        String fileName = dirName + "/" + reviewerUid + ".jpg";

        return fileName;
    }
}