// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Settings.java

package com.fitechlabs.dbind.gen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Settings
{

    public Settings()
    {
    }

    public static void setBuildNumber(String string)
    {
        buildNumber = string;
    }

    public static void setBuildIdentifier(String string)
    {
        buildIdString = string;
    }

    public static String buildIdentifier()
    {
        return buildNumber + "." + buildIdString;
    }

    private static final String tmpFileRoot()
    {
        return relPathToRoot + "/" + "";
    }

    public static void setTmpFileSubdir(String s)
    {
        tmpFileSubdir = s + "/";
    }

    public static String tmpFileRelPath()
    {
        return tmpFileRoot() + tmpFileSubdir;
    }

    public static void setDevPath(String dev)
    {
        relPathToRoot = dev;
    }

    public static String getAbsPath(String relPath)
    {
        if(rootDirectory == null)
            return relPath;
        if(relPath.startsWith(rootDirectory))
            return relPath;
        boolean rootEnd = rootDirectory.endsWith("/");
        boolean pathStart = relPath.startsWith("/");
        if(rootEnd && pathStart)
            return rootDirectory + relPath.substring(1);
        if(!rootEnd && !pathStart)
            return rootDirectory + "/" + relPath;
        else
            return rootDirectory + relPath;
    }

    public static void setSubPackageName(String name)
    {
        subPackageName = name;
    }

    public static String getSubPackageName()
    {
        return subPackageName;
    }

    public static String beanPackageName()
    {
        return subPackageName;
    }

    public static String beanPackageSubdir()
    {
        return beanPackageName().replace('.', '/');
    }

    public static String beanSourceRelPath()
    {
        return tmpFileRelPath() + beanPackageSubdir() + "/";
    }

    public static String daoSourceRelPath()
    {
        String relpath = beanSourceRelPath();
        return relpath;
    }

    public static String daoPackageName()
    {
        String rowpkg = beanPackageName();
        String daopkg = rowpkg;
        return daopkg;
    }

    public static String buildVersion()
    {
        return "bld-" + buildIdentifier() + "-" + timestamp;
    }

    public static void setDatabaseClassName(String dbname)
    {
        databaseClassName = dbname;
    }

    public static String getDatabaseClassName()
    {
        return databaseClassName;
    }

    public static void setProcessor(String p)
    {
        processor = p;
    }

    public static String getProcessor()
    {
        return processor;
    }

    public static void setImportsString(String s)
    {
        importsString = s;
    }

    public static String getImportsString()
    {
        return importsString;
    }

    public static final String beanPackageRoot = "com.fitechlabs.dbind";
    public static boolean verbose = true;
    public static String buildNumber = "1";
    public static String buildIdString = "d";
    public static final String timestamp = (new SimpleDateFormat("yy/MM/dd-HH:mm")).format(new Date());
    public static final String genPackageName = "com.fitechlabs.dbind";
    public static String relPathToRoot = "../../../..";
    public static final String tmpSubdir = "";
    public static final String interfaceSubdir = "interface";
    public static final String debugSubdir = "debug";
    public static final String releaseSubdir = "release";
    private static String tmpFileSubdir = "";
    public static String rootDirectory = null;
    public static String processor = "session";
    public static String importsString = "";
    public static String subPackageName = "auto";
    private static String databaseClassName = "DbDatabase";

}
