// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MasterDataSpecInstance.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.dbind.gen.*;

public class MasterDataSpecInstance extends DataSpec
{

    private MasterDataSpecInstance()
    {
        super(tables);
    }

    public static DataSpec getInstance()
    {
        return instance;
    }

    private static final ColumnSpec eqtype_sample_quote_data_columns[] = {
        new ColumnSpec("product_code", 12, 10, 0, false, true, null, null), new ColumnSpec("market_code", 12, 20, 0, false, true, null, null), new ColumnSpec("open_price", 3, 18, 3, true, false, null, null), new ColumnSpec("current_price", 3, 18, 3, true, false, null, null), new ColumnSpec("bid_price", 3, 18, 3, true, false, null, null), new ColumnSpec("ask_price", 3, 18, 3, true, false, null, null), new ColumnSpec("volume", 3, 18, 3, true, false, null, null), new ColumnSpec("bid_size", 3, 18, 3, true, false, null, null), new ColumnSpec("ask_size", 3, 18, 3, true, false, null, null), new ColumnSpec("ytd_low_price", 3, 18, 3, true, false, null, null), 
        new ColumnSpec("ytd_high_price", 3, 18, 3, true, false, null, null), new ColumnSpec("last_closing_price", 3, 18, 3, true, false, null, null), new ColumnSpec("prev_trading_day_closing_price", 3, 18, 3, true, false, null, null), new ColumnSpec("diff", 3, 18, 3, true, false, null, null), new ColumnSpec("created_timestamp", 93, 0, 0, false, false, null, null), new ColumnSpec("last_updated_timestamp", 93, 0, 0, false, false, null, null)
    };
    private static final TableSpec tables[] = {
        new TableSpec("eqtype_sample_quote_data", eqtype_sample_quote_data_columns, "inv=0")
    };
    private static final MasterDataSpecInstance instance = new MasterDataSpecInstance();

}
