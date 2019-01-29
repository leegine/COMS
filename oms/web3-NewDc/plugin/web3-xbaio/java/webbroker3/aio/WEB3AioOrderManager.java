head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金注文マネージャ(WEB3AioOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 韋念瓊 (中訊) 新規作成
                   2004/10/25 于美麗 (中訊) レビュー 
                   2004/12/06 周勇 (中訊) 残対応
                   2005/02/16 黄建 (中訊) 残対応
                   2007/1/2  何文敏 (中訊) 仕様変更・モデル668 
Revesion History : 2007/10/16  何文敏 (中訊) 仕様変更・モデル807
Revesion History : 2007/10/26  何文敏 (中訊) 仕様変更・モデル817
Revesion History : 2008/09/22  王志葵 (中訊) 仕様変更・モデル992
Revesion History : 2009/03/12  柴双紅 (中訊) 仕様変更・モデル1109
Revesion History : 2009/03/16  車進 (中訊) 仕様変更・モデル1139
Revesion History : 2009/03/18  車進 (中訊) 仕様変更・モデル1121
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioProcessManagementIdDivDef;
import webbroker3.aio.define.WEB3OrderCancelPossibleDef;
import webbroker3.aio.define.WEB3OrderSendPossibleDef;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OtherOrgCodeDef;
import webbroker3.common.define.WEB3ProcDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3ServiceDateDivDef;
import webbroker3.common.define.WEB3ServiceDivDef;
import webbroker3.common.define.WEB3TheDayTransferDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FinInstitutionAvailableRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow;
import webbroker3.gentrade.data.OtherOrgSrvTimeRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (入出金注文マネージャ)<BR>
 * 入出金注文マネージャクラス<BR>
 * （AioOrderManagerの拡張クラス）
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */ 

public class WEB3AioOrderManager extends AioOrderManagerImpl 
{    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderManager.class);
    /**
     * (validate注文)<BR>
     * 注文共通チェックを実施する。 <BR>
     * <BR>
     * 以下のチェックを行う。 <BR>
     * 　@－受付時間チェック <BR>
     * 　@－システム停止中チェック <BR>
     * 　@－顧客のチェック（Ｙ客、管理ロック等） <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（入出金注文）validate注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40EBF6590242
     */
    public void validateOrder(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
                  
        //1.2 受付時間チェック/システム売買停止チェックを行う。 
        //取引時間管理.validate注文受付可能( )
        log.debug("取引時間管理.validate注文受付可能( ).");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
              
        //1.3 共通チェック処理を行う。 
        try
        {        
            log.debug("共通チェック処理を行う。");
            l_reusableValidations.commonFirstValidationsForAllOperations(l_subAccount);
        }
        catch (OrderValidationException l_ex)
        {
            log.error("Error in validate注文", l_ex);
            throw new WEB3SystemLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //注文チェックオブジェクトを取得する
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 

        //1.3.1 顧客ロックのチェックを行う。 
        //－注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
            
        log.debug("注文チェック.validate取引可能顧客(): isFailedResult = " + 
                l_validationResult.getProcessingResult().isFailedResult());
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate新規注文)<BR>
     * （validateNewOrderのオーバーライド）<BR>
     * <BR>
     * 新規注文の発注審査を行う。<BR>
     * <BR>
     * １）注文共通チェックを実施する。 <BR>
     *    this.validate注文()メソッドをコールする。<BR>
     * <BR>
     *    以下のチェックを行う。 <BR>
     * 　@   －受付時間チェック <BR>
     * 　@   －システム停止中チェック <BR>
     * 　@   －顧客のチェック（Ｙ客、管理ロック等） <BR>
     * <BR>
     *    [引数]<BR>
     *    補助口座： 引数.補助口座<BR>
     * <BR>
     * ２）入出金注文内容.注文種別によって、以下のメソッドをコールする。<BR>
     * <BR>
     *    1001（出金注文）： validate出金注文()<BR>
     *    1002（入金注文）： validate入金注文()<BR>
     * <BR>
     *    [引数]<BR>
     *    補助口座： 引数.補助口座<BR>
     *    入出金注文内容： 引数.入出金注文内容<BR>
     * <BR>
     * ３）新規の注文IDを取得する。<BR>
     *   this.createNewOrderId()<BR>
     * <BR>
     * ４）発注審査結果(NewOrderValidationResult)インスタンスを生成する。<BR>
     * <BR>
     *   [インスタンス生成の引数]<BR>
     *   １．ProcessingResult.SUCCESS_RESULT<BR>
     *   ２．２）の注文ID<BR>
     * <BR>
     * ５）発注審査結果を返す。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）validate新規注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (商品タイプ)
     * @@param l_cashTransOrderSpec - (入出金注文内容)
     * @@return NewOrderValidationResult
     * @@roseuid 40EE07120029
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        NewOrderSpec l_newOrderSpec)
    {
        String STR_METHOD_NAME = "validateNewOrder(SubAccount l_subAccount," + 
            "ProductTypeEnum l_productType, NewOrderSpec l_newOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_newOrderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AioNewOrderSpec l_web3AioNewOrderSpec = (WEB3AioNewOrderSpec) l_newOrderSpec;
        
        //発注審査結果
        NewOrderValidationResult l_newOrderValidationResult = null;
        
        try
        {      
            //１）注文共通チェックを行う。 
            //以下のチェックを行う。 
            //   －受付時間チェック 
            //   －システム停止中チェック 
            //   －顧客のチェック（Ｙ客、管理ロック等） 
            //[引数] 
            //補助口座： 引数.補助口座 
      
            this.validateOrder(l_subAccount);
            
            log.debug("入出金注文内容.注文種別=" + 
                    l_web3AioNewOrderSpec.getOrderTypeEnum().intValue());
            
            //1.2 入出金注文内容.注文種別=1001（出金）の場合           
            if (OrderTypeEnum.CASH_OUT.equals(l_web3AioNewOrderSpec.getOrderTypeEnum()))
            {
                log.debug("入出金注文内容.注文種別=1001（出金）の場合");               
                //1.2.1 出金注文のチェックを行う。 
                //[引数] 
                //補助口座： 引数.補助口座 
                //入出金注文内容： 引数.入出金注文内容 

                this.validateCashoutOrder(l_subAccount, l_web3AioNewOrderSpec);
            }
            //1.3 入出金注文内容.注文種別=1002（入金）の場合
            else if (OrderTypeEnum.CASH_IN.equals(l_web3AioNewOrderSpec.getOrderTypeEnum()))
            {
                log.debug("入出金注文内容.注文種別=1002（入金）の場合");               
                //1.3.1 入金注文のチェックを行う。
                //[引数]
                //補助口座： 引数.補助口座 
                //入出金注文内容： 引数.入出金注文内容
                this.validateCashinOrder(l_subAccount, l_web3AioNewOrderSpec);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate新規注文", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        
        long l_lngOrderId = 0L;
        //入出金注文内容.注文ID = null の場合、createNewOrderId()の戻り値 
        if (l_web3AioNewOrderSpec.getOrderId() == null)
        {
            log.debug("入出金注文内容.注文ID = null の場合.");
            
            //３）新規の注文IDを取得する。
            l_lngOrderId = this.createNewOrderId();
            log.debug("新規の注文ID: " + l_lngOrderId);
        }
        //入出金注文内容.注文ID != null の場合、入出金注文内容.注文ID 
        else
        {
            log.debug("入出金注文内容.注文ID != null の場合.");
            
            l_lngOrderId = l_web3AioNewOrderSpec.getOrderId().longValue();
            
            log.debug("入出金注文内容.注文ID: " + l_lngOrderId);
        }
        
        //４）発注審査結果(NewOrderValidationResult)インスタンスを生成する。
        l_newOrderValidationResult = 
            new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, l_lngOrderId);
                
        log.debug("発注審査結果 = " + 
                l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
        //５）発注審査結果を返す。
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * （validateCancelOrderのオーバーライド）<BR>
     * <BR>
     * 取消注文の発注審査を行う。<BR>
     * <BR>
     * １）注文共通チェックを実施する。 <BR>
     *    this.validate注文()メソッドをコールする。<BR>
     * <BR>
     *    以下のチェックを行う。 <BR>
     * 　@   －受付時間チェック <BR>
     * 　@   －システム停止中チェック <BR>
     * 　@   －顧客のチェック（Ｙ客、管理ロック等） <BR>
     * <BR>
     *    [引数]<BR>
     *    補助口座： 引数.補助口座<BR>
     * <BR>
     * ２）注文取消内容妥当性チェック<BR>
     *    該当注文がSONAR送信未済であるかをチェックする。<BR>
     * <BR>
     *    ２－１）取消注文内容から取消対象の注文オブジェクトを取得する。<BR>
     *       取消注文内容.getOrderID()<BR>
     * <BR>
     *    ２－２）注文オブジェクトから、注文単位オブジェクトを取得する。<BR>
     *       注文.getOrderUnits()の1番目の要素<BR>
     * <BR>
     *    ２－３）注文状態のチェック <BR>
     * <BR>
     *    注文状態 != 1（受付済） or 注文取消区分 != 0（初期値）<BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01938<BR>
     * <BR>
     * <BR>
     * ２－４）SONAR送信状況のチェック <BR>
     *    注文単位.MQステータス != "0"（未処理） の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00716<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_cancelOrderSpec - (取消注文内容)<BR>
     * 取消注文内容オブジェクト
     * @@return OrderValidationResult
     * @@roseuid 40EE079B0336
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount, 
        CancelOrderSpec l_cancelOrderSpec)                  
    {
        String STR_METHOD_NAME = "validateCancelOrder(" + 
            "SubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //発注審査結果
        OrderValidationResult l_orderValidationResult = null;
        
        //１）注文共通チェックを実施する。 
        try
        {      
            log.debug("注文共通チェックを実施する。");
            this.validateOrder(l_subAccount);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate注文()", l_ex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        //２）注文取消内容妥当性チェック
        //２－１）取消注文内容から取消対象の注文オブジェクトを取得する。
        //２－２）注文オブジェクトから、注文単位オブジェクトを取得する。
        //注文.getOrderUnits()の1番目の要素
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) this.getOrderUnits(l_cancelOrderSpec.getOrderId())[0];
        AioOrderUnitRow l_orderUnitRow = (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
        
        //２－３）注文状態のチェック
        // 注文状態 != 1（受付済） or 注文取消区分 != 0（初期値）の場合、例外をスローする。

        if (!OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
            || !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_orderUnitRow.getCancelType()))
        {
            log.debug("Error in 注文状態 != 1（受付済） or 注文取消区分 != 0（初期値）の場合");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01938));      
        }            
        
        //２－４）SONAR送信状況のチェック <BR>
        //  注文単位.MQステータス != "0"（未処理） の場合、例外をスローする。
        log.debug("注文単位.MQステータス = " + l_orderUnitRow.getMqStatus());
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_orderUnitRow.getMqStatus()))
        {
            log.debug("Error in 注文単位.MQステータス != '0'（未処理）");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00716));      
        }            
        //NewOrderValidationResultオブジェクトを生成して返す
        l_orderValidationResult =
            new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);

        log.debug("発注審査result = " + 
            l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;

    }
    
    /**
     * (validate入金金額)<BR>
     * 入金金額のチェックを行う。<BR>
     * <BR>
     * - 1回当たりの上限金額チェック<BR>
     * - 1回当たりの下限金額チェック<BR>
     * - 入金金額単位チェック<BR>
     * - 入金総額チェック<BR>
     * - 入金回数チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）validate入金金額」 参照<BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@param l_dblAmount - (入金額)
     * @@throws WEB3BaseException
     * @@roseuid 40F2751D0076
     */
    public void validateCreditAmount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        double l_dblAmount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCreditAmount(SubAccount l_subAccount, " +
            "String l_strPaySchemeId, double l_dblAmount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1. 入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        log.debug("証券会社コード: " + l_subAccount.getInstitution().getInstitutionCode());
        log.debug("部店コード: " + l_subAccount.getMainAccount().getBranch().getBranchCode());
        log.debug("決済機@関ID: " + l_strPaySchemeId);
        
        //1.2. 会社別決済機@関条件インスタンスを生成する。 
        WEB3AioCompanySettleInstitutionConditions l_aioSettleCondition = 
            new WEB3AioCompanySettleInstitutionConditions(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strPaySchemeId);
                
        //---1回当たりの上限金額チェック 
        
        //1.3. get上限入金金額(): 1回当たりの上限入金金額を取得する。        
        double l_dblMaxAmountOnce = l_aioSettleCondition.getMaxAmountOnce();
        
        log.debug("get上限入金金額():" + l_dblMaxAmountOnce);
        
        //1.4. validate上限金額(): 1回当たりの上限金額のチェックを行う。
        //[引数] 
        //入金額： 引数.入金額 
        //上限金額： get上限入金金額（1回当たり）()の戻り値
        l_reusableValidations.validateMaxAmount(l_dblAmount, l_dblMaxAmountOnce);
        
        //--- 1回当たりの下限金額チェック 
        
        //1.5. get下限入金金額(): 1回当たりの下限入金金額を取得する。 
        double l_dblMinAmountOnce = l_aioSettleCondition.getMinAmountOnce();
        
        log.debug("get下限入金金額():" + l_dblMinAmountOnce);
        
        //1.6. validate下限金額(): 1回当たりの下限金額のチェックを行う。
        //[引数] 
        //入金額： 引数.入金額 
        //下限金額： get下限入金金額（1回当たり）()の戻り値
        l_reusableValidations.validateMinAmount(l_dblAmount, l_dblMinAmountOnce);
        
        //--- 入金金額単位チェック

        //1.7. get単位入金金額(): 単位入金金額を取得する。
        double l_dblAmountUnit = l_aioSettleCondition.getAmountUnit();
        
        log.debug("get単位入金金額():" + l_dblAmountUnit);
        
        //1.8. validate最小単位(): 入金額が最小単位で割り切れるかをチェックする。
        //[引数] 
        //入金額： 引数.入金額 
        //最小単位： get単位入金金額()の戻り値
        l_reusableValidations.validateSmallestUnit(l_dblAmount, l_dblAmountUnit);
        
        //--- 入金総額チェック 

        //1.9. getオンライン入金発注日(): 該当する決済機@関での発注日を取得する。
        Date l_datBizDate = this.getOnlineCashinBizDate(l_strPaySchemeId);
        
        log.debug("getオンライン入金発注日():" + l_datBizDate);
        
        //1.10. get上限入金金額(): 1日当たりの上限入金金額を取得する。 
        double l_dblMaxAmountDaily = l_aioSettleCondition.getMaxAmountDaily();
        
        log.debug("get上限入金金額():" + l_dblMaxAmountDaily);
        
        //1.11. get総入金額(): 現時点の総入金額を取得する。
        //[引数] 
        //補助口座： 引数.補助口座 
        //決済機@関ID： 引数.決済機@関ID 
        //発注日： getオンライン入金発注日()の戻り値 

        double l_dblTotalCreditAmout = this.getTotalCreditAmount(
            l_subAccount, 
            l_strPaySchemeId, 
            l_datBizDate);
        
        //1.12. validate総入金上限額(): 1日の入金総額が上限を超えてないかチェックする。
        //[引数] 
        //現時点入金総額： get総入金額()の戻り値 
        //入金額： 引数.入金額 
        //上限総額： get上限入金金額（1日当たり）()の戻り値 
        
        log.debug("現時点入金総額：" + l_dblTotalCreditAmout);
        log.debug("引数.入金額：" + l_dblAmount);
        log.debug("上限総額：" + l_dblMaxAmountDaily);
        
        l_reusableValidations.validateTotalCreditMaxAmount(
            l_dblTotalCreditAmout, l_dblAmount, l_dblMaxAmountDaily);
        
        //--- 入金回数チェック 
        
        //1.13.  get上限回数(): 1日当たりの入金上限回数を取得する。
        long l_lngMaxCount = l_aioSettleCondition.getMaxCount();
        
        log.debug("get上限回数():" + l_lngMaxCount);
        
        //1.14. get入金注文回数(): 現時点の総入金回数を取得する。
        long l_lngOrderCount = this.getCashinOrderCount(l_subAccount, l_strPaySchemeId, l_datBizDate);
        
        log.debug("get入金注文回数():" + l_lngOrderCount);
        
        //1.15. validate上限回数(): 1日の入金回数が上限を超えてないかチェックする。
        //[引数] 
        //現時点注文回数： get入金注文回数()の戻り値 
        //上限回数： get上限回数()の戻り値
        l_reusableValidations.validateMaxCount(l_lngOrderCount, l_lngMaxCount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get入金注文回数)<BR>
     * 1日の入金回数を取得する。<BR>
     * <BR>
     * １）金融機@関連携入出金状況テーブルから以下の<BR>
     * 条件と一致するレコードを取得する。<BR>
     * <BR>
     *    [検索条件]<BR>
     *    証券会社コード = <BR>
     * 補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     *    部店コード = 補助口座.get取引店().getBranchCode()の戻り値<BR>
     *    顧客コード = <BR>
     * 補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     *    決済機@関ID = 引数.決済機@関ID<BR>
     *    集計基準日 = 引数.発注日<BR>
     *   （処理区分 = '1' or 処理FLAG（決済結果） in ('1','2','5')）<BR> 
     * <BR>
     * ２）１）で取得したレコード数を返す。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@param l_datBizDate - (発注日)
     * @@throws WEB3BaseException
     * @@roseuid 40F3693C000F
     */
    public long getCashinOrderCount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        Date l_datBizDate) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCashinOrderCount(" + 
            "SubAccount l_subAccount, String l_strPaySchemeId, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）金融機@関連携入出金状況テーブルから以下の条件と一致するレコードを取得する。
        List l_lisRows = null;
        try
        {           
            //証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値 
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            
            //部店コード = 補助口座.get取引店().getBranchCode()の戻り値
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            
            //顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            log.debug("証券会社コード = " + l_strInstitutionCode);
            log.debug("部店コード = " + l_strBranchCode);
            log.debug("顧客コード = " + l_strAccountCode);
            
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ?" +
                " and account_code = ? and pay_scheme_id = ?" +
                " and to_char(base_date, 'YYMMDD') = to_char(?, 'YYMMDD')" +
                " and transaction_status = ? and result_status_flag in ( ? , ? , ? )";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strPaySchemeId, 
                    l_datBizDate, 
                    WEB3TransactionStatusDef.OK, 
                    WEB3ResultStatusFlagDef.NOTIFY_RECEIPT, 
                    WEB3ResultStatusFlagDef.RESPONSE_SEND, 
                    WEB3ResultStatusFlagDef.RESPONSE_ERROR_TELEGRAM
                    };
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            long l_lngReturnSize = l_lisRows.size();
            log.debug("レコード数 = " + l_lngReturnSize);
            
            //２）１）で取得したレコード数を返す。
            log.exiting(STR_METHOD_NAME);
            return l_lngReturnSize;
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get総入金額)<BR>
     * 1日当たりの入金総額を取得する。<BR>
     * <BR>
     * １）金融機@関連携入出金状況テーブルから以下の条件と一致するレコードを取得する。<BR>
     * <BR>
     *    [検索条件]<BR>
     *    証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     *    部店コード = 補助口座.get取引店().getBranchCode()の戻り値<BR>
     *    顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     *    決済機@関ID = 引数.決済機@関ID<BR>
     *    集計基準日 = 引数.発注日<BR>
     *    処理区分 = '1'（OK） <BR>
     * <BR>
     * ２）取得したレコードから、金額項目の合計を算出する。<BR>
     * <BR>
     * ３）２）の算出結果を返す。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@param l_datBizDate - (発注日)
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40F3693C001F
     */
    public double getTotalCreditAmount(
        SubAccount l_subAccount, 
        String l_strPaySchemeId, 
        Date l_datBizDate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getTotalCreditAmount(" + 
            "SubAccount l_subAccount, String l_strPaySchemeId, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）金融機@関連携入出金状況テーブルから以下の条件と一致するレコードを取得する。
        List l_lisRows = null;
       
        try
        {            
            //証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値 
            String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            
            //部店コード = 補助口座.get取引店().getBranchCode()の戻り値
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            
            //顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            log.debug("証券会社コード = " + l_strInstitutionCode);
            log.debug("部店コード = " + l_strBranchCode);
            log.debug("顧客コード = " + l_strAccountCode);
            
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ?" +
                " and account_code = ? and pay_scheme_id = ?" +
                " and to_char(base_date, 'YYMMDD') = to_char(?, 'YYMMDD')" +
                " and transaction_status = ? ";                
            
            Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strPaySchemeId, 
                    l_datBizDate, 
                    WEB3TransactionStatusDef.OK };          
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            //２）取得したレコードから、金額項目の合計を算出する。
            double l_dblAmountCount = 0;
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                    (BankCashTransferStatusRow) l_lisRows.get(i);
                log.debug("金融機@関連携入出金状況テーブルRow : " + l_bankCashTransferStatusRow);
                
                l_dblAmountCount += l_bankCashTransferStatusRow.getAmount();
            }
            log.debug("金額項目の合計 = " + l_dblAmountCount);
            
            //３）２）の算出結果を返す。
            log.exiting(STR_METHOD_NAME);
            return l_dblAmountCount;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (validate出金注文)<BR>
     * 出金注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）validate出金注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_cashTransOrderSpec - (入出金注文内容)
     * @@throws WEB3BaseException
     * @@roseuid 40F4FD3801E9
     */
    protected void validateCashoutOrder(
        SubAccount l_subAccount, 
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCashoutOrder(" + 
            "SubAccount l_subAccount, WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cashTransOrderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. 振込予定日を取得する。 
        Date l_datEstimatedDate = l_cashTransOrderSpec.getEstimatedTransferDate();        
        
        log.debug("振込予定日 = " + l_datEstimatedDate);
        
        //入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        //1.2. validate出金重複注文(): 同一受渡日の出金注文のチェックを行う。
        //[引数] 
        //補助口座： 引数.補助口座 
        //受渡日： 入出金注文内容.getEstimatedTransferDate()の戻り値        
        l_reusableValidations.validateCashoutDuplicateOrder(
            l_subAccount, l_datEstimatedDate);
            
        log.debug("call validate出金重複注文()...");
        //1.3.  getQuantity(): 金額を取得する。
        double l_dblQuantity = l_cashTransOrderSpec.getQuantity();
        
        log.debug("金額 = " + l_dblQuantity);
        
        //1.4. validate出金金額(): 出金金額の範囲のチェックを行う。
        //[引数] 
        //補助口座： 引数.補助口座 
        //金額： 入出金注文内容.getQuantity()の戻り値 
        //受渡日：　@入出金注文内容.getEstimatedTransferDate()の戻り値
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblQuantity, 
            l_cashTransOrderSpec.getEstimatedTransferDate());
               
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate入金注文)<BR>
     * 入金注文の発注審査を行う。<BR>
     * <BR>
     * １）金額チェック<BR>
     *   this.validate入金金額()メソッドをコールする。<BR>
     * <BR>
     *   [引数]<BR>
     *   補助口座： 引数.補助口座<BR>
     *   決済機@関ID： 入出金注文内容.get決済機@関ID()の戻り値<BR>
     *   入金金額： 入出金注文内容.getQuantity()の戻り値<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）validate入金注文」 参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_cashTransOrderSpec - (入出金注文内容)
     * @@throws WEB3BaseException
     * @@roseuid 40F4FD4F00C0
     */
    protected void validateCashinOrder(
        SubAccount l_subAccount, 
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCashinOrder(" + 
            "SubAccount l_subAccount, WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_cashTransOrderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //決済機@関ID： 入出金注文内容.get決済機@関ID()の戻り値
        String l_strPaySchemeId = "";
        l_strPaySchemeId = l_cashTransOrderSpec.getPaySchemeId();
        
        log.debug("入出金注文内容.get決済機@関ID() = " + l_strPaySchemeId);
        
        //入金金額： 入出金注文内容.getQuantity()の戻り値
        double l_dblQuantity = 0;
        l_dblQuantity = l_cashTransOrderSpec.getQuantity();
        
        log.debug("入出金注文内容.getQuantity() = " + l_dblQuantity);
        
        //１）金額チェック
        this.validateCreditAmount(l_subAccount, l_strPaySchemeId, l_dblQuantity);
   
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get商品ID)<BR>
     * 入出金用の商品IDを取得する。<BR>
     * <BR>
     * １）Productテーブルからレコード（行オブジェクト）を取得する。<BR>
     *   ProductDao.findRowsByInstitutionCodeProductType(証券会社コード, 商品タイプ)<BR>
     * <BR>
     *   [引数]<BR>
     *   証券会社コード： 引数.証券会社.getInstitutionCode()の戻り値<BR>
     *   商品タイプ： 5（現金）<BR>
     * <BR>
     * ２）行オブジェクト.getProductId()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 40F52F9C0142
     */
    public long getProductId(Institution l_institution) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getProductId(Institution l_institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //証券会社コード： 引数.証券会社.getInstitutionCode()の戻り値
        String l_strInstitutionCode = l_institution.getInstitutionCode();
         
        List l_lisProductRow = null;
        try
        {
            //[引数] 
            //証券会社コード： 引数.証券会社.getInstitutionCode()の戻り値 
            //商品タイプ： 5（現金） 
            log.debug("ProductDao.findRowsBy...");
            l_lisProductRow = ProductDao.findRowsByInstitutionCodeProductType(
                l_strInstitutionCode, ProductTypeEnum.CASH);               
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //１）Productテーブルからレコード（行オブジェクト）を取得する。
        if (l_lisProductRow != null && l_lisProductRow.size() != 0)
        {
            ProductRow l_productRow = (ProductRow) l_lisProductRow.get(0);        
            log.debug("ProductRow : " + l_productRow);
            
            //２）行オブジェクト.getProductId()の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_productRow.getProductId();
        }
        
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
            getClass().getName() + STR_METHOD_NAME); 
    }
    
    /**
     * (get注文単位)<BR>
     * 注文単位オブジェクトを取得する。<BR>
     * <BR>
     * １）以下のオブジェクトを取得する。<BR>
     * 
     * 証券会社オブジェクト = 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード)<BR>
     * 部店オブジェクト = 拡張アカウントマネージャ.get部店(引数.証券会社コード, 引数.部店コード)<BR>
     * 口座（顧客）オブジェクト = 拡張アカウントマネージャ.getMainAccount(証券会社オブジェクト, 部店オブジェクト, 
     * 引数.顧客コード)<BR>
     * 補助口座オブジェクト = 拡張アカウントマネージャ.getSubAccount(口座.口座ID, 引数.補助口座タイプ)<BR>
     * <BR>
     * ２）注文単位オブジェクトを取得する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 口座ID = 口座.口座ID<BR>
     * 補助口座ID = 補助口座.補助口座ID<BR>
     * 部店ID = 部店.部店ID<BR>
     * 銘柄タイプ = ProductTypeEnum.CASH<BR>
     * 識別コード = 引数.識別コード<BR>
     * <BR>
     * 該当行が存在した場合、複数行一致した場合は例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00739<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@param l_subAccountType - (補助口座タイプ)
     * @@return AioOrderUnit
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 40FCB8370387
     */
    public AioOrderUnit getOrderUnit(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber, 
        SubAccountTypeEnum l_subAccountType)        
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getOrderUnit(" + 
            "String l_strBranchCode, String l_strAccountCode, " + 
            "String l_strOrderRequestNumber, SubAccountTypeEnum l_subAccountType)";
            
        log.entering(STR_METHOD_NAME);        
        
        //１）以下のオブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        Branch l_branch = null;
        
        try
        {        
            //Throw NotFoundException
            //証券会社オブジェクト = 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード) 
            Institution l_institution = l_web3GentradeAccountManager.getInstitution(
                l_strInstitutionCode);
            log.debug("証券会社.get証券会社Code() = " + l_institution.getInstitutionCode());
            
            //部店オブジェクト = 拡張アカウントマネージャ.get部店(証券会社オブジェクト, 引数.部店コード) 
            l_branch = l_web3GentradeAccountManager.getBranch(
                l_institution, l_strBranchCode);
            log.debug("部店.get部店Code() = " + l_branch.getBranchCode());
            
            //口座（顧客）オブジェクト = 拡張アカウントマネージャ.getMainAccount
            //(証券会社オブジェクト, 部店オブジェクト, 引数.顧客コード) 
            l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                l_institution, l_branch, l_strAccountCode);
                
            log.debug("口座（顧客）オブジェクト.get口座Code = " + l_mainAccount.getAccountId());
            
            //補助口座オブジェクト = 拡張アカウントマネージャ.getSubAccount(口座.口座ID, 引数.補助口座タイプ) 
            l_subAccount = l_web3GentradeAccountManager.getSubAccount(
                l_mainAccount.getAccountId(), l_subAccountType);
            
            log.debug("補助口座.get補助口座Id() = " + l_subAccount.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        try
        {
            //２）注文単位オブジェクトを取得する。
            List l_lisRows = null;
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and product_type = ? and order_request_number = ?";
            
            //口座ID = 口座.口座ID
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            //補助口座ID = 補助口座.補助口座ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //部店ID = 部店.部店ID
            long l_lngBranchId = l_branch.getBranchId();           
            
            log.debug("口座ID  = " + l_lngAccountId);
            log.debug("補助口座ID  = " + l_lngSubAccountId);
            log.debug("部店ID  = " + l_lngBranchId);
            log.debug("銘柄タイプ  = " + ProductTypeEnum.CASH.intValue());
            log.debug("識別コード  = " + l_strOrderRequestNumber);
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                ProductTypeEnum.CASH, 
                l_strOrderRequestNumber };
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
            
            AioOrderUnit l_aioOrderUnit = null;
            //該当行が存在した場合、複数行一致した場合は例外をスローする。
            if (l_lisRows.size() > 1)
            {                
                log.debug("該当する注文情報が複数見つかりました。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当する注文情報が複数見つかりました。size = " + l_lisRows.size());
            }
            else if (l_lisRows.size() == 1)
            {
                //注文単位オブジェクトを取得する。
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(0);            
                log.debug("注文単位Row : " + l_aioOrderUnitRow);
                //this.getOrderUnit()をコールし、注文単位オブジェクトを取得する
                l_aioOrderUnit = (AioOrderUnit) this.toOrderUnit(
                        l_aioOrderUnitRow);
    
                log.exiting(STR_METHOD_NAME);
            }
            else 
            {
                log.debug("データ不整合エラー");
                //例外をスローする
                throw new NotFoundException("注文単位オブジェクトを取得できない");
            }
            return l_aioOrderUnit;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create振込先金融機@関明細)<BR>
     * 振込先金融機@関明細の配列を生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）create振込先金融機@関明細」  参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@return WEB3AioFinancialInstitutionUnit
     * @@throws WEB3BaseException
     * @@roseuid 41084E1D02C5
     */
    public WEB3AioFinancialInstitutionUnit[] createFinancialInstitutionDetails(
        String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createFinancialInstitutionDetails(" + 
            "String l_strInstitutionCode, String l_strBranchCode)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 1.2 該当する部店で利用可能な金融機@関を金融機@関利用テーブルから取得する。 
        String l_strWhereClause = "";
        String l_strOrderBy = "";
        
        List l_lisRows = null;
        Object l_bindVars[] = null;
        try
        {        
            //Where条件：引数.部店コード=nullの場合： ”institution_code=?” 
            //orderBy条件：引数.部店コード=nullの場合： ”fin_institution_code” 
            if (l_strBranchCode == null)
            {
                log.debug("引数.部店コード = nullの場合");
                l_strWhereClause = "institution_code = ?";
                l_strOrderBy = "fin_institution_code";
                l_bindVars = new Object[1];
                l_bindVars[0] = l_strInstitutionCode;

            }
            //Where条件：引数.部店コード!=nullの場合： ”institution_code=? and branch_code=?" 
            //orderBy条件：引数.部店コード!=nullの場合： ”display_order"  
            else
            {
                log.debug("引数.部店コード != nullの場合");
                l_strWhereClause = "institution_code = ? and branch_code = ?";
                l_strOrderBy = "display_order";

                l_bindVars = new Object[2];              
                l_bindVars[0] = l_strInstitutionCode;
                l_bindVars[1] = l_strBranchCode;

            }
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FinInstitutionAvailableRow.TYPE, 
                    l_strWhereClause,
                    l_strOrderBy,
                    null,                    
                    l_bindVars);
            
            log.debug("search 金融機@関利用テーブル.size: = " + l_lisRows.size());
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.3 ArrayListのインスタンスを生成する。
        List l_lisFinancialInstitutionUnit = new ArrayList();
        
        //1.4 取得した要素分のLoop処理         
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            FinInstitutionAvailableRow l_finInstAvailableRow = 
                (FinInstitutionAvailableRow) l_lisRows.get(i);            
            
            log.debug("金融機@関利用テーブルRow : " + l_finInstAvailableRow);
            
            //1.4.1 振込先金融機@関明細
            WEB3AioFinancialInstitutionUnit l_financialInstirutionUnit =
                new WEB3AioFinancialInstitutionUnit();
                
            //1.4.2 金融機@関インスタンスを生成する。
            WEB3GentradeFinInstitution l_finInstitution = 
                new WEB3GentradeFinInstitution(
                    l_strInstitutionCode, 
                    l_finInstAvailableRow.getFinInstitutionCode());
                
            //1.4.3 プロパティセット
            //(*1)以下のとおりにプロパティをセットする。

            //振込先金融機@関明細.コード ＝ 金融機@関利用行オブジェクト.get金融機@関コード()の戻り値
            l_financialInstirutionUnit.finInstitutionCode = 
                l_finInstAvailableRow.getFinInstitutionCode();

            //振込先金融機@関明細.名称 ＝ 金融機@関.get金融機@関名（漢字）()の戻り値
            l_financialInstirutionUnit.finInstitutionName = 
                l_finInstitution.getFinInstitutionNameKanji();
            
            log.debug("振込先金融機@関明細.コード ＝ " + l_financialInstirutionUnit.finInstitutionCode);
            log.debug("振込先金融機@関明細.名称 ＝ " + l_financialInstirutionUnit.finInstitutionName);            
            
            //重複する内容のオブジェクトがある場合は、追加しない。
            if (!WEB3Toolkit.contain(l_lisFinancialInstitutionUnit, l_financialInstirutionUnit))
            {
                log.debug("振込先金融機@関明細オブジェクトをArrayListに追加する。");
                //1.4.4 振込先金融機@関明細オブジェクトをArrayListに追加する。 
                l_lisFinancialInstitutionUnit.add(l_financialInstirutionUnit);   
            }
        }
        
        WEB3AioFinancialInstitutionUnit[] l_financialInstirutionUnits = 
            new WEB3AioFinancialInstitutionUnit[l_lisFinancialInstitutionUnit.size()];
                    
        l_lisFinancialInstitutionUnit.toArray(l_financialInstirutionUnits);
                
        log.exiting(STR_METHOD_NAME);
        return l_financialInstirutionUnits;
    }

    /**
     * (create振込先金融機@関明細)<BR>
     * （オーバーロードメソッド）<BR>
     * 振込先金融機@関明細の配列を生成する。<BR>
     * <BR>
     * １）証券会社コード、部店コードを取得する。<BR>
     * <BR>
     *    証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     *    部店コード： 引数.補助口座.get取引店().getBranchCode()の戻り値<BR>
     * <BR>
     * ２）create振込先金融機@関明細（証券会社コード, 部店コード）メソッドをコールする。<BR>
     * <BR>
     *   ［引数］<BR>
     *   証券会社コード： １）で取得した証券会社コード<BR>
     *   部店コード： １）で取得した部店コード<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@return WEB3AioFinancialInstitutionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4108628E0267
     */
    public WEB3AioFinancialInstitutionUnit[] createFinancialInstitutionDetails(
        SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createFinancialInstitutionDetails(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）証券会社コード、部店コードを取得する。       
        //証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()の戻り値
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード： 引数.補助口座.get取引店().getBranchCode()の戻り値
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //２）create振込先金融機@関明細（証券会社コード, 部店コード）メソッドをコールする。
        WEB3AioFinancialInstitutionUnit[] l_web3AioFinancialInstitutionUnits = 
            this.createFinancialInstitutionDetails(l_strInstitutionCode, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
        return l_web3AioFinancialInstitutionUnits;
    }
    
    /**
     * (validate注文送信可能)<BR>
     * 出金注文が送信可能かどうかのチェックを行う。<BR>
     * <BR>
     * １）注文状態のチェック<BR>
     * <BR>
     *    注文状態 != 1（受付済） and 注文取消区分 != 0（初期値）<BR>
     * <BR>
     *    の場合、'1'（NG）を返す。<BR>
     * <BR>
     * ２）送信状態のチェック<BR>
     * <BR>
     *    注文単位.MQステータス != '0'（未処理）<BR>
     * <BR>
     *    の場合、'1'（NG）を返す。<BR>
     * <BR>
     * ３）発注日のチェック<BR>
     * <BR>
     *    注文単位.発注日 > 当日日付<BR>
     * <BR>
     *    の場合、'1'（NG）を返す。<BR>
     * <BR>
     * ４）'0'（OK）を返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@roseuid 4129D45B006D
     */
    public String validateOrderSendPossible(AioOrderUnit l_orderUnit) 
    {
        String STR_METHOD_NAME = "validateOrderSendPossible(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_orderUnit.getDataSourceObject();
        //１）注文状態のチェック
        OrderStatusEnum l_orderStatus = l_aioOrderUnitRow.getOrderStatus();        
        log.debug("注文状態 = " + l_orderStatus.intValue());
        
        String l_strCancelType = l_aioOrderUnitRow.getCancelType();
        log.debug("取消区分 = " + l_strCancelType);
        
        //注文状態 != 1（受付済） and 注文取消区分 != 0（初期値）の場合、'1'（NG）を返す。
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus) && 
            !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            log.debug("注文状態 != 1（受付済）and 注文取消区分 != 0（初期値）の場合、'1'（NG）を返す。");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;        
        }
        
        //２）送信状態のチェック
        String l_strMqStatus = l_aioOrderUnitRow.getMqStatus();
        log.debug("送信状態 = " + l_strMqStatus);
        
        //注文単位.MQステータス != '0'（未処理）の場合、'1'（NG）を返す。
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_strMqStatus))
        {
            log.debug("注文単位.MQステータス != '0'（未処理）の場合、'1'（NG）を返す。");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;
        }
        
        //３）発注日のチェック    
        //注文単位.発注日    
        String l_strBizDate = l_aioOrderUnitRow.getBizDate();
        Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
        log.debug("注文単位.発注日 = " + 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); 
        
        //当日日付
        Date l_datToday = GtlUtils.getSystemTimestamp();
        log.debug("当日日付 = " + l_datToday);
       
        //注文単位.発注日 > 当日日付の場合、'1'（NG）を返す。
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datToday) > 0)
        {
            log.debug("注文単位.発注日 > 当日日付の場合、'1'（NG）を返す。");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderSendPossibleDef.SEND_NG;
        }
        
        //４）'0'（OK）を返す。
        log.debug("'0'（OK）を返す。");
        log.exiting(STR_METHOD_NAME);
        return WEB3OrderSendPossibleDef.SEND_OK;        
    }
    
    /**
     * (validate注文取消可能)<BR>
     * 出金注文が取消可能かどうかのチェックを行う。<BR>
     * <BR>
     * １）注文状態のチェック<BR>
     * <BR>
     *    注文状態 != 1（受付済） and 注文取消区分 != 0（初期値）<BR>
     * <BR>
     *    の場合、'1'（NG）を返す。<BR>
     * <BR>
     * ２）送信状態のチェック<BR>
     * <BR>
     *    注文単位.MQステータス != '0'（未処理）<BR>
     * <BR>
     *    の場合、'1'（NG）を返す。<BR>
     * <BR>
     * ３）'0'（OK）を返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@roseuid 412D80A7000D
     */
    public String validateOrderCancelPossible(AioOrderUnit l_orderUnit) 
    {
        String STR_METHOD_NAME = "validateOrderCancelPossible(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        //１）注文状態のチェック
        //注文状態
        OrderStatusEnum l_orderStatus = l_aioOrderUnitRow.getOrderStatus();
        
        //注文取消区分
        String l_strCancelType = l_aioOrderUnitRow.getCancelType();
        
        //注文状態 != 1（受付済） and 注文取消区分 != 0（初期値）の場合、'1'（NG）を返す。
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus) && 
            !WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            log.debug("注文状態 != 1（受付済） and 注文取消区分 != 0（初期値）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderCancelPossibleDef.CANCEL_NG;
        }
        //２）送信状態のチェック
        String l_strMqStatus = l_aioOrderUnitRow.getMqStatus();
        
        //注文単位.MQステータス != '0'（未処理）の場合、'1'（NG）を返す。
        if (!WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_strMqStatus))
        {
            log.debug("注文単位.MQステータス != '0'（未処理）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderCancelPossibleDef.CANCEL_NG;
        }      
                
        //３）'0'（OK）を返す。
        log.exiting(STR_METHOD_NAME);
        return WEB3OrderCancelPossibleDef.CANCEL_OK;     
    }
    
    /**
     * (get直近振込日)<BR>
     * 直近の振込日を取得する。<BR>
     * <BR>
     * １）証券会社オブジェクトを取得する。<BR>
     * <BR>
     *    補助口座.getInstitution()<BR>
     * <BR>
     * ２）直近振込日を算出する。<BR>
     * <BR>
     * ２－１）証券会社Params.当日出金振込実施 = '0'（未実施）の場合<BR>
     * <BR>
     *    引数.発注日の翌営業日を返却する。<BR>
     * <BR>
     * ２－２）証券会社Params.当日出金振込実施 = '1'（実施）の場合<BR>
     * <BR>
     *    引数.発注日を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_datBizDate - (発注日)
     * @@throws WEB3BaseException
     * @@return Date
     * @@roseuid 412ADEF900FD
     */
    public Date getClosestTransferDate(SubAccount l_subAccount, Date l_datBizDate) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getClosestTransferDate(" +
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //１）証券会社オブジェクトを取得する。
        Institution l_institution = l_subAccount.getInstitution();
        
        log.debug("証券会社.証券会社Code = " + l_institution.getInstitutionCode());
        
        //２）直近振込日を算出する。
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        String l_strTheDayTransfer = l_institutionRow.getTheDayTransfer();
        
        Date l_datTransferDate = null;
        log.debug("証券会社Params.当日出金振込実施 = " + l_strTheDayTransfer);
        
        //２－１）証券会社Params.当日出金振込実施 = '0'（未実施）の場合
        if (WEB3TheDayTransferDef.NOT_ENFORCEMENT.equals(l_strTheDayTransfer))
        {
            log.debug("証券会社Params.当日出金振込実施 = '0'（未実施）の場合");
            //引数.発注日の翌営業日を返却する。             
            l_datTransferDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);        
        }
        
        //２－２）証券会社Params.当日出金振込実施 = '1'（実施）の場合
        else if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_strTheDayTransfer))
        {
            log.debug("証券会社Params.当日出金振込実施 = '1'（実施）の場合");
            //引数.発注日を返却する。
            l_datTransferDate = l_datBizDate;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datTransferDate;
    }
    
    /**
     * (validate先物取引口座開設)<BR>
     * 顧客が先物取引口座を開設しているかをチェックする。 <BR>
     * <BR>
     * （入出金発注審査個別チェック.validate先物取引口座開設()メソッドに委譲する。）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 41344AB90026
     */
    public void validateOpenFuturesTradedAccount(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOpenFuturesTradedAccount(SubAccount l_subAccount) ";
        log.entering(STR_METHOD_NAME);   
        
        //入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
            AioProductTypeOrderManagerReusableValidations.getInstance();
            
        l_reusableValidations.validateOpenFuturesTradedAccount(l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get振替回数)<BR>
     * 現時点での振替回数を取得する。<BR>
     * <BR>
     * １）以下の条件で注文単位を検索する。<BR> 
     * <BR>
     *     [検索条件] <BR>
     *     口座ID： 引数.補助口座.getMainAccount().口座ID <BR>
     *     補助口座ID： 引数.補助口座.補助口座ID <BR>
     *     部店ID： 引数.補助口座.get取引店().部店ID <BR>
     *     発注日： 引数.発注日 <BR>
     *     注文カテゴリ： 引数.注文カテゴリ <BR>
     *     注文状態 ：　@(*) <BR>
     *<BR> 
     * 　@　@(*)注文状態の設定 <BR>
     * 　@　@ - 引数.注文カテゴリ ＝ 15（為替保証金振替）の場合： <BR>
     * 　@　@　@　@　@　@注文状態 != ( 6（発注失敗（新規注文）） or 14（発注済（取消注文）） <BR>
     * 　@　@ - 引数.注文カテゴリ != 15（為替保証金振替）の場合： <BR>
     * 　@　@　@　@　@　@注文状態 != 6（発注失敗（新規注文）） <BR>
     * <BR>
     * 　@　@※為替保証金振替の場合、FXシステムの結果通知エラーによって取消 <BR>
     *       された注文もカウントしない <BR>
     *<BR> 
     * ２）取得したリストの件数を返却する<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_datBizDate - (発注日)
     * @@param l_orderCategEnum - (注文カテゴリ)
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 413537F001F5
     */
    public int getTransferCount(SubAccount l_subAccount, Date l_datBizDate, 
        OrderCategEnum l_orderCategEnum) throws WEB3BaseException
    {       
        String STR_METHOD_NAME = "getTransferCount(" +
                "SubAccount l_subAccount, Date l_datBizDate, " +
                "OrderCategEnum l_orderCategEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisRows = null;
        String l_strWhereClause = "";   
        
        //口座ID： 引数.補助口座.getMainAccount().口座ID        
        long l_lngAccountId  = l_subAccount.getMainAccount().getAccountId();
            
        //補助口座ID： 引数.補助口座.補助口座ID
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
        //部店ID： 引数.補助口座.get取引店().部店ID 
        long l_lngBranchId = 
            l_subAccount.getMainAccount().getBranch().getBranchId();
        
        String l_strBizDate = 
            WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        
        try
        {
            //(*)注文状態の設定 
            //- 引数.注文カテゴリ ＝ 15（為替保証金振替）の場合： 
            //  注文状態 != ( 6（発注失敗（新規注文）） or 14（発注済（取消注文）） 
            //- 引数.注文カテゴリ != 15（為替保証金振替）の場合： 
            //  注文状態 != 6（発注失敗（新規注文））
            if (OrderCategEnum.FX.equals(l_orderCategEnum))
            { 
                log.debug("引数.注文カテゴリ ＝ 15（為替保証金振替）の場合");
                
                l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                    "and branch_id = ? and biz_date = ? and order_categ = ? " +
                    "and (order_status <> ? and order_status <> ?)";
                            
        
                Object[] l_bindVars = {
                        new Long(l_lngAccountId), 
                        new Long(l_lngSubAccountId), 
                        new Long(l_lngBranchId), 
                        l_strBizDate, 
                        l_orderCategEnum, 
                        OrderStatusEnum.NOT_ORDERED,    //6 （発注失敗（新規注文））
                        OrderStatusEnum.CANCELLED       //14（発注済（取消注文））
                        };
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            }
            else
            {
                log.debug("引数.注文カテゴリ !＝ 15（為替保証金振替）の場合");
                l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and biz_date = ? and order_categ = ? " +
                "and order_status <> ?";
    
                Object[] l_bindVars = {
                        new Long(l_lngAccountId), 
                        new Long(l_lngSubAccountId), 
                        new Long(l_lngBranchId), 
                        l_strBizDate, 
                        l_orderCategEnum, 
                        OrderStatusEnum.NOT_ORDERED    //6 （発注失敗（新規注文））                    
                        };
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            }
            
            log.exiting(STR_METHOD_NAME);
            
            log.debug("取得したリストの件数 = " + l_lisRows.size());
            
            //２）取得したリストの件数を返却する。 
            return l_lisRows.size();
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate振替可能回数)<BR>
     * 現時点での振替回数が、振替可能回数を超えてないかチェックする。<BR>
     * <BR>
     * １）現時点での振替回数を取得する。 <BR>
     *<BR> 
     *  this.get振替回数() <BR>
     * <BR>
     *  [引数]<BR> 
     *      補助口座： 引数.補助口座 <BR>
     *      発注日： 引数.発注日 <BR>
     *      注文カテゴリ： 引数.注文カテゴリ <BR>
     * <BR>
     * ２）設定されている振替可能回数を取得する。 <BR>
     *<BR> 
     *  振替可能回数 = 補助口座.getMainAccount().振替可能回数 <BR>
     *<BR> 
     * ３）以下の条件に当てはまる場合、例外をスローする。 <BR>
     *<BR> 
     *  １）で取得した振替回数 + 1 > ２）で取得した振替可能回数 <BR>
     *<BR> 
     * ４）２）で取得した振替可能回数を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * @@param l_orderCategEnum - (注文カテゴリ)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41355F81012F
     */
    public int validateTransferPossibleCount(
        SubAccount l_subAccount, Date l_datBizDate, OrderCategEnum l_orderCategEnum)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateTransferPossibleCount(" +
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）現時点での振替回数を取得する。
        int l_intTransferCount1 = this.getTransferCount(l_subAccount, l_datBizDate, l_orderCategEnum);
        log.debug("現時点での振替回数 = " + l_intTransferCount1);
                
        //２）設定されている振替可能回数を取得する。
        //振替可能回数 = 補助口座.getMainAccount().振替可能回数 
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        int l_intTransferCount2 = l_mainAccountRow.getTransferCount();
        
        log.debug("補助口座.getMainAccount().振替可能回数 = " + l_intTransferCount2);
        
        //３）以下の条件に当てはまる場合、例外をスローする。
        //１）で取得した振替回数 + 1 > ２）で取得した振替可能回数
        if (l_intTransferCount1 + 1 > l_intTransferCount2)
        {
            log.debug("振替回数が上限回数を超えています。 ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00740,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取得した振替回数[" + l_intTransferCount1 + "] + 1 > 取得した振替可能回数[" + 
                l_intTransferCount2 + "]");
        }       
        log.exiting(STR_METHOD_NAME);
        
        // =========remain zhuo-yong NO.3 begin ============
        
        //４）２）で取得した振替可能回数を返却する。
        return l_intTransferCount2;
        
        // =========remain zhuo-yong NO.3 end ============
    }
    
    /**
     * (validate証券振替可能回数)<BR>
     * 現時点での証券振替回数が、振替可能回数を超えてないかチェックする。 <BR>
     * <BR>
     * １）現時点での振替回数を取得する。<BR>
     * <BR>
     *    １－１）以下の条件で注文単位を検索する。<BR>
     * <BR>
     *    [検索条件] <BR>
     *    口座ID： 引数.補助口座.getMainAccount().口座ID <BR>
     *    補助口座ID： 引数.補助口座.補助口座ID <BR>
     *    部店ID： 引数.補助口座.get取引店().部店ID <BR>
     *    発注日： 引数.発注日 <BR>
     *    注文カテゴリ： 14（証券振替）<BR>
     *    注文状態 != 6（発注失敗（新規注文）） <BR>
     * <BR>
     *    １－２）取得したリストの件数を振替回数とする。<BR>
     * ２）設定されている振替可能回数を取得する。 <BR>
     *    振替可能回数 = 補助口座.get取引店().証券振替上限回数<BR>
     * <BR>
     * ３）以下の条件に当てはまる場合、例外をスローする。<BR>
     *   １）で取得した振替回数 + 1 > ２）で取得した振替可能回数 
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01033 <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_datBizDate - (発注日)
     * @@throws WEB3BaseException
     * @@roseuid 41355F81012F
     */
    public void validateInstitutionTransferPossibleCount(
        SubAccount l_subAccount, Date l_datBizDate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateInstitutionTransferPossibleCount(" +  
            "SubAccount l_subAccount, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）現時点での振替回数を取得する。        

        //１－１）以下の条件で注文単位を検索する。
        //[検索条件] 
        //口座ID： 引数.補助口座.getMainAccount().口座ID 
        //補助口座ID： 引数.補助口座.補助口座ID 
        //部店ID： 引数.補助口座.get取引店().部店ID 
        //発注日： 引数.発注日 
        //注文カテゴリ： 14（証券振替） 
        //注文状態 != 6（発注失敗（新規注文）） 
        
        String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
            "and branch_id = ? and biz_date = ? and order_categ = ? " +
            "and order_status <> ?";    
                    
        //口座ID： 引数.補助口座.getMainAccount().口座ID        
        long l_lngAccountId  = l_subAccount.getMainAccount().getAccountId();
            
        //補助口座ID： 引数.補助口座.補助口座ID
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
        //部店ID： 引数.補助口座.get取引店().部店ID 
        long l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        List l_lisRows = null;

        try
        {
            Object l_bindVars[] = {
                new Long(l_lngAccountId), 
                new Long(l_lngSubAccountId), 
                new Long(l_lngBranchId), 
                l_strBizDate, 
                OrderCategEnum.ASSET_TRANSFER, //14（証券振替）
                OrderStatusEnum.NOT_ORDERED };      //6（発注失敗（新規注文））
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
                    
            //１－２）取得したリストの件数を振替回数とする。           
            int l_intTransferCount = l_lisRows.size();
            
            //２）設定されている振替可能回数を取得する。
            //振替可能回数 = 補助口座.get取引店().証券振替上限回数
            BranchRow branchRow =
                (BranchRow) l_subAccount.getMainAccount().getBranch().getDataSourceObject(); 
            
            int l_intTransferPossibleCount = branchRow.getMarginSecTransferMaxCount();
            
            //３）以下の条件に当てはまる場合、例外をスローする。
            //１）で取得した振替回数 + 1 > ２）で取得した振替可能回数 

            if (l_intTransferCount + 1 > l_intTransferPossibleCount)
            {
                log.debug("振替回数が上限回数を超えています。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00740,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取得した振替回数[" + l_intTransferCount + 
                    "] + 1 > 取得した振替可能回数[" + l_intTransferPossibleCount + "]");
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit振替注文)<BR>
     * 振替注文の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金注文）submit振替注文」 参照<BR>
     * @@param l_subAccount (補助口座)
     * @@param l_productTypeEnum (銘柄タイプ)
     * @@param l_orderTypeEnum (注文種別)
     * @@param l_newOrderSpec (注文内容)
     * @@param l_aioOrderManagerPersistenceEventInterceptor (インタセプタ)
     * @@param l_lngOrder (注文ID)
     * @@param l_strPassword (パスワード)
     * @@throws WEB3BaseException
     * @@roseuid 41358DBB0006
     */
    public void submitTransferOrder(
            SubAccount l_subAccount,
            ProductTypeEnum l_productTypeEnem,
            OrderTypeEnum l_orderTypeEnum,
            NewOrderSpec l_newOrderSpec,
            AioOrderManagerPersistenceEventInterceptor l_aioOrderManagerPersistenceEventInterceptor, 
            long l_lngOrderId, 
            String l_strPassword)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitTransferOrder()";
        log.entering(STR_METHOD_NAME);

        //=========remain zhou-yong NO.2 begin ===========
        
        //1.1) setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        //(AIO注文マネージャ::setThreadLocalPersistenceEventInterceptor)
        //アイテムの定義
        //インタセプタをスレッドにセットする。
        //[引数] 
        //arg0： 引数.インタセプタ 
        this.setThreadLocalPersistenceEventInterceptor(
            l_aioOrderManagerPersistenceEventInterceptor);
        
        //1.2) submitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec, long,
        //     論理ビュー::java::lang::String, boolean)
        //アイテムの定義
        //振替注文の登録を行う。
        //[引数] 
        //補助口座：　@引数.補助口座 
        //銘柄タイプ：　@引数.銘柄タイプ 
        //注文内容：　@引数.注文内容 
        //注文ＩＤ：　@引数.注文ID 
        //パスワード：　@引数.パスワード 
        //isSkip発注審査：　@true 
        OrderSubmissionResult l_orderSubmitResult = 
            this.submitNewOrder(
                l_subAccount, 
                l_productTypeEnem,
                l_newOrderSpec,
                l_lngOrderId,
                l_strPassword,
                true);
        
        if (l_orderSubmitResult.getProcessingResult().isFailedResult())
        {
            log.debug("振替注文の登録エラー" + l_orderSubmitResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_orderSubmitResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //=========remain zhou-yong NO.2 end ===========
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate決済機@関受付可能)<BR>
     * 該当する決済機@関が受付時間内かどうかをチェックする。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 受付日時を取得する。<BR>
     * <BR>　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * 
     * ２）受付日時のカレンダを取得する。<BR>
     *    Calendar.getInstance()<BR>
     *    Calendar.setTime()<BR>
     * <BR>
     * ３）外部機@関受付時間テーブルチェック <BR>
     * <BR>
     * ３－１）外部機@関受付時間テーブルから以下の条件のレコードを取得する。<BR>
     * <BR>
     *      [検索条件]<BR>
     *      外部機@関コード = this.決済機@関ID and<BR>
     *      曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and<BR>
     *      受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<BR>
     *      受付時間（TO) >= カレンダの時刻（HHMMSS）部分<BR>
     * <BR>
     *   取得できなかった場合、falseを返す。<BR>
     * <BR>
     * ３－２）取得したレコードのサービス区分が0（停止中）の場合、falseを返す。<BR>
     * <BR>
     * ４）外部機@関受付時間外（曜日）テーブルチェック <BR>
     *   外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する<BR>
     * <BR>
     *      [検索条件]<BR>
     *      外部機@関コード = this.決済機@関ID and<BR>
     *      (月 = Calendar.get("MONTH")の戻り値+1 or <BR>
     *       月 = "0" ) and<BR>
     *      曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and<BR>
     *      (曜日番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or<BR>
     *       曜日番号 = "0") and<BR>
     *      停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<BR>
     *      停止時間（TO) >= カレンダの時刻（HHMMSS）部分<BR>
     * <BR>
     *   取得できた場合、falseを返す。<BR>
     * <BR>
     * ５）外部機@関受付時間外（日付）テーブルチェック <BR>
     *   外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する<BR>
     * <BR>
     *      [検索条件]<BR>
     *      外部機@関コード = this.決済機@関ID and<BR>
     *      日付 = カレンダの日付(MMDD)部分 and<BR>
     *      停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<BR>
     *      停止時間（TO) >= カレンダの時刻（HHMMSS）部分<BR>
     * <BR>
     *   取得できた場合、falseを返す。<BR>
     * <BR>
     * ６）営業日チェック<BR>
     *   受付日時の日付が非営業日だった場合は、以降の処理を行わずにtrueを返却する。<BR>
     * <BR>
     * ７）会社別決済機@関条件テーブルチェック<BR>
     *   会社別決済機@関条件テーブルから以下の条件のレコードを取得する<BR>
     * <BR>
     *      [検索条件]<BR>
     *      証券会社コード = this.証券会社コード and
     *      部店コード = this.部店コード and
     *      外部機@関コード = this.決済機@関ID and<BR>
     *      受付停止時間（自) <= カレンダの時刻（HHMMSS）部分 and<BR>
     *      受付停止時間（至) >= カレンダの時刻（HHMMSS）部分<BR>
     * <BR>
     *   取得できた場合、falseを返す。<BR>
     * <BR>
     * ８）trueを返す。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * マルチバンク決済を行う決済機@関のID
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413BCA700233
     */
    public boolean validatePaySchemeAcceptPossible(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strPaySchemeId) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validatePaySchemeAcceptPossible(String l_strPaySchemeId)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Date l_datServiceDate = GtlUtils.getSystemTimestamp();
        
        //２）受付日時のカレンダを取得する。
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datServiceDate);
        Date l_datServiceTime = l_calendar.getTime();
        
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        log.debug("受付日時 = " + l_strServiceTime);
        
        //３）外部機@関受付時間テーブルチェック
        //３－１）外部機@関受付時間テーブルから以下の条件のレコードを取得する。
        //[検索条件] 
        //外部機@関コード = this.決済機@関ID and 
        //曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
        //受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
        //受付時間（TO) >= カレンダの時刻（HHMMSS）部分 

        List l_lisOtherOrgSrvTimeRows = null;
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;
        List l_lisCompBnakCondithinRows = null;

        //カレンダの時刻（HHMMSS）部分
        String l_strWhereClause = "other_org_code = ? and week_div = ? " +
            "and service_start_time <= ? and service_end_time >= ?";
        
        //曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1
        String l_strWeekDiv = (l_calendar.get(Calendar.DAY_OF_WEEK) - 1) + "";

        log.debug("決済機@関ID = " + l_strPaySchemeId);
        log.debug("曜日区分 = " + l_strWeekDiv);
        
        Object l_bindServiceTimeVars[] = {
            l_strPaySchemeId, 
            l_strWeekDiv, 
            l_strServiceTime, 
            l_strServiceTime };
        
        //=======remain zhou-yong NO.3 台帳.123  being =========
        
        try
        {               
            l_lisOtherOrgSrvTimeRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgSrvTimeRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceTimeVars);
            
            log.debug("外部機@関受付時間テーブル取得.size() = " + l_lisOtherOrgSrvTimeRows.size());
            //取得できなかった場合、falseを返す。 
            if (l_lisOtherOrgSrvTimeRows == null || l_lisOtherOrgSrvTimeRows.size() == 0)
            {
                log.debug("外部機@関受付時間テーブル取得できなかった場合");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }
            
            //取得したレコードのサービス区分が0（停止中）の場合、falseを返す。            
            OtherOrgSrvTimeRow l_otherOrgSrvTimeRow = (OtherOrgSrvTimeRow) 
                l_lisOtherOrgSrvTimeRows.get(0);
            
            log.debug("search result : = " + l_otherOrgSrvTimeRow);
            log.debug("取得したレコードのサービス区分: = " + l_otherOrgSrvTimeRow.getServiceDiv());
            
            if (WEB3ServiceDivDef.STOPPING.equals(l_otherOrgSrvTimeRow.getServiceDiv()))
            {
                log.debug("取得したレコードのサービス区分が0（停止中）の場合、falseを返す。");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            //４）外部機@関受付時間外（曜日）テーブルチェック 
            //決済機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。 

            //[検索条件] 
            // 外部機@関コード = this.決済機@関ID and 
            // (月 = Calendar.get("MONTH")の戻り値+1 or 
            // 月 = "0" ) and 
            // 曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
            // (曜日番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or 
            // 曜日番号 = "0") and 
            // 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
            // 停止時間（TO) >= カレンダの時刻（HHMMSS）部分 

            l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
                " and week_div = ? and (week_no = ? or week_no = ?)" + 
                " and suspension_start_time <= ? and suspension_end_time >= ?";  
                      
            //月 = Calendar.get("MONTH")の戻り値+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            
            //曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            
            //曜日番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            
            Object l_bindServiceWeekVars[] = {
                l_strPaySchemeId, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0", 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            //取得できた場合、falseを返す。
            if (l_lisOtherOrgOutOfSrvWeekRows != null && l_lisOtherOrgOutOfSrvWeekRows.size() > 0)
            {
                log.debug("外部機@関受付時間外（曜日）テーブル取得できた場合、falseを返す。");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }
            
            // ５）外部機@関受付時間外（日付）テーブルチェック 
            // 外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。 
            // 
            // [検索条件] 
            // 外部機@関コード = this.決済機@関ID and 
            // 日付 = カレンダの日付(MMDD)部分 and 
            // 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
            // 停止時間（TO) >= カレンダの時刻（HHMMSS）部分  

            l_strWhereClause = "other_org_code = ? and suspension_date = ? " +                
            "and suspension_start_time <= ? and suspension_end_time >= ?";  
                      
            //日付 = カレンダの日付(MMDD)部分
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),"MMdd");

            Object l_bindServiceDateVars[] = {
                l_strPaySchemeId, 
                l_strSuspensionDate, 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceDateVars);            
            
            //取得できた場合、falseを返す。
            if (l_lisOtherOrgOutOfSrvDateRows != null && l_lisOtherOrgOutOfSrvDateRows.size() > 0)
            {                
                log.debug("外部機@関受付時間外（日付）テーブル取得できた場合、falseを返す。");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }

            //=======remain zhou-yong NO.3 台帳.123  end =========

			//６）営業日チェック
			//  受付日時の日付が非営業日だった場合は、以降の処理を行わずにtrueを返却する。
			WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate((Timestamp)l_datServiceDate);
			try {
				l_genBizDate.roll(0);
			} catch (WEB3SystemLayerException e) {
				return true;
			}
						
            //７）会社別決済機@関条件テーブルチェック
            //  会社別決済機@関条件テーブルから以下の条件のレコードを取得する
            //
            //     [検索条件]
            //     証券会社コード = this.証券会社コード and
            //     部店コード = this.部店コード and
            //     外部機@関コード = this.決済機@関ID and
            //     受付停止時間（自) <= カレンダの時刻（HHMMSS）部分 and
            //     受付停止時間（至) >= カレンダの時刻（HHMMSS）部分

            l_strWhereClause = "institution_code = ? " +
            "and branch_code = ? and pay_scheme_id = ? " +
            "and suspension_start_time <= ? and suspension_end_time >= ?";  

            Object l_bindCompBankVars[] = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strPaySchemeId,
                l_strServiceTime,
                l_strServiceTime };

            l_lisCompBnakCondithinRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompBankConditionRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindCompBankVars);

            //  取得できた場合、falseを返す。
            if (l_lisCompBnakCondithinRows != null && l_lisCompBnakCondithinRows.size() > 0)
            {                
                log.debug("会社別決済機@関条件テーブルのデータ取得できた場合、falseを返す。");
                log.exiting(STR_METHOD_NAME); 
                return false;
            }

            log.exiting(STR_METHOD_NAME); 
            //８）trueを返す。
            return true;
                
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
    }

    /**
     * (getオンライン入金発注日)<BR>
     * 入金注文をする際の発注日を取得する。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）受付日時のカレンダを取得する。<BR>
     *    Calendar.getInstance()<BR>
     *    Calendar.setTime()<BR>
     * <BR>
     * ３）外部機@関受付時間テーブルから以下の条件のレコードを取得する。<BR>
     * <BR>
     *      [検索条件]<BR>
     *      外部機@関コード  = 引数.決済機@関ID and<BR>
     *      曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and<BR>
     *      受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<BR>
     *      受付時間（TO) >= カレンダの時刻（HHMMSS）部分<BR>
     * <BR>
     *   取得できなかった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00751<BR>
     * <BR>
     * <BR>
     * ４）取得したレコードの受付日区分に従い、発注日を返す。<BR>
     * <BR>
     *    - 受付日区分が1（当日）の場合、カレンダの当日日付を返す。<BR>
     *    - 受付日区分が2（翌日）の場合、カレンダの翌営業日日付を返す。<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 413BCA700253
     */
    public Date getOnlineCashinBizDate(String l_strPaySchemeId) 
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "getOnlineCashinBizDate(String l_strPaySchemeId)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Date l_datServiceDate = GtlUtils.getSystemTimestamp();
        log.debug("受付日時 = " + l_datServiceDate);

        //２）受付日時のカレンダを取得する。
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datServiceDate);
        
        //========remain zhou-yong NO.4 begin ========
        
        //３）外部機@関受付時間テーブルから以下の条件のレコードを取得する。
        //[検索条件] 
        //外部機@関コード = 引数.決済機@関ID and 
        //曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
        //受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
        //受付時間（TO) >= カレンダの時刻（HHMMSS）部分 

        List l_lisRows = null;
        String l_strWhereClause = "other_org_code = ? and week_div = ?" +
            " and service_start_time <= ? and service_end_time >= ?";
        
        //曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1
        String l_strWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
        
        //カレンダの時刻（HHMMSS）部分
        Date l_datServiceTime = l_calendar.getTime();
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        log.debug("決済機@関ID = " + l_strPaySchemeId);
        log.debug("曜日区分 = " + l_strWeekDiv);
        log.debug("カレンダの時刻（HHMMSS）部分 = " + l_strServiceTime);
        
        Object l_bindVars[] = {
            l_strPaySchemeId, 
            l_strWeekDiv, 
            l_strServiceTime, 
            l_strServiceTime};
        try
        {            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgSrvTimeRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
                    
            //取得できなかった場合、例外をスローする。 
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("指定した外部機@関の受付時間に関する情報が取得できませんでした。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定した外部機@関の受付時間に関する情報が取得できませんでした。");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）取得したレコードの受付日区分に従い、発注日を返す。            
        
        OtherOrgSrvTimeRow l_otherOrgSrvTimeRow = (OtherOrgSrvTimeRow) l_lisRows.get(0);
        
        //- 受付日区分が1（当日）の場合、カレンダの当日日付を返す。
        Date l_datOnlineCashinBizDate = null;
        if (WEB3ServiceDateDivDef.DAY_BIZ_DATE.equals(
            l_otherOrgSrvTimeRow.getServiceDateDiv()))
        {
            log.debug("受付日区分が1（当日）の場合、カレンダの当日日付を返す。"); 
            l_datOnlineCashinBizDate = l_datServiceDate;
        }
        //- 受付日区分が2（翌日）の場合、カレンダの翌営業日日付を返す。
        else
        {
            log.debug("受付日区分が2（翌日）の場合、カレンダの翌営業日日付を返す。"); 
            
            Date l_datBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datServiceDate.getTime())).roll(1);                

            l_datOnlineCashinBizDate = l_datBizDate;
        }     
        
        //========remain zhou-yong NO.4 end ========
        
        log.exiting(STR_METHOD_NAME); 
        return WEB3DateUtility.toDay(l_datOnlineCashinBizDate);
     
    }
    
    /**
     * (get振替注文単位)<BR>
     * 振替注文の注文単位オブジェクトの配列を取得する。<BR>
     * <BR>
     * １）以下のオブジェクトを取得する。<BR>
     * <BR>
     * 証券会社オブジェクト = <BR>
     * 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード)<BR>
     * 部店オブジェクト = <BR>
     * 拡張アカウントマネージャ.get部店(引数.証券会社コード, 引数.部店コード)<BR>
     * 口座（顧客）オブジェクト = <BR>
     * 拡張アカウントマネージャ.getMainAccount(証券会社オブジェクト, <BR>
     *       部店オブジェクト, 引数.顧客コード)<BR>
     * <BR>
     * ２）注文単位オブジェクトを取得する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 口座ID = 口座.口座ID<BR>
     * 部店ID = 部店.部店ID<BR>
     * 銘柄タイプ = ProductTypeEnum.CASH<BR>
     * 識別コード = 引数.識別コード<BR>
     * <BR>
     * 取得した注文単位.注文カテゴリ != 15(為替保証金振替)の場合、<BR>
     * 取得した注文単位数 != 2の場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3SystemLayerException <BR>
     *         tag:   SYSTEM_ERROR_80006 <BR>
     * ※為替保証金振替の場合は作成される注文単位数 == 1 <BR>
     * <BR>
     * ３）取得した注文単位を配列で返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@return AioOrderUnit[]
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 413C4E3300AC
     */
    public AioOrderUnit[] getTransferOrderUnit(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strOrderRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME = "getTransferOrderUnit()";
        log.entering(STR_METHOD_NAME);
        
        //１）以下のオブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //証券会社オブジェクト = 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード) 
        Institution l_institution = l_web3GentradeAccountManager.getInstitution(
            l_strInstitutionCode);

        //部店オブジェクト = 拡張アカウントマネージャ.get部店(証券会社オブジェクト, 引数.部店コード)
        Branch l_branch = l_web3GentradeAccountManager.getBranch(
            l_institution, l_strBranchCode);

        //口座（顧客）オブジェクト = 拡張アカウントマネージャ.getMainAccount
        //(証券会社オブジェクト, 部店オブジェクト, 引数.顧客コード)
        MainAccount l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
            l_institution, l_branch, l_strAccountCode);

        //２）注文単位オブジェクトを取得する。
        List l_lisRows = null;
        
        try
        {            
            String l_strWhereClause = "account_id = ? and branch_id = ?" +
                " and order_request_number = ?";
            
            //口座ID = 口座.口座ID
            long l_lngAccountId = l_mainAccount.getAccountId();     
            log.debug("口座.口座ID = " + l_lngAccountId);
                        
            //部店ID = 部店.部店ID
            long l_lngBranchId = l_branch.getBranchId();
            log.debug("部店.部店ID = " + l_lngBranchId);
            log.debug("識別コード = " + l_strOrderRequestNumber);
            
            //[検索条件]
            //口座ID = 口座.口座ID
            //部店ID = 部店.部店ID
            //識別コード = 引数.識別コード
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),                 
                new Long(l_lngBranchId), 
                l_strOrderRequestNumber };  //識別コード = 引数.識別コード 
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);

            List l_lisAioOrderUnit = new ArrayList();
            int l_intNotFxCount = 0;
            boolean l_blnIsNotFxRecord = false;
            
            log.debug("l_lisRows.size() = " + l_lisRows.size());
            
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i);
                log.debug("l_aioOrderUnitRow = " + l_aioOrderUnitRow);
                log.debug("注文単位.注文カテゴリ = " + l_aioOrderUnitRow.getOrderCateg());
                if (!OrderCategEnum.FX.equals(l_aioOrderUnitRow.getOrderCateg()))
                {
                    l_intNotFxCount = l_intNotFxCount + 1;
                    l_blnIsNotFxRecord = true;
                }
                OrderUnit l_orderUnit = 
                    this.getOrderUnit(l_aioOrderUnitRow.getOrderUnitId());
                AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit;
                
                l_lisAioOrderUnit.add(l_aioOrderUnit);
            }
            log.debug("l_blnIsNotFxRecord = " + l_blnIsNotFxRecord);
            log.debug("l_intNotFxCount = " + l_intNotFxCount);
            if (l_blnIsNotFxRecord && l_intNotFxCount != 2)
            {
                log.debug("為替保証金振替以外の場合は、取得した振替注文単位が2つではありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "為替保証金振替以外の場合は、取得した振替注文単位が2つではありません。");
            }
            AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_lisRows.size()];
            l_lisAioOrderUnit.toArray(l_aioOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnits;
        }
        catch (DataException l_ex)
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
    
    /**
     * (validate外部システム受付可能)
     * <BR>
     * 該当する外部システムが受付時間内かどうかをチェックする。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 <BR>
     *     　@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     *          "xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * ２）受付日時のカレンダを取得する。 <BR>
     *     Calendar.getInstance() <BR>
     *     Calendar.setTime() <BR>
     * <BR>
     * ３）外部機@関受付時間外（曜日）テーブルチェック <BR>
     *    外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。 <BR>
     * <BR>
     *  [検索条件] <BR>
     *    外部機@関コード = 引数.システムコード and <BR>
     *    (月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and <BR>
     *    曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and <BR>
     *    (週番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or 曜日番号 = "0") and <BR>
     *    停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and <BR>
     *    停止時間（TO) >= カレンダの時刻（HHMMSS）部分 <BR>
     * <BR>
     *    取得できた場合、例外をthrowする。 <BR>
     * <BR>
     * ４）外部機@関受付時間外（日付）テーブルチェック <BR>
     *    外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。 <BR>
     * <BR>
     *  [検索条件] <BR>
     *    外部機@関コード = 引数.システムコード and <BR>
     *    日付 = カレンダの日付(MMDD)部分 and <BR>
     *    停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and <BR>
     *    停止時間（TO) >= カレンダの時刻（HHMMSS）部分 <BR>
     * <BR>
     *    取得できた場合、例外をthrowする。 <BR>
     * <BR>
     * @@param l_strSystemCode - (システムコード)
     * @@throws WEB3BaseException
     */
    public void validateOtherSystemAcceptPossible(String l_strSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXSystemAcceptPossible(" +
                "String l_strSystemCode)";
        log.entering(STR_METHOD_NAME);       
        
        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_dteAcceptDate = GtlUtils.getSystemTimestamp();
        
        //２）受付日時のカレンダを取得する。
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_dteAcceptDate);
        
        //カレンダの時刻（HHMMSS）部分
        Date l_datServiceTime = l_calendar.getTime();        
        String l_strServiceTime = 
            WEB3DateUtility.formatDate(l_datServiceTime, "HHmmss");
        
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;        
        
        try
        {
            //３）外部機@関受付時間外（曜日）テーブルチェック 
            //外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。 
    
            //[検索条件] 
            //外部機@関コード = 引数.システムコード and 
            //(月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and 
            //曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
            //(週番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or 曜日番号 = "0") and 
            //停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
            //停止時間（TO) >= カレンダの時刻（HHMMSS）部分 
            
            String l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
            " and week_div = ? and (week_no = ? or week_no = ?)" + 
            " and suspension_start_time <= ? and suspension_end_time >= ?";  
                  
            //月 = Calendar.get("MONTH")の戻り値+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            
            //曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            
            //曜日番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            
            log.debug("week_div = " + l_strDayOfWeek);
            log.debug("カレンダの時刻（HHMMSS）部分 = " + l_strServiceTime);
            
            Object l_bindServiceWeekVars[] = {
                l_strSystemCode, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0", 
                l_strServiceTime, 
                l_strServiceTime };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            //取得できた場合、例外をthrowする。
            if (l_lisOtherOrgOutOfSrvWeekRows != null && 
                    l_lisOtherOrgOutOfSrvWeekRows.size() > 0)
            {
                log.debug("外部システムが受付可能時間外です。");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01863,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外部システムが受付可能時間外です。");
                
            }
            
            //４）外部機@関受付時間外（日付）テーブルチェック
            //外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。 
            //[検索条件] 
            //外部機@関コード = 引数.システムコード and 
            //日付 = カレンダの日付(MMDD)部分 and 
            //停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
            //停止時間（TO) >= カレンダの時刻（HHMMSS）部分
            
            l_strWhereClause = "other_org_code = ? and suspension_date = ? " +                
            "and suspension_start_time <= ? and suspension_end_time >= ?";  
            
            //日付 = カレンダの日付(MMDD)部分
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(),"MMdd");
        
            log.debug("外部機@関コード = " + l_strSystemCode);
            log.debug("日付MMdd = " + l_strSuspensionDate);
            
            Object l_bindServiceDateVars[] = {
                l_strSystemCode, 
                l_strSuspensionDate, 
                l_strServiceTime, 
                l_strServiceTime };
          
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceDateVars);
          
            //取得できた場合、例外をthrowする。 
            if (l_lisOtherOrgOutOfSrvDateRows != null && 
                    l_lisOtherOrgOutOfSrvDateRows.size() > 0)
            {                
                log.debug("外部システムが受付可能時間外です。");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01863,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外部システムが受付可能時間外です。");
            }
        }
        catch (DataQueryException l_ex)
        {
          log.error("__DataQueryException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        catch (DataNetworkException l_ex)
        {
          log.error("__DataNetworkException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validateFX口座開設可能)
     * <BR>
     * 顧客がFX取引口座開設可能であるかチェックを行う。 <BR>
     * <BR>
     * （入出金発注審査個別チェック.validateFX口座開設可能()メソッドに委譲する。） <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpenPossible(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpenPossible(" +
            "SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);        
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFXAccOpenPossible(
            l_subAccount, l_compFxConditionParams);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateFX振替可能)
     * <BR>
     * 顧客がFX振替取引可能であるかチェックを行う。 <BR>
     * <BR>
     * （入出金発注審査個別チェック.validateFX振替可能()メソッドに委譲する。） <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strFXSystemCode - (FXシステムコード)
     * @@throws WEB3BaseException
     */
    public void validateFXTransferPossible(
            SubAccount l_subAccount, 
            String l_strFXSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXTransferPossible(" +
                "SubAccount l_subAccount, String l_strFXSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFXTransferPossible(
                l_subAccount, l_strFXSystemCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (振替注文取消)
	 * <BR>
	 * 振替注文の取消処理を行う。 <BR>
	 * <BR>
	 * シーケンス図 <BR>
	 * 「（振替注文）振替注文取消」 参照 <BR>
	 * @@param l_strInstitutionCode - (証券会社コード)
	 * @@param l_strBranchCode - (部店コード)
	 * @@param l_strAccountCode - (顧客コード)
	 * @@param l_strRequestNumber - (識別コード)
	 * @@param l_strMrgTrnRequestNumber - (信用振替用識別コード)
	 * @@throws WEB3BaseException
	 */
	public void transferOrderCancel(
			String l_strInstitutionCode, 
			String l_strBranchCode, 
			String l_strAccountCode, 
			String l_strRequestNumber, 
			String l_strMrgTrnRequestNumber) 
		throws WEB3BaseException
	{
		String STR_METHOD_NAME = "transferOrderCancel(String,String,String,String,String)";
		log.entering(STR_METHOD_NAME);

		//振替注文取消(証券会社コード, 部店コード, 顧客コード, 識別コード, 信用振替用識別コード, パスワード)に委譲する。
		//※パスワード = null とする。
		transferOrderCancel(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strRequestNumber,l_strMrgTrnRequestNumber,null);

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (振替注文取消)
     * <BR>
     * 振替注文の取消処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（振替注文）振替注文取消」 参照 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strRequestNumber - (識別コード)
     * @@param l_strMrgTrnRequestNumber - (信用振替用識別コード)
     * @@throws WEB3BaseException
     */
    public void transferOrderCancel(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber,
            String l_strAdminPassword) 
        throws WEB3BaseException
    {
		String STR_METHOD_NAME = "transferOrderCancel(String,String,String,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1顧客インスタンスを取得する。 
        //[引数] 
        //証券会社コード： 引数.証券会社コード 
        //部店コード： 引数.部店コード 
        //口座コード： 引数.顧客コード 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //顧客オブジェクトを取得する    
        MainAccount l_mainAccount = 
            l_accMgr.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);      
        
        //1.2 注文単位を取得する。 
        //[引数] 
        //証券会社コード： 引数.証券会社コード 
        //部店コード： 引数.部店コード 
        //顧客コード： 引数.顧客コード 
        //識別コード： 引数.識別コード 
        //補助口座タイプ： 1（預り金口座） 
        
        log.debug("証券会社コード = " + l_strInstitutionCode);
        log.debug("部店コード = " + l_strBranchCode);
        log.debug("顧客コード = " + l_strAccountCode);
        log.debug("識別コード = " + l_strRequestNumber);
        
        AioOrderUnit l_aioOrderUnit = null;
        try
        {
            l_aioOrderUnit = this.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRequestNumber,
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
            //1.3 取消注文内容(1)を生成する。 
            //[引数] 
            //注文ID： 注文単位（注文単位(1)）.注文ID 
            CancelOrderSpec l_cancelOrderSpec1 = 
                new CancelOrderSpec(l_aioOrderUnit.getOrderId());
            
            //1.4 出金取消更新インタセプタを生成する。 
            WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
                new WEB3AioCashoutCancelUpdateInterceptor();
            
            //1.5 インタセプタをセットする。
            this.setThreadLocalPersistenceEventInterceptor(
                    l_aioCashoutCancelUpdateInterceptor);

			//1.6 補助口座(1)を取得する。 
			//[引数] 
			//口座ID： 注文単位（注文単位(1)）.口座ID 
			//補助口座ID： 注文単位（注文単位(1)）.補助口座ID 
			log.debug("口座ID = " + l_aioOrderUnit.getAccountId());
			log.debug("補助口座ID = " + l_aioOrderUnit.getSubAccountId());
	            
			SubAccount l_subAccount1 = l_accMgr.getSubAccount(
					l_aioOrderUnit.getAccountId(),
					l_aioOrderUnit.getSubAccountId());

			//1.7 ログインタイプ属性から、取引パスワード設定を属性値を取得する。
			//サービスを取得
			OpLoginSecurityService l_securityService = (OpLoginSecurityService)
				Services.getService(OpLoginSecurityService.class);
			OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
				OpLoginAdminService.class);
        
			//LoginInfo->LoginType->LoginTypeAttribute 
			LoginInfo l_loginInfo = l_securityService.getLoginInfo();
			Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
			//取引パスワード設定を取得する
			String l_strAttribute =
				(String) l_mapAttributes.get(
					WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

			String l_strPassword = null;
			// ログインパスワードの場合
			if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
			{
				l_strPassword = l_strAdminPassword;
			}
			// 取引パスワードの場合
			else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
			{
				WEB3Crypt l_web3Crypt = new WEB3Crypt();
				l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
			}
			log.debug("取引パスワード設定 = " + l_strAttribute);
            
            //1.8 取消を実行する。 
            //[引数] 
            //補助口座：　@get補助口座()の戻り値（補助口座(1)） 
            //取消注文内容：　@取消注文内容オブジェクト（取消注文内容(1)） 
			//パスワード： （以下のとおり）
			//  取引パスワード設定 == ”DEFAULT” の場合、引数.パスワード
			//  取引パスワード設定 == ”取引パスワード使用” の場合、顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
            //isSkip発注審査：　@true 
            this.submitCancelOrder(
                l_subAccount1, 
                l_cancelOrderSpec1, 
				l_strPassword,
                true);
            
            log.debug("取消を実行する(1) end");
            
            log.debug("引数.信用振替用識別コード = " + l_strMrgTrnRequestNumber);
            //1.9 引数.信用振替用識別コード != null
            if (!WEB3StringTypeUtility.isEmpty(l_strMrgTrnRequestNumber))
            {
                log.debug("引数.信用振替用識別コード != null");
                //1.9.1 注文単位を取得する。 
                //[引数] 
                //証券会社コード： 引数.証券会社コード 
                //部店コード： 引数.部店コード 
                //顧客コード： 引数.顧客コード 
                //識別コード： 引数.信用振替用識別コード 
                AioOrderUnit[] l_aioOrderUnits = 
                    this.getTransferOrderUnit(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_strMrgTrnRequestNumber);
                
                //1.9.2 取得した注文単位の要素毎にLoop処理
                for (int i = 0; i < l_aioOrderUnits.length; i++)
                {                    
                    long l_lngOrderId = l_aioOrderUnits[i].getOrderId();
                    long l_lngAccountId = l_aioOrderUnits[i].getAccountId();
                    long l_lngSubAccountId = l_aioOrderUnits[i].getSubAccountId();
                    
                    //1.9.2.1 取消注文内容(2)を生成する。 
                    //[引数] 
                    //注文ID： 注文単位（Loop処理の要素）.注文ID 
                    CancelOrderSpec l_cancelOrderSpec2 = new CancelOrderSpec(l_lngOrderId);
                    
                    //1.9.2.2 補助口座(2)を取得する。 
                    //[引数] 
                    //口座ID： 注文単位（注文単位(2)）.口座ID 
                    //補助口座ID： 注文単位（注文単位(2)）.補助口座ID 
                    SubAccount l_subAccount2 = l_accMgr.getSubAccount(
                            l_lngAccountId,
                            l_lngSubAccountId);

                    //1.9.2.3 インタセプタをセットする。
                    //[引数] 
                    //arg0： 出金取消更新インタセプタ 
                    this.setThreadLocalPersistenceEventInterceptor(
                            l_aioCashoutCancelUpdateInterceptor);

                    //1.9.2.4 取消を実行する。 
                    //[引数] 
                    //補助口座：　@get補助口座()の戻り値（補助口座(2)） 
                    //取消注文内容：　@取消注文内容オブジェクト（取消注文内容(2)） 
					//パスワード： （以下のとおり）
					//  取引パスワード設定 == ”DEFAULT” の場合、引数.パスワード
					//  取引パスワード設定 == ”取引パスワード使用” の場合、顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
                    //isSkip発注審査：　@true 
                    this.submitCancelOrder(
                            l_subAccount2, 
                            l_cancelOrderSpec2, 
							l_strPassword,
                            true);
                    log.debug("取消を実行する(2) end");
                }
            }            
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            //例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validate外株口座開設可能)
     * <BR>
     * 顧客が外株取引口座開設可能であるかチェックを行う。 <BR>
     * <BR>
     * （入出金発注審査個別チェック.validate外株口座開設可能()メソッドに委譲する。）<BR>
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public void validateFeqConAccOpenPossible(
            SubAccount l_subAccount)
        throws WEB3BaseException
    {
        
        String STR_METHOD_NAME = "validateFeqConAccountOpenPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFeqConAccOpenPossible(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate外株振替可能)
     * <BR>
     * 顧客が外株振替取引可能であるかチェックを行う。 <BR>
     * <BR>
     *（入出金発注審査個別チェック.validate外株振替可能()メソッドに委譲する。）<BR> 
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public void validateFeqConTransferPossible(
            SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConTransferPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFeqConTransferPossible(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get外株振替発注日)
     * <BR>
     * 外株振替注文をする際の発注日を取得する。<BR>
     * １）取引時間管理.get発注日()をコールし発注日を取得し、それを(A)とする。 <BR>
     * <BR>
     * ２）(A)のカレンダを取得する。 <BR>
     * <BR>
     * ３）以下の2つのテーブルを検索する。 <BR>
     * <BR>
     * (1)外部機@関受付時間外（曜日）テーブルチェック <BR>
     * 外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。<BR> 
     * <BR>
     * [検索条件] <BR>
     * 外部機@関コード = 外部機@関コード.”UWG” and <BR>
     * (月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and <BR>
     * 曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and <BR>
     * (週番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or 曜日番号 = "0") <BR>
     * <BR>
     * (2)外部機@関受付時間外（日付）テーブルチェック <BR>
     * 外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 外部機@関コード = 外部機@関コード.”UWG” and <BR>
     * 日付 = カレンダの日付(MMDD)部分 <BR>
     * <BR>
     * ４）①@と②のテーブルでレコードが取得された場合は(A)の翌営業日を取得し、 <BR>
     * ３）からの処理を行う。 <BR>
     * <BR>
     * ５）レコードが取得されなかった場合は、(A)の日付を返却する。 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public Date getFeqConTransferBizDate() 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getFEqConTransferBizDate()";
        log.entering(STR_METHOD_NAME);
        
        //１）取引時間管理.get発注日()をコールし発注日を取得し、それを(A)とする。 
        Date l_datBizDateA = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        log.debug("取引時間管理.get発注日() = " + 
                WEB3DateUtility.formatDate(l_datBizDateA,"yyyyMMdd"));
        
        //２）(A)のカレンダを取得する。
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datBizDateA);
        Date l_datBizDateTimeA = l_calendar.getTime();
        
        log.debug("(A)のカレンダを取得 = " + 
                WEB3DateUtility.formatDate(l_datBizDateTimeA,"yyyyMMdd"));
        
        Date l_datTransferDate = null;
        List l_lisOtherOrgOutOfSrvWeekRows = null;
        List l_lisOtherOrgOutOfSrvDateRows = null;
        
        //３）以下の2つのテーブルを検索する。 
        try
        {
            //(1)外部機@関受付時間外（曜日）テーブルチェック 
            //外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。 
            //[検索条件] 
            //外部機@関コード = 外部機@関コード.”UWG” and 
            //(月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and 
            //曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
            //(週番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値 or 曜日番号 = "0") 
            String l_strWhereClause = "other_org_code = ? and (month = ? or month = ?)" +
            " and week_div = ? and (week_no = ? or week_no = ?)" ;
                  
            //月 = Calendar.get("MONTH")の戻り値+1
            String l_strMonth = l_calendar.get(Calendar.MONTH) + 1 + "";
            log.debug("月 = " + l_strMonth);
            
            //曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1
            String l_strDayOfWeek = l_calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
            log.debug("曜日 = " + l_strDayOfWeek);
            
            //曜日番号 = Calendar.get("DAY_OF_WEEK_IN_MONTH")の戻り値
            String l_strWeekOfMonth = l_calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
            log.debug("曜日番号 = " + l_strWeekOfMonth);
                       
            Object l_bindServiceWeekVars[] = {
                WEB3OtherOrgCodeDef.UWG, 
                l_strMonth, 
                "0", 
                l_strDayOfWeek, 
                l_strWeekOfMonth, 
                "0" };
            
            l_lisOtherOrgOutOfSrvWeekRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvWeekRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindServiceWeekVars);
            
            log.debug("l_lisOtherOrgOutOfSrvWeekRows.size() = " + 
                    l_lisOtherOrgOutOfSrvWeekRows.size());
            
            //(2)外部機@関受付時間外（日付）テーブルチェック 
            //外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。 
            //[検索条件] 
            //外部機@関コード = 外部機@関コード.”UWG” and 
            //日付 = カレンダの日付(MMDD)部分 
            l_strWhereClause = "other_org_code = ? and suspension_date = ?";  
            
            //日付 = カレンダの日付(MMDD)部分
            String l_strSuspensionDate = WEB3DateUtility.formatDate(
                    l_datBizDateTimeA,"MMdd");
            
            log.debug("日付 = " + l_strSuspensionDate);
            
            Object l_bindServiceDateVars[] = {
                WEB3OtherOrgCodeDef.UWG, 
                l_strSuspensionDate };
          
            l_lisOtherOrgOutOfSrvDateRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE, 
                    l_strWhereClause, 
                    null, 
                    l_bindServiceDateVars);
            
            log.debug("l_lisOtherOrgOutOfSrvDateRows.size() = " + 
                    l_lisOtherOrgOutOfSrvDateRows.size());
            
            //４）(1)と(2)のテーブルでレコードが取得された場合は(A)の翌営業日を取得し、 
            //    ３）からの処理を行う。 
            if ((l_lisOtherOrgOutOfSrvWeekRows != null && 
                l_lisOtherOrgOutOfSrvWeekRows.size() > 0) && 
                (l_lisOtherOrgOutOfSrvDateRows != null && 
                l_lisOtherOrgOutOfSrvDateRows.size() > 0))
            {
                log.debug("(1)と(2)のテーブルでレコードが取得された場合");
                l_datTransferDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datBizDateA.getTime())).roll(1);                
            }
            //５）レコードが取得されなかった場合は、(A)の日付を返却する。 
            else
            {
                log.debug("レコードが取得されなかった場合");
                l_datTransferDate = l_datBizDateA;
            }
        }
        catch (DataQueryException l_ex)
        {
          log.error("__DataQueryException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        catch (DataNetworkException l_ex)
        {
          log.error("__DataNetworkException__", l_ex);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        log.debug("return : " + 
                WEB3DateUtility.formatDate(l_datTransferDate,"yyyyMMdd"));
        return l_datTransferDate;
    }
    
    /**
     * (validate外株初回振替)
     * <BR>
     * 外株振替が初回の場合のチェックを行う。 <BR>
     * <BR>
     * （入出金発注審査個別チェック.validate外株初回振替()メソッドに委譲する。） <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_subAccount - (振替金額)
     * @@throws WEB3BaseException
     */
    public void validateFeqConFirstTransfer(
            SubAccount l_subAccount, double l_dblTransferAmount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConFirstTransfer(" +
                "SubAccount l_subAccount, double l_dblTransferAmount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        l_reusableValidations.validateFeqConFirstTransfer(
                l_subAccount, l_dblTransferAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate外株振替取消)
     * <BR>
     * 取消対象となっている外株振替注文が取消可能かどうかのチェックを行う。 <BR>
     * <BR>
     * １）UWG振替状況の取得 <BR>
     * 外株振替連携データ制御サービスImpl.getUWG振替状況()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 部店コード： 引数.補助口座.get取引店.getBranchCode() <BR>
     * 識別コード： 引数.識別コード <BR>
     * <BR>
     * ２）UWG振替状況Params.初回振替フラグ==”初回振替” の場合、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）以下の条件でUWG振替状況テーブルを検索する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 部店コード： 引数.補助口座.get取引店.getBranchCode() <BR>
     * 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() <BR>
     * 振替状況区分： ”決済中” <BR>
     * 送受信区分： ”未送信” <BR>
     * 初回振替フラグ： ”その他” <BR>
     * <BR>
     * ２－２）検索の結果が0件でなかった場合は、取消不可ということで例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01940<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strRequestNumber - 識別コード
     * @@throws WEB3BaseException
     */
    public void validateFeqConTransferCancel(
            SubAccount l_subAccount, String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConTransferCancel(" +
                "SubAccount l_subAccount, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);       
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）UWG振替状況の取得 
        //外株振替連携データ制御サービスImpl.getUWG振替状況()をコールする。 
        //[引数] 
        //証券会社コード： 引数.補助口座.証券会社コード 
        //部店コード： 引数.補助口座.get取引店.getBranchCode() 
        //識別コード： 引数.識別コード 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            l_feqConTransferDataControlService.getUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_strRequestNumber);
        
        //２）UWG振替状況Params.初回振替フラグ==”初回振替” の場合、以下の処理を行う。 
        //２－１）以下の条件でUWG振替状況テーブルを検索する。 
        //[検索条件] 
        //証券会社コード： 引数.補助口座.証券会社コード 
        //部店コード： 引数.補助口座.get取引店.getBranchCode() 
        //顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        //振替状況区分： ”決済中” 
        //送受信区分： ”未送信” 
        //初回振替フラグ： ”その他” 
        if (WEB3FeqFirstTransferFlagDef.TRANSFERRED.equals(
                l_uwgTransferStatusParams.getFirstTransferDiv()))
        {
            List l_lisRows = null;
            
            try
            {            
                String l_strWhereClause = 
                    " institution_code = ? and branch_code = ?" +
                    " and account_code = ? and transfer_status_div = ?" +
                    " and send_rcv_div = ? and first_transfer_div = ?";
                      
                Object l_bindVars[] = {
                    l_subAccount.getInstitution().getInstitutionCode(),                 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode(), 
                    WEB3TransferStatusDivDef.PROCESSING, 
                    WEB3SendRcvDivDef.NOT_SEND, 
                    WEB3FeqFirstTransferFlagDef.NOT_TRANSFER }; 
                
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        UwgTransferStatusRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
                
                //２－２）検索の結果が0件でなかった場合は、取消不可ということで例外をスローする。 
                if (l_lisRows.size() > 0)
                {
                    log.debug("処理中のUWG振替状況が存在しています。");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01940,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "処理中のUWG振替状況が存在しています。");
                }
                
            }
            catch (DataException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validateFX口座開設)<BR>
     * <BR>
     * 顧客がFX取引口座開設済であるかチェックを行う。  
     * 
     * （入出金発注審査個別チェック.validateFX口座開設()メソッドに委譲する。） 
     * @@param l_subAccount - (補助口座)
     * @@param l_strFXSystemCode - (FXシステムコード)
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpen(
            SubAccount l_subAccount, 
            String l_strFXSystemCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpen(" +
                "SubAccount, String)";
        log.entering(STR_METHOD_NAME);        
        
        //（入出金発注審査個別チェック.validateFX口座開設()メソッドに委譲する。）
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
        
        l_reusableValidations.validateFXAccOpen(
            l_subAccount, l_strFXSystemCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is保証金振替)<BR>
     * 保証金振替において、振替注文を作成する必要が<BR> 
     * あるか判定する。<BR> 
     * <BR>
     * 作成する必要がある場合は、”true”を、<BR> 
     * 作成する必要がない場合は、”false”を返却する。<BR> 
     * <BR>
     * １）発注日のチェック<BR> 
     * 発注日が翌営業日の時間帯かどうかの判定を行う。<BR> 
     * 引数.業務日付 ＜ 引数.発注日の場合、<BR>
     * 以下の処理を行う。<BR>
     * 引数.業務日付 ＜ 引数.発注日でない場合、<BR>
     * "true"を返却する。<BR>　@ 
     * <BR>
     * ２）プロセス管理Paramsの取得<BR> 
     * プロセス管理テーブルを以下の条件で検索を行う。<BR>　@ 
     * <BR>
     * [引数の設定]<BR> 
     * プロセスID：　@”0005”(保証金自動振替終了)<BR> 
     * 証券会社コード：　@引数.補助口座.証券会社コード<BR>
     * 部店コード：　@引数.補助口座.get取引店.部店コード<BR>
     * 処理区分：”1”（処理済）<BR>
     * <BR>
     * ３）プロセス管理Paramsが取得できない（該当データなし）場合、<BR> 
     * "false"を返却する。<BR>
     * <BR>
     * ４）プロセス管理Paramsが取得できた（該当データあり）場合、<BR> 
     * "true"を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_datBizDate - (業務日付)
     * @@param l_datOrderBizDate - (発注日)
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     */
    public boolean isDepositTransfer(
        SubAccount l_subAccount, 
        Date l_datBizDate, 
        Date l_datOrderBizdate)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "isDepositTransfer(" + 
            "SubAccount l_subAccount, Date l_datBizDate, Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_datBizDate == null || l_datOrderBizdate == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）発注日のチェック
        //引数.発注日 > 引数.業務日付の場合、以下の処理を行う。
        if (WEB3DateUtility.compareToDay(l_datOrderBizdate, l_datBizDate) > 0)
        {
            log.debug("注文単位.発注日 > 業務日付の場合");
            
            //２）プロセス管理テーブルのレコード取得
            //   プロセス管理テーブルを以下の条件で検索を行う。　@
            // [引数の設定] 
            // プロセスID：　@”0005”(保証金自動振替終了) 
            // 証券会社コード：　@引数.補助口座.証券会社コード 
            // 部店コード：　@引数.補助口座.get取引店.部店コード
            // 処理区分：”1”（処理済） 

            List l_lisRows = null;
            try
            {
                String l_strWhereClause = 
                    " process_id = ? and institution_code = ?" +
                    " and branch_code = ? and status = ?";
            
                Object l_bindVars[] = {
                    WEB3AioProcessManagementIdDivDef.DEPOSIT_TRANSFER_END, 
                    l_subAccount.getInstitution().getInstitutionCode(),                 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    WEB3ProcDivDef.PROCESSED};
             
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ProcessManagementRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);
            
            
                //３）プロセス管理テーブルのレコードが取得できない（該当データなし）場合、
                //  "false"を返却する。 
                if (l_lisRows.size() == 0)
                {
                    log.debug("プロセス管理テーブルに該当データがありません。");
                    log.exiting(STR_METHOD_NAME); 
                    return false;
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBサーバとの通信に失敗した", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
            
        //４）引数.発注日 > 引数.業務日付でない場合、
        //    プロセス管理テーブルのレコードが取得できた（該当データあり）場合、
        //    "true"を返却する。 
        log.exiting(STR_METHOD_NAME); 
        return true;
    }

    /**
     * (validate居住者)<BR>
     * <BR>
     * 居住者チェックを行う。<BR>
     * １）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分<BR>
     * 　@　@が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02344<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateResident(SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateResident(WEB3GentradeSubAccount l_subAccount)";
        log.debug(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accountMgr.getMainAccount(l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("顧客テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MainAccountRow l_row = (MainAccountRow) l_mainAccount.getDataSourceObject();

        //１）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分
        //が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_row.getResident()) ||
            WEB3ResidentDef.NON_RESIDENT.equals(l_row.getResident()))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02708,
                this.getClass().getName() + STR_METHOD_NAME,
                "非居住者は申込むことができません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateSL返済重複注文)<BR>
     * 引数の受渡日と同じ日にすでに証券担保ローン返済注文が出てないかどうかを<BR>
     * チェックする。<BR>
     * <BR>
     * ※重複が許されないのは、通常の証券担保ローン返済注文のみ。<BR>
     * <BR>
     * １）以下の条件にて注文単位テーブルのレコードを取得する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 口座ID = 引数.補助口座.getAccountId()の戻り値<BR>
     * 補助口座ID = 引数.補助口座.getSubAccountId()の戻り値<BR>
     * 注文種別 = 1020（振替注文（預かり金からオリックスクレジット））<BR>
     * 注文状態 = 1（受付済） or 2（発注中） or 3（発注済）<BR>
     * 注文有効状態 = 1（オープン）<BR>
     * 受渡日 = 引数.受渡日<BR>
     * 出金申込区分 = null<BR>
     * <BR>
     * ２）取得したレコード件数 > 0 の場合は、例外をスローする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 注文の受渡日<BR>
     * @@throws WEB3BaseException
     */
    public void validateSLRepayDuplicateOrder(SubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSLRepayDuplicateOrder(SubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        try
        {
            //１）以下の条件にて注文単位テーブルのレコードを取得する
            long l_lngAccountId = l_subAccount.getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and sub_account_id = ? ");
            l_sbWhere.append(" and order_type = ? ");
            l_sbWhere.append(" and (order_status = ? or order_status = ? or order_status = ? ) ");
            l_sbWhere.append(" and order_open_status = ? ");
            l_sbWhere.append(" and delivery_date = ? ");
            l_sbWhere.append(" and payment_application_div is null");

            Object[] l_sqlValues = new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.TO_ORIX_CREDIT.intValue() + "",
                OrderStatusEnum.ACCEPTED.intValue() + "",
                OrderStatusEnum.ORDERING.intValue() + "",
                OrderStatusEnum.ORDERED.intValue() + "",
                OrderOpenStatusEnum.OPEN.intValue() + "",
                l_datDeliveryDate};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listFindAllQuerys = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_sqlValues);

            //２）取得したレコード件数 > 0 の場合は、例外をスローする
            if(l_listFindAllQuerys.size() > 0)
            {
                log.debug("指定したお振込予定日と同じ振込日の出金申込がすでに登録されています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00757,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "指定したお振込予定日と同じ振込日の出金申込がすでに登録されています。" );                
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

    /**
     * (validateCFD振替可能)<BR>
     * 顧客がFX振替取引可能であるかチェックを行う。<BR>
     * <BR>
     * （入出金発注審査個別チェック.validateCFD振替可能()メソッドに委譲する。）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@throws WEB3BaseException
     */
    public void validateCFDChangePoss(
        SubAccount l_subAccount, String l_strFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCFDChangePoss(SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();

        //入出金発注審査個別チェック.validateCFD振替可能()メソッドに委譲する。
        l_reusableValidations.validateCFDChangePoss(
            l_subAccount, l_strFxSystemCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate振替取引可能)<BR>
     * 顧客が振替取引可能であるかチェックを行う。<BR>
     * <BR>
     * （入出金発注審査個別チェック.validate振替取引可能()メソッドに委譲する。）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateTransferTradePossible(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTransferTradePossible(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();

        //入出金発注審査個別チェック.validate振替取引可能()メソッドに委譲する
        l_reusableValidations.validateTransferTradePossible(
            l_subAccount,
            l_compFxConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get証拠金振替指定日)<BR>
     * 証拠金振替の指定日を取得する。<BR>
     * <BR>
     * １）証拠金振替の指定日を取得する。<BR>
     * <BR>
     * １－１）業務日付(*1)と引数.発注日が一致する場合<BR>
     * <BR>
     * 　@0を返却する。<BR>
     * <BR>
     * １－２）その他（翌日発注）の場合<BR>
     * <BR>
     * 　@1を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return int
     */
    public int getMarginTransferDesignatedDate(Date l_datBizDate)
    {
        final String STR_METHOD_NAME = "getMarginTransferDesignatedDate(Date)";
        log.entering(STR_METHOD_NAME);

        //業務日付(*1)と引数.発注日が一致する場合
        //0を返却する。
        int l_intDesignatedDate = 0;

        //その他（翌日発注）の場合
        //1を返却する。
        if (WEB3DateUtility.compare(GtlUtils.getTradingSystem().getBizDate(),
            l_datBizDate) != 0)
        {
        	l_intDesignatedDate = 1;
        }

        log.exiting(STR_METHOD_NAME);
        return l_intDesignatedDate;
    }
}
@
