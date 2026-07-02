package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.unit.Density;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\"\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\bH\u0080@¢\u0006\u0004\b\"\u0010#J\u000e\u0010$\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010%J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0007J\u0006\u0010)\u001a\u00020\bJ\u0018\u0010*\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0080@¢\u0006\u0004\b+\u0010,R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\b8GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013¨\u0006."}, d2 = {"Landroidx/compose/material/BottomSheetState;", "", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "density", "Landroidx/compose/ui/unit/Density;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/ui/unit/Density;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "anchoredDraggableState", "Landroidx/compose/material/AnchoredDraggableState;", "getAnchoredDraggableState$material_release", "()Landroidx/compose/material/AnchoredDraggableState;", "currentValue", "getCurrentValue", "()Landroidx/compose/material/BottomSheetValue;", "isCollapsed", "()Z", "isExpanded", NotificationCompat.CATEGORY_PROGRESS, "getProgress$annotations", "()V", "getProgress", "()F", "targetValue", "getTargetValue", "animateTo", "", "target", "velocity", "animateTo$material_release", "(Landroidx/compose/material/BottomSheetValue;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collapse", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expand", Constants.MessagePayloadKeys.FROM, "to", "requireOffset", "snapTo", "snapTo$material_release", "(Landroidx/compose/material/BottomSheetValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState<BottomSheetValue> anchoredDraggableState;

    @Deprecated(message = "Please use the progress function to query progress explicitly between targets.", replaceWith = @ReplaceWith(expression = "progress(from = , to = )", imports = {}))
    public static /* synthetic */ void getProgress$annotations() {
    }

    public BottomSheetState(BottomSheetValue initialValue, final Density density, AnimationSpec<Float> animationSpec, Function1<? super BottomSheetValue, Boolean> function1) {
        this.anchoredDraggableState = new AnchoredDraggableState<>(initialValue, new Function1<Float, Float>() { // from class: androidx.compose.material.BottomSheetState$anchoredDraggableState$1
            {
                super(1);
            }

            public final Float invoke(float it) {
                Density $this$invoke_u24lambda_u240 = density;
                return Float.valueOf($this$invoke_u24lambda_u240.mo365toPx0680j_4(BottomSheetScaffoldKt.BottomSheetScaffoldPositionalThreshold));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        }, new Function0<Float>() { // from class: androidx.compose.material.BottomSheetState$anchoredDraggableState$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Density $this$invoke_u24lambda_u240 = density;
                return Float.valueOf($this$invoke_u24lambda_u240.mo365toPx0680j_4(BottomSheetScaffoldKt.BottomSheetScaffoldVelocityThreshold));
            }
        }, animationSpec, function1);
    }

    public /* synthetic */ BottomSheetState(BottomSheetValue bottomSheetValue, Density density, AnimationSpec animationSpec, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bottomSheetValue, density, (i & 4) != 0 ? BottomSheetScaffoldDefaults.INSTANCE.getAnimationSpec() : animationSpec, (i & 8) != 0 ? new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetState.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BottomSheetValue it) {
                return true;
            }
        } : anonymousClass1);
    }

    public final AnchoredDraggableState<BottomSheetValue> getAnchoredDraggableState$material_release() {
        return this.anchoredDraggableState;
    }

    public final BottomSheetValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final BottomSheetValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final boolean isExpanded() {
        return this.anchoredDraggableState.getCurrentValue() == BottomSheetValue.Expanded;
    }

    public final boolean isCollapsed() {
        return this.anchoredDraggableState.getCurrentValue() == BottomSheetValue.Collapsed;
    }

    public final float getProgress() {
        return this.anchoredDraggableState.getProgress();
    }

    public final float progress(BottomSheetValue from, BottomSheetValue to) {
        float fromOffset = this.anchoredDraggableState.getAnchors().positionOf(from);
        float toOffset = this.anchoredDraggableState.getAnchors().positionOf(to);
        float currentOffset = RangesKt.coerceIn(this.anchoredDraggableState.getOffset(), Math.min(fromOffset, toOffset), Math.max(fromOffset, toOffset));
        float fraction = (currentOffset - fromOffset) / (toOffset - fromOffset);
        if (Float.isNaN(fraction)) {
            return 1.0f;
        }
        return Math.abs(fraction);
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        BottomSheetValue bottomSheetValue;
        if (this.anchoredDraggableState.getAnchors().hasAnchorFor(BottomSheetValue.Expanded)) {
            bottomSheetValue = BottomSheetValue.Expanded;
        } else {
            bottomSheetValue = BottomSheetValue.Collapsed;
        }
        BottomSheetValue target = bottomSheetValue;
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, target, 0.0f, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final Object collapse(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, BottomSheetValue.Collapsed, 0.0f, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public static /* synthetic */ Object animateTo$material_release$default(BottomSheetState bottomSheetState, BottomSheetValue bottomSheetValue, float f, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            f = bottomSheetState.anchoredDraggableState.getLastVelocity();
        }
        return bottomSheetState.animateTo$material_release(bottomSheetValue, f, continuation);
    }

    public final Object animateTo$material_release(BottomSheetValue target, float velocity, Continuation<? super Unit> continuation) {
        Object objAnimateTo = AnchoredDraggableKt.animateTo(this.anchoredDraggableState, target, velocity, continuation);
        return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
    }

    public final Object snapTo$material_release(BottomSheetValue target, Continuation<? super Unit> continuation) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, target, continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/BottomSheetState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/BottomSheetState;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Landroidx/compose/material/BottomSheetValue;", "", "density", "Landroidx/compose/ui/unit/Density;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<BottomSheetState, ?> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> confirmStateChange, final Density density) {
            return SaverKt.Saver(new Function2<SaverScope, BottomSheetState, BottomSheetValue>() { // from class: androidx.compose.material.BottomSheetState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final BottomSheetValue invoke(SaverScope $this$Saver, BottomSheetState it) {
                    return it.getAnchoredDraggableState$material_release().getCurrentValue();
                }
            }, new Function1<BottomSheetValue, BottomSheetState>() { // from class: androidx.compose.material.BottomSheetState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final BottomSheetState invoke(BottomSheetValue it) {
                    return new BottomSheetState(it, density, animationSpec, confirmStateChange);
                }
            });
        }
    }
}
