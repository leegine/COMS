head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効サービスImpl(WEB3AdminIfoManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
Revision History : 2007/07/09　@孟亜南(中訊) 仕様変更モデルNo.002
*/

package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderCondition;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;



/**
 * (管理者・先物OP手動失効サービスImpl)<BR>
 * 管理者・先物OP手動失効サービス実装クラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualExpireServiceImpl extends WEB3ClientRequestService implements WEB3AdminIfoManualExpireService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireServiceImpl.class);
    
    /**
     * ServerAccessorオブジェクト <BR>
     * <BR>
     * 失効処理を各サーバに振り分ける。<BR>
     * ※初回実行時にセットされる。<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (管理者・先物OP手動失効サービスImpl)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImpl
     * @@roseuid 446932950058
     */
    public WEB3AdminIfoManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * 先物OP手動失効処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・先物OP手動失効入力リクエストの場合<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○管理者・先物OP手動失効確認リクエストの場合<BR>
     * 　@　@this.validate手動失効()をコールする。<BR>
     * <BR>
     * ○管理者・先物OP手動失効処理起動リクエストの場合<BR>
     * 　@　@this.run手動失効()をコールする。<BR>
     * <BR>
     * ○管理者・先物OP手動失効処理ステータス確認リクエストの場合<BR>
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
        
        //管理者・先物OP手動失効入力リクエストの場合
        if (l_request instanceof WEB3AdminIfoManualLapseInputRequest)
        {
            //this.get入力画面()をコールする。
            l_response = this.getInputScreen((WEB3AdminIfoManualLapseInputRequest)l_request);
        }
        
        //管理者・先物OP手動失効確認リクエストの場合
        else if (l_request instanceof WEB3AdminIfoManualLapseConfirmRequest)
        {
            //this.validate手動失効()をコールする。
            l_response = this.validateManualExpire((WEB3AdminIfoManualLapseConfirmRequest)l_request);
        }
        
        //管理者・先物OP手動失効処理起動リクエストの場合
        else if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            //this.run手動失効()をコールする。
            l_response = this.runManualExpire((WEB3AdminIfoManualLapseRunRequest)l_request);
        }
        
        //管理者・先物OP手動失効処理ステータス確認リクエストの場合
        else if (l_request instanceof WEB3AdminIfoManualLapseStatusRequest)
        {
            //this.validate処理ステータス()をコールする。
            l_response = this.validateStatus((WEB3AdminIfoManualLapseStatusRequest)l_request);
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
     * 先物OP手動失効入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者先物OP手動失効サービス）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効入力リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03A6
     */
    protected WEB3AdminIfoManualLapseInputResponse getInputScreen(WEB3AdminIfoManualLapseInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIfoManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);
        
        // get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        // validate注文繰越処理中(証券会社,String[])
        //取引区分一覧
        String[] l_strTradingTypeList = null;
        this.validateCarryOvering(l_institution,l_strTradingTypeList);
        
        // get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // get（部店指数別）取扱条件(証券会社コード : String)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(l_strInstitutionCode);
        
        // createResponse( )
        WEB3AdminIfoManualLapseInputResponse l_response = 
            (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
        
        // (*)プロパティセット
        //指数種別一覧 = get（部店指数別）取扱条件の戻り値 
        //uniqueな指数（=原資産銘柄コード）のみの配列をセット
        Vector l_vecTargetProductCode = new Vector();
        for (int i = 0; i < l_branchIndexDealtConds.length; i++) 
        {
            WEB3GentradeBranchIndexDealtCond l_branchIndexDealtCond = l_branchIndexDealtConds[i];
            if (!l_vecTargetProductCode.contains(l_branchIndexDealtCond.getUnderlyingProductCode())) 
            {
                l_vecTargetProductCode.add(l_branchIndexDealtCond.getUnderlyingProductCode());
            }
        }
        
        String[] l_strTargetProductCodeList = new String[l_vecTargetProductCode.size()];
        for (int i = 0; i < l_vecTargetProductCode.size(); i++) 
        {
            l_strTargetProductCodeList[i] = (String)l_vecTargetProductCode.get(i);
        }
        
        l_response.targetProductList = l_strTargetProductCodeList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate手動失効)<BR>
     * 先物OP手動失効確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者先物OP手動失効サービス）validate手動失効」参照<BR>
     * ===================================================<BR>
     * 　@シーケンス図 :（管理者先物OP手動失効サービス）validate手動失効<BR>
     * 　@具体位置    : 1.5 get手動失効対象注文単位一覧()<BR>
     * 　@　@　@　@　@　@　@　@　@　@nullが返却された場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@「該当注文が存在しません。」の例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_02086<BR>
     * ===================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効確認リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03B6
     */
    protected WEB3AdminIfoManualLapseConfirmResponse validateManualExpire(WEB3AdminIfoManualLapseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateManualExpire(WEB3AdminIfoManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        // validate手動失効可能(管理者, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        // get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();

        // get銘柄(証券会社, String, String, String, String, String)
        WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(l_institution ,
            l_request.ifoLapseTargetOrderCondition.fuOpDiv ,
            l_request.ifoLapseTargetOrderCondition.targetProductCode ,
            l_request.ifoLapseTargetOrderCondition.delivaryMonth ,
            l_request.ifoLapseTargetOrderCondition.strikePrice ,
            l_request.ifoLapseTargetOrderCondition.opProductType
            );
        //get銘柄(証券会社, String, String, String, String, String)
        
        String l_strProductId = null;
        String l_productCode = null;
        if (l_ifoProductImpl != null) 
        {
            l_strProductId = Long.toString(l_ifoProductImpl.getProductId());
            l_productCode = l_ifoProductImpl.getProductCode();
        }
        
        // get手動失効対象注文単位一覧(証券会社, String[], String, String[], String[], String[], String, Long, Long)
        IfoOrderUnitRow[] l_rows = 
            WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution,  
                l_request.ifoLapseTargetOrderCondition.branchCode, 
                l_strProductId,
                l_request.ifoLapseTargetOrderCondition.tradingTypeList,
                l_request.ifoLapseTargetOrderCondition.accountCode,
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
        
        // createResponse( )
        WEB3AdminIfoManualLapseConfirmResponse l_response =
            (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
        
        // (*)プロパティセット
        //対象注文件数    ＝　@get手動失効対象注文単位一覧()の戻り値.length
        l_response.lapseTargetOrderNumber = String.valueOf(l_rows.length);
        
        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //銘柄名       ＝　@get銘柄()銘柄の戻り値 != nullの場合、
        //                get銘柄()の戻り値銘柄名。
        String l_strProductCode = l_productCode;
        if (l_strProductCode != null) 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            IfoProduct l_ifoProduct = null;
            try 
            {
                l_ifoProduct = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);
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
            
            IfoProductRow l_row = (IfoProductRow)l_ifoProduct.getDataSourceObject();
            l_response.productName = l_row.getStandardName();
       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run手動失効)<BR>
     * 先物OP手動失効起動を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者先物OP手動失効サービス）run手動失効」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効処理起動リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03D5
     */
    protected WEB3AdminIfoManualLapseRunResponse runManualExpire(WEB3AdminIfoManualLapseRunRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "runManualExpire(WEB3AdminIfoManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
      
        // validate手動失効可能()
        this.validateManualExpirePossibility(l_admin, l_request);
        
        // get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // deleteオンライン実行結果(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        // getデーモントリガー一覧( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        // getServerAccessor( )
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        // (*)getデーモントリガー一覧()の戻り値の要素数分、
        //Loop処理
        try
        {
            for (int i = 0; i < l_lisDaemonTriggerList.size(); i++) 
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                
                // updateAP呼出中(long)
                this.updateAPCalling(l_row.getThreadNo());
                
                // 管理者・先物OP手動失効メインリクエスト( )
                WEB3AdminIfoManualLapseMainRequest l_mainRequest = 
                    new WEB3AdminIfoManualLapseMainRequest();
                
                //証券会社コード   ＝　@get証券会社コード()の戻り値
                l_mainRequest.institutionCode = l_strInstitutionCode;
                
                //スレッドNo        ＝　@処理対象の要素.スレッド番号
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                
                //From口座ID  ＝　@処理対象の要素.顧客コード（自）
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());
                
                //To口座ID        ＝　@処理対象の要素.顧客コード（至）
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());
                
                //失効対象注文条件  ＝　@リクエストの同名項目
                l_mainRequest.ifoLapseTargetOrderCondition = l_request.ifoLapseTargetOrderCondition;
 
                // doRequest(arg0 : Request)
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
        
        // createResponse( )
        WEB3AdminIfoManualLapseRunResponse l_response = 
            (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
        
        // (*)プロパティセット
        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate処理ステータス)<BR>
     * 先物OP手動失効の処理ステータスを確認する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者先物OP手動失効サービス）validate処理ステータス」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効処理ステータス確認リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321F000C
     */
    protected WEB3AdminIfoManualLapseStatusResponse validateStatus(WEB3AdminIfoManualLapseStatusRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateStatus(WEB3AdminIfoManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        // getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);
        
        // get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // getデーモントリガー一覧( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        // getオンライン実行結果一覧(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        // createResponse( )
        WEB3AdminIfoManualLapseStatusResponse l_response =
            (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
        
        // (*)プロパティセット
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
     * 先物OP手動失効処理が実行可能かどうかチェックする。<BR>
     * 
     * シーケンス図<BR>
     * 「（管理者・先物OP手動失効サービス）validate手動失効可能」参照。<BR>
     * =====================================================<BR>
     * 　@シーケンス図 :（管理者・先物OP手動失効サービス）validate手動失効可能<BR>
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
     * 　@シーケンス図 : （管理者・先物OP手動失効サービス）validate手動失効可能<BR>
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
        
        // (*)リクエストの型判別
        WEB3AdminIfoManualLapseRunRequest l_runRequest = null;
        WEB3AdminIfoManualLapseConfirmRequest l_confirmRequest = null;

        WEB3AdminIfoLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminIfoManualLapseConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminIfoManualLapseConfirmRequest)l_request;
            l_condition = l_confirmRequest.ifoLapseTargetOrderCondition;

        }
        else if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminIfoManualLapseRunRequest)l_request;
            l_condition = l_runRequest.ifoLapseTargetOrderCondition;

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
        
        //リクエスト.失効対象注文条件.先物／オプション区分
        String l_strFuturesOptionDivision = l_condition.fuOpDiv;
        
        //リクエスト.失効対象注文条件.指数種別
        String l_strTargetProductCode = l_condition.targetProductCode;
        
        //リクエスト.失効対象注文条件.限月
        String l_strDelivaryMonth = l_condition.delivaryMonth;
        
        //リクエスト.失効対象注文条件.行使価格
        String l_strStrikePrice = l_condition.strikePrice;
        
        //リクエスト.失効対象注文条件.オプション商品区分
        String l_strOpProductType = l_condition.opProductType;
        
        //リクエスト.失効対象注文条件.取引区分一覧
        String[] l_strTradingTypeList = l_condition.tradingTypeList;
        
        //リクエスト.失効対象注文条件.顧客コード
        String l_strAccountCode = l_condition.accountCode;
        
        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);

        // validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        // (*)処理起動リクエストの場合
        if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            // validate取引パスワード(String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        // get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_administrator.getInstitution();
        
        // validate注文繰越処理中(証券会社,String[])
        this.validateCarryOvering(l_institution,l_strTradingTypeList);

        // getデーモントリガー一覧( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        // get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        // getオンライン実行結果一覧(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        // (*)二重起動チェック
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
        // (*)取得した先物／オプション区分?!=?nullの場合
        if (l_strFuturesOptionDivision != null) 
        {
            
            // get銘柄(証券会社, String, String, String, String, String)
                
                WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(
                    l_institution ,
                    l_strFuturesOptionDivision ,
                    l_strTargetProductCode ,
                    l_strDelivaryMonth ,
                    l_strStrikePrice ,
                    l_strOpProductType);
                
                if (l_ifoProductImpl == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "条件に該当するデータが存在しない。"
                    );
                    
                }

        }
        
        // (*)取得した顧客コード != nullの場合
        if (l_strAccountCode != null) 
        {
            // get顧客一覧(String, String[], String)
            WEB3AdminIfoDataManager.getAccountList(l_strInstitutionCode,l_strBranchCodes, l_strAccountCode);    
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
     * 　@　@処理タイプ = "手動失効（ifo）"<BR>
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
        //　@　@処理タイプ = "手動失効（ifo）" 
        Object[] l_bindValues = {WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE};

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
     * 　@　@銘柄タイプ = "先物OP"<BR>
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
        //　@　@銘柄タイプ = "先物OP" 
        //　@　@先物／オプション区分 = "DEFAULT" 
        //　@　@オンラインサービス区分 = "手動失効" 
        String l_strWhere = " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.IFO);
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
     * 　@　@銘柄タイプ = "先物OP"<BR>
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
        //　@　@銘柄タイプ = "先物OP" 
        //　@　@先物／オプション区分 = "DEFAULT" 
        //　@　@オンラインサービス区分 = "手動失効" 
        final String l_strWhere= " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.IFO);
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
     * １）　@get注文繰越処理区分()の引数.出来終了区分にセットする値を設定する。 <BR>
     * <BR>
     * 　@１－１）　@取引時間管理.is夕場時間帯()の戻り値がfalseの場合 <BR>
     * 　@　@　@　@　@　@　@　@"DEFAULT"を設定する。 <BR>
     * 　@１－２）　@取引時間管理.is夕場時間帯()の戻り値がtrueの場合 <BR>
     * 　@　@　@　@　@　@　@　@"夕場前出来終了"を設定する。 <BR>
     * <BR>
     * ２）  先物が注文繰越処理中かどうかのチェック <BR>
     *   パラメータ.証券会社.get注文繰越処理区分()メソッドをコールする。<BR>
     * <BR>
     *   [get注文繰越処理区分()にセットするパラメータ]<BR>
     *    銘柄タイプ：　@"先物OP" <BR>
     *   先物／オプション区分：　@"先物" <BR>
     *   出来終了区分：　@１）の設定値<BR>
     * <BR>
     * ３）　@オプションが注文繰越処理中かどうかのチェック <BR>
     *   パラメータ.証券会社.get注文繰越処理区分()メソッドをコールする。 <BR>
     * <BR>
     *   [get注文繰越処理区分()にセットするパラメータ] <BR>
     *   銘柄タイプ：　@"先物OP" <BR>
     *   先物／オプション区分：　@"オプション" <BR>
     *   出来終了区分：　@１）の設定値 <BR>
     * <BR>
     * ４）  パラメータ.取引区分一覧により処理内容を分岐する。<BR>
     *   [パラメータ.取引区分一覧 == nullの場合] <BR>
     *  （入力画面表示処理からコールされた場合）<BR>
     *   ２）、３）の戻り値が両方とも"注文繰越AP呼出中"であれば、<BR>
     *  「注文繰越処理中の為、処理不可」の例外をスローする。 <BR>
     * <BR>
     *   [パラメータ.取引区分一覧に先物取引(*1)が含まれる場合] <BR>
     *   ２）の戻り値 == "注文繰越AP呼出中"であれば、<BR>
     *  「注文繰越処理中の為、処理不可」の例外をスローする。 <BR>
     * <BR>
     *   [パラメータ.取引区分一覧にオプション取引(*2)が含まれる場合] <BR>
     *   ３）の戻り値 == "注文繰越AP呼出中"であれば、<BR>
     *   「注文繰越処理中の為、処理不可」の例外をスローする。 <BR>
     *   <BR>
     * @@param l_institution - (証券会社)<BR>   
     * 証券会社オブジェクト <BR>  
     * @@param l_strTradingTypeList - (取引区分一覧)<BR>  
     * <BR> 
     * @@throws WEB3BaseException   
     */
    protected void validateCarryOvering(WEB3GentradeInstitution l_institution,String[] l_strTradingTypeList) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCarryOvering(WEB3GentradeInstitution,String[])";
        log.entering(STR_METHOD_NAME);

        //１）get注文繰越処理区分()の引数.出来終了区分にセットする値を設定する。
        //取引時間管理.is夕場時間帯()
        boolean l_blnIsEveningSessionTimeZone = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();

        String l_strEndType = null;

        if (l_blnIsEveningSessionTimeZone)
        {
            //出来終了区分
            l_strEndType = WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
        }
        else
        {
            l_strEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }

        //２）  先物が注文繰越処理中かどうかのチェック
        // パラメータ.証券会社.get注文繰越処理区分()メソッドをコールする。
        String l_strCarryoverEndTypeFutures = 
            l_institution.getCarryoverEndType(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.FUTURES, l_strEndType);
        
        //３）  オプションが注文繰越処理中かどうかのチェック
        // パラメータ.証券会社.get注文繰越処理区分()メソッドをコールする。
        String l_strCarryoverEndTypeOption = 
            l_institution.getCarryoverEndType(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION, l_strEndType);
        
        //パラメータ.取引区分一覧 == nullの場合
        //２）、３）の戻り値が両方とも"注文繰越AP呼出中"であれば
        if (l_strTradingTypeList == null)
        {
            if (WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeFutures)&&
                WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeOption))
            {
                log.debug("注文繰越処理中の為、処理不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文繰越処理中の為、処理不可。");
                          
            }
        }
        
        //[パラメータ.取引区分一覧に先物取引(*1)が含まれる場合]２）の戻り値 == "注文繰越AP呼出中"であれば、
        //「注文繰越処理中の為、処理不可」の例外をスローする。
        if (l_strTradingTypeList != null && 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeFutures))
        {
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    log.debug("注文繰越処理中の為、処理不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注文繰越処理中の為、処理不可。");                
                }
            }
        }
        
        //[パラメータ.取引区分一覧にオプション取引(*2)が含まれる場合]３）の戻り値 == "注文繰越AP呼出中"であれば、
        //「注文繰越処理中の為、処理不可」の例外をスローする。
        if (l_strTradingTypeList != null && 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeOption))
        {
            for (int i = 0; i < l_strTradingTypeList.length; i++)
            {
                if ( String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(l_strTradingTypeList[i]))
                {
                    log.debug("注文繰越処理中の為、処理不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注文繰越処理中の為、処理不可。");                
                }
            }
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
}
@
