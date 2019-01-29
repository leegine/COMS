head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOrderRouteInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注経路情報(WEB3AdminOrderRouteInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * 発注経路情報クラス<BR>
 * <BR>
 * WEB3AdminOrderRouteInfo<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminOrderRouteInfo extends Message
{
      
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminOrderRouteInfo.class);
    

	/**
	 * 変換市場コード<BR>
	 */
	public String convertMarketCode;

    /**
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * フロント発注システム区分<BR>
     */
    public String frontOrderSystemCode;
    
    /**
     * 銘柄タイプ<BR>
     */
    public String productType;
    
    /**
     * 発注経路区分<BR>
     */
    public String submitOrderRouteDiv;
    
    /**
     * 変更後発注経路区分<BR>
     */
    public String changedSubmitOrderRouteDiv;
    
	/**
	 * 切替ステータス。<BR>
	 * <BR>
	 * ０ ： 未処理。<BR>
	 * １ ： 切替中。<BR>
	 * ２ ： 切替済。<BR>
	 * ９ ： 切替不可。<BR>
	 */
	public String changeStatus;
    
	/**
	 * 切替起動区分。<BR>
	 * <BR>
	 * ０ ： 発注経路切替。<BR>
	 * １ ： 通番照会要求再起動。<BR>
	 * ２ ： 通知代行解除要求再起動。<BR>
	 * ３ ： 通知代行要求再起動。<BR>
	 * ４ ： 通知再送要求（受付系）再起動。<BR>
	 * ５ ： 通知再送要求（約定系）再起動。<BR>
	 * ６ ： 全訂正処理再起動。<BR>
	 * ７ ： 切替処理完了。<BR>
	 */
	public String changeStartDiv;
    
    
    /**
     * 発注経路情報クラスコンストラクタ<BR>
     * @@roseuid 42E8A90901DC
     */
    public WEB3AdminOrderRouteInfo() 
    {
     
    }
    
	/**
	 * 当クラスの整合性をチェックする。<BR>
	 * <BR>
	 * １）　@変換市場コードチェック<BR>
	 * 　@this.変換市場コード == nullの場合、例外をスローする。<BR>
	 * <BR>
     * ２）　@市場コードチェック<BR>
     * 　@this.市場コード == nullの場合、例外をスローする。<BR>
     * <BR> 
	 * ２）　@銘柄タイプ<BR>
	 * 　@this.銘柄タイプ == nullの場合、例外をスローする。<BR>
	 * <BR>
	 * ３）　@発注経路<BR>
	 * 　@this.発注経路 == nullの場合、例外をスローする。<BR>
	 * @@roseuid 42D211F10321
	 */
    public void validate() throws WEB3BaseException 
    {
  
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
  
		//変換市場コードチェック
		//エラーの場合は、例外をthrowする。
		if (this.convertMarketCode == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02209,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        //市場コードチェック
        //エラーの場合は、例外をthrowする。
        else if (this.marketCode == null){
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
		//銘柄タイプチェック
		//エラーの場合は、例外をthrowする。
		else if (this.productType == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01109,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
		//発注経路区分チェック
		//エラーの場合は、例外をthrowする。
		else if (this.submitOrderRouteDiv == null){
			
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02210,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		log.exiting(STR_METHOD_NAME);
    }
}
@
