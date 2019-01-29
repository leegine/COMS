head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AdminTPSessionDatabaseExtensions extends Plugin {

  private WEB3AdminTPSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AdminTPSessionDatabaseExtensions.class );
  }

  public static void onPlug() throws Exception {

    // dependencies
    com.fitechlabs.xtrade.kernel.boot.KernelPlugin.plug();

    // extensions
    regClasses();
    regDbExtensions();
    regDataObjectClasses();
  }

  private static void regClasses() throws Exception {
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionPK.class );
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionParams.class );
    regClass(  webbroker3.tradingpoweradmin.data.RequisitionAccountEquityParams.class );
    regClass(  webbroker3.tradingpoweradmin.data.RequisitionAccountMarginParams.class );
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityPK.class );
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityParams.class );
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginPK.class );
    regClass(  webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "payment_requisition",
      webbroker3.tradingpoweradmin.data.PaymentRequisitionPK.class,
      webbroker3.tradingpoweradmin.data.PaymentRequisitionParams.class,
      null,
      null );
    regDbExtension( "session", "requisition_account_equity",
      null,
      webbroker3.tradingpoweradmin.data.RequisitionAccountEquityParams.class,
      null,
      null );
    regDbExtension( "session", "requisition_account_margin",
      null,
      webbroker3.tradingpoweradmin.data.RequisitionAccountMarginParams.class,
      null,
      null );
    regDbExtension( "session", "payment_requisition_equity",
      webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityPK.class,
      webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityParams.class,
      null,
      null );
    regDbExtension( "session", "payment_requisition_margin",
      webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginPK.class,
      webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradingpoweradmin.data.PaymentRequisitionRow.class,
        webbroker3.tradingpoweradmin.data.PaymentRequisitionDao.FACTORY );
    regDao(
        webbroker3.tradingpoweradmin.data.RequisitionAccountEquityRow.class,
        webbroker3.tradingpoweradmin.data.RequisitionAccountEquityDao.FACTORY );
    regDao(
        webbroker3.tradingpoweradmin.data.RequisitionAccountMarginRow.class,
        webbroker3.tradingpoweradmin.data.RequisitionAccountMarginDao.FACTORY );
    regDao(
        webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityRow.class,
        webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityDao.FACTORY );
    regDao(
        webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow.class,
        webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginDao.FACTORY );
  }

}
@
