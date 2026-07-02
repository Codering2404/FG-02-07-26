package androidx.compose.foundation.text;

import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardActionRunner.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionRunner;", "Landroidx/compose/foundation/text/KeyboardActionScope;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "(Landroidx/compose/ui/platform/SoftwareKeyboardController;)V", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "getFocusManager", "()Landroidx/compose/ui/focus/FocusManager;", "setFocusManager", "(Landroidx/compose/ui/focus/FocusManager;)V", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "getKeyboardActions", "()Landroidx/compose/foundation/text/KeyboardActions;", "setKeyboardActions", "(Landroidx/compose/foundation/text/KeyboardActions;)V", "defaultKeyboardAction", "", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "defaultKeyboardAction-KlQnJC8", "(I)V", "runAction", "runAction-KlQnJC8", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyboardActionRunner implements KeyboardActionScope {
    public static final int $stable = 8;
    public FocusManager focusManager;
    public KeyboardActions keyboardActions;
    private final SoftwareKeyboardController keyboardController;

    public KeyboardActionRunner(SoftwareKeyboardController keyboardController) {
        this.keyboardController = keyboardController;
    }

    public final KeyboardActions getKeyboardActions() {
        KeyboardActions keyboardActions = this.keyboardActions;
        if (keyboardActions != null) {
            return keyboardActions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("keyboardActions");
        return null;
    }

    public final void setKeyboardActions(KeyboardActions keyboardActions) {
        this.keyboardActions = keyboardActions;
    }

    public final FocusManager getFocusManager() {
        FocusManager focusManager = this.focusManager;
        if (focusManager != null) {
            return focusManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("focusManager");
        return null;
    }

    public final void setFocusManager(FocusManager focusManager) {
        this.focusManager = focusManager;
    }

    /* JADX INFO: renamed from: runAction-KlQnJC8, reason: not valid java name */
    public final void m990runActionKlQnJC8(int imeAction) {
        Function1<KeyboardActionScope, Unit> onSend;
        Unit unit = null;
        if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6269getDoneeUduSuo())) {
            onSend = getKeyboardActions().getOnDone();
        } else if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6270getGoeUduSuo())) {
            onSend = getKeyboardActions().getOnGo();
        } else if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6271getNexteUduSuo())) {
            onSend = getKeyboardActions().getOnNext();
        } else if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6273getPreviouseUduSuo())) {
            onSend = getKeyboardActions().getOnPrevious();
        } else if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6274getSearcheUduSuo())) {
            onSend = getKeyboardActions().getOnSearch();
        } else if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6275getSendeUduSuo())) {
            onSend = getKeyboardActions().getOnSend();
        } else {
            if (!(ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6268getDefaulteUduSuo()) ? true : ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6272getNoneeUduSuo()))) {
                throw new IllegalStateException("invalid ImeAction".toString());
            }
            onSend = null;
        }
        if (onSend != null) {
            onSend.invoke(this);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            mo989defaultKeyboardActionKlQnJC8(imeAction);
        }
    }

    @Override // androidx.compose.foundation.text.KeyboardActionScope
    /* JADX INFO: renamed from: defaultKeyboardAction-KlQnJC8, reason: not valid java name */
    public void mo989defaultKeyboardActionKlQnJC8(int imeAction) {
        if (!ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6271getNexteUduSuo())) {
            if (!ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6273getPreviouseUduSuo())) {
                if (!ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6269getDoneeUduSuo())) {
                    if (ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6270getGoeUduSuo()) ? true : ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6274getSearcheUduSuo()) ? true : ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6275getSendeUduSuo()) ? true : ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6268getDefaulteUduSuo())) {
                        return;
                    }
                    ImeAction.m6255equalsimpl0(imeAction, ImeAction.INSTANCE.m6272getNoneeUduSuo());
                    return;
                } else {
                    SoftwareKeyboardController softwareKeyboardController = this.keyboardController;
                    if (softwareKeyboardController != null) {
                        softwareKeyboardController.hide();
                        return;
                    }
                    return;
                }
            }
            getFocusManager().mo3848moveFocus3ESFkO8(FocusDirection.INSTANCE.m3844getPreviousdhqQ8s());
            return;
        }
        getFocusManager().mo3848moveFocus3ESFkO8(FocusDirection.INSTANCE.m3843getNextdhqQ8s());
    }
}
