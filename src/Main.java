public class Main {
    public static void main(String[] args) throws Exception {
        Testing.testFigure(Generator.triangle());
        Testing.testFigure(Generator.rectangle());
        Testing.testFigure(Generator.circle());

        Testing.testList(Generator.drawableList1());
        Testing.testList(Generator.drawableList2());

        Testing.testGroup(Generator.flatGroup());
        Testing.testGroup(Generator.nestedGroup());
    }
}