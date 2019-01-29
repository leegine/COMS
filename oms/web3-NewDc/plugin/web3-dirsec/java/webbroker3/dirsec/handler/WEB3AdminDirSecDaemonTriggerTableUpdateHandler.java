head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者デーモントリガーテーブルステータス更新ハンドラ(WEB3AdminDirSecDaemonTriggerTableUpdateHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/19  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecDaemonTriggerTableUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者デーモントリガーテーブルステータス更新ハンドラ)<BR>
 * 管理者デーモントリガーテーブルステータス更新ハンドラクラス。<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecDaemonTriggerTableUpdateHandler implements MessageHandler
{
	/**
	 * ログ出力ユーティリティ。<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class);

	/**
	 * @@roseuid 44BE20BF0177
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateHandler()
	{

	}

	/**
	 * (検索条件入力)<BR>
	 * デーモントリガーテーブル検索画面表示を行う。<BR>
	 * <BR>
	 * 管理者デーモントリガーテーブルステータス更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *            管理者・デーモントリガーテーブル検索入力リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchInputResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecDaemonTriggerTableSearchInputResponse searchConditionInput(
		WEB3AdminDirSecDaemonTriggerTableSearchInputRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"searchConditionInput(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecDaemonTriggerTableSearchInputResponse l_response = null;
		WEB3AdminDirSecDaemonTriggerTableUpdateService l_service = null;

		// 管理者キューテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecDaemonTriggerTableUpdateService) Services.getService(
				WEB3AdminDirSecDaemonTriggerTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorMessage = l_ex.getMessage();
			log.error(l_request, 
				"管理者デーモントリガーテーブルステータス更新サービスImplを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchInputResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
			log.error(l_request, 
				"デーモントリガーテーブル検索画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (検索結果)<BR>
	 * デーモントリガーテーブル検索結果画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者デーモントリガーテーブルステータス更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *            管理者・デーモントリガーテーブル検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchResultResponse
	 * @@roseuid 44B324F60073
	 */
	public WEB3AdminDirSecDaemonTriggerTableSearchResultResponse searchResult(
		WEB3AdminDirSecDaemonTriggerTableSearchResultRequest l_request)
	{
		final String STR_METHOD_NAME =
			" searchResult(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecDaemonTriggerTableSearchResultResponse l_response = null;
		WEB3AdminDirSecDaemonTriggerTableUpdateService l_service = null;
		
		// 管理者デーモントリガーテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecDaemonTriggerTableUpdateService) Services.getService(
				WEB3AdminDirSecDaemonTriggerTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorMessage = l_ex.getMessage();
			log.error(l_request, 
				"管理者デーモントリガーテーブルステータス更新サービスImplを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
			log.error(l_request, 
				"デーモントリガーテーブル検索結果画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (更新確認)<BR>
	 * デーモントリガーテーブルステータス更新確認画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者デーモントリガーテーブルステータス更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request -(リクエストデータ)<BR>
	 *            デーモントリガーテーブルステータス更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse
	 * @@roseuid 44B3250E01BB
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse updateConfirm(
		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest l_request)
	{
		final String STR_METHOD_NAME = 
			" updateConfirm(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse l_response = null;
		WEB3AdminDirSecDaemonTriggerTableUpdateService l_service = null;

		// 管理者デーモントリガーテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecDaemonTriggerTableUpdateService) Services.getService(
				WEB3AdminDirSecDaemonTriggerTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorMessage = l_ex.getMessage();
			log.error(l_request, 
				"管理者デーモントリガーテーブルステータス更新サービスImplを取得に失敗しました。",
					l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
			log.error(l_request, 
				"デーモントリガーテーブルステータス更新確認画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
	 * (更新完了)<BR>
	 * デーモントリガーテーブルステータス更新完了画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者デーモントリガーテーブルステータス更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 *            デーモントリガーテーブルステータス更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse
	 * @@roseuid 44B3251F0100
	 */
    public WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse updateComplete(
		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest l_request)
	{
		final String STR_METHOD_NAME = 
			" updateComplete(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse l_response = null;
		WEB3AdminDirSecDaemonTriggerTableUpdateService l_service = null;

		// 管理者デーモントリガーテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecDaemonTriggerTableUpdateService) Services.getService(
				WEB3AdminDirSecDaemonTriggerTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorMessage = l_ex.getMessage();
			log.error(l_request, 
				"管理者デーモントリガーテーブルステータス更新サービスImplを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
			log.error(l_request, 
				"デーモントリガーテーブルステータス更新完了画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
