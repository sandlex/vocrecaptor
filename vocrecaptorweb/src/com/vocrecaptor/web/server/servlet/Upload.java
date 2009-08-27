package com.vocrecaptor.web.server.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.server.utils.DictionaryFileUtil;

public class Upload extends HttpServlet {

	private static final long serialVersionUID = 2120875096865259719L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = null;
		try {
			response.setContentType("text/plain");
			out = response.getWriter();

			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);

			DictionaryTransferObject dictionaryTO = new DictionaryTransferObject();
			dictionaryTO.setIsPublic(false);

			for (Iterator i = items.iterator(); i.hasNext();) {
				FileItem item = (FileItem) i.next();

				if (item.isFormField()) {

					if ("user".equals(item.getFieldName())) {
						dictionaryTO.setUser(new Long(item.getString()));
					}

					if ("learningLanguage".equals(item.getFieldName())) {
						dictionaryTO.setLearningLanguage(//new Long(
								item.getString());
					}

					if ("throughLanguage".equals(item.getFieldName())) {
						dictionaryTO.setThroughLanguage(//new Long(
								item.getString());
					}

					if ("description".equals(item.getFieldName())) {
						dictionaryTO.setDescription(item.getString());
					}

					if ("isPublic".equals(item.getFieldName())) {
						dictionaryTO.setIsPublic(true);
					}

				} else {
					if (DictionaryFileUtil.isCorrectFormat(item.getInputStream()) && isAcceptableSize()) {
						dictionaryTO.setFile(inputStreamToBytes(item.getInputStream()));
					} else {
						out.print("BADFORMAT");
						out.close();
						return;
					}
				}
			}

			request.getSession().setAttribute("dictionary", dictionaryTO);
			response.sendRedirect("dictionaryService");

		} catch (Exception e) {
			e.printStackTrace();
			out.print("ERROR");
			out.close();
			return;
		}
	}

	private byte[] inputStreamToBytes(InputStream inputStream) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		try {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) >= 0) {
				out.write(buffer, 0, len);
			}

			inputStream.close();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return out.toByteArray();
	}

	private boolean isAcceptableSize() {
		// TODO Ensure and check size limit (5Mb)
		return true;
	}

}
