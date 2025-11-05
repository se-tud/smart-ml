package de.tu_darmstadt.smartml.program;

import de.tu_darmstadt.smartml.program.decl.*;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public final class PrettyPrinter {
    public String print(Object n) { return print(n, 0); }
    private String i(int n){ return "  ".repeat(n); }

    @SuppressWarnings("unchecked")
    private <T> T get(Object o, String... names) {
        if (o == null) return null;
        for (String name : names) {
            try {
                Method m = o.getClass().getMethod(name);
                m.setAccessible(true);
                return (T) m.invoke(o);
            } catch (ReflectiveOperationException ignored) {}
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <E> List<E> list(Object o, String... names) {
        Object v = get(o, names);
        if (v instanceof List<?> l) return (List<E>) l;
        if (v instanceof Collection<?> c) return (List<E>) List.copyOf(c);
        return List.of();
    }

    private String print(Object n, int d){
        if (n == null) return "null";

        // === program & decls ===
        if (n instanceof Program p) {
            var sb = new StringBuilder("Contract\n");
            for (var dec : p.decls()) sb.append(i(d+1)).append(print(dec, d+1)).append("\n");
            return sb.toString().trim();
        }
        if (n instanceof ContractDecl c) {
            var sb = new StringBuilder("ContractDecl ").append(c.name()).append("\n");

            // STATE (fields)
            var state = list(c, "state", "stateDecls", "fields");
            if (!state.isEmpty()) {
                sb.append(i(d+1)).append("state:\n");
                for (var s : state) sb.append(i(d+2)).append(print(s, d+2)).append("\n");
            }

            var members = list(c, "members", "functions", "body");
            if (!members.isEmpty()) {
                sb.append(i(d+1)).append("members:\n");
                for (var m : members) sb.append(i(d+2)).append(print(m, d+2)).append("\n");
            } else {
                var children = reflectChildren(c);
                if (!children.isEmpty()) {
                    sb.append(i(d+1)).append("members:\n");
                    for (var ch : children) sb.append(i(d+2)).append(print(ch, d+2)).append("\n");
                }
            }

            var invs = list(c, "invariants", "invDecls");
            if (!invs.isEmpty()) {
                sb.append(i(d+1)).append("invariants:\n");
                for (var inv : invs) sb.append(i(d+2)).append(print(inv, d+2)).append("\n");
            }
            return sb.toString().trim();
        }

        if (n instanceof FunctionDecl fd) {
            var sb = new StringBuilder("Function ").append(fd.name()).append("\n");

            var params = list(fd, "params", "parameters", "args");
            if (!params.isEmpty()) {
                sb.append(i(d+1)).append("params:\n");
                for (var p : params) sb.append(i(d+2)).append(print(p, d+2)).append("\n");
            }

            Object ret = get(fd, "returnType", "ret", "type");
            if (ret != null) {
                sb.append(i(d+1)).append("ret: ");
                if (ret instanceof String s) sb.append(s).append("\n");
                else sb.append(print(ret, d+1)).append("\n");
            }

            Object body = get(fd, "body", "block");
            if (body == null) {
                var kids = reflectChildren(fd);
                for (var ch : kids) if (ch instanceof Block) { body = ch; break; }
            }
            if (body != null) {
                sb.append(i(d+1)).append("body:\n");
                sb.append(i(d+2)).append(print(body, d+2));
            }
            return sb.toString().trim();
        }
        if (n instanceof InterfaceDecl i0) return "InterfaceDecl " + i0.name();
        if (n instanceof DatatypeDecl dt)  {
            var sb = new StringBuilder("DatatypeDecl ").append(dt.name());
            var kids = reflectChildren(dt);
            if (!kids.isEmpty()) {
                sb.append("\n");
                for (var ch : kids) sb.append(i(d+1)).append(print(ch, d+1)).append("\n");
                return sb.toString().trim();
            }
            return sb.toString();
        }
        if (n instanceof ResourceDecl r)   return "ResourceDecl " + r.name();
        if (n instanceof FieldDecl fd)     return "Field " + fd.type() + " " + fd.name();
        if (n instanceof FunctionDecl fd) {
            var sb = new StringBuilder("Function ").append(fd.name()).append("\n");
            var params = list(fd, "params", "parameters", "args");
            if (!params.isEmpty()) {
                sb.append(i(d+1)).append("params:\n");
                for (var p : params) sb.append(i(d+2)).append(print(p, d+2)).append("\n");
            }
            var ret = get(fd, "returnType", "ret", "type");
            if (ret != null) sb.append(i(d+1)).append("ret: ").append(print(ret, d+1)).append("\n");
            var body = get(fd, "body", "block");
            if (body == null) { // fallback via children
                var kids = reflectChildren(fd);
                for (var ch : kids) if (ch instanceof Block) { body = ch; break; }
            }
            if (body != null) {
                sb.append(i(d+1)).append("body:\n");
                sb.append(i(d+2)).append(print(body, d+2));
            }
            return sb.toString().trim();
        }
        if (n instanceof ConstructorDecl cd) {
            var sb = new StringBuilder("Constructor\n");
            var params = list(cd, "params", "parameters");
            if (!params.isEmpty()) {
                sb.append(i(d+1)).append("params:\n");
                for (var p : params) sb.append(i(d+2)).append(print(p, d+2)).append("\n");
            }
            var body = get(cd, "body", "block");
            if (body == null) { // fallback via children
                var kids = reflectChildren(cd);
                for (var ch : kids) if (ch instanceof Block) { body = ch; break; }
            }
            if (body != null) {
                sb.append(i(d+1)).append("body:\n");
                sb.append(i(d+2)).append(print(body, d+2));
            }
            return sb.toString().trim();
        }

        // === blocks & statements ===
        if (n instanceof Block b) {
            var sb = new StringBuilder("Block\n");
            for (var s : b.statements()) sb.append(i(d+1)).append(print(s, d+1)).append("\n");
            return sb.toString().trim();
        }
        if (n instanceof If x) {
            var sb = new StringBuilder("If\n");
            sb.append(i(d+1)).append("cond:\n").append(i(d+2)).append(print(x.cond(), d+2)).append("\n");
            sb.append(i(d+1)).append("then:\n").append(i(d+2)).append(print(x.thenBranch(), d+2)).append("\n");
            sb.append(i(d+1)).append("else:\n").append(i(d+2)).append(print(x.elseBranch(), d+2));
            return sb.toString();
        }
        if (n instanceof While x) {
            var sb = new StringBuilder("While\n");
            sb.append(i(d+1)).append("cond:\n").append(i(d+2)).append(print(x.cond(), d+2)).append("\n");
            sb.append(i(d+1)).append("body:\n").append(i(d+2)).append(print(x.body(), d+2));
            return sb.toString();
        }
        if (n instanceof Assign x) {
            var sb = new StringBuilder("Assign\n");
            sb.append(i(d+1)).append("lhs:\n").append(i(d+2)).append(print(x.lhs(), d+2)).append("\n");
            sb.append(i(d+1)).append("rhs:\n").append(i(d+2)).append(print(x.rhs(), d+2));
            return sb.toString();
        }
        if (n instanceof ExpressionStatement x) return "ExprStmt\n" + i(d+1) + print(x.expr(), d+1);
        if (n instanceof Return x)             return "Return\n"  + i(d+1) + print(x.value(), d+1);
        if (n instanceof CallStmt x)           return "CallStmt\n"+ i(d+1) + print(x.call(), d+1);
        if (n instanceof TryCatch x) {
            var sb = new StringBuilder("TryCatch\n");
            sb.append(i(d+1)).append("try:\n").append(i(d+2)).append(print(x.tryStmt(), d+2)).append("\n");
            sb.append(i(d+1)).append("catchVar:\n").append(i(d+2)).append(print(x.catchVar(), d+2)).append("\n");
            sb.append(i(d+1)).append("catch:\n").append(i(d+2)).append(print(x.catchBlock(), d+2));
            return sb.toString();
        }
        if (n instanceof Transaction t) {
            var sb = new StringBuilder("Transaction\n");
            sb.append(i(d+1)).append("try:\n").append(i(d+2)).append(print(t.tryStmt(), d+2)).append("\n");
            if (t.abortBlock()!=null) {
                sb.append(i(d+1)).append("abort:\n").append(i(d+2)).append(print(t.abortBlock(), d+2)).append("\n");
            }
            if (t.successBlock()!=null) {
                sb.append(i(d+1)).append("success:\n").append(i(d+2)).append(print(t.successBlock(), d+2)).append("\n");
            }
            return sb.toString().trim();
        }
        if (n instanceof VarTarget vt) {
            var tpe = get(vt, "type");
            var storage = get(vt, "storage");
            var target = get(vt, "target");
            String s = "VarTarget";
            if (Boolean.TRUE.equals(storage)) s += " [storage]";
            if (tpe != null) s += " : " + print(tpe, d);
            if (target != null) s += "\n" + i(d+1) + print(target, d+1);
            return s;
        }

        // === expressions ===
        if (n instanceof Var v)           return "Var " + v.name();
        if (n instanceof IntLit i1)       return "Int " + i1.value();
        if (n instanceof StringLit s)     return "String \"" + s.value() + "\"";
        if (n instanceof BoolLit b1)      return "Bool " + b1.value();
        if (n instanceof AddressLit a)    return "Address " + a.value();
        if (n instanceof UnaryNeg u)      return "UnaryNeg\n" + i(d+1) + print(u.expr(), d+1);
        if (n instanceof UnaryNot u)      return "UnaryNot\n" + i(d+1) + print(u.expr(), d+1);
        if (n instanceof Binary b2) {
            var sb = new StringBuilder("Binary ").append(b2.op()).append("\n");
            sb.append(i(d+1)).append(print(b2.left(), d+1)).append("\n");
            sb.append(i(d+1)).append(print(b2.right(), d+1));
            return sb.toString();
        }
        if (n instanceof QualifiedAccess qa) {
            return "QAccess " + qa.base() + (qa.path().isEmpty() ? "" : "." + String.join(".", qa.path()));
        }

        var kids = reflectChildren(n);
        if (!kids.isEmpty()) {
            var sb = new StringBuilder(n.getClass().getSimpleName()).append("\n");
            for (var ch : kids) sb.append(i(d+1)).append(print(ch, d+1)).append("\n");
            return sb.toString().trim();
        }

        return n.getClass().getSimpleName();
    }

    private List<Object> reflectChildren(Object n) {
        try {
            Method mCnt = n.getClass().getDeclaredMethod("getChildCount");
            Method mGet = n.getClass().getDeclaredMethod("getChild", int.class);
            mCnt.setAccessible(true);
            mGet.setAccessible(true);
            int cnt = (int) mCnt.invoke(n);
            if (cnt <= 0) return List.of();
            var out = new java.util.ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                Object child = mGet.invoke(n, i);
                if (child != null) out.add(child);
            }
            return out;
        } catch (ReflectiveOperationException e) {
            return List.of();
        }
    }
}
