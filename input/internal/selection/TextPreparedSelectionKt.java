package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: TextPreparedSelection.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¨\u0006\t"}, d2 = {"calculateAdjacentCursorPosition", "", "transformedText", "", "cursor", "forward", "", "state", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextPreparedSelectionKt {

    /* JADX INFO: compiled from: TextPreparedSelection.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final int calculateAdjacentCursorPosition(String transformedText, int cursor, boolean forward, TransformedTextFieldState state) {
        int proposedCursor;
        IndexTransformationType type$iv;
        if (forward) {
            proposedCursor = StringHelpers_androidKt.findFollowingBreak(transformedText, cursor);
        } else {
            proposedCursor = StringHelpers_androidKt.findPrecedingBreak(transformedText, cursor);
        }
        if (proposedCursor == -1) {
            return cursor;
        }
        long untransformed$iv = state.m1215mapFromTransformedjx7JFs(proposedCursor);
        long retransformed$iv = state.m1218mapToTransformedGEjPoXI(untransformed$iv);
        if (TextRange.m6085getCollapsedimpl(untransformed$iv) && TextRange.m6085getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Untransformed;
        } else if (!TextRange.m6085getCollapsedimpl(untransformed$iv) && !TextRange.m6085getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Replacement;
        } else if (TextRange.m6085getCollapsedimpl(untransformed$iv) && !TextRange.m6085getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Insertion;
        } else {
            type$iv = IndexTransformationType.Deletion;
        }
        IndexTransformationType type = type$iv;
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
            case 2:
                return proposedCursor;
            case 3:
                return forward ? TextRange.m6086getEndimpl(retransformed$iv) : TextRange.m6091getStartimpl(retransformed$iv);
            case 4:
                if (forward) {
                    if (proposedCursor == TextRange.m6091getStartimpl(retransformed$iv)) {
                        state.setSelectionWedgeAffinity(new SelectionWedgeAffinity(WedgeAffinity.Start));
                        return proposedCursor;
                    }
                    state.setSelectionWedgeAffinity(new SelectionWedgeAffinity(WedgeAffinity.End));
                    return cursor;
                }
                if (proposedCursor == TextRange.m6086getEndimpl(retransformed$iv)) {
                    state.setSelectionWedgeAffinity(new SelectionWedgeAffinity(WedgeAffinity.End));
                    return proposedCursor;
                }
                state.setSelectionWedgeAffinity(new SelectionWedgeAffinity(WedgeAffinity.Start));
                return cursor;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
