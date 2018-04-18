package com.example.fileupanddown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
@Path("/file")
public class FileResource {
//文件的下载	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("download")
	public Response downloadFile(@QueryParam("filePath")String filePath) {

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			String mt = new MimetypesFileTypeMap().getContentType(file);
			String fileName = file.getName();
			try {
				//处理get乱码
				fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return Response
					.ok(file, mt)
					.header("Content-disposition",
							"attachment;filename=" + fileName)
					.header("ragma", "No-cache")
					.header("Cache-Control", "no-cache").build();

		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("下载失败，未找到该文件").build();
		}
	}
//文件的上传
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(
			@FormDataParam("file") InputStream fis,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) throws IOException{
				String fileName = "target/"+contentDispositionHeader.getFileName();
				FileOutputStream fos = new FileOutputStream(fileName);
				IOUtils.copy(fis, fos);
				fos.close();
				return new String("已上传到：target/项目目录下！");
		
	}
}
