package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextSelectionDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¨\u0006\f"}, d2 = {"getSelectionHandleCoordinates", "Landroidx/compose/ui/geometry/Offset;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "offset", "", "isStart", "", "areHandlesCrossed", "(Landroidx/compose/ui/text/TextLayoutResult;IZZ)J", "getHorizontalPosition", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextSelectionDelegateKt {
    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int offset, boolean isStart, boolean areHandlesCrossed) {
        int line = textLayoutResult.getLineForOffset(offset);
        if (line >= textLayoutResult.getLineCount()) {
            return Offset.INSTANCE.m3936getUnspecifiedF1C5BW0();
        }
        float x = RangesKt.coerceIn(getHorizontalPosition(textLayoutResult, offset, isStart, areHandlesCrossed), 0.0f, IntSize.m6795getWidthimpl(textLayoutResult.getSize()));
        float y = RangesKt.coerceIn(textLayoutResult.getLineBottom(line), 0.0f, IntSize.m6794getHeightimpl(textLayoutResult.getSize()));
        return OffsetKt.Offset(x, y);
    }

    public static final float getHorizontalPosition(TextLayoutResult $this$getHorizontalPosition, int offset, boolean isStart, boolean areHandlesCrossed) {
        int offsetToCheck = ((!isStart || areHandlesCrossed) && (isStart || !areHandlesCrossed)) ? Math.max(offset - 1, 0) : offset;
        ResolvedTextDirection bidiRunDirection = $this$getHorizontalPosition.getBidiRunDirection(offsetToCheck);
        ResolvedTextDirection paragraphDirection = $this$getHorizontalPosition.getParagraphDirection(offset);
        return $this$getHorizontalPosition.getHorizontalPosition(offset, bidiRunDirection == paragraphDirection);
    }
}
