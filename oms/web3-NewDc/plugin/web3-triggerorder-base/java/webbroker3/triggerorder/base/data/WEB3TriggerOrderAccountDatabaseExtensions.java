head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3TriggerOrderAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TriggerOrderAccountDatabaseExtensions extends Plugin {

  private WEB3TriggerOrderAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TriggerOrderAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.triggerorder.base.data.RsvEqOrderUnitPK.class );
    regClass(  webbroker3.triggerorder.base.data.RsvEqOrderUnitParams.class );
    regClass(  webbroker3.triggerorder.base.data.RsvEqOrderActionParams.class );
    regClass(  webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams.class );
    regClass(  webbroker3.triggerorder.base.data.RsvIfoOrderUnitPK.class );
    regClass(  webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams.class );
    regClass(  webbroker3.triggerorder.base.data.RsvIfoOrderActionParams.class );
    regClass(  webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "rsv_eq_order_unit",
      webbroker3.triggerorder.base.data.RsvEqOrderUnitPK.class,
      webbroker3.triggerorder.base.data.RsvEqOrderUnitParams.class,
      null,
      null );
    regDbExtension( "account", "rsv_eq_order_action",
      null,
      webbroker3.triggerorder.base.data.RsvEqOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "rsv_eq_closing_contract_spec",
      null,
      webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams.class,
      null,
      null );
    regDbExtension( "account", "rsv_ifo_order_unit",
      webbroker3.triggerorder.base.data.RsvIfoOrderUnitPK.class,
      webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams.class,
      null,
      null );
    regDbExtension( "account", "rsv_ifo_order_action",
      null,
      webbroker3.triggerorder.base.data.RsvIfoOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "rsv_ifo_closing_contract_spec",
      null,
      webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.triggerorder.base.data.RsvEqOrderUnitRow.class,
        webbroker3.triggerorder.base.data.RsvEqOrderUnitDao.FACTORY );
    regDao(
        webbroker3.triggerorder.base.data.RsvEqOrderActionRow.class,
        webbroker3.triggerorder.base.data.RsvEqOrderActionDao.FACTORY );
    regDao(
        webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow.class,
        webbroker3.triggerorder.base.data.RsvEqClosingContractSpecDao.FACTORY );
    regDao(
        webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow.class,
        webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao.FACTORY );
    regDao(
        webbroker3.triggerorder.base.data.RsvIfoOrderActionRow.class,
        webbroker3.triggerorder.base.data.RsvIfoOrderActionDao.FACTORY );
    regDao(
        webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow.class,
        webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecDao.FACTORY );
  }

}
@
