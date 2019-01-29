head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスImpl(WEB3AdminFeqExecuteResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 鄭海良(中訊) 新規作成
                   2006/09/18 徐大方(中訊) 仕様変更・モデル243  
                   2006/12/21 徐宏偉(中訊) ＤＢ更新仕様 83  
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2009/08/03 車進(中訊) モデルNo.513
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqExecuteResultInputCSV;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqOpenAtOrderDLDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecuteResultUploadService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.slebase.data.SleRcvdQDao;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスImpl)<BR>
 * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス実装クラス<BR>
 * <BR>
 * 管理者外国株式アップロードサービスインタセプタをPluginする。<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteResultUploadServiceImpl implements WEB3AdminFeqExecuteResultUploadService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqExecuteResultUploadServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F302FD
     */
    public WEB3AdminFeqExecuteResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式約定結果アップロード処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －getアップロード画面()<BR>
     * －validateアップロードファ@イル()<BR>
     * －submitアップロードファ@イル()<BR>
     * －undoアップロードファ@イル()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429AE05C01BD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
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
        if (l_request instanceof WEB3AdminFeqExecuteResultUploadInputRequest)
        {
            //getアップロード画面
            l_response = 
                this.getUploadScreen((WEB3AdminFeqExecuteResultUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadConfirmRequest)
        {
            //validateアップロード
            l_response = 
                this.validateUpload((WEB3AdminFeqExecuteResultUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadCompleteRequest)
        {
            //submitアップロード
            l_response = 
                this.submitUpload((WEB3AdminFeqExecuteResultUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadStopRequest)
        {
            //undoアップロード
            l_response = 
                this.undoUpload((WEB3AdminFeqExecuteResultUploadStopRequest)l_request);
        }
        else
        {
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
     * (getアップロード画面)<BR>
     * アップロード画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定一括入力）getアップロード画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード入力リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BB1005D
     */
    protected WEB3AdminFeqExecuteResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqExecuteResultUploadInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminFeqExecuteResultUploadInputRequest )";
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

        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //1.3 createResponse()
        WEB3AdminFeqExecuteResultUploadInputResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロード)<BR>
     * アップロードファ@イル確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定一括入力）validateアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード確認リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BBC0271
     */
    protected WEB3AdminFeqExecuteResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqExecuteResultUploadConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpload(WEB3AdminFeqExecuteResultUploadConfirmRequest )";
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
        
        //1,2 getInstanceFromログイン情報( )
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

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //外国株式運用コード採番区分を取得する。
        //[getPREFIX()に指定する引数]
        //証券会社コード：get証券会社コード( )の戻り値
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strFeqOrderEmpCodeManageDiv =
            l_feqOrderEmpCodeGettingService.getPREFIX(l_strInstitutionCode);

        //1.4 外国株式約定結果一括入力CSV( )
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = new WEB3FeqExecuteResultInputCSV();

        //1.5 validate同時アップロード(long)
        l_feqExecuteResultInputCsv.validateSameTimeUpload(null);//WEB3BaseException
        
        //1.6 get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.7 saveアップロード開始(long, String, String, String, String)
        l_feqExecuteResultInputCsv.saveUpLoadStart(
            0,
            null,
            null,
            null,
            l_strAdministratorCode);//WEB3SystemLayerException

        //1.8 リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        if (l_request.uploadFileList == null || l_request.uploadFileList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードファ@イルが未指定(null)です。");
        }
        ArrayList l_arrayList = new ArrayList();
        int  l_intFileCount = l_request.uploadFileList.length;
        for (int i = 0; i < l_intFileCount; i++)
        {
            int l_intIndex = 0;
            try
            {
                //1.8.1 add明細行(String)
                l_intIndex = l_feqExecuteResultInputCsv.addRow(
                    l_request.uploadFileList[i]);//WEB3SystemLayerException
                    
                //1.8.2 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
                if (l_intIndex < 0)
                {
                    continue;
                }
            }
            //1.8.3 add明細行()が例外をスローした場合
            catch(WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                
                //1.8.3.1 saveアップロードエラー(ErrorInfo)
                l_feqExecuteResultInputCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.3.2 （例外）
                throw l_ex;
            }
            
            try
            {
                //1.8.4 validate明細行(int)
                int l_intValidateIndex = l_feqExecuteResultInputCsv.validateDetailLine(
                    l_intIndex, l_strFeqOrderEmpCodeManageDiv);//WEB3BaseException
            
                //1.8.5 処理対象行でない場合（validate明細行()の戻り値 < 0）
                if (l_intValidateIndex < 0)
                {
                    //1.8.5.1 delete明細行(行番号 : int)
                    //QA:WEB3-XBFEQ-A-ＦT-0166
                    l_feqExecuteResultInputCsv.deleteRow(l_intIndex);

                    //1.8.5.2 当該要素の処理を中断（continue;）
                    continue;
                }

                //1.8.6 validate重複行(int)
                l_feqExecuteResultInputCsv.validateRepeatLine(l_intIndex);//WEB3BaseException
            }
            //1.8.7 validate明細行()または、validate重複行()が例外をスローした場合
            catch (WEB3BaseException l_ex)
            {
                String l_strMessage = l_feqExecuteResultInputCsv.getOrderEmpCode(l_intIndex)
                + ":"
                + l_ex.getErrorInfo().getErrorMessage();
                log.error(l_strMessage, l_ex);
                
                //1.8.7.1 saveアップロードエラー(ErrorInfo)
                l_feqExecuteResultInputCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.7.2 （例外）
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    //1.8.7.2.1 delete明細行(String)
                    l_feqExecuteResultInputCsv.deleteRow(l_intIndex);
                    l_arrayList.add(l_strMessage);
                    
                }
                else if (l_ex instanceof WEB3SystemLayerException)
                {
                    throw new WEB3SystemLayerException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
                else
                {
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
            }
            
        }
        
        //1.9 get明細行数( )
        int l_intRowCount = l_feqExecuteResultInputCsv.getRowCount();
        
        //1.10 ＵＬ対象行がない場合（明細行数 == 0）、例外をスローする。
        if (l_intRowCount == 0)
        {
            //1.10.1 saveアップロードエラー(ErrorInfo)
            l_feqExecuteResultInputCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_02188);//WEB3SystemLayerException
            
            //1.10.2 （例外）
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02188,
                this.getClass().getName() + STR_METHOD_NAME,
                "ＵＬ対象行が存在できません。");
        }
        
        //1.11 getアップロードＩＤ( )
        long l_lngUploadId = l_feqExecuteResultInputCsv.getAdministratorUploadId();
        
        //1.12 saveアップロードTemp( )
        l_feqExecuteResultInputCsv.saveUploadTemp();//WEB3SystemLayerException
        
        //1.14 get通貨(String)
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
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeCurrency[] l_genCurrencys = 
            l_productManager.getCurrency(l_strInstitutionCode);//WEB3BaseException
        
        //1.15 get為替()戻り値の要素数分LOOP処理
        int l_intUnitCount = 0;
        if (l_genCurrencys != null)
        {
            l_intUnitCount = l_genCurrencys.length;
        }
        WEB3FeqExchangeUnit[] l_feqExchangeUnits = new WEB3FeqExchangeUnit[l_intUnitCount];
        for (int i = 0; i < l_intUnitCount; i++)
        {
            WEB3GentradeCurrency l_genCurrency = l_genCurrencys[i];
            GenCurrencyParams l_currencyParmas = (GenCurrencyParams)l_genCurrency.getDataSourceObject();
            //1.15.1 外国株式為替情報( )
            WEB3FeqExchangeUnit l_feqExchangeUnit = new WEB3FeqExchangeUnit();
        
            //1.15.2 プロパティセット
            //通貨コード
            l_feqExchangeUnit.currencyCode = l_currencyParmas.getCurrencyCode();
            //レート区分
            l_feqExchangeUnit.rateDiv = WEB3FeqRateDivDef.EXECUTED_EXCHANGE;
            //売付為替レート
            l_feqExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellExecRate());
            //買付為替レート
            l_feqExchangeUnit.buyExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyExecRate());
            //登録日時
            l_feqExchangeUnit.registDatetime = l_currencyParmas.getExecRateUpdateTimestamp();
            
            l_feqExchangeUnits[i] = l_feqExchangeUnit;
        }
        
        //1.16 createResponse()
        WEB3AdminFeqExecuteResultUploadConfirmResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "レスポンスがnullです。");
        }    

        String[] l_strErrInfoList = new String[l_arrayList.size()];
        l_arrayList.toArray(l_strErrInfoList);
        //1.17 プロパティセット
        l_response.uploadNumber = l_intRowCount + "";
        l_response.uploadId = l_lngUploadId + "";
        l_response.feqExchangeUnit = l_feqExchangeUnits;
        l_response.errorInfoList = l_strErrInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロード)<BR>
     * アップロード完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定一括入力）submitアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード完了リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BD40399
     */
    protected WEB3AdminFeqExecuteResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqExecuteResultUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitUpload(WEB3AdminFeqExecuteResultUploadCompleteRequest )";
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
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();       
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

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.5 外国株式約定結果一括入力CSV( )
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = new WEB3FeqExecuteResultInputCSV();

        //1.6 validate同時アップロード(long)
        if (WEB3StringTypeUtility.isEmpty(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDが未指定(null)です。");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDタイプ不正。");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        l_feqExecuteResultInputCsv.validateSameTimeUpload(
            new Long(l_lngUploadId));//WEB3BaseException

        //1.7 setDataFromアップロードTemp(long)
        l_feqExecuteResultInputCsv.setDataFromUploadTemp(l_lngUploadId);//WEB3SystemLayerException
        
        //1.8 get明細行数( )
        int l_intRowCount = l_feqExecuteResultInputCsv.getRowCount();
        
        //1.9 明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++)
        {   
            //1.9.1 get注文単位(int)
            WEB3FeqOrderUnit l_orderUnit = l_feqExecuteResultInputCsv.getOrderUnit(i);
            if (l_orderUnit == null)
            {
                String l_strMessage = "外国株式注文単位が存在しない。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
            
            //1.9.2 (注文受付出来通知キューデータの登録を行なう)
            SleRcvdQParams l_rcvdQParams = new SleRcvdQParams();
            try
            {
                //キューID: 自動採番した値
                l_rcvdQParams.setQueueId(SleRcvdQDao.newPkValue());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            
            //銘柄タイプ: 4:外国株
            l_rcvdQParams.setXblocksProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //注文ID: 外株注文単位.注文ID
            l_rcvdQParams.setInternalRef(l_orderUnit.getOrderId() + "");
            
            //銘柄コード: 外国株式約定結果一括入力CSV.get銘柄コード()の戻り値
            l_rcvdQParams.setStockCode(l_feqExecuteResultInputCsv.getProductCode(i));
            
            //買売区分:
            if (WEB3FeqOpenAtOrderDLDef.BUY.equals(l_feqExecuteResultInputCsv.getTrade(i)))
            {
                //外国株式約定結果一括入力CSV.get売買()の戻り値が"B"の場合は、1:買付
                l_rcvdQParams.setSide(SideEnum.BUY.intValue());
            }
            else if (WEB3FeqOpenAtOrderDLDef.SELL.equals(l_feqExecuteResultInputCsv.getTrade(i)))
            {
                //外国株式約定結果一括入力CSV.get売買()の戻り値が"S"の場合は、2:売付
                l_rcvdQParams.setSide(SideEnum.SELL.intValue());
            }
            
            //約定単価: 外国株式約定結果一括入力CSV.get約定単価()の戻り値
            l_rcvdQParams.setExecPrice(l_feqExecuteResultInputCsv.getExecPrice(i));
            
            //約定株数: 外国株式約定結果一括入力CSV.get約定株数()の戻り値
            l_rcvdQParams.setExecQty(l_feqExecuteResultInputCsv.getExecQuantity(i));
            
            //約定日時: 外国株式約定結果一括入力CSV.get約定日時()の戻り値
            l_rcvdQParams.setExecTimestamp(l_feqExecuteResultInputCsv.getExecTimestamp(i));
            
            //運用コード: 外株注文単位.運用コード
            l_rcvdQParams.setOrderEmpCode(l_orderUnit.getOrderEmpCode());

            //以下の値を右詰前0埋（3桁）に編集してセット
            //外国株式約定結果一括入力CSV.get約定No.()の戻り値
            String l_strExecNo = l_feqExecuteResultInputCsv.getExecNo(i) + "";
            while (l_strExecNo.length() < 3)
            {
                l_strExecNo = "0" + l_strExecNo;
            }
            l_rcvdQParams.setExecSerialNo(l_strExecNo);
            
            //経路区分: 2:約定結果一括入力
            l_rcvdQParams.setRouteDiv(WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD);
            
            //アカウントID: 外株注文単位.口座ID
            l_rcvdQParams.setAccountId(l_orderUnit.getAccountId());
            
            //オペレータタイプ: 0:新規
            l_rcvdQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
            
            //証券会社コード: 外株注文単位.証券会社コード
            l_rcvdQParams.setInstitutionCode(l_orderUnit.getInstitutionCode());
            
            //部店コード: 外株注文単位.部店IDに該当する部店.部店コード
            l_rcvdQParams.setBranchCode(l_orderUnit.getBranchCode());
            
            //識別コード: 外株注文単位.識別コード
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_rcvdQParams.setOrderRequestNumber(l_feqOrderUnitRow.getOrderRequestNumber());
            
            //更新者コード: 管理者.get管理者コード()の戻り値
            l_rcvdQParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //処理区分: 0:未処理
            l_rcvdQParams.setStatus(SleRcvdqProcStatusEnum.TODO); 
            
            //作成日付: 登録時でsysdate が設定される
            l_rcvdQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日付: 登録時でsysdate が設定される
            l_rcvdQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_rcvdQParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
        }
        
        //1.10 saveアップロード終了( )
        l_feqExecuteResultInputCsv.saveUploadEnd();//WEB3SystemLayerException
        
        //1.11 deleteアップロードTemp( )
        l_feqExecuteResultInputCsv.deleteUploadTemp();//WEB3SystemLayerException
        
        //1.12 createResponse()
        WEB3AdminFeqExecuteResultUploadCompleteResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定一括入力）undoアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード中止リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BDC0138
     */
    protected WEB3AdminFeqExecuteResultUploadStopResponse undoUpload(
        WEB3AdminFeqExecuteResultUploadStopRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminFeqExecuteResultUploadStopRequest )";
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
        
        //1.1 外国株式約定結果一括入力CSV(long)
        if (WEB3StringTypeUtility.isEmpty(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDが未指定(null)です。");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDタイプ不正。");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = 
            new WEB3FeqExecuteResultInputCSV(l_lngUploadId);
            
        //1.2 deleteアップロードTemp( )
        l_feqExecuteResultInputCsv.deleteUploadTemp();//WEB3SystemLayerException 
        
        //1.3 saveアップロード中止( )
        l_feqExecuteResultInputCsv.saveUploadStop(); //WEB3SystemLayerException
               
        //1.4 createResponse()
        WEB3AdminFeqExecuteResultUploadStopResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadStopResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
