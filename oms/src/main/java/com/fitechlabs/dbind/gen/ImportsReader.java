// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImportsReader.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.StringTokenizer;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            DataSpec, TableSpec, Settings

public class ImportsReader
{

    private static void status(String s)
    {
        System.out.println(s);
    }

    private static void warn(String s)
    {
        System.err.println("  WARN: " + s);
    }

    private static void error(String s)
    {
        System.err.println("  ERROR: " + s);
    }

    private static void debug(String s1)
    {
    }

    private ImportsReader()
    {
    }

    public static void readImports(DataSpec dataSpec, String imports)
    {
        StringTokenizer st = new StringTokenizer(imports, ",: \t\n\r");
        StringBuffer importsList = new StringBuffer();
        String token;
        for(; st.hasMoreTokens(); importsList.append("import " + token + ";\n"))
        {
            token = st.nextToken();
            readDataSpecInstance(dataSpec, token);
        }

        Settings.setImportsString(importsList.toString());
    }

    private static void readDataSpecInstance(DataSpec dataSpec, String token)
    {
        if(!token.endsWith(".*"))
        {
            debug("skipping " + token);
            return;
        }
        for(int i = 0; i < dataSpecInstanceNames.length; i++)
            try
            {
                String className = token.substring(0, token.length() - 1) + dataSpecInstanceNames[i];
                Class classObj = Class.forName(className);
                Method method = classObj.getMethod("getInstance", null);
                DataSpec importSpec = (DataSpec)method.invoke(classObj, null);
                status("importing tables from " + className);
                TableSpec importTable;
                for(Enumeration e = importSpec.getTableSpecElements(); e.hasMoreElements(); dataSpec.addImportTableSpec(importTable))
                    importTable = (TableSpec)e.nextElement();

            }
            catch(ClassNotFoundException cnfe)
            {
                debug(cnfe.toString());
            }
            catch(Exception e)
            {
                warn(e.toString());
            }

    }

    private static final String dataSpecInstanceNames[] = {
        "DataSpecInstance", "SessionDataSpecInstance", "MasterDataSpecInstance", "AccountDataSpecInstance", "LoginSessionDataSpecInstance", "LoginMasterDataSpecInstance", "LoginAccountDataSpecInstance"
    };

}
