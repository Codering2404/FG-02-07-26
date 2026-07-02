package androidx.compose.foundation.text.input.internal.undo;

import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.EditingBuffer;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextUndoOperation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"redo", "", "Landroidx/compose/foundation/text/input/TextFieldState;", "op", "Landroidx/compose/foundation/text/input/internal/undo/TextUndoOperation;", "undo", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUndoOperationKt {
    public static final void undo(TextFieldState $this$undo, TextUndoOperation op) {
        $this$undo.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer $this$undo_u24lambda_u240 = $this$undo.getMainBuffer();
        $this$undo_u24lambda_u240.replace(op.getIndex(), op.getIndex() + op.getPostText().length(), op.getPreText());
        $this$undo_u24lambda_u240.setSelection(TextRange.m6091getStartimpl(op.getPreSelection()), TextRange.m6086getEndimpl(op.getPreSelection()));
        TextFieldCharSequence afterEditValue$iv = new TextFieldCharSequence($this$undo.getMainBuffer().toString(), $this$undo.getMainBuffer().m1130getSelectiond9O1mEE(), $this$undo.getMainBuffer().m1129getCompositionMzsxiRA(), null, 8, null);
        $this$undo.updateValueAndNotifyListeners($this$undo.getValue$foundation_release(), afterEditValue$iv, true);
    }

    public static final void redo(TextFieldState $this$redo, TextUndoOperation op) {
        $this$redo.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer $this$redo_u24lambda_u241 = $this$redo.getMainBuffer();
        $this$redo_u24lambda_u241.replace(op.getIndex(), op.getIndex() + op.getPreText().length(), op.getPostText());
        $this$redo_u24lambda_u241.setSelection(TextRange.m6091getStartimpl(op.getPostSelection()), TextRange.m6086getEndimpl(op.getPostSelection()));
        TextFieldCharSequence afterEditValue$iv = new TextFieldCharSequence($this$redo.getMainBuffer().toString(), $this$redo.getMainBuffer().m1130getSelectiond9O1mEE(), $this$redo.getMainBuffer().m1129getCompositionMzsxiRA(), null, 8, null);
        $this$redo.updateValueAndNotifyListeners($this$redo.getValue$foundation_release(), afterEditValue$iv, true);
    }
}
