head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション新規建注文入力画面リクエスト(WEB3SuccOptionsOpenInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,304
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株価指数オプション新規建注文入力画面リクエスト)<BR>
 * （連続）株価指数オプション新規建注文入力画面リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenInputRequest extends WEB3OptionsOpenMarginInputRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsOpenInputRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141609L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_input";

    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * @@roseuid 47D9F2CB002A
     */
    public WEB3SuccOptionsOpenInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsOpenInputResponse(this);
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
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02251<BR>
     * <BR>
     * 　@２－２）　@this.連続注文共通情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@"OP新規建（前提注文）"<BR>
     * 　@　@　@　@　@"OP新規建"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@ tag: BUSINESS_ERROR_02252<BR>
     * <BR>
     * 　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。<BR>
     * <BR>
     * ３）　@連続注文取引区分チェック<BR>
     * 　@３－１）　@this.連続注文共通情報.連続注文取引区分=="OP新規建（前提注文）"の場合、<BR>
     * 　@　@　@　@　@　@「銘柄コード」が設定されていなければ、<BR>
     * 　@　@　@　@　@　@「入力パラメータチェックエラー。」の例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47ABAD5F03D5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@super.validate()をコールする。
        super.validate();

        //２）　@連続注文共通情報チェック
        // 　@２－１）　@this.連続注文共通情報==nullの場合、
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

        //２－２）　@this.連続注文共通情報.validate()をコールする。
        this.succCommonInfo.validate();

        //２－３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
        // 　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
        // 　@　@　@　@　@"OP新規建（前提注文）"
        // 　@　@　@　@　@"OP新規建"
        //　@　@　@　@　@※コード値は、先物OP予約注文単位テーブルを参照。
        if (!WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("連続注文取引区分の値が処理対象外");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文取引区分の値が処理対象外");
        }

        //３）　@連続注文取引区分チェック
        // 　@３－１）　@this.連続注文共通情報.連続注文取引区分=="OP新規建（前提注文）"の場合、
        // 　@　@　@　@　@　@「銘柄コード」が設定されていなければ、
        // 　@　@　@　@　@　@「入力パラメータチェックエラー。」の例外をthrowする。
        if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && this.opProductCode == null)
        {
            log.debug("入力パラメータチェックエラー。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力パラメータチェックエラー。");
        }

    }
}
@
