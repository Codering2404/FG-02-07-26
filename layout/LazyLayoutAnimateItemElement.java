package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÂ\u0003J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004HÂ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÂ\u0003J?\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0001J\b\u0010\u000e\u001a\u00020\u0002H\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J\f\u0010\u001a\u001a\u00020\u0018*\u00020\u001bH\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;", "fadeInSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "placementSpec", "Landroidx/compose/ui/unit/IntOffset;", "fadeOutSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "component1", "component2", "component3", "copy", "create", "equals", "", "other", "", "hashCode", "", "toString", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class LazyLayoutAnimateItemElement extends ModifierNodeElement<LazyLayoutAnimationSpecsNode> {
    public static final int $stable = 0;
    private final FiniteAnimationSpec<Float> fadeInSpec;
    private final FiniteAnimationSpec<Float> fadeOutSpec;
    private final FiniteAnimationSpec<IntOffset> placementSpec;

    private final FiniteAnimationSpec<Float> component1() {
        return this.fadeInSpec;
    }

    private final FiniteAnimationSpec<IntOffset> component2() {
        return this.placementSpec;
    }

    private final FiniteAnimationSpec<Float> component3() {
        return this.fadeOutSpec;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LazyLayoutAnimateItemElement copy$default(LazyLayoutAnimateItemElement lazyLayoutAnimateItemElement, FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, FiniteAnimationSpec finiteAnimationSpec3, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = lazyLayoutAnimateItemElement.fadeInSpec;
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec2 = lazyLayoutAnimateItemElement.placementSpec;
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec3 = lazyLayoutAnimateItemElement.fadeOutSpec;
        }
        return lazyLayoutAnimateItemElement.copy(finiteAnimationSpec, finiteAnimationSpec2, finiteAnimationSpec3);
    }

    public final LazyLayoutAnimateItemElement copy(FiniteAnimationSpec<Float> fadeInSpec, FiniteAnimationSpec<IntOffset> placementSpec, FiniteAnimationSpec<Float> fadeOutSpec) {
        return new LazyLayoutAnimateItemElement(fadeInSpec, placementSpec, fadeOutSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LazyLayoutAnimateItemElement)) {
            return false;
        }
        LazyLayoutAnimateItemElement lazyLayoutAnimateItemElement = (LazyLayoutAnimateItemElement) other;
        return Intrinsics.areEqual(this.fadeInSpec, lazyLayoutAnimateItemElement.fadeInSpec) && Intrinsics.areEqual(this.placementSpec, lazyLayoutAnimateItemElement.placementSpec) && Intrinsics.areEqual(this.fadeOutSpec, lazyLayoutAnimateItemElement.fadeOutSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return ((((this.fadeInSpec == null ? 0 : this.fadeInSpec.hashCode()) * 31) + (this.placementSpec == null ? 0 : this.placementSpec.hashCode())) * 31) + (this.fadeOutSpec != null ? this.fadeOutSpec.hashCode() : 0);
    }

    public String toString() {
        return "LazyLayoutAnimateItemElement(fadeInSpec=" + this.fadeInSpec + ", placementSpec=" + this.placementSpec + ", fadeOutSpec=" + this.fadeOutSpec + ')';
    }

    public LazyLayoutAnimateItemElement(FiniteAnimationSpec<Float> finiteAnimationSpec, FiniteAnimationSpec<IntOffset> finiteAnimationSpec2, FiniteAnimationSpec<Float> finiteAnimationSpec3) {
        this.fadeInSpec = finiteAnimationSpec;
        this.placementSpec = finiteAnimationSpec2;
        this.fadeOutSpec = finiteAnimationSpec3;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public LazyLayoutAnimationSpecsNode getNode() {
        return new LazyLayoutAnimationSpecsNode(this.fadeInSpec, this.placementSpec, this.fadeOutSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(LazyLayoutAnimationSpecsNode node) {
        node.setFadeInSpec(this.fadeInSpec);
        node.setPlacementSpec(this.placementSpec);
        node.setFadeOutSpec(this.fadeOutSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("animateItem");
        $this$inspectableProperties.getProperties().set("fadeInSpec", this.fadeInSpec);
        $this$inspectableProperties.getProperties().set("placementSpec", this.placementSpec);
        $this$inspectableProperties.getProperties().set("fadeOutSpec", this.fadeOutSpec);
    }
}
