package aws.example.k8spoc.model.bean;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class S3 {
    
    private String bucketName;
    private String bucketRegion;


    public S3() {
    }

    public S3(String bucketName, String bucketRegion) {
        this.bucketName = bucketName;
        this.bucketRegion = bucketRegion;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketRegion() {
        return this.bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    public S3 bucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public S3 bucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof S3)) {
            return false;
        }
        S3 s3 = (S3) o;
        return Objects.equals(bucketName, s3.bucketName) && Objects.equals(bucketRegion, s3.bucketRegion);
    }

    @Override
    public String toString() {
        return "{" +
            " bucketName='" + getBucketName() + "'" +
            ", bucketRegion='" + getBucketRegion() + "'" +
            "}";
    }

}