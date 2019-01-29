head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecInquiryAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Admin Order Exec Inquiry AppPlugin(WEB3AdminOrderExecInquiryAppPlugin.java)
Author Name      : Daiwa Institute of Research
                   2004/10/25 黄建 (中訊) 仕様変更モデルNo.058
*/
package webbroker3.adminorderexecinquiry;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.adminorderexecinquiry.data.WEB3AdminorderexecinquiryMasterDatabaseExtensions;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminBondExecuteReferenceHandler;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminEquityExecuteReferenceHandler;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminFeqExecutionReferenceHandler;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminIfoExecuteReferenceHandler;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminMutualRuitoExecuteReferenceHandler;
import webbroker3.adminorderexecinquiry.handler.WEB3AdminOrderExecuteCountReferenceHandler;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminBondExecuteReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminEquityExecuteReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminFeqExecutionReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminIfoExecuteReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminMutualRuitoExecuteReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminOrderExecuteCountReferenceService;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminBondExecuteReferenceServiceImpl;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminEquityExecuteReferenceServiceImpl;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminFeqExecutionReferenceServiceImpl;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminIfoExecuteReferenceServiceImpl;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminMutualRuitoExecuteReferenceServiceImpl;
import webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminOrderExecuteCountReferenceServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mf.data.WEB3MfMasterDatabaseExtensions;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Eqtypeadmin<BR>
 * WEB3AdminOrderExecInquiryAppPlugin
 * @@author SRAI
 * @@version 1.0
 */
public final class WEB3AdminOrderExecInquiryAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminOrderExecInquiryAppPlugin.class);

    /**
     * デフォルトコンストラクタ
     */
    public WEB3AdminOrderExecInquiryAppPlugin()
    {
    }

    /**
     * plug method
     * @@throws Exception exception
     */
    public static void plug() throws Exception
    {
        final String STR_METHOD_NAME = "plug()";
        log.entering(STR_METHOD_NAME);
        plug(WEB3AdminOrderExecInquiryAppPlugin.class);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * onPlug method
     * @@throws Exception exception
     */
    public static void onPlug() throws Exception
    {
        final String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        KernelPlugin.plug();

        // DatabaseExtensions のプラグイン処理
        WEB3AdminorderexecinquiryMasterDatabaseExtensions.plug();

        WEB3MfMasterDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 1)Registering the service implementation for Admin Equity Execute Reference
        Services.registerService(WEB3AdminEquityExecuteReferenceService.class,
            new WEB3AdminEquityExecuteReferenceServiceImpl());

       //2 Registering the service implementation for AdminMutualRuitoExecuteReference
       Services.registerService(WEB3AdminMutualRuitoExecuteReferenceService.class,
                  new WEB3AdminMutualRuitoExecuteReferenceServiceImpl());

        // 3. Registering the service implementation for WEB3AdminOrderExecuteCountReference
        Services.registerService(WEB3AdminOrderExecuteCountReferenceService.class,
                   new WEB3AdminOrderExecuteCountReferenceServiceImpl());

        //4 Registering the service implementation for AdminIfoExecuteReferenceService
        Services.registerService(WEB3AdminIfoExecuteReferenceService.class,
                   new WEB3AdminIfoExecuteReferenceServiceImpl());
                   
        //5 Registering the service implementation for AdminFeqExecutionReferenceService
        Services.registerService(
            WEB3AdminFeqExecutionReferenceService.class,
            new WEB3AdminFeqExecutionReferenceServiceImpl());      
        
        //6 Registering the service implementation for AdminFeqExecutionReferenceService
        Services.registerService(
            WEB3AdminBondExecuteReferenceService.class,
            new WEB3AdminBondExecuteReferenceServiceImpl()); 

        // 1) Registering the Admin Equity Execute Reference Service
        Services.addInterceptor(WEB3AdminEquityExecuteReferenceService.class,
            new WEB3AdminEquityExecuteReferenceServiceInterceptor());

        // 2) Registering the AdminMutualRuitoExecuteReference Service
        Services.addInterceptor(WEB3AdminMutualRuitoExecuteReferenceService.class,
            new WEB3AdminMutualRuitoExecuteReferenceServiceInterceptor());

        // 3) Registering the AdminOrderExecuteCountReference Service
        Services.addInterceptor(WEB3AdminOrderExecuteCountReferenceService.class,
            new WEB3AdminOrderExecuteCountReferenceServiceInterceptor());

        //4) Registering the AdminIfoExecuteReferenceService
        Services.addInterceptor(WEB3AdminIfoExecuteReferenceService.class,
                   new WEB3AdminIfoExecuteReferenceServiceInterceptor());
                   
        //5) Registering the AdminFeqExecutionReferenceService
        Services.addInterceptor(
            WEB3AdminFeqExecutionReferenceService.class,
            new WEB3AdminFeqExecutionReferenceServiceInterceptor());  
        
        //6) Registering the WEB3AdminBondExecuteReferenceService
        Services.addInterceptor(
            WEB3AdminBondExecuteReferenceService.class,
            new WEB3AdminBondExecuteReferenceInterceptor()); 

        // 1) registering the SysTime interceptor for Admin Equity Execute Reference
        Services.addInterceptor(WEB3AdminEquityExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //2)  registering the SysTime interceptor for  AdminMutualRuitoExecuteReference
        Services.addInterceptor(WEB3AdminMutualRuitoExecuteReferenceService.class,
                   new WEB3LogSysTimeInterceptor());

        //3)  registering the SysTime interceptor for WEB3AdminOrderExecuteCountReferenceService
        Services.addInterceptor(WEB3AdminOrderExecuteCountReferenceService.class,
                   new WEB3LogSysTimeInterceptor());

        //4)  registering the SysTime interceptor for  AdminIfoExecuteReference
        Services.addInterceptor(WEB3AdminIfoExecuteReferenceService.class,
                   new WEB3LogSysTimeInterceptor());
        
        //5) Registering the AdminFeqExecutionReferenceService
        Services.addInterceptor(
            WEB3AdminFeqExecutionReferenceService.class,
            new WEB3LogSysTimeInterceptor()); 
        
        //6) registering the SysTime interceptor for  WEB3AdminBondExecuteReferenceService
        Services.addInterceptor(
            WEB3AdminBondExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor()); 

        // 1) Registering Transaction interceptor for Admin Equity Execute Reference
        Services.addInterceptor(WEB3AdminEquityExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //2) Registering Transaction interceptor for AdminMutualRuitoExecuteReference
        Services.addInterceptor(WEB3AdminMutualRuitoExecuteReferenceService.class,
                   new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //3) Registering Transaction interceptor for WEB3AdminOrderExecuteCountReferenceService
        Services.addInterceptor(WEB3AdminOrderExecuteCountReferenceService.class,
                   new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //4) Registering Transaction interceptor for AdminIfoExecuteReference
        Services.addInterceptor(WEB3AdminIfoExecuteReferenceService.class,
                   new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //5) Registering the AdminFeqExecutionReferenceService
        Services.addInterceptor(
            WEB3AdminFeqExecutionReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //6) Registering the WEB3AdminBondExecuteReferenceService
        Services.addInterceptor(
            WEB3AdminBondExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        // Message Fro Admin Equity Execute Reference
        // 1-1)
        regClass(WEB3AdminOREquityOrderExecutionRefInputRequest.class);
        regClass(WEB3AdminOREquityOrderExecutionRefInputResponse.class);
        // 1-2)
        regClass(WEB3AdminOREquityOrderExecutionRefReferenceRequest.class);
        regClass(WEB3AdminOREquityOrderExecutionRefReferenceResponse.class);

        // Message Fro AdminMutualRuitoExecuteReference
        // 2-1
        regClass(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest.class);
        regClass(WEB3AdminORMutualRuitoOrderExecutionRefInputResponse.class);

        // 2-2)
        regClass(WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse.class);
        regClass(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.class);

        // Message Fro AdminOrderExecuteCountReferenceService
        // 3-1
        regClass(WEB3AdminORExecutionNumberInputRequest.class);
        regClass(WEB3AdminORExecutionNumberInputResponse.class);

        // 3-2)
        regClass(WEB3AdminORExecutionNumberReferenceRequest.class);
        regClass(WEB3AdminORExecutionNumberReferenceResponse.class);

        //Message for AdminIfoExecuteReference
        // 4-1 getReferenceScreen
        regClass(WEB3AdminORFutOpOrderExecutionRefReferenceRequest.class);
        regClass(WEB3AdminORFutOpOrderExecutionRefReferenceResponse.class);

        // 4-2) getInputScreen
        regClass(WEB3AdminORFutOpOrderExecutionRefInputResponse.class);
        regClass(WEB3AdminORFutOpOrderExecutionRefInputRequest.class);
        
        //Message for AdminFeqExecutionReference
        //5-1 getExecInputList
        regClass(WEB3AdminORFeqExecuteListRequest.class);
        regClass(WEB3AdminORFeqExecuteListResponse.class);
        
        //5-2 getInputScreen
        regClass(WEB3AdminORFeqOrderExecutionRefInputRequest.class);
        regClass(WEB3AdminORFeqOrderExecutionRefInputResponse.class);
        
        //5-3 getReferenceScreen
        regClass(WEB3AdminORFeqOrderExecutionRefReferenceRequest.class);
        regClass(WEB3AdminORFeqOrderExecutionRefReferenceResponse.class);
        
        //Message for WEB3AdminBondExecuteReferenceService
        //6-1 bondExecRefReferenceRequest
        regClass(WEB3AdminORBondExecRefReferenceRequest.class);
        regClass(WEB3AdminORBondExecRefReferenceResponse.class);
        
        //6-2 bondExecRefInputRequest
        regClass(WEB3AdminORBondExecRefInputRequest.class);
        regClass(WEB3AdminORBondExecRefInputResponse.class);

        // 1-1) Handler For Admin Equity Execute Reference
        regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminOREquityOrderExecutionRefInputRequest .class,
         WEB3AdminEquityExecuteReferenceHandler.class,
           "getInputScreen");
        // 1-2) Handler For Admin Equity Execute Reference
        regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
           WEB3AdminOREquityOrderExecutionRefReferenceRequest.class,
           WEB3AdminEquityExecuteReferenceHandler.class,
           "getReferenceScreen");

        // 2-1) Handler For AdminMutualRuitoExecuteReference
        regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORMutualRuitoOrderExecutionRefInputRequest.class,
        WEB3AdminMutualRuitoExecuteReferenceHandler.class,
             "getInputScreen");
        // 2-2) Handler For Admin Equity Execute Reference
             regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.class,
        WEB3AdminMutualRuitoExecuteReferenceHandler.class,
                "getReferenceScreen");

        // 3-1) Handler For AdminOrderExecuteCountReferenceService
        regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORExecutionNumberInputRequest.class,
        WEB3AdminOrderExecuteCountReferenceHandler.class,
             "getInputScreen");
        // 3-2) Handler For AdminOrderExecuteCountReferenceService
             regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORExecutionNumberReferenceRequest.class,
        WEB3AdminOrderExecuteCountReferenceHandler.class,
                "getReferenceScreen");

        //4-1) Handler For  Admin Ifo Execute Reference
        regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORFutOpOrderExecutionRefInputRequest.class,
        WEB3AdminIfoExecuteReferenceHandler.class,
             "getInputScreen");
        //4-2) Handler For  Admin Ifo Execute Reference
             regHandler(WEB3AdminOrderExecInquiryAppPlugin.class,
        WEB3AdminORFutOpOrderExecutionRefReferenceRequest.class,
        WEB3AdminIfoExecuteReferenceHandler.class,
                "getReferenceScreen");
                
        // 5-1) Handler For AdminFeqExecutionReferenceService  
        regHandler(
            WEB3AdminOrderExecInquiryAppPlugin.class,
            WEB3AdminORFeqOrderExecutionRefInputRequest.class,
            WEB3AdminFeqExecutionReferenceHandler.class,
            "getInputScreen");
        // 5-2) Handler For AdminFeqExecutionReferenceService      
        regHandler(
            WEB3AdminOrderExecInquiryAppPlugin.class,
            WEB3AdminORFeqOrderExecutionRefReferenceRequest.class,
            WEB3AdminFeqExecutionReferenceHandler.class,
            "getReferenceScreen");
        // 5-3) Handler For AdminFeqExecutionReferenceService      
        regHandler(
            WEB3AdminOrderExecInquiryAppPlugin.class,
            WEB3AdminORFeqExecuteListRequest.class,
            WEB3AdminFeqExecutionReferenceHandler.class,
            "getExecInputList");
        
        //6-1) Handler For AdminBondExecuteReferenceService  
        regHandler(
            WEB3AdminOrderExecInquiryAppPlugin.class,
            WEB3AdminORBondExecRefReferenceRequest.class,
            WEB3AdminBondExecuteReferenceHandler.class,
            "getReferenceScreen");
        //6-2) Handler For AdminBondExecuteReferenceService  
        regHandler(
            WEB3AdminOrderExecInquiryAppPlugin.class,
            WEB3AdminORBondExecRefInputRequest.class,
            WEB3AdminBondExecuteReferenceHandler.class,
            "getInputScreen");

        log.exiting(METHOD_NAME);
    }
}@
