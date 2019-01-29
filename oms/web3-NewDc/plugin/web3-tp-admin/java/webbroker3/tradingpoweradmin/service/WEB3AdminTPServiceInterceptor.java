head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPServiceInterceptorクラス(WEB3AdminTPServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.service;

import java.lang.reflect.Method;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPOrderAccProductDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProductCodeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * 余力管理者サービスインターセプタ
 *
 * ※詳細未定のため、
 * 各機@能ごとに受付時間チェック設定が異なる場合
 * サービスごとにサービスインターセプタを定義する予定。
 *
 */
public class WEB3AdminTPServiceInterceptor implements Interceptor
{
    /** ログ出力ユーティリティ */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPServiceInterceptor.class);

    /**
     * 取引カレンダコンテキストの属性名
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.tradingcalendarcontext";

    /**
     * 受付時間の属性名
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.timestamp";

    /**
     * オフセットの属性名
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.offset";


   /**
    * @@roseuid 41DBCABF01D6
    */
   public WEB3AdminTPServiceInterceptor()
   {
   }

   /**
    * ※詳細未定
    *
    * １）　@取引カレンダコンテキストに内容をセットする。
    * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストの
    * 　@　@　@プロパティをセットする。
    *
    * 　@取引カレンダコンテキスト.証券会社コード  = 管理者.get証券会社コード()
    * 　@取引カレンダコンテキスト.部店コード  = 管理者.get部店コード()
    * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
    * 　@取引カレンダコンテキスト.受付時間区分 = ”00：OTHER”
    * 　@取引カレンダコンテキスト.商品コード = ”00：UNDEFINED”
    * 　@取引カレンダコンテキスト.注文受付商品 = "0：DEFAULT"
    * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
    *
    * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
    * 　@　@　@取引時間コンテキストをセットする。
    * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
    *
    * ２）　@受付日時、日付ロールをセットする。
    * 　@－取引時間管理.setTimestamp()をコールする。
    * @@roseuid 41B971650058
    */
   public Object onCall(Method arg0, Object[] arg1)
   {
       // 設定されているThreadLocal属性を移動
       removeAttributes();

       //管理者オブジェクトを取得
       // 取引カレンダコンテキストを生成
       WEB3GentradeTradingClendarContext l_context =
           new WEB3GentradeTradingClendarContext();
       String l_strInstitutionCode = null; // 証券会社コード
       String l_strBranchCode = null; // 部店コード

       try
       {
           WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_strInstitutionCode = l_admin.getInstitutionCode();
           l_strBranchCode = l_admin.getBranchCode();

       }
       catch (WEB3SystemLayerException se)
       {
           log.error(se.getMessage(), se);
       }

       // 取引カレンダコンテキスト.証券会社コード = 管理者.get証券会社コード()
       l_context.setInstitutionCode(l_strInstitutionCode);
       // 取引カレンダコンテキスト.部店コード = 管理者.get部店コード()
       l_context.setBranchCode(l_strBranchCode);
       // 取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
       l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
       // 取引カレンダコンテキスト.受付時間区分 = ”00：DEFAULT”
       l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
       // 取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
       l_context.setProductCode(WEB3AdminTPProductCodeDef.DEFAULT);
       // 取引カレンダコンテキスト.注文受付商品 = ”00：DEFAULT”
       l_context.setOrderAcceptProduct(WEB3AdminTPOrderAccProductDef.DEFAULT);
       // 取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”
       l_context.setOrderAcceptTransaction(
           WEB3OrderAccTransactionDef.REFERENCE);

       // 取引時間コンテキストをセット
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_context);

       // 受付日時、日付ロールをセット
       try
       {
           WEB3GentradeTradingTimeManagement.setTimestamp();
       }
       catch (WEB3SystemLayerException sle)
       {
           log.error(sle.getMessage(), sle);
       }

       log.debug("--------------------------------------------------");
       log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
       log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
       log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
       log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
       log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
       log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
       log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
       log.debug("--------------------------------------------------");

       return null;



   }

   /**
    * サービスメソッド終了時にコールされる。
    * 取引カレンダコンテキストクリア処理。
    *
    * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
    *
    * 取引時間管理.TIMESTAMP_TAG
    * 取引時間管理.OFFSET_TAG
    * 取引時間管理.TRADING_CAL_CONTEXT_PATH
    * @@roseuid 41B97171027B
    */
   public void onReturn(Object arg0, Object arg1)
   {
       resetAttributes();
   }

   /**
    * サービスメソッド終了時にコールされる。
    * 取引カレンダコンテキストクリア処理。
    *
    * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
    *
    * 取引時間管理.TIMESTAMP_TAG
    * 取引時間管理.OFFSET_TAG
    * 取引時間管理.TRADING_CAL_CONTEXT_PATH
    * @@roseuid 41B9717502B9
    */
   public void onThrowable(Object arg0, Throwable arg1)
   {
       resetAttributes();
   }

   /**
    * 設定されているThreadLocal属性を移動する
    */
   private void removeAttributes()
   {

       // 設定されているThreadLocal属性を取得
       Object l_objTempTradingCalendarContext =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
       Object l_objTempTimestamp =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
       Object l_objTempOffset =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.OFFSET_TAG);

       // 取得したThreadLocal属性を別の名前で保存
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
           l_objTempTradingCalendarContext);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TIMESTAMP_ATTRIBUTE_NAME,
           l_objTempTimestamp);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           OFFSET_ATTRIBUTE_NAME,
           l_objTempOffset);
   }

   /**
    * ThreadLocal属性を再設定する。
    */
   private void resetAttributes()
   {

       // 退避していた属性を取得
       Object l_objContext =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME);
       Object l_objTimestamp =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               TIMESTAMP_ATTRIBUTE_NAME);
       Object l_objOffset =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               OFFSET_ATTRIBUTE_NAME);

       // 取得した属性を再設定
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_objContext);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           l_objTimestamp);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           l_objOffset);

       // 退避していた属性を削除
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
           null);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TIMESTAMP_ATTRIBUTE_NAME,
           null);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           OFFSET_ATTRIBUTE_NAME,
           null);

   }



}
@
