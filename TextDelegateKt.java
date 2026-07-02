package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001av\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\f\u0010\u0018\u001a\u00020\u0010*\u00020\u0019H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001a"}, d2 = {"updateTextDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "current", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "", "minLines", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "updateTextDelegate-rm0N8CA", "(Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;ZIIILjava/util/List;)Landroidx/compose/foundation/text/TextDelegate;", "ceilToIntPx", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextDelegateKt {
    public static final int ceilToIntPx(float $this$ceilToIntPx) {
        float $this$fastRoundToInt$iv = (float) Math.ceil($this$ceilToIntPx);
        return Math.round($this$fastRoundToInt$iv);
    }

    /* JADX INFO: renamed from: updateTextDelegate-rm0N8CA$default, reason: not valid java name */
    public static /* synthetic */ TextDelegate m1048updateTextDelegaterm0N8CA$default(TextDelegate textDelegate, AnnotatedString annotatedString, TextStyle textStyle, Density density, FontFamily.Resolver resolver, boolean z, int i, int i2, int i3, List list, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            z = true;
        }
        if ((i4 & 64) != 0) {
            i = TextOverflow.INSTANCE.m6541getClipgIe3tQ8();
        }
        if ((i4 & 128) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        if ((i4 & 256) != 0) {
            i3 = 1;
        }
        return m1047updateTextDelegaterm0N8CA(textDelegate, annotatedString, textStyle, density, resolver, z, i, i2, i3, list);
    }

    /* JADX INFO: renamed from: updateTextDelegate-rm0N8CA, reason: not valid java name */
    public static final TextDelegate m1047updateTextDelegaterm0N8CA(TextDelegate current, AnnotatedString text, TextStyle style, Density density, FontFamily.Resolver fontFamilyResolver, boolean softWrap, int overflow, int maxLines, int minLines, List<AnnotatedString.Range<Placeholder>> list) {
        FontFamily.Resolver resolver;
        boolean z;
        int i;
        int i2;
        int i3;
        List<AnnotatedString.Range<Placeholder>> list2;
        if (!Intrinsics.areEqual(current.getText(), text) || !Intrinsics.areEqual(current.getStyle(), style)) {
            resolver = fontFamilyResolver;
            z = softWrap;
            i = overflow;
            i2 = maxLines;
            i3 = minLines;
            list2 = list;
        } else {
            z = softWrap;
            if (current.getSoftWrap() != z) {
                resolver = fontFamilyResolver;
                i = overflow;
                i2 = maxLines;
                i3 = minLines;
                list2 = list;
            } else {
                i = overflow;
                if (!TextOverflow.m6534equalsimpl0(current.getOverflow(), i)) {
                    resolver = fontFamilyResolver;
                    i2 = maxLines;
                    i3 = minLines;
                    list2 = list;
                } else {
                    i2 = maxLines;
                    if (current.getMaxLines() != i2) {
                        resolver = fontFamilyResolver;
                        i3 = minLines;
                        list2 = list;
                    } else {
                        i3 = minLines;
                        if (current.getMinLines() != i3 || !Intrinsics.areEqual(current.getDensity(), density)) {
                            resolver = fontFamilyResolver;
                            list2 = list;
                        } else {
                            list2 = list;
                            if (!Intrinsics.areEqual(current.getPlaceholders(), list2)) {
                                resolver = fontFamilyResolver;
                            } else {
                                resolver = fontFamilyResolver;
                                if (current.getFontFamilyResolver() == resolver) {
                                    return current;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new TextDelegate(text, style, i2, i3, z, i, density, resolver, list2, null);
    }
}
