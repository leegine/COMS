head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	SessionDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class SessionDataSpecInstance extends DataSpec {

  private static final ColumnSpec various_inform_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("request_number",java.sql.Types.VARCHAR,13,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("account_name",java.sql.Types.VARCHAR,40,0,true,false,null,null),
    new ColumnSpec("email_address",java.sql.Types.VARCHAR,100,0,true,false,null,null),
    new ColumnSpec("ext_div1",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div2",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div3",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div4",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div5",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div6",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div7",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div8",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div9",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div10",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div11",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div12",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div13",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div14",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div15",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div16",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div17",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div18",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div19",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div20",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div21",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div22",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div23",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div24",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div25",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div26",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div27",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div28",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div29",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div30",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div31",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div32",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div33",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div34",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div35",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div36",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div37",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div38",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div39",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_div40",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("ext_code1",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code2",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code3",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code4",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code5",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code6",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code7",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code8",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code9",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_code10",java.sql.Types.VARCHAR,10,0,true,false,null,null),
    new ColumnSpec("ext_text1",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text2",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text3",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text4",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text5",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text6",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text7",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text8",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text9",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text10",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text11",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text12",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text13",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text14",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text15",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text16",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text17",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text18",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text19",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text20",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text21",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text22",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text23",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text24",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text25",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text26",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text27",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text28",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text29",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text30",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text31",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text32",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text33",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text34",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text35",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text36",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text37",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text38",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text39",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_text40",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("ext_value1",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value2",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value3",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value4",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value5",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value6",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value7",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value8",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value9",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value10",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value11",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value12",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value13",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value14",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value15",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value16",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value17",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value18",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value19",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value20",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value21",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value22",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value23",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value24",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value25",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value26",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value27",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value28",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value29",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_value30",java.sql.Types.NUMERIC,15,0,true,false,null,null),
    new ColumnSpec("ext_note1",java.sql.Types.VARCHAR,200,0,true,false,null,null),
    new ColumnSpec("ext_note2",java.sql.Types.VARCHAR,1000,0,true,false,null,null),
    new ColumnSpec("last_updater",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("fund_code",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("sonar_trader_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("status",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("error_reason_code",java.sql.Types.VARCHAR,4,0,true,false,null,null),
    new ColumnSpec("order_request_number",java.sql.Types.VARCHAR,9,0,true,false,null,null),
    new ColumnSpec("request_code",java.sql.Types.VARCHAR,5,0,true,false,null,null),
    new ColumnSpec("send_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
    new ColumnSpec("receipt_timestamp",java.sql.Types.TIMESTAMP,0,0,true,false,null,null),
  };
  private static final ColumnSpec inform_ctrl_request_number_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("last_info_ctrl_request_number",java.sql.Types.VARCHAR,13,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("various_inform",various_inform_columns,null),
    new TableSpec("inform_ctrl_request_number",inform_ctrl_request_number_columns,null),
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
