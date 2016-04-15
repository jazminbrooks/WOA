package com.example.jazminbrooks.woa;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.example.jazminbrooks.woa.Data.WorkoutContent;
import com.firebase.client.Firebase;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void AddExerciseToWorkoutTest() throws Exception {

        ExerciseContent.Exercise ex = new ExerciseContent.Exercise();
        ex.setName("Test");
        WorkoutContent.initNewWorkout();
        WorkoutContent.addRemoveExerciseToWorkout(ex);

        assertEquals(WorkoutContent.NEW_EXERCISES.size(), 1);

    }

    @Test
    public void ExerciseAddItemTest() throws Exception {

        ExerciseContent.Exercise ex = new ExerciseContent.Exercise();
        ex.setName("Test");

        ExerciseContent.addItem(ex);

        assertEquals(ExerciseContent.ITEMS.size(), 1);

    }


}