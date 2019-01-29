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
filename	WEB3MarginCloseMarginContractListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済建株一覧リクエスト(WEB3MarginCloseMarginContractListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引決済建株一覧リクエスト）。<br>
 * <br>
 * 信用取引決済建株一覧リクエスト
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractListRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCloseMarginContractListRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginContractList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101648L;        
    /**
     * (ＩＤ)<BR>
     * 注文ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 414032D001DA
     */
    public WEB3MarginCloseMarginContractListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤ＝nullであれば「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866AAD0347
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "●●● WEB3MarginCloseMarginContractListRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("信用取引決済建株一覧リクエストのチェック: BEGIN");
        // １）　@ＩＤチェック<BR>
        // 　@this.ＩＤ＝nullであれば「IDがnull」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00080<BR>
        log.debug("ＩＤチェック!");
        if (this.id == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,
            this.getClass().getName() + "validate");
        }
        log.debug("信用取引決済建株一覧リクエストのチェック: END");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414032D001EE
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginCloseMarginContractListResponse(this);
    }
}
@
