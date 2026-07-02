package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0083\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0004HÂ\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateModifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateNode;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;)V", "component1", "copy", "create", "equals", "", "other", "", "hashCode", "", "toString", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class TraversablePrefetchStateModifierElement extends ModifierNodeElement<TraversablePrefetchStateNode> {
    private final LazyLayoutPrefetchState prefetchState;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    public static /* synthetic */ TraversablePrefetchStateModifierElement copy$default(TraversablePrefetchStateModifierElement traversablePrefetchStateModifierElement, LazyLayoutPrefetchState lazyLayoutPrefetchState, int i, Object obj) {
        if ((i & 1) != 0) {
            lazyLayoutPrefetchState = traversablePrefetchStateModifierElement.prefetchState;
        }
        return traversablePrefetchStateModifierElement.copy(lazyLayoutPrefetchState);
    }

    public final TraversablePrefetchStateModifierElement copy(LazyLayoutPrefetchState prefetchState) {
        return new TraversablePrefetchStateModifierElement(prefetchState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TraversablePrefetchStateModifierElement) && Intrinsics.areEqual(this.prefetchState, ((TraversablePrefetchStateModifierElement) other).prefetchState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.prefetchState.hashCode();
    }

    public String toString() {
        return "TraversablePrefetchStateModifierElement(prefetchState=" + this.prefetchState + ')';
    }

    public TraversablePrefetchStateModifierElement(LazyLayoutPrefetchState prefetchState) {
        this.prefetchState = prefetchState;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TraversablePrefetchStateNode create() {
        return new TraversablePrefetchStateNode(this.prefetchState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TraversablePrefetchStateNode node) {
        node.setPrefetchState(this.prefetchState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("traversablePrefetchState");
        $this$inspectableProperties.setValue(this.prefetchState);
    }
}
