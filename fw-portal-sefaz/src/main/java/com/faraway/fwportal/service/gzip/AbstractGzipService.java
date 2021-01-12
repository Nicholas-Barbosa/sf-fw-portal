package com.faraway.fwportal.service.gzip;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public abstract class AbstractGzipService {

	protected String decode(String compressedText) {
		byte[] compressed;
		try {
			compressed = compressedText.getBytes("UTF8");

			Base64.Decoder d = Base64.getDecoder();
			compressed = d.decode(compressed);

			final int BUFFER_SIZE = 32;

			ByteArrayInputStream is = new ByteArrayInputStream(compressed);

			GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);

			StringBuilder string = new StringBuilder();

			byte[] data = new byte[BUFFER_SIZE];

			int bytesRead;

			while ((bytesRead = gis.read(data)) != -1) {
				string.append(new String(data, 0, bytesRead));
			}
			gis.close();
			is.close();
			return string.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
