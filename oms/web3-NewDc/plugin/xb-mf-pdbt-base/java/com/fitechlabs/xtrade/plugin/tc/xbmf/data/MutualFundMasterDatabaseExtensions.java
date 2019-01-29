head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.11.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundMasterDatabaseExtensions.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class MutualFundMasterDatabaseExtensions extends Plugin {

  private MutualFundMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( MutualFundMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "mutual_fund_traded_product",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "mf_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_product",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao.FACTORY );
  }

}
@
