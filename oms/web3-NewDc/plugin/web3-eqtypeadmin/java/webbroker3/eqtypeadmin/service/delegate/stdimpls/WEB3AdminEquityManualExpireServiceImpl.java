head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効サービスImpl(WEB3AdminEquityManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
Revesion History : 2007/12/17  趙林鵬(中訊) 仕様変更モデルNo.168
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityLapseTargetOrderCondition;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;



/**
 * (管理者・株式手動失効サービスImpl)<BR>
 * 管理者・株式手動失効サービス実装クラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityManualExpireServiceImpl extends WEB3ClientRequestService implements WEB3AdminEquityManualExpireService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireServiceImpl.class);
    
    /**
     * ServerAccessorオブジェクト <BR>
     * <BR>
     * 失効処理を各サーバに振り分ける。<BR>
     * ※初回実行時にセットされる。<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (管理者・株式手動失効サービスImpl)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireServiceImpl
     * @@roseuid 446932950058
     */
    public WEB3AdminEquityManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * 株式手動失効処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・株式手動失効入力リクエストの場合<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○管理者・株式手動失効確認リクエストの場合<BR>
     * 　@　@this.validate手動失効()をコールする。<BR>
     * <BR>
     * ○管理者・株式手動失効処理起動リクエストの場合<BR>
     * 　@　@this.run手動失効()をコールする。<BR>
     * <BR>
     * ○管理者・株式手動失効処理ステータス確認リクエストの場合<BR>
     * 　@　@this.validate処理ステータス()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4469317D0107
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
        
        //管理者・株式手動失効入力リクエストの場合
        if (l_request instanceof WEB3AdminEquityManualLapseInputRequest)
        {
            //this.get入力画面()をコールする。
            l_response = this.getInputScreen((WEB3AdminEquityManualLapseInputRequest)l_request);
        }
        
        //管理者・株式手動失効確認リクエストの場合
        else if (l_request instanceof WEB3AdminEquityManualLapseConfirmRequest)
        {
            //this.validate手動失効()をコールする。
            l_response = this.validateManualExpire((WEB3AdminEquityManualLapseConfirmRequest)l_request);
        }
        
        //管理者・株式手動失効処理起動リクエストの場合
        else if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            //this.run手動失効()をコールする。
            l_response = this.runManualExpire((WEB3AdminEquityManualLapseRunRequest)l_request);
        }
        
        //管理者・株式手動失効処理ステータス確認リクエストの場合
        else if (l_request instanceof WEB3AdminEquityManualLapseStatusRequest)
        {
            //this.validate処理ステータス()をコールする。
            l_response = this.validateStatus((WEB3AdminEquityManualLapseStatusRequest)l_request);
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
     * 株式手動失効入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者株式手動失効サービス）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効入力リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03A6
     */
    protected WEB3AdminEquityManualLapseInputResponse getInputScreen(WEB3AdminEquityManualLapseInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminEquityManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);
        
        //1.4get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.5 validate注文繰越処理中(証券会社)
        this.validateCarryOvering(l_institution);
        
        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        String[] l_strMarkets = 
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);

        //1.8 get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        String[] l_strPTSMarkets = 
        	WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);

        String[] l_strResults = mergeAndSort(l_strMarkets, l_strPTSMarkets);

        //1.9  createResponse( )
        WEB3AdminEquityManualLapseInputResponse l_response = 
            (WEB3AdminEquityManualLapseInputResponse)l_request.createResponse();
        
        //1.10  (*)プロパティセット
        //市場コード一覧　@＝　@(*1)で取得した市場コードの配列と(*2)で取得した市場コードの配列をマージした配列を昇順でセット。
        l_response.marketList = l_strResults;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate手動失効)<BR>
     * 株式手動失効確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者株式手動失効サービス）validate手動失効」参照<BR>
     * ===================================================<BR>
     * 　@シーケンス図 :（管理者株式手動失効サービス）validate手動失効<BR>
     * 　@具体位置    : 1.5 get手動失効対象注文単位一覧()<BR>
     * 　@　@　@　@　@　@　@　@　@　@nullが返却された場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@「該当注文が存在しません。」の例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_02086<BR>
     * ===================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効確認リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03B6
     */
    protected WEB3AdminEquityManualLapseConfirmResponse validateManualExpire(WEB3AdminEquityManualLapseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateManualExpire(WEB3AdminEquityManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3 validate手動失効可能(管理者, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        //1.4  get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.5 get手動失効対象注文単位一覧(証券会社, String[], String, String[], String[], String[], String, Long, Long)
        EqtypeOrderUnitRow[] l_rows = 
            WEB3AdminPMEquityDataManager.getManualExpireOrderUnits(
                l_institution,  
                l_request.equityLapseTargetOrderCondition.branchCode, 
                l_request.equityLapseTargetOrderCondition.productCode,
                l_request.equityLapseTargetOrderCondition.marketList,
                l_request.equityLapseTargetOrderCondition.tradingTypeList,
                l_request.equityLapseTargetOrderCondition.repaymentList,
                l_request.equityLapseTargetOrderCondition.accountCode,
                null,
                null);
        
        //　@nullが返却された場合、
        //「該当注文が存在しません。」の例外をスローする。
        if (l_rows == null) 
        {
            log.debug("該当注文が存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当注文が存在しません。");          
        }
        
        //1.6 createResponse( )
        WEB3AdminEquityManualLapseConfirmResponse l_response =
            (WEB3AdminEquityManualLapseConfirmResponse)l_request.createResponse();
        
        //1.7  (*)プロパティセット
        //対象注文件数    ＝　@get手動失効対象注文単位一覧()の戻り値.length
        l_response.lapseTargetOrderNumber = String.valueOf(l_rows.length);
        
        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //銘柄名       ＝　@リクエストデータ.失効対象注文条件.銘柄コード != nullの場合、
        //               　@銘柄コードに該当する株式銘柄.getDataSourceObject().銘柄名をセット。
        String l_strProductCode = l_request.equityLapseTargetOrderCondition.productCode;
        if (l_strProductCode != null) 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqTypeProduct l_eqTypeProduct = null;
            try 
            {
                l_eqTypeProduct = l_equityProductManager.getProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex) 
            {
                log.error("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
            
            EqtypeProductRow l_row = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_response.productName = l_row.getStandardName();
       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run手動失効)<BR>
     * 株式手動失効起動を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者株式手動失効サービス）run手動失効」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効処理起動リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03D5
     */
    protected WEB3AdminEquityManualLapseRunResponse runManualExpire(WEB3AdminEquityManualLapseRunRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "runManualExpire(WEB3AdminEquityManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
      
        //1.3 validate手動失効可能()
        this.validateManualExpirePossibility(l_admin, l_request);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 deleteオンライン実行結果(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        //1.6 getデーモントリガー一覧( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        //1.7  getServerAccessor( )
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        //1.8 (*)getデーモントリガー一覧()の戻り値の要素数分、
        //Loop処理
        try
        {
            for (int i = 0; i < l_lisDaemonTriggerList.size(); i++) 
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                
                //1.8.1 updateAP呼出中(long)
                this.updateAPCalling(l_row.getThreadNo());
                
                //1.8.2 管理者・株式手動失効メインリクエスト( )
                WEB3AdminEquityManualLapseMainRequest l_mainRequest = 
                    new WEB3AdminEquityManualLapseMainRequest();
                
                //証券会社コード   ＝　@get証券会社コード()の戻り値
                l_mainRequest.institutionCode = l_strInstitutionCode;
                
                //スレッドNo        ＝　@処理対象の要素.スレッド番号
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                
                //From口座ID  ＝　@処理対象の要素.顧客コード（自）
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());
                
                //To口座ID        ＝　@処理対象の要素.顧客コード（至）
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());
                
                //失効対象注文条件  ＝　@リクエストの同名項目
                l_mainRequest.equityLapseTargetOrderCondition = l_request.equityLapseTargetOrderCondition;
 
                //1.8.3 doRequest(arg0 : Request)
                l_serverAccessor.doRequest(l_mainRequest); 
    
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.9 createResponse( )
        WEB3AdminEquityManualLapseRunResponse l_response = 
            (WEB3AdminEquityManualLapseRunResponse)l_request.createResponse();
        
        //1.10 (*)プロパティセット
        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate処理ステータス)<BR>
     * 株式手動失効の処理ステータスを確認する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者株式手動失効サービス）validate処理ステータス」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効処理ステータス確認リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321F000C
     */
    protected WEB3AdminEquityManualLapseStatusResponse validateStatus(WEB3AdminEquityManualLapseStatusRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateStatus(WEB3AdminEquityManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2  validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);
        
        //1.3 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 getデーモントリガー一覧( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        //1.5 getオンライン実行結果一覧(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.6 createResponse( )
        WEB3AdminEquityManualLapseStatusResponse l_response =
            (WEB3AdminEquityManualLapseStatusResponse)l_request.createResponse();
        
        //1.7 (*)プロパティセット
        //処理ステータス   ＝　@以下の分岐によりセットする。
        //　@①@"処理中"をセットする条件
        //　@　@・オンライン実行結果レコードが取得できなかった場合
        //　@　@・取得したデーモントリガーレコードの件数と、オンライン実行結果レコードの件数が異なる場合
        //　@　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
        //　@　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
        if (l_lisOnlineExecResultList == null || l_lisDaemonTriggerList.size() != l_lisOnlineExecResultList.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else 
        {
            int l_intSize = l_lisDaemonTriggerList.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for(int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineExecResultList.get(i);
                //　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
                //　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
                if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus()) 
                    || WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 1;
                    break;
                }
                else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                    || !WEB3RunStatusDivDef.DEALED.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 2;                 
                }
            }
            //①@"処理中"をセットする条件
            if (l_intFlag == 1)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
            }
            //　@②"処理済"をセットする条件
            //　@・取得した全てのデーモントリガーレコード.処理状態 == "未稼動"　@かつ
            //　@取得した全てのオンライン実行結果レコード.実行ステータス区分 == "処理済"
            else if (l_intFlag == 0)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALED;
            }
            //"エラー"をセットする条件
            //以外の場合
            else if (l_intFlag == 2)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.ERROR;
            }
        }
        
        //　@現在時刻  ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate手動失効可能)<BR>
     * 株式手動失効処理が実行可能かどうかチェックする。<BR>
     * 
     * シーケンス図<BR>
     * 「（管理者・株式手動失効サービス）validate手動失効可能」参照。<BR>
     * =====================================================<BR>
     * 　@シーケンス図 :（管理者・株式手動失効サービス）validate手動失効可能<BR>
     * 　@ 具体位置   : 1.10  (*)二重起動チェック<BR>
     *             以下の条件に該当しない場合は、<BR>
     *             「指定AP起動中（二重起動エラー）。」の例外をスローする。<BR>
     *             <BR>
     *             ①@getオンライン実行結果一覧()の戻り値 == null<BR>
     *             ②getオンライン実行結果一覧()の戻り値の件数 ==<BR>
     *             　@getデーモントリガー一覧()の戻り値の件数 かつ<BR>
     *             　@getオンライン実行結果一覧()の戻り値の各要素の<BR>
     *             　@実行ステータス区分に"処理中"が存在しない かつ<BR>
     *             　@getデーモントリガー一覧()の戻り値の各要素の<BR>
     *             　@処理状態が"未稼動<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_01992<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * 　@シーケンス図 : （管理者・株式手動失効サービス）validate手動失効可能<BR>
     * 　@ 具体位置   : 1.11.1 getProduct()<BR>
     * 　@　@　@　@　@　@　@　@1.12.1get顧客一覧(String, String[], String)<BR>
     * 　@　@　@　@　@　@　@　@　@　@取得できなかった場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@「条件に該当するデータが存在しない。」の<BR>
     * 　@　@　@　@　@　@　@　@　@　@業務エラーをスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_01037<BR>
     * =====================================================<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト
     * @@throws WEB3BaseException 
     * @@roseuid 4469564F03D3
     */
    protected void validateManualExpirePossibility(
        WEB3Administrator l_administrator, 
        WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateManualExpirePossibility(WEB3Administrator, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_administrator == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        //1.1 (*)リクエストの型判別
        WEB3AdminEquityManualLapseRunRequest l_runRequest = null;
        WEB3AdminEquityManualLapseConfirmRequest l_confirmRequest = null;
        WEB3AdminEquityLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminEquityManualLapseConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminEquityManualLapseConfirmRequest)l_request;
            l_condition = l_confirmRequest.equityLapseTargetOrderCondition;

        }
        else if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminEquityManualLapseRunRequest)l_request;
            l_condition = l_runRequest.equityLapseTargetOrderCondition;

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
        
        //リクエスト.失効対象注文条件.部店コード
        String[] l_strBranchCodes = l_condition.branchCode;
        
        //リクエスト.失効対象注文条件.銘柄コード
        String l_strProductCode = l_condition.productCode;
        
        //リクエスト.失効対象注文条件.顧客コード
        String l_strAccountCode = l_condition.accountCode;
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);

        //1.3 validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        //1.4  (*)処理起動リクエストの場合
        if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            //1.4.1 validate取引パスワード(String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        //1.5  get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_administrator.getInstitution();
        
        //1.6 validate注文繰越処理中(証券会社)
        this.validateCarryOvering(l_institution);

        //1.7  getデーモントリガー一覧( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.8 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.9 getオンライン実行結果一覧(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.10  (*)二重起動チェック
        if (l_lisOnlineExecResultList != null) 
        {
            if (l_lisOnlineExecResultList.size() != l_lisTriggerList.size()) 
            {
                log.debug("指定AP起動中（二重起動エラー）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定AP起動中（二重起動エラー）。");
            }
            else 
            {
                OnlineRunStatusRow l_onlineRunStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_lisOnlineExecResultList.size(); i++)
                {
                    l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineExecResultList.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisTriggerList.get(i);
                    if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())) 
                    {
                        log.debug("指定AP起動中（二重起動エラー）。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "指定AP起動中（二重起動エラー）。");
                    }
                }
            }
        }
        // 1.11 (*)取得した銘柄コード != nullの場合
        if (l_strProductCode != null) 
        {
            
            //1.11.1 getProduct()
            try 
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_equityProductManager.getProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException nf_ex)
            {
                log.debug("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
        }
        
        //1.12 (*)取得した顧客コード != nullの場合
        if (l_strAccountCode != null) 
        {
            //1.12.1 get顧客一覧(String, String[], String)
            WEB3AdminPMEquityDataManager.getAccountList(l_strInstitutionCode,l_strBranchCodes, l_strAccountCode);    
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getデーモントリガー一覧)<BR>
     * 引数の条件に該当するデーモントリガーテーブルの<BR>
     * レコードを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件でデーモントリガーテーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 　@　@処理タイプ = "手動失効（Eqtype）"<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@　@スレッド番号　@昇順<BR>
     * <BR>
     * 　@該当データなしの場合、「該当データなし」の<BR>
     * 　@システムエラーをスローする。<BR>
     *  class: WEB3SystemLayerException<BR>
     *  tag:SYSTEM_ERROR_80005<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@return java.util.List
     * @@throws WEB3BaseException 
     * @@roseuid 44695650026C
     */
    protected List getDaemonTriggerList() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDaemonTriggerList()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@DB検索 
        //　@以下の条件でデーモントリガーテーブルを検索する。 
        //　@[条件] 
        //　@　@処理タイプ = "手動失効（Eqtype）" 
        Object[] l_bindValues = {WEB3DaemonTriggerTypeDef.EQTYPE_MANUAL_EXPIRE};

        //　@[ソート条件] 
        //　@　@スレッド番号　@昇順 
        String l_strCondition = "thread_no asc";
        
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_bindValues);
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
        
        //　@該当データなしの場合、「該当データなし」の 
        //　@システムエラーをスローする。 
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        //２）　@検索結果を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (getオンライン実行結果一覧)<BR>
     * 引数の条件に該当するオンライン実行結果テーブルの<BR>
     * レコードを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件でオンライン実行結果テーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@銘柄タイプ = "株式"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@オンラインサービス区分 = "手動失効"<BR>
     * <BR>
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@return java.util.List
     * @@throws WEB3BaseException 
     * @@roseuid 446956500369
     */
    protected List getOnlineRunStatusList(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOnlineRunStatusList(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@DB検索 
        //　@以下の条件でオンライン実行結果テーブルを検索する。 
        //　@[条件] 
        //　@　@証券会社コード = パラメータ.証券会社コード 
        //　@　@銘柄タイプ = "株式" 
        //　@　@先物／オプション区分 = "DEFAULT" 
        //　@　@オンラインサービス区分 = "手動失効" 
        String l_strWhere = " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);
        
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_bindValues);
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
        
        // 該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@検索結果を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (deleteオンライン実行結果)<BR>
     * 条件に該当するオンライン実行結果テーブルの<BR>
     * レコードを物理削除する。<BR>
     * <BR>
     * １）　@以下の条件に該当するオンライン実行結果テーブルの<BR>
     * 　@レコードをdeleteする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@銘柄タイプ = "株式"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@オンラインサービス区分 = "手動失効"<BR>
     * <BR>
     * 　@※削除対象のレコードがなくても例外としないこと。<BR>
     * 　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@throws WEB3BaseException 
     * @@roseuid 446956510034
     */
    protected void deleteOnlineRunStatus(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "deleteOnlineRunStatus(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@以下の条件に該当するオンライン実行結果テーブルの 
        //　@レコードをdeleteする。 
        //　@[条件] 
        //　@　@証券会社コード = パラメータ.証券会社コード 
        //　@　@銘柄タイプ = "株式" 
        //　@　@先物／オプション区分 = "DEFAULT" 
        //　@　@オンラインサービス区分 = "手動失効" 
        final String l_strWhere= " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        
        final Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);
        
        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                OnlineRunStatusRow.TYPE,
                                l_strWhere,
                                "for update",
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
            );
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
     * (updateAP呼出中)<BR>
     * 引数のスレッドNoに該当するデーモントリガーの<BR>
     * レコードを、"AP呼出中"でupdateする。<BR>
     * <BR>
     * １）　@以下の条件に該当するデーモントリガーテーブルの<BR>
     * 　@レコードをupdateする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@スレッド番号 = パラメータ.スレッドNo<BR>
     * <BR>
     * 　@[更新値]<BR>
     * 　@　@処理状態 = "トリガー（AP呼出中）"<BR>
     * <BR>
     * 　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * @@param l_lngThreadNo - (スレッドNo)<BR>
     * スレッドNo
     * @@throws WEB3BaseException 
     * @@roseuid 446956510131
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);
        
        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報の更新に失敗した場合の値
        final int NO_ROWS = -2; // 更新対象スレッド情報を取得できなかった場合の値
        
        //１）　@以下の条件に該当するデーモントリガーテーブルの 
        //　@レコードをupdateする。 
        //
        //　@[条件] 
        //　@　@スレッド番号 = パラメータ.スレッドNo 
        //
        //　@[更新値] 
        //　@　@処理状態 = "トリガー（AP呼出中）" 
        //
        //　@※本処理は新規トランザクションで処理を行い、 
        //　@　@処理完了時に更新が反映されるようにすること。 
        //　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）
        try 
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status",WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
            
            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_lngThreadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                        DaemonTriggerRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows != null && l_lisRows.size() > 0)
                    {
                        DaemonTriggerRow l_row =
                            (DaemonTriggerRow) l_lisRows.get(0);
                        WEB3DataAccessUtility.updateRow(l_row, l_changes);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(NO_ROWS);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "テーブルに該当するデータがありません。");
            }
        }
        catch (DataException de)
        {
            log.error("DBへのアクセスに失敗しました。", de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文繰越処理中)<BR>
     * 注文繰越処理中かどうかチェックする。<BR>
     * <BR>
     * １）　@パラメータ.証券会社.get注文繰越処理区分()メソッドを<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get注文繰越処理区分()にセットするパラメータ]<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@先物／オプション区分：　@"DEFAULT"<BR>
     * <BR>
     * 　@nullが返却された場合は、処理を終了する。<BR>
     * <BR>
     * ２）　@１）の戻り値が"注文繰越AP呼出中"の場合、<BR>
     * 　@「注文繰越処理中の為、処理不可」の例外をスローする。<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02446<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@throws WEB3BaseException 
     * @@roseuid 447264600022
     */
    protected void validateCarryOvering(WEB3GentradeInstitution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateCarryOvering(WEB3GentradeInstitution)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@パラメータ.証券会社.get注文繰越処理区分()メソッドを 
        //　@コールする。 
        String l_strCarryoverEndType = 
            l_institution.getCarryoverEndType(ProductTypeEnum.EQUITY, WEB3FuturesOptionDivDef.DEFAULT);

        //　@nullが返却された場合は、処理を終了する。 
        if (l_strCarryoverEndType == null) 
        {
            log.exiting(l_strCarryoverEndType);
            return;
        }
        //２）　@１）の戻り値が"注文繰越AP呼出中"の場合、 
        //　@「注文繰越処理中の為、処理不可」の例外をスローする。 
        else if (WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndType))
        {
            log.debug("注文繰越処理中の為、処理不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文繰越処理中の為、処理不可。");     
        }
     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 負荷分散処理を行う為のServerAccessorオブジェクトを<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@this.accessor != nullの場合、this.accessorを返却する。<BR>
     * <BR>
     * ２）　@１）以外の場合、以降の手順にてServerAccessor<BR>
     * 　@オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@クラスタリング先サーバーURLを取得する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@ServerConfigRow.TYPE<BR>
     * 　@　@　@arg1：　@"config_categ = ?"<BR>
     * 　@　@　@arg2：　@"cluster.urls"のみを要素とする配列<BR>
     * <BR>
     * 　@　@※"cluster.urls"は定数定義クラス参照すること。<BR>
     * <BR>
     * 　@　@検索結果の各要素.config_valueを取得し、文字列配列を<BR>
     * 　@　@作成する。<BR>
     * 　@　@※検索結果が取得できなかった場合、「該当データなし」の<BR>
     * 　@　@　@システムエラーをスローする。<BR>
     * 　@　@class　@:　@WEB3SystemLayerException<BR>
     * 　@　@tag　@:　@SYSTEM_ERROR_80005<BR>
     * <BR>
     * ４）　@ServerAccessorの作成<BR>
     * 　@ServerConnector.createAccessor()メソッドをコールし、<BR>
     * 　@戻り値をthis.accessorにセットした後、戻り値を返却する。<BR>
     * <BR>
     * 　@[craeteAccessor()にセットするパラメータ]<BR>
     * 　@　@arg0（ACCESSOR_KEY）：　@"web3-static-cluster"<BR>
     * 　@　@arg1（URL）：　@３）にて作成した文字列配列<BR>
     * <BR>
     * 　@　@※"web3-static-cluster"は定数定義クラス参照すること。<BR>
     * @@return ServerAccessor
     * @@throws WEB3BaseException 
     * @@roseuid 4472EC71004E
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.accessor != nullの場合、this.accessorを返却する。 
        if (this.accessor != null)
        {
            return this.accessor;
        }
        
        //２）　@１）以外の場合、以降の手順にてServerAccessor 
        //　@オブジェクトを取得する。 
        //
        //３）　@クラスタリング先サーバーURLを取得する。 
        //　@　@QueryProcessor.doFindAllQuery()メソッドを 
        //　@　@コールする。 
        //
        //　@　@[doFindAllQuery()にセットするパラメータ] 
        //　@　@　@arg0：　@ServerConfigRow.TYPE 
        //　@　@　@arg1：　@"config_categ = ?" 
        //　@　@　@arg2：　@"cluster.urls"のみを要素とする配列 
        //
        //　@　@※"cluster.urls"は定数定義クラス参照すること。 
        //
        //　@　@検索結果の各要素.config_valueを取得し、文字列配列を 
        //　@　@作成する。 
        //　@　@※検索結果が取得できなかった場合、「該当データなし」の 
        //　@　@　@システムエラーをスローする。 
        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;
        try 
        {         
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);
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
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        //４）　@ServerAccessorの作成 
        // 　@ServerConnector.createAccessor()メソッドをコールし、
        // 　@戻り値をthis.accessorにセットする。
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow) l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }
        
        String[] l_strConfigValues = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strConfigValues);
        try
        {
            ServerAccessor l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strConfigValues);
            this.accessor = l_serverAccessor;
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return this.accessor;
    }

    /**
     * 二つの配列を一つの配列に合併して、昇順ソートで返却する
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrc, String[] l_strDest)
     {
         Object[] l_objMergeArray = WEB3Toolkit.merge(l_strArraySrc, l_strDest);
         l_objMergeArray = WEB3Toolkit.toUnique(l_objMergeArray);
         int l_intMergeArrayCnt = l_objMergeArray.length;
         String[] l_strResults = new String[l_intMergeArrayCnt];
         for (int i = 0; i < l_intMergeArrayCnt; i++)
         {
             l_strResults[i] = (String) l_objMergeArray[i];
         }

         int l_intResultsCnt = l_strResults.length;
         String l_strTemp = null;
         for (int i = 0; i < l_intResultsCnt; i++)
         {
             for (int j = i + 1; j < l_intResultsCnt; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }
         return l_strResults;
     }
}
@
