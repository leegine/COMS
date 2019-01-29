head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・為替登録完了リクエスト(WEB3AdminTMExchangeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・為替登録完了リクエスト)<BR>
 * 管理者・為替登録完了リクエストクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistCompleteRequest.class);

    /**
     * (為替情報一覧)<BR>
     * 為替情報の配列<BR>
     * <BR>
     * ※入力があった通貨・レートについての情報のみがセットされる。<BR>
     */
    public WEB3AdminTMExchangeInfoUnit[] exchangeInfoUnit;

    /**
     * 暗証番号
     */
    public String password;

    /**
     * コンストラクタ
     */
    public WEB3AdminTMExchangeRegistCompleteRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@為替情報一覧[] == null or 為替情報一覧[].length == 0の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02195<BR>
     * <BR>
     * ２）　@為替情報一覧[]の各要素毎に、為替情報一覧.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@為替情報一覧[] == null or 為替情報一覧[].length == 0の場合、例外をスローする。
        if (this.exchangeInfoUnit == null || this.exchangeInfoUnit.length == 0)
        {
            log.debug("為替情報一覧[] == null or 為替情報一覧[].length == 0の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02195,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        // ２）　@為替情報一覧[]の各要素毎に、為替情報一覧.validate()をコールする。
        int l_intLength = this.exchangeInfoUnit.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("l_intLength = " + l_intLength);
            this.exchangeInfoUnit[i].validate();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMExchangeRegistCompleteResponse(this);
    }
}
@
