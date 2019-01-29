head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注メインサービスImpl(WEB3ToEquityManualOrderMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 魏新(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.rlsgateway.define.WEB3RlsNotifyTypeDef;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式手動発注メインサービスImpl)<BR>
 * 抽象クラス（abstract）<BR>
 * 
 * @@author 魏新 <BR>
 * @@version 1.0<BR>
 */
public abstract class WEB3ToEquityManualOrderMainServiceImpl 
    extends WEB3ClientRequestService implements WEB3ToEquityManualOrderMainService 
{
    
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToEquityManualOrderMainServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToEquityManualOrderMainServiceImpl() 
    {
     
    }
    
    /**
     * 株式注文更新メインサービス処理を実施する。<BR>
     * <BR>
     * １）　@instanceof(株式手動発注完了リクエスト)==trueの場合 <BR>
     * 　@　@　@　@　@this.submit()をコールする。 <BR>
     * 　@　@　@そうでない場合、 <BR>
     * 　@　@　@　@　@this.validate()をコールする。 <BR>
     * <BR>
     * ２）　@１）の戻り値を返す。 <BR>
     * <BR>
     * @@param l_request - (WEB3GenRequest) <BR>
     * @@return WEB3GenResponse <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータはNullです");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        //１）　@instanceof(株式手動発注完了リクエスト)==trueの場合
        if(l_request instanceof WEB3EquityManualCompleteRequest)
        {
            l_response = this.submit((WEB3EquityManualCompleteRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityManualConfirmRequest)
        {
            l_response = this.validate((WEB3EquityManualConfirmRequest) l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        log.exiting(STR_METHOD_NAME);
        //２）　@１）の戻り値を返す。
        return l_response;
    }
    
    /**
     * シーケンス図  <BR>
     * 「(株式手動発注メインサービス)validate」参照。 <BR>
     * <BR>
     *  レスポンス.手動発注エラーコードが"正常"の株式手動発注Unitが1件も存在しない場合、<BR>
     *        「手動発注対象注文なし」の例外をthrowする。<BR>
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag   :BUSINESS_ERROR_02393<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualConfirmResponse validate(
        WEB3EquityManualConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(WEB3EquityManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2 validate管理者権限(String[])
            this.validateAdminPermission(l_request.branchCode);
        }
        //1.3 ArrayListを生成する。
        List l_lisEquityManualUnit = new ArrayList();

        //1.4 getUnitService( )
        WEB3ToEquityManualOrderUnitService l_toEquityManualOrderUnitService = 
            this.getUnitService();
            
        //1.5 リクエストデータ.注文ID[]の要素数分loop処理
        String[] l_strOrderIds = l_request.orderId;
        boolean l_blnManualOrderError = true;
        for (int i = 0; i < l_strOrderIds.length; i++)
        {
            //1.5.1 validate(String)
            WEB3EquityManualUnit l_equityManualUnit
                = l_toEquityManualOrderUnitService.validate(l_strOrderIds[i]);
            if(WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                l_equityManualUnit.manualOrderErrorCode))
            {
                l_blnManualOrderError = false;
            }            
            //1.5.2 add(arg0 : Object)
            l_lisEquityManualUnit.add(l_equityManualUnit);
        }
        //submit( )の戻り値.手動発注エラーコードが"正常"の戻り値が1件も無かった場合、
        //「手動発注対象注文なし」の例外をthrowする。
        if (l_blnManualOrderError)
        {
            log.debug("手動発注対象注文なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393,
                this.getClass().getName() + STR_METHOD_NAME, 
                "手動発注対象注文なし。");  
        }
        
        WEB3EquityManualUnit[] l_equityManualUnits = new WEB3EquityManualUnit[l_lisEquityManualUnit.size()]; 
        //1.6 toArray( )
        l_lisEquityManualUnit.toArray(l_equityManualUnits);
        
        //1.7 createResponse( )
        WEB3EquityManualConfirmResponse l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
        
        //1.8 set確認レスポンスプロパティ(株式手動発注確認レスポンス, 株式手動発注Unit[])
        this.setConfirmResponseProperty(l_response, l_equityManualUnits);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * シーケンス図 <BR>
     * 「(株式手動発注メインサービス)submit」参照。<BR>
     * <BR>
     *  レスポンス.手動発注エラーコードが"正常"の株式手動発注Unitが1件も存在しない場合、<BR>
     * 「手動発注対象注文なし」の例外をthrowする。<BR>
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag   :BUSINESS_ERROR_02393<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualCompleteResponse submit(
        WEB3EquityManualCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(WEB3EquityManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        String l_strRlsNotifyType = null;
        //1.1 validate( )
        l_request.validate();
        
        //1.2 （分岐フロー：管理者の場合）
        //※管理者の場合(OpLoginSecurityService.getAccountIdの
        //戻り値 == 0)の場合
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                    
        if (l_opLoginSec.getAccountId() == 0)
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

            //1.2.1 validate管理者権限(String[])
            this.validateAdminPermission(l_request.branchCode);
            
            //1.2.2 validate取引パスワード
            l_admin.validateTradingPassword(l_request.password);            
            l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_ADMIN;
        }
        else
        {
            // 1.3 get代理入力者( ) 
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
            if(l_trader == null)
            {
                //顧客の場合（管理者でない
                //&&代理入力者オブジェクトも取得できない場合））のみ実施する。
                
                //1.4 validate取引パスワード
                this.validateTradingPassword(l_request.password);
                l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_USER;
            }
            else
            {
                l_strRlsNotifyType = WEB3RlsNotifyTypeDef.MANUAL_OPERATOR;
            }
        }
        //1.5 ArrayListを生成する。
        List l_lisEquityManualUnit = new ArrayList();

        //1.6 getUnitService( )
        WEB3ToEquityManualOrderUnitService l_toEquityManualOrderUnitService = this.getUnitService();
        
        //1.7 リクエストデータ.注文ID[]の要素数分loop処理
        String[] l_strOrderIds = l_request.orderId;
        boolean l_blnManualOrderError = true;
        for (int i = 0; i < l_strOrderIds.length; i++)
        {
            //1.7.1 submit(String, Long, String)
            WEB3EquityManualUnit l_equityManualUnit = 
                l_toEquityManualOrderUnitService.submit(
                    l_strOrderIds[i],
                    new Long(l_opLoginSec.getLoginId()),
                    l_strRlsNotifyType);
                
            if(WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_equityManualUnit.manualOrderErrorCode))
            {
                l_blnManualOrderError = false;
            }            
            //1.7.2 add(arg0 : Object)
            l_lisEquityManualUnit.add(l_equityManualUnit);
        }
        //submit( )の戻り値.手動発注エラーコードが"正常"の戻り値が1件も無かった場合、
        //「手動発注対象注文なし」の例外をthrowする。
        if (l_blnManualOrderError)
        {
            log.debug("手動発注対象注文なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393,
                this.getClass().getName() + STR_METHOD_NAME, 
                "手動発注対象注文なし。");  
        }
        
        WEB3EquityManualUnit[] l_equityManualUnits = new WEB3EquityManualUnit[l_lisEquityManualUnit.size()];
        //1.8 toArray( )
        l_lisEquityManualUnit.toArray(l_equityManualUnits);
        
        //1.9 createResponse( )
        WEB3EquityManualCompleteResponse l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
        
        //1.10 set完了レスポンスプロパティ(株式手動発注完了レスポンス, 株式手動発注Unit[])
        this.setCompleteResponseProperty(l_response, l_equityManualUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate管理者権限)
     * <BR>
     * １）　@ログイン情報より管理者インスタンスを取得する。  <BR>
     * 　@　@　@管理者.getInstanceFromログイン情報()をコールする。 <BR>
     * <BR>
     * <BR>
     * ２）　@管理者権限チェック。 <BR>
     * 　@　@　@管理者.validate権限()をコールする。 <BR>
     * <BR>
     * 　@　@　@[validate権限()に指定する引数] <BR>
     * 　@　@　@機@能カテゴリコード：<BR>
     * 　@　@　@　@WEB3TransactionCategoryDef.国内株式(トリガー注文手動発注) <BR>
     * 　@　@　@is更新：　@true <BR>
     * <BR>
     * <BR>
     * ３）　@ 部店権限チェック。 <BR>
     * 　@　@　@ 管理者.validate部店権限()をコールする。 <BR>
     * <BR>
     * 　@　@　@[validate権限()に指定する引数] <BR>
     * 　@　@　@部店コード：　@パラメータ.部店コード一覧 <BR>
     * <BR>
     * @@param l_strBranchCodes - (部店コード一覧) <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 432175DD01A0
     */
    protected void validateAdminPermission(
        String[] l_strBranchCodes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdminPermission(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        //２）　@管理者権限チェック。
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_MANUAL, 
            true);
        //３）　@ 部店権限チェック。 
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);

        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (validate取引パスワード)
     * <BR>
     * 顧客の取引パスワードをチェックする。 <BR>
     * <BR>
     * １）　@顧客オブジェクトを取得する。 <BR>
     * 　@　@　@this.get口座()をコールする。 <BR>
     * <BR>
     * ２）　@取引パスワードチェック。 <BR>
     * 　@　@　@注文チェック.validate取引パスワード()をコールする。 <BR>
     * <BR>
     * 　@　@　@[validate取引パスワード()に指定する引数] <BR>
     * 　@　@　@顧客：　@get口座()の戻り値 <BR>
     * 　@　@　@パスワード：　@パラメータ.パスワード <BR>
     * <BR>
     * @@param l_strPassword - (パスワード)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void validateTradingPassword(
        String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPassword(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount= 
            (WEB3GentradeMainAccount) this.getMainAccount();
        
        //２）　@取引パスワードチェック。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(l_mainAccount, l_strPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("パスワードのチェックを行う");            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * 抽象メソッド（abstract）<BR>
     * @@return WEB3EquityManualOrderUnitService
     * @@roseuid 432175DD01A0
     */
    protected abstract WEB3ToEquityManualOrderUnitService getUnitService();
    
    /**
     * (set確認レスポンスプロパティ)
     * <BR>
     * １）　@下記条件の全てに該当する場合のみ以下を実施する。 <BR>
     * 　@　@　@　@・顧客である。 <BR>
     * 　@　@　@　@・パラメータ.株式手動発注Unit[0].手動発注エラーコードが"正常"である。 <BR>
     * <BR>
     * 　@　@１－１）　@顧客オブジェクトを取得する。 <BR>
     * 　@　@　@　@　@　@　@this.get顧客()をコールする。 <BR>
     * <BR>
     * 　@　@１－２）　@当該客が信用客かどうか判定する。  <BR>
     * <BR>
     * 　@　@　@　@　@　@　@顧客．is信用口座開設("0：指定なし")が、"true"の場合は信用客。 <BR>
     * 　@　@　@　@　@　@　@以外、非信用客。 <BR>
     * <BR>
     * 　@　@１－３）　@this.get補助口座( )にて、該当顧客の補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@[get補助口座()に設定する引数]  <BR>
     * 　@　@　@　@　@　@　@　@信用客の場合：　@SubAccountTypeEnum.信用取引口座をセット <BR>
     * 　@　@　@　@　@　@　@　@非信用客の場合：　@SubAccountTypeEnum.株式取引口座をセット。 <BR>
     * <BR>
     * 　@　@１－４）　@this.set取引カレンダコンテキスト( )をコールする。  <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@[set取引カレンダコンテキスト()に設定する引数]  <BR>
     * 　@　@　@　@　@　@　@　@株式手動発注Unit：　@株式手動発注Unitの0番目の要素<BR>
     * 　@　@１－５）　@市場終了警告市場コードを取得する。 <BR>
     * 　@　@　@　@　@　@　@取引時間管理.get市場閉局警告市場()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@[get市場閉局警告市場()に設定する引数] <BR>
     * 　@　@　@　@　@　@　@　@部店：　@get補助口座()の戻り値.get取引店()  <BR>
     * 　@　@　@　@　@　@　@　@銘柄タイプ：　@"株式"  <BR>
     * 　@　@　@　@　@　@　@　@信用取引区分：　@(*1) <BR>
     * <BR>
     * (*1)信用取引区分 <BR>
     * 　@　@①@非信用客の場合、"DEFAULT"をセット。 <BR>
     * 　@　@②信用客の場合は以下の通り。 <BR>
     * 　@　@　@　@・以下条件全てに該当する場合、”制度／一般信用(両方)”をセット。 <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”制度信用”)がtrue <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”一般信用”)がtrue <BR>
     * 　@　@　@　@・以下条件全てに該当する場合、”制度信用”をセット。 <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”制度信用”)がtrue <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”一般信用”)がfalse <BR>
     * 　@　@　@　@・以下条件全てに該当する場合、”一般信用”をセット。 <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”制度信用”)がfalse <BR>
     * 　@　@　@　@　@　@　@顧客.is信用口座開設(”一般信用”)がtrue  <BR>
     * <BR>
     * ２）　@確認レスポンスにプロパティをセットする。 <BR>
     * <BR>
     * 　@株式手動発注Unit：　@パラメータ.株式手動発注一覧 <BR>
     * 　@市場終了警告市場コード：　@get市場閉局警告市場()の戻り値(*2) <BR>
     * <BR>
     * 　@(*2)顧客のみセットする。 <BR>
     * 　@　@　@　@管理者の場合はnullをセット。 <BR>
     * <BR>
     * ３）　@確認レスポンスを返す。 <BR>
     * <BR>
     * @@param l_confirmResponse - (確認レスポンス)<BR>
     * @@param l_equityManualUnits - (株式手動発注Unit[])<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualConfirmResponse setConfirmResponseProperty(
        WEB3EquityManualConfirmResponse l_confirmResponse, 
        WEB3EquityManualUnit[] l_equityManualUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setConfirmResponseProperty(" +
            "WEB3EquityManualConfirmResponse, WEB3EquityManualUnit[])";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();
        SubAccountTypeEnum l_subAccountTypeEnum = null;
        String l_strMarginTradingDiv = null;
        
        //１）　@下記条件の全てに該当する場合のみ以下を実施する。 
        //　@　@・顧客である。 
        //　@　@・パラメータ.株式手動発注Unit[0].手動発注エラーコードが"正常"である。 

        if (l_opLoginSec.getAccountId() != 0 && l_trader == null &&
                WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                    l_equityManualUnits[0].manualOrderErrorCode))
        {
            //１－１）　@顧客オブジェクトを取得する。 
            WEB3GentradeMainAccount l_mainAccount= (
                WEB3GentradeMainAccount) this.getMainAccount();
            
            WEB3GentradeSubAccount l_subAccount = null;
            //１－２）　@当該客が信用客かどうか判定する。 
            if(l_mainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                // 信用客の場合：　@SubAccountTypeEnum.信用取引口座をセット 
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
                
                //②信用客の場合は以下の通り。
                boolean l_blnMarginSys =
                    l_mainAccount.isMarginAccountEstablished(
                        WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
                
                boolean l_blnMarginGen =
                    l_mainAccount.isMarginAccountEstablished(
                        WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
                
                //・以下条件全てに該当する場合、”制度／一般信用(両方)”をセット。 
                //       顧客.is信用口座開設(”制度信用”)がtrue 
                //       顧客.is信用口座開設(”一般信用”)がtrue                     
                if(l_blnMarginSys && l_blnMarginGen)
                {
                    l_strMarginTradingDiv = 
                        WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
                }
                else if(l_blnMarginSys && !l_blnMarginGen)
                {
                    l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
                }
                else if(!l_blnMarginSys && l_blnMarginGen)
                {
                    l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
                }
            }
            else
            {
                //非信用客の場合：　@SubAccountTypeEnum.株式取引口座をセット
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                // ①@非信用客の場合、"DEFAULT"をセット。
                l_strMarginTradingDiv =WEB3MarginTradingDivDef.DEFAULT;                  
            }
            // １－３）　@this.get補助口座( )にて、該当顧客の補助口座オブジェクトを取得する。
            l_subAccount = 
                (WEB3GentradeSubAccount) this.getSubAccount(l_subAccountTypeEnum);
            
            // １－４）　@this.set取引カレンダコンテキスト( )をコールする。  
            //　@　@[set取引カレンダコンテキスト()に設定する引数]  
            //　@　@株式手動発注Unit：　@株式手動発注Unitの0番目の要素
            this.setTradingClendarContext(l_equityManualUnits[0]);
            
            //１－５）　@市場終了警告市場コードを取得する。
            //取引時間管理.get市場閉局警告市場()
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                    l_subAccount.getWeb3GenBranch(),
                    ProductTypeEnum.EQUITY,
                    l_strMarginTradingDiv
                    );
            
            //２）　@確認レスポンスにプロパティをセットする。 
            l_confirmResponse.messageSuspension = l_strTradeCloseSuspensions;
        }
        // 管理者の場合はnullをセット
        else if(l_opLoginSec.getAccountId() == 0)
        {
            l_confirmResponse.messageSuspension = null;
        }
        l_confirmResponse.manualUnits = l_equityManualUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

    /**
     * (set完了レスポンスプロパティ)
     * <BR>
     * 株式手動発注完了レスポンスに値をセットし、返す。 <BR>
     * <BR>
     * １）　@パラメータ．完了レスポンスにプロパティをセットする。 <BR>
     * <BR>
     * 　@株式手動発注Unit：　@パラメータ.株式手動発注一覧 <BR>
     * 　@更新時間：　@GtlUtils.getSystemTimestamp() <BR>
     * <BR>
     * ２）　@完了レスポンスを返す。<BR>
     * <BR>
     * @@param l_completeResponse - (完了レスポンス)<BR>
     * @@param l_equityManualUnits - (株式手動発注Unit[])<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualCompleteResponse setCompleteResponseProperty(
        WEB3EquityManualCompleteResponse l_completeResponse, 
        WEB3EquityManualUnit[] l_equityManualUnits)
    {
        final String STR_METHOD_NAME = "setCompleteResponseProperty(" +
            "WEB3EquityManualCompleteResponse, WEB3EquityManualUnit[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@パラメータ．完了レスポンスにプロパティをセットする。
        l_completeResponse.manualUnits = l_equityManualUnits;
        l_completeResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        //２）　@完了レスポンスを返す
        return l_completeResponse;
    }
    /**
     * (set取引カレンダコンテキスト)
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     *<BR>
     *１）　@取引カレンダコンテキストに内容をセットする。 <BR>
     *　@－パラメータ.注文データの内容より取引時間コンテキストの <BR>
     *　@　@　@プロパティをセットする。 <BR>
     *<BR>
     *　@取引カレンダコンテキスト.証券会社コード =  OpLoginSecurityServiceより編集<BR>
     *　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 <BR>
     *　@取引カレンダコンテキスト.市場コード = パラメータ.株式手動発注Unit.市場コード<BR>
     *　@取引カレンダコンテキスト.受付時間区分 = (*1) <BR>
     *　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     *　@取引カレンダコンテキスト.注文受付商品 = null<BR>
     *　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     *<BR>
     *　@(*1)受付時間区分<BR>
     *　@　@　@パラメータ.株式手動発注Unit.取引区分が、<BR>
     *　@　@　@　@・"現引注文"または"現渡注文"の場合、<BR>
     *　@　@　@　@　@　@"現引・現渡"をセットする。<BR>
     *　@　@　@　@・上記以外の場合、<BR>
     *　@　@　@　@　@　@"株式・信用"をセットする。<BR>
     *<BR>
     *　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     *　@　@　@取引時間コンテキストをセットする。 <BR>
     *設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。    <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_equityManualUnit - (株式手動発注Unit)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
   protected void setTradingClendarContext(WEB3EquityManualUnit l_equityManualUnit) 
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "setTradingClendarContext(WEB3EquityManualUnit)";
       log.entering(STR_METHOD_NAME);
       
       if (l_equityManualUnit == null)
       {
           log.debug("パラメータ値不正。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
       }

       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       
       WEB3GentradeAccountManager l_accountManager = 
           (WEB3GentradeAccountManager) l_finApp.getAccountManager();
       
       OpLoginSecurityService l_opLoginSecurityService =
           (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
       
       MainAccount l_mainAccount = null;
       
       try
       {
           long l_lngAccountId = l_opLoginSecurityService.getAccountId();
           l_mainAccount = 
               l_accountManager.getMainAccount(l_lngAccountId);
       }       
       catch (NotFoundException l_ex)
       {
           log.error("テーブルに該当するデータがありません。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
           
       String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
       String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();       
   
       //１）　@取引カレンダコンテキストに内容をセットする。      
       
       WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
       
       //取引カレンダコンテキスト.証券会社コード =  OpLoginSecurityServiceより編集
       l_context.setInstitutionCode(l_strInstitutionCode);
   
       //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集 
       l_context.setBranchCode(l_strBranchCode);
   
       //　@取引カレンダコンテキスト.市場コード = パラメータ.株式手動発注Unit.市場コード 
       l_context.setMarketCode(l_equityManualUnit.marketCode);
   
       //　@取引カレンダコンテキスト.受付時間区分 = (*1) 
       //　@(*1)受付時間区分
       //　@パラメータ.株式手動発注Unit.取引区分が、
       //　@　@・"現引注文"または"現渡注文"の場合、
       //　@　@　@　@"現引・現渡"をセットする。
       //　@　@・上記以外の場合、
       //　@　@　@　@"株式・信用"をセットする。
       
       if (WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG).equals(
               l_equityManualUnit.tradingType) ||
           WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT).equals(
               l_equityManualUnit.tradingType))
       {
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
       }
       //  ・注文カテゴリが上記以外の場合、 
       //　@　@　@”株式・信用”をセットする。
       else
       {
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
       }      
       
       //　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
       l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
       
       //　@取引カレンダコンテキスト.注文受付商品 = null
       l_context.setOrderAcceptProduct(null);       
       
       //　@取引カレンダコンテキスト.注文受付トランザクション = null              
       l_context.setOrderAcceptTransaction(null);
       
       //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて 
       //　@　@取引時間コンテキストをセットする。 
       //    設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
       
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_context);
       
       // ２）　@受付日時、日付ロールをセットする。  
       //　@－取引時間管理.setTimestamp()をコールする。
       WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
     
       log.exiting(STR_METHOD_NAME);
    }
   
}
@
