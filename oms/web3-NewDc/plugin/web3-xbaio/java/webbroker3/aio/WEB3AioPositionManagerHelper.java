head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金ポジションヘルパー(WEB3AioPositionManagerHelper)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建  (中訊) 新規作成
                   2004/10/27 屈陽 (中訊) レビュー
                   2008/12/03 SCS大嶋　@CFD連携対応
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioPositionManagerHelper;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (入出金ポジションヘルパー)<BR>
 * 入出金ポジションヘルパークラス<BR>
 * （AioPositionManagerHelperの拡張クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */
public class WEB3AioPositionManagerHelper extends AioPositionManagerHelper
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioPositionManagerHelper.class);
        
    /**
     * @@param arg0
     */
    public WEB3AioPositionManagerHelper(ProductTypeEnum arg0)
    {
        super(arg0);
    }
    /**
     * (is入出金)<BR>
     * （isCashTransferのオーバーライド）<BR>
     * <BR>
     * 引数の注文単位が、以下の注文種別の場合はtrueを、それ以外の<BR>
     * 場合はfalseを返却する。<BR>
     * <BR>
     *    1001： 出金注文<BR>
     *    1002： 入金注文<BR>
     *    1005： 振替注文(預り金から信用保証金)<BR>
     *    1006： 振替注文(信用保証金から預り金)<BR>
     *    1007： 振替注文(預り金から株先証拠金)<BR>
     *    1008： 振替注文(株先証拠金から預り金)<BR>
     *    1011： 為替保証金振替注文(預り金から為替保証金) <BR>
     *    1012： 為替保証金振替注文(為替保証金から預り金) <BR>
     *    1017： その他振替注文（預り金からX）<BR>
     *    1018： その他振替注文（Xから預り金）<BR> 
     *    1021：CFD振替注文（預り金からCFD口座）
     *    1022：CFD振替注文（CFD口座から預り金）
     *    
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return boolean
     * @@roseuid 413EDC8B0336
     */
    public boolean isCashTransfer(AioOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isCashTransfer(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.debug("注文単位オブジェクトオブジェクトNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //===========注文単位オブジェクト注文種別タイプを取得する==================
        log.debug("注文単位オブジェクト注文種別タイプを取得する = " + l_orderUnit.getOrderType());
        
        if (!((OrderTypeEnum.CASH_OUT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.CASH_IN.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_orderUnit.getOrderType()))
        ))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    /**
     * (set入出金金額)<BR>
     * （setAssetNetAmountのオーバーライド）<BR>
     * <BR>
     * 入出金額の計算を行い、値をトランザクションの項目にセットする。<BR>
     * <BR>
     * １）トランザクション.トランザクションカテゴリ = 10（入出金）の場合<BR>
     * 1－１） <BR>
     * トランザクション.トランザクションタイプ = 10（入金注文） の場合 <BR>
     * <BR>
     * 入出金金額 = <BR>
     *      トランザクション.数量 - トランザクション.手数料 - トランザクション.手数料消費税 <BR>
     * <BR>
     * 1－２） <BR>
     * トランザクション.トランザクションタイプ = 20（出金注文） の場合 <BR>
     * <BR>
     * 入出金金額 = <BR>
     *      -1 × トランザクション.数量 - トランザクション.手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２）トランザクション.トランザクションカテゴリ = 100（振替）の場合<BR>
     * <BR>
     * ２－１）トランザクション.補助口座IDから補助口座オブジェクトを取得し、<BR>
     *         補助口座タイプを取得する。<BR>
     * <BR>
     * ２－２）補助口座タイプ = 1（預り金口座）の場合<BR>
     * <BR>
     * ２－２－１）<BR>
     *    トランザクション.トランザクションタイプ = 1005<BR>
     *      （振替注文（預り金から信用保証金）） or<BR>
     *    トランザクション.トランザクションタイプ = 1007<BR>
     *      （振替注文（預り金から株先証拠金）） or <BR> 
     *    トランザクション.トランザクションタイプ = 1011 <BR>
     *      （為替保証金振替注文（預り金から為替保証金）） の場合 <BR>
     * <BR>
     *    入出金金額 = -1 × トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２－２－２）<BR>
     *    トランザクション.トランザクションタイプ = 1006 <BR>
     *      （振替注文（信用保証金から預り金）） or <BR>
     *    トランザクション.トランザクションタイプ = 1008 <BR>
     *      （振替注文（株先証拠金から預り金）） or <BR>
     *    トランザクション.トランザクションタイプ = 1012 <BR>
     *      （為替保証金振替注文（為替保証金から預り金）） の場合 <BR>
     * <BR>
     *    入出金金額 = トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２－３）補助口座タイプ = 2（保証金口座）の場合<BR>
     * <BR>
     * ２－３－１）<BR>
     *    トランザクション.トランザクションタイプ = 1005<BR>
     * （振替注文（預り金から信用保証金）） の場合<BR>
     * <BR>
     *    入出金金額 = トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２－３－２）<BR>
     *    トランザクション.トランザクションタイプ = 1006<BR>
     * （振替注文（信用保証金から預り金）） の場合<BR>
     * <BR>
     *    入出金金額 = -1 × トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２－４）補助口座タイプ = 7（証拠金口座）の場合<BR>
     * <BR>
     * ２－４－１）<BR>
     *    トランザクション.トランザクションタイプ = 1007<BR>
     * （振替注文（預り金から株先証拠金）） の場合<BR>
     * <BR>
     *    入出金金額 = トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ２－４－２）<BR>
     *    トランザクション.トランザクションタイプ = 1008<BR>
     * （振替注文（株先証拠金から預り金）） の場合<BR>
     * <BR>
     *    入出金金額 = -1 × トランザクション.数量 - トランザクション.<BR>
     * 手数料 - トランザクション.手数料消費税<BR>
     * <BR>
     * ３）トランザクション.金額に、入出金金額の値をセットする。<BR>
     * <BR>
     * @@param l_finTransactionParams - (トランザクション)<BR>
     * トランザクションParamsオブジェクト<BR>
     * @@roseuid 413EDCB80123
     */
    protected void setAssetNetAmount(AioFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME =
            "setAssetNetAmount(AioFinTransactionParams l_finTransactionParams)";
        log.entering(STR_METHOD_NAME);
        if (l_finTransactionParams == null)
        {
            log.debug("トランザクションParamsオブジェクトNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）トランザクション.トランザクションカテゴリ = 10（入出金）の場合
        //  スーパークラスのメソッドをコールする。
        if (FinTransactionCateg.CASH_IN_OUT.equals(
                l_finTransactionParams.getFinTransactionCateg()))
        {
            //1－１） 
            //トランザクション.トランザクションタイプ = 10（入金注文） の場合 
            //入出金金額 = トランザクション.数量 - 
            //  トランザクション.手数料 - トランザクション.手数料消費税 
                        
            if (FinTransactionType.CREDIT.equals(
                    l_finTransactionParams.getFinTransactionType()))
            {
                double netAmount = l_finTransactionParams.getQuantity() - 
                        l_finTransactionParams.getCommissionFee() - 
                        l_finTransactionParams.getCommissionFeeTax();
                l_finTransactionParams.setNetAmount(netAmount);
            }           
            //1－２） 
            //トランザクション.トランザクションタイプ = 20（出金注文） の場合 
            //入出金金額 = -1 × トランザクション.数量 - 
            //  トランザクション.手数料 - トランザクション.手数料消費税 
            else if (FinTransactionType.DEBIT.equals(
                    l_finTransactionParams.getFinTransactionType()))
            {
                double netAmount = -1 * l_finTransactionParams.getQuantity() - 
                        l_finTransactionParams.getCommissionFee() - 
                        l_finTransactionParams.getCommissionFeeTax();
                l_finTransactionParams.setNetAmount(netAmount);
            }           
        }
        
        //２）トランザクション.トランザクションカテゴリ = 100（振替）の場合
        if (FinTransactionCateg.CASH_TRANSFER.equals(
                l_finTransactionParams.getFinTransactionCateg()))
        {
            // ２－１）トランザクション.補助口座IDから補助口座オブジェクトを取得し、
            // 補助口座タイプを取得する。
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //拡張アカウントマネージャ取得
            WEB3GentradeAccountManager l_gentradeAccountManaer =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            try
            {
                l_subAccount =
                    l_gentradeAccountManaer.getSubAccount(
                        l_finTransactionParams.getAccountId(),
                        l_finTransactionParams.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(
                    "Not Found 該当の補助口座  with "
                        + "(補助口座ID)l_lngSubAccountId =  "
                        + l_finTransactionParams.getSubAccountId()
                        + " and (口座ID)l_lngAccountId = "
                        + l_finTransactionParams.getAccountId(), l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
           
           //=============入出金金額タイプを取得する==================
            //入出金金額     
            double l_dblNetAmount = 0;
            int l_intCflag = -1;
            //トランザクション.数量を取得する
            double l_dblQuantity = l_finTransactionParams.getQuantity();
            //トランザクション.手数料を取得する
            double l_dblCommissionFee = l_finTransactionParams.getCommissionFee();
            //トランザクション.手数料消費税を取得する
            double l_dblCommissionFeeTax = 
                l_finTransactionParams.getCommissionFeeTax();
            //=============入出金金額タイプを取得する==================
               
            //２－２）補助口座タイプ = 1（預り金口座）の場合
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                //２－２－１）
                //トランザクション.トランザクションタイプ = 1005
                //  （振替注文（預り金から信用保証金）） or
                //トランザクション.トランザクションタイプ = 1007
                //  （振替注文（預り金から株先証拠金）） or 
                //トランザクション.トランザクションタイプ = 1011
                //  （為替保証金振替注文（預り金から為替保証金）） 
                //トランザクション.トランザクションタイプ = 1021
            	//　@（CFD振替注文（預り金からCFD口座））） の場合 

                if ((FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType())) || 
                    (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                        l_finTransactionParams.getFinTransactionType())) ||
                    (FinTransactionType.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))||
                    (FinTransactionType.CFD_FROM_DEPOSIT_AMOUNT.equals(
                                l_finTransactionParams.getFinTransactionType())))
                {
                    //入出金金額 = -1 × トランザクション.数量 - トランザクション.
                    // 手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                //２－２－２）
                //トランザクション.トランザクションタイプ = 1006
                //（振替注文（信用保証金から預り金）） or
                // トランザクション.トランザクションタイプ = 1008
                //（振替注文（株先証拠金から預り金）） or
                //トランザクション.トランザクションタイプ = 1012
                //（為替保証金振替注文（為替保証金から預り金））
                //トランザクション.トランザクションタイプ = 1022
                //（CFD振替注文（CFD口座から預り金）））の場合
                if ((FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType())) ||
                    (FinTransactionType.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType())) || 
                    (FinTransactionType.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType()))|| 
                    (FinTransactionType.DEPOSIT_AMOUNT_FROM_CFD.equals(
                        l_finTransactionParams.getFinTransactionType())))
                {
                    //入出金金額 = トランザクション.数量 - トランザクション.
                    //手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            //２－３）補助口座タイプ = 2（保証金口座）の場合
            else if (
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                //２－３－１）
                // トランザクション.トランザクションタイプ = 1005
                //（振替注文（預り金から信用保証金）） の場合
                if (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //入出金金額 = トランザクション.数量 - トランザクション.
                    // 手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                // ２－３－２）
                // トランザクション.トランザクションタイプ = 1006
                //（振替注文（信用保証金から預り金）） の場合
                if (FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    // 入出金金額 = -1 × トランザクション.数量 - トランザクション.
                    // 手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            // ２－４）補助口座タイプ = 7（証拠金口座）の場合
            else if (
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                // ２－４－１）
                //トランザクション.トランザクションタイプ = 1007
                //（振替注文（預り金から株先証拠金）） の場合
                if (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //入出金金額 = トランザクション.数量 - トランザクション.
                    //手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                // ２－４－２）
                // トランザクション.トランザクションタイプ = 1008
                //（振替注文（株先証拠金から預り金）） の場合
                if (FinTransactionType.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //入出金金額 = -1 × トランザクション.数量 - トランザクション.
                    //手数料 - トランザクション.手数料消費税
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            // ３）トランザクション.金額に、入出金金額の値をセットする。
            l_finTransactionParams.setNetAmount(l_dblNetAmount);    
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
