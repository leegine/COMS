head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文銘柄選択画面リクエストクラス(WEB3FuturesProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 呉艶飛 (中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;

/**
 * (株価指数先物新規建注文銘柄選択画面リクエスト)<BR>
 * 株価指数先物新規建注文銘柄選択画面リクエストクラス
 * @@author 呉艶飛
 * @@version 1.0 
 */
public class WEB3FuturesProductSelectRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_ProductSelect";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201855L;

    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;

    /**
     * @@roseuid 40F7AE0D001F
     */
    public WEB3FuturesProductSelectRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@建区分チェック<BR>
     * 　@１－１）this.建区分がnullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00263<BR>
     * 　@１－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00264<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A218530140
     */
    public void validate() throws WEB3BaseException
    {
        //(１）　@建区分チェック)
        //１－１）this.建区分がnullの値であれば例外をスローする。
        if (this.contractType == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "建区分がnullである");
        }
        //１－２）this.建区分が以下の値以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE0D003E
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesProductSelectResponse(this);
    }
}
@
