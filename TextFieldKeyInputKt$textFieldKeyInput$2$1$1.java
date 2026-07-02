package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.KeyEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: TextFieldKeyInput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* synthetic */ class TextFieldKeyInputKt$textFieldKeyInput$2$1$1 extends FunctionReferenceImpl implements Function1<KeyEvent, Boolean> {
    TextFieldKeyInputKt$textFieldKeyInput$2$1$1(Object obj) {
        super(1, obj, TextFieldKeyInput.class, "process", "process-ZmokQxo(Landroid/view/KeyEvent;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
        return m1069invokeZmokQxo(keyEvent.m5208unboximpl());
    }

    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
    public final Boolean m1069invokeZmokQxo(android.view.KeyEvent p0) {
        return Boolean.valueOf(((TextFieldKeyInput) this.receiver).m1066processZmokQxo(p0));
    }
}
