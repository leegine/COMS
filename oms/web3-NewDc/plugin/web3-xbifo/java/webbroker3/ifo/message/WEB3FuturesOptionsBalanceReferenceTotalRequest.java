head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション残高照会残高合計リクエストクラス(WEB3FuturesOptionsBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物オプション残高照会残高合計リクエスト)<BR>
 * 株価指数先物オプション残高照会残高合計リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceTotalRequest extends WEB3GenRequest
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReferenceTotal";
    
    /**
     * 先物／オプション区分<BR>
     * 　@1：先物 2：オプション<BR>
     */
    public String fuOpDiv;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@先物／オプション区分のチェック<BR>
     * 　@１－１）nullの場合、例外とする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01736<BR>
     * 　@１－２）以下の項目以外が存在した場合、例外とする。<BR>
     * 　@　@　@・1(先物)<BR>
     * 　@　@　@・2(オプション)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01737<BR>
     * @@throws 
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 41AD445301B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".validate()";
         //１）　@先物／オプション区分のチェック
        if (WEB3StringTypeUtility.isEmpty(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01736,
                getClass().getName() + STR_METHOD_NAME);
        }
        //１－２）以下の項目以外が存在した場合、例外とする。
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv) && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOptionsBalanceReferenceTotalResponse(this);
    }
}
@
