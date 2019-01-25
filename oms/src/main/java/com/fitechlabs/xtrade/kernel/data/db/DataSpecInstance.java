// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSpecInstance.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.gen.*;

public class DataSpecInstance extends DataSpec
{

    private DataSpecInstance()
    {
        super(tables);
    }

    public static DataSpec getInstance()
    {
        return instance;
    }

    private static final ColumnSpec server_config_columns[] = {
        new ColumnSpec("config_title", 12, 32, 0, false, true, null, null), new ColumnSpec("config_categ", 12, 32, 0, false, true, null, null), new ColumnSpec("config_name", 12, 64, 0, false, true, null, null), new ColumnSpec("config_value", 12, 512, 0, true, false, null, null)
    };
    private static final ColumnSpec server_license_columns[] = {
        new ColumnSpec("license_id", 12, 48, 0, false, true, null, null), new ColumnSpec("component", 12, 64, 0, false, false, null, null), new ColumnSpec("licensee", 12, 64, 0, false, false, null, null), new ColumnSpec("expire_date", 93, 0, 0, false, false, null, null), new ColumnSpec("license_xml", 12, 2048, 0, false, false, null, null)
    };
    private static final ColumnSpec segment_url_columns[] = {
        new ColumnSpec("segment_id", 2, 18, 0, false, true, null, null), new ColumnSpec("data_source_url", 12, 128, 0, false, false, null, null)
    };
    private static final ColumnSpec account_segment_columns[] = {
        new ColumnSpec("account_id", 2, 18, 0, false, true, null, null), new ColumnSpec("segment_id", 2, 18, 0, false, false, "segment_url", "segment_id")
    };
    private static final ColumnSpec error_message_columns[] = {
        new ColumnSpec("error_message_id", 2, 18, 0, false, true, null, null), new ColumnSpec("error_code", 12, 32, 0, false, false, null, null), new ColumnSpec("error_tag", 12, 64, 0, false, false, null, null), new ColumnSpec("localized_text", 12, 1024, 0, true, false, null, null), new ColumnSpec("initial_text", 12, 1024, 0, false, false, null, null), new ColumnSpec("context_text", 12, 1024, 0, true, false, null, null), new ColumnSpec("error_class", 12, 256, 0, false, false, null, null)
    };
    private static final TableSpec tables[] = {
        new TableSpec("server_config", server_config_columns, "inv=0"), new TableSpec("server_license", server_license_columns, "inv=0"), new TableSpec("segment_url", segment_url_columns, "inv=0"), new TableSpec("account_segment", account_segment_columns, "inv=0"), new TableSpec("error_message", error_message_columns, "inv=0")
    };
    private static final DataSpecInstance instance = new DataSpecInstance();

}
