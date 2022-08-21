package com.as.handlers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class JndiPropertiesHandler {

	private static final String FS_CONTEXT = "com.sun.jndi.fscontext.RefFSContextFactory";
	private static final String PATH = "path";


	public static Properties getJNDIProps(){
		Properties props = null;
		Context context = null;
		try {
			context = new InitialContext(getEnvironment());
			NamingEnumeration<Binding> listBindings = context.listBindings("");
			if (listBindings.hasMoreElements()) {
				Binding binding = (Binding) listBindings.nextElement();
				String propsPath = binding.getObject().toString();
				props = new Properties();
				props.load(new FileReader(propsPath));
			}

		} catch (NamingException | IOException e) {
			e.printStackTrace();
		} finally {
			if (context!=null) {
				try {
					context.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

	private static Hashtable<?, ?> getEnvironment() {
		Hashtable<String, String> environment = new Hashtable<>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, FS_CONTEXT);
		environment.put(Context.PROVIDER_URL, PATH);
		return environment;
	}

}
