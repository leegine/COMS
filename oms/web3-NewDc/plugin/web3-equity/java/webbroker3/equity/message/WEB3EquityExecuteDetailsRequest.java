head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定詳細リクエスト(WEB3EquityExecuteDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 張坤芳 (中訊) 新規作成
Revesion History : 2004/12/07 岡村和明(SRA) 残案件対応 Ｎｏ.０５７
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文約定詳細リクエスト）。<BR>
 * <BR>
 * 現物株式注文約定詳細要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityExecuteDetailsRequest extends WEB3GenRequest
{

    /**
     * (ID)<BR>
     * 注文ID<BR>
     * （照会画面から詳細画面への遷移時のみ使う）<BR>
     */
    public String id;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_details";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405111057L;

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteDetailsRequest.class);

    /**
     * (現物株式注文約定詳細リクエスト)<BR>
     * コンストラクタ<BR>
     * @@roseuid 406A8AAB02CD
     */
    public WEB3EquityExecuteDetailsRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@param requestData - (リクエストデータ)<BR>
     * 現物株式注文約定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406A8A75005C
     */
    public WEB3GenResponse createResponse(WEB3EquityExecuteDetailsRequest l_request)
    {
        return new WEB3EquityExecuteDetailsResponse(l_request);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40A5B2B60232
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityExecuteDetailsResponse(this);
    }
    
    /**
     * （validate）<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@IDチェック<BR>
     * 　@this.ID＝nullの場合、<BR>
     * 　@「IDがnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            this.getClass().getName() + "validate");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
