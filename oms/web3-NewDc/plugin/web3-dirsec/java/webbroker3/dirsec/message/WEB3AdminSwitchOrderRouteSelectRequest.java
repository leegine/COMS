head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注先切替選択リクエスト(WEB3AdminSwitchOrderRouteSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者・発注先切替選択リクエストクラス<BR>
 */
public class WEB3AdminSwitchOrderRouteSelectRequest extends WEB3GenRequest
{
	
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_switch_order_route_select";

	/**
	 * serialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200502011606L;
	     
	/**
	 * 証券会社コード<BR>
	 */
	public String institutionCode;
    
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteSelectRequest.class);


    /**
     * 管理者発注先切替選択リクエストコンストラクタ<BR>
     * @@roseuid 42E46E2B005D
     */
    public WEB3AdminSwitchOrderRouteSelectRequest() 
    {
     
    }

	/**
	 * 当リクエストデータの整合性チェックを行う。<BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
	 * <BR>
	 * １）証券会社コードチェック<BR>
	 * 　@１－１）this.証券会社コード == nullの場合、<BR>
	 * 　@　@　@　@　@「this.証券会社コードがnull」の例外をスローする。<BR>
	 * <BR>
	 *            class : WEB3BusinessLayerException<BR>
	 *            tag : BUSINESS_ERROR_00079<BR>
	 * <BR>
	*/
	public void validate() throws WEB3BusinessLayerException
	{
		
		final String STR_METHOD_NAME = "validate()";

		log.entering(STR_METHOD_NAME);

		// 1-1 証券会社コード　@nullチェック
		if (this.institutionCode == null)
		{

			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00827,
				this.getClass().getName() + "." + STR_METHOD_NAME);

		}
		log.exiting(STR_METHOD_NAME);
	}

	/** (non-Javadoc)
	 * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminSwitchOrderRouteSelectResponse(this);
	}

}
@
