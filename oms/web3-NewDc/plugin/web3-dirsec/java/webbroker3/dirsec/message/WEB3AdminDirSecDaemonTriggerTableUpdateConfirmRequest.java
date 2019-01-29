head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・デーモントリガーテーブルステータス更新確認リクエスト(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・デーモントリガーテーブルステータス更新確認リクエスト)<BR>
 * 管理者・デーモントリガーテーブルステータス更新確認リクエストクラス<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest extends WEB3GenRequest
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_update_confirm";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(
			WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);
	
	/**
	 * (更新ステータス)<BR>
	 * 更新ステータス<BR>
	 */
	public String updateTriggerStatus;

	/**
	 * @@roseuid 44BE20C1004E
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest()
	{

	}

	/**
	 * 当リクエストデータの整合性チェックを行う。 <BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
	 * <BR>
	 * １)更新ステータスのチェックを行う。<BR>
	 * <BR>
	 * １－１）this.更新ステータス == nullの場合、 <BR>
	 * 「更新ステータスが未指定です。」<BR>
	 * の例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02516<BR>
	 * <BR>
	 * １－２）this.更新ステータス == 半角数字以外の場合、<BR>
	 * 「更新ステータスを半角数字で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02517<BR>
	 * <BR>
	 * １－３）this.更新ステータスのサイズ != 1桁の場合、<BR>
	 * 「更新ステータスを1桁で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02518<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B309B202F4
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		
		//１）更新ステータスのチェックを行う。
        //１－１）this.更新ステータス == nullの場合、 
		//「更新ステータスが未指定です。」 の例外をスローする。
		if (this.updateTriggerStatus == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02516, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"更新ステータスが未指定です。");
		}
		
		// １－２）this.更新ステータス == 半角数字以外の場合、 
		//「更新ステータスを半角数字で入力して下さい。」 の例外をスローする
		if (!WEB3StringTypeUtility.isDigit(this.updateTriggerStatus))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02517, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"更新ステータスを半角数字で入力して下さい。");
		}
		
		//１－３）this.更新ステータスのサイズ != 1桁の場合、 
		//「更新ステータスを1桁で入力して下さい。」 の例外をスローする
		if (this.updateTriggerStatus.length() != 1)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02518,
				this.getClass().getName() + STR_METHOD_NAME, 
				"更新ステータスを1桁で入力して下さい。");
		}
		log.exiting(STR_METHOD_NAME);

	}
	
	/**
	 * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
	 * <BR>
	 * 
	 * @@return レスポンスオブジェクト
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse(this);
	}
}
@
