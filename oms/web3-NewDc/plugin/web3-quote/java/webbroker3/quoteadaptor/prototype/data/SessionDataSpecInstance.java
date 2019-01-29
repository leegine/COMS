head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec web3_quote_proto_columns[] = {
    new ColumnSpec("quote_data_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("quote_date",java.sql.Types.VARCHAR,8,0,false,false,null,null),
    new ColumnSpec("real_type",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("data_type",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("product_code",java.sql.Types.VARCHAR,9,0,false,false,null,null),
    new ColumnSpec("contract_month",java.sql.Types.VARCHAR,6,0,true,false,null,null),
    new ColumnSpec("put_and_call",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("strike_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("open_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("open_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("high_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("high_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("low_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("low_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("current_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("current_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("current_price_flag",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("change",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("volume",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("volume_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("ask_price_title",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("ask_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("ask_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("bid_price_title",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("bid_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bid_price_time",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("base_price",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("web3_quote_proto",web3_quote_proto_columns,"inv=0"),
  };

  private static final SessionDataSpecInstance instance = new SessionDataSpecInstance();

  private SessionDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
