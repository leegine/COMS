head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金注文内容(WEB3AioNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 周勇 (中訊) 新規作成     
                   2004/10/23 于美麗 (中訊) レビュー              
Revesion History : 2009/03/12 柴双紅 (中訊) 仕様変更・モデルNo.1109、1152
*/
package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.ordersubmitter.io.AioNewOrderSpec;

import webbroker3.util.WEB3LogUtility;


/**
 * (入出金注文内容)<BR>
 * 入出金注文内容クラス
 * （AioNewOrderSpecの拡張クラス）
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioNewOrderSpec extends AioNewOrderSpec 
{
    
    /**
     * (決済機@関ID)
     */
    private String paySchemeId;
    
    /**
     * (注文ID)
     */
    private Long orderId;

    /**
     * (摘要コード)
     */
    private String remarkCode;

    /**
     * (摘要名)
     */
    private String remarkName;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioNewOrderSpec.class); 
    
    /**
     * (入出金注文内容)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）スーパークラスのメソッドをコールする。<BR>
     * <BR>
     * ２）決済機@関IDの値をセットする。<BR>
     * <BR>
     * ３）注文IDの値をセットする。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト
     * @@param l_orderType - (注文種別)
     * @@param l_transferType - (振替タイプ)
     * @@param l_lngProductId - (商品ID)
     * @@param l_dblNetAmount - (金額)
     * @@param l_strDescription - (記述)
     * @@param l_datEstTransferDate - (振替予定日)
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@param l_lngOrderId - (注文ID)
     * @@roseuid 40F5011501AA
     */
    public WEB3AioNewOrderSpec(
        Trader l_trader, 
        OrderTypeEnum l_orderType, 
        AssetTransferTypeEnum l_transferType, 
        long l_lngProductId, 
        double l_dblNetAmount, 
        String l_strDescription, 
        Date l_datEstTransferDate, 
        String l_strPaySchemeId, 
        Long l_lngOrderId) 
    {   
        // １）スーパークラスのメソッドをコールする。
        super(l_trader,l_orderType,l_transferType, l_lngProductId, l_dblNetAmount,
            l_strDescription, l_datEstTransferDate);
        
        final String STR_METHOD_NAME = "WEB3AioNewOrderSpec(" +
            "Trader l_trader, " +  
            "OrderTypeEnum l_orderType, " +  
            "AssetTransferTypeEnum l_transferType, " +  
            "long l_lngProductId, " + 
            "double l_dblNetAmount, " + 
            "String l_strDescription, " + 
            "Date l_datEstTransferDate, " + 
            "String l_strPaySchemeId, " + 
            "Long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        
        // ２）決済機@関IDの値をセットする。
        this.paySchemeId = l_strPaySchemeId;
        
        // ３）注文IDの値をセットする。 
        this.orderId = l_lngOrderId;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (入出金注文内容)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）スーパークラスのメソッドをコールする。<BR>
     * <BR>
     * ２）決済機@関IDの値をセットする。<BR>
     * <BR>
     * ３）注文IDの値をセットする。<BR>
     * <BR>
     * ４）摘要コードの値をセットする。<BR>
     * <BR>
     * ５）摘要名の値をセットする。<BR>
     * <BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_transferType - (振替タイプ)<BR>
     * 振替タイプ<BR>
     * @@param l_lngProductId - (商品ID)<BR>
     * 商品ID<BR>
     * @@param l_dblNetAmount - (金額)<BR>
     * 金額<BR>
     * @@param l_strDescription - (記述)<BR>
     * 記述<BR>
     * @@param l_datEstTransferDate - (振替予定日)<BR>
     * 振替予定日<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * 決済機@関ID<BR>
     * @@param l_orderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_strRemarkCode - (摘要コード)<BR>
     * 摘要コード<BR>
     * @@param l_strRemarkName - (摘要名)<BR>
     * 摘要名<BR>
     */
    public WEB3AioNewOrderSpec(
        Trader l_trader,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_transferType,
        long l_lngProductId,
        double l_dblNetAmount,
        String l_strDescription,
        Date l_datEstTransferDate,
        String l_strPaySchemeId,
        Long l_orderId,
        String l_strRemarkCode,
        String l_strRemarkName)
    {
        //スーパークラスのメソッドをコールする
        super(l_trader, l_orderType, l_transferType, l_lngProductId,
            l_dblNetAmount, l_strDescription, l_datEstTransferDate);
        final String STR_METHOD_NAME = "WEB3AioNewOrderSpec(Trader, OrderTypeEnum, "
            + "AssetTransferTypeEnum, long, double, String, Date, String, Long, String, String)";
        log.entering(STR_METHOD_NAME);

        //決済機@関IDの値をセットする。
        this.paySchemeId = l_strPaySchemeId;

        //注文IDの値をセットする。
        this.orderId = l_orderId;

        //摘要コードの値をセットする。
        this.remarkCode = l_strRemarkCode;

        //摘要名の値をセットする。
        this.remarkName = l_strRemarkName;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get決済機@関ID)<BR>
     * 決済機@関IDを取得する。
     * @@return String
     * @@roseuid 40F5028D00EF
     */
    public String getPaySchemeId() 
    {   
        return this.paySchemeId;
    }
    
    /**
     * (get注文ID)<BR>
     * 注文IDを取得する。
     * @@return long
     * @@roseuid 4100D779007D
     */
    public Long getOrderId() 
    {
        return this.orderId;
    }

    /**
     * (get摘要コード)<BR>
     * 摘要コードを取得する。<BR>
     * @@return String
     */
    public String getRemarkCode()
    {
        return this.remarkCode;
    }

    /**
     * (get摘要名)<BR>
     * 摘要名を取得する。<BR>
     * @@return String
     */
    public String getRemarkName()
    {
        return this.remarkName;
    }
}
@
