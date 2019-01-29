head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TplibMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImplForMock;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImplForMock;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TplibMockAppPlugin extends Plugin
{

    public WEB3TplibMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TplibMockAppPlugin.class);

    public static void plug()
    throws Exception
    {
        plug(WEB3TplibMockAppPlugin.class);
    }
    public static void onPlug()
    throws Exception
    {
      final String STR_METHOD_NAME = "plugTplib()";
      log.entering(STR_METHOD_NAME);

      Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
      
      //WEB3TPPaymentRequisitionManageServiceImplForMock
      Services.overrideService(WEB3TPPaymentRequisitionManageService.class, new WEB3TPPaymentRequisitionManageServiceImplForMock());
      
      // WEB3IfoDepositCalcServiceImplForMock()
      Services.overrideService(WEB3IfoDepositCalcService.class, new WEB3IfoDepositCalcServiceImplForMock());
      
      // WEB3TPTradingPowerSettlementServiceImplForMock
      Services.overrideService(WEB3TPTradingPowerSettlementService.class, new WEB3TPTradingPowerSettlementServiceImplForMock());

      //WEB3TPBondSimplexCooperationServiceImplForMock
      Services.overrideService(WEB3TPBondSimplexCooperationService.class, new WEB3TPBondSimplexCooperationServiceImplForMock());

      //WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock
      Services.overrideService(WEB3IfoDepositCalcResultCreatePerAccountService.class, new WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock());

      log.exiting(STR_METHOD_NAME);
    }
}
@
