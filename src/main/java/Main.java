import haxidenti.jsignal.JSignal;

public class Main {

    private static JSignal<PrintDetails> printSignals = new JSignal<>();

    public static void main(String[] args) {
        printer1();
        printer2();
        printer3();

        for (int i = 0; i < 1000; i++) {
            printSignals.emit(new PrintDetails(i % 3, "Text #" + i));
        }
    }

    public static class PrintDetails {
        public int id;
        public String text;

        public PrintDetails(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }

    public static void printer1() {
        printSignals.connectFiltered(s -> {
            System.out.println("Printer 1: " + s.text);
        }, d -> d.id == 1);
    }

    public static void printer2() {
        printSignals.connectFiltered(s -> {
            System.out.println("Printer 2: " + s.text);
        }, d -> d.id == 2);
    }

    public static void printer3() {
        printSignals.connectFiltered(s -> {
            System.out.println("Printer 3: " + s.text);
        }, d -> d.id == 3);
    }
}
