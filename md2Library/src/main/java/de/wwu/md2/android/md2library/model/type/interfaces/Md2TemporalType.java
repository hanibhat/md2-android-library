package de.wwu.md2.android.md2library.model.type.interfaces;

/**
 * {@inheritDoc}
 * <p/>
 * Representation of numeric types in MD2-DSL
 * <p/>
 * Created on 15/03/2018
 *
 * @author Christoph Rieger
 * @version 1.0
 * @since 1.0
 */
public interface Md2TemporalType extends Md2DataType {
    /**
     * greater than
     *
     * @param value the value
     * @return the boolean
     */
    boolean gt(Md2NumericType value);

    /**
     * greater than or equal
     *
     * @param value the value
     * @return the boolean
     */
    boolean gte(Md2NumericType value);

    /**
     * less than
     *
     * @param value the value
     * @return the boolean
     */
    boolean lt(Md2NumericType value);

    /**
     * less than or equal
     *
     * @param value the value
     * @return the boolean
     */
    boolean lte(Md2NumericType value);
}
