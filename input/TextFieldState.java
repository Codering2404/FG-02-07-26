package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextUndoManager;
import androidx.compose.foundation.text.input.internal.EditingBuffer;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TextFieldState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001:\u0002]^B\u001b\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0015\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u001fH\u0000¢\u0006\u0002\b9J\u0010\u0010:\u001a\u0002072\u0006\u0010;\u001a\u00020<H\u0001J&\u0010=\u001a\u0002072\b\u0010>\u001a\u0004\u0018\u00010?2\b\b\u0002\u0010@\u001a\u00020\u000e2\b\b\u0002\u0010A\u001a\u00020BH\u0002J\"\u0010C\u001a\u0002072\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002070E¢\u0006\u0002\bFH\u0086\bJE\u0010G\u001a\u0002072\b\u0010>\u001a\u0004\u0018\u00010?2\b\b\u0002\u0010@\u001a\u00020\u000e2\b\b\u0002\u0010A\u001a\u00020B2\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002070E¢\u0006\u0002\bFH\u0080\b¢\u0006\u0002\bHJ'\u0010I\u001a\u0002072\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002070E¢\u0006\u0002\bFH\u0080\b¢\u0006\u0002\bJJ\b\u0010K\u001a\u000207H\u0001J(\u0010L\u001a\u0002072\u0006\u0010M\u001a\u00020/2\u0006\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020P2\u0006\u0010A\u001a\u00020BH\u0002J\u0015\u0010Q\u001a\u0002072\u0006\u00108\u001a\u00020\u001fH\u0000¢\u0006\u0002\bRJ\b\u0010S\u001a\u00020<H\u0001J2\u0010T\u001a\u0002072\u0006\u0010U\u001a\u00020<2\b\u0010V\u001a\u0004\u0018\u00010\u00052\u0006\u0010W\u001a\u00020\u000e2\u0006\u0010X\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0002\bYJ\b\u0010Z\u001a\u00020\u0003H\u0016J \u0010[\u001a\u0002072\u0006\u0010\\\u001a\u00020/2\u0006\u0010;\u001a\u00020/2\u0006\u0010@\u001a\u00020\u000eH\u0002R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00058Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00168\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010 \u001a\u00020\u00058Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001c\u0010*\u001a\u00020+8GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0018\u001a\u0004\b-\u0010.R+\u00100\u001a\u00020/2\u0006\u0010\r\u001a\u00020/8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u0014\u001a\u0004\b1\u00102\"\u0004\b3\u00104\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006_"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState;", "", "initialText", "", "initialSelection", "Landroidx/compose/ui/text/TextRange;", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "initialTextUndoManager", "Landroidx/compose/foundation/text/input/TextUndoManager;", "(Ljava/lang/String;JLandroidx/compose/foundation/text/input/TextUndoManager;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "composition", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "<set-?>", "", "isEditing", "()Z", "setEditing", "(Z)V", "isEditing$delegate", "Landroidx/compose/runtime/MutableState;", "mainBuffer", "Landroidx/compose/foundation/text/input/internal/EditingBuffer;", "getMainBuffer$foundation_release$annotations", "()V", "getMainBuffer$foundation_release", "()Landroidx/compose/foundation/text/input/internal/EditingBuffer;", "setMainBuffer$foundation_release", "(Landroidx/compose/foundation/text/input/internal/EditingBuffer;)V", "notifyImeListeners", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "selection", "getSelection-d9O1mEE", "()J", "text", "", "getText", "()Ljava/lang/CharSequence;", "textUndoManager", "getTextUndoManager$foundation_release", "()Landroidx/compose/foundation/text/input/TextUndoManager;", "undoState", "Landroidx/compose/foundation/text/input/UndoState;", "getUndoState$annotations", "getUndoState", "()Landroidx/compose/foundation/text/input/UndoState;", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", AppMeasurementSdk.ConditionalUserProperty.VALUE, "getValue$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "setValue", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;)V", "value$delegate", "addNotifyImeListener", "", "notifyImeListener", "addNotifyImeListener$foundation_release", "commitEdit", "newValue", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "commitEditAsUser", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "restartImeIfContentChanges", "undoBehavior", "Landroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;", "edit", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "editAsUser", "editAsUser$foundation_release", "editWithNoSideEffects", "editWithNoSideEffects$foundation_release", "finishEditing", "recordEditForUndo", "previousValue", "postValue", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "removeNotifyImeListener", "removeNotifyImeListener$foundation_release", "startEdit", "syncMainBufferToTemporaryBuffer", "textFieldBuffer", "newComposition", "textChanged", "selectionChanged", "syncMainBufferToTemporaryBuffer-TS3Rm5k$foundation_release", "toString", "updateValueAndNotifyListeners", "oldValue", "NotifyImeListener", "Saver", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: isEditing$delegate, reason: from kotlin metadata */
    private final MutableState isEditing;
    private EditingBuffer mainBuffer;
    private final MutableVector<NotifyImeListener> notifyImeListeners;
    private final TextUndoManager textUndoManager;
    private final UndoState undoState;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "", "onChange", "", "oldValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "newValue", "restartImeIfContentChanges", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface NotifyImeListener {
        void onChange(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges);
    }

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextFieldEditUndoBehavior.values().length];
            try {
                iArr[TextFieldEditUndoBehavior.ClearHistory.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.MergeIfPossible.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.NeverMerge.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TextFieldState(String str, long j, TextUndoManager textUndoManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, textUndoManager);
    }

    public /* synthetic */ TextFieldState(String str, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j);
    }

    public static /* synthetic */ void getMainBuffer$foundation_release$annotations() {
    }

    public static /* synthetic */ void getUndoState$annotations() {
    }

    private TextFieldState(String initialText, long initialSelection, TextUndoManager initialTextUndoManager) {
        this.textUndoManager = initialTextUndoManager;
        this.mainBuffer = new EditingBuffer(initialText, TextRangeKt.m6097coerceIn8ffj60Q(initialSelection, 0, initialText.length()), (DefaultConstructorMarker) null);
        this.isEditing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldCharSequence(initialText, initialSelection, null, null, 12, null), null, 2, null);
        this.undoState = new UndoState(this);
        this.notifyImeListeners = new MutableVector<>(new NotifyImeListener[16], 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextFieldState(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        str = (i & 1) != 0 ? "" : str;
        this(str, (i & 2) != 0 ? TextRangeKt.TextRange(str.length()) : j, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextFieldState(String str, long j) {
        this(str, j, new TextUndoManager(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: getTextUndoManager$foundation_release, reason: from getter */
    public final TextUndoManager getTextUndoManager() {
        return this.textUndoManager;
    }

    /* JADX INFO: renamed from: getMainBuffer$foundation_release, reason: from getter */
    public final EditingBuffer getMainBuffer() {
        return this.mainBuffer;
    }

    public final void setMainBuffer$foundation_release(EditingBuffer editingBuffer) {
        this.mainBuffer = editingBuffer;
    }

    private final boolean isEditing() {
        State $this$getValue$iv = this.isEditing;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setEditing(boolean z) {
        MutableState $this$setValue$iv = this.isEditing;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    private final void setValue(TextFieldCharSequence textFieldCharSequence) {
        MutableState $this$setValue$iv = this.value;
        $this$setValue$iv.setValue(textFieldCharSequence);
    }

    public final TextFieldCharSequence getValue$foundation_release() {
        State $this$getValue$iv = this.value;
        return (TextFieldCharSequence) $this$getValue$iv.getValue();
    }

    public final CharSequence getText() {
        return getValue$foundation_release().getText();
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m1101getSelectiond9O1mEE() {
        return getValue$foundation_release().getSelection();
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1100getCompositionMzsxiRA() {
        return getValue$foundation_release().getComposition();
    }

    public final void edit(Function1<? super TextFieldBuffer, Unit> block) {
        TextFieldBuffer mutableValue = startEdit();
        try {
            block.invoke(mutableValue);
            commitEdit(mutableValue);
        } finally {
            finishEditing();
        }
    }

    public String toString() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            return "TextFieldState(selection=" + ((Object) TextRange.m6094toStringimpl(m1101getSelectiond9O1mEE())) + ", text=\"" + ((Object) getText()) + "\")";
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    public final UndoState getUndoState() {
        return this.undoState;
    }

    public final TextFieldBuffer startEdit() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            boolean isEditingFreeze = isEditing();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            if (!(!isEditingFreeze)) {
                throw new IllegalStateException("TextFieldState does not support concurrent or nested editing.".toString());
            }
            setEditing(true);
            return new TextFieldBuffer(getValue$foundation_release(), null, null, null, 14, null);
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            throw th;
        }
    }

    public final void commitEdit(TextFieldBuffer newValue) {
        boolean textChanged = newValue.getChanges().getChangeCount() > 0;
        boolean selectionChanged = true ^ TextRange.m6084equalsimpl0(newValue.getSelectionInChars(), this.mainBuffer.m1130getSelectiond9O1mEE());
        if (textChanged) {
            this.textUndoManager.clearHistory();
        }
        m1102syncMainBufferToTemporaryBufferTS3Rm5k$foundation_release(newValue, null, textChanged, selectionChanged);
    }

    public final void finishEditing() {
        setEditing(false);
    }

    public static /* synthetic */ void editAsUser$foundation_release$default(TextFieldState $this, InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1 block, int i, Object obj) {
        if ((i & 2) != 0) {
            restartImeIfContentChanges = true;
        }
        if ((i & 4) != 0) {
            undoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        $this.getMainBuffer().getChangeTracker().clearChanges();
        block.invoke($this.getMainBuffer());
        $this.commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
    }

    public final void editAsUser$foundation_release(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1<? super EditingBuffer, Unit> block) {
        getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(getMainBuffer());
        commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
    }

    public final void editWithNoSideEffects$foundation_release(Function1<? super EditingBuffer, Unit> block) {
        getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(getMainBuffer());
        TextFieldCharSequence afterEditValue = new TextFieldCharSequence(getMainBuffer().toString(), getMainBuffer().m1130getSelectiond9O1mEE(), getMainBuffer().m1129getCompositionMzsxiRA(), null, 8, null);
        updateValueAndNotifyListeners(getValue$foundation_release(), afterEditValue, true);
    }

    static /* synthetic */ void commitEditAsUser$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitEditAsUser(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldCharSequence beforeEditValue = getValue$foundation_release();
        if (this.mainBuffer.getChangeTracker().getChangeCount() == 0 && TextRange.m6084equalsimpl0(beforeEditValue.getSelection(), this.mainBuffer.m1130getSelectiond9O1mEE())) {
            if (!Intrinsics.areEqual(beforeEditValue.getComposition(), this.mainBuffer.m1129getCompositionMzsxiRA()) || !Intrinsics.areEqual(beforeEditValue.getHighlight(), this.mainBuffer.getHighlight())) {
                updateValueAndNotifyListeners(getValue$foundation_release(), new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.m1130getSelectiond9O1mEE(), this.mainBuffer.m1129getCompositionMzsxiRA(), this.mainBuffer.getHighlight(), null), restartImeIfContentChanges);
                return;
            }
            return;
        }
        TextFieldCharSequence afterEditValue = new TextFieldCharSequence(this.mainBuffer.toString(), this.mainBuffer.m1130getSelectiond9O1mEE(), this.mainBuffer.m1129getCompositionMzsxiRA(), this.mainBuffer.getHighlight(), null);
        if (inputTransformation == null) {
            updateValueAndNotifyListeners(beforeEditValue, afterEditValue, restartImeIfContentChanges);
            recordEditForUndo(beforeEditValue, afterEditValue, this.mainBuffer.getChangeTracker(), undoBehavior);
            return;
        }
        TextFieldBuffer textFieldBuffer = new TextFieldBuffer(afterEditValue, this.mainBuffer.getChangeTracker(), beforeEditValue, null, 8, null);
        inputTransformation.transformInput(textFieldBuffer);
        boolean textChangedByFilter = !StringsKt.contentEquals(textFieldBuffer.asCharSequence(), afterEditValue);
        boolean selectionChangedByFilter = !TextRange.m6084equalsimpl0(textFieldBuffer.getSelectionInChars(), afterEditValue.getSelection());
        if (textChangedByFilter || selectionChangedByFilter) {
            m1102syncMainBufferToTemporaryBufferTS3Rm5k$foundation_release(textFieldBuffer, null, textChangedByFilter, selectionChangedByFilter);
        } else {
            updateValueAndNotifyListeners(beforeEditValue, TextFieldBuffer.m1091toTextFieldCharSequenceudt6zUU$foundation_release$default(textFieldBuffer, 0L, afterEditValue.getComposition(), 1, null), restartImeIfContentChanges);
        }
        recordEditForUndo(beforeEditValue, getValue$foundation_release(), textFieldBuffer.getChanges(), undoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateValueAndNotifyListeners(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
        setValue(newValue);
        finishEditing();
        MutableVector<NotifyImeListener> mutableVector = this.notifyImeListeners;
        int size$iv = mutableVector.getSize();
        if (size$iv <= 0) {
            return;
        }
        int i$iv = 0;
        Object[] content$iv = mutableVector.getContent();
        do {
            NotifyImeListener it = (NotifyImeListener) content$iv[i$iv];
            it.onChange(oldValue, newValue, restartImeIfContentChanges);
            i$iv++;
        } while (i$iv < size$iv);
    }

    private final void recordEditForUndo(TextFieldCharSequence previousValue, TextFieldCharSequence postValue, TextFieldBuffer.ChangeList changes, TextFieldEditUndoBehavior undoBehavior) {
        switch (WhenMappings.$EnumSwitchMapping$0[undoBehavior.ordinal()]) {
            case 1:
                this.textUndoManager.clearHistory();
                break;
            case 2:
                TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, true);
                break;
            case 3:
                TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, false);
                break;
        }
    }

    public final void addNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.add(notifyImeListener);
    }

    public final void removeNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.remove(notifyImeListener);
    }

    /* JADX INFO: renamed from: syncMainBufferToTemporaryBuffer-TS3Rm5k$foundation_release, reason: not valid java name */
    public final void m1102syncMainBufferToTemporaryBufferTS3Rm5k$foundation_release(TextFieldBuffer textFieldBuffer, TextRange newComposition, boolean textChanged, boolean selectionChanged) {
        String bufferString = this.mainBuffer.toString();
        TextFieldCharSequence bufferState = new TextFieldCharSequence(bufferString, this.mainBuffer.m1130getSelectiond9O1mEE(), this.mainBuffer.m1129getCompositionMzsxiRA(), null, 8, null);
        boolean compositionChanged = !Intrinsics.areEqual(newComposition, this.mainBuffer.m1129getCompositionMzsxiRA());
        if (textChanged) {
            this.mainBuffer = new EditingBuffer(textFieldBuffer.toString(), textFieldBuffer.getSelectionInChars(), (DefaultConstructorMarker) null);
        } else if (selectionChanged) {
            this.mainBuffer.setSelection(TextRange.m6091getStartimpl(textFieldBuffer.getSelectionInChars()), TextRange.m6086getEndimpl(textFieldBuffer.getSelectionInChars()));
        }
        if (newComposition != null && !TextRange.m6085getCollapsedimpl(newComposition.getPackedValue())) {
            this.mainBuffer.setComposition(TextRange.m6089getMinimpl(newComposition.getPackedValue()), TextRange.m6088getMaximpl(newComposition.getPackedValue()));
        } else {
            this.mainBuffer.commitComposition();
        }
        if (textChanged || (!selectionChanged && compositionChanged)) {
            this.mainBuffer.commitComposition();
        }
        TextFieldCharSequence finalValue = new TextFieldCharSequence(textChanged ? textFieldBuffer.toString() : bufferString, this.mainBuffer.m1130getSelectiond9O1mEE(), this.mainBuffer.m1129getCompositionMzsxiRA(), null, 8, null);
        updateValueAndNotifyListeners(bufferState, finalValue, true);
    }

    /* JADX INFO: compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$Saver;", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/input/TextFieldState;", "", "()V", "restore", AppMeasurementSdk.ConditionalUserProperty.VALUE, "save", "Landroidx/compose/runtime/saveable/SaverScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Saver implements androidx.compose.runtime.saveable.Saver<TextFieldState, Object> {
        public static final int $stable = 0;
        public static final Saver INSTANCE = new Saver();

        private Saver() {
        }

        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope $this$save, TextFieldState value) {
            String string = value.getText().toString();
            Integer numValueOf = Integer.valueOf(TextRange.m6091getStartimpl(value.m1101getSelectiond9O1mEE()));
            Integer numValueOf2 = Integer.valueOf(TextRange.m6086getEndimpl(value.m1101getSelectiond9O1mEE()));
            TextUndoManager.Companion.Saver $this$save_u24lambda_u240 = TextUndoManager.Companion.Saver.INSTANCE;
            return CollectionsKt.listOf(string, numValueOf, numValueOf2, $this$save_u24lambda_u240.save($this$save, value.getTextUndoManager()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextFieldState restore(Object value) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List list = (List) value;
            Object text = list.get(0);
            Object selectionStart = list.get(1);
            Object selectionEnd = list.get(2);
            Object savedTextUndoManager = list.get(3);
            Intrinsics.checkNotNull(text, "null cannot be cast to non-null type kotlin.String");
            Intrinsics.checkNotNull(selectionStart, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) selectionStart).intValue();
            Intrinsics.checkNotNull(selectionEnd, "null cannot be cast to non-null type kotlin.Int");
            long jTextRange = TextRangeKt.TextRange(iIntValue, ((Integer) selectionEnd).intValue());
            TextUndoManager.Companion.Saver $this$restore_u24lambda_u241 = TextUndoManager.Companion.Saver.INSTANCE;
            Intrinsics.checkNotNull(savedTextUndoManager);
            TextUndoManager textUndoManagerRestore = $this$restore_u24lambda_u241.restore(savedTextUndoManager);
            Intrinsics.checkNotNull(textUndoManagerRestore);
            return new TextFieldState((String) text, jTextRange, textUndoManagerRestore, (DefaultConstructorMarker) null);
        }
    }
}
