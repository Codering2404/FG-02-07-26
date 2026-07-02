package androidx.compose.foundation.gestures;

import androidx.collection.MutableObjectFloatMap;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputChange;
import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u001d\u0010\t\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\n\u0012\u0004\u0012\u00020\u000b0\u0001¢\u0006\u0002\b\fH\u0007\u001a\u0017\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082\b\u001a\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0012\"\u0004\b\u0000\u0010\u0007H\u0002\u001aF\u0010\u0013\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u000f2\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0018\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0017H\u0082@¢\u0006\u0002\u0010\u0019\u001aT\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u0007*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0003H\u0007\u001a\\\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u0007*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0003H\u0007\u001a&\u0010'\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u0007H\u0087@¢\u0006\u0002\u0010)\u001aD\u0010'\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010/\u001a\u0002H\u0007H\u0082@¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020+\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u00072\u0006\u0010*\u001a\u00020+H\u0087@¢\u0006\u0002\u00102\u001a\u0014\u00103\u001a\u00020+*\u00020+2\u0006\u00104\u001a\u00020+H\u0002\u001a\u0018\u00105\u001a\u00020+\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607H\u0002\u001a\u0018\u00108\u001a\u00020+\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607H\u0002\u001a&\u00109\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u001d2\u0006\u0010(\u001a\u0002H\u0007H\u0087@¢\u0006\u0002\u0010)\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"AlwaysDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "DEBUG", "DraggableAnchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "builder", "Landroidx/compose/foundation/gestures/DraggableAnchorsConfig;", "", "Lkotlin/ExtensionFunctionType;", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "emptyDraggableAnchors", "Landroidx/compose/foundation/gestures/MapDraggableAnchors;", "restartable", "I", "inputs", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDraggable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "startDragImmediately", "reverseDirection", "animateTo", "targetValue", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "velocity", "", "anchoredDragScope", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "latestTarget", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;FLandroidx/compose/foundation/gestures/AnchoredDragScope;Landroidx/compose/foundation/gestures/DraggableAnchors;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToWithDecay", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceToTarget", "target", "maxValueOrNaN", "K", "Landroidx/collection/ObjectFloatMap;", "minValueOrNaN", "snapTo", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableKt {
    private static final Function1<PointerInputChange, Boolean> AlwaysDrag = new Function1<PointerInputChange, Boolean>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$AlwaysDrag$1
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(PointerInputChange it) {
            return true;
        }
    };
    private static final boolean DEBUG = false;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt", f = "AnchoredDraggable.kt", i = {0, 0}, l = {1049}, m = "animateToWithDecay", n = {"remainingVelocity", "velocity"}, s = {"L$0", "F$0"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        float F$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnchoredDraggableKt.animateToWithDecay(null, null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt", f = "AnchoredDraggable.kt", i = {}, l = {1116}, m = "restartable", n = {}, s = {})
    static final class C02651<I> extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02651(Continuation<? super C02651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnchoredDraggableKt.restartable(null, null, this);
        }
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, boolean reverseDirection, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, Boolean.valueOf(reverseDirection), interactionSource, startDragImmediately, overscrollEffect));
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, null, interactionSource, startDragImmediately, overscrollEffect));
    }

    public static final <T> DraggableAnchors<T> DraggableAnchors(Function1<? super DraggableAnchorsConfig<T>, Unit> function1) {
        DraggableAnchorsConfig draggableAnchorsConfig = new DraggableAnchorsConfig();
        function1.invoke(draggableAnchorsConfig);
        return new MapDraggableAnchors(draggableAnchorsConfig.getAnchors$foundation_release());
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$snapTo$2", f = "AnchoredDraggable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02672<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        C02672(Continuation<? super C02672> continuation) {
            super(4, continuation);
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            C02672 c02672 = new C02672(continuation);
            c02672.L$0 = anchoredDragScope;
            c02672.L$1 = draggableAnchors;
            c02672.L$2 = t;
            return c02672.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    float targetOffset = draggableAnchors.positionOf(latestTarget);
                    if (!Float.isNaN(targetOffset)) {
                        AnchoredDragScope.dragTo$default($this$anchoredDrag, targetOffset, 0.0f, 2, null);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final <T> Object snapTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new C02672(null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object animateTo(androidx.compose.foundation.gestures.AnchoredDraggableState<T> r9, float r10, final androidx.compose.foundation.gestures.AnchoredDragScope r11, androidx.compose.foundation.gestures.DraggableAnchors<T> r12, T r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r0 = r11
            r1 = 0
            float r3 = r12.positionOf(r13)
            kotlin.jvm.internal.Ref$FloatRef r2 = new kotlin.jvm.internal.Ref$FloatRef
            r2.<init>()
            r8 = r2
            float r2 = r9.getOffset()
            boolean r2 = java.lang.Float.isNaN(r2)
            if (r2 == 0) goto L18
            r2 = 0
            goto L1c
        L18:
            float r2 = r9.getOffset()
        L1c:
            r8.element = r2
            boolean r2 = java.lang.Float.isNaN(r3)
            if (r2 != 0) goto L4d
            float r2 = r8.element
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L2c
            r2 = 1
            goto L2d
        L2c:
            r2 = 0
        L2d:
            if (r2 != 0) goto L4d
            r2 = 0
            float r2 = r8.element
            androidx.compose.animation.core.AnimationSpec r5 = r9.getSnapAnimationSpec()
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$2$2 r4 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$2$2
            r4.<init>()
            r6 = r4
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r4 = r10
            r7 = r14
            java.lang.Object r10 = androidx.compose.animation.core.SuspendAnimationKt.animate(r2, r3, r4, r5, r6, r7)
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r10 != r14) goto L4f
            return r10
        L4d:
            r4 = r10
            r7 = r14
        L4f:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.animateTo(androidx.compose.foundation.gestures.AnchoredDraggableState, float, androidx.compose.foundation.gestures.AnchoredDragScope, androidx.compose.foundation.gestures.DraggableAnchors, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$4, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$4", f = "AnchoredDraggable.kt", i = {}, l = {1022}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnchoredDraggableState<T> $this_animateTo;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super AnonymousClass4> continuation) {
            super(4, continuation);
            this.$this_animateTo = anchoredDraggableState;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$this_animateTo, continuation);
            anonymousClass4.L$0 = anchoredDragScope;
            anonymousClass4.L$1 = draggableAnchors;
            anonymousClass4.L$2 = t;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors anchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    if (AnchoredDraggableKt.animateTo(this.$this_animateTo, this.$this_animateTo.getLastVelocity(), $this$anchoredDrag, anchors, latestTarget, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnonymousClass4(anchoredDraggableState, null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object animateToWithDecay(androidx.compose.foundation.gestures.AnchoredDraggableState<T> r8, T r9, float r10, kotlin.coroutines.Continuation<? super java.lang.Float> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.AnchoredDraggableKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1
            r0.<init>(r11)
        L19:
            r5 = r0
            java.lang.Object r11 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            float r8 = r5.F$0
            java.lang.Object r9 = r5.L$0
            kotlin.jvm.internal.Ref$FloatRef r9 = (kotlin.jvm.internal.Ref.FloatRef) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5e
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r8
            r8 = r10
            r2 = r9
            kotlin.jvm.internal.Ref$FloatRef r9 = new kotlin.jvm.internal.Ref$FloatRef
            r9.<init>()
            r9.element = r8
            androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2 r10 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2
            r3 = 0
            r10.<init>(r1, r8, r9, r3)
            r4 = r10
            kotlin.jvm.functions.Function4 r4 = (kotlin.jvm.functions.Function4) r4
            r5.L$0 = r9
            r5.F$0 = r8
            r10 = 1
            r5.label = r10
            r6 = 2
            r7 = 0
            java.lang.Object r10 = androidx.compose.foundation.gestures.AnchoredDraggableState.anchoredDrag$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L5e
            return r0
        L5e:
            float r10 = r9.element
            float r10 = r8 - r10
            java.lang.Float r10 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.animateToWithDecay(androidx.compose.foundation.gestures.AnchoredDraggableState, java.lang.Object, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2", f = "AnchoredDraggable.kt", i = {}, l = {1059, 1077, 1092}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $remainingVelocity;
        final /* synthetic */ AnchoredDraggableState<T> $this_animateToWithDecay;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AnchoredDraggableState<T> anchoredDraggableState, float f, Ref.FloatRef floatRef, Continuation<? super AnonymousClass2> continuation) {
            super(4, continuation);
            this.$this_animateToWithDecay = anchoredDraggableState;
            this.$velocity = f;
            this.$remainingVelocity = floatRef;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_animateToWithDecay, this.$velocity, this.$remainingVelocity, continuation);
            anonymousClass2.L$0 = anchoredDragScope;
            anonymousClass2.L$1 = draggableAnchors;
            anonymousClass2.L$2 = t;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass2<T> anonymousClass2;
            Object $result2;
            AnonymousClass2<T> anonymousClass22;
            Object $result3;
            AnonymousClass2 anonymousClass23;
            Object $result4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    final float targetOffset = draggableAnchors.positionOf(latestTarget);
                    if (!Float.isNaN(targetOffset)) {
                        final Ref.FloatRef prev = new Ref.FloatRef();
                        prev.element = Float.isNaN(this.$this_animateToWithDecay.getOffset()) ? 0.0f : this.$this_animateToWithDecay.getOffset();
                        boolean canDecayToTarget = false;
                        if (!(prev.element == targetOffset)) {
                            if (this.$velocity * (targetOffset - prev.element) >= 0.0f) {
                                if (!(this.$velocity == 0.0f)) {
                                    float projectedDecayOffset = DecayAnimationSpecKt.calculateTargetValue(this.$this_animateToWithDecay.getDecayAnimationSpec(), prev.element, this.$velocity);
                                    if (this.$velocity > 0.0f) {
                                        if (projectedDecayOffset >= targetOffset) {
                                            canDecayToTarget = true;
                                        }
                                    } else if (projectedDecayOffset <= targetOffset) {
                                        canDecayToTarget = true;
                                    }
                                    if (canDecayToTarget) {
                                        AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(prev.element, this.$velocity, 0L, 0L, false, 28, null);
                                        DecayAnimationSpec<Float> decayAnimationSpec = this.$this_animateToWithDecay.getDecayAnimationSpec();
                                        final Ref.FloatRef floatRef = this.$remainingVelocity;
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 2;
                                        if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default, decayAnimationSpec, false, new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt.animateToWithDecay.2.3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                                                invoke2(animationScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(AnimationScope<Float, AnimationVector1D> animationScope) {
                                                if (Math.abs(animationScope.getValue().floatValue()) >= Math.abs(targetOffset)) {
                                                    float finalValue = AnchoredDraggableKt.coerceToTarget(animationScope.getValue().floatValue(), targetOffset);
                                                    $this$anchoredDrag.dragTo(finalValue, animationScope.getVelocity().floatValue());
                                                    floatRef.element = Float.isNaN(animationScope.getVelocity().floatValue()) ? 0.0f : animationScope.getVelocity().floatValue();
                                                    prev.element = finalValue;
                                                    animationScope.cancelAnimation();
                                                    return;
                                                }
                                                $this$anchoredDrag.dragTo(animationScope.getValue().floatValue(), animationScope.getVelocity().floatValue());
                                                floatRef.element = animationScope.getVelocity().floatValue();
                                                prev.element = animationScope.getValue().floatValue();
                                            }
                                        }, this, 2, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        anonymousClass23 = this;
                                        $result4 = $result;
                                    } else {
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 3;
                                        if (AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, $this$anchoredDrag, draggableAnchors, latestTarget, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        anonymousClass22 = this;
                                        $result3 = $result;
                                        anonymousClass22.$remainingVelocity.element = 0.0f;
                                    }
                                }
                            }
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 1;
                            if (AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, $this$anchoredDrag, draggableAnchors, latestTarget, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = this;
                            $result2 = $result;
                            anonymousClass2.$remainingVelocity.element = 0.0f;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass2 = this;
                    $result2 = $result;
                    ResultKt.throwOnFailure($result2);
                    anonymousClass2.$remainingVelocity.element = 0.0f;
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass23 = this;
                    $result4 = $result;
                    ResultKt.throwOnFailure($result4);
                    return Unit.INSTANCE;
                case 3:
                    anonymousClass22 = this;
                    $result3 = $result;
                    ResultKt.throwOnFailure($result3);
                    anonymousClass22.$remainingVelocity.element = 0.0f;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float $this$coerceToTarget, float target) {
        if (target == 0.0f) {
            return 0.0f;
        }
        return target > 0.0f ? RangesKt.coerceAtMost($this$coerceToTarget, target) : RangesKt.coerceAtLeast($this$coerceToTarget, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <I> java.lang.Object restartable(kotlin.jvm.functions.Function0<? extends I> r4, kotlin.jvm.functions.Function2<? super I, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.AnchoredDraggableKt.C02651
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableKt.C02651) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            goto L49
        L31:
            r4 = move-exception
            goto L4a
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2 r2 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            r3 = 0
            r2.<init>(r4, r5, r3)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            r3 = 1
            r0.label = r3     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            java.lang.Object r2 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r2, r0)     // Catch: androidx.compose.foundation.gestures.AnchoredDragFinishedSignal -> L31
            if (r2 != r1) goto L49
            return r1
        L49:
        L4a:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.restartable(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "I", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2", f = "AnchoredDraggable.kt", i = {}, l = {1119}, m = "invokeSuspend", n = {}, s = {})
    static final class C02662 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ Function0<I> $inputs;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02662(Function0<? extends I> function0, Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C02662> continuation) {
            super(2, continuation);
            this.$inputs = function0;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02662 c02662 = new C02662(this.$inputs, this.$block, continuation);
            c02662.L$0 = obj;
            return c02662;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02662) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Ref.ObjectRef previousDrag = new Ref.ObjectRef();
                    this.label = 1;
                    if (SnapshotStateKt.snapshotFlow(this.$inputs).collect(new AnonymousClass1(previousDrag, $this$coroutineScope, this.$block), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: AnchoredDraggable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "I", "latestInputs", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ Ref.ObjectRef<Job> $previousDrag;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Ref.ObjectRef<Job> objectRef, CoroutineScope coroutineScope, Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2) {
                this.$previousDrag = objectRef;
                this.$$this$coroutineScope = coroutineScope;
                this.$block = function2;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object emit(I r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
                /*
                    r9 = this;
                    boolean r0 = r11 instanceof androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$emit$1
                    if (r0 == 0) goto L14
                    r0 = r11
                    androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$emit$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L14
                    int r11 = r0.label
                    int r11 = r11 - r2
                    r0.label = r11
                    goto L19
                L14:
                    androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$emit$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$emit$1
                    r0.<init>(r9, r11)
                L19:
                    java.lang.Object r11 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    switch(r2) {
                        case 0: goto L3c;
                        case 1: goto L2d;
                        default: goto L24;
                    }
                L24:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L2d:
                    r10 = 0
                    java.lang.Object r1 = r0.L$2
                    kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                    java.lang.Object r1 = r0.L$1
                    java.lang.Object r2 = r0.L$0
                    androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1 r2 = (androidx.compose.foundation.gestures.AnchoredDraggableKt.C02662.AnonymousClass1) r2
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L66
                L3c:
                    kotlin.ResultKt.throwOnFailure(r11)
                    r2 = r9
                    kotlin.jvm.internal.Ref$ObjectRef<kotlinx.coroutines.Job> r3 = r2.$previousDrag
                    T r3 = r3.element
                    kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
                    if (r3 == 0) goto L68
                    r4 = r3
                    r5 = 0
                    androidx.compose.foundation.gestures.AnchoredDragFinishedSignal r6 = new androidx.compose.foundation.gestures.AnchoredDragFinishedSignal
                    r6.<init>()
                    java.util.concurrent.CancellationException r6 = (java.util.concurrent.CancellationException) r6
                    r4.cancel(r6)
                    r0.L$0 = r2
                    r0.L$1 = r10
                    r0.L$2 = r3
                    r3 = 1
                    r0.label = r3
                    java.lang.Object r3 = r4.join(r0)
                    if (r3 != r1) goto L64
                    return r1
                L64:
                    r1 = r10
                    r10 = r5
                L66:
                    r10 = r1
                L68:
                    kotlin.jvm.internal.Ref$ObjectRef<kotlinx.coroutines.Job> r1 = r2.$previousDrag
                    kotlinx.coroutines.CoroutineScope r3 = r2.$$this$coroutineScope
                    kotlinx.coroutines.CoroutineStart r5 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                    androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2 r4 = new androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2
                    kotlin.jvm.functions.Function2<I, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r6 = r2.$block
                    kotlinx.coroutines.CoroutineScope r7 = r2.$$this$coroutineScope
                    r8 = 0
                    r4.<init>(r6, r10, r7, r8)
                    r6 = r4
                    kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                    r7 = 1
                    r4 = 0
                    kotlinx.coroutines.Job r3 = kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)
                    r1.element = r3
                    kotlin.Unit r1 = kotlin.Unit.INSTANCE
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.C02662.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: AnchoredDraggable.kt */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "I", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2", f = "AnchoredDraggable.kt", i = {}, l = {1125}, m = "invokeSuspend", n = {}, s = {})
            static final class C00152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
                final /* synthetic */ I $latestInputs;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00152(Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2, I i, CoroutineScope coroutineScope, Continuation<? super C00152> continuation) {
                    super(2, continuation);
                    this.$block = function2;
                    this.$latestInputs = i;
                    this.$$this$coroutineScope = coroutineScope;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00152(this.$block, this.$latestInputs, this.$$this$coroutineScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    C00152 c00152;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Function2<I, Continuation<? super Unit>, Object> function2 = this.$block;
                            I i = this.$latestInputs;
                            this.label = 1;
                            if (function2.invoke(i, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c00152 = this;
                            break;
                            break;
                        case 1:
                            c00152 = this;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    CoroutineScopeKt.cancel(c00152.$$this$coroutineScope, new AnchoredDragFinishedSignal());
                    return Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> MapDraggableAnchors<T> emptyDraggableAnchors() {
        return new MapDraggableAnchors<>(new MutableObjectFloatMap(0, 1, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[PHI: r0
      0x006a: PHI (r0v5 'minValue' float) = (r0v4 'minValue' float), (r0v6 'minValue' float) binds: [B:9:0x0030, B:22:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <K> float minValueOrNaN(androidx.collection.ObjectFloatMap<K> r20) {
        /*
            int r0 = r20.get_size()
            r1 = 1
            if (r0 != r1) goto La
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        La:
            r0 = 0
            r0 = 2139095040(0x7f800000, float:Infinity)
            r2 = r20
            r3 = 0
            float[] r4 = r2.values
            r5 = r2
            r6 = 0
            long[] r7 = r5.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            r9 = 0
            if (r9 > r8) goto L71
        L1c:
            r10 = r7[r9]
            r12 = r10
            r14 = 0
            r15 = r2
            long r1 = ~r12
            r16 = 7
            long r1 = r1 << r16
            long r1 = r1 & r12
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r1 = r1 & r16
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 == 0) goto L6a
            int r1 = r9 - r8
            int r1 = ~r1
            int r1 = r1 >>> 31
            r2 = 8
            int r1 = 8 - r1
            r12 = 0
        L3c:
            if (r12 >= r1) goto L68
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r10
            r16 = 0
            r17 = 128(0x80, double:6.3E-322)
            int r17 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r17 >= 0) goto L4c
            r17 = 1
            goto L4e
        L4c:
            r17 = 0
        L4e:
            if (r17 == 0) goto L64
            int r13 = r9 << 3
            int r13 = r13 + r12
            r14 = r13
            r16 = 0
            r17 = r4[r14]
            r18 = 0
            int r19 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r19 > 0) goto L60
            r0 = r17
        L60:
        L64:
            long r10 = r10 >> r2
            int r12 = r12 + 1
            goto L3c
        L68:
            if (r1 != r2) goto L73
        L6a:
            if (r9 == r8) goto L72
            int r9 = r9 + 1
            r2 = r15
            r1 = 1
            goto L1c
        L71:
            r15 = r2
        L72:
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.minValueOrNaN(androidx.collection.ObjectFloatMap):float");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[PHI: r0
      0x006a: PHI (r0v5 'maxValue' float) = (r0v4 'maxValue' float), (r0v6 'maxValue' float) binds: [B:9:0x0030, B:22:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <K> float maxValueOrNaN(androidx.collection.ObjectFloatMap<K> r20) {
        /*
            int r0 = r20.get_size()
            r1 = 1
            if (r0 != r1) goto La
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        La:
            r0 = 0
            r0 = -8388608(0xffffffffff800000, float:-Infinity)
            r2 = r20
            r3 = 0
            float[] r4 = r2.values
            r5 = r2
            r6 = 0
            long[] r7 = r5.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            r9 = 0
            if (r9 > r8) goto L71
        L1c:
            r10 = r7[r9]
            r12 = r10
            r14 = 0
            r15 = r2
            long r1 = ~r12
            r16 = 7
            long r1 = r1 << r16
            long r1 = r1 & r12
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r1 = r1 & r16
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 == 0) goto L6a
            int r1 = r9 - r8
            int r1 = ~r1
            int r1 = r1 >>> 31
            r2 = 8
            int r1 = 8 - r1
            r12 = 0
        L3c:
            if (r12 >= r1) goto L68
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r10
            r16 = 0
            r17 = 128(0x80, double:6.3E-322)
            int r17 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r17 >= 0) goto L4c
            r17 = 1
            goto L4e
        L4c:
            r17 = 0
        L4e:
            if (r17 == 0) goto L64
            int r13 = r9 << 3
            int r13 = r13 + r12
            r14 = r13
            r16 = 0
            r17 = r4[r14]
            r18 = 0
            int r19 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r19 < 0) goto L60
            r0 = r17
        L60:
        L64:
            long r10 = r10 >> r2
            int r12 = r12 + 1
            goto L3c
        L68:
            if (r1 != r2) goto L73
        L6a:
            if (r9 == r8) goto L72
            int r9 = r9 + 1
            r2 = r15
            r1 = 1
            goto L1c
        L71:
            r15 = r2
        L72:
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.maxValueOrNaN(androidx.collection.ObjectFloatMap):float");
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
