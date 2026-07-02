package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharedElement.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0013\u0010a\u001a\u00020bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bc\u00101J\u0010\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u0016J\b\u0010h\u001a\u00020eH\u0016J\b\u0010i\u001a\u00020eH\u0016J\b\u0010j\u001a\u00020eH\u0016R+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR/\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0013\u001a\u0004\u0018\u00010 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\u0019\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0017\u0010.\u001a\u00020/8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b0\u00101R+\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\u0019\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00107\u001a\u0004\u0018\u00010\u0000X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R+\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010\u0019\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R+\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u0019\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR+\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010\u0019\u001a\u0004\bF\u0010B\"\u0004\bG\u0010DR+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010\u0019\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bO\u0010BR\u0014\u0010P\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010BR\u0011\u0010R\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bS\u0010BR\u0011\u0010T\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bU\u0010BR+\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bZ\u0010\u0019\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR+\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00118V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b_\u0010`\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006k"}, d2 = {"Landroidx/compose/animation/SharedElementInternalState;", "Landroidx/compose/animation/LayerRenderer;", "Landroidx/compose/runtime/RememberObserver;", "sharedElement", "Landroidx/compose/animation/SharedElement;", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeHolderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;", "renderOnlyWhenVisible", "", "overlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "renderInOverlayDuringTransition", "userState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "zIndex", "", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;ZLandroidx/compose/animation/SharedTransitionScope$OverlayClip;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;F)V", "<set-?>", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "setBoundsAnimation", "(Landroidx/compose/animation/BoundsAnimation;)V", "boundsAnimation$delegate", "Landroidx/compose/runtime/MutableState;", "clipPathInOverlay", "Landroidx/compose/ui/graphics/Path;", "getClipPathInOverlay$animation_release", "()Landroidx/compose/ui/graphics/Path;", "setClipPathInOverlay$animation_release", "(Landroidx/compose/ui/graphics/Path;)V", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "layer$delegate", "lookaheadCoords", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLookaheadCoords", "()Lkotlin/jvm/functions/Function0;", "setLookaheadCoords", "(Lkotlin/jvm/functions/Function0;)V", "nonNullLookaheadSize", "Landroidx/compose/ui/geometry/Size;", "getNonNullLookaheadSize-NH-jbRc", "()J", "getOverlayClip", "()Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "setOverlayClip", "(Landroidx/compose/animation/SharedTransitionScope$OverlayClip;)V", "overlayClip$delegate", "parentState", "getParentState", "()Landroidx/compose/animation/SharedElementInternalState;", "setParentState", "(Landroidx/compose/animation/SharedElementInternalState;)V", "getPlaceHolderSize", "()Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;", "setPlaceHolderSize", "(Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;)V", "placeHolderSize$delegate", "getRenderInOverlayDuringTransition", "()Z", "setRenderInOverlayDuringTransition", "(Z)V", "renderInOverlayDuringTransition$delegate", "getRenderOnlyWhenVisible", "setRenderOnlyWhenVisible", "renderOnlyWhenVisible$delegate", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "setSharedElement", "(Landroidx/compose/animation/SharedElement;)V", "sharedElement$delegate", "shouldRenderBasedOnTarget", "getShouldRenderBasedOnTarget", "shouldRenderInOverlay", "getShouldRenderInOverlay$animation_release", "shouldRenderInPlace", "getShouldRenderInPlace", "target", "getTarget", "getUserState", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "setUserState", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;)V", "userState$delegate", "getZIndex", "()F", "setZIndex", "(F)V", "zIndex$delegate", "Landroidx/compose/runtime/MutableFloatState;", "calculateLookaheadOffset", "Landroidx/compose/ui/geometry/Offset;", "calculateLookaheadOffset-F1C5BW0", "drawInOverlay", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "onAbandoned", "onForgotten", "onRemembered", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SharedElementInternalState implements LayerRenderer, RememberObserver {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: boundsAnimation$delegate, reason: from kotlin metadata */
    private final MutableState boundsAnimation;
    private Path clipPathInOverlay;

    /* JADX INFO: renamed from: overlayClip$delegate, reason: from kotlin metadata */
    private final MutableState overlayClip;
    private SharedElementInternalState parentState;

    /* JADX INFO: renamed from: placeHolderSize$delegate, reason: from kotlin metadata */
    private final MutableState placeHolderSize;

    /* JADX INFO: renamed from: renderInOverlayDuringTransition$delegate, reason: from kotlin metadata */
    private final MutableState renderInOverlayDuringTransition;

    /* JADX INFO: renamed from: renderOnlyWhenVisible$delegate, reason: from kotlin metadata */
    private final MutableState renderOnlyWhenVisible;

    /* JADX INFO: renamed from: sharedElement$delegate, reason: from kotlin metadata */
    private final MutableState sharedElement;

    /* JADX INFO: renamed from: userState$delegate, reason: from kotlin metadata */
    private final MutableState userState;

    /* JADX INFO: renamed from: zIndex$delegate, reason: from kotlin metadata */
    private final MutableFloatState zIndex;
    private Function0<? extends LayoutCoordinates> lookaheadCoords = new Function0() { // from class: androidx.compose.animation.SharedElementInternalState$lookaheadCoords$1
        @Override // kotlin.jvm.functions.Function0
        public final Void invoke() {
            return null;
        }
    };

    /* JADX INFO: renamed from: layer$delegate, reason: from kotlin metadata */
    private final MutableState layer = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public SharedElementInternalState(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderOnlyWhenVisible, SharedTransitionScope.OverlayClip overlayClip, boolean renderInOverlayDuringTransition, SharedTransitionScope.SharedContentState userState, float zIndex) {
        this.zIndex = PrimitiveSnapshotStateKt.mutableFloatStateOf(zIndex);
        this.renderInOverlayDuringTransition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(renderInOverlayDuringTransition), null, 2, null);
        this.sharedElement = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(sharedElement, null, 2, null);
        this.boundsAnimation = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(boundsAnimation, null, 2, null);
        this.placeHolderSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(placeHolderSize, null, 2, null);
        this.renderOnlyWhenVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(renderOnlyWhenVisible), null, 2, null);
        this.overlayClip = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(overlayClip, null, 2, null);
        this.userState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(userState, null, 2, null);
    }

    @Override // androidx.compose.animation.LayerRenderer
    public float getZIndex() {
        FloatState $this$getValue$iv = this.zIndex;
        return $this$getValue$iv.getFloatValue();
    }

    public void setZIndex(float f) {
        MutableFloatState $this$setValue$iv = this.zIndex;
        $this$setValue$iv.setFloatValue(f);
    }

    public final boolean getRenderInOverlayDuringTransition() {
        State $this$getValue$iv = this.renderInOverlayDuringTransition;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setRenderInOverlayDuringTransition(boolean z) {
        MutableState $this$setValue$iv = this.renderInOverlayDuringTransition;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final SharedElement getSharedElement() {
        State $this$getValue$iv = this.sharedElement;
        return (SharedElement) $this$getValue$iv.getValue();
    }

    public final void setSharedElement(SharedElement sharedElement) {
        MutableState $this$setValue$iv = this.sharedElement;
        $this$setValue$iv.setValue(sharedElement);
    }

    public final BoundsAnimation getBoundsAnimation() {
        State $this$getValue$iv = this.boundsAnimation;
        return (BoundsAnimation) $this$getValue$iv.getValue();
    }

    public final void setBoundsAnimation(BoundsAnimation boundsAnimation) {
        MutableState $this$setValue$iv = this.boundsAnimation;
        $this$setValue$iv.setValue(boundsAnimation);
    }

    public final SharedTransitionScope.PlaceHolderSize getPlaceHolderSize() {
        State $this$getValue$iv = this.placeHolderSize;
        return (SharedTransitionScope.PlaceHolderSize) $this$getValue$iv.getValue();
    }

    public final void setPlaceHolderSize(SharedTransitionScope.PlaceHolderSize placeHolderSize) {
        MutableState $this$setValue$iv = this.placeHolderSize;
        $this$setValue$iv.setValue(placeHolderSize);
    }

    public final boolean getRenderOnlyWhenVisible() {
        State $this$getValue$iv = this.renderOnlyWhenVisible;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setRenderOnlyWhenVisible(boolean z) {
        MutableState $this$setValue$iv = this.renderOnlyWhenVisible;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final SharedTransitionScope.OverlayClip getOverlayClip() {
        State $this$getValue$iv = this.overlayClip;
        return (SharedTransitionScope.OverlayClip) $this$getValue$iv.getValue();
    }

    public final void setOverlayClip(SharedTransitionScope.OverlayClip overlayClip) {
        MutableState $this$setValue$iv = this.overlayClip;
        $this$setValue$iv.setValue(overlayClip);
    }

    public final SharedTransitionScope.SharedContentState getUserState() {
        State $this$getValue$iv = this.userState;
        return (SharedTransitionScope.SharedContentState) $this$getValue$iv.getValue();
    }

    public final void setUserState(SharedTransitionScope.SharedContentState sharedContentState) {
        MutableState $this$setValue$iv = this.userState;
        $this$setValue$iv.setValue(sharedContentState);
    }

    /* JADX INFO: renamed from: getClipPathInOverlay$animation_release, reason: from getter */
    public final Path getClipPathInOverlay() {
        return this.clipPathInOverlay;
    }

    public final void setClipPathInOverlay$animation_release(Path path) {
        this.clipPathInOverlay = path;
    }

    @Override // androidx.compose.animation.LayerRenderer
    public void drawInOverlay(DrawScope drawScope) throws Throwable {
        DrawScope $this$drawInOverlay_u24lambda_u245;
        GraphicsLayer layer = getLayer();
        if (layer != null && getShouldRenderInOverlay$animation_release()) {
            if (getSharedElement().getCurrentBounds() == null) {
                throw new IllegalArgumentException("Error: current bounds not set yet.".toString());
            }
            Rect currentBounds = getSharedElement().getCurrentBounds();
            Unit unit = null;
            Offset offsetM3910boximpl = currentBounds != null ? Offset.m3910boximpl(currentBounds.m3956getTopLeftF1C5BW0()) : null;
            Intrinsics.checkNotNull(offsetM3910boximpl);
            long packedValue = offsetM3910boximpl.getPackedValue();
            float fM3921getXimpl = Offset.m3921getXimpl(packedValue);
            float fM3922getYimpl = Offset.m3922getYimpl(packedValue);
            Path it = this.clipPathInOverlay;
            if (it == null) {
                $this$drawInOverlay_u24lambda_u245 = drawScope;
            } else {
                int clipOp$iv = ClipOp.INSTANCE.m4151getIntersectrtfAjoo();
                DrawContext $this$withTransform_u24lambda_u246$iv$iv = drawScope.getDrawContext();
                $this$drawInOverlay_u24lambda_u245 = drawScope;
                long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo4633getSizeNHjbRc();
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$clipPath_KD09W0M_u24lambda_u245$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                    $this$clipPath_KD09W0M_u24lambda_u245$iv.mo4635clipPathmtrdDE(it, clipOp$iv);
                    drawScope.getDrawContext().getTransform().translate(fM3921getXimpl, fM3922getYimpl);
                    try {
                        try {
                            GraphicsLayerKt.drawLayer(drawScope, layer);
                            drawScope.getDrawContext().getTransform().translate(-fM3921getXimpl, -fM3922getYimpl);
                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                            $this$withTransform_u24lambda_u246$iv$iv.mo4634setSizeuvyYCjk(previousSize$iv$iv);
                            unit = Unit.INSTANCE;
                        } catch (Throwable th) {
                            drawScope.getDrawContext().getTransform().translate(-fM3921getXimpl, -fM3922getYimpl);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv.mo4634setSizeuvyYCjk(previousSize$iv$iv);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (unit == null) {
                DrawScope $this$translate$iv = $this$drawInOverlay_u24lambda_u245;
                $this$translate$iv.getDrawContext().getTransform().translate(fM3921getXimpl, fM3922getYimpl);
                try {
                    GraphicsLayerKt.drawLayer($this$translate$iv, layer);
                } finally {
                    $this$translate$iv.getDrawContext().getTransform().translate(-fM3921getXimpl, -fM3922getYimpl);
                }
            }
        }
    }

    /* JADX INFO: renamed from: getNonNullLookaheadSize-NH-jbRc, reason: not valid java name */
    public final long m101getNonNullLookaheadSizeNHjbRc() {
        LayoutCoordinates layoutCoordinatesInvoke = this.lookaheadCoords.invoke();
        if (layoutCoordinatesInvoke == null) {
            throw new IllegalArgumentException(("Error: lookahead coordinates is null for " + getSharedElement().getKey() + '.').toString());
        }
        return IntSizeKt.m6807toSizeozmzZPI(layoutCoordinatesInvoke.mo5498getSizeYbymL2g());
    }

    public final Function0<LayoutCoordinates> getLookaheadCoords() {
        return this.lookaheadCoords;
    }

    public final void setLookaheadCoords(Function0<? extends LayoutCoordinates> function0) {
        this.lookaheadCoords = function0;
    }

    @Override // androidx.compose.animation.LayerRenderer
    public SharedElementInternalState getParentState() {
        return this.parentState;
    }

    public void setParentState(SharedElementInternalState sharedElementInternalState) {
        this.parentState = sharedElementInternalState;
    }

    /* JADX INFO: renamed from: calculateLookaheadOffset-F1C5BW0, reason: not valid java name */
    public final long m100calculateLookaheadOffsetF1C5BW0() {
        LayoutCoordinates layoutCoordinatesInvoke = this.lookaheadCoords.invoke();
        if (layoutCoordinatesInvoke == null) {
            throw new IllegalArgumentException("Error: lookahead coordinates is null.".toString());
        }
        LayoutCoordinates c = layoutCoordinatesInvoke;
        return getSharedElement().getScope().getLookaheadRoot$animation_release().mo5499localPositionOfR5De75A(c, Offset.INSTANCE.m3937getZeroF1C5BW0());
    }

    public final boolean getTarget() {
        return getBoundsAnimation().getTarget();
    }

    public final GraphicsLayer getLayer() {
        State $this$getValue$iv = this.layer;
        return (GraphicsLayer) $this$getValue$iv.getValue();
    }

    public final void setLayer(GraphicsLayer graphicsLayer) {
        MutableState $this$setValue$iv = this.layer;
        $this$setValue$iv.setValue(graphicsLayer);
    }

    private final boolean getShouldRenderBasedOnTarget() {
        return Intrinsics.areEqual(getSharedElement().getTargetBoundsProvider(), this) || !getRenderOnlyWhenVisible();
    }

    public final boolean getShouldRenderInOverlay$animation_release() {
        return getShouldRenderBasedOnTarget() && getSharedElement().getFoundMatch() && getRenderInOverlayDuringTransition();
    }

    public final boolean getShouldRenderInPlace() {
        return !getSharedElement().getFoundMatch() || (!getShouldRenderInOverlay$animation_release() && getShouldRenderBasedOnTarget());
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        getSharedElement().getScope().onStateAdded$animation_release(this);
        getSharedElement().updateTargetBoundsProvider();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        getSharedElement().getScope().onStateRemoved$animation_release(this);
        getSharedElement().updateTargetBoundsProvider();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
    }
}
