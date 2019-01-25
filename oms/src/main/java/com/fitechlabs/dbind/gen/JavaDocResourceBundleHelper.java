// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaDocResourceBundleHelper.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ColumnSpec

class JavaDocResourceBundleHelper
{
    static class SubstituteValue
    {

        String getValue(Map map)
            throws Exception
        {
            switch(type)
            {
            case 1: // '\001'
                return method.invoke(map.get("ts"), new Object[0]).toString();

            case 2: // '\002'
                return method.invoke(map.get("cs"), new Object[0]).toString();

            case 3: // '\003'
                return method.invoke(null, new Object[0]).toString();

            case 4: // '\004'
                return map.get(expression).toString();
            }
            throw new RuntimeException("type of SubstituteValue is uncertain");
        }

        static final int TABLE_SPEC_METHOD = 1;
        static final int COLUMN_SPEC_METHOD = 2;
        static final int SETTINGS_STATIC_METHOD = 3;
        static final int VAR = 4;
        int type;
        Method method;
        String expression;

        SubstituteValue(String s)
            throws Exception
        {
            expression = s;
            if(s.startsWith("ts.", 0))
            {
                type = 1;
                String methodName = s.substring("ts.".length(), s.indexOf("("));
                method = (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$TableSpec != null ? JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$TableSpec : (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$TableSpec = JavaDocResourceBundleHelper._mthclass$("com.fitechlabs.dbind.gen.TableSpec"))).getMethod(methodName, new Class[0]);
            } else
            if(s.startsWith("cs.", 0))
            {
                type = 2;
                String methodName = s.substring("cs.".length(), s.indexOf("("));
                method = (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$ColumnSpec != null ? JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$ColumnSpec : (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$ColumnSpec = JavaDocResourceBundleHelper._mthclass$("com.fitechlabs.dbind.gen.ColumnSpec"))).getMethod(methodName, new Class[0]);
            } else
            if(s.startsWith("Settings.", 0))
            {
                type = 3;
                String methodName = s.substring("Settings.".length(), s.indexOf("("));
                method = (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$Settings != null ? JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$Settings : (JavaDocResourceBundleHelper.class$com$fitechlabs$dbind$gen$Settings = JavaDocResourceBundleHelper._mthclass$("com.fitechlabs.dbind.gen.Settings"))).getMethod(methodName, new Class[0]);
            } else
            {
                type = 4;
            }
        }
    }


    static JavaDocResourceBundleHelper instance()
    {
        if(singleton == null)
            singleton = new JavaDocResourceBundleHelper();
        return singleton;
    }

    static void setLocale(Locale locale)
    {
        locale = locale;
    }

    JavaDocResourceBundleHelper()
    {
        templates = new HashMap();
        String baseName = "codegen_javadoc";
        rourceBundle = ResourceBundle.getBundle("codegen_javadoc", locale);
    }

    String getJavaDoc(String key, TableSpec ts, ColumnSpec cs)
    {
        Map map = new HashMap();
        map.put("ts", ts);
        map.put("cs", cs);
        return getJavaDoc(key, map);
    }

    String getJavaDoc(String key, Map map)
    {
        StringBuffer result;
        List template = (List)templates.get(key);
        if(template == null)
            templates.put(key, template = createTemplate(rourceBundle.getString(key)));
        result = new StringBuffer();
        for(int i = 0; i < template.size(); i++)
        {
            Object obj = template.get(i);
            if(obj instanceof SubstituteValue)
                result.append(((SubstituteValue)obj).getValue(map));
            else
                result.append(obj);
        }

        return result.toString();
        Exception e;
        e;
        throw new IllegalStateException("Exception thrown when generating Javadoc:" + e.getMessage());
    }

    List createTemplate(String raw)
        throws Exception
    {
        List template;
label0:
        {
            template = new LinkedList();
            int i = 0;
            String BEGIN = "<%=";
            String END = "%>";
            do
            {
                if(i >= raw.length())
                    break label0;
                int beginPos = raw.indexOf("<%=", i);
                if(beginPos <= -1)
                    break;
                int endPos = raw.indexOf("%>", beginPos + BEGIN.length());
                if(i < beginPos)
                    template.add(raw.substring(i, beginPos));
                template.add(new SubstituteValue(raw.substring(beginPos + BEGIN.length(), endPos)));
                i = endPos + END.length();
            } while(true);
            if(i < raw.length())
                template.add(raw.substring(i));
        }
        return template;
    }

