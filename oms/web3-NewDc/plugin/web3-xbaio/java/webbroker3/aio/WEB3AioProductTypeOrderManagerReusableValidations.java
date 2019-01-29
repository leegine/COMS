head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioProductTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金発注審査個別チェック(WEB3AioProductTypeOrderManagerReusableValidations)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 周勇 (中訊) 新規作成      
                   2004/10/25 于美麗 (中訊) レビュー    
                   2005/10/20 韋念瓊 (中訊) 投信フィデリティ対応         
                   2006/03/24 玉岡（SRA） 仕様変更（モデル）522
                   2006/03/29 情野（SRA） 仕様変更（モデル）524
                   2006/05/10 黄建（中訊） 仕様変更（モデル）561、566               
                   2006/11/02 鈴木 (SCS) モデルNo680対応
Revesion History : 2008/09/22 王志葵 (中訊) 仕様変更（モデル）992,1004,1023,1044
Revesion History : 2009/03/12 柴双紅 (中訊) 仕様変更（モデル）1109,1156
Revesion History : 2009/03/18 車進 (中訊) 仕様変更（モデル）1121,1159
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.data.AmountRangeDao;
import webbroker3.aio.data.AmountRangeRow;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3CfdAccOpenDivDef;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FundTypeDef;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3MrfAccOpenDivDef;
import webbroker3.common.define.WEB3MrfAllowDivDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OnlineAccOpenDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入出金発注審査個別チェック)<BR>
 * 入出金発注審査個別チェッククラス<BR>
 * （AioProductTypeOrderManagerReusableValidationsの拡張クラス）<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioProductTypeOrderManagerReusableValidations extends AioProductTypeOrderManagerReusableValidations 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductTypeOrderManagerReusableValidations.class); 
    
    /**
     * (validate上限金額)<BR>
     * 入金額が、上限金額を超えていないかをチェックする。<BR>
     * <BR>
     * 以下の条件に当てはまる場合、例外をスローする。<BR>
     *   引数.金額 > 引数.上限金額
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00252<BR>
     * <BR>
     * @@param l_dblCreditAmount - (入金額)<BR>
     * 画面にて入力された入金額
     * @@param l_dblMaxAmount - (上限金額)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E929120275
     */
    public void validateMaxAmount(double l_dblCreditAmount, double l_dblMaxAmount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMaxAmount(double l_dblCreditAmount, " +
            "double l_dblMaxAmount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblCreditAmount > l_dblMaxAmount)
        {
            log.debug("引数.金額 > 引数.上限金額");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.金額[=" + l_dblCreditAmount +"] > 引数.上限金額[=" + l_dblMaxAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate下限金額)<BR>
     * 入金額が、下限金額を超えていないかをチェックする。<BR>
     * <BR>
     * 以下の条件に当てはまる場合、例外をスローする。<BR>
     *   引数.金額 < 引数.下限金額<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00253<BR>
     * <BR>
     * @@param l_dblCreditAmount - (入金額)<BR>
     * 画面にて入力された入金額
     * @@param l_dblMinAmount - (下限金額)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E9291D00FE
     */
    public void validateMinAmount(double l_dblCreditAmount, double l_dblMinAmount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMinAmount(double l_dblCreditAmount, " +
            "double l_dblMinAmount) ";
        log.entering(STR_METHOD_NAME);

        if(l_dblCreditAmount < l_dblMinAmount)
        {
            log.debug("引数.金額 < 引数.下限金額");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00253,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.金額[=" + l_dblCreditAmount +"] < 引数.下限金額[=" + l_dblMinAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (validate最小単位)<BR>
     * 入金額が最小単位で割り切れるかどうかをチェックする。<BR>
     * 入金額 ÷ 最小単位<BR>
     * 上記計算式の結果が割り切れない場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00752<BR>
     * <BR>
     * @@param l_dblCreditAmount - (入金額)<BR>
     * 画面にて入力された入金額
     * @@param l_dblSmallestUnit - (最小単位)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E929300051
     */
    public void validateSmallestUnit(double l_dblCreditAmount, double l_dblSmallestUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateSmallestUnit(double l_dblCreditAmount, " +
            "double l_dblSmallestUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblCreditAmount % l_dblSmallestUnit != 0)
        {
            log.debug("入金額 ÷ 最小単位,計算式の結果が割り切れない場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00752,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入金額[=" + l_dblCreditAmount + "] ÷ 最小単位[=" + l_dblSmallestUnit + 
                "],計算式の結果が割り切れない");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate上限回数)<BR>
     * 1日当たりの入金回数が上限回数を越えてないかをチェックする。<BR>
     * 以下の条件と一致する場合、例外をスローする。<BR>
     *    引数.現時点注文回数+1 > 引数.上限回数<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00753<BR>
     * <BR>
     * @@param l_lngPresentOrderCount - (現時点注文回数)<BR>
     * 現時点での1日の入金注文回数<BR>
     * @@param l_lngMaxCount - (1日の入金上限回数)<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E9295903AB
     */
    public void validateMaxCount(long l_lngPresentOrderCount, long l_lngMaxCount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMaxCount(long l_lngPresentOrderCount, " +
            "long l_lngMaxCount)";
        log.entering(STR_METHOD_NAME);
         
        if((l_lngPresentOrderCount + 1) > l_lngMaxCount)
        {
            log.debug("引数.現時点注文回数+1 > 引数.上限回数場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00753,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.現時点注文回数[=" + l_lngPresentOrderCount + 
                "] + 1  > 引数.上限回数[=" + l_lngMaxCount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate総入金上限額 )<BR>
     * 1日当たりの入金総額を超えてないかをチェックする。<BR>
     * 以下の条件と一致する場合、例外をスローする。<BR>
     *    引数.現時点入金総額 + 引数.入金額 > 引数.上限総額<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00754<BR>
     * <BR>
     * @@param l_dblPresentCreditTotalAmount - (現時点入金総額)<BR>
     * 現時点での1日の入金総額
     * @@param l_dblCreditAmount - (入金額)<BR>
     * 今回の入金額<BR>
     * @@param l_dblMaxTotalAmount - (上限総額)<BR>
     * 1日の入金上限額<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40F28FBD0161
     */
    public void validateTotalCreditMaxAmount(double l_dblPresentCreditTotalAmount,
        double l_dblCreditAmount, double l_dblMaxTotalAmount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateTotalCreditMaxAmount(double l_dblPresentCreditTotalAmount, " +
            "double l_dblCreditAmount, double l_dblMaxTotalAmount)";
        log.entering(STR_METHOD_NAME);
     
        if(l_dblPresentCreditTotalAmount + l_dblCreditAmount > l_dblMaxTotalAmount)
        {
            log.debug("引数.現時点入金総額 + 引数.入金額 > 引数.上限総額場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00754,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.現時点入金総額[=" + l_dblPresentCreditTotalAmount + 
                "] + 引数.入金額[=" + l_dblCreditAmount + "] > 引数.上限総額[=" + l_dblMaxTotalAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate出金金額)<BR>
     * 出金金額のチェックを行う。<BR>
     * <BR>
     * １）　@入出金注文単位テーブルを検索し、既存注文の出金額の総計を計算する。 <BR>
     * 　@－入出金注文単位テーブルを検索し、入出金注文単位ParamsのListを取得する。<BR> 
     * 　@　@[検索条件]  <BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId()の戻り値  <BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId()の戻り値  <BR>
     * 　@　@　@注文種別 = 1001（出金注文）  <BR>
     * 　@　@　@銘柄タイプ = 5(現金） <BR>
     * 　@　@　@注文状態 = 1（受付済）  <BR>
     * 　@　@　@注文有効状態 = 1（オープン）  <BR>
     * 　@　@　@受渡日 = 引数.受渡日  <BR>
     * 　@　@　@出金申込区分 = null or mf(投信解約） <BR>
     * 　@　@　@振替タイプ = 2(出金） <BR>
     * <BR>
     * 　@－取得した入出金注文単位Paramsの注文数量（get注文数量()にて取得）<BR>
     *      の総計を計算する。 <BR>
     * <BR>
     * ２）　@１）で取得した注文数量の総計 + 引数.金額 を計算する。 <BR>
     * <BR>
     * ３）  ２）の計算結果 > 9桁の場合、例外をスローする。 <BR>
     * <BR>
     * ４）以下の条件で、会社別取引金額テーブルからレコードを取得する。 <BR>
     * <BR>
     *   [検索条件] <BR>
     *   証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 <BR>
     *   商品区分： "5"（出金） <BR>
     *   処理区分： "5"（その他） <BR>
     * <BR>
     *   レコードが取得できなかった場合は、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00755<BR>
     * <BR>
     * ５）所得したレコードの取引上限金額、取引下限金額について、<BR>
     *      以下の条件の場合例外をスローする。 <BR>
     * <BR>
     *   取引上限金額 < ２）の計算結果 or 取引下限金額 > ２）の計算結果<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00756<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_dblNetAmount - (金額)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412DD8B100E9
     */
    public void validatePaymentAmount(
        SubAccount l_subAccount, 
        double l_dblNetAmount, 
        Date l_datDeliveryDate) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validatePaymentAmount(" +
            "SubAccount, double, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null )
        {
            log.debug("補助口座がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        //１）　@入出金注文単位テーブルを検索し、既存注文の出金額の総計を計算する。 
        //－入出金注文単位テーブルを検索し、入出金注文単位ParamsのListを取得する。 
        //　@[検索条件]  
        //　@　@口座ID = 引数.補助口座.getAccountId()の戻り値  
        //　@　@補助口座ID = 引数.補助口座.getSubAccountId()の戻り値  
        //　@　@注文種別 = 1001（出金注文）  
        //　@　@銘柄タイプ = 8(入出金） 
        //　@　@注文状態 = 1（受付済）  
        //　@　@注文有効状態 = 1（オープン）  
        //　@　@受渡日 = 引数.受渡日  
        //　@　@出金申込区分 = null or mf(投信解約） 
        //　@　@振替タイプ = 2(出金） 
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("account_id = ? ");
        l_sbWhere.append("and sub_account_id = ? ");
        l_sbWhere.append("and order_type = ? ");
        l_sbWhere.append("and product_type = ? ");
        l_sbWhere.append("and order_status = ? ");
        l_sbWhere.append("and order_open_status = ? ");
        l_sbWhere.append("and delivery_date = ? ");
        l_sbWhere.append("and (payment_application_div is null ");
        l_sbWhere.append("or payment_application_div = ? )");
        l_sbWhere.append("and transfer_type = ? ");
        
        Object[] l_objAioWhere = new Object[] {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_OUT,
            ProductTypeEnum.CASH,
            OrderStatusEnum.ACCEPTED,
            OrderOpenStatusEnum.OPEN,
            l_datDeliveryDate,
            WEB3AioPaymentApplicationDivDef.MF,
            AssetTransferTypeEnum.CASH_OUT};
        
        List l_listAioOrderUnitRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_listAioOrderUnitRows = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objAioWhere);
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
        //－取得した入出金注文単位Paramsの注文数量（get注文数量()にて取得）の総計を計算する。 
        double l_dblQuantityTotal = 0;       
        if (l_listAioOrderUnitRows != null && l_listAioOrderUnitRows.size() > 0)
        {           
            for (int i = 0; i < l_listAioOrderUnitRows.size(); i ++)
            {                
                AioOrderUnitRow l_orderUnitRow = 
                    (AioOrderUnitRow)l_listAioOrderUnitRows.get(i);
                
                l_dblQuantityTotal += l_orderUnitRow.getQuantity();
            }
        }
        
        //２）１）で取得した注文数量の総計 + 引数.金額 を計算する。
        double l_dblCalcResult = 0; 
        l_dblCalcResult = l_dblQuantityTotal + l_dblNetAmount;
        log.debug("既存出金金額総計 + 引数.金額" + l_dblCalcResult);            
                  
        //３）  ２）の計算結果 > 9桁の場合、例外をスローする。
        if (WEB3StringTypeUtility.formatNumber(l_dblCalcResult).length() > 9)
        {
            log.debug("注文数量が上限数量を超えました。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00144,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が上限数量を超えました。");
        }      
        
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        try
        {
            //４）以下の条件で、会社別取引金額テーブルからレコードを取得する
            //[検索条件]
            //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
            //商品区分： "5"（出金）<BR>
            //処理区分： "5"（その他）<BR>
            AmountRangeRow l_amountRangeRow = 
                AmountRangeDao.findRowByInstitutionCodeFundTypeTransactionType(
                    l_strInstitutionCode, 
                    WEB3FundTypeDef.PAYMENT, 
                    WEB3TransactionTypeDef.DEFAULT);
            
            // レコードが取得できなかった場合は、例外をスローする。
            if(l_amountRangeRow == null)
            {
                log.debug("会社別取引金額テーブルレコードが取得できなかった場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00755,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "会社別取引金額テーブルレコードが取得できなかった");                
            }
            log.debug("AmountRangeRow::InstitutionCode = " + l_amountRangeRow.getInstitutionCode());
            
            //５）所得したレコードの取引上限金額、取引下限金額について、以下の条件の場合例外をスローする。 
            //取引上限金額 < ２）の計算結果 or 取引下限金額 > ２）の計算結果 

            long l_lngMaxAmount = l_amountRangeRow.getMaxAmount();
            long l_lngMinAmount = l_amountRangeRow.getMinAmount();
            if(l_lngMaxAmount < l_dblCalcResult || l_lngMinAmount > l_dblCalcResult)
            {
                log.debug("出金取引金額範囲外エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00756,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引上限金額[=" + l_lngMaxAmount + "] < 出金金額総計[=" + l_dblCalcResult + 
                    "] or 取引下限金額[=" + l_lngMinAmount + "] > 出金金額総計[=" + l_dblCalcResult + "]");
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
    
   /*
    * (validate出金金額)
    * 出金金額のチェックを行う。  <BR>
    * <BR>
    * １）以下の条件で、会社別取引金額テーブルからレコードを取得する。  <BR>
    * <BR>
    * [検索条件]  <BR>
    * 証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 <BR>
    * 商品区分： "5"（出金）  <BR>
    * 処理区分： "5"（その他）<BR>  
    * <BR>
    * レコードが取得できなかった場合は、例外をスローする。<BR>  
    * <BR>
    *         class: WEB3BusinessLayerException<BR>
    *         tag:   BUSINESS_ERROR_00755<BR>
    * <BR>
    * ２）所得したレコードの取引上限金額、取引下限金額について、<BR>
    * 以下の条件の場合例外をスローする。  <BR>
    * <BR>
    * 取引上限金額 < 引数.金額 or 取引下限金額 > 引数.金額 <BR>
    * <BR>
    *         class: WEB3BusinessLayerException<BR>
    *         tag:   BUSINESS_ERROR_00756<BR>
    * <BR>
    * @@param l_subAccount - (補助口座)
    * @@param l_dblNetAmount - (金額)<BR>
    * @@throws WEB3BaseException
    * @@roseuid 412DD8B100E9
    */
   
   public void validatePaymentAmount(SubAccount l_subAccount, double l_dblNetAmount) 
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "validatePaymentAmount(" +
           "SubAccount l_subAccount, double l_dblNetAmount) ";
       log.entering(STR_METHOD_NAME);
       
       if (l_subAccount == null )
       {
           log.debug("補助口座がNULL");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }       
       
       String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
       try
       {
           //１）以下の条件で、会社別取引金額テーブルからレコードを取得する
           //[検索条件]
           //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
           //商品区分： "5"（出金）<BR>
           //処理区分： "5"（その他）<BR>
           AmountRangeRow l_amountRangeRow = AmountRangeDao.findRowByInstitutionCodeFundTypeTransactionType(
               l_strInstitutionCode, 
               WEB3FundTypeDef.PAYMENT, 
               WEB3TransactionTypeDef.DEFAULT);
           
           if(l_amountRangeRow == null)
           {
               log.debug("会社別取引金額テーブルレコードが取得できなかった場合");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00755,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "会社別取引金額テーブルレコードが取得できなかった");                
           }
           
           //２）所得したレコードの取引上限金額、取引下限金額について、以下の条件の場合例外をスローする。
           //取引上限金額 < 引数.金額 or 取引下限金額 > 引数.金額
           long l_lngMaxAmount = l_amountRangeRow.getMaxAmount();
           long l_lngMinAmount = l_amountRangeRow.getMinAmount();
           if(l_lngMaxAmount < l_dblNetAmount || l_lngMinAmount > l_dblNetAmount)
           {
               log.debug("出金取引金額範囲外エラー");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00756,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "取引上限金額[=" + l_lngMaxAmount + "] < 引数.金額[=" + l_dblNetAmount + 
                   "] or 取引下限金額[=" + l_lngMinAmount + "] > 引数.金額[=" + l_dblNetAmount + "]");
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
    
    /**
     * (validate出金重複注文)<BR>
     * 引数の受渡日と同じ日にすでに出金注文が出てないかどうかをチェックする。<BR>
     * <BR>
     * ※重複が許されないのは、通常の出金注文のみ。<BR>
     * <BR>
     * １）以下の条件にて注文単位テーブルのレコードを取得する。<BR>
     * <BR>
     *    [検索条件]<BR>
     *    口座ID = 引数.補助口座.getAccountId()の戻り値<BR>
     *    補助口座ID = 引数.補助口座.getSubAccountId()の戻り値<BR>
     *    注文種別 = 1001（出金注文）<BR>
     *    注文状態 = 1（受付済） or 2（発注中） or 3（発注済）<BR>
     *    注文有効状態 = 1（オープン）<BR>
     *    受渡日 = 引数.受渡日<BR>
     *    出金申込区分 = null<BR>
     * <BR>
     * ２）取得したレコード件数 > 0 の場合は、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00757<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 注文の受渡日
     * @@throws WEB3BusinessLayerException
     * @@roseuid 412DD96601D3
     */
    public void validateCashoutDuplicateOrder(SubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCashoutDuplicateOrder(SubAccount l_subAccount, " +
            "Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null 
            || l_datDeliveryDate == null)
        {
            log.debug("補助口座/受渡日がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        try
        {
            //１）以下の条件にて注文単位テーブルのレコードを取得する
            long l_lngAccountId = l_subAccount.getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();   
            log.debug("SubAccount::AccountId = " + l_lngAccountId);
            log.debug("SubAccount::SubAccountId = " + l_lngSubAccountId);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("account_id = ? ");
            l_sbWhere.append("and sub_account_id = ? ");
            l_sbWhere.append("and order_type = ? ");
            l_sbWhere.append("and (order_status = ? or order_status = ? or order_status = ? ) ");
            l_sbWhere.append("and order_open_status = ? ");
            l_sbWhere.append("and delivery_date = ? ");
            l_sbWhere.append("and payment_application_div is null");
            
            Object[] l_objAioWhere = new Object[] {
                    String.valueOf(l_lngAccountId),
                    String.valueOf(l_lngSubAccountId),
                    String.valueOf(OrderTypeEnum.IntValues.CASH_OUT),
                    Integer.toString(OrderStatusEnum.IntValues.ACCEPTED),
                    Integer.toString(OrderStatusEnum.IntValues.ORDERING),
                    Integer.toString(OrderStatusEnum.IntValues.ORDERED),
                    String.valueOf(OrderOpenStatusEnum.IntValues.OPEN),
                    l_datDeliveryDate};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listFindAllQuery = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAioWhere);
            
            log.debug("AioOrderUnitRow.size() = " + l_listFindAllQuery.size());
            //２）取得したレコード件数 > 0 の場合は、例外をスローする
            if(l_listFindAllQuery.size() > 0)
            {
                log.debug("指定したお振込予定日と同じ振込日の出金申込がすでに登録されています。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00757,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取得したレコード件数[=" + l_listFindAllQuery.size() + "] > 0");                
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate先物取引口座開設)<BR>
     * 顧客が先物取引口座を開設しているかをチェックする。 <BR>
     * <BR>
     * １）顧客オブジェクト取得 <BR>
     *   補助口座.getMainAccount()にて顧客オブジェクトを取得する。 <BR>
     * <BR>
     * ２）口座開設区分チェック <BR>
     * <BR>
     *   以下の項目の何れかが”先物口座開設”または”先物OP口座開設”になっていること。 <BR>
     *   すべての項目が”DEFAULT（口座なし）”または”OP口座開設”であれば例外をスローする。 <BR>
     *     顧客.先物OP口座開設区分（東証） <BR>
     *     顧客.先物OP口座開設区分（大証） <BR>
     *     顧客.先物OP口座開設区分（名証） <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00284<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4134447B01F9
     */
    public void validateOpenFuturesTradedAccount(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOpenFuturesTradedAccount(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("補助口座がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）顧客オブジェクト取得
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        
        //２）口座開設区分チェック
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //先物OP口座開設区分（東証）
        String l_strIfoAccOpenDivTokyo =  l_mainAccountRow.getIfoAccOpenDivTokyo();

        //先物OP口座開設区分（大証）
        String l_strIfoAccOpenDivOsaka =  l_mainAccountRow.getIfoAccOpenDivOsaka();

        //先物OP口座開設区分（名証）
        String l_strIfoAccOpenDivNagoya =  l_mainAccountRow.getIfoAccOpenDivNagoya();
        
        boolean l_blnTokyo = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivTokyo) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo);
        
        boolean l_blnOsaka = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivOsaka) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka);

        boolean l_blnNagoya = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivNagoya) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya);
        log.debug("l_blnTokyo = " + l_blnTokyo + "  l_blnOsaka = " + l_blnOsaka + "  l_blnNagoya = " + l_blnNagoya);
        if(l_blnTokyo && l_blnOsaka && l_blnNagoya)
        {
            log.debug("お客様は先物口座を開設されておりません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客.先物OP口座開設区分（東証）と顧客.先物OP口座開設区分（大証）と" +
                "顧客.先物OP口座開設区分（名証）の項目が”DEFAULT（口座なし）”または”OP口座開設”");             
        }
    }
    /**
     * ()
     * 当クラスのインスタンスを登録するstaticメソッド。<BR>
     * <BR>
     * １） 入出金発注審査個別チェック.setInstance()をコールする。<BR>
     * 　@［setInstanceに渡すパラメタ］<BR>
     * 　@　@インスタンス： new 入出金発注審査個別チェック()<BR>
     * @@roseuid 40C6A9540350
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()";
        log.entering(STR_METHOD_NAME);
        
        //１） 入出金発注審査個別チェック.setInstance()をコールする
        WEB3AioProductTypeOrderManagerReusableValidations.setInstance(
            new WEB3AioProductTypeOrderManagerReusableValidations());
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateFX口座開設可能)
     * <BR>
     * 顧客がFX取引口座開設可能であるかチェックを行う。 <BR>
     * <BR>
     * １）FXデータ制御サービス.getFX顧客()をコールする。 <BR>
     * <BR>
     * 　@１－１）DB検索<BR>
     * 　@　@[引数の設定]<BR>
     * 　@　@　@証券会社コード： 引数.補助口座.証券会社コード<BR>
     * 　@　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode()<BR>
     * 　@　@　@FXシステムコード：引数.会社別FXシステム条件Params.FXシステムコード<BR>
     * 　@　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()<BR>
     * <BR>
     * 　@１－２）例外処理<BR>
     * 　@　@getFX顧客()でFX顧客Paramsが取得できた場合以下の条件で例外をthrowする。<BR>
     * <BR>
     * 　@　@　@１－２－１）下記条件にて例外をthrowする。<BR>
     * 　@　@　@＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合＞<BR>
     * 　@　@　@「CFD口座開設処理中エラー」として例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_03133<BR>
     * 　@　@　@＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）以外の場合＞<BR>
     * 　@　@　@「口座開設処理中エラー」として例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_02423<BR>
     * <BR>
     *  ２）<BR>
     * 　@　@引数.会社別FXシステム条件Params.オンライン口座開設実施区分<BR>
     * 　@　@== ０：オンライン口座開設未実施 の場合は、以下の処理を行う。<BR>
     * <BR>
     * 　@　@２-１）FXデータ制御サービス.getGFT口座開設状況()をコールする。<BR>
     * <BR>
     * 　@　@[引数の設定]<BR>
     * 　@　@　@検索条件文字列：" institution_code=? and branch_code=?<BR>
     * 　@　@　@　@and account_code=? and account_open_status_div in (?,?) "<BR>
     * 　@　@　@検索条件データコンテナ：（以下の要素の配列）<BR>
     * 　@　@　@　@　@　@引数.補助口座.証券会社コード<BR>
     * 　@　@　@　@　@　@引数.補助口座.get取引店().getBranchCode()<BR>
     * 　@　@　@　@　@　@引数.補助口座.getMainAccount().getAccountCode()<BR>
     * 　@　@　@　@　@　@口座開設状況区分."口座開設中"<BR>
     * 　@　@　@　@　@　@口座開設状況区分."ダウンロード済"<BR>
     * 　@　@　@ソート条件： null<BR>
     * <BR>
     * 　@　@２-２）例外のthrow<BR>
     * 　@　@　@２-１）にてレコードが取得できた場合以下の条件で例外をthrowする。<BR>
     * <BR>
     * 　@　@　@２－２-１）下記条件にて例外をthrowする。<BR>
     * <BR>
     * 　@　@　@＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合＞<BR>
     * 　@　@　@「CFD口座開設処理中エラー」として例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_03133<BR>
     * 　@　@　@＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）以外の場合＞<BR>
     * 　@　@　@「口座開設処理中エラー」として例外をthrowする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@: BUSINESS_ERROR_02423<BR>
     * <BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト <BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpenPossible(
            SubAccount l_subAccount, 
            CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFXAccOpenPossible(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_compFxConditionParams == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）FXデータ制御サービス.getFX顧客()をコールする。
        //１－１）DB検索
        //[引数の設定]
        //証券会社コード： 引数.補助口座.証券会社コード
        //部店コード：　@引数.補助口座.get取引店.getBranchCode()
        //FXシステムコード：引数.会社別FXシステム条件Params.FXシステムコード
        //顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            l_fxDataControlService.getFXAccount(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_compFxConditionParams.getFxSystemCode(),
                l_subAccount.getMainAccount().getAccountCode());

            //getFX顧客()でFX顧客Paramsが取得できた場合以下の条件で例外をthrowする。
            //引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
            //「CFD口座開設処理中エラー」として例外をthrowする。
            if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
            {
                log.debug("CFD口座開設処理中です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03133,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "CFD口座開設処理中です。");
            }
            //＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）以外の場合＞
            else
            {
                log.debug("現在、FX口座開設処理中です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02423,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "現在、FX口座開設処理中です。");
            }
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__" + l_ex.getMessage());
        }

        //２）引数.会社別FXシステム条件Params.オンライン口座開設実施区分
        //== ０：オンライン口座開設未実施 の場合は、以下の処理を行う。
        if (WEB3OnlineAccOpenDef.ONLINE_ACC_OPEN_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getOnlineAccOpen()))
        {
        	//２-１）FXデータ制御サービス.getGFT口座開設状況()をコールする。
        	//検索条件文字列：" institution_code=? and branch_code=?
        	//and account_code=? and account_open_status_div in (?,?) "
        	//検索条件データコンテナ：（以下の要素の配列）
        	//引数.補助口座.証券会社コード
        	//引数.補助口座.get取引店().getBranchCode()
            //引数.補助口座.getMainAccount().getAccountCode()
        	//口座開設状況区分."口座開設中"
        	//口座開設状況区分."ダウンロード済"
        	//ソート条件： null
        	String l_strWhere = " institution_code=? and branch_code=? "
                + "and account_code=? and account_open_status_div in (?,?) ";
        	String[] l_strQueryContainers = {
                l_subAccount.getInstitution().getInstitutionCode(),
        	    l_subAccount.getMainAccount().getBranch().getBranchCode(),
        	    l_subAccount.getMainAccount().getAccountCode(),
        	    WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING,
        	    WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE};

            GftAccountOpenStatusParams[] l_gftAccountOpenStatusParams =
                l_fxDataControlService.getGFTAccountOpenStatuses(
                    l_strWhere,
                    l_strQueryContainers,
                    null);

        	//例外のthrow
            //２-１）にてレコードが取得できた場合以下の条件で例外をthrowする。
            if (l_gftAccountOpenStatusParams.length > 0)
            {
            	//＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合＞
            	//「CFD口座開設処理中エラー」として例外をthrowする。
            	if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(
                    l_compFxConditionParams.getFxSystemDiv()))
            	{
                    log.debug("CFD口座開設処理中です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03133,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "CFD口座開設処理中です。");
            	}
                //＜引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）以外の場合＞
            	else
            	{
            	    //「口座開設処理中エラー」として例外をthrowする。
                    log.debug("現在、FX口座開設処理中です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02423,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "現在、FX口座開設処理中です。");
            	}
            }
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validateFX振替可能)
     * <BR>
     *顧客がFX振替取引可能であるかチェックを行う。<BR>
     *<BR>
     *１）FX口座開設チェック<BR>
     *<BR>
     *　@FXデータ制御サービス.getFX顧客()をコールする。<BR>
     *<BR>
     *　@[引数の設定]<BR>
     *　@　@証券会社コード： 引数.補助口座.証券会社コード<BR>
     *　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode()<BR>
     *　@　@FXシステムコード：　@引数.FXシステムコード<BR>
     *　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()<BR>
     *<BR>
     *　@getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、例外をthrowする。<BR>
     *<BR>
     *　@getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、<BR>
     *　@例外をthrowする。（BUSINESS_ERROR_02440）<BR>
     *<BR>
     *　@getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、<BR>
     *　@例外をthrowする。（BUSINESS_ERROR_01867）<BR>
     *<BR>
     *２）MRF口座開設チェック<BR>
     *<BR>
     *　@２)-１　@以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@　@部店ID = 補助口座.getBranch().部店ID <BR>
     *　@　@プリファ@レンス名 = "fx.deliverydate.insert.check" <BR>
     *　@　@プリファ@レンス名の連番 = 1 <BR>
     *<BR>
     *　@２)-２　@取得したプリファ@レンス値=="受渡日をセットしない"の場合、 <BR>
     *　@　@　@　@　@引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()をコールする。<BR>
     *<BR>
     *  戻り値 == true の場合は、２）－３の処理を行う。<BR>
     *  戻り値 == false の場合は、２）－３の処理をスキップする。<BR>
     *<BR>
     *　@２)－３拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。<BR>
     *<BR>
     *　@[引数の設定]<BR>
     *　@　@口座ID：　@引数.補助口座.getAccountId()<BR>
     *<BR>
     *　@顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、例外をthrowする。<BR>
     *<BR>
     *３）FX口座開設チェック<BR>
     *<BR>
     *  顧客.FX口座開設区分 != ”口座開設” の場合、例外をthrowする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト <BR>
     * @@param l_strFXSystemCode - FXシステムコード <BR>
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
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）FX口座開設チェック 
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            //NotFoundException
            FxAccountParams l_fxAccountParams = 
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_strFXSystemCode, 
                    l_subAccount.getMainAccount().getAccountCode());
            
            //getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、
            //例外をthrowする。（BUSINESS_ERROR_02440）
            if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("振替停止中エラー。");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "振替停止中エラー。");
            }
            
            //getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、
            //例外をthrowする。（BUSINESS_ERROR_01867）
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("為替保証金口座が取引不可の状態です。");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "為替保証金口座が取引不可の状態です。");
            }
            		
        }
        //getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、例外をthrowする。 
        catch (NotFoundException l_ex)
        {
            log.debug("為替保証金口座が未開設です。", l_ex);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替保証金口座が未開設です。");
        }        
        try
        {
            //拡張アカウントマネージャ取得する   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //２）MRF口座開設チェック 
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();   
            
            WEB3GentradeBranch l_genBranch = 
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

            //　@２)-１　@以下の条件で、部店用プリファ@レンステーブルからレコードを取得する
            //　@[条件]
            // 部店ID = 補助口座.getBranch().部店ID
            //　@プリファ@レンス名 = "fx.deliverydate.insert.check"
            //　@プリファ@レンス名の連番 = 1
            long l_lngBranchId = l_genBranch.getBranchId();

            BranchPreferencesRow l_branchReferencesRow = null;
            try
            {
                l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                    1);
            }
            catch (DataNetworkException l_dqex)
            {
                log.error("DBへのアクセスに失敗しました:", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);

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

            //２)-２　@取得したプリファ@レンス値=="受渡日をセットしない"の場合、 <BR>
            //　@　@   引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()をコールする。<BR>
            if (l_branchReferencesRow == null ||
                WEB3FxDeliveryDateInsertCheckDef.DEFAULT.equals(l_branchReferencesRow.getValue()))
            {

            // 戻り値 == true の場合は、２）－３の処理を行う。<BR>
            // 戻り値 == false の場合は、２）－３の処理をスキップする。<BR>

                if(l_genBranch.isFxMrfAccountOpenCheck())
                {           
                    //２)－３拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。 
                    //
                    //　@[引数の設定] 
                    //　@　@口座ID：　@引数.補助口座.getAccountId() 
                
                    //顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、
                    //例外をthrowする。

                    if (!WEB3AccountOpenDef.NOT_OPEN.equals(
                            l_mainAccountRow.getMrfAccOpenDiv()))
                    {
                        log.debug("MRF口座が開設済みです。");
                        log.exiting(STR_METHOD_NAME); 
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "MRF口座が開設済みです。");
                    }            
                }
            }
            
			//３）FX口座開設チェック
			//顧客.FX口座開設区分 != ”口座開設” の場合、例外をthrowする。            
			if (!WEB3AccountOpenDef.OPEN.equals(
					l_mainAccountRow.getFxAccOpenDiv()))
			{
				log.debug("為替保証金口座が未開設です。");
				log.exiting(STR_METHOD_NAME); 
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01866,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"為替保証金口座が未開設です。");
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
     * １）外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 　@ 証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 　@ 部店コード：　@引数.補助口座.get取引店.getBranchCode() <BR>
     * 　@ 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() <BR>
     * <BR>
     * get外国株式顧客()で外国株式顧客Paramsが取得できた場合(外株口座開設済)、例外をthrowする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * ２）get外国株式顧客()で外国株式顧客にデータが存在しない、且つ、 <BR>
     * UWG口座開設状況テーブルにデータが存在する場合(*1)、例外をthrowする。<BR> 
     * <BR>
     * (*1)以下のデータを除く <BR>
     * 送受信区分＝「３：受信エラー」 <BR>
     * 口座開設状況区分＝「２：口座開設エラー」又は「９：削除」 <BR>
     * <BR>
     * ２－１）検索条件文字列を作成する。 <BR>
     * ”institution_code = ? and branch_code = ? and account_code = ? ” <BR>
     * <BR>
     * ２－２）検索条件データコンテナを作成する。 <BR>
     * <BR>
     * ２－３）空のArrayListを生成する。 <BR>
     * <BR>
     * ２－４）引数.補助口座.証券会社コードを２－２－１）のListに追加する。 <BR>
     * <BR>
     * ２－５）引数.補助口座.get取引店.getBranchCode()を２－２－１）のListに追加する。<BR> 
     * <BR>
     * ２－６）引数.補助口座.getMainAccount().getAccountCode()を２－２－１）<BR>
     *       のListに追加する。<BR> 
     * <BR>
     * ２－７）Listから配列を取得する。 <BR>
     * <BR>
     * ２－８）外株振替連携データ制御サービスImpl.getUWG口座開設状況()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 　@ 検索条件文字列： ２－１）で作成した検索条件文字列 <BR>
     * 　@ 検索条件データコンテナ： ２－７）で取得した検索条件データコンテナ <BR>
     * 　@ ソート条件： null <BR>
     * <BR>
     * ２－９）取得したレコード数分Loop処理を実施する。 <BR>
     * <BR>
     *  送受信区分 != 「３：受信エラー」 and <BR>
     *  口座開設状況区分 != 「２：口座開設エラー」 and <BR>
     *  口座開設状況区分 != 「９：削除」 <BR>
     *  のデータが存在する場合(外株口座開設済)、例外をthrowする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * ３）拡張アカウントマネージャ.get顧客()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 　@ 証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 　@ 部店コード：　@引数.補助口座.get取引店.getBranchCode() <BR>
     * 　@ 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() <BR>
     * <BR>
     * ３－１）顧客.外国株式連携口座開設区分 == ”口座開設” の場合、例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * ３－２）顧客.外国証券口座開設区分 == ”未開設” の場合、例外をthrowする。 <BR>
     * <BR>
     * ３－３）顧客.emailアドレス == null の場合、例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01943<BR>
     * <BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@throws WEB3BaseException
     */
    public void validateFeqConAccOpenPossible(SubAccount l_subAccount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConAccountOpenPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 
        // [引数の設定] 
        // 証券会社コード： 引数.補助口座.証券会社コード 
        // 部店コード：　@引数.補助口座.get取引店.getBranchCode() 
        // 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
            
            if (l_feqAccountParams != null)
            {
                log.debug("外国株式口座は既に開設されています。");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外国株式口座は既に開設されています。");
            }
        }
        //get外国株式顧客()で外国株式顧客Paramsが取得できた場合(外株口座開設済)、例外をthrowする。
        catch (NotFoundException l_ex)
        {        
            log.debug("__NotFoundException__" + l_ex.getMessage());
        }
        
        //２）get外国株式顧客()で外国株式顧客にデータが存在しない、且つ、 
        //  UWG口座開設状況テーブルにデータが存在する場合(*1)、例外をthrowする。
        //  (*1)以下のデータを除く 
        //  送受信区分＝「３：受信エラー」 
        //  口座開設状況区分＝「２：口座開設エラー」又は「９：削除」 


        //２－１）検索条件文字列を作成する。 
        //”institution_code = ? and branch_code = ? and account_code = ? ”
        String l_strCondition = 
            "institution_code = ? and branch_code = ? and account_code = ? ";
        
        //２－２）検索条件データコンテナを作成する。
        
        //２－３）空のArrayListを生成する。
        List l_lisValue = new ArrayList();
        
        //２－４）引数.補助口座.証券会社コードを２－２－１）のListに追加する。 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();           
        l_lisValue.add(l_strInstitutionCode);
        
        //２－５）引数.補助口座.get取引店.getBranchCode()を２－２－１）のListに追加する。 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        l_lisValue.add(l_strBranchCode);
        
        //２－６）引数.補助口座.getMainAccount().getAccountCode()を２－２－１）のListに追加する。 
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        l_lisValue.add(l_strAccountCode);
        
        //２－７）Listから配列を取得する。 
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);        

        //２－８）外株振替連携データ制御サービスImpl.getUWG口座開設状況()をコールする。 
        //  [引数の設定] 
        //  検索条件文字列： ２－１）で作成した検索条件文字列 
        //  検索条件データコンテナ： ２－７）で取得した検索条件データコンテナ 
        //  ソート条件： null 
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_feqConTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                null);
        
        //２－９）取得したレコード数分Loop処理を実施する。 
        //  送受信区分 != 「３：受信エラー」 and 
        //  口座開設状況区分 != 「２：口座開設エラー」 and 
        //  口座開設状況区分 != 「９：削除」 
        //  のデータが存在する場合(外株口座開設済)、例外をthrowする。
        
        if (l_accountOpenStatusParams != null)
        {
            UwgAccountOpenStatusParams l_params = null;      
            for (int i = 0; i < l_accountOpenStatusParams.length; i++)
            {           
                l_params = l_accountOpenStatusParams[i];
                
                if (!WEB3SendRcvDivDef.RECEIVE_ERROR.equals(
                        l_params.getSendRcvDiv())
                    && !WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                        l_params.getAccountOpenStatusDiv())
                    && !WEB3AccountOpenStatusDivDef.DELETE.equals(
                        l_params.getAccountOpenStatusDiv()))
                {
                    log.debug("外国株式口座は既に開設されています。");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "外国株式口座は既に開設されています。");
                }
            }
        }

        //拡張アカウントマネージャ取得する   
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //３）拡張アカウントマネージャ.get顧客()をコールする。 
        // [引数の設定] 
        // 証券会社コード： 引数.補助口座.証券会社コード 
        // 部店コード：　@引数.補助口座.get取引店.getBranchCode() 
        // 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        //NotFoundException
        MainAccount l_mainAccount = 
            l_web3GentradeAccountManager.getMainAccount(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode());
       
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //３－１）顧客.外国株式連携口座開設区分 == ”口座開設” の場合、例外をthrowする。 
        if (WEB3AccountOpenDef.OPEN.equals(
                l_mainAccountRow.getFeqConAccOpenDiv()))
        {
            log.debug("顧客.外国株式連携口座開設区分 == ”口座開設” の場合。");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外国株式口座は既に開設されています。");
        }
		//３－２）顧客.外国証券口座開設区分 == ”未開設” の場合、例外をthrowする。 
		if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
				l_mainAccountRow.getForeignSecAccOpenDiv()))
		{
			log.debug("顧客.外国証券口座開設区分 == ”未開設” の場合。");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01341,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"当該顧客は外国証券口座開設なし。");
		}
        //３－３）顧客.emailアドレス == null の場合、例外をthrowする。 
        if (l_mainAccountRow.getEmailAddress() == null)
        {
            log.debug("顧客のemailアドレスが未指定です。");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客のemailアドレスが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate外株振替可能)
     * <BR>
     * 顧客が外株振替取引可能であるかチェックを行う。 <BR>
     * <BR>
     * １）外株口座開設チェック <BR>
     * <BR>
     * 　@外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 <BR>
     * <BR>
     * 　@[引数の設定] <BR>
     * 　@　@証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode() <BR>
     * 　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() <BR>
     * <BR>
     * 　@get外国株式顧客()で外国株式顧客Paramsが取得できなかった場合(外株口座未開設）、<BR>
     *          例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * 　@get外国株式顧客()で取得した外国株式顧客Params.外国株式口座区分 != ”開設済”の場合、<BR>
     *          例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ２）外国証券口座開設チェック <BR>
     * <BR>
     * 　@拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。 <BR>
     * <BR>
     * 　@[引数の設定] <BR>
     * 　@　@口座ID：　@引数.補助口座.getAccountId() <BR>
     * <BR>
     * 顧客.外国証券口座開設区分 == ”未開設” の場合、例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * ３）外国株式連携口座開設チェック <BR>
     * <BR>
     * 顧客.外国株式連携口座開設区分 != ”口座開設” の場合、例外をthrowする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
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
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）外株口座開設チェック 
        //　@外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 
        //  [引数の設定] 
        //　@　@証券会社コード： 引数.補助口座.証券会社コード 
        //　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode() 
        //　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);  
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        //get外国株式顧客()で外国株式顧客Paramsが取得できなかった場合(外株口座未開設）、例外をthrowする。
        catch(NotFoundException l_ex)
        {        
            log.debug("外株口座が未開設です。", l_ex);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外株口座が未開設です。");
        }
        //get外国株式顧客()で取得した外国株式顧客Params.外国株式口座区分 != ”開設済”の場合、
        //例外をthrowする。
        if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                l_feqAccountParams.getAccountOpenDiv()))
        {
            log.debug("外株口座が未開設です。");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外株口座が未開設です。");
        }
        
        MainAccount l_mainAccount = null;
        try
        {
            //拡張アカウントマネージャ取得する   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。 
            //[引数の設定] 
            //口座ID：　@引数.補助口座.getAccountId()   
            //NotFoundException
            l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
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
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
		//２）外国証券口座開設チェック 
			  //顧客.外国証券口座開設区分 == ”未開設” の場合、例外をthrowする。
		if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
				l_mainAccountRow.getForeignSecAccOpenDiv()))
		{
			log.debug("当該顧客は外国証券口座開設なし。");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01341,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"当該顧客は外国証券口座開設なし。");
		}
            
        //３）外国株式連携口座開設チェック 
        //顧客.外国株式連携口座開設区分 != ”口座開設” の場合、例外をthrowする。
        if (!WEB3AccountOpenDef.OPEN.equals(
                l_mainAccountRow.getFeqConAccOpenDiv()))
        {
			log.debug("外株口座が未開設です。");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01944,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"外株口座が未開設です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validate外株初回振替)
     * <BR>
     * 外株振替が初回の場合のチェックを行う。 <BR>
     * <BR>
     * １）外株顧客行オブジェクトの取得 <BR>
     * <BR>
     * 外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 <BR>
     * <BR>
     * [引数の設定] <BR>
     * 証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 部店コード：　@引数.補助口座.get取引店.getBranchCode() <BR>
     * 顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() <BR>
     * <BR>
     * ２）初回振替のチェック <BR>
     * <BR>
     * １）で取得した外国株式顧客Params.初回振替フラグ == ”未実施” and <BR>
     * 引数.振替金額 < 50000 <BR>
     * <BR>
     * の場合、例外をスローする。  <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01946<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_dblTransferAmount - (振替金額)
     * @@throws WEB3BaseException
     */
    public void validateFeqConFirstTransfer(
            SubAccount l_subAccount, double l_dblTransferAmount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConFirstTransfer(" +
                "SubAccount l_subAccount, double l_dblTransferAmount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）外株顧客行オブジェクトの取得 
        //外株振替連携データ制御サービスImpl.get外国株式顧客()をコールする。 
        //[引数の設定] 
        //証券会社コード： 引数.補助口座.証券会社コード 
        //部店コード：　@引数.補助口座.get取引店.getBranchCode() 
        //顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）初回振替のチェック 
        //  １）で取得した外国株式顧客Params.初回振替フラグ == ”未実施” and 
        //   引数.振替金額 < 50000 の場合、例外をスローする。 
        if (WEB3FeqFirstTransferFlagDef.NOT_TRANSFER.equals(
                l_feqAccountParams.getFirstTransferFlag())
            && (l_dblTransferAmount < 50000))
        {
            log.debug("外国株式顧客Params.初回振替フラグ == ”未実施” and " +
                    "引数.振替金額 < 50000 の場合");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01946,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "初回振替の場合、振替金額が下限金額に達していません。");
        }    
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateFX口座開設)
     * <BR>
     * 顧客がFX取引口座開設済であるかチェックを行う。<BR> 
     * <BR>
     * １）FX顧客を取得する<BR> 
     * <BR>
     * FXデータマネージャ.getFX顧客()をコールする。<BR> 
     * <BR>
     * [引数] <BR>
     * 　@証券会社コード： 引数.補助口座.証券会社コード <BR>
     * 　@部店コード：　@引数.補助口座.get取引店.getBranchCode()<BR> 
     * 　@FXシステムコード：　@引数.FXシステムコード <BR>
     * 　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()<BR> 
     * <BR>
     * getFX顧客()でFX顧客Paramsが取得出来ない場合(FX口座未開設)、<BR>
     * 例外をthrowする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_01866<BR>
     * <BR>
     * ２）１）で取得したFX顧客.FX口座区分==1:開設済でない場合(FX口座未開設）、<BR>
     * 例外をthrowする。 <BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_01866<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト <BR>
     * @@param l_strFXSystemCode - FXシステムコード <BR>
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpen(
        SubAccount l_subAccount, 
        String l_strFXSystemCode)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpen(" +
            "SubAccount l_subAccount, String l_strFXSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）FX顧客を取得する 
        //FXデータマネージャ.getFX顧客()をコールする。 
        //[引数] 
        //証券会社コード： 引数.補助口座.証券会社コード 
        //部店コード：　@引数.補助口座.get取引店.getBranchCode() 
        //FXシステムコード：　@引数.FXシステムコード 
        //顧客コード：　@引数.補助口座.getMainAccount().getAccountCode() 
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        try
        {
            FxAccountParams l_fxAccountParams = 
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strFXSystemCode,
                    l_subAccount.getMainAccount().getAccountCode());
            
            //getFX顧客()でFX顧客Paramsが取得出来ない場合(FX口座未開設)、
            //例外をthrowする。 
            if (l_fxAccountParams == null)
            {
                log.debug("getFX顧客()でFX顧客Paramsが取得出来ない");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
					"getFX顧客()でFX顧客Paramsが取得出来ない");                
            }
            
            //２）１）で取得したFX顧客.FX口座区分==1:開設済でない場合(FX口座未開設）、
            //例外をthrowする。 
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("取得したFX顧客.FX口座区分==1:開設済でない");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
					"取得したFX顧客.FX口座区分==1:開設済でない");   
            }            
        }
        catch (NotFoundException l_nfex)
        {
            log.debug("getFX顧客()でFX顧客Paramsが取得出来ない", l_nfex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
				"getFX顧客()でFX顧客Paramsが取得出来ない");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (validateCFD振替可能)<BR>
     * 顧客がCFD振替取引可能であるかチェックを行う。<BR>
     * <BR>
     * １）CFD口座開設チェック<BR>
     * <BR>
     * 　@FXデータ制御サービス.getFX顧客()をコールする。<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@　@証券会社コード： 引数.補助口座.証券会社コード<BR>
     * 　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode()<BR>
     * 　@　@FXシステムコード：　@引数.FXシステムコード<BR>
     * 　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()<BR>
     * <BR>
     * 　@getFX顧客()でFX顧客Paramsが取得できなかった場合(CFD口座未開設）、例外をthrowする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_01866<BR>
     * <BR>
     * 　@getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、<BR>
     * 　@例外をthrowする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_02440<BR>
     * <BR>
     * 　@getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、<BR>
     * 　@例外をthrowする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_01867<BR>
     * <BR>
     * ２）MRF口座開設チェック<BR>
     * <BR>
     * 　@２)-１　@以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().部店ID<BR>
     * 　@　@プリファ@レンス名 = "fx.deliverydate.insert.check"<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@２)-２　@取得したプリファ@レンス値=="受渡日をセットしない"の場合、<BR>
     * 　@　@　@　@　@引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()をコールする。<BR>
     * <BR>
     * 戻り値 == true の場合は、２）－３の処理を行う。<BR>
     * 戻り値 == false の場合は、２）－３の処理をスキップする。<BR>
     * <BR>
     * 　@２）－３拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@　@口座ID：　@引数.補助口座.getAccountId()<BR>
     * <BR>
     * 　@顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、例外をthrowする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_01868<BR>
     * <BR>
     * ３）CFD口座開設チェック<BR>
     * <BR>
     * 顧客.CFD口座開設区分 != ”口座開設” の場合、例外をthrowする。<BR>
     * 　@　@　@　@class：　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@　@BUSINESS_ERROR_01866<BR>
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

        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）CFD口座開設チェック
        //FXデータマネージャ.getFX顧客()をコールする。
        //[引数の設定]
        //証券会社コード： 引数.補助口座.証券会社コード
        //部店コード：　@引数.補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@引数.FXシステムコード
        //顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        try
        {
            FxAccountParams l_fxAccountParams =
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strFxSystemCode,
                    l_subAccount.getMainAccount().getAccountCode());

            //getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、
            //例外をthrowする。
            if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(
                l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("振替停止中エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "振替停止中エラー。");
            }

            //getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、
            //例外をthrowする。
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("為替保証金口座が取引不可の状態です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "為替保証金口座が取引不可の状態です。");
            }
        }
        //getFX顧客()でFX顧客Paramsが取得できなかった場合(CFD口座未開設）、例外をthrowする。
        catch (NotFoundException l_ex)
        {
            log.error("為替保証金口座が未開設です。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替保証金口座が未開設です。");
        }

        try
        {
            //拡張アカウントマネージャ取得する
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            WEB3GentradeBranch l_genBranch =
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

            //２）MRF口座開設チェック
            //２)-１　@以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。
            //[条件]
            //部店ID = 補助口座.getBranch().部店ID
            //プリファ@レンス名 = "fx.deliverydate.insert.check"
            //プリファ@レンス名の連番 = 1
            long l_lngBranchId = l_genBranch.getBranchId();
            BranchPreferencesRow l_branchReferencesRow = null;

            MainAccount l_mainAccount =
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            try
            {
                l_branchReferencesRow =
                	BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
	                    l_lngBranchId,
	                    WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
	                    Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO));
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
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

            //２)-２　@取得したプリファ@レンス値=="受渡日をセットしない"の場合、
            //引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()をコールする。
            if (l_branchReferencesRow == null
                || WEB3FxDeliveryDateInsertCheckDef.DEFAULT.equals(
                    l_branchReferencesRow.getValue()))
            {
                //戻り値 == true の場合は、２）－３の処理を行う。
                //戻り値 == false の場合は、２）－３の処理をスキップする。
                if (l_genBranch.isFxMrfAccountOpenCheck())
                {
                    //２）－３拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。
                    //[引数の設定]
                    //口座ID：　@引数.補助口座.getAccountId()

                    //顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、
                    //例外をthrowする。
                    if (!WEB3MrfAccOpenDivDef.DEFAULT.equals(
                        l_mainAccountRow.getMrfAccOpenDiv()))
                    {
                        log.debug("MRF口座が開設済みです。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "MRF口座が開設済みです。");
                    }
                }
            }

            //３）CFD口座開設チェック
            //顧客.CFD口座開設区分 != ”口座開設” の場合、例外をthrowする。
            if (!WEB3CfdAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getCfdAccOpenDiv()))
            {
                log.debug("為替保証金口座が未開設です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "為替保証金口座が未開設です。");
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
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
     * (validate振替取引可能)<BR>
     * 顧客が振替取引可能であるかチェックを行う。<BR>
     * <BR>
     * １）口座開設チェック<BR>
     * 　@　@1-1) FXデータ制御サービス.getFX顧客()をコールする。<BR>
     * 　@　@　@引数の設定]<BR>
     * 　@　@　@証券会社コード： 引数.補助口座.証券会社コード<BR>
     * 　@　@　@部店コード：　@引数.補助口座.get取引店.getBranchCode()<BR>
     * 　@　@　@FXシステムコード：　@引数.FXシステムコード<BR>
     * 　@　@　@顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()<BR>
     * <BR>
     * 　@　@　@getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_01866<BR>
     * <BR>
     * 　@　@　@getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_02440<BR>
     * <BR>
     * 　@　@　@getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_01867<BR>
     * <BR>
     * ２）MRF口座開設チェック<BR>
     * 　@　@2-1）引数.会社別FXシステム条件Params.MRF口座許可区分 = "０：不可"の場合<BR>
     * 　@　@　@拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。<BR>
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@口座ID：　@引数.補助口座.getAccountId()<BR>
     * <BR>
     * 　@　@　@顧客.MRF口座開設区分 ！= ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_01868<BR>
     * <BR>
     * ３）口座開設区分チェック<BR>
     * 　@　@3-1) 口座開設区分オブジェクトを生成。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@口座ID = 引数.補助口座.口座ＩＤ<BR>
     * 　@　@　@口座種別 = 会社別FXシステム条件テーブル.口座種別<BR>
     * <BR>
     * 　@　@3-2) 口座開設区分.get口座開設区分()をコールする。<BR>
     * <BR>
     * 　@　@　@get口座開設区分()戻り値 ！= ”1:開設済” の場合、例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_01866<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
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

        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

        //口座開設チェック
        //FXデータ制御サービス.getFX顧客()をコールする。
        // 引数の設定]
        //  証券会社コード： 引数.補助口座.証券会社コード
        //  部店コード：　@引数.補助口座.get取引店.getBranchCode()
        //  FXシステムコード：　@引数.FXシステムコード
        //  顧客コード：　@引数.補助口座.getMainAccount().getAccountCode()
        FxAccountParams l_fxAccountParams = null;
        try
        {
            MainAccount l_mainAccountFromSubAccount = l_subAccount.getMainAccount();
            l_fxAccountParams =
                l_fXDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_mainAccountFromSubAccount.getBranch().getBranchCode(),
                    l_compFxConditionParams.getFxSystemCode(),
                    l_mainAccountFromSubAccount.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            l_fxAccountParams = null;
        }

        //getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、
        // 例外をthrowする。
        if (l_fxAccountParams == null)
        {
            log.debug("為替保証金口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替保証金口座が未開設です。");
        }

        String l_strFxAccountDiv = l_fxAccountParams.getFxAccountDiv();
        //getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、
        // 例外をthrowする。
        if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_strFxAccountDiv))
        {
            log.debug("振替停止中エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替停止中エラー。");
        }

        //getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、
        // 例外をthrowする。 
        if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_strFxAccountDiv))
        {
            log.debug("為替保証金口座が取引不可の状態です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替保証金口座が取引不可の状態です。");
        }

        //拡張アカウントマネージャ取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //MRF口座開設チェック
        // 引数.会社別FXシステム条件Params.MRF口座許可区分 = "０：不可"の場合
        if (WEB3MrfAllowDivDef.DISABLED.equals(l_compFxConditionParams.getMrfAllowDiv()))
        {
            //拡張アカウントマネージャ.get顧客()により顧客オブジェクトを取得する。
            //[引数の設定]
            // 口座ID：　@引数.補助口座.getAccountId()
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    l_gentradeAccountManager.getMainAccount(
                        l_subAccount.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            //顧客.MRF口座開設区分 ！= ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、
            // 例外をthrowする。
            if (!WEB3MrfAccOpenDivDef.DEFAULT.equals(
                l_mainAccountRow.getMrfAccOpenDiv()))
            {
                log.debug("MRF口座が開設済みです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MRF口座が開設済みです。");
            }
        }

        //口座開設区分チェック
        //口座開設区分オブジェクトを生成。
        //[引数]
        // 口座ID = 引数.補助口座.口座ＩＤ
        // 口座種別 = 会社別FXシステム条件テーブル.口座種別
        WEB3GentradeAccOpenDiv l_gentradeAccOpenDiv =
            new WEB3GentradeAccOpenDiv(
                l_subAccount.getAccountId(),
                l_compFxConditionParams.getAccType());

        //口座開設区分.get口座開設区分()をコールする。
        String l_strAccOpenDiv = l_gentradeAccOpenDiv.getAccOpenDiv();

        //get口座開設区分()戻り値 ！= ”1:開設済” の場合、例外をthrowする。
        if (!WEB3AccountOpenDef.OPEN.equals(l_strAccOpenDiv))
        {
            log.debug("為替保証金口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "為替保証金口座が未開設です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
