head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付注文入力リクエスト(WEB3SuccEquitySellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）現物株式売付注文入力リクエスト)<BR>
 * （連続）現物株式売付注文入力リクエスト。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquitySellInputRequest extends WEB3EquitySellInputRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquitySellInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellInput";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 434896050186
     */
    public WEB3SuccEquitySellInputRequest() 
    {
     
    }
    
    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.equity.message.WEB3EquitySellInputResponse
     * @@roseuid 40602C24036C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquitySellInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@連続注文共通情報チェック<BR>
     * 　@１－１）　@this.連続注文共通情報==nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。<BR>
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
     * ２）　@this.連続注文共通情報.連続注文取引区分=="売付（前提注文）"の場合、<BR>
     * 　@　@　@以下のチェックを行う。<BR>
     * 　@２－１） super.市場コード==nullの場合は、<BR>
     * 　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02257<BR>
     * <BR>
     * ３）　@this.連続注文共通情報.連続注文取引区分=="売付（既存残）"の場合のみ、<BR>
     * 　@　@　@super.validate()をコールする。<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326B05901F3
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文共通情報チェック
        //　@１－１）　@this.連続注文共通情報==nullの場合、
        //　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
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

        //２）　@this.連続注文共通情報.連続注文取引区分=="売付（前提注文）"の場合、
        //　@　@　@以下のチェックを行う。
        if (WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
        {
            //　@２－１） super.市場コード==nullの場合は、
            //　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。
            if (super.marketCode == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                    getClass().getName() + STR_METHOD_NAME,
                    "反対取引時は市場コード指定は必須です。");
            }
        }
        //３）　@this.連続注文共通情報.連続注文取引区分=="売付（既存残）"の場合のみ、
        //　@　@　@super.validate()をコールする。
        else if (WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succCommonInfo.succTradingType))
        {
            super.validate(); //WEB3BusinessLayerException
        }

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
