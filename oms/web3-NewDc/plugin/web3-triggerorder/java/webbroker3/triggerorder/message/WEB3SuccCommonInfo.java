head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccCommonInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文共通情報(WEB3SuccCommonInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (連続注文共通情報)<BR>
 * 連続注文共通情報。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccCommonInfo extends Message 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccCommonInfo.class);
    
    /**
     * (（親注文）注文ID)<BR>
     * （親注文）注文ID。
     */
    public String parentOrderId;
    
    /**
     * (連続注文取引区分)<BR>
     * 連続注文取引区分。
     */
    public String succTradingType;
    
    /**
     * @@roseuid 4348960601F4
     */
    public WEB3SuccCommonInfo() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@（親注文）注文IDチェック<BR>
     * 　@１－１）（親注文）注文ID＝nullの場合、<BR>
     * 　@　@　@　@　@「（親注文）注文IDがnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02258<BR>
     * <BR>
     * 　@１－２）（親注文）注文ID＜0の場合、<BR>
     * 　@　@　@　@　@「（親注文）注文IDの値が不正」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02259<BR>
     * <BR>
     * ２）　@連続注文取引区分チェック<BR>
     * 　@２－１）this.連続注文取引区分＝nullの場合、<BR>
     * 　@　@　@　@　@「連続注文取引区分がnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02262<BR>
     * <BR>
     * 　@２－２）this.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「連続注文取引区分の値が未定義」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02263<BR>
     * <BR>
     * 　@　@　@　@　@"買付（前提注文）"<BR>
     * 　@　@　@　@　@"買付"<BR>
     * 　@　@　@　@　@"売付（前提注文）"<BR>
     * 　@　@　@　@　@"売付（既存残）"<BR>
     * 　@　@　@　@　@"信用新規建（前提注文）"<BR>
     * 　@　@　@　@　@"信用新規建"<BR>
     * 　@　@　@　@　@"信用返済（前提注文）"<BR>
     * 　@　@　@　@　@"信用返済（既存残）"<BR>
     * 　@　@　@　@　@"現引現渡（前提注文）"<BR>
     * 　@　@　@　@　@"現引現渡（既存残）"<BR>
     * 　@　@　@　@　@"先物新規建（前提注文）"<BR>
     * 　@　@　@　@　@"先物新規建"<BR>
     * 　@　@　@　@　@"先物返済（前提注文）"<BR>
     * 　@　@　@　@　@"先物返済（既存残）"<BR>
     * 　@　@　@　@　@"OP新規建（前提注文）"<BR>
     * 　@　@　@　@　@"OP新規建"<BR>
     * 　@　@　@　@　@"OP返済（前提注文）"<BR>
     * 　@　@　@　@　@"OP返済（既存残）"<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326AEBA03E7
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@（親注文）注文IDチェック
        //　@１－１）（親注文）注文ID＝nullの場合、
        //　@　@　@　@　@「（親注文）注文IDがnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.parentOrderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02258,
                getClass().getName() + STR_METHOD_NAME,
                "（親注文）注文IDが未指定です。");
        }

        //　@１－２）（親注文）注文ID＜0の場合、
        //　@　@　@　@　@「（親注文）注文IDの値が不正」の例外をスローする。
        if (Double.parseDouble(this.parentOrderId) < 0)
        {
            String l_strMessage = "親注文）注文ID「" + this.parentOrderId + "」が値が不正です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02259,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //２）　@連続注文取引区分チェック
        //　@２－１）this.連続注文取引区分＝nullの場合、
        //　@　@　@　@　@「連続注文取引区分がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02262,
                getClass().getName() + STR_METHOD_NAME,
                "連続注文取引区分がnullです。");
        }

        //　@２－２）this.連続注文取引区分が以下の値以外の場合、
        //　@　@　@　@　@「連続注文取引区分の値が未定義」の例外をスローする。
        if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.BUY.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(this.succTradingType))
        {
            String l_strMessage = "連続注文取引区分「" + this.succTradingType + "」が不正なコード値です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02263,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
