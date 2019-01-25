// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColumnSpec.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.StringTokenizer;

public class ColumnSpec
{

    ColumnSpec(String name, int sqlType, int size, int digits, boolean isNullable)
    {
        foreignTableName = null;
        foreignColumnName = null;
        headerString = name;
        this.sqlType = sqlType;
        this.size = size;
        this.digits = digits;
        isPkComponent = false;
        this.isNullable = isNullable;
        isPrimitiveType = mapsToPrimitiveType(sqlType);
        classString = convertTableNameToClassName(headerString);
        pkClassString = classString + "PK";
        String stub = uncapfirst(classString);
        paramString = "p_" + stub;
        memberString = name;
        localString = "l_" + stub;
        pkParamString = paramString + "PK";
        typeString = stringFor(sqlType, false);
        stub = stringFor(sqlType, true);
        capTypeString = stub.equals("Int") ? "Integer" : stub;
        setterString = "set" + stub;
        getterString = "get" + stub;
    }

    public ColumnSpec(String name, int sqlType, int size, int digits, boolean isNullable, boolean isPrimaryKeyComponent, String foreignKeyTableName, 
            String foreignKeyColumnName)
    {
        this(name, sqlType, size, digits, isNullable);
        setIsPrimaryKeyComponent(isPrimaryKeyComponent);
        if(foreignKeyTableName != null)
            setForeignKeyNames(foreignKeyTableName, foreignKeyColumnName);
    }

    ColumnSpec(ColumnSpec pattern, String alias)
    {
        this(alias, pattern.sqlType, pattern.size, pattern.digits, pattern.isNullable);
    }

    public void setCustomType(String className, String defaultValue)
    {
        isCustomType = true;
        typeString = className;
        capTypeString = className;
        codegenDefaultValue = defaultValue;
        isPrimitiveType = false;
    }

    public boolean getIsCustomType()
    {
        return isCustomType;
    }

    public void setHasDatabaseDefaultValue(boolean value)
    {
        databaseDefaultValue = value;
    }

    public String getCodegenDefaultValue()
    {
        return codegenDefaultValue;
    }

    public boolean hasCodegenDefaultValue()
    {
        return codegenDefaultValue != null;
    }

    public boolean hasDatabaseDefaultValue()
    {
        return databaseDefaultValue;
    }

    public boolean shouldSkipOnInsertWhenNotSetByUser()
    {
        return hasDatabaseDefaultValue() && !hasCodegenDefaultValue();
    }

    public boolean shouldTrackUserSet()
    {
        return shouldSkipOnInsertWhenNotSetByUser() || mustBeSetByUserBeforeInsert();
    }

    public boolean mustBeSetByUserBeforeInsert()
    {
        return !isNullable && !hasDatabaseDefaultValue() && !hasCodegenDefaultValue();
    }

    public int hashCode()
    {
        return headerString.hashCode();
    }

    public String toString()
    {
        return headerString;
    }

    public String asHeader()
    {
        return headerString;
    }

    public String asClass()
    {
        return classString;
    }

    public String asPKClass()
    {
        return pkClassString;
    }

    public String asParam()
    {
        return paramString;
    }

    public String asMember()
    {
        return memberString;
    }

    public String asLocal()
    {
        return localString;
    }

    public String asPKParam()
    {
        return pkParamString;
    }

    public String asType()
    {
        return typeString;
    }

    public String asCapType()
    {
        return capTypeString;
    }

    public String asSetter()
    {
        return setterString;
    }

    public String asGetter()
    {
        return getterString;
    }

    public int columnSize()
    {
        return size;
    }

    public int decimalDigits()
    {
        return digits;
    }

    public void setForeignKeyNames(String table, String column)
    {
        foreignTableName = table;
        foreignColumnName = column;
        ftClassString = convertTableNameToClassName(table);
        String columnString = convertTableNameToClassName(column);
        ftGetterString = "get" + columnString;
        ftPKClassString = "PK" + ftClassString;
        ftMemberString = "m_" + uncapfirst(columnString);
    }

    public String getForeignTableName()
    {
        return foreignTableName;
    }

    public String getForeignColumnName()
    {
        return foreignColumnName;
    }

    public String asFTClass()
    {
        return ftClassString;
    }

    public String asFTGetter()
    {
        return ftGetterString;
    }

    public String asFTPKClass()
    {
        return ftPKClassString;
    }

    public String asFTMember()
    {
        return ftMemberString;
    }

