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
filename	WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文単位テーブル注文状態更新サービスImpl(WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
				   2006/08/11  徐大方 (中訊) 式樣變更・モデル015
                   2007/01/08  徐大方 (中訊) 仕様変更・モデル022
Revesion History : 2007/10/30  武波   (中訊) 仕様変更・モデル114
Revesion History : 2007/11/02  武波   (中訊) 仕様変更・モデル115
Revesion History : 2008/07/15  楊夫志 (中訊) 仕様変更・モデル131,ＤＢ更新仕様010
Revesion History : 2008/08/28  張少傑 (中訊) 仕様變更・実装の問題013
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirOrderUnitTblKbnDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

/**
 * (管理者注文単位テーブル注文状態更新サービスImpl)<BR>
 * 管理者注文単位テーブル注文状態更新サービス実装クラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl 
	implements WEB3AdminDirSecAioOrderUnitTableUpdateService
{
	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class);

	/**
	 * @@roseuid 44BE20BE031C
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl()
	{

	}

	/**
	 * 管理者・注文単位テーブル注文状態更新処理を開始する。<BR>
	 * <BR>
	 * リクエストデータの型により、 <BR>
	 * 以下のメソッドを呼び分ける。 <BR>
	 * <BR>
	 * ○管理者・注文単位テーブル検索入力リクエストの場合 <BR>
	 * this.get検索画面()をコールする。 <BR>
	 * <BR>
	 * ○管理者・注文単位テーブル検索結果リクエストの場合 <BR>
	 * this.get検索結果画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・注文単位テーブル注文状態更新確認リクエストの場合 <BR>
	 * this.get更新確認画面()をコールする。<BR>
	 * <BR>
	 * ○管理者・注文単位テーブル注文状態更新完了リクエストの場合<BR>
	 * this.get更新完了画面()をコールする。<BR>
	 * 
	 * @@param l_request - リクエストデータ。<BR>
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

		// ○管理者・注文単位テーブル検索入力リクエストの場合 <BR>
		// this.get検索画面()をコールする。
		if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableSearchInputRequest)
		{
			l_response = 
				this.getSearchScreen(
					(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest) l_request);
		}

		// ○管理者・注文単位テーブル検索結果リクエストの場合 <BR>
		// this.get検索結果画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableSearchResultRequest)
		{
			l_response = 
				this.getSearchResultScreen(
					(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest) l_request);
		}

		// ○管理者・注文単位テーブル注文状態更新確認リクエストの場合 <BR>
		// this.get更新確認画面)をコールする。
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest)
		{
			l_response = 
				this.getUpdateConfirmScreen(
					(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest) l_request);
		}

		// ○管理者・注文単位テーブル注文状態更新完了リクエストの場合<BR>
		// this.get更新完了画面()をコールする。
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest)
		{
			l_response = 
				this.getUpdateCompleteScreen(
					(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest) l_request);
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
	 * (get検索画面)<BR>
	 * 管理者注文単位テーブルレコード検索画面表示処理を行う。 <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get検索結果画面」参照。<BR>
	 * 具体位置 :1.4 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル検索入力リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchInputResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B04025A
	 */
	protected WEB3AdminDirSecAioOrderUnitTableSearchInputResponse getSearchScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchInputRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			" getSearchScreen(WEB3AdminDirSecOrderUnitTableSearchInputRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
		// [validate権限（）に指定する引数]
		// 機@能カテゴリコード："Z0102" （システム管理 注文単位テーブル注文状態更新）
		// is更新：true
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

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

		// 1.5 createResponse( )
		WEB3AdminDirSecAioOrderUnitTableSearchInputResponse l_response = 
			(WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get検索結果画面)<BR>
	 * 管理者注文単位テーブルレコード検索結果画面表示処理を行う。<BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get検索結果画面」参照。<BR>
	 * 具体位置 :1.5 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル検索結果リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchResultResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0A011F
	 */
	protected WEB3AdminDirSecAioOrderUnitTableSearchResultResponse getSearchResultScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchResultScreen(WEB3AdminDirSecOrderUnitTableSearchResultRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
		// [validate権限（）に指定する引数]
		// 機@能カテゴリコード："Z0102" （システム管理 注文単位テーブル注文状態更新）
		// is更新：true
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

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

        // 1.7 createResponse( )
        WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response =
            (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();

        //リクエストデータ.注文単位テーブル区分 == 0(外株)の場合、以下の処理を行う。
		//分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 0(外株)の場合、
        //以下の処理を行う。
        //get外株注文単位テーブルレコード(long)
        //外株注文単位テーブルのレコードを検索条件をもとに取得する。
        //[get外株注文単位テーブルレコード(）に指定する引数]
        //検索条件データ　@：　@リクエストデータ.注文単位ID
        if (WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(l_request.orderUnitTblKbn))
        {
            FeqOrderUnitRow l_feqOrderUnitRow = this.getFeqOrderUnitTableRecord(
                Long.parseLong(l_request.orderUnitId));

            // 1.8 （*）プロパティセット
            // （*）レスポンスデータに以下の内容をセットする。
            // 口座ID = get注文単位テーブルレコード()の戻り値.get口座ID()
            l_response.accountId = String.valueOf(l_feqOrderUnitRow.getAccountId());

            // 部店ID = get注文単位テーブルレコード()の戻り値.get部店ID()
            l_response.branchId = String.valueOf(l_feqOrderUnitRow.getBranchId());

            // 注文ID = get注文単位テーブルレコード()の戻り値.get注文ID()
            l_response.orderId = String.valueOf(l_feqOrderUnitRow.getOrderId());

            // 銘柄タイプ = get注文単位テーブルレコード()の戻り値.get銘柄タイプ()
            l_response.productType = String.valueOf(l_feqOrderUnitRow.getProductType().intValue());

            // 注文状態 = get注文単位テーブルレコード()の戻り値.get注文状態()
            l_response.orderStatus = String.valueOf(l_feqOrderUnitRow.getOrderStatus().intValue());

            // 注文有効状態 = get注文単位テーブルレコード()の戻り値.get注文有効状態()
            l_response.orderOpenStatus = String.valueOf(l_feqOrderUnitRow.getOrderOpenStatus().intValue());

            // 識別コード = get注文単位テーブルレコード()の戻り値.get識別コード()
            l_response.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();
        }
        //リクエストデータ.注文単位テーブル区分 == 1(入出金)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 1(入出金)の場合、
        //以下の処理を行う。
		//get注文単位テーブルレコード(long)
		//[get注文単位テーブルレコード(）に指定する引数]
		//検索条件データ ： リクエストデータ.注文単位ID
        else if (WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(l_request.orderUnitTblKbn))
        {
    		AioOrderUnitRow l_aioOrderUnitRow = this.getOrderUnitTableRecord(
    			Long.parseLong(l_request.orderUnitId));

            // 1.8 （*）プロパティセット
            // （*）レスポンスデータに以下の内容をセットする。
            // 口座ID = get注文単位テーブルレコード()の戻り値.get口座ID()
            l_response.accountId = String.valueOf(l_aioOrderUnitRow.getAccountId());

            // 部店ID = get注文単位テーブルレコード()の戻り値.get部店ID()
            l_response.branchId = String.valueOf(l_aioOrderUnitRow.getBranchId());

            // 注文ID = get注文単位テーブルレコード()の戻り値.get注文ID()
            l_response.orderId = String.valueOf(l_aioOrderUnitRow.getOrderId());

            // 銘柄タイプ = get注文単位テーブルレコード()の戻り値.get銘柄タイプ()
            l_response.productType = String.valueOf(l_aioOrderUnitRow.getProductType().intValue());

            // 注文状態 = get注文単位テーブルレコード()の戻り値.get注文状態()
            l_response.orderStatus = String.valueOf(l_aioOrderUnitRow.getOrderStatus().intValue());

            // 注文有効状態 = get注文単位テーブルレコード()の戻り値.get注文有効状態()
            l_response.orderOpenStatus = String.valueOf(l_aioOrderUnitRow.getOrderOpenStatus().intValue());

            // 識別コード = get注文単位テーブルレコード()の戻り値.get識別コード()
            l_response.requestNumber = l_aioOrderUnitRow.getOrderRequestNumber();
        }
        //リクエストデータ.注文単位テーブル区分 == 2(投信)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 2(投信)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(l_request.orderUnitTblKbn))
        {
            // get投信注文単位テーブルレコード(long)
            MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
                this.getMutualFundOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // （*）プロパティセット
            // （*）レスポンスデータに以下の内容をセットする。
            // 口座ID = get投信注文単位テーブルレコード()の戻り値.get口座ID()
            l_response.accountId = String.valueOf(l_mutualFundOrderUnitRow.getAccountId());

            // 部店ID = get投信注文単位テーブルレコード()の戻り値.get部店ID()
            l_response.branchId = String.valueOf(l_mutualFundOrderUnitRow.getBranchId());

            // 注文ID = get投信注文単位テーブルレコード()の戻り値.get注文ID()
            l_response.orderId = String.valueOf(l_mutualFundOrderUnitRow.getOrderId());

            // 銘柄タイプ = get投信注文単位テーブルレコード()の戻り値.get銘柄タイプ()
            l_response.productType = String.valueOf(l_mutualFundOrderUnitRow.getProductType().intValue());

            // 注文状態 = get投信注文単位テーブルレコード()の戻り値.get注文状態()
            l_response.orderStatus = String.valueOf(l_mutualFundOrderUnitRow.getOrderStatus().intValue());

            // 注文有効状態 = get投信注文単位テーブルレコード()の戻り値.get注文有効状態()
            l_response.orderOpenStatus = String.valueOf(l_mutualFundOrderUnitRow.getOrderOpenStatus().intValue());

            // 識別コード = get投信注文単位テーブルレコード()の戻り値.get識別コード()
            l_response.requestNumber = l_mutualFundOrderUnitRow.getOrderRequestNumber();

            // 約定状態 = get投信注文単位テーブルレコード()の戻り値.get約定状態()
            l_response.execStatus = l_mutualFundOrderUnitRow.getExecStatus();
        }
        //リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(l_request.orderUnitTblKbn))
        {
        	EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                this.getEqtypeOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // （*）プロパティセット
            // （*）レスポンスデータに以下の内容をセットする。
            // 口座ID = get株式注文単位テーブルレコード()の戻り値.get口座ID()
            l_response.accountId = String.valueOf(l_eqtypeOrderUnitRow.getAccountId());

            // 部店ID = get株式注文単位テーブルレコード()の戻り値.get部店ID()
            l_response.branchId = String.valueOf(l_eqtypeOrderUnitRow.getBranchId());

            // 注文ID = get株式注文単位テーブルレコード()の戻り値.get注文ID()
            l_response.orderId = String.valueOf(l_eqtypeOrderUnitRow.getOrderId());

            // 銘柄タイプ = get株式注文単位テーブルレコード()の戻り値.get銘柄タイプ()
            l_response.productType = String.valueOf(l_eqtypeOrderUnitRow.getProductType().intValue());

            // 注文状態 = get株式注文単位テーブルレコード()の戻り値.get注文状態()
            l_response.orderStatus = String.valueOf(l_eqtypeOrderUnitRow.getOrderStatus().intValue());

            // 注文有効状態 = get株式注文単位テーブルレコード()の戻り値.get注文有効状態()
            l_response.orderOpenStatus = String.valueOf(l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());

            // 識別コード = get株式注文単位テーブルレコード()の戻り値.get識別コード()
            l_response.requestNumber = l_eqtypeOrderUnitRow.getOrderRequestNumber();

            // 約定状態 = null
            l_response.execStatus = null;

            // 発注日 = get株式注文単位テーブルレコード()の戻り値.get発注日()
            l_response.orderBizDate = l_eqtypeOrderUnitRow.getBizDate();
        }
        //リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(l_request.orderUnitTblKbn))
        {
        	IfoOrderUnitRow l_ifoOrderUnitRow =
                this.getIfoOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // （*）プロパティセット
            // （*）レスポンスデータに以下の内容をセットする。
            // 口座ID = get先物OP注文単位テーブルレコード()の戻り値.get口座ID()
            l_response.accountId = String.valueOf(l_ifoOrderUnitRow.getAccountId());

            // 部店ID = get先物OP注文単位テーブルレコード()の戻り値.get部店ID()
            l_response.branchId = String.valueOf(l_ifoOrderUnitRow.getBranchId());

            // 注文ID = get先物OP注文単位テーブルレコード()の戻り値.get注文ID()
            l_response.orderId = String.valueOf(l_ifoOrderUnitRow.getOrderId());

            // 銘柄タイプ = get先物OP注文単位テーブルレコード()の戻り値.get銘柄タイプ()
            l_response.productType = String.valueOf(l_ifoOrderUnitRow.getProductType().intValue());

            // 注文状態 = get先物OP注文単位テーブルレコード()の戻り値.get注文状態()
            l_response.orderStatus = String.valueOf(l_ifoOrderUnitRow.getOrderStatus().intValue());

            // 注文有効状態 = get先物OP注文単位テーブルレコード()の戻り値.get注文有効状態()
            l_response.orderOpenStatus = String.valueOf(l_ifoOrderUnitRow.getOrderOpenStatus().intValue());

            // 識別コード = get先物OP注文単位テーブルレコード()の戻り値.get識別コード()
            l_response.requestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            // 約定状態 = null
            l_response.execStatus = null;

            // 発注日 = get先物OP注文単位テーブルレコード()の戻り値.get発注日()
            l_response.orderBizDate = l_ifoOrderUnitRow.getBizDate();
        }

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get更新確認画面)<BR>
	 * 管理者注文単位テーブル注文状態更新確認画面表示処理を行う。<BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get更新確認画面」参照。<BR>
	 * 具体位置 :1.5 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル注文状態更新確認リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0C0237
	 */
	protected WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen"
			+ "(WEB3AdminDirSecOrderUnitTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		//validate更新_発注日(String)
		//更新_発注日のチェックを行う。
		//[validate更新_発注日（）に指定する引数]
		//更新_発注日：リクエストデータ.更新_発注日
		this.validateUpdateOrderBizDate(l_request.updateOrderBizDate);

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
		// [validate権限（）に指定する引数]
		// 機@能カテゴリコード："Z0102" （システム管理 注文単位テーブル注文状態更新）
		// is更新：true
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

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

		// 1.6 createResponse()
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse l_response = 
			(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get更新完了画面)<BR>
	 * 管理者注文単位テーブル注文状態更新完了画面表示処理を行う。<BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get更新完了画面」参照。<BR>
	 * 具体位置 :1.5 isDIR管理者()の戻り値 == falseの場合、例外をスロー<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * <BR>
	 * =============================================== <BR>
	 * シーケンス図 : 「（管理者）get更新完了画面」参照。<BR>
	 * 具体位置 :リクエストデータ.更新_注文状態==null &&<BR>
     * リクエストデータ.更新_注文有効状態==null &&<BR>
     * リクエストデータ.更新_約定状態==null &&<BR>
     * リクエストデータ.更新_発注日==nullの場合は、例外をスローする。<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * 　@tag : BUSINESS_ERROR_02519<BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * 管理者・注文単位テーブル注文状態更新完了リクエストクラス。<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0F018A
	 */
	protected WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen"
			+ "(WEB3AdminDirSecOrderUnitTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		//validate更新_発注日(String)
		//更新_発注日のチェックを行う。
		//[validate更新_発注日（）に指定する引数]
		//更新_発注日：リクエストデータ.更新_発注日
		this.validateUpdateOrderBizDate(l_request.updateOrderBizDate);

		// 1.2 getInstanceFromログイン情報( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
		// [validate権限（）に指定する引数]
		// 機@能カテゴリコード："Z0102" （システム管理 注文単位テーブル注文状態更新）
		// is更新：true
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

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

		// 1.7 リクエストデータ.更新_注文状態==null && リクエストデータ.更新_注文有効状態==null
        // && リクエストデータ.更新_約定状態==null && 
		//リクエストデータ.更新_発注日==nullの場合は、例外をスローする。
		if (l_request.updateOrderStatus == null
			&& l_request.updateOrderOpenStatus == null
            && l_request.updateExecStatus == null
            && l_request.updateOrderBizDate == null)
		{
            log.debug("全ての変更項目が未指定です。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02519, 
				this.getClass().getName() + STR_METHOD_NAME,
				"全ての変更項目が未指定です。");
		}

        //リクエストデータ.注文単位テーブル区分 == 0(外株)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 0(外株)の場合、
        //以下の処理を行う。
		//update外株注文単位テーブルレコード(long, String)
        //外株注文単位テーブルのレコード更新処理をおこなう。
        //[update外株注文単位テーブルレコード(）に指定する引数]
        //注文単位ID　@：　@リクエストデータ.注文単位ID
        //更新_注文状態 ： リクエストデータ.更新_注文状態
        if (WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(l_request.orderUnitTblKbn))
        {
            this.updateFeqOrderUnitTableRecord(
                Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus);
        }
        //リクエストデータ.注文単位テーブル区分 == 1(入出金)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 1(入出金)の場合、
        //以下の処理を行う。
		//update注文単位テーブルレコード(long, String, String)
		//[update注文単位テーブルレコード(）に指定する引数]
		//注文単位ID ： リクエストデータ.注文単位ID
		//更新_注文状態 ： リクエストデータ.更新_注文状態
		//更新_注文有効状態 ： リクエストデータ.更新_注文有効状態
        else if (WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(l_request.orderUnitTblKbn))
        {
    		this.updateOrderUnitTableRecord(Long.parseLong(l_request.orderUnitId),
    			l_request.updateOrderStatus,
    			l_request.updateOrderOpenStatus);
        }

        //リクエストデータ.投信注文単位テーブル区分 == 2(投信)の場合、以下の処理を行う。
        //リクエストデータ.投信注文単位テーブル区分 == 2(投信)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(l_request.orderUnitTblKbn))
        {
            //[update投信注文単位テーブルレコード(）に指定する引数]
            //注文単位ID　@：　@リクエストデータ.注文単位ID
            //更新_注文状態 ： リクエストデータ.更新_注文状態
            //更新_注文有効状態 ： リクエストデータ.更新_注文有効状態
            //更新_約定状態 ： リクエストデータ.更新_約定状態
            this.updateMutualFundOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateExecStatus);
        }

        //リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(l_request.orderUnitTblKbn))
        {
        	//[update注文単位テーブルレコード(）に指定する引数]
        	//注文単位ID　@：　@リクエストデータ.注文単位ID
        	//更新_注文状態 ： リクエストデータ.更新_注文状態
        	//更新_注文有効状態 ： リクエストデータ.更新_注文有効状態
        	//更新_発注日 ： リクエストデータ.更新_発注日
            this.updateEqtypeOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateOrderBizDate);
        }

        //リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、以下の処理を行う。
        //分岐フロー
        //リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、
        //以下の処理を行う。
        else if (WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(l_request.orderUnitTblKbn))
        {
        	//[update注文単位テーブルレコード(）に指定する引数]
        	//注文単位ID　@：　@リクエストデータ.注文単位ID
        	//更新_注文状態 ： リクエストデータ.更新_注文状態
        	//更新_注文有効状態 ： リクエストデータ.更新_注文有効状態
        	//更新_発注日 ： リクエストデータ.更新_発注日
            this.updateIfoOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateOrderBizDate);
        }

		// 1.9 createResponse()
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse l_response =
			(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get注文単位テーブルレコード)<BR>
	 * 注文単位テーブルから検索条件データを元にレコードを検索する。<BR>
	 * <BR>
	 * 注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
	 * 取得したRowオブジェクトを返却する。<BR>
	 * <BR>
	 * ※検索結果が0件の場合、エラーを返却する。<BR>
	 * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
	 * （BUSINESS_ERROR_01037）<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01037<BR>
	 * 
	 * @@param l_lngOrderUnitId - (注文単位ID)<BR>
	 * 注文単位ID。<BR>
	 * @@return AioOrderUnitRow
	 * @@throws WEB3BaseException
	 * @@roseuid 444F40E101CE
	 */
	private AioOrderUnitRow getOrderUnitTableRecord(long l_lngOrderUnitId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getOrderUnitTableRecord" + "(long l_lngOrderUnitId)";
		log.entering(STR_METHOD_NAME);

		AioOrderUnitRow l_aioOrderUnitRow = null;
		try
		{	
			l_aioOrderUnitRow = (AioOrderUnitRow) AioOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		if (l_aioOrderUnitRow == null)
		{
			log.debug("条件に該当するデータが存在しない。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"条件に該当するデータが存在しない。");	
		}

		log.exiting(STR_METHOD_NAME);
		return l_aioOrderUnitRow;
	}

	/**
	 * (update注文単位テーブルレコード)<BR>
	 * 注文単位テーブルの更新処理をおこなう。<BR>
	 * <BR>
	 * （引数）注文単位IDをキーとして以下内容で注文単位テーブルのUPDATEを行う。<BR>
	 * <BR>
	 * １） （引数）更新_注文状態 != nullの場合、<BR>
	 * ・注文状態：（引数）更新_注文状態<BR>
	 * ※ （引数）更新_注文状態 == nullの場合、<BR>
	 * 　@　@注文状態：更新不要（既存値）<BR>
	 * <BR>
	 * ２） （引数）更新_注文有効状態 != nullの場合、<BR>
	 * ・注文有効状態：（引数）更新_注文有効状態<BR>
	 * ※ （引数）更新_注文有効状態 == nullの場合、<BR>
	 *     注文有効状態：更新不要（既存値）<BR>
	 * <BR>
	 * @@param l_lngOrderUnitId - (注文単位ID)<BR>
	 * 注文単位ID。<BR>
	 * @@param l_strUpdateOrderStatus - (更新_注文状態)<BR>
	 * 更新_注文状態。<BR>
	 * @@param l_strUpdateOrderOpenStatus - (更新_注文有効状態)<BR>
	 * 更新_注文有効状態。<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private void updateOrderUnitTableRecord(long l_lngOrderUnitId, String l_strUpdateOrderStatus,
		String l_strUpdateOrderOpenStatus) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " updateOrderUnitTableRecord"
			+ "(l_lngOrderUnitId, l_strUpdateOrderStatus,"
			+ "l_strUpdateOrderOpenStatus)";
		log.entering(STR_METHOD_NAME);
			
        //処理状態を元に生成したMapオブジェクト 
        Map l_mapStatus = new HashMap();
        
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }
 
        if (l_strUpdateOrderOpenStatus != null)
        {
        	l_mapStatus.put("order_open_status", l_strUpdateOrderOpenStatus);
        }
        
		try
		{   
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			
			//スレッド番号を元に生成したPrimaryKeyオブジェクト 
			AioOrderUnitPK l_aioOrderUnitPK = new AioOrderUnitPK(l_lngOrderUnitId);
			l_queryProcessor.doUpdateQuery(l_aioOrderUnitPK, l_mapStatus);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get外株注文単位テーブルレコード)<BR>
     * 外株注文単位テーブルから検索条件データを元にレコードを検索する。<BR>
     * <BR>
     * 外株注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
     * 取得したRowオブジェクトを返却する。<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * （BUSINESS_ERROR_01037）<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID。<BR>
     * @@return FeqOrderUnitRow
     * @@throws WEB3BaseException
     * @@roseuid 444F40E101CE
     */
    private FeqOrderUnitRow getFeqOrderUnitTableRecord(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitTableRecord" + "(long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = null;
        try
        {
            l_feqOrderUnitRow = FeqOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        if (l_feqOrderUnitRow == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitRow;
    }

    /**
     * (update外株注文単位テーブルレコード)<BR>
     * 外株注文単位テーブルの更新処理をおこなう。<BR>
     * <BR>
     * （引数）注文単位IDをキーとして以下内容で外株注文単位テーブルのUPDATEを行う。<BR>
     * <BR>
     * １） （引数）更新_注文状態 != nullの場合、<BR>
     * ・注文状態：（引数）更新_注文状態<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID。<BR>
     * @@param l_strUpdateOrderStatus - (更新_注文状態)<BR>
     * 更新_注文状態。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4451C7E80111
     */
    private void updateFeqOrderUnitTableRecord(long l_lngOrderUnitId,
        String l_strUpdateOrderStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateFeqOrderUnitTableRecord"
            + "(long, String)";
        log.entering(STR_METHOD_NAME);

        Map l_mapStatus = new HashMap();

        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                FeqOrderUnitPK l_feqOrderUnitPK = new FeqOrderUnitPK(l_lngOrderUnitId);
                l_queryProcessor.doUpdateQuery(l_feqOrderUnitPK, l_mapStatus);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get投信注文単位テーブルレコード (long 注文単位ID))<BR>
     * 投信注文単位テーブルから検索条件データを元にレコードを検索する。<BR>
     * <BR>
     * 投信注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
     * 取得したRowオブジェクトを返却する。<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * （BUSINESS_ERROR_01037））<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@return MutualFundOrderUnitRow
     * @@throws WEB3BaseException
     */
    private MutualFundOrderUnitRow getMutualFundOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMutualFundOrderUnitTableRecord(long)";
        log.entering(STR_METHOD_NAME);

        MutualFundOrderUnitRow l_mutualFundOrderUnitRow = null;
        try
        {
            // 投信注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
            // 取得したRowオブジェクトを返却する。
            l_mutualFundOrderUnitRow =
                MutualFundOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        // ※検索結果が0件の場合、エラーを返却する。
        // エラーメッセージ「条件に該当するデータが存在しない。」
        // （BUSINESS_ERROR_01037））
        if (l_mutualFundOrderUnitRow == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitRow;
    }

    /**
     * (update投信注文単位テーブルレコード)<BR>
     * 投信注文単位テーブルの更新処理をおこなう。<BR>
     * <BR>
     * （引数）注文単位IDをキーとして以下内容で投信注文単位テーブルのUPDATEを行う。<BR>
     * <BR>
     * １） （引数）更新_注文状態 != nullの場合、<BR>
     * ・注文状態：（引数）更新_注文状態<BR>
     * ※ （引数）更新_注文状態 == nullの場合、<BR>
     * 　@　@注文状態：更新不要（既存値）<BR>
     * <BR>
     * ２） （引数）更新_注文有効状態 != nullの場合、<BR>
     * ・注文有効状態：（引数）更新_注文有効状態<BR>
     * ※ （引数）更新_注文有効状態 == nullの場合、<BR>
     * 　@　@注文有効状態：更新不要（既存値）<BR>
     * <BR>
     * ３） （引数）更新_約定状態 != nullの場合、<BR>
     * ・約定状態：（引数）更新_約定状態<BR>
     * ※ （引数）更新_約定状態 == nullの場合、<BR>
     * 　@　@約定状態：更新不要（既存値）<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_strUpdateOrderStatus - (更新_注文状態)<BR>
     * 更新_注文状態。<BR>
     * @@param l_strOrderOpenStatus - (更新_注文有効状態)<BR>
     * 更新_注文有効状態<BR>
     * @@param l_strExecStatus - (更新_約定状態)<BR>
     * 更新_約定状態<BR>
     * @@throws WEB3BaseException
     */
    private void updateMutualFundOrderUnit(long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strExecStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateMutualFundOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //（引数）注文単位IDをキーとして以下内容で投信注文単位テーブルのUPDATEを行う。
        Map l_mapStatus = new HashMap();

        //１） （引数）更新_注文状態 != nullの場合、
        //・注文状態：（引数）更新_注文状態
        //※ （引数）更新_注文状態 == nullの場合、
        //注文状態：更新不要（既存値）
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //２） （引数）更新_注文有効状態 != nullの場合、
        //・注文有効状態：（引数）更新_注文有効状態
        //※ （引数）更新_注文有効状態 == nullの場合、
        //注文有効状態：更新不要（既存値）
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //３） （引数）更新_約定状態 != nullの場合、
        //・約定状態：（引数）更新_約定状態
        //※ （引数）更新_約定状態 == nullの場合、
        //約定状態：更新不要（既存値）
        if (l_strExecStatus != null)
        {
            l_mapStatus.put("exec_status", l_strExecStatus);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            MutualFundOrderUnitPK l_mutualFundOrderUnitPK =
                new MutualFundOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_mutualFundOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get株式注文単位テーブルレコード)<BR>
     * 株式注文単位テーブルから検索条件データを元にレコードを検索する。<BR>
     * <BR>
     * 株式注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
     * 取得したRowオブジェクトを返却する。<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * （BUSINESS_ERROR_01037）<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID。<BR>
     * @@return EqtypeOrderUnitRow
     * @@throws WEB3BaseException
     */
    private EqtypeOrderUnitRow getEqtypeOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getEqtypeOrderUnitTableRecord(long)";
       log.entering(STR_METHOD_NAME);

       EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
       try
       {
    	   l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
       }
       catch (DataQueryException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       catch (DataNetworkException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       if (l_eqtypeOrderUnitRow == null)
       {
           log.debug("条件に該当するデータが存在しない。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01037,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "条件に該当するデータが存在しない。");
       }

       log.exiting(STR_METHOD_NAME);
       return l_eqtypeOrderUnitRow;
   }

    /**
     * (update株式注文単位テーブルレコード)<BR>
     * 株式注文単位テーブルの更新処理をおこなう。<BR>
     * <BR>
     * （引数）注文単位IDをキーとして以下内容で株式注文単位テーブルのUPDATEを行う。<BR>
     * <BR>
     * １） （引数）更新_注文状態 != nullの場合、<BR>
     * ・注文状態：（引数）更新_注文状態<BR>
     * ※ （引数）更新_注文状態 == nullの場合、<BR>
     * 　@　@注文状態：更新不要（既存値）<BR>
     * <BR>
     * ２） （引数）更新_注文有効状態 != nullの場合、<BR>
     * ・注文有効状態：（引数）更新_注文有効状態<BR>
     * ※ （引数）更新_注文有効状態 == nullの場合、<BR>
     * 　@　@注文有効状態：更新不要（既存値）<BR>
     * <BR>
     * ３） （引数）更新_発注日 != nullの場合、<BR>
     * ・発注日：（引数）更新_発注日<BR>
     * ※ （引数）更新_発注日 == nullの場合、<BR>
     * 　@　@発注日：更新不要（既存値）<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_strUpdateOrderStatus - (更新_注文状態)<BR>
     * 更新_注文状態。<BR>
     * @@param l_strOrderOpenStatus - (更新_注文有効状態)<BR>
     * 更新_注文有効状態<BR>
     * @@param l_strUpdateOrderBizDate - (更新_発注日)<BR>
     * 更新_発注日<BR>
     * @@throws WEB3BaseException
     */
    private void updateEqtypeOrderUnit(
		long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEqtypeOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //（引数）注文単位IDをキーとして以下内容で株式注文単位テーブルのUPDATEを行う。
        Map l_mapStatus = new HashMap();

        //１） （引数）更新_注文状態 != nullの場合、
        //・注文状態：（引数）更新_注文状態
        //※ （引数）更新_注文状態 == nullの場合、
        //注文状態：更新不要（既存値）
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //２） （引数）更新_注文有効状態 != nullの場合、
        //・注文有効状態：（引数）更新_注文有効状態
        //※ （引数）更新_注文有効状態 == nullの場合、
        //注文有効状態：更新不要（既存値）
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //３） （引数）更新_発注日 != nullの場合、
        //・発注日：（引数）更新_約定状態
        //※ （引数）更新_発注日 == nullの場合、
        //発注日：更新不要（既存値）
        if (l_strUpdateOrderBizDate != null)
        {
            l_mapStatus.put("biz_date", l_strUpdateOrderBizDate);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            EqtypeOrderUnitPK l_eqtypeOrderUnitPK =
                new EqtypeOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_eqtypeOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get先物OP注文単位テーブルレコード)<BR>
     * 先物OP注文単位テーブルから検索条件データを元にレコードを検索する。<BR>
     * <BR>
     * 先物OP注文単位テーブルから、（引数）注文単位IDをキーとして検索を行い、<BR>
     * 取得したRowオブジェクトを返却する。<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」<BR>
     * （BUSINESS_ERROR_01037）<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID。<BR>
     * @@return IfoOrderUnitRow
     * @@throws WEB3BaseException
     */
    private IfoOrderUnitRow getIfoOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getIfoOrderUnitTableRecord(long)";
       log.entering(STR_METHOD_NAME);

       IfoOrderUnitRow l_ifoOrderUnitRow = null;
       try
       {
    	   l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
       }
       catch (DataQueryException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       catch (DataNetworkException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       if (l_ifoOrderUnitRow == null)
       {
           log.debug("条件に該当するデータが存在しない。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01037,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "条件に該当するデータが存在しない。");
       }

       log.exiting(STR_METHOD_NAME);
       return l_ifoOrderUnitRow;
   }

    /**
     * (update先物OP注文単位テーブルレコード)<BR>
     * 先物OP注文単位テーブルの更新処理をおこなう。<BR>
     * <BR>
     * （引数）注文単位IDをキーとして以下内容で株式注文単位テーブルのUPDATEを行う。<BR>
     * <BR>
     * １） （引数）更新_注文状態 != nullの場合、<BR>
     * ・注文状態：（引数）更新_注文状態<BR>
     * ※ （引数）更新_注文状態 == nullの場合、<BR>
     * 　@　@注文状態：更新不要（既存値）<BR>
     * <BR>
     * ２） （引数）更新_注文有効状態 != nullの場合、<BR>
     * ・注文有効状態：（引数）更新_注文有効状態<BR>
     * ※ （引数）更新_注文有効状態 == nullの場合、<BR>
     * 　@　@注文有効状態：更新不要（既存値）<BR>
     * <BR>
     * ３） （引数）更新_発注日 != nullの場合、<BR>
     * ・発注日：（引数）更新_発注日<BR>
     * ※ （引数）更新_発注日 == nullの場合、<BR>
     * 　@　@発注日：更新不要（既存値）<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_strUpdateOrderStatus - (更新_注文状態)<BR>
     * 更新_注文状態。<BR>
     * @@param l_strOrderOpenStatus - (更新_注文有効状態)<BR>
     * 更新_注文有効状態<BR>
     * @@param l_strUpdateOrderBizDate - (更新_発注日)<BR>
     * 更新_発注日<BR>
     * @@throws WEB3BaseException
     */
    private void updateIfoOrderUnit(
		long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateIfoOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //（引数）注文単位IDをキーとして以下内容で株式注文単位テーブルのUPDATEを行う。
        Map l_mapStatus = new HashMap();

        //１） （引数）更新_注文状態 != nullの場合、
        //・注文状態：（引数）更新_注文状態
        //※ （引数）更新_注文状態 == nullの場合、
        //注文状態：更新不要（既存値）
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //２） （引数）更新_注文有効状態 != nullの場合、
        //・注文有効状態：（引数）更新_注文有効状態
        //※ （引数）更新_注文有効状態 == nullの場合、
        //注文有効状態：更新不要（既存値）
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //３） （引数）更新_発注日 != nullの場合、
        //・発注日：（引数）更新_約定状態
        //※ （引数）更新_発注日 == nullの場合、
        //発注日：更新不要（既存値）
        if (l_strUpdateOrderBizDate != null)
        {
            l_mapStatus.put("biz_date", l_strUpdateOrderBizDate);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            IfoOrderUnitPK l_ifoOrderUnitPK =
                new IfoOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_ifoOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate更新_発注日)<BR>
     * １）　@WEB3StringTypeUtility.isEmpty(更新_発注日)  == false　@の場合、<BR>
     * 　@　@　@　@　@以下のチェックを行う。<BR>
     * 　@１－１）WEB3DateUtility.getDate(更新_発注日, "yyyyMMdd") == null の場合、<BR>
     * 　@　@　@　@　@　@　@　@「発注日が正しい日付ではありません。」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@tag: BUSINESS_ERROR_03109<BR>
     * 　@１－２）更新_発注日が営業日以外の日付であった場合、<BR>
     * 　@　@　@　@　@　@　@　@「発注日は営業日ではありません。」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@（BUSINESS_ERROR_02019）<BR>
     * 　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@tag: BUSINESS_ERROR_02019<BR>
     * @@param l_strUpdateOrderBizDate - (更新_発注日)<BR>
     * 更新_発注日<BR>
     * @@throws WEB3BaseException
     */
    private void validateUpdateOrderBizDate(String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validateUpdateOrderBizDate(String)";
        log.entering(STR_METHOD_NAME);

        //WEB3StringTypeUtility.isEmpty(更新_発注日)  == false　@の場合、
        //以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(l_strUpdateOrderBizDate))
        {
        	//WEB3DateUtility.getDate(更新_発注日, "yyyyMMdd") == null の場合、
            //「発注日が正しい日付ではありません。」の例外をスローする。
            if (WEB3DateUtility.getDate(
        		l_strUpdateOrderBizDate,
        		WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
            {
                log.debug("発注日が正しい日付ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03109,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注日が正しい日付ではありません。");
            }

            //更新_発注日が営業日以外の日付であった場合、
            //「発注日は営業日ではありません。」の例外をスローする。（BUSINESS_ERROR_02019）
            String l_strFlag = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(WEB3DateUtility.getDate(
            		l_strUpdateOrderBizDate,
            		WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag))
            {
            	log.debug("発注日は営業日ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注日は営業日ではありません。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
