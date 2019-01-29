head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注経路切替選択リクエスト (WEB3AdminFrontRouteChangeSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・発注経路切替選択リクエスト)<BR>
 * <BR>
 * 管理者発注経路切替選択サービス（入力画面表示）のリクエストデータ。<BR>
 * <BR>
 * -----<English>--------------<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeSelectRequest extends WEB3GenRequest {
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_select";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeSelectRequest.class);

   
    /**
     * 証券会社コード<BR>
     */
    public String institutionCode;
   
    /**
     * 切替処理方式区分<BR>
     * <BR>
     * ０：通番照会処理方式。<BR>
     * <BR>
     * １：全訂正処理方式。<BR>
     */
    public String changeProcessDiv;
    
    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontRouteChangeSelectResponse(this);
    }

    /**
     * @@roseuid 42FFFED40310
     */
    public WEB3AdminFrontRouteChangeSelectRequest() 
    {
    
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@１－１）this.証券会社コード == <BR>
     * nullの場合、「証券会社コードがnull」の例外をスローする。<BR>
     * <BR>
     * ２）　@切替処理方式区分チェック<BR>
     * 　@２－１）this.切替処理方式区分 == <BR>
     * nullの場合、「切替処理方式区分がnull」の例外をスローする。<BR>
     * @@roseuid 42F874B40141
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if institutionCode is null, throw Exception.
        if (this.institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1-1 if changeProcessDiv is null, throw Exception.
        else if (this.changeProcessDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02205,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);    
    }
}
@
