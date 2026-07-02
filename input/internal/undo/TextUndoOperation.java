package androidx.compose.foundation.text.input.internal.undo;

import androidx.compose.foundation.text.UndoManager_jvmKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextUndoOperation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/foundation/text/input/internal/undo/TextUndoOperation;", "", "index", "", "preText", "", "postText", "preSelection", "Landroidx/compose/ui/text/TextRange;", "postSelection", "timeInMillis", "", "canMerge", "", "(ILjava/lang/String;Ljava/lang/String;JJJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCanMerge", "()Z", "deletionType", "Landroidx/compose/foundation/text/input/internal/undo/TextDeleteType;", "getDeletionType", "()Landroidx/compose/foundation/text/input/internal/undo/TextDeleteType;", "getIndex", "()I", "getPostSelection-d9O1mEE", "()J", "J", "getPostText", "()Ljava/lang/String;", "getPreSelection-d9O1mEE", "getPreText", "textEditType", "Landroidx/compose/foundation/text/input/internal/undo/TextEditType;", "getTextEditType", "()Landroidx/compose/foundation/text/input/internal/undo/TextEditType;", "getTimeInMillis", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextUndoOperation {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<TextUndoOperation, Object> Saver = new Saver<TextUndoOperation, Object>() { // from class: androidx.compose.foundation.text.input.internal.undo.TextUndoOperation$Companion$Saver$1
        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope $this$save, TextUndoOperation value) {
            return CollectionsKt.listOf(Integer.valueOf(value.getIndex()), value.getPreText(), value.getPostText(), Integer.valueOf(TextRange.m6091getStartimpl(value.getPreSelection())), Integer.valueOf(TextRange.m6086getEndimpl(value.getPreSelection())), Integer.valueOf(TextRange.m6091getStartimpl(value.getPostSelection())), Integer.valueOf(TextRange.m6086getEndimpl(value.getPostSelection())), Long.valueOf(value.getTimeInMillis()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextUndoOperation restore(Object value) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List $this$restore_u24lambda_u240 = (List) value;
            Object obj = $this$restore_u24lambda_u240.get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) obj).intValue();
            Object obj2 = $this$restore_u24lambda_u240.get(1);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            Object obj3 = $this$restore_u24lambda_u240.get(2);
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.String");
            Object obj4 = $this$restore_u24lambda_u240.get(3);
            Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue2 = ((Integer) obj4).intValue();
            Object obj5 = $this$restore_u24lambda_u240.get(4);
            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Int");
            long jTextRange = TextRangeKt.TextRange(iIntValue2, ((Integer) obj5).intValue());
            Object obj6 = $this$restore_u24lambda_u240.get(5);
            Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue3 = ((Integer) obj6).intValue();
            Object obj7 = $this$restore_u24lambda_u240.get(6);
            Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.Int");
            long jTextRange2 = TextRangeKt.TextRange(iIntValue3, ((Integer) obj7).intValue());
            Object obj8 = $this$restore_u24lambda_u240.get(7);
            Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Long");
            return new TextUndoOperation(iIntValue, (String) obj2, (String) obj3, jTextRange, jTextRange2, ((Long) obj8).longValue(), false, 64, null);
        }
    };
    private final boolean canMerge;
    private final int index;
    private final long postSelection;
    private final String postText;
    private final long preSelection;
    private final String preText;
    private final TextEditType textEditType;
    private final long timeInMillis;

    public /* synthetic */ TextUndoOperation(int i, String str, String str2, long j, long j2, long j3, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, j, j2, j3, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private TextUndoOperation(int r4, java.lang.String r5, java.lang.String r6, long r7, long r9, long r11, boolean r13) {
        /*
            r3 = this;
            r3.<init>()
            r3.index = r4
            r3.preText = r5
            r3.postText = r6
            r3.preSelection = r7
            r3.postSelection = r9
            r3.timeInMillis = r11
            r3.canMerge = r13
            java.lang.String r0 = r3.preText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L20
            r0 = r1
            goto L21
        L20:
            r0 = r2
        L21:
            if (r0 == 0) goto L3b
            java.lang.String r0 = r3.postText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L2f
            r0 = r1
            goto L30
        L2f:
            r0 = r2
        L30:
            if (r0 != 0) goto L33
            goto L3b
        L33:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Either pre or post text must not be empty"
            r0.<init>(r1)
            throw r0
        L3b:
            java.lang.String r0 = r3.preText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L47
            r0 = r1
            goto L48
        L47:
            r0 = r2
        L48:
            if (r0 == 0) goto L5c
            java.lang.String r0 = r3.postText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L56
            r0 = r1
            goto L57
        L56:
            r0 = r2
        L57:
            if (r0 == 0) goto L5c
            androidx.compose.foundation.text.input.internal.undo.TextEditType r0 = androidx.compose.foundation.text.input.internal.undo.TextEditType.Insert
            goto L7e
        L5c:
            java.lang.String r0 = r3.preText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L68
            r0 = r1
            goto L69
        L68:
            r0 = r2
        L69:
            if (r0 == 0) goto L7c
            java.lang.String r0 = r3.postText
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L76
            goto L77
        L76:
            r1 = r2
        L77:
            if (r1 == 0) goto L7c
            androidx.compose.foundation.text.input.internal.undo.TextEditType r0 = androidx.compose.foundation.text.input.internal.undo.TextEditType.Delete
            goto L7e
        L7c:
            androidx.compose.foundation.text.input.internal.undo.TextEditType r0 = androidx.compose.foundation.text.input.internal.undo.TextEditType.Replace
        L7e:
            r3.textEditType = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.undo.TextUndoOperation.<init>(int, java.lang.String, java.lang.String, long, long, long, boolean):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextUndoOperation(int i, String str, String str2, long j, long j2, long j3, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        long jTimeNowMillis;
        boolean z2;
        if ((i2 & 32) == 0) {
            jTimeNowMillis = j3;
        } else {
            jTimeNowMillis = UndoManager_jvmKt.timeNowMillis();
        }
        if ((i2 & 64) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        this(i, str, str2, j, j2, jTimeNowMillis, z2, null);
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getPreText() {
        return this.preText;
    }

    public final String getPostText() {
        return this.postText;
    }

    /* JADX INFO: renamed from: getPreSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPreSelection() {
        return this.preSelection;
    }

    /* JADX INFO: renamed from: getPostSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPostSelection() {
        return this.postSelection;
    }

    public final long getTimeInMillis() {
        return this.timeInMillis;
    }

    public final boolean getCanMerge() {
        return this.canMerge;
    }

    public final TextEditType getTextEditType() {
        return this.textEditType;
    }

    public final TextDeleteType getDeletionType() {
        if (this.textEditType == TextEditType.Delete && TextRange.m6085getCollapsedimpl(this.postSelection)) {
            boolean zM6085getCollapsedimpl = TextRange.m6085getCollapsedimpl(this.preSelection);
            long j = this.preSelection;
            if (zM6085getCollapsedimpl) {
                if (TextRange.m6091getStartimpl(j) > TextRange.m6091getStartimpl(this.postSelection)) {
                    return TextDeleteType.Start;
                }
                return TextDeleteType.End;
            }
            if (TextRange.m6091getStartimpl(j) == TextRange.m6091getStartimpl(this.postSelection) && TextRange.m6091getStartimpl(this.preSelection) == this.index) {
                return TextDeleteType.Inner;
            }
            return TextDeleteType.NotByUser;
        }
        return TextDeleteType.NotByUser;
    }

    /* JADX INFO: compiled from: TextUndoOperation.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/undo/TextUndoOperation$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/input/internal/undo/TextUndoOperation;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<TextUndoOperation, Object> getSaver() {
            return TextUndoOperation.Saver;
        }
    }
}
