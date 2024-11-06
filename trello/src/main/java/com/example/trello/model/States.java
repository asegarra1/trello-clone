package com.example.trello.model;


/*
 * Enum for task states, implementing basic state transition logic to control task progression through the TO-DO, DOING, and DONE states.
 */
public enum States {
    TODO {
        @Override
        public States next() {
            return DOING;
        }
    },
    DOING {
        @Override
        public States next() {
            return DONE;
        }
    },
    DONE {
        @Override
        public States next() {
            throw new IllegalStateException("Task is completed, cannot do anything");
        }
    };

    public abstract States next();

}