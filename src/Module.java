public class Module {

    private float marks1;
    private float marks2;
    private float marks3;

    public Module (float marks1, float marks2, float marks3){
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
    }

    private float average(){
        return (this.marks1 + this.marks2 + this.marks3)/3;
    }

    private String moduleGrade(float averageMarks){

        averageMarks = average();

        if(averageMarks >= 80){
            return "Distinction";
        } else if (averageMarks >= 70) {
            return "Merit";
        } else if (averageMarks >= 40) {
            return "Pass";
        }else {
            return "Fail";
        }
    }

    //--------------------------- Getters and Setters ---------------------------

    public float getMarks1() {
        return marks1;
    }

    public void setMarks1(float marks1) {
        this.marks1 = marks1;
    }

    public float getMarks2() {
        return marks2;
    }

    public void setMarks2(float marks2) {
        this.marks2 = marks2;
    }

    public float getMarks3() {
        return marks3;
    }

    public void setMarks3(float marks3) {
        this.marks3 = marks3;
    }

    public String getGrade(){
        return moduleGrade(average());
    }

    public float getAverage(){
        return average();
    }


}
