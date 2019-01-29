head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来約定取消サービスImpl(WEB3AdminFeqCancelExecutionServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 鄭海良(中訊) 新規作成
                 : 2005/08/01 韋念瓊(中訊) レビュー       
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.469
Revesion History : 2009/08/03 武波(中訊 【外国株式】仕様変更管理台帳（モデル）No.508
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqCancelExecutionService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式出来約定取消サービスImpl)<BR>
 * 管理者外国株式出来約定取消サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionServiceImpl 
    implements WEB3AdminFeqCancelExecutionService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F10222
     */
    public WEB3AdminFeqCancelExecutionServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式出来約定取消処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get入力画面()<BR>
     * －validate取消()<BR>
     * －submit取消()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942ECB03C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqCancelExecutionInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqCancelExecutionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCancelExecutionConfirmRequest)
        {
            //validate取消()
            l_response = 
                this.validateCancel((WEB3AdminFeqCancelExecutionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCancelExecutionCompleteRequest)
        {
            //submit取消()
            l_response =
                this.submitCancel((WEB3AdminFeqCancelExecutionCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（出来約定取消）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0334
     */
    public WEB3AdminFeqCancelExecutionInputResponse getInputScreen(
        WEB3AdminFeqCancelExecutionInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqCancelExecutionInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqCancelExecutionInputResponse l_response = 
            (WEB3AdminFeqCancelExecutionInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate取消)<BR>
     * 出来約定取消確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（出来約定取消）validate取消」 参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR> 
     * 出来約定取消」(出来約定取消）validate取消)<BR>
     * 　@　@:  1.6 出来終了処理済みの場合（is出来終了() == true）、<BR> 
     * 例外をスローする。<BR> 
     * 　@　@出来終了処理済みの場合（is出来終了() == true）、<BR> 
     * 例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02164<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0336
     */
    public WEB3AdminFeqCancelExecutionConfirmResponse validateCancel(
        WEB3AdminFeqCancelExecutionConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateCancel(WEB3AdminFeqCancelExecutionConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //get証券会社コード
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get運用コード(証券会社コード : String, 運用コード : String)
        //証券会社コード： get証券会社コード（）の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);
        //1.4 get注文単位By運用コード(Date, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datBizDate = l_request.orderBizDate;
        if (l_datBizDate == null)
        {
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            l_datBizDate = WEB3DateUtility.toDay(l_tradingSystem.getSystemTimestamp());
        }
        WEB3FeqOrderUnit l_orderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderEmpCode(
                l_datBizDate, 
                l_strEmpCode);//WEB3BaseException
        if (l_orderUnit == null)
        {
            String l_strMessage = "get注文単位By運用コード(" 
                + l_datBizDate 
                + ", " 
                + l_request.managementCode 
                + ")がnull。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 is出来終了( )
        boolean l_blnExecEnd = l_orderUnit.isExecEnd();
        
        //1.6 出来終了処理済みの場合（is出来終了() == true）、例外をスローする
        if (l_blnExecEnd)
        {
            //1.6.1 （例外）
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02164,
                this.getClass().getName() + STR_METHOD_NAME,
                "出来終了処理済みなので、出来約定取消不可です。");
        }
        
        // 約定処理中の場合、例外をスローする。
        // （注文単位.get約定状態区分( ) == "3：約定処理中（一部成立）" or ”4：約定処理中（全部成立）”）
        if (l_orderUnit.getExecStatusDiv().equals(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE) 
            || l_orderUnit.getExecStatusDiv().equals(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03134,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定処理中の場合、出来約定取消不可です。");
        }
        
        //1.7 get有効約定By約定番号(long, int)
        int l_intExecNo = Integer.parseInt(l_request.execNo);
        WEB3FeqOrderExecution l_orderExec = 
            (WEB3FeqOrderExecution)l_orderManager.getValidExecByExecNo(
                l_orderUnit.getOrderUnitId(), 
                l_intExecNo);//WEB3BaseException
        
        //1.8 getトランザクション(FeqOrderExecution)
        WEB3FeqFinTransactionManager l_finTransactionManager = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        if (l_finTransactionManager == null)
        {
            String l_strMessage = "外国株式TransactionManagerが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        FeqFinTransactionParams l_feqFinTranParam = l_finTransactionManager.getTransaction(l_orderExec);//WEB3BaseException
            
        //1.9 外国株式約定入力情報( )
        WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecUnit = new WEB3FeqOrderAndExecutionUnit();

        //1.10 create外国株式約定入力情報(...)
        WEB3FeqCommonMessageCreatedService l_service = 
            new WEB3FeqCommonMessageCreatedServiceImpl();
        l_service.createFeqOrderAndExecutionUnit(
            l_feqOrderAndExecUnit,
            l_orderUnit,
            l_orderExec,
            l_feqFinTranParam);
        
        //1.11 createResponse()
        WEB3AdminFeqCancelExecutionConfirmResponse l_response = 
            (WEB3AdminFeqCancelExecutionConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "レスポンスがnullです。");
        }    

        //1.12 プロパティセット
        l_response.orderAndExecutionUnit = l_feqOrderAndExecUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit取消)<BR>
     * 出来約定取消完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（出来約定取消）submit取消」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0338
     */
    public WEB3AdminFeqCancelExecutionCompleteResponse submitCancel(
        WEB3AdminFeqCancelExecutionCompleteRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitCancel(WEB3AdminFeqCancelExecutionCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.5 update約定取消(外株出来通知キューParams, long)
        WEB3FeqOrderAndExecutionUpdateService l_feqOrderAndExecutionUpdateService = 
            (WEB3FeqOrderAndExecutionUpdateService )Services.getService(WEB3FeqOrderAndExecutionUpdateService.class);
        long l_lngExecId = Long.parseLong(l_request.execId); 
        l_feqOrderAndExecutionUpdateService.updateExecuteCancel(null, l_lngExecId); 
        
        //1.6 getOrderExecution(arg0 : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "外国株式TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderExecution l_feqOrderExecution = null;
        try
        {
            l_feqOrderExecution = 
                (WEB3FeqOrderExecution)l_orderManager.getOrderExecution(l_lngExecId);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "外国株式約定が存在しない。[約定ID = " + l_lngExecId + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqOrderExecution == null)
        {
            String l_strMessage = "外国株式約定が存在しない。[約定ID = " + l_lngExecId + " ]";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.7 updateトランザクション(long, boolean)
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        if (l_feqPositionManager == null)
        {
            String l_strMessage = "外国株式ポジションマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        l_feqPositionManager.updateTransaction(l_feqOrderExecution.getOrderUnitId(), true);
        
        //1.8 update概算受渡代金(外国株式注文単位, Date)
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        try
        {
            l_feqOrderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_feqOrderExecution.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "外国株式注文単位が存在しない。[注文単位ID = " 
                + l_feqOrderExecution.getOrderUnitId() 
                + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "外国株式注文単位が存在しない。[注文単位ID = " 
                + l_feqOrderExecution.getOrderUnitId() 
                + " ]";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datExecDate = WEB3DateUtility.toDay(l_feqOrderExecution.getExecutionTimestamp());
        l_orderManager.updateEstimatedPrice(l_feqOrderUnit, l_datExecDate);
         
        //1.9 余力再計算(補助口座 : 補助口座)
        WEB3TPTradingPowerReCalcService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_web3TPTradingPowerService.reCalcTradingPower(l_feqOrderUnit.getSubAccount());//WEB3SystemLayerException

        //1.10 createResponse
        WEB3AdminFeqCancelExecutionCompleteResponse l_response = 
            (WEB3AdminFeqCancelExecutionCompleteResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "レスポンスがnullです。");
        }    

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
