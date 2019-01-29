head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.trialcalc.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec account_portfolio_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("portfolio_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("portfolio_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("list_range",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };
  private static final ColumnSpec account_portfolio_product_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,"branch","branch_id"),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,true,"main_account","account_id"),
    new ColumnSpec("portfolio_code",java.sql.Types.VARCHAR,10,0,false,true,null,null),
    new ColumnSpec("product_id",java.sql.Types.NUMERIC,18,0,false,true,"product","product_id"),
    new ColumnSpec("market_code",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("quantity",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("buy_price",java.sql.Types.DECIMAL,18,6,false,true,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("account_portfolio",account_portfolio_columns,"inv=?.account_id"),
    new TableSpec("account_portfolio_product",account_portfolio_product_columns,"inv=?.account_id"),
  };

  private static final AccountDataSpecInstance instance = new AccountDataSpecInstance();

  private AccountDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
