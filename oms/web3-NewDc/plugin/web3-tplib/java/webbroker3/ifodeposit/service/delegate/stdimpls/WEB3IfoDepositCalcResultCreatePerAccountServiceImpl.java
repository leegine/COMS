head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultCreatePerAccountServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceImplクラス(WEB3IfoDepositCalcResultCreatePerAccountServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/24 孫(FLJ) 新規作成
 */

package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.WEB3IfoCustomerTransfer;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcConditionPerIndex;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoPositionBalance;
import webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex;
import webbroker3.ifodeposit.data.IfoDepositCalcResultDao;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.define.WEB3IfoDepositCalcResultSaveDiv;
import webbroker3.ifodeposit.define.WEB3IfoDepositFixedIfoDepositFlgDiv;
import webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceUnit;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (顧客証拠金計算結果作成サービスImpl)<BR>
 * 顧客証拠金計算結果作成サービス実装クラス。<BR>
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultCreatePerAccountServiceImpl implements WEB3IfoDepositCalcResultCreatePerAccountService
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultSaveServiceImpl.class);//

    /**
     *	コンストラクター  
     */
    public WEB3IfoDepositCalcResultCreatePerAccountServiceImpl()
    {
        super();
    }

    /**
     * 顧客証拠金計算結果作成サービスを実施する。
     * 
     * @@param l_request
     *  - (リクエストデータ)
     * リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public IfoDepositCalcResultParams createIfoDepositCalcResult(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createIfoDepositCalcResult(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        IfoDepositCalcResultParams l_param = new IfoDepositCalcResultParams();
        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
        try
        {
            l_param.setDepositInfoId(IfoDepositCalcResultDao.newPkValue());//証拠金情報ＩＤ
            l_param.setAccountId(l_subAccount.getAccountId());//口座ＩＤ
            l_param.setBaseDate(l_strBaseDate);//基準日
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_e.getMessage(),
                    l_e);
        }

        try
        {
            //証拠金計算サービスを取得する
            WEB3IfoDepositCalcService l_calcService = (WEB3IfoDepositCalcService) Services.getService(WEB3IfoDepositCalcService.class);

            // 証拠金計算を取得する
            WEB3IfoDepositCalc l_calc = l_calcService.getIfoDepositCalc(l_subAccount);

            // 証拠金推移明細の一覧を作成する
            WEB3IfoDepositTransitionReferenceUnit[] l_ifoDepositTransitionReferences = createIfoDepositTransitionReferences(l_calc);

            //指数別証拠金推移明細t+0を取得する
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit0 = l_ifoDepositTransitionReferences[0].ifoDepositTranRefPerIndexUnit;
            //NK225証拠金推移明細t+0 と ミニNK225証拠金推移明細t+0 を取得する
            WEB3IfoDepositTranRefPerIndexUnit l_nk225TranUnit0 = new WEB3IfoDepositTranRefPerIndexUnit();
            WEB3IfoDepositTranRefPerIndexUnit l_miniNk225TranUnit0 = new WEB3IfoDepositTranRefPerIndexUnit();
            for (int j = 0; j < l_perIndexUnit0.length; j++)
            {
                if (WEB3IfoDepositCalcResultSaveDiv.NK225.equals(l_perIndexUnit0[j].targetProductCode))
                {
                    l_nk225TranUnit0 = l_perIndexUnit0[j];
                }
                if (WEB3IfoDepositCalcResultSaveDiv.MINI_NK225.equals(l_perIndexUnit0[j].targetProductCode))
                {
                    l_miniNk225TranUnit0 = l_perIndexUnit0[j];
                }
            }

            //指数別証拠金推移明細t+1を取得する
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit1 = l_ifoDepositTransitionReferences[1].ifoDepositTranRefPerIndexUnit;
            //NK225証拠金推移明細t+1 と ミニNK225証拠金推移明細t+1 を取得する
            WEB3IfoDepositTranRefPerIndexUnit l_nk225TranUnit1 = new WEB3IfoDepositTranRefPerIndexUnit();
            WEB3IfoDepositTranRefPerIndexUnit l_miniNk225TranUnit1 = new WEB3IfoDepositTranRefPerIndexUnit();
            for (int j = 0; j < l_perIndexUnit1.length; j++)
            {
                if (WEB3IfoDepositCalcResultSaveDiv.NK225.equals(l_perIndexUnit1[j].targetProductCode))
                {
                    l_nk225TranUnit1 = l_perIndexUnit1[j];
                }
                if (WEB3IfoDepositCalcResultSaveDiv.MINI_NK225.equals(l_perIndexUnit1[j].targetProductCode))
                {
                    l_miniNk225TranUnit1 = l_perIndexUnit1[j];
                }
            }

            //指数別証拠金推移明細t+2を取得する
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit2 = l_ifoDepositTransitionReferences[2].ifoDepositTranRefPerIndexUnit;
            //NK225証拠金推移明細t+2 と ミニNK225証拠金推移明細t+2 を取得する
            WEB3IfoDepositTranRefPerIndexUnit l_nk225TranUnit2 = new WEB3IfoDepositTranRefPerIndexUnit();
            WEB3IfoDepositTranRefPerIndexUnit l_miniNk225TranUnit2 = new WEB3IfoDepositTranRefPerIndexUnit();
            for (int j = 0; j < l_perIndexUnit2.length; j++)
            {
                if (WEB3IfoDepositCalcResultSaveDiv.NK225.equals(l_perIndexUnit2[j].targetProductCode))
                {
                    l_nk225TranUnit2 = l_perIndexUnit2[j];
                }
                if (WEB3IfoDepositCalcResultSaveDiv.MINI_NK225.equals(l_perIndexUnit2[j].targetProductCode))
                {
                    l_miniNk225TranUnit2 = l_perIndexUnit2[j];
                }
            }

            // 証拠金計算条件を取得する。
            WEB3IfoDepositCalcCondition l_calcCondition = l_calc.getIfoDepositCalcCondition();

            // 先物OP顧客移動明細を取得する
            WEB3IfoCustomerTransfer l_transfer = l_calc.getIfoCustomerTransfer();

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 未入金額を取得する
            double l_dblNonPayAmount = l_calc.calcNonPayAmount();

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 当日請求額を取得する
            double l_dblCurrentBizDateDemandAmount = l_calc.getCurrentBizDateDemandAmount();

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 翌日請求額を取得する
            double l_dblNextBizDateDemandAmount = l_calc.calcNextBizDateDemandAmount();

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 証拠金振替余力額を算出する
            double l_dblIfoDepositTransferableAmount = l_calc.calcIfoDepositTransferableAmount();

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 受渡日を取得する
            Date l_datDeliveryDate = l_calcCondition.getBizDate(l_calcCondition.getIfoDepositBaseDate());

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            //証拠金不足確定FLAG
            String l_insufficientFlag = null;
            //(証拠金計算条件.is夕場実施 == true and 証拠金計算条件.is清算値速報受信済() == true) or 
            //(証拠金計算条件.is夕場実施 == false and 証拠金計算条件.is証拠金不足メール送信済() == true)の場合、
            // 1：確定。以外、0：未確定。
            if ((l_calcCondition.isEveningSessionEnforcemented() && l_calcCondition.isQuickReportReceived()) ||
                 (!l_calcCondition.isEveningSessionEnforcemented() && l_calcCondition.isIfoDepositMailFlag()) )
            {
                l_insufficientFlag = WEB3IfoDepositFixedIfoDepositFlgDiv.FIXED;
            }
            else
            {
                l_insufficientFlag = WEB3IfoDepositFixedIfoDepositFlgDiv.NOT_FIXED;
            }

            //指数別証拠金
            WEB3IfoDepositPerIndexUnit[] l_ifoDepositPerIndexUnit = this.createIfoDepositPerIndexUnitList(l_calcCondition);
            //指数別証拠金（NK225） と 指数別証拠金（ミニNK225） を取得する
            WEB3IfoDepositPerIndexUnit l_nk225Unit = new WEB3IfoDepositPerIndexUnit();
            WEB3IfoDepositPerIndexUnit l_miniNk225Unit = new WEB3IfoDepositPerIndexUnit();
            for (int j = 0; j < l_ifoDepositPerIndexUnit.length; j++)
            {
                if (WEB3IfoDepositCalcResultSaveDiv.NK225.equals(l_ifoDepositPerIndexUnit[j].targetProductCode))
                {
                    l_nk225Unit = l_ifoDepositPerIndexUnit[j];
                }
                if (WEB3IfoDepositCalcResultSaveDiv.MINI_NK225.equals(l_ifoDepositPerIndexUnit[j].targetProductCode))
                {
                    l_miniNk225Unit = l_ifoDepositPerIndexUnit[j];
                }
            }

            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 翌々日請求額を取得する
            double l_dblNextNextBizDateDemandAmount = l_calc.calcNext2BizDateDemandAmount();
            
            //--ロジックはWEB3IfoDepositTransitionReferenceServiceImpl(証拠金推移参照画面表示サービスImplクラス)を参照
            // 翌々日請求額（夕場）を取得する
            double l_dblNextNextEveningBizDateDemandAmount = l_calc.calcNextBizDateDemandAmountEvening();

            Date l_datSysDate = new Date();

            l_param.setBalance0(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(0)));//残高（T+0）
            l_param.setBalance1(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(1)));//残高（T+1）
            l_param.setBalance2(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(2)));//残高（T+2）
            l_param.setIfoDepositBalance0(l_ifoDepositTransitionReferences[0].ifoDepositBal);//証拠金残(T+0)
            l_param.setIfoDepositBalance1(l_ifoDepositTransitionReferences[1].ifoDepositBal);//証拠金残(T+1)
            l_param.setIfoDepositBalance2(l_ifoDepositTransitionReferences[2].ifoDepositBal);//証拠金残(T+2)
			l_param.setIfoDepositBalanceF0(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(0)));//証拠金残(T+0)(仮確定)
			l_param.setIfoDepositBalanceF1(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(1)));//証拠金残(T+1)(仮確定)
			l_param.setIfoDepositBalanceF2(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(2)));//証拠金残(T+2)(仮確定)
            l_param.setTodayTransferAmt0(l_ifoDepositTransitionReferences[0].todayCahangeAmt);//本日振替額(T+0)
            l_param.setTodayTransferAmt1(l_ifoDepositTransitionReferences[1].todayCahangeAmt);//本日振替額(T+1)
            l_param.setTodayTransferAmt2(l_ifoDepositTransitionReferences[2].todayCahangeAmt);//本日振替額(T+2)
            l_param.setTodayFutSettleProfit0(l_ifoDepositTransitionReferences[0].todayFutSettleProfitLoss);//本日先物決済損益(T+0)
            l_param.setTodayFutSettleProfit1(l_ifoDepositTransitionReferences[1].todayFutSettleProfitLoss);//本日先物決済損益(T+1)
            l_param.setTodayFutSettleProfit2(l_ifoDepositTransitionReferences[2].todayFutSettleProfitLoss);//本日先物決済損益(T+2)
            l_param.setTodayOpNetAmt0(l_ifoDepositTransitionReferences[0].todayOpNetAmt);//本日オプション受渡代金(T+0)
            l_param.setTodayOpNetAmt1(l_ifoDepositTransitionReferences[1].todayOpNetAmt);//本日オプション受渡代金(T+1)
            l_param.setTodayOpNetAmt2(l_ifoDepositTransitionReferences[2].todayOpNetAmt);//本日オプション受渡代金(T+2)
            l_param.setFutEvalProfit0(l_ifoDepositTransitionReferences[0].futEvalProfitLoss);//先物評価損益(T+0)
            l_param.setFutEvalProfit1(l_ifoDepositTransitionReferences[1].futEvalProfitLoss);//先物評価損益(T+1)
            l_param.setFutEvalProfit2(l_ifoDepositTransitionReferences[2].futEvalProfitLoss);//先物評価損益(T+2)
            l_param.setLongFutEvalProfit0(l_ifoDepositTransitionReferences[0].lfEvalProfitLoss);//買建先物評価損益(T+0)
            l_param.setLongFutEvalProfit1(l_ifoDepositTransitionReferences[1].lfEvalProfitLoss);//買建先物評価損益(T+1)
            l_param.setLongFutEvalProfit2(l_ifoDepositTransitionReferences[2].lfEvalProfitLoss);//買建先物評価損益(T+2)
            l_param.setShortFutEvalProfit0(l_ifoDepositTransitionReferences[0].sfEvalProfitLoss);//売建先物評価損益(T+0)
            l_param.setShortFutEvalProfit1(l_ifoDepositTransitionReferences[1].sfEvalProfitLoss);//売建先物評価損益(T+1)
            l_param.setShortFutEvalProfit2(l_ifoDepositTransitionReferences[2].sfEvalProfitLoss);//売建先物評価損益(T+2)
            l_param.setAcceptanceIfoDepositBal0(l_ifoDepositTransitionReferences[0].acceptanceIfoDepositBal);//受入証拠金残(T+0)
            l_param.setAcceptanceIfoDepositBal1(l_ifoDepositTransitionReferences[1].acceptanceIfoDepositBal);//受入証拠金残(T+1)
            l_param.setAcceptanceIfoDepositBal2(l_ifoDepositTransitionReferences[2].acceptanceIfoDepositBal);//受入証拠金残(T+2)
            l_param.setAcceptanceIfoDepositBalF0(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(0)));//受入証拠金残(T+0)(仮確定)
            l_param.setAcceptanceIfoDepositBalF1(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(1)));//受入証拠金残(T+1)(仮確定)
            l_param.setAcceptanceIfoDepositBalF2(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(2)));//受入証拠金残(T+2)(仮確定)
            l_param.setNetOpValueTotalAmt0(l_ifoDepositTransitionReferences[0].netOptionlValueTotalAmt);//ネット・オプション価値総額(T+0)
            l_param.setNetOpValueTotalAmt1(l_ifoDepositTransitionReferences[1].netOptionlValueTotalAmt);//ネット・オプション価値総額(T+1)
            l_param.setNetOpValueTotalAmt2(l_ifoDepositTransitionReferences[2].netOptionlValueTotalAmt);//ネット・オプション価値総額(T+2)
			l_param.setNetOpValueTotalAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(0)));//ネット・オプション価値総額(T+0)(仮確定)
			l_param.setNetOpValueTotalAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(1)));//ネット・オプション価値総額(T+1)(仮確定)
			l_param.setNetOpValueTotalAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(2)));//ネット・オプション価値総額(T+2)(仮確定)
            l_param.setIfoDepositNecessaryAmt0(l_ifoDepositTransitionReferences[0].ifoDepositNecessaryAmt);//証拠金所要額(T+0)
            l_param.setIfoDepositNecessaryAmt1(l_ifoDepositTransitionReferences[1].ifoDepositNecessaryAmt);//証拠金所要額(T+1)
            l_param.setIfoDepositNecessaryAmt2(l_ifoDepositTransitionReferences[2].ifoDepositNecessaryAmt);//証拠金所要額(T+2)
            l_param.setIfoDepositNecessaryAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(0)));//証拠金所要額(T+0)(仮確定)
            l_param.setIfoDepositNecessaryAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(1)));//証拠金所要額(T+1)(仮確定)
            l_param.setIfoDepositNecessaryAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(2)));//証拠金所要額(T+2)(仮確定)
            l_param.setIfoDepositPower0(l_ifoDepositTransitionReferences[0].ifoDepositPower);//証拠金余力額(T+0)
            l_param.setIfoDepositPower1(l_ifoDepositTransitionReferences[1].ifoDepositPower);//証拠金余力額(T+1)
            l_param.setIfoDepositPower2(l_ifoDepositTransitionReferences[2].ifoDepositPower);//証拠金余力額(T+2)
            l_param.setIfoDepositTransferPower0(l_ifoDepositTransitionReferences[0].depositChangePower);//証拠金振替余力額(T+0)
            l_param.setIfoDepositTransferPower1(l_ifoDepositTransitionReferences[1].depositChangePower);//証拠金振替余力額(T+1)
            l_param.setIfoDepositTransferPower2(l_ifoDepositTransitionReferences[2].depositChangePower);//証拠金振替余力額(T+2)
            l_param.setBullQuantityNk2250(l_nk225TranUnit0.bullQuantity);//先物買建またはOPプット売建可能数量（NK225）(T+0)
            l_param.setBullQuantityNk2251(l_nk225TranUnit1.bullQuantity);//先物買建またはOPプット売建可能数量（NK225）(T+1)
            l_param.setBullQuantityNk2252(l_nk225TranUnit2.bullQuantity);//先物買建またはOPプット売建可能数量（NK225）(T+2)
            l_param.setBearQuantityNk2250(l_nk225TranUnit0.bearQuantity);//先物売建またはOPコール売建可能数量（NK225）(T+0)
            l_param.setBearQuantityNk2251(l_nk225TranUnit1.bearQuantity);//先物売建またはOPコール売建可能数量（NK225）(T+1)
            l_param.setBearQuantityNk2252(l_nk225TranUnit2.bearQuantity);//先物売建またはOPコール売建可能数量（NK225）(T+2)
            l_param.setLongPosNk2250(l_nk225TranUnit0.longPositionContract);//買ポジション建玉（NK225）(T+0)
            l_param.setLongPosNk2251(l_nk225TranUnit1.longPositionContract);//買ポジション建玉（NK225）(T+1)
            l_param.setLongPosNk2252(l_nk225TranUnit2.longPositionContract);//買ポジション建玉（NK225）(T+2)
            l_param.setPartLongPosNk2250(l_nk225TranUnit0.partLongPositionContract);//買ポジション建玉（内注文中）（NK225）(T+0)
            l_param.setPartLongPosNk2251(l_nk225TranUnit1.partLongPositionContract);//買ポジション建玉（内注文中）（NK225）(T+1)
            l_param.setPartLongPosNk2252(l_nk225TranUnit2.partLongPositionContract);//買ポジション建玉（内注文中）（NK225）(T+2)
            l_param.setShortPosNk2250(l_nk225TranUnit0.shortPositionContract);//売ポジション建玉（NK225）(T+0)
            l_param.setShortPosNk2251(l_nk225TranUnit1.shortPositionContract);//売ポジション建玉（NK225）(T+1)
            l_param.setShortPosNk2252(l_nk225TranUnit2.shortPositionContract);//売ポジション建玉（NK225）(T+2)
            l_param.setPartShortPosNk2250(l_nk225TranUnit0.partShortPositionContract);//売ポジション建玉（内注文中）（NK225）(T+0)
            l_param.setPartShortPosNk2251(l_nk225TranUnit1.partShortPositionContract);//売ポジション建玉（内注文中）（NK225）(T+1)
            l_param.setPartShortPosNk2252(l_nk225TranUnit2.partShortPositionContract);//売ポジション建玉（内注文中）（NK225）(T+2)
            l_param.setBullQuantityNk225Mini0(l_miniNk225TranUnit0.bullQuantity);//先物買建またはOPプット売建可能数量（NK225ミニ）(T+0)
            l_param.setBullQuantityNk225Mini1(l_miniNk225TranUnit1.bullQuantity);//先物買建またはOPプット売建可能数量（NK225ミニ）(T+1)
            l_param.setBullQuantityNk225Mini2(l_miniNk225TranUnit2.bullQuantity);//先物買建またはOPプット売建可能数量（NK225ミニ）(T+2)
            l_param.setBearQuantityNk225Mini0(l_miniNk225TranUnit0.bearQuantity);//先物売建またはOPコール売建可能数量（NK225ミニ）(T+0)
            l_param.setBearQuantityNk225Mini1(l_miniNk225TranUnit1.bearQuantity);//先物売建またはOPコール売建可能数量（NK225ミニ）(T+1)
            l_param.setBearQuantityNk225Mini2(l_miniNk225TranUnit2.bearQuantity);//先物売建またはOPコール売建可能数量（NK225ミニ）(T+2)
            l_param.setLongPosNk225Mini0(l_miniNk225TranUnit0.longPositionContract);//買ポジション建玉（NK225ミニ）(T+0)
            l_param.setLongPosNk225Mini1(l_miniNk225TranUnit1.longPositionContract);//買ポジション建玉（NK225ミニ）(T+1)
            l_param.setLongPosNk225Mini2(l_miniNk225TranUnit2.longPositionContract);//買ポジション建玉（NK225ミニ）(T+2)
            l_param.setPartLongPosNk225Mini0(l_miniNk225TranUnit0.partLongPositionContract);//買ポジション建玉（内注文中）（NK225ミニ）(T+0)
            l_param.setPartLongPosNk225Mini1(l_miniNk225TranUnit1.partLongPositionContract);//買ポジション建玉（内注文中）（NK225ミニ）(T+1)
            l_param.setPartLongPosNk225Mini2(l_miniNk225TranUnit2.partLongPositionContract);//買ポジション建玉（内注文中）（NK225ミニ）(T+2)
            l_param.setShortPosNk225Mini0(l_miniNk225TranUnit0.shortPositionContract);//売ポジション建玉（NK225ミニ）(T+0)
            l_param.setShortPosNk225Mini1(l_miniNk225TranUnit1.shortPositionContract);//売ポジション建玉（NK225ミニ）(T+1)
            l_param.setShortPosNk225Mini2(l_miniNk225TranUnit2.shortPositionContract);//売ポジション建玉（NK225ミニ）(T+2)
            l_param.setPartShortPosNk225Mini0(l_miniNk225TranUnit0.partShortPositionContract);//売ポジション建玉（内注文中）（NK225ミニ）(T+0)
            l_param.setPartShortPosNk225Mini1(l_miniNk225TranUnit1.partShortPositionContract);//売ポジション建玉（内注文中）（NK225ミニ）(T+1)
            l_param.setPartShortPosNk225Mini2(l_miniNk225TranUnit2.partShortPositionContract);//売ポジション建玉（内注文中）（NK225ミニ）(T+2)

			l_param.setBuyContractAmt0(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(0)));//買ポジション金額(T+0)
			l_param.setBuyContractAmt1(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(1)));//買ポジション金額(T+1)
			l_param.setBuyContractAmt2(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(2)));//買ポジション金額(T+2)
			l_param.setBuyContractAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(0)));//買ポジション金額(T+0)(仮確定)
			l_param.setBuyContractAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(1)));//買ポジション金額(T+1)(仮確定)
			l_param.setBuyContractAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(2)));//買ポジション金額(T+2)(仮確定)
			l_param.setSellContractAmt0(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(0)));//売ポジション金額(T+0)
			l_param.setSellContractAmt1(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(1)));//売ポジション金額(T+1)
			l_param.setSellContractAmt2(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(2)));//売ポジション金額(T+2)
			l_param.setSellContractAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(0)));//売ポジション金額(T+0)(仮確定)
			l_param.setSellContractAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(1)));//売ポジション金額(T+1)(仮確定)
			l_param.setSellContractAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(2)));//売ポジション金額(T+2)(仮確定)

            l_param.setNonPayAmt(WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount));//未入金額
            l_param.setTodayClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount));//本日請求額
            l_param.setTomorrowClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount));//翌日請求額
            l_param.setDeliveryDate(l_datDeliveryDate);//受渡日
            l_param.setIfoDepositInsufficientFlag(l_insufficientFlag);//証拠金不足確定FLAG
            l_param.setIfoDepositIndexNk225(l_nk225Unit.regIfoDeposit);//指数別証拠金（NK225）
            l_param.setIfoDepositIndexNk225Mini(l_miniNk225Unit.regIfoDeposit);//指数別証拠金（NK225ミニ）
            l_param.setAfterTomorrowClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblNextNextBizDateDemandAmount));//翌々日請求額
            l_param.setTomorrowClaimAmtEvening(WEB3StringTypeUtility.formatNumber(l_dblNextNextEveningBizDateDemandAmount));//翌日請求額（夕場）
            l_param.setStatus(WEB3IfoDepositCalcResultSaveDiv.STATUS_FINISH);//処理
            l_param.setCreatedTimestamp(l_datSysDate);//作成日付
            l_param.setLastUpdatedTimestamp(l_datSysDate);//更新日付
        }
        catch (Exception e)
        {
            l_param.setStatus(WEB3IfoDepositCalcResultSaveDiv.STATUS_ERROR);
            String l_message = e.getMessage();
            if (l_message != null && l_message.length() > WEB3IfoDepositCalcResultSaveDiv.ERROR_MESSAGE_LENGTH)
            {
                l_message = l_message.substring(0, WEB3IfoDepositCalcResultSaveDiv.ERROR_MESSAGE_LENGTH);
            }
            l_param.setAcceptanceIfoDepositBal0(l_message);
        }
        log.exiting(STR_METHOD_NAME);
        return l_param;
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    // 以下は「証拠金推移参照画面表示サービスImplクラス」からコピーした内容です。
    // 「証拠金推移参照画面表示サービスImplクラス」 に以下のメソッドの修正があったら、こちらも更新してください。
    // webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * (create証拠金推移明細一覧)<BR>
     * T+0～T+2までの証拠金推移明細オブジェクトの配列を返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証拠金推移参照画面表示サービス）create証拠金推移明細一覧」参照。<BR>
     * @@param ifoDepositCalc - (証拠金計算)
     * 証拠金計算クラス。
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReference[]
     * @@roseuid 41452A4A02E3
     */
    protected WEB3IfoDepositTransitionReferenceUnit[] createIfoDepositTransitionReferences(WEB3IfoDepositCalc l_ifoDepositCalc)
    {
        
        // 証拠金推移明細リスト
        List l_references = new ArrayList();

        // 先物OP顧客移動明細を取得する
        WEB3IfoCustomerTransfer l_transfer =
            l_ifoDepositCalc.getIfoCustomerTransfer();

        // 証拠金計算条件を取得する
        WEB3IfoDepositCalcCondition l_calcCondition =
            l_ifoDepositCalc.getIfoDepositCalcCondition();

        // 指定日の範囲(n = 0～2まで)のLoop処理
        for (int i = 0; i < 3; i++)
        {

            WEB3IfoDepositTransitionReferenceUnit l_reference =
                new WEB3IfoDepositTransitionReferenceUnit();

            // 営業日を取得する
            Date l_datBizDate = l_calcCondition.getBizDate(i);

            // 証拠金残高を算出する
            double l_dblIfoDepositBalance =
                l_ifoDepositCalc.calcIfoDepositBalance(i);

            // 先物評価損益を算出する
            double l_dblFuturesAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesAppraisalProfitLoss();

            // 先物買建評価損益を算出する
            double l_dblFuturesBuyAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesBuyAppraisalProfitLoss();

            // 先物売建評価損益を算出する
            double l_dblFuturesSellAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesSellAppraisalProfitLoss();
                
            // 受入証拠金残高を算出する
            double l_dblReceiptIfoDepositBlance =
                l_ifoDepositCalc.calcReceiptIfoDepositBalance(i);

            // 買ポジション建玉を算出する
            double l_dblBuyContractQty = l_ifoDepositCalc.calcBuyContractQty(i);

            // 注文中の買ポジション建玉を算出する
            double l_dblOrderingBuyContractQty =
                l_ifoDepositCalc.calcBuyOrderQty(i);

            // 注文中の売ポジション建玉を算出する
            double l_dblSellContractQty =
                l_ifoDepositCalc.calcSellContractQty(i);

            // 注文中の売ポジション建玉を算出する
            double l_dblOrderingSellContractQty =
                l_ifoDepositCalc.calcSellOrderQty(i);

            // ポジションバランスを算出する
            WEB3IfoPositionBalance l_positionBalance =
                l_ifoDepositCalc.calcPositionBalance(i);

            // SPAN証拠金を取得する
            double l_dblSPANIfoDeposit = -1.0;
            if (l_calcCondition.isSPANUsable())
            {
                l_dblSPANIfoDeposit = l_ifoDepositCalc.getSPANIfoDeposit(i);
            }

            // ネットオプション価値総額を算出する
            double l_dblNetOptionTotalAmount =
                l_ifoDepositCalc.calcNetOptionTotalAmount(i);

            // 証拠金所要額を算出する
            double l_dblIfoDepositRequiredAmount =
                l_ifoDepositCalc.calcIfoDepositRequiredAmount(i);

            // 証拠金余力額を算出する
            double l_dblIfoDepositTradingPowerAmount =
                l_ifoDepositCalc.calcIfoDepositTradingPowerAmount(i);
            
			// 証拠金振替余力額を算出する
			double l_dblIfoDepositTransferableAmount =
				l_ifoDepositCalc.calcIfoDepositTransferableAmount(i);

            // オプション評価損益を算出する
            double l_dblOptionAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionAppraisalProfitLoss();

            // オプション買建評価損益を算出する
            double l_dblOptionBuyAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionBuyAppraisalProfitLoss();

            // オプション売建評価損益を算出する
            double l_dblOptionSellAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionSellAppraisalProfitLoss();

            // 日付
            l_reference.bizDate = l_datBizDate;

            // 証拠金残
            l_reference.ifoDepositBal =
                WEB3StringTypeUtility.formatNumber(l_dblIfoDepositBalance);

            //本日振替額：      
            //指定日(n) == 0の場合、先物OP顧客移動明細.get振替額[T+0]の戻り値
            //指定日(n) != 1の場合、先物OP顧客移動明細.get振替額[T+1]の戻り値   
            if (i == 0)
            {
                // 振替額[T+0]を取得する
                double l_dblIfoTransferAmount = l_transfer.getCurrentBizDateTransferAmount();

                l_reference.todayCahangeAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblIfoTransferAmount);
            }
            else if(i !=0)
            {
                // 振替額[T+1]を取得する
                double l_dblIfoTransferAmount = l_transfer.getNextBizDateTransferAmount();

                l_reference.todayCahangeAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblIfoTransferAmount);
            }

            //本日先物決済損益：
            //指定日(n) == 0の場合、null
            //指定日(n) == 1の場合、先物OP顧客移動明細.get先物決済損益[T+1]の戻り値
            //指定日(n) == 2の場合、先物OP顧客移動明細.get先物決済損益[T+2]の戻り値            
            if (i == 0)
            {
                l_reference.todayFutSettleProfitLoss = null;
            }
            else if (i == 1)
            {
                l_reference.todayFutSettleProfitLoss =
                    WEB3StringTypeUtility.formatNumber(
                        l_transfer.getNextBizDateFuturesCloseProfitLoss());
            }
            else
            {
                l_reference.todayFutSettleProfitLoss = 
                    WEB3StringTypeUtility.formatNumber(
                        l_transfer.getNext2BizDateFuturesCloseProfitLoss());
            }

            //本日オプション受渡代金：    
            //指定日(n) == 0の場合、null
            //指定日(n) == 1の場合、先物OP顧客移動明細.getオプション受渡代金[T+1] + 先物OP顧客移動明細.getオプション買建受渡代金[T+1]
            //指定日(n) == 2の場合、先物OP顧客移動明細.getオプション受渡代金[T+2] + 先物OP顧客移動明細.getオプション買建受渡代金[T+2]

            if(i==0)
            {
                l_reference.todayOpNetAmt = null;
            }
            else if(i==1)
            {
                double l_dblOptionNetAmount = 
                    l_transfer.getNextBizDateOptionNetAmount() + 
                        l_transfer.getNextBizDateOptionBuyEstimatedNetAmount();

                l_reference.todayOpNetAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblOptionNetAmount);
            }
            else
            {
                double l_dblOptionNetAmount = 
                    l_transfer.getNext2BizDateOptionNetAmount() + 
                        l_transfer.getNext2BizDateOptionBuyEstimatedNetAmount();

                l_reference.todayOpNetAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblOptionNetAmount);
            }

            // 先物評価損益
            l_reference.futEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesAppraisalProfitLoss);

            // 買建先物評価損益
            l_reference.lfEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesBuyAppraisalProfitLoss);

            // 売建先物評価損益
            l_reference.sfEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesSellAppraisalProfitLoss);

            // 受入証拠金算
            l_reference.acceptanceIfoDepositBal =
                WEB3StringTypeUtility.formatNumber(l_dblReceiptIfoDepositBlance);

            // 買ポジション建玉
            l_reference.longPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblBuyContractQty);

            // 買ポジション建玉（内注文中）
            l_reference.partLongPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblOrderingBuyContractQty);

            // 売ポジション建玉
            l_reference.shortPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblSellContractQty);

            // 売ポジション建玉（内注文中）
            l_reference.partShortPositionContract =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOrderingSellContractQty);

            if (l_positionBalance != null)
            {
                // ポジションバランス
                l_reference.positionBalance =
                    WEB3StringTypeUtility.formatNumber(
                        l_positionBalance.positionBalance);

                // ポジションバランス区分
                l_reference.positionBalanceDiv =
                    l_positionBalance.positionBalanceType;

            }

            // SPAN証拠金
            if (l_calcCondition.isSPANUsable())
            {
                l_reference.spanIfoDeposit =
                    WEB3StringTypeUtility.formatNumber(l_dblSPANIfoDeposit);
            }

            // ネットオプション価値総額
            l_reference.netOptionlValueTotalAmt =
                WEB3StringTypeUtility.formatNumber(l_dblNetOptionTotalAmount);

            // 証拠金所要額
            l_reference.ifoDepositNecessaryAmt =
                WEB3StringTypeUtility.formatNumber(
                    l_dblIfoDepositRequiredAmount);

            // 証拠金余力額
            if (i != 0)
            {
				l_reference.ifoDepositPower =
					WEB3StringTypeUtility.formatNumber(
						Math.max(0, l_dblIfoDepositTradingPowerAmount));
            }
            
			// 証拠金振替余力額
			l_reference.depositChangePower =
				WEB3StringTypeUtility.formatNumber(
					Math.max(0, l_dblIfoDepositTransferableAmount));

            // オプション評価損益
            l_reference.opEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionAppraisalProfitLoss);
            
            // 買建オプション評価損益
            l_reference.loEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionBuyAppraisalProfitLoss);
            
            // 売建オプション評価損益
            l_reference.soEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionSellAppraisalProfitLoss);
                    
            // 指数別証拠金推移明細
            l_reference.ifoDepositTranRefPerIndexUnit =
                this.createIfoDepositTranRefPerIndexUnitList(l_ifoDepositCalc, i);

            // add(証拠金移動明細)
            l_references.add(l_reference);

        }

        // 配列を作成し、返却する
        return (WEB3IfoDepositTransitionReferenceUnit[]) l_references.toArray(
            new WEB3IfoDepositTransitionReferenceUnit[0]);

    }
    
    /**
     * (create指数別証拠金一覧)<BR>
     * 指数別証拠金オブジェクトの配列を返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証拠金推移参照画面表示サービス）create指数別証拠金一覧」参照。<BR>
     * @@param ifoDepositCalcCondition - (証拠金計算条件)
     * 証拠金計算条件クラス。
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit[]
     */
    protected WEB3IfoDepositPerIndexUnit[] createIfoDepositPerIndexUnitList(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        if(l_ifoDepositCalcCondition.isSPANUsable())
        {
            return null;
        }
        
        List l_list = new ArrayList();
        
        // 指数別証拠金計算条件を取得する。
        WEB3IfoDepositCalcConditionPerIndex[] l_calcConditionPerIndexList = 
            l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();
        
        // 指数別証拠金計算条件ごとのLoop処理    
        for(int i= 0; i < l_calcConditionPerIndexList.length; i++)
        {
            WEB3IfoDepositPerIndexUnit l_ifoDepositPerIndexUnit = new WEB3IfoDepositPerIndexUnit();
           
            // 原資産銘柄コード 
            l_ifoDepositPerIndexUnit.targetProductCode =
                l_calcConditionPerIndexList[i].getUnderlyingProductCode();
            
            // 規定証拠金
            l_ifoDepositPerIndexUnit.regIfoDeposit =
                WEB3StringTypeUtility.formatNumber(
                    l_calcConditionPerIndexList[i].getIfoDepositPerUnit());
            
            l_list.add(l_ifoDepositPerIndexUnit);
        }
        
        // 配列を作成し、返却する
        return (WEB3IfoDepositPerIndexUnit[]) l_list.toArray(new WEB3IfoDepositPerIndexUnit[0]);
    }
    
    /**
     * (create指数別証拠金推移明細一覧)<BR>
     * 指定日に紐付く指数別証拠金推移明細オブジェクトの配列を返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証拠金推移参照画面表示サービス）create指数別証拠金推移明細一覧」参照。<BR>
     * @@param ifoDepositCalc - (証拠金計算)
     * 証拠金計算クラス。
     * @@param l_intReservedDate - (指定日)
     * 指定日。
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit[]
     */
    protected WEB3IfoDepositTranRefPerIndexUnit[] createIfoDepositTranRefPerIndexUnitList(
        WEB3IfoDepositCalc l_ifoDepositCalc,
        int l_intReservedDate)
    {
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = 
            l_ifoDepositCalc.getIfoDepositCalcCondition();

        //新規建余力可能フラグ
        boolean l_blnNewOpenTradingPowerAvailable =
            this.isNewOpenTradingPowerAvailable(l_ifoDepositCalc,l_intReservedDate);

        List l_list = new ArrayList();

        //指数別先物OP建玉集計マップ
        Map ifoSummaryContractPerIndexMap = new HashMap();

        //指数別先物OP建玉集計一覧を取得する。
        WEB3IfoSummaryContractPerIndex[] ifoSummaryContractPerIndexList = l_ifoDepositCalc.getIfoSummaryContractPerIndexList();

        if(ifoSummaryContractPerIndexList != null)
        {
            //指数別先物OP建玉集計.原資産銘柄コードをキーに、指数別先物OP建玉集計をマッピングする。
            for(int i = 0; i < ifoSummaryContractPerIndexList.length; i++)
            {
                ifoSummaryContractPerIndexMap.put(ifoSummaryContractPerIndexList[i].getTargetProductCode(),ifoSummaryContractPerIndexList[i]);
            }

        }

        // 指数別証拠金計算条件を取得する。
        WEB3IfoDepositCalcConditionPerIndex[] l_calcConditionPerIndexList = 
            l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

        // 指数別証拠金計算条件ごとのLoop処理    
        for(int i= 0; i < l_calcConditionPerIndexList.length; i++)
        {
            WEB3IfoDepositTranRefPerIndexUnit l_ifoDepositTranRefPerIndexUnit =
                new WEB3IfoDepositTranRefPerIndexUnit();

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndex = null;

            // 原資産銘柄コード 
            String l_strTargetProductCode =
                l_calcConditionPerIndexList[i].getUnderlyingProductCode();

            // 先物買建またはOPプット売建可能数量
            double l_dblBullQuantity = 0;

            // 先物売建またはOPコール建可能数量
            double l_dblBearQuantity = 0;

            //買ポジション建玉
            double longPositionContract = 0;

            //注文中買ポジション建玉
            double partLongPositionContract = 0;

            //売ポジション建玉
            double shortPositionContract = 0;

            //注文中売ポジション建玉
            double partShortPositionContract = 0;

            //新規建余力可能な場合
            if(l_blnNewOpenTradingPowerAvailable)
            {
                // 先物買建またはOPプット売建可能数量
                l_dblBullQuantity =
                    l_ifoDepositCalc.calcPossibleBuyQty(l_strTargetProductCode,l_intReservedDate);
                // 先物売建またはOPコール建可能数量
                l_dblBearQuantity =
                    l_ifoDepositCalc.calcPossibleSellQty(l_strTargetProductCode,l_intReservedDate);
            }

            //指数別先物OP建玉集計マップが指定された原資産銘柄コードのマッピングを保持する場合
            if(ifoSummaryContractPerIndexMap.containsKey(l_calcConditionPerIndexList[i].getUnderlyingProductCode()))
            {
                ifoSummaryContractPerIndex = (WEB3IfoSummaryContractPerIndex)ifoSummaryContractPerIndexMap.get(l_calcConditionPerIndexList[i].getUnderlyingProductCode());

                //買ポジション建玉
                longPositionContract = ifoSummaryContractPerIndex.calcBuyContractQty(l_intReservedDate);
                //注文中買ポジション建玉
                partLongPositionContract = ifoSummaryContractPerIndex.calcBuyOrderQty(l_intReservedDate);
                //売ポジション建玉
                shortPositionContract = ifoSummaryContractPerIndex.calcSellContractQty(l_intReservedDate);
                //注文中売ポジション建玉
                partShortPositionContract = ifoSummaryContractPerIndex.calcSellOrderQty(l_intReservedDate);
            }

            // 原資産銘柄コード
            l_ifoDepositTranRefPerIndexUnit.targetProductCode = l_strTargetProductCode;

            // 先物買建またはOPプット売建可能数量
            l_ifoDepositTranRefPerIndexUnit.bullQuantity = 
                WEB3StringTypeUtility.formatNumber(Math.max(0,l_dblBullQuantity));

            // 先物売建またはOPコール建可能数量
            l_ifoDepositTranRefPerIndexUnit.bearQuantity = 
                WEB3StringTypeUtility.formatNumber(Math.max(0,l_dblBearQuantity));

            //SPAN使用可能、または、指定日 == 0 の場合
            if(l_ifoDepositCalcCondition.isSPANUsable() || l_intReservedDate == 0)
            {
                l_ifoDepositTranRefPerIndexUnit.bullQuantity = null;
                l_ifoDepositTranRefPerIndexUnit.bearQuantity = null;
            }

            //買ポジション建玉
            l_ifoDepositTranRefPerIndexUnit.longPositionContract = 
                WEB3StringTypeUtility.formatNumber(longPositionContract);

            //注文中買ポジション建玉
            l_ifoDepositTranRefPerIndexUnit.partLongPositionContract = 
                WEB3StringTypeUtility.formatNumber(partLongPositionContract);

            //売ポジション建玉
            l_ifoDepositTranRefPerIndexUnit.shortPositionContract = 
                WEB3StringTypeUtility.formatNumber(shortPositionContract);

            //注文中売ポジション建玉
            l_ifoDepositTranRefPerIndexUnit.partShortPositionContract = 
                WEB3StringTypeUtility.formatNumber(partShortPositionContract);
            
            l_list.add(l_ifoDepositTranRefPerIndexUnit);
        }

        // 配列を作成し、返却する
        return (WEB3IfoDepositTranRefPerIndexUnit[]) l_list.toArray(
            new WEB3IfoDepositTranRefPerIndexUnit[0]);
    }
    
    /**
     * (is新規建余力可能)<BR>
     * 新規建余力が可能であるかを判定する。<BR>
     * <BR>
     * １）　@以下のいずれかに当てはまる場合、新規建余力不可として<BR>
　@　@ *　@falseを返却する。以外はtrueを返却する。<BR>
     * <BR>
　@　@ *　@・証拠金計算.get証拠金計算条件().is新規建余力可能() == false<BR>
　@　@ *　@・証拠金計算.calc受入証拠金残高(引数.指定日) < <BR> 
     *    証拠金計算.get証拠金計算条件().get必要最低証拠金()<BR>
　@　@ *　@・証拠金計算.calc未入金額() > 0<BR>
     * <BR>
     * @@param ifoDepositCalc - (証拠金計算)
     * 証拠金計算クラス。
     * @@param l_intReservedDate - (指定日)
     * 指定日。
     * @@return boolean
     */
    protected boolean isNewOpenTradingPowerAvailable(
        WEB3IfoDepositCalc l_ifoDepositCalc,
        int l_intReservedDate)
    {
    
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = 
            l_ifoDepositCalc.getIfoDepositCalcCondition();
    
        // 新規建余力が可能であるかを判定する
        boolean l_blnNewOpenTradingPowerAvailable = 
            l_ifoDepositCalcCondition.isNewOpenTradingPowerAvailable();  

        // 必要最低証拠金を取得する
        double l_dblMinIfoDep = l_ifoDepositCalcCondition.getMinIfoDeposit();
                
        // 受入証拠金残高を算出する
        double l_dblReceiptIfoDepositBlance =
            l_ifoDepositCalc.calcReceiptIfoDepositBalance(l_intReservedDate);
            
        // 未入金額を取得する
        double l_dblNonPayAmt = l_ifoDepositCalc.calcNonPayAmount();
                
        if (l_blnNewOpenTradingPowerAvailable == false
            || l_dblReceiptIfoDepositBlance < l_dblMinIfoDep
            || l_dblNonPayAmt > 0)
        {
            return false;
        }  
        return true;
    }
}@
