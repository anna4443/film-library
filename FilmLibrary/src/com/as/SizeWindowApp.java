package com.as;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class SizeWindowApp {
    public static void main(String[] args) {

        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

        env.put(Context.PROVIDER_URL, "url");

        try{
            Context ctxt = new InitialContext(env);

            System.out.println(ctxt.lookup("Height"));


        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
