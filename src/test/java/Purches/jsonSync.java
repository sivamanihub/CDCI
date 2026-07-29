package Purches;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonSync {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ObjectMapper om= new ObjectMapper();// the class from Jackson librabry, it will read the json file also conver into java and new json
		JsonNode jn=om.readTree(new File("C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\StartUpJsonfiles.json")); 
		
		String oldPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\StartUpJsonfiles.json";
        String newPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\Sample_StartUpJson_Appu4Series_v5.1.5.json";
        String outputPath = "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches\\Updated_StartUpJson.json";


	}

}
