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
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ExerciseContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Exercise> ITEMS = new ArrayList<Exercise>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Exercise> ITEM_MAP = new HashMap<String, Exercise>();

    public static boolean INIT;

    public static Firebase myFirebaseRef;

    static {
        // Add some sample items.
        //for (int i = 1; i <= COUNT; i++) {
            //addItem(getExerciseItem(i));
        //}

        INIT = false;

    }

    public static void updateItems() {


        Query queryRef = myFirebaseRef.child("Exercise").orderByValue();
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getChildrenCount() + " children in Data!!!!!!!!!!!!");
                for (DataSnapshot exerciseSnapshot: snapshot.getChildren()) {
                    ExerciseContent.Exercise exercise = exerciseSnapshot.getValue(ExerciseContent.Exercise.class);
                    System.out.println("Exercise #" + exerciseSnapshot.getValue());
                    //for (DataSnapshot row : exerciseSnapshot.getChildren()) {
                    //    System.out.println(row.getKey() + " === " + row.getValue());
                    //}

                    System.out.println("ExerciseContent Adding Exercise: " + exercise.getName() + " -> " + exercise.getType());
                    addItem(exercise);
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }

        });
    }


    private static void addItem(Exercise item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }


    /**
     * A dummy item representing a piece of name.
     */
    public static class Exercise {
        private String name;
        private String description;
        private String video;
        private String type;
        private String sets;
        private String reps;
        private String points;

        public Exercise() {
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getVideo() {
            return video;
        }

        public String getType() {
            return type;
        }

        public String getSets() {
            return sets;
        }

        public String getReps() {
            return reps;
        }

        public String getPoints() {
            return points;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSets(String sets) {
            this.sets = sets;
        }

        public void setReps(String reps) {
            this.reps = reps;
        }

        public void setPoints(String points) {
            this.points = points;
        }


        @Override
        public String toString() {
            return name;
        }

    }
}
