package org.maple.demo01;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 苦工工厂，负责生产、校验、销毁苦工。
 */
public class PeonFactory implements PooledObjectFactory<Peon> {

    private final String number;

    public PeonFactory(String number) {
        this.number = number;
    }

    @Override
    public PooledObject<Peon> makeObject() throws Exception {
        Peon peon = new Peon();
        peon.setId(number);
        return new DefaultPooledObject<>(peon);
    }

    @Override
    public void destroyObject(PooledObject<Peon> pooledObject) throws Exception {
        Peon peon = pooledObject.getObject();
        peon = null;
    }

    @Override
    public boolean validateObject(PooledObject<Peon> pooledObject) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<Peon> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<Peon> pooledObject) throws Exception {

    }

}
