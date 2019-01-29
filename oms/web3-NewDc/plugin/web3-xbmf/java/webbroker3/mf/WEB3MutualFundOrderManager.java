head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信注文マネージャクラス(WEB3MutualFundOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 韋念瓊 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2005/10/18 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/03/08 玉岡 (SRA) 仕様変更(モデル)：400
Revesion History : 2006/06/19 鈴木 (SRA) 本番障害H00126対応                        
Revesion History : 2006/06/26 周捷 (中訊) 仕様変更(モデル)：419  
Revesion History : 2006/09/11 周捷 仕様変更・モデル488
Revesion History : 2007/01/05 唐性峰 (中訊)　@モデル530
Revesion History : 2007/04/09 趙林鵬 (中訊) モデル 554,561
Revesion History : 2007/10/15 孫洪江 (中訊) モデル 581
*/

package webbroker3.mf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrder;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3ExemptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.data.MfExemptionAccountDao;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信注文マネージャクラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManager extends MutualFundOrderManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundOrderManager.class);
    /**
     * (validate新規注文)<BR>
     * （validateNewOrderのオーバーロード）<BR>
     * <BR>
     * 投信買付・解約注文の発注審査を行う。<BR>
     * <BR>
     * １）　@引数.処理区分の値が”1：買付”の場合 <BR>
     * 　@－validate新規注文（買付）()をコールする。 <BR>
     * 　@　@［validate新規注文（買付）に渡すパラメタ］ <BR>
     * 　@　@　@補助口座： 引数.補助口座 <BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 <BR>
     * 　@　@　@注文数量： 引数.注文数量 <BR>
     *       処理区分： 引数.処理区<BR>
     * 　@　@　@指定方法@： 引数.指定方法@<BR>
     * 　@　@　@決済方法@： 引数.決済方法@<BR>
     * 　@－validate新規注文（買付）()が正常終了した場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT <BR>
     * 　@－validate新規注文（買付）()が例外をスローした場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： 例外.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * ２）　@引数.処理区分の値が”2：解約”または”4：買取”で引数.乗換先銘柄がnullの場合 <BR>
     * 　@－validate新規注文（解約）()をコールする。 <BR>
     * 　@　@［validate新規注文（解約）に渡すパラメタ］ <BR>
     * 　@　@　@補助口座： 引数.補助口座 <BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 <BR>
     * 　@　@　@注文数量： 引数.注文数量 <BR>
     * 　@　@　@処理区分： 引数.処理区分<BR>
     * 　@　@　@受渡方法@： 引数.受渡方法@ <BR>
     * 　@　@　@指定方法@： 引数.指定方法@ <BR>
     *       税区分： 引数.税区分<BR>
     * 　@　@　@決済方法@： 引数.決済方法@<BR>
     * 　@－validate新規注文（解約）()が正常終了した場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT <BR>
     * 　@－validate新規注文（解約）()が例外をスローした場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： 例外.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * ３）　@引数.処理区分の値が”2：解約”または”4：買取”で引数.乗換先銘柄がnullでない場合 <BR>
     * 　@－validate新規注文（乗換）()をコールする。 <BR>
     * 　@　@［validate新規注文（乗換）に渡すパラメタ］ <BR>
     * 　@　@　@補助口座： 引数.補助口座 <BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 <BR>
     * 　@　@　@注文数量： 引数.注文数量 <BR>
     * 　@　@　@処理区分： 引数.処理区分<BR>
     * 　@　@　@指定方法@： 引数.指定方法@<BR>
     * 　@　@　@乗換先銘柄： 引数.乗換先銘柄<BR>
     *       税区分： 引数.税区分 <BR>
     * 　@－validate新規注文（乗換）()が正常終了した場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT <BR>
     * 　@－validate新規注文（乗換）()が例外をスローした場合は、 <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。 <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@発注審査結果： 例外.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * ４）　@引数.処理区分の値が”5：募集”の場合 <BR>
     * 　@－validate新規注文（募集）()をコールする。  <BR>
     * 　@　@［validate新規注文（募集）に渡すパラメタ］  <BR>
     * 　@　@　@補助口座： 引数.補助口座  <BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄  <BR>
     * 　@　@　@注文数量： 引数.注文数量  <BR>
     *       処理区分： 引数.処理区分 <BR>
     * 　@　@　@指定方法@： 引数.指定方法@ <BR>
     * 　@－validate新規注文（募集）()が正常終了した場合は、  <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。  <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］  <BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT  <BR>
     * 　@－validate新規注文（募集）()が例外をスローした場合は、  <BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。  <BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］  <BR>
     * 　@　@　@　@発注審査結果： 例外.getValidationResult().getProcessingResult()  <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_web3MutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取 5：募集 <BR>
     * @@param l_strPaymentMethod - (受渡方法@)<BR>
     * 1：銀行振込み　@2：証券口座入金<BR>
     * <BR>
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 2：全部　@3：金額　@4：口数<BR>
     * @@param l_switchingSubject - (乗換先銘柄)<BR>
     * 乗換先の拡張投信銘柄オブジェクト<BR>
     * @@param l_taxType - (税区分) <BR>
     * <BR>
     * @@param l_strSettleDiv - (決済方法@)<BR>
     * 1:円貨 2:外貨<BR>
     * <BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40B15E2F00B2
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubject,
        TaxTypeEnum l_taxType,
        String l_strSettleDiv)
    {
        String STR_METHOD_NAME = "validateNewOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //発注審査結果
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            log.debug("引数.処理区分 = " + l_strProcessDiv);
            //１）引数.処理区分の値が”1：買付”の場合
            if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
            {
                log.debug("引数.処理区分の値が”1：買付”の場合");
                //validate新規注文（買付）()をコールする
                this.validateBuyNewOrder(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_dblOrderQuantity,
                    l_strProcessDiv,
                    l_strDesignateMethod,
                    l_strSettleDiv);
                //validate新規注文（買付）()が正常終了した場合
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("（買付）発注審査結果：" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            //２）引数.処理区分の値が”2：解約”または”4：買取”で
            //    引数.乗換先銘柄がnullの場合 
            else if ((WEB3ProcessDivDef.SELL.equals(l_strProcessDiv)
                    || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
                    && l_switchingSubject == null)

            {
                log.debug("引数.処理区分=2：解約”||”4：買取”&& 引数.乗換先銘柄=null");
                //validate新規注文（解約）()をコールする
                this.validateSellNewOrder(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_dblOrderQuantity,
                    l_strProcessDiv,
                    l_strPaymentMethod,
                    l_strDesignateMethod,
                    l_taxType,
                    l_strSettleDiv);
                //validate新規注文（解約）()が正常終了した場合
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("（解約）発注審査結果：" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            //３）引数.処理区分の値が”2：解約”または”4：買取”で
            //    引数.乗換先銘柄がnullでない場合
            else if ((WEB3ProcessDivDef.SELL.equals(l_strProcessDiv)
                    || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
                    && l_switchingSubject != null)
            {
                log.debug("引数.処理区分=2：解約”||”4：買取”&& 引数.乗換先銘柄 !=null");
                //－validate新規注文（乗換）()をコールする。  
                //　@［validate新規注文（乗換）に渡すパラメタ］  
                //    補助口座： 引数.補助口座  
                //　@  拡張投信銘柄： 引数.拡張投信銘柄  
                //　@　@注文数量： 引数.注文数量  
                //　@　@処理区分： 引数.処理区分 
                //　@　@指定方法@： 引数.指定方法@ 
                //　@　@乗換先銘柄： 引数.乗換先銘柄 
                //　@　@税区分： 引数.税区分 
                this.validateSwitchingNewOrder(
                    l_subAccount, 
                    l_web3MutualFundProduct, 
                    l_dblOrderQuantity, 
                    l_strProcessDiv, 
                    l_strDesignateMethod, 
                    l_switchingSubject, 
                    l_taxType);
                
                //validate新規注文（乗換）()が正常終了した場合
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("（乗換）発注審査結果：" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            
            //４）　@引数.処理区分の値が”5：募集”の場合
            else if (WEB3ProcessDivDef.RECRUIT.equals(l_strProcessDiv))                
            {
                //－validate新規注文（募集）()をコールする。   
                //補助口座： 引数.補助口座  
                //拡張投信銘柄： 引数.拡張投信銘柄  
                //注文数量： 引数.注文数量
                //指定方法@： 引数.指定方法@
                //処理区分： 引数.処理区分
                
                this.validateRecruitNewOrder(
                    l_subAccount, 
                    l_web3MutualFundProduct, 
                    l_dblOrderQuantity, 
                    l_strDesignateMethod, 
                    l_strProcessDiv
                    );
                
                //－validate新規注文（募集）()が正常終了した場合は、  
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("（募集）発注審査結果：" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());                
            }
        }
        catch (OrderValidationException l_ex)
        {
            //validate新規注文が例外をスローした場合
            log.debug("validate新規注文が例外をスローした場合");
            l_newOrderValidationResult =
                new NewOrderValidationResult(l_ex.getValidationResult().getProcessingResult());
            
            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate新規注文");
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * (validate取消注文)<BR>
     * （validateCancelOrderの実装）<BR>
     * <BR>
     * 投信取消の発注審査を行う。<BR>
     * <BR>
     * １）　@this.getOrder()をコールし、投信注文オブジェクトを取得する。<BR>
     * 　@［getOrderに渡すパラメタ］<BR>
     * 　@　@注文ID： 引数.投信取消注文内容.getOrderId()の戻り値<BR>
     * <BR>
     * ２）　@取得した投信注文オブジェクト.getOrderUniｔs()をコールし、<BR>
     * 投信注文単位オブジェクトの配列を取得する。<BR>
     * <BR>
     * ３）　@指定された注文が取消可能かチェックする。<BR>
     * 　@－投信発注審査個別チェック.validate取消可能()をコールする。<BR>
     * 　@　@［validate取消可能に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数の補助口座<BR>
     * 　@　@　@投信注文単位： 取得した投信注文単位オブジェクトの配列[0]<BR>
     * <BR>
     * 　@－validate取消可能()が例外をスローした場合は、<BR>
     * NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@発注審査結果： 例外.getValidationResult().getProcessingResult()<BR>
     * <BR>
     * ４）　@NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT <BR>
     * @@param l_subAccount - 補助口座ID<BR>
     * @@param l_mutualCancelOrderSpec - 投信取消注文内容<BR>
     * @@return OrderValidationResult
     * @@roseuid 40B15E2F00B9
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_mutualCancelOrderSpec)
    {
        String STR_METHOD_NAME = "validateCancelOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mutualCancelOrderSpec == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        //発注審査結果
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {            
            //１）this.getOrder()をコールし、投信注文オブジェクトを取得する
            MutualFundOrder l_mfOrder = null;
            l_mfOrder = (MutualFundOrder) this.getOrder(l_mutualCancelOrderSpec.getOrderId());

            //２）取得した投信注文オブジェクト.getOrderUnits()をコールし、
            //投信注文単位オブジェクトの配列を取得する            
            OrderUnit l_orderUnit[] = (OrderUnit[]) l_mfOrder.getOrderUnits();
            MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit) l_orderUnit[0];

            log.debug("取得した投信注文単位.orderUnitId = " + l_mfOrderUnit.getOrderUnitId());
            //３）指定された注文が取消可能かチェックする
            //投信発注審査個別チェック.validate取消可能()をコールする
            l_validationsCheck.validateCancelPossible(l_subAccount, l_mfOrderUnit);

            //４）NewOrderValidationResultオブジェクトを生成して返す
            l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in 注文オブジェクトを取得");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));            
        }        
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in validate取消可能()");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * validate新規注文（買付）<BR>
     * <BR>
     * 投信買付注文の発注審査を行う。<BR>
     * <BR>
     * １）　@買付数量チェック<BR>
     * 　@１－１）　@引数.注文数量 ≧ 10000000000 の場合例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     * 　@１－２）　@新規買付か追加買付かの判定<BR>
     * 　@　@当買付注文が新規買付か追加買付かの判断を行う。<BR>
     * 　@　@－拡張投信ポジションマネージャ.getAsset()をコールし、保有資産オブジェクト<BR>
     * 　@　@　@を取得する。<BR>
     * 　@　@［getAssetに渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座オブジェクト<BR>
     * 　@　@　@銘柄：　@引数.拡張投信銘柄オブジェクト<BR>
     * 　@　@－getAsset()がNotFoundExceptionをスローした場合は新規注文、そうでない場合は<BR>
     * 　@　@　@追加注文と判断する<BR>
     * <BR>
     * 　@１－３）　@新規注文の場合は以下の処理を行う。<BR>
     * 　@　@－買付最低数量を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get最低金額<BR>
     * （新規注文）()の戻り値を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get最低口数<BR>
     * （新規注文）()の戻り値を取得する。<BR>
     * 　@　@－引数.注文数量 ＜ 買付最低数量の場合は例外をスローする。<BR>
     * 　@　@　@（買付最低数量エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00360<BR>
     * 　@　@－買付単位数量を取得する<BR>
     * 　@　@　@(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get単位金額<BR>
     * （新規注文）()の戻り値を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get単位口数<BR>
     * （新規注文）()の戻り値を取得する。<BR>
     * 　@　@－引数.注文数量が買付単位数量で割り切れない場合は例外をスローする。<BR>
     * 　@　@　@（買付単位数量エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00361<BR>
     * <BR>
     * 　@１－４）　@追加注文の場合は以下の処理を行う。<BR>
     * 　@　@－買付最低数量を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get最低金額<BR>
     * （追加注文）()の戻り値を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get最低口数<BR>
     * （追加注文）()の戻り値を取得する。<BR>
     * 　@　@－引数.注文数量 ＜ 買付最低数量の場合は例外をスローする。<BR>
     * 　@　@　@＊買付最低数量エラー<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00360<BR>
     * 　@　@－買付単位数量を取得する<BR>
     * 　@　@　@(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get単位金額<BR>
     * （追加注文）()の戻り値を取得する。<BR>
     * 　@　@　@(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get単位口数<BR>
     * （追加注文）()の戻り値を取得する。<BR>
     * 　@　@－引数.注文数量が買付単位数量で割り切れない場合は例外をスローする。<BR>
     * 　@　@　@（買付単位数量エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00361<BR>
     * <BR>
     * ２）　@取扱可能銘柄チェック<BR>
     * 　@－引数.拡張投信銘柄.isシステム取扱()をコールする。<BR>
     * 　@－isシステム取扱()が false を返す場合は例外をスローする。<BR>
     * 　@　@（取扱不可銘柄エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00362<BR>
     * <BR>
     * ３）　@発注日取得 <BR>
     * 　@－投信取引時間管理.get投信発注日()をコールし、発注日を取得する。 <BR>
     * <BR>
     * ４）　@取引可能銘柄チェック<BR>
     * 　@－引数.拡張投信銘柄.is買付可能()をコールする。<BR>
     * 　@　@［is買付可能に渡すパラメタ］<BR>
     * 　@　@　@発注日： 取得した発注日<BR>
     * 　@－is買付可能()が false を返す場合は例外をスローする。<BR> 
     * 　@　@（取引不可銘柄エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00363<BR>
     * 　@－引数.拡張投信銘柄.is買付制限有り()が true を返す場合は例外をスローする。<BR>
     * 　@　@（取引不可銘柄エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00363<BR>
     * <BR>
     * ５）　@緊急停止チェック<BR>
     * 　@－投信発注審査個別チェック.validate緊急停止()をコールする。<BR>
     * 　@　@［validate緊急停止に渡すパラメタ］<BR>
     * 　@　@　@拡張投信銘柄： 引数.拡張投信銘柄<BR>
     * 　@　@　@処理区分： 引数.処理区分<BR>
     * 　@－チェックエラーの場合は、例外をスローする。<BR>
     * 　@　@（緊急停止エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00012<BR>
     * <BR>
     * ６）　@取引停止時間チェック<BR>
     * 　@－投信取引時間管理.validete注文受付可能()をコールする。<BR>
     * 　@－チェックエラーの場合は、例外をスローする。<BR>
     * 　@　@（取引停止時間エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * ７）  外国証券口座開設チェック<BR>
     *    引数.銘柄.is外国投信() == true or 引数.銘柄.isFWF() == true の場合、実施 
     * 　@　@（外国証券口座未開設エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * ８）  累投口座開設チェック<BR>
     *    引数.銘柄.is再投資銘柄() == true の場合、実施 
     * 　@　@（累投口座未開設エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00249<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_mutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取<BR>
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 3：金額　@4：口数<BR>
     * @@param l_strSettleDiv - (決済方法@)<BR>
     * 1:円貨 2:外貨<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
    public void validateBuyNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strDesignateMethod,
        String l_strSettleDiv)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateBuyNewOrder()";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //買付最低数量
        long l_lngNewBuyMinQty = 0;
        //買付単位数量
        long l_lngNewBuyUnitQty = 0;
        //１）新規買付か追加買付かの判定 
        try
        {
            // 保有資産テーブルを検索
            List l_lisAssets = new ArrayList();
            String l_strWhere =
                "account_id = ? and sub_account_id = ? and product_id = ? ";
            Object[] l_objWhereValues = 
            {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId())
            };
                               
            // -保有資産テーブルを検索し、保有資産ParamsのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssets =
                l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
                    
            if (l_lisAssets.size() > 0)
            {
                log.debug("引数.指定方法@ = " + l_strDesignateMethod);
                //１－３）追加注文の場合は以下の処理を行う。
                //(*) 引数.指定方法@が”3：金額”かつ引数.決済方法@が”1:円貨”の場合
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getAddBuyMinAmt();
                    //買付単位数量を取得する
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getAddBuyUnitAmt();
                }

                //(*) 引数.指定方法@が”3：金額”かつ引数.決済方法@が”2:外貨”の場合
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getFrgnAddBuyMinAmt();
                    //買付単位数量を取得する
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getFrgnAddBuyUnitAmt();
                }

                //(*) 引数.指定方法@が”4：口数”の場合
                if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getAddBuyMinQty();
                    //買付単位数量を取得する                                        
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getAddBuyUnitQty();
                }
                //引数.注文数量 ＜ 買付最低数量の場合は例外をスローする
                log.debug("引数.注文数量 = " + l_dblOrderQuantity);
                log.debug("買付最低数量 = " + l_lngNewBuyMinQty);
                if (l_dblOrderQuantity < l_lngNewBuyMinQty)
                {
                    log.debug(" __追加注文:買付最低数量エラー__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00360,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __追加注文:買付最低数量エラー__");
                }
                //引数.注文数量が買付単位数量で割り切れない場合は例外をスローする
                log.debug("買付単位数量 = " + l_lngNewBuyUnitQty);
                if (l_dblOrderQuantity % l_lngNewBuyUnitQty != 0)
                {
                    log.debug(" __追加注文:買付単位数量エラー__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00361,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __追加注文:買付単位数量エラー__");
                }
            }
            else if (l_lisAssets.size() == 0)
            {
                log.debug("引数.指定方法@ = " + l_strDesignateMethod);
                //１－２）新規注文の場合は以下の処理を行う
                //引数.指定方法@が”3：金額”かつ引数.決済方法@が”1:円貨”の場合
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getNewBuyMinAmt();
                    //買付単位数量を取得する
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getNewBuyUnitAmt();
                }

                // 引数.指定方法@が”3：金額”かつ引数.決済方法@が”2:外貨”の場合
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getFrgnNewBuyMinAmt();
                    //買付単位数量を取得する
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getFrgnNewBuyUnitAmt();
                }
                //(*) 引数.指定方法@が”4：口数”の場合
                else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
                {
                    //買付最低数量を取得する
                    l_lngNewBuyMinQty = l_mutualFundProduct.getNewBuyMinQty();
                    //買付単位数量を取得する                                        
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getNewBuyUnitQty();
                }
                //引数.注文数量 ＜ 買付最低数量の場合は例外をスローする
                log.debug("引数.注文数量 = " + l_dblOrderQuantity);
                log.debug("買付最低数量 = " + l_lngNewBuyMinQty);
                if (l_dblOrderQuantity < l_lngNewBuyMinQty)
                {
                    log.debug(" __新規注文:買付最低数量エラー__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00360,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __新規注文:買付最低数量エラー__");
                }
                //引数.注文数量が買付単位数量で割り切れない場合は例外をスローする
                log.debug("買付単位数量 = " + l_lngNewBuyUnitQty);
                if (l_dblOrderQuantity % l_lngNewBuyUnitQty != 0)
                {
                    log.debug(" __新規注文:買付単位数量エラー__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00361,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }      
            }
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        
        //２）取扱可能銘柄チェック 
        //引数.拡張投信銘柄.isシステム取扱()をコールする
        if (!l_mutualFundProduct.isSystemHandling())
        {
            log.debug(" __取扱不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __新規注文:買付単位数量エラー__");
        }
        
        //３）発注日取得
        //投信取引時間管理.get投信発注日()をコールし、発注日を取得する。 
        Date l_datOrderBizDate = null;
        l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
        log.debug("投信発注日 = " + l_datOrderBizDate);

        //４）　@取引可能銘柄チェック 
        //引数.拡張投信銘柄.is買付可能()をコールする
        boolean l_blnAcquiredPossible = false; //買付可能
        l_blnAcquiredPossible = l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);
        log.debug("引数.拡張投信銘柄.is買付可能() = " + l_blnAcquiredPossible);

        //is買付可能()が false を返す場合は例外をスローする
        if (!l_blnAcquiredPossible)
        {
            log.debug(" __取引不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __取引不可銘柄エラー__");
        }
        
        boolean l_blnAcquiredDeregExistence = false; //買付制限有り
        l_blnAcquiredDeregExistence = l_mutualFundProduct.isAcquiredDeregExistence();
        log.debug("引数.拡張投信銘柄.is買付制限有り()" + l_blnAcquiredDeregExistence);
        
        //is買付制限有り()が true を返す場合は例外をスローする
        if (l_blnAcquiredDeregExistence)
        {
            log.debug(" __取引不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __取引不可銘柄エラー__");
        }
        //５）緊急停止チェック
        //投信発注審査個別チェック.validate緊急停止()をコールする
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("緊急停止エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);           
        }
        //６）取引停止時間チェック
        //投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //７）外国証券口座開設チェック
        if (l_mutualFundProduct.isForeignFund()
            || l_mutualFundProduct.isFWF()
            || l_mutualFundProduct.isFrgnMmf())
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            if (!l_mainAccount.isForeignAccountOpen())
            {
                log.debug(" __外国証券口座未開設エラー__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __外国証券口座未開設エラー__");
            }
        }

        //８）累投口座開設チェック
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            if (!l_mainAccount.isRuitoAccountOpen())
            {
                log.debug(" __累投口座未開設エラー__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __累投口座未開設エラー__");
            }
        }

        //validate外貨MMF二重注文(補助口座, 拡張投信銘柄, Date)
        //［validate外貨MMF二重注文に渡すパラメタ］
        //      補助口座       ： 引数.補助口座
        //      拡張投信銘柄 ： 引数.拡張投信銘柄
        //      発注日          ： 取得した発注日
        l_validationsCheck.validateFrgnMmfDoubleOrder(
            l_subAccount,
            l_mutualFundProduct,
            l_datOrderBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate新規注文（解約）<BR>
     * <BR>
     * validate新規注文（解約） <BR>s
     * シーケンス図（投信）発注審査（解約）を参照 <BR>
     * <BR>
     * =============================================== <BR>
     * 1.1 isシステム取扱()が false を返す場合は例外をスローする。 <BR>
     *       （取扱不可銘柄エラー） <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00362 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.3 is解約乗換可能()が false を返す場合は例外をスローする。 
     *      （取引不可銘柄エラー） <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00363 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.1 is円貨振込先（銀行口座）登録( )の戻り値がfalseの場合は例外をスローする。<BR>
     * 　@　@　@（振込先（銀行口座）登録が円貨登録ではありません。）<BR>
     * 　@　@　@　@class        : WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag          : BUSINESS_ERROR_02751<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.2.1 顧客の振込先金融機@関オブジェクトを取得できなかった場合は、例外をスローする。<BR>
     * 　@　@　@（振込先金融機@関が登録されていません。）<BR>
     * 　@　@　@　@class        : WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag          : BUSINESS_ERROR_01937<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 ①@引数.拡張投信銘柄.getMutualFundType() == MutualFundTypeEnum.国内の場合、<BR>
     *        引数.注文数量 ≧ 10000000000 の場合例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00077 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 ③引数.注文数量 ＜ 解約最低数量の場合は例外をスローする。 <BR>
     * 　@　@（解約最低数量エラー） <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00368 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 ④－引数.注文数量が解約単位数量で割り切れない場合は例外をスローする。 <BR>
     * 　@　@　@（解約単位数量エラー） <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00369 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_espMutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取<BR>
     * @@param l_strPaymentMethod - (受渡方法@)<BR>
     * 1：銀行振込み　@2：証券口座入金<BR>
     * <BR>
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 2：全部　@3：金額　@4：口数<BR>
     * @@param l_taxType - (税区分)<BR>
     * @@param l_strSettleDiv - (決済方法@)<BR>
     * 1:円貨 2:外貨<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C6
     */
    public void validateSellNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        TaxTypeEnum l_taxType,
        String l_strSettleDiv)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSellNewOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //1.1 isシステム取扱( )
        //引数.拡張投信銘柄.isシステム取扱()をコールする
        log.debug("引数.拡張投信銘柄.isシステム取扱() = " + 
            l_web3MutualFundProduct.isSystemHandling());
            
        //isシステム取扱()が false を返す場合は例外をスローする。 
        //（取扱不可銘柄エラー） 
        if (!l_web3MutualFundProduct.isSystemHandling())
        {
            log.debug("取扱不可銘柄エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー");
        }
        //1.2 発注日取得
        //投信取引時間管理.get投信発注日()をコールし、発注日を取得する。
        Date l_datOrderBizDate = null;
        l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

        //1.3 is解約乗換可能(Date) 
        Date l_datArgIsSellSwitchingPossible = null;
        long l_lngProductId = l_web3MutualFundProduct.getProductId();
        if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
        {
        	l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
        	l_datArgIsSellSwitchingPossible = l_datOrderBizDate;
        }

        //引数.拡張投信銘柄.is解約乗換可能()をコールする
        boolean l_blnSellSwitchingPossible = 
            l_web3MutualFundProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible);

        log.debug("引数.拡張投信銘柄.is解約乗換可能() = " + l_blnSellSwitchingPossible);
        if (!l_blnSellSwitchingPossible)
        {
            log.debug(" __取引不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __取引不可銘柄エラー__");
        }

        //1.4 請求方法@チェック
        boolean l_blnBuyingRequestPossible = false;
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
        //1.4.1 引数.拡張投信銘柄.is国内投信（）の戻り値==true
        //      AND 引数.処理区分が”4：買取”の場合 
        boolean l_blnIsDomesticFund = l_web3MutualFundProduct.isDomesticFund();       
        log.debug("引数.処理区分 = " + l_strProcessDiv);
        if (l_blnIsDomesticFund && WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {
            l_blnBuyingRequestPossible =
                l_validationsCheck.isBuyingRequestPossible(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_web3MutualFundProduct);
            log.debug("is買取請求可能 = " + l_blnBuyingRequestPossible);
            if (!l_blnBuyingRequestPossible)
            {
                log.debug("取引不可銘柄エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引不可銘柄エラー");
            }
        }
            
        try
        {
            //1.6 緊急停止チェック 
            //投信発注審査個別チェック.validate緊急停止()をコールする
            l_validationsCheck.validateEmergencyStop(
                l_web3MutualFundProduct, l_strProcessDiv);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("緊急停止エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);           
        }
        //1.7 取引停止時間チェック 
        //投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.8 振込先（銀行口座）チェック
        //引数.受渡方法@の値が”1：銀行振込み”の場合
        log.debug("引数.受渡方法@ = " + l_strPaymentMethod);
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_strPaymentMethod))
        {
            MainAccount l_mainAccount = null;
            //顧客オブジェクトを取得する
            l_mainAccount = l_subAccount.getMainAccount();

            //1.8.1 is円貨振込先（銀行口座）登録( )
            WEB3GentradeMainAccount l_genMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

            boolean l_blnIsJapCurBankAccountRegi =
                l_genMainAccount.isJapaneseCurrencyBankAccountRegi();

            //is円貨振込先（銀行口座）登録( )の戻り値がfalseの場合は例外をスローする。
            if (!l_blnIsJapCurBankAccountRegi)
            {
                log.debug("振込先（銀行口座）登録が円貨登録ではありません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "振込先（銀行口座）登録が円貨登録ではありません。");
            }
            //is円貨振込先（銀行口座）登録( )の戻り値 ==true の場合、
            //振込先金融機@関テーブルをチェックする
            else
            {
                //1.8.2.1 get振込先金融機@関( )
                //顧客の振込先金融機@関オブジェクトを取得する。
                //取得できなかった場合は、例外をスローする。
                if (l_genMainAccount.getTransferedFinInstitution() == null)
                {
                    log.debug("振込先金融機@関が登録されていません。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01937,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "振込先金融機@関が登録されていません。");
                }
            }
        }

        //1.9 全部解約時、全部解約が可能かチェックする
        //引数.指定方法@の値が”2：全部”の場合、全部解約可能かをチェックする。 
        log.debug("引数.指定方法@ = " + l_strDesignateMethod);
        
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
            //1.9.1 ＜分岐＞特定日取引銘柄の場合
        	if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
            {
                //1.9.1.1 validate全部解約乗換可能(補助口座, 拡張投信銘柄, TaxTypeEnum)
                //［validate全部解約乗換可能に渡すパラメタ］ 
                //　@　@　@補助口座： 引数.補助口座 
                //　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 
                //　@　@　@税区分： 引数.税区分

                l_validationsCheck.validateUnitTypeProductAllSellPoss(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_taxType);
            }
            //1.9.2 ＜分岐＞特定日取引銘柄以外の場合
            else
            {
                //1.9.2.1 validate全部解約乗換可能(補助口座, 拡張投信銘柄, TaxTypeEnum)
                //［validate全部解約乗換可能に渡すパラメタ］ 
                //　@　@　@補助口座： 引数.補助口座 
                //　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 
                //　@　@　@税区分： 引数.税区分
                //　@　@　@発注日：get投信発注日()の戻り値

                l_validationsCheck.validateAllSellSwtPoss(
                    l_subAccount,
                    l_web3MutualFundProduct, 
                    l_taxType,
                    l_datOrderBizDate);

            }
        	
        }

        //1.10 解約数量チェック
        long l_lngSellMinQty = 0; //解約最低数量
        long l_lngSellUnitQty = 0; //解約単位数量

        //引数.指定方法@の値が”2：全部”でない場合
        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
           //①@解約最低数量を取得する。
            // 引数.指定方法@が”3：金額”かつ引数.決済方法@が”1:円貨”の場合
            if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod)
                && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getSellMinAmt();
                l_lngSellUnitQty = l_web3MutualFundProduct.getSellUnitAmt();
                log.debug("解約最低数量 = " + l_lngSellMinQty);
                log.debug("解約単位数量 = " + l_lngSellUnitQty);
            }
            //引数.指定方法@が”3：金額”かつ引数.決済方法@が”2:外貨”の場合
            else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod)
                && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getFrgnSellMinAmt();
                l_lngSellUnitQty = l_web3MutualFundProduct.getFrgnSellUnitAmt();
                log.debug("解約最低数量 = " + l_lngSellMinQty);
                log.debug("解約単位数量 = " + l_lngSellUnitQty);
            }
            //引数.指定方法@が”4：口数”の場合
            else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getSellMinQty();
                l_lngSellUnitQty = l_web3MutualFundProduct.getSellUnitQty();
                log.debug("解約最低数量 = " + l_lngSellMinQty);
                log.debug("解約単位数量 = " + l_lngSellUnitQty);
            }
            //②引数.注文数量 ＜ 解約最低数量の場合は例外をスローする。 
            //　@（解約最低数量エラー） 
            if (l_dblOrderQuantity < l_lngSellMinQty)
            {
                log.debug("引数.注文数量 ＜ 解約最低数量の場合は例外をスローする。");
                log.debug("解約最低数量エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00368,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約最低数量エラー");
            }
            //③解約単位数量を取得する 
            //－引数.注文数量が解約単位数量で割り切れない場合は例外をスローする。 
            // （解約単位数量エラー） 
            if (l_dblOrderQuantity % l_lngSellUnitQty != 0)
            {
                log.debug("引数.注文数量が解約単位数量で割り切れない場合は例外をスローする。");
                log.debug("解約単位数量エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00369,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約単位数量エラー");
            }
        }

        //validate外貨MMF二重注文(補助口座, 拡張投信銘柄, Date)
        //［validate外貨MMF二重注文に渡すパラメタ］
        //    補助口座       ： 引数.補助口座
        //    拡張投信銘柄 ： 引数.拡張投信銘柄
        //    発注日          ： 取得した発注日
        l_validationsCheck.validateFrgnMmfDoubleOrder(
            l_subAccount,
            l_web3MutualFundProduct,
            l_datOrderBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate新規注文（乗換）)<BR>
     * <BR>
     * 投信乗換の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図「発注審査（乗換）」参照。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_web3MutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取<BR>
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 2：全部　@3：金額　@4：口数<BR>
     * @@param l_switchingSubjectProduct - (乗換先銘柄)<BR>
     * @@param l_taxType - (税区分)<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40BD67EF0262
     */
    public void validateSwitchingNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubjectProduct, 
        TaxTypeEnum l_taxType)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwitchingNewOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_web3MutualFundProduct == null ||
            l_switchingSubjectProduct == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 乗換発注日を取得する。
        //投信取引時間管理.get乗換発注日()をコールし、乗換発注日を取得する。  
        //[引数]
        //乗換元銘柄コード：　@引数.拡張投信銘柄
        //乗換先銘柄コード：　@引数.乗換先銘柄
        Date l_datSwtOrderBizDate = 
            WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
                l_web3MutualFundProduct.getProductCode(), l_switchingSubjectProduct.getProductCode());
        log.debug("乗換発注日 = " + l_datSwtOrderBizDate);

        //1.2 validate乗換可能銘柄(SubAccount, 拡張投信銘柄, 拡張投信銘柄, Date)
        //乗換元と乗換先のそれぞれの銘柄について、乗換可能かどうかをチェックする。 
        //[引数] 
        //補助口座： 引数.補助口座 
        //乗換元銘柄： 引数.拡張投信銘柄 
        //乗換先銘柄： 引数.乗換先銘柄 
        //発注日： get乗換発注日()の戻り値 
        
        this.validateSwitchingPossProduct(
            l_subAccount, 
            l_web3MutualFundProduct, 
            l_switchingSubjectProduct, 
            l_datSwtOrderBizDate);

        //1.3 is買取請求可能(Date, SubAccount, 拡張投信銘柄)
        //引数.拡張投信銘柄.is国内投信（）の戻り値==true AND 
        //処理区分が”4：買取”の場合、請求方法@チェックのチェックをする。 
        //［is買取請求可能に渡すパラメタ］ 
        //　@　@　@発注日： get乗換発注日()の戻り値
        //　@　@　@補助口座：引数.補助口座 
        //　@　@　@拡張投信銘柄：引数.拡張投信銘柄
        boolean l_blnBuyingRequestPossible = false;
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();        
        
        if (l_web3MutualFundProduct.isDomesticFund() &&
            WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {            
            l_blnBuyingRequestPossible =
                l_validationsCheck.isBuyingRequestPossible(
                    l_datSwtOrderBizDate, 
                    l_subAccount, 
                    l_web3MutualFundProduct);

            log.debug("引数.拡張投信銘柄.is買取請求可能() = " + l_blnBuyingRequestPossible);   
            if (!l_blnBuyingRequestPossible)
            {
                log.debug("取引不可銘柄エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引不可銘柄エラー");
            }
        }
        
        //1.4 validate全部解約乗換可能(補助口座, 拡張投信銘柄, TaxTypeEnum)
        //引数.指定方法@の値が”2：全部”の場合、全部乗換が可能かどうかチェックする。 
        
        log.debug("引数.指定方法@ = " + l_strDesignateMethod);
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
            //［validate全部解約乗換可能に渡すパラメタ］ 
            //　@　@　@補助口座： 引数.補助口座 
            //　@　@　@拡張投信銘柄： 引数.拡張投信銘柄 
            //　@　@　@税区分： 引数.税区分
            //　@　@　@発注日： get乗換発注日()の戻り値                
            l_validationsCheck.validateAllSellSwtPoss(
                l_subAccount, 
                l_web3MutualFundProduct, 
                l_taxType,
                l_datSwtOrderBizDate);
        }
        //1.5 指定方法@の値が”2：全部”でない場合、以下の処理を行う。
        else 
        {
            long l_lngNewSwtMinQty = 0L; //乗換最低数量
            long l_lngNewSwtUnitQty = 0L; //乗換単位数量
            
            //(*) 引数.指定方法@が”3：金額”の場合
            if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
            {
                //乗換最低数量を取得する
                l_lngNewSwtMinQty = l_web3MutualFundProduct.getSwitchingMinAmt();
                
                //乗換単位数量を取得する                      
                l_lngNewSwtUnitQty = l_web3MutualFundProduct.getSwitchingUnitAmt();
            }
            //(*) 引数.指定方法@が”4：口数”の場合
            if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
            {
                //乗換最低数量を取得する
                l_lngNewSwtMinQty = l_web3MutualFundProduct.getSwitchingMinQty();
                
                //乗換単位数量を取得する                                        
                l_lngNewSwtUnitQty = l_web3MutualFundProduct.getSwitchingUnitQty();
            }

            log.debug("引数.注文数量 " + l_dblOrderQuantity);
            log.debug("引数.乗換最低数量 " + l_lngNewSwtMinQty);
            log.debug("引数.乗換単位数量 " + l_lngNewSwtUnitQty);

            //引数.注文数量 ＜ 乗換最低数量の場合は例外をスローする。
            if (l_dblOrderQuantity < l_lngNewSwtMinQty)
            {
                log.debug("乗換最低数量エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00370,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "乗換最低数量エラー");
            }
            
            //引数.注文数量が乗換単位数量で割り切れない場合は例外をスローする。
            if (l_dblOrderQuantity % l_lngNewSwtUnitQty != 0)
            {
                log.debug("乗換単位数量エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00371,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "乗換単位数量エラー");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文単位)<BR>
     * 投信注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@証券会社オブジェクトを取得する。<BR>
     * 　@アカウントマネージャ.getInstitution()をコールし、証券会社オブジェクトを取得する。<BR>
     * 　@［getInstitutionに渡すパラメタ］<BR>
     * 　@　@証券会社コード： 引数.証券会社コード<BR>
     * <BR>
     * ２）　@部店オブジェクトを取得する。<BR>
     * 　@アカウントマネージャ.getBranch()をコールして、部店オブジェクトを取得する。<BR>
     * 　@［getBranchに渡すパラメタ］<BR>
     * 　@　@証券会社： 取得した証券会社オブジェクト<BR>
     * 　@　@部店コード： 引数.部店コード<BR>
     * <BR>
     * ３）　@投信注文単位オブジェクトを取得する。<BR>
     * 　@－以下の条件で投信注文単位テーブルを検索し、投信注文単位Paramsオブジェクトを取得する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@部店ID = 取得した部店オブジェクト.getBranchId()の戻り値 AND<BR>
     * 　@　@　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@－this.getOrderUnit()をコールし、投信注文単位オブジェクトを取得する。<BR>
     * 　@　@[getOrderUnitに渡すパラメタ]<BR>
     * 　@　@　@注文単位ID： 投信注文単位Params.getOrderUnitId()の戻り値<BR>
     * <BR>
     * ４）　@取得した投信注文単位オブジェクトを返す。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strRequestNumber - 識別コード<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40B15E2F00CC
     */
    public MutualFundOrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(String l_strInstitutionCode, "
                + "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        // 1)証券会社オブジェクトを取得する。
        FinApp l_finApp;
        l_finApp = (FinApp) Services.getService(FinApp.class);

        AccountManager l_accMgr;
        l_accMgr = l_finApp.getAccountManager();
        Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
        // 2)部店オブジェクトを取得する。
        Branch l_branch;
        l_branch = l_accMgr.getBranch(l_institution, l_strBranchCode);
        long l_lngBranchId = 0;
        l_lngBranchId = l_branch.getBranchId();

        MutualFundOrderUnit l_mfOrderUnit = null;
        try
        {
            log.debug("[検索条件] 部店ID  = " + l_lngBranchId);
            log.debug("[検索条件] 識別コード = " + l_strRequestNumber);
            // 3)累投注文単位オブジェクトを取得する。
            String l_whereClause;
            l_whereClause = "branch_id = ? and order_request_number = ?";

            List l_lisRows = new Vector();
            Object l_bindVars[] = { new Long(l_lngBranchId), l_strRequestNumber};
            //以下の条件で投信注文単位テーブルを検索し、
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
            if (l_lisRows == null || l_lisRows.size() ==0)
            {
                log.debug("テーブルに該当するデータがありません");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //投信注文単位Paramsオブジェクトを取得する
                MutualFundOrderUnitParams l_mfOrderUnitParams = 
                    (MutualFundOrderUnitParams)l_lisRows.get(0);

                //this.getOrderUnit()をコールし、投信注文単位オブジェクトを取得する
                l_mfOrderUnit =
                    (MutualFundOrderUnit) this.getOrderUnit(
                    l_mfOrderUnitParams.getOrderUnitId());
                log.debug("this.getOrderUnit().getOrderUnitId() = " + l_mfOrderUnit.getOrderUnitId());
            }         

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
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        log.exiting(STR_METHOD_NAME);
        //４）取得した投信注文単位オブジェクトを返す
        return l_mfOrderUnit;
    }

    /**
     * (get注文単位)<BR>
     * 投信注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@投信注文単位オブジェクトを取得する。<BR>
     * 　@－以下の条件で投信注文単位テーブルを検索し、投信注文単位Paramsオブジェクトを取得する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@補助口座ID = 引数.補助口座ID AND<BR>
     * 　@　@　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@－this.getOrderUnit()をコールし、投信注文単位オブジェクトを取得する。<BR>
     * 　@　@[getOrderUnitに渡すパラメタ]<BR>
     * 　@　@　@注文単位ID： 投信注文単位Params.getOrderUnitId()の戻り値<BR>
     * <BR>
     * ２）　@取得した投信注文単位オブジェクトを返す。<BR>
     * @@param l_lngSubAccountId - 補助口座ID<BR>
     * @@param l_strDiscriminationCode - 識別コード<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40B15E2F00D0
     */
    public MutualFundOrderUnit getOrderUnit(
            long l_lngSubAccountId, String l_strRequestNumber)
            throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(" + "long l_lngSubAccountId, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        //１）投信注文単位オブジェクトを取得する
        MutualFundOrderUnit l_mfOrderUnit = null;

        String l_whereClause;
        try
        {
            log.debug("@@param 補助口座ID = " + l_lngSubAccountId);
            log.debug("@@param 識別コード = " + l_strRequestNumber);
            l_whereClause = "sub_account_id = ? and order_request_number = ?";
            Object l_bindVars[] = { new Long(l_lngSubAccountId), 
                                    l_strRequestNumber};

            List l_lisRows = new Vector();
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);

            log.debug("find MutualFundOrderUnit record size = " + l_lisRows.size());
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("__テーブルに該当するデータがありません__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //投信注文単位Paramsオブジェクトを取得する
                MutualFundOrderUnitParams l_mfOrderUnitParams =
                    (MutualFundOrderUnitParams)l_lisRows.get(0);
                
                //this.getOrderUnit()をコールし、投信注文単位オブジェクトを取得する
                l_mfOrderUnit =
                    (MutualFundOrderUnit) this.getOrderUnit(
                        l_mfOrderUnitParams.getOrderUnitId());
                log.debug("this.getOrderUnit().getOrderUnitId() = " + l_mfOrderUnit.getOrderUnitId());
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
        //２）取得した投信注文単位オブジェクトを返す
        return l_mfOrderUnit;
    }

    /**
     * (get注文単位一覧)<BR>
     * 指定条件に一致する注文の投信注文単位オブジェクトの一覧を返却する。<BR>
     * (getOrderUnitsのオーバーロード)<BR>
     * <BR>
     * １)　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２)　@投信注文単位テーブルを検索し、投信注文単位ParamsのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@ 口座ID = 口座ID(補助口座オブジェクト.getAccountId( )で取得可) and<BR>
     * 　@　@　@ 補助口座ID = 補助口座ID(補助口座オブジェクト.getSubAccountId( )で取得可) and<BR>
     * 　@　@　@ 銘柄タイプ = ProductTypeEnum.MUTUAL_FUND(投資信託) and<BR>
     * 　@　@　@((発注日 >= 当日(*1))<BR>
     * 　@　@　@or<BR>
     * 　@　@　@(発注日 < 当日(*1) and <BR>
     * 　@　@　@ 約定状態 = "約定中"))<BR>
     * <BR>
     * 　@２-１）投信･外貨MMF表示区分 = "投信のみ"の場合<BR>
     * 　@　@" and 投信タイプ <> MutualFundTypeEnum.外貨MMF " を追加<BR>
     * <BR>
     * 　@２-２）投信･外貨MMF表示区分 = "外貨MMFのみ"の場合<BR>
     * 　@　@" and 投信タイプ = MutualFundTypeEnum.外貨MMF " を追加<BR>
     * <BR>
     *  (*1)TradingSystem.getBizDate()にて取得した業務(バッチ)日付の戻り値<BR>
     * <BR>
     * ３)　@検索結果を返却する。<BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - 投信･外貨MMF表示区分<BR>
     * 投信･外貨MMF表示区分  <BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ <BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     * @@return List
     * @@roseuid 40B15E2F00D3
     */
    public List getOrderUnitList(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getOrderUnitList(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //検索条件を生成する。
        List l_lisRows = new Vector();
        List l_lisReturnOrderUnit = null;
        String l_strWhereClause;
        l_strWhereClause =
            "account_id = ? and sub_account_id = ? and "                                                                                                                        
            + "product_type = ? and ((biz_date >= ?) or "                                                                                                                     
            + "(biz_date < ? and exec_status = ?))";

        //投信･外貨MMF表示区分 = "投信のみ"の場合
        //  " and 投信タイプ <> MutualFundTypeEnum.外貨MMF " を追加
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
            l_strMutualFrgnMmfDisplayDiv))
        {
            l_strWhereClause = l_strWhereClause + " and fund_type <> ? ";
        }

        //投信･外貨MMF表示区分 = "外貨MMFのみ"の場合
        // " and 投信タイプ = MutualFundTypeEnum.外貨MMF " を追加
        if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
            l_strMutualFrgnMmfDisplayDiv))
        {
            l_strWhereClause = l_strWhereClause + " and fund_type = ? ";
        }

        try
        {
            //口座ID(補助口座オブジェクト.getAccountId()で取得可)
            long l_lngAccount = l_subAccount.getAccountId();

            //補助口座ID(補助口座オブジェクト.getSubAccountId( )で取得可)
            long l_lngSubAccount = l_subAccount.getSubAccountId();

            //(*1)　@業務日付の取得   
              //TradingSystem.getBizDate()にて取得した業務(バッチ)日付の戻り値
              Date l_dateBizDate =
                  GtlUtils.getTradingSystem().getBizDate();

              SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
              String l_strBizDate;
              l_strBizDate = l_format.format(l_dateBizDate);
              log.debug("業務日付を取得 = " + l_strBizDate);

            //約定状態 = "約定中") 
            String l_strExecStatus = null;
            l_strExecStatus = WEB3ExecStatusDef.EXECUTED_IN_PROCESS;            

            int l_intLength = 6;
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_intLength = 7;
            }

            Object[] l_bindValues = new Object[l_intLength];
            l_bindValues[0] = new Long(l_lngAccount);
            l_bindValues[1] = new Long(l_lngSubAccount);
            l_bindValues[2] = ProductTypeEnum.MUTUAL_FUND;
            l_bindValues[3] = l_strBizDate;
            l_bindValues[4] = l_strBizDate;
            l_bindValues[5] = l_strExecStatus;

            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_bindValues[6] = MutualFundTypeEnum.FOREIGN_MMF;
            }

            // パラメータで検索条件データコンテナを作成する。
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindValues);
            log.debug("検索MutualFundOrderUnit Record Size = " + l_lisRows.size());

            //投信注文単位ParamsのListを取得する
            int l_intCount = l_lisRows.size();
            MutualFundOrderUnitParams[] l_mfOrderUnitParams =
                new MutualFundOrderUnitParams[l_intCount];
            l_lisRows.toArray(l_mfOrderUnitParams);
                
            l_lisReturnOrderUnit = new ArrayList();
    
            for (int i = 0; i < l_intCount; i++)
            {
                log.debug("for i = " + i);                    
                l_lisReturnOrderUnit.add(
                   this.getOrderUnit(l_mfOrderUnitParams[i].getOrderUnitId()));
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        //３)　@検索結果を返却する
        return l_lisReturnOrderUnit;
    }

    /**
     * (calc概算受渡代金)<BR>
     * 口数指定時は概算受渡代金、金額指定時は概算売買口数を算出して、<BR>
     * 概算受渡代金オブジェクトに設定して返却する。<BR>
     * <BR>
     * （calc概算受渡代金シーケンス図参照）<BR>
     * <BR>
     * <BR>
     * (1) 概算受渡代金オブジェクトを生成する。<BR>
     * 　@　@<BR>
     * <BR>
     * (2) 指定方法@が 口数の場合、概算受渡代金を算出する。<BR>
     * <BR>
     * 　@(2-1) 概算売買代金を算出して、概算受渡代金オブジェクトの概算売買代金にセットする。<BR>
     * 　@　@　@（calc概算売買代金()をコールする）<BR>
     * <BR>
     * 　@　@　@[calc概算売買代金()の引数]<BR>
     * 　@　@　@処理区分　@　@　@　@　@　@：引数の処理区分<BR>
     * 　@　@　@注文数量　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@　@決済方法@　@　@　@　@　@　@：引数の決済方法@<BR>
     * 　@　@　@概算受渡代金　@　@　@ ：概算受渡代金オブジェクト<BR>
     * 　@　@　@拡張投信銘柄　@　@　@ ：引数の拡張投信銘柄オブジェクト<BR>
     * <BR>
     * <BR>
     * 　@(2-2) 割増拘束率を加味した概算受渡代金を算出して、<BR>
     * 概算受渡代金オブジェクトの概算受渡代金にセットする。<BR>
     * 　@　@　@（calc割増拘束金()をコールする）<BR>
     * <BR>
     * 　@　@　@　@[calc割増拘束金()の引数]<BR>
     * 　@　@　@　@処理区分　@　@　@　@：引数の処理区分<BR>
     * 　@　@　@　@指定方法@　@　@　@　@：引数の指定方法@<BR>
     * 　@　@　@　@数量　@　@　@　@　@　@　@：概算受渡代金オブジェクトの概算売買代金<BR>
     * 　@　@　@　@補助口座　@　@　@　@：引数の補助口座オブジェクト<BR>
     * 　@　@　@　@概算受渡代金　@：概算受渡代金オブジェクト<BR>
     * 　@　@　@　@拡張投信銘柄　@：拡張投信銘柄オブジェクト<BR>
     * <BR>
     * 　@(2-3) 概算売買口数に、引数の注文数量をセットする。<BR>　@　@
     * <BR>
     * <BR>
     * (3) 指定方法@が金額指定の場合、概算売買口数を算出する。<BR>
     * <BR>
     * 　@(3-1) 概算売買口数を算出する。<BR>
     * 　@　@　@（calc概算売買口数()をコールする）<BR>
     * <BR>
     * 　@　@　@[calc概算売買口数の引数]<BR>
     * 　@　@　@処理区分　@　@　@　@：引数の処理区分<BR>
     * 　@　@　@注文数量　@　@　@　@：引数の注文数量
     * 　@　@　@拡張投信銘柄：引数の拡張投信銘柄オブジェクト<BR>
     * 　@　@　@概算受渡代金：概算受渡代金オブジェクト<BR>
     *      
     * <BR>
     * 　@(3-2) 割増拘束率を加味した概算売買口数を算出して、<BR>
     * 概算受渡代金オブジェクトの概算売買口数にセットする。<BR>
     * 　@　@　@（calc割増拘束金()をコールする）<BR>
     * <BR>
     * 　@　@　@　@[calc割増拘束金()の引数]<BR>
     * 　@　@　@　@処理区分　@　@　@　@：引数の処理区分<BR>
     * 　@　@　@　@指定方法@　@　@　@　@：引数の指定方法@<BR>
     * 　@　@　@　@数量　@　@　@　@　@　@　@：概算受渡代金オブジェクトの概算売買口数<BR>
     * 　@　@　@　@補助口座　@　@　@　@：引数の補助口座オブジェクト<BR>
     * 　@　@　@　@概算受渡代金　@：概算受渡代金オブジェクト<BR>
     * 　@　@　@　@拡張投信銘柄　@：拡張投信銘柄オブジェクト<BR>
     * <BR>
     * 　@(3-3) 概算受渡代金に、引数の注文数量をセットする。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_web3MutualFundProduct - (拡張投信銘柄)<BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * <BR>
     * １：買付<BR>
     * ２：解約<BR>
     * ３：乗換<BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * <BR>
     * 口数指定の場合は注文口数、金額指定の場合は注文金額<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * <BR>
     * ３：金額指定<BR>
     * ４：口数指定<BR>
     * @@param l_strSettlementMethod - 決済方法@<BR>
     * １：円貨<BR>
     * ２：外貨<BR>
     * @@return webbroker3.mf.WEB3MutualFundEstimatedPrice
     * @@roseuid 40B4759D0231
     */
    protected WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //(1) 概算受渡代金オブジェクトを生成する
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        //投信の計算サービス
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();        
        
        //(2) 指定方法@が 口数の場合、概算受渡代金を算出する
        log.debug("指定方法@ = " + l_strDesignateMethod);
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
        {
            log.debug("指定方法@が 口数の場合");
    
            //(2-1) 概算売買代金を算出して、
            //      概算受渡代金オブジェクトの概算売買代金にセットする
            //      （calc概算売買代金()をコールする）

            l_mfBizLogicProvider.calcEstimatedTradeAmount(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettlementMethod,
                l_web3MutualFundProduct,
                l_mfEstimatedPrice);       
            
            //(2-2) 割増拘束率を加味した概算受渡代金を算出して、
            //      概算受渡代金オブジェクトの概算受渡代金にセットする。 
            //      （calc割増拘束金()をコールする） 
            l_mfBizLogicProvider.calcIncreaseRestraintPriceInRatio(
                l_strProcessDiv,
                l_strDesignateMethod,
                l_mfEstimatedPrice.getEstimatedTradeAmount(),
                l_subAccount,
                l_mfEstimatedPrice,
                l_web3MutualFundProduct);
                
            //(2-3) 概算売買口数に、引数の注文数量をセットする
            l_mfEstimatedPrice.setEstimatedQty(l_dblOrderQuantity);
        }
        //(3) 指定方法@が金額指定の場合、概算売買口数を算出する
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
        {
            log.debug("指定方法@が金額指定の場合");
            log.debug("概算売買口数を算出する");
            //(3-1) 概算売買口数を算出する（calc概算売買口数()をコールする）
            l_mfBizLogicProvider.calcEstimatedQty(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_web3MutualFundProduct,
                l_mfEstimatedPrice);
            
            //(3-2) 割増拘束率を加味した概算売買口数を算出して、
            //      概算受渡代金オブジェクトの概算売買口数にセットする。 
            //      （calc割増拘束金()をコールする） 
            log.debug("割増拘束率を加味した概算売買口数を算出して");
            l_mfBizLogicProvider.calcIncreaseRestraintPriceInRatio(
                l_strProcessDiv,
                l_strDesignateMethod,
                l_mfEstimatedPrice.getEstimatedQty(),
                l_subAccount,
                l_mfEstimatedPrice,
                l_web3MutualFundProduct);

            //(3-3) 概算受渡代金に、引数の注文数量をセットする
            l_mfEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
            
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_mfEstimatedPrice;
    }

    /**
     * (get最終注文履歴)<BR>
     * 指定した注文IDに該当する注文単位の注文履歴最終通番に該当する<BR>
     * 投信注文履歴オブジェクトを返す。<BR>
     * <BR>
     * １）　@this.getOrder()をコールし、投信注文オブジェクトを取得する。<BR>
     * 　@［getOrderに渡すパラメタ］<BR>
     * 　@　@注文ID： 引数.注文ID<BR>
     * <BR>
     * ２）　@取得した投信注文オブジェクト.getOrderUnits()をコールし、投信注文単位<BR>
     * 　@　@オブジェクトの配列を取得する。<BR>
     * <BR>
     * ３）　@取得した投信注文単位オブジェクトの配列[0].getOrderActions()をコールし、<BR>
     * 　@　@投信注文履歴オブジェクトの配列を取得する。<BR>
     * <BR>
     * ４）　@取得した投信注文単位オブジェクトの配列[0].getDataSourceObject()
     *      .getLastOrderActionSerialNo()<BR>
     * 　@　@をコールし、注文履歴最終通番を取得する。<BR>
     * <BR>
     * ５）　@取得した投信注文履歴オブジェクトの配列の中から、取得した注文履歴最終通番と<BR>
     * 　@　@等しい注文履歴番号の投信注文履歴オブジェクトを取得して返す。<BR>
     * @@param l_lngOrderId - 注文ID<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderAction
     * @@roseuid 40B53C0200ED
     */
    public MutualFundOrderAction getFinalOrderAction(long l_lngOrderId) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getFinalOrderAction()";
        log.entering(STR_METHOD_NAME);

        MutualFundOrder l_mfOrder = null;
        MutualFundOrderAction l_mfReturnOrderAction = null;
        try
        {
            //１）this.getOrder()をコールし、投信注文オブジェクトを取得する
            l_mfOrder = (MutualFundOrder) this.getOrder(l_lngOrderId);

            //２）取得した投信注文オブジェクト.getOrderUnits()をコールし、
            //投信注文単位オブジェクトの配列を取得する
            OrderUnit l_orderUnit[] = l_mfOrder.getOrderUnits();
            int l_intSize = 0;
            l_intSize = l_orderUnit.length;
            log.debug("getOrderUnits.length = " + l_intSize);
            
            MutualFundOrderUnit[] l_mfOrderUnits =
                new MutualFundOrderUnit[l_intSize]; 

            for(int i = 0; i < l_intSize; i++)
            {
                log.debug("l_mfOrderUnits enter for i= " + i);
                MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit)l_orderUnit[i];
                l_mfOrderUnits[i] = l_mfOrderUnit;
            }

            //３）取得した投信注文単位オブジェクトの配列[0].getOrderActions()をコールし、
            //投信注文履歴オブジェクトの配列を取得する
            OrderAction l_orderAction[] = l_mfOrderUnits[0].getOrderActions();
            
            MutualFundOrderAction l_mfOrderActions[] =
                new MutualFundOrderAction[l_orderAction.length];
            
            for (int i = 0; i < l_orderAction.length; i++)
            {
                log.debug("l_mfOrderActions enter for i= " + i);
                MutualFundOrderAction l_mfOrderAction = (MutualFundOrderAction)l_orderAction[i];
                l_mfOrderActions[i] = l_mfOrderAction;
            }           
                
            //４）取得した投信注文単位オブジェクトの配列[0].getDataSourceObject()
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            //注文履歴最終通番を取得する
            int l_intLastOrderActionSerialNo =  
                l_mfOrderUnitRow.getLastOrderActionSerialNo();
            log.debug("注文履歴最終通番 l_mfOrderUnitRow.getLastOrderActionSerialNo() = " + 
                l_intLastOrderActionSerialNo);
            //５）取得した投信注文履歴オブジェクトの配列の中から、取得した注文履歴最終通番と 
            //  等しい注文履歴番号の投信注文履歴オブジェクトを取得して返す
           
            int i = 0;
            for (i = 0; i < l_mfOrderActions.length; i++)
            {
                log.debug("enter for i= " + i);
                log.debug("注文履歴番号 l_mfOrderAction[i].getOrderActionSerialNo() = " + 
                    l_mfOrderActions[i].getOrderActionSerialNo());
                if (l_mfOrderActions[i].getOrderActionSerialNo() == 
                    l_intLastOrderActionSerialNo)
                {
                    log.debug("注文履歴最終通番 = 注文履歴番号");
                    l_mfReturnOrderAction = l_mfOrderActions[i];
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(" __NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_mfReturnOrderAction;

    }

    /**
     * (get注文数量区分)<BR>
     * 投信注文単位より、その注文の注文数量区分を判定して返却する。<BR>
     * <BR>
     * １)　@投信注文単位.注文数量タイプが"数量"の場合、"0"をリターンする。<BR>
     * <BR>
     * ２)　@投信注文単位.注文数量タイプが"金額"の場合<BR>
     * 　@２－１)　@投信注文単位.決済区分が"円貨"の場合、"T0"をリターンする。<BR>
     * <BR>
     * 　@２－２)　@投信注文単位.決済区分が"外貨"の場合<BR>
     * 　@　@　@　@　@　@　@投信注文単位.getProduct( ).get通貨コード( )の戻り値をリターンする。<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@return String
     * @@roseuid 40BD92380174
     */
    public String getOrderQuantityDiv(MutualFundOrderUnit l_mutualFundOrderUnit)            
    {
        String STR_METHOD_NAME = "getOrderQuantityDiv()";
        log.entering(STR_METHOD_NAME);
       
        if (l_mutualFundOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        String l_strOrderQtyType = null;    //注文数量タイプ
        String l_strSettlementDiv = null;   //決済区分
        QuantityTypeEnum l_orderQtyType = null;   //注文数量タイプ
        
        MutualFundOrderUnitRow l_mfOrderUnitRow  = 
            (MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject();
        l_orderQtyType = l_mfOrderUnitRow.getQuantityType();
        l_strSettlementDiv = l_mfOrderUnitRow.getSettlementDiv();
        
        log.debug("投信注文単位.注文数量タイプ = " + l_orderQtyType);
        //１)　@投信注文単位.注文数量タイプが"数量"の場合
        //"0"をリターンする
        if (QuantityTypeEnum.QUANTITY.equals(l_orderQtyType))
        {
            l_strOrderQtyType = WEB3MFOrderQuantityType.QTY;
        }
        //２)　@投信注文単位.注文数量タイプが"金額"の場合
        if (QuantityTypeEnum.AMOUNT.equals(l_orderQtyType))
        {
            log.debug("投信注文単位.決済区分 = " + l_strSettlementDiv);
            //２－１)　@投信注文単位.決済区分が"円貨"の場合、"T0"をリターンする
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
            {
                l_strOrderQtyType = WEB3MFOrderQuantityType.EN;
            }
            //２－２)　@投信注文単位.決済区分が"外貨"の場合
            //投信注文単位.getProduct( ).get通貨コード( )の戻り値をリターンする
            if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
            {
                MutualFundProductRow l_mfProductRow = null;
                l_mfProductRow = (MutualFundProductRow)
                    l_mutualFundOrderUnit.getProduct().getDataSourceObject();
                l_strOrderQtyType = l_mfProductRow.getCurrencyCode();
            }
        }
        log.debug("return 注文数量区分 = " + l_strOrderQtyType);
        log.exiting(STR_METHOD_NAME);
        //注文数量区分をリターンする
        return l_strOrderQtyType;
    }

    /**
     * (get概算受渡代金通貨コード)<BR>
     * 投信注文単位より、その注文の概算数量区分を判定して返却する。<BR>
     * <BR>
     * １)　@this.get注文数量区分( )をコールする。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@引数:投信注文単位<BR>
     * <BR>
     * ２)　@戻り値セット<BR>
     * 　@２－１)　@１)の戻り値が"0"(口数)の場合<BR>
     * 　@　@２－１－１)　@投信注文単位.決済区分が"円貨"の場合<BR>
     * 　@　@　@　@　@　@　@"T0"をリターンする。<BR>
     * <BR>
     * 　@　@２－１－２)　@投信注文単位.決済区分が"外貨"の場合、<BR>
     * 　@　@　@　@　@　@　@投信注文単位.getProduct( ).get通貨コード( )をリターンする。<BR>
     *   ２－１)　@１)の戻り値が"0"以外の場合
     *      １)の戻り値をリターンする。
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@return String
     * @@roseuid 40BD92690164
     */
    public String getEstimateDeliveryAmountCurrencyCode(
        MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        String STR_METHOD_NAME = "getEstimateDeliveryAmountCurrencyCode()";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        
        //１)this.get注文数量区分()をコールする
        String l_strOrderQuantityDiv = this.getOrderQuantityDiv(l_mutualFundOrderUnit);
        //２)　@戻り値セット
        String l_strCurrencyCode = null;
                
        //２－１)　@１)の戻り値が"0"(口数)の場合
        log.debug("this.get注文数量区分()の戻り値 = " + l_strOrderQuantityDiv);        
        if (WEB3MFOrderQuantityType.QTY.equals(l_strOrderQuantityDiv))
        {
            MutualFundOrderUnitRow l_mfOrderUnitRow = null;
            l_mfOrderUnitRow = (MutualFundOrderUnitRow)
                l_mutualFundOrderUnit.getDataSourceObject();
            String l_strSettlementDiv = l_mfOrderUnitRow.getSettlementDiv();
            log.debug("投信注文単位.決済区分 = " + l_strSettlementDiv);
            
            //２－１－１)　@投信注文単位.決済区分が"円貨"の場合"T0"をリターンする
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
            {
                l_strCurrencyCode = WEB3MFOrderQuantityType.EN;
                log.debug("get概算受渡代金通貨コード(return) = " + l_strCurrencyCode);
            }
            //２－１－２)　@投信注文単位.決済区分が"外貨"の場合、 
            //投信注文単位.getProduct( ).get通貨コード( )をリターンする
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
            {
                MutualFundProductRow l_mfProductRow = null;
                l_mfProductRow = (MutualFundProductRow)
                    l_mutualFundOrderUnit.getProduct().getDataSourceObject();
                l_strCurrencyCode = l_mfProductRow.getCurrencyCode();
                log.debug("get概算受渡代金通貨コード(return) = " + l_strCurrencyCode);
            }
        }
        else
        {
            log.debug("戻り値が'0'以外の場合");
            l_strCurrencyCode = l_strOrderQuantityDiv;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strCurrencyCode;
    }

    /**
     * (get売買区分（投信）)<BR>
     * 引数:投信注文単位より、売買区分を取得して"買付"、"解約"、"乗換"、"募集"のいずれかを返却する。<BR>
     * <BR>
     * １)　@"買付"判定<BR>
     * 　@投信注文単位が以下の条件に合致する場合、"買付"をリターンする。<BR>
     * 　@○注文種別="投資信託買注文"である<BR>
     * <BR>
     * ２)　@"解約"判定 <BR>
     * 　@投信注文単位が以下の条件に合致する場合、"解約"をリターンする。 <BR>
     * 　@○注文種別="投資信託売注文"である <BR>
     * <BR>
     * ３)　@"乗換"判定 <BR>
     * 　@投信注文単位が以下の条件に合致する場合、"乗換"をリターンする。 <BR>
     * 　@○注文種別="投資信託乗換注文"である <BR>
     * <BR>
     * ４)　@"募集"判定 <BR>
     * 　@投信注文単位が以下の条件に合致する場合、"募集"をリターンする。 <BR>
     * 　@○注文種別="投資信託募集注文"である <BR>
     * <BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@return String
     * @@roseuid 40DBE07C01E3
     */
    public String getMutualTradeDiv(MutualFundOrderUnit l_mutualFundOrderUnit)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getMutualTradeDiv()";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strMfTradeDiv = null;   //売買区分
        MutualFundOrderUnitRow l_mfOrderUnitRow  = 
            (MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject();
        //１)　@"買付"判定
        //○注文種別="投資信託買注文"である       
        log.debug("注文種別 = " + l_mutualFundOrderUnit.getOrderType()); 
        if (OrderTypeEnum.MF_BUY.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.BUY;
            log.debug("売買区分 = " + l_strMfTradeDiv);
        }        
        //２)　@"解約"判定
        //投信注文単位が以下の条件に合致する場合、"解約"をリターンする。 
        //○注文種別="投資信託売注文"である 
        log.debug("銘柄コード = " + l_mfOrderUnitRow.getSwtProductCode());
        if (OrderTypeEnum.MF_SELL.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.SELL;
            log.debug("売買区分 = " + l_strMfTradeDiv);
        }        
        //３)　@"乗換"判定
        //投信注文単位が以下の条件に合致する場合、"乗換"をリターンする。
        //○注文種別="投資信託乗換注文"である 
        if (OrderTypeEnum.MF_SWITCHING.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.SWITCHING;
            log.debug("売買区分 = " + l_strMfTradeDiv);
        }
        //４)　@"募集"判定 
        //投信注文単位が以下の条件に合致する場合、"募集"をリターンする。 
        //　@○注文種別="投資信託募集注文"である 
        if (OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.RECRUIT;
            log.debug("売買区分 = " + l_strMfTradeDiv);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMfTradeDiv;
    }
    
    /**
     * (validate新規注文（募集）)
     * validate新規注文（募集） <BR>
     *<BR>
     * 投信募集の発注審査を行う。 <BR>
     *<BR>
     * シーケンス図「発注審査（募集）」参照。 <BR>
     * 1.1 募集数量チェック
     * =============================================== <BR>
     * －引数.注文数量 ＜ 募集最低数量の場合は例外をスローする。 <BR>
     * 　@　@　@（募集最低数量エラー）<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02227 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * －引数.注文数量が募集単位数量で割り切れない場合は例外をスローする。 <BR>
     * 　@　@　@（募集単位数量エラー）<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02228 <BR>
     * =============================================== <BR>
     *<BR>
     * 1.8 累投口座開設チェック
     * =============================================== <BR>
     * －銘柄が再投資銘柄で累投口座未開設の場合は例外をスローする。 <BR>
     * 　@　@　@（累投口座未開設エラー）<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00249 <BR>
     * =============================================== <BR>
     *<BR>
     * ===============================================<BR>
     *       シーケンス図 :（投信）発注審査（募集）<BR>
     *       具体位置    : 1.9. 二重注文チェック <BR>
     *　@　@　@　@　@　@　@　@　@　@　@２．同一銘柄注文の存在チェック<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@１．で取得したレコード件数が0以外の場合、<BR>
     *　@　@　@　@　@　@　@　@　@　@　@例外をスローする。（二重注文エラー）<BR>       
     *       class       : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02648 <BR>
     * ===============================================<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_dblOrderQuantity - 注文数量
     * @@param l_strDesignateMethod - (指定方法@)
     * 3：金額　@4：口数
     * @@param l_strProcessDiv - (処理区分)
     * 1：買付　@2：解約　@3：乗換　@4：買取　@5：募集
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
   public void validateRecruitNewOrder(
       SubAccount l_subAccount,
       WEB3MutualFundProduct l_mutualFundProduct,
       double l_dblOrderQuantity,
       String l_strDesignateMethod, 
       String l_strProcessDiv)
           throws OrderValidationException, WEB3BaseException
   {
       String STR_METHOD_NAME = "validateRecruitNewOrder(" +
            "SubAccount, WEB3MutualFundProduct, double, String, String)";
       log.entering(STR_METHOD_NAME);
       
       if (l_subAccount == null || l_mutualFundProduct == null)
       {
           log.debug(" パラメータ値がNULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       //1.1 募集数量チェック        
       //以下の処理を行う。 
       //－募集最低数量を取得する。           
       long l_lngRecruitMinQty = 0L;
       
       //(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get最低金額（募集）()の戻り値を取得する。
       if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
       {
           //募集最低数量を取得する
           l_lngRecruitMinQty = Long.parseLong(l_mutualFundProduct.getRecruitMinAmt());               
       }
       //(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get最低口数（募集）()の戻り値を取得する。 
       else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
       {
           //募集最低数量を取得する
           l_lngRecruitMinQty = Long.parseLong(l_mutualFundProduct.getRecruitMinQty());
       }
       log.debug("引数.注文数量 = " + l_dblOrderQuantity);
       log.debug("募集最低数量 = " + l_lngRecruitMinQty);
       
       //－引数.注文数量 ＜ 募集最低数量の場合は例外をスローする。 
       //　@（募集最低数量エラー） 
       if (l_dblOrderQuantity < l_lngRecruitMinQty)
       {
           log.debug("募集最低数量エラー");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02227,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "募集最低数量エラー");
       }
       
       //－募集単位数量を取得する 
       long l_lngRecruitUnitQty = 0L;
       
       //　@(*) 引数.指定方法@が”3：金額”の場合は拡張投信銘柄.get単位金額（募集）()の戻り値を取得する。 
       if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
       {
           //募集単位数量を取得する
           l_lngRecruitUnitQty = Long.parseLong(l_mutualFundProduct.getRecruitUnitAmt());               
       }
       //　@(*) 引数.指定方法@が”4：口数”の場合は拡張投信銘柄.get単位口数（募集）()の戻り値を取得する。 
       else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
       {
           //募集単位数量を取得する
           l_lngRecruitUnitQty = Long.parseLong(l_mutualFundProduct.getRecruitUnitQty());
       }
       log.debug("引数.注文数量 = " + l_dblOrderQuantity);
       log.debug("募集単位数量 = " + l_lngRecruitUnitQty);
     
       //－引数.注文数量が募集単位数量で割り切れない場合は例外をスローする。 
       //　@（募集単位数量エラー） 
       if (l_dblOrderQuantity % l_lngRecruitUnitQty != 0)
       {
           log.debug("引数.注文数量が募集単位数量で割り切れない場合は例外をスローする。");
           log.debug("募集単位数量エラー");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02228,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "募集単位数量エラー");
       }
       
       //1.2 isシステム取扱( )
       //取扱可能銘柄チェックをする 
       //引数.拡張投信銘柄.isシステム取扱()をコールする。 
       //isシステム取扱()が false を返す場合は例外をスローする。 
       //（取扱不可銘柄エラー）
       if (!l_mutualFundProduct.isSystemHandling())
       {
           log.debug("取扱不可銘柄エラー");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00362,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "取扱不可銘柄エラー");
       }
       
       //1.3 get発注日( )
       //取引時間管理.get発注日()をコールし、発注日を取得する。
       Date l_datBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate();
       
       //1.4 is募集可能(Date)
       //取引可能銘柄チェック 
       //引数.拡張投信銘柄.is募集可能()をコールする。 
       //［引数］ 
       //発注日： 取得した発注日 
       //is募集可能()が false を返す場合は例外をスローする。（取引不可銘柄エラー）
       boolean l_blnIsRecruitPoss = l_mutualFundProduct.isRecruitPossible(l_datBizDate);
       
       if (!l_blnIsRecruitPoss)
       {
           log.debug("取引不可銘柄エラー");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00363,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "取引不可銘柄エラー");
       }
       
       //1.5 getInstance( )
       WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
           (WEB3MutualFundOrderManagerReusableValidationsCheck)
               MutualFundProductTypeOrderManagerReusableValidations.getInstance();

       //1.6 validate緊急停止(拡張投信銘柄, String)
       //緊急停止チェック 
       //投信発注審査個別チェック.validate緊急停止()をコールする。 
       //［引数］ 
       //拡張投信銘柄： 引数.拡張投信銘柄 
       //処理区分： 引数.処理区分 
       //チェックエラーの場合は、例外をスローする。（緊急停止エラー）
       try
       {           
           l_validationsCheck.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
       }
       catch(WEB3BaseException l_ex)
       {
           log.error("緊急停止エラー");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00012,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getErrorMessage(),
               l_ex);           
       }
       
       //1.7 validate注文受付可能( )
       //取引停止時間チェック 
       //取引時間管理.validete注文受付可能()をコールする。 
       //チェックエラーの場合は、例外をスローする。（取引停止時間エラー）
       
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.8 累投口座開設チェック
        //(*) 引数.拡張投信銘柄.is再投資銘柄()の戻り値 == true の場合
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            // 1.8.1 getMainAccount()
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

            // 1.8.2 is累投口座開設()
            if (!l_mainAccount.isRuitoAccountOpen())
            {
                log.debug(" __累投口座未開設エラー__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __累投口座未開設エラー__");
            }
        }
        
        //1.9.二重注文チェック
        //二重注文チェック 
        //１．同一銘柄注文を取得 
        //    以下の条件で注文単位テーブルを検索する。  
        //    [検索条件]  
        StringBuffer l_strWhere = new StringBuffer();
        //    口座ID = 引数.補助口座.getAccountId() and  
        l_strWhere.append("account_id = ?");    
        //    補助口座ID = 引数.補助口座.getSubAccountId() and 
        l_strWhere.append(" and sub_account_id = ?");
        //    銘柄ID = 引数.拡張投信銘柄.getProductId() and 
        l_strWhere.append(" and product_id = ?");
        //    注文種別 = OrderTypeEnum.投資信託募集注文 and 
        l_strWhere.append(" and order_type = ?");
        //    ( 注文状態 = OrderStatusEnum.受付済(新規注文) 
        //      or 
        //      注文状態 = OrderStatusEnum.発注済(新規注文) 
        //      or 
        //      注文状態 = OrderStatusEnum.発注失敗（取消注文） ) 
        l_strWhere.append(" and order_status in (?, ?, ?) ");    
        
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProductId = l_mutualFundProduct.getProductId();
        long l_lngOrderType = OrderTypeEnum.MF_RECRUIT.intValue();
        long l_lngOrderStatusOne = OrderStatusEnum.ACCEPTED.intValue();
        long l_lngOrderStatusTwo = OrderStatusEnum.ORDERED.intValue();
        long l_lngOrderStatusThree = OrderStatusEnum.NOT_ORDERED.intValue();
        Object[] l_objQuerys = 
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                new Long(l_lngOrderType),
                new Long(l_lngOrderStatusOne),
                new Long(l_lngOrderStatusTwo),
                new Long(l_lngOrderStatusThree)};
        List l_lisOrderUnitRow = null;
        try
        {            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            l_lisOrderUnitRow = 
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE, 
                    l_strWhere.toString(), 
                    null,
                    l_objQuerys);
                    
        } 
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２．同一銘柄注文の存在チェック 
        //    １．で取得したレコード件数が0以外の場合、例外をスローする。（二重注文エラー） 
        if (l_lisOrderUnitRow != null && !l_lisOrderUnitRow.isEmpty())
        {
            log.debug("二重注文エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02648,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "二重注文エラー。");
        }

    log.exiting(STR_METHOD_NAME);
   }
   
   /**
     * (calc概算受渡代金)
     * 概算受渡代金（概算売買代金、概算売買口数）を算出し、 <BR>
     * 概算受渡代金オブジェクトに設定して返却する。 <BR>
     * <BR>
     * １）引数.銘柄.is外貨MMF　@= trueの場合<BR>
     * <BR>
     * １-１）　@this.calc外貨MMF概算受渡代金()に処理を委譲する。<BR>
     * <BR>
     * ２)引数.銘柄.is外貨MMF　@= falseの場合、以下を実行する。<BR>
     * <BR>
     * ２-１）当該顧客の証券会社が概算代金算出時に手数料を考慮するかどうかを判定する。 <BR>
     * <BR>
     *    引数.補助口座.getBranch().is投信手数料計算()をコールする。<BR> 
     * <BR>
     * ２-２）（ ２-１）の戻り値 ） == true（手数料計算要） の場合 <BR>
     * <BR>
     *    （投信）計算サービス.calc概算受渡代金()に処理を委譲する。 <BR>
     * <BR>
     *    [引数] <BR>
     *    顧客： 引数.補助口座.getMainAccount()の戻り値 <BR>
     *    銘柄： 引数.銘柄 <BR>
     *    銘柄（乗換先）： 引数.銘柄（乗換先） <BR>
     *    取引区分： 引数.処理区分 <BR>
     *    注文チャネル： 引数.注文チャネル <BR>
     *    指定区分： 引数.指定方法@ <BR>
     *    注文数量： 引数.注文数量 <BR>
     *    請求区分： 引数.請求方法@ <BR>
     *    口座区分： 引数.口座区分 <BR>
     *    発注日： 引数.発注日 <BR>
     * <BR>
     * ２-３）（ ２-１）の戻り値 ） == false（手数料計算不要） の場合 <BR>
     * <BR>
     *    this.calc概算受渡代金()に処理を委譲する。 <BR>
     * <BR>
     *    [引数] <BR>
     *    補助口座： 引数.補助口座 <BR>
     *    拡張投信銘柄： 引数.銘柄 <BR>
     *    処理区分： 引数.処理区分 <BR>
     *    注文数量： 引数.注文数量 <BR>
     *    指定方法@： 引数.指定方法@ <BR>
     *    決済方法@： 引数.決済方法@ <BR>
     *  <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_swtProduct - 銘柄（乗換先）
     * @@param l_strProcessDiv - 処理区分
     * @@param l_dblOrderQuantity - 注文数量
     * @@param l_strDesignateMethod - 指定方法@
     * @@param l_strSettlementMethod - 決済方法@
     * @@param l_strRequestMethod - 請求方法@
     * @@param l_strAccountDiv - 口座区分
     * @@param l_strOrderChannel - 注文チャネル
     * @@param l_datBizDate - 発注日
     * @@throws WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
    public WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundProduct l_swtProduct, 
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        String l_strOrderChannel, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount(SubAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String," +
            "double, String, String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
   
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //引数.銘柄.is外貨MMF　@= trueの場合
        //this.calc外貨MMF概算受渡代金()に処理を委譲する。
        if (l_mutualFundProduct.isFrgnMmf())
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcFrgnMmfEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strDesignateMethod,
                l_strSettlementMethod);
        }

        //引数.銘柄.is外貨MMF　@= falseの場合、以下を実行する
        //１）当該顧客の証券会社が概算代金算出時に手数料を考慮するかどうかを判定する。 
        //引数.補助口座.getBranch().is投信手数料計算()をコールする。
        
        WEB3GentradeBranch l_gentradeBranch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        
        boolean l_blnIsMfCommCalc = l_gentradeBranch.isCommissionCalc();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;
        
        //２）（ １）の戻り値 ） == true（手数料計算要） の場合 
        //    （投信）計算サービス.calc概算受渡代金()に処理を委譲する。 
        //    [引数] 
        //    顧客： 引数.補助口座.getMainAccount()の戻り値 
        //    銘柄： 引数.銘柄 
        //    銘柄（乗換先）： 引数.銘柄（乗換先） 
        //    取引区分： 引数.処理区分 
        //    注文チャネル： 引数.注文チャネル 
        //    指定区分： 引数.指定方法@ 
        //    注文数量： 引数.注文数量 
        //    請求区分： 引数.請求方法@ 
        //    口座区分： 引数.口座区分 
        //    決済区分： 引数.決済方法@ 
        //    発注日： 引数.発注日 
        if (l_blnIsMfCommCalc)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();        
            
            l_mfEstimatedPrice = 
                l_mfBizLogicProvider.calcEstimatedPrice(
                    (WEB3GentradeMainAccount)l_subAccount.getMainAccount(), 
                    l_mutualFundProduct, 
                    l_swtProduct, 
                    l_strProcessDiv, 
                    l_strOrderChannel, 
                    l_strDesignateMethod, 
                    l_dblOrderQuantity, 
                    l_strRequestMethod, 
                    l_strAccountDiv, 
                    l_strSettlementMethod, 
                    l_datBizDate);
        }
        //３）（ １）の戻り値 ） == false（手数料計算不要） の場合 
        //  this.calc概算受渡代金()に処理を委譲する。 
        //  [引数] 
        //  補助口座： 引数.補助口座 
        //  拡張投信銘柄： 引数.銘柄 
        //  処理区分： 引数.処理区分 
        //  注文数量： 引数.注文数量 
        //  指定方法@： 引数.指定方法@ 
        //  決済方法@： 引数.決済方法@ 
        else
        {
            l_mfEstimatedPrice = 
                this.calcEstimateDeliveryAmount(
                    l_subAccount, 
                    l_mutualFundProduct, 
                    l_strProcessDiv, 
                    l_dblOrderQuantity, 
                    l_strDesignateMethod, 
                    l_strSettlementMethod);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mfEstimatedPrice;        
    }   

    /**
     * (validate乗換可能銘柄)<BR>
     * 乗換元と乗換先のそれぞれの銘柄について、乗換可能な状態かどうかをチェックする。<BR> 
     * <BR>
     * シーケンス図「validate乗換可能銘柄」参照 <BR>
     * =============================================== <BR>
     * 1.4 isシステム取扱( ) <BR>
     * 　@　@戻り値 == false の場合、(取扱不可銘柄エラー)例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00362 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.5 is買付可能(Date) <BR>
     * 　@　@戻り値 == false の場合、(取引不可銘柄エラー)例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00363 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.2  is外国証券口座開設( ) <BR>
     * 　@　@戻り値 == false の場合、(外国証券口座未開設エラー)例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01341 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.9.2  is累投口座開設( ) <BR>
     * 　@　@戻り値 == false の場合、(累投口座未開設エラー)例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00249 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_swtOriginProduct - 乗換元銘柄<BR>
     * @@param l_swtPointProduct - 乗換先銘柄<BR>
     * @@param l_datBizDate - 発注日<BR>
     * @@return String
     * @@roseuid 40DBE07C01E3
     */
    public void validateSwitchingPossProduct(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_mfSwtProduct, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwitchingPossProduct(" +
                "SubAccount, WEB3MutualFundProduct, WEB3MutualFundProduct, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_mfSwtProduct == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //1.1 reset銘柄コード(銘柄コード : String)
        //取引カレンダコンテキストの銘柄コードを更新する。 
        //[引数] 
        //銘柄コード： 引数.乗換先銘柄.銘柄コード 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfSwtProduct.getProductCode());
       
        //1.2  reset注文受付トランザクション(注文受付トランザクション : String)
        //取引カレンダコンテキストの注文受付トランザクションを更新する。 
        //[引数] 
        //注文受付トランザクション： ”買付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        
        //1.3 setTimestamp( )
        //受付日時、日付ロールをセットする。
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.4 isシステム取扱( )(拡張投信銘柄::isシステム取扱)
        //取扱可能銘柄チェックを行う。
        boolean l_blnIsSystemHandle = l_mfSwtProduct.isSystemHandling();
        
        //戻り値 == false の場合、(取扱不可銘柄エラー)例外をスローする。
        if(!l_blnIsSystemHandle)
        {
            log.debug("取扱不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー。");
        }
        
        //1.5 is買付可能(Date)(拡張投信銘柄::is買付可能)
        //取引可能銘柄チェックを行う。 
        //[引数] 
        //発注日： 引数.発注日 
        boolean l_blnBuyPoss = l_mfSwtProduct.isAcquiredPossible(l_datBizDate);
        
        //戻り値 == false の場合、(取引不可銘柄エラー)例外をスローする。
        if (!l_blnBuyPoss)
        {
            log.debug("取引不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引不可銘柄エラー。");
        }
        
        //1.6 validate緊急停止(拡張投信銘柄, String)
        //緊急停止チェックをする 
        // ［引数］ 
        //拡張投信銘柄： 引数.乗換先銘柄 
        //処理区分： ”買付” 
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
        l_validationsCheck.validateEmergencyStop(
            l_mfSwtProduct, 
            WEB3ProcessDivDef.BUY);

        //1.7 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.8 (*) 引数.乗換先銘柄.is外国投信()の戻り値 == true or 
        //       引数.乗換先銘柄.isFWF()の戻り値 == true の場合
        if (l_mfSwtProduct.isForeignFund() || l_mfSwtProduct.isFWF())
        {
            log.debug("引数.乗換先銘柄.is外国投信()の戻り値 == true or " +
                "引数.乗換先銘柄.isFWF()の戻り値 == true の場合");
            
            //1.8.1 getMainAccount( )
            //顧客オブジェクトを取得する。
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
            //1.8.2  is外国証券口座開設( )
            //外国証券口座が開設されているかをチェックする。
            boolean l_blnForergnAccountOpen = l_genMainAccount.isForeignAccountOpen();
            
            //戻り値 == false の場合、(外国証券口座未開設エラー)例外をスローする。
            if (!l_blnForergnAccountOpen)
            {
                log.debug("外国証券口座未開設エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外国証券口座未開設エラー。");
            }
            
        }

        //1.9 (*) 引数.乗換先銘柄.is再投資銘柄()の戻り値 == true の場合
        if (l_mfSwtProduct.isPlowbackProduct())
        {
            log.debug("引数.乗換先銘柄.is再投資銘柄()の戻り値 == true の場合");
            
            // 1.9.1 getMainAccount()
            //顧客オブジェクトを取得する。
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

            // 1.9.2 is累投口座開設()
            //累投口座が開設されているかをチェックする。
            boolean l_blnRuitoAccountOpen = l_genMainAccount.isRuitoAccountOpen();

            //戻り値 == false の場合、(累投口座未開設エラー)例外をスローする。
            if (!l_blnRuitoAccountOpen)
            {
                log.debug("累投口座未開設エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "累投口座未開設エラー。");
            }
        }

        //1.10 reset銘柄コード(銘柄コード : String)
        //取引カレンダコンテキストの銘柄コードを更新する。 
        //[引数] 
        //銘柄コード： 引数.乗換元銘柄.銘柄コード 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfProduct.getProductCode());
        
        //1.11 reset注文受付トランザクション(注文受付トランザクション : String)
        //取引カレンダコンテキストの注文受付トランザクションを更新する。 
        //[引数] 
        //注文受付トランザクション： ”売付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //1.12 setTimestamp( )
        //受付日時、日付ロールをセットする。
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.13 isシステム取扱( )(拡張投信銘柄::isシステム取扱)
        //取扱可能銘柄チェックをする
        boolean l_blnIsSystemHandleOrigin = l_mfProduct.isSystemHandling();
        
        //戻り値 == false の場合,例外(取扱不可銘柄エラー)をスローする。
        if(!l_blnIsSystemHandleOrigin)
        {
            log.debug("取扱不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー。");
        }
        
        //1.14 is解約乗換可能(Date)(拡張投信銘柄::is解約乗換可能)
        //      取引可能銘柄チェックをする 
        //［is解約乗換可能に渡すパラメタ］ 
        //　@　@　@発注日： 取得した投信発注日
        boolean l_blnSellSwtPoss = 
            l_mfProduct.isSellSwitchingPossible(l_datBizDate);
        
        //戻り値 == false の場合,例外(取引不可銘柄エラー)をスローする。
        if (!l_blnSellSwtPoss)
        {
            log.debug("取引不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引不可銘柄エラー。");
        }
        
        //1.15 is乗換可能( )
        boolean l_blnIsSwitchingAble = l_mfProduct.isSwitchingAble();
        
        //戻り値 == false の場合,例外(乗換不可銘柄エラー)をスローする。
        if (!l_blnIsSwitchingAble)
        {
            log.debug("乗換不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換不可銘柄エラー。");
        }
        
        //1.16 validate緊急停止(拡張投信銘柄, String)
        //      緊急停止チェックをする 
        //［validate緊急停止に渡すパラメタ］ 
        //　@　@　@拡張投信銘柄： 乗換元の投信銘柄 
        //　@　@　@処理区分： ”3：乗換”
        l_validationsCheck.validateEmergencyStop(
            l_mfProduct, 
            WEB3ProcessDivDef.SWITCHING);
        
        //1.17 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get源泉徴収拘束金)<BR>
     * 源泉徴収の拘束金のチェックを行う。<BR> 
     * <BR>
     * １）以下の条件を満たす場合は、以降の処理を行う。 <BR>
     *    満たさない場合は、Double.NaNを返却する。 <BR>
     * <BR>
     *    [条件] <BR>
     *    引数.補助口座から取得した顧客の税区分 == ”特定口座かつ源泉徴収” and <BR>
     *    引数.保有資産IDから取得した保有資産.税区分 == ”特定” <BR>
     * <BR>
     * ２）源泉徴収拘束率の取得 <BR>
     * <BR>
     *    引数.補助口座から取得した部店オブジェクト.get投信源泉徴収拘束率()をコールする。 <BR>
     * <BR>
     *    値が返却された場合は、その数値を源泉徴収拘束率とする。 <BR>
     *    Double.NaNが返却された場合は、Double.NaNを返却する。 <BR>
     * <BR>
     * ３）拘束金の算出 <BR>
     * <BR>
     *    引数.概算受渡代金 × 取得した源泉徴収拘束率（小数点以下四捨五入） <BR>
     * <BR>
     *    を返却する。 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strRequestDiv - 請求区分<BR>
     * @@param l_strAssetId - 保有資産ID<BR>
     * @@param l_dblEstimateDeliveryAmount - 概算受渡代金<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 40DBE07C01E3
     */
    public Double getWithholdingTaxRestriction(
        SubAccount l_subAccount, 
        String l_strRequestDiv, 
        String l_strAssetId, 
        double l_dblEstimateDeliveryAmount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getWithholdingTaxRestriction(" +
            "SubAccount, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //１）以下の条件を満たす場合は、以降の処理を行う。 
        //満たさない場合は、Double.NaNを返却する。 

        //[条件] 
        //引数.補助口座から取得した顧客の税区分 == ”特定口座かつ源泉徴収” and 
        //引数.保有資産IDから取得した保有資産.税区分 == ”特定”        
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule
                (ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = l_mfPositionMgr.getAsset(Long.parseLong(l_strAssetId));
            l_assetRow = (AssetRow) l_asset.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        TaxTypeEnum l_taxType = l_mainAccountRow.getTaxType();
        if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType) && 
            TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
        {
            //２）源泉徴収拘束率の取得 
            //引数.補助口座から取得した部店オブジェクト.get投信源泉徴収拘束率()をコールする。 

            //値が返却された場合は、その数値を源泉徴収拘束率とする。 
            //Double.NaNが返却された場合は、nullを返却する。 
            WEB3GentradeBranch l_genBranch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            
            double l_dblRestrictionRate = l_genBranch.getWithholdingtaxRestrictionRate();
            log.debug("投信源泉徴収拘束率 = " + l_dblRestrictionRate);
            
            if (Double.isNaN(l_dblRestrictionRate))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("2) get源泉徴収拘束金() = null" );
                return null;
            }            
            //３）拘束金の算出 
            //引数.概算受渡代金 × 取得した源泉徴収拘束率（小数点以下四捨五入）を返却する。             
            double l_dblWithholdingTaxRestriction = 
                Math.round(l_dblEstimateDeliveryAmount * l_dblRestrictionRate);
            
            log.exiting(STR_METHOD_NAME);   
            log.debug("3) get源泉徴収拘束金() = " + l_dblWithholdingTaxRestriction);
            return new Double(l_dblWithholdingTaxRestriction);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);    
            log.debug("1) get源泉徴収拘束金() = null" );
            return null;
        }
    }
    
    /**
     * (validate乗換先買付最低金額)<BR>
     * 乗換元の概算受渡代金が乗換先銘柄の買付最低金額以上かのチェックを行う。<BR> 
     * <BR>
     * １）当チェック処理の要否の確認 <BR>
     * <BR>
     *    引数.補助口座から取得した部店オブジェクト.is投信乗換先買付最低金額<BR> 
     *      チェック実施()をコールする。 <BR>
     * <BR>
     *    ・戻り値 == true（チェック要） の場合、以降の処理を行う。 <BR>
     *    ・戻り値 == false（チェック不要） の場合、何もせずに処理を終了する。 <BR>
     * <BR>
     * ２）買付最低金額のチェック <BR>
     * <BR>
     * ２－１）新規買付か追加買付かの判定を行う。<BR> 
     * <BR>
     *    保有資産テーブルを検索し、0件の場合は新規注文、<BR> 
     * 1件以上ある場合は追加注文とする。 <BR>
     * <BR>
     *    [条件] <BR>
     *    補助口座ID： 引数.補助口座.補助口座ID <BR>
     *    口座ID： 引数.補助口座から取得した口座ID <BR>
     *    銘柄ID： 引数.乗換先銘柄.銘柄ID <BR>
     * <BR>
     * ２－２）新規買付の場合 <BR>
     * <BR>
     *    引数.概算受渡代金 < 引数.乗換先銘柄.get最低金額（新規買付）()の戻り値 <BR>
     * <BR>
     *    の場合、例外をスローする。 <BR> 
     * <BR> 
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00343 <BR>
     * <BR>
     * ２－３）追加買付の場合 <BR>
     * <BR>
     *    引数.概算受渡代金 < 引数.乗換先銘柄.get最低金額（追加買付）()の戻り値 <BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     * <BR> 
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00347 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strDesignDiv - 指定区分<BR>
     * @@param l_dblEstimateDeliveryAmount - 概算受渡代金<BR>
     * @@param l_swtProduct - 乗換先銘柄<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40DBE07C01E3
     */
    public void validateSwtBuyMinAmt(
        SubAccount l_subAccount, 
        String l_strDesignDiv, 
        double l_dblEstimateDeliveryAmount, 
        WEB3MutualFundProduct l_swtProduct) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwtBuyMinAmt(" +
            "SubAccount, String, double, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_swtProduct == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //１）当チェック処理の要否の確認 
        //引数.補助口座から取得した部店オブジェクト.is投信乗換先買付最低金額チェック実施()をコールする。 

        //・戻り値 == true（チェック要） の場合、以降の処理を行う。 
        //・戻り値 == false（チェック不要） の場合、何もせずに処理を終了する。
       
        WEB3GentradeBranch l_genBranch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        
        boolean l_blnBuyMinAmtCheck = 
            l_genBranch.isBuyingMinimumAmountCheck();
        
        if (l_blnBuyMinAmtCheck)
        {
            log.debug("is投信乗換先買付最低金額() = true");
            
            //２）買付最低金額のチェック                 
            //２－１）新規買付か追加買付かの判定を行う。 

            //   保有資産テーブルを検索し、0件の場合は新規買付、1件以上ある場合は追加買付とする。 
            //   [条件] 
            //   補助口座ID： 引数.補助口座.補助口座ID 
            //   口座ID： 引数.補助口座から取得した口座ID 
            //   銘柄ID： 引数.乗換先銘柄.銘柄ID 
                
            List l_lisAssets = new ArrayList();
            try
            {
                // 保有資産テーブルを検索。
                String l_strWhere = 
                    "account_id = ? and sub_account_id = ? "
                    + "and product_id = ? ";

                Object[] l_objWhereValues = {
                    new Long(l_subAccount.getAccountId()),
                    new Long(l_subAccount.getSubAccountId()),
                    new Long(l_swtProduct.getProductId())
                };
                // -保有資産テーブルを検索し、保有資産ParamsのListを取得する。
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAssets = l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In 保有資産テーブルを検索し ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In 保有資産テーブルを検索し ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            //0件の場合は新規注文
            if (l_lisAssets == null || l_lisAssets.size() == 0)
            {
                log.debug("0件の場合は新規注文");
                //２－２）新規買付の場合 
                //引数.概算受渡代金 < 引数.乗換先銘柄.get最低金額（新規買付）()の戻り値 
                //の場合、例外をスローする。 
                if (l_dblEstimateDeliveryAmount < l_swtProduct.getNewBuyMinAmt())
                {
                    log.debug("乗換先買付最低金額（新規買付）エラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02337,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "乗換先買付最低金額（新規買付）エラー。");
                }

            }
            //1件以上ある場合は追加注文
            else
            {
                log.debug("1件以上ある場合は追加注文");
                //２－３）追加買付の場合 
                //引数.概算受渡代金 < 引数.乗換先銘柄.get最低金額（追加買付）()の戻り値 
                //の場合、例外をスローする。 
                if (l_dblEstimateDeliveryAmount < l_swtProduct.getAddBuyMinAmt())
                {
                    log.debug("乗換先買付最低金額（追加買付）エラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02338,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "乗換先買付最低金額（追加買付）エラー。");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is手数料無料顧客)<BR>
     * 引数.顧客が、投資信託取引において手数料が無料かどうかを判定する。<BR> 
     * <BR>
     * １）以下の条件で、投信控除対象顧客テーブルからレコードを取得する。<BR> 
     * <BR>
     *　@　@[条件]<BR> 
     *　@　@証券会社コード = 引数.顧客.getInstitution().getInstitutionCode()の戻り値<BR> 
     *　@　@部店コード = 引数.顧客.getBranch().getBranchCode()の戻り値 <BR>
     *　@　@顧客コード = 引数.顧客.getAccountCode()の戻り値 <BR>
     *　@　@控除区分 = ”手数料無料” <BR>
     * <BR>
     * ２）１）でレコードが取得できなかった場合はfalseを返却する。<BR> 
     * <BR>
     * ３）１）でレコードが取得できた場合は、以下の条件で、<BR>
     *　@　@ 部店プリファ@レンステーブルからレコードを取得する。<BR> 
     * <BR>
     * 　@[条件]<BR> 
     *　@部店ID = 引数.顧客.getBranch().getBranchId()の戻り値<BR> 
     *　@プリファ@レンス名 = "mf.commission.free.product" <BR>
     *   <BR>
     *   ３-１）３）が取得できた場合、取得した各プリファ@レンスの値について<BR>
     *   　@　@下記の条件のチェックを行い、<BR> 
     *   　@　@　@　@1つでもtrueであればtrueを、すべてfalseであればfalseを返却する。 <BR>
     *   <BR>
     *   　@　@　@　@引数.銘柄.銘柄コード.startWith(プリファ@レンス[i]の値)<BR> 
     *   <BR>
     *   ３-２）３）が取得できなかった場合、trueを返却する。<BR> 
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト <BR>
     * @@param l_mutualFundProduct - (銘柄)<BR>
     * 銘柄オブジェクト <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCommissionFreeAccount(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mutualFundProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isCommissionFreeAccount(WEB3GentradeMainAccount, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_mutualFundProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strInstituionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            MfExemptionAccountDao.findRowByPk(
                l_strInstituionCode,
                l_strBranchCode,
                l_strAccountCode,
                WEB3ExemptionDivDef.COMMISSION_FREE);
        }
        catch (DataFindException l_ex)
        {
            //２）１）でレコードが取得できなかった場合はfalseを返却する。
            log.debug("投信控除対象顧客テーブルに" 
                + "証券会社コード = " + l_strInstituionCode
                + " 部店コード = " + l_strBranchCode
                + " 顧客コード = " + l_strAccountCode
                + " 控除区分 = " + "手数料無料"
                + " のレコードが無い");
            log.exiting(STR_METHOD_NAME);
            return false;
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
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ?");
        l_sbWhere.append(" and name = ?");
        
        long l_lngBranchId = l_mainAccount.getBranch().getBranchId();
        String l_strPreferencesName = WEB3BranchPreferencesNameDef.MF_COMMISSION_FREE_PRODUCT;
        List l_lisBranchPreferencesRows = new ArrayList();
        int l_intRecord = 0;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBranchPreferencesRows = l_queryProcessor.doFindAllQuery(
                BranchPreferencesRow.TYPE,
                l_sbWhere.toString(),
                new Object[]{new Long(l_lngBranchId), l_strPreferencesName});
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
        
        //３-２）３）が取得できなかった場合、trueを返却する。
        if (l_lisBranchPreferencesRows == null || l_lisBranchPreferencesRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //３-１）３）が取得できた場合、取得した各プリファ@レンスの値について
        //      下記の条件のチェックを行い、
        //1つでもtrueであればtrueを、すべてfalseであればfalseを返却する。
        for (int i = 0; i < l_lisBranchPreferencesRows.size(); i++)
        {         
            BranchPreferencesRow l_branchPreferencesRow = 
                (BranchPreferencesRow)l_lisBranchPreferencesRows.get(i);
            BranchPreferencesParams l_branchPreferencesParams = 
                new BranchPreferencesParams(l_branchPreferencesRow);
            
            //引数.銘柄.銘柄コード.startWith(プリファ@レンス[i]の値)
            boolean l_blnIsValue = l_mutualFundProduct.getProductCode().startsWith(
                l_branchPreferencesParams.getValue());
            if (l_blnIsValue)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            l_intRecord++;
        }
        
        if (l_intRecord == l_lisBranchPreferencesRows.size())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is非課税顧客)<BR>
     * <BR>
     * 引数.顧客が、投資信託取引において非課税かどうかを判定する。 <BR>
     * <BR>
     * １）以下の条件で、投信控除対象顧客テーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード = 引数.顧客.getInstitution().getInstitutionCode()の戻り値<BR>
     * 部店コード = 引数.顧客.getBranch().getBranchCode()の戻り値<BR>
     * 顧客コード = 引数.顧客.getAccountCode()の戻り値<BR>
     * 控除区分 = ”非課税”<BR>
     * <BR>
     * ２）レコードが取得できた場合はtrueを、取得できなかった場合はfalseを返却する。<BR>
     * <BR>
     * @@param l_mainAccount - 顧客<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isTaxFreeAccount(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTaxFreeAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strInstituionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            MfExemptionAccountDao.findRowByPk(
                l_strInstituionCode,
                l_strBranchCode,
                l_strAccountCode,
                WEB3ExemptionDivDef.TAX_FREE);
        }
        catch (DataFindException e)
        {
            //２）レコードが取得できた場合はtrueを、取得できなかった場合はfalseを返却する。
            log.debug("投信控除対象顧客テーブルに" 
                + "証券会社コード = " + l_strInstituionCode
                + " 部店コード = " + l_strBranchCode
                + " 顧客コード = " + l_strAccountCode
                + " 控除区分 = " + "非課税"
                + " のレコードが無い");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (calc外貨MMF概算受渡代金)<BR>
     * 口数指定時は概算受渡代金、金額指定時は概算売買口数を算出して、<BR>
     * 概算受渡代金オブジェクトに設定して返却する。 <BR>
     * <BR>
     * (1) 概算受渡代金オブジェクトを生成する。 <BR>
     * <BR>
     * <BR>
     * (2) 指定方法@が口数の場合、概算受渡代金を算出する。<BR> 
     * <BR>
     * 　@(2-1) 概算売買代金を算出して、概算受渡代金オブジェクトの概算売買代金にセットする。<BR> 
     * 　@　@　@（計算サービスのcalc外貨MMF概算売買代金()をコールする） <BR>
     * <BR>
     * 　@　@　@　@[calc外貨MMF概算受渡代金()の引数] <BR>
     * 　@　@　@　@処理区分           ：引数の処理区分 <BR>
     * 　@　@　@　@注文数量           ：引数の注文数量 <BR>
     * 　@　@　@　@決済方法@           ：引数の決済方法@ <BR>
     * 　@　@　@　@拡張投信銘柄       ：引数の拡張投信銘柄オブジェクト <BR>
     * 　@　@　@　@概算受渡代金       ：概算受渡代金オブジェクト <BR>
     * <BR>
     * 　@(2-3)概算受渡代金オブジェクトの 概算売買口数に引数の注文数量をセットする。<BR>
     * <BR>
     * <BR>
     * (3) 指定方法@が金額指定の場合、概算売買口数を算出する。 <BR>
     * <BR>
     * 　@(3-1) 概算売買口数を算出して、概算受渡代金オブジェクトの概算売買口数にセットする。<BR>
     * 　@　@　@（計算サービスのcalc外貨MMF概算売買口数()をコールする） <BR>
     * <BR>
     * 　@　@　@　@[calc外貨MMF概算売買口数の引数] <BR>
     * 　@　@　@　@処理区分           ：引数の処理区分 <BR>
     * 　@　@　@　@注文数量           ：引数の注文数量 <BR>
     * 　@　@　@　@決済方法@           ：引数の決済方法@ <BR>
     * 　@　@　@　@拡張投信銘柄       ：引数の拡張投信銘柄オブジェクト <BR>
     * 　@　@　@　@概算受渡代金       ：概算受渡代金オブジェクト <BR>
     * <BR>
     * 　@(3-3) 概算受渡代金オブジェクトの概算受渡代金に、引数の注文数量をセットする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_mutualFundProduct - (拡張投信銘柄)<BR>
     * 拡張投信銘柄<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分 <BR>
     * <BR>
     * １：買付 <BR>
     * ２：解約 <BR>
     * ３：乗換 <BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * <BR>
     * 口数指定の場合は注文口数、金額指定の場合は注文金額<BR>
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 指定方法@<BR>
     * <BR>
     * ３：金額指定 <BR>
     * ４：口数指定<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済方法@ <BR>
     * １：円貨 <BR>
     * ２：外貨<BR>
     * @@return WEB3MutualFundEstimatedPrice
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFundEstimatedPrice calcFrgnMmfEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettleDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcFrgnMmfEstimateDeliveryAmount(" +
            "SubAccount, WEB3MutualFundProduct, String, double, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //概算受渡代金オブジェクトを生成する。
        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            new WEB3MutualFundEstimatedPrice();

        //投信の計算サービス
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        //指定方法@が口数の場合、概算受渡代金を算出する。
        //概算売買代金を算出して、概算受渡代金オブジェクトの概算売買代金にセットする。
        //（計算サービスのcalc外貨MMF概算売買代金()をコールする）
        //[calc外貨MMF概算受渡代金()の引数]
        //処理区分           ：引数の処理区分
        //注文数量           ：引数の注文数量
        //決済方法@           ：引数の決済方法@
        //拡張投信銘柄       ：引数の拡張投信銘柄オブジェクト
        //概算受渡代金       ：概算受渡代金オブジェクト
        if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
        {
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettleDiv,
                l_mutualFundProduct,
                l_mutualFundEstimatedPrice);
            //概算受渡代金オブジェクトの 概算売買口数に引数の注文数量をセットする。
            l_mutualFundEstimatedPrice.setEstimatedQty(l_dblOrderQuantity);
        }

        //指定方法@が金額指定の場合、概算売買口数を算出する。
        //概算売買口数を算出して、概算受渡代金オブジェクトの概算売買口数にセットする。
        //（計算サービスのcalc外貨MMF概算売買口数()をコールする）
        //[calc外貨MMF概算売買口数の引数]
        //処理区分           ：引数の処理区分
        //注文数量           ：引数の注文数量
        //決済方法@           ：引数の決済方法@
        //拡張投信銘柄       ：引数の拡張投信銘柄オブジェクト
        //概算受渡代金       ：概算受渡代金オブジェクト
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod))
        {
            l_mfBizLogicProvider.calcFrgnMmfEstimatedQty(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettleDiv,
                l_mutualFundProduct,
                l_mutualFundEstimatedPrice);
            //概算受渡代金オブジェクトの概算受渡代金に、引数の注文数量をセットする。
            l_mutualFundEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
        }

        log.exiting(STR_METHOD_NAME);
        return l_mutualFundEstimatedPrice;
    }
}
@
