head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TriggerOrderMockPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImplForMock;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesCancelOrderServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractOrderServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImplForMock;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImplForMock;

public class WEB3TriggerOrderMockPlugin extends Plugin {

	public WEB3TriggerOrderMockPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void plug()
    throws Exception
	{
	    plug(WEB3TriggerOrderMockPlugin.class);
	}
	
	public static void onPlug()
	    throws Exception
	{
        Services.overrideService(WEB3ToSuccReservationIfoOrderUpdateService.class, new WEB3ToSuccReservationIfoOrderUpdateServiceImplForMock());
        
        Services.overrideService(WEB3OptionOrderCarryOverUnitService.class, new WEB3OptionOrderCarryOverUnitServiceImplForMock());
        // WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock
        Services.overrideService(WEB3ToSuccReservationEqTypeOrderUpdateService.class, new WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock());

        // WEB3ToSuccFuturesOpenContractInputServiceImplForMock
        Services.overrideService(WEB3ToSuccFuturesOpenContractInputService.class,new WEB3ToSuccFuturesOpenContractInputServiceImplForMock());
        
        // WEB3ToSuccFuturesCancelOrderServiceImplForMock
        Services.overrideService(WEB3ToSuccFuturesCancelOrderService.class,new WEB3ToSuccFuturesCancelOrderServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccFuturesSettleContractInputService.class, new WEB3ToSuccFuturesSettleContractInputServiceImplForMock());
 
        Services.overrideService(WEB3ToSuccFuturesSettleContractOrderService.class, new WEB3ToSuccFuturesSettleContractOrderServiceImplForMock());
 
        Services.overrideService(WEB3ToSuccFuturesChangeClosingContractInputService.class, new WEB3ToSuccFuturesChangeClosingContractInputServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionCancelOrderService.class, new WEB3ToSuccOptionCancelOrderServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionOpenContractOrderService.class, new WEB3ToSuccOptionOpenContractOrderServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccFuturesChangeClosingContractService.class, new WEB3ToSuccFuturesChangeClosingContractServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccFuturesChangeOpenContractService.class, new WEB3ToSuccFuturesChangeOpenContractServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccFuturesChangeOpenContractInputService.class, new WEB3ToSuccFuturesChangeOpenContractInputServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionChangeClosingContractService.class, new WEB3ToSuccOptionChangeClosingContractServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionChangeClosingContractInputService.class, new WEB3ToSuccOptionChangeClosingContractInputServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionChangeOpenContractService.class, new WEB3ToSuccOptionChangeOpenContractServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionChangeOpenContractInputService.class, new WEB3ToSuccOptionChangeOpenContractInputServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionSettleContractOrderService.class, new WEB3ToSuccOptionSettleContractOrderServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccOptionSettleContractInputService.class, new WEB3ToSuccOptionSettleContractInputServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccDataGettingService.class, new WEB3ToSuccDataGettingServiceImplForMock());
        
        Services.overrideService(WEB3ToSuccIfoOrderUnitService.class, new WEB3ToSuccIfoOrderUnitServiceImplForMock());
 
      Services.overrideService(WEB3FuturesOrderCarryOverUnitService.class, new WEB3FuturesOrderCarryOverUnitServiceImplForMock());
 
	}
}
@
