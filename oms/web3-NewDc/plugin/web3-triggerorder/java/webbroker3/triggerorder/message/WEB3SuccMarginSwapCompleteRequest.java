head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginSwapCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡注文完了リクエストクラス(WEB3SuccMarginSwapCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引現引現渡注文完了リクエストクラス)<BR>
 * （連続）信用取引現引現渡注文完了リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginSwapCompleteRequest extends WEB3MarginSwapMarginCompleteRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginSwapCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginSwapComplete";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4369CC4302AF
     */
    public WEB3SuccMarginSwapCompleteRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginSwapCompleteResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@連続注文共通情報チェック<BR>
     * 　@１－１）　@this.連続注文共通情報＝nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報指定なし」の例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@１－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"現引現渡（前提注文）"<BR>
     * 　@　@　@　@　@"現引現渡（既存残）"<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、株式予約注文単位テーブルを参照。<BR>
     * <BR>
     * ２）　@this.連続注文共通情報.連続注文取引区分=="現引現渡（既存残）"の場合のみ、<BR>
     * 　@　@　@super.validate()をコールする。<BR>
     * 　@　@　@以外、super.validateAT反対取引()をコールする。<BR>
     * <BR>
     * ３）　@this.連続注文共通情報.連続注文取引区分=="現引現渡（前提注文）"の場合、 <BR>
     * 　@　@　@リクエスト.決済順序区分==（null or "建日順"）の場合は、 <BR>
     * 　@　@　@「連続注文・反対取引時の決済順序区分指定不正」の例外をthrowする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02306<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 432944F403C7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@連続注文共通情報チェック
        // 　@１－１）　@this.連続注文共通情報==nullの場合、
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
        
       //　@１－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();
        
        // １－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        if ((!WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
            && (!WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))) 
        {
            log.debug("連続注文取引区分の値が処理対象外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外です。");
        }
        
        // ２）　@this.連続注文共通情報.連続注文取引区分=="現引現渡（既存残）"の場合のみ、
        // 　@　@　@super.validate()をコールする。
        // 　@　@　@以外、super.validateAT反対取引()をコールする。
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }
        else
        {
            super.validateAtReverseOrder();
        }
        
        // ３）　@リクエスト.決済順序区分チェック
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(
        this.succCommonInfo.succTradingType))
        {
            if (this.closingOrder == null
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02306,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "反対取引時の決済順序区分指定不正。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
