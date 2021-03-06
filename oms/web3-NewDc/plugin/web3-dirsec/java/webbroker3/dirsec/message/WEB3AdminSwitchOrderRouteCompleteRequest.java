head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注先切替完了リクエストクラス(WEB3AdminSwitchOrderRouteCompleteRequest.java)
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
 * 管理者・発注先切替完了リクエストクラス<BR>
 */
public class WEB3AdminSwitchOrderRouteCompleteRequest extends WEB3GenRequest
{
	
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_switch_order_route_complete";

	/**
	 * serialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200502011606L;
	
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteCompleteRequest.class);
    
	/**
	 * 証券会社コード<BR>
	 */
	public String institutionCode;
       
    /**
     * 暗証番号<BR>
     */
    public String password;
    
	/**
	 * 発注先情報<BR>
	 */
	public WEB3AdminOrderRouteSwitchingInfo infoUnit;


    /**
     * @@roseuid 42E871E000D2
     */
    public WEB3AdminSwitchOrderRouteCompleteRequest() 
    {
     
    }
    
    /**
     * 当クラスの整合性をチェックする。<BR>
     * <BR>
     * １）　@発注先情報チェック<BR>
     * 　@this.発注先情報.validate()をコールする。<BR>
     * @@roseuid 42D212720052
     */
	public void validate() throws WEB3BaseException 
	{
     
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
     
		// 証券会社コードチェック
		//エラーの場合は、例外をthrowする。
		if (this.institutionCode == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00827,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        // 暗証番号チェック
        else if(this.password == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        else 
        {
            // 発注先情報クラスのvalidateチェック
            infoUnit.validate();
        }
     
		log.exiting(STR_METHOD_NAME);
     
	}

	/** (non-Javadoc)
	 * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminSwitchOrderRouteCompleteResponse(this);
	}


}
@
