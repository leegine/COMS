head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成リクエスト(WEB3AdminEquityForcedSettleTempOrderCreateRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデル.132
Revision History : 2007/04/27 趙林鵬 (中訊) モデル.138,147
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.define.WEB3ForcedSettleTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済仮注文作成リクエスト)<BR>
 * 強制決済仮注文作成リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleTempOrderCreateRequest extends WEB3BackRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_temp_order_create";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (証券会社コード)<BR>
     * 強制決済仮注文作成対象の証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (From口座ID)<BR>
     * From口座ID<BR>
     */
    public long rangeFrom;

    /**
     * (To口座ID)<BR>
     * To口座ID<BR>
     */
    public long rangeTo;

    /**
     * (強制決済処理区分)<BR>
     * 強制決済処理区分<BR>
     * <BR>
     * 1：　@オンライン開始前<BR>
     * 2：　@休憩時間帯<BR>
     */
    public String forcedSettleType;

    /**
     * @@roseuid 462CA4260331
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@this.証券会社コード＝nullの場合、<BR>
     * 　@「証券会社コードがnull」の例外をthrowする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00827<BR>
     * <BR>
     * ２）　@強制決済処理区分チェック <BR>
     * 　@２－１）　@this.強制決済処理区分＝nullであった場合 <BR>
     * 　@　@　@　@「強制決済処理区分がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02762<BR>
     * <BR>
     * 　@２－２）　@this.強制決済処理区分に下記の値以外が含まれている場合、<BR>
     * 　@　@「強制決済処理区分が未定義の値」の例外をスローする。<BR>
     * <BR>
     * 　@　@・"オンライン開始前"<BR>
     * 　@　@・"休憩時間帯"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02774<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4603762402E6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 当リクエストデータの整合性チェックを行う。
        // （ただし、当クラス内で完結する簡易チェックのみとする。）
        // １）　@証券会社コードチェック
        // 　@this.証券会社コード＝nullの場合、
        // 　@「証券会社コードがnull」の例外をthrowする。
        if (this.institutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }

        // ２）　@強制決済処理区分チェック
        // 　@２－１）　@this.強制決済処理区分＝nullであった場合
        // 　@　@　@　@「強制決済処理区分がnull」の例外をスローする。
        if (this.forcedSettleType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02762,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "強制決済処理区分がnull。");
        }

        // 　@２－２）　@this.強制決済処理区分に下記の値以外が含まれている場合、
        // 　@　@「強制決済処理区分が未定義の値」の例外をスローする。
        // 　@　@・"オンライン開始前"
        // 　@　@・"休憩時間帯"
        if ((!WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(this.forcedSettleType))
            && (!WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(this.forcedSettleType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "強制決済処理区分が未定義の値。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminEquityForcedSettleTempOrderCreateResponse(this);
    }
}
@
