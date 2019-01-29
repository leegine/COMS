head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文情報(WEB3AdminBondOrderInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondTradingTypeDef;


/**
 * (注文情報)<BR>
 * 注文情報クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderInfo extends Message
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderInfo.class);
    
    /**
     * (注文種別)<BR>
     * 債券の注文種別<BR>
     * <BR>
     * 401：債券買注文　@402：債券売り注文
     */
    public String tradingType;
    
    /**
     * (取引)<BR>
     * 取引<BR>
     * <BR>
     * 35:募集取引　@92:国内仕切取引
     */
    public String dealType;
    
    /**
     * (決済区分)<BR>
     * 決済<BR>
     * <BR>
     * 1：円貨　@2：外貨
     */
    public String settleDiv;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     * *画面の'額面金額'項目に対応する
     */
    public String orderQuantity;
    
    /**
     * (単価)<BR>
     * 単価
     */
    public String price;
    
    /**
     * (為替レート)<BR>
     * 為替レート
     */
    public String fxRate;
    
    /**
     * (税区分)<BR>
     * 税区分
     */
    public String taxType;
    
    /**
     * (注文情報)<BR>
     * コンストラクタ
     * @@roseuid 44CB0401033C
     */
    public WEB3AdminBondOrderInfo() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@注文種別チェック  
     *  （this.注文種別 == 債券買い注文　@または　@this.注文種別 == 債券売り注文）<BR>
     *   でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01403<BR>
     * <BR>
     * ２)　@取引チェック <BR>
     * 　@　@・（this.取引 == 募集取引　@または　@this.取引 == 国内仕切取引） <BR>
     *       でない場合、例外をスローする。
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02642<BR>
     * <BR>
     * 　@　@this.取引 == 募集取引<BR>
     * 　@　@かつ<BR>
     * 　@　@this.注文種別 ！= 債券買注文の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02539<BR>
     * <BR>
     * ３)　@注文数量チェック <BR>
     * 　@this.注文数量==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02634<BR>
     * <BR>
     * 　@this.注文数量が整数１１桁以内でない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02635<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02641<BR>
     * <BR>
     * 　@this.注文数量 <= 0場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02636<BR>
     * <BR>
     * ４)　@単価チェック <BR>
     * 　@this.単価==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02021<BR>
     * <BR>
     * 　@this.単価が整数４桁以内（＜＝４桁）＋小数点＋小数６桁以内（＜＝６桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02551<BR>
     * <BR>
     * 　@this.単価が数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02022<BR>
     * <BR>
     * 　@this.単価＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02023<BR>
     * <BR>
     * ５)　@為替レートチェック <BR>
     * 　@this.為替レート!=nullの場合、以下のチェックをする。 <BR>
     * 　@this.為替レートが整数３桁以内（＜＝３桁）＋小数点＋小数４桁以内（＜＝４桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02037<BR>
     * <BR>
     * 　@this.為替レートが数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02220<BR>
     * <BR>
     * 　@this.為替レート＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02196
     * @@throws WEB3BaseException
     * @@roseuid 44BDED360002
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１)　@注文種別チェック  
        //（this.注文種別 == 債券買い注文　@または　@this.注文種別 == 債券売り注文） 
        //でない場合、例外をスローする。
        if ((!WEB3BondTradingTypeDef.BOND_BUY.equals(this.tradingType)) && 
            (!WEB3BondTradingTypeDef.BOND_SELL.equals(this.tradingType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "売買区分の値が存在しないコード値です。");
        }
        
        //２)　@取引チェック  
        //（this.取引 == 募集取引　@または　@this.取引 == 国内仕切取引） 
        //でない場合、例外をスローする。 
        if ((!WEB3DealTypeDef.RECRUIT_TRADING.equals(this.dealType)) && 
                (!WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.dealType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02642,
                this.getClass().getName() + "."+ STR_METHOD_NAME,
                "取引の値が存在しないコード値です。");
        }
        
        //this.取引 == 募集取引
        //かつ
        //this.注文種別 ！= 債券買注文の場合、例外をスローする。
        
        if ((WEB3DealTypeDef.RECRUIT_TRADING.equals(this.dealType)) && 
            (!WEB3BondTradingTypeDef.BOND_BUY.equals(this.tradingType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02539,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "売買と取引が不正な関係です。");   
        }
        
        //３)　@注文数量チェック 
        //this.注文数量==nullの場合、例外をスローする。 
        if (this.orderQuantity == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が未指定です。");
        }
        
        //this.注文数量が整数１１桁以内でない場合、例外をスローする。 
        if(WEB3StringTypeUtility.getIntegerDigits(this.orderQuantity) > 11)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額のサイズが不正です。");
        }      
        if (!WEB3StringTypeUtility.isInteger(this.orderQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が整数値ではありません。");
        }

        //this.注文数量 <= 0場合、例外をスローする。 
        if ((Long.parseLong(this.orderQuantity) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が0以下の値です。");
        }
               
        //４)　@単価チェック 
        //this.単価==nullの場合、例外をスローする。
        if (this.price == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が未入力です。");
        }
        
        //this.単価が整数４桁以内（＜＝４桁）＋小数点＋小数６桁以内（＜＝６桁）でない場合、例外をスローする。
        if (((WEB3StringTypeUtility.getIntegerDigits(this.price) > 4) || 
               (WEB3StringTypeUtility.getFractionDigits(this.price) > 6)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価は整数部４桁，小数部６桁の範囲外です。");
        }
        
        //this.単価が数値でない場合、例外をスローする。
        if ((!WEB3StringTypeUtility.isNumber(this.price)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が数値以外の値です。");
        }
        
        //this.単価＜＝０の場合、例外をスローする。
        if ((Double.parseDouble(this.price) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が0以下の値です。");
        }
        
        //５)　@為替レートチェック 
        //this.為替レート!=nullの場合、以下のチェックをする。
        if (this.fxRate != null)
        {    
            //this.為替レートが整数３桁以内（＜＝３桁）＋小数点＋小数４桁以内（＜＝４桁）でない場合、例外をスローする。
            if ((WEB3StringTypeUtility.getIntegerDigits(this.fxRate) > 3) || 
                    (WEB3StringTypeUtility.getFractionDigits(this.fxRate) > 4))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");
            }
            
            //this.為替レートが数値でない場合、例外をスローする。
            if ((!WEB3StringTypeUtility.isNumber(this.fxRate)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートが数値以外の値です。");
            }
            
            //this.為替レート＜＝０の場合、例外をスローする。
            if (Double.parseDouble(this.fxRate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "約定為替レートが0以下の値です。");
            }
            log.exiting(STR_METHOD_NAME);
        }
    }
}
@
