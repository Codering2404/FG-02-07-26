package androidx.compose.foundation.content.internal;

import android.view.DragEvent;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.platform.AndroidClipboardManager_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReceiveContentDragAndDropNode.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0000¨\u0006\n"}, d2 = {"ReceiveContentDragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "dragAndDropRequestPermission", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "", "toTransferableContent", "Landroidx/compose/foundation/content/TransferableContent;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReceiveContentDragAndDropNode_androidKt {
    public static final DragAndDropModifierNode ReceiveContentDragAndDropNode(final ReceiveContentConfiguration receiveContentConfiguration, final Function1<? super DragAndDropEvent, Unit> function1) {
        return DragAndDropNodeKt.DragAndDropModifierNode(new Function1<DragAndDropEvent, Boolean>() { // from class: androidx.compose.foundation.content.internal.ReceiveContentDragAndDropNode_androidKt.ReceiveContentDragAndDropNode.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(DragAndDropEvent it) {
                return true;
            }
        }, new DragAndDropTarget() { // from class: androidx.compose.foundation.content.internal.ReceiveContentDragAndDropNode_androidKt.ReceiveContentDragAndDropNode.2
            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onStarted(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragStart();
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onEnded(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragEnd();
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onEntered(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragEnter();
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onExited(DragAndDropEvent event) {
                receiveContentConfiguration.getReceiveContentListener().onDragExit();
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public boolean onDrop(DragAndDropEvent event) {
                function1.invoke(event);
                TransferableContent original = ReceiveContentDragAndDropNode_androidKt.toTransferableContent(event);
                TransferableContent remaining = receiveContentConfiguration.getReceiveContentListener().onReceive(original);
                return !Intrinsics.areEqual(original, remaining);
            }
        });
    }

    public static final TransferableContent toTransferableContent(DragAndDropEvent $this$toTransferableContent) {
        DragEvent $this$toTransferableContent_u24lambda_u240 = DragAndDrop_androidKt.toAndroidDragEvent($this$toTransferableContent);
        return new TransferableContent(AndroidClipboardManager_androidKt.toClipEntry($this$toTransferableContent_u24lambda_u240.getClipData()), AndroidClipboardManager_androidKt.toClipMetadata($this$toTransferableContent_u24lambda_u240.getClipDescription()), TransferableContent.Source.INSTANCE.m329getDragAndDropkB6V9T0(), null, 8, null);
    }
}
