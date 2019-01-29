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
filename	WEB3AdminDirSecAioOrderUnitTableUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文単位テーブル注文状態更新ハンドラ(WEB3AdminDirSecAioOrderUnitTableUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
*/

package webbroker3.dirsec.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (管理者注文単位テーブル注文状態更新ハンドラ)<BR>
 * 管理者注文単位テーブル注文状態更新ハンドラクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateHandler implements MessageHandler
{
	/**
	 * ログ出力ユーティリティ。<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class);

	/**
	 * @@roseuid 44BE20BF005D
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateHandler()
	{

	}

	/**
	 * (get検索画面)<BR>
	 * 注文単位テーブルレコード検索画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者注文単位テーブル注文状態更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル検索入力リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchInputResponse
	 * @@roseuid 444E0A340162
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputResponse getSearchScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchInputRequest l_request)
	{
		final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminDirSecOrderUnitTableSearchInputRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableSearchInputResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// 管理者注文単位テーブル注文状態更新サービスImplを取得
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "管理者注文単位テーブル注文状態更新サービスImplを取得に失敗しました。", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "注文単位テーブルレコード検索画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get検索結果画面)<BR>
	 * 注文単位テーブルレコード検索結果画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者注文単位テーブル注文状態更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchResultResponse
	 * @@roseuid 444E0A3502AA
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse getSearchResultScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request)
	{
		final String STR_METHOD_NAME = " getSearchResultScreen(WEB3AdminDirSecOrderUnitTableSearchResultRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// 管理者注文単位テーブル注文状態更新サービスImplを取得
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "管理者注文単位テーブル注文状態更新サービスImplを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "注文単位テーブルレコード検索結果画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get更新確認画面)<BR>
	 * 注文単位テーブル注文状態更新確認画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者注文単位テーブル注文状態更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル注文状態更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateConfirmResponse
	 * @@roseuid 444E0A3702F7
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request)
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// 管理者注文単位テーブル注文状態更新サービスImplを取得
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "管理者注文単位テーブル注文状態更新サービスImplを取得に失敗しました。", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "注文単位テーブル注文状態更新確認画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get更新完了画面)<BR>
	 * 管理者注文単位テーブル注文状態更新完了画面表示処理を行う。<BR>
	 * <BR>
	 * 管理者注文単位テーブル注文状態更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル注文状態更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateCompleteResponse
	 * @@roseuid 444E0A3703D1
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request)
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen(WEB3AdminDirSecOrderUnitTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// 管理者注文単位テーブル注文状態更新サービスImplを取得
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "管理者注文単位テーブル注文状態更新サービスImplを取得に失敗しました。", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "管理者注文単位テーブル注文状態更新完了画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
