package de.tu_darmstadt.smartml.program.type;

public final class PrimitiveType implements SmartType {
    public enum Kind { INT, BOOL, STRING, ADDRESS  }

    private final Kind kind;
    public PrimitiveType(Kind kind) { this.kind = kind; }
    public Kind kind() { return kind; }

    @Override public String display() { return kind.name().toLowerCase(); }

    @Override public boolean equals(Object o){ return (o instanceof PrimitiveType p) && p.kind==kind; }
    @Override public int hashCode(){ return kind.hashCode(); }
}
