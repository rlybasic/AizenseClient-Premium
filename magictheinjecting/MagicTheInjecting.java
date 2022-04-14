/*
 * Decompiled with CFR 0.150.
 */
package magictheinjecting;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;

public class MagicTheInjecting
extends Thread {
    public static byte[][] classes;

    private static Class tryGetClass(PrintWriter printWriter, ClassLoader classLoader, String ... arrstring) throws ClassNotFoundException {
        ClassNotFoundException classNotFoundException = null;
        for (String string : arrstring) {
            try {
                return classLoader.loadClass(string);
            }
            catch (ClassNotFoundException classNotFoundException2) {
                classNotFoundException2.printStackTrace(printWriter);
                classNotFoundException = classNotFoundException2;
            }
        }
        throw classNotFoundException;
    }

    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(System.getProperty("user.home") + File.separator + "eloader-log.txt", "UTF-8");
            printWriter.println("Starting!");
            printWriter.flush();
            try {
                Iterator<Method> exception2;
                Object object;
                Object object2;
                Object object3;
                Object object4;
                ClassLoader classLoader = null;
                for (Thread object62 : Thread.getAllStackTraces().keySet()) {
                    if (object62 == null || object62.getContextClassLoader() == null || (object4 = object62.getContextClassLoader()).getClass() == null || object4.getClass().getName() == null) continue;
                    object3 = object4.getClass().getName();
                    printWriter.println("Thread: " + object62.getName() + " [" + (String)object3 + "]");
                    printWriter.flush();
                    if (!((String)object3).contains("LaunchClassLoader") && !((String)object3).contains("RelaunchClassLoader")) continue;
                    classLoader = object4;
                    break;
                }
                if (classLoader == null) {
                    throw new Exception("ClassLoader is null");
                }
                this.setContextClassLoader(classLoader);
                Class class_ = MagicTheInjecting.tryGetClass(printWriter, classLoader, "cpw.mods.fml.common.Mod$EventHandler", "net.minecraftforge.fml.common.Mod$EventHandler");
                Class class_2 = MagicTheInjecting.tryGetClass(printWriter, classLoader, "cpw.mods.fml.common.Mod", "net.minecraftforge.fml.common.Mod");
                object4 = MagicTheInjecting.tryGetClass(printWriter, classLoader, "cpw.mods.fml.common.event.FMLInitializationEvent", "net.minecraftforge.fml.common.event.FMLInitializationEvent");
                object3 = MagicTheInjecting.tryGetClass(printWriter, classLoader, "cpw.mods.fml.common.event.FMLPreInitializationEvent", "net.minecraftforge.fml.common.event.FMLPreInitializationEvent");
                Method method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                method.setAccessible(true);
                printWriter.println("Loading " + classes.length + " classes");
                printWriter.flush();
                ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
                Object object5 = classes;
                int arrobject = ((byte[][])object5).length;
                for (int class_22 = 0; class_22 < arrobject; ++class_22) {
                    object2 = object5[class_22];
                    if (object2 == null) {
                        throw new Exception("classData is null");
                    }
                    if (classLoader.getClass() == null) {
                        throw new Exception("getClass() is null");
                    }
                    try {
                        Serializable exception = (Class)method.invoke(classLoader, null, object2, 0, ((byte[])object2).length, classLoader.getClass().getProtectionDomain());
                        if (((Class)exception).getAnnotation(class_2) == null) continue;
                        object = new Object[3];
                        object[0] = exception;
                        exception2 = new ArrayList();
                        ArrayList<Method> arrayList2 = new ArrayList<Method>();
                        for (Method method2 : ((Class)exception).getDeclaredMethods()) {
                            if (method2.getAnnotation(class_) != null && method2.getParameterCount() == 1 && method2.getParameterTypes()[0] == object4) {
                                method2.setAccessible(true);
                                arrayList2.add(method2);
                            }
                            if (method2.getAnnotation(class_) == null || method2.getParameterCount() != 1 || method2.getParameterTypes()[0] != object3) continue;
                            method2.setAccessible(true);
                            ((ArrayList)((Object)exception2)).add(method2);
                        }
                        object[1] = exception2;
                        object[2] = arrayList2;
                        arrayList.add((Object[])object);
                        continue;
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        throw new Exception("Exception on defineClass", exception);
                    }
                }
                printWriter.println(classes.length + " loaded successfully");
                printWriter.flush();
                object5 = arrayList.iterator();
                while (object5.hasNext()) {
                    Object[] arrobject2 = (Object[])object5.next();
                    Class class_3 = (Class)arrobject2[0];
                    object2 = (ArrayList)arrobject2[1];
                    exception = (ArrayList)arrobject2[2];
                    object = null;
                    try {
                        printWriter.println("Instancing " + class_3.getName());
                        printWriter.flush();
                        object = class_3.newInstance();
                        printWriter.println("Instanced");
                        printWriter.flush();
                    }
                    catch (Exception exception) {
                        printWriter.println("Genexeption on instancing: " + exception);
                        exception.printStackTrace(printWriter);
                        printWriter.flush();
                        throw new Exception("Exception on instancing", exception);
                    }
                    for (Method method3 : object2) {
                        try {
                            printWriter.println("Preiniting " + method3);
                            printWriter.flush();
                            printWriter.println("Preinited");
                            printWriter.flush();
                            method3.invoke(object, new Object[]{null});
                        }
                        catch (InvocationTargetException invocationTargetException) {
                            printWriter.println("InvocationTargetException on preiniting: " + invocationTargetException);
                            invocationTargetException.getCause().printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on preiniting (InvocationTargetException)", invocationTargetException.getCause());
                        }
                        catch (Exception exception) {
                            printWriter.println("Genexeption on preiniting: " + exception);
                            exception.printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on preiniting", exception);
                        }
                    }
                    exception2 = ((ArrayList)exception).iterator();
                    while (exception2.hasNext()) {
                        Method method4 = (Method)exception2.next();
                        try {
                            printWriter.println("Initing " + method4);
                            printWriter.flush();
                            printWriter.println("Inited");
                            printWriter.flush();
                            method4.invoke(object, new Object[]{null});
                        }
                        catch (InvocationTargetException invocationTargetException) {
                            printWriter.println("InvocationTargetException on initing: " + invocationTargetException);
                            invocationTargetException.getCause().printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on initing (InvocationTargetException)", invocationTargetException.getCause());
                        }
                        catch (Exception exception) {
                            printWriter.println("Genexeption on initing: " + exception);
                            exception.printStackTrace(printWriter);
                            printWriter.flush();
                            throw new Exception("Exception on initing", exception);
                        }
                    }
                }
                printWriter.println("Successfully injected");
                printWriter.flush();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace(printWriter);
                printWriter.flush();
            }
            printWriter.close();
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static int injectCP(byte[][] arrby) {
        try {
            classes = arrby;
            MagicTheInjecting magicTheInjecting = new MagicTheInjecting();
            magicTheInjecting.start();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0;
    }

    public static byte[][] getByteArray(int n) {
        return new byte[n][];
    }
}

