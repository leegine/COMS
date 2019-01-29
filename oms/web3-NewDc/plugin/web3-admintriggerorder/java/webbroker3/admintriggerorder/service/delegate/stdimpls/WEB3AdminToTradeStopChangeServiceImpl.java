head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止変更サービスImpl(WEB3AdminToTradeStopChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 余新敏(中訊) 新規作成
                 : 2006/04/12 余新敏(中訊) 仕様変更・モデル055
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopPK;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (トリガー注文管理者・取扱停止変更サービスImpl)<BR>
 * トリガー注文管理者・取扱停止変更サービス実装クラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToTradeStopChangeServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopChangeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopChangeServiceImpl.class);
    
    /**
     * @@roseuid 4430DDF40271
     */
    public WEB3AdminToTradeStopChangeServiceImpl() 
    {
     
    }
    
    /**
     * 取扱停止変更処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止変更入力リクエストの場合<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止変更確認リクエストの場合<BR>
     * 　@this.validate変更()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止変更完了リクエストの場合<BR>
     * 　@this.submit変更()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FB8102AD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        if (l_request instanceof WEB3AdminToTradeStopUpdInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToTradeStopUpdInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToTradeStopUpdConfirmRequest)
        {
            l_response = this.validateChange((WEB3AdminToTradeStopUpdConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToTradeStopUpdCompleteRequest)
        {
            l_response = this.submitChange((WEB3AdminToTradeStopUpdCompleteRequest) l_request);
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
     * (get入力画面)<BR>
     * 取扱停止変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止変更サービス）get入力画面」参照。<BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止変更サービス）get入力画面」<BR>
     *    具体位置：1.5.1 (*)特殊執行条件取扱停止Rowの取得<BR>
     *    リクエストデータ.特殊執行条件取扱停止IDに<BR>
     *    該当する特殊執行条件取扱停止Rowを取得する。<BR>
     *    ※Daoクラスのメソッドを使用。<BR>
     *    取得できなかった場合、該当データなしのシステムエラーをスローする。<BR>
     *    class : WEB3SystemLayerException<BR>
     *    tag : SYSTEM_ERROR_80005<BR>
     * ============================================================== <BR>
     * <BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止変更サービス）get入力画面」<BR>
     *    具体位置：1.6.2 get特殊執行条件取扱停止一覧(String, String[], String, <BR>
     *    String, WEB3AdminToTradeStopSortKey[])<BR>
     *    nullが返却された場合、<BR>
     *    「条件に該当するデータが存在しない。」の<BR>
     *    業務エラーをスローする。<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA00F8
     */
    protected WEB3AdminToTradeStopUpdInputResponse getInputScreen(WEB3AdminToTradeStopUpdInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopUpdInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
        
        //1.4.validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5.(*)銘柄別取扱停止変更（リクエストデータ.特殊執行条件取扱停止ID != null）の場合
        List l_lisRows = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.triggerTradeStopId))
        {
            try
            {
                //1.5.1.(*)特殊執行条件取扱停止Rowの取得
                //リクエストデータ.特殊執行条件取扱停止IDに
                //該当する特殊執行条件取扱停止Rowを取得する。
                //※Daoクラスのメソッドを使用。
                //取得できなかった場合、該当データなしのシステムエラーをスローする。
                TriggerOrderStopRow l_triggerOrderStopRow = TriggerOrderStopDao.findRowByPk(
                    Long.parseLong(l_request.triggerTradeStopId));
                l_lisRows = new ArrayList();
                l_lisRows.add(l_triggerOrderStopRow);
            }
            catch (DataFindException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
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
        }
        //1.6.(*)上記以外の場合
        else
        {
            //1.6.1.get証券会社コード( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            
            //1.6.2.get特殊執行条件取扱停止一覧(String, String[], String, 
            //String, WEB3AdminToTradeStopSortKey[])
            //nullが返却された場合、「条件に該当するデータが存在しない。」の
            //業務エラーをスローする。
            l_lisRows = WEB3AdminToDataManager.getTriggerOrderStopList(
                l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.tradeStopDiv, 
                null, 
                null);
            
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
        }
        
        //1.7.get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.8.create取扱停止情報一覧(証券会社, 特殊執行条件取扱停止Row[])
        TriggerOrderStopRow[] l_rows = new TriggerOrderStopRow[l_lisRows.size()];
        l_lisRows.toArray(l_rows);
        WEB3AdminToTradeStopInfoUnit[] l_tradeStopInfoUnits = 
            WEB3AdminToDataManager.createTradeStopInfoList(
                l_institution, 
                l_rows);
        
        //1.9.createResponse( )
        WEB3AdminToTradeStopUpdInputResponse l_response = 
            (WEB3AdminToTradeStopUpdInputResponse) l_request.createResponse();

        //1.10.(*)プロパティセット
        //(*)レスポンスデータに以下のプロパティをセットする。
        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        //取扱停止情報一覧  ＝　@create取扱停止情報一覧()の戻り値
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        l_response.tradeStopInfoList = l_tradeStopInfoUnits;
        
        //1.11
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更)<BR>
     * 取扱停止変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止変更サービス）validate変更」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA0117
     */
    protected WEB3AdminToTradeStopUpdConfirmResponse validateChange(WEB3AdminToTradeStopUpdConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminToTradeStopUpdConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4.createResponse( )
        WEB3AdminToTradeStopUpdConfirmResponse l_response = 
            (WEB3AdminToTradeStopUpdConfirmResponse) l_request.createResponse();
        
        //レスポンス生成後、現在時刻をセットする。
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //1.5
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * 取扱停止変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止変更サービス）submit変更」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA0136
     */
    protected WEB3AdminToTradeStopUpdCompleteResponse submitChange(WEB3AdminToTradeStopUpdCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminToTradeStopUpdCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1/4 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.(*)リクエストデータ.取扱停止情報一覧の要素（=取扱停止情報）数分、Loop処理
        int l_intStopInfoLen = l_request.tradeStopInfoList.length;
        for (int i = 0; i < l_intStopInfoLen; i++)
        {
            //1.5.1.HashMap( )
            Map l_map = new HashMap();
            
            //1.5.2.(*)変更後店頭公開区分チェック
            if (WEB3MarketCodeDef.JASDAQ.equals(l_request.tradeStopInfoList[i].marketCode))
            {
                if (!WEB3Toolkit.isEquals(l_request.tradeStopInfoList[i].otcOpenDiv, l_request.tradeStopInfoList[i].aftOtcOpenDiv))
                {
                    l_map.put("addition", l_request.tradeStopInfoList[i].aftOtcOpenDiv);
                }
            }
            
            //1.5.3.(*)変更後停止理由チェック
            if (!WEB3Toolkit.isEquals(l_request.tradeStopInfoList[i].stopReason, l_request.tradeStopInfoList[i].aftChangeStopReason))
            {
                l_map.put("stop_reason", l_request.tradeStopInfoList[i].aftChangeStopReason);
            }

            //1.5.4.(*)変更後有効期限Toチェック
            if (!l_request.tradeStopInfoList[i].expirationEndDate.equals(
                l_request.tradeStopInfoList[i].aftChangeExpirationEndDate))
            {
                l_map.put("valid_term_to", l_request.tradeStopInfoList[i].aftChangeExpirationEndDate);
            }
            
            //1.5.5.(*)処理対象の要素.注文停止状況一覧の要素（=注文停止状況）数分、Loop処理
            int l_intStopStateLen = l_request.tradeStopInfoList[i].orderStopStateList.length;
            for (int j = 0; j < l_intStopStateLen; j++)
            {
                //1.5.5.1.(*)変更後停止フラグチェック
                if (l_request.tradeStopInfoList[i].orderStopStateList[j].stopFlag != 
                    l_request.tradeStopInfoList[i].orderStopStateList[j].aftChangeStopFlag)
                {
                    String l_strColumn = null;
                    if (WEB3TriggerOrderTypeDef.SUCC.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "succ_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.OCO.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "oco_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.IFD.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "ifd_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.STOP.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "stop_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "wlimit_order_stop_flag";
                    }
                    
                    if (l_request.tradeStopInfoList[i].orderStopStateList[j].aftChangeStopFlag)
                    {
                        l_map.put(l_strColumn, BooleanEnum.TRUE);
                    }
                    else
                    {
                        l_map.put(l_strColumn, BooleanEnum.FALSE);
                    }
                }
            }
            
            //1.5.6.(*)変更あり（HashMap.size() != 0）の場合
            if (l_map.size() != 0)
            {
                //1.5.6.1.(*)生成したHashMapに更新値：更新者コード、更新日付をセットする。
                l_map.put("last_updater", l_admin.getAdministratorCode());
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                
                try
                {
                    //1.5.6.2.doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
                    //(*)引数設定仕様
                    //arg0：　@処理対象の要素.IDを引数として生成した特殊執行条件取扱停止Pk
                    //arg1：　@生成したHashMap
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    TriggerOrderStopPK l_triggerOrderStopPK = 
                        new TriggerOrderStopPK(Long.parseLong(l_request.tradeStopInfoList[i].id));
                    l_queryProcessor.doUpdateQuery(l_triggerOrderStopPK, l_map); 
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
            }
        }
        
        //1.6.createResponse( )
        //レスポンス生成後、現在時刻をセットする。
        WEB3AdminToTradeStopUpdCompleteResponse l_response = 
            (WEB3AdminToTradeStopUpdCompleteResponse) l_request.createResponse();
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        //1.7
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
