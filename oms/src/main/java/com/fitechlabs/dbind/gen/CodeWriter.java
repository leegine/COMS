// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CodeWriter.java

package com.fitechlabs.dbind.gen;

import java.io.PrintStream;
import java.util.Enumeration;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            WritesPKs, WritesRows, WritesParams, WritesDaos, 
//            DataSpecWriter, TableSpec, Settings, DataSpec

public class CodeWriter
{

    public CodeWriter()
    {
        primaryKeyWriter = new WritesPKs();
        rowWriter = new WritesRows();
        paramsWriter = new WritesParams();
        daosWriter = new WritesDaos();
        dataSpecWriter = new DataSpecWriter();
    }

    public void write(DataSpec ds, String enc)
    {
        try
        {
            Settings.verbose = true;
            TableSpec ts;
            for(Enumeration et = ds.getTableSpecElements(); et.hasMoreElements(); daosWriter.write(ts, enc))
            {
                ts = (TableSpec)et.nextElement();
                primaryKeyWriter.write(ts, enc);
                rowWriter.write(ts, enc);
                paramsWriter.write(ts, enc);
            }

            dataSpecWriter.write(ds);
        }
        catch(Exception e)
        {
            System.err.println("CodeWriter: " + e);
            e.printStackTrace();
        }
    }

    WritesPKs primaryKeyWriter;
    WritesRows rowWriter;
    WritesParams paramsWriter;
    WritesDaos daosWriter;
    DataSpecWriter dataSpecWriter;
}
