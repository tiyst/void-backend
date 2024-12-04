package st.tiy.budgetopgg.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileLoaderUtil {

	private static final JsonMapper jsonMapper = createJsonMapper();

	public static <T> T loadFile(String path, Class<T> classType) throws IOException {
		URL url = FileLoaderUtil.class.getClassLoader().getResource(path);

		if (url == null) {
			throw new IOException("File not found");
		}

		InputStream inputStream = url.openStream();
		return jsonMapper.readValue(inputStream, classType);
	}

	private static JsonMapper createJsonMapper() {
		return JsonMapper.builder()
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
			.build();
	}
}
