head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付サービス登録・変更完了リクエスト(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoReportDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (電子交付サービス登録・変更完了リクエスト)<BR>
 * 電子交付サービス登録・変更完了リクエストクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_register_change_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121538L;

    /**
     * (取引報告書交付区分)<BR>
     * 取引報告書交付区分 <BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String tradingReportDiv;

    /**
     * (取引残高報告書交付区分)<BR>
     * 取引残高報告書交付区分<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String positionReportDiv;

    /**
     * (運用報告書交付区分)<BR>
     * 運用報告書交付区分<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String opeReportDiv;

    /**
     * (約款・規定集報告書交付区分)<BR>
     * 約款・規定集報告書交付区分<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String ordRulReportDiv;

    /**
     * (書面交付区分１)<BR>
     * 書面交付区分１<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String report_div1;

    /**
     * (書面交付区分２)<BR>
     * 書面交付区分２<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String report_div2;

    /**
     * (書面交付区分３)<BR>
     * 書面交付区分３<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String report_div3;

    /**
     * (書面交付区分４)<BR>
     * 書面交付区分４<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String report_div4;

    /**
     * (書面交付区分５)<BR>
     * 書面交付区分５<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String report_div5;

    /**
     * コンストラクタ<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest()
    {

    }

    /**
     * １）　@取引報告書交付区分チェック <BR>
     * 　@　@　@　@リクエスト.取引報告書交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「取引報告書交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03211<BR>
     * <BR>
     * ２）　@取引残高報告書交付区分チェック <BR>
     * 　@　@　@　@リクエスト.取引残高報告書交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「取引残高報告書交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03212<BR>
     * <BR>
     * ３）　@運用報告書交付区分チェック <BR>
     * 　@　@　@　@リクエスト.運用報告書交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「運用報告書交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03213<BR>
     * <BR>
     * ４）　@約款・規定集報告書交付区分チェック <BR>
     * 　@　@　@　@リクエスト.約款・規定集報告書交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「約款・規定集報告書交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03214<BR>
     * <BR>
     * ５）　@書面１交付区分チェック <BR>
     * 　@　@　@　@リクエスト.書面１交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「書面１交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03215<BR>
     * <BR>
     * ６）　@書面２交付区分チェック 　@　@ <BR>
     * 　@　@　@　@リクエスト.書面２交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「書面２交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03216<BR>
     * <BR>
     * ７）　@書面３交付区分チェック <BR>
     * 　@　@　@　@リクエスト.書面３交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「書面３交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03217<BR>
     * <BR>
     * ８）　@書面４交付区分チェック <BR>
     * 　@　@　@　@リクエスト.書面４交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「書面４交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03218<BR>
     * <BR>
     * ９）　@書面５交付区分チェック <BR>
     * 　@　@　@　@リクエスト.書面５交付区分≠null、かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「書面５交付区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・0：郵便配布　@ <BR>
     * 　@　@　@　@・1：電子配布 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03219<BR>
     * <BR>
     * １０）訂正が入っているかのチェック <BR>
     * 　@　@　@下記条件を全て満たす場合、何も訂正されていないと判断し、 <BR>
     * 　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@①@リクエスト.取引報告書交付区分＝null <BR>
     * 　@　@　@　@②リクエスト.取引残高報告書交付区＝null <BR>
     * 　@　@　@　@③リクエスト.運用報告書交付区分＝null <BR>
     * 　@　@　@　@④リクエスト.約款・規定集報告書交付区分＝null <BR>
     * 　@　@　@　@⑤リクエスト.書面１交付区分＝null <BR>
     * 　@　@　@　@⑥リクエスト.書面２交付区分＝null <BR>
     * 　@　@　@　@⑦リクエスト.書面３交付区分＝null <BR>
     * 　@　@　@　@⑧リクエスト.書面４交付区分＝null <BR>
     * 　@　@　@　@⑨リクエスト.書面５交付区分＝null <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03220<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@取引報告書交付区分チェック
        //　@　@　@　@リクエスト.取引報告書交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「取引報告書交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.tradingReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.tradingReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.tradingReportDiv))
        {
            log.debug("取引報告書交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03211,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引報告書交付区分が未定義の値です。");
        }

        //２）　@取引残高報告書交付区分チェック
        //　@　@　@　@リクエスト.取引残高報告書交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「取引残高報告書交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.positionReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.positionReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.positionReportDiv))
        {
            log.debug("取引残高報告書交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03212,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引残高報告書交付区分が未定義の値です。");
        }

        //３）　@運用報告書交付区分チェック
        //　@　@　@　@リクエスト.運用報告書交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「運用報告書交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.opeReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.opeReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.opeReportDiv))
        {
            log.debug("運用報告書交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03213,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "運用報告書交付区分が未定義の値です。");
        }

        //４）　@約款・規定集報告書交付区分チェック
        //　@　@　@　@リクエスト.約款・規定集報告書交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「約款・規定集報告書交付区分が未定義の値です。」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.ordRulReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.ordRulReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.ordRulReportDiv))
        {
            log.debug("約款・規定集報告書交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03214,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約款・規定集報告書交付区分が未定義の値です。");
        }

        //５）　@書面１交付区分チェック
        //　@　@　@　@リクエスト.書面１交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「書面１交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.report_div1 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div1)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div1))
        {
            log.debug("書面１交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03215,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面１交付区分が未定義の値です。");
        }

        //６）　@書面２交付区分チェック
        //　@　@　@　@リクエスト.書面２交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「書面２交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.report_div2 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div2)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div2))
        {
            log.debug("書面２交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面２交付区分が未定義の値です。");
        }

        //７）　@書面３交付区分チェック
        //　@　@　@　@リクエスト.書面３交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「書面３交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.report_div3 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div3)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div3))
        {
            log.debug("書面３交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03217,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面３交付区分が未定義の値です。");
        }

        //８）　@書面４交付区分チェック
        //　@　@　@　@リクエスト.書面４交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「書面４交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.report_div4 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div4)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div4))
        {
            log.debug("書面４交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03218,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面４交付区分が未定義の値です。");
        }

        //９）　@書面５交付区分チェック
        //　@　@　@　@リクエスト.書面５交付区分≠null、かつ下記の値以外の場合、
        //　@　@　@　@　@「書面５交付区分が未定義の値」の例外をスローする。
        //　@　@　@　@・0：郵便配布
        //　@　@　@　@・1：電子配布
        if (this.report_div5 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div5)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div5))
        {
            log.debug("書面５交付区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03219,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面５交付区分が未定義の値です。");
        }

        //１０）訂正が入っているかのチェック
        //　@　@　@下記条件を全て満たす場合、何も訂正されていないと判断し、
        //　@　@　@　@例外をスローする。
        //　@　@　@　@①@リクエスト.取引報告書交付区分＝null
        //　@　@　@　@②リクエスト.取引残高報告書交付区＝null
        //　@　@　@　@③リクエスト.運用報告書交付区分＝null
        //　@　@　@　@④リクエスト.約款・規定集報告書交付区分＝null
        //　@　@　@　@⑤リクエスト.書面１交付区分＝null
        //　@　@　@　@⑥リクエスト.書面２交付区分＝null
        //　@　@　@　@⑦リクエスト.書面３交付区分＝null
        //　@　@　@　@⑧リクエスト.書面４交付区分＝null
        //　@　@　@　@⑨リクエスト.書面５交付区分＝null
        if (this.tradingReportDiv == null
            && this.positionReportDiv == null
            && this.opeReportDiv == null
            && this.ordRulReportDiv == null
            && this.report_div1 == null
            && this.report_div2 == null
            && this.report_div3 == null
            && this.report_div4 == null
            && this.report_div5 == null)
        {
            log.debug("申込対象書面が選択されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03220,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "申込対象書面が選択されていません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse(this);
    }
}
@
