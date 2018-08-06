package visitor.hittableandtablevisitor;

import logic.table.NullTable;
import logic.table.PlayableTable;
import visitor.HittableVisitor;

/**
 * Interface of a Visitor class that visits {@link logic.gameelements.Hittable}s and {@link logic.table.Table}s
 *
 * @author Felipe Canales
 */
public interface HittableAndTableVisitor extends HittableVisitor {
    /**
     * Receives a {@link NullTable} and acts accordingly.
     *
     * @param n Instance of NullTable.
     */
    void visitNullTable(NullTable n);

    /**
     * Receives a {@link PlayableTable} and acts accordingly.
     *
     * @param p Instance of PlayableTable.
     */
    void visitPlayableTable(PlayableTable p);
}
