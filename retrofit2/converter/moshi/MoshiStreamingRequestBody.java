package retrofit2.converter.moshi;

import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes11.dex */
final class MoshiStreamingRequestBody<T> extends RequestBody {
    private final JsonAdapter<T> adapter;
    private final T value;

    public MoshiStreamingRequestBody(JsonAdapter<T> adapter, T value) {
        this.adapter = adapter;
        this.value = value;
    }

    @Override // okhttp3.RequestBody
    /* JADX INFO: renamed from: contentType */
    public MediaType get$contentType() {
        return MoshiRequestBodyConverter.MEDIA_TYPE;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        this.adapter.toJson(sink, this.value);
    }
}
