package org.universaal.tools.packaging.tool.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UAPP {

	public void createUAPPfile(String sourcePath, String destinationPath) {

		try{
			File directoryToZip = new File(sourcePath);

			List<File> fileList = new ArrayList<File>();
			System.out.println("[Application Packager] - Getting references to all files in: " + directoryToZip.getCanonicalPath());
			getAllFiles(directoryToZip, fileList);
			System.out.println("[Application Packager] - Creating zip file");
			writeZipFile(destinationPath, directoryToZip, fileList);
			System.out.println("[Application Packager] - Done");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void getAllFiles(File dir, List<File> fileList) {

		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("[Application Packager] - directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					System.out.println("     [Application Packager] - file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeZipFile(String destinationPath, File directoryToZip, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(destinationPath);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addToZip(File directoryToZip, File file, ZipOutputStream zos) {

		try{
			FileInputStream fis = new FileInputStream(file);

			// we want the zipEntry's path to be a relative path that is relative
			// to the directory being zipped, so chop off the rest of the path
			String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1, file.getCanonicalPath().length());
			System.out.println("[Application Packager] - Writing '" + zipFilePath + "' to zip file");
			ZipEntry zipEntry = new ZipEntry(zipFilePath);
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}

			zos.closeEntry();
			fis.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}