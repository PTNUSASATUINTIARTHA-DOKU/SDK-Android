package com.doku.sdkocov2;

import android.app.Application;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest {
    @Test
    public void testApplicationLaunch() {
        Application application = ApplicationProvider.getApplicationContext();
        assertNotNull(application);
    }
}