package retrofit2.converter.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.Moshi;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes11.dex */
public final class MoshiConverterFactory extends Converter.Factory {
    private final boolean failOnUnknown;
    private final boolean lenient;
    private final Moshi moshi;
    private final boolean serializeNulls;
    private final boolean streaming;

    public static MoshiConverterFactory create() {
        return create(new Moshi.Builder().build());
    }

    public static MoshiConverterFactory create(Moshi moshi) {
        if (moshi != null) {
            return new MoshiConverterFactory(moshi, false, false, false, false);
        }
        throw new NullPointerException("moshi == null");
    }

    private MoshiConverterFactory(Moshi moshi, boolean lenient, boolean failOnUnknown, boolean serializeNulls, boolean streaming) {
        this.moshi = moshi;
        this.lenient = lenient;
        this.failOnUnknown = failOnUnknown;
        this.serializeNulls = serializeNulls;
        this.streaming = streaming;
    }

    public MoshiConverterFactory asLenient() {
        return new MoshiConverterFactory(this.moshi, true, this.failOnUnknown, this.serializeNulls, this.streaming);
    }

    public MoshiConverterFactory failOnUnknown() {
        return new MoshiConverterFactory(this.moshi, this.lenient, true, this.serializeNulls, this.streaming);
    }

    public MoshiConverterFactory withNullSerialization() {
        return new MoshiConverterFactory(this.moshi, this.lenient, this.failOnUnknown, true, this.streaming);
    }

    public MoshiConverterFactory withStreaming() {
        return new MoshiConverterFactory(this.moshi, this.lenient, this.failOnUnknown, this.serializeNulls, true);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        JsonAdapter<?> adapter = this.moshi.adapter(type, jsonAnnotations(annotations));
        if (this.lenient) {
            adapter = adapter.lenient();
        }
        if (this.failOnUnknown) {
            adapter = adapter.failOnUnknown();
        }
        if (this.serializeNulls) {
            adapter = adapter.serializeNulls();
        }
        return new MoshiResponseBodyConverter(adapter);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        JsonAdapter<?> adapter = this.moshi.adapter(type, jsonAnnotations(parameterAnnotations));
        if (this.lenient) {
            adapter = adapter.lenient();
        }
        if (this.failOnUnknown) {
            adapter = adapter.failOnUnknown();
        }
        if (this.serializeNulls) {
            adapter = adapter.serializeNulls();
        }
        return new MoshiRequestBodyConverter(adapter, this.streaming);
    }

    private static Set<? extends Annotation> jsonAnnotations(Annotation[] annotations) {
        Set<Annotation> result = null;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if (result == null) {
                    result = new LinkedHashSet<>();
                }
                result.add(annotation);
            }
        }
        return result != null ? Collections.unmodifiableSet(result) : Collections.emptySet();
    }
}
