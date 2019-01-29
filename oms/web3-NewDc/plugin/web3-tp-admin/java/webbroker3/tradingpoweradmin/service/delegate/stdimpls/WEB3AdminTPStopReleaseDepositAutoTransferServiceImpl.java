head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録/解除サービスImplクラス(WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopDao;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopPK;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopParams;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDepositAutoTransferStopRegistDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProcessManagementIdDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPDepositAutoTransferStopInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPStopReleaseDepositAutoTransferService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 保証金自動振替停止登録/解除サービスImplクラス
 */
public class WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPStopReleaseDepositAutoTransferService
{
    /**
     * ログ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlServiceImpl.class);

   /**
    * @@roseuid 41DBCAAE005F
    */
   public WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl()
   {

   }

   /**
    * 保証金自動振替停止登録/解除処理を行う。
    *
    * リクエストデータの型により、以下のメソッドを呼び分ける。
    *
    * ○保証金自動振替停止登録入力画面リクエストの場合
    * 　@this.get保証金自動振替停止登録入力画面()メソッドをコールする。
    *
    * ○保証金自動振替停止登録確認リクエストの場合
    * 　@this.validate保証金自動振替停止登録()メソッドをコールする。
    *
    * ○保証金自動振替停止登録完了リクエストの場合
    * 　@this.submit保証金自動振替停止登録()メソッドをコールする。
    *
    * ○保証金自動振替停止顧客検索リクエストの場合
    * 　@this.get保証金自動振替停止顧客検索結果()メソッドをコールする。
    *
    * ○保証金自動振替停止解除確認リクエストの場合
    * 　@this.validate保証金自動振替停止解除()メソッドをコールする。
    *
    * ○保証金自動振替停止登録完了リクエストの場合
    * 　@this.submit保証金自動振替停止解除()メソッドをコールする。
    * @@param l_request - リクエスト
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41BE539902FB
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPStopDepositAutoTransferInputRequest)
       {
           l_response =  this.getDepositAutoTransferInputInfo((WEB3AdminTPStopDepositAutoTransferInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPStopDepositAutoTransferConfirmRequest)
       {
           l_response = this.validateStopDepositAutoTransfer((WEB3AdminTPStopDepositAutoTransferConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPStopDepositAutoTransferCompleteRequest)
       {
           l_response =  this.submitStopDepositAutoTransfer((WEB3AdminTPStopDepositAutoTransferCompleteRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPFindDepositAutoTransferStopRequest)
       {
           l_response =  this.findDepositAutoTransferStops((WEB3AdminTPFindDepositAutoTransferStopRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPReleaseDepositAutoTransferConfirmRequest)
       {
           l_response = this.validateReleaseDepositAutoTransfer((WEB3AdminTPReleaseDepositAutoTransferConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPReleaseDepositAutoTransferCompleteRequest)
       {
           l_response =  this.submitReleaseDepositAutoTransfer((WEB3AdminTPReleaseDepositAutoTransferCompleteRequest)l_request);
       }
       else
       {
           log.error(l_request, "Unknown Request=."+ l_request.getClass().getName(), null, new IllegalArgumentException());
           //予期せぬエラー
           throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
       }
       log.exiting(METHOD_NAME);
       return l_response;
   }

   /**
    * (get保証金自動振替停止登録入力画面)
    * 保証金自動振替停止登録入力画面取得処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在チェック
    *・信用顧客チェック
    *
    * ３）保証金自動振替停止登録入力画面レスポンスを作成し
    * プロパティに値をセットする。
    *
    * ・部店コード = 顧客.get部店コード()
    * ・顧客コード = 顧客.get顧客コード()
    * ・顧客名 = 顧客.get顧客名()
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止登録入力画面リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResp
    * onse
    * @@roseuid 41BE51F202DC
    */
   protected WEB3AdminTPStopDepositAutoTransferInputResponse getDepositAutoTransferInputInfo(WEB3AdminTPStopDepositAutoTransferInputRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPStopDepositAutoTransferInputResponse l_response = new WEB3AdminTPStopDepositAutoTransferInputResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・顧客の存在チェック
           //・信用顧客チェック
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //３）保証金自動振替停止登録入力画面レスポンスを作成し
           //プロパティに値をセットする。
           l_response.accountName = l_account.getDisplayAccountName();

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
    * (validate保証金自動振替停止登録)
    * 保証金自動振替停止登録確認処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    *・管理者部店権限
    * ・顧客の存在、信用顧客チェック
    * ・対象顧客が既に保証金自動振替停止設定が行われているかどうかチェック。
    * this.validate既登録()を呼ぶ。
    * ・停止期間のチェック
    * this.validate停止期間()を呼ぶ
    *
    * ３）保証金自動振替停止登録確認レスポンスを作成する。
    *
    * ４）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止解除完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRe
    * sponse
    * @@roseuid 41C90D5A0038
    */
   protected WEB3AdminTPStopDepositAutoTransferConfirmResponse validateStopDepositAutoTransfer(WEB3AdminTPStopDepositAutoTransferConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)";

       WEB3AdminTPStopDepositAutoTransferConfirmResponse l_response = new WEB3AdminTPStopDepositAutoTransferConfirmResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・顧客の存在チェック
           //・信用顧客チェック
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //対象顧客が既に保証金自動振替停止設定が行われているかどうかチェック。
           //this.validate既登録()を呼ぶ。
           this.validateAlreadyRegistered(l_account.getAccountId());

           //・停止期間のチェック
           //this.validate停止期間()を呼ぶ
           this.validateStopTerm(
                    l_request.autotransferStopStart,
                    l_request.autotransferStopEnd,
                    l_strInstCode,
                    l_request.branchCode);

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
    * (submit保証金自動振替停止登録)
    * 保証金自動振替停止登録完了処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・顧客の存在、信用顧客チェック
    * ・対象顧客が既に保証金自動振替停止設定が行われているかどうかチェック。
    * this.validate既登録()を呼ぶ。
    * ・停止期間のチェック
    * this.validate停止期間()を呼ぶ
    * ・暗証番号のチェック
    *
    * ３）this.insert保証金自動振替停止Params()を呼ぶ。
    *
    * ４）保証金自動振替停止登録完了レスポンスを作成し
    * レスポンスのプロパティを以下のようにセットする。
    *
    * ・更新日時 = DB更新時にセットした更新日時
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止登録完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteR
    * esponse
    * @@roseuid 41C127B70378
    */
   protected WEB3AdminTPStopDepositAutoTransferCompleteResponse submitStopDepositAutoTransfer(WEB3AdminTPStopDepositAutoTransferCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)";

       WEB3AdminTPStopDepositAutoTransferCompleteResponse l_response = new WEB3AdminTPStopDepositAutoTransferCompleteResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           //・管理者部店権限
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //・顧客の存在チェック
           //・信用顧客チェック
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //対象顧客が既に保証金自動振替停止設定が行われているかどうかチェック。
           //this.validate既登録()を呼ぶ。
           this.validateAlreadyRegistered(l_account.getAccountId());

           //・停止期間のチェック
            //this.validate停止期間()を呼ぶ
            this.validateStopTerm(
                    l_request.autotransferStopStart,
                    l_request.autotransferStopEnd,
                    l_strInstCode,
                    l_request.branchCode);

           //・管理者パスワード
           l_admin.validateTradingPassword(l_request.adminPassword);

           /////////////////////////////////////////////////////////////////////////

           //３）this.insert保証金自動振替停止Params()を呼ぶ。
           Timestamp l_timeStamp =
               this.insertDepositAutoTransferStopParams(
                   l_account,
                   l_admin,
                   l_request.autotransferStopStart,
                   l_request.autotransferStopEnd,
                   WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST);

           //余力制御機@能変更完了レスポンスを作成する。
           l_response.lastUpdatedTime = l_timeStamp;

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01035;
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
    * (get保証金自動振替停止顧客検索結果)
    * 保証金自動振替停止顧客検索処理を行う。
    *
    * １）リクエスト入力項目チェック。

    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客存在、信用顧客チェック
    * ※信用顧客でない場合、一覧対象から外す。
    *
    * ３）this.get保証金自動振替停止Params一覧()を呼ぶ.
    *
    * ４）get保証金自動振替停止Params()の戻り値の要素数分LOOPし停止顧客一覧を作成する
    *
    * ４－１）get顧客()で顧客を取得。
    * 引数：
    * 保証金自動振替停止Params.get顧客ID()
    *
    * ４－２）顧客別保証金自動振替停止登録情報を生成し以下のように属性をセットし、返却
    * する。
    *
    * ・部店コード = 顧客.get部店コード()
    * ・顧客コード = 顧客.get顧客コード()
    * ・顧客名 = 顧客.get顧客名()
    * ・停止開始日 = 保証金自動振替停止Params.get停止開始日()
    * ・停止終了日 = 保証金自動振替停止Params.get停止終了日()
    * ・登録区分 = 保証金自動振替停止Params.get登録区分()
    *
    * ４）保証金自動振替停止検索レスポンスを作成しプロパティに値をセットする。
    *
    * ・停止顧客一覧
    * =　@LOOP処理で作成した顧客別保証金自動停止登録情報オブジェクトの配列
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止登録顧客検索リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRespo
    * nse
    * @@roseuid 41BE58770200
    */
   protected WEB3AdminTPFindDepositAutoTransferStopResponse findDepositAutoTransferStops(WEB3AdminTPFindDepositAutoTransferStopRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPFindDepositAutoTransferStopResponse l_response = new WEB3AdminTPFindDepositAutoTransferStopResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           //・管理者部店権限
           for(int i = 0; i < l_request.branchCodes.length; i++)
           {
               l_admin.validateBranchPermission(l_request.branchCodes[i]);
           }

           // ３）this.get保証金自動振替停止Params一覧()を呼ぶ.
           List l_rows = this.getDepositAutoTransferStopParamsList(l_request, l_admin);

           //４）get保証金自動振替停止Params()の戻り値の要素数分LOOPし停止顧客一覧を作成する
           //４－１）get顧客()で顧客を取得。


           // ４－２）顧客別保証金自動振替停止登録情報を生成し属性を値をセットする。
           if((l_rows == null) || (l_rows.size() < 1))
           {
               l_response.autoTransferStopInfos = new WEB3AdminTPDepositAutoTransferStopInfo[0];
           }
           else
           {
               WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
               
               List l_stopList = new ArrayList();

               int l_intSize = l_rows.size();
	             for(int i = 0; i < l_intSize; i++)
	             {
	                 DepositAutotransferStopRow l_row = (DepositAutotransferStopRow)l_rows.get(i);
	                 log.debug("selected row[" + i + "]" + l_row.toString());

	                 WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
	                 
	                 //信用顧客であった場合
	                 //保証金自動振替顧客一覧に含める。
	                 if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
	                 {
		                 WEB3AdminTPDepositAutoTransferStopInfo l_depositAutoTransferStopInfo = new WEB3AdminTPDepositAutoTransferStopInfo();
		                 l_depositAutoTransferStopInfo.branchCode = l_account.getBranch().getBranchCode();
		                 l_depositAutoTransferStopInfo.accountCode = l_account.getDisplayAccountCode();
		                 l_depositAutoTransferStopInfo.accountName = l_account.getDisplayAccountName();
		                 l_depositAutoTransferStopInfo.autoTransferStopId = String.valueOf(l_row.getDepositAutotransferStopId());
	                     l_depositAutoTransferStopInfo.transferStopStart = l_row.getAutotransferStopStart();
	                     l_depositAutoTransferStopInfo.transferStopEnd = l_row.getAutotransferStopEnd();
		                 l_stopList.add(l_depositAutoTransferStopInfo);

	                 }
	                 //既に信用顧客でない場合
	                 //一覧に含めない。
	                 else
	                 {
	                 	log.debug("保証金自動振替停止登録された顧客が信用顧客でない account_id=" + l_account.getAccountId());
	                 }

	             }
	             
	             l_response.autoTransferStopInfos = (WEB3AdminTPDepositAutoTransferStopInfo[])l_stopList.toArray(new WEB3AdminTPDepositAutoTransferStopInfo[l_stopList.size()]);
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
    * (validate保証金自動振替停止解除)
    * 保証金自動振替停止登録解除確認処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在、信用顧客チェック
    * リクエスト.保証金自動振替停止IDの要素数分LOOPする
    * エラーの場合、処理を中断しエラーレスポンスを返却する。
    *
    * ４）保証金自動振替停止解除確認レスポンスを作成する。
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止解除確認リクエスト
    * @@return 保証金自動振替停止解除確認レスポンス
    * @@roseuid 41BE5206001D
    */
   protected WEB3AdminTPReleaseDepositAutoTransferConfirmResponse validateReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)";

       WEB3AdminTPReleaseDepositAutoTransferConfirmResponse l_response = new WEB3AdminTPReleaseDepositAutoTransferConfirmResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

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

           int i_intSize = l_request.autoTransferStopIds.length;
           for(int i = 0; i < i_intSize; i++)
           {
               DepositAutotransferStopRow l_row = this.getDepositAutoTransferStopParams(Long.parseLong(l_request.autoTransferStopIds[i]));

               //・顧客の存在チェック
               //・管理者部店権限
               //・信用顧客チェック
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

               if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
               {
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
               }


           }
       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01903;
           return l_response;
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
    * (submit保証金自動振替停止解除)
    * 保証金自動振替停止登録解除完了処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在、信用顧客チェック
    * リクエスト.保証金自動振替停止IDの要素数分LOOPする
    * エラーの場合、処理を中断しエラーレスポンスを返却する。
    * ・暗証番号のチェック
    *
    * ３）this.update保証金自動振替停止Params解除()を呼ぶ。
    *  引数として以下を渡す：
    * ・管理者
    * ・保証金自動振替停止ID
    *
    *
    * ４）保証金自動振替停止解除完了レスポンスを作成する。
    * プロパティに以下をセットする。
    *
    * ・更新日時 = DB更新時にセットした更新日時
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 保証金自動振替停止解除完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferComple
    * teResponse
    * @@roseuid 41C127C9005B
    */
   protected WEB3AdminTPReleaseDepositAutoTransferCompleteResponse submitReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)";

       WEB3AdminTPReleaseDepositAutoTransferCompleteResponse l_response = new WEB3AdminTPReleaseDepositAutoTransferCompleteResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

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

           int i_intSize = l_request.autoTransferStopIds.length;
           for(int i = 0; i < i_intSize; i++)
           {
               DepositAutotransferStopRow l_row = this.getDepositAutoTransferStopParams(Long.parseLong(l_request.autoTransferStopIds[i]));

               //・顧客の存在チェック
               //・管理者部店権限
               //・信用顧客チェック
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

               if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
               {
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
               }

           }

           //・管理者パスワード
           l_admin.validateTradingPassword(l_request.adminPassword);

           //３）this.update保証金自動振替停止Params解除()を呼ぶ。
	       Timestamp l_timestamp = null;

           for(int i = 0; i < i_intSize; i++)
           {
               long l_lngTransferStopId = Long.parseLong(l_request.autoTransferStopIds[i]);

               l_timestamp = this.updateDepositAutoTransferStopParams(l_admin, l_lngTransferStopId, null, null, WEB3AdminTPDepositAutoTransferStopRegistDivDef.RELEASE);
           }

           l_response.lastUpdatedTime = l_timestamp;

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01903;
           return l_response;
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
    * (get保証金自動振替停止Params一覧)
    *
    * 保証金自動振替停止テーブルより
    * 現在、または未来の保証金自動振替停止の顧客
    * を検索。
    *
    * 条件：
    * ・当日 =< 停止期間（至）
    * ・登録区分 = ”登録”
    * [リクエスト.顧客指定の場合]
    * 顧客コード指定の場合、部店コードとの組み合わせで
    * ・顧客の存在チェック
    * ・信用顧客チェック
    * にパスした顧客ID
    *
    * [上記以外(部店のみ指定の場合)]
    * ・部店ID = get部店().get部店ID()の戻り値
    * 引数 :
    * 証券会社コード : 管理者.get証券会社コード()
    * 部店コード : リクエスト.部店コード
    *
    * @@return List
    * @@roseuid 41C1284101B3
    */
   protected List getDepositAutoTransferStopParamsList(WEB3AdminTPFindDepositAutoTransferStopRequest l_request, WEB3Administrator l_admin)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "getDepositAutoTransferStopParamsList(WEB3AdminTPFindDepositAutoTransferStopRequest l_request, WEB3Administrator l_admin)";

       StringBuffer l_sbWhere = new StringBuffer();
       List l_lisBindVars = new ArrayList();

       //・登録区分 = ”登録”
       l_sbWhere.append("regist_div = ?");
       l_lisBindVars.add(WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST);

       //・当日 =< 停止期間（至）
       //業務日付を取得する。
       Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();
       l_sbWhere.append(" and autotransfer_stop_end >= ?");
       l_lisBindVars.add(l_bizDate);

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       //顧客コード != nullのとき
       if(l_request.accountCode != null)
       {
           String l_strInstCode = l_admin.getInstitutionCode();

           //顧客が存在　@かつ
           //信用顧客である場合
           //検索条件に加える。
           //上記に該当しない場合はSkipする。（条件に加えない）
           int l_intHitCount = 0;
           int l_intBranchLength = l_request.branchCodes.length;
           for(int i = 0; i < l_intBranchLength; i++)
           {
               try
               {
                   WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCodes[i], l_request.accountCode);

                   //検索された顧客が信用顧客の場合
                   if((l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
                   {
                       //顧客コードと部店コードが1対1で指定されていた場合
                       if(l_intHitCount == 0)
                       {
                           l_sbWhere.append(" and account_id in (?");
                       }
                       else
                       {
                           l_sbWhere.append(",?");
                       }
                       l_intHitCount++;
                       l_lisBindVars.add(new Long(l_account.getAccountId()));
                   }
                   //信用顧客でなかった場合
                   else
                   {
                       log.error("this account was not margin customer. branchCode = " + l_request.branchCodes[i] + " accountCode = " + l_request.accountCode);
                   }

               }
               //顧客の存在チェック,Skipした顧客をログ。
               catch(WEB3BaseException e)
               {
	               log.error(e.getErrorInfo().getErrorMessage() + " branchCode = " + l_request.branchCodes[i] + " accountCode = " + l_request.accountCode);
               }
           }
           if(l_intHitCount > 0)
           {
               l_sbWhere.append(")");
           }
           //顧客が1件もHitしなかった場合、DB検索せず空のリストを返す。
           else
           {
               return Collections.EMPTY_LIST;
           }
       }


       else
       {
           //[部店コード  != nullの場合]
           //・部店ID = get部店().get部店ID()の戻り値
           //引数 :
           //証券会社コード : 管理者.get証券会社コード()
           //部店コード : リクエスト.部店コード
           if(l_request.branchCodes != null)
           {

               l_sbWhere.append(" and branch_id in (");
               for(int i = 0; i < l_request.branchCodes.length; i++)
               {
                   long l_lngBranchId;
                   try
                   {
                       l_lngBranchId = l_accMgr.getBranch(l_admin.getInstitution(), l_request.branchCodes[i]).getBranchId();
                       if(i > 0)
                       {
                           l_sbWhere.append(",");
                       }
                       l_sbWhere.append("?");
                       l_lisBindVars.add(new Long(l_lngBranchId));
                   }
                   catch(NotFoundException nfe)
                   {
                       //該当部店なし
                       throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
                   }

               }
               l_sbWhere.append(") ");
           }

       }

       final String l_strWhere = l_sbWhere.toString();
       final Object[] l_bindVars = l_lisBindVars.toArray();
       log.debug("l_strWhere=" + l_strWhere);
       log.debug("l_bindVars=" + l_bindVars);

       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           return l_qp.doFindAllQuery(DepositAutotransferStopRow.TYPE, l_strWhere, l_bindVars);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }

   }

   /**
    * (get保証金自動振替停止Params)
    *
    * 保証金自動振替停止テーブルより、PK(引数.保証金自動振替停止ID)検索
    * した結果を返す。
    * @@param l_lngDepoistAutoTransferId - 保証金自動振替停止ID
    * @@return DepositAutoTransferStopRow
    * @@roseuid 41D257C200AC
    */
   public DepositAutotransferStopRow getDepositAutoTransferStopParams(long l_lngDepoistAutoTransferId)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getDepositAutoTransferStopParams(long l_lngDepoistAutoTransferId)";
       try
       {
           return DepositAutotransferStopDao.findRowByPk(l_lngDepoistAutoTransferId);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo定義確認　@SYSTEM_ERROR_80005（該当データなし）でOK?
           log.error(METHOD_NAME + " updating data not found l_lngDepoistAutoTransferId=" + l_lngDepoistAutoTransferId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }

   }

   /**
    * (insert保証金自動振替停止Params)
    *
    * 保証金自動振替停止Paramsを作成、プロパティを以下のようにセットし、
    * 保証金自動振替停止テーブルに引数で渡された登録内容のレコードを追加する。
    *
    * ・保証金自動振替停止ID = xTradeで自動生成
    * ・顧客ID = 顧客.get顧客ID()
    * ・停止期間(自) = 引数.停止開始日()
    * ・停止期間(至) = 引数.停止終了日()
    * ・登録区分 = 引数.登録区分
    * ・更新者コード = 管理者.get管理者コード()
    * ・更新日時 = ThreadLocalSystemRegistry.getAttribute(取引時間.TIMESTAMP_TAG)
    * @@param l_account - 顧客
    * @@param l_admin - 管理者
    * @@param l_autoTransferStopStart - 停止開始日
    * @@param l_autoTransferStopEnd - 停止終了日
    * @@param l_strRegistDiv - 登録区分
    * @@return Timestamp
    * @@roseuid 41BE51FD0378
    */
   protected Timestamp insertDepositAutoTransferStopParams(MainAccount l_account, WEB3Administrator l_admin, Date l_autoTransferStopStart, Date l_autoTransferStopEnd, String l_strRegistDiv)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "insertDepositAutoTransferStopParams(MainAccount l_account, WEB3Administrator l_admin, Date l_autoTransferStopStart, Date l_autoTransferStopEnd, String l_strRegistDiv)";

       Timestamp l_timestamp = null;

       try
       {
	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	           return null;
	       }

	       long l_lngPK = DepositAutotransferStopDao.newPkValue();
           DepositAutotransferStopParams l_depositAutotransferStopParams =
               new DepositAutotransferStopParams();

           l_depositAutotransferStopParams.setDepositAutotransferStopId(l_lngPK);
           l_depositAutotransferStopParams.setAccountId(l_account.getAccountId());
           l_depositAutotransferStopParams.setBranchId(l_account.getBranch().getBranchId());
           l_depositAutotransferStopParams.setRegistDiv(l_strRegistDiv);
           l_depositAutotransferStopParams.setAutotransferStopStart(l_autoTransferStopStart);
           l_depositAutotransferStopParams.setAutotransferStopEnd(l_autoTransferStopEnd);
           l_depositAutotransferStopParams.setLastUpdater(l_admin.getAdministratorCode());
           l_depositAutotransferStopParams.setLastUpdatedTimestamp(l_timestamp);
           l_depositAutotransferStopParams.setCreatedTimestamp(l_timestamp);

           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_qp.doInsertQuery(l_depositAutotransferStopParams);
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

       return l_timestamp;



   }

   /**
    * (update保証金自動振替停止Params)
    * 保証金自動振替停止テーブルを以下の条件、値で更新する。
    *
    * 条件：
    * 保証金自動振替停止ID = 引数.保証金自動振替停止ID
    *
    * 値：（各引数の値がnullでない場合、以下のようにセットする。）
    * ・停止期間(自) = 引数.停止開始日
    * ・停止期間(至) = 引数.停止終了日
    * ・登録区分 = 引数.登録区分
    * ・更新者コード = 管理者.get管理者コード()
    * ・更新日時 = ThreadLocalSystemRegistry.getAttribute(取引時間.TIMESTAMP_TAG)
    * @@param l_admin - 管理者
    * @@param l_lngDepositAutoTransferStopId - 保証金自動振替停止ID
    * @@param l_datDepositAutoTransferStopStart - 停止開始日
    * @@param l_datDepositAutoTransferStopEnd - 停止終了日
    * @@param l_strRegistDiv - 登録区分
    * @@return Timestamp
    * @@roseuid 41BE521700D8
    */
   protected Timestamp updateDepositAutoTransferStopParams(WEB3Administrator l_admin, long l_lngDepositAutoTransferStopId, Date l_datDepositAutoTransferStopStart, Date l_datDepositAutoTransferStopEnd, String l_strRegistDiv)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "updateDepositAutoTransferStopParams(WEB3Administrator l_admin, long l_lngDepositAutoTransferStopId, Date l_datDepositAutoTransferStopStart, Date l_datDepositAutoTransferStopEnd, String l_strRegistDiv)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
           l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

           if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	           return null;
	       }

	       Map l_updateMap = new HashMap();
	       //停止開始日、停止終了日については
	       //指定があった場合に値をセット
	       if(l_datDepositAutoTransferStopStart != null)
	       {
		       l_updateMap.put("autotransfer_stop_start", l_datDepositAutoTransferStopStart);
	       }
	       if(l_datDepositAutoTransferStopEnd != null)
	       {
		       l_updateMap.put("autotransfer_stop_end", l_datDepositAutoTransferStopEnd);
	       }
	       l_updateMap.put("regist_div", l_strRegistDiv);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	       l_intUdateCount = l_qp.doUpdateQuery(new DepositAutotransferStopPK(l_lngDepositAutoTransferStopId), l_updateMap);

       }
       //保証金自動振替停止IDが不正な値のとき
       catch(NumberFormatException nfe)
       {
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
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
           log.error(METHOD_NAME + " updating data not found l_lngDepositAutoTransferStopId=" + l_lngDepositAutoTransferStopId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;
    }

   /**
     * (validate停止期間) <BR>
     * 停止期間のチェックを実施する。<BR> 
     * 停止期間が不正だった場合、業務エラーをスローする。 <BR>
     * <BR>
     * １）当日の保証金自動振替処理の実施状況を確認する。 <BR>
     * <BR>
     * 　@１-１）プロセス管理テーブルを検索する。 <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@プロセスID：0005：保証金自動振替終了 <BR>
     * 　@　@　@　@証券会社コード：引数.証券会社コード <BR>
     * 　@　@　@　@部店コード：引数.部店コード <BR>
     * <BR>
     * ２）停止期間をチェックする。 <BR>
     * <BR>
     * 　@２-１）停止期間(至)が、停止期間(自)より過去日付でない。 <BR>
     * 　@　@[a. 引数.停止期間(自) > 引数.停止期間(至) 　@の場合] <BR>
     * 　@　@　@　@業務エラーをスローする。 <BR>
     * <BR>
     * 　@２-２）停止期間（自）が過日でない。 <BR>
     * 　@　@[a.１-１）の検索結果 == null || １-１）の検索結果の要素数 == 0　@の場合]<BR> 
     * <BR>
     * 　@　@　@[b. 当日(*) > 引数.停止期間(自) の場合] <BR>
     * 　@　@　@　@　@業務エラーをスローする。<BR>
     * <BR>
     * 　@　@[a.以外の場合]<BR>
     * <BR>
     * 　@　@　@[b. 当日(*) >= 引数.停止期間(自) の場合] <BR>
     * 　@　@　@　@　@業務エラーをスローする。<BR>
     * <BR>
     * 　@　@(*)当日 = GtlUtils.getTradingSystem().getBizDate() <BR>
     * <BR>
     * @@param l_datStopStart - 停止期間(自)
     * @@param l_datStopEnd - 停止期間(至)
     * @@param l_strInstCode - 証券会社コード
     * @@param l_strBranCode - 部店コード
     */
    protected void validateStopTerm(
            Date l_datStopStart,
            Date l_datStopEnd,
            String l_strInstCode,
            String l_strBranCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStopTerm(Date l_datStopStart, Date l_datStopEnd, String l_strInstCode, String l_strBranCode) ";

        /*
         * プロセス管理テーブルを検索する。
         * 
         * [検索条件]
         * 　@プロセスID：0005：保証金自動振替終了
         * 　@証券会社コード：引数.証券会社コード 
         * 　@部店コード：引数.部店コード 
         */
        //プロセス管理Row
        ProcessManagementRow l_processManagementRow = null;
        try
        {
            //プロセス管理テーブルを検索
            l_processManagementRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3AdminTPProcessManagementIdDivDef.DEPOSIT_AUTOTRANSFER_END,
                    l_strInstCode,
                    l_strBranCode);
        }
        catch(DataFindException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        /*
         * 停止期間をチェックする
         */
        //業務日付を取得する。
        Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();

        log.debug("l_datStopStart="
                + WEB3DateUtility.formatDate(l_datStopStart, "yyyy/MM/dd")
                + " l_datStopEnd="
                + WEB3DateUtility.formatDate(l_datStopEnd, "yyyy/MM/dd")
                + " l_bizDate="
                + WEB3DateUtility.formatDate(l_bizDate, "yyyy/MM/dd"));

        /*
         * 停止期間(至)が、停止期間(自)より過去日付でない。
         */
        if(WEB3DateUtility.compareToDay(l_datStopStart, l_datStopEnd) > 0)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                    STR_METHOD_NAME);
        }

        /*
         * 停止期間（自）が過日でない。
         */
        //プロセス管理Row == null の場合
        if(l_processManagementRow == null)
        {
            //当日(*) > 引数.停止期間(自) の場合
            if(WEB3DateUtility.compareToDay(l_bizDate, l_datStopStart) > 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                        STR_METHOD_NAME);
            }
        }
        //以外（プロセス管理Row != null）の場合
        else
        {
            //当日(*) >= 引数.停止期間(自) の場合
            if(WEB3DateUtility.compareToDay(l_bizDate, l_datStopStart) >= 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                        STR_METHOD_NAME);
            }
        }
    }

   /**
    * (validate既登録)
    * 対象顧客が既に保証金自動振替停止設定が行われているかどうかチェック
    *
    * 保証金自動振替停止テーブルより以下の条件で検索しレコードが存在すればエラー。
    * ・顧客ID = 顧客.get顧客ID()
    * ・登録区分 = ”登録（停止）”
    * ・当日 < 停止期間（至）
    * @@param l_lngAccountId - 顧客ID
    * @@roseuid 41D24C02039A
    */
   protected void validateAlreadyRegistered(long l_lngAccountId) throws WEB3BaseException
   {
       final String METHOD_NAME = "validateAlreadyRegistered(long l_lngAccountId)";
       final String l_strWhere = "account_id = ? and regist_div = ? and autotransfer_stop_end > ?";

       //業務日付を取得する。
       Date l_bizDate0 = GtlUtils.getTradingSystem().getBizDate();

       final Object[] l_bindVars =
       {
               new Long(l_lngAccountId),
               WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST,
               l_bizDate0
       };

       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           List l_rows = l_qp.doFindAllQuery(DepositAutotransferStopRow.TYPE, l_strWhere, l_bindVars);
           if(l_rows.size() > 0)
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01904, METHOD_NAME);
           }
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

}
@
