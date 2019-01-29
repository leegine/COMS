head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者デーモントリガーテーブルステータス更新サービスImpl(WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/19  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecDaemonTriggerTableUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.DaemonTriggerPK;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (管理者デーモントリガーテーブルステータス更新サービスImpl)<BR>
 * 管理者デーモントリガーテーブルステータス更新サービス実装クラス。<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl implements
	WEB3AdminDirSecDaemonTriggerTableUpdateService
{
	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.class);

	/**
	 * @@roseuid 44BE20BE01E4
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl()
	{

	}

	/**
	 * 管理者・デーモントリガーテーブルステータス更新処理を開始する。<BR>
	 * <BR>
	 * リクエストデータの型により、 <BR>
	 * 以下のメソッドを呼び分ける。 <BR>
	 * <BR>
	 * ○管理者・デーモントリガーテーブル検索入力リクエストの場合 <BR>
	 * this.get検索条件入力画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・デーモントリガーテーブル検索結果リクエストの場合 <BR>
	 * this.get検索結果画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・デーモントリガーテーブルステータス更新確認リクエストの場合 <BR>
	 * this.validate更新確認画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・デーモントリガーテーブルステータス更新完了リクエストの場合 <BR>
	 * this.submit更新完了画面()をコールする。<BR>
	 * 
	 * @@param l_request - リクエストデータ。<BR>
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32B290169
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
		log.entering(STR_METHOD_NAME);

		if (l_request == null)
		{
			log.debug("パラメータ値不正。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"パラメータ値不正。");
		}

		WEB3GenResponse l_response = null;

		// ○管理者・デーモントリガーテーブル検索入力リクエストの場合
		// this.get検索条件入力画面()をコールする。
		if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableSearchInputRequest)
		{
			l_response = this.getSearchConditionInput(
				(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest) l_request);
		}

		// ○管理者・デーモントリガーテーブル検索結果リクエストの場合 
		// this.get検索結果画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableSearchResultRequest)
		{
			l_response = this.getSearchResult(
				(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest) l_request);
		}

		// ○管理者・デーモントリガーテーブルステータス更新確認リクエストの場合
		// this.validate更新確認画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)
		{
			l_response = this.validateUpdateConfirm(
				(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest) l_request);
		}

		// ○管理者・デーモントリガーテーブルステータス更新完了リクエストの場合 
		// this.submit更新完了画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest)
		{
			l_response = this.submitUpdateComplete(
				(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest) l_request);
		}
		else
		{
			log.debug("パラメータタイプ不正。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"パラメータタイプ不正。");
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get検索条件入力)<BR>
	 * 管理者デーモントリガーテーブル検索画面表示処理を行う。 <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get検索条件入力画面」参照。<BR>
	 * 具体位置 : 1.3 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * 例外をスローする。 <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *         管理者・デーモントリガーテーブル検索入力リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchInputResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32CA10198
	 */
	protected WEB3AdminDirSecDaemonTriggerTableSearchInputResponse getSearchConditionInput(
		WEB3AdminDirSecDaemonTriggerTableSearchInputRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchConditionInput(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2validate権限(機@能カテゴリコード : String, is更新 : boolean)
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.3 isDIR管理者( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}

		// 1.4 createResponse( )
		WEB3AdminDirSecDaemonTriggerTableSearchInputResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableSearchInputResponse) l_request.createResponse();
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get検索結果)<BR>
	 * 管理者デーモントリガーテーブル検索画面表示処理を行う。 <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get検索結果画面」参照。<BR>
	 * 具体位置 : 1.4 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * 例外をスローする。 <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *         管理者・デーモントリガーテーブル検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchResultResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB70228
	 */
	protected WEB3AdminDirSecDaemonTriggerTableSearchResultResponse getSearchResult(
		WEB3AdminDirSecDaemonTriggerTableSearchResultRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchResult(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( ) 
		l_request.validate();

		// 1.2 getInstanceFromログイン情報( ) 
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean) 
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR管理者( ) 
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName()+ "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}
		// 1.5 getデーモントリガーテーブルレコード(String, String) 
		DaemonTriggerRow l_daemonTriggerRow = this.getDaemonTriggerTableRecord(
			l_request.threadNo, l_request.triggerStatus);

		// 1.6 createResponse( ) 
		WEB3AdminDirSecDaemonTriggerTableSearchResultResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse) l_request.createResponse();

		// 1.7 プロパティセット 
		// レスポンスデータに以下のプロパティをセットする
		
		// 処理タイプ = getデーモントリガーテーブルレコードの戻り値.get処理タイプ
		l_response.triggerType = l_daemonTriggerRow.getTriggerType();
		
		// 識別コード = getデーモントリガーテーブルレコードの戻り値.get識別コード
		l_response.orderRequestNumber = l_daemonTriggerRow.getOrderRequestNumber();
		
		// 顧客コード（自） = getデーモントリガーテーブルレコードの戻り値.get顧客コード（自） 
		l_response.rangeFrom = String.valueOf(l_daemonTriggerRow.getRangeFrom());
		
		// 顧客コード（至） = getデーモントリガーテーブルレコードの戻り値.get顧客コード（至）
		l_response.rangeTo = String.valueOf(l_daemonTriggerRow.getRangeTo());
		
		// ステータス = getデーモントリガーテーブルレコードの戻り値.get処理状態
		l_response.triggerStatus = l_daemonTriggerRow.getTriggerStatus();
		
		// 最終処理日時 = getデーモントリガーテーブルレコードの戻り値.get最終処理日時
		l_response.triggerDate = l_daemonTriggerRow.getTriggerDate();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (validate更新確認)<BR>
	 * 管理者デーモントリガーテーブル検索画面表示処理を行う。 <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）validate更新確認画面」参照。 <BR>
	 * 具体位置 : 1.4 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * 例外をスローする。 <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *         管理者・デーモントリガーテーブルステータス更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB90014
	 */
	protected WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse validateUpdateConfirm(
		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validateUpdateConfirm"
				+ "(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR管理者( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}

		// 1.5 createResponse( )
		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (submit更新完了)<BR>
	 * 管理者デーモントリガーテーブル検索画面表示処理を行う。 <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）submit更新完了画面」参照。 <BR>
	 * 具体位置 : 1.4 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * 例外をスローする。 <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *         管理者・デーモントリガーテーブルステータス更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB903CE
	 */
	protected WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse submitUpdateComplete(
		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " submitUpdateComplete"
				+ "(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate() 
		l_request.validate();
		
		// 1.2 getInstanceFromログイン情報( ) 
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean) 
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR管理者( ) 
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}

		// 1.5 validate取引パスワード(パスワード : String) 
		l_admin.validateTradingPassword(l_request.password);
		
		// 1.6 updateデーモントリガーテーブルレコード
		this.updateDaemonTriggerTableRecord(l_request.threadNo,
			l_request.updateTriggerStatus);

		// 1.7 createResponse 
		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;

	}

	/**
	 * (getデーモントリガーテーブルレコード)<BR>
	 * 下記条件に該当する行をデーモントリガーテーブルより検索する。<BR>
	 * <BR>
	 * １）引数：処理状態の値により検索条件に下記を指定する<BR>
	 * <BR>
	 * ●引数：ステータス == Nullの場合<BR>
	 * 引数：スレッド番号<BR>
	 * <BR>
	 * ●引数：ステータス != Nullの場合<BR>
	 * 引数：スレッド番号、引数：処理状態<BR>
	 * <BR>
	 * ２）検索結果が0件の場合、エラーを返却する。<BR>
	 * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01037<BR>
	 * 
	 * @@param l_strThreadNo - (スレッド番号)<BR>
	 *         スレッド番号。<BR>
	 * @@param l_strTriggerStatus - (処理状態)<BR>
	 *         処理状態。<BR>
	 * @@return DaemonTriggerRow
	 * @@throws WEB3BaseException
	 * @@roseuid 44B365D60367
	 */
	private DaemonTriggerRow getDaemonTriggerTableRecord(String l_strThreadNo,
		String l_strTriggerStatus) 
	    throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getDaemonTriggerTableRecord"
			+ "(l_strThreadNo,l_strTriggerStatus)";
		log.entering(STR_METHOD_NAME);
		
		DaemonTriggerRow l_daemonTriggerRow = null;
		String l_strWhere = null;
		Object[] l_objbindVars = null;
		List l_lisDaemonTriggerRowList = null;
		//引数：処理状態の値により検索条件に下記を指定する
		//引数：ステータス == Nullの場合
		//引数：スレッド番号
		if (l_strTriggerStatus == null)
		{
			l_strWhere = " thread_no = ?  ";
			l_objbindVars =  new Object[1];
			l_objbindVars[0] = l_strThreadNo;
			
		}
		//引数：ステータス != Nullの場合
		//引数：スレッド番号、引数：処理状態
		else
		{
			l_strWhere = " thread_no = ? and  trigger_status = ?  ";
			l_objbindVars =  new Object[2];
			l_objbindVars[0] = l_strThreadNo;
			l_objbindVars[1] = l_strTriggerStatus;	
		}

		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisDaemonTriggerRowList = 
				l_queryProcessor.doFindAllQuery(
					DaemonTriggerRow.TYPE, 
					l_strWhere,
					null, 
					l_objbindVars);
		}
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisDaemonTriggerRowList == null || l_lisDaemonTriggerRowList.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }		
		l_daemonTriggerRow = 
			(DaemonTriggerRow) l_lisDaemonTriggerRowList.get(0);
		log.exiting(STR_METHOD_NAME);
		return l_daemonTriggerRow;
	}

	/**
	 * (updateデーモントリガーテーブルレコード)<BR>
     * デーモントリガーテーブルの更新処理をおこなう。 <BR>
	 * <BR>
	 * １）　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。 <BR>
	 * [doUpdateAllQuery()にセットするパラメータ] <BR>
     * arg0：　@引数：スレッド番号を元に生成したPrimaryKeyオブジェクト <BR>
     * arg1：　@引数：処理状態を元に生成したMapオブジェクト <BR>
     * 
	 * @@param l_strThreadNo - (スレッド番号)<BR>
	 *         スレッド番号。<BR>
	 * @@param l_strTriggerStatus - (処理状態)<BR>
	 *         処理状態。<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 44B3662C0368
	 */
	private void updateDaemonTriggerTableRecord(String l_strThreadNo,
		String l_strTriggerStatus) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " updateDaemonTriggerTableRecord"
			+ "(String l_strThreadNo, String l_strTriggerStatus)";
		log.entering(STR_METHOD_NAME);
		
        //処理状態を元に生成したMapオブジェクト 
        Map l_mapStatus = new HashMap();
        l_mapStatus.put("trigger_status", l_strTriggerStatus);

		try
		{   
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			
			//スレッド番号を元に生成したPrimaryKeyオブジェクト 
			long l_lngThreadNo = Long.parseLong(l_strThreadNo);
			DaemonTriggerPK l_daemonTriggerPK = new DaemonTriggerPK(l_lngThreadNo);
			l_queryProcessor.doUpdateQuery(l_daemonTriggerPK, l_mapStatus);
		}
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
		log.exiting(STR_METHOD_NAME);
	}

}
@
