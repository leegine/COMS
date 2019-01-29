head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.59.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	MasterDataSpecInstance.java;


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

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec inform_ctrl_item_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("item_symbol_name",java.sql.Types.VARCHAR,30,0,false,true,null,null),
    new ColumnSpec("item_name",java.sql.Types.VARCHAR,50,0,true,false,null,null),
    new ColumnSpec("necessary_flag",java.sql.Types.NUMERIC,1,0,false,false,null,null),
    new ColumnSpec("item_min",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("item_max",java.sql.Types.NUMERIC,6,0,true,false,null,null),
    new ColumnSpec("item_check_mode",java.sql.Types.VARCHAR,2,0,true,false,null,null),
  };
  private static final ColumnSpec inform_ctrl_item_attribute_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("item_symbol_name",java.sql.Types.VARCHAR,30,0,false,true,null,null),
    new ColumnSpec("attribute_name",java.sql.Types.VARCHAR,32,0,false,false,null,null),
    new ColumnSpec("attribute_value",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("range_from",java.sql.Types.DECIMAL,15,0,true,false,null,null),
    new ColumnSpec("range_to",java.sql.Types.DECIMAL,15,0,true,false,null,null),
  };
  private static final ColumnSpec inform_dl_form_master_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("branch_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("column_number",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("column_label",java.sql.Types.VARCHAR,30,0,true,false,null,null),
    new ColumnSpec("column_type",java.sql.Types.NUMERIC,6,0,false,false,null,null),
    new ColumnSpec("date_format",java.sql.Types.VARCHAR,30,0,true,false,null,null),
    new ColumnSpec("input_item_symbol_name",java.sql.Types.VARCHAR,30,0,false,false,null,null),
    new ColumnSpec("cat_delimitter",java.sql.Types.VARCHAR,1,0,true,false,null,null),
    new ColumnSpec("section_number",java.sql.Types.NUMERIC,6,0,true,false,null,null),
  };
  private static final ColumnSpec inform_div_preferences_columns[] = {
    new ColumnSpec("branch_id",java.sql.Types.NUMERIC,18,0,false,true,"branch","branch_id"),
    new ColumnSpec("inform_div",java.sql.Types.VARCHAR,2,0,false,true,null,null),
    new ColumnSpec("name",java.sql.Types.VARCHAR,80,0,false,true,null,null),
    new ColumnSpec("name_serial_no",java.sql.Types.NUMERIC,6,0,false,true,null,null),
    new ColumnSpec("value",java.sql.Types.VARCHAR,200,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("inform_ctrl_item_master",inform_ctrl_item_master_columns,"inv=0"),
    new TableSpec("inform_ctrl_item_attribute",inform_ctrl_item_attribute_columns,"inv=0"),
    new TableSpec("inform_dl_form_master",inform_dl_form_master_columns,"inv=0"),
    new TableSpec("inform_div_preferences",inform_div_preferences_columns,"inv=0"),
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
