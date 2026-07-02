package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2", f = "Scrollable.kt", i = {}, l = {782}, m = "invokeSuspend", n = {}, s = {})
final class ScrollingLogic$doFlingAnimation$2 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $available;
    final /* synthetic */ Ref.LongRef $result;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$doFlingAnimation$2(ScrollingLogic scrollingLogic, Ref.LongRef longRef, long j, Continuation<? super ScrollingLogic$doFlingAnimation$2> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
        this.$result = longRef;
        this.$available = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic$doFlingAnimation$2 scrollingLogic$doFlingAnimation$2 = new ScrollingLogic$doFlingAnimation$2(this.this$0, this.$result, this.$available, continuation);
        scrollingLogic$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic$doFlingAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
        return ((ScrollingLogic$doFlingAnimation$2) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ScrollingLogic scrollingLogic;
        Ref.LongRef longRef;
        ScrollingLogic scrollingLogic2;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
                final ScrollingLogic scrollingLogic3 = this.this$0;
                ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1
                    @Override // androidx.compose.foundation.gestures.ScrollScope
                    public float scrollBy(float pixels) {
                        return scrollingLogic3.reverseIfNeeded(scrollingLogic3.m490toFloatk4lQ0M(nestedScrollScope.mo447scrollByWithOverscrollOzD1aCk(scrollingLogic3.m488reverseIfNeededMKHz9U(scrollingLogic3.m491toOffsettuRUvjQ(pixels)), NestedScrollSource.INSTANCE.m5255getSideEffectWNlRxjI())));
                    }
                };
                scrollingLogic = this.this$0;
                longRef = this.$result;
                long j2 = this.$available;
                FlingBehavior $this$invokeSuspend_u24lambda_u241_u24lambda_u240 = scrollingLogic.flingBehavior;
                long j3 = longRef.element;
                float fReverseIfNeeded = scrollingLogic.reverseIfNeeded(scrollingLogic.m483toFloatTH1AsA0(j2));
                this.L$0 = scrollingLogic;
                this.L$1 = scrollingLogic;
                this.L$2 = longRef;
                this.J$0 = j3;
                this.label = 1;
                Object objPerformFling = $this$invokeSuspend_u24lambda_u241_u24lambda_u240.performFling(scrollScope, fReverseIfNeeded, this);
                if (objPerformFling == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objPerformFling;
                scrollingLogic2 = scrollingLogic;
                j = j3;
                break;
            case 1:
                j = this.J$0;
                longRef = (Ref.LongRef) this.L$2;
                scrollingLogic = (ScrollingLogic) this.L$1;
                scrollingLogic2 = (ScrollingLogic) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        longRef.element = scrollingLogic.m484updateQWom1Mo(j, scrollingLogic2.reverseIfNeeded(((Number) $result).floatValue()));
        return Unit.INSTANCE;
    }
}
