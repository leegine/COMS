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
filename	WEB3AdminDirSecUploadTableEndDateUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者アップロードテーブル終了日時更新ハンドラ(WEB3AdminDirSecUploadTableEndDateUpdateHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/08/11  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (管理者アップロードテーブル終了日時更新ハンドラ)<BR>
 * 管理者アップロードテーブル終了日時更新ハンドラクラス。<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableEndDateUpdateHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecUploadTableEndDateUpdateHandler.class);
	
    /**
	 * (get一覧画面)<BR>
	 * アップロードテーブルレコード一覧画面表示処理を行う。<BR>
	 * <BR>
	 * 管理者アップロードテーブル終了日時更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・アップロードテーブルレコード検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableListResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableListResponse getListScreen(
		WEB3AdminDirSecUploadTableListRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getDisplayScreen(WEB3AdminDirSecUploadTableListRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableListResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// 管理者キューテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"管理者アップロードテーブル終了日時更新サービスImpl lを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"アップロードテーブルレコード一覧画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
	 * (get更新確認画面)<BR>
	 * アップロードテーブル終了日時更新確認画面表示処理を行う。 <BR>
	 * <BR>
	 * 管理者アップロードテーブル終了日時更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・アップロードテーブル終了日時更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableUpdateConfirmResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getUpdateConfirm(WEB3AdminDirSecUploadTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableUpdateConfirmResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// 管理者キューテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"管理者アップロードテーブル終了日時更新サービスImpl lを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"アップロードテーブル終了日時更新確認画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
    /**
	 * (get更新完了画面)<BR>
	 * アップロードテーブル終了日時更新完了画面表示処理を行う。  <BR>
	 * <BR>
	 * 管理者アップロードテーブル終了日時更新サービスImplを取得し、 <BR>
	 * execute()メソッドをコールする。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・アップロードテーブル終了日時更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableUpdateCompleteResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getUpdateComplete(WEB3AdminDirSecUploadTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableUpdateCompleteResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// 管理者キューテーブルステータス更新サービスを取得
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"管理者アップロードテーブル終了日時更新サービスImpl lを取得に失敗しました。",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"アップロードテーブル終了日時更新完了画面表示処理の実施に失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
}
@
