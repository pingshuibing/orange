package com.qut.spc.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

/**
 * Binding Xml to Solar component
 * @author QuocViet
 * @param <T>
 */
public class ListRequestTask<T> extends XmlRequestTask {
	protected List<T> list;
	protected Class<T> itemClass;
	protected String itemName;
	// Current element
	protected T component;
	protected String setterName;
	protected int depth = 0;
	protected Map<String, Method> setterMethods;
	
	public ListRequestTask(String itemName, Class<T> itemClass) {
		super();
		this.itemClass = itemClass;
		this.itemName = itemName;
		getSetterMethods();
	}
	
	@Override
	protected void onXmlTag(XmlPullParser parser, int eventType) {
		String name;
		
		switch (eventType) {
		case XmlPullParser.START_DOCUMENT:
			list = new LinkedList<T>();
			depth = 0;
			break;
			
		case XmlPullParser.START_TAG:
			++depth;
			name = parser.getName();
			if (depth == 2 && name.equals(itemName)) {
				try {
					component = itemClass.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else if (depth == 3) {
				setterName = "set" + Character.toUpperCase(name.charAt(0))
						+ (name.length() > 1 ? name.substring(1) : "");;
			}
			break;
		case XmlPullParser.END_TAG:
			--depth;
			name = parser.getName();
			if (depth == 2 && name.equals(itemName) && component != null) {
				list.add(component);
				component = null;
			}
			break;
		case XmlPullParser.TEXT:
			if (depth == 3 && component != null) {
				bindProperty(component, setterName, parser.getText());
			}
			break;
		}
	}
	
	@Override
	protected void onPostExecute(XmlPullParser parser) {
		onPostExecute(this.list);
	}
	
	protected void onPostExecute(List<T> list) {
		// Sub class should override this
	}

	/**
	 * Bind value to current instance
	 * @param value
	 */
	private void bindProperty(Object obj, String method, String value) {
		Method m = setterMethods.get(method);
		
		if (m != null) {
			// invoke this method to set property
			Class<?> paramType = m.getParameterTypes()[0];
			try {
				m.invoke(obj, paramType.getDeclaredMethod("valueOf", String.class).invoke(null, value));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void getSetterMethods() {
		Method[] methods = itemClass.getMethods();
		
		setterMethods = new HashMap<String, Method>();
		
		for (Method m : methods) {
			String name = m.getName();
			if (name.startsWith("set")
					&& m.getParameterTypes().length == 1
					&& m.getReturnType().equals(Void.TYPE)) {
				setterMethods.put(name, m);
			}
		}
	}
}
