head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更サービスImplクラス(WEB3AdminTPChangeCalcControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 堀野 和美(FLJ) 新規作成
Revision History : 2007/07/26 趙林鵬(中訊) モデルNo.004,005,006
Revision History : 2007/09/03 孟亞南(中訊) モデルNo.013,014
Revision History : 2007/09/18 孟亞南(中訊) モデルNo.015,017
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionPK;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeCalcControlService;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPIfoAccountOpenDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPCalcControlInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * 余力制御機@能変更サービスImplクラス
 *
 * 余力制御機@能（信用新規建停止、先物OP新規建停止、出金停止、その他商品買付停止に関
 * わる）変更のインターフェースの実装クラス。
 * 汎用クライアントリクエストサービスクラスを拡張。（現在未使用）
 */
public class WEB3AdminTPChangeCalcControlServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPChangeCalcControlService
{
    /**
     * ログ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlServiceImpl.class);

   /**
    * @@roseuid 41DBCA970224
    */
   public WEB3AdminTPChangeCalcControlServiceImpl()
   {

   }

   /**
    * |余力制御機@能変更処理を行う。
    * |
    * |リクエストデータの型により、以下のメソッドを呼び分ける。
    * |
    * |○余力制御機@能検索リクエストの場合
    * |　@this.get余力制御機@能検索結果()メソッドをコールする。
    * |
    * |○余力制御機@能変更確認リクエストの場合
    * |　@this.validate余力制御機@能変更()メソッドをコールする。
    * |
    * |○余力制御機@能変更完了リクエストの場合
    * |　@this.submit余力制御機@能変更()メソッドをコールする。
    * @@param l_request - リクエスト
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DB98740385
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPFindCalcControlRequest)
       {
           l_response =  this.findTradingPowerCalcControls((WEB3AdminTPFindCalcControlRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlInputRequest)
       {
           l_response =  this.getChangeTradingPowerCalcControlInputInfo((WEB3AdminTPChangeCalcControlInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlConfirmRequest)
       {
           l_response = this.validateChangeTradingPowerCalcControl((WEB3AdminTPChangeCalcControlConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlCompleteRequest)
       {
           l_response =  this.submitChangeTradingPowerCalcControl((WEB3AdminTPChangeCalcControlCompleteRequest)l_request);
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
    * (get余力制御機@能検索結果)
    * 余力制御機@能検索処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在チェック（リクエスト.顧客コード != nullの場合）
    *
    * ３）this.get顧客余力条件Params一覧()を呼ぶ.
    * 引数に以下を渡す。
    *
    * ・検索条件 = リクエスト.get検索条件()
    * ・顧客ID =
    * （リクエスト.検索条件 = ”顧客”の場合に取得した顧客）
    *
    * ４）余力機@能制御検索結果レスポンスを作成し
    * プロパティに値をセットする。
    *
    *
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 余力制御機@能検索リクエスト
    * @@return webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse
    * @@roseuid 41B9672D0048
    */
   protected WEB3AdminTPFindCalcControlResponse findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPFindCalcControlResponse l_response = new WEB3AdminTPFindCalcControlResponse();

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

           // ３）this.get顧客余力条件Params一覧()を呼ぶ.
           List l_rows = this.getTradingPowerCalcConditionParamsList(l_request, l_admin);
                      

           //４）余力機@能制御検索結果レスポンスを作成し
           //プロパティに値をセットする。
           if((l_rows == null) || (l_rows.size() < 1))
           {
               l_response.calcControlInfos = new WEB3AdminTPCalcControlInfo[0];
           }
           else
           {
               WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
               
               List l_lisControlInfos = new ArrayList();               
               
               int l_intSize = l_rows.size();
               for(int i = 0; i < l_intSize; i++)
               {
                   TradingpowerCalcConditionRow l_row = (TradingpowerCalcConditionRow)l_rows.get(i);
                   WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
                   
                   //表示対象顧客かどうかチェック
                   if(this.isShowableAccount(l_request, l_account, l_row))
                   {
                       WEB3AdminTPCalcControlInfo l_calcControlInfo = this.createCalcControlInfo(l_account, l_row);
                       l_lisControlInfos.add(l_calcControlInfo);
                       log.debug("追加した顧客の余力制御情報=" + l_calcControlInfo);
                   }	                 
               }
               l_response.calcControlInfos = (WEB3AdminTPCalcControlInfo[])l_lisControlInfos.toArray(new WEB3AdminTPCalcControlInfo[l_lisControlInfos.size()]);           
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
    * (get余力制御機@能変更入力画面)
    * １）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限（※４）処理で顧客を取得する際にチェック）
    *
    * ２）this.get顧客余力条件ParamsByPK()を呼ぶ.
    * 引数に以下を渡す。
    *
    * ・顧客余力条件ID = リクエスト.顧客余力条件ID
    *
    * ３）余力機@能制御検索結果レスポンスを作成し
    * プロパティに値をセットする。
    *
    * ４）レスポンスを返却する。
    * @@param l_request - 余力制御機@能変更入力画面リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse
    * @@roseuid 41CBA13F02F2
    */
   protected WEB3AdminTPChangeCalcControlInputResponse getChangeTradingPowerCalcControlInputInfo(WEB3AdminTPChangeCalcControlInputRequest l_request)
   {
       final String METHOD_NAME = "getChangeTradingPowerCalcControlInputInfo(WEB3AdminTPChangeCalcControlInputRequest l_request)";

       WEB3AdminTPChangeCalcControlInputResponse l_response = new WEB3AdminTPChangeCalcControlInputResponse();

       try
       {
           //１）リクエスト入力項目チェック。
           l_request.validate();

           //２）以下をチェックする。
           //・	管理者権限
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

           // ３）this.get顧客余力条件Params一覧()を呼ぶ.
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);

           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

           //４）余力機@能制御検索結果レスポンスを作成し
           //プロパティに値をセットする。

           if(l_row == null)
           {
               //該当データなし
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
           }
           else
           {
               l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
				WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

				//・管理者部店権限のチェック
				l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());
				l_response.calcControlInfo = this.createCalcControlInfo(l_account, l_row);
				
           }

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01893;
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
    * (validate余力制御機@能変更)
    * 余力制御機@能変更確認処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在
    *
    * ３）余力制御機@能変更確認レスポンスを作成する。
    *
    * ４）レスポンスを返却する。
    * @@param l_request - 余力制御機@能変更確認リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse
    * @@roseuid 41B9673C025B
    */
   protected WEB3AdminTPChangeCalcControlConfirmResponse validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)";

       WEB3AdminTPChangeCalcControlConfirmResponse l_response = new WEB3AdminTPChangeCalcControlConfirmResponse();

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


           //顧客余力条件、
           //・管理者部店権限
           //顧客の存在チェック
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);
           if(l_row == null)
           {
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
               log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, new IllegalArgumentException());
           }
           else
           {
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());
           }

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01893;
           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, nfe);
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
    * (submit余力制御機@能変更)
    * 余力制御機@能変更完了処理を行う。
    *
    * １）リクエスト入力項目チェック。
    *
    * ２）以下をチェックする。
    * ・管理者権限
    * ・管理者部店権限
    * ・顧客の存在
    * ・管理者パスワード
    *
    * ３）this.update顧客余力条件Params()を呼ぶ。
    *
    * ４）余力制御機@能変更完了レスポンスを作成する。
    *
    * ５）レスポンスを返却する。
    * @@param l_request - 余力制御機@能変更完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRespons
    * e
    * @@roseuid 41C0E210025E
    */
   protected WEB3AdminTPChangeCalcControlCompleteResponse submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)";

       WEB3AdminTPChangeCalcControlCompleteResponse l_response = new WEB3AdminTPChangeCalcControlCompleteResponse();

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

           //顧客余力条件、
           //管理者部店権限
           //顧客の存在チェック
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);
           if(l_row == null)
           {
               //該当データなし
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
               log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, new IllegalArgumentException());
               return l_response;
           }

           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
           l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

           //・管理者パスワード
           l_admin.validateTradingPassword(l_request.adminPassword);

           //this.update顧客余力条件Params()を呼ぶ。
           Timestamp l_timeStamp = this.updateTradingPowerCalcConditionParams(l_request, l_admin);

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
    * (get顧客余力条件Params一覧)<BR>
    * 顧客余力条件テーブルから検索した結果を返却する。<BR>
    * <BR>
    * １）リクエスト.顧客コード != nullの場合<BR>
    * 条件：<BR>
    * 顧客ID = 顧客.get顧客ID()<BR>
    * <BR>
    * ２）上記以外の場合<BR>
    * <BR>
    * 条件（以下全てORでつなぐ）：<BR>
    * [余力制御機@能検索.取引停止区分 != nullの場合]<BR>
    * ・取引停止区分 = 余力制御機@能検索.取引停止区分<BR>
    * <BR>
    * [余力制御機@能検索.信用新規建余力区分 != nullの場合]<BR>
    * ・信用新規建余力区分 = 余力制御機@能検索.信用新規建余力区分<BR>
    * <BR>
    * [余力制御機@能検索.先物OP新規建余力区分 != nullの場合]<BR>
    * ・先物OP新規建余力区分 = 余力制御機@能検索.先物OP新規建余力区分<BR>
    * <BR>
    * [余力制御機@能検索.出金余力区分 != nullの場合]<BR>
    * ・出金余力区分 = 余力制御機@能検索.出金余力区分<BR>
    * <BR>
    * [余力制御機@能検索.その他商品買付区分 != nullの場合]<BR>
    * ・その他商品買付区分 = 余力制御機@能検索.その他商品買付余力区分<BR>
    * <BR>
    * [余力制御機@能検索.追証未入金区分 != nullの場合] <BR>
    * ・追証未入金区分 = 余力制御機@能検索.追証未入金区分<BR>
    * <BR>
    * [余力制御機@能検索.部店コード  != nullの場合]<BR>
    * ・部店ID = get部店(, 余力制御機@能検索.部店コード()).get部店ID()の戻り値<BR>
    * 引数 :<BR>
    * 証券会社コード : 管理者.get証券会社コード()<BR>
    * 部店コード : 余力制御機@能検索.部店コード<BR>
    * @@param l_request - 余力制御機@能検索リクエスト<BR>
    * @@param l_account - 顧客<BR>
    * @@param l_admin - 管理者<BR>
    * @@return List
    * @@throws WEB3BaseException
    * @@roseuid 41C0E1FE000D
    */
    protected List getTradingPowerCalcConditionParamsList(WEB3AdminTPFindCalcControlRequest l_request, WEB3Administrator l_admin)
    throws WEB3BaseException
    {
        final String METHOD_NAME = "getTradingPowerCalcConditionParamsList(WEB3AdminTPFindCalcControlRequest)";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
       
        //顧客コード != nullのとき
        if(l_request.accountCode != null)
        {
            String l_strInstCode = l_admin.getInstitutionCode();
	
            //１つでも顧客が検索されれば成功
            //全部見つからなかった場合エラー
            int l_intMissCount = 0;
            int l_intBranchLength = l_request.branchCodes.length;
            boolean l_blnIsFlag = true;

            for(int i = 0; i < l_intBranchLength; i++)
            {
                try
                {           		
                    WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCodes[i], l_request.accountCode);
                    if(l_blnIsFlag)
                    {
                        l_sbWhere.append("account_id in (?");
                        l_blnIsFlag = false;
                    }
                    else
                    {
                        l_sbWhere.append(", ?");		               
                    }
                    l_lisBindVars.add(new Long(l_account.getAccountId()));
                }
                //顧客の存在チェック
                catch(WEB3BaseException e)
                {
                    log.debug("account not found. institution_code = " + l_strInstCode + " branch_code=" + l_request.branchCodes[i] + " account_code=" + l_request.accountCode);
                    l_intMissCount++;
                    log.debug("miss count=" + l_intMissCount + " branch length=" + l_intBranchLength);
                    if(l_intMissCount >= l_intBranchLength)
                    {
                        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01987, METHOD_NAME);
                    }
                }
            }
            //顧客が見つかった場合
            if(l_sbWhere.length() > 0)
            {
                l_sbWhere.append(")");           	
            }
        }
        
        //顧客コードと余力制御情報が指定されていたら
        if ((l_request.accountCode != null)
            && ((l_request.tradingStop != null)
                || (l_request.marginOpenPositionStop != null)
                || (l_request.ifoOpenPositionStop != null)
                || (l_request.paymentStop != null)
                || (l_request.otherTradingStop != null)
                || (l_request.additionalDepositStop != null)))
        {
            l_sbWhere.append(" and ");
        }

        //余力制御情報が指定されていたら
        if ((l_request.tradingStop != null)
            || (l_request.marginOpenPositionStop != null)
            || (l_request.ifoOpenPositionStop != null)
            || (l_request.paymentStop != null)
            || (l_request.otherTradingStop != null)
            || (l_request.additionalDepositStop != null))
        {
            l_sbWhere.append("(");
        }

       //[余力制御機@能検索.取引停止区分 != nullの場合]
       //取引停止区分 = 余力制御機@能検索.取引停止区分
       if(l_request.tradingStop != null)
       {
           l_sbWhere.append("trading_stop = ?");
           l_lisBindVars.add(l_request.tradingStop);
       }
       //[余力制御機@能検索.信用新規建余力区分 != nullの場合]
       //・信用新規建余力区分 = 余力制御機@能検索.信用新規建余力区分
       if(l_request.marginOpenPositionStop != null)
       {
           if(l_request.tradingStop != null)
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("margin_open_position_stop = ?");
           l_lisBindVars.add(l_request.marginOpenPositionStop);
       }
       //[余力制御機@能検索.先物OP新規建余力区分 != nullの場合]
       //・先物OP新規建余力区分 = 余力制御機@能検索.先物OP新規建余力区分
       if(l_request.ifoOpenPositionStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("ifo_open_position_stop = ?");
           l_lisBindVars.add(l_request.ifoOpenPositionStop);
       }
       //余力制御機@能検索.出金余力区分 != nullの場合]
       //・出金余力区分 = 余力制御機@能検索.出金余力区分
       if(l_request.paymentStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null) ||
                   (l_request.ifoOpenPositionStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("payment_stop = ?");
           l_lisBindVars.add(l_request.paymentStop);
       }
       //[余力制御機@能検索.その他商品買付区分 != nullの場合]
       //・その他商品買付区分 = 余力制御機@能検索.その他商品買付余力区分
       if(l_request.otherTradingStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null) ||
                   (l_request.ifoOpenPositionStop != null) ||
                   (l_request.paymentStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("other_trading_stop = ?");
           l_lisBindVars.add(l_request.otherTradingStop);
       }

       //[余力制御機@能検索.追証未入金区分 != nullの場合]
       //・追証未入金区分 = 余力制御機@能検索.追証未入金区分
       if (l_request.additionalDepositStop != null)
       {
           if ((l_request.tradingStop != null)
               || (l_request.marginOpenPositionStop != null)
               || (l_request.ifoOpenPositionStop != null)
               || (l_request.paymentStop != null)
               || (l_request.otherTradingStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("additional_deposit_stop = ?");
           l_lisBindVars.add(l_request.additionalDepositStop);
       }

       //余力制御情報が指定されていたら
       if ((l_request.tradingStop != null)
           || (l_request.marginOpenPositionStop != null)
           || (l_request.ifoOpenPositionStop != null)
           || (l_request.paymentStop != null)
           || (l_request.otherTradingStop != null)
           || (l_request.additionalDepositStop != null))
       {
               l_sbWhere.append(")");
       }

       //[余力制御機@能検索.部店コード  != nullの場合]
       //・部店ID = get部店(, 余力制御機@能検索.部店コード()).get部店ID()の戻り値
       //引数 :
       //証券会社コード : 管理者.get証券会社コード()
       //部店コード : 余力制御機@能検索.部店コード
       if(l_request.branchCodes != null)
       {
           if(l_sbWhere.length() > 0)
           {
               l_sbWhere.append(" and ");
           }
           l_sbWhere.append("branch_id in (");
           for(int i = 0; i < l_request.branchCodes.length; i++)
           {
               long l_lngBranchId;
               try
               {
                   l_lngBranchId = l_accMgr.getBranch(l_admin.getInstitution(), l_request.branchCodes[i]).getBranchId();
                   if(i == 0)
                       l_sbWhere.append("?");
                   else
                       l_sbWhere.append(",?");
                   l_lisBindVars.add(new Long(l_lngBranchId));
               }
               catch(NotFoundException nfe)
               {
                   //該当部店なし
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
               }

           }
           l_sbWhere.append(")");
       }

       final String l_strWhere = l_sbWhere.toString();
       final Object[] l_bindVars = l_lisBindVars.toArray();

       log.debug("l_strWhere=" + l_strWhere);
       log.debug("l_bindVars=" + l_bindVars);
       
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           return l_qp.doFindAllQuery(TradingpowerCalcConditionRow.TYPE, l_strWhere, l_bindVars);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       
    }

   /**
    * (get顧客余力条件Params)
    * 顧客余力条件テーブルより、PK検索した結果を返却する。
    * @@param l_lngTradingPowerCalcConditionId - 顧客余力条件ID
    * @@return TradingPowerCalcConditionRow
    * @@roseuid 41CBAA9D03CC
    */
   protected TradingpowerCalcConditionRow getTradingPowerCalcConditionParams(long l_lngTradingPowerCalcConditionId)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getTradingPowerCalcConditionParams(long l_lngTradingPowerCalcConditionId)";
       try
       {
           return TradingpowerCalcConditionDao.findRowByPk(l_lngTradingPowerCalcConditionId);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo定義確認　@SYSTEM_ERROR_80005（該当データなし）でOK?
           log.error(METHOD_NAME + " updating data not found l_lngTradingPowerCalcConditionId=" + l_lngTradingPowerCalcConditionId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);

       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * (update顧客余力条件Params)<BR>
    * 顧客余力条件テーブルの更新を行う。<BR>
    * <BR>
    * 条件：<BR>
    * PK = リクエスト.顧客余力条件ID<BR>
    * <BR>
    * 値：<BR>
    * ・信用新規建余力区分 = リクエスト.信用新規建余力区分<BR>
    * ・先物OP新規建余力区分 = リクエスト.先物OP新規建余力区分<BR>
    * ・出金余力区分 = リクエスト.出金余力区分<BR>
    * ・その他商品買付余力区分 = リクエストその他商品買付余力区分<BR>
    * ・追証未入金区分 = リクエスト.変更内容.追証未入金区分 <BR>
    * [リクエスト.取引余力区分 = 取引停止解除(0)の場合]<BR>
    * 特別立替金実績 = 0<BR>
    * <BR>
    * @@param l_request - 余力制御機@能変更完了リクエスト
    * @@param l_admin - 管理者
    * @@return Timestamp
    * @@roseuid 41B967440336
    */
   protected Timestamp updateTradingPowerCalcConditionParams(WEB3AdminTPChangeCalcControlCompleteRequest l_request, WEB3Administrator l_admin)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "updateTradingPowerCalcConditionParams(WEB3AdminTPChangeCalcControlCompleteRequest l_request, WEB3Administrator l_admin)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
	       Map l_updateMap = new HashMap();
	       l_updateMap.put("trading_stop", l_request.tradingStop);//追加

           //リクエスト.信用新規建余力区分 != nullの場合
	       if(l_request.marginOpenPositionStop != null)
	       {
		       l_updateMap.put("margin_open_position_stop", l_request.marginOpenPositionStop);
	           
	       }
           //リクエスト.先物OP新規建余力区分 != nullの場合
	       if(l_request.ifoOpenPositionStop != null)
	       {
		       l_updateMap.put("ifo_open_position_stop", l_request.ifoOpenPositionStop);	           
	       }
	       l_updateMap.put("payment_stop", l_request.paymentStop);
	       l_updateMap.put("other_trading_stop", l_request.otherTradingStop);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

           //リクエスト.追証未入金区分 != nullの場合
           if (l_request.additionalDepositStop != null)
           {
               l_updateMap.put("additional_deposit_stop", l_request.additionalDepositStop);
           }

	       //[リクエスト.取引余力区分 = 取引停止解除(0)の場合]
	       //特別立替金実績 = 0
	       if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.ENABLE.equals(l_request.tradingStop))
	       {
	           l_updateMap.put("special_debit_amount", new Double(0));
	       }

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	       l_intUdateCount = l_qp.doUpdateQuery(new TradingpowerCalcConditionPK(l_lngCalcConditionId), l_updateMap);

       }
       //顧客余力条件IDが不正な値のとき
       catch(NumberFormatException nfe)
       {
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
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
           log.error(METHOD_NAME + " updating data not found calcConditionId=" + l_request.calcConditionId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;

    }
   
   /**
    * (create顧客余力制御情報) <BR>
    * <BR>
    * 顧客余力制御情報を作成し、返却する。<BR>
    * <BR>
    * 1)余力制御情報クラスを生成する。 <BR>
    * <BR>
    * 2)余力制御情報クラスに値をセットする。 <BR>
    * 　@2-1)余力制御情報.部店コード = 顧客.get部店コード <BR>
    * 　@2-2)余力制御情報.顧客コード = 顧客.get顧客コード <BR>
    * 　@2-3)余力制御情報.顧客名 = 顧客.get顧客名 <BR>
    * 　@2-4)余力制御情報.顧客余力条件ID = 顧客余力条件.get顧客余力条件ID <BR>
    * 　@2-5)余力制御情報.取引停止区分 = 顧客余力条件.余力停止区分 <BR>
    * 　@2-6)余力制御情報.追証未入金区分 = 顧客余力条件.追証未入金区分 <BR>
    * <BR>
    * 3)顧客.is信用口座開設 <BR>
    * 　@3-1) 0:指定なしの場合 <BR>
    * 　@　@余力制御情報.信用新規建区分 = 顧客余力条件.get信用新規建停止区分 <BR>
    * 　@　@余力制御情報.追証未入金区分 = 顧客余力条件.get追証未入金区分 <BR>
    * 　@3-2) それ以外の場合 <BR>
    * 　@　@余力制御情報.信用新規建区分 = null <BR>
    * 　@　@余力制御情報.追証未入金区分 = null <BR>
    * <BR>
    * 4)is先OP口座開設顧客 <BR>
    * 　@4-1) 口座開設済の場合 <BR>
    * 　@　@　@先物OP新規余力区分 = 顧客余力条件.get先物OP新規建停止区分 <BR>
    * 　@4-2) 口座未開設の場合 <BR>
    * 　@　@　@先物OP新規余力区分 = null <BR>
    * <BR>
    * 5)余力制御情報クラスに値をセットする。 <BR>
    * 　@5-1) 余力制御情報.出金停止区分 = 顧客余力条件.出金停止区分 <BR>
    * 　@5-2) 余力制御情報.その他商品買付区分 = 顧客余力条件.その他商品買付区分 <BR>
    * <BR>
    * 6) 余力制御情報を返却する。 <BR>
    * <BR>
    * @@param l_account - 顧客<BR>
    * (顧客)
    * @@param l_row - 顧客余力条件Params<BR>
    * (顧客余力条件)
    * @@return WEB3AdminTPCalcControlInfo
    */
   private WEB3AdminTPCalcControlInfo createCalcControlInfo(WEB3GentradeMainAccount l_account, TradingpowerCalcConditionRow l_row)
   {
		WEB3AdminTPCalcControlInfo l_calcControlInfo = new WEB3AdminTPCalcControlInfo();
		l_calcControlInfo.branchCode = l_account.getBranch().getBranchCode();
		l_calcControlInfo.accountCode = l_account.getDisplayAccountCode();
		l_calcControlInfo.accountName = l_account.getDisplayAccountName();
		l_calcControlInfo.calcConditionId = String.valueOf(l_row.getCalcConditionId());
		l_calcControlInfo.tradingStop = l_row.getTradingStop();

        //追証未入金区分 = 顧客余力条件Params.get追証未入金区分()
        l_calcControlInfo.additionalDepositStop = l_row.getAdditionalDepositStop();

		if (l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
		{
			l_calcControlInfo.marginOpenPositionStop = l_row.getMarginOpenPositionStop();
            //追証未入金区分 = 顧客余力条件Params.get追証未入金区分()
            l_calcControlInfo.additionalDepositStop = l_row.getAdditionalDepositStop();
		}
		else
		{
		    //何もセットしない。(null)
			l_calcControlInfo.marginOpenPositionStop = null;
            l_calcControlInfo.additionalDepositStop = null;
		}
		
		if(isIfoAccountEstablished(l_account))
		{
			l_calcControlInfo.ifoOpenPositionStop = l_row.getIfoOpenPositionStop();		    
		}
		else
		{
		    //何もセットしない。(null)
			l_calcControlInfo.ifoOpenPositionStop = null;		    		    
		}
		
		l_calcControlInfo.paymentStop = l_row.getPaymentStop();
		l_calcControlInfo.otherTradingStop = l_row.getOtherTradingStop();

		return l_calcControlInfo;
		
   }
   
   /**
    * 先物オプション口座開設顧客かどうか判断する。
    * 開設している場合true, それ以外はfalseを返す。
    * 
    * @@param l_account
    * @@return boolean 
    */
   private boolean isIfoAccountEstablished(WEB3GentradeMainAccount l_account)
   {
		MainAccountRow l_row = (MainAccountRow)l_account.getDataSourceObject();
		
		//大阪、名古屋、東京のいずれかで口座を開設していればtrue
		if((!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivNagoya()))
		        	||
		        (!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivOsaka()))
		        	||
		        (!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivTokyo())))
		{
		            return true;
		    
		}
		return false;
   }
   
   /**
    * (is画面表示顧客)<BR>
    * <BR>
    * 画面に表示するかどうかをチェックする<BR>
    * 信用新規建停止が選択されている場合は信用顧客チェック<BR>
    * オプション新規建停止が選択されている場合はオプション顧客チェック<BR>
    * <BR>
    * 1)余力制御機@能検索リクエスト.信用新規建余力区分のみにチェックがついている場合<BR>
    * 　@信用客でない場合、信用新規建停止区分にfalseを設定する。<BR>
    * <BR>
    * 2)余力制御機@能検索リクエスト.先物OP新規建余力区分のみ選択されている場合<BR>
    * 　@オプション客でない場合、オプション新規建停止区分にfalseを設定する。<BR>
    * <BR>
    * 3)余力制御機@能検索リクエスト.追証未入金区分のみにチェックがついている場合<BR>
    * 　@信用客でない場合、追証未入金区分にfalseを設定する。<BR>
    * <BR>
    * 4)余力制御機@能検索リクエスト.取引停止区分が選択されている場合<BR>
    * 　@余力停止区分にtrueを設定する。<BR>
    * <BR>
    * 5)余力制御機@能検索リクエスト.出金余力区分が選択されている場合<BR>
    * 　@出金停止区分にtrueを設定する。<BR>
    * <BR>
    * 6)余力制御機@能検索リクエスト.その他商品買付区分が選択されている場合<BR>
    * 　@その他商品買付停止区分にtrueを設定する。<BR>
    * <BR>
    * 7)表示顧客区分の設定<BR>
    * 　@7-1)部店コードと顧客コードしか指定されていない場合、<BR>
    * 　@　@　@又は、部店コードあるいは顧客コードしか指定されていない場合、<BR>
    * 　@　@　@表示顧客区分にtrueを設定<BR>
    * 　@7-2)1～6までのいずれかがtrueになっている場合は表示顧客区分にtrueを設定,<BR>
    * 　@　@　@すべてがfalseとなっている場合は表示顧客区<BR>
    * 　@　@　@分にfalseを設定する。<BR>
    * <BR>
    * 8)表示顧客区分を返却する。<BR>
    * @@param l_request - 余力制御機@能検索リクエスト<BR>
    * (余力制御機@能検索リクエスト)
    * @@param l_account - 顧客<BR>
    * (顧客)
    * @@param l_row - 顧客余力条件Params<BR>
    * (顧客余力条件Params)
    * @@return boolean
    */
   protected boolean isShowableAccount(WEB3AdminTPFindCalcControlRequest l_request, WEB3GentradeMainAccount l_account, TradingpowerCalcConditionRow l_row)
   {
       boolean isShowByMarginOpenPositionStop = false;
       boolean isShowByIfoOpenPositionStop = false;
       boolean l_blnIsShowByAdditionalDepositStop = false;
       boolean isShowByTradingStop = false;
       boolean isShowByPaymentStop = false;
       boolean isShowByOtherTradingStop = false;
       boolean isShow = false;       
       
       //検索条件
       //余力制御機@能検索リクエスト.信用新規建余力区分のみにチェックがついている場合
       //信用客でない場合、信用新規建停止区分にfalseを設定する。
       if(l_request.marginOpenPositionStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getMarginOpenPositionStop()))
           {
               isShowByMarginOpenPositionStop = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);                          
           }
           else
           {
               isShowByMarginOpenPositionStop = false;
           }
       }

       //余力制御機@能検索リクエスト.先物OP新規建余力区分のみ選択されている場合
       //オプション客でない場合、オプション新規建停止区分にfalseを設定する。
       if(l_request.ifoOpenPositionStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getIfoOpenPositionStop()))
           {
               isShowByIfoOpenPositionStop = this.isIfoAccountEstablished(l_account);               
           }
           else
           {
               isShowByIfoOpenPositionStop = false;
           }
       }       

       //余力制御機@能検索リクエスト.追証未入金区分のみにチェックがついている場合
       //信用客でない場合、追証未入金区分にfalseを設定する。
       if (l_request.additionalDepositStop != null)
       {
           if (WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(l_row.getAdditionalDepositStop()))
           {
               l_blnIsShowByAdditionalDepositStop = l_account.isMarginAccountEstablished(
                   WEB3GentradeRepaymentDivDef.DEFAULT);
           }
           else
           {
               l_blnIsShowByAdditionalDepositStop = false;
           }
       }

       //余力制御機@能検索リクエスト.取引停止区分が選択されている場合
       //余力停止区分にtrueを設定する。
       if(l_request.tradingStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getTradingStop()))
           {
               isShowByTradingStop = true;
           }
           else
           {
               isShowByTradingStop = false;
           }           
       }

       //余力制御機@能検索リクエスト.出金余力区分が選択されている場合
       //出金停止区分にtrueを設定する。
       if(l_request.paymentStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getPaymentStop()))
           {
               isShowByPaymentStop = true;
           }
           else
           {
               isShowByPaymentStop = false;
           }           
       }

       //余力制御機@能検索リクエスト.その他商品買付区分が選択されている場合
       //その他商品買付停止区分にtrueを設定する。
       if(l_request.otherTradingStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getOtherTradingStop()))
           {
               isShowByOtherTradingStop = true;
           }
           else
           {
               isShowByOtherTradingStop = false;
           }           
       }
       
       //部店コードと顧客コードしか指定されていない場合、
       //又は、部店コードあるいは顧客コードしか指定されていない場合、表示顧客区分にtrueを設定
       if ((l_request.branchCodes != null || l_request.accountCode != null)
           && l_request.marginOpenPositionStop == null
           && l_request.ifoOpenPositionStop == null
           && l_request.additionalDepositStop == null
           && l_request.tradingStop == null
           && l_request.paymentStop == null
           && l_request.otherTradingStop == null
         )
       {
           isShow = true;
       }
       else
       {
           isShow = isShowByMarginOpenPositionStop || isShowByIfoOpenPositionStop
               || l_blnIsShowByAdditionalDepositStop || isShowByTradingStop || isShowByPaymentStop
               || isShowByOtherTradingStop;
       }
       

       log.debug("isShowByMarginOpenPositionStop=" + isShowByMarginOpenPositionStop);
	   log.debug("isShowByIfoOpenPositionStop=" + isShowByIfoOpenPositionStop);
	   log.debug("isShowByTradingStop=" + isShowByTradingStop);
	   log.debug("isShowByPaymentStop=" + isShowByPaymentStop);
	   log.debug("isShowByOtherTradingStop=" + isShowByOtherTradingStop);
	   log.debug("isShow=" + isShow);
                      
       if(isShow)
       {
           log.debug("表示する顧客=" + l_account.getAccountCode());
       }
       
       return isShow;
       
   }
   


}
@
