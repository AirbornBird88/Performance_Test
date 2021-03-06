package cz.vse.java;


import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(CollectionsBenchmark.class.getSimpleName())
                .forks(1).build();
        new Runner(options).run();
    }
}
