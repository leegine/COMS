head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	MqGatewayMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mqgateway.stdimpls.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class MqGatewayMasterDatabaseExtensions extends Plugin {

  private MqGatewayMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( MqGatewayMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsPK.class );
    regClass(  webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "mq_message_id_mappings",
      webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsPK.class,
      webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow.class,
        webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsDao.FACTORY );
  }

}
@
