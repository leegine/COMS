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
filename	WEB3AdminToManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効サービスImpl(WEB3AdminToManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17　@鄭徳懇(中訊) 新規作成
                 : 2006/08/23　@肖志偉(中訊) 仕様変更モデルNo.068,089
                 : 2006/10/18　@柴雙紅(中訊) 仕様変更モデルNo.093，097                 
                 : 2006/11/28　@周捷(中訊) 仕様変更モデルNo.115 
                 : 2006/11/20 黄建(中訊) モデル 112                
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToLapseTargetOrderCondition;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・手動失効サービスImpl)<BR>
 * （WEB3AdminToManualExpireServiceImpl）<BR>
 * トリガー注文管理者・手動失効サービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToManualExpireServiceImpl 
    extends WEB3ClientRequestService implements WEB3AdminToManualExpireService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualExpireServiceImpl.class);
    
    /**
     * ServerAccessorオブジェクト<BR>
     * <BR>
     * 失効処理を各サーバに振り分ける。<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (トリガー注文管理者・手動失効サービスImpl)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）クラスタリング先サーバーURLを取得する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@ServerConfigRow.TYPE<BR>
     * 　@　@　@arg1：　@"config_categ = ?"<BR>
     * 　@　@　@arg2：　@"cluster.urls"のみを要素とする配列<BR>
     * <BR>
     * 　@　@※"cluster.urls"は定数定義クラスをcommon.defineに<BR>
     * 　@　@　@作成すること。<BR>
     * <BR>
     * 　@　@検索結果の各要素.config_valueを取得し、文字列配列を<BR>
     * 　@　@作成する。<BR>
     * 　@　@※検索結果が取得できなかった場合、「該当データなし」の<BR>
     * 　@　@　@システムエラーをスローする。<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ２）　@ServerAccessorの作成<BR>
     * 　@ServerConnector.createAccessor()メソッドをコールし、<BR>
     * 　@戻り値をthis.accessorにセットする。<BR>
     * <BR>
     * 　@[craeteAccessor()にセットするパラメータ]<BR>
     * 　@　@arg0（ACCESSOR_KEY）：　@"web3-static-cluster"<BR>
     * 　@　@arg1（URL）：　@１）にて作成した文字列配列<BR>
     * <BR>
     * 　@　@※"web3-static-cluster"は定数定義クラスをcommon.defineに<BR>
     * 　@　@　@作成すること。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 440BE6F103D1
     */
    public WEB3AdminToManualExpireServiceImpl() throws WEB3BaseException
    {

    }
    
    /**
     * 手動失効処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○トリガー注文管理者・手動失効入力リクエストの場合<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・手動失効確認リクエストの場合<BR>
     * 　@　@this.validate手動失効()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・手動失効処理起動リクエストの場合<BR>
     * 　@　@this.run手動失効()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・手動失効処理ステータス確認リクエストの場合<BR>
     * 　@　@this.validate処理ステータス()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054D4D0212
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
        if (l_request instanceof WEB3AdminToManualLapseInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToManualLapseInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseConfirmRequest)
        {
            l_response = this.validateManualExpire((WEB3AdminToManualLapseConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            l_response = this.runManualExpire((WEB3AdminToManualLapseRunRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseStatusRequest)
        {
            l_response = this.validateStatus((WEB3AdminToManualLapseStatusRequest) l_request);
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
     * 手動失効条件入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効サービス）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToManualLapseInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD801D4
     */
    protected WEB3AdminToManualLapseInputResponse getInputScreen(WEB3AdminToManualLapseInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get商品区分一覧(String, String[])
        String[] l_strProductTypeList = WEB3AdminToDataManager.getCommodityDivList(l_strInstitutionCode, l_request.branchCode);
        
        //1.7 get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        String[] l_strMarkets = 
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseInputResponse l_response = 
            (WEB3AdminToManualLapseInputResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //条件注文種別一覧    ＝　@null（固定）　@※将来的に対応。
        l_response.triggerOrderTypeList = null;
        //商品区分一覧  ＝　@get商品区分一覧()の戻り値
        l_response.productDivList = l_strProductTypeList;
        //市場コード一覧 ＝　@get取扱可能市場()の戻り値
        l_response.marketList = l_strMarkets;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate手動失効)<BR>
     * 手動失効確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効サービス）validate手動失効」参照。<BR>
     *       =============================================== <BR>
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効<BR>
     *       具体位置    : 1.7.2 get手動失効対象注文単位一覧(Long, 証券会社, <BR>
     *                     String[], String[], String[], String[], String, <BR>
     *                     String, Long, Long)<BR>
     *                     nullが返却された場合、<BR>
     *                    「注文ＩＤに該当する注文単位が存在しません。」の例外をスローする。<BR>
     *       class       : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02086 <BR>
     *       =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToManualLapseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD801F3
     */
    protected WEB3AdminToManualLapseConfirmResponse validateManualExpire(WEB3AdminToManualLapseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualExpire(WEB3AdminToManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate手動失効可能(管理者, WEB3GenRequest)
        OrderUnit l_orderUnit = this.validateManualExpirePossibility(l_admin, l_request);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 createResponse( )
        WEB3AdminToManualLapseConfirmResponse l_response = 
            (WEB3AdminToManualLapseConfirmResponse) l_request.createResponse();
        
        //1.6 (*)ID直接指定時
        //（リクエストデータ.失効対象注文条件.注文ID != null）の場合
        String l_strlapseTargetOrderNumber = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.lapseTargetOrderCondition.id))
        {
            //1.6.1 create明細(String, OrderUnit, 
            //トリガー注文管理者・手動失効確認レスポンス)
            this.createUnit(l_strInstitutionCode, l_orderUnit, l_response);
            l_strlapseTargetOrderNumber = "1";
        }
        //1.7 (*)ID直接指定時でない場合
        else
        {
            //1.7.1 get証券会社( )
            Institution l_institution = l_admin.getInstitution();
            
            //1.7.2 get手動失効対象注文単位一覧(注文ID : Long, 
            //証券会社 : 証券会社, 部店コード : String[], 
            //条件注文種別一覧 : String[], 商品区分一覧 : String[], 
            //市場コード一覧 : String[], 銘柄コード : String, 
            //顧客コード : String, 口座IDFrom : Long, 口座IDTo : Long)
            WEB3AdminToLapseTargetOrderCondition l_orderCondition = l_request.lapseTargetOrderCondition;
            OrderUnit[] l_orderUnits = WEB3AdminToDataManager.getManualExpireOrderUnits(
                null,
                l_institution,
                l_orderCondition.branchCode,
                l_orderCondition.triggerOrderTypeList,
                l_orderCondition.productDivList,
                l_orderCondition.marketList,
                l_orderCondition.productCode,
                l_orderCondition.accountCode,
                null,
                null);

            if (l_orderUnits == null || l_orderUnits.length == 0)
            {
                log.error("該当注文が存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当注文が存在しません。");
            }
            
            l_strlapseTargetOrderNumber = String.valueOf(l_orderUnits.length);
        }
        
        //1.8 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //対象注文件数  ＝　@ID直接指定の場合、1。
        //以外、get手動失効対象注文単位一覧()の戻り値.length
        l_response.lapseTargetOrderNumber = l_strlapseTargetOrderNumber;
        //現在時刻 ＝ GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        //銘柄名
        if (l_request.lapseTargetOrderCondition.productCode != null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct = l_productManager.getProduct(
                    l_admin.getInstitution(),
                    l_request.lapseTargetOrderCondition.productCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("条件に該当するデータが存在しない。", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_nfe.getMessage(), 
                    l_nfe);
            }
            l_response.productName =
                ((EqtypeProductRow)l_eqtypeProduct.getDataSourceObject()).getStandardName();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run手動失効)<BR>
     * 手動失効処理起動を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効サービス）run手動失効」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効処理起動リクエストオブジェクト<BR>
     * @@return WEB3AdminToManualLapseRunResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD80212
     */
    protected WEB3AdminToManualLapseRunResponse runManualExpire(WEB3AdminToManualLapseRunRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminToManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate手動失効可能(管理者, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 deleteオンライン実行結果(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        //1.6 getデーモントリガー一覧( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.7 (*)getデーモントリガー一覧()の戻り値の要素数分、Loop処理
        int l_intLength = l_lisTriggerList.size();
        
        // getServerAccessor()
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        try
        {
            for(int i = 0; i < l_intLength; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow) l_lisTriggerList.get(i);
                // updateAP呼出中()
                this.updateAPCalling(l_row.getThreadNo());
                //1.7.1 トリガー注文管理者・手動失効メインリクエスト( )
                WEB3AdminToManualLapseMainRequest l_mainRequest = new WEB3AdminToManualLapseMainRequest();
                //(*)インスタンス生成後、以下のプロパティをセットする。
                //証券会社コード ＝　@get証券会社コード()の戻り値
                l_mainRequest.institutionCode = l_strInstitutionCode;
                //スレッドNo      ＝　@処理対象の要素.スレッド番号
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                //From口座ID    ＝　@処理対象の要素.顧客コード（自）
                l_mainRequest.rangeFrom = new Long(l_row.getRangeFrom());
                //To口座ID      ＝　@処理対象の要素.顧客コード（至）
                l_mainRequest.rangeTo = new Long(l_row.getRangeTo());
                //失効対象注文条件    ＝　@リクエストデータの同名項目
                l_mainRequest.lapseTargetOrderCondition = l_request.lapseTargetOrderCondition;
                
                //1.7.2 doRequest(arg0 : Request)
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
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseRunResponse l_response = 
            (WEB3AdminToManualLapseRunResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //現在時刻        ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate処理ステータス)<BR>
     * 手動失効の処理ステータスを確認する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効サービス）validate処理ステータス」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効処理ステータス確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToManualLapseStatusResponse
     * @@throws WEB3BaseException
     * @@roseuid 440E89B90065
     */
    protected WEB3AdminToManualLapseStatusResponse validateStatus(WEB3AdminToManualLapseStatusRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateStatus(WEB3AdminToManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
   
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
 
        //1.6 getデーモントリガー一覧( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.7 getオンライン実行結果一覧(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseStatusResponse l_response = 
            (WEB3AdminToManualLapseStatusResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //処理ステータス   ＝　@以下の分岐によりセットする。
        //　@①@"処理中"をセットする条件
        //　@　@・オンライン実行結果レコードが取得できなかった場合
        //　@　@・取得したデーモントリガーレコードの件数と、オンライン実行結果レコードの件数が異なる場合
        //　@　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
        //　@　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
        if (l_lisOnlineExecResultList == null || l_lisTriggerList.size() != l_lisOnlineExecResultList.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else 
        {
            int l_intSize = l_lisTriggerList.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for(int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisTriggerList.get(i);
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
     * 手動失効処理が実行可能かどうかチェックする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効サービス）validate手動失効可能」参照。<BR>
     *       =============================================== <BR>
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効可能<BR>
     *       具体位置     : 1.5.2 getProduct(arg0 : Institution, <BR>
     *                      arg1 : 論理ビュー::java::lang::String)<BR>
     *                      取得できなかった場合、条件に該当するデータが存在しない。」<BR>
     *                      の業務エラーをスローする。<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_01037 <BR>
     *       =============================================== <BR>   
     *       =============================================== <BR> 
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効可能<BR>
     *       具体位置     : 1.6.2 get顧客一覧(String, String[], String)<BR>
     *                      取得できなかった場合、「条件に該当するデータが存在しない。」<BR>
     *                      の業務エラーをスローする。<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_01037 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR>
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効可能<BR>
     *       具体位置     : 1.7.1.1 getOrderUnits(arg0 : long)<BR>
     *                      取得できなかった場合、<BR>
     *                     「注文ＩＤに該当する注文単位が存在しません。」の<BR>
     *                      業務エラーをスローする。<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02011 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR>  
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効可能<BR>
     *       具体位置     : 1.7.2.1 getOrderUnits(arg0 : long)<BR>
     *                      取得できなかった場合、<BR>
     *                     「注文ＩＤに該当する注文単位が存在しません。」の<BR>
     *                      業務エラーをスローする。<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02011 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR> 
     *       シーケンス図 :（トリガー注文管理者・手動失効サービス）validate手動失効可能<BR>
     *       具体位置     : 1.7.3 (*)注文未発注チェック<BR>
     *                      (*)以下の条件のいずれかに該当する場合は、<BR>
     * 　@                  「指定された注文は手動失効処理対象外」の例外をスローする。<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02419 <BR>
     *       =============================================== <BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 440790DB03D5
     */
    protected OrderUnit validateManualExpirePossibility(WEB3Administrator l_administrator, WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateManualExpirePossibility(WEB3Administrator, WEB3GenRequest)";
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
        WEB3AdminToManualLapseRunRequest l_runRequest = null;
        WEB3AdminToLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminToManualLapseConfirmRequest)
        {
            WEB3AdminToManualLapseConfirmRequest l_confirmRequest = 
                (WEB3AdminToManualLapseConfirmRequest) l_request;
            l_condition = l_confirmRequest.lapseTargetOrderCondition;
        }
        else if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminToManualLapseRunRequest) l_request;
            l_condition = l_runRequest.lapseTargetOrderCondition;
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
        
        //リクエスト.失効対象注文条件.注文ID
        String l_strOrderId = l_condition.id;
        //リクエスト.失効対象注文条件.部店コード
        String[] l_strBranchCodes = l_condition.branchCode;
        //リクエスト.失効対象注文条件.商品区分一覧
        String[] l_strProductList = l_condition.productDivList;
        //リクエスト.失効対象注文条件.銘柄コード
        String l_productCode = l_condition.productCode;
        //リクエスト.失効対象注文条件.顧客コード
        String l_strAccountCode = l_condition.accountCode;
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);

        //1.3 validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        //1.4 (*)処理起動リクエストの場合
        if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            //1.4.1 validate取引パスワード(パスワード : String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        //1.7 getオンライン実行結果一覧
        //1.8 二重起動チェック    
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        List l_lstOnlineRunStatus = this.getOnlineRunStatusList(l_strInstitutionCode);
        // オンライン実行結果レコードあり
        if (l_lstOnlineRunStatus != null)
        {
            //1.5 getデーモントリガー一覧()
            List l_lstDaemonTrigger = this.getDaemonTriggerList();
            // 件数が異なる場合
            if (l_lstOnlineRunStatus.size() != l_lstDaemonTrigger.size())
            {
                log.debug("件数不整合（オンライン実行結果の件数 != デーモントリガーの件数）");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "他の手動失効プロセスが起動中です。");
            }
            
            for (int i = 0; i < l_lstOnlineRunStatus.size(); i++)
            {
                OnlineRunStatusRow l_onlineRunStatusRow =
                    (OnlineRunStatusRow)l_lstOnlineRunStatus.get(i);
                // "処理中"が存在する場合
                if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    log.debug("オンライン実行結果に\"処理中\"のレコードあり");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "他の手動失効プロセスが起動中です。");
                }
            }
            
            for (int i = 0; i < l_lstDaemonTrigger.size(); i++)
            {
                DaemonTriggerRow l_daemonTriggerRow =
                    (DaemonTriggerRow)l_lstDaemonTrigger.get(i);
                // "未稼動"でない場合
                if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                {
                    log.debug("オンライン実行結果に\"処理中\"のレコードあり");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "他の手動失効プロセスが起動中です。");
                }
            }
        }
        
        //1.9 (*)取得した銘柄コード != nullの場合
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        if (l_productCode != null)
        {
            //1.9.1 get証券会社( )
            Institution l_institution = l_administrator.getInstitution();
            WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            
            //1.9.2  getProduct(arg0 : Institution, arg1 : String)
            try
            {
                l_productMgr.getProduct(l_institution, l_productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("条件に該当するデータが存在しない。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
        }
        
        //1.10 (*)取得した顧客コード != nullの場合
        if (l_strAccountCode != null)
        {
            //1.10.1 get顧客一覧(証券会社コード : String, 部店コード : 
            //String[], 顧客コード : String)
            WEB3AdminToDataManager.getAccountList(l_strInstitutionCode, l_strBranchCodes, l_strAccountCode);
        }
        
        //1.11 (*)ID直接指定（取得した注文ID != null）の場合
        OrderUnit[] l_orderUnits = null;
        if (l_strOrderId != null)
        {
            int l_intLength = l_strProductList.length;
            boolean l_blnIsEquity = false;
            for(int i = 0; i < l_intLength; i++)
            {
                //1.11.1 (*)取得した商品区分一覧に、"現物株式" or "信用取引"が含まれる場合
                if (WEB3CommodityDivDef.EQUITY.equals(l_strProductList[i])
                    || WEB3CommodityDivDef.MARGIN.equals(l_strProductList[i]))
                {
                    l_blnIsEquity = true;
                    break;
                }
                
                //1.11.2 取得した商品区分一覧に、"先物" or "オプション"が含まれる場合
                if (WEB3CommodityDivDef.FUTURE.equals(l_strProductList[i])
                    || WEB3CommodityDivDef.OPTION.equals(l_strProductList[i]))
                {
                    l_blnIsEquity = false;
                    break;
                }
            }
            
            String l_strOrderConditionType = null;
            OrderOpenStatusEnum l_orderOpenStatusEnum = null; 
            String l_strRequestType = null;
            
            //1.11.1.1 getOrderUnits(arg0 : long)
            if (l_blnIsEquity)
            {
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
                
                if (l_orderUnits == null || l_orderUnits.length == 0)
                {
                        log.debug("注文ＩＤに該当する注文単位が存在しません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "注文ＩＤに該当する注文単位が存在しません。");
                }
                
                EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_orderUnits[0].getDataSourceObject();
                //注文単位.注文有効状態
                l_orderOpenStatusEnum = l_orderUnits[0].getOrderOpenStatus();
                //注文単位.発注条件
                l_strOrderConditionType = l_row.getOrderConditionType();
                //注文単位.リクエストタイプ
                l_strRequestType = l_row.getRequestType();
            }
            
            //1.11.2.1 getOrderUnits(arg0 : long)
            if (l_blnIsEquity == false)
            {
                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManagerImpl = 
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                l_orderUnits = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_strOrderId));
                
                if (l_orderUnits == null || l_orderUnits.length == 0)
                {
                        log.debug("注文ＩＤに該当する注文単位が存在しません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "注文ＩＤに該当する注文単位が存在しません。");
                }
                
                IfoOrderUnitRow l_row = (IfoOrderUnitRow) l_orderUnits[0].getDataSourceObject();
                //注文単位.注文有効状態
                l_orderOpenStatusEnum = l_orderUnits[0].getOrderOpenStatus();
                //注文単位.発注条件
                l_strOrderConditionType = l_row.getOrderConditionType();
                //注文単位.リクエストタイプ
                l_strRequestType = l_row.getRequestType();
            }
            
            //1.11.3 (*)注文未発注チェック
            //(*)以下の条件のいずれかに該当する場合は、
            //「指定された注文は手動失効処理対象外」の例外をスローする。
            //①@注文単位.発注条件 == "DEFAULT"　@の場合
            //②注文単位.注文有効状態 == "クローズ"の場合
            //③注文単位.発注条件 == "W指値" かつ 注文単位.リクエストタイプ == "時価サーバ"の場合
            //※チェックに必要な注文単位の各項目値は、上記分岐内にて
            //取得しておくこと。
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
                || OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusEnum)
                || (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType)))
            {
                log.debug("指定された注文は手動失効処理対象外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02419,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定された注文は手動失効処理対象外です。");
            }
        }
        
        //1.11.4 取得した注文単位を返却する
        log.exiting(STR_METHOD_NAME);
        if (l_orderUnits != null && l_orderUnits.length > 0)
        {
            return l_orderUnits[0];
        }
        //1.12 nullを返却する
        return null;
    }
    
    /**
     * (create明細)<BR>
     * 引数の注文単位の注文明細を作成し、<BR>
     * レスポンスにセットする。<BR>
     * ※決済注文の場合は建株／建玉明細も作成する。<BR>
     * <BR>
     * １）　@条件注文種別を判別する。<BR>
     * 　@１－１）キャストした注文単位(*1).発注条件 == "逆指値"の場合<BR>
     * 　@　@条件注文種別 == "逆指値"<BR>
     * <BR>
     * 　@１－２）キャストした注文単位(*1).発注条件 == "W指値"の場合 <BR>
     * 　@　@条件注文種別 == "W指値" <BR>
     * <BR>
     * 　@１－３）上記以外は、「パラメータ不正」の例外をスローする。<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80017<BR>
     * <BR>
     * 　@※キャストした注文単位から判別に必要な項目を取得するようにし、<BR>
     * 　@　@上記条件文は重複させないこと。<BR>
     * <BR>
     * ２）　@注文明細を作成する。 <BR>
     * 　@２－１）株式注文単位の場合 <BR>
     * 　@（パラメータ.注文単位の型がEqtypeOrderUnit） <BR>
     * 　@　@２－１－１）　@取引カレンダコンテキストの値をセットし直す。 <BR>
     * 　@　@　@トリガー注文管理者データマネージャ.reset取引カレンダコンテキスト()をコールする。 <BR>
     * <BR>
     * 　@　@　@[reset取引カレンダコンテキスト()にセットするパラメータ] <BR>
     * 　@　@　@　@証券会社コード：　@パラメータ.証券会社コード <BR>
     * 　@　@　@　@銘柄タイプ：　@キャストした注文単位.銘柄タイプ <BR>
     * 　@　@　@　@部店ID：　@キャストした注文単位.部店ID <BR>
     * 　@　@　@　@市場ID：　@キャストした注文単位.市場ID <BR>
     * 　@　@　@　@銘柄ID：　@キャストした注文単位.銘柄ID <BR>
     * 　@　@　@　@受付時間区分：　@"株式・信用" <BR>
     * <BR>
     * 　@　@２－１－２）　@トリガー注文管理者・株式注文照会サービスImpl. <BR>
     * 　@　@　@create株式注文照会Unit一覧()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create株式注文照会Unit一覧()にセットするパラメータ] <BR>
     * 　@　@　@　@注文単位一覧：　@キャストした注文単位のみを要素とする配列 <BR>
     * 　@　@　@　@リクエストデータ：　@作成したリクエストデータ(*2) <BR>
     * <BR>
     * 　@　@２－１－３）　@決済注文の場合、建株明細を作成する。 <BR>
     * 　@　@（キャストした注文単位.注文カテゴリ == "信用返済注文"） <BR>
     * 　@　@拡張株式注文マネージャ.create建株明細ByOrder()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create建株明細ByOrder()にセットするパラメータ] <BR>
     * 　@　@　@　@注文単位ID：　@キャストした注文単位.注文単位ID <BR>
     * <BR>
     * 　@　@２－１－４）　@パラメータ.レスポンスに以下のプロパティをセットする。 <BR>
     * 　@　@　@株式注文明細 = ２－１－２）の戻り値 <BR>
     * 　@　@　@株式建株明細 = ２－１－３）の戻り値 <BR>
     * 　@　@　@　@※コールしていない場合はnullをセット。 <BR>
     * <BR>
     * 　@２－２）　@先物OP注文単位の場合 <BR>
     * 　@（パラメータ.注文単位の型がIfoOrderUnit） <BR>
     * 　@　@２－２－１）　@取引カレンダコンテキストの値をセットし直す。 <BR>
     * 　@　@　@トリガー注文管理者データマネージャ.reset取引カレンダコンテキスト()をコールする。 <BR>
     * <BR>
     * 　@　@　@[reset取引カレンダコンテキスト()にセットするパラメータ] <BR>
     * 　@　@　@　@証券会社コード：　@パラメータ.証券会社コード <BR>
     * 　@　@　@　@銘柄タイプ：　@キャストした注文単位.銘柄タイプ <BR>
     * 　@　@　@　@部店ID：　@キャストした注文単位.部店ID <BR>
     * 　@　@　@　@市場ID：　@キャストした注文単位.市場ID <BR>
     * 　@　@　@　@銘柄ID：　@キャストした注文単位.銘柄ID <BR>
     * 　@　@　@　@受付時間区分：　@"株価指数先物OP" <BR>
     * <BR>
     * 　@　@２－２－２）　@トリガー注文管理者・先物OP注文照会サービスImpl. <BR>
     * 　@　@　@create先物OP注文照会Unit一覧()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create先物OP注文照会Unit一覧()にセットするパラメータ] <BR>
     * 　@　@　@　@注文単位一覧：　@キャストした注文単位のみを要素とする配列 <BR>
     * 　@　@　@　@リクエストデータ：　@作成したリクエストデータ(*2) <BR>
     * <BR>
     * 　@　@２－２－３）　@決済注文の場合、建玉明細を作成する。 <BR>
     * 　@　@（キャストした注文単位.注文カテゴリ == "先物返済注文" or "OP返済注文"） <BR>
     * 　@　@OP注文マネージャ.create建玉明細ByOrder()をコールする。 <BR>
     * <BR>
     * 　@　@　@[create建玉明細ByOrder()にセットするパラメータ] <BR>
     * 　@　@　@　@注文ID：　@キャストした注文単位.注文ID <BR>
     * <BR>
     * 　@　@２－２－４）　@パラメータ.レスポンスに以下のプロパティをセットする。 <BR>
     * 　@　@　@先物OP注文明細 = ２－２－２）の戻り値 <BR>
     * 　@　@　@先物OP建玉明細 = ２－２－３）の戻り値 <BR>
     * 　@　@　@　@※コールしていない場合はnullをセット。 <BR>
     * <BR>
     * (*1)キャストした注文単位 <BR>
     * 　@パラメータ.注文単位をinstanceofにて判別し、 <BR>
     * 　@以下のいずれかにキャストする。 <BR>
     * 　@・EqtypeOrderUnit <BR>
     * 　@・IfoOrderUnit <BR>
     * <BR>
     * (*2)作成したリクエストデータ <BR>
     * 　@[株式注文単位の場合] <BR>
     * 　@　@トリガー注文管理者・株式注文照会リクエストオブジェクトを生成 <BR>
     * 　@[先物OP注文単位の場合] <BR>
     * 　@　@トリガー注文管理者・先物OP注文照会リクエストオブジェクトを生成 <BR>
     * <BR>
     * 　@生成したオブジェクトに以下のプロパティをセットする。 <BR>
     * <BR>
     * 　@リクエストデータ.条件注文種別 = １）にて判別した条件注文種別<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_response - (レスポンス)<BR>
     * トリガー注文管理者・手動失効確認レスポンスオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4407A834017B
     */
    protected void createUnit(
        String l_strInstitutionCode,
        OrderUnit l_orderUnit,
        WEB3AdminToManualLapseConfirmResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createUnit(String, OrderUnit, WEB3AdminToManualLapseConfirmResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        // １）　@条件注文種別を判別する。
        String l_orderConditionType = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        IfoOrderUnitRow l_ifoOrderUnitRow = null;
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_orderConditionType = l_eqtypeOrderUnitRow.getOrderConditionType();
        }
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_orderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
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
        
        String l_strOrderingConditionType = null;
        //　@１－１）キャストした注文単位(*1).発注条件 == "逆指値"の場合
        // 　@　@条件注文種別 == "逆指値"
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderConditionType)) 
        {
            l_strOrderingConditionType = WEB3TriggerOrderTypeDef.STOP;
        }
        //１－２）キャストした注文単位(*1).発注条件 == "W指値"の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderConditionType))
        {
            l_strOrderingConditionType = WEB3TriggerOrderTypeDef.W_LlIMIT;
        }
        //１－３）上記以外は、「パラメータ不正」の例外をスローする。
        else
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        // ２）　@注文明細を作成する。
        //　@２－１）株式注文単位の場合
        // 　@（パラメータ.注文単位の型がEqtypeOrderUnit）
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            // ２－１－１）　@取引カレンダコンテキストの値をセットし直す。
            // トリガー注文管理者データマネージャ.reset取引カレンダコンテキスト()
            //をコールする。
            WEB3AdminToDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                l_eqtypeOrderUnitRow.getProductType(),
                new Long(l_eqtypeOrderUnitRow.getBranchId()),
                new Long(l_eqtypeOrderUnitRow.getMarketId()),
                new Long(l_eqtypeOrderUnitRow.getProductId()),
                WEB3TradingTimeTypeDef.EQUITY);
            
            // ２－１－２）　@トリガー注文管理者・株式注文照会サービスImpl.
            // 　@create株式注文照会Unit一覧()をコールする。
            WEB3AdminToEquityOrderReferenceServiceImpl l_serviceImpl =  new WEB3AdminToEquityOrderReferenceServiceImpl();
            EqTypeOrderUnit[] l_eqTypeOrderUnits = new EqTypeOrderUnit[1];
            l_eqTypeOrderUnits[0] = (EqTypeOrderUnit) l_orderUnit;
            WEB3AdminToEquityOrderRefRefRequest l_request = new WEB3AdminToEquityOrderRefRefRequest();
            l_request.triggerOrderType = l_strOrderingConditionType;
            WEB3AdminToEquityOrderRefUnit[] l_orderRefUnits = 
                l_serviceImpl.createEquityOrderRefUnitList(l_eqTypeOrderUnits, l_request);
            
            // ２－１－３）　@決済注文の場合、建株明細を作成する。
            //　@　@（キャストした注文単位.注文カテゴリ == "信用返済注文"）
            WEB3MarginContractUnit[] l_contractUnits = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnits[0].getOrderCateg()))
            {      
                //拡張株式注文マネージャ.create建株明細ByOrder()
                // 　@　@をコールする。
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                l_contractUnits = l_orderManager.createContractUnitByOrder(l_eqTypeOrderUnits[0].getOrderUnitId());
            }
            
            // ２－１－４）　@パラメータ.レスポンスに以下のプロパティをセットする。
            //　@　@株式注文明細 = ２－１－２）の戻り値
            //   ※コールしていない場合はnullをセット。
            if (l_orderRefUnits != null && l_orderRefUnits.length != 0)
            {
                l_response.equityOrderUnit = l_orderRefUnits[0];
            }
            //　@　@株式建株明細 = ２－１－３）の戻り値
            if (l_contractUnits != null && l_contractUnits.length != 0)
            {
                l_response.equityContractUnits = l_contractUnits;
            }
        }       
        //　@２－２）　@先物OP注文単位の場合
        //　@（パラメータ.注文単位の型がIfoOrderUnit）
        else
        {
            //２－２－１）　@取引カレンダコンテキストの値をセットし直す。
            // トリガー注文管理者データマネージャ.reset取引カレンダコンテキスト()
            // をコールする。
            WEB3AdminToDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                new Long(l_ifoOrderUnitRow.getBranchId()),
                new Long(l_ifoOrderUnitRow.getMarketId()),
                new Long(l_ifoOrderUnitRow.getProductId()),
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //２－２－２）　@トリガー注文管理者・先物OP注文照会サービスImpl.
            //create先物OP注文照会Unit一覧()をコールする。
            WEB3AdminToIfoOrderReferenceServiceImpl l_serviceImpl = new WEB3AdminToIfoOrderReferenceServiceImpl();
            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            l_request.triggerOrderType = l_strOrderingConditionType;
            IfoOrderUnit[] l_ifoOrderUnits = new IfoOrderUnit[1];
            l_ifoOrderUnits[0] = (IfoOrderUnit) l_orderUnit;
            WEB3AdminToIfoOrderRefUnit[] l_refUnits = 
                l_serviceImpl.createIfoOrderRefUnitList(l_ifoOrderUnits, l_request);
            
            //２－２－３）　@決済注文の場合、建玉明細を作成する。
            // （キャストした注文単位.注文カテゴリ == "先物返済注文" or "OP返済注文"）
            WEB3FuturesOptionsContractUnit[] l_contractUnits = null;
            if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_ifoOrderUnits[0].getOrderCateg())
                || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnits[0].getOrderCateg()))
            {
                //　@OP注文マネージャ.create建玉明細ByOrder()をコールする。
                //　@　@　@[create建玉明細ByOrder()にセットするパラメータ]
                //　@　@　@　@注文ID：　@キャストした注文単位.注文ID
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManagerImpl =
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                l_contractUnits = l_orderManagerImpl.createContractUnitByOrder(l_ifoOrderUnits[0].getOrderId());
            }
            
            // ２－２－４）　@パラメータ.レスポンスに以下のプロパティをセットする。
            //　@先物OP注文明細 = ２－２－２）の戻り値
            //※コールしていない場合はnullをセット
            if (l_refUnits != null && l_refUnits.length != 0)
            {
                l_response.ifoOrderUnit = l_refUnits[0];
            }
            //　@ 先物OP建玉明細 = ２－２－３）の戻り値
            if (l_contractUnits != null && l_contractUnits.length != 0)
            {
                l_response.ifoContractUnits = l_contractUnits;
            }
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
     * 　@　@処理タイプ = "手動失効（Eqtype／Ifo）"<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@　@スレッド番号　@昇順<BR>
     * <BR>
     * 　@該当データなしの場合、「該当データなし」の<BR>
     * 　@システムエラーをスローする。<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80005<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 440BFEFC024A
     */
    protected List getDaemonTriggerList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDaemonTriggerList()";
        log.entering(STR_METHOD_NAME);

        //１）　@DB検索
        // 　@以下の条件でデーモントリガーテーブルを検索する。
        // 　@[条件]
        // 　@　@処理タイプ = "手動失効（Eqtype／Ifo）"
        String l_strCondition = "order by thread_no asc";
        String[] l_strBindValues = {WEB3DaemonTriggerTypeDef.EQTYPE_IFO_TRIGGER_MANUAL_EXPIRE};
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                l_strBindValues);
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
     * 　@　@銘柄タイプ = "その他"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@オンラインサービス区分 = "手動失効"<BR>
     * <BR>
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 440E8B73019E
     */
    protected List getOnlineRunStatusList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOnlineRunStatusList(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@DB検索
        // 　@以下の条件でオンライン実行結果テーブルを検索する。
        // 　@[条件]
        // 　@　@証券会社コード = パラメータ.証券会社コード
        // 　@　@銘柄タイプ = "その他"
        // 　@　@先物／オプション区分 = "DEFAULT"
        // 　@　@オンラインサービス区分 = "手動失効"
        String l_strWhere= " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(String.valueOf(ProductTypeEnum.OTHER.intValue()));
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        String[] l_strBindValues = new String[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_strBindValues);
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_strBindValues);
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
     * 　@　@銘柄タイプ = "その他"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@オンラインサービス区分 = "手動失効"<BR>
     * <BR>
     * 　@※削除対象のレコードがなくても例外としないこと。<BR>
     * 　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441627100082
     */
    protected void deleteOnlineRunStatus(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteOnlineRunStatus(String)";
        log.entering(STR_METHOD_NAME);

        // １）　@以下の条件に該当するオンライン実行結果テーブルの
        // 　@レコードをdeleteする。
        // 　@[条件]
        // 　@　@証券会社コード = パラメータ.証券会社コード
        // 　@　@銘柄タイプ = "その他"
        // 　@　@先物／オプション区分 = "DEFAULT"
        // 　@　@オンラインサービス区分 = "手動失効"
        final String l_strWhere= " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(String.valueOf(ProductTypeEnum.OTHER.intValue()));
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        final String[] l_strBindValues = new String[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_strBindValues);
        
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
                                l_strBindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow) l_lisRows.get(i);
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
     * (updateAP呼出中)
     *
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

     * @@param l_lngThreadNo - (スレッドNo)
     * スレッドNo
     * @@throws WEB3BaseException
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateApCalling(l_lngThreadNo)";
        log.entering(STR_METHOD_NAME);

        final String l_strWhere= " thread_no = ?";
        final String l_strCondition = "for update";
        final Object[] l_bindValues = {new Long(l_lngThreadNo)};

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
                                DaemonTriggerRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                DaemonTriggerParams l_params = new DaemonTriggerParams();
                                GtlUtils.copyRow2Params((DaemonTriggerRow)l_lisRows.get(i), l_params);
                                l_params.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
                                WEB3DataAccessUtility.updateRow(l_params);
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
     * (getServerAccessor)
     *
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
     *
     * @@return ServerAccessor
     * @@throws WEB3BaseException
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accessor != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.accessor;
        }

        // １）クラスタリング先サーバーURLを取得する。
        // 　@　@QueryProcessor.doFindAllQuery()メソッドを
        // 　@　@コールする。
        String[] l_strBindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                ServerConfigRow.TYPE,
                " config_categ = ?",
                l_strBindValues);
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
        
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        // ２）　@ServerAccessorの作成
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
        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
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
        return l_serverAccessor;
    }
}
@
