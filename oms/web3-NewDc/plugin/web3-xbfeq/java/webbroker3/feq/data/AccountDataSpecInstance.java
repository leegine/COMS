head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccountDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class AccountDataSpecInstance extends DataSpec {

  private static final ColumnSpec f_cash_balance_columns[] = {
    new ColumnSpec("feq_cash_balance_id",java.sql.Types.NUMERIC,18,0,false,true,null,null),
    new ColumnSpec("account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","account_id"),
    new ColumnSpec("sub_account_id",java.sql.Types.NUMERIC,18,0,false,false,"sub_account","sub_account_id"),
    new ColumnSpec("currency_code",java.sql.Types.VARCHAR,3,0,false,false,null,null),
    new ColumnSpec("cash_balance0",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance1",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance2",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance3",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance4",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("cash_balance5",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("f_cash_balance",f_cash_balance_columns,"inv=?.account_id"),
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
