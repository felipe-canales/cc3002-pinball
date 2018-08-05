package visitor.hittableandtablevisitor;

import logic.table.NullTable;
import logic.table.PlayableTable;
import visitor.HittableVisitor;

public interface HittableAndTableVisitor extends HittableVisitor {
    void visitNullTable(NullTable n);
    void visitPlayableTable(PlayableTable p);
}
