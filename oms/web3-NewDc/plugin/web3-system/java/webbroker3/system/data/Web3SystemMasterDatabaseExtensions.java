head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	Web3SystemMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class Web3SystemMasterDatabaseExtensions extends Plugin {

  private Web3SystemMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( Web3SystemMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.system.data.AffinityKeyBasedMapPK.class );
    regClass(  webbroker3.system.data.AffinityKeyBasedMapParams.class );
    regClass(  webbroker3.system.data.AffinityRangeBasedMapPK.class );
    regClass(  webbroker3.system.data.AffinityRangeBasedMapParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "rac-config", "affinity_key_based_map",
      webbroker3.system.data.AffinityKeyBasedMapPK.class,
      webbroker3.system.data.AffinityKeyBasedMapParams.class,
      null,
      null );
    regDbExtension( "rac-config", "affinity_range_based_map",
      webbroker3.system.data.AffinityRangeBasedMapPK.class,
      webbroker3.system.data.AffinityRangeBasedMapParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.system.data.AffinityKeyBasedMapRow.class,
        webbroker3.system.data.AffinityKeyBasedMapDao.FACTORY );
    regDao(
        webbroker3.system.data.AffinityRangeBasedMapRow.class,
        webbroker3.system.data.AffinityRangeBasedMapDao.FACTORY );
  }

}
@
