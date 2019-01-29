head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了サービスImpl(WEB3AdminFeqExecutionEndServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 郭英 (中訊) 新規作成
Revesion History : 2005/08/03 黄建(中訊) レビュー
Revesion History : 2007/02/27 斉珂 (中訊) 実装002 本番問合No.Q00144
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.FeqOrderexecutionEndDao;
import webbroker3.feq.data.FeqOrderexecutionEndParams;
import webbroker3.feq.data.FeqOrderexecutionEndRow;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndInputResponse;
import webbroker3.feq.message.WEB3FeqExecutionEndExecuteCondUnit;
import webbroker3.feq.message.WEB3FeqExecutionEndUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来終了サービスImpl)<BR>
 * 外国株式出来終了サービス実装クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndServiceImpl implements WEB3AdminFeqExecutionEndService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F002BF
     */
    public WEB3AdminFeqExecutionEndServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式出来終了サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコール<BR>する。
     * <BR>
     *    get入力画面()<BR>
     *    validate出来終了()<BR>
     *    submit出来終了()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A945400A5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqExecutionEndInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqExecutionEndInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecutionEndConfirmRequest)
        {
            //validate出来終了()
            l_response = 
                this.validateExecEnd((WEB3AdminFeqExecutionEndConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecutionEndCompleteRequest)
        {
            //submit出来終了()
            l_response = 
                this.submitExecEnd((WEB3AdminFeqExecutionEndCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図「（（管）出来終了）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D303C2
     */
    protected WEB3AdminFeqExecutionEndInputResponse getInputScreen(
        WEB3AdminFeqExecutionEndInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqExecutionEndInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.debug("管理者のログイン情報が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.2:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4:get取扱可能市場(String, ProductTypeEnum)(
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.5: create出来終了一覧(String, String[])
        WEB3FeqExecutionEndUnit[] l_ExecEndUnits = 
            this.createExecEndList(l_strInstitutionCode, l_strHandlingPossMarkets);
        
        //1.6:createResponse( )
        WEB3AdminFeqExecutionEndInputResponse l_response = 
            (WEB3AdminFeqExecutionEndInputResponse)l_request.createResponse();
        
        //1.7:(*)プロパティセット
        //(*)レスポンスデータに以下のプロパティをセットする。
        //出来終了一覧  ＝　@create出来終了一覧()の戻り値
        l_response.executionEndList = l_ExecEndUnits;
        
        log.exiting(STR_METHOD_NAME);
        
        //1.8
        return l_response;
    }
    
    /**
     * (validate出来終了)<BR>
     * 出来終了処理の確認を行う。<BR>
     * <BR>
     * シーケンス図「（（管）出来終了）validate出来終了」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）出来終了」(（管）出来終了）validate出来終了)<BR>
     * 　@　@:  1.5.1 get外株出来終了<BR> 
     * 　@　@nullが返却された場合は、<BR> 
     * 　@　@「該当データなし」の例外<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01037<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）出来終了」(（管）出来終了）validate出来終了)<BR>
     * 　@　@:  1.5.3 is出来終了可能<BR> 
     * 　@　@falseが返却された場合、<BR> 
     * 　@　@「発注日エラー」の例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02160<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D303E1
     */
    protected WEB3AdminFeqExecutionEndConfirmResponse validateExecEnd(
        WEB3AdminFeqExecutionEndConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateExecEnd(WEB3AdminFeqExecutionEndConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            log.debug("管理者のログイン情報が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.3:validate権限(機@能カテゴリコード : String, is更新 : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5:(*)リクエストデータ.出来終了実施一覧の要素数分Loop処理
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {            
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
                
                if (l_execEndCondUnit != null)
                {
                    //1.5.1:get外株出来終了(String, String[])
                    FeqOrderexecutionEndParams[] l_orderExecEndParamses = 
                        this.getFeqExecEnd(l_strInstitutionCode, 
                        new String[]{l_execEndCondUnit.marketCode});
                    
                    if (l_orderExecEndParamses == null || l_orderExecEndParamses.length == 0)
                    {
                        log.debug("条件に該当する外株出来終了が存在しない。");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "条件に該当する外株出来終了が存在しない。");
                    }
                    else if(l_orderExecEndParamses.length != 1)
                    {
                        log.debug("外株出来終了がデータ不整合エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    
                    //1.5.2:(*)処理区分チェック
                    //get外株出来終了()の戻り値.処理区分 == "エラー"の場合 かつ
                    //処理対象の発注日 == 外株出来終了.前回実施日のYYYYMMDDの場合、
                    //次の要素へ処理を移行する。(continue)
                    //※リラン可能とする為。
                    boolean l_blnFlag = WEB3CarryoverEndTypeDef.ERROR.equals(
                        l_orderExecEndParamses[0].getStatus());
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    Date l_datLastExec = l_orderExecEndParamses[0].getLastExecuteDate();
                    int l_intFlag = WEB3DateUtility.compareToDay(
                        l_datOrderBizDate,
                        l_datLastExec);   
                        
                    if (l_blnFlag && l_intFlag == 0)
                    {
                        continue;
                    }                    
                    
                    //1.5.3: is出来終了可能(Date, Date)
                    boolean l_blnIsExecEndPoss = this.isExecEndPossible(
                        l_datLastExec,
                        l_datOrderBizDate);
                        
                    //falseが返却された場合、
                    //「発注日エラー」の例外をスローする。
                    if (!l_blnIsExecEndPoss)
                    {
                        log.debug("発注日エラー。");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "発注日エラー。");
                    }
                }
            }
        }
        
        //1.6:createResponse( )
        WEB3AdminFeqExecutionEndConfirmResponse l_response = 
            (WEB3AdminFeqExecutionEndConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        //1.7
        return l_response;
    }
    
    /**
     * (submit出来終了)<BR>
     * 出来終了処理を行う。<BR>
     * <BR>
     * シーケンス図「（（管）出来終了）submit出来終了」 参照<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）出来終了」(（管）出来終了）submit出来終了)<BR>
     * 　@　@:  1.6.3 is出来終了可能<BR> 
     * 　@　@falseが返却された場合、<BR> 
     * 　@　@「発注日エラー」の例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02160<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D40009
     */
    protected WEB3AdminFeqExecutionEndCompleteResponse submitExecEnd(
        WEB3AdminFeqExecutionEndCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitExecEnd(WEB3AdminFeqExecutionEndCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug( "管理者のログイン情報が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                 "管理者のログイン情報が存在しない。");
        }
        
        //1.3:validate権限(機@能カテゴリコード : String, is更新 : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:(*)リクエストデータ.出来終了実施一覧の要素数分Loop処理
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {            
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
                
                if (l_execEndCondUnit != null)
                {
                    //1.6.1:get外株出来終了(String, String[])
                    
                    FeqOrderexecutionEndParams[] l_orderExecEndParamses = 
                        this.getFeqExecEnd(l_strInstitutionCode, 
                        new String[]{l_execEndCondUnit.marketCode});

                    //nullが返却された場合は、
                    //次の要素へ処理を移行する。(continue)
                    if (l_orderExecEndParamses == null || l_orderExecEndParamses.length == 0)
                    {                        
                        continue;
                    }
                    else if(l_orderExecEndParamses.length != 1)
                    {
                        log.debug("外株出来終了がデータ不整合エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    
                    //1.6.2:(*)処理区分チェック
                    //get外株出来終了()の戻り値.処理区分 == "エラー"の場合 かつ
                    //処理対象の発注日 == 外株出来終了.前回実施日のYYYYMMDDの場合、
                    //次の要素へ処理を移行する。(continue)
                    //※リラン可能とする為。
                    boolean l_blnFlag = WEB3CarryoverEndTypeDef.ERROR.equals(
                        l_orderExecEndParamses[0].getStatus());
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    Date l_datLastExec = l_orderExecEndParamses[0].getLastExecuteDate();
                    int l_intFlag = WEB3DateUtility.compareToDay(
                        l_datOrderBizDate,
                        l_datLastExec);
                        
                    if (l_blnFlag && l_intFlag == 0)
                    {
                        continue;
                    }  
                    
                    //1.6.3: is出来終了可能(Date, Date)
                    boolean l_blnIsExecEndPoss = this.isExecEndPossible(
                        l_datLastExec,
                        l_datOrderBizDate);
                    
                    //falseが返却された場合、
                    //「発注日エラー」の例外をスローする。
                    if (!l_blnIsExecEndPoss)
                    {
                        log.debug("発注日エラー。");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "発注日エラー。");
                    }
                }
            }
        }
        
        //1.7:(*)リクエストデータ.出来終了実施一覧の要素数分Loop処理
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {                        
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
        
                if (l_execEndCondUnit != null)
                {
                    String l_strMarketCode = l_execEndCondUnit.marketCode;
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    
                    //1.7.1:get処理対象顧客一覧(String, String, Date)
                    WEB3GentradeMainAccount[] l_mainAccounts = this.getDealTargetAccountList(
                        l_strInstitutionCode, 
                        l_strMarketCode, 
                        l_datOrderBizDate);
                        
                    int l_intDealCnt = 0;
                        
                    //1.7.2: (*)get処理対象顧客一覧()の要素数分Loop処理
                    if (l_mainAccounts != null && l_mainAccounts.length > 0)
                    {
                        int l_intMainAccountCnt = l_mainAccounts.length;
                                                                        
                        for (int j = 0; j < l_intMainAccountCnt; j++)
                        {
                            WEB3GentradeMainAccount l_mainAccount = l_mainAccounts[j];
                            
                            //1.7.2.1:exec出来終了(顧客, String, Date)
                            WEB3AdminFeqExecutionEndUnitService l_execEndUnitService = 
                                (WEB3AdminFeqExecutionEndUnitService)Services.getService(
                                    WEB3AdminFeqExecutionEndUnitService.class);
                            
                            if (l_execEndUnitService == null)
                            {
                                log.debug("外国株式出来終了UnitServiceImplが存在しない。");
                                log.exiting(STR_METHOD_NAME);
                                throw new WEB3SystemLayerException(
                                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                     this.getClass().getName() + STR_METHOD_NAME,
                                    "外国株式出来終了UnitServiceImplが存在しない。");
                            }
                            
                            try
                            {
                                l_execEndUnitService.execExecEnd(
                                    l_mainAccount, 
                                    l_strMarketCode, 
                                    l_datOrderBizDate);
                            }
                            //例外がスローされた場合、次の顧客へ処理を移行する。(continue)
                            catch (Exception l_ex)
                            {
                                log.error("exec出来終了(顧客コード: " + l_mainAccount.getAccountCode()
                                    + " 市場コード: " + l_strMarketCode
                                    + " 発注日: " + l_datOrderBizDate + ")",
                                    l_ex);

                                l_intDealCnt++;
                                continue;
                            }
                        }
                    }
                    //1.7.3:update外株出来終了(String, String, Date, String)
                    String l_strStatus = null;
                    //[exec出来終了()にて一件も例外がスローされなかった場合] 
                    if (l_intDealCnt == 0)
                    {
                        //"1：処理済"をセット 
                        l_strStatus = WEB3CarryoverEndTypeDef.COMPLETE_PROCESS;
                    }
                    //[上記以外] 
                    else
                    {
                        // "9：エラー"をセット
                        l_strStatus = WEB3CarryoverEndTypeDef.ERROR;
                    }
                    this.updateFeqExecEnd(l_strInstitutionCode, 
                        l_strMarketCode,
                        l_datOrderBizDate,
                        l_strStatus);
                }
            }
        }
        
        //1.8: get取扱可能市場(String, ProductTypeEnum)
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.9:get外株出来終了(String, String[])
        FeqOrderexecutionEndParams[] l_paramses = this.getFeqExecEnd(
            l_strInstitutionCode, 
            l_strHandlingPossMarkets);
                    
        if (l_paramses == null || l_paramses.length == 0)
        {
            log.debug("条件に該当する外株出来終了が存在しない。");
            log.exiting(STR_METHOD_NAME);                        
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME, 
                "条件に該当する外株出来終了が存在しない。");
        }
       
        //1.10:(*)get外株出来終了()の全要素.処理区分 == "処理済"の場合
        boolean l_blnIsAll = false;
        if (l_paramses != null && l_paramses.length > 0)
        {
            int l_intParamsCnt = l_paramses.length;
            
            for (int i = 0; i < l_intParamsCnt; i++)
            {               
                FeqOrderexecutionEndParams l_params = l_paramses[i]; 
                if (l_params != null)
                {
                    if (WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_params.getStatus()))
                    {
                        l_blnIsAll = true;                
                    }
                    else 
                    {
                        l_blnIsAll = false;                        
                        break;            
                    } 
                }               
            }
        }        
        
        if (l_blnIsAll)
        {            
            //1.10.1: (*)出来終了Paramsを生成する。
            //(*)出来終了Paramsを生成し、プロパティをセットする。
            //セットするプロパティは、
            //【xTrade】補足資料.DB更新\\20.(管)出来終了「外株出来終了_出来終了テーブル仕様.xls」参照。
            OrderexecutionEndParams l_orderExceEndParams = new OrderexecutionEndParams();
            
            //institution_code:管理者.証券会社コード
            l_orderExceEndParams.setInstitutionCode(l_strInstitutionCode);
            
            //product_type:4：外国株式
            l_orderExceEndParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //future_option_div:0：DEFAULT
            l_orderExceEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            
            //carryover_end_type:0：未処理
            l_orderExceEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS);
            
            //created_timestamp:現在時刻
            Timestamp l_tsNowTime = GtlUtils.getSystemTimestamp();
            
            l_orderExceEndParams.setCreatedTimestamp(l_tsNowTime);
            
            //last_updated_timestamp:現在時刻
            l_orderExceEndParams.setLastUpdatedTimestamp(l_tsNowTime);
            
            //1.10.2:insertRow(l_row : Row)
            try
            {
                WEB3DataAccessUtility.insertRow(
                    l_orderExceEndParams);//DataNetworkException, DataQueryException
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
        }

        
        //1.11:createResponse( )
        WEB3AdminFeqExecutionEndCompleteResponse l_response = 
            (WEB3AdminFeqExecutionEndCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        //1.12
        return l_response;
    }
    
    /**
     * (get外株出来終了)<BR>
     * 引数の証券会社コード、市場コードに該当する行を<BR>
     * 外株出来終了テーブルより取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件にて外株出来終了テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード かつ<BR>
     * 　@　@市場コード in (パラメータ.市場コード)<BR>
     * <BR>
     * ※検索結果は、市場コードで昇順ソートし、取得すること。<BR>
     * <BR> 
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@取得した検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCodes - (市場コード)<BR>
     * 市場コードの一覧<BR>
     * @@return FeqOrderexecutionEndParams[]
     * @@throws WEB3BaseException
     * @@roseuid 42AFD97900C3
     */
    protected FeqOrderexecutionEndParams[] getFeqExecEnd(
        String l_strInstitutionCode, 
        String[] l_strMarketCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFeqExecEnd(String, String[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_strMarketCodes == null || l_strMarketCodes.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "市場コードが未指定です。");
        }
        
        try
        {
            //１）　@DB検索
            //以下の条件にて外株出来終了テーブルを検索する。
            //[条件]
            //証券会社コード = パラメータ.証券会社コード かつ
            //市場コード in (パラメータ.市場コード)
            String l_strWhere = " institution_code = ? and market_code in (";
            
            int l_intCnt = l_strMarketCodes.length;
            
            Object[] l_objs = new Object[l_intCnt + 1];
            
            l_objs[0] = l_strInstitutionCode;
                
            for (int i = 0; i < l_intCnt; i++)
            {
                l_strWhere += "?, ";
                l_objs[i + 1] = l_strMarketCodes[i];
            }
            
            l_strWhere = l_strWhere.substring(0, l_strWhere.length() - 2) + ") ";
            
            //※検索結果は、市場コードで昇順ソートし、取得すること。
            String l_strOrderby = "market_code";
            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisOrderexecEndRows = l_queryProcessor.doFindAllQuery(
                FeqOrderexecutionEndRow.TYPE, 
                l_strWhere,
                l_strOrderby,
                null,
                l_objs);//DataNetworkException, DataQueryException
            
            //該当データなしの場合、nullを返却する。
            //２）　@取得した検索結果を返却する。
            FeqOrderexecutionEndParams[] l_params = null;
            if (l_lisOrderexecEndRows != null && !l_lisOrderexecEndRows.isEmpty())
            {
                int l_intRowsCnt = l_lisOrderexecEndRows.size();
                l_params = new FeqOrderexecutionEndParams[l_intRowsCnt];
                for (int i = 0; i < l_intRowsCnt; i++)
                {
                    l_params[i] = new FeqOrderexecutionEndParams(
                        (FeqOrderexecutionEndRow)l_lisOrderexecEndRows.get(i));
                }                
            }            
            return l_params;
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
    }
    
    /**
     * (create出来終了一覧)<BR>
     * 引数の証券会社コード、市場コード一覧より、<BR>
     * 外国株式出来終了明細の一覧を作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@this.get外株出来終了()をコールする。<BR>
     * <BR>
     * 　@　@[get外株出来終了()に指定する引数]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@市場コード：　@パラメータ.市場コード一覧 <BR>
     * <BR>
     * ３）　@２）の戻り値の要素数分以下の処理を繰り返す。 <BR>
     *  ３－１）　@外国株式出来終了明細インスタンスを生成する。<BR>
     *  ３－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>           
     * 　@　@　@市場コード = 処理対象の要素.市場コード<BR>
     * 　@　@　@市場名 = 処理対象の要素.市場コードに該当する市場.市場名<BR>
     * 　@　@　@処理区分 = 処理対象の要素.処理区分<BR>
     * 　@　@　@前回発注日 = 処理対象の要素.前回実施日 <BR>
     * 　@　@　@前回出来終了日時 = 処理対象の要素.更新日付 <BR>
     * <BR>
     * 　@２－５）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCodeList - (市場コード一覧)<BR>
     * 市場コード一覧<BR>
     * @@return WEB3FeqExecutionEndUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42AFDEE703C0
     */
    protected WEB3FeqExecutionEndUnit[] createExecEndList(
        String l_strInstitutionCode, 
        String[] l_strMarketCodeList) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createExecEndList(String, String[]) ";
        log.entering(STR_METHOD_NAME);
        
        
            //１）　@ArrayListを生成する。
            List l_lisExecEndUnit = new ArrayList();
        
            //２） this.get外株出来終了()をコールする。
            FeqOrderexecutionEndParams[] l_orderExecEndPraramses = 
                this.getFeqExecEnd(l_strInstitutionCode, l_strMarketCodeList); 
            if (l_orderExecEndPraramses == null)
            {
                log.error("外株出来終了テーブルが存在しない");

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外株出来終了テーブルが存在しない" );
            }
           
            //３）　@２）の戻り値の要素数分以下の処理を繰り返す。
            if (l_orderExecEndPraramses != null && l_orderExecEndPraramses.length > 0)
            {
                int l_intCnt = l_orderExecEndPraramses.length;
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug("FinAppが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinAppが存在しない。");
                }
    
                WEB3GentradeFinObjectManager l_finObjectMgr =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                if (l_finObjectMgr == null)
                {
                    log.debug("FinObjectManagerが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinObjectManagerが存在しない。");
                }
    
                for (int i = 0; i < l_intCnt; i++)
                {
                    if (l_orderExecEndPraramses[i] != null)
                    {
                        FeqOrderexecutionEndParams l_orderExecEndPrarams = l_orderExecEndPraramses[i];
        
                        //３－１）　@外国株式出来終了明細インスタンスを生成する。
                        WEB3FeqExecutionEndUnit l_executionEndUnit = new WEB3FeqExecutionEndUnit();
    
                        //３－２）　@生成したインスタンスに以下のプロパティをセットする。
                        //市場コード = 処理対象の要素.市場コード
                        l_executionEndUnit.marketCode = l_orderExecEndPrarams.getMarketCode();
                        try
                        {
                        //市場名 = 処理対象の要素.市場コードに該当する市場.市場名
                        Market l_market = l_finObjectMgr.getMarket(
                            l_strInstitutionCode, 
                            l_orderExecEndPrarams.getMarketCode());//NotFoundException
                            
                            l_executionEndUnit.marketName = l_market.getMarketName();
                        } 
                        catch (NotFoundException l_ex)
                        {
                            log.error("市場テーブルに該当するデータがありません。", l_ex);

                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }        

                        //処理区分 = 処理対象の要素.処理区分
                        l_executionEndUnit.executionEndDiv = l_orderExecEndPrarams.getStatus();
    
                        //前回発注日 = 処理対象の要素.前回実施日
                        l_executionEndUnit.previousOrderBizDate = 
                            WEB3DateUtility.toDay(l_orderExecEndPrarams.getLastExecuteDate());
        
                        //前回出来終了日時 = 処理対象の要素.更新日付
                        l_executionEndUnit.previousExecutionEndTime = 
                            l_orderExecEndPrarams.getLastUpdatedTimestamp();
            
                        //２－５）　@ArrayListにプロパティセットしたインスタンスを追加する。
                        l_lisExecEndUnit.add(l_executionEndUnit);
                    }
                }
            }
    
            //３）　@ArrayList.toArray()の戻り値を返却する。 
            WEB3FeqExecutionEndUnit[] l_execEndUnits = null;
    
            if (!l_lisExecEndUnit.isEmpty())
            {
                l_execEndUnits = new WEB3FeqExecutionEndUnit[l_lisExecEndUnit.size()];
                l_lisExecEndUnit.toArray(l_execEndUnits);
            }
    
            log.exiting(STR_METHOD_NAME);
    
            return l_execEndUnits;
        
    }
    
    /**
     * (is出来終了可能)<BR>
     * 引数の発注日(nullの場合は、取引時間管理.get発注日()の戻り値)に<BR>
     * 出来終了処理が可能かどうか判別する。<BR>
     * 出来終了可能であればtrueを、以外falseを返却する。<BR>
     * <BR>
     * １）　@出来終了可能チェック<BR>
     * 　@以下の条件に該当する場合は、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * <BR>
     * 　@パラメータ.前回実施日 < パラメータ.発注日 <= 業務日付(*1)<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_datLastExecuteDate - (前回実施日)<BR>
     * 前回実施日<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42AFE1580314
     */
    protected boolean isExecEndPossible(Date l_datLastExecuteDate, Date l_datBizDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExecEndPossible(Date, Date) ";
        log.entering(STR_METHOD_NAME);
        
        //引数の発注日(nullの場合は、取引時間管理.get発注日()の戻り値)
        if (l_datBizDate == null)
        {                        
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                        
        }
        
        //１）　@出来終了可能チェック
        //以下の条件に該当する場合は、trueを返却する。
        //以外、falseを返却する。
        //パラメータ.前回実施日 < パラメータ.発注日 <= 業務日付(*1)
        TradingSystem l_ts = GtlUtils.getTradingSystem();
        if (l_ts == null)
        {
            log.debug("TradingSystemが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "TradingSystemが存在しない。");
        }
              
        Date l_datBusDate = l_ts.getBizDate();
        int l_intLastCompareBiz = WEB3DateUtility.compareToDay(l_datLastExecuteDate, l_datBizDate);
        int l_intBizCompareBus = WEB3DateUtility.compareToDay(l_datBizDate, l_datBusDate);
        
        if (l_intLastCompareBiz < 0 && l_intBizCompareBus <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;                
    }
    
    /**
     * (get処理対象顧客一覧)<BR>
     * 出来終了処理対象となる注文を保持する<BR>
     * 顧客の一覧を返却する。<BR>
     * <BR>
     * １）　@注文単位検索<BR>
     * 　@外国株式注文マネージャ.get出来終了対象注文単位()をコールする。<BR>
     * <BR>
     * 　@[get出来終了対象注文単位()に指定する引数]<BR>
     * 　@　@口座ID：　@null<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@市場コード：　@パラメータ.市場コード<BR>
     * 　@　@発注日：　@パラメータ.発注日<BR>
     * <BR>
     * 　@nullが返却された場合、nullを返却する。<BR>
     * <BR>
     * ２）　@顧客オブジェクト作成<BR>
     * 　@２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。<BR>
     * 　@２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、<BR>
     * 　@　@配列として返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 42B7E7A30301
     */
    protected WEB3GentradeMainAccount[] getDealTargetAccountList(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDealTargetAccountList(String, String, Date) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@注文単位検索
            //外国株式注文マネージャ.get出来終了対象注文単位()をコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModuleが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModuleが存在しない。");
            }
            
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            if (l_orderMgr == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式注文マネージャが存在しない。");
            }
            WEB3FeqOrderUnit[] l_orderUnits = l_orderMgr.getOrderExecEndObjectOrderUnit(
                null,
                l_strInstitutionCode, 
                l_strMarketCode,
                l_datBizDate);        
            WEB3GentradeMainAccount[] l_mainAccounts= null;
            List l_lisMainAccounts = new ArrayList();
            
            //nullが返却された場合、nullを返却する。        
            if (l_orderUnits != null && l_orderUnits.length > 0)
            {                
                //２）　@顧客オブジェクト作成
                //２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。            
                //２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、
                //配列として返却する。                
                WEB3GentradeAccountManager l_accMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accMgr == null)
                {
                    log.debug("拡張アカウントマネージャが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "拡張アカウントマネージャが存在しない。");
                }
                
                int l_intCnt = l_orderUnits.length;
                
                log.debug("１）の検索結果 > 0," + l_intCnt);
                
                List l_lisAccountId = new ArrayList();
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
    
                    if (l_orderUnit != null)
                    {
                        Long l_accountId = new Long(l_orderUnit.getAccountId());
                        
                        if (!l_lisAccountId.contains(l_accountId))  
                        {
                            l_lisAccountId.add(l_accountId); 
                        }
                    }
                }
                
                int l_intAccountCnt = l_lisAccountId.size();
                
                for (int i = 0; i < l_intAccountCnt; i++)
                {
                    Long l_accountId = (Long)l_lisAccountId.get(i);
                    
                    WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_accountId.longValue());//NotFoundException
                       
                    l_lisMainAccounts.add(l_mainAccount);
                }
                
                if (!l_lisMainAccounts.isEmpty())
                {
                    l_mainAccounts = new WEB3GentradeMainAccount[l_lisMainAccounts.size()];
                    l_lisMainAccounts.toArray(l_mainAccounts);
                }
            }     
            
            log.exiting(STR_METHOD_NAME);   
            
            return l_mainAccounts;
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
    }
    
    /**
     * (update外株出来終了)<BR>
     * 引数の条件に該当する外株出来終了テーブルを<BR>
     * udpateする。<BR>
     * <BR>
     * パラメータ.証券会社コード、パラメータ.市場コードに<BR>
     * 該当する外株出来終了テーブルのデータをupdateする。<BR>
     * <BR>
     * 更新内容は、<BR>
     * 【xTrade】補足資料.DB更新\\20.(管)出来終了<BR>
     * 「外株出来終了_外株出来終了テーブル仕様.xls」<BR>
     * 　@参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_strStatus - (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：　@未処理<BR>
     * 1：　@処理済<BR>
     * 9：　@エラー<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7E7A3033F
     */
    protected void updateFeqExecEnd(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        Date l_datBizDate, 
        String l_strStatus) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateFeqExecEnd(String, String, Date, String) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //パラメータ.証券会社コード、パラメータ.市場コードに
            //該当する外株出来終了テーブルのデータをupdateする。
            FeqOrderexecutionEndRow l_orderexecEndRow = 
                FeqOrderexecutionEndDao.findRowByInstitutionCodeMarketCode(
                    l_strInstitutionCode, l_strMarketCode);//DataNetworkException,DataQueryException
            
            if (l_orderexecEndRow != null)
            {
                log.debug("外株出来終了テーブルのデータ != null");
                
                FeqOrderexecutionEndParams l_orderexecEndParams = 
                    new FeqOrderexecutionEndParams(l_orderexecEndRow);
                
                //last_execute_date:発注日(* 画面入力値)
                l_orderexecEndParams.setLastExecuteDate(l_datBizDate);
                
                //処理区分:例外が発生した場合は、9：エラー
                //以外、1：処理済
                l_orderexecEndParams.setStatus(l_strStatus);

                //last_updater:セッションよりログインＩＤを取得、ログインＩＤに該当する管理者.管理者コード。
                //ログインＩＤが取得できない場合は、null。
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService) Services.getService(
                        OpLoginSecurityService.class);
                if (l_opLoginSec == null)
                {
                    log.debug("ログインセキュリティサービスが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "ログインセキュリティサービスが存在しない。");
                }        
                
                LoginInfo l_loginInfo = l_opLoginSec.getLoginInfo(); 
                if (l_loginInfo == null)
                {
                    log.debug("LoginInfoが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "LoginInfoが存在しない。");
                } 
                
                long l_lngLoginId = l_loginInfo.getLoginId();
                
                AdministratorRow l_administratorRow =
                    AdministratorDao.findRowByLoginId(l_lngLoginId);//DataNetworkException,DataQueryException
                                
                if (l_administratorRow != null)
                {
                    l_orderexecEndParams.setLastUpdater(l_administratorRow.getAdministratorCode());
                }
                else
                {
                    l_orderexecEndParams.setLastUpdater(null);
                }
                  
                //last_updated_timestamp:現在時刻
                l_orderexecEndParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                QueryProcessor l_queryProcessor = 
                    Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doUpdateQuery(
                    l_orderexecEndParams);//DataNetworkException,DataQueryException                    
            }
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
