head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算方法@変更サービスImplクラス(WEB3AdminTPChangeAssetEvalDivServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeAssetEvalDivService;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力計算方法@変更サービスImplクラス)
 *
 * 余力計算方法@変更（預り証券評価制区分変更）のインターフェースの実装クラス。
 * 汎用クライアントリクエストサービスクラスを拡張。（現在未使用）
 */
public class WEB3AdminTPChangeAssetEvalDivServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPChangeAssetEvalDivService
{
   /**
    *
    * ログ。
    */
    private final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeAssetEvalDivServiceImpl.class);

   /**
    * @@roseuid 41DBC9CD0234
    */
   public WEB3AdminTPChangeAssetEvalDivServiceImpl()
   {

   }

   /**
    * 余力計算方法@変更処理を行う。
    *
    * リクエストデータの型により、以下のメソッドを呼び分ける。
    *
    * ○余力計算方法@変更入力リクエストの場合
    * 　@this.get余力計算方法@変更入力画面()メソッドをコールする。
    *
    * ○余力計算方法@変更確認リクエストの場合
    * 　@this.validate余力計算方法@変更()メソッドをコールする。
    *
    * ○余力計算方法@変更完了リクエストの場合
    * 　@this.submit余力計算方法@変更()メソッドをコールする。
    * @@param l_request - リクエスト
    *
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B8072E0352
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPChangeAssetEvalDivInputRequest)
       {
           l_response =  this.getChangeAssetEvalDivInputInfo((WEB3AdminTPChangeAssetEvalDivInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeAssetEvalDivConfirmRequest)
       {
           l_response = this.validateChangeAssetEvalDiv((WEB3AdminTPChangeAssetEvalDivConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeAssetEvalDivCompleteRequest)
       {
           l_response =  this.submitChangeAssetEvalDiv((WEB3AdminTPChangeAssetEvalDivCompleteRequest)l_request);
       }
       else
       {
           //予期せぬエラー
           throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
       }
       log.exiting(METHOD_NAME);
       return l_response;


   }

   /**
    * (get余力計算方法@変更入力画面)
    * 余力計算方法@変更入力画面作成処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・信用顧客でない（現物客である）
    *
    * ３）this.get顧客余力条件Params(顧客ID)を呼ぶ.
    *
    * ４）余力計算方法@変更入力画面レスポンスを作成し
    * プロパティに値をセットする。
    *
    * 預り証券評価区分 = 顧客余力条件Params.get預り証券評価区分
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 余力計算方法@変更入力画面リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse
    * @@roseuid 41B8FB970058
    */
   protected WEB3AdminTPChangeAssetEvalDivInputResponse getChangeAssetEvalDivInputInfo(WEB3AdminTPChangeAssetEvalDivInputRequest l_request)
   {
       final String METHOD_NAME = "getChangeAssetEvalDivInputInfo";
       WEB3AdminTPChangeAssetEvalDivInputResponse l_response = new WEB3AdminTPChangeAssetEvalDivInputResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);

           //・信用顧客の場合エラー
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }

           //３）this.get顧客余力条件Params(顧客ID)を呼ぶ.
           TradingpowerCalcConditionRow l_row = this.getTradingPowerCalcConditionParams(l_account.getAccountId());
           //余力計算方法@変更入力画面レスポンスを作成し
           //プロパティに値をセットする。
           l_response.accountName = l_account.getDisplayAccountName();
           l_response.assetEvalDiv = l_row.getAssetEvaluationDiv();
           //レスポンスを返却する。

       }
       catch(Exception e)
       {
           //カスタマイズ定義済みエラーの場合
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //予期せぬエラー
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_response.errorInfo.error_message);
           //log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }
       return l_response;

   }

   /**
    * (validate余力計算方法@変更)
    * 余力計算方法@変更確認処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・信用顧客でない（現物客である）
    *
    * ３）余力計算方法@変更確認レスポンスを作成する。
    *
    * ４）レスポンスを返却する。
    * @@param l_request - 余力計算方法@変更確認リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRespons
    * e
    * @@roseuid 41C1009A01E1
    */
   protected WEB3AdminTPChangeAssetEvalDivConfirmResponse validateChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeAssetEvalDiv";
       WEB3AdminTPChangeAssetEvalDivConfirmResponse l_response = new WEB3AdminTPChangeAssetEvalDivConfirmResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);

           //・信用顧客の場合エラー
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }
       }
       catch(Exception e)
       {
           //カスタマイズ定義済みエラーの場合
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //予期せぬエラー
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }
       return l_response;

   }

   /**
    * (submit余力計算方法@変更)
    * 余力計算方法@変更完了処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・信用顧客でない（現物客である）
    * ・暗証番号
    *
    * ３）this.update顧客余力条件Params()を呼ぶ.
    *
    * 引数に以下を与える。
    * ・顧客ID = ２）で取得した顧客
    * ・預り証券評価区分 = リクエスト.預り証券評価区分
    * ・更新日時=ThreadLocalSystemRegistry.getAttribute(取引管理.TIMESTAMP_TAG)の戻り?
    *
    *
    * ４）余力計算方法@変更完了レスポンスを作成し
    * プロパティに値をセットする。
    *
    * 更新日時 = ３）でDBにセットした更新日時
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 余力計算方法@変更完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRespon
    * se
    * @@roseuid 41B8FBD103C3
    */
   protected WEB3AdminTPChangeAssetEvalDivCompleteResponse submitChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)";
       WEB3AdminTPChangeAssetEvalDivCompleteResponse l_response = new WEB3AdminTPChangeAssetEvalDivCompleteResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);

           //・信用顧客の場合エラー
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }

           //・暗証番号
           l_admin.validateTradingPassword(l_request.adminPassword);

           //３）this.update顧客余力条件Params()を呼ぶ.
           Timestamp l_timestamp = this.updateTradingpowerCalcConditionParams(l_admin, l_account.getAccountId(), l_request.assetEvalDiv);

           //追加
           //顧客余力計算条件テーブルをアップデートした後、
           //余力計算キューテーブルに当該顧客レコードを１件
           //インサートする処理を追加する。
           WEB3TPTradingPowerReCalcService l_service = (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);           
           WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);               
           l_service.reCalcTradingPower(l_subAccount);           
           
           //４）余力計算方法@変更完了レスポンスを作成し
           //プロパティに値をセットする。
           l_response.lastUpdatedTime = l_timestamp;

       }
       catch(Exception e)
       {
           //カスタマイズ定義済みエラーの場合
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //予期せぬエラー
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }

       return l_response;

   }

   /**
    * (update顧客余力条件Params)
    * 顧客余力条件テーブルを以下の条件、値で更新する。
    *
    * 条件：
    * ・顧客ID = 引数.顧客ID
    *
    * 値：
    * ・預り証券評価制区分 = 引数.預り証券評価制区分
    * ・更新日時 =
    * ThreadLocalSystemRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)の戻り値
    * @@param l_lngAccountId - (顧客ID)
    * @@param l_strAssetEvalDiv - (預り証券評価制区分)
    * @@return Timestamp
    * @@roseuid 41C7E6790395
    */
   protected Timestamp updateTradingpowerCalcConditionParams(WEB3Administrator l_admin, long l_lngAccountId, String l_strAssetEvalDiv)
    throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "updateTradingpowerCalcConditionParams(long l_lngAccountId, String l_strAssetEvalDiv)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
	       final String l_strWhere = new String("account_id = ?");
	       final Object[] l_bindVars = new Object[]{new Long(l_lngAccountId)};

	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	       }

	       Map l_updateMap = new HashMap();
	       l_updateMap.put("asset_evaluation_div", l_strAssetEvalDiv);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

	       log.debug("asset_evaluation_div=" + l_updateMap.get("asset_evaluation_div"));
	       log.debug("last_updater=" + l_updateMap.get("last_updater"));
	       log.debug("last_updated_timestamp=" + l_updateMap.get("last_updated_timestamp"));

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	        l_intUdateCount = l_qp.doUpdateAllQuery(TradingpowerCalcConditionRow.TYPE, l_strWhere, l_bindVars, l_updateMap);


       }
       //DBAccessエラーの場合
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       catch(Exception e)
       {
           log.error(e.getMessage(), e);
           //予期しないエラー
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME);
       }

       if(l_intUdateCount == 0)
       {
           //TODO ErrorInfo定義確認　@SYSTEM_ERROR_80005（該当データなし）でOK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;

    }

   /**
    * (get顧客余力条件Params)
    * 顧客余力条件テーブルより、以下の条件でレコードを検索し結果を返す。
    *
    * 条件：
    * 顧客ID = 顧客.顧客ID
    * @@param l_lngAccountId - (顧客ID)
    * @@return TradingPowerCalcConditionRow
    * @@roseuid 41C940E50038
    */
   protected TradingpowerCalcConditionRow getTradingPowerCalcConditionParams(long l_lngAccountId)
    throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getTradingPowerCalcConditionParams";
       try
       {
           TradingpowerCalcConditionRow l_row = TradingpowerCalcConditionDao.findRowByAccountId(l_lngAccountId);
           if(l_row != null)
           {
               return l_row;

           }
           //TODO ErrorInfo定義確認　@SYSTEM_ERROR_80005（該当データなし）でOK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo定義確認　@SYSTEM_ERROR_80005（該当データなし）でOK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       //DBAccessエラーの場合
       catch(DataException de)
       {
           log.error(METHOD_NAME + de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

}
@
