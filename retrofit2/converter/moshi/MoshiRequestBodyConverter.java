package retrofit2.converter.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes11.dex */
final class MoshiRequestBodyConverter<T> implements Converter<T, RequestBody> {
    static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");
    private final JsonAdapter<T> adapter;
    private final boolean streaming;

    MoshiRequestBodyConverter(JsonAdapter<T> adapter, boolean streaming) {
        this.adapter = adapter;
        this.streaming = streaming;
    }

    @Override // retrofit2.Converter
    public RequestBody convert(T value) throws IOException {
        if (this.streaming) {
            return new MoshiStreamingRequestBody(this.adapter, value);
        }
        Buffer buffer = new Buffer();
        JsonWriter writer = JsonWriter.of(buffer);
        this.adapter.toJson(writer, value);
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
