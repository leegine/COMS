head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替完了リクエスト (WEB3AdminFrontRouteChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminFrontServiceStartDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替完了リクエスト)<BR>
 * <BR>
 * 管理者発注経路切替サービス（完了）のリクエストデータ。<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeCompleteRequest extends WEB3AdminFrontRouteChangeCommonRequest {
    

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeCompleteRequest.class);
        
    /**
     * サービス起動区分<BR>
     * <BR>
     * 0：　@管理者起動<BR>
     * 1：　@自動起動<BR>
     */
    public String serviceStartDiv;
   

    /**
     * 暗証番号<BR>
     */
    public String password;
   

    /**
     * @@roseuid 42FFFED503AC
     */
    public WEB3AdminFrontRouteChangeCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@サービス起動区分チェック<BR>
     * 　@１－１）thisサービス起動区分 == <BR>
     * nullの場合、「サービス起動区分がnull」の例外をスローする。<BR>
     * <BR>
     * ２）　@暗証番号チェック<BR>
     * 　@２－１）this暗証番号 == nullの場合、「暗証番号がnull」の例外をスローする。<BR>
     * @@roseuid 42F884D100A5
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // サービス起動区分のnullチェック
        if (this.serviceStartDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02207,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 暗証番号チェック        
        else if (serviceStartDiv.equals(WEB3AdminFrontServiceStartDivDef.ADMINISTRATOR_DIV) && this.password == null) 
        {
            throw new WEB3BusinessLayerException(
            
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        
        // 1-1 super.validate()
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
      * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
      */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontRouteChangeCompleteResponse(this);
    }

}
@
