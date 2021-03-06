head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建注文確認リクエストクラス(WEB3SuccMarginOpenConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引新規建注文確認リクエストクラス)<BR>
 * （連続）信用取引新規建注文確認リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenConfirmRequest extends WEB3MarginOpenMarginConfirmRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginOpenConfirmRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenConfirm";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * @@roseuid 4369CBEF032C
     */
    public WEB3SuccMarginOpenConfirmRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginOpenConfirmResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@連続注文共通情報チェック<BR>
     * 　@２−１）　@連続注文共通情報＝nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@２−２）　@連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@２−３）　@連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * 　@　@　@　@　@"新規建（前提注文）"<BR>
     * 　@　@　@　@　@"新規建"<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * <BR>
     * ３）　@連続注文単価調整値情報チェック<BR>
     * 　@３−１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@３−２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"新規建（前提注文）"であれば<BR>
     * 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02253<BR>
     * <BR>
     * 　@３−３）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック<BR>
     * 　@super.validate連続注文()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 43293DBF0166
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@super.validate()をコールする。
        super.validate();
        
        //２）　@連続注文共通情報チェック
        // 　@２−１）　@this.連続注文共通情報==nullの場合、
        // 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
        if (this.succCommonInfo == null)
        {
            log.debug("連続注文共通情報が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文共通情報が未指定です。");
        }
       
        //２−２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();
       
        //２−３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        if ((!WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
           && (!WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(this.succCommonInfo.succTradingType))) 
        {
            log.debug("連続注文取引区分の値が処理対象外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外です。");
        }
        
        //３−１）　@連続注文単価調整値情報≠nullの場合、
        // 　@　@　@　@　@ 連続注文単価調整値情報.validate()をコールする。
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            
            //３−２）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@　@連続注文共通情報.連続注文取引区分≠"新規建（前提注文）"であれば
            // 　@　@　@　@　@　@「連続注文取引区分が、連続注文の±指値指定不可の区分」の例外をthrowする。
            if (!WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType))
            {
                log.debug("連続注文取引区分が、連続注文の±指値指定不可の区分です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "連続注文取引区分が、連続注文の±指値指定不可の区分です。");
            }
            
            //３−３）　@連続注文単価調整値情報≠nullの場合、
            //　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            //　@　@　@　@　@  例外をthrowする。
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("単価調整値と注文単価区分の指定が不整合です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "単価調整値と注文単価区分の指定が不整合です。");
            }
        }
        
        //４）　@連続注文・注文条件チェック
        //     super.validate連続注文()をコールする。
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@
