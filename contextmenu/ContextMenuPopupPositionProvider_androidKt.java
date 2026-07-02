package androidx.compose.foundation.contextmenu;

import kotlin.Metadata;

/* JADX INFO: compiled from: ContextMenuPopupPositionProvider.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a*\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0001\u001a \u0010\b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a(\u0010\f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\r"}, d2 = {"alignEndEdges", "", "popupLength", "windowLength", "closeAffinity", "", "alignPopupAxis", "position", "alignPopupEndEdgeToPosition", "alignPopupStartEdgeToPosition", "alignStartEdges", "popupFitsBetweenPositionAndEndEdge", "popupFitsBetweenPositionAndStartEdge", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextMenuPopupPositionProvider_androidKt {
    public static /* synthetic */ int alignPopupAxis$default(int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            z = true;
        }
        return alignPopupAxis(i, i2, i3, z);
    }

    public static final int alignPopupAxis(int position, int popupLength, int windowLength, boolean closeAffinity) {
        if (popupLength >= windowLength) {
            return alignStartEdges(popupLength, windowLength, closeAffinity);
        }
        if (popupFitsBetweenPositionAndEndEdge(position, popupLength, windowLength, closeAffinity)) {
            return alignPopupStartEdgeToPosition(position, popupLength, closeAffinity);
        }
        if (popupFitsBetweenPositionAndStartEdge(position, popupLength, windowLength, closeAffinity)) {
            return alignPopupEndEdgeToPosition(position, popupLength, closeAffinity);
        }
        return alignEndEdges(popupLength, windowLength, closeAffinity);
    }

    private static final boolean popupFitsBetweenPositionAndStartEdge(int position, int popupLength, int windowLength, boolean closeAffinity) {
        return closeAffinity ? popupLength <= position : windowLength - popupLength > position;
    }

    private static final boolean popupFitsBetweenPositionAndEndEdge(int position, int popupLength, int windowLength, boolean closeAffinity) {
        return popupFitsBetweenPositionAndStartEdge(position, popupLength, windowLength, !closeAffinity);
    }

    private static final int alignPopupStartEdgeToPosition(int position, int popupLength, boolean closeAffinity) {
        return closeAffinity ? position : position - popupLength;
    }

    private static final int alignPopupEndEdgeToPosition(int position, int popupLength, boolean closeAffinity) {
        return alignPopupStartEdgeToPosition(position, popupLength, !closeAffinity);
    }

    private static final int alignStartEdges(int popupLength, int windowLength, boolean closeAffinity) {
        if (closeAffinity) {
            return 0;
        }
        return windowLength - popupLength;
    }

    private static final int alignEndEdges(int popupLength, int windowLength, boolean closeAffinity) {
        return alignStartEdges(popupLength, windowLength, !closeAffinity);
    }
}
