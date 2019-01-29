head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済建玉一覧リクエストクラス(WEB3FuturesCloseMarginContractListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物返済建玉一覧リクエスト)<BR>
 * 株価指数先物返済建玉一覧リクエストクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractListRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginContractList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191513L;

    /**
     * (注文ＩＤ)
     */
    public String id;

    /**
     * @@roseuid 40F7AE17029F
     */
    public WEB3FuturesCloseMarginContractListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2203D0366
     */
    public void validate() throws WEB3BaseException
    {
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE1702BF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCloseMarginContractListResponse(this);
    }
}
@
