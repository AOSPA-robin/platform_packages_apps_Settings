/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.wallpaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.VisibleForTesting;

import com.android.settings.display.WallpaperPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.android.settingslib.search.SearchIndexable;

import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class WallpaperSuggestionActivity extends StyleSuggestionActivityBase implements Indexable {

    private static final String WALLPAPER_FLAVOR_EXTRA = "com.android.launcher3.WALLPAPER_FLAVOR";
    private static final String WALLPAPER_FOCUS = "focus_wallpaper";

    @Override
    protected void addExtras(Intent intent) {
        intent.putExtra(WALLPAPER_FLAVOR_EXTRA, WALLPAPER_FOCUS);
    }

    @VisibleForTesting
    public static boolean isSuggestionComplete(Context context) {
        if (!isWallpaperServiceEnabled(context)) {
            return true;
        }
        final WallpaperManager manager = (WallpaperManager) context.getSystemService(
                WALLPAPER_SERVICE);
        return manager.getWallpaperId(WallpaperManager.FLAG_SYSTEM) > 0;
    }

    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                private static final String SUPPORT_SEARCH_INDEX_KEY = "wallpaper_type";

                @Override
                public List<SearchIndexableRaw> getRawDataToIndex(Context context,
                        boolean enabled) {
                    final List<SearchIndexableRaw> result = new ArrayList<>();
                    return result;
                }
            };
}
