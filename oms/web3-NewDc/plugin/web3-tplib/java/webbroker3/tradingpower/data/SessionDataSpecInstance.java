head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec tp_asset_history_columns[] = {
    new ColumnSpec("tp_asset_history_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,null,null),
    new ColumnSpec("calc_date",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("account_balance",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("equity_asset_delivered",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("mini_stock_asset_delivered",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("ruito_asset_delivered",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("mutual_fund_asset_delivered",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("bond_asset_delivered",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec tp_calc_result_exec_notify_columns[] = {
    new ColumnSpec("occurred_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"main_account","account_id"),
    new ColumnSpec("real_calc_flag",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("mark_to_market_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("rowid",java.sql.Types.VARCHAR,20,20,false,true,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("tp_asset_history",tp_asset_history_columns,null),
    new TableSpec("tp_calc_result_exec_notify",tp_calc_result_exec_notify_columns,"rowid_pk=true"),
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
