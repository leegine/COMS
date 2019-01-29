head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式買付注文入力リクエスト(WEB3SuccEquityBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）現物株式買付注文入力リクエスト)<BR>
 * （連続）現物株式買付注文入力リクエスト。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquityBuyInputRequest extends WEB3EquityBuyInputRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityBuyInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyInput";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4348960503D8
     */
    public WEB3SuccEquityBuyInputRequest() 
    {
     
    }
    
    /**
     * レスポンスデータを作成する。 <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 40602B2B00BE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityBuyInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@連続注文共通情報チェック<BR>
     * 　@２－１）　@this.連続注文共通情報==nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@２－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@"買付（前提注文）"<BR>
     * 　@　@　@　@　@"買付"<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * <BR>
     * ３）　@取引区分チェック<BR>
     * 　@３－１）　@取引区分≠"現物買付注文"の場合は、<BR>
     * 　@　@　@　@　@　@「取引区分が処理対象外」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02255<BR>
     * <BR>
     * ４）　@連続注文取引区分チェック<BR>
     * 　@４－１）　@this.連続注文共通情報.連続注文取引区分=="買付（前提注文）"の場合、<BR>
     * 　@　@　@　@　@　@super.銘柄コード==nullであれば<BR>
     * 　@　@　@　@　@　@「反対取引時は銘柄コード指定は必須」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02256<BR>
     * <BR>
     * 　@４－２）　@this.連続注文共通情報.連続注文取引区分=="買付（前提注文）"の場合、<BR>
     * 　@　@　@　@　@　@super.市場コード==nullであれば<BR>
     * 　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02257<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326AFEB036A
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@super.validate()をコールする。
        super.validate();//WEB3BusinessLayerException
        
        //２）　@連続注文共通情報チェック
        //　@２－１）　@this.連続注文共通情報==nullの場合、
        //　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
        if (this.succCommonInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + STR_METHOD_NAME,
                "連続注文共通情報が未指定です。");
        }

        //　@２－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate(); //WEB3BusinessLayerException

        //　@２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        //　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.BUY.equals(this.succCommonInfo.succTradingType))
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

        //３）　@取引区分チェック
        //　@３－１）　@取引区分≠"現物買付注文"の場合は、
        //　@　@　@　@　@　@「取引区分が処理対象外」の例外をthrowする。
        if (!WEB3TradingTypeDef.BUY_ORDER.equals(super.tradingType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02255,
                getClass().getName() + STR_METHOD_NAME,
                "取引区分が連続注文の処理対象外です。");
        }

        //４）　@連続注文取引区分チェック
        //　@４－１）　@this.連続注文共通情報.連続注文取引区分=="買付（前提注文）"の場合、
        //　@　@　@　@　@　@super.銘柄コード==nullであれば
        //　@　@　@　@　@　@「反対取引時は銘柄コード指定は必須」の例外をthrowする。
        if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && super.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02256,
                getClass().getName() + STR_METHOD_NAME,
                "反対取引時は銘柄コード指定は必須です。");
        }
        
        //　@４－２）　@this.連続注文共通情報.連続注文取引区分=="買付（前提注文）"の場合、
        //　@　@　@　@　@　@super.市場コード==nullであれば
        //　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。
        if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && super.marketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                getClass().getName() + STR_METHOD_NAME,
                "反対取引時は市場コード指定は必須です。");
        }

        log.exiting(STR_METHOD_NAME);     
    }
}
@
