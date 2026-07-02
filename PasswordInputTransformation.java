package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BasicSecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0011\u001a\u00020\u0004J\f\u0010\u0012\u001a\u00020\u0004*\u00020\u0013H\u0016R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text/PasswordInputTransformation;", "Landroidx/compose/foundation/text/input/InputTransformation;", "scheduleHide", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "<set-?>", "", "revealCodepointIndex", "getRevealCodepointIndex$foundation_release", "()I", "setRevealCodepointIndex", "(I)V", "revealCodepointIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "getScheduleHide", "()Lkotlin/jvm/functions/Function0;", "hide", "transformInput", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PasswordInputTransformation implements InputTransformation {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: revealCodepointIndex$delegate, reason: from kotlin metadata */
    private final MutableIntState revealCodepointIndex = SnapshotIntStateKt.mutableIntStateOf(-1);
    private final Function0<Unit> scheduleHide;

    public PasswordInputTransformation(Function0<Unit> function0) {
        this.scheduleHide = function0;
    }

    public final Function0<Unit> getScheduleHide() {
        return this.scheduleHide;
    }

    private final void setRevealCodepointIndex(int i) {
        MutableIntState $this$setValue$iv = this.revealCodepointIndex;
        $this$setValue$iv.setIntValue(i);
    }

    public final int getRevealCodepointIndex$foundation_release() {
        IntState $this$getValue$iv = this.revealCodepointIndex;
        return $this$getValue$iv.getIntValue();
    }

    @Override // androidx.compose.foundation.text.input.InputTransformation
    public void transformInput(TextFieldBuffer $this$transformInput) {
        boolean singleCharacterInsertion = $this$transformInput.getChanges().getChangeCount() == 1 && TextRange.m6087getLengthimpl($this$transformInput.getChanges().mo1097getRangejx7JFs(0)) == 1 && TextRange.m6087getLengthimpl($this$transformInput.getChanges().mo1096getOriginalRangejx7JFs(0)) == 0;
        if (!singleCharacterInsertion || $this$transformInput.hasSelection()) {
            setRevealCodepointIndex(-1);
            return;
        }
        int insertionPoint = TextRange.m6089getMinimpl($this$transformInput.getChanges().mo1097getRangejx7JFs(0));
        if (getRevealCodepointIndex$foundation_release() != insertionPoint) {
            this.scheduleHide.invoke();
            setRevealCodepointIndex(insertionPoint);
        }
    }

    public final void hide() {
        setRevealCodepointIndex(-1);
    }
}
