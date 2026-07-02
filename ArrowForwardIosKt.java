package androidx.compose.material.icons.automirrored.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArrowForwardIos.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_arrowForwardIos", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ArrowForwardIos", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getArrowForwardIos", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ArrowForwardIosKt {
    private static ImageVector _arrowForwardIos;

    public static final ImageVector getArrowForwardIos(Icons.AutoMirrored.TwoTone $this$ArrowForwardIos) {
        if (_arrowForwardIos != null) {
            ImageVector imageVector = _arrowForwardIos;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        float $this$dp$iv$iv = Dp.m6625constructorimpl(24.0f);
        float $this$dp$iv$iv2 = Dp.m6625constructorimpl(24.0f);
        ImageVector.Builder $this$_get_ArrowForwardIos__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.TwoTone.ArrowForwardIos", $this$dp$iv$iv, $this$dp$iv$iv2, 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m4188getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m4514getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m4524getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(6.23f, 20.23f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(1.77f, 1.77f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(10.0f, -10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-10.0f, -10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-1.77f, 1.77f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(8.23f, 8.23f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        _arrowForwardIos = ImageVector.Builder.m4845addPathoIyEayM$default($this$_get_ArrowForwardIos__u24lambda_u241, $this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), pathFillType$iv, "", fill$iv$iv, 1.0f, null, 1.0f, 1.0f, strokeLineCap$iv$iv, strokeLineJoin$iv$iv, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        ImageVector imageVector2 = _arrowForwardIos;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
