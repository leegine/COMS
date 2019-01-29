head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	GenTradeAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class GenTradeAccountDatabaseExtensions extends Plugin {

  private GenTradeAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( GenTradeAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "account_preferences",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesParams.class,
      null,
      null );
    regDbExtension( "account", "asset",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams.class,
      null,
      null );
    regDbExtension( "account", "asset_unit",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitParams.class,
      null,
      null );
    regDbExtension( "account", "asset_unit_sales",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesParams.class,
      null,
      null );
    regDbExtension( "account", "gen_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "locked_asset_details",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams.class,
      null,
      null );
    regDbExtension( "account", "participant",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantParams.class,
      null,
      null );
    regDbExtension( "account", "sub_account",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams.class,
      null,
      null );
    regDbExtension( "account", "sub_account_preferences",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AccountPreferencesDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountPreferencesDao.FACTORY );
  }

}
@
