head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MasterDataSpecInstance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.data;

import com.fitechlabs.dbind.gen.*;
import java.sql.Types;

public class MasterDataSpecInstance extends DataSpec {

  private static final ColumnSpec faq_number_columns[] = {
    new ColumnSpec("institution_code",java.sql.Types.VARCHAR,3,0,false,true,null,null),
    new ColumnSpec("last_faq_number",java.sql.Types.VARCHAR,13,0,false,false,null,null),
    new ColumnSpec("created_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
    new ColumnSpec("last_updated_timestamp",java.sql.Types.TIMESTAMP,0,0,false,false,null,null),
  };

  private static final TableSpec tables[] = {
    new TableSpec("faq_number",faq_number_columns,"inv=0"),
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
