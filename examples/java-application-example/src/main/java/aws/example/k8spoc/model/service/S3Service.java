package aws.example.k8spoc.model.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import aws.example.k8spoc.model.bean.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

@Service
public class S3Service {
    
    public S3Service() {
    }

    public ResponseEntity<?> listarObjetos(S3 bucket) {
        
        System.out.format("Objects in S3 bucket %s:\n", bucket.getBucketName());
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(bucket.getBucketRegion()).build();
        
        ListObjectsV2Result result = s3.listObjectsV2(bucket.getBucketName());
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        
        
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
        
        return new ResponseEntity<Object>(objects, HttpStatus.OK);
    }
    
     
}