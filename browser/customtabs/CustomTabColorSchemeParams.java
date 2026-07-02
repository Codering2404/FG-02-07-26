package androidx.browser.customtabs;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class CustomTabColorSchemeParams {
    public final Integer navigationBarColor;
    public final Integer navigationBarDividerColor;
    public final Integer secondaryToolbarColor;
    public final Integer toolbarColor;

    CustomTabColorSchemeParams(Integer toolbarColor, Integer secondaryToolbarColor, Integer navigationBarColor, Integer navigationBarDividerColor) {
        this.toolbarColor = toolbarColor;
        this.secondaryToolbarColor = secondaryToolbarColor;
        this.navigationBarColor = navigationBarColor;
        this.navigationBarDividerColor = navigationBarDividerColor;
    }

    Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.toolbarColor != null) {
            bundle.putInt(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, this.toolbarColor.intValue());
        }
        if (this.secondaryToolbarColor != null) {
            bundle.putInt(CustomTabsIntent.EXTRA_SECONDARY_TOOLBAR_COLOR, this.secondaryToolbarColor.intValue());
        }
        if (this.navigationBarColor != null) {
            bundle.putInt(CustomTabsIntent.EXTRA_NAVIGATION_BAR_COLOR, this.navigationBarColor.intValue());
        }
        if (this.navigationBarDividerColor != null) {
            bundle.putInt(CustomTabsIntent.EXTRA_NAVIGATION_BAR_DIVIDER_COLOR, this.navigationBarDividerColor.intValue());
        }
        return bundle;
    }

    static CustomTabColorSchemeParams fromBundle(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle(0);
        }
        return new CustomTabColorSchemeParams((Integer) bundle.get(CustomTabsIntent.EXTRA_TOOLBAR_COLOR), (Integer) bundle.get(CustomTabsIntent.EXTRA_SECONDARY_TOOLBAR_COLOR), (Integer) bundle.get(CustomTabsIntent.EXTRA_NAVIGATION_BAR_COLOR), (Integer) bundle.get(CustomTabsIntent.EXTRA_NAVIGATION_BAR_DIVIDER_COLOR));
    }

    CustomTabColorSchemeParams withDefaults(CustomTabColorSchemeParams defaults) {
        return new CustomTabColorSchemeParams(this.toolbarColor == null ? defaults.toolbarColor : this.toolbarColor, this.secondaryToolbarColor == null ? defaults.secondaryToolbarColor : this.secondaryToolbarColor, this.navigationBarColor == null ? defaults.navigationBarColor : this.navigationBarColor, this.navigationBarDividerColor == null ? defaults.navigationBarDividerColor : this.navigationBarDividerColor);
    }

    public static final class Builder {
        private Integer mNavigationBarColor;
        private Integer mNavigationBarDividerColor;
        private Integer mSecondaryToolbarColor;
        private Integer mToolbarColor;

        public Builder setToolbarColor(int color) {
            this.mToolbarColor = Integer.valueOf((-16777216) | color);
            return this;
        }

        public Builder setSecondaryToolbarColor(int color) {
            this.mSecondaryToolbarColor = Integer.valueOf(color);
            return this;
        }

        public Builder setNavigationBarColor(int color) {
            this.mNavigationBarColor = Integer.valueOf((-16777216) | color);
            return this;
        }

        public Builder setNavigationBarDividerColor(int color) {
            this.mNavigationBarDividerColor = Integer.valueOf(color);
            return this;
        }

        public CustomTabColorSchemeParams build() {
            return new CustomTabColorSchemeParams(this.mToolbarColor, this.mSecondaryToolbarColor, this.mNavigationBarColor, this.mNavigationBarDividerColor);
        }
    }
}
