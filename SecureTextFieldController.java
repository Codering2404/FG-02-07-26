package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: BasicSecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0015\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/text/SecureTextFieldController;", "", "obfuscationMaskState", "Landroidx/compose/runtime/State;", "", "(Landroidx/compose/runtime/State;)V", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "getCodepointTransformation", "()Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "focusChangeModifier", "Landroidx/compose/ui/Modifier;", "getFocusChangeModifier", "()Landroidx/compose/ui/Modifier;", "passwordInputTransformation", "Landroidx/compose/foundation/text/PasswordInputTransformation;", "getPasswordInputTransformation", "()Landroidx/compose/foundation/text/PasswordInputTransformation;", "resetTimerSignal", "Lkotlinx/coroutines/channels/Channel;", "", "observeHideEvents", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleHide", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SecureTextFieldController {
    public static final int $stable = 8;
    private final State<Character> obfuscationMaskState;
    private final PasswordInputTransformation passwordInputTransformation = new PasswordInputTransformation(new SecureTextFieldController$passwordInputTransformation$1(this));
    private final CodepointTransformation codepointTransformation = new CodepointTransformation() { // from class: androidx.compose.foundation.text.SecureTextFieldController$$ExternalSyntheticLambda0
        @Override // androidx.compose.foundation.text.input.internal.CodepointTransformation
        public final int transform(int i, int i2) {
            return SecureTextFieldController.codepointTransformation$lambda$0(this.f$0, i, i2);
        }
    };
    private final Modifier focusChangeModifier = FocusChangedModifierKt.onFocusChanged(Modifier.INSTANCE, new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.SecureTextFieldController$focusChangeModifier$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
            invoke2(focusState);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(FocusState it) {
            if (!it.isFocused()) {
                this.this$0.getPasswordInputTransformation().hide();
            }
        }
    });
    private final Channel<Unit> resetTimerSignal = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);

    public SecureTextFieldController(State<Character> state) {
        this.obfuscationMaskState = state;
    }

    public final PasswordInputTransformation getPasswordInputTransformation() {
        return this.passwordInputTransformation;
    }

    public final CodepointTransformation getCodepointTransformation() {
        return this.codepointTransformation;
    }

    static final int codepointTransformation$lambda$0(SecureTextFieldController this$0, int codepointIndex, int codepoint) {
        if (codepointIndex == this$0.passwordInputTransformation.getRevealCodepointIndex$foundation_release()) {
            return codepoint;
        }
        return this$0.obfuscationMaskState.getValue().charValue();
    }

    public final Modifier getFocusChangeModifier() {
        return this.focusChangeModifier;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.SecureTextFieldController$observeHideEvents$2, reason: invalid class name */
    /* JADX INFO: compiled from: BasicSecureTextField.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.SecureTextFieldController$observeHideEvents$2", f = "BasicSecureTextField.kt", i = {}, l = {242}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureTextFieldController.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = this;
                    break;
                    break;
                case 1:
                    anonymousClass2 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SecureTextFieldController.this.getPasswordInputTransformation().hide();
            return Unit.INSTANCE;
        }
    }

    public final Object observeHideEvents(Continuation<? super Unit> continuation) {
        Object objCollectLatest = FlowKt.collectLatest(FlowKt.consumeAsFlow(this.resetTimerSignal), new AnonymousClass2(null), continuation);
        return objCollectLatest == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatest : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleHide() {
        Object result = this.resetTimerSignal.mo8529trySendJP2dKIU(Unit.INSTANCE);
        if (ChannelResult.m8552isFailureimpl(result)) {
            this.passwordInputTransformation.hide();
        }
    }
}
