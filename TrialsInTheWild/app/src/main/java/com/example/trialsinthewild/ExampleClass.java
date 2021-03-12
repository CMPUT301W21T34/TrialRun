// First we have the beginning comments which should take the form:
/*
 * Class Name
 *
 * Version information
 *
 * Date
 *
 * Copyright notice [see https://www.jetbrains.com/help/idea/copyright.html]
 */

//Second we have package details
package com.example.trialsinthewild;

//Third we have import statements

/**
 * Class documentation statement
 */
public class ExampleClass {         // Class/Interface declaration. classes/interface names are always capitalized
    /* Class/Interface statement */

    // Class (static) variables in the order
    /* public
     * protected
     * no access modifier
     * private
     */

    public static int example_number;
    protected static boolean example_boolean;
    static double example_double;                   /* in line comments should be indended away from code and matched to each other */
    static final int MAX_WIDTH = 400;               /* class constants should be all uppercase separated by underlines */
    private static float example_float;


    // Class instance variables in the order
    /* public
     * protected
     * no access modifier
     * private
     */

    // Next list constructors
    public ExampleClass() {

    }

    // Next we list our methods
    // Group by functionality rather than scope or accessibility
    /*
        e.g. group turtle things together
        public void getExampleTurtle
        private int setExampleTurtle
        boolean isATurtle
     */

    // For Methods The first word is always lower case, the rest are capitalized properly

    public void setExampleBoolean(boolean value) {
        int level;
        int size;
        // Preffered over
        // int level, size;

        size = 3;
        // Correct way to format if, else if, else statements
        // space before and after the (conditional) in 'if (conditional) {'
        if (value) {
            size++;
        } else if (size==3) {
            level=size;
        } else {
            size*=2;
        }

        // Do not do the following, always use curly brackets
        if (!value)
            size++;
    }

    public void setExampleNumber(int number) {
        // casting always has a space between the object casted
        float converted = ((float) number);

        int i = 0;
        int j = 1;
        int k = 4;

        // operators should always be separated by spaces with the exceptions of unary operators (++, --, +=)
        i += ((j + k) + (number * 4)) / 40;

        // more examples
        while (j++ == i++) {
            k++;
        }

        // This is dumb and hard to format this way, maybe we should do it the other way.
        switch (number) {
        case 1:
            i++;
            break;
        case 2:
            i--;
            break;
        default:
            j++;
            break;
        }

        example_number = i + k + k + number;
    }
}
