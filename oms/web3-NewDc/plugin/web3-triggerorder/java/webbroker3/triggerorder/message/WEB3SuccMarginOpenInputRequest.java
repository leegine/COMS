head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建注文入力リクエスト(WEB3SuccMarginOpenInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引新規建注文入力リクエスト)<BR>
 * （連続）信用取引新規建注文入力リクエスト<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenInputRequest extends WEB3MarginOpenMarginInputRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginOpenInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenInput";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4369CBD7030D
     */
    public WEB3SuccMarginOpenInputRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginOpenInputResponse(this);
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
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@２－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"信用新規建（前提注文）"<BR>
     * 　@　@　@　@　@"信用新規建"<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * <BR>
     * ３）　@連続注文取引区分チェック<BR>
     * 　@３－１）　@this.連続注文共通情報.連続注文取引区分=="信用新規建（前提注文）"の場合、<BR>
     * 　@　@　@　@　@　@super.銘柄コード==nullであれば<BR>
     * 　@　@　@　@　@　@「反対取引時は銘柄コード指定は必須」の例外をthrowする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02256<BR>
     * <BR>
     * 　@３－２）　@this.連続注文共通情報.連続注文取引区分=="信用新規建（前提注文）"の場合、<BR>
     * 　@　@　@　@　@　@super.市場コード==nullであれば<BR>
     * 　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02257<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4328E32E01CC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
         
        //１）　@super.validate()をコールする。
        super.validate();
         
        //２）　@連続注文共通情報チェック
        // 　@２－１）　@this.連続注文共通情報==nullの場合、
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
        
        //２－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();
        
        //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
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
        
        //３）　@連続注文取引区分チェック
        if (WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
        {
            //  ３－１）　@this.連続注文共通情報.連続注文取引区分==
            //          "信用新規建（前提注文）"の場合、
            // 　@　@　@　@　@　@super.銘柄コード==nullであれば
            // 　@　@　@　@　@　@反対取引時は銘柄コード指定は必須」の例外をthrowする。
            if (super.productCode == null)
            {
                log.debug("反対取引時は銘柄コード指定は必須です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02256,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "反対取引時は銘柄コード指定は必須です。");
            }
            
            //３－２）　@this.連続注文共通情報.連続注文取引区分==
            //        "信用新規建（前提注文）"の場合、
            // 　@　@　@　@ super.市場コード==nullであれば
            // 　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。
            if (super.marketCode == null) 
            {
                log.debug("反対取引時は市場コード指定は必須です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "反対取引時は市場コード指定は必須です。"); 
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
