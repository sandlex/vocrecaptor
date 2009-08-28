package com.vocrecaptor.web.server.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
			response.setContentType("text/plain");
			out = response.getWriter();

			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			ServletFileUpload upload = new ServletFileUpload();

			DictionaryTransferObject dictionaryTO = new DictionaryTransferObject();
			dictionaryTO.setIsPublic(false);

			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next(); // http://commons.apache.org/fileupload/streaming.html
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					if ("user".equals(item.getFieldName())) {
						dictionaryTO
								.setUser(new Long(Streams.asString(stream)));
					}

					if ("learningLanguage".equals(item.getFieldName())) {
						dictionaryTO.setLearningLanguage(Streams
								.asString(stream));
					}

					if ("throughLanguage".equals(item.getFieldName())) {
						dictionaryTO.setThroughLanguage(Streams
								.asString(stream));
					}

					if ("description".equals(item.getFieldName())) {
						dictionaryTO.setDescription(Streams.asString(stream));
					}

					if ("isPublic".equals(item.getFieldName())) {
						dictionaryTO.setIsPublic(true);
					}

				} else {
					if (/*DictionaryFileUtil.isCorrectFormat(item
							.openStream())
							&& */isAcceptableSize()) {
						dictionaryTO.setFile(inputStreamToBytes(item
								.openStream()));
					} else {
						out.print("BADFORMAT");
						out.close();
						return;
					}
				}
			}

			request.getSession().setAttribute("dictionary", dictionaryTO);
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
			out.print("TOLARGE");
			out.close();
			return;
		}
	}

	private byte[] inputStreamToBytes(InputStream stream)
			throws IOException, IllegalArgumentException, IllegalStateException {
		//TODO Maybe use this approach
		/*ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}

		//inputStream.close();
		out.close();

		return out.toByteArray();*/
		
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new InputStreamReader(stream));

		String str;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StringBuffer buffer = new StringBuffer();
		while ((str = inputStream.readLine()) != null) {
			if ("".equals(str) || str.split("\\|").length != DictionaryFileUtil.WORD_PARTS) {
				throw new IllegalArgumentException();
			} else {
				buffer.append(str);
				buffer.append("\n");
			}
		}
		out.write(buffer.toString().getBytes());
		
		//GAE free limit is 1Mb per object
		if (buffer.toString().getBytes().length > 900000) {
			throw new IllegalStateException();
		}
		
		out.close();
		return out.toByteArray();
	}

	private boolean isAcceptableSize() {
		// TODO Ensure and check size limit (5Mb)
		return true;
	}

}
