package com.example.jazminbrooks.woa.Data;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample userid for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WorkoutContent {

    // List of all Workouts for all users
    public static List<Workout> ALL_ITEMS = new ArrayList<>();
    // List of all workouts for current user
    public static List<Workout> USER_ITEMS = new ArrayList<>();

    // Map of Workout ID's to Workout Objects
    public static Map<String, Workout> ALL_ITEMS_MAP = new HashMap();
    // Map of Workouts to List of Exercise Objects in the Workout
    public static Map<Workout, List<ExerciseContent.Exercise>> WORKOUT_EXERCISES = new HashMap<>();

    // List of Exercises in Current Workout
    public static List<ExerciseContent.Exercise> EXERCISE_LIST = new ArrayList<>();
    public static Workout CURRENT_WORKOUT = new Workout();

    // Building Workout
    public static Workout NEW_WORKOUT;
    public static List<ExerciseContent.Exercise> NEW_EXERCISES;
    private static int NUM_WORKOUTS;

    // Boolean for Firebase Data Initialization
    public static boolean INIT;

    // Current User ID
    public static String USERID;

    public static Firebase myFirebaseRef;

    static {
        // Add some sample items.
        //for (int i = 1; i <= COUNT; i++) {
            //addItem(getExerciseItem(i));
        //}

        INIT = false;
        USERID = "none";
    }

    public static void updateItems() {

        Query queryRef = myFirebaseRef.child("Workouts").orderByValue();

        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ALL_ITEMS = new ArrayList<>();
                ALL_ITEMS_MAP = new HashMap<>();

                System.out.println(snapshot.getChildrenCount() + " children in Data!!!!!!!!!!!!");
                NUM_WORKOUTS = (int)snapshot.getChildrenCount();

                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
                    Workout workout = workoutSnapshot.getValue(Workout.class);
                    String workout_id = workoutSnapshot.getKey();

                    System.out.println("(" + workout.getUserid() + ", " + workout.getName() + ") -> " + workout_id);
                    addItem(workout, workout_id);
                }
                updateUserWorkouts();
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });


        Query exercisesQueryRef = myFirebaseRef.child("Workout_Exercises").orderByValue();

        exercisesQueryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                WORKOUT_EXERCISES = new HashMap<>();

                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
                    String workout_key = workoutSnapshot.getKey();
                    Workout workout = ALL_ITEMS_MAP.get(workout_key);

                    List<ExerciseContent.Exercise> exercises = new ArrayList<>();

                    for (DataSnapshot workoutExerciseSnapshot : workoutSnapshot.getChildren()) {
                        String exercise_key = workoutExerciseSnapshot.getKey();
                        WorkoutExercise we = workoutExerciseSnapshot.getValue(WorkoutExercise.class);

                        ExerciseContent.Exercise exercise = ExerciseContent.ITEM_MAP.get(we.getExercise());
                        exercises.add(exercise);

                        System.out.println(workout_key + ": " + exercise_key + " -> " + we);
                    }

                    addExercise(workout, exercises);
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });
    }


    private static void addItem(Workout item, String id) {
        boolean contains = false;
        for(Workout e : ALL_ITEMS) {
            if (e.getName().equals(item.getName())) {
                contains = true;
            }
        }
        if(!contains) {
            ALL_ITEMS.add(item);
            ALL_ITEMS_MAP.put(id, item);
        }
    }

    private static void addExercise(Workout item, List<ExerciseContent.Exercise> exercises) {
        boolean contains = false;
        for(Workout e : WORKOUT_EXERCISES.keySet()) {
            if (e.getName().equals(item.getName())) {
                contains = true;
            }
        }
        if(!contains) {
            WORKOUT_EXERCISES.put(item, exercises);
        }
    }

    public static void setCurrentWorkout (Workout workout) {
        CURRENT_WORKOUT = workout;
        EXERCISE_LIST = WORKOUT_EXERCISES.get(workout);

        for (ExerciseContent.Exercise e: EXERCISE_LIST) {
            System.out.println("Set Current Workout: " + workout.getName() + " Exercise: " + e.getName());
        }

    }

    public static void initNewWorkout() {
        NEW_WORKOUT = new Workout();
        NEW_EXERCISES = new ArrayList<>();
    }

    public static void createNewWorkout(String workoutName) {
        NEW_WORKOUT.setName(workoutName);
        NEW_WORKOUT.setUserid(USERID);
        String workout_id = Integer.toString(NUM_WORKOUTS);

        System.out.println("Workout: " + workoutName + " User ID: " + USERID );

        Firebase workoutsRef = myFirebaseRef.child("Workouts").child(workout_id);

        workoutsRef.setValue(NEW_WORKOUT);
        addItem(NEW_WORKOUT, workout_id);

        Firebase workoutExercisesRef = myFirebaseRef.child("Workout_Exercises").child(workout_id);

        List<ExerciseContent.Exercise> exercises = new ArrayList<>();

        int i = 0;
        String ex = "ex";

        for(ExerciseContent.Exercise e : NEW_EXERCISES) {
            System.out.println(e.getName());
            String exercise_id = ex + Integer.toString(i);
            workoutExercisesRef.child(exercise_id).child("exercise").setValue(e.getName());

            exercises.add(e);
            i++;
        }
        addExercise(NEW_WORKOUT,exercises);

        NUM_WORKOUTS++;
    }

    public static void addRemoveExerciseToWorkout(ExerciseContent.Exercise exercise) {
        boolean contains = false;
        int location = 0;
        for (int i = 0; i< NEW_EXERCISES.size(); i++) {
            ExerciseContent.Exercise e = NEW_EXERCISES.get(i);
            if (e.getName().equals(exercise.getName())) {
                contains = true;
                location = i;
            }
        }

        if (!contains) {
            NEW_EXERCISES.add(exercise);
        } else {
            NEW_EXERCISES.remove(location);
        }
    }

    public static void updateUserWorkouts() {
        USER_ITEMS = new ArrayList<>();

        for (int i = 0; i < ALL_ITEMS.size(); i++){
            Workout workout = ALL_ITEMS.get(i);
            if (workout.getUserid().equals(USERID)) {
                USER_ITEMS.add(workout);
            }
        }
    }

    /**
     */
    public static class Workout {
        private String userid;
        private String name;


        public Workout() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        @Override
        public String toString() {
            return userid;
        }

    }

    public static class WorkoutExercise {

        private String exercise;

        public String getExercise() {
            return exercise;
        }

        public void setExercise(String exercise) {
            this.exercise = exercise;
        }

        @Override
        public String toString() {
            return exercise;
        }

    }
}
