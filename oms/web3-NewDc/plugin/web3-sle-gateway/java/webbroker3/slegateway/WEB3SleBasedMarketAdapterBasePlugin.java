head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleBasedMarketAdapterBasePlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleBasedMarketAdapterBasePluginクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 呉 新規作成
                    2006/05/29 孫 リカバリー部分を追加 
 */
package webbroker3.slegateway;

import webbroker3.slebase.data.SleMarketAdapterSessionDatabaseExtensions;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleOpenStatusEnum;
import webbroker3.slegateway.handler.WEB3SleBasedMarketAdapterPluginMessagesHandler;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryResponse;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;
import webbroker3.slegateway.message.WEB3ProcessSleSendqResponse;
import webbroker3.slegateway.service.delegate.WEB3SleRecoveryProcessorManagerService;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorManagerService;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorService;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleConnectorManager;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleRecoveryProcessorManagerServiceImpl;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleSendqProcessorManagerServiceImpl;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleSendqProcessorServiceImpl;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;

/**
 * SLE Connectorを使ったマーケット・アダプタープラグインです。
 */
public final class WEB3SleBasedMarketAdapterBasePlugin extends Plugin {

  private static final WEB3LogUtility m_log          = WEB3LogUtility
                                                         .getInstance(WEB3SleBasedMarketAdapterBasePlugin.class);


  public static boolean               isPluggingDone = false;


  private WEB3SleBasedMarketAdapterBasePlugin() {
    ;
  }

  /**
   * プラグインをプラグします。
   */
  public static void plug() throws Exception {
    plug(WEB3SleBasedMarketAdapterBasePlugin.class);
  }

  /**
   * プラグされる時のコールバックメソッドです。
   */
  public static void onPlug() throws Exception {

    m_log.entering("onPlug()");
    if (isPluggingDone) {
      m_log.info("Already plugged in. Ignoring the call.");
      return;
    }
    m_log.info("Plugging in SLE based MarketAdapterBase.");


    com.fitechlabs.xtrade.kernel.boot.KernelPlugin.plug();
    com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.plug();

	//baseのDBエクステンションを登録する
    SleMarketAdapterSessionDatabaseExtensions.plug();

    // サービスの登録
    m_log.info("Installing necessary services...");
    registerServices();

    m_log.info("Adding enums ");
    //baseのエナムー登録する
    addEnums();

    m_log.info("registering messages and handlers");
    registerMessagesAndHandlers();
    
    //↓インタセプタ追加(2006/9/21)
    m_log.info("Adding interceptor");
    //送信サービスインタセプタ登録する
    addInterceptors();


    //final Class[] preloadClasses = { WEB3SleMarketAdapterErrorMessageDef.class };//ErrorCatogクラスを削除 2006/10/23


    m_log.exiting("onPlug()");
    // plugging done
    isPluggingDone = true;
  }

  /**
   * 定数の追加
   */
  private static void addEnums() throws Exception {
    final Class[] enums = { 
    	SleSendqOpTypeEnum.class,//オペレータタイプ
        SleSendqProcStatusEnum.class,//送信ステータス
		SleRcvdqProcStatusEnum.class,//下りキュー処理区分
        SleConnectionStatusEnum.class,//SLEコネクタとSLE直結エンジン間の接続状態
        SleOpenStatusEnum.class,//SLE接続状態管理レコードのオープン状態
    };

    for (int i = 0; i < enums.length; i++) {
      EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
    }
  }

  /**
   * ハンドルの登録
   */
  private static void registerMessagesAndHandlers() throws Exception {


    final Class[] messages = {
            WEB3ProcessSleSendqRequest.class, WEB3ProcessSleSendqResponse.class,
            WEB3ProcessSleRecoveryRequest.class,WEB3ProcessSleRecoveryResponse.class//reg class .2006/05/29 孫　@追加
            };
    for (int i = 0; i < messages.length; i++) {
      regClass(messages[i]);
    }


    regHandler(WEB3SleBasedMarketAdapterBasePlugin.class, WEB3ProcessSleSendqRequest.class,
            WEB3SleBasedMarketAdapterPluginMessagesHandler.class, "handleSendQ");
    //reg handler .2006/05/29 孫　@追加
    regHandler(WEB3SleBasedMarketAdapterBasePlugin.class, WEB3ProcessSleRecoveryRequest.class,
            WEB3SleBasedMarketAdapterPluginMessagesHandler.class, "handleRecovery");
    
  }

  /**サービスの登録*/
  private static void registerServices() throws Exception {
	
	// send_q manager service.2006/9/21 李 追加
    Services.registerService(WEB3SleSendqProcessorManagerService.class,new WEB3SleSendqProcessorManagerServiceImpl());

    //recovery service.2006/05/29 孫　@追加
    Services.registerService(WEB3SleRecoveryProcessorManagerService.class, new WEB3SleRecoveryProcessorManagerServiceImpl());
    
    //send_q service.2006/9/21 李追加
	Services.registerService(WEB3SleSendqProcessorService.class, 
							 new WEB3SleSendqProcessorServiceImpl(
							 )); 
  }
  
  /**
   * サービスインタセプタの追加
   */
   private static void addInterceptors() throws Exception {
   	
		// Serviceインタセプタの設定
		// 1)send_qメッセージ1件送信サービスインタセプタ
		Services.addInterceptor(
			WEB3SleSendqProcessorService.class,
		  	new WEB3SLEGatewayInterceptor());
	
		// RAC-CTXインタセプタの設定
		// 1)send_qメッセージ1件送信サービスRAC-CTXインタセプタ 追加 2006/11/7
		Services.addInterceptor(
			WEB3SleSendqProcessorService.class,
			new  WEB3SleSenderRacCtxInterceptor());
   }

  /**
   * アンプラグ時にはすべてのSLEコネクタおよびその他のリソースをクローズします。
   */
  public static void onUnplug() throws Exception {

      WEB3SleConnectorManager.close();
  }
}@
