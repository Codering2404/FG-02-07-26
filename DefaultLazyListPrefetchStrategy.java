package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LazyListPrefetchStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0016J\u001c\u0010\u000e\u001a\u00020\u000b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\u000b*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/lazy/DefaultLazyListPrefetchStrategy;", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "nestedPrefetchItemCount", "", "(I)V", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "indexToPrefetch", "wasScrollingForward", "", "onNestedPrefetch", "", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "firstVisibleItemIndex", "onScroll", "Landroidx/compose/foundation/lazy/LazyListPrefetchScope;", "delta", "", "layoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "onVisibleItemsUpdated", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultLazyListPrefetchStrategy implements LazyListPrefetchStrategy {
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private int indexToPrefetch;
    private final int nestedPrefetchItemCount;
    private boolean wasScrollingForward;

    public DefaultLazyListPrefetchStrategy() {
        this(0, 1, null);
    }

    public DefaultLazyListPrefetchStrategy(int nestedPrefetchItemCount) {
        this.nestedPrefetchItemCount = nestedPrefetchItemCount;
        this.indexToPrefetch = -1;
    }

    public /* synthetic */ DefaultLazyListPrefetchStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onScroll(LazyListPrefetchScope $this$onScroll, float delta, LazyListLayoutInfo layoutInfo) {
        int indexToPrefetch;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle3;
        if (!layoutInfo.getVisibleItemsInfo().isEmpty()) {
            boolean z = false;
            boolean scrollingForward = delta < 0.0f;
            if (scrollingForward) {
                indexToPrefetch = ((LazyListItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo())).getIndex() + 1;
            } else {
                indexToPrefetch = ((LazyListItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo())).getIndex() - 1;
            }
            if (indexToPrefetch >= 0 && indexToPrefetch < layoutInfo.getTotalItemsCount()) {
                z = true;
            }
            if (z) {
                if (indexToPrefetch != this.indexToPrefetch) {
                    if (this.wasScrollingForward != scrollingForward && (prefetchHandle3 = this.currentPrefetchHandle) != null) {
                        prefetchHandle3.cancel();
                    }
                    this.wasScrollingForward = scrollingForward;
                    this.indexToPrefetch = indexToPrefetch;
                    this.currentPrefetchHandle = $this$onScroll.schedulePrefetch(indexToPrefetch);
                }
                if (scrollingForward) {
                    LazyListItemInfo lastItem = (LazyListItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo());
                    int spacing = layoutInfo.getMainAxisItemSpacing();
                    int distanceToPrefetchItem = ((lastItem.getOffset() + lastItem.getSize()) + spacing) - layoutInfo.getViewportEndOffset();
                    if (distanceToPrefetchItem >= (-delta) || (prefetchHandle2 = this.currentPrefetchHandle) == null) {
                        return;
                    }
                    prefetchHandle2.markAsUrgent();
                    return;
                }
                LazyListItemInfo firstItem = (LazyListItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo());
                int distanceToPrefetchItem2 = layoutInfo.getViewportStartOffset() - firstItem.getOffset();
                if (distanceToPrefetchItem2 >= delta || (prefetchHandle = this.currentPrefetchHandle) == null) {
                    return;
                }
                prefetchHandle.markAsUrgent();
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onVisibleItemsUpdated(LazyListPrefetchScope $this$onVisibleItemsUpdated, LazyListLayoutInfo layoutInfo) {
        int expectedPrefetchIndex;
        if (this.indexToPrefetch != -1 && !layoutInfo.getVisibleItemsInfo().isEmpty()) {
            if (this.wasScrollingForward) {
                expectedPrefetchIndex = ((LazyListItemInfo) CollectionsKt.last((List) layoutInfo.getVisibleItemsInfo())).getIndex() + 1;
            } else {
                expectedPrefetchIndex = ((LazyListItemInfo) CollectionsKt.first((List) layoutInfo.getVisibleItemsInfo())).getIndex() - 1;
            }
            if (this.indexToPrefetch != expectedPrefetchIndex) {
                this.indexToPrefetch = -1;
                LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
                if (prefetchHandle != null) {
                    prefetchHandle.cancel();
                }
                this.currentPrefetchHandle = null;
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope $this$onNestedPrefetch, int firstVisibleItemIndex) {
        int i = this.nestedPrefetchItemCount;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            $this$onNestedPrefetch.schedulePrefetch(firstVisibleItemIndex + i3);
        }
    }
}
