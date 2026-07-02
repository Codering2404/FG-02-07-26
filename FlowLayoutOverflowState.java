package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlowLayoutOverflow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010%\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b&J\u000e\u0010'\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b(J\u000e\u0010)\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b*J'\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J'\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u0000¢\u0006\u0002\b1J-\u00102\u001a\u0004\u0018\u00010\r2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b3J\u0013\u00104\u001a\u00020\u00102\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0005HÖ\u0001J6\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\b\u0010 \u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010;\u001a\u00020<H\u0000ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\\\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<28\u0010\u000e\u001a4\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(?\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000fH\u0000ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ6\u00107\u001a\u0002082\b\u0010 \u001a\u0004\u0018\u00010B2\b\u0010\b\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020\u00102\u0006\u0010;\u001a\u00020<H\u0000ø\u0001\u0000¢\u0006\u0004\b=\u0010DJ\t\u0010E\u001a\u00020FHÖ\u0001R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000RB\u0010\u000e\u001a6\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0014\u0010\u0014\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0010\u0010 \u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\u0004\u0018\u00010\rX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "", "type", "Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "minLinesToShowCollapse", "", "minCrossAxisSizeToShowCollapse", "(Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;II)V", "collapseMeasurable", "Landroidx/compose/ui/layout/Measurable;", "collapsePlaceable", "Landroidx/compose/ui/layout/Placeable;", "collapseSize", "Landroidx/collection/IntIntPair;", "getOverflowMeasurable", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isExpandable", "noOfItemsShown", "itemCount", "getItemCount$foundation_layout_release", "()I", "setItemCount$foundation_layout_release", "(I)V", "itemShown", "getItemShown$foundation_layout_release", "setItemShown$foundation_layout_release", "getMinCrossAxisSizeToShowCollapse$foundation_layout_release", "getMinLinesToShowCollapse$foundation_layout_release", "getNoOfItemsShown$foundation_layout_release", "seeMoreMeasurable", "seeMorePlaceable", "seeMoreSize", "getType$foundation_layout_release", "()Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "component1", "component1$foundation_layout_release", "component2", "component2$foundation_layout_release", "component3", "component3$foundation_layout_release", "copy", "ellipsisInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "hasNext", "lineIndex", "totalCrossAxisSize", "ellipsisInfo$foundation_layout_release", "ellipsisSize", "ellipsisSize-F35zm-w$foundation_layout_release", "equals", "other", "hashCode", "setOverflowMeasurables", "", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "setOverflowMeasurables--hBUhpc$foundation_layout_release", "(Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Landroidx/compose/ui/layout/Measurable;Landroidx/compose/ui/layout/Measurable;J)V", "numberOfItemsShown", "setOverflowMeasurables-VKLhPVY$foundation_layout_release", "(Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function2;)V", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "isHorizontal", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;Landroidx/compose/ui/layout/IntrinsicMeasurable;ZJ)V", "toString", "", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class FlowLayoutOverflowState {
    public static final int $stable = 8;
    private Measurable collapseMeasurable;
    private Placeable collapsePlaceable;
    private IntIntPair collapseSize;
    private Function2<? super Boolean, ? super Integer, ? extends Measurable> getOverflowMeasurable;
    private int itemCount;
    private int itemShown = -1;
    private final int minCrossAxisSizeToShowCollapse;
    private final int minLinesToShowCollapse;
    private Measurable seeMoreMeasurable;
    private Placeable seeMorePlaceable;
    private IntIntPair seeMoreSize;
    private final FlowLayoutOverflow.OverflowType type;

    /* JADX INFO: compiled from: FlowLayoutOverflow.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FlowLayoutOverflow.OverflowType.values().length];
            try {
                iArr[FlowLayoutOverflow.OverflowType.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.Clip.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.ExpandIndicator.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ FlowLayoutOverflowState copy$default(FlowLayoutOverflowState flowLayoutOverflowState, FlowLayoutOverflow.OverflowType overflowType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            overflowType = flowLayoutOverflowState.type;
        }
        if ((i3 & 2) != 0) {
            i = flowLayoutOverflowState.minLinesToShowCollapse;
        }
        if ((i3 & 4) != 0) {
            i2 = flowLayoutOverflowState.minCrossAxisSizeToShowCollapse;
        }
        return flowLayoutOverflowState.copy(overflowType, i, i2);
    }

    /* JADX INFO: renamed from: component1$foundation_layout_release, reason: from getter */
    public final FlowLayoutOverflow.OverflowType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2$foundation_layout_release, reason: from getter */
    public final int getMinLinesToShowCollapse() {
        return this.minLinesToShowCollapse;
    }

    /* JADX INFO: renamed from: component3$foundation_layout_release, reason: from getter */
    public final int getMinCrossAxisSizeToShowCollapse() {
        return this.minCrossAxisSizeToShowCollapse;
    }

    public final FlowLayoutOverflowState copy(FlowLayoutOverflow.OverflowType type, int minLinesToShowCollapse, int minCrossAxisSizeToShowCollapse) {
        return new FlowLayoutOverflowState(type, minLinesToShowCollapse, minCrossAxisSizeToShowCollapse);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowLayoutOverflowState)) {
            return false;
        }
        FlowLayoutOverflowState flowLayoutOverflowState = (FlowLayoutOverflowState) other;
        return this.type == flowLayoutOverflowState.type && this.minLinesToShowCollapse == flowLayoutOverflowState.minLinesToShowCollapse && this.minCrossAxisSizeToShowCollapse == flowLayoutOverflowState.minCrossAxisSizeToShowCollapse;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + Integer.hashCode(this.minLinesToShowCollapse)) * 31) + Integer.hashCode(this.minCrossAxisSizeToShowCollapse);
    }

    public String toString() {
        return "FlowLayoutOverflowState(type=" + this.type + ", minLinesToShowCollapse=" + this.minLinesToShowCollapse + ", minCrossAxisSizeToShowCollapse=" + this.minCrossAxisSizeToShowCollapse + ')';
    }

    public FlowLayoutOverflowState(FlowLayoutOverflow.OverflowType type, int minLinesToShowCollapse, int minCrossAxisSizeToShowCollapse) {
        this.type = type;
        this.minLinesToShowCollapse = minLinesToShowCollapse;
        this.minCrossAxisSizeToShowCollapse = minCrossAxisSizeToShowCollapse;
    }

    public final FlowLayoutOverflow.OverflowType getType$foundation_layout_release() {
        return this.type;
    }

    public final int getMinLinesToShowCollapse$foundation_layout_release() {
        return this.minLinesToShowCollapse;
    }

    public final int getMinCrossAxisSizeToShowCollapse$foundation_layout_release() {
        return this.minCrossAxisSizeToShowCollapse;
    }

    public final int getNoOfItemsShown$foundation_layout_release() {
        if (this.itemShown == -1) {
            throw new IllegalStateException("Accessing noOfItemsShown before it is set. Are you calling this in the Composition phase, rather than in the draw phase? Consider our samples on how to use it during the draw phase or consider using ContextualFlowRow/ContextualFlowColumn which initializes this method in the composition phase.");
        }
        return this.itemShown;
    }

    /* JADX INFO: renamed from: getItemShown$foundation_layout_release, reason: from getter */
    public final int getItemShown() {
        return this.itemShown;
    }

    public final void setItemShown$foundation_layout_release(int i) {
        this.itemShown = i;
    }

    /* JADX INFO: renamed from: getItemCount$foundation_layout_release, reason: from getter */
    public final int getItemCount() {
        return this.itemCount;
    }

    public final void setItemCount$foundation_layout_release(int i) {
        this.itemCount = i;
    }

    /* JADX INFO: renamed from: ellipsisSize-F35zm-w$foundation_layout_release, reason: not valid java name */
    public final IntIntPair m601ellipsisSizeF35zmw$foundation_layout_release(boolean hasNext, int lineIndex, int totalCrossAxisSize) {
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
            case 2:
                return null;
            case 3:
                if (hasNext) {
                    return this.seeMoreSize;
                }
                return null;
            case 4:
                if (hasNext) {
                    return this.seeMoreSize;
                }
                if (lineIndex + 1 < this.minLinesToShowCollapse || totalCrossAxisSize < this.minCrossAxisSizeToShowCollapse) {
                    return null;
                }
                return this.collapseSize;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final FlowLayoutBuildingBlocks.WrapEllipsisInfo ellipsisInfo$foundation_layout_release(boolean hasNext, int lineIndex, int totalCrossAxisSize) {
        Measurable measurable;
        IntIntPair ellipsisSize;
        Placeable placeable;
        Measurable measurableInvoke;
        Measurable measurable2;
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
            case 2:
                return null;
            case 3:
            case 4:
                Measurable measurable3 = null;
                if (!hasNext) {
                    if (lineIndex >= this.minLinesToShowCollapse - 1 && totalCrossAxisSize >= this.minCrossAxisSizeToShowCollapse) {
                        Function2<? super Boolean, ? super Integer, ? extends Measurable> function2 = this.getOverflowMeasurable;
                        if (function2 == null || (measurableInvoke = function2.invoke(false, Integer.valueOf(getNoOfItemsShown$foundation_layout_release()))) == null) {
                            measurableInvoke = this.collapseMeasurable;
                        }
                        measurable3 = measurableInvoke;
                    }
                    IntIntPair ellipsisSize2 = this.collapseSize;
                    if (this.getOverflowMeasurable != null) {
                        measurable = measurable3;
                        ellipsisSize = ellipsisSize2;
                        placeable = null;
                    } else {
                        Placeable placeable2 = this.collapsePlaceable;
                        measurable = measurable3;
                        ellipsisSize = ellipsisSize2;
                        placeable = placeable2;
                    }
                } else {
                    Function2<? super Boolean, ? super Integer, ? extends Measurable> function22 = this.getOverflowMeasurable;
                    if (function22 == null || (measurable2 = function22.invoke(true, Integer.valueOf(getNoOfItemsShown$foundation_layout_release()))) == null) {
                        measurable2 = this.seeMoreMeasurable;
                    }
                    ellipsisSize = this.seeMoreSize;
                    if (this.getOverflowMeasurable != null) {
                        measurable = measurable2;
                        placeable = null;
                    } else {
                        Placeable placeable3 = this.seeMorePlaceable;
                        measurable = measurable2;
                        placeable = placeable3;
                    }
                }
                if (measurable == null) {
                    return null;
                }
                Intrinsics.checkNotNull(ellipsisSize);
                return new FlowLayoutBuildingBlocks.WrapEllipsisInfo(measurable, placeable, ellipsisSize.getPackedValue(), false, 8, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: setOverflowMeasurables--hBUhpc$foundation_layout_release, reason: not valid java name */
    public final void m603setOverflowMeasurableshBUhpc$foundation_layout_release(IntrinsicMeasurable seeMoreMeasurable, IntrinsicMeasurable collapseMeasurable, boolean isHorizontal, long constraints) {
        LayoutOrientation orientation = isHorizontal ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical;
        long orientationIndependentConstraints = OrientationIndependentConstraints.m642constructorimpl(constraints, orientation);
        if (seeMoreMeasurable != null) {
            int mainAxisSize = FlowLayoutKt.mainAxisMin(seeMoreMeasurable, isHorizontal, Constraints.m6577getMaxHeightimpl(orientationIndependentConstraints));
            int crossAxisSize = FlowLayoutKt.crossAxisMin(seeMoreMeasurable, isHorizontal, mainAxisSize);
            this.seeMoreSize = IntIntPair.m16boximpl(IntIntPair.m19constructorimpl(mainAxisSize, crossAxisSize));
            this.seeMoreMeasurable = seeMoreMeasurable instanceof Measurable ? (Measurable) seeMoreMeasurable : null;
            this.seeMorePlaceable = null;
        }
        if (collapseMeasurable != null) {
            int mainAxisSize2 = FlowLayoutKt.mainAxisMin(collapseMeasurable, isHorizontal, Constraints.m6577getMaxHeightimpl(orientationIndependentConstraints));
            int crossAxisSize2 = FlowLayoutKt.crossAxisMin(collapseMeasurable, isHorizontal, mainAxisSize2);
            this.collapseSize = IntIntPair.m16boximpl(IntIntPair.m19constructorimpl(mainAxisSize2, crossAxisSize2));
            this.collapseMeasurable = collapseMeasurable instanceof Measurable ? (Measurable) collapseMeasurable : null;
            this.collapsePlaceable = null;
        }
    }

    /* JADX INFO: renamed from: setOverflowMeasurables--hBUhpc$foundation_layout_release, reason: not valid java name */
    public final void m602setOverflowMeasurableshBUhpc$foundation_layout_release(final FlowLineMeasurePolicy measurePolicy, Measurable seeMoreMeasurable, Measurable collapseMeasurable, long constraints) {
        boolean isHorizontal = measurePolicy.isHorizontal();
        LayoutOrientation orientation = isHorizontal ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical;
        long jM642constructorimpl = OrientationIndependentConstraints.m642constructorimpl(constraints, orientation);
        long orientationIndependentConstraints = OrientationIndependentConstraints.m643copyyUG9Ft0(jM642constructorimpl, (10 & 1) != 0 ? Constraints.m6580getMinWidthimpl(jM642constructorimpl) : 0, (10 & 2) != 0 ? Constraints.m6578getMaxWidthimpl(jM642constructorimpl) : 0, (10 & 4) != 0 ? Constraints.m6579getMinHeightimpl(jM642constructorimpl) : 0, (10 & 8) != 0 ? Constraints.m6577getMaxHeightimpl(jM642constructorimpl) : 0);
        long finalConstraints = OrientationIndependentConstraints.m655toBoxConstraintsOenEA2s(orientationIndependentConstraints, orientation);
        if (seeMoreMeasurable != null) {
            FlowLayoutKt.m599measureAndCacherqJ1uqs(seeMoreMeasurable, measurePolicy, finalConstraints, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutOverflowState$setOverflowMeasurables$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                    invoke2(placeable);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable placeable) {
                    int mainAxisSize = 0;
                    int crossAxisSize = 0;
                    if (placeable != null) {
                        FlowLineMeasurePolicy $this$invoke_u24lambda_u241_u24lambda_u240 = measurePolicy;
                        mainAxisSize = $this$invoke_u24lambda_u241_u24lambda_u240.mainAxisSize(placeable);
                        crossAxisSize = $this$invoke_u24lambda_u241_u24lambda_u240.crossAxisSize(placeable);
                    }
                    this.this$0.seeMoreSize = IntIntPair.m16boximpl(IntIntPair.m19constructorimpl(mainAxisSize, crossAxisSize));
                    this.this$0.seeMorePlaceable = placeable;
                }
            });
            this.seeMoreMeasurable = seeMoreMeasurable;
        }
        if (collapseMeasurable != null) {
            FlowLayoutKt.m599measureAndCacherqJ1uqs(collapseMeasurable, measurePolicy, finalConstraints, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutOverflowState$setOverflowMeasurables$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                    invoke2(placeable);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable placeable) {
                    int mainAxisSize = 0;
                    int crossAxisSize = 0;
                    if (placeable != null) {
                        FlowLineMeasurePolicy $this$invoke_u24lambda_u241_u24lambda_u240 = measurePolicy;
                        mainAxisSize = $this$invoke_u24lambda_u241_u24lambda_u240.mainAxisSize(placeable);
                        crossAxisSize = $this$invoke_u24lambda_u241_u24lambda_u240.crossAxisSize(placeable);
                    }
                    this.this$0.collapseSize = IntIntPair.m16boximpl(IntIntPair.m19constructorimpl(mainAxisSize, crossAxisSize));
                    this.this$0.collapsePlaceable = placeable;
                }
            });
            this.collapseMeasurable = collapseMeasurable;
        }
    }

    /* JADX INFO: renamed from: setOverflowMeasurables-VKLhPVY$foundation_layout_release, reason: not valid java name */
    public final void m604setOverflowMeasurablesVKLhPVY$foundation_layout_release(FlowLineMeasurePolicy measurePolicy, long constraints, Function2<? super Boolean, ? super Integer, ? extends Measurable> getOverflowMeasurable) {
        this.itemShown = 0;
        this.getOverflowMeasurable = getOverflowMeasurable;
        Measurable seeMoreMeasurable = getOverflowMeasurable.invoke(true, 0);
        Measurable collapseMeasurable = getOverflowMeasurable.invoke(false, 0);
        m602setOverflowMeasurableshBUhpc$foundation_layout_release(measurePolicy, seeMoreMeasurable, collapseMeasurable, constraints);
    }
}
