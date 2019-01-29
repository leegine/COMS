head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec acc_open_item_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("validate_type",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("item_symbol_name",java.sql.Types.VARCHAR,30,0,false,true,null,null),
    new ColumnSpec("item_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("necessary_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("item_min",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("item_max",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("item_check_mode",java.sql.Types.VARCHAR,2,0,true,false,null,null),
  };
  private static final ColumnSpec acc_open_item_attribute_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("item_symbol_name",java.sql.Types.VARCHAR,30,0,false,true,null,null),
    new ColumnSpec("attribute_name",java.sql.Types.VARCHAR,64,0,false,false,null,null),
    new ColumnSpec("attribute_value",java.sql.Types.VARCHAR,4,0,false,true,null,null),
    new ColumnSpec("range_from",java.sql.Types.DECIMAL,18,6,true,false,null,null),
    new ColumnSpec("range_to",java.sql.Types.DECIMAL,18,6,true,false,null,null),
  };
  private static final ColumnSpec acc_open_dl_form_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("account_div",java.sql.Types.VARCHAR,1,0,false,true,null,null),
    new ColumnSpec("column_number",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("column_label",java.sql.Types.VARCHAR,64,0,true,false,null,null),
    new ColumnSpec("column_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("date_format",java.sql.Types.VARCHAR,30,0,true,false,null,null),
    new ColumnSpec("input_item_symbol_name",java.sql.Types.VARCHAR,30,0,false,false,null,null),
    new ColumnSpec("cat_delimitter",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("section_number",java.sql.Types.NUMERIC,6,0,true,false,null,null),
  };
  private static final ColumnSpec acc_open_waiting_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("acc_open_request_number",java.sql.Types.VARCHAR,13,0,false,true,null,null),
    new ColumnSpec("serial_no",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("review_code",java.sql.Types.VARCHAR,2,0,false,false,null,null),
    new ColumnSpec("duplication_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("diplo_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("diplo_account_code",java.sql.Types.VARCHAR,7,0,true,false,null,null),
    new ColumnSpec("y_customer_id",java.sql.Types.NUMERIC,18,0,true,false,null,null),
    new ColumnSpec("control_branch_code",java.sql.Types.VARCHAR,3,0,true,false,null,null),
    new ColumnSpec("operation_div",java.sql.Types.VARCHAR,2,0,true,false,null,null),
    new ColumnSpec("control_number",java.sql.Types.NUMERIC,7,0,true,false,null,null),
    new ColumnSpec("check_div",java.sql.Types.VARCHAR,1,0,false,false,null,null),
    new ColumnSpec("checker_code",java.sql.Types.VARCHAR,20,0,true,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("acc_open_item_master",acc_open_item_master_columns,"inv=0"),
    new TableSpec("acc_open_item_attribute",acc_open_item_attribute_columns,"inv=0"),
    new TableSpec("acc_open_dl_form_master",acc_open_dl_form_master_columns,"inv=0"),
    new TableSpec("acc_open_waiting",acc_open_waiting_columns,"inv=0"),
  };

  private static final MasterDataSpecInstance instance = new MasterDataSpecInstance();

  private MasterDataSpecInstance() {
    super( tables );
  }

  public static DataSpec getInstance() {
    return instance;
  };

}
@
