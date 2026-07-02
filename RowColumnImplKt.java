package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001ae\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00172\u001d\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0019¢\u0006\u0002\b\u001a2\u001d\u0010\u001b\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0019¢\u0006\u0002\b\u001a2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015H\u0082\b\u001aF\u0010\u001e\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00172\u001d\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0019¢\u0006\u0002\b\u001a2\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015H\u0082\b\"\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\u00020\u0006*\u0004\u0018\u00010\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b\"\u001a\u0010\n\u001a\u0004\u0018\u00010\u0002*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001a\u0010\n\u001a\u0004\u0018\u00010\u0002*\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000f\"\u001a\u0010\u0010\u001a\u00020\u0011*\u0004\u0018\u00010\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "Landroidx/compose/foundation/layout/RowColumnParentData;", "getCrossAxisAlignment", "(Landroidx/compose/foundation/layout/RowColumnParentData;)Landroidx/compose/foundation/layout/CrossAxisAlignment;", "fill", "", "getFill", "(Landroidx/compose/foundation/layout/RowColumnParentData;)Z", "isRelative", "rowColumnParentData", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getRowColumnParentData", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Landroidx/compose/foundation/layout/RowColumnParentData;", "Landroidx/compose/ui/layout/Placeable;", "(Landroidx/compose/ui/layout/Placeable;)Landroidx/compose/foundation/layout/RowColumnParentData;", "weight", "", "getWeight", "(Landroidx/compose/foundation/layout/RowColumnParentData;)F", "intrinsicCrossAxisSize", "", "children", "", "mainAxisSize", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "crossAxisSize", "mainAxisAvailable", "mainAxisSpacing", "intrinsicMainAxisSize", "crossAxisAvailable", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowColumnImplKt {
    public static final RowColumnParentData getRowColumnParentData(IntrinsicMeasurable $this$rowColumnParentData) {
        Object parentData = $this$rowColumnParentData.getParentData();
        if (parentData instanceof RowColumnParentData) {
            return (RowColumnParentData) parentData;
        }
        return null;
    }

    public static final RowColumnParentData getRowColumnParentData(Placeable $this$rowColumnParentData) {
        Object parentData = $this$rowColumnParentData.getParentData();
        if (parentData instanceof RowColumnParentData) {
            return (RowColumnParentData) parentData;
        }
        return null;
    }

    public static final float getWeight(RowColumnParentData $this$weight) {
        if ($this$weight != null) {
            return $this$weight.getWeight();
        }
        return 0.0f;
    }

    public static final boolean getFill(RowColumnParentData $this$fill) {
        if ($this$fill != null) {
            return $this$fill.getFill();
        }
        return true;
    }

    public static final CrossAxisAlignment getCrossAxisAlignment(RowColumnParentData $this$crossAxisAlignment) {
        if ($this$crossAxisAlignment != null) {
            return $this$crossAxisAlignment.getCrossAxisAlignment();
        }
        return null;
    }

    public static final boolean isRelative(RowColumnParentData $this$isRelative) {
        CrossAxisAlignment crossAxisAlignment = getCrossAxisAlignment($this$isRelative);
        if (crossAxisAlignment != null) {
            return crossAxisAlignment.isRelative$foundation_layout_release();
        }
        return false;
    }

    private static final int intrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2, int crossAxisAvailable, int mainAxisSpacing) {
        if (list.isEmpty()) {
            return 0;
        }
        int weightUnitSpace = 0;
        int fixedSpace = 0;
        float totalWeight = 0.0f;
        int index$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv >= size) {
                float $this$fastRoundToInt$iv = weightUnitSpace * totalWeight;
                return Math.round($this$fastRoundToInt$iv) + fixedSpace + ((list.size() - 1) * mainAxisSpacing);
            }
            Object item$iv = list.get(index$iv);
            IntrinsicMeasurable child = (IntrinsicMeasurable) item$iv;
            float weight = getWeight(getRowColumnParentData(child));
            int size2 = function2.invoke(child, Integer.valueOf(crossAxisAvailable)).intValue();
            if (weight == 0.0f) {
                fixedSpace += size2;
            } else if (weight > 0.0f) {
                totalWeight += weight;
                float $this$fastRoundToInt$iv2 = size2 / weight;
                weightUnitSpace = Math.max(weightUnitSpace, Math.round($this$fastRoundToInt$iv2));
            }
            index$iv++;
        }
    }

    private static final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function22, int mainAxisAvailable, int mainAxisSpacing) {
        float f;
        int weightUnitSpace;
        int iRound;
        boolean z = false;
        if (list.isEmpty()) {
            return 0;
        }
        boolean z2 = true;
        int fixedSpace = Math.min((list.size() - 1) * mainAxisSpacing, mainAxisAvailable);
        int crossAxisMax = 0;
        float totalWeight = 0.0f;
        int index$iv = 0;
        int size = list.size();
        while (true) {
            f = 0.0f;
            if (index$iv >= size) {
                break;
            }
            Object item$iv = list.get(index$iv);
            IntrinsicMeasurable child = (IntrinsicMeasurable) item$iv;
            float weight = getWeight(getRowColumnParentData(child));
            if (weight == 0.0f ? z2 : z) {
                int remaining = mainAxisAvailable == Integer.MAX_VALUE ? Integer.MAX_VALUE : mainAxisAvailable - fixedSpace;
                int mainAxisSpace = Math.min(function2.invoke(child, Integer.MAX_VALUE).intValue(), remaining);
                fixedSpace += mainAxisSpace;
                crossAxisMax = Math.max(crossAxisMax, function22.invoke(child, Integer.valueOf(mainAxisSpace)).intValue());
            } else if (weight > 0.0f) {
                totalWeight += weight;
            }
            index$iv++;
            z = false;
            z2 = true;
        }
        if (totalWeight == 0.0f) {
            weightUnitSpace = 0;
        } else if (mainAxisAvailable == Integer.MAX_VALUE) {
            weightUnitSpace = Integer.MAX_VALUE;
        } else {
            float $this$fastRoundToInt$iv = Math.max(mainAxisAvailable - fixedSpace, 0) / totalWeight;
            weightUnitSpace = Math.round($this$fastRoundToInt$iv);
        }
        int index$iv2 = 0;
        int size2 = list.size();
        while (index$iv2 < size2) {
            Object item$iv2 = list.get(index$iv2);
            IntrinsicMeasurable child2 = (IntrinsicMeasurable) item$iv2;
            float weight2 = getWeight(getRowColumnParentData(child2));
            if (weight2 > f) {
                if (weightUnitSpace != Integer.MAX_VALUE) {
                    float $this$fastRoundToInt$iv2 = weightUnitSpace * weight2;
                    iRound = Math.round($this$fastRoundToInt$iv2);
                } else {
                    iRound = Integer.MAX_VALUE;
                }
                crossAxisMax = Math.max(crossAxisMax, function22.invoke(child2, Integer.valueOf(iRound)).intValue());
            }
            index$iv2++;
            f = 0.0f;
        }
        return crossAxisMax;
    }
}
