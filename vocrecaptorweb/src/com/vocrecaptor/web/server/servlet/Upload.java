package com.vocrecaptor.web.server.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

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
			response.setCharacterEncoding("utf8");
			response.setContentType("text/plain");
			out = response.getWriter();

			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			ServletFileUpload upload = new ServletFileUpload();

			//for creating
			String user = "";
			String learningLanguage = "";
			String throughLanguage = "";
			String description = "";
			boolean isPublic = false;
			
			//for updating
			String dictId = "";
			String mode = "";
			
			
			//for both
			String file = "";

			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next(); // http://commons.apache.org/fileupload/streaming.html
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					
					//for creating
					if ("user".equals(item.getFieldName())) {
						user = Streams.asString(stream);
					}

					if ("learningLanguage".equals(item.getFieldName())) {
						learningLanguage = Streams.asString(stream);
					}

					if ("throughLanguage".equals(item.getFieldName())) {
						throughLanguage = Streams.asString(stream);
					}

					if ("description".equals(item.getFieldName())) {
						description = Streams.asString(stream);
					}

					if ("isPublic".equals(item.getFieldName())) {
						isPublic = true;
					}
					
					//for updating
					if ("mode".equals(item.getFieldName())) {
						mode = Streams.asString(stream);
					}

					if ("id".equals(item.getFieldName())) {
						dictId = Streams.asString(stream);
					}

				} else {
					file = inputStreamToString(item.openStream());
				}
			}

			DictionaryTransferObject dictionaryTO = new DictionaryTransferObject();
			if (!"".equals(user) && "".equals(dictId)) {
				dictionaryTO.setUser(new Long(user));
				dictionaryTO.setLearningLanguage(learningLanguage);
				dictionaryTO.setThroughLanguage(throughLanguage);
				dictionaryTO.setDescription(description);				
				dictionaryTO.setIsPublic(isPublic);
				dictionaryTO.setFile(file);
				mode = "create";
			} else if (!"".equals(dictId) && "".equals(user)) {
				dictionaryTO.setId(new Long(dictId));
				dictionaryTO.setFile(file);
			} else {
				throw new IOException("user or dictId should be provided");
			}
			
			request.getSession().setAttribute(mode, dictionaryTO);
			response.sendRedirect("dictionaryService");

		} catch (IllegalArgumentException e) {
			out.print("BADFORMAT");
			out.close();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			out.print("ERROR");
			out.close();
			return;
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.print("ERROR");
			out.close();
			return;
		} catch (IllegalStateException e) {
			//TODO Check this
			out.print("TOLARGE");
			out.close();
			return;
		}
	}

	private String inputStreamToString(InputStream stream) throws IOException,
			IllegalArgumentException, IllegalStateException {
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new InputStreamReader(stream, "utf8"));

		String str;
		StringBuffer buffer = new StringBuffer();
		while ((str = inputStream.readLine()) != null) {
			if ("".equals(str)
					|| str.split("\\|").length != DictionaryFileUtil.WORD_PARTS) {
				throw new IllegalArgumentException();
			} else {
				buffer.append(str);
				buffer.append("\n");
			}
		}

		// GAE free limit is 1Mb per object
		if (buffer.toString().getBytes().length > 900000) {
			throw new IllegalStateException();
		}

		return buffer.toString();
	}

}
