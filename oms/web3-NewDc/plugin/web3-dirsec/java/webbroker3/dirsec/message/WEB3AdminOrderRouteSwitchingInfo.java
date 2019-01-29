head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOrderRouteSwitchingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注先情報(WEB3AdminOrderRouteSwitchingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * 発注先情報クラス<BR>
 */
public class WEB3AdminOrderRouteSwitchingInfo extends WEB3AdminOrderRouteInfo
{
    
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminOrderRouteSwitchingInfo.class);
   
	/**
	 * 有効フラグ<BR>
	 * <BR>
	 * false：　@無効<BR>
	 * true：　@有効<BR>
	 */
	public String validFlag;

	/**
	 * 変更後有効フラグ<BR>
	 * <BR>
	 * false：　@無効<BR>
	 * true：　@有効<BR>
	 */
	public String changedValidFlag;
    
	/**
	 * 発注先情報クラスコンストラクタ<BR>
	 * @@roseuid 42EEC5820000
	 */
	public WEB3AdminOrderRouteSwitchingInfo() 
	{
     
	}

    /**
     * 当クラスの整合性をチェックする。<BR>
     * <BR>
     * １）　@有効フラグのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * ２）　@変更後有効フラグのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR> 
     * @@roseuid 42D2123E0246
     */
    public void validate() throws WEB3BaseException 
    {
     
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
     
        // 有効フラグチェック
        // エラーの場合は、例外をthrowする。
        if (this.validFlag == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02214,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 変更後有効フラグチェック
        // エラーの場合は、例外をthrowする。
        else if (this.changedValidFlag == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02215,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
