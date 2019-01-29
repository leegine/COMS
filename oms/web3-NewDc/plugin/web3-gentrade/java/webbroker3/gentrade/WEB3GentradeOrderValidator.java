head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOrderValidator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文チェック(WEB3GentradeOrderValidator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 鄒政 (中訊) 新規作成
Revesion History : 2007/02/28 栄イ (中訊) 仕様変更・モデルNo.222を対応
Revesion History : 2007/11/12 栄イ (中訊) 仕様変更・モデルNo.283を対応
Revesion History : 2007/11/23 栄イ (中訊) 仕様変更・モデルNo.292を対応
Revesion History : 2008/01/23 栄イ (中訊) 仕様変更・モデルNo.304を対応
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CommonOrderValidatorImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchLockDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3OrderPermissionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.define.WEB3GentradeProcessFlagDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 注文チェック<BR>
 * （CommonOrderValidatorImplの拡張クラス）<BR>
 * WEB3EquityOrderValidator→WEB3OrderValidator <BR>
 * <BR> 
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3GentradeOrderValidator extends CommonOrderValidatorImpl
{

    /** 
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderValidator.class);

    /**
     * コンストラクタ。<BR>
     * @@roseuid 409B412D0290
     */
    public WEB3GentradeOrderValidator()
    {

    }

    /**
     * (validate取引パスワード) <BR>
     * 取引パスワードが正しいかのチェックを行う。<BR>
     * （validateTradingPasswordのオーバーライド）<BR>
     * <BR>
     * １）　@処理対象外判定 <BR>
     * 　@ログイン情報が取得できない場合（＝下り処理からのコールの場合）、何もせずに処理を終了する。 <BR>
     * <BR>
     * ２）　@セッションより取得した注文経路区分==”IVR”の場合、何もせずに処理を終了する。 <BR>
     *  <BR>
     * ３）　@代理入力者の取得 <BR>
     * パラメータ.代理入力者がnullの場合、代理入力者の取得を行う。<BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（validate取引パスワード）代理入力者取得」参照。 <BR>
     * ４）　@代理入力の場合のチェック <BR>
     * （パラメータ.代理入力者 == null）の場合、ログイン情報より<BR>
     * 扱者オブジェクトの取得を行う。扱者オブジェクトが取得できた場合、<BR>
     * 代理入力であると判断し、処理を終了する。<BR>
     * （代理入力の場合は、取引パスワードを入力しない）<BR>
     *  <BR>
     * ５）　@顧客入力の場合のチェック <BR>
     * this.validate取引パスワード(顧客, String)をコールし、<BR>
     * 処理結果を返却する。<BR>
     * <BR> 
     * @@param l_proxyInputPerson - (代理入力者)<BR>
     *    トレーダの代理入力の場合指定する。<BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strTradingPassword - (入力パスワード)
     * @@return チェック結果
     * @@roseuid 4042EC7B016D
     */
    public OrderValidationResult validateTradingPassword(
        Trader l_proxyInputPerson,
        SubAccount l_subAccount,
        String l_strTradingPassword)
    {
        final String STR_METHOD_NAME =
            "validateTradingPassword(Trader, SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ログイン情報が取得できない場合（＝下り処理からのコールの場合）、
        // 何もせずに処理を終了する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_loginId = 0L;
        try
        {
            l_loginId = l_opLoginSec.getLoginId();
        }
        catch (IllegalSessionStateException Isse)
        {
            log.info("ログイン情報が取得できない場合" +
                "（＝下り処理からのコールの場合）、何もせずに処理を終了する。");
            return OrderValidationResult.VALIDATION_OK_RESULT;          
        }

        //２）　@セッションより取得した注文経路区分==”IVR”の場合、何もせずに処理を終了する。
        if (WEB3OrderRootDivDef.IVR.equals(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV)))
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }

        //３）　@代理入力者の取得
        //　@パラメータ.代理入力者がnullの場合、代理入力者の取得を行う。
        if (l_proxyInputPerson == null)
        {
            try
            {
                l_proxyInputPerson =
                    l_finApp.getFinObjectManager().getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException e)
            {
                //catchしたNotFoundExceptionを無視する。
            }

        }
                
        //４）　@代理入力の場合のチェック
        //代理入力の場合は、取引パスワードを入力しない
        if(l_proxyInputPerson != null)
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }  
        else
        {
            //５）　@顧客入力の場合のチェック
            //this.validate取引パスワード(顧客, String)をコールし、 
            //処理結果を返却する。
            WEB3GentradeMainAccount l_genMainAccount;
            try
            {
                l_genMainAccount =
                    new WEB3GentradeMainAccount(l_subAccount.getAccountId());
            }
            catch (DataException de)
            {
                log.debug(STR_METHOD_NAME, de);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003));
            }
            return this.validateTradingPassword(
                l_genMainAccount,
                l_strTradingPassword);
        }

    }
    
    /**
     * (validate取引パスワード) <BR>
     * 取引パスワードが正しいかのチェックを行う。<BR>
     * （validateTradingPasswordのオーバーライド）<BR>
     * <BR> 
     * 　@OpLoginSecurityServiceより、ログインタイプ属性を取得する。<BR>
     *  <BR>
     * －ログインタイプ属性.属性名 == 取引パスワード設定 <BR>
     *    （：TRADING_PWD_ENV）の属性値が”0：DEFAULT<BR>
     *    （取引パスワード項目を使用しない）”の場合、<BR>
     *     ログインパスワードの照合※1する。<BR>
     *  <BR>
     * －ログインタイプ属性.属性名 == 取引パスワード設定 <BR>
     *    （：TRADING_PWD_ENV）の属性値が”1：取引パスワード使用”<BR>
     *     の場合、取引パスワードの照合※2する。<BR>
     *  <BR>
     * ※1 ログインパスワードの照合 <BR>
     * （OpLoginSecurityService.checkPassword() == false）の場合、<BR>
     * エラー結果を返却する。以外、正常終了を返却する。<BR>
     *  <BR>
     * ※2 取引パスワードの照合 <BR>
     * 顧客.取引パスワードを復号化（WEB3Crypt.decrypt()を使用）する。<BR>
     * 復号化したパスワードと引数のパスワードが一致しない場合、<BR>
     * エラー結果を返却する。以外、正常終了を返却する。<BR> 
     *  <BR>
     * @@param l_genMainAccount - (顧客)
     * @@param l_strTradingPassword - (入力パスワード)
     * @@return チェック結果
     * @@roseuid 4042EC7B016D
     */
    public OrderValidationResult validateTradingPassword(
        WEB3GentradeMainAccount l_genMainAccount,
        String l_strTradingPassword)
    {
        final String STR_METHOD_NAME = "validateTradingPassword(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        OrderValidationResult l_orderValidationResult;
        
        //1) OpLoginSecurityServiceより、ログインタイプ属性を取得する
        
        //サービスを取得
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //ログインタイプ属性を取得する
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        
        //2) ログインパスワードの照合
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //（OpLoginSecurityService.checkPassword() == false）の場合、
            //エラー結果を返却する。以外、正常終了を返却する。
            if(l_securityService.checkPassword(l_strTradingPassword) == false)
            {
                l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00009));
            }
            else
            {
                l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            }

        }
        //2) 取引パスワードの照合
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            // 顧客.取引パスワードを復号化（WEB3Crypt.decrypt()を使用）する。
            //復号化したパスワードと引数のパスワードが一致しない場合、
            //エラー結果を返却する。以外、正常終了を返却する。
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strMainAccountPassword =
                l_crypt.decrypt(l_genMainAccount.getTradingPassword());
            if (l_strMainAccountPassword.equals(l_strTradingPassword))
            {
                l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            }
            else
            {
                l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00009));
            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワード設定(ログインタイプ属性) = " + l_strAttribute);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
        
    }

    /**
     * (validate取引可能顧客) <BR>
     * <BR>
     * １）顧客が取引停止中であるか判定する。 <BR>
     *  取得した顧客オブジェクトの内容が、以下(1)～(3)の何れかである場合、<BR>
     *  例外をスローする。 
     *  <BR>
     *  [取引停止中のエラー条件]  <BR>
     *  (1) 顧客.Ｙ客区分 == ”Y客” <BR> 
     *  (2) 顧客.考査ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     *  (3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     * <BR>
     * ２）顧客が管理ロック中かを判定する。  <BR>
     * （発注日が特定できる場合(*2)かつ、強制決済注文でない場合(*3)のみチェックする。） <BR>
     *   取得した顧客オブジェクトの内容が、管理ロック中で且つ、<BR>
     *   ロック解除期間外の場合、例外をスローする。 <BR>
     *  <BR>
     *  [管理ロック中のエラー条件] <BR>
     *  顧客.管理ロック == ”ロック” And <BR>
     *  顧客.注文認可 == ”非認可” And <BR>
     *  { 顧客.管理ロック解除開始日 ＞ 発注日(*1) Or <BR>
     *    顧客.管理ロック解除終了日 ＜ 発注日(*1) }　@<BR>
     *  ※発注日がロック解除期間外 <BR>
     * <BR>
     * (*1) 発注日 <BR>
     * 取引時間管理.get発注日()にて取得する。<BR> 
     * (*2) 発注日が特定できる場合 <BR>
     * 取引時間管理.get発注日()で正常に発注日が取得できる<BR>
     * （例外がスローされない）場合。 <BR>
     * (*3) 強制決済注文でない場合 <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP) <BR>
     * で取得した値がBooleanEnum.TRUEでない場合。 <BR>
     *  <BR>
     * @@param l_genMainAccount - (顧客) <BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 409B3FC102CE
     */
    public OrderValidationResult validateAccountForTrading(WEB3GentradeMainAccount l_genMainAccount)
    {
        final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get 顧客.Ｙ客区分
        String l_strYellowCustomer = l_mainAccountRow.getYellowCustomer();
        //get 顧客.考査ロック
        String l_strExaminLockFlag = l_mainAccountRow.getExaminLockFlag();
        //get 顧客.支店ロック
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get 顧客.注文認可
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get 顧客.管理ロック
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();

        // １）顧客が取引停止中であるか判定する。   
        //[取引停止中のエラー条件]  
        //(1) 顧客.Ｙ客区分 == ”Y客” 
        //(2) 顧客.考査ロック == ”ロック” And 顧客.注文認可 == ”非認可” 
        //(3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可” 
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_strYellowCustomer)
            || (WEB3MngLockDef.LOCK.equals(l_strExaminLockFlag) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            || (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission)))
        {
            log.debug("[Ｙ客区分] ： " + l_strYellowCustomer);
            log.debug("[考査ロック] ： " + l_strExaminLockFlag);
            log.debug("[支店ロック] ： " + l_strBranchLock);
            log.debug("[注文認可] ： " + l_strOrderPermission);
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00275));
        }

        //取引時間管理.get発注日()にて取得
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        catch (WEB3SystemLayerException e)
        {
            log.debug("管理ロックについては、取引時間管理.get発注日()で例外がthrowされた場合はチェックしない");
            log.exiting(STR_METHOD_NAME);
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }
        if(l_datBizDate == null)
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }

        //強制決済注文の場合
        if (BooleanEnum.TRUE.equals(
            ThreadLocalSystemAttributesRegistry.getAttribute(  
                WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP)))
        {
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }
        //get 顧客.管理ロック解除開始日
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get 顧客.管理ロック解除終了日 
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();

        //２）顧客が管理ロック中かを判定する。
        //[管理ロック中のエラー条件] 
        // 顧客.管理ロック == ”ロック” And 
        // 顧客.注文認可 == ”非認可” And 
        // { 顧客.管理ロック解除開始日 ＞ 発注日(*1) Or 
        //   顧客.管理ロック解除終了日 ＜ 発注日(*1) }
        if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
           && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
        {
            if ((l_datStartDate != null) && (l_datEndDate != null))
            {
                if ((WEB3DateUtility.compareToDay(l_datStartDate, l_datBizDate) > 0)
                    || (WEB3DateUtility.compareToDay(l_datEndDate, l_datBizDate) < 0))
                {
                    log.debug("[管理ロック] ： " + l_strMngLockFlag);
                    log.debug("[注文認可] ： " + l_strOrderPermission);
                    log.debug("[管理ロック解除開始日] ： " + l_datStartDate);
                    log.debug("[管理ロック解除終了日] ： " + l_datEndDate);
                    log.debug("[発注日] ： " + l_datBizDate);
                    log.debug("管理ロック顧客エラー");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00276));
                }
            }
            else
            {
                log.debug("[管理ロック] ： " + l_strMngLockFlag);
                log.debug("[注文認可] ： " + l_strOrderPermission);
                log.debug("[管理ロック解除開始日／終了日] ： null");
                log.debug("管理ロック顧客エラー");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00276));
            }
        }

        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
    
    /**
     * (validate取引可能顧客) <BR>
     *（validateSubAccountForTradingのオーバーライド）<BR>
     *  <BR>
     * １）　@スーパークラスの処理を実施する。<BR>
     *  <BR>
     * ２）　@取引可能客かを判定する。<BR>
     * this.validate取引可能顧客（顧客）　@をコールし、処理結果を返却する。<BR>
     *  <BR>
     * [validate取引可能顧客()に指定する引数] <BR>
     *  顧客：　@補助口座.getMainAccount() <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateSubAccountForTrading(SubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "validateSubAccountForTrading(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //１）スーパークラスの処理を実施する
        OrderValidationResult l_result =
            super.validateSubAccountForTrading(l_subAccount);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            return l_result;
        }
        
        //get顧客
        WEB3GentradeMainAccount l_genMainAccount;
        try
        {
            l_genMainAccount = 
                new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataException de)
        {
            log.debug(STR_METHOD_NAME,de);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        
        //２） 　@取引可能客かを判定する
        l_result =  this.validateAccountForTrading(l_genMainAccount);
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate取引可能顧客) <BR>
     *（validateAccountForTrading）<BR>
     *  <BR>
     * １）　@顧客が取引停止中であるか判定する。<BR>
     * 引数の顧客オブジェクトの内容が、以下(1)～(3)の何れかである場合、<BR>
     * 例外をスローする。<BR>
     *  <BR>
     * [取引停止中のエラー条件] <BR>
     * (1) 顧客.Ｙ客区分 == ”Y客” <BR>
     * (2) 顧客.考査ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     * (3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     *  <BR>
     * ２）　@顧客が管理ロック中かを判定する。<BR>
     * 取得した顧客オブジェクトの内容が、<BR>
     * 管理ロック中で且つ、ロック解除期間外の場合、例外をスローする。<BR>
     *  <BR>
     * [管理ロック中のエラー条件] <BR>
     * 顧客.管理ロック == ”ロック” And <BR>  
     * 顧客.注文認可 == ”非認可” And <BR>
     * { 顧客.管理ロック解除開始日 ＞ 引数.発注日 Or <BR> 
     *   顧客.管理ロック解除終了日 ＜ 引数.発注日 }　@<BR>
     * ※発注日がロック解除期間外<BR>
     *  <BR>
     * @@param l_genMainAccount - (顧客) <BR>
     * @@param l_tsBizDate - (発注日)<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_genMainAccount,
        Timestamp l_tsBizDate)
    {
        final String STR_METHOD_NAME = 
            "validateSubAccountForTrading(WEB3GentradeMainAccount, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get 顧客.Ｙ客区分
        String l_strYellowCustomer = l_mainAccountRow.getYellowCustomer();
        //get 顧客.考査ロック
        String l_strExaminLockFlag = l_mainAccountRow.getExaminLockFlag();
        //get 顧客.支店ロック
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get 顧客.注文認可
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get 顧客.管理ロック
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();
        //get 顧客.管理ロック解除開始日
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get 顧客.管理ロック解除終了日 
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();
        
        //１）　@顧客が取引停止中であるか判定する。
        //引数の顧客オブジェクトの内容が、以下(1)～(3)の何れかである場合、例外をスローする。
        //   [取引停止中のエラー条件] 
        //   (1) 顧客.Ｙ客区分 == ”Y客” 
        //   (2) 顧客.考査ロック == ”ロック” And 顧客.注文認可 == ”非認可” 
        //   (3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可”
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_strYellowCustomer)
            || (WEB3MngLockDef.LOCK.equals(l_strExaminLockFlag) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            || (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock) 
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission)))
        {
            log.debug("[Ｙ客区分] ： " + l_strYellowCustomer);
            log.debug("[考査ロック] ： " + l_strExaminLockFlag);
            log.debug("[支店ロック] ： " + l_strBranchLock);
            log.debug("[注文認可] ： " + l_strOrderPermission);
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00275));
        }
        
        //２）　@顧客が管理ロック中かを判定する。
        //取得した顧客オブジェクトの内容が、
        //管理ロック中で且つ、ロック解除期間外の場合、例外をスローする。
        //   [管理ロック中のエラー条件] 
        //    顧客.管理ロック == ”ロック” And 
        //    顧客.注文認可 == ”非認可” And 
        //    { 顧客.管理ロック解除開始日 ＞ 引数.発注日 Or 
        //      顧客.管理ロック解除終了日 ＜ 引数.発注日 }　@
        if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
           && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
        {
            if ((l_datStartDate != null) && (l_datEndDate != null))
            {
                if ((WEB3DateUtility.compareToDay(l_datStartDate, l_tsBizDate) > 0)
                    || (WEB3DateUtility.compareToDay(l_datEndDate, l_tsBizDate) < 0))
                {
                    log.debug("[管理ロック] ： " + l_strMngLockFlag);
                    log.debug("[注文認可] ： " + l_strOrderPermission);
                    log.debug("[管理ロック解除開始日] ： " + l_datStartDate);
                    log.debug("[管理ロック解除終了日] ： " + l_datEndDate);
                    log.debug("[発注日] ： " + l_tsBizDate);
                    log.debug("管理ロック顧客エラー");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00276));
                }
            }
            else
            {
                log.debug("[管理ロック] ： " + l_strMngLockFlag);
                log.debug("[注文認可] ： " + l_strOrderPermission);
                log.debug("[管理ロック解除開始日／終了日] ： null");
                log.debug("管理ロック顧客エラー");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00276));
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    /**
     * (validate取引可能顧客) <BR>
     *  <BR>
     * １）　@顧客が取引停止中であるか判定する。 <BR>
     * <BR>
     * 　@引数の顧客オブジェクトの内容が、以下の[取引停止中のエラー条件]に適合する場合、例外をスローする。 <BR>
     * <BR>
     * 【エラーチェック条件】 <BR>
     * ○引数.処理フラグ = 1 の場合、(3),(4)のチェックを行う。 <BR>
     * 　@引数.処理フラグ = 1 以外の場合、以下のチェックは行わない。 <BR>
     * <BR>
     * <BR>
     * 　@[取引停止中のエラー条件] <BR>
     * 　@(1) 顧客.Ｙ客区分 == ”Y客” <BR>
     * 　@(2) 顧客.考査ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     * 　@(3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可” <BR>
     * 　@(4) 顧客が管理ロック中かを判定する。 <BR>
     * 　@　@　@取得した顧客オブジェクトの内容が、 <BR>
     * 　@　@　@管理ロック中で且つ、ロック解除期間外の場合、例外をスローする。 <BR>
     * 　@　@　@[管理ロック中のエラー条件] <BR>
     * 　@　@　@顧客.管理ロック == ”ロック” And   <BR>
     * 　@　@　@顧客.注文認可 == ”非認可” And <BR>
     * 　@　@　@{ 顧客.管理ロック解除開始日 > 引数.発注日 Or  <BR>
     * 　@　@　@  顧客.管理ロック解除終了日 < 引数.発注日 }　@※発注日がロック解除期間外 <BR>
     * <BR>
     * <BR>
     * ※部分的な取引停止のチェックを行う際にこのメソッドを使用します。 <BR>
     * 　@ チェックしたいロックを処理フラグによってカスタマイズしてください。 <BR>
     * <BR>
     * ※引数.処理フラグ = 1　@の場合は以下のエラーメッセージを出力する。 <BR>
     * 　@(3)で例外をスロー場合、支店ロックエラー。（新規エラーメッセージ） <BR>
     *   (4)で例外をスロー場合、管理ロックエラー。（新規エラーメッセージ）<BR>
     *  <BR>
     * @@param l_genMainAccount - (顧客) <BR>
     * @@param l_tsBizDate - (発注日)<BR>
     * @@param l_strProcessFlag - (処理フラグ)<BR>
     * @@return
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     */
    public OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_genMainAccount,
        Timestamp l_tsBizDate,
        String l_strProcessFlag)
    {
        final String STR_METHOD_NAME =
            "validateSubAccountForTrading(WEB3GentradeMainAccount, Timestamp, String)";
        log.entering(STR_METHOD_NAME);

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_genMainAccount.getDataSourceObject();
        //get 顧客.支店ロック
        String l_strBranchLock = l_mainAccountRow.getBranchLock();
        //get 顧客.注文認可
        String l_strOrderPermission = l_mainAccountRow.getOrderPermission();
        //get 顧客.管理ロック
        String l_strMngLockFlag = l_mainAccountRow.getMngLockFlag();
        //get 顧客.管理ロック解除開始日
        Date l_datStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //get 顧客.管理ロック解除終了日
        Date l_datEndDate = l_mainAccountRow.getMngLockOffEndDate();

        //１）　@顧客が取引停止中であるか判定する。
        //引数の顧客オブジェクトの内容が、以下の[取引停止中のエラー条件]に適合する場合、
        //例外をスローする。
        //【エラーチェック条件】
        //引数.処理フラグ = 1 の場合、(3),(4)のチェックを行う。
        //引数.処理フラグ = 1 以外の場合、以下のチェックは行わない。
        if (WEB3GentradeProcessFlagDef.PROCESS_FLAG_1.equals(l_strProcessFlag))
        {
            //(3) 顧客.支店ロック == ”ロック” And 顧客.注文認可 == ”非認可”
            if (WEB3BranchLockDef.BRANCH_LOCK.equals(l_strBranchLock)
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            {
                log.debug("[支店ロック] ： " + l_strBranchLock);
                log.debug("[注文認可] ： " + l_strOrderPermission);
                log.debug("支店ロックエラー");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02957));
            }
            //(4) 顧客が管理ロック中かを判定する。
            //取得した顧客オブジェクトの内容が、
            //管理ロック中で且つ、ロック解除期間外の場合、例外をスローする。
            //[管理ロック中のエラー条件]
            //顧客.管理ロック == ”ロック” And
            //顧客.注文認可 == ”非認可” And
            //{ 顧客.管理ロック解除開始日 > 引数.発注日 Or
            //顧客.管理ロック解除終了日 < 引数.発注日 }　@※発注日がロック解除期間外
            if (WEB3MngLockDef.LOCK.equals(l_strMngLockFlag)
                && WEB3OrderPermissionDef.NO_PERMISSION.equals(l_strOrderPermission))
            {
                if ((l_datStartDate != null) && (l_datEndDate != null))
                {
                    if ((WEB3DateUtility.compareToDay(l_datStartDate, l_tsBizDate) > 0)
                        || (WEB3DateUtility.compareToDay(l_datEndDate, l_tsBizDate) < 0))
                    {
                        log.debug("[管理ロック] ： " + l_strMngLockFlag);
                        log.debug("[注文認可] ： " + l_strOrderPermission);
                        log.debug("[管理ロック解除開始日] ： " + l_datStartDate);
                        log.debug("[管理ロック解除終了日] ： " + l_datEndDate);
                        log.debug("[発注日] ： " + l_tsBizDate);
                        log.debug("管理ロックエラー");
                        log.exiting(STR_METHOD_NAME);
                        return new OrderValidationResult(
                            ProcessingResult.newFailedResultInstance(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02958));
                    }
                }
                else
                {
                    log.debug("[管理ロック] ： " + l_strMngLockFlag);
                    log.debug("[注文認可] ： " + l_strOrderPermission);
                    log.debug("[管理ロック解除開始日／終了日] ： null");
                    log.debug("管理ロックエラー");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02958));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
}
@
