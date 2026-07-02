package androidx.compose.foundation.gestures;

import androidx.collection.ObjectFloatMap;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u001f\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J@\u0010\u0014\u001a\u00020\u001526\u0010\u0016\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00150\u0017H\u0016J\u0015\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\fH\u0016J\u0015\u0010!\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020$H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006%"}, d2 = {"Landroidx/compose/foundation/gestures/MapDraggableAnchors;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Landroidx/compose/foundation/gestures/DraggableAnchors;", "anchors", "Landroidx/collection/ObjectFloatMap;", "(Landroidx/collection/ObjectFloatMap;)V", "size", "", "getSize", "()I", "closestAnchor", "position", "", "(F)Ljava/lang/Object;", "searchUpwards", "", "(FZ)Ljava/lang/Object;", "equals", "other", "", "forEach", "", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "anchor", "hasAnchorFor", AppMeasurementSdk.ConditionalUserProperty.VALUE, "(Ljava/lang/Object;)Z", "hashCode", "maxAnchor", "minAnchor", "positionOf", "(Ljava/lang/Object;)F", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MapDraggableAnchors<T> implements DraggableAnchors<T> {
    private final ObjectFloatMap<T> anchors;

    public MapDraggableAnchors(ObjectFloatMap<T> objectFloatMap) {
        this.anchors = objectFloatMap;
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float positionOf(T value) {
        return this.anchors.getOrDefault(value, Float.NaN);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public boolean hasAnchorFor(T value) {
        return this.anchors.containsKey(value);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public T closestAnchor(float position) {
        T t = null;
        float f = Float.POSITIVE_INFINITY;
        ObjectFloatMap<T> objectFloatMap = this.anchors;
        Object[] objArr = objectFloatMap.keys;
        float[] fArr = objectFloatMap.values;
        long[] jArr = objectFloatMap.metadata;
        int length = jArr.length - 2;
        int i = 0;
        if (0 <= length) {
            while (true) {
                long j = jArr[i];
                T t2 = t;
                float f2 = f;
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    t = t2;
                    f = f2;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            float fAbs = Math.abs(position - fArr[i4]);
                            if (fAbs <= f2) {
                                t2 = (T) obj;
                                f2 = fAbs;
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return t2;
                    }
                    t = t2;
                    f = f2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public T closestAnchor(float position, boolean searchUpwards) {
        T t = null;
        float f = Float.POSITIVE_INFINITY;
        ObjectFloatMap<T> objectFloatMap = this.anchors;
        Object[] objArr = objectFloatMap.keys;
        float[] fArr = objectFloatMap.values;
        long[] jArr = objectFloatMap.metadata;
        int length = jArr.length - 2;
        int i = 0;
        if (0 <= length) {
            while (true) {
                long j = jArr[i];
                T t2 = t;
                float f2 = f;
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    t = t2;
                    f = f2;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            float f3 = fArr[i4];
                            float f4 = searchUpwards ? f3 - position : position - f3;
                            float f5 = f4 < 0.0f ? Float.POSITIVE_INFINITY : f4;
                            if (f5 <= f2) {
                                t2 = (T) obj;
                                f2 = f5;
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return t2;
                    }
                    t = t2;
                    f = f2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float minAnchor() {
        return AnchoredDraggableKt.minValueOrNaN(this.anchors);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public float maxAnchor() {
        return AnchoredDraggableKt.maxValueOrNaN(this.anchors);
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public int getSize() {
        return this.anchors.get_size();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof MapDraggableAnchors) {
            return Intrinsics.areEqual(this.anchors, ((MapDraggableAnchors) other).anchors);
        }
        return false;
    }

    public int hashCode() {
        return this.anchors.hashCode() * 31;
    }

    public String toString() {
        return "MapDraggableAnchors(" + this.anchors + ')';
    }

    @Override // androidx.compose.foundation.gestures.DraggableAnchors
    public void forEach(Function2<? super T, ? super Float, Unit> block) {
        int $i$f$forEach;
        Object[] k$iv;
        int i;
        int $i$f$forEach2;
        Object[] k$iv2;
        ObjectFloatMap<T> objectFloatMap = this.anchors;
        int $i$f$forEach3 = 0;
        Object[] k$iv3 = objectFloatMap.keys;
        float[] v$iv = objectFloatMap.values;
        long[] m$iv$iv = objectFloatMap.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            ObjectFloatMap<T> objectFloatMap2 = objectFloatMap;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i2;
                        $i$f$forEach2 = $i$f$forEach3;
                        k$iv2 = k$iv3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        $i$f$forEach2 = $i$f$forEach3;
                        k$iv2 = k$iv3;
                        block.invoke(k$iv3[index$iv$iv], Float.valueOf(v$iv[index$iv$iv]));
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    k$iv3 = k$iv2;
                    $i$f$forEach3 = $i$f$forEach2;
                }
                $i$f$forEach = $i$f$forEach3;
                k$iv = k$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            objectFloatMap = objectFloatMap2;
            k$iv3 = k$iv;
            $i$f$forEach3 = $i$f$forEach;
        }
    }
}