    public void setIsPrimaryKeyComponent(boolean b)
    {
        isPkComponent = b;
        isNullable = b ? false : isNullable;
    }

    public boolean getIsPrimaryKeyComponent()
    {
        return isPkComponent;
    }

    public boolean getIsNullable()
    {
        return isNullable;
    }

    public boolean getIsPrimitiveType()
    {
        return isPrimitiveType;
    }

    public int getSqlType()
    {
        return sqlType;
    }

    public static String convertTableNameToClassName(String underscorebased)
    {
        StringTokenizer st = new StringTokenizer(underscorebased.toLowerCase(), "_");
        String result;
        for(result = ""; st.hasMoreTokens(); result = result + capfirst(st.nextToken()));
        return result;
    }

    private static String capfirst(String lowercase)
    {
        return lowercase.substring(0, 1).toUpperCase() + lowercase.substring(1);
    }

    public static String uncapfirst(String mixedcase)
    {
        return mixedcase.substring(0, 1).toLowerCase() + mixedcase.substring(1);
    }

    public static String toMember(String tableOrPackageName)
    {
        return "m_" + uncapfirst(toCapName(tableOrPackageName));
    }

    public static String toCapName(String tableOrPackageName)
    {
        StringTokenizer st = new StringTokenizer(tableOrPackageName.toLowerCase(), "_.-");
        String result;
        for(result = ""; st.hasMoreTokens(); result = result + capfirst(st.nextToken()));
        return result;
    }

    private boolean mapsToPrimitiveType(int sqlType)
    {
        switch(sqlType)
        {
        case -7: 
        case -6: 
        case -5: 
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return true;

        case -4: 
        case -3: 
        case -2: 
        case -1: 
        case 0: // '\0'
        case 1: // '\001'
        default:
            return false;
        }
    }

    private String stringFor(int sqlType, boolean getSetStub)
    {
        switch(sqlType)
        {
        case -7: 
            return getSetStub ? "Boolean" : "boolean";

        case -6: 
            return getSetStub ? "Byte" : "byte";

        case 5: // '\005'
            return getSetStub ? "Short" : "short";

        case 4: // '\004'
            return getSetStub ? "Int" : "int";

        case -5: 
            return getSetStub ? "Long" : "long";

        case -4: 
            return getSetStub ? "Bytes" : "byte[]";

        case 6: // '\006'
            return getSetStub ? "Double" : "double";

        case 7: // '\007'
            return getSetStub ? "Float" : "float";

        case 8: // '\b'
            return getSetStub ? "Double" : "double";

        case 1: // '\001'
            return "String";

        case 12: // '\f'
            return "String";

        case -1: 
            return "String";

        case 91: // '['
            return getSetStub ? "Date" : "java.sql.Date";

        case 92: // '\\'
            return getSetStub ? "Time" : "java.sql.Time";

        case 93: // ']'
            return getSetStub ? "Timestamp" : "java.sql.Timestamp";

        case 2: // '\002'
        case 3: // '\003'
            if(digits > 0)
                return getSetStub ? "Double" : "double";
            if(size > 9 || size == 0)
                return getSetStub ? "Long" : "long";
            else
                return getSetStub ? "Int" : "int";
        }
        System.err.println("** column \"" + headerString + "\":");
        System.err.println("**   unknown sql type value java.sql.Types == " + sqlType);
        return "void*";
    }

    public String asProcArgType()
    {
        switch(getSqlType())
        {
        case -7: 
            return "boolean";

        case -6: 
        case -5: 
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return "number";

        case -1: 
        case 1: // '\001'
        case 12: // '\f'
            return "varchar2";

        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
            return "date";
        }
        System.err.println("** column \"" + headerString + "\":");
        System.err.println("**   unknown sql type value java.sql.Types == " + sqlType);
        return "varchar2";
    }

    public boolean isArrayType()
    {
        return sqlType == -4;
    }

    private String headerString;
    private String classString;
    private String pkClassString;
    private String paramString;
    private String memberString;
    private String localString;
    private String pkParamString;
    private String typeString;
    private String capTypeString;
    private String setterString;
    private String getterString;
    private String codegenDefaultValue;
    private boolean databaseDefaultValue;
    private int sqlType;
    private int size;
    private int digits;
    private boolean isPkComponent;
    private boolean isNullable;
    private boolean isPrimitiveType;
    private boolean isCustomType;
    private String foreignTableName;
    private String foreignColumnName;
    private String ftClassString;
    private String ftGetterString;
    private String ftPKClassString;
    private String ftMemberString;
    private String dbTypeString;
}
