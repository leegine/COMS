head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputExecInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式(PTS)出来入力リクエスト（WEB3AdminEquityPTSInputExecInputRequest.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/23 金傑 (中訊) 新規作成モデル173
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式（PTS）出来入力リクエスト)<BR>
 * 管理者・株式（PTS）出来入力リクエストクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecInputRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231022L;

    /**
     * (注文ID)<BR>
     */
    public String orderId;

    /**
     * (管理者・株式(PTS)出来入力リクエスト)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4795A0F9018A
     */
    public WEB3AdminEquityPTSInputExecInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@注文IDチェック <BR>
     * 　@this.注文ID＝nullの場合、 <BR>
     * 　@「注文IDがnull」の例外をスローする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4766442A032A
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@注文IDチェック
        // this.注文ID＝nullの場合、
        //「注文IDがnull」の例外をスローする。
        if (this.orderId == null)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSInputExecInputResponse(this);
    }
}
@
