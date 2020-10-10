package com.syncpower.system.util;

public abstract class BaseProducer implements Runnable {

    @Override
    public void run() {
        try{
            batch();
        }catch(RuntimeException e){
            throw e;
        }
    }

    protected abstract void batch();

}
