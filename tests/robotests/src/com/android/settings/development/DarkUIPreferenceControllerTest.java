/*
 * Copyright (C) 2019 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.settings.development;

import static com.google.common.truth.Truth.assertThat;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Handler;
import android.os.IPowerManager;
import android.os.PowerManager;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class DarkUIPreferenceControllerTest {

    private DarkUIPreferenceController mController;
    private Context mContext;
    @Mock
    private Fragment mFragment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mContext = spy(RuntimeEnvironment.application);
        mController = spy(new DarkUIPreferenceController(mContext, "dark_ui_mode"));
        mController.setParentFragment(mFragment);
        mController.mPreference = new SwitchPreference(mContext);
        mController.onStart();
    }

    @Test
    public void batterySaverToggles_disabledStateUpdates() {
        doReturn(true).when(mController).isPowerSaveMode();
        mController.updateEnabledStateIfNeeded();
        assertThat(mController.mPreference.isEnabled()).isFalse();

        doReturn(false).when(mController).isPowerSaveMode();
        mController.updateEnabledStateIfNeeded();
        assertThat(mController.mPreference.isEnabled()).isTrue();

        doReturn(true).when(mController).isPowerSaveMode();
        mController.updateEnabledStateIfNeeded();
        assertThat(mController.mPreference.isEnabled()).isFalse();
    }
}
