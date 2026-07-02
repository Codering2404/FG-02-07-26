package androidx.compose.material.ripple;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CommonRipple.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ*\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0013H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\f\u0010\u001d\u001a\u00020\u0013*\u00020\u001eH\u0016R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Landroidx/compose/material/ripple/CommonRippleNode;", "Landroidx/compose/material/ripple/RippleNode;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "bounded", "", "radius", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "rippleAlpha", "Lkotlin/Function0;", "Landroidx/compose/material/ripple/RippleAlpha;", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "ripples", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "Landroidx/compose/material/ripple/RippleAnimation;", "addRipple", "", "interaction", "size", "Landroidx/compose/ui/geometry/Size;", "targetRadius", "", "addRipple-12SF9DM", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;JF)V", "onDetach", "removeRipple", "drawRipples", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CommonRippleNode extends RippleNode {
    public static final int $stable = 8;
    private final MutableScatterMap<PressInteraction.Press, RippleAnimation> ripples;

    public /* synthetic */ CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer, function0);
    }

    private CommonRippleNode(InteractionSource interactionSource, boolean bounded, float radius, ColorProducer color, Function0<RippleAlpha> function0) {
        super(interactionSource, bounded, radius, color, function0, null);
        this.ripples = new MutableScatterMap<>(0, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006a  */
    @Override // androidx.compose.material.ripple.RippleNode
    /* JADX INFO: renamed from: addRipple-12SF9DM */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1740addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction.Press r22, long r23, float r25) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            androidx.collection.MutableScatterMap<androidx.compose.foundation.interaction.PressInteraction$Press, androidx.compose.material.ripple.RippleAnimation> r2 = r0.ripples
            androidx.collection.ScatterMap r2 = (androidx.collection.ScatterMap) r2
            r3 = 0
            java.lang.Object[] r4 = r2.keys
            java.lang.Object[] r5 = r2.values
            r6 = r2
            r7 = 0
            long[] r8 = r6.metadata
            int r9 = r8.length
            int r9 = r9 + (-2)
            r10 = 0
            if (r10 > r9) goto L73
        L17:
            r11 = r8[r10]
            r13 = r11
            r15 = 0
            r16 = r2
            r17 = r3
            long r2 = ~r13
            r18 = 7
            long r2 = r2 << r18
            long r2 = r2 & r13
            r18 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r2 = r2 & r18
            int r2 = (r2 > r18 ? 1 : (r2 == r18 ? 0 : -1))
            if (r2 == 0) goto L6a
            int r2 = r10 - r9
            int r2 = ~r2
            int r2 = r2 >>> 31
            r3 = 8
            int r2 = 8 - r2
            r13 = 0
        L3a:
            if (r13 >= r2) goto L68
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r11
            r18 = 0
            r19 = 128(0x80, double:6.3E-322)
            int r19 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r19 >= 0) goto L4a
            r19 = 1
            goto L4c
        L4a:
            r19 = 0
        L4c:
            if (r19 == 0) goto L64
            int r14 = r10 << 3
            int r14 = r14 + r13
            r15 = r14
            r18 = 0
            r19 = r4[r15]
            r20 = r5[r15]
            androidx.compose.material.ripple.RippleAnimation r20 = (androidx.compose.material.ripple.RippleAnimation) r20
            androidx.compose.foundation.interaction.PressInteraction$Press r19 = (androidx.compose.foundation.interaction.PressInteraction.Press) r19
            r19 = 0
            r20.finish()
        L64:
            long r11 = r11 >> r3
            int r13 = r13 + 1
            goto L3a
        L68:
            if (r2 != r3) goto L78
        L6a:
            if (r10 == r9) goto L77
            int r10 = r10 + 1
            r2 = r16
            r3 = r17
            goto L17
        L73:
            r16 = r2
            r17 = r3
        L77:
        L78:
            boolean r2 = r0.getBounded()
            r3 = 0
            if (r2 == 0) goto L89
            long r4 = r1.getPressPosition()
            androidx.compose.ui.geometry.Offset r2 = androidx.compose.ui.geometry.Offset.m3910boximpl(r4)
            goto L8a
        L89:
            r2 = r3
        L8a:
            androidx.compose.material.ripple.RippleAnimation r4 = new androidx.compose.material.ripple.RippleAnimation
            boolean r5 = r0.getBounded()
            r6 = r25
            r4.<init>(r2, r6, r5, r3)
            androidx.collection.MutableScatterMap<androidx.compose.foundation.interaction.PressInteraction$Press, androidx.compose.material.ripple.RippleAnimation> r5 = r0.ripples
            r5.set(r1, r4)
            kotlinx.coroutines.CoroutineScope r7 = r0.getCoroutineScope()
            androidx.compose.material.ripple.CommonRippleNode$addRipple$2 r5 = new androidx.compose.material.ripple.CommonRippleNode$addRipple$2
            r5.<init>(r4, r0, r1, r3)
            r10 = r5
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = 3
            r12 = 0
            r8 = 0
            r9 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r7, r8, r9, r10, r11, r12)
            r3 = r0
            androidx.compose.ui.node.DrawModifierNode r3 = (androidx.compose.ui.node.DrawModifierNode) r3
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.CommonRippleNode.mo1740addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction$Press, long, float):void");
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void removeRipple(PressInteraction.Press interaction) {
        RippleAnimation rippleAnimation = this.ripples.get(interaction);
        if (rippleAnimation != null) {
            rippleAnimation.finish();
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void drawRipples(DrawScope $this$drawRipples) {
        long[] m$iv$iv;
        int lastIndex$iv$iv;
        float alpha;
        int i$iv$iv;
        long[] m$iv$iv2;
        int lastIndex$iv$iv2;
        float alpha2;
        int i$iv$iv2;
        int j$iv$iv;
        int lastIndex$iv$iv3;
        float alpha3 = getRippleAlpha().invoke().getPressedAlpha();
        if (!(alpha3 == 0.0f)) {
            ScatterMap this_$iv = this.ripples;
            Object[] k$iv = this_$iv.keys;
            Object[] v$iv = this_$iv.values;
            long[] m$iv$iv3 = this_$iv.metadata;
            int lastIndex$iv$iv4 = m$iv$iv3.length - 2;
            int i$iv$iv3 = 0;
            if (0 > lastIndex$iv$iv4) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv3[i$iv$iv3];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    m$iv$iv = m$iv$iv3;
                    lastIndex$iv$iv = lastIndex$iv$iv4;
                    alpha = alpha3;
                    i$iv$iv = i$iv$iv3;
                } else {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv3 - lastIndex$iv$iv4)) >>> 31);
                    long slot$iv$iv2 = slot$iv$iv;
                    int j$iv$iv2 = 0;
                    while (j$iv$iv2 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv2 & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            m$iv$iv2 = m$iv$iv3;
                            lastIndex$iv$iv2 = lastIndex$iv$iv4;
                            alpha2 = alpha3;
                            i$iv$iv2 = i$iv$iv3;
                            j$iv$iv = j$iv$iv2;
                            lastIndex$iv$iv3 = i;
                        } else {
                            int index$iv$iv = (i$iv$iv3 << 3) + j$iv$iv2;
                            Object obj = k$iv[index$iv$iv];
                            RippleAnimation ripple = (RippleAnimation) v$iv[index$iv$iv];
                            long[] m$iv$iv4 = m$iv$iv3;
                            long j = m1751getRippleColor0d7_KjU();
                            m$iv$iv2 = m$iv$iv4;
                            j$iv$iv = j$iv$iv2;
                            lastIndex$iv$iv2 = lastIndex$iv$iv4;
                            i$iv$iv2 = i$iv$iv3;
                            lastIndex$iv$iv3 = i;
                            alpha2 = alpha3;
                            ripple.m1743draw4WTKRHQ($this$drawRipples, Color.m4160copywmQWz5c(j, (14 & 1) != 0 ? Color.m4164getAlphaimpl(j) : alpha3, (14 & 2) != 0 ? Color.m4168getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4167getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4165getBlueimpl(j) : 0.0f));
                        }
                        slot$iv$iv2 >>= lastIndex$iv$iv3;
                        j$iv$iv2 = j$iv$iv + 1;
                        alpha3 = alpha2;
                        m$iv$iv3 = m$iv$iv2;
                        i = lastIndex$iv$iv3;
                        lastIndex$iv$iv4 = lastIndex$iv$iv2;
                        i$iv$iv3 = i$iv$iv2;
                    }
                    m$iv$iv = m$iv$iv3;
                    lastIndex$iv$iv = lastIndex$iv$iv4;
                    alpha = alpha3;
                    i$iv$iv = i$iv$iv3;
                    if (bitCount$iv$iv != i) {
                        return;
                    }
                }
                int lastIndex$iv$iv5 = lastIndex$iv$iv;
                int i$iv$iv4 = i$iv$iv;
                if (i$iv$iv4 == lastIndex$iv$iv5) {
                    return;
                }
                i$iv$iv3 = i$iv$iv4 + 1;
                alpha3 = alpha;
                lastIndex$iv$iv4 = lastIndex$iv$iv5;
                m$iv$iv3 = m$iv$iv;
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.ripples.clear();
    }
}
