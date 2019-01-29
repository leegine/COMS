head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleBasedMarketAdapterBasePlugin.java;


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
 */

package webbroker3.slebase;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import webbroker3.slebase.data.SleMarketAdapterSessionDatabaseExtensions;
import webbroker3.slebase.enums.*;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * ベースプラグインです。
 * </p>
 */
public final class SleBasedMarketAdapterBasePlugin extends Plugin {

  private static final WEB3LogUtility m_log          = WEB3LogUtility
                                                         .getInstance(SleBasedMarketAdapterBasePlugin.class);

  public static boolean               isPluggingDone = false;

  /**
   * singleton constructor
   */
  private SleBasedMarketAdapterBasePlugin() {
    ;
  }

  /**
   * プラグインをプラグします。
   */
  public static void plug() throws Exception {
    plug(SleBasedMarketAdapterBasePlugin.class);
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


    SleMarketAdapterSessionDatabaseExtensions.plug();


    m_log.info("Adding enums ");
    addEnums();


    m_log.exiting("onPlug()");

    isPluggingDone = true;
  }

  /**
   * enumsの追加
   */
  private static void addEnums() throws Exception {
    final Class[] enums = {
        SleSendqProcStatusEnum.class,//送信キュー処理区分
		SleRcvdqProcStatusEnum.class,//下りキュー処理区分
		SleConnectionStatusEnum.class,//SLEコネクタステータス
		SleOpenStatusEnum.class,//オープンステータス 
		SleSendqOpTypeEnum.class,//オペレータタイプ
    };

    for (int i = 0; i < enums.length; i++) {
      EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
    }
  }

  /**
   * アンプラグ時にはすべてのSLEコネクタおよびその他のリソースをクローズします。
   */
  public static void onUnplug() throws Exception {
    ;
  }
}@
