package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\r\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001TB1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0014\u0010/\u001a\u00060\u0001j\u0002`\u00022\u0006\u00100\u001a\u000201H\u0016J\u0016\u0010/\u001a\u00060\u0001j\u0002`\u00022\b\u00102\u001a\u0004\u0018\u00010#H\u0016J&\u0010/\u001a\u00060\u0001j\u0002`\u00022\b\u00102\u001a\u0004\u0018\u00010#2\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001bH\u0016J\u0006\u00105\u001a\u00020#J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u00020\u001bJ\b\u00108\u001a\u000209H\u0002J \u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\u001bH\u0002J\u000e\u0010>\u001a\u0002092\u0006\u00107\u001a\u00020\u001bJ\u000e\u0010?\u001a\u0002092\u0006\u00107\u001a\u00020\u001bJ\u001e\u0010@\u001a\u0002092\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u00020#J9\u0010@\u001a\u0002092\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u00020#2\b\b\u0002\u0010A\u001a\u00020\u001b2\b\b\u0002\u0010B\u001a\u00020\u001bH\u0000¢\u0006\u0002\bCJ \u0010D\u001a\u0002092\u0006\u00107\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020\u00182\u0006\u0010F\u001a\u00020\u0018H\u0002J\u001a\u0010G\u001a\u0002092\u0006\u0010H\u001a\u00020\u001fH\u0002ø\u0001\u0000¢\u0006\u0004\bI\u0010,J\u0006\u0010J\u001a\u000209J\u0015\u0010K\u001a\u0002092\u0006\u0010L\u001a\u00020#H\u0000¢\u0006\u0002\bMJ\b\u0010N\u001a\u00020OH\u0016J(\u0010P\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\u001f2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\bR\u0010SR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\u00020\u001f8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R*\u0010)\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u001f8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b*\u0010!\"\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u001fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010.\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006U"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "initialChanges", "Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "originalValue", "offsetMappingCalculator", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/ChangeTracker;Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)V", "backingChangeTracker", "buffer", "Landroidx/compose/foundation/text/input/internal/PartialGapBuffer;", "changeTracker", "getChangeTracker", "()Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "getChanges$annotations", "()V", "getChanges", "()Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "hasSelection", "", "()Z", "length", "", "getLength", "()I", "originalSelection", "Landroidx/compose/ui/text/TextRange;", "getOriginalSelection-d9O1mEE", "()J", "originalText", "", "getOriginalText", "()Ljava/lang/CharSequence;", "getOriginalValue$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", AppMeasurementSdk.ConditionalUserProperty.VALUE, "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "selectionInChars", "J", "append", "char", "", "text", "start", "end", "asCharSequence", "charAt", "index", "clearChangeList", "", "onTextWillChange", "replaceStart", "replaceEnd", "newLength", "placeCursorAfterCharAt", "placeCursorBeforeCharAt", "replace", "textStart", "textEnd", "replace$foundation_release", "requireValidIndex", "startExclusive", "endExclusive", "requireValidRange", "range", "requireValidRange-5zc-tL8", "revertAllChanges", "setTextIfChanged", "newText", "setTextIfChanged$foundation_release", "toString", "", "toTextFieldCharSequence", "composition", "toTextFieldCharSequence-udt6zUU$foundation_release", "(JLandroidx/compose/ui/text/TextRange;)Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "ChangeList", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldBuffer implements Appendable {
    public static final int $stable = 8;
    private ChangeTracker backingChangeTracker;
    private final PartialGapBuffer buffer;
    private final OffsetMappingCalculator offsetMappingCalculator;
    private final TextFieldCharSequence originalValue;
    private long selectionInChars;

    /* JADX INFO: compiled from: TextFieldBuffer.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "", "changeCount", "", "getChangeCount", "()I", "getOriginalRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getOriginalRange--jx7JFs", "(I)J", "getRange", "getRange--jx7JFs", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ChangeList {
        int getChangeCount();

        /* JADX INFO: renamed from: getOriginalRange--jx7JFs, reason: not valid java name */
        long mo1096getOriginalRangejx7JFs(int changeIndex);

        /* JADX INFO: renamed from: getRange--jx7JFs, reason: not valid java name */
        long mo1097getRangejx7JFs(int changeIndex);
    }

    public static /* synthetic */ void getChanges$annotations() {
    }

    public TextFieldBuffer(TextFieldCharSequence initialValue, ChangeTracker initialChanges, TextFieldCharSequence originalValue, OffsetMappingCalculator offsetMappingCalculator) {
        this.originalValue = originalValue;
        this.offsetMappingCalculator = offsetMappingCalculator;
        this.buffer = new PartialGapBuffer(initialValue);
        this.backingChangeTracker = initialChanges != null ? new ChangeTracker(initialChanges) : null;
        this.selectionInChars = initialValue.getSelection();
    }

    public /* synthetic */ TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldCharSequence, (i & 2) != 0 ? null : changeTracker, (i & 4) != 0 ? textFieldCharSequence : textFieldCharSequence2, (i & 8) != 0 ? null : offsetMappingCalculator);
    }

    /* JADX INFO: renamed from: getOriginalValue$foundation_release, reason: from getter */
    public final TextFieldCharSequence getOriginalValue() {
        return this.originalValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ChangeTracker getChangeTracker() {
        ChangeTracker changeTracker = this.backingChangeTracker;
        if (changeTracker != null) {
            return changeTracker;
        }
        ChangeTracker changeTracker2 = new ChangeTracker(null, 1, 0 == true ? 1 : 0);
        this.backingChangeTracker = changeTracker2;
        return changeTracker2;
    }

    public final int getLength() {
        return this.buffer.length();
    }

    public final CharSequence getOriginalText() {
        return this.originalValue.getText();
    }

    /* JADX INFO: renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name */
    public final long m1092getOriginalSelectiond9O1mEE() {
        return this.originalValue.getSelection();
    }

    public final ChangeList getChanges() {
        return getChangeTracker();
    }

    public final boolean hasSelection() {
        return !TextRange.m6085getCollapsedimpl(getSelectionInChars());
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelectionInChars() {
        return this.selectionInChars;
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1094setSelection5zctL8(long value) {
        m1090requireValidRange5zctL8(value);
        this.selectionInChars = value;
    }

    public final void replace(int start, int end, CharSequence text) {
        replace$foundation_release(start, end, text, 0, text.length());
    }

    public static /* synthetic */ void replace$foundation_release$default(TextFieldBuffer textFieldBuffer, int i, int i2, CharSequence charSequence, int i3, int i4, int i5, Object obj) {
        int i6;
        int length;
        if ((i5 & 8) == 0) {
            i6 = i3;
        } else {
            i6 = 0;
        }
        if ((i5 & 16) == 0) {
            length = i4;
        } else {
            length = charSequence.length();
        }
        textFieldBuffer.replace$foundation_release(i, i2, charSequence, i6, length);
    }

    public final void replace$foundation_release(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (start <= end) {
            if (!(textStart <= textEnd)) {
                throw new IllegalArgumentException(("Expected textStart=" + textStart + " <= textEnd=" + textEnd).toString());
            }
            onTextWillChange(start, end, textEnd - textStart);
            this.buffer.replace(start, end, text, textStart, textEnd);
            return;
        }
        throw new IllegalArgumentException(("Expected start=" + start + " <= end=" + end).toString());
    }

    public final void setTextIfChanged$foundation_release(CharSequence newText) {
        CharSequence a$iv = this.buffer;
        int aStart$iv = 0;
        int aEnd$iv = a$iv.length();
        int bStart$iv = 0;
        int bEnd$iv = newText.length();
        if (a$iv.length() > 0) {
            if (newText.length() > 0) {
                boolean prefixFound$iv = false;
                boolean suffixFound$iv = false;
                while (true) {
                    if (!prefixFound$iv) {
                        if (a$iv.charAt(aStart$iv) == newText.charAt(bStart$iv)) {
                            aStart$iv++;
                            bStart$iv++;
                        } else {
                            prefixFound$iv = true;
                        }
                    }
                    if (!suffixFound$iv) {
                        if (a$iv.charAt(aEnd$iv - 1) == newText.charAt(bEnd$iv - 1)) {
                            aEnd$iv--;
                            bEnd$iv--;
                        } else {
                            suffixFound$iv = true;
                        }
                    }
                    if (aStart$iv >= aEnd$iv || bStart$iv >= bEnd$iv || (prefixFound$iv && suffixFound$iv)) {
                        break;
                    }
                }
            }
        }
        if (aStart$iv >= aEnd$iv && bStart$iv >= bEnd$iv) {
            return;
        }
        int thisStart = aStart$iv;
        int thisEnd = aEnd$iv;
        int newStart = bStart$iv;
        int newEnd = bEnd$iv;
        replace$foundation_release(thisStart, thisEnd, newText, newStart, newEnd);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text) {
        TextFieldBuffer $this$append_u24lambda_u245 = this;
        if (text != null) {
            $this$append_u24lambda_u245.onTextWillChange($this$append_u24lambda_u245.getLength(), $this$append_u24lambda_u245.getLength(), text.length());
            PartialGapBuffer.replace$default($this$append_u24lambda_u245.buffer, $this$append_u24lambda_u245.buffer.length(), $this$append_u24lambda_u245.buffer.length(), text, 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text, int start, int end) {
        TextFieldBuffer $this$append_u24lambda_u246 = this;
        if (text != null) {
            $this$append_u24lambda_u246.onTextWillChange($this$append_u24lambda_u246.getLength(), $this$append_u24lambda_u246.getLength(), end - start);
            PartialGapBuffer.replace$default($this$append_u24lambda_u246.buffer, $this$append_u24lambda_u246.buffer.length(), $this$append_u24lambda_u246.buffer.length(), text.subSequence(start, end), 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char c) {
        TextFieldBuffer $this$append_u24lambda_u247 = this;
        $this$append_u24lambda_u247.onTextWillChange($this$append_u24lambda_u247.getLength(), $this$append_u24lambda_u247.getLength(), 1);
        PartialGapBuffer.replace$default($this$append_u24lambda_u247.buffer, $this$append_u24lambda_u247.buffer.length(), $this$append_u24lambda_u247.buffer.length(), String.valueOf(c), 0, 0, 24, null);
        return this;
    }

    private final void onTextWillChange(int replaceStart, int replaceEnd, int newLength) {
        int selEnd;
        getChangeTracker().trackChange(replaceStart, replaceEnd, newLength);
        OffsetMappingCalculator offsetMappingCalculator = this.offsetMappingCalculator;
        if (offsetMappingCalculator != null) {
            offsetMappingCalculator.recordEditOperation(replaceStart, replaceEnd, newLength);
        }
        int start = Math.min(replaceStart, replaceEnd);
        int end = Math.max(replaceStart, replaceEnd);
        int selStart = TextRange.m6089getMinimpl(getSelectionInChars());
        int selEnd2 = TextRange.m6088getMaximpl(getSelectionInChars());
        if (selEnd2 < start) {
            return;
        }
        if (selStart <= start && end <= selEnd2) {
            int diff = newLength - (end - start);
            if (selStart == selEnd2) {
                selStart += diff;
            }
            selEnd = selEnd2 + diff;
        } else if (selStart > start && selEnd2 < end) {
            selStart = start + newLength;
            selEnd = start + newLength;
        } else if (selStart >= end) {
            int diff2 = newLength - (end - start);
            selStart += diff2;
            selEnd = selEnd2 + diff2;
        } else if (start < selStart) {
            selStart = start + newLength;
            selEnd = selEnd2 + (newLength - (end - start));
        } else {
            selEnd = start;
        }
        this.selectionInChars = TextRangeKt.TextRange(selStart, selEnd);
    }

    public final char charAt(int index) {
        return this.buffer.charAt(index);
    }

    public String toString() {
        return this.buffer.toString();
    }

    public final CharSequence asCharSequence() {
        return this.buffer;
    }

    private final void clearChangeList() {
        getChangeTracker().clearChanges();
    }

    public final void revertAllChanges() {
        replace(0, getLength(), this.originalValue.toString());
        m1094setSelection5zctL8(this.originalValue.getSelection());
        clearChangeList();
    }

    public final void placeCursorBeforeCharAt(int index) {
        requireValidIndex(index, true, false);
        this.selectionInChars = TextRangeKt.TextRange(index);
    }

    public final void placeCursorAfterCharAt(int index) {
        requireValidIndex(index, false, true);
        this.selectionInChars = TextRangeKt.TextRange(RangesKt.coerceAtMost(index + 1, getLength()));
    }

    /* JADX INFO: renamed from: toTextFieldCharSequence-udt6zUU$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1091toTextFieldCharSequenceudt6zUU$foundation_release$default(TextFieldBuffer textFieldBuffer, long j, TextRange textRange, int i, Object obj) {
        if ((i & 1) != 0) {
            j = textFieldBuffer.getSelectionInChars();
        }
        if ((i & 2) != 0) {
            textRange = null;
        }
        return textFieldBuffer.m1095toTextFieldCharSequenceudt6zUU$foundation_release(j, textRange);
    }

    /* JADX INFO: renamed from: toTextFieldCharSequence-udt6zUU$foundation_release, reason: not valid java name */
    public final TextFieldCharSequence m1095toTextFieldCharSequenceudt6zUU$foundation_release(long selection, TextRange composition) {
        return new TextFieldCharSequence(this.buffer.toString(), selection, composition, null, 8, null);
    }

    private final void requireValidIndex(int index, boolean startExclusive, boolean endExclusive) {
        boolean z = false;
        int start = startExclusive ? 0 : -1;
        int end = getLength();
        if (!endExclusive) {
            end++;
        }
        if (start <= index && index < end) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Expected " + index + " to be in [" + start + ", " + end + ')').toString());
        }
    }

    /* JADX INFO: renamed from: requireValidRange-5zc-tL8, reason: not valid java name */
    private final void m1090requireValidRange5zctL8(long range) {
        long validRange = TextRangeKt.TextRange(0, getLength());
        if (!TextRange.m6081contains5zctL8(validRange, range)) {
            throw new IllegalArgumentException(("Expected " + ((Object) TextRange.m6094toStringimpl(range)) + " to be in " + ((Object) TextRange.m6094toStringimpl(validRange))).toString());
        }
    }
}
