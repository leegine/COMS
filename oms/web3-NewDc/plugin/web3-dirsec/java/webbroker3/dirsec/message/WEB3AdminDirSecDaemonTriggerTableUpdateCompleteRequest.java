head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・デーモントリガーテーブルステータス更新完了リクエスト(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.java)
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

/**
 * (管理者・デーモントリガーテーブルステータス更新完了リクエスト)<BR>
 * 管理者・デーモントリガーテーブルステータス更新完了リクエストクラス<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest extends WEB3GenRequest
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_update_complete";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

	/**
	 * (スレッド番号)<BR>
	 * スレッド番号<BR>
	 */
	public String threadNo;

	/**
	 * (更新ステータス)<BR>
	 * 更新ステータス<BR>
	 */
	public String updateTriggerStatus;

	/**
	 * (暗証番号)<BR>
	 * 暗証番号<BR>
	 */
	public String password;

	/**
	 * @@roseuid 44BE20C101C5
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest()
	{

	}

	/**
	 * 当リクエストデータの整合性チェックを行う。 <BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
	 * <BR>
	 * １）スレッド番号のチェックを行う。<BR>
	 * <BR>
	 * １－１）this.スレッド番号 == nullの場合、 <BR>
	 * 「スレッド番号が未指定です。」<BR>
	 * の例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02511<BR>
	 * <BR>
	 * ２）更新ステータスのチェックを行う。<BR>
	 * <BR>
	 * ２－１）this.更新ステータス == nullの場合、 <BR>
	 * 「更新ステータスが未指定です。」<BR>
	 * の例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02516<BR>
	 * <BR>
	 * ３)暗証番号のチェックを行う。<BR>
	 * <BR>
	 * ３－１）this.暗証番号 == nullの場合、 <BR>
	 * 「暗証番号が未入力です。」<BR>
	 * の例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02510<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B30B8200C0
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		
        //１）スレッド番号のチェックを行う。
	    //１－１）this.スレッド番号 == nullの場合、 
		//「スレッド番号が未指定です。」 の例外をスローする。
		if (this.threadNo == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02511, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"スレッド番号が未指定です。");
		}
		
		//２）更新ステータスのチェックを行う。
		//２－１）this.更新ステータス == nullの場合、
		//「更新ステータスが未指定です。」 の例外をスローする。
		if (this.updateTriggerStatus == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02516, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"更新ステータスが未指定です。");
		}
		
		//３)暗証番号のチェックを行う。
		//３－１）this.暗証番号 == nullの場合、 
		// 「暗証番号が未入力です。」の例外をスローする。
		if (this.password == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02510, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"暗証番号が未入力です。");
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
		return new WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse(this);
	}
}
@
