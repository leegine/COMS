head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付注文確認リクエスト(WEB3SuccEquitySellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）現物株式売付注文確認リクエスト)<BR>
 * （連続）現物株式売付注文確認リクエスト。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquitySellConfirmRequest extends WEB3EquitySellConfirmRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquitySellConfirmRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellConfirm";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * @@roseuid 434896050242
     */
    public WEB3SuccEquitySellConfirmRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityOrder
     * @@roseuid 40602C5D0204
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquitySellConfirmResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@連続注文共通情報チェック<BR>
     * 　@１－１）　@this.連続注文共通情報＝nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@１－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@"売付（前提注文）"<BR>
     * 　@　@　@　@　@"売付（既存残）"<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * <BR>
     * ２）　@this.連続注文共通情報.連続注文取引区分=="売付（既存残）"の場合のみ、<BR>
     * 　@　@　@super.validate()をコールする。<BR>
     * 　@　@　@以外、super.validateAT反対取引()をコールする。 <BR>
     * <BR>
     * ３）　@連続注文単価調整値情報チェック<BR>
     * 　@３－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@３－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"売付（前提注文）"であれば<BR>
     * 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外を<BR> 
     * 　@　@　@　@　@　@throwする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02253<BR>
     * <BR>
     * 　@３－３）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が<BR>
     * 　@　@　@　@　@　@不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック<BR>
     * 　@ super.validate連続注文()をコールする。<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4329095802BA
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文共通情報チェック
        //　@１－１）　@this.連続注文共通情報＝nullの場合、
        //　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。
        if (this.succCommonInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + STR_METHOD_NAME,
                "連続注文共通情報が未指定です。");
        }

        //　@１－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate(); //WEB3BusinessLayerException
        
        //　@１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        //　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        if (!WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succCommonInfo.succTradingType))
        {
            String l_strMessage = "連続注文取引区分「" 
                + this.succCommonInfo.succTradingType 
                + "」の値が処理対象外です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //２）　@this.連続注文共通情報.連続注文取引区分=="売付（既存残）"の場合のみ、
        //　@　@　@super.validate()をコールする。
        //　@　@　@以外、super.validateAT反対取引()をコールする。 
        if (WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succCommonInfo.succTradingType))
        {
            super.validate();//WEB3BusinessLayerException
        }
        else
        {
            super.validateAtReverseOrder();
        }
        
        //３）　@連続注文単価調整値情報チェック
        if (this.priceAdjustmentValueInfo != null)
        {
            //　@３－１）　@連続注文単価調整値情報≠nullの場合、
            //　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
            this.priceAdjustmentValueInfo.validate();//WEB3BusinessLayerException
            
            //　@３－２）　@連続注文単価調整値情報≠nullの場合、
            //　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"売付（前提注文）"であれば
            //　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外を 
            //　@　@　@　@　@　@throwする。
            if (!WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    getClass().getName() + STR_METHOD_NAME,
                    "連続注文取引区分が、連続注文の±指値指定不可の区分です。");
            }
            
            //　@３－３）　@連続注文単価調整値情報≠nullの場合、
            //　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が
            //　@　@　@　@　@　@不整合」の例外をthrowする。
            if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(super.orderPriceDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + STR_METHOD_NAME,
                    "単価調整値と注文単価区分の指定が不整合です。");
            }
        }
        //４）　@連続注文・注文条件チェック
        //　@ super.validate連続注文()をコールする。
        super.validateSuccOrder();//WEB3BusinessLayerException

        log.exiting(STR_METHOD_NAME);     
    }
}
@
