// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritesTriggers.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ColumnSpec, Settings, DataSpec

public final class WritesTriggers
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

    private WritesTriggers()
    {
    }

    public static void writeTriggers(DataSpec ds, String triggers, Properties variables)
        throws IOException
    {
        PrintWriter pw;
        PrintWriter rw;
        int d = triggers.lastIndexOf('/');
        String dir = "";
        if(d >= 0)
        {
            dir = triggers.substring(0, d);
            (new File(dir)).mkdirs();
            dir = dir + "/";
        }
        dir = Settings.getAbsPath(dir);
        String adders = dir + triggers.substring(d + 1);
        String removers = dir + "remove_" + triggers.substring(d + 1);
        status("creating " + adders);
        status("creating " + removers);
        pw = new PrintWriter(new FileOutputStream(adders));
        rw = new PrintWriter(new FileOutputStream(removers));
        pw.println("------------------------------------------------------------------");
        pw.println("-- Autogenerated per-table triggers for mutators");
        pw.println("------------------------------------------------------------------");
        pw.println();
        rw.println("------------------------------------------------------------------");
        rw.println("-- Autogenerated per-table trigger removers");
        rw.println("------------------------------------------------------------------");
        rw.println();
        for(Enumeration e = ds.getTableSpecElements(); e.hasMoreElements();)
        {
            TableSpec ts = (TableSpec)e.nextElement();
            pw.println("--------------------------------------------------");
            pw.println("--- " + ts.asHeader());
            pw.println();
            String inv[] = ts.getAttributeValues("inv");
            String select = null;
            if(inv != null)
            {
                for(int i = 0; i < inv.length; i++)
                {
                    if(!inv[i].startsWith("$$"))
                        continue;
                    select = (String)variables.get(inv[i]);
                    if(select == null)
                        error("Invalidation-Account-Select variable " + inv[i] + " not found.");
                }

                writeTriggers(pw, rw, ts, inv, select);
            } else
            {
                warn("table " + ts.asHeader() + " has no 'inv' attribute in the tables property, skipping trigger for this table");
            }
        }

        pw.close();
        rw.close();
        break MISSING_BLOCK_LABEL_501;
        Exception exception;
        exception;
        pw.close();
        rw.close();
        throw exception;
    }

    private static void writeTriggers(PrintWriter pw, PrintWriter rw, TableSpec ts, String inv[], String select)
    {
        String table = ts.asHeader();
        StringBuffer pk = new StringBuffer();
        String joiner = "";
        for(Enumeration e = ts.getPrimaryKeyComponents(); e.hasMoreElements();)
        {
            ColumnSpec cs = (ColumnSpec)e.nextElement();
            pk.append(joiner);
            switch(cs.getSqlType())
            {
            case 91: // '['
            case 93: // ']'
                pk.append("to_char(?.");
                pk.append(cs.asHeader());
                pk.append(", 'YYMMDDHH24MISS')");
                break;

            default:
                pk.append("?.");
                pk.append(cs.asHeader());
                break;
            }
            joiner = "||','||";
        }

        String pks = pk.length() <= 0 ? "?.rowid" : pk.toString();
        String declaration = select != null ? "    l_acct number;\n" : "";
        String assignment = select != null ? "    " + replace(select, " from ", " into l_acct from ") + ";\n" : "";
        int MAX_TRIGGER_STEM_LENGTH = 22;
        String stem = table.substring(0, Math.min(table.length(), MAX_TRIGGER_STEM_LENGTH));
        pw.println("create trigger inv_" + stem + "_ins");
        pw.println("  after insert on " + table + "");
        pw.println("  for each row");
        pw.println("  declare");
        pw.print(declaration);
        pw.println("  begin");
        pw.print(replace(assignment, "?", ":new"));
        for(int i = 0; i < inv.length; i++)
        {
            String ref = select != null ? "l_acct" : inv[i];
            pw.println("    invalidation.on_mutate( '" + table + "', 'ins', " + replace(ref, "?", ":new") + ", " + replace(pks, "?", ":new") + " );");
        }

        pw.println("  end;");
        pw.println("/");
        pw.println("show errors");
        pw.println("");
        pw.println("create trigger inv_" + stem + "_upd");
        pw.println("  before update on " + table + "");
        pw.println("  for each row");
        pw.println("  declare");
        pw.print(declaration);
        pw.println("  begin");
        pw.print(replace(assignment, "?", ":old"));
        for(int i = 0; i < inv.length; i++)
        {
            String ref = select != null ? "l_acct" : inv[i];
            pw.println("    invalidation.on_mutate( '" + table + "', 'upd', " + replace(ref, "?", ":old") + ", " + replace(pks, "?", ":old") + " );");
        }

        pw.println("  end;");
        pw.println("/");
        pw.println("show errors");
        pw.println("");
        pw.println("create trigger inv_" + stem + "_del");
        pw.println("  before delete on " + table + "");
        pw.println("  for each row");
        pw.println("  declare");
        pw.print(declaration);
        pw.println("  begin");
        pw.print(replace(assignment, "?", ":old"));
        for(int i = 0; i < inv.length; i++)
        {
            String ref = select != null ? "l_acct" : inv[i];
            pw.println("    invalidation.on_mutate( '" + table + "', 'del', " + replace(ref, "?", ":old") + ", " + replace(pks, "?", ":old") + " );");
        }

        pw.println("  end;");
        pw.println("/");
        pw.println("show errors");
        pw.println("");
        rw.println("drop trigger inv_" + stem + "_ins;");
        rw.println("drop trigger inv_" + stem + "_upd;");
        rw.println("drop trigger inv_" + stem + "_del;");
    }

    private static String replace(String string, String pattern, String replacement)
    {
        StringBuffer b = new StringBuffer();
        int i = 0;
        for(int j = string.indexOf(pattern); j >= 0; j = string.indexOf(pattern, i))
        {
            b.append(string.substring(i, j));
            b.append(replacement);
            i = j + pattern.length();
        }

        b.append(string.substring(i));
        return b.toString();
    }
}