package aws.example.k8spoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aws.example.k8spoc.model.bean.S3;
import aws.example.k8spoc.model.service.S3Service;

@RestController
@RequestMapping("/api")
public class S3Controller {
	
	@Autowired
	private S3Service s3Service;
	
	@GetMapping("/listarObjetos/")
	public ResponseEntity<?> listarObjetos() {

		String bucketName = System.getenv("BUCKET_NAME");
		String bucketRegion = System.getenv("REGION_NAME");

		S3 s3 = new S3(bucketName, bucketRegion);

		return s3Service.listarObjetos(s3);
	}

	@GetMapping("/health/")
	public ResponseEntity<?> healthCheck() {

		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}
}