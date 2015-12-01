package se.extest.mah.gusfri.gustav.projekttwoapp;

import java.util.EventObject;

/**
 * Created by gustav on 2014-10-23.
 */
public class ConnectionEvent extends EventObject {

    int type;
    String message;

    public static final int INCOMMING_MESSAGE = 0;
    public static final int CONNECTION_FAILED=-1;
    public static final int CONNECTION_SUCCESS=1;

    public ConnectionEvent(Object source, int type) {
        super(source);
        this.type=type;
    }
    public ConnectionEvent(Object source, int type, String message) {
        super(source);
        this.type=type;
        this.message = message;
    }
    public int getType(){
        return type;
    }

}
