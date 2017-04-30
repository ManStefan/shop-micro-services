package com.man.shop.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by smanolache on 4/2/2017.
 */
public class PersistentMoneyUserType implements CompositeUserType, Serializable {
    @Override
    public String[] getPropertyNames() {
        return new String[] {"currCode", "amount"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[] {StandardBasicTypes.CURRENCY, StandardBasicTypes.BIG_DECIMAL};
    }

    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        if (o == null){
            return null;
        } else if (!returnedClass().isAssignableFrom(o.getClass())){
            throw new HibernateException("Incorrect type of class passed as parameter: " + o.getClass());
        } else {
            if (i == 0){
                return ((MonetaryAmount)o).getCurrency();
            } else if (i == 1){
                return new BigDecimal(((MonetaryAmount)o).getNumber().doubleValue());
            }

        }
        return null;
    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {
        throw new HibernateException("Called setPropertyValue on an immutable type: {" + o.getClass() + "}");
    }

    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == o1){
            return true;
        } else if (o != null && o1 != null){
            return o.equals(o1);
        }

        return false;
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        if (o != null){
            return o.hashCode();
        }

        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        if (resultSet == null){
            throw new HibernateException("Null value has been passed for the parameter resultSet!");
        }

        String currencyCode = resultSet.getString(strings[0]);
        BigDecimal amount = resultSet.getBigDecimal(strings[1]);

        if (currencyCode != null && amount != null) {
            return Money.of(amount, currencyCode);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {

        if (o != null){
            preparedStatement.setString(i, ((MonetaryAmount)o).getCurrency().getCurrencyCode());
            preparedStatement.setBigDecimal(i + 1, new BigDecimal(((MonetaryAmount)o).getNumber().doubleValue()));
        } else {
            preparedStatement.setString(i, null);
            preparedStatement.setBigDecimal(i + 1, null);
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SessionImplementor sharedSessionContractImplementor) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, SessionImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, SessionImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return o;
    }
}
