head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibIfoDepositSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TPLibIfoDepositSessionDatabaseExtensions extends Plugin {

  private WEB3TPLibIfoDepositSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TPLibIfoDepositSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.ifodeposit.data.IfoDepositCalcResultPK.class );
    regClass(  webbroker3.ifodeposit.data.IfoDepositCalcResultParams.class );
    regClass(  webbroker3.ifodeposit.data.IfoDepositCalcLockPK.class );
    regClass(  webbroker3.ifodeposit.data.IfoDepositCalcLockParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "ifo_deposit_calc_result",
      webbroker3.ifodeposit.data.IfoDepositCalcResultPK.class,
      webbroker3.ifodeposit.data.IfoDepositCalcResultParams.class,
      null,
      null );
    regDbExtension( "session", "ifo_deposit_calc_lock",
      webbroker3.ifodeposit.data.IfoDepositCalcLockPK.class,
      webbroker3.ifodeposit.data.IfoDepositCalcLockParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.ifodeposit.data.IfoDepositCalcResultRow.class,
        webbroker3.ifodeposit.data.IfoDepositCalcResultDao.FACTORY );
    regDao(
        webbroker3.ifodeposit.data.IfoDepositCalcLockRow.class,
        webbroker3.ifodeposit.data.IfoDepositCalcLockDao.FACTORY );
  }

}
@
