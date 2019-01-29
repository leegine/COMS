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
filename	WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者アップロードテーブル終了日時更新サービスImpl(WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/11  徐大方 (中訊) 新規作成
 */

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者アップロードテーブル終了日時更新サービスImpl)<BR>
 * 管理者アップロードテーブル終了日時更新サービスImplクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl implements
	WEB3AdminDirSecUploadTableEndDateUpdateService
{
	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.class);

	/**
	 * 管理者・アップロードテーブル終了日時更新処理を開始する。<BR>
	 * <BR>
	 * リクエストデータの型により、<BR>
	 * 以下のメソッドを呼び分ける。<BR>
	 * <BR>
	 * ○管理者・アップロードテーブルレコード一覧リクエストの場合<BR>
	 * this.get一覧画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・アップロードテーブル終了日時更新確認リクエストの場合<BR>
	 * this.get更新確認画面)をコールする。<BR>
	 * <BR>
	 * ○管理者・アップロードテーブル終了日時更新完了リクエストの場合<BR>
	 * this.get更新完了画面()をコールする。<BR>
	 * 
	 * @@param l_request -
	 *            リクエストデータ。<BR>
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E04A900F3
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

		// ○管理者・アップロードテーブルレコード一覧リクエストの場合<BR>
		// this.get一覧画面()をコールする。
		if (l_request instanceof WEB3AdminDirSecUploadTableListRequest)
		{
			l_response = this.getListScreen(
				(WEB3AdminDirSecUploadTableListRequest) l_request);
		}

		// ○管理者・アップロードテーブル終了日時更新確認リクエストの場合<BR>
		// this.get更新確認画面)をコールする。
		else if (l_request instanceof WEB3AdminDirSecUploadTableUpdateConfirmRequest)
		{
			l_response = this.getUpdateConfirmScreen(
				(WEB3AdminDirSecUploadTableUpdateConfirmRequest) l_request);
		}

		// ○管理者・アップロードテーブル終了日時更新完了リクエストの場合<BR>
		// this.get更新完了画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecUploadTableUpdateCompleteRequest)
		{
			l_response = this.getUpdateCompleteScreen(
				(WEB3AdminDirSecUploadTableUpdateCompleteRequest) l_request);
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
	 * (createアップロードテーブルレコード詳細)<BR>
	 * アップロードテーブルレコードListより、アップロードテーブルレコード詳細型配列を作成する。<BR>
	 * <BR>
	 * １） ArrayListオブジェクトの生成。<BR>
	 * <BR>
	 * ２） 引数:アップロードテーブルレコードListの要素分、Loop処理をおこなう。<BR>
	 * <BR>
	 * ２－１） アップロードテーブルレコード詳細クラスのオブジェクトを生成する。<BR>
	 * <BR>
	 * ２－２） ２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
	 * <BR>
	 * アップロードテーブルレコード詳細オブジェクト.アップロードID = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).getアップロードID()<BR>
	 * アップロードテーブルレコード詳細オブジェクト.証券会社コード = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).get証券会社コード() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.部店コード = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).get部店コード() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.銘柄タイプ = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).get銘柄タイプ() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.アップロード開始日時 =<BR>
	 * （引数）アップロードテーブルレコードList.get(index).getアップロード開始日時() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.アップロード終了日時 = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).getアップロード終了日時() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.メッセージコード = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).getメッセージコード() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.アップロード件数 = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).getアップロード件数() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.更新者コード = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).get更新者コード() <BR>
	 * アップロードテーブルレコード詳細オブジェクト.データキー = <BR>
	 * （引数）アップロードテーブルレコードList.get(index).getデータキー() <BR>
	 * <BR>
	 * ２－３） １）で生成したArrayListオブジェクトにアップロードテーブルレコード詳細<BR>
	 * オブジェクトをadd()する。<BR>
	 * <BR>
	 * ３） アップロードテーブルレコード詳細クラス型の配列オブジェクトを<BR>
	 * ArrayListオブジェクトのサイズで生成する。<BR>
	 * <BR>
	 * ４） toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。<BR>
	 * <BR>
	 * ArrayListオブジェクト.toArray(<BR>
	 * アップロードテーブルレコード詳細クラス型の配列オブジェクト);<BR>
	 * <BR>
	 * ５） 変換した配列オブジェクトを返却する。<BR>
	 * 
	 * @@param l_lisUploadTableRecordList -
	 *            (アップロードテーブルレコードList)<BR>
	 *            アップロードテーブルレコードList。<BR>
	 * @@return WEB3AdminDirSecUploadTableRecordDetail[]
	 * @@roseuid 4451C7E80111
	 */
	private WEB3AdminDirSecUploadTableUnit[] createUploadTableRecordDetail(
		List l_lisUploadTableRecordList)
	{
		// １） ArrayListオブジェクトの生成。
		List l_lisArrayList = new ArrayList();

		// ２） 引数:アップロードテーブルレコードListの要素分、Loop処理をおこなう。
		int l_intUploadTableRecordList = 0;
		if (l_lisUploadTableRecordList != null)
		{
			l_intUploadTableRecordList = l_lisUploadTableRecordList.size();
		}
		for (int i = 0; i < l_intUploadTableRecordList; i++)
		{
			AdministratorUploadRow l_row = (AdministratorUploadRow) l_lisUploadTableRecordList.get(i);
			// ２－１） アップロードテーブルレコード詳細クラスのオブジェクトを生成する。
			WEB3AdminDirSecUploadTableUnit l_dirSecUploadTableUnit = new WEB3AdminDirSecUploadTableUnit();
			// ２－２） ２－１）で生成したオブジェクトに以下の内容をセットする。
			// アップロードテーブルレコード詳細オブジェクト.アップロードID =
			// （引数）アップロードテーブルレコードList.get(index).getアップロードID()
			l_dirSecUploadTableUnit.administratorUploadId = 
				l_row.getAdministratorUploadId() + "";

			// アップロードテーブルレコード詳細オブジェクト.証券会社コード =
			// （引数）アップロードテーブルレコードList.get(index).get証券会社コード()
			l_dirSecUploadTableUnit.institutionCode = 
				l_row.getInstitutionCode();

			// アップロードテーブルレコード詳細オブジェクト.部店コード =
			// （引数）アップロードテーブルレコードList.get(index).get部店コード()
			l_dirSecUploadTableUnit.branchCode = l_row.getBranchCode();

			// アップロードテーブルレコード詳細オブジェクト.銘柄タイプ =
			// （引数）アップロードテーブルレコードList.get(index).get銘柄タイプ()
			l_dirSecUploadTableUnit.productType = 
				l_row.getProductType().intValue() + "";

			// アップロードテーブルレコード詳細オブジェクト.アップロード開始日時 =
			// （引数）アップロードテーブルレコードList.get(index).getアップロード開始日時()
            l_dirSecUploadTableUnit.uploadStartTimestamp = 
                WEB3DateUtility.formatDate(l_row.getUploadStartTimestamp(), "yyyyMMddHHmmss");  

			// アップロードテーブルレコード詳細オブジェクト.アップロード終了日時 =
			// （引数）アップロードテーブルレコードList.get(index).getアップロード終了日時()
            if (l_row.getUploadEndTimestamp() != null)
            {
                l_dirSecUploadTableUnit.uploadEndTimestamp = 
                    WEB3DateUtility.formatDate(l_row.getUploadEndTimestamp(), "yyyyMMddHHmmss"); 
            }

			// アップロードテーブルレコード詳細オブジェクト.メッセージコード =
			// （引数）アップロードテーブルレコードList.get(index).getメッセージコード()
			l_dirSecUploadTableUnit.messageCode = l_row.getMessageCode();

			// アップロードテーブルレコード詳細オブジェクト.アップロード件数 =
			// （引数）アップロードテーブルレコードList.get(index).getアップロード件数()
			l_dirSecUploadTableUnit.uploadCount = l_row.getUploadCount() + "";

			// アップロードテーブルレコード詳細オブジェクト.更新者コード =
			// （引数）アップロードテーブルレコードList.get(index).get更新者コード()
			l_dirSecUploadTableUnit.lastUpdater = l_row.getLastUpdater();

			// アップロードテーブルレコード詳細オブジェクト.データキー =
			// （引数）アップロードテーブルレコードList.get(index).getデータキー()
			l_dirSecUploadTableUnit.uploadKey = l_row.getUploadKey() + "";

			// ２－３） １）で生成したArrayListオブジェクトに
			// アップロードテーブルレコード詳細オブジェクトをadd()する。
			l_lisArrayList.add(l_dirSecUploadTableUnit);
		}

		// ３） アップロードテーブルレコード詳細クラス型の配列オブジェクトを
		// ArrayListオブジェクトのサイズで生成する。
		WEB3AdminDirSecUploadTableUnit[] l_dirSecUploadTableUnits = 
			new WEB3AdminDirSecUploadTableUnit[l_lisArrayList.size()];

		// ４） toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
		// ArrayListオブジェクト.toArray(アップロードテーブルレコード詳細クラス型の配列オブジェクト);
		l_lisArrayList.toArray(l_dirSecUploadTableUnits);

		// ５） 変換した配列オブジェクトを返却する。
		return l_dirSecUploadTableUnits;
	}

	/**
	 * (getアップロードテーブルレコード)<BR>
	 * アップロードテーブルから、アップロード終了日時がnullのレコードを検索し、<BR>
	 * 取得したレコードをListで返却する。<BR>
	 * <BR>
	 * １） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
	 * <BR>
	 * [doFindAllQuery()にセットするパラメータ]<BR>
	 * arg0： アップロードテーブルRowType <BR>
	 * arg1： "upload_end_timestamp is null"<BR>
	 * arg2： "administrator_upload_id" <BR>
	 * arg3： null <BR>
	 * arg4： null <BR>
	 * <BR>
	 * <BR>
	 * ※検索結果が0件の場合、エラーを返却する。 <BR>
	 * エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037））<BR>
	 * 
     * @@param l_institutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
	 * @@return List
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private List getUploadTableRecord(String l_institutionCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getUploadTableRecord(String)";
		log.entering(STR_METHOD_NAME);

		List l_lisAdministratorUploadRow = null;

		String[] l_strInstitutionCode = {l_institutionCode};
		try
		{
			// １） QueryProcessor.doFindAllQuery()メソッドをコールする。
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisAdministratorUploadRow = l_queryProcessor.doFindAllQuery(
				AdministratorUploadRow.TYPE,
				"institution_code=? and upload_end_timestamp is null",
				"administrator_upload_id",
				null, 
				l_strInstitutionCode);
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

		// ※検索結果が0件の場合、エラーを返却する。
		if (l_lisAdministratorUploadRow == null
			|| l_lisAdministratorUploadRow.size() == 0)
		{
			log.debug("条件に該当するデータが存在しない。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"条件に該当するデータが存在しない。");
		}

		log.exiting(STR_METHOD_NAME);
		return l_lisAdministratorUploadRow;
	}

	/**
	 * (getアップロードテーブルレコード)<BR>
	 * アップロードテーブルから、（引数）アップロードIDをキーとして検索を行い、<BR>
	 * 取得したレコードをListで返却する。<BR>
	 * <BR>
	 * １） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
	 * <BR>
	 * [doFindAllQuery()にセットするパラメータ]<BR>
	 * arg0： アップロードテーブルRowType <BR>
	 * arg1： "administrator_upload_id in (?,?,?…)"(※) <BR>
	 * arg2： "administrator_upload_id" <BR>
	 * arg3： null <BR>
	 * arg4： （引数）アップロードID<BR>
	 * <BR> （※ ? は（引数）アップロードID要素数分記述） <BR>
	 * 
	 * @@param l_strUploadID -
	 *            (アップロードID)<BR>
	 *            更新対象アップロードIDの配列。<BR>
	 * @@return List
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private List getUploadTableRecord(String[] l_strUploadId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getUploadTableRecord(String[])";
		log.entering(STR_METHOD_NAME);

		List l_lisAdministratorUploadRow = null;
		String l_strWhere = "administrator_upload_id in (?";
		int l_intUploadIdLength = l_strUploadId.length - 1;
		for (int i = 0; i < l_intUploadIdLength; i ++)
		{
			l_strWhere = l_strWhere + ",?";
		}
		l_strWhere = l_strWhere + ")";

		try
		{
			// １） QueryProcessor.doFindAllQuery()メソッドをコールする。
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisAdministratorUploadRow = l_queryProcessor.doFindAllQuery(
				AdministratorUploadRow.TYPE, 
				l_strWhere,
				"administrator_upload_id", 
				null, 
				l_strUploadId);
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

		log.exiting(STR_METHOD_NAME);
		return l_lisAdministratorUploadRow;
	}

	/**
	 * (get一覧画面)<BR>
	 * アップロードテーブルレコード一覧画面表示処理を行う。 <BR>
	 * <BR>
	 * シーケンス図 <BR>
	 * 「（管理者）get一覧画面」参照。<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）getキューテーブル一覧       <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
	 * 
	 * @@param l_request -
	 *            (管理者・アップロードテーブルレコード一覧リクエスト)<BR>
	 *            管理者・アップロードテーブルレコード検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableListResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableListResponse getListScreen(
		WEB3AdminDirSecUploadTableListRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecUploadTableListRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// getアップロードテーブルレコード（）で取得した値
		List l_lisTemp = null;

		WEB3AdminDirSecUploadTableUnit[] l_unit = null;

		// 1.1 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2validate権限(機@能カテゴリコード : String, is更新 : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

		// 1.3 isDIR管理者( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.4 DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857,
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}

		// 1.5 getアップロードテーブルレコード
		l_lisTemp = this.getUploadTableRecord(l_admin.getInstitutionCode());

		// 1.6 createアップロードテーブルレコード詳細
		l_unit = this.createUploadTableRecordDetail(l_lisTemp);

		// 1.7 createResponse( )
		WEB3AdminDirSecUploadTableListResponse l_response = 
			(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();

		l_response.uploadTables = l_unit;

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get更新確認画面)<BR>
	 * アップロードテーブル終了日時更新確認画面表示処理を行う。 <BR>
	 * <BR>
	 * シーケンス図 <BR>
	 * 「（管理者）get更新確認画面」参照。<BR>
	 * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）getキューテーブル一覧       <BR>
     *         具体位置    :  1.5 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * 
	 * @@param l_request -
	 *            (管理者・アップロードテーブル終了日時更新確認リクエスト)<BR>
	 *            管理者・アップロードテーブル終了日時更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableEndDateUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen(WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3validate権限(機@能カテゴリコード : String, is更新 : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

		// 1.4 isDIR管理者( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.5 DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR管理者権限チェックエラー。");
		}

		// 1.6 createResponse( )
		WEB3AdminDirSecUploadTableUpdateConfirmResponse l_response =
			(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
	 * (get更新完了画面)<BR>
	 * アップロードテーブル終了日時更新完了画面表示処理を行う。<BR>
	 * <BR>
	 * シーケンス図 <BR>
	 * 「（管理者）get更新完了画面」参照。<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）getキューテーブル一覧       <BR>
     *         具体位置    :  1.5 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
	 * 
	 * @@param l_request -
	 *            (管理者・アップロードテーブル終了日時更新完了リクエスト)<BR>
	 *            管理者・アップロードテーブル終了日時更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecUploadTableEndDateUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen(WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// getアップロードテーブルレコード（）で取得した値
		List l_lisTemp = null;

		WEB3AdminDirSecUploadTableUnit[] l_unit = null;

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3validate権限(機@能カテゴリコード : String, is更新 : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

		// 1.4 isDIR管理者( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.5 DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR管理者権限チェックエラー。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"DIR管理者権限チェックエラー。");
		}

		// 1.6 validate取引パスワード(パスワード : String)
		l_admin.validateTradingPassword(l_request.password);

		// 1.7 updateアップロードテーブル
		this.updateUploadTable(l_request.administratorUploadId);

		// 1.8 getアップロードテーブルレコード
		l_lisTemp = this.getUploadTableRecord(l_request.administratorUploadId);

		// 1.9 createアップロードテーブルレコード詳細
		l_unit = this.createUploadTableRecordDetail(l_lisTemp);

		// 1.10 createResponse( )
		WEB3AdminDirSecUploadTableUpdateCompleteResponse l_response = 
			(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();

		l_response.uploadTables = l_unit;

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (updateアップロードテーブル )<BR>
	 * 指定したアップロードテーブルの更新処理をおこなう。(DB更新仕様参照) <BR>
	 * <BR>
	 * <BR>
	 * １） 更新内容を「コラム名-値」のペアで示すMapオブジェクトを生成する。<BR>
	 * コラム名："upload_end_timestamp" <BR>
	 * 値：TradingSystem#getSystemTimestamp()の戻り値 <BR>
	 * <BR>
	 * ２） （引数）アップロードIDの要素数回以下を繰り返す。 <BR>
	 * <BR>
	 * ２－１） QueryProcessor.doUpdateAllQuery()メソッドをコールする。 <BR>
	 * <BR>
	 * [doUpdateAllQuery()にセットするパラメータ] <BR>
	 * arg0： アップロードテーブルRowType <BR>
	 * arg1： "administrator_upload_id =?" <BR>
	 * arg2： （引数）アップロードID[index] <BR>
	 * arg3： １）で作成したMap <BR>
	 * 
	 * @@param l_strUploadId - (アップロードID)<BR>
	 * 更新対象アップロードIDの配列。<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private void updateUploadTable(String[] l_strUploadId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "updateUploadTable(String[] l_strUploadId)";
		log.entering(STR_METHOD_NAME);
		
		String l_strWhere = "administrator_upload_id in (?";
		int l_intUploadIdLength = l_strUploadId.length - 1;
		for (int i = 0; i < l_intUploadIdLength; i ++)
		{
			l_strWhere = l_strWhere + ",?";
		}
		l_strWhere = l_strWhere + ")";

		// １） 更新内容を「コラム名-値」のペアで示すMapオブジェクトを生成する。
		Map l_mapStatus = new HashMap();
		l_mapStatus.put("upload_end_timestamp", GtlUtils.getSystemTimestamp());

		// ２－１） QueryProcessor.doUpdateAllQuery()メソッドをコールする.
		QueryProcessor l_queryProcessor;
		try
		{
			l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doUpdateAllQuery(
				AdministratorUploadRow.TYPE,
				l_strWhere, 
				l_strUploadId, 
				l_mapStatus);
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

		log.exiting(STR_METHOD_NAME);
	}


}
@