    public static void main(String args[])
    {
        setLocale(new Locale("ja", ""));
        JavaDocResourceBundleHelper helper = instance();
        Field fields[] = (com.fitechlabs.dbind.gen.JavaDocResourceBundleHelper.class).getDeclaredFields();
        for(int i = 0; i < fields.length; i++)
            if(Modifier.isFinal(fields[i].getModifiers()))
            {
                String s = fields[i].getName();
                System.out.println("print " + s + "... ...");
                System.out.println(helper.rourceBundle.getString(s));
                System.out.println();
            }

    }

    static final String PK_CLASS_HEADER = "PK_CLASS_HEADER";
    static final String PK_CLASS_TAGNAME = "PK_CLASS_TAGNAME";
    static final String PK_CLASS_PTYPE = "PK_CLASS_PTYPE";
    static final String PK_CLASS_GET_ROW_TYPE = "PK_CLASS_GET_ROW_TYPE";
    static final String COLUMN = "COLUMN";
    static final String COLUMN_WITH_SIZE = "COLUMN_WITH_SIZE";
    static final String COLUMN_WITH_SIZE_PRECISION = "COLUMN_WITH_SIZE_PRECISION";
    static final String PK_DEFAULT_CONSTRUCTOR = "PK_DEFAULT_CONSTRUCTOR";
    static final String PK_CONSTRUCTOR_HEADER = "PK_CONSTRUCTOR_HEADER";
    static final String PK_FROM_STRING = "PK_FROM_STRING";
    static final String PK_TO_STRING = "PK_TO_STRING";
    static final String PK_EQUALS = "PK_EQUALS";
    static final String PK_HASHCODE = "PK_HASHCODE";
    static final String ROW_HEADER = "ROW_HEADER";
    static final String ROW_ROW_TYPE = "ROW_ROW_TYPE";
    static final String ROW_COLUMN_GETTER = "ROW_COLUMN_GETTER";
    static final String ROW_COLUMN_IS_NULL = "ROW_COLUMN_IS_NULL";
    static final String ROW_COLUMN_IS_SET = "ROW_COLUMN_IS_SET";
    static final String PARAMS_HEADER = "PARAMS_HEADER";
    static final String PARAMS_TAGNAME = "PARAMS_TAGNAME";
    static final String PARAMS_PTYPE = "PARAMS_PTYPE";
    static final String PARAMS_ROWTYPE = "PARAMS_ROWTYPE";
    static final String PARAMS_GET_ROWTYPE = "PARAMS_GET_ROWTYPE";
    static final String PARAMS_GET_PK = "PARAMS_GET_PK";
    static final String PARAMS_SETTER_HEADER = "PARAMS_SETTER_HEADER";
    static final String PARAMS_OBJECT_SETTER_FOR_PRIMITIVE = "PARAMS_OBJECT_SETTER_FOR_PRIMITIVE";
    static final String PARAMS_OBJECT_SETTER_FOR_JAVA_UTIL_DATE = "PARAMS_OBJECT_SETTER_FOR_JAVA_UTIL_DATE";
    static final String PARAMS_GET_COLUMN = "PARAMS_GET_COLUMN";
    static final String PARAMS_SET_COLUMN = "PARAMS_SET_COLUMN";
    static final String PARAMS_TO_STRING = "PARAMS_TO_STRING";
    static final String PARAMS_ASSERT_VALID_FOR_INSERT = "PARAMS_ASSERT_VALID_FOR_INSERT";
    static final String PARAMS_TO_INSERT_MAP = "PARAMS_TO_INSERT_MAP";
    static final String PARAMS_TO_UPDATE_MAP = "PARAMS_TO_UPDATE_MAP";
    static final String PARAMS_DEFAULT_CONSTRUCTOR = "PARAMS_DEFAULT_CONSTRUCTOR";
    static final String PARAMS_CONSTRUCTOR_FOR_SUBTABLE = "PARAMS_CONSTRUCTOR_FOR_SUBTABLE";
    static final String PARAMS_COPY_CONSTRUCTOR = "PARAMS_COPY_CONSTRUCTOR";
    static final String PARAMS_MARK_ALL_VALUE_AS_SET = "PARAMS_MARK_ALL_VALUE_AS_SET";
    static final String PARAMS_EQUALS = "PARAMS_EQUALS";
    static final String PARAMS_FIELDS_EQUALS = "PARAMS_FIELDS_EQUALS";
    static final String PARAMS_HASHCODE = "PARAMS_HASHCODE";
    static final String PARAMS_FIELD = "PARAMS_FIELD";
    static final String DAO_HEADER = "DAO_HEADER";
    static final String DAO_ROW_FIELD = "DAO_ROW_FIELD";
    static final String DAO_FACTORY_FIELD = "DAO_FACTORY_FIELD";
    static final String DAO_FACTORY_NEW_INSTANCE = "DAO_FACTORY_NEW_INSTANCE";
    static final String DAO_CONSTRUCTOR = "DAO_CONSTRUCTOR";
    static final String DAO_GET_ROW = "DAO_GET_ROW";
    static final String DAO_FOR_ROW = "DAO_FOR_ROW";
    static final String DAO_NEW_PK_VALUE = "DAO_NEW_PK_VALUE";
    static final String DAO_NEW_PK_OBJECT = "DAO_NEW_PK_OBJECT";
    static final String DAO_FIND_ROW_BY_PK_VALUE = "DAO_FIND_ROW_BY_PK_VALUE";
    static final String DAO_FIND_ROW_BY_PK_OBJECT = "DAO_FIND_ROW_BY_PK_OBJECT";
    static final String DAO_FIND_DAO_BY_PK_VALUE = "DAO_FIND_DAO_BY_PK_VALUE";
    static final String DAO_FIND_DAO_BY_PK_OBJECT = "DAO_FIND_DAO_BY_PK_OBJECT";
    static final String DAO_FETCH_ROW_VIA_FK = "DAO_FETCH_ROW_VIA_FK";
    static final String DAO_FETCH_ROW_VIA_FK_NULLABLE = "DAO_FETCH_ROW_VIA_FK_NULLABLE";
    static final String DAO_FETCH_DAO_VIA_FK = "DAO_FETCH_DAO_VIA_FK";
    static final String DAO_FETCH_ROWS_BY_FK = "DAO_FETCH_ROWS_BY_FK";
    static final String DAO_FETCH_DAOS_BY_FK = "DAO_FETCH_DAOS_BY_FK";
    static final String DAO_FIND_ROWS_BY_DAO = "DAO_FIND_ROWS_BY_DAO";
    static final String DAO_FIND_ROWS_BY_ROW = "DAO_FIND_ROWS_BY_ROW";
    static final String DAO_FIND_ROWS_BY_PK = "DAO_FIND_ROWS_BY_PK";
    static final String DAO_FIND_ROWS_BY_FK_VALUE = "DAO_FIND_ROWS_BY_FK_VALUE";
    static final String DAO_FIND_DAOS_BY_DAO = "DAO_FIND_DAOS_BY_DAO";
    static final String DAO_FIND_DAOS_BY_ROW = "DAO_FIND_DAOS_BY_ROW";
    static final String DAO_FIND_DAOS_BY_PK = "DAO_FIND_DAOS_BY_PK";
    static final String DAO_FIND_DAOS_BY_PK_VALUE = "DAO_FIND_DAOS_BY_PK_VALUE";
    static final String SEARCH_COLUMN = "SEARCH_COLUMN";
    static final String DAO_FIND_ROW_BY_UNIQUE_INDEX_COLUMN = "DAO_FIND_ROW_BY_UNIQUE_INDEX_COLUMN";
    static final String DAO_FIND_DAO_BY_UNIQUE_INDEX_COLUMN = "DAO_FIND_DAO_BY_UNIQUE_INDEX_COLUMN";
    static final String DAO_FIND_ROWS_BY_INDEX_COLUMN = "DAO_FIND_ROWS_BY_INDEX_COLUMN";
    static final String DAO_FIND_DAOS_BY_INDEX_COLUMN = "DAO_FIND_DAOS_BY_INDEX_COLUMN";
    ResourceBundle rourceBundle;
    static JavaDocResourceBundleHelper singleton = null;
    static Locale locale = Locale.getDefault();
    Map templates;
    static Class class$com$fitechlabs$dbind$gen$TableSpec; /* synthetic field */
    static Class class$com$fitechlabs$dbind$gen$ColumnSpec; /* synthetic field */
    static Class class$com$fitechlabs$dbind$gen$Settings; /* synthetic field */

}
