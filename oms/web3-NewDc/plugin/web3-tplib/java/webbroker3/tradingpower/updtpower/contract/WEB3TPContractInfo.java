head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉情報(WEB3TPContractInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
Revesion History : 2007/07/28 孟亜南 (中訊)モデルNo.116
Revesion History : 2008/02/01 トウ鋒鋼　@(中訊)　@仕様変更　@モデルNo.254、258
Revesion History : 2008/02/01 崔遠鵬　@(中訊)　@仕様変更　@モデルNo.255、256
Revesion History : 2008/11/07 孟亞南　@(中訊)　@仕様変更　@モデルNo.363
Revesion History : 2008/11/07 李キョウ　@(中訊)　@仕様変更　@モデルNo.382
*/
package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.tradingpower.data.EqtypeFixedContractRow;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (建玉情報)
 */
public class WEB3TPContractInfo extends WEB3TPAssetValuation 
{
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPContractInfo.class);
    
    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

    /**
     * (yyyyMMddフォーマット)
     */
    private static final String format_yyyyMMdd = "yyyyMMdd";

    /**
     * (対象建玉)
     */
    private List targetContracts;
    
    /**
     * (建玉ごと変動情報Map)
     */
    private Map contractHistories;
    
    /**
     * (建玉返済指定情報)
     */
    private Map closingContractSpecs;
    
    /**
     * @@roseuid 4104AB49004E
     */
    public WEB3TPContractInfo() 
    {
        targetContracts = new ArrayList();
        contractHistories = new HashMap();
        closingContractSpecs = new HashMap();
    }
    
    /**
     * (create建玉情報)<BR>
     * 建玉情報を生成する。<BR>
     * @@param accountInfo - (顧客属性)
     * @@param l_calcCondition - (余力計算条件)
     * @@param newOrderSpecs - (現注文内容)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo
     * @@roseuid 40F3CC0502C4
     */
    public static WEB3TPContractInfo create(WEB3TPAccountInfo l_accountInfo, WEB3TPCalcCondition l_calcCondition, WEB3TPNewOrderSpec[] l_newOrderSpecs) 
    {
        WEB3TPContractInfo l_thisInstance = new WEB3TPContractInfo();
        l_thisInstance.setAccountInfo(l_accountInfo);
        l_thisInstance.setCalcCondition(l_calcCondition);
        l_thisInstance.setNewOrderSpecs(l_newOrderSpecs);
        return l_thisInstance;
    }
    
    /**
     * (get未決済建玉の集計)<BR>
     * 引数で指定した日の未決済建玉の集計をする。<BR>
     * <BR>
     * １）対象建玉一覧を取得する。<BR>
     * <BR>
     * ２）対象建玉から建玉ごと変動情報を取得する。<BR>
     * <BR>
     * ３）建玉ごと変動情報を単位として未決済建玉の集計をする。<BR>
     * 　@－集計項目：<BR>
     * 　@　@・建玉代金(n)　@　@　@　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).建玉代金<BR>
     * 　@　@・必要保証金(n)　@　@　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).必要保証金<BR>
     * 　@　@・現金必要保証金(n)　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).現金必要保証金<BR>
     * 　@　@・発注分建玉代金(n)　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).発注分建玉代金<BR>
     * 　@　@・発注分必要保証金(n)　@　@　@＝Σ建玉ごと未決済建玉の集計(n).発注分必要保証金<BR>
     * 　@　@・発注分現金必要保証金(n)　@＝Σ建玉ごと未決済建玉の集計(n).発注分現金必要保証金<BR>
     * 　@　@・未決済建玉評価損(n)　@　@　@＝Σ建玉ごと未決済建玉の集計(n).未決済建玉評価損<BR>
     * 　@　@・未決済建玉評価益(n)　@　@　@＝Σ建玉ごと未決済建玉の集計(n).未決済建玉評価益<BR>
     * 　@　@・建手数料(n)　@　@　@　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).建手数料<BR>
     * 　@　@・日歩・逆日歩損(n)　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).日歩・逆日歩損<BR>
     * 　@　@・日歩・逆日歩益(n)　@　@　@　@＝Σ建玉ごと未決済建玉の集計(n).日歩・逆日歩益<BR>
     * 　@　@・その他建玉諸経費(n)　@　@　@＝Σ建玉ごと未決済建玉の集計(n).その他建玉諸経費<BR>
     * <BR>
     * ４）未決済建玉の集計結果を返す。<BR>
     * <BR>
     * ※nは、引数の指定日。<BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉ごと未決済建玉の集計(n)　@　@　@　@　@　@　@　@　@　@ 　@・・・建玉ごと変動情報.get未決済建玉の集計(n)<BR>
     * ・建玉ごと未決済建玉の集計(n).建玉代金　@　@　@　@　@　@　@・・・未決済建玉の集計(n).get建玉代金()<BR>
     * ・建玉ごと未決済建玉の集計(n).必要保証金　@　@　@　@　@　@・・・未決済建玉の集計(n).get必要保証金()<BR>
     * ・建玉ごと未決済建玉の集計(n).現金必要保証金　@　@　@　@・・・未決済建玉の集計(n).get現金必要保証金()<BR>
     * ・建玉ごと未決済建玉の集計(n).発注分建玉代金　@　@　@　@・・・未決済建玉の集計(n).get発注分建玉代金()<BR>
     * ・建玉ごと未決済建玉の集計(n).発注分必要保証金　@　@　@・・・未決済建玉の集計(n).get発注分必要保証金()<BR>
     * ・建玉ごと未決済建玉の集計(n).発注分現金必要保証金　@・・・未決済建玉の集計(n).get発注分現金必要保証金()<BR>
     * ・建玉ごと未決済建玉の集計(n).未決済建玉評価損　@　@　@・・・未決済建玉の集計(n).get未決済建玉評価損()<BR>
     * ・建玉ごと未決済建玉の集計(n).未決済建玉評価益　@　@　@・・・未決済建玉の集計(n).get未決済建玉評価益()<BR>
     * ・建玉ごと未決済建玉の集計(n).建手数料　@　@　@　@　@　@　@・・・未決済建玉の集計(n).get建手数料()<BR>
     * ・建玉ごと未決済建玉の集計(n).日歩・逆日歩損　@　@　@　@・・・未決済建玉の集計(n).get日歩・逆日歩損()<BR>
     * ・建玉ごと未決済建玉の集計(n).日歩・逆日歩益　@　@　@　@・・・未決済建玉の集計(n).get日歩・逆日歩益()<BR>
     * ・建玉ごと未決済建玉の集計(n).その他建玉諸経費　@　@　@・・・未決済建玉の集計(n).getその他建玉諸経費()<BR>
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 40E0DEFC039B
     */
    public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryOpenContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create未決済建玉の集計
        WEB3TPSummaryOpenContract l_sumOpenContract = WEB3TPSummaryOpenContract.create();

        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();

        //対象建玉一覧のサイズでループ
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryOpenContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
            
            //未決済建玉の集計(建玉ごと)
            WEB3TPSummaryOpenContract l_sumOpenContractPerContract = l_historyPerContract.getSummaryOpenContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumOpenContractPerContract.toString());
            }

            //建玉代金
            double l_dblContractAmount = l_sumOpenContract.getContractAmount() + l_sumOpenContractPerContract.getContractAmount();
            l_sumOpenContract.setContractAmount(l_dblContractAmount);
            
            //必要保証金
            double l_dblMarginDeposit = l_sumOpenContract.getMarginDeposit() + l_sumOpenContractPerContract.getMarginDeposit();
            l_sumOpenContract.setMarginDeposit(l_dblMarginDeposit);

            //現金必要保証金
            double l_dblCashMarginDeposit = l_sumOpenContract.getCashMarginDeposit() + l_sumOpenContractPerContract.getCashMarginDeposit();
            l_sumOpenContract.setCashMarginDeposit(l_dblCashMarginDeposit);

            //発注分建玉代金
            double l_dblUnExecContractAmount = l_sumOpenContract.getUnExecContractAmount() + l_sumOpenContractPerContract.getUnExecContractAmount();
            l_sumOpenContract.setUnExecContractAmount(l_dblUnExecContractAmount);
            
            //発注分必要保証金
            double l_dblUnExecMarginDeposit = l_sumOpenContract.getUnExecMarginDeposit() + l_sumOpenContractPerContract.getUnExecMarginDeposit();
            l_sumOpenContract.setUnExecMarginDeposit(l_dblUnExecMarginDeposit);

            //発注分現金必要保証金
            double l_dblUnExecCashMarginDeposit = l_sumOpenContract.getUnExecCashMarginDeposit() + l_sumOpenContractPerContract.getUnExecCashMarginDeposit();
            l_sumOpenContract.setUnExecCashMarginDeposit(l_dblUnExecCashMarginDeposit);
            
            //未決済建玉評価損
            double l_dblAssetLoss = l_sumOpenContract.getAssetLoss() + l_sumOpenContractPerContract.getAssetLoss();
            l_sumOpenContract.setAssetLoss(l_dblAssetLoss);
            
            //未決済建玉評価益
            double l_dblAssetProfit = l_sumOpenContract.getAssetProfit() + l_sumOpenContractPerContract.getAssetProfit();
            l_sumOpenContract.setAssetProfit(l_dblAssetProfit);
            
            //建手数料
            double l_dblSetupFee = l_sumOpenContract.getSetupFee() + l_sumOpenContractPerContract.getSetupFee();
            l_sumOpenContract.setSetupFee(l_dblSetupFee);
            
            //日歩・逆日歩損
            double l_dblInterestLoss = l_sumOpenContract.getInterestLoss() + l_sumOpenContractPerContract.getInterestLoss();
            l_sumOpenContract.setInterestLoss(l_dblInterestLoss);
            
            //日歩・逆日歩益
            double l_dblInterestProfit = l_sumOpenContract.getInterestProfit() + l_sumOpenContractPerContract.getInterestProfit();
            l_sumOpenContract.setInterestProfit(l_dblInterestProfit);
            
            //その他建玉諸経費
            double l_dblOtherCost = l_sumOpenContract.getOtherCost() + l_sumOpenContractPerContract.getOtherCost();
            l_sumOpenContract.setOtherCost(l_dblOtherCost);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryOpenContract Total:" + l_sumOpenContract.toString());
        }        
        return l_sumOpenContract;
    }
    
    /**
     * (get未受渡建玉の集計) <BR>
     * 引数で指定した日の未受渡建玉の集計をする。 <BR>
     * <BR>
     * １）対象建玉一覧を取得する。 <BR>
     * <BR>
     * ２）対象建玉から建玉ごと変動情報を取得する。 <BR>
     * <BR>
     * ３）建玉ごと変動情報を単位として未受渡建玉の集計をする。 <BR>
     * －集計項目： <BR>
     * ・建玉代金(n) ＝Σ建玉ごと未受渡建玉の集計(n).建玉代金 <BR>
     * ・必要保証金(n) ＝Σ建玉ごと未受渡建玉の集計(n).必要保証金 <BR>
     * ・現金必要保証金(n) ＝Σ建玉ごと未受渡建玉の集計(n).現金必要保証金 <BR>
     * ・決済損(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済損 <BR>
     * ・決済益(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済益 <BR>
     * ・決済損 <当日>(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済損 <当日><BR>
     * ・決済益 <当日>(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済益 <当日><BR>
     * ・決済建玉前日価格評価 <当日>(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済建玉前日価格評価 <当日><BR>
     * ・決済損 <指定日>(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済損 <指定日><BR>
     * ・決済益 <指定日>(n) ＝Σ建玉ごと未受渡建玉の集計(n).決済益 <指定日><BR>
     * <BR>
     * ４）未受渡建玉の集計結果を返す。 <BR>
     * <BR>
     * ※nは、引数の指定日。<BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・建玉ごと未受渡建玉の集計(n)　@　@　@　@　@　@　@　@ 　@　@　@　@　@　@・・・建玉ごと変動情報.get未受渡建玉の集計(n)<BR>
     * ・建玉ごと未受渡建玉の集計(n).建玉代金　@　@　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get建玉代金()<BR>
     * ・建玉ごと未受渡建玉の集計(n).必要保証金　@　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get必要保証金()<BR>
     * ・建玉ごと未受渡建玉の集計(n).現金必要保証金　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get現金必要保証金()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済損　@　@　@　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済損()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済益　@　@　@　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済益()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済損<当日>　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済損<当日>()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済益<当日>　@　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済益<当日>()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済建玉前日価格評価<当日>　@・・・未受渡建玉の集計(n).get決済建玉前日価格評価<当日>()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済損<指定日>　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済損<指定日>()<BR>
     * ・建玉ごと未受渡建玉の集計(n).決済益<指定日>　@　@　@　@　@　@　@・・・未受渡建玉の集計(n).get決済益<指定日>()<BR>
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 40E0DEFC03CA
     */
    public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryUndeliveredContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create未受渡建玉の集計
        WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract = WEB3TPSummaryUndeliveredContract.create();

        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();

        //対象建玉一覧のサイズでループ
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryUndeliveredContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
            
            //未受渡建玉の集計(建玉ごと)
            WEB3TPSummaryUndeliveredContract l_sumUndeliveredContractPerContract = l_historyPerContract.getSummaryUndeliveredContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumUndeliveredContractPerContract.toString());
            }

            //建玉代金
            double l_dblContractAmount = l_sumUndeliveredContract.getContractAmount() + l_sumUndeliveredContractPerContract.getContractAmount();
            l_sumUndeliveredContract.setContractAmount(l_dblContractAmount);
            
            //必要保証金
            double l_dblMarginDeposit = l_sumUndeliveredContract.getMarginDeposit() + l_sumUndeliveredContractPerContract.getMarginDeposit();
            l_sumUndeliveredContract.setMarginDeposit(l_dblMarginDeposit);

            //現金必要保証金
            double l_dblCashMarginDeposit = l_sumUndeliveredContract.getCashMarginDeposit() + l_sumUndeliveredContractPerContract.getCashMarginDeposit();
            l_sumUndeliveredContract.setCashMarginDeposit(l_dblCashMarginDeposit);
            
            //決済益
            double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + l_sumUndeliveredContractPerContract.getContractProfit();
            l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);
            
            //決済損
            double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss() + l_sumUndeliveredContractPerContract.getContractLoss();
            l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
            
            //決済益<当日>
            double l_dblTodayRepayContractProfit = l_sumUndeliveredContract.getTodayRepayContractProfit() + l_sumUndeliveredContractPerContract.getTodayRepayContractProfit();
            l_sumUndeliveredContract.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
            
            //決済損<当日>
            double l_dblTodayRepayContractLoss = l_sumUndeliveredContract.getTodayRepayContractLoss() + l_sumUndeliveredContractPerContract.getTodayRepayContractLoss();
            l_sumUndeliveredContract.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);
            
            //決済建玉前日価格評価<当日>
            double l_dblTodayRepayContractPrevAsset = l_sumUndeliveredContract.getTodayRepayContractPrevAsset() + l_sumUndeliveredContractPerContract.getTodayRepayContractPrevAsset();
            l_sumUndeliveredContract.setTodayRepayContractPrevAsset(l_dblTodayRepayContractPrevAsset);
            
            //決済益<指定日>
            double l_dblDesignateDateContractProfit = l_sumUndeliveredContract.getDesignateDateContractProfit() + l_sumUndeliveredContractPerContract.getDesignateDateContractProfit();
            l_sumUndeliveredContract.setDesignateDateContractProfit(l_dblDesignateDateContractProfit);
            
            //決済損<指定日>
            double l_dblDesignateDateContractLoss = l_sumUndeliveredContract.getDesignateDateContractLoss() + l_sumUndeliveredContractPerContract.getDesignateDateContractLoss();
            l_sumUndeliveredContract.setDesignateDateContractLoss(l_dblDesignateDateContractLoss);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryUndeliveredContract Total:" + l_sumUndeliveredContract.toString());
        }
        return l_sumUndeliveredContract;
    }

    /**
     * (get日計り返済・現引現渡建玉の集計) <BR>
     * <BR>
     * 引数.指定日の日計り返済・現引現渡建玉を集計する。 <BR>
     * <BR>
     * 1)日計り返済・現引現渡建玉の集計オブジェクトを作成する。<BR>
     * 　@-日計り返済・現引現渡建玉の集計.create日計り返済・現引現渡建玉の集計()をコール <BR>
     * <BR>
     * 2)対象建玉一覧を取得する。<BR>
     * 　@-this.get対象建玉一覧()をコール <BR>
     * <BR>
     * 3)日計り返済・現引現渡建玉の集計をする。 <BR>
     * <BR>
     * 　@＜取得した対象建玉一覧の要素数回LOOP処理＞ <BR>
     * 　@　@3-1)建玉ごと変動情報を取得する。 <BR>
     * 　@　@－this.get建玉ごと変動情報(:対象建玉 = (*)対象建玉オブジェクト)をコール <BR>
     * <BR>
     * 　@　@　@(*)対象建玉一覧より取得した対象建玉オブジェクト <BR>
     * <BR>
     * 　@　@3-2)建玉ごとの日計り返済・現引現渡建玉の集計オブジェクトを取得する。 <BR>
     * 　@　@－建玉ごと変動情報.get日計り返済・現引現渡建玉の集計(:Date = 引数.指定日) <BR>
     * <BR>
     * 　@　@3-3)各値を集計する。 <BR>
     * 　@　@－建玉代金 = 建玉代金 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get建玉代金() <BR>
     * 　@　@－必要保証金 = 必要保証金 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get必要保証金() <BR>
     * 　@　@－現金必要保証金 = 現金必要保証金 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get現金必要保証金() <BR>
     * <BR>
     * 　@　@－現引現渡建玉評価損 = 現引現渡建玉評価損 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get現引現渡建玉評価損() <BR>
     * 　@　@－現引現渡建玉評価益 = 現引現渡建玉評価益 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get現引現渡建玉評価益() <BR>
     * 　@　@－現引現渡建玉決済損 = 現引現渡建玉決済損 + 3-2)で取得した日計り返済・現引現渡建玉の集計.get現引現渡建玉決済損() <BR>
     * <BR>
     * 4)日計り返済・現引現渡建玉の集計オブジェクトに値をセットし返却する。 <BR>
     * <BR>
     * 　@－日計り返済・現引現渡建玉の集計.建玉代金 = 3)で集計された建玉代金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.必要保証金 = 3)で集計された必要保証金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現金必要保証金 = 3)で集計された現金必要保証金 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉評価損 = 3)で集計された現引現渡建玉評価損 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉評価益 = 3)で集計された現引現渡建玉評価益 <BR>
     * 　@－日計り返済・現引現渡建玉の集計.現引現渡建玉決済損 = 3)で集計された現引現渡建玉決済損 <BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     */
    public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryDayTradeSwapContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create日計り返済・現引現渡建玉の集計
        WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract = WEB3TPSummaryDayTradeSwapContract.create();

        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();

        //対象建玉一覧のサイズでループ
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryDayTradeSwapContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
           
            //日計り返済・現引現渡建玉の集計(建玉ごと)
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContractPerContract = l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumDayTradeSwapContractPerContract.toString());
            }
            
            //建玉代金
            double l_dblContractAmount = l_sumDayTradeSwapContract.getContractAmount() + l_sumDayTradeSwapContractPerContract.getContractAmount();
            l_sumDayTradeSwapContract.setContractAmount(l_dblContractAmount);
            
            //必要保証金
            double l_dblMarginDeposit = l_sumDayTradeSwapContract.getMarginDeposit() + l_sumDayTradeSwapContractPerContract.getMarginDeposit();
            l_sumDayTradeSwapContract.setMarginDeposit(l_dblMarginDeposit);

            //現金必要保証金
            double l_dblCashMarginDeposit = l_sumDayTradeSwapContract.getCashMarginDeposit() + l_sumDayTradeSwapContractPerContract.getCashMarginDeposit();
            l_sumDayTradeSwapContract.setCashMarginDeposit(l_dblCashMarginDeposit);

            //現引現渡建玉評価損
            double l_dblSwapContractAssetLoss = l_sumDayTradeSwapContract.getSwapContractAssetLoss() + l_sumDayTradeSwapContractPerContract.getSwapContractAssetLoss();
            l_sumDayTradeSwapContract.setSwapContractAssetLoss(l_dblSwapContractAssetLoss);
            
            //現引現渡建玉評価益
            double l_dblSwapContractAssetProfit = l_sumDayTradeSwapContract.getSwapContractAssetProfit() + l_sumDayTradeSwapContractPerContract.getSwapContractAssetProfit();
            l_sumDayTradeSwapContract.setSwapContractAssetProfit(l_dblSwapContractAssetProfit);

            //現引現渡建玉決済損
            BigDecimal l_bdSwapContractSettleLoss = new BigDecimal(Double.toString(
                l_sumDayTradeSwapContract.getSwapContractSettleLoss()));

            BigDecimal l_bdSwapContractSettleLossPerContract = new BigDecimal(Double.toString(
                l_sumDayTradeSwapContractPerContract.getSwapContractSettleLoss()));

            double l_dblSwapContractSettleLoss =
                l_bdSwapContractSettleLoss.add(l_bdSwapContractSettleLossPerContract).doubleValue();
            l_sumDayTradeSwapContract.setSwapContractSettleLoss(l_dblSwapContractSettleLoss);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryDayTradeSwapContract Total:" + l_sumDayTradeSwapContract.toString());
        }
        return l_sumDayTradeSwapContract;
    }

    /**
     * (get建玉銘柄一覧)<BR>
     * 引数.指定日の保有建玉銘柄IDの配列を返却する。<BR>
     * <BR>
     * １）対象建玉一覧を取得する。<BR>
     * 　@・建玉情報.get対象建玉一覧()をコール<BR>
     * <BR>
     * ２）引数.指定日の保有建玉銘柄ID一覧を作成する。<BR>
     * 　@・以下の処理を、取得した対象建玉一覧の要素数回LOOP処理<BR>
     * <BR>
     * 　@２－１）対象建玉一覧より、対象建玉オブジェクトを取得<BR>
     * <BR>
     * 　@２－２）対象建玉オブジェクトに紐付く建玉ごと変動情報オブジェクトを取得<BR>
     * 　@　@　@・建玉情報.get建玉ごと変動情報()をコール<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@対象建玉：２－１）で取得した対象建玉オブジェクト<BR>
     * <BR>
     *   ２－３）建玉ごと変動情報オブジェクトより、未決済建玉の集計オブジェクトを取得<BR>
     * 　@　@　@・建玉ごと変動情報.get未決済建玉の集計()をコール<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@指定日：引数.指定日<BR>
     * <BR>
     * 　@２－４）建玉ごと変動情報オブジェクトより、日計り返済・現引現渡建玉の集計オブジェクトを取得<BR>
     * 　@　@　@・建玉ごと変動情報.get日計り返済・現引現渡建玉の集計をコール<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@指定日：引数.指定日<BR>
     * <BR>
     * 　@２－５）未決済建玉代金を計算する。<BR>
     * 　@　@　@　@[計算式]<BR>
     * 　@　@　@　@　@未決済建玉代金＝建玉代金＋発注分建玉代金＋日計り返済・現引現渡建玉代金<BR>
     * <BR>
     * 　@　@　@　@　@(*)建玉代金：未決済建玉の集計.get建玉代金()<BR>
     * 　@　@　@　@　@(*)発注分建玉代金：未決済建玉の集計.get発注分建玉代金()<BR>
     * 　@　@　@　@　@(*)日計り返済・現引現渡建玉代金：日計り返済・現引現渡建玉の集計.get建玉代金()<BR>
     * <BR>
     * 　@２－６）以下の条件に該当する場合、保有建玉銘柄ID一覧に追加する。<BR>
     * 　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@２－５）で計算した未決済建玉代金 > 0 かつ　@<BR>
     * 　@　@　@　@　@保有建玉銘柄ID一覧に、対象建玉.get対象建玉詳細().get銘柄ID() が存在しない場合<BR>
     * 
     * ３）保有建玉銘柄ID一覧を配列に変換して返却する。<BR>
     * 　@注）保有建玉銘柄ID一覧=nullまたは、保有建玉銘柄ID一覧の要素数=0の場合、nullを返却する。<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@param l_contractType - 建区分
     * @@return long[]
     */
    public long[] getContractProducts(Date l_datDate, ContractTypeEnum l_contractType)
    {
        if(DBG)
        {
            log
                .debug("getContractProducts("
                        + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd")
                        + ")");
        }
    
        //保有建玉銘柄一覧
        List l_lisContractPruducts = new ArrayList();
    
        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();
    
        //対象建玉一覧のサイズでループ
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract) l_targetContracts
                .get(i);
            
            //対象建玉の建区分を取得
            ContractTypeEnum l_contType = l_targetContract.getTargetContractDetail().getContractType();
            

            //建区分 != 引数.建区分の場合
            if(l_contType.equals(l_contractType) == false)
            {
                //次の処理へ
                continue;
            }
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
    
            if(DBG)
            {
                log.debug("WEB3TPTargetContract[" + i + "]");
                log.debug("contract_id="
                        + l_targetContract
                            .getTargetContractDetail().getContractId());
                log.debug("product_id="
                        + l_targetContract
                            .getTargetContractDetail().getProductId());
                log.debug("is_executed="
                        + l_targetContract.isContractExecuted());
            }
    
            //未決済建玉の集計(建玉ごと)
            WEB3TPSummaryOpenContract l_sumOpenContractPerContract = l_historyPerContract
                .getSummaryOpenContract(l_datDate);
            //日計り返済・現引現渡建玉の集計(建玉ごと)
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContractPerContract = l_historyPerContract
                .getSummaryDayTradeSwapContract(l_datDate);
    
            if(DBG)
            {
                log.debug(l_sumOpenContractPerContract.toString());
                log.debug(l_sumDayTradeSwapContractPerContract.toString());
            }
    
            //未決済建玉代金
            double l_dblContractAmount = l_sumOpenContractPerContract
                .getContractAmount()
                    + l_sumOpenContractPerContract.getUnExecContractAmount()
                    + l_sumDayTradeSwapContractPerContract.getContractAmount();
    
            
            //未決済建玉が存在する(未決済建玉代金 > 0)場合
            if(l_dblContractAmount > 0)
            {
                Long l_bufProductId = new Long(l_targetContract
                    .getTargetContractDetail().getProductId());
    
                if(l_lisContractPruducts.contains(l_bufProductId) == false)
                {
                    l_lisContractPruducts.add(l_bufProductId);
                }
            }
        }
    
        if(l_lisContractPruducts == null || l_lisContractPruducts.size() == 0)
        {
            return null;
        }
        
        long[] l_productIds = new long[l_lisContractPruducts.size()];
        for(int i = 0; i < l_lisContractPruducts.size(); i++)
        {
            Long l_productId = (Long) l_lisContractPruducts.get(i);
    
            l_productIds[i] = l_productId.longValue();
        }
    
        return l_productIds;
    }
    
    /**
     * (do建玉情報ロード)<BR>
     * 集計に必要な建玉情報をロードする。<BR>
     * <BR>
     * １）建玉情報をロードするか判定する。<BR>
     * 　@－信用顧客か判定し、信用顧客でない場合はロード処理を終了する。<BR>
     * <BR>
     * ２）do建玉情報<確定>ロードをコールする。<BR>
     * <BR>
     * ３）do建玉情報<当日>ロードをコールする。<BR>
     * <BR>
     * ４）do建玉返済指定情報ロードをコールする。<BR>
     * <BR>
     * ５）do変動情報<確定>ロードをコールする。<BR>
     * <BR>
     * ６）do変動情報<当日>ロードをコールする。<BR>
     * <BR>
     * ７）do未約定変動情報ロードをコールする。<BR>
     * @@roseuid 40BE83BF030C
     */
    public void loadContractInfo() 
    {
        try
        {
            //顧客属性を取得
            WEB3TPAccountInfo l_accountInfo = getAccountInfo();
            
            //信用顧客フラグを取得
            boolean l_isEquityMargin = l_accountInfo.isMarginCustomer();
            
            if(DBG)
            {
                log.debug("isMarginAccount:" + l_isEquityMargin);
            }

            //信用顧客でない場合、ロード処理終了
            if(l_isEquityMargin == false)
            {
                return;
            }
            
            //確定の建玉情報をロード
            loadFixedContracts();
            
            //当日の建玉情報をロード
            loadTodaysContracts();
            
            //建玉返済指定情報をロード
            loadClosingContractSpecs();
            
            //確定の変動情報をロード
            loadFixedHistories();
            
            //当日の変動情報をロード
            loadTodaysHistories();
            
            //未約定変動情報をロード
            loadUnexecutedOrderSpecs();
        }
        finally
        {
            if(DBG)
            {
                log.debug("loadContractInfo finished:" + this.toString());
            }
        }
    }
    
    /**
     * (get対象建玉一覧)<BR>
     * 対象建玉一覧を取得する。<BR>
     * @@return java.util.List
     * @@roseuid 40F2427C0222
     */
    public List getTargetContracts() 
    {
        return targetContracts;
    }
    
    /**
     * (get建玉返済指定情報一覧)<BR>
     * 建玉返済指定情報一覧を取得する。<BR>
     * <BR>
     * １）引数の注文単位IDに関連する建玉返済指定情報一覧を返す。<BR>
     * ２）存在しない場合はnullを返す。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@return java.util.List
     * @@roseuid 40EE0E04026A
     */
    public List getClosingContractSpecs(long l_lngOrderUnitId) 
    {
        //Long型に変換
        Long l_orderUnitId = new Long(l_lngOrderUnitId);
        
        //注文単位IDをキーにして、建玉返済指定情報を取得        
        List l_closingContractSpecs = (List)closingContractSpecs.get(l_orderUnitId);
        
        if(l_closingContractSpecs == null)
        {
            l_closingContractSpecs = new ArrayList();
        }
        
        return l_closingContractSpecs;
    }
    
    /**
     * (do建玉情報<確定>ロード)<BR>
     * 確定の建玉情報をロードする。<BR>
     * <BR>
     * １）確定の建玉情報を取得する。<BR>
     * 　@　@データベースアクセス管理.get建玉情報<確定>をコールする。<BR>
     * 　@　@以降の処理は、検索結果の各行に対して実施する。<BR>
     * 　@　@データベースアクセス管理.get株式取引銘柄をコールする。<BR>
     * <BR>
     * ２）対象建玉詳細を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・建玉ID　@　@　@　@　@　@＝確定の建株テーブル.建玉ID<BR>
     * 　@　@　@・口座ID　@　@　@　@　@　@＝顧客属性.口座ID<BR>
     * 　@　@　@・補助口座ID　@　@　@　@＝顧客属性.補助口座ID<BR>
     * 　@　@　@・市場ID　@　@　@　@　@　@＝確定の建株テーブル.市場ID<BR>
     * 　@　@　@・銘柄ID　@　@　@　@　@　@＝確定の建株テーブル.銘柄ID<BR>
     * 　@　@　@・建区分　@　@　@　@　@　@＝確定の建株テーブル.建区分<BR>
     * 　@　@　@・建単価　@　@　@　@　@　@＝確定の建株テーブル.建単価<BR>
     * 　@　@　@・元建株数　@　@　@　@　@＝確定の建株テーブル.元建株数<BR>
     * 　@　@　@・建株数　@　@　@　@　@　@＝確定の建株テーブル.建株数<BR>
     * 　@　@　@・建日　@　@　@　@　@　@　@＝確定の建株テーブル.建日<BR>
     * 　@　@　@・期日　@　@　@　@　@　@　@＝確定の建株テーブル.期日<BR>
     * 　@　@　@・建手数料　@　@　@　@　@＝確定の建株テーブル.建手数料<BR>
     * 　@　@　@・建手数料消費税　@　@＝確定の建株テーブル.建手数料消費税<BR>
     * 　@　@　@・名義書換料　@　@　@　@＝確定の建株テーブル.名義書換料<BR>
     * 　@　@　@・名義書換料消費税　@＝確定の建株テーブル.名義書換料消費税<BR>
     * 　@　@　@・管理費　@　@　@　@　@　@＝確定の建株テーブル.管理費<BR>
     * 　@　@　@・管理費消費税　@　@　@＝確定の建株テーブル.管理費消費税<BR>
     * 　@　@　@・順日歩　@　@　@　@　@　@＝確定の建株テーブル.順日歩<BR>
     * 　@　@　@・逆日歩　@　@　@　@　@　@＝確定の建株テーブル.逆日歩<BR>
     * 　@　@　@・貸株料　@　@　@　@　@　@＝確定の建株テーブル.貸株料<BR>
     * 　@　@　@・保証金率　@　@　@　@　@＝確定の建株テーブル.保証金率<BR>
     * 　@　@　@・現金保証金率　@　@　@＝確定の建株テーブル.現金保証金率<BR>
     * 　@　@　@・税区分　@　@　@　@　@　@＝確定の建株テーブル.税区分<BR>
     * 　@　@　@・弁済区分　@　@　@　@　@＝確定の建株テーブル.弁済区分<BR>
     * 　@　@　@・弁済期限値　@　@　@　@＝確定の建株テーブル.弁済期限値<BR>
     * 　@　@　@・前日終値　@　@　@　@　@＝株式取引銘柄テーブル.基準値(前日終値)<BR>
     * 　@　@　@・評価単価　@　@　@　@　@＝評価単価Callback.get評価単価<建株><BR>
     * <BR>
     * ３）対象建玉を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・新規建約定済フラグ　@＝true<BR>
     * 　@　@　@・対象建玉詳細　@　@　@　@＝生成した対象建玉詳細<BR>
     * <BR>
     * ４）建玉ごと変動情報を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・建玉情報　@＝this<BR>
     * 　@　@　@・対象建玉　@＝生成した対象建玉<BR>
     * <BR>
     * ５）対象建玉と建玉ごと変動情報を関連づける。<BR>
     * <BR>
     * ６）建玉変動を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・トランザクションカテゴリ　@＝新規建<BR>
     * 　@　@　@・約定済フラグ　@　@　@　@　@　@　@＝true<BR>
     * 　@　@　@・トランザクション発生日　@　@＝確定の建株テーブル.建日<BR>
     * 　@　@　@・単価　@　@　@　@　@　@　@　@　@　@　@＝確定の建株テーブル.建単価<BR>
     * 　@　@　@・株数　@　@　@　@　@　@　@　@　@　@　@＝確定の建株テーブル.元建株数<BR>
     * 　@　@　@・変動反映開始日　@　@　@　@　@　@＝営業日(T+0)<BR>
     * 　@　@　@・変動反映終了日　@　@　@　@　@　@＝営業日(T+5)<BR>
     * 　@　@　@※変動反映開始日、変動反映終了日は建玉変動.calc変動反映日で設定する。<BR>
     * <BR>
     * ７）建玉ごと変動情報に建玉変動を追加する。<BR>
     * @@roseuid 40BE83CC004D
     */
    protected void loadFixedContracts() 
    {
        final String STR_METHOD_NAME = "loadFixedContracts()";

        //計算条件を取得
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //評価単価Callbackを取得
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();

        //確定の建玉レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getFixedContracts(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_fixed_contract row found.");

        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeFixedContractRow l_row = (EqtypeFixedContractRow)l_rows.get(i);
            
            //株式取引銘柄を取得
            EqtypeTradedProductRow l_eqTradedProduct = 
                WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this,
                                                                                l_row.getProductId(),
                                                                                l_row.getMarketId(),
                                                                                WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));

            //create対象建玉詳細
            WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
            
            //該当データが存在しない場合はデバッグログを出力して、基準値をセットしない。(初期値0のまま)
            if(l_eqTradedProduct == null)
            {
                if(DBG)
                {
                    StringBuffer l_strBuf = new StringBuffer("データが見つからないので基準値(前日終値)=0 株式取引銘柄マスターテーブルの検索条件:");
                    l_strBuf.append("institution_code=");
                    l_strBuf.append(this.getAccountInfo().getInstitutionCode());
                    l_strBuf.append(" product_id=");
                    l_strBuf.append(l_row.getProductId());
                    l_strBuf.append(" market_id=");
                    l_strBuf.append(l_row.getMarketId());
                    l_strBuf.append(" valid_until_biz_date=");
                    l_strBuf.append(WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));
                    String l_strMsg = l_strBuf.toString();
                    log.debug(l_strMsg);
                }
            }
            //基準値(前日終値)をセット
            else
            {
                //基準値(前日終値)
                l_targetContractDetail.setLastClosingPrice(l_eqTradedProduct.getLastClosingPrice());                
            }
            
            //建株ID
            l_targetContractDetail.setContractId(l_row.getFixedContractId());
            //口座ID
            l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
            //補助口座ID
            l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
            //市場ID
            l_targetContractDetail.setMarketId(l_row.getMarketId());
            //銘柄ID
            l_targetContractDetail.setProductId(l_row.getProductId());
            //建区分
            l_targetContractDetail.setContractType(l_row.getContractType());
            //建単価
            l_targetContractDetail.setContractPrice(l_row.getContractPrice());
            //元建株数
            l_targetContractDetail.setOriginalQuantity(l_row.getOriginalQuantity());
            //建株数
            l_targetContractDetail.setQuantity(l_row.getQuantity());
            //建日
            l_targetContractDetail.setOpenDate(l_row.getOpenDate());
            //期日
            l_targetContractDetail.setCloseDate(l_row.getCloseDate());
            //建手数料
            l_targetContractDetail.setSetupFee(l_row.getSetupFee());
            //建手数料消費税
            l_targetContractDetail.setSetupFeeTax(l_row.getSetupFeeTax());
            //名義書換料
            l_targetContractDetail.setNameTransferFee(l_row.getNameTransferFee());
            //名義書換料消費税
            l_targetContractDetail.setNameTransferFeeTax(l_row.getNameTransferFeeTax());
            //管理費
            l_targetContractDetail.setManagementFee(l_row.getManagementFee());
            //管理費消費税
            l_targetContractDetail.setManagementFeeTax(l_row.getManagementFeeTax());
            //順日歩
            l_targetContractDetail.setInterestFee(l_row.getInterestFee());
            //逆日歩
            l_targetContractDetail.setPayInterestFee(l_row.getPayInterestFee());
            //貸株料
            l_targetContractDetail.setLoanEquityFee(l_row.getLoanEquityFee());
            //保証金率
            l_targetContractDetail.setMarginDepositRate(l_row.getMarginDepositRate());
            //現金保証金率
            l_targetContractDetail.setCashMarginDepositRate(l_row.getCashMarginDepositRate());
            //税区分
            l_targetContractDetail.setTaxType(l_row.getTaxType());
            //弁済区分
            l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
            //弁済期限値
            l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());

            //当初建日
            l_targetContractDetail.setFirstOpenDate(l_row.getFirstOpenDate());

            //評価単価を求める
            double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_targetContractDetail);
            //評価単価
            l_targetContractDetail.setUnitPrice(l_dblUnitPrice);
            
            //load対象建玉
            WEB3TPTargetContract l_targetContract = loadTargetContract(true, l_targetContractDetail);
            
            //対象建玉一覧に対象建玉を追加
            List l_targetContracts = getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            //create建玉ごと変動情報
            WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
            //対象建玉と建玉ごと変動情報の関連づけ
            addHistoryPerContract(l_targetContract, l_historyPerContract);
            
            //create建玉変動
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //トランザクションカテゴリ
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
            //約定済フラグ
            l_history.setExecuted(true);
            //トランザクション発生日
            l_history.setTransactionDate(l_row.getOpenDate());
            //単価
            l_history.setPrice(l_row.getContractPrice());
            //株数
            l_history.setQuantity(l_row.getOriginalQuantity());
            //calc変動反映日
            l_history.calcReflectDay(null);
            
            //建玉変動を建玉ごと変動情報に追加
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do建玉情報<当日>ロード)<BR>
     * 当日の建玉情報をロードする。<BR>
     * <BR>
     * １）当日の建玉情報を取得する。<BR>
     * 　@　@データベースアクセス管理.get建玉情報<当日>をコールする。<BR>
     * 　@　@以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）対象建玉詳細を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・建玉ID　@　@　@　@　@＝建株テーブル.建玉ID<BR>
     * 　@　@　@・口座ID　@　@　@　@　@＝顧客属性.口座ID<BR>
     * 　@　@　@・補助口座ID　@　@　@＝顧客属性.補助口座ID<BR>
     * 　@　@　@・市場ID　@　@　@　@　@＝建株テーブル.市場ID<BR>
     * 　@　@　@・銘柄ID　@　@　@　@　@＝建株テーブル.銘柄ID<BR>
     * 　@　@　@・建区分　@　@　@　@　@＝建株テーブル.建区分<BR>
     * 　@　@　@・建単価　@　@　@　@　@＝建株テーブル.建単価<BR>
     * 　@　@　@・元建株数　@　@　@　@＝建株テーブル.元建株数<BR>
     * 　@　@　@・建株数　@　@　@　@　@＝建株テーブル.建株数<BR>
     * 　@　@　@・建日　@　@　@　@　@　@＝建株テーブル.建日<BR>
     * 　@　@　@・期日　@　@　@　@　@　@＝建株テーブル.期日<BR>
     * 　@　@　@・建手数料　@　@　@　@＝建株テーブル.建手数料<BR>
     * 　@　@　@・建手数料消費税　@＝建株テーブル.建手数料消費税<BR>
     * 　@　@　@・保証金率　@　@　@　@＝建株テーブル.保証金率<BR>
     * 　@　@　@・現金保証金率　@　@＝建株テーブル.現金保証金率<BR>
     * 　@　@　@・税区分　@　@　@　@　@＝建株テーブル.税区分<BR>
     * 　@　@　@・弁済区分　@　@　@　@＝建株テーブル.弁済区分<BR>
     * 　@　@　@・弁済期限値　@　@　@＝建株テーブル.弁済期限値<BR>
     * 　@　@　@・評価単価　@　@　@　@＝終値テーブル.終値(該当市場)<BR>
     * <BR>
     * ３）対象建玉情報を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・新規建約定済フラグ　@＝true<BR>
     * 　@　@　@・対象建玉詳細　@　@　@　@＝生成した対象建玉詳細<BR>
     * <BR>
     * ４）建玉ごと変動情報を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・建玉情報　@＝this<BR>
     * 　@　@　@・対象建玉　@＝生成した対象建玉<BR>
     * <BR>
     * ５）対象建玉と建玉ごと変動情報を関連づける。<BR>
     * <BR>
     * ６）建玉変動を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・トランザクションカテゴリ　@＝新規建<BR>
     * 　@　@　@・約定済フラグ　@　@　@　@　@　@　@＝true<BR>
     * 　@　@　@・トランザクション発生日　@　@＝建株テーブル.建日<BR>
     * 　@　@　@・単価　@　@　@　@　@　@　@　@　@　@　@＝建株テーブル.建単価<BR>
     * 　@　@　@・株数　@　@　@　@　@　@　@　@　@　@　@＝建株テーブル.建株数<BR>
     * 　@　@　@・変動反映開始日　@　@　@　@　@　@＝営業日(T+1)<BR>
     * 　@　@　@・変動反映終了日　@　@　@　@　@　@＝営業日(T+5)<BR>
     * 　@　@　@※変動反映開始日、変動反映終了日は建玉変動.calc変動反映日で設定する。<BR>
     * <BR>
     * ７）建玉ごと変動情報に建玉変動を追加する。<BR>
     * @@roseuid 40BE83F102DE
     */
    protected void loadTodaysContracts() 
    {
        final String STR_METHOD_NAME = "loadTodaysContracts()";

        //計算条件を取得
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        
        //営業日(T+0)を取得
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //評価単価Callbackを取得
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();

        //当日の建玉レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysContracts(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_contract row found.");
        
        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeContractRow l_row = (EqtypeContractRow)l_rows.get(i);
            
            //株式取引銘柄を取得
            EqtypeTradedProductRow l_eqTradedProduct = 
                WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this,
                                                                                l_row.getProductId(),
                                                                                l_row.getMarketId(),
                                                                                WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));

            //create対象建玉詳細
            WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
            
            //該当データが存在しない場合はデバッグログを出力して、基準値をセットしない。(初期値0のまま)
            if(l_eqTradedProduct == null)
            {
                if(DBG)
                {
                    StringBuffer l_strBuf = new StringBuffer("データが見つからないので基準値(前日終値)=0 株式取引銘柄マスターテーブルの検索条件:");
                    l_strBuf.append("institution_code=");
                    l_strBuf.append(this.getAccountInfo().getInstitutionCode());
                    l_strBuf.append(" product_id=");
                    l_strBuf.append(l_row.getProductId());
                    l_strBuf.append(" market_id=");
                    l_strBuf.append(l_row.getMarketId());
                    l_strBuf.append(" valid_until_biz_date=");
                    l_strBuf.append(WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));
                    String l_strMsg = l_strBuf.toString();
                    log.debug(l_strMsg);
                }
            }
            //基準値(前日終値)をセット
            else
            {
                //基準値(前日終値)
                l_targetContractDetail.setLastClosingPrice(l_eqTradedProduct.getLastClosingPrice());                
            }

            //建株ID
            l_targetContractDetail.setContractId(l_row.getContractId());
            //口座ID
            l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
            //補助口座ID
            l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
            //市場ID
            l_targetContractDetail.setMarketId(l_row.getMarketId());
            //銘柄ID
            l_targetContractDetail.setProductId(l_row.getProductId());
            //建区分
            l_targetContractDetail.setContractType(l_row.getContractType());
            //建単価
            l_targetContractDetail.setContractPrice(l_row.getContractPrice());
            //元建株数
            l_targetContractDetail.setOriginalQuantity(l_row.getOriginalQuantity());
            //建株数
            l_targetContractDetail.setQuantity(l_row.getQuantity());
            //建日
            l_targetContractDetail.setOpenDate(l_row.getOpenDate());
            //期日
            l_targetContractDetail.setCloseDate(l_row.getCloseDate());
            //建手数料
            l_targetContractDetail.setSetupFee(l_row.getSetupFee());
            //建手数料消費税
            l_targetContractDetail.setSetupFeeTax(l_row.getSetupFeeTax());
            
            //名義書換料
            l_targetContractDetail.setNameTransferFee(l_row.getNameTransferFee());
            //名義書換料消費税
            l_targetContractDetail.setNameTransferFeeTax(l_row.getNameTransferFeeTax());
            //管理費
            l_targetContractDetail.setManagementFee(l_row.getManagementFee());
            //管理費消費税
            l_targetContractDetail.setManagementFeeTax(l_row.getManagementFeeTax());
            //順日歩
            l_targetContractDetail.setInterestFee(l_row.getInterestFee());
            //逆日歩
            l_targetContractDetail.setPayInterestFee(l_row.getPayInterestFee());
            //貸株料
            l_targetContractDetail.setLoanEquityFee(l_row.getLoanEquityFee());

            //保証金率
            l_targetContractDetail.setMarginDepositRate(l_row.getMarginDepositRate());
            //現金保証金率
            l_targetContractDetail.setCashMarginDepositRate(l_row.getCashMarginDepositRate());
            //税区分
            l_targetContractDetail.setTaxType(l_row.getTaxType());
            //弁済区分
            l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
            //弁済期限値
            l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());

            //当初建日
            l_targetContractDetail.setFirstOpenDate(l_row.getFirstOpenDate());
            
            //評価単価を求める
            double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_targetContractDetail);
            //評価単価としてセット
            l_targetContractDetail.setUnitPrice(l_dblUnitPrice);

            //load対象建玉
            WEB3TPTargetContract l_targetContract = loadTargetContract(true, l_targetContractDetail);
            
            //対象建玉一覧に対象建玉を追加
            List l_targetContracts = getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            //create建玉ごと変動情報
            WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
            //対象建玉と建玉ごと変動情報の関連づけ
            addHistoryPerContract(l_targetContract, l_historyPerContract);
            
            //create建玉変動
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //トランザクションカテゴリ
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
            //約定済フラグ
            l_history.setExecuted(true);
            //トランザクション発生日
            l_history.setTransactionDate(l_row.getOpenDate());
            //単価
            l_history.setPrice(l_row.getContractPrice());
            //株数
            l_history.setQuantity(l_row.getQuantity());
            //calc変動反映日
            l_history.calcReflectDay(null);
            
            //建玉変動を建玉ごと変動情報に追加
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do建玉返済指定情報ロード)<BR>
     * 建玉返済指定情報をロードする。<BR>
     * <BR>
     * １）建玉返済指定情報を取得する。<BR>
     * 　@　@データベースアクセス管理.get建玉返済指定情報をコールする。<BR>
     * 　@　@以降の処理は、検索結果の各行に対して実施する。<BR>
     * <BR>
     * ２）対象建玉を取得する。<BR>
     * <BR>
     * ３）対象建玉情報を取得する。<BR>
     * <BR>
     * ４）建玉返済指定情報を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・建玉ID　@　@　@　@＝建玉返済指定情報テーブル.建玉ID<BR>
     * 　@　@　@・建単価　@　@　@　@＝対象建玉詳細.建単価<BR>
     * 　@　@　@・返済注文数量　@＝建玉返済指定情報テーブル.返済注文数量<BR>
     * 　@　@　@・返済約定数量　@＝建玉返済指定情報テーブル.返済約定数量<BR>
     * <BR>
     * ５）ロード済みの注文か判定し、処理を振り分ける。<BR>
     * 　@　@－ロード済みの場合<BR>
     * 　@　@　@・建玉返済指定情報一覧を取得する。<BR>
     * <BR>
     * 　@　@－ロード済みでない場合<BR>
     * 　@　@　@・建玉返済指定情報一覧を生成する。<BR>
     * <BR>
     * ６）建玉返済指定情報一覧に生成した建玉返済指定情報を追加する。<BR>
     * <BR>
     * ７）注文と建玉返済指定情報一覧を関連づける。<BR>
     * @@roseuid 40EE04910141
     */
    protected void loadClosingContractSpecs() 
    {
        //建玉返済指定情報レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getClosingContractSpecs(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_closing_contract_spec row found.");
        
        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow)l_rows.get(i);
            
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());
            
            //対象建玉詳細を取得
            WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
            
            //create建玉返済指定情報
            WEB3TPClosingContractSpec l_closingContractSpec = WEB3TPClosingContractSpec.create();
            //建株ID
            l_closingContractSpec.setContractId(l_row.getContractId());
            //建単価
            l_closingContractSpec.setContractPrice(l_targetContractDetail.getContractPrice());
            //返済注文数量
            l_closingContractSpec.setQuantity(l_row.getQuantity());
            //返済約定数量
            l_closingContractSpec.setExecutedQuantity(l_row.getExecutedQuantity());

            //建玉返済指定情報一覧に建玉返済指定情報を追加
            List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());

            l_closingContractSpecs.add(l_closingContractSpec);
            
            //注文単位IDと建玉返済指定情報一覧の関連づけ
            addClosingContractSpecsPerOrderUnit(l_row.getOrderUnitId(), l_closingContractSpecs);
        }
    }
    
    /**
     * (do建玉変動情報<確定>ロード)<BR>
     * 建玉変動情報<確定>をロードする <BR>
     * <BR>
     * シーケンス図「(建玉情報)do建玉変動情報<確定>ロード」参照<BR>
     * @@roseuid 40C843C50251
     */
    protected void loadFixedHistories() 
    {
        //確定の建玉変動レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getFixedHistories(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " fixed_fin_transaction row found.");

        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            FixedFinTransactionRow l_row = (FixedFinTransactionRow)l_rows.get(i);
            
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getFixedContractId());
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
            
            //元建株数の復元
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            double l_dblRestoreQuantity = l_targetContractDetail.getOriginalQuantity() + l_row.getQuantity();
            l_targetContractDetail.setOriginalQuantity(l_dblRestoreQuantity);
            
            //create建玉変動
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //トランザクションカテゴリ
            l_history.setTransactionCateg(l_row.getFinTransactionCateg());
            //約定済フラグ
            l_history.setExecuted(true);
            //トランザクション発生日
            l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
            //単価
            l_history.setPrice(l_targetContractDetail.getContractPrice());
            //株数
            l_history.setQuantity(l_row.getQuantity());
            //受渡日
            l_history.setDeliveryDate(l_row.getDeliveryDate());
            //calc変動反映日
            l_history.calcReflectDay(l_row.getDeliveryDate());
            //返済の場合
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //受渡代金
                l_history.setNetAmount(l_row.getNetAmount());
            }

            //確定トランザクションテーブル.トランザクションカテゴリ = 60:現引現渡
            //　@以降の処理を実行
            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                String l_strInstBranCalcCondition =
                    this.getCalcCondition().getInstBranCalcCondition(
                         WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);
                //this.get余力計算条件.get会社部店別余力計算条件
                //  （”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = ”1”の場合
                //　@　@以降の処理を実行
                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                    l_strInstBranCalcCondition))
                {
                    //建手数料
                    BigDecimal l_bdImportedSetupFee =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //建手数料消費税
                    BigDecimal l_bdImportedSetupFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //名義書換料
                    BigDecimal l_bdImportedNameTransferFee =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //名義書換料消費税
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //管理費
                    BigDecimal l_bdImportedManagementFee =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //管理費消費税
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    //順日歩
                    BigDecimal l_bdImportedInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                    //逆日歩
                    BigDecimal l_bdImportedPayInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                    //貸株料
                    BigDecimal l_bdImportedLoanEquityFee =
                        new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                    //対象建玉詳細.建区分 = ”買建”の場合
                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //建玉諸経費　@=　@確定トランザクションテーブル.建手数料　@+　@確定トランザクションテーブル.建手数料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.名義書換料　@+　@確定トランザクションテーブル.名義書換料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.管理費　@+　@確定トランザクションテーブル.管理費消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.順日歩
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(l_bdImportedNameTransferFee).add(
                                l_bdImportedNameTransferFeeTax).add(l_bdImportedManagementFee).add(
                                    l_bdImportedManagementFeeTax).add(l_bdImportedInterestFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                    //対象建玉詳細.建区分 = ”売建”の場合
                    else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                    {
                        //建玉諸経費　@=　@確定トランザクションテーブル.建手数料　@+　@確定トランザクションテーブル.建手数料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.名義書換料　@+　@確定トランザクションテーブル.名義書換料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.管理費　@+　@確定トランザクションテーブル.管理費消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.逆日歩　@+　@確定トランザクションテーブル.貸株料
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                             l_bdImportedSetupFeeTax).add(l_bdImportedNameTransferFee).add(
                                l_bdImportedNameTransferFeeTax).add(l_bdImportedManagementFee).add(
                                    l_bdImportedManagementFeeTax).add(l_bdImportedPayInterestFee).add(
                                        l_bdImportedLoanEquityFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                }
                //this.get余力計算条件.get会社部店別余力計算条件（
                //   ”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = NULL
                //　@　@　@　@又は、this.get余力計算条件.get会社部店別余力計算条件（
                //   ”eqtype.swap.margin.cost.undelivered.contract.loss.div”） != ”1”の場合
                //　@　@建玉諸経費＝0
                else
                {
                    l_history.setContractTotalCost(0.0D);
                }
            }
            //上記以外の場合
            //　@建玉諸経費＝0
            else
            {
                l_history.setContractTotalCost(0.0D);
            }

            //建玉変動を建玉ごと変動情報に追加
            l_historyPerContract.addHistory(l_history);
        }

        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();
        
        //対象建玉一覧のサイズでループ
        int l_intContractSize = l_targetContracts.size();
        for(int i = 0; i < l_intContractSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //新規建約定済
            if(l_targetContract.isContractExecuted())
            {
                //対象建玉詳細を取得
                WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
                
                Date l_datOpenDate = l_targetContractDetail.getOpenDate();
                Date l_datDate0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
                double l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();
                double l_dblQuantity = l_targetContractDetail.getQuantity();
                
                long l_lngOriginalQuantity = new Double(l_dblOriginalQuantity).longValue();
                long l_lngQuantity = new Double(l_dblQuantity).longValue();

                //建日<営業日(T+0) 且つ 元建株数!=建株数
                if((WEB3DateUtility.compareToDay(l_datOpenDate,l_datDate0) < 0)
                    && (l_lngOriginalQuantity != l_lngQuantity))
                {
                    //建株数=0は、DBの手数料等の項目が0になっているので、無処理
                    if(l_lngQuantity > 0)
                    {
                        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
                        BigDecimal l_bdOriginalQuantity = new BigDecimal(Long.toString(l_lngOriginalQuantity));
                        BigDecimal l_bdQuantity = new BigDecimal(Long.toString(l_lngQuantity));
                        //按分を戻す比率
                        double l_dblRate = l_bdOriginalQuantity.divide(l_bdQuantity, l_intScale, BigDecimal.ROUND_HALF_UP).doubleValue();

                        if(DBG)
                        {
                            StringBuffer l_sbLog = new StringBuffer("contract_id=");
                            l_sbLog.append(l_targetContractDetail.getContractId());
                            l_sbLog.append(" reverse_proportional_rate=");
                            l_sbLog.append(l_dblRate);
                            log.debug(l_sbLog.toString());
                        }
                        
                        BigDecimal l_bdRate = new BigDecimal(Double.toString(l_dblRate));
                        //建手数料
                        double l_dblSetupFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getSetupFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setSetupFee(l_dblSetupFee);
                    
                        //建手数料消費税
                        double l_dblSetupFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getSetupFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setSetupFeeTax(l_dblSetupFeeTax);
                    
                        //名義書換料
                        double l_dblNameTransferFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setNameTransferFee(l_dblNameTransferFee);
                    
                        //名義書換料消費税
                        double l_dblNameTransferFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setNameTransferFeeTax(l_dblNameTransferFeeTax);
                    
                        //管理費
                        double l_dblManagementFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getManagementFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setManagementFee(l_dblManagementFee);
                    
                        //管理費消費税
                        double l_dblManagementFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getManagementFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setManagementFeeTax(l_dblManagementFeeTax);
                    
                        //順日歩
                        double l_dblInterestFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getInterestFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setInterestFee(l_dblInterestFee);
                    
                        //逆日歩
                        double l_dblPayInterestFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getPayInterestFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setPayInterestFee(l_dblPayInterestFee);
                    
                        //貸株料
                        double l_dblLoanEquityFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getLoanEquityFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setLoanEquityFee(l_dblLoanEquityFee);
                    }

                    //建玉ごと変動情報を取得
                    WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                    
                    //建玉変動一覧を取得
                    List l_histories = l_historyPerContract.getHistories();
                    
                    //建玉変動一覧のサイズでループ
                    int l_intHistorySize = l_histories.size();
                    for(int j = 0; j < l_intHistorySize; j++)
                    {
                        //建玉変動を取得
                        WEB3TPHistory l_history =  (WEB3TPHistory)l_histories.get(j);
                        
                        //新規建
                        if(FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_history.getTransactionCateg()))
                        {
                            l_history.setQuantity(l_dblOriginalQuantity);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * (do建玉変動情報<当日>ロード)<BR>
     * 建玉変動情報<当日>をロードする<BR>
     * <BR>
     * シーケンス図「(建玉情報)do建玉変動情報<当日>ロード」参照<BR>
     * @@roseuid 40DBBCCD0196
     */
    protected void loadTodaysHistories() 
    {
        //当日の建玉変動レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysHistories(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_fin_transaction row found.");

        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_rows.get(i);
            
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());
            
            //建玉ごと変動情報を取得
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
            
            //対象建玉詳細を取得
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            
            //create建玉変動
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //トランザクションカテゴリ
            l_history.setTransactionCateg(l_row.getFinTransactionCateg());
            //約定済フラグ
            l_history.setExecuted(true);
            //トランザクション発生日
            l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
            //単価
            l_history.setPrice(l_targetContractDetail.getContractPrice());
            //株数
            l_history.setQuantity(l_row.getQuantity());
            //受渡日
            l_history.setDeliveryDate(l_row.getDeliveryDate());
            //calc変動反映日
            l_history.calcReflectDay(l_row.getDeliveryDate());
            //注文単位ID
            l_history.setOrderUnitId(l_row.getOrderUnitId());
            //返済の場合
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //受渡代金
                l_history.setNetAmount(l_row.getNetAmount());
            }

            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //確定トランザクションテーブル.トランザクションカテゴリ = 60:現引現渡
                //以降の処理を実行
                String l_strInstBranCalcCondition =
                    this.getCalcCondition().getInstBranCalcCondition(
                         WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);

                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                    l_strInstBranCalcCondition))
                {
                    //this.get余力計算条件.get会社部店別余力計算条件
                    //（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = ”1”の場合
                    //以降の処理を実行

                    //建手数料
                    BigDecimal l_bdImportedSetupFee =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //建手数料消費税
                    BigDecimal l_bdImportedSetupFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //名義書換料
                    BigDecimal l_bdImportedNameTransferFee =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //名義書換料消費税
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //管理費
                    BigDecimal l_bdImportedManagementFee =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //管理費消費税
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    //順日歩
                    BigDecimal l_bdImportedInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                    //逆日歩
                    BigDecimal l_bdImportedPayInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                    //貸株料
                    BigDecimal l_bdImportedLoanEquityFee =
                        new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //対象建玉詳細.建区分 = ”買建”の場合

                        //建玉諸経費　@=　@確定トランザクションテーブル.建手数料　@+　@確定トランザクションテーブル.建手数料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.名義書換料　@+　@確定トランザクションテーブル.名義書換料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.管理費　@+　@確定トランザクションテーブル.管理費消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.順日歩
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedInterestFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                    else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                    {
                        //対象建玉詳細.建区分 = ”売建”の場合

                        //建玉諸経費　@=　@確定トランザクションテーブル.建手数料　@+　@確定トランザクションテーブル.建手数料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.名義書換料　@+　@確定トランザクションテーブル.名義書換料消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.管理費　@+　@確定トランザクションテーブル.管理費消費税
                        //　@　@　@　@　@　@+　@確定トランザクションテーブル.逆日歩　@+　@確定トランザクションテーブル.貸株料
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedPayInterestFee).add(
                            l_bdImportedLoanEquityFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                }
                else
                {
                    //上記以外の場合
                    l_history.setContractTotalCost(0.0D);
                }
            }
            else
            {
                //上記以外の場合
                l_history.setContractTotalCost(0.0D);
            }

            //建玉変動を建玉ごと変動情報に追加
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do未約定変動情報ロード)<BR>
     * 未約定変動情報ををロードする<BR>
     * <BR>
     * シーケンス図「(建玉情報)do未約定変動情報ロード」参照<BR>
     * @@roseuid 40DBBD46030D
     */
    protected void loadUnexecutedOrderSpecs() 
    {
        final String STR_METHOD_NAME = "loadUnexecutedOrderSpecs()";

        //未約定変動情報レコードセットを取得
        List l_rows = WEB3TPPersistentDataManager.getInstance().getUnExecutedOrders(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_order_unit row found.");

        //取得行のサイズでループ
        for(int i = 0; i < l_intSize; i++)
        {
            //レコードを取得
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_rows.get(i);
            
            //現引現渡の場合
            if(OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg()))
            {
                //建玉返済指定情報一覧を取得
                List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());
                
                //建玉返済指定情報一覧のサイズでループ
                int l_intClosingSize = l_closingContractSpecs.size();
                for(int j = 0; j < l_intClosingSize; j++)
                {
                    //建玉返済指定情報を取得
                    WEB3TPClosingContractSpec l_closingContractSpec = (WEB3TPClosingContractSpec)l_closingContractSpecs.get(j);
                    
                    //対象建玉を取得
                    WEB3TPTargetContract l_targetContract = getTargetContract(true, l_closingContractSpec.getContractId());
                    
                    //建玉ごと変動情報を取得
                    WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                    
                    //create建玉変動
                    WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
                    //トランザクションカテゴリ
                    l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
                    //約定済フラグ
                    l_history.setExecuted(false);
                    //トランザクション発生日
                    l_history.setTransactionDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                    //単価
                    l_history.setPrice(l_targetContract.getTargetContractDetail().getContractPrice());
                    //株数
                    l_history.setQuantity(l_closingContractSpec.getQuantity() - l_closingContractSpec.getExecutedQuantity());
                    //受渡日
                    l_history.setDeliveryDate(l_row.getDeliveryDate());

                    String l_strInstBranCalcCondition =
                        this.getCalcCondition().getInstBranCalcCondition(
                            WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);

                    if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                        l_strInstBranCalcCondition))
                    {
                        //this.get余力計算条件.get会社部店別余力計算条件
                        //（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） = ”1”の場合
                        //以降の処理を実行

                        //対象建玉詳細
                        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

                        //対象建玉詳細.元建株数
                        double l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();

                        long l_lngOriginalQuantity = new Double(l_dblOriginalQuantity).longValue();
                        if (l_lngOriginalQuantity > 0)
                        {
                            //対象建玉詳細.元建株数 > 0の場合
                            //以降の処理を実行

                            BigDecimal l_bdRatio = new BigDecimal("0.0");
                            BigDecimal l_bdContractQuantity = new BigDecimal(Double.toString(l_closingContractSpec.getQuantity()));
                            BigDecimal l_bdExecutedQuantity =
                                new BigDecimal(Double.toString(l_closingContractSpec.getExecutedQuantity()));
                            BigDecimal l_bdQuantity = l_bdContractQuantity.subtract(l_bdExecutedQuantity);
                            long l_lngQuantity = l_bdQuantity.longValue();

                            if (l_lngQuantity == l_lngOriginalQuantity)
                            {
                                //対象建玉詳細.元建株数 = 上記で計算した株数の場合
                                //按分比率 = 1
                                l_bdRatio = new BigDecimal("1.0");
                            }
                            else
                            {
                                //上記以外の場合
                                //按分比率 = 上記で計算した株数 / 元建株数
                                //(除算結果の丸めを行わない)
                                BigDecimal l_bdOriginalQuantity = new BigDecimal(Double.toString(l_lngOriginalQuantity));

                                int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
                                l_bdRatio =
                                    l_bdQuantity.divide(l_bdOriginalQuantity, l_intScale, BigDecimal.ROUND_HALF_UP);
                            }

                            //建手数料
                            BigDecimal l_bdSetupFee = new BigDecimal(Double.toString(l_targetContractDetail.getSetupFee()));

                            //建手数料消費税
                            BigDecimal l_bdSetupFeeTax = new BigDecimal(Double.toString(l_targetContractDetail.getSetupFeeTax()));

                            //名義書換料
                            BigDecimal l_bdNameTransferFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFee()));

                            //名義書換料消費税
                            BigDecimal l_bdNameTransferFeeTax =
                                new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFeeTax()));

                            //管理費
                            BigDecimal l_bdManagementFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getManagementFee()));

                            //管理費消費税
                            BigDecimal l_bdManagementFeeTax =
                                new BigDecimal(Double.toString(l_targetContractDetail.getManagementFeeTax()));

                            //順日歩
                            BigDecimal l_bdInterestFee = new BigDecimal(Double.toString(l_targetContractDetail.getInterestFee()));

                            //逆日歩
                            BigDecimal l_bdPayInterestFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getPayInterestFee()));

                            //貸株料
                            BigDecimal l_bdLoanEquityFee = new BigDecimal(Double.toString(
                                l_targetContractDetail.getLoanEquityFee()));

                            //Math.floor（対象建玉詳細.建手数料　@×　@按分比率）
                            BigDecimal l_bdSetupFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdSetupFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.建手数料消費税　@×　@按分比率）
                            BigDecimal l_bdSetupFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdSetupFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.名義書換料　@×　@按分比率）
                            BigDecimal l_bdNameTransferFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdNameTransferFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.名義書換料消費税　@×　@按分比率）
                            BigDecimal l_bdNameTransferFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdNameTransferFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.管理費　@×　@按分比率）
                            BigDecimal l_bdManagementFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdManagementFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.管理費消費税　@×　@按分比率）
                            BigDecimal l_bdManagementFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdManagementFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.順日歩　@×　@按分比率）
                            BigDecimal l_bdInterestFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdInterestFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.逆日歩　@×　@按分比率）
                            BigDecimal l_bdPayInterestFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdPayInterestFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor（対象建玉詳細.貸株料　@×　@按分比率）
                            BigDecimal l_bdLoanEquityFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdLoanEquityFee.multiply(l_bdRatio).doubleValue())));

                            if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                            {
                                //対象建玉詳細.建区分 = "買建"の場合

                                BigDecimal l_bdContractTotalCost =
                                    l_bdSetupFeeRatio.add(
                                    l_bdSetupFeeTaxRatio).add(
                                    l_bdNameTransferFeeRatio).add(
                                    l_bdNameTransferFeeTaxRatio).add(
                                    l_bdManagementFeeRatio).add(
                                    l_bdManagementFeeTaxRatio).add(
                                    l_bdInterestFeeRatio);
                                l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                            }
                            else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                            {
                                //対象建玉詳細.建区分 = "売建"の場合

                                BigDecimal l_bdContractTotalCost =
                                    l_bdSetupFeeRatio.add(
                                    l_bdSetupFeeTaxRatio).add(
                                    l_bdNameTransferFeeRatio).add(
                                    l_bdNameTransferFeeTaxRatio).add(
                                    l_bdManagementFeeRatio).add(
                                    l_bdManagementFeeTaxRatio).add(
                                    l_bdPayInterestFeeRatio).add(
                                    l_bdLoanEquityFeeRatio);
                                l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                            }
                        }
                        else
                        {
                            //対象建玉詳細.元建株数 <= 0の場合
                            //建玉諸経費 = 0
                            l_history.setContractTotalCost(0.0D);
                        }
                    }
                    else
                    {
                        //this.get余力計算条件.get会社部店別余力計算条件
                        //（”eqtype.swap.margin.cost.undelivered.contract.loss.div”） != ”1”の場合
                        //建玉諸経費 = 0
                        l_history.setContractTotalCost(0.0D);
                    }

                    //calc変動反映日
                    l_history.calcReflectDay(l_row.getDeliveryDate());
                    
                    //建玉変動を建玉ごと変動情報に追加
                    l_historyPerContract.addHistory(l_history);
                }
                
                //返済指定情報一覧のサイズ=0の場合(取消注文の場合)
                if(l_intClosingSize == 0)
                {
                    if(GtlUtils.Double.isZero(l_row.getPrice()))
                    {
                        if(GtlUtils.Double.isZero(l_row.getQuantity()))
                        {
                            //対象建玉一覧を取得
                            List l_targetContracts = getTargetContracts();

                            //対象建玉一覧のサイズでループ
                            int l_intContractSize = l_targetContracts.size();
                            for(int j = 0; j < l_intContractSize; j++)
                            {
                                //対象建玉を取得
                                WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(j);
                                
                                //建玉ごと変動情報を取得
                                WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                                
                                //変動情報を取得
                                List l_lisHistorys = l_historyPerContract.getHistories();
                                
                                Iterator l_iter = l_lisHistorys.iterator();
                                while(l_iter.hasNext())
                                {
                                    WEB3TPHistory l_history = (WEB3TPHistory)l_iter.next();
                                    
                                    //注文単位IDが等しい時は、上書きする(単価と数量=0になる)
                                    if(l_history.getOrderUnitId() == l_row.getOrderUnitId())
                                    {
                                        l_history.setPrice(l_row.getPrice());
                                        l_history.setQuantity(l_row.getQuantity());
                                        
                                        if(DBG)
                                        {
                                            StringBuffer l_sbLog = new StringBuffer("swap cancel complete:contract_id=");
                                            l_sbLog.append(l_targetContract.getTargetContractDetail().getContractId());
                                            l_sbLog.append(" history=");
                                            l_sbLog.append(l_history.toString());
                                            log.debug(l_sbLog.toString());
                                        }
                                    }
                                }
                           }
                        }
                    }
                }
            }
            //新規建の場合
            else if(OrderCategEnum.OPEN_MARGIN.equals(l_row.getOrderCateg()))
            {
                //株式取引銘柄を取得
                EqtypeTradedProductRow l_eqTradedProduct = 
                    WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this, l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                //create対象建玉詳細
                WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
                //建株ID
                l_targetContractDetail.setContractId(l_row.getOrderUnitId());
                //口座ID
                l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
                //補助口座ID
                l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
                //市場ID
                l_targetContractDetail.setMarketId(l_row.getMarketId());
                //銘柄ID
                l_targetContractDetail.setProductId(l_row.getProductId());
                //建区分
                l_targetContractDetail.setContractType(toContractTypeEnum(l_row.getOrderType()));
                //建単価
                l_targetContractDetail.setContractPrice(l_row.getPrice());
                //元建株数
                l_targetContractDetail.setOriginalQuantity(l_row.getQuantity() - l_row.getExecutedQuantity());
                //建日
                l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                if(OrderTypeEnum.MARGIN_LONG.equals(l_row.getOrderType()))
                {
                    if(l_eqTradedProduct != null)
                    {
                        //保証金率
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProduct.getLongMarginDepositRate());
                        //現金保証金率
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProduct.getLongCashMarginDepositRate());                    
                    }else
                    {                        
                        log.debug("search eqtype_traded_product_updq because eqtype_traded_product is not found");
                        
                        //株式取引銘柄Updqを取得
                        EqtypeTradedProductUpdqRow l_eqTradedProductUpdq = 
                            WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProductUpdq(l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                        //該当データが存在しない場合は例外
                        if(l_eqTradedProductUpdq == null)
                        {
                            StringBuffer l_strErrorBuf = new StringBuffer("株式取引銘柄マスターUpdqテーブルの検索条件:");
                            l_strErrorBuf.append("product_id=");
                            l_strErrorBuf.append(l_row.getProductId());
                            l_strErrorBuf.append(" market_id=");
                            l_strErrorBuf.append(l_row.getMarketId());
                            l_strErrorBuf.append(" valid_until_biz_date=");
                            l_strErrorBuf.append(l_row.getBizDate());
                            String l_strErrorMsg = l_strErrorBuf.toString();
                            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMsg);
                        }
                        
                        //保証金率
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProductUpdq.getLongMarginDepositRate());
                        //現金保証金率
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProductUpdq.getLongCashMarginDepositRate());                    
                    }
                }
                else if(OrderTypeEnum.MARGIN_SHORT.equals(l_row.getOrderType()))
                {
                    if(l_eqTradedProduct != null)
                    {
                        //保証金率
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProduct.getShortMarginDepositRate());
                        //現金保証金率
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProduct.getShortCashMarginDepositRate());
                    }else
                    {
                        log.debug("search eqtype_traded_product_updq because eqtype_traded_product is not found");

                        //株式取引銘柄Updqを取得
                        EqtypeTradedProductUpdqRow l_eqTradedProductUpdq = 
                            WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProductUpdq(l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                        //該当データが存在しない場合は例外
                        if(l_eqTradedProductUpdq == null)
                        {
                            StringBuffer l_strErrorBuf = new StringBuffer("株式取引銘柄マスターUpdqテーブルの検索条件:");
                            l_strErrorBuf.append("product_id=");
                            l_strErrorBuf.append(l_row.getProductId());
                            l_strErrorBuf.append(" market_id=");
                            l_strErrorBuf.append(l_row.getMarketId());
                            l_strErrorBuf.append(" valid_until_biz_date=");
                            l_strErrorBuf.append(l_row.getBizDate());
                            String l_strErrorMsg = l_strErrorBuf.toString();
                            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMsg);
                        }
                        
                        //保証金率
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProductUpdq.getShortMarginDepositRate());
                        //現金保証金率
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProductUpdq.getShortCashMarginDepositRate());
                    }
                }
                //税区分
                l_targetContractDetail.setTaxType(l_row.getTaxType());
                //弁済区分
                l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
                //弁済期限値
                l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());
            
                 //load対象建玉
                WEB3TPTargetContract l_targetContract = loadTargetContract(false, l_targetContractDetail);
            
                //対象建玉一覧に対象建玉を追加
                List l_targetContracts = getTargetContracts();
                l_targetContracts.add(l_targetContract);
            
                //create建玉ごと変動情報
                WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
                //対象建玉と建玉ごと変動情報の関連づけ
                addHistoryPerContract(l_targetContract, l_historyPerContract);
            
                //create建玉変動
                WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
                //トランザクションカテゴリ
                l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
                //約定済フラグ
                l_history.setExecuted(false);
                //トランザクション発生日
                l_history.setTransactionDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                //単価
                l_history.setPrice(l_row.getPrice());
                //株数
                l_history.setQuantity(l_row.getQuantity() - l_row.getExecutedQuantity());
                //calc変動反映日
                l_history.calcReflectDay(l_row.getDeliveryDate());
            
                //建玉変動を建玉ごと変動情報に追加
                l_historyPerContract.addHistory(l_history);
            }
        }
    }
    
    /**
     * (load対象建玉)<BR>
     * 対象建玉を生成し、対象建玉を返す。<BR>
     * <BR>
     * １）対象建玉を生成する。<BR>
     * 　@　@－設定項目<BR>
     * 　@　@　@・新規建約定済フラグ　@＝引数の新規建約定済フラグ<BR>
     * 　@　@　@・対象建玉詳細　@　@　@　@＝引数の対象建玉詳細<BR>
     * <BR>
     * ２）対象建玉を返す。<BR>
     * @@param l_isNewContractExecuted - (新規建約定済フラグ)
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40C843F803A9
     */
    protected WEB3TPTargetContract loadTargetContract(boolean l_isNewContractExecuted, WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        //対象建玉オブジェクト
        WEB3TPTargetContract l_targetContract = WEB3TPTargetContract.create();
        
        //新規建約定済フラグをセット
        l_targetContract.setContractExecuted(l_isNewContractExecuted);
        
        //対象建玉詳細オブジェクトをセット
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        
        return l_targetContract;
    }
    
    /**
     * (get対象建玉)<BR>
     * 対象建玉を取得する。<BR>
     * <BR>
     * １）対象建玉一覧を取得する。<BR>
     * <BR>
     * ２）対象建玉を検索する。<BR>
     * 　@－検索条件<BR>
     * 　@　@・引数の新規建約定済フラグ<BR>
     * 　@　@・引数の建玉ID<BR>
     * <BR>
     * ３）引数の建玉IDと一致する対象建玉が存在する場合は、該当する対象建玉を返す。<BR>
     * 　@　@存在しない場合はnullを返す。<BR>
     * @@param l_isNewContractExecuted - (新規建約定済フラグ)
     * @@param l_lngContractId - (建玉ID)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DBBF8E035B
     */
    protected WEB3TPTargetContract getTargetContract(boolean l_isNewContractExecuted, long l_lngContractId) 
    {
        final String STR_METHOD_NAME = "getTargetContract(boolean l_isNewContractExecuted, long l_lngContractId)";

        //対象建玉一覧を取得
        List l_targetContracts = getTargetContracts();
        
        //一覧のサイズでループ
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //新規建約定済フラグが等しい
            if(l_isNewContractExecuted == l_targetContract.isContractExecuted())
            {
                //建玉IDが等しい
                if(l_lngContractId == l_targetContract.getTargetContractDetail().getContractId())
                {
                    return l_targetContract;
                }
            }
        }
        
        //元建玉が存在しない状態になるので例外
        String l_strErroMsg = "対象建玉の検索条件:contractExecutedFlag=" + l_isNewContractExecuted
                            + " contract_id=" + l_lngContractId;
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErroMsg);
    }
    
    /**
     * (add建玉ごと変動情報)<BR>
     * 対象建玉と建玉ごと変動情報を関連づける。<BR>
     * <BR>
     * HashMapに登録する。<BR>
     * －キー：引数の対象建玉<BR>
     * －値　@：引数の建玉ごと変動情報<BR>
     * @@param l_targetContract - (対象建玉)
     * @@param l_historyPerContract - (建玉ごと変動情報)
     * @@roseuid 40DBBF8E03A9
     */
    protected void addHistoryPerContract(WEB3TPTargetContract l_targetContract, WEB3TPHistoryPerContract l_historyPerContract) 
    {
        contractHistories.put(l_targetContract, l_historyPerContract);
    }
    
    /**
     * (get建玉ごと変動情報)<BR>
     * 引数の対象建玉に関連する建玉ごと変動情報を取得する。<BR>
     * @@param l_targetContract - (対象建玉)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract
     * @@roseuid 40DC09E50289
     */
    protected WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract) 
    {
        return (WEB3TPHistoryPerContract)contractHistories.get(l_targetContract);
    }
    
    /**
     * (add注文単位ごと建玉返済指定情報一覧)<BR>
     * 注文単位IDと建玉返済指定情報一覧を関連づける。<BR>
     * <BR>
     * HashMapに登録する。<BR>
     * －キー：引数の注文単位IDのLongオブジェクト<BR>
     * －値　@：引数の建玉返済指定情報一覧<BR>
     * @@param l_orderUnitId - (注文単位ID)
     * @@param l_closingContractSpecs - (建玉返済指定情報)
     * @@roseuid 40EE0E1703E1
     */
    protected void addClosingContractSpecsPerOrderUnit(long l_lngOrderUnitId, List l_closingContractSpecs) 
    {
        Long l_orderUnitId = new Long(l_lngOrderUnitId);
        closingContractSpecs.put(l_orderUnitId, l_closingContractSpecs);
    }
    
    /**
     * (to建区分)<BR>
     * 注文種別を建区分に変換する。<BR>
     * <BR>
     * １）以下の対応で変換を行う。<BR>
     * 　@－注文種別　@　@　@　@　@　@　@　@　@建区分<BR>
     *　@　@・株式信用新規買建注文　@→　@買建<BR>
     *　@　@・株式信用新規売立注文　@→　@売建<BR>
     * <BR>
     * ２）建区分を返す。<BR>
     * @@param l_orderType - (注文種別)
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum
     * @@roseuid 
     */
    protected ContractTypeEnum toContractTypeEnum(OrderTypeEnum l_orderType) 
    {
        final String STR_METHOD_NAME = "toContractTypeEnum(OrderTypeEnum l_orderType)";

        ContractTypeEnum l_contractTypeEnum = null;
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_contractTypeEnum = ContractTypeEnum.LONG;
        }
        else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_contractTypeEnum = ContractTypeEnum.SHORT;
        }else
        {
            String l_strErroMsg = "建区分に変換したいOrderTypeEnum=" + l_orderType;
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErroMsg);
        }
        
        return l_contractTypeEnum;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.appendSuper(super.toString());
        
        //対象建玉
        List l_targetContracts = this.getTargetContracts();
        int i = 0;
        //建玉ごと変動情報で対象建玉をtoStringしてるため、不要とする
