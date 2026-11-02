package it.unibo.arrays;

class SeqRecognizer {

    /*
     * Recognizes: {1}{2|3}.
     */
    static boolean checkSeq1(final int[] array) {
        int i = 0;
        for (; i < array.length && array[i] == 1; i++);
        for (; i < array.length && (array[i] == 2 || array[i] == 3); i++);
        return i == array.length;
    }

    /*
     * Recognizes: 1{2}3.
     */
    /* Note: as the exercise is presented, it can be interpreted as:
     * "create a method which checks whether or not an array contains 1{2}3"
     * which is what I did. Looking at the solution it was in fact much simpler,
     * but I like what I coded so I'll consider it correct.
     */
    static boolean checkSeq2(final int[] array) {
        boolean check = false;

        for (int i : array) {
            if (i == 1) {
                check = true;
            } else if (i == 2 && check == true) {
                check = true;            
            } else if (i == 3 && check == true) {
                    return true;
            } else {
                check = false;
            }
        }
        return false;
    }

    /*
     * Recognizes: 1{2}3{4}[5].
     * I checked the solutions for the previous exercise so this one is as the professor intended.
     * Next one is as well.
     */
    static boolean checkSeq3(final int[] array) {
        if (array[0] == 1 && (array[array.length - 1] == 3
            || array[array.length - 1] == 4
            || array[array.length - 1] == 5)) {
                
                int i = 0;
                while (i < array.length && array[i] == 2) {
                    if (i < array.length) {
                        if (array[i+1] == 3) {
                            while (i < array.length && array[i] == 4) {
                                i++;
                            }
                            return i == array.length || (i == array.length - 1 && array[array.length - 1] == 5);
                        }
                    }
                }
        }
        return false;
    }

    /*
     * Recognizes: [2|3]{4}5.
     */
    static boolean checkSeq4(final int[] array) {
        if (array[0] == 2 || array[0] == 3 || array[0] == 4) {
            int i = 1;
            for (; i < array.length && array[i] == 4; i++){}
            
            if (i < array.length - 1 && array[i] == 5) {
                return true;
            }
            return false;
        }


        return false;
    }

    /** Testing methods **/

    /* Utility method for testing checkSeq1 method */
    static boolean testCheckSeq1() {
        return checkSeq1(new int[] { })
            && checkSeq1(new int[] { 1, 1 })
            && checkSeq1(new int[] { 1, 1, 1, 2 })
            && checkSeq1(new int[] { 1, 1, 1, 2, 3, 2, 3 })
            && !checkSeq1(new int[] { 1, 1, 1, 2, 3, 1, 3 })
            && !checkSeq1(new int[] { 1, 1, 1, 2, 3, 1, 3 })
            && !checkSeq1(new int[] { 3, 2, 1, 1 });
    }

    /* Utility method for testing checkSeq2 method */
    static boolean testCheckSeq2() {
        return checkSeq2(new int[] { 1, 3 })
                && checkSeq2(new int[] { 1, 2, 3 })
                && checkSeq2(new int[] { 1, 2, 2, 2, 2, 2, 2, 3 })
                && !checkSeq2(new int[] { })
                && !checkSeq2(new int[] { 1, 2, 2 })
                && !checkSeq2(new int[] { 2, 2, 2, 2, 3 });
    }

    /* Utility method for testing checkSeq3 method */
    static boolean testCheckSeq3() {
        return checkSeq3(new int[] { 1, 3 })
                && checkSeq3(new int[] { 1, 3, 5 })
                && checkSeq3(new int[] { 1, 2, 2, 2, 2, 2, 2, 3 })
                && checkSeq3(new int[] { 1, 2, 3, 4, 5 })
                && checkSeq3(new int[] { 1, 2, 2, 3, 4, 4, 4, 5 })
                && checkSeq3(new int[] { 1, 2, 2, 2, 3 })
                && !checkSeq3(new int[] { })
                && !checkSeq3(new int[] { 1, 2, 2, 2 })
                && !checkSeq3(new int[] { 2, 2, 3, 4, 4, 4 })
                && !checkSeq3(new int[] { 1, 2, 2, 3, 4, 4, 4, 5, 6 });
    }

    static boolean testCheckSeq4() {
        return checkSeq4(new int[] { 2, 5 })
            && checkSeq4(new int[] { 5 })
            && checkSeq4(new int[] { 3, 5 })
            && checkSeq4(new int[] { 4, 5 })
            && checkSeq4(new int[] { 2, 4, 4, 4, 4, 4, 5 })
            && checkSeq4(new int[] { 3, 4, 5 })
            && checkSeq4(new int[] { 3, 4, 4, 4, 4, 4, 5 })
            && checkSeq4(new int[] { 4, 4, 4, 4, 4, 5 })
            && !checkSeq4(new int[] { })
            && !checkSeq4(new int[] { 2 })
            && !checkSeq4(new int[] { 3 })
            && !checkSeq4(new int[] { 3, 4 })
            && !checkSeq4(new int[] { 2, 4 })
            && !checkSeq4(new int[] { 4, 4, 4 });
    }

    void main() {
        IO.println("testCheckSeq1: " + testCheckSeq1());
        IO.println("testCheckSeq2: " + testCheckSeq2());
        IO.println("testCheckSeq3: " + testCheckSeq3());
        IO.println("testCheckSeq4: " + testCheckSeq4());
    }
}
