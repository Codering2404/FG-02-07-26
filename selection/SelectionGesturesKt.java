package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\rH\u0082@¢\u0006\u0002\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a*\u0010\u0017\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0016\u001a\u001c\u0010\u0018\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a\"\u0010\u001d\u001a\u00020\u0010*\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0080@¢\u0006\u0002\u0010\u001f\u001a\"\u0010 \u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010\"\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a\"\u0010$\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010!\u001a \u0010%\u001a\u00020\u0019*\u00020\u00192\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100'H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006("}, d2 = {"STATIC_KEY", "", "isPrecisePointer", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "awaitDown", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "", "observer", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelectionBtf2", "selectionGestureInput", "Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "selectionGesturePointerInputBtf2", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "downEvent", "touchSelectionSubsequentPress", "updateSelectionTouchMode", "updateTouchMode", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {425}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {158, 181}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C04021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04021(Continuation<? super C04021> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelectionBtf2$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {351, 377}, m = "mouseSelectionBtf2", n = {"$this$mouseSelectionBtf2", "observer", "$this$mouseSelectionBtf2", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C04031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04031(Continuation<? super C04031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelectionBtf2(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {124, 128}, m = "touchSelection", n = {"$this$touchSelection", "observer", "firstDown", "$this$touchSelection", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C04051 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04051(Continuation<? super C04051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {238, 241}, m = "touchSelectionFirstPress", n = {"$this$touchSelectionFirstPress", "observer", "firstDown", "$this$touchSelectionFirstPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C04071 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04071(Continuation<? super C04071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionFirstPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 0, 0, 1, 1}, l = {276, 315}, m = "touchSelectionSubsequentPress", n = {"$this$touchSelectionSubsequentPress", "observer", "firstDown", "overSlop", "pointerId", "$this$touchSelectionSubsequentPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1"})
    static final class C04081 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C04081(Continuation<? super C04081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionSubsequentPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1", f = "SelectionGestures.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    static final class C04091 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04091(Function1<? super Boolean, Unit> function1, Continuation<? super C04091> continuation) {
            super(2, continuation);
            this.$updateTouchMode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04091 c04091 = new C04091(this.$updateTouchMode, continuation);
            c04091.L$0 = obj;
            return c04091;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C04091) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {93}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        static final class C00621 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00621(Function1<? super Boolean, Unit> function1, Continuation<? super C00621> continuation) {
                super(2, continuation);
                this.$updateTouchMode = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00621 c00621 = new C00621(this.$updateTouchMode, continuation);
                c00621.L$0 = obj;
                return c00621;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00621) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0038 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
            /* JADX WARN: Removed duplicated region for block: B:15:0x004b  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0039 -> B:12:0x003f). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 1
                    switch(r1) {
                        case 0: goto L20;
                        case 1: goto L13;
                        default: goto La;
                    }
                La:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L13:
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                    kotlin.ResultKt.throwOnFailure(r9)
                    r4 = r3
                    r3 = r1
                    r1 = r0
                    r0 = r9
                    goto L3f
                L20:
                    kotlin.ResultKt.throwOnFailure(r9)
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                L28:
                    androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r5 = r1
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r1.L$0 = r3
                    r1.label = r2
                    java.lang.Object r4 = r3.awaitPointerEvent(r4, r5)
                    if (r4 != r0) goto L39
                    return r0
                L39:
                    r7 = r0
                    r0 = r9
                    r9 = r4
                    r4 = r3
                    r3 = r1
                    r1 = r7
                L3f:
                    androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
                    kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r3.$updateTouchMode
                    boolean r6 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r9)
                    if (r6 != 0) goto L4b
                    r9 = r2
                    goto L4c
                L4b:
                    r9 = 0
                L4c:
                    java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
                    r5.invoke(r9)
                    r9 = r0
                    r0 = r1
                    r1 = r3
                    r3 = r4
                    goto L28
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C04091.C00621.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if ($this$pointerInput.awaitPointerEventScope(new C00621(this.$updateTouchMode, null), this) == coroutine_suspended) {
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

    public static final Modifier updateSelectionTouchMode(Modifier $this$updateSelectionTouchMode, Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput($this$updateSelectionTouchMode, Integer.valueOf(STATIC_KEY), new C04091(function1, null));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
    static final class C04041 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04041(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super C04041> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04041 c04041 = new C04041(this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            c04041.L$0 = obj;
            return c04041;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C04041) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    ClicksCounter clicksCounter = new ClicksCounter($this$pointerInput.getViewConfiguration());
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00611(this.$mouseSelectionObserver, clicksCounter, this.$textDragObserver, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", i = {0}, l = {105, 111, 113}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
        static final class C00611 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ClicksCounter $clicksCounter;
            final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
            final /* synthetic */ TextDragObserver $textDragObserver;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00611(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, Continuation<? super C00611> continuation) {
                super(2, continuation);
                this.$mouseSelectionObserver = mouseSelectionObserver;
                this.$clicksCounter = clicksCounter;
                this.$textDragObserver = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00611 c00611 = new C00611(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, continuation);
                c00611.L$0 = obj;
                return c00611;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00611) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x00b1  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x0091 A[SYNTHETIC] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r19) {
                /*
                    Method dump skipped, instruction units count: 214
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C04041.C00611.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public static final Modifier selectionGestureInput(Modifier $this$selectionGestureInput, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput($this$selectionGestureInput, mouseSelectionObserver, textDragObserver, new C04041(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b1 A[Catch: CancellationException -> 0x00e3, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a9, B:34:0x00b1, B:36:0x00c2, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00dd A[Catch: CancellationException -> 0x00e3, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00e3, blocks: (B:32:0x00a9, B:34:0x00b1, B:36:0x00c2, B:38:0x00d0, B:39:0x00d3, B:40:0x00d8, B:41:0x00dd, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.TextDragObserver r8, androidx.compose.ui.input.pointer.PointerEvent r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.selection.MouseSelectionObserver r8, androidx.compose.foundation.text.selection.ClicksCounter r9, androidx.compose.ui.input.pointer.PointerEvent r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGesturePointerInputBtf2$2", f = "SelectionGestures.kt", i = {0}, l = {209, 217, 220, 221}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clicksCounter = clicksCounter;
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00a1  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00bb  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x009c A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 254
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object selectionGesturePointerInputBtf2(PointerInputScope $this$selectionGesturePointerInputBtf2, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        ClicksCounter clicksCounter = new ClicksCounter($this$selectionGesturePointerInputBtf2.getViewConfiguration());
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$selectionGesturePointerInputBtf2, new AnonymousClass2(clicksCounter, mouseSelectionObserver, textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00af A[Catch: CancellationException -> 0x00e1, TryCatch #0 {CancellationException -> 0x00e1, blocks: (B:32:0x00a7, B:34:0x00af, B:36:0x00c0, B:38:0x00ce, B:39:0x00d1, B:40:0x00d6, B:41:0x00db, B:18:0x004b, B:25:0x0076, B:27:0x007a, B:29:0x0084, B:21:0x0054), top: B:48:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00db A[Catch: CancellationException -> 0x00e1, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00e1, blocks: (B:32:0x00a7, B:34:0x00af, B:36:0x00c0, B:38:0x00ce, B:39:0x00d1, B:40:0x00d6, B:41:0x00db, B:18:0x004b, B:25:0x0076, B:27:0x007a, B:29:0x0084, B:21:0x0054), top: B:48:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object touchSelectionFirstPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.TextDragObserver r8, androidx.compose.ui.input.pointer.PointerEvent r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionFirstPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a7 A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:44:0x00e4, B:46:0x00ec, B:48:0x00fd, B:50:0x010b, B:51:0x010e, B:52:0x0113, B:53:0x0118, B:27:0x00a3, B:29:0x00a7, B:30:0x00a9, B:32:0x00ae, B:34:0x00b1, B:36:0x00bc, B:38:0x00c2, B:40:0x00c6, B:41:0x00cb), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ae A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:44:0x00e4, B:46:0x00ec, B:48:0x00fd, B:50:0x010b, B:51:0x010e, B:52:0x0113, B:53:0x0118, B:27:0x00a3, B:29:0x00a7, B:30:0x00a9, B:32:0x00ae, B:34:0x00b1, B:36:0x00bc, B:38:0x00c2, B:40:0x00c6, B:41:0x00cb), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b1 A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:44:0x00e4, B:46:0x00ec, B:48:0x00fd, B:50:0x010b, B:51:0x010e, B:52:0x0113, B:53:0x0118, B:27:0x00a3, B:29:0x00a7, B:30:0x00a9, B:32:0x00ae, B:34:0x00b1, B:36:0x00bc, B:38:0x00c2, B:40:0x00c6, B:41:0x00cb), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ec A[Catch: CancellationException -> 0x003c, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:44:0x00e4, B:46:0x00ec, B:48:0x00fd, B:50:0x010b, B:51:0x010e, B:52:0x0113, B:53:0x0118, B:27:0x00a3, B:29:0x00a7, B:30:0x00a9, B:32:0x00ae, B:34:0x00b1, B:36:0x00bc, B:38:0x00c2, B:40:0x00c6, B:41:0x00cb), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0118 A[Catch: CancellationException -> 0x003c, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x003c, blocks: (B:13:0x0036, B:44:0x00e4, B:46:0x00ec, B:48:0x00fd, B:50:0x010b, B:51:0x010e, B:52:0x0113, B:53:0x0118, B:27:0x00a3, B:29:0x00a7, B:30:0x00a9, B:32:0x00ae, B:34:0x00b1, B:36:0x00bc, B:38:0x00c2, B:40:0x00c6, B:41:0x00cb), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v1, types: [androidx.compose.foundation.text.TextDragObserver] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r13v10, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope] */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object touchSelectionSubsequentPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, androidx.compose.foundation.text.TextDragObserver r13, androidx.compose.ui.input.pointer.PointerEvent r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionSubsequentPress(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094 A[Catch: all -> 0x004b, TryCatch #2 {all -> 0x004b, blocks: (B:18:0x0046, B:30:0x008c, B:32:0x0094, B:34:0x00a5, B:36:0x00b3), top: B:75:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0117 A[Catch: all -> 0x003b, TryCatch #3 {all -> 0x003b, blocks: (B:13:0x0035, B:55:0x010f, B:57:0x0117, B:59:0x0128, B:61:0x0136), top: B:77:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object mouseSelectionBtf2(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.selection.MouseSelectionObserver r8, androidx.compose.foundation.text.selection.ClicksCounter r9, androidx.compose.ui.input.pointer.PointerEvent r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelectionBtf2(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004f -> B:18:0x0057). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r18) {
        /*
            r0 = r18
            boolean r1 = r0 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            switch(r3) {
                case 0: goto L3c;
                case 1: goto L30;
                default: goto L27;
            }
        L27:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            java.lang.Object r3 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            goto L57
        L3c:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r17
        L42:
            androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r1.L$0 = r3
            r1.label = r4
            java.lang.Object r5 = r3.awaitPointerEvent(r5, r1)
            if (r5 != r2) goto L4f
            return r2
        L4f:
            r16 = r1
            r1 = r0
            r0 = r5
            r5 = r3
            r3 = r2
            r2 = r16
        L57:
            androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
            java.util.List r6 = r0.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            int r10 = r6.size()
        L67:
            if (r9 >= r10) goto L7f
            java.lang.Object r11 = r6.get(r9)
            r12 = r11
            r13 = 0
            r14 = r12
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            r15 = 0
            boolean r14 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r14)
            if (r14 != 0) goto L7b
            r10 = 0
            goto L81
        L7b:
            int r9 = r9 + 1
            goto L67
        L7f:
            r10 = r4
        L81:
            if (r10 == 0) goto L84
            return r0
        L84:
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r5
            goto L42
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange change1, PointerInputChange change2) {
        float slop = DragGestureDetectorKt.m417pointerSlopE8SPZFQ(viewConfiguration, change1.getType());
        return Offset.m3919getDistanceimpl(Offset.m3925minusMKHz9U(change1.getPosition(), change2.getPosition())) < slop;
    }

    public static final boolean isPrecisePointer(PointerEvent $this$isPrecisePointer) {
        List<PointerInputChange> changes = $this$isPrecisePointer.getChanges();
        int size = changes.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (!PointerType.m5433equalsimpl0(it.getType(), PointerType.INSTANCE.m5438getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }
}
