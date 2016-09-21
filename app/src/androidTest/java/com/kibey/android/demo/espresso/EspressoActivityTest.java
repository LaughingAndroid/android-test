package com.kibey.android.demo.espresso;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.kibey.android.demo.MainActivity;
import com.kibey.android.demo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * 这个类的所有代码,当然除了这里的注释全部都是Espresso Test Recorder自动创建的。
 * 当前录制的操作是新增五条内容,删除第3和第4条内容。
 * 详细参考:
 * <a href="https://developer.android.com/studio/test/espresso-test-recorder.html">https://developer.android.com/studio/test/espresso-test-recorder.html</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void espressoActivityTest() {
        ViewInteraction recyclerView = onView(
                allOf(ViewMatchers.withId(R.id.content_rv), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("新增"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.item_espresso), withText("新增内容3"),
                        withParent(allOf(withId(R.id.item_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.item_espresso), withText("新增内容4"),
                        withParent(allOf(withId(R.id.item_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("删除"),
                        withParent(allOf(withId(R.id.action_container),
                                withParent(withId(R.id.activity_espresso)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

    }

}
