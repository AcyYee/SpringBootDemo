package com.example.demo.common;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Update by acy on 2017/8/3.
 */

public class FileUp {

	//图片类型文件
	public static final int IMAGE = 1;
	//音频类型文件
	public static final int AUDIO = 2;
	//视频类型文件
	public static final int VIDEO = 3;
	//文档类型文件
	public static final int DOCUMENT = 4;
	//其他类型文件
	public static final int OTHER = 5;

	/**
	 * 实现SpringMVC中文件的上传的功能
	 * @param files 上传的文件,类型:MultipartFile
	 * @param dirNames 文件夹名,多层文件夹名用,号隔开例如:",1,2"
	 * @param type 类型 图片为1,音频为2,视频为3,文档为4,其他为5 可使用类内的静态变量
	 * @param isNewName 是否使用新文件名 boolean 是为true
	 * @return 返回(文件夹名和文件名)或者空 例如:"/1/2/3.jpg"
	 */
	public static String[] UpFiles(MultipartFile[] files, HttpServletRequest request, String dirNames, int type, boolean isNewName)
	{
		String[] resultString = new String[files.length];
		for (int i=0;i<files.length;i++) {
			MultipartFile tempsFile = files[i];
			resultString[i]=UpFile(tempsFile,request,dirNames,type,isNewName);
		}
		return resultString;
	}

	public static String UpFile(MultipartFile file, HttpServletRequest request, String dirName, int type, boolean isNewName)
	{
		String newName;
		String[] newDirs;
		if (file==null|| file.isEmpty()) {
			request.getSession().setAttribute("error", "文件为空");
			System.out.print("文件为空");
			return "";
		}
		int index = file.getOriginalFilename().lastIndexOf(".");
		String postfix = file.getOriginalFilename().substring(index);

		if (!isTrueType(type, postfix)) {
			request.getSession().setAttribute("error", "文件格式不正确");
			System.out.print("文件格式不正确");
			return "";
		}
		if (isNewName) {
			newName = newFileName(type, postfix);
		} else {
			newName = file.getOriginalFilename();
		}
		String path = request.getSession().getServletContext().getRealPath("/");
		newDirs = dirName.split(",");
		StringBuilder dirNames = new StringBuilder("");
		if (newDirs.length > 1) {
			for (String newDir : newDirs) {
				dirNames .append("/");
				dirNames .append(newDir);
			}
		} else {
			dirNames.append("/");
			dirNames .append(dirName);
		}
		StringBuilder builder = new StringBuilder(path); 
		builder.append(dirNames);
		File temp = new File(builder.toString().trim());
		File temp2 = new File(builder.toString(), newName.trim());
		if (!temp.exists()) {
			if (!temp.mkdirs()){
				request.getSession().setAttribute("error", "无法创建文件夹");
			}
		}
		try {
			file.transferTo(temp2);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return dirNames.toString() + "/" + newName;
	}

	private static boolean isTrueType(int type,String postfix){
		if(type == IMAGE){
			if(!postfix.equals(".jpg")&&!postfix.equals(".jpeg")&&!postfix.equals(".png")&&!postfix.equals(".ico")&&!postfix.equals(".gif"))
			{
				return false;
			}
		}else if(type == AUDIO){
			if(!postfix.equals(".mp3")&&!postfix.equals(".silk")&&!postfix.equals(".wav"))
			{
				return false;
			}
		}else if(type ==VIDEO){
			if(!postfix.equals(".mp4")&&!postfix.equals(".flv")&&!postfix.equals(".avi"))
			{
				return false;
			}
		}else if(type ==DOCUMENT){
			if(!postfix.equals(".doc")&&!postfix.equals(".ppt")&&!postfix.equals(".xls")&&!postfix.equals(".docx")&&!postfix.equals(".pptx")&&!postfix.equals(".xlsx")&&!postfix.equals(".txt")&&!postfix.equals(".pdf"))
			{
				return false;
			}
		}else{
			return true;
		}
		return true;
	}

	public static String makeNewDir(HttpServletRequest request,String dirName){
		String path = request.getSession().getServletContext().getRealPath("/");
		StringBuilder dirNames = new StringBuilder();
		String[] newDirs = dirName.split(",");
		if (newDirs.length > 1) {
			dirNames.append("");
			for (String newDir : newDirs) {
				dirNames.append( "/");
				dirNames.append(newDir);
			}
		}else{
			dirNames.append( "/");
			dirNames.append(dirName);
		}
		path += dirNames.toString();
		System.out.println(path);
		File temp = new File(path.trim());
		if (!temp.exists()) {
			if (!temp.mkdir()){
				request.getSession().setAttribute("error", "无法创建文件夹");
			}
		}
		return dirNames.toString();
	}

	private static String newFileName(int type,String postfix){
		if(type == IMAGE){
			return "image"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+postfix;
		}else if(type == AUDIO){
			return "audio"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+postfix;
		}else if(type ==VIDEO){
			return "video"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+postfix;
		}else if(type ==DOCUMENT){
			return "document"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+postfix;
		}else {
			return "file" + new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + postfix;
		}
	}

	public static void deleteFolder(String deletePath,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		String dirNames = "";
		String[] newDirs = deletePath.split(",");
		if (newDirs.length > 1) {
			dirNames = "";
			for (int j = newDirs.length-1; j >= 0; j--)
				dirNames += "/" + newDirs[j];
		}
		path += dirNames;
		System.out.println(path);
		File temp = new File(path.trim());
		temp.delete();
	}

    public static String[] rename(String path,int type, String oldName,String newName,HttpServletRequest request) {
		String[] temp = new String[2];
		String dirNames= "";
		String[] newDirs = path.split(",");
		if (newDirs.length > 1) {
			dirNames = "";
			for (int j = newDirs.length-1; j >= 0; j--)
				dirNames += "/" + newDirs[j];
		}
		if(type == 1){
			String realPath = request.getServletContext().getRealPath("/") + dirNames;
			File file1 = new File(realPath +"/"+ oldName);
			//将原文件夹更改为A，其中路径是必要的。注意
			file1.renameTo(new File(realPath +"/"+ newName));
			temp[0] = file1.getName();
			temp[1] = dirNames+"/"+ newName;
		}else{
			String realPath = request.getServletContext().getRealPath("/") + dirNames;
			if(newName.contains(".")){
				File file1 = new File(realPath +"/"+ oldName);
				//将原文件夹更改为A，其中路径是必要的。注意
				file1.renameTo(new File(realPath +"/"+ newName));
				temp[0] = newName;
				temp[1] = dirNames+"/"+ newName;
			}else{
				int index = oldName.lastIndexOf(".");
				String postfix = oldName.substring(index);
				File file1 = new File(realPath +"/"+ oldName);
				//将原文件夹更改为A，其中路径是必要的。注意
				file1.renameTo(new File(realPath +"/"+ newName+postfix));
				temp[0] = newName+postfix;
				temp[1] = dirNames+"/"+ newName+postfix;
			}
		}
		return temp;
    }
}