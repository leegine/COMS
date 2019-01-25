// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateTableParserConstants.java

package com.fitechlabs.dbind.gen.parser;


public interface CreateTableParserConstants
{

    public static final int EOF = 0;
    public static final int CREATE = 5;
    public static final int TABLE = 6;
    public static final int NOT = 7;
    public static final int NULL = 8;
    public static final int CONSTRAINT = 9;
    public static final int UNIQUE = 10;
    public static final int PRIMARY = 11;
    public static final int DEFAULT_VAL = 12;
    public static final int FOREIGN = 13;
    public static final int REFERENCES = 14;
    public static final int LONG = 15;
    public static final int ID = 16;
    public static final int INTEGER = 17;
    public static final int FLOAT = 18;
    public static final int STRING = 19;
    public static final int DEFAULT = 0;
    public static final String tokenImage[] = {
        "<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"CREATE\"", "\"TABLE\"", "\"NOT\"", "\"NULL\"", "\"CONSTRAINT\"", 
        "\"UNIQUE\"", "\"PRIMARY\"", "\"DEFAULT\"", "\"FOREIGN\"", "\"REFERENCES\"", "\"LONG\"", "<ID>", "<INTEGER>", "<FLOAT>", "<STRING>", 
        "\"(\"", "\")\"", "\",\""
    };

}
