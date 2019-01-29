head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : シングルサインオンサービスImpl(WEB3FXSingleSignOnServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/28 韋念瓊 (中訊) 新規作成   
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更 モデルNo.849
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxUnnecessaryExplanationRow;
import webbroker3.aio.data.SsoMessageParams;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3GftTelegramFormatDef;
import webbroker3.aio.message.WEB3FXSingleSignOnRequest;
import webbroker3.aio.message.WEB3FXSingleSignOnResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXTradeAgreementResponse;
import webbroker3.aio.service.delegate.WEB3FXSingleSignOnService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (シングルサインオンサービスImpl) <BR>
 * シングルサインオンサービス実装クラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnServiceImpl extends WEB3ClientRequestService
    implements WEB3FXSingleSignOnService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3FXSingleSignOnServiceImpl()
    {
    }

    /**
     * シングルサインオンサービス処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * 　@・getFX取引同意画面() <BR>
     * 　@・submit外部為替システム表示() <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型により、以下のいずれかのメソッドをコールする。 
        // ・getFX取引同意画面()
        // ・submit外部為替システム表示()
        if (l_request instanceof WEB3FXTradeAgreementRequest)
        {
            l_response = 
                getFXTradeAgreementScreen((WEB3FXTradeAgreementRequest)l_request);   
        }
        else if (l_request instanceof WEB3FXSingleSignOnRequest)
        {
            l_response =
                submitDisplayExterFxSystem((WEB3FXSingleSignOnRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }

    /**
     * (getFX取引同意画面) <BR>
     * FX取引同意画面表示データの取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（シングルサインオン）getFX取引同意画面」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTradeAgreementResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B2080090
     */
    protected WEB3FXTradeAgreementResponse getFXTradeAgreementScreen(
        WEB3FXTradeAgreementRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getTradeAgreementScreen(WEB3FXTradeAgreementRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。  
        //[引数]  
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 validate注文受付可能( )
        //以下のチェックを行う。  
        //　@－受付時間チェック  
        //　@－システム停止中チェック  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get会社別FXシステム条件(String, String, String)
        //会社別FXシステム条件Paramsを取得する。  
        //[引数]  
        //証券会社コード： 補助口座.証券会社コード  
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：リクエストデータ.FXシステムコード
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionparams = null;
        try
        {
            l_compFxConditionparams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in get会社別FXシステム条件()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4 validate外部システム受付可能(String)
        //FXシステムの受付時間チェックを行う。  
        //[引数]  
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionparams.getFxSystemCode());
        
        //1.5 validateFX口座開設(SubAccount, String)
        //FX取引口座開設のチェックを行う。 
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        //FXシステムコード：会社別FXシステム条件Params.FXシステムコード
        l_orderManager.validateFXAccOpen(
            l_subAccount, 
            l_compFxConditionparams.getFxSystemCode());
        
        //1.6  createResponse()
        WEB3FXTradeAgreementResponse l_response = 
            (WEB3FXTradeAgreementResponse)l_request.createResponse();
            
        //1.7 レスポンスデータにプロパティをセットする。
        MainAccountRow l_mainAccountRow = (MainAccountRow)
            l_subAccount.getMainAccount().getDataSourceObject();
        
        String l_strFamilyName = l_mainAccountRow.getFamilyName();
             
        //顧客名: 顧客(*1).苗字
        //(*1) 補助口座.getMainAccount()にて取得
        l_response.accountName = l_strFamilyName;
                                     
        log.exiting(STR_METHOD_NAME);            
        //1.8 
        return l_response;
    }

    /**
     * (submit外部為替システム表示) <BR>
     * 外部為替システム表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（シングルサインオン）submit外部為替システム表示」参照。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXSingleSignOnResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800AF
     */
    protected WEB3FXSingleSignOnResponse submitDisplayExterFxSystem(
        WEB3FXSingleSignOnRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitDisplayExterFxSystem(WEB3FXSingleSignOnRequest l_request)";
        log.entering(STR_METHOD_NAME);        

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        //クエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。  
        //[引数]  
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 validate注文受付可能( )
        //以下のチェックを行う。  
        //　@－受付時間チェック  
        //　@－システム停止中チェック  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 get会社別FXシステム条件(String, String, String)
        //会社別FXシステム条件Paramsを取得する。  
        //[引数]  
        //証券会社コード： 補助口座.証券会社コード  
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：リクエストデータ.FXシステムコード
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionparams = null;
        try
        {
            l_compFxConditionparams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in get会社別FXシステム条件()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5 validate外部システム受付可能(String)
        //FXシステムの受付時間チェックを行う。  
        //[引数]  
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionparams.getFxSystemCode());
        
        //1.6 validateFX口座開設(SubAccount, String)
        //FX取引口座開設のチェックを行う。 
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        //FXシステムコード：会社別FXシステム条件Params.FXシステムコード
        l_orderManager.validateFXAccOpen(
            l_subAccount, 
            l_compFxConditionparams.getFxSystemCode());        
        
        //1.7 if　@リクエストデータ.電子鳩チェックフラグ==true
        if (l_request.batoCheckFlag)
        {
            //1.7.1 validateFXドキュメント閲覧履歴(String, String[])
            //FXドキュメント閲覧履歴のチェックを行う 
            //[引数] 
            //リクエストデータ.種別コード 
            //リクエストデータ.識別コード[]
            String[] l_strReadHistorys = 
                l_dataControlService.validateFxDocReadHistory(
                    l_request.typeCode, 
                    l_request.requestCode);

            int l_intLength = 0;
            if (l_strReadHistorys != null)
            {
                l_intLength = l_strReadHistorys.length;
            }
            //1.7.2 if　@validateFXドキュメント閲覧履歴()の戻り値 != null or 
            //          validateFXドキュメント閲覧履歴()の戻り値.length != 0
            if (l_strReadHistorys != null || l_intLength != 0)
            {
                //1.7.2.1  createResponse()
                WEB3FXSingleSignOnResponse l_response = 
                    (WEB3FXSingleSignOnResponse)l_request.createResponse(); 
                
                //1.7.2.2 （*）プロパティセット
                //識別コード = validateFXドキュメント閲覧履歴の戻り値
                l_response.requestCode = l_strReadHistorys;
                
                //説明不要承諾履歴チェック結果 = false;
                l_response.noExplainAgreeHistoryCheck = false;
                
                //暗号化文字列 = null
                l_response.encryptString = null;
                
                //秘密鍵 = null
                l_response.secretKey = null;
                
                //ハッシュ値 = null
                l_response.hashValue = null;
                
                //URL = null
                l_response.fxUrl = null;
                
                //1.7.2.3
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }            
        }
        
        //1.8 if　@リクエストデータ.説明不要承諾履歴作成フラグ == true
        if (l_request.noExplainAgreeHistoryFlag)
        {
            //1.8.1 insert説明不要承諾履歴(String, String, String)
            //説明不要承諾履歴の作成を行う 
            //[引数]  
            //証券会社コード： 補助口座.証券会社コード  
            //部店コード： 補助口座.get取引店.getBranchCode()  
            //顧客コード： 補助口座.getMainAccount().getAccountCode() 
            l_dataControlService.insertUnnecessaryExplanation(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode());
        }
        
        //1.9 create検索条件文字列(String)
        //検索条件文字列を生成する。 
        //[引数] 
        //説明不要承諾有効期間：会社別FXシステム条件Params.説明不要承諾有効期間
        String l_strQueryString = 
            this.createQueryString(l_compFxConditionparams.getValidTerm());
        log.debug("検索条件文字列 " + l_strQueryString);
        
        //1.10 create検索条件データコンテナ(String, String, String, String)
        //検索条件データコンテナを作成する。  
        //[引数]  
        //証券会社コード： 補助口座.証券会社コード  
        //部店コード： 補助口座.get取引店.getBranchCode()  
        //顧客コード： 補助口座.getMainAccount().getAccountCode() 
        //説明不要承諾有効期間： 会社別FXシステム条件Params.説明不要承諾有効期間 
        Object[] l_queryContainers = 
            this.createQueryContainer(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode(), 
                l_compFxConditionparams.getValidTerm());
        
        //1.11 createソート条件( )(シングルサインオンサービスImpl::createソート条件)
        //ソート条件を作成する。
        String l_strSortCond = this.createSortCond();
        log.debug("ソート条件 " + l_strSortCond);

        //1.12 is説明不要承諾履歴(String, Object[], String)
        //説明不要承諾履歴チェックを行う 
        //[引数]  
        //検索条件文字列：　@create検索条件文字列()の戻り値  
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値  
        //ソート条件：　@createソート条件()の戻り値                
        boolean l_blnIsUnnecessary = 
            this.isUnnecessaryExplanation(
                l_strQueryString, 
                l_queryContainers, 
                l_strSortCond);

        //1.13 if　@is説明不要承諾履歴()の戻り値 == false
        if (!l_blnIsUnnecessary)
        {
            log.debug("is説明不要承諾履歴()の戻り値 == false");
            
            //1.13.1  createResponse()
            WEB3FXSingleSignOnResponse l_response = 
                (WEB3FXSingleSignOnResponse)l_request.createResponse();     
            
            //1.13.2 （*）プロパティセット
            //識別コード = null
            l_response.requestCode = null;
            
            //説明不要承諾履歴チェック結果 = is説明不要承諾履歴の戻り値;
            l_response.noExplainAgreeHistoryCheck = l_blnIsUnnecessary;
            
            //暗号化文字列 = null
            l_response.encryptString = null;
            
            //秘密鍵 = null
            l_response.secretKey = null;
            
            //ハッシュ値 = null
            l_response.hashValue = null;
            
            //URL = null
            l_response.fxUrl = null;
            
            //1.13.3
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.14 create乱数( )
        //乱数（4桁）を生成する。
        String l_strRandom = this.createRandom();
        log.debug("乱数（4桁）= " + l_strRandom);
        
        //1.15 create秘密鍵( )(シングルサインオンサービスImpl::create秘密鍵)
        //秘密鍵（8桁）をランダムに生成する。
        String l_strSecretKey = this.creatSecretKey();        
        log.debug("秘密鍵（8桁）= " + l_strSecretKey);
        
        //1.16 getFX顧客(String, String, String, String)
        //FX顧客Paramsを取得する。 
        //[引数] 
        //証券会社コード：補助口座.証券会社コード 
        //部店コード：補助口座.get取引店.getBranchCode() 
        //FXシステムコード：会社別FXシステム条件Params.FXシステムコード 
        //顧客コード：補助口座.getMainAccount().getAccountCode() 
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = 
                l_dataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_compFxConditionparams.getFxSystemCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch(NotFoundException l_ex)
        {
            log.error("__an notFoundexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.17 createポスト用生成前データ(String, String)
        //ポスト用生成前データを生成する。 
        //[引数] 
        //FXログインID：FX顧客.FXログインID 
        //乱数：create乱数()の戻り値
        String l_strFxLoginId = l_fxAccountParams.getFxLoginId() + "";
        
        String l_strPostCreateData = 
            this.createPostCreateData(
                l_strFxLoginId, 
                l_strRandom);
        
        log.debug("ポスト用生成前データ = " + l_strPostCreateData);

        //1.18 create暗号化文字列(String, String)
        //暗号化文字列を生成する。 
        //[引数] 
        //秘密鍵：create秘密鍵()の戻り値 
        //ポスト用生成前データ：createポスト用生成前データの戻り値
        String l_strEncryptString = 
            this.creatEncryptString(
                l_strSecretKey, 
                l_strPostCreateData);
        
        log.debug("暗号化文字列 = " + l_strEncryptString);
        
        //1.19 createハッシュ値(String, String)
        //ハッシュ値を生成する。 
        //[引数] 
        //暗号化文字列：create暗号化文字列()の戻り値 
        //秘密鍵：create秘密鍵()の戻り値
        String l_strHashValue = 
            this.creatHashValue(l_strEncryptString, l_strSecretKey);
        
        log.debug("ハッシュ値 = " + l_strHashValue);
            
        //1.20 insert送信電文(SubAccount, 会社別FXシステム条件Params, String, String, String, String)
        //送信電文をシングルサインオン保存テーブルにinsertする。 
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        //ポスト用生成前データ：createポスト用生成前データ()の戻り値 
        //暗号化文字列：create暗号化文字列()の戻り値 
        //秘密鍵：create秘密鍵()の戻り値 
        //ハッシュ値：createハッシュ値()の戻り値
        this.insertSsoMessage(
            l_subAccount, 
            l_strPostCreateData, 
            l_strEncryptString, 
            l_strSecretKey, 
            l_strHashValue);
        
        //1.21 createResponse()
        WEB3FXSingleSignOnResponse l_response = 
            (WEB3FXSingleSignOnResponse)l_request.createResponse();            
        
        //1.22 レスポンスデータにプロパティをセットする。       
        //識別コード = null
        l_response.requestCode = null;
        
        //説明不要承諾履歴チェック結果 = is説明不要承諾履歴の戻り値;
        l_response.noExplainAgreeHistoryCheck = l_blnIsUnnecessary;
        
        //暗号化文字列 = create暗号化文字列()の戻り値 
        l_response.encryptString = l_strEncryptString;
        
        //秘密鍵 = create秘密鍵()の戻り値 
        l_response.secretKey = l_strSecretKey;
        
        //ハッシュ値 = createハッシュ値()の戻り値
        l_response.hashValue = l_strHashValue;
        
        //URL = 会社別FXシステム条件Params.Single Sign-On URL
        l_response.fxUrl = l_compFxConditionparams.getSingleSignOnUrl();
                         
        //1.23
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is説明不要承諾履歴) <BR>
     * FX説明不要承諾履歴チェックを行う  <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>  
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]  <BR>
     * 　@　@arg0：　@FX説明不要承諾履歴管理Row.TYPE  <BR>
     * 　@　@arg1：　@引数.検索条件文字列  <BR>
     * 　@　@arg2：　@引数.ソート条件  <BR>
     * 　@　@arg3：　@null  <BR>
     * 　@　@arg4：　@引数.検索条件データコンテナ  <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、falseを返却する。  <BR>
     * <BR>
     * ２）検索結果が取得出来た場合、trueを返却する。 <BR>
     * <BR>
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainers - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected boolean isUnnecessaryExplanation(
        String l_strQueryString, 
        Object[] l_queryContainers, 
        String l_strSortCond) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "isUnnecessaryExplanation(" +
            "String l_strQueryString, Object[] l_queryContainers, " +
            "String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        if (l_queryContainers == null || l_queryContainers.length == 0)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。  
        //[doFindAllQuery()にセットするパラメータ]  
        //　@arg0：　@FX説明不要承諾履歴管理Row.TYPE  
        //　@arg1：　@引数.検索条件文字列  
        //　@arg2：　@引数.ソート条件  
        //　@arg3：　@null  
        //　@arg4：　@引数.検索条件データコンテナ  
        //検索結果が取得できなかった場合、falseを返却する。  

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_lisRows = l_queryProcessor.doFindAllQuery(
                FxUnnecessaryExplanationRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null, 
                l_queryContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //検索結果が取得できなかった場合、falseを返却する。
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //２）検索結果が取得出来た場合、trueを返却する。 
        log.exiting(STR_METHOD_NAME);        
        return true;
    }
    
    /**
     * (create暗号化文字列) <BR>
     * 暗号化文字列を生成する。 <BR>
     * <BR>
     * １）引数.秘密鍵、引数.ポスト用生成前データを使用して暗号化文字列を生成する。（*1） <BR>
     * <BR>
     * ２）１）で生成した暗号化文字列を返却する。 <BR>
     * <BR>
     * （*1）DESを使用<BR>
     * <BR>
     * @@param l_strSecretKey - 秘密鍵
     * @@param l_strPostCreateData - ポスト用生成前データ     
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatEncryptString(
        String l_strSecretKey, String l_strPostCreateData)           
    {
        String STR_METHOD_NAME = "creatEncryptString(" +
            "String l_strSecretKey, String l_strPostCreatData)";
        
        log.entering(STR_METHOD_NAME);        
        
        //１）引数.秘密鍵、引数.ポスト用生成前データを使用して暗号化文字列を生成する。（*1） 
        //（*1）DESを使用
        String l_strEncryptSring = this.encrypt(l_strPostCreateData, l_strSecretKey);
      
        //２）１）で生成した暗号化文字列を返却する。
        log.exiting(STR_METHOD_NAME);  
        return l_strEncryptSring;
    }
    
    /**
     * (create秘密鍵) <BR>
     * 秘密鍵（8桁）をランダムに生成し、返却する。<BR>
     *     
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatSecretKey()
    {
        String STR_METHOD_NAME = "creatSecretKey()";
        log.entering(STR_METHOD_NAME);        
        
        //秘密鍵（8桁）をランダムに生成し、返却する。
        String l_strSecretKey = this.generateRandom(8) + "";
      
        log.exiting(STR_METHOD_NAME);
        
        return l_strSecretKey;
    }
    
    /**
     * (createハッシュ値) <BR>
     * 暗号化文字列と秘密鍵を使用してハッシュ値を生成し、返却する。 <BR>
     * <BR>
     * １）ハッシュ項目の連結文字列の作成  <BR>
     * <BR>
     * 「キー名=値」をセットにした文字列の配列を以下の順で作成する。 <BR>
     * <BR>
     * encryptedData=引数.暗号化文字列  <BR>
     * secretKey=引数.秘密鍵 <BR>
     * <BR>
     * ※キー名は電文Formatのキー名を使用。 <BR>
     * ※パラメータ間は、'&'をセットする。  <BR>
     * <BR>
     * ２）ハッシュ値の生成  <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。 <BR> 
     * [引数]  <BR>
     * 計算方式：　@"MD5"  <BR>
     * 計算対象：　@１）にて作成した文字列配列  <BR>
     * <BR>
     * ３）　@生成したハッシュ値を返却する。 <BR>
     * <BR>
     * @@param l_strEncryptString - 暗号化文字列 
     * @@param l_strSecretKey - 秘密鍵
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatHashValue(
        String l_strEncryptString, 
        String l_strSecretKey)
    {
        String STR_METHOD_NAME = 
            "creatHashValue(String l_strEncryptString, String l_strSecretKey)";
        log.entering(STR_METHOD_NAME);        
        
        //１）ハッシュ項目の連結文字列の作成  
        //「キー名=値」をセットにした文字列の配列を以下の順で作成する。 
        //encryptedData=引数.暗号化文字列  
        //secretKey=引数.秘密鍵 
        //※キー名は電文Formatのキー名を使用。 
        //※パラメータ間は、'&'をセットする。 
        List l_lisWhere = new Vector();
        
        //encryptedData=引数.暗号化文字列
        l_lisWhere.add(WEB3GftTelegramFormatDef.encryptedData + "=" + 
            ((l_strEncryptString == null) ? "" : l_strEncryptString));
        
        //secretKey=引数.秘密鍵 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.secretKey + "=" + 
            ((l_strSecretKey == null) ? "" : l_strSecretKey));        

        //２）ハッシュ値の生成  
        //WEB3StringTypeUtility.createHashValue()を用いてハッシュ値を生成する。  
        //[引数]  
        //計算方式：　@"MD5"  
        //計算対象：　@１）にて作成した文字列配列 
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
      
        //３）　@生成したハッシュ値を返却する。 
        log.exiting(STR_METHOD_NAME);
        
        return l_strHashValue;
    }
    
    /**
     * (createポスト用生成前データ) <BR>
     * ポスト用生成前データを生成し、返却する。 <BR>
     * <BR>
     * １）引数.FXログインID（9桁）、処理時間（YYYYMMDDHHMM）（*1）、<BR>
     *  引数.乱数を連結しポスト用生成前データを生成する。 <BR>
     * <BR>
     * ２）１）で生成したポスト用生成前データを返却する。 <BR>
     * <BR>
     * （*1）現在日付から取得<BR>
     * <BR>
     * @@param l_strFxLoginId - FXログインID
     * @@param l_strRandom - 乱数
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createPostCreateData(String l_strFxLoginId, String l_strRandom)
    {
        String STR_METHOD_NAME = "createPostCreateData(" +
                "String l_strFxLoginId, String l_strRandom)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strFxLoginId.length() != 9)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）引数.FXログインID（9桁）、処理時間（YYYYMMDDHHMM）（*1）、引数.乱数
        //   を連結しポスト用生成前データを生成する。        
        //（*1）現在日付から取得

        String l_strOperationTime = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyyMMddHHmm");
        
        String l_strPostCreateData = 
            l_strFxLoginId.substring(0, 9) + l_strOperationTime + l_strRandom;
      
        //２）１）で生成したポスト用生成前データを返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_strPostCreateData;
    }
    
    /**
     * (create乱数) <BR>
     * 乱数（4桁）を生成し、返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createRandom()
    {
        String STR_METHOD_NAME = "createRandom()";
        log.entering(STR_METHOD_NAME);        
        
        //乱数（4桁）を生成し、返却する。
        String l_strRandom = this.generateRandom(4) + "";
        log.exiting(STR_METHOD_NAME);
        
        return l_strRandom;
    }
    
    /**
     * (create検索条件文字列) <BR>
     * 説明不要承諾履歴管理テーブルからデータを取得する際の条件を生成し、返却する。 <BR>
     * <BR>
     * １）空の文字列を生成する  <BR>
     * <BR>
     * ２）証券会社コード  <BR>
     * <BR>
     * " institution_code = ?"を１）の文字列に追加する。  <BR>
     * <BR>
     * ３）部店コード  <BR>
     * <BR>
     * " and branch_code = ?"を１）の文字列に追加する。  <BR>
     * <BR>
     * ４）顧客コード <BR>
     * <BR>
     * " and account_code = ?"を１）の文字列に追加する。  <BR>
     * <BR>
     * ５）有効フラグ <BR>
     * <BR>
     * " and fx_valid_flag = ?"を１）の文字列に追加する。  <BR>
     * <BR>
     * ６）作成日付 <BR>
     * 引数.説明不要承諾有効期間 != nullの場合、 <BR>
     * " and created_timestamp > ?"を１）の文字列に追加する。  <BR>
     * <BR>
     * ７）文字列を返却する<BR>
     * <BR>
     * @@param l_strNoExplainAgreeValidTerm - 説明不要承諾有効期間
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createQueryString(String l_strNoExplainAgreeValidTerm)
    {
        String STR_METHOD_NAME = 
            "createQueryString(String l_strNoExplainAgreeValidTerm)";
        log.entering(STR_METHOD_NAME);        
        
        //１）空の文字列を生成する 
        StringBuffer l_strBuffer = new StringBuffer();
        
        //２）証券会社コード 
        //" institution_code = ?"を１）の文字列に追加する。 
        l_strBuffer.append(" institution_code = ?");
        
        //３）部店コード  
        //" and branch_code = ?"を１）の文字列に追加する。  
        l_strBuffer.append(" and branch_code = ?");
      
        //４）顧客コード 
        //" and account_code = ?"を１）の文字列に追加する。  
        l_strBuffer.append(" and account_code = ?");
        
        //５）有効フラグ 
        //" and fx_valid_flag = ?"を１）の文字列に追加する。  
        l_strBuffer.append(" and fx_valid_flag = ?");
        
        //６）作成日付 
        //引数.説明不要承諾有効期間 != nullの場合、 
        //" and created_timestamp > ?"を１）の文字列に追加する。  
        if (l_strNoExplainAgreeValidTerm != null)
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDhh24miss') > ?");
        }
        
        //７）文字列を返却する
        log.exiting(STR_METHOD_NAME);
        return l_strBuffer.toString();
    }
    
    /**
     * (create検索条件データコンテナ) <BR>
     * FX説明不要承諾履歴管理テーブルからデータを取得する際の条件のデータコンテナを生成し、返却する。 <BR>
     * <BR>
     * １）空のArrayListを生成する。  <BR>
     * <BR>
     * ２）証券会社コード  <BR>
     * <BR>
     * 引数.証券会社コードを１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード  <BR>
     * <BR>
     * 引数.部店コードを１）のListに追加する。<BR>
     * <BR>
     * ４）顧客コード <BR>
     * <BR>
     * 引数.顧客コードを１）のListに追加する。  <BR>
     * <BR>
     * ５）有効フラグ <BR>
     * <BR>
     * ”0”を１）のListに追加する。 <BR>
     * <BR>
     * ６）作成日付 <BR>
     * 引数.説明不要承諾有効期間 != nullの場合、 <BR>
     * 現在日付 - X(*1)ヶ月を１）のListに追加する。（YYYYMMDDHH24MISS）<BR> 
     * <BR>
     * ７）Listから配列を取得して、返却する。  <BR>
     * <BR>
     * (*1)引数.説明不要承諾有効期間<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strNoExplainAgreeValidTerm - 説明不要承諾有効期間
     * @@return Object[]
     * @@roseuid 41C7B20800BF
     */
    protected Object[] createQueryContainer(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode, 
        String l_strNoExplainAgreeValidTerm)
    {
        String STR_METHOD_NAME = "createQueryString(" +
            "String l_strInstitutionCode, String l_strBranchCode," +
            "String l_strAccountCode, String l_strNoExplainAgreeValidTerm)";
        log.entering(STR_METHOD_NAME);        
        
        //１）空のArrayListを生成する。
        List l_lisValue = new ArrayList();
        
        //２）証券会社コード 
        //引数.証券会社コードを１）のListに追加する。 
        l_lisValue.add(l_strInstitutionCode);
      
        //３）部店コード  
        //引数.部店コードを１）のListに追加する。 
        l_lisValue.add(l_strBranchCode);
        
        //４）顧客コード 
        //引数.顧客コードを１）のListに追加する。  
        l_lisValue.add(l_strAccountCode);

        //５）有効フラグ 
        //”0”を１）のListに追加する。 
        l_lisValue.add("0");
        
        //６）作成日付 
        //引数.説明不要承諾有効期間 != nullの場合、 
        //現在日付 - X(*1)ヶ月を１）のListに追加する。（YYYYMMDDHH24MISS） 
        //(*1)引数.説明不要承諾有効期間

        if (l_strNoExplainAgreeValidTerm != null)
        {            
            String l_strBeforeYM = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMM");
            
            int l_intMonthDate = 
                Integer.parseInt(l_strBeforeYM.substring(0, 4)) * 12 + 
                Integer.parseInt(l_strBeforeYM.substring(4, 6)) - 
                Integer.parseInt(l_strNoExplainAgreeValidTerm);
            
            String l_strAfterYM = 
                WEB3StringTypeUtility.formatNumber((int)(l_intMonthDate / 12)) + 
                WEB3StringTypeUtility.formatNumber(l_intMonthDate % 12, 2);
            
            String l_strSystemAll = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMddHHmmss");
            
            String l_strHMS = l_strSystemAll.substring(6, 14);
            
            String l_strCreatedTime = l_strAfterYM + l_strHMS;            
                       
            l_lisValue.add(l_strCreatedTime);
        }
        //７）Listから配列を取得して、返却する。
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        for (int i = 0; i < l_strValue.length; i++)
        {
            log.debug("条件のデータコンテナ = " + l_strValue[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }
    
    /**
     * (createソート条件) <BR>
     * ソート条件を作成し、返却する。 <BR>
     * <BR>
     * １）テーブル列物理名より、以下のソート条件を表すソート条件文字列を作成する。<BR>  
     * <BR>
     * FX説明不要承諾履歴管理.履歴番号　@降順  <BR>
     * <BR>
     * ２）作成したソート条件文字列を返却する。 <BR>
     * <BR>
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createSortCond()
    {
        String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);        
        
        String l_strSort = new String();
        //１）テーブル列物理名より、以下のソート条件を表すソート条件文字列を作成する。  
        //FX説明不要承諾履歴管理.履歴番号　@降順  
        l_strSort = " fx_serial_no desc";
        
        //２）作成したソート条件文字列を返却する。        
        log.exiting(STR_METHOD_NAME);        
        return l_strSort;
    }
    
    /**
     * (insert送信電文) <BR>
     * ソート条件を作成し、返却する。 <BR>
     * <BR>
     * 送信電文をシングルサインオン保存テーブルにinsertする。 <BR>
     * <BR>
     * 挿入する行の内容に関しては、下記を参照。  <BR>
     * 【ｘTrade】補足資料.DB更新  <BR>
     * 「シングルサインオンデータ保存テーブル.xls」 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_strPostCreateData - ポスト用生成前データ
     * @@param l_strEncryptString - 暗号化文字列
     * @@param l_strSecretKey - 秘密鍵
     * @@param l_strHashValue - ハッシュ値
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected void insertSsoMessage(
        SubAccount l_subAccount, 
        String l_strPostCreateData, 
        String l_strEncryptString, 
        String l_strSecretKey, 
        String l_strHashValue)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertSsoMessage(" +
            "SubAccount, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        SsoMessageParams l_ssoMessageparams = new SsoMessageParams();
        
        //証券会社コード = 引数.補助口座.証券会社コード
        l_ssoMessageparams.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());
        
        //部店コード = 引数.補助口座.部店コード
        l_ssoMessageparams.setBranchCode(
            l_subAccount.getMainAccount().getBranch().getBranchCode());
        
        //顧客コード = 引数.補助口座.顧客コード
        l_ssoMessageparams.setAccountCode(
            l_subAccount.getMainAccount().getAccountCode());
        
        //FXログインID = 引数.ポスト用生成前データの1桁目から9桁目
        l_ssoMessageparams.setFxLoginId(
            Long.parseLong(l_strPostCreateData.substring(0, 9)));
        
        //乱数 = 引数.ポスト用生成前データの22桁目から25桁目
        l_ssoMessageparams.setRandom(
            l_strPostCreateData.substring(21, 25));
        
        //処理時間 = 引数.ポスト用生成前データの10桁目～21桁目（YYYYMMDDHH24MI）
        l_ssoMessageparams.setOperationTime(
            l_strPostCreateData.substring(9, 21));
        
        //暗号化文字列 = 引数.暗号化文字列
        l_ssoMessageparams.setEncryptString(l_strEncryptString);
        
        //引数.秘密鍵 = 引数.秘密鍵
        l_ssoMessageparams.setSecretKey(l_strSecretKey);
        
        //ハッシュ値 = 引数.ハッシュ値
        l_ssoMessageparams.setHashKey(l_strHashValue);
        
        //作成日付 = システムタイムスタンプ（YYYYMMDDHH24MISS）
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        
        l_ssoMessageparams.setCreatedTimestamp(l_timestamp);
        
        //更新日付 = システムタイムスタンプ（YYYYMMDDHH24MISS）
        l_ssoMessageparams.setLastUpdatedTimestamp(l_timestamp);
        
        try
        {
            WEB3DataAccessUtility.insertRow(l_ssoMessageparams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In 送信電文をシングルサインオン保存テーブルにinsertする", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In 送信電文をシングルサインオン保存テーブルにinsertする", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(STR_METHOD_NAME);
    }    
    
    private long generateRandom(int digit)
    {
        double l_dblMax = Math.pow(10, digit);
        
        double l_lngMin = Math.pow(10, digit - 1);        
        
        double l_dblRandom = Math.random();
        
        long l_lngRandom = 
            (long)((l_dblMax - l_lngMin) * l_dblRandom + l_lngMin);
        
        return l_lngRandom;
    }
    
    /**
     * DESで文字列の暗号化を行う。<BR>
     *<BR>
     * @@param l_strPlane 暗号化したい文字列
     * @@param l_strKeyData 秘密鍵データ
     * @@return 暗号化した文字列
     */
    private String encrypt(String l_strPlane, String l_strKeyData)
    {
        final String STR_METHOD_NAME = "encrypt(String, String)";
        log.entering(STR_METHOD_NAME);

        final String TRANSFORMATION = "DES";

        //秘密鍵
        SecretKeySpec l_key;
        byte[] l_btDesKeyData = l_strKeyData.getBytes();
        //暗号化の機@能を提供するオブジェクト
        Cipher l_cipher;
        byte[] l_btArray = null;

        l_key = new SecretKeySpec(l_btDesKeyData, TRANSFORMATION);
        try
        {
            l_cipher = Cipher.getInstance(TRANSFORMATION);

            l_cipher.init(Cipher.ENCRYPT_MODE, l_key);
            // 暗号化を行う
            l_btArray = l_cipher.doFinal(l_strPlane.getBytes());
        }
        catch (Exception l_exp)
        {
            throw new RuntimeSystemException(l_exp.getMessage(), l_exp);
        }

        BASE64Encoder l_encoder = new BASE64Encoder();

        log.exiting(STR_METHOD_NAME);
        return l_encoder.encode(l_btArray);
    }

}@
