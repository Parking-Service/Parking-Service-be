package jh.ParkingService.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //Mutiplefile 전달받아 File로 전환하고 S3에 있는 dirName 디렉토리에 저장
    public List<String> upload(List<MultipartFile> multipartFiles, String dirName, String reviewerUid) throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            System.out.println("multipartFile = " + multipartFile);
        }

        List<String> ImageUrls = null;

        int cnt = 0;
        for (MultipartFile multipartFile : multipartFiles) {
            File uploadFile = convert(multipartFile)
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

            String value = upload(uploadFile, dirName, reviewerUid, cnt);


            System.out.println("value = " + value);
            ImageUrls.add(value);
            cnt++;
        }
        return ImageUrls;
}

    //전환된 File을 S3에 public 읽기 권한으로 S3에 put 하고 image URL 리턴
    private String upload(File uploadFile, String dirName, String reviewerUid, int cnt) {
        String count = String.valueOf(cnt);
        String fileName = dirName + "/" + reviewerUid + "-" + count + ".jpg";
        System.out.println("fileName = " + fileName);
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();

    }

    //local에 생성된 File 삭제
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            System.out.println("파일이 삭제되었습니다.");
        } else {
            System.out.println("파일이 삭제되지 못했습니다.");
        }
    }

    public void delete(String dirName) {
        try {
            amazonS3Client.deleteObject(bucket, dirName);
            System.out.println("삭제 성공");
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.out.println("삭제 실패");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}