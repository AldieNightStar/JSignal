# JSignal
## Inspired by Godot signals

# Import
```groovy
repositories {
    // ...
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.AldieNightStar:JSignal:2eae03bccf'
}
```

# Usage
```java
JSignal<PrintDetails> signal = new JSignal<>();

// Connect to a signal
signal.coonect(data -> {});

// Connect to a signal but for once
signal.coonectOnce(data -> {});

// Connect to a signal but filter messages
// For example we will allow only signals with: userName == "HaxiDenti"
signal.coonectFiltered(data -> {}, data -> data.userName.equals("HaxiDenti"));

// Clear signals
signal.clear();

// Disconnect from a signal by a task
signal.disconnect(task1);
```

# Example
```java
import haxidenti.jsignal.JSignal;

public class Main {

    // Here is our signal
    private static JSignal<PrintDetails> printSignals = new JSignal<>();

    
    // Main method
    public static void main(String[] args) {
        
        // Call signal receiver methods
        // They will connect to a signal
        printer1();
        printer2();
        printer3();

        // Emit signal and test
        for (int i = 0; i < 1000; i++) {
            printSignals.emit(new PrintDetails(i % 3, "Text #" + i));
        }
    }
    
    
    // Signal message entity
    public static class PrintDetails {
        public int id;
        public String text;

        public PrintDetails(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }
    
    
    
    // ==========================
    // Signal receiver methods
    // ==========================

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
```