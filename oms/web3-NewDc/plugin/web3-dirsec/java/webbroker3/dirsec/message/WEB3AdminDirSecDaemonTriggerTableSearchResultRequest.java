head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・デーモントリガーテーブル検索結果リクエスト(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.java)
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
 * (管理者・デーモントリガーテーブル検索結果リクエスト)<BR>
 * 管理者・デーモントリガーテーブル検索結果リクエストクラス。<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableSearchResultRequest extends WEB3GenRequest
{	
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_search_result";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

	/**
	 * (スレッド番号)<BR>
	 * スレッド番号<BR>
	 */
	public String threadNo;

	/**
	 * (ステータス)<BR>
	 * ステータス<BR>
	 */
	public String triggerStatus;

	/**
	 * @@roseuid 44BE20C1033C
	 */
	public WEB3AdminDirSecDaemonTriggerTableSearchResultRequest()
	{

	}

	/**
	 * 当リクエストデータの整合性チェックを行う。 <BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
	 * <BR>
	 * １）スレッド番号のチェック<BR>
	 * <BR>
	 * １－１）this.スレッド番号 == nullの場合、<BR>
	 * 「スレッド番号が未指定です。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02511<BR>
	 * <BR>
	 * １－２）this.スレッド番号 == 半角数字以外の場合、<BR>
	 * 「スレッド番号を半角数字で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02512<BR>
	 * <BR>
	 * １－３）this.スレッド番号のサイズ ＞ 18桁の場合、<BR>
	 * 「スレッド番号を18桁以下で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02513<BR>
	 * <BR>
	 * ２）処理状態のチェック<BR>
	 * this.処理状態 != nullの場合、以下のチェックを行う<BR>
	 * <BR>
	 * ２－１）this.処理状態 == 半角数字以外の場合、<BR>
	 * 「ステータスを半角数字で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02514<BR>
	 * <BR>
	 * ２－２）this.処理状態のサイズ != 1桁の場合、<BR>
	 * 「ステータスを1桁で入力して下さい。」<BR>
	 * の例外をスローする<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02515<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B2FBD60114
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		//１）スレッド番号のチェック １－１）this.スレッド番号 == nullの場合、 
		//スレッド番号が未指定です。」 の例外をスローする
		if (this.threadNo == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02511, 
				this.getClass().getName() + STR_METHOD_NAME, 
				 "スレッド番号が未指定です。");
		}
		
		//１－２）this.スレッド番号 == 半角数字以外の場合、 
		//スレッド番号を半角数字で入力して下さい。」 
		//の例外をスローする
		if (!WEB3StringTypeUtility.isDigit(this.threadNo))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02512, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"スレッド番号を半角数字で入力して下さい。");
		}
		
		//１－３）this.スレッド番号のサイズ ＞ 18桁の場合 
		//「スレッド番号を18桁以下で入力して下さい。」 の例外をスローする		
		if (this.threadNo.length() > 18)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02513, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"スレッド番号を18桁以下で入力して下さい。");
		}
		
        //２）処理状態のチェック this.処理状態 != nullの場合、以下のチェックを行う
		//２－１）this.処理状態 == 半角数字以外の場合、 「ステータスを半角数字で入力して下さい。」
		//の例外をスローする 
		if (this.triggerStatus != null)
		{
			if (!WEB3StringTypeUtility.isDigit(this.triggerStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02514, 
					this.getClass().getName() + STR_METHOD_NAME, 
					"ステータスを半角数字で入力して下さい。");
			}
			
			//２－２）this.処理状態のサイズ != 1桁の場合、 
		    //「ステータスを1桁で入力して下さい。」 の例外をスローする
			if (this.triggerStatus.length() != 1)
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02515, 
					this.getClass().getName() + STR_METHOD_NAME, 
					"ステータスを1桁で入力して下さい。");
			}
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
		return new WEB3AdminDirSecDaemonTriggerTableSearchResultResponse(this);
	}
}
@
