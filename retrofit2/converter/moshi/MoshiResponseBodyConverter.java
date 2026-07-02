package retrofit2.converter.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import java.io.IOException;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.ByteString;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes11.dex */
final class MoshiResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final ByteString UTF8_BOM = ByteString.decodeHex("EFBBBF");
    private final JsonAdapter<T> adapter;

    MoshiResponseBodyConverter(JsonAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override // retrofit2.Converter
    public T convert(ResponseBody value) throws IOException {
        BufferedSource source = value.getBodySource();
        try {
            if (source.rangeEquals(0L, UTF8_BOM)) {
                source.skip(UTF8_BOM.size());
            }
            JsonReader reader = JsonReader.of(source);
            T result = this.adapter.fromJson(reader);
            if (reader.peek() != JsonReader.Token.END_DOCUMENT) {
                throw new JsonDataException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            value.close();
        }
    }
}
