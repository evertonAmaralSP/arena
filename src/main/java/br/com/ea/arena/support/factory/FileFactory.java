package br.com.ea.arena.support.factory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
@Scope(value = "singleton")
public class FileFactory {

	private static final String REGEX_FILE_HTML = "^.*\\.(html)$";
	
	@Value("${dir.tmp}")
	private String DIR_TMP;
	@Value("${file.extension}")
	private String FILE_EXTENSION;

	public String generatePathOfDirectoryTemplate(String pathProduction, String pathTemaplte) {
		if (StringUtils.isEmpty(pathProduction) || StringUtils.isEmpty(pathTemaplte))
			throw new IllegalArgumentException();
		return DIR_TMP + "/" + pathProduction + "/" + pathTemaplte;
	}

	public String generatePathOfDirectoryProduct(String pathProduction) {
		if (StringUtils.isEmpty(pathProduction))
			throw new IllegalArgumentException();
		return DIR_TMP + "/" + pathProduction;
	}


	public void createDiretorio(String path) throws IOException {
		FileUtils.forceMkdir(new File(path));
	}

	public File createHtmlFile(String path, String slug) {
		File file = new File(path + "/" + slug + FILE_EXTENSION);
		return file;
	}

	public void setDIR_TMP(String dIR_TMP) {
		DIR_TMP = dIR_TMP;
	}

	public void setFILE_EXTENSION(String fILE_EXTENSION) {
		FILE_EXTENSION = fILE_EXTENSION;
	}
	
	public List<String> listFiles(String path) {
		List<String> listaFile = new ArrayList<String>();
		File file = new File(path);
		File afile[] = file.listFiles();
		if(afile!=null) {
			for (File arquivo : afile) {
					listaFile.add(arquivo.getName());
      }
			return listaFile;
		}
		return null;
	}
	
	public boolean excluir(String path, String name) {
		File file = new File(path + "/" + name);
		return file.delete();
	}

	public boolean validateFileTypes(MultipartFile file) {
		String[] typeValid = {"text/html", "text/x-server-parsed-html", "application/x-javascript", "text/javascript", "image/jpeg", "image/gif", "image/png", "image/x-ico", "image/ico","image/vnd.microsoft.icon", "text/css",};
		for (String type : typeValid) {
	     if(type.equalsIgnoreCase(file.getContentType())) {
	    	 return true;
	     }
    }
	  return false;
  }
	public boolean validateFileJavascript(MultipartFile file) {
		String[] typeValid = {"application/x-javascript", "text/javascript"};
		for (String type : typeValid) {
	     if(type.equalsIgnoreCase(file.getContentType())) {
	    	 return true;
	     }
    }
	  return false;
  }
	


	public void salvarArquivoPathProduct(String path, MultipartFile file, String fileName) throws IOException, FileNotFoundException {
	  byte[] bytes = file.getBytes();
	  FileUtils.forceMkdir(new File(path));
	  BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(path + "/" + fileName )));
	  buffStream.write(bytes);
	  buffStream.close();
  }

}
