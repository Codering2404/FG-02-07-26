package androidx.compose.foundation.text.selection;

import androidx.collection.LongIntMap;
import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.text.selection.Selection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectionLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0+2\u0006\u0010,\u001a\u00020\rH\u0016J\u001c\u0010-\u001a\u00020.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020.00H\u0016J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u0000H\u0002J\u0012\u00106\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0001H\u0016J\u0018\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\u000bH\u0002J\u0018\u0010:\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\u0006\u0010;\u001a\u00020\u000bH\u0002J\b\u0010<\u001a\u00020=H\u0016J2\u0010>\u001a\u00020.*\b\u0012\u0004\u0012\u00020\r0?2\u0006\u0010,\u001a\u00020\r2\u0006\u0010@\u001a\u00020\u00062\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\bH\u0002R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0014\u0010\t\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0015R\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0019R\u0014\u0010'\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019¨\u0006C"}, d2 = {"Landroidx/compose/foundation/text/selection/MultiSelectionLayout;", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "selectableIdToInfoListIndex", "Landroidx/collection/LongIntMap;", "infoList", "", "Landroidx/compose/foundation/text/selection/SelectableInfo;", "startSlot", "", "endSlot", "isStartHandle", "", "previousSelection", "Landroidx/compose/foundation/text/selection/Selection;", "(Landroidx/collection/LongIntMap;Ljava/util/List;IIZLandroidx/compose/foundation/text/selection/Selection;)V", "crossStatus", "Landroidx/compose/foundation/text/selection/CrossStatus;", "getCrossStatus", "()Landroidx/compose/foundation/text/selection/CrossStatus;", "currentInfo", "getCurrentInfo", "()Landroidx/compose/foundation/text/selection/SelectableInfo;", "endInfo", "getEndInfo", "getEndSlot", "()I", "firstInfo", "getFirstInfo", "getInfoList", "()Ljava/util/List;", "()Z", "lastInfo", "getLastInfo", "getPreviousSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "getSelectableIdToInfoListIndex", "()Landroidx/collection/LongIntMap;", "size", "getSize", "startInfo", "getStartInfo", "getStartSlot", "createSubSelections", "Landroidx/collection/LongObjectMap;", "selection", "forEachMiddleInfo", "", "block", "Lkotlin/Function1;", "getInfoListIndexBySelectableId", "id", "", "shouldAnyInfoRecomputeSelection", "other", "shouldRecomputeSelection", "slotToIndex", "slot", "isMinimumSlot", "startOrEndSlotToIndex", "isStartSlot", "toString", "", "createAndPutSubSelection", "Landroidx/collection/MutableLongObjectMap;", "info", "minOffset", "maxOffset", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MultiSelectionLayout implements SelectionLayout {
    private final int endSlot;
    private final List<SelectableInfo> infoList;
    private final boolean isStartHandle;
    private final Selection previousSelection;
    private final LongIntMap selectableIdToInfoListIndex;
    private final int startSlot;

    /* JADX INFO: compiled from: SelectionLayout.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CrossStatus.values().length];
            try {
                iArr[CrossStatus.COLLAPSED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CrossStatus.NOT_CROSSED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CrossStatus.CROSSED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MultiSelectionLayout(LongIntMap selectableIdToInfoListIndex, List<SelectableInfo> list, int startSlot, int endSlot, boolean isStartHandle, Selection previousSelection) {
        this.selectableIdToInfoListIndex = selectableIdToInfoListIndex;
        this.infoList = list;
        this.startSlot = startSlot;
        this.endSlot = endSlot;
        this.isStartHandle = isStartHandle;
        this.previousSelection = previousSelection;
        if (this.infoList.size() > 1) {
        } else {
            throw new IllegalStateException(("MultiSelectionLayout requires an infoList size greater than 1, was " + this.infoList.size() + '.').toString());
        }
    }

    public final LongIntMap getSelectableIdToInfoListIndex() {
        return this.selectableIdToInfoListIndex;
    }

    public final List<SelectableInfo> getInfoList() {
        return this.infoList;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getStartSlot() {
        return this.startSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getEndSlot() {
        return this.endSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    /* JADX INFO: renamed from: isStartHandle, reason: from getter */
    public boolean getIsStartHandle() {
        return this.isStartHandle;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Selection getPreviousSelection() {
        return this.previousSelection;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getSize() {
        return this.infoList.size();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public CrossStatus getCrossStatus() {
        return getStartSlot() < getEndSlot() ? CrossStatus.NOT_CROSSED : getStartSlot() > getEndSlot() ? CrossStatus.CROSSED : this.infoList.get(getStartSlot() / 2).getRawCrossStatus();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getStartInfo() {
        return this.infoList.get(startOrEndSlotToIndex(getStartSlot(), true));
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getEndInfo() {
        return this.infoList.get(startOrEndSlotToIndex(getEndSlot(), false));
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    /* JADX INFO: renamed from: getCurrentInfo */
    public SelectableInfo getInfo() {
        return getIsStartHandle() ? getStartInfo() : getEndInfo();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getFirstInfo() {
        return getCrossStatus() == CrossStatus.CROSSED ? getEndInfo() : getStartInfo();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getLastInfo() {
        return getCrossStatus() == CrossStatus.CROSSED ? getStartInfo() : getEndInfo();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public void forEachMiddleInfo(Function1<? super SelectableInfo, Unit> block) {
        int minIndex = getInfoListIndexBySelectableId(getFirstInfo().getSelectableId());
        int maxIndex = getInfoListIndexBySelectableId(getLastInfo().getSelectableId());
        if (minIndex + 1 >= maxIndex) {
            return;
        }
        for (int i = minIndex + 1; i < maxIndex; i++) {
            block.invoke(this.infoList.get(i));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public boolean shouldRecomputeSelection(SelectionLayout other) {
        return (getPreviousSelection() != null && other != null && (other instanceof MultiSelectionLayout) && getIsStartHandle() == ((MultiSelectionLayout) other).getIsStartHandle() && getStartSlot() == ((MultiSelectionLayout) other).getStartSlot() && getEndSlot() == ((MultiSelectionLayout) other).getEndSlot() && !shouldAnyInfoRecomputeSelection((MultiSelectionLayout) other)) ? false : true;
    }

    private final boolean shouldAnyInfoRecomputeSelection(MultiSelectionLayout other) {
        if (getSize() != other.getSize()) {
            return true;
        }
        int size = this.infoList.size();
        for (int i = 0; i < size; i++) {
            SelectableInfo thisInfo = this.infoList.get(i);
            SelectableInfo otherInfo = other.infoList.get(i);
            if (thisInfo.shouldRecomputeSelection(otherInfo)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public LongObjectMap<Selection> createSubSelections(final Selection selection) {
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            if (!((selection.getHandlesCrossed() && selection.getStart().getOffset() >= selection.getEnd().getOffset()) || (!selection.getHandlesCrossed() && selection.getStart().getOffset() <= selection.getEnd().getOffset()))) {
                throw new IllegalStateException(("unexpectedly miss-crossed selection: " + selection).toString());
            }
            return LongObjectMapKt.longObjectMapOf(selection.getStart().getSelectableId(), selection);
        }
        final MutableLongObjectMap<Selection> mutableLongObjectMapMutableLongObjectMapOf = LongObjectMapKt.mutableLongObjectMapOf();
        Selection.AnchorInfo minAnchor = selection.getHandlesCrossed() ? selection.getEnd() : selection.getStart();
        createAndPutSubSelection(mutableLongObjectMapMutableLongObjectMapOf, selection, getFirstInfo(), minAnchor.getOffset(), getFirstInfo().getTextLength());
        forEachMiddleInfo(new Function1<SelectableInfo, Unit>() { // from class: androidx.compose.foundation.text.selection.MultiSelectionLayout$createSubSelections$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SelectableInfo selectableInfo) {
                invoke2(selectableInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SelectableInfo info) {
                this.this$0.createAndPutSubSelection(mutableLongObjectMapMutableLongObjectMapOf, selection, info, 0, info.getTextLength());
            }
        });
        Selection.AnchorInfo maxAnchor = selection.getHandlesCrossed() ? selection.getStart() : selection.getEnd();
        createAndPutSubSelection(mutableLongObjectMapMutableLongObjectMapOf, selection, getLastInfo(), 0, maxAnchor.getOffset());
        return mutableLongObjectMapMutableLongObjectMapOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createAndPutSubSelection(MutableLongObjectMap<Selection> mutableLongObjectMap, Selection selection, SelectableInfo info, int minOffset, int maxOffset) {
        Selection subSelection;
        if (selection.getHandlesCrossed()) {
            subSelection = info.makeSingleLayoutSelection(maxOffset, minOffset);
        } else {
            subSelection = info.makeSingleLayoutSelection(minOffset, maxOffset);
        }
        if (!(minOffset <= maxOffset)) {
            throw new IllegalStateException(("minOffset should be less than or equal to maxOffset: " + subSelection).toString());
        }
        mutableLongObjectMap.put(info.getSelectableId(), subSelection);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("MultiSelectionLayout(isStartHandle=").append(getIsStartHandle()).append(", startPosition=").append((getStartSlot() + 1) / 2.0f).append(", endPosition=").append((getEndSlot() + 1) / 2.0f).append(", crossed=").append(getCrossStatus()).append(", infos=");
        StringBuilder $this$toString_u24lambda_u247 = new StringBuilder();
        $this$toString_u24lambda_u247.append("[\n\t");
        boolean first = true;
        List<SelectableInfo> list = this.infoList;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            SelectableInfo info = (SelectableInfo) item$iv;
            int index = index$iv;
            if (first) {
                first = false;
            } else {
                $this$toString_u24lambda_u247.append(",\n\t");
            }
            $this$toString_u24lambda_u247.append((index + 1) + " -> " + info);
        }
        $this$toString_u24lambda_u247.append("\n]");
        String string = $this$toString_u24lambda_u247.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return sbAppend.append(string).append(')').toString();
    }

    private final int startOrEndSlotToIndex(int slot, boolean isStartSlot) {
        boolean z = true;
        switch (WhenMappings.$EnumSwitchMapping$0[getCrossStatus().ordinal()]) {
            case 1:
                break;
            case 2:
                z = isStartSlot;
                break;
            case 3:
                if (isStartSlot) {
                    z = false;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return slotToIndex(slot, z);
    }

    private final int slotToIndex(int slot, boolean isMinimumSlot) {
        return (slot - (!isMinimumSlot ? 1 : 0)) / 2;
    }

    private final int getInfoListIndexBySelectableId(long id) {
        try {
            return this.selectableIdToInfoListIndex.get(id);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Invalid selectableId: " + id, e);
        }
    }
}
