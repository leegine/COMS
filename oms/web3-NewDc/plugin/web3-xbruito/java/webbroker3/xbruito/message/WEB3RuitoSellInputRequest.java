head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約入力リクエストクラス(WEB3RuitoSellInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 累投解約入力リクエスト<BR>
 */
public class WEB3RuitoSellInputRequest extends WEB3GenRequest
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoSellInputRequest.class);	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * 銘柄コード<BR>
     * 累積投資ファ@ンドコード。<BR>
     */
    public String ruitoProductCode;

    /**
     * コンストラクタ<BR>
     * @@roseuid 40762D0E01DE
     */
    public WEB3RuitoSellInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@銘柄コードチェック<BR>
     * 　@　@ this.銘柄コードがnullの値であれば例外をスローする。<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     *      code     : 90<BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4092335900DA
     */
    public void validate() throws WEB3BusinessLayerException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);   

        //銘柄コードがnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("銘柄コードが入力されていません。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが入力されていません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投解約入力レスポンスを作成する<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762B9B01DE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoSellInputResponse(this);
    }
}
@
