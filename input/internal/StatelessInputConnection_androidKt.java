package androidx.compose.foundation.text.input.internal;

import android.content.ClipData;
import android.os.Bundle;
import android.view.inputmethod.ExtractedText;
import androidx.compose.foundation.content.PlatformTransferableContent;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.ui.platform.AndroidClipboardManager_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StatelessInputConnection.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002\u001a\u0016\u0010\u000b\u001a\u00020\f*\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"DEBUG_CLASS", "", StatelessInputConnection_androidKt.EXTRA_INPUT_CONTENT_INFO, "SIC_DEBUG", "", "getSIC_DEBUG$annotations", "()V", "STATELESS_TAG", "toExtractedText", "Landroid/view/inputmethod/ExtractedText;", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "toTransferableContent", "Landroidx/compose/foundation/content/TransferableContent;", "Landroidx/core/view/inputmethod/InputContentInfoCompat;", "extras", "Landroid/os/Bundle;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StatelessInputConnection_androidKt {
    private static final String DEBUG_CLASS = "StatelessInputConnection";
    private static final String EXTRA_INPUT_CONTENT_INFO = "EXTRA_INPUT_CONTENT_INFO";
    public static final boolean SIC_DEBUG = false;
    private static final String STATELESS_TAG = "StatelessIC";

    public static /* synthetic */ void getSIC_DEBUG$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExtractedText toExtractedText(TextFieldCharSequence textFieldCharSequence) {
        ExtractedText extractedText = new ExtractedText();
        extractedText.text = textFieldCharSequence;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = textFieldCharSequence.length();
        extractedText.partialStartOffset = -1;
        extractedText.selectionStart = TextRange.m6089getMinimpl(textFieldCharSequence.getSelection());
        extractedText.selectionEnd = TextRange.m6088getMaximpl(textFieldCharSequence.getSelection());
        extractedText.flags = !StringsKt.contains$default((CharSequence) textFieldCharSequence, '\n', false, 2, (Object) null) ? 1 : 0;
        return extractedText;
    }

    public static final TransferableContent toTransferableContent(InputContentInfoCompat $this$toTransferableContent, Bundle extras) {
        ClipData clipData = new ClipData($this$toTransferableContent.getDescription(), new ClipData.Item($this$toTransferableContent.getContentUri()));
        return new TransferableContent(AndroidClipboardManager_androidKt.toClipEntry(clipData), AndroidClipboardManager_androidKt.toClipMetadata($this$toTransferableContent.getDescription()), TransferableContent.Source.INSTANCE.m330getKeyboardkB6V9T0(), new PlatformTransferableContent($this$toTransferableContent.getLinkUri(), extras == null ? Bundle.EMPTY : extras), null);
    }
}
