// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SummaryWriter.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.Enumeration;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            TableSpec, ColumnSpec, DataSpec

public class SummaryWriter
{

    public SummaryWriter()
    {
    }

    public void write(DataSpec ds)
    {
        for(Enumeration et = ds.getTableSpecElements(); et.hasMoreElements();)
        {
            TableSpec ts = (TableSpec)et.nextElement();
            System.out.println("Table: " + ts.asHeader() + ", " + ts.asClass() + ", " + ts.asBean() + ", " + ts.asHome() + ", " + ts.asInterface() + ", " + ts.asPKClass() + ", " + ts.asPKParam() + ", ");
            ColumnSpec cs;
            for(Enumeration ec = ts.getColumnElements(); ec.hasMoreElements(); System.out.println("\tColumn " + cs.asHeader() + "(" + cs.columnSize() + "," + cs.decimalDigits() + ")," + cs.asClass() + ", " + cs.asPKClass() + ", " + cs.asParam() + ", " + cs.asMember() + ", " + cs.asLocal() + ", " + cs.asPKParam() + ", " + cs.asType() + ", " + cs.asSetter() + ", " + cs.asGetter() + ", "))
                cs = (ColumnSpec)ec.nextElement();

            ColumnSpec cs;
            for(Enumeration ep = ts.getPrimaryKeyComponents(); ep.hasMoreElements(); System.out.println("\tPrimaryKey " + cs.asHeader()))
                cs = (ColumnSpec)ep.nextElement();

            ColumnSpec cs;
            for(Enumeration ep = ts.getForeignKeyColumns(); ep.hasMoreElements(); System.out.println("\tForeignKey " + cs.asHeader() + " -> " + cs.getForeignTableName() + "(" + cs.getForeignColumnName() + ")"))
                cs = (ColumnSpec)ep.nextElement();

            Enumeration eu = ts.getNonPKCreateRequireds();
            while(eu.hasMoreElements()) 
            {
                ColumnSpec cs = (ColumnSpec)eu.nextElement();
                System.out.println("\tOther required column for create: " + cs.asHeader() + (cs.getIsPrimaryKeyComponent() ? "(p)" : ""));
            }
        }

    }
}
