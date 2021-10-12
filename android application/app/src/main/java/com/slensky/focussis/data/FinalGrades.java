package com.slensky.focussis.data;

import java.util.List;

/**
 * Created by slensky on 4/3/18.
 */

public class FinalGrades {
    private static final String TAG = "FinalGrades";
    private final List<FinalGrade> finalGrades;
    private FinalGradesPage finalGradesPage;

    public FinalGrades(List<FinalGrade> finalGrades) {
        this.finalGrades = finalGrades;
    }

    public FinalGradesPage getFinalGradesPage() {
        return finalGradesPage;
    }

    public void setFinalGradesPage(FinalGradesPage finalGradesPage) {
        this.finalGradesPage = finalGradesPage;
    }

    public List<FinalGrade> getFinalGrades() {
        return finalGrades;
    }

}
