public class Test {
    private static final Student [] stModule_evaluator = new Student[100];

    public static void main(String[] args) {

        initializeModuleEvaluator(stModule_evaluator);

        System.out.println(stModule_evaluator.length);

        for (int i = 0; i < stModule_evaluator.length; i++) {
            System.out.println(i+stModule_evaluator[i].getStName());

        }

    }

    public static void initializeModuleEvaluator(Student[] array){

        for (int i = 0; i < stModule_evaluator.length; i++) {
            stModule_evaluator[i] = new Student("-","-");
        }
    }


}
