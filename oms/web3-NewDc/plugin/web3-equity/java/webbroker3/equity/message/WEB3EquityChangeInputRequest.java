head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正入力リクエスト(WEB3EquityChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/08 岡村和明(SRA) 残案件対応 Ｎｏ.０５７
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文訂正入力リクエスト）。<BR>
 * <BR>
 * 現物株式注文訂正入力要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeInputRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeInputRequest.class);

    /**
     * (ID)<BR>
     * 注文ID<BR>
     */
    public String id;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004052001;

    /**
     * コンストラクタ<BR>
     * @@roseuid 40611EB7000C
     */
    public WEB3EquityChangeInputRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.equity.message.WEB3EquityChangeInputResponse
     * @@roseuid 40611F9C0377
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityChangeInputResponse(this);
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@IDチェック<BR>
     * 　@this.ID＝nullの場合、<BR>
     * 　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
