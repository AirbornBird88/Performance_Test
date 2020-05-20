package cz.vse.java;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3)
public class CollectionsBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private Set<Employee> employeeSet = new HashSet<>();
        private List<Employee> employeeList = new ArrayList<>();
        private Map<Long, String> employeeHashMap = new HashMap<>();
        private Map<Long, String> employeeTreeMap = new TreeMap<>();
        private Map<Long, String> employeeConcurrentMap = new ConcurrentHashMap<>();
        private Map<Long, String> employeeLinkedHashMap = new LinkedHashMap<>();
        private Map<Long, String> employeeConcurrentSkipListMap = new ConcurrentSkipListMap<>();

        private long iterations = 100;

        private Employee employee = new Employee(100L, "Harry");
        private Long id = 100L;
        private String name = "Harry";

        @Setup(Level.Trial)
        public void setUp() {

            for (long i = 0; i < iterations; i++) {
                employeeSet.add(new Employee(i, "John"));
                employeeList.add(new Employee(i, "John"));
                employeeHashMap.put(i, "John");
                employeeTreeMap.put(i, "John");
                employeeConcurrentMap.put(i, "John");
                employeeLinkedHashMap.put(i, "John");
                employeeConcurrentSkipListMap.put(i, "John");
            }

            employeeList.add(employee);
            employeeSet.add(employee);
            employeeHashMap.put(id, name);
            employeeTreeMap.put(id, name);
            employeeConcurrentMap.put(id, name);
            employeeLinkedHashMap.put(id, name);
            employeeConcurrentSkipListMap.put(id, name);
        }
    }

    @Benchmark
    public boolean testArrayList(MyState state) {
        return state.employeeList.contains(state.employee);
    }

    @Benchmark
    public boolean testHashSet(MyState state) {
        return state.employeeSet.contains(state.employee);
    }

    @Benchmark
    public boolean testHashMapKey(MyState state) {
        return state.employeeHashMap.containsKey(state.id);
    }

    @Benchmark
    public boolean testHashMapValue(MyState state) {
        return state.employeeHashMap.containsValue(state.name);
    }

    @Benchmark
    public boolean testTreeMapKey(MyState state) {
        return state.employeeTreeMap.containsKey(state.id);
    }

    @Benchmark
    public boolean testTreeMapValue(MyState state) {
        return state.employeeTreeMap.containsValue(state.name);
    }

    @Benchmark
    public boolean testConcurrentMapKey(MyState state) {
        return state.employeeConcurrentMap.containsKey(state.id);
    }

    @Benchmark
    public boolean testConcurrentMapValue(MyState state) {
        return state.employeeTreeMap.containsValue(state.name);
    }

    @Benchmark
    public boolean testLinkedHashMapKey(MyState state) {
        return state.employeeHashMap.containsKey(state.id);
    }

    @Benchmark
    public boolean testLinkedHashMapValue(MyState state) {
        return state.employeeHashMap.containsValue(state.name);
    }

    @Benchmark
    public boolean testConcurrentSkipListMapKey(MyState state) {
        return state.employeeTreeMap.containsKey(state.id);
    }

    @Benchmark
    public boolean testConcurrentSkipListMapValue(MyState state) {
        return state.employeeTreeMap.containsValue(state.name);
    }
}
