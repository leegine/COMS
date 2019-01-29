head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション返済入力画面リクエスト(WEB3SuccOptionsCloseInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株価指数オプション返済入力画面リクエスト)<BR>
 * （連続）株価指数オプション返済入力画面リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseInputRequest extends WEB3OptionsCloseMarginInputRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsCloseInputRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141528L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_input";

    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * @@roseuid 47D9F2CA0059
     */
    public WEB3SuccOptionsCloseInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseInputResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@連続注文共通情報チェック<BR>
     * 　@１－１）　@this.連続注文共通情報==nullの場合、<BR>
     * 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@１－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"OP返済（前提注文）"<BR>
     * 　@　@　@　@　@"OP返済（既存残）"<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、先物ＯＰ予約注文単位テーブルを参照。<BR>
     * <BR>
     * ２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、<BR>
     * 　@　@　@super.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B0F6B002A2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文共通情報チェック
        // 　@１－１）　@this.連続注文共通情報==nullの場合、
        // 　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
        if (this.succCommonInfo == null)
        {
            log.debug("連続注文共通情報が未指定");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文共通情報が未指定");
        }

        //１－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();

        //１－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        // 　@　@　@　@　@"OP返済（前提注文）"
        // 　@　@　@　@　@"OP返済（既存残）"
        // 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
        if (!WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("連続注文取引区分の値が処理対象外");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外");
        }

        //２）　@this.連続注文共通情報.連続注文取引区分=="OP返済（既存残）"の場合のみ、
        // 　@　@　@super.validate()をコールする。
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
