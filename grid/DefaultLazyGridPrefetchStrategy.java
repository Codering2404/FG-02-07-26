package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.runtime.collection.MutableVector;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LazyGridPrefetchStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u001c\u0010\u000f\u001a\u00020\f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0014\u0010\u0015\u001a\u00020\f*\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/grid/DefaultLazyGridPrefetchStrategy;", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchStrategy;", "nestedPrefetchItemCount", "", "(I)V", "currentLinePrefetchHandles", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineToPrefetch", "wasScrollingForward", "", "onNestedPrefetch", "", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "firstVisibleItemIndex", "onScroll", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "delta", "", "layoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "onVisibleItemsUpdated", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultLazyGridPrefetchStrategy implements LazyGridPrefetchStrategy {
    private final MutableVector<LazyLayoutPrefetchState.PrefetchHandle> currentLinePrefetchHandles;
    private int lineToPrefetch;
    private final int nestedPrefetchItemCount;
    private boolean wasScrollingForward;

    public DefaultLazyGridPrefetchStrategy() {
        this(0, 1, null);
    }

    public DefaultLazyGridPrefetchStrategy(int nestedPrefetchItemCount) {
        this.nestedPrefetchItemCount = nestedPrefetchItemCount;
        this.lineToPrefetch = -1;
        this.currentLinePrefetchHandles = new MutableVector<>(new LazyLayoutPrefetchState.PrefetchHandle[16], 0);
    }

    public /* synthetic */ DefaultLazyGridPrefetchStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onScroll(LazyGridPrefetchScope $this$onScroll, float delta, LazyGridLayoutInfo layoutInfo) {
        int lineToPrefetch;
        int closestNextItemToPrefetch;
        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector;
        int size$iv;
        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector2;
        int size$iv2;
        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector3;
        int size$iv3;
        if (layoutInfo.getVisibleItemsInfo().isEmpty()) {
            return;
        }
        boolean z = false;
        boolean scrollingForward = delta < 0.0f;
        if (scrollingForward) {
            LazyGridItemInfo it = (LazyGridItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo());
            lineToPrefetch = (layoutInfo.getOrientation() == Orientation.Vertical ? it.getRow() : it.getColumn()) + 1;
            closestNextItemToPrefetch = ((LazyGridItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo())).getIndex() + 1;
        } else {
            LazyGridItemInfo it2 = (LazyGridItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo());
            lineToPrefetch = (layoutInfo.getOrientation() == Orientation.Vertical ? it2.getRow() : it2.getColumn()) - 1;
            closestNextItemToPrefetch = ((LazyGridItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo())).getIndex() - 1;
        }
        if (closestNextItemToPrefetch >= 0 && closestNextItemToPrefetch < layoutInfo.getTotalItemsCount()) {
            z = true;
        }
        if (z) {
            if (lineToPrefetch != this.lineToPrefetch) {
                if (this.wasScrollingForward != scrollingForward && (size$iv3 = (mutableVector3 = this.currentLinePrefetchHandles).getSize()) > 0) {
                    int i$iv = 0;
                    Object[] content$iv = mutableVector3.getContent();
                    do {
                        ((LazyLayoutPrefetchState.PrefetchHandle) content$iv[i$iv]).cancel();
                        i$iv++;
                    } while (i$iv < size$iv3);
                }
                this.wasScrollingForward = scrollingForward;
                this.lineToPrefetch = lineToPrefetch;
                this.currentLinePrefetchHandles.clear();
                MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector4 = this.currentLinePrefetchHandles;
                mutableVector4.addAll(mutableVector4.getSize(), (List<? extends LazyLayoutPrefetchState.PrefetchHandle>) $this$onScroll.scheduleLinePrefetch(lineToPrefetch));
            }
            if (!scrollingForward) {
                LazyGridItemInfo firstItem = (LazyGridItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo());
                int distanceToPrefetchItem = layoutInfo.getViewportStartOffset() - LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(firstItem, layoutInfo.getOrientation());
                if (distanceToPrefetchItem >= delta || (size$iv = (mutableVector = this.currentLinePrefetchHandles).getSize()) <= 0) {
                    return;
                }
                int i$iv2 = 0;
                Object[] content$iv2 = mutableVector.getContent();
                do {
                    ((LazyLayoutPrefetchState.PrefetchHandle) content$iv2[i$iv2]).markAsUrgent();
                    i$iv2++;
                } while (i$iv2 < size$iv);
                return;
            }
            LazyGridItemInfo lastItem = (LazyGridItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo());
            int itemSize = LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis(lastItem, layoutInfo.getOrientation());
            int itemSpacing = layoutInfo.getMainAxisItemSpacing();
            int distanceToPrefetchItem2 = ((LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(lastItem, layoutInfo.getOrientation()) + itemSize) + itemSpacing) - layoutInfo.getViewportEndOffset();
            if (distanceToPrefetchItem2 >= (-delta) || (size$iv2 = (mutableVector2 = this.currentLinePrefetchHandles).getSize()) <= 0) {
                return;
            }
            int i$iv3 = 0;
            Object[] content$iv3 = mutableVector2.getContent();
            do {
                ((LazyLayoutPrefetchState.PrefetchHandle) content$iv3[i$iv3]).markAsUrgent();
                i$iv3++;
            } while (i$iv3 < size$iv2);
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onVisibleItemsUpdated(LazyGridPrefetchScope $this$onVisibleItemsUpdated, LazyGridLayoutInfo layoutInfo) {
        int expectedLineToPrefetch;
        if (this.lineToPrefetch != -1 && !layoutInfo.getVisibleItemsInfo().isEmpty()) {
            if (this.wasScrollingForward) {
                LazyGridItemInfo it = (LazyGridItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo());
                expectedLineToPrefetch = (layoutInfo.getOrientation() == Orientation.Vertical ? it.getRow() : it.getColumn()) + 1;
            } else {
                LazyGridItemInfo it2 = (LazyGridItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo());
                expectedLineToPrefetch = (layoutInfo.getOrientation() == Orientation.Vertical ? it2.getRow() : it2.getColumn()) - 1;
            }
            if (this.lineToPrefetch != expectedLineToPrefetch) {
                this.lineToPrefetch = -1;
                MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector = this.currentLinePrefetchHandles;
                int size$iv = mutableVector.getSize();
                if (size$iv > 0) {
                    int i$iv = 0;
                    Object[] content$iv = mutableVector.getContent();
                    do {
                        ((LazyLayoutPrefetchState.PrefetchHandle) content$iv[i$iv]).cancel();
                        i$iv++;
                    } while (i$iv < size$iv);
                }
                this.currentLinePrefetchHandles.clear();
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope $this$onNestedPrefetch, int firstVisibleItemIndex) {
        int i = this.nestedPrefetchItemCount;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            $this$onNestedPrefetch.schedulePrefetch(firstVisibleItemIndex + i3);
        }
    }
}
