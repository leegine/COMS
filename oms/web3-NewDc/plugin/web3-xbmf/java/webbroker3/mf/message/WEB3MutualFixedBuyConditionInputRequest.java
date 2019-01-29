head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録入力リクエスト(WEB3MutualFixedBuyConditionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル605
Revision History : 2008/08/01 武波(中訊) 仕様変更 モデル623
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFFirstDisplayDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (投信定時定額買付銘柄条件登録入力リクエスト)<BR>
 * 投信定時定額買付銘柄条件登録入力リクエスト<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101430L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionInputRequest.class);

    /**
     * (カテゴリーコード)<BR>
     * カテゴリーコード<BR>
     */
    public String categoryCode;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String[] productCode;

    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String  typeCode;

    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ<BR>
     * <BR>
     * true：チェック要<BR>
     * false：チェック不要<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (初回表示フラグ)<BR>
     * 初回表示フラグ<BR>
     * <BR>
     * 一覧から登録入力画面に遷移する際、新規追加銘柄を表示しないよう判断する。<BR>
     * <BR>
     * 0：表示する<BR>
     * 1：表示しない<BR>
     * 2：閲覧チェック時<BR>
     */
    public String firstDisplayDiv;

    /**
     * (投信定時定額買付銘柄条件登録入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4848DB8400E9
     */
    public WEB3MutualFixedBuyConditionInputRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 投信定時定額買付銘柄条件登録入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4848DBB40000
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionInputResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）初回表示フラグチェックを行う<BR>
     * 　@　@１－１)　@初回表示フラグ==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03100<BR>
     * 　@　@１－２)　@初回表示フラグが以下の値のいずれかでない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@”1：表示しない”<BR>
     * 　@　@　@　@　@　@　@”0：表示する”<BR>
     * 　@　@　@　@　@　@　@”2：閲覧チェック時”<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03101<BR>
     * @@throws WEB3BaseException
     * @@roseuid 486C885102BA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）初回表示フラグチェックを行う
        //　@　@１－１)　@初回表示フラグ==nullの場合、例外をスローする。
        if (this.firstDisplayDiv == null)
        {
            log.debug("初回表示フラグが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03100,
                getClass().getName() + "." + STR_METHOD_NAME,
                "初回表示フラグが未指定です。");
        }

        //　@　@１－２)　@初回表示フラグが以下の値のいずれかでない場合、例外をスローする。
        //　@　@　@　@　@　@　@”1：表示しない”
        //　@　@　@　@　@　@　@”0：表示する”
        //             ”2：閲覧チェック時”
        if (!WEB3MFFirstDisplayDivDef.NO_DISPLAY.equals(this.firstDisplayDiv)
            && !WEB3MFFirstDisplayDivDef.DISPLAY.equals(this.firstDisplayDiv)
            && !WEB3MFFirstDisplayDivDef.READING_CHECK.equals(this.firstDisplayDiv))
        {
            log.debug("初回表示フラグの値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03101,
                getClass().getName() + "." + STR_METHOD_NAME,
                "初回表示フラグの値が不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
