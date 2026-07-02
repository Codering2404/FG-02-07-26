package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0007\u0010\rB\u0011\b\u0002\u0012\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000fJ;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0011J\u0015\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b$\u0010\"J\u0013\u0010%\u001a\u00020\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u000fJ\u001b\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\rJ\u0010\u0010)\u001a\u00020*HÖ\u0001¢\u0006\u0004\b+\u0010,R\u0012\u0010\u0006\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0005\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0012\u0010\u0004\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0012\u0010\u0002\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u000e\u001a\u00020\nX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0015\u0088\u0001\u000e\u0092\u0001\u00020\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "", "mainAxisMin", "", "mainAxisMax", "crossAxisMin", "crossAxisMax", "constructor-impl", "(IIII)J", "c", "Landroidx/compose/ui/unit/Constraints;", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)J", AppMeasurementSdk.ConditionalUserProperty.VALUE, "(J)J", "getCrossAxisMax-impl", "(J)I", "getCrossAxisMin-impl", "getMainAxisMax-impl", "getMainAxisMin-impl", "J", "copy", "copy-yUG9Ft0", "(JIIII)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "maxHeight", "maxHeight-impl", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)I", "maxWidth", "maxWidth-impl", "stretchCrossAxis", "stretchCrossAxis-q4ezo7Y", "toBoxConstraints", "toBoxConstraints-OenEA2s", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class OrientationIndependentConstraints {
    private final long value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OrientationIndependentConstraints m639boximpl(long j) {
        return new OrientationIndependentConstraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static long m641constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m645equalsimpl(long j, Object obj) {
        return (obj instanceof OrientationIndependentConstraints) && Constraints.m6571equalsimpl0(j, ((OrientationIndependentConstraints) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m646equalsimpl0(long j, long j2) {
        return Constraints.m6571equalsimpl0(j, j2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m651hashCodeimpl(long j) {
        return Constraints.m6581hashCodeimpl(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m656toStringimpl(long j) {
        return "OrientationIndependentConstraints(value=" + ((Object) Constraints.m6583toStringimpl(j)) + ')';
    }

    public boolean equals(Object obj) {
        return m645equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m651hashCodeimpl(this.value);
    }

    public String toString() {
        return m656toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    private /* synthetic */ OrientationIndependentConstraints(long value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: getMainAxisMin-impl, reason: not valid java name */
    public static final int m650getMainAxisMinimpl(long arg0) {
        return Constraints.m6580getMinWidthimpl(arg0);
    }

    /* JADX INFO: renamed from: getMainAxisMax-impl, reason: not valid java name */
    public static final int m649getMainAxisMaximpl(long arg0) {
        return Constraints.m6578getMaxWidthimpl(arg0);
    }

    /* JADX INFO: renamed from: getCrossAxisMin-impl, reason: not valid java name */
    public static final int m648getCrossAxisMinimpl(long arg0) {
        return Constraints.m6579getMinHeightimpl(arg0);
    }

    /* JADX INFO: renamed from: getCrossAxisMax-impl, reason: not valid java name */
    public static final int m647getCrossAxisMaximpl(long arg0) {
        return Constraints.m6577getMaxHeightimpl(arg0);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m640constructorimpl(int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m641constructorimpl(ConstraintsKt.Constraints(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m642constructorimpl(long c, LayoutOrientation orientation) {
        return m640constructorimpl(orientation == LayoutOrientation.Horizontal ? Constraints.m6580getMinWidthimpl(c) : Constraints.m6579getMinHeightimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m6578getMaxWidthimpl(c) : Constraints.m6577getMaxHeightimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m6579getMinHeightimpl(c) : Constraints.m6580getMinWidthimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m6577getMaxHeightimpl(c) : Constraints.m6578getMaxWidthimpl(c));
    }

    /* JADX INFO: renamed from: stretchCrossAxis-q4ezo7Y, reason: not valid java name */
    public static final long m654stretchCrossAxisq4ezo7Y(long arg0) {
        int iM6579getMinHeightimpl;
        int iM6580getMinWidthimpl = Constraints.m6580getMinWidthimpl(arg0);
        int iM6578getMaxWidthimpl = Constraints.m6578getMaxWidthimpl(arg0);
        if (Constraints.m6577getMaxHeightimpl(arg0) != Integer.MAX_VALUE) {
            iM6579getMinHeightimpl = Constraints.m6577getMaxHeightimpl(arg0);
        } else {
            iM6579getMinHeightimpl = Constraints.m6579getMinHeightimpl(arg0);
        }
        return m640constructorimpl(iM6580getMinWidthimpl, iM6578getMaxWidthimpl, iM6579getMinHeightimpl, Constraints.m6577getMaxHeightimpl(arg0));
    }

    /* JADX INFO: renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m655toBoxConstraintsOenEA2s(long arg0, LayoutOrientation orientation) {
        return orientation == LayoutOrientation.Horizontal ? ConstraintsKt.Constraints(Constraints.m6580getMinWidthimpl(arg0), Constraints.m6578getMaxWidthimpl(arg0), Constraints.m6579getMinHeightimpl(arg0), Constraints.m6577getMaxHeightimpl(arg0)) : ConstraintsKt.Constraints(Constraints.m6579getMinHeightimpl(arg0), Constraints.m6577getMaxHeightimpl(arg0), Constraints.m6580getMinWidthimpl(arg0), Constraints.m6578getMaxWidthimpl(arg0));
    }

    /* JADX INFO: renamed from: maxWidth-impl, reason: not valid java name */
    public static final int m653maxWidthimpl(long arg0, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m6578getMaxWidthimpl(arg0);
        }
        return Constraints.m6577getMaxHeightimpl(arg0);
    }

    /* JADX INFO: renamed from: maxHeight-impl, reason: not valid java name */
    public static final int m652maxHeightimpl(long arg0, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m6577getMaxHeightimpl(arg0);
        }
        return Constraints.m6578getMaxWidthimpl(arg0);
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0, reason: not valid java name */
    public static final long m643copyyUG9Ft0(long arg0, int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m640constructorimpl(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax);
    }
}
