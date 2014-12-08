package maya;

public class MiscUtil {

	public static Object getFieldByName(Class<?> obj, String name)
	{
		if (obj == null || name == null) return null;
		try {
			java.lang.reflect.Field f = obj.getField(name);
			if (!f.isAccessible()) {
		        f.setAccessible(true);
			}
			if (java.lang.reflect.Modifier.isStatic(f.getModifiers())){
				return f.get(null);
			}
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NullPointerException e) {
		}
		return null; 
	}
	
	public static Object getFieldByName(Object obj, String name)
	{
		if (obj == null || name == null) return null;
		try {
			java.lang.reflect.Field f = obj.getClass().getField(name);
			if (!f.isAccessible()) {
		        f.setAccessible(true);
			}
			return f.get(obj);
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NullPointerException e) {
		}
		return null; 
	}
}
