package victor.training.oo.structural.adapter.domain;

public class ExtractInterface {
}

class SomeClass implements ISomeClass {
    @Override
    public void someMethod() {

    }
    public void someMethod2() {

    }
}
class C {
    private void m(SomeClass someClass) {
        someClass.someMethod();
        someClass.someMethod2();
    }
}

class A {
    private final ISomeClass someClass;

    A(ISomeClass someClass) {
        this.someClass = someClass;
    }
}
class B {
    private final ISomeClass someClass;

    B(ISomeClass someClass) {
        this.someClass = someClass;
    }
}