//        for(Iterator l_it = l_targetContracts.iterator();l_it.hasNext();)
//        {
//            l_builder.append("getTargetContracts" + "[" + i +"]",l_it.next());
//            i += 1;
//        }
        
        //建玉ごと変動情報
        i = 0;
        for(Iterator l_it = contractHistories.keySet().iterator();l_it.hasNext();)
        {
            WEB3TPTargetContract l_key = (WEB3TPTargetContract)l_it.next();
//            l_builder.append("contractHistories" + "[" + i +"]" + ".key:",l_key);
//            l_builder.append("contractHistories" + "[" + i +"]" + ".value:",contractHistories.get(l_key));
            l_builder.append("contractHistories" + "[" + i +"]",contractHistories.get(l_key));
            i += 1;
        }
        
        //建玉返済指定情報
        i = 0;
        for(Iterator l_it = closingContractSpecs.keySet().iterator();l_it.hasNext();)
        {
            Long l_key = (Long)l_it.next();
            l_builder.append("closingContractSpecs" + "[" + i +"]" + ".key",l_key);
            l_builder.append("closingContractSpecs" + "[" + i +"]" + ".value",closingContractSpecs.get(l_key));
            i += 1;
        }
        
        return l_builder.toString();
    }
    
    /**
     * (get当日返済建玉代金の集計)<BR>
     * (get当日返済建玉代金の集計)<BR>
     * <BR>
     * 集計した当日返済建玉代金を返却する。<BR>
     * （戻り値の型：double） <BR>
     * <BR>
     * １）　@当日建玉代金計上開始基準日を取得する。<BR>
     * 　@［取得方法@］<BR>
     * <BR>
     * 　@（1）this.get余力計算条件().get会社部店別余力計算条件（<BR>
     * 　@　@　@　@　@　@"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、<BR>
     * 　@　@　@1：FROM_BIZ_DATEの場合<BR>
     * <BR>
     * 　@　@　@・this.get余力計算条件().get営業日(T+0)<BR>
     * <BR>
     * 　@（2）this.get余力計算条件().get会社部店別余力計算条件（<BR>
     * 　@　@　@　@　@　@"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、<BR>
     * 　@　@　@2：FROM_T2の場合<BR>
     * <BR>
     * 　@　@　@・this.get余力計算条件().get営業日(T-2)<BR>
     * <BR>
     * 　@（3）以外の場合<BR>
     * <BR>
     * 　@　@　@・this.get余力計算条件().get営業日(T-1)<BR>
     * <BR>
     * ２）　@対象建玉一覧を取得し、判定を行う。<BR>
     * ［取得方法@］<BR>
     * 　@this.get対象建玉一覧()<BR>
     * 　@※ this.get対象建玉一覧()　@==　@null　@又は<BR>
     * 　@this.get対象建玉一覧().size()　@==　@0 の場合、0を返却する。<BR>
     * <BR>
     * ３）　@取得した対象建玉一覧の要素数分LOOPし、以下の処理を行う。<BR>
     * <BR>
     * （1） 対象建玉一覧より、対象建玉オブジェクトを取得する。<BR>
     * <BR>
     * （2） 対象建玉オブジェクトより、対象建玉詳細オブジェクトを取得する。<BR>
     * <BR>
     * ［取得方法@］<BR>
     * 　@対象建玉.get対象建玉詳細()<BR>
     * <BR>
     * （3） 対象建玉詳細オブジェクトが以下の条件を満たす場合、（4）以降の処理を行う。<BR>
     * ［抽出条件］<BR>
     * 　@対象建玉詳細.get建日()　@<　@１）で取得した日付<BR>
     * <BR>
     * （4） 建単価を取得する。<BR>
     * ［取得方法@］<BR>
     * 　@対象建玉詳細.get建単価()<BR>
     * <BR>
     * （5） 対象建玉オブジェクトをキーとして、<BR>
     * 　@建玉ごと変動情報オブジェクトを取得し、判定を行う。<BR>
     * ［取得方法@］<BR>
     * 　@建玉情報.get建玉ごと変動情報(対象建玉：（1）の対象建玉オブジェクト) <BR>
     * 　@※ 建玉情報.get建玉ごと変動情報()の戻り値　@!=　@nullの場合、（6）以降の処理を行う。<BR>
     * <BR>
     * （6） 当日返済建玉株数を取得する。<BR>
     * ［取得方法@］<BR>
     * 　@建玉ごと変動情報.get当日返済建玉株数の集計()<BR>
     * <BR>
     * （7） 返済代金を計算する。<BR>
     * ［計算式］<BR>
     * 　@返済代金　@=　@（(4)で取得した建単価）　@×　@（(6)で取得した株数）<BR>
     * <BR>
     * （8） 計算結果を集計する。<BR>
     * ［計算式］<BR>
     * 　@集計結果　@=　@集計結果　@＋　@(7)で計算した返済代金 <BR>
     * <BR>
     * ４）　@返済代金の集計結果を返却する。<BR>
     * <BR>
     * @@return double
     */
    public double getSummaryTodayRepayContractAmount()
    {
        final String STR_METHOD_NAME = "getSummaryTodayRepayContractAmount()";
        log.entering(STR_METHOD_NAME);

        //this.get余力計算条件().get会社部店別余力計算条件（"contractamount.apply.date"：当日建玉代金計上開始日）
        String l_strContractApplyDate =
            this.getCalcCondition().getInstBranCalcCondition(
                WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);

        //当日建玉代金計上開始基準日を取得する。
        //　@（1）this.get余力計算条件().get会社部店別余力計算条件（"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、
        // 1：FROM_BIZ_DATEの場合
        //　@・this.get余力計算条件().get営業日(T+0)
        // （2）this.get余力計算条件().get会社部店別余力計算条件（"contractamount.apply.date"：当日建玉代金計上開始日）の戻り値が、
        // 2：FROM_T2の場合
        // ・this.get余力計算条件().get営業日(T-2)
        // （3）以外の場合
        // ・this.get余力計算条件().get営業日(T-1)
        Date l_datBizDate = null;
        if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS2);
        }
        else
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        }

        //対象建玉一覧を取得し、判定を行う。
        List l_lisTargetContracts = this.getTargetContracts();

        //株数の集計
        double l_dblSumAmount = 0;
        BigDecimal l_bdSumAmount = new BigDecimal(Double.toString(l_dblSumAmount));

        //※ this.get対象建玉一覧()　@==　@null　@又は this.get対象建玉一覧().size()　@==　@0 の場合、0を返却する。
        if (l_lisTargetContracts == null || l_lisTargetContracts.size() == 0)
        {
            log.debug(" 集計結果 = " + l_dblSumAmount);
            log.exiting(STR_METHOD_NAME);
            return l_dblSumAmount;
        }

        int l_intSize = l_lisTargetContracts.size();

        for (int i = 0; i < l_intSize; i++)
        {
            //対象建玉一覧より、対象建玉オブジェクトを取得する。
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_lisTargetContracts.get(i);

            //対象建玉オブジェクトより、対象建玉詳細オブジェクトを取得する。
            WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

            Date l_datOpenDate = l_targetContractDetail.getOpenDate();

            //対象建玉詳細.get建日()　@<　@１）で取得した日付
            //以降の処理を行う。
            if (WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate) >= 0)
            {
                continue;
            }

            //建単価を取得する。
            double l_dblContractPrice = l_targetContractDetail.getContractPrice();

            //対象建玉オブジェクトをキーとして、建玉ごと変動情報オブジェクトを取得し、判定を行う。
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //※ 建玉情報.get建玉ごと変動情報()の戻り値　@!=　@nullの場合、（6）以降の処理を行う。
            if (l_historyPerContract == null)
            {
                continue;
            }

            //当日返済建玉株数を取得する。
            double l_dblRepayContractQuantity =
                l_historyPerContract.getSummaryTodayRepayContractQuantity();

            //返済代金を計算する。
            BigDecimal l_bdContractPric = new BigDecimal(Double.toString(l_dblContractPrice));
            BigDecimal l_bdRepayContractQuantity = new BigDecimal(Double.toString(l_dblRepayContractQuantity));
            BigDecimal l_bdAmount = l_bdContractPric.multiply(l_bdRepayContractQuantity);

            //計算結果を集計する。
            l_bdSumAmount = l_bdSumAmount.add(l_bdAmount);
        }

        //返済代金の集計結果を返却する。
        log.debug(" 集計結果 = " + l_bdSumAmount.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdSumAmount.doubleValue();
    }

    /**
     * (calc銘柄ごと必要保証金) <BR>
     * 引数.指定日の引数.銘柄IDの必要保証金を返却する。 <BR>
     * <BR>
     * １）対象建玉一覧を作成する。 <BR>
     * 　@・建玉情報.get対象建玉一覧()をコール <BR>
     * <BR>
     * ２）銘柄ごと必要保証金を集計する。 <BR>
     * <BR>
     * 　@・以下の処理を、取得した対象建玉一覧の要素数回LOOP処理 <BR>
     * 　@　@２－１）対象建玉一覧より、対象建玉オブジェクトを取得 <BR>
     * <BR>
     * 　@　@２－２）対象建玉オブジェクトより、銘柄IDを取得する。 <BR>
     * 　@　@　@　@ ・対象建玉.get対象建玉詳細().get銘柄ID（）をコール <BR>
     * <BR>
     * 　@[対象建玉.get対象建玉詳細().get銘柄ID（）== 引数.銘柄ID]の場合以下処理を実施 <BR>
     * <BR>
     * 　@　@２－３）対象建玉オブジェクトに紐付く建玉ごと変動情報オブジェクトを取得 <BR>
     * 　@　@　@　@・建玉情報.get建玉ごと変動情報()をコール <BR>
     * 　@　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@　@対象建玉：２－１）で取得した対象建玉オブジェクト <BR>
     * <BR>
     * 　@　@２－４）建玉ごと変動情報オブジェクトより、未決済建玉の集計オブジェクトを取得 <BR>
     * 　@　@　@　@・建玉ごと変動情報.get未決済建玉の集計()をコール <BR>
     * 　@　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@　@指定日：引数.指定日 <BR>
     * <BR>
     * 　@　@２－５）建玉ごと変動情報オブジェクトより、日計り返済・現引現渡建玉の集計オブジェクトを取得 <BR>
     * 　@　@　@　@・建玉ごと変動情報.get日計り返済・現引現渡建玉の集計をコール <BR>
     * 　@　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@　@指定日：引数.指定日 <BR>
     * <BR>
     * 　@　@２－６）未決済建玉必要保証金を計算する。 <BR>
     * 　@　@　@　@　@[計算式] <BR>
     * 　@　@　@　@　@　@未決済建玉必要保証金＝必要保証金＋発注分必要保証金＋日計り返済・現引現渡必要保証金 <BR>
     * <BR>
     * 　@　@　@　@　@　@(*)必要保証金：未決済建玉の集計.get必要保証金 () <BR>
     * 　@　@　@　@　@　@(*)発注分必要保証金：未決済建玉の集計.get発注分必要保証金 () <BR>
     * 　@　@　@　@　@　@(*)日計り返済・現引現渡必要保証金：日計り返済・現引現渡建玉の集計.get必要保証金 () <BR>
     * <BR>
     * 　@　@２－７）　@銘柄ごと必要保証金　@＝　@銘柄ごと必要保証金　@+　@未決済建玉必要保証金 <BR>
     * <BR>
     * ３）銘柄ごと必要保証金を返却する。<BR>
     * @@param l_datDate - (指定日)
     * @@param l_lngProductId - (銘柄ID)
     * @@return double
     */
    public double calcProductMarginDeposit(Date l_datDate, long l_lngProductId)
    {
        final String STR_METHOD_NAME = "calcProductMarginDeposit(Date, long)";
        log.entering(STR_METHOD_NAME);

        //対象建玉一覧を作成
        List l_lisTargetContracts = getTargetContracts();

        //銘柄ごと必要保証金
        BigDecimal l_bdProductMarginDeposit = new BigDecimal("0");

        //対象建玉一覧の要素数回LOOP処理
        int l_intSize = l_lisTargetContracts.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //対象建玉を取得
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_lisTargetContracts.get(i);

            //対象建玉の銘柄IDを取得
            long l_lngContractProductId = l_targetContract.getTargetContractDetail().getProductId();
            log.debug("銘柄ID[" + i + "] = " + l_lngContractProductId);

            //[対象建玉.get対象建玉詳細().get銘柄ID（）== 引数.銘柄ID]の場合以下処理を実施
            if (l_lngContractProductId == l_lngProductId)
            {
                //建玉ごと変動情報
                WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

                //未決済建玉の集計()
                WEB3TPSummaryOpenContract l_summaryOpenContract =
                    l_historyPerContract.getSummaryOpenContract(l_datDate);

                //日計り返済・現引現渡建玉の集計
                WEB3TPSummaryDayTradeSwapContract l_summaryDayTradeSwapContract =
                    l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);

                //必要保証金
                double l_dblMarginDeposit = l_summaryOpenContract.getMarginDeposit();

                //発注分必要保証金
                double l_dblUnExecMarginDeposit = l_summaryOpenContract.getUnExecMarginDeposit();

                //日計り返済・現引現渡必要保証金
                double l_dblContractMarginDeposit = l_summaryDayTradeSwapContract.getMarginDeposit();

                //未決済建玉必要保証金＝必要保証金＋発注分必要保証金＋日計り返済・現引現渡必要保証金
                BigDecimal l_bdMarginDeposit = new BigDecimal(String.valueOf(l_dblMarginDeposit));
                BigDecimal l_bdUnExecMarginDeposit = new BigDecimal(String.valueOf(l_dblUnExecMarginDeposit));
                BigDecimal l_bdContractMarginDeposit = new BigDecimal(String.valueOf(l_dblContractMarginDeposit));
                BigDecimal l_bdSummaryOpenContractMarginDeposit =
                    l_bdMarginDeposit.add(l_bdUnExecMarginDeposit).add(l_bdContractMarginDeposit);

                //銘柄ごと必要保証金　@＝　@銘柄ごと必要保証金　@+　@未決済建玉必要保証金
                l_bdProductMarginDeposit = l_bdProductMarginDeposit.add(l_bdSummaryOpenContractMarginDeposit);
            }
        }
        log.debug("指定日 = " + l_datDate);
        log.debug("銘柄ID = " + l_lngProductId);
        log.debug("銘柄ごと必要保証金 = " + l_bdProductMarginDeposit.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdProductMarginDeposit.doubleValue();
    }
}
@
