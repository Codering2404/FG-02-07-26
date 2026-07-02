package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextLayoutResultProxy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\"\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001aø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001a\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001aH\u0000ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001a\u0010%\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001aH\u0000ø\u0001\u0000¢\u0006\u0004\b&\u0010$J\u0016\u0010'\u001a\u00020\u001a*\u00020\u001aH\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010$R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/foundation/text/TextLayoutResultProxy;", "", AppMeasurementSdk.ConditionalUserProperty.VALUE, "Landroidx/compose/ui/text/TextLayoutResult;", "innerTextFieldCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "decorationBoxCoordinates", "(Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;)V", "getDecorationBoxCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setDecorationBoxCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "getInnerTextFieldCoordinates", "setInnerTextFieldCoordinates", "getValue", "()Landroidx/compose/ui/text/TextLayoutResult;", "getLineEnd", "", "lineIndex", "visibleEnd", "", "getLineForVerticalPosition", "vertical", "", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "coerceInVisibleBounds", "getOffsetForPosition-3MmeM6k", "(JZ)I", "isPositionOnText", "offset", "isPositionOnText-k-4lQ0M", "(J)Z", "translateDecorationToInnerCoordinates", "translateDecorationToInnerCoordinates-MK-Hz9U$foundation_release", "(J)J", "translateInnerToDecorationCoordinates", "translateInnerToDecorationCoordinates-MK-Hz9U$foundation_release", "coercedInVisibleBoundsOfInputText", "coercedInVisibleBoundsOfInputText-MK-Hz9U", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutResultProxy {
    public static final int $stable = 8;
    private LayoutCoordinates decorationBoxCoordinates;
    private LayoutCoordinates innerTextFieldCoordinates;
    private final TextLayoutResult value;

    public TextLayoutResultProxy(TextLayoutResult value, LayoutCoordinates innerTextFieldCoordinates, LayoutCoordinates decorationBoxCoordinates) {
        this.value = value;
        this.innerTextFieldCoordinates = innerTextFieldCoordinates;
        this.decorationBoxCoordinates = decorationBoxCoordinates;
    }

    public /* synthetic */ TextLayoutResultProxy(TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textLayoutResult, (i & 2) != 0 ? null : layoutCoordinates, (i & 4) != 0 ? null : layoutCoordinates2);
    }

    public final TextLayoutResult getValue() {
        return this.value;
    }

    public final LayoutCoordinates getInnerTextFieldCoordinates() {
        return this.innerTextFieldCoordinates;
    }

    public final void setInnerTextFieldCoordinates(LayoutCoordinates layoutCoordinates) {
        this.innerTextFieldCoordinates = layoutCoordinates;
    }

    public final LayoutCoordinates getDecorationBoxCoordinates() {
        return this.decorationBoxCoordinates;
    }

    public final void setDecorationBoxCoordinates(LayoutCoordinates layoutCoordinates) {
        this.decorationBoxCoordinates = layoutCoordinates;
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k$default, reason: not valid java name */
    public static /* synthetic */ int m1081getOffsetForPosition3MmeM6k$default(TextLayoutResultProxy textLayoutResultProxy, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return textLayoutResultProxy.m1082getOffsetForPosition3MmeM6k(j, z);
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k, reason: not valid java name */
    public final int m1082getOffsetForPosition3MmeM6k(long position, boolean coerceInVisibleBounds) {
        long coercedPosition = coerceInVisibleBounds ? m1080coercedInVisibleBoundsOfInputTextMKHz9U(position) : position;
        long relativePosition = m1084translateDecorationToInnerCoordinatesMKHz9U$foundation_release(coercedPosition);
        return this.value.m6063getOffsetForPositionk4lQ0M(relativePosition);
    }

    public final int getLineForVerticalPosition(float vertical) {
        long coercedPosition = m1080coercedInVisibleBoundsOfInputTextMKHz9U(OffsetKt.Offset(0.0f, vertical));
        float relativeVertical = Offset.m3922getYimpl(m1084translateDecorationToInnerCoordinatesMKHz9U$foundation_release(coercedPosition));
        return this.value.getLineForVerticalPosition(relativeVertical);
    }

    public static /* synthetic */ int getLineEnd$default(TextLayoutResultProxy textLayoutResultProxy, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayoutResultProxy.getLineEnd(i, z);
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        return this.value.getLineEnd(lineIndex, visibleEnd);
    }

    /* JADX INFO: renamed from: isPositionOnText-k-4lQ0M, reason: not valid java name */
    public final boolean m1083isPositionOnTextk4lQ0M(long offset) {
        long visibleOffset = m1080coercedInVisibleBoundsOfInputTextMKHz9U(offset);
        long relativeOffset = m1084translateDecorationToInnerCoordinatesMKHz9U$foundation_release(visibleOffset);
        int line = this.value.getLineForVerticalPosition(Offset.m3922getYimpl(relativeOffset));
        return Offset.m3921getXimpl(relativeOffset) >= this.value.getLineLeft(line) && Offset.m3921getXimpl(relativeOffset) <= this.value.getLineRight(line);
    }

    /* JADX INFO: renamed from: translateDecorationToInnerCoordinates-MK-Hz9U$foundation_release, reason: not valid java name */
    public final long m1084translateDecorationToInnerCoordinatesMKHz9U$foundation_release(long offset) {
        LayoutCoordinates it;
        LayoutCoordinates innerCoords = this.innerTextFieldCoordinates;
        if (innerCoords != null) {
            if (!innerCoords.isAttached()) {
                innerCoords = null;
            }
            if (innerCoords != null && (it = this.decorationBoxCoordinates) != null) {
                LayoutCoordinates decorationCoords = it.isAttached() ? it : null;
                if (decorationCoords != null) {
                    return innerCoords.mo5499localPositionOfR5De75A(decorationCoords, offset);
                }
            }
            return offset;
        }
        return offset;
    }

    /* JADX INFO: renamed from: translateInnerToDecorationCoordinates-MK-Hz9U$foundation_release, reason: not valid java name */
    public final long m1085translateInnerToDecorationCoordinatesMKHz9U$foundation_release(long offset) {
        LayoutCoordinates it;
        LayoutCoordinates innerCoords = this.innerTextFieldCoordinates;
        if (innerCoords != null) {
            if (!innerCoords.isAttached()) {
                innerCoords = null;
            }
            if (innerCoords != null && (it = this.decorationBoxCoordinates) != null) {
                LayoutCoordinates decorationCoords = it.isAttached() ? it : null;
                if (decorationCoords != null) {
                    return decorationCoords.mo5499localPositionOfR5De75A(innerCoords, offset);
                }
            }
            return offset;
        }
        return offset;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX INFO: renamed from: coercedInVisibleBoundsOfInputText-MK-Hz9U, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final long m1080coercedInVisibleBoundsOfInputTextMKHz9U(long r7) {
        /*
            r6 = this;
            androidx.compose.ui.layout.LayoutCoordinates r0 = r6.innerTextFieldCoordinates
            if (r0 == 0) goto L20
            r1 = 0
            boolean r2 = r0.isAttached()
            if (r2 == 0) goto L17
            androidx.compose.ui.layout.LayoutCoordinates r2 = r6.decorationBoxCoordinates
            r3 = 0
            if (r2 == 0) goto L1d
            r4 = 0
            r5 = 2
            androidx.compose.ui.geometry.Rect r3 = androidx.compose.ui.layout.LayoutCoordinates.localBoundingBoxOf$default(r2, r0, r4, r5, r3)
            goto L1d
        L17:
            androidx.compose.ui.geometry.Rect$Companion r2 = androidx.compose.ui.geometry.Rect.INSTANCE
            androidx.compose.ui.geometry.Rect r3 = r2.getZero()
        L1d:
            if (r3 != 0) goto L26
        L20:
            androidx.compose.ui.geometry.Rect$Companion r0 = androidx.compose.ui.geometry.Rect.INSTANCE
            androidx.compose.ui.geometry.Rect r3 = r0.getZero()
        L26:
            long r0 = androidx.compose.foundation.text.TextLayoutResultProxyKt.m1086access$coerceIn3MmeM6k(r7, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextLayoutResultProxy.m1080coercedInVisibleBoundsOfInputTextMKHz9U(long):long");
    }
}
