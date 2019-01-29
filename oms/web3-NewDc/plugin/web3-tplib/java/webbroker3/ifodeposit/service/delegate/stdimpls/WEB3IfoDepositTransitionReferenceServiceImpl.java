head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移参照画面表示サービスImplクラス(WEB3IfoDepositTransitionReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/27 hijikata(SRA) 夕場対応 モデルNo.064, No.083, No.086
 Revesion History : 2007/08/01 hijikata(SRA) 夕場対応 モデルNo.096, No.099
 Revesion History : 2007/10/18 k.yamashita(SRA) モデルNo.117

 */
package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifodeposit.WEB3IfoCustomerTransfer;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcConditionPerIndex;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoPositionBalance;
import webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex;
import webbroker3.ifodeposit.define.WEB3IfoDepositFixedIfoDepositFlgDiv;
import webbroker3.ifodeposit.define.WEB3IfoDepositSPANDivDef;
import webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositClientRequestService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証拠金推移参照画面表示サービスImpl)<BR>
 * 証拠金推移参照画面表示サービス実装クラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceServiceImpl
    extends WEB3IfoDepositClientRequestService
    implements WEB3IfoDepositTransitionReferenceService
{
    
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositTransitionReferenceServiceImpl.class);

    /**
     * @@roseuid 4186170B000E
     */
    public WEB3IfoDepositTransitionReferenceServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 証拠金推移参照画面表示サービス処理を実施する。<BR>
     * <BR>
     * 引数.リクエストデータを証拠金推移参照画面表示リクエストに
     * キャストしてthis.create証拠金推移参照画面( )をコールする。<BR>
     * 
     * @@param l_request
     *  - (リクエストデータ)
     * リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4145299F0209
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(METHOD_NAME);
        WEB3IfoDepositTransitionReferenceRequest l_req =
            (WEB3IfoDepositTransitionReferenceRequest) l_request;
        WEB3IfoDepositTransitionReferenceResponse l_res =
            createIfoDepositTransitionReferenceResponse(l_req);
        log.exiting(METHOD_NAME);
        return l_res;
    }

    /**
     * (create証拠金推移参照画面)<BR>
     * 証拠金推移参照画面表示サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証拠金推移参照画面表示サービス）create証拠金推移参照画面」参照。<BR>
     * @@param l_request - (リクエストデータ)
     * 証拠金推移参照画面表示リクエスト
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse
     * @@roseuid 414543C30005
     */
    protected WEB3IfoDepositTransitionReferenceResponse createIfoDepositTransitionReferenceResponse(WEB3IfoDepositTransitionReferenceRequest l_request)
        throws WEB3BaseException
    {

        // レスポンスメッセージ生成
        WEB3IfoDepositTransitionReferenceResponse l_response =
            (WEB3IfoDepositTransitionReferenceResponse) l_request
                .createResponse();

		// 受付時間チェック／システム売買停止チェック
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 補助口座取得
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        // 証拠金口座未開設の場合、レスポンスに値を設定しないで返す。
        if (l_subAccount == null)
        {
            return l_response;
        }

        // 証拠金計算サービスを生成
        WEB3IfoDepositCalcService l_calcService =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        // 証拠金計算を取得する
        WEB3IfoDepositCalc l_calc =
            l_calcService.getIfoDepositCalc(l_subAccount);

        // 証拠金推移明細の一覧を作成する
        WEB3IfoDepositTransitionReferenceUnit[] l_ifoDepositTransitionReferences =
            createIfoDepositTransitionReferences(l_calc);

        // 証拠金計算条件を取得する。
        WEB3IfoDepositCalcCondition l_calcCondition =
            l_calc.getIfoDepositCalcCondition();

        // 未入金額を取得する
        double l_dblNonPayAmount = l_calc.calcNonPayAmount();

        // 当日請求額を取得する
        double l_dblCurrentBizDateDemandAmount =
            l_calc.getCurrentBizDateDemandAmount();

        // 翌日請求額を取得する
        double l_dblNextBizDateDemandAmount =
            l_calc.calcNextBizDateDemandAmount();

        // 翌々日請求額を取得する
        double l_dblNext2BizDateDemandAmount =
            l_calc.calcNext2BizDateDemandAmount();

        // 翌日請求額＜夕場＞を取得する
        double l_dblNextBizDateDemandAmountEvening =
            l_calc.calcNextBizDateDemandAmountEvening();

        // 証拠金振替余力額を算出する
        double l_dblIfoDepositTransferableAmount =
            l_calc.calcIfoDepositTransferableAmount();

        // 受渡日を取得する
        Date l_datDeliveryDate =
            l_calcCondition.getBizDate(l_calcCondition.getIfoDepositBaseDate());

        // SPAN区分を取得する
        String l_strSPANAdoptionDiv = WEB3IfoDepositSPANDivDef.NOT_ADOPTION;
        if (l_calcCondition.isSPANUsable())
        {
            l_strSPANAdoptionDiv = WEB3IfoDepositSPANDivDef.ADOPTION;
        }

        // 証拠金推移明細
        l_response.ifoDepositUnit =
            l_ifoDepositTransitionReferences;

        // 未入金額
        l_response.nonPayAmt =
            WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount);

        // 当日請求額
        l_response.todayClaimAmt =
            WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount);

        // 翌日請求額
        l_response.tomorrowClaimAmt =
            WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount);

        // 翌々日請求額
        l_response.dayAfterTomorrowClaimAmt =  
            WEB3StringTypeUtility.formatNumber(l_dblNext2BizDateDemandAmount);	

        // 翌日請求額＜夕場＞
        l_response.tomorrowClaimAmtEve =  
            WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmountEvening);	

        //振替余力額
        l_response.depositChangePower = 
            WEB3StringTypeUtility.formatNumber(l_dblIfoDepositTransferableAmount);

        // 受渡日
        l_response.deliveryDate = l_datDeliveryDate;

        // SPAN区分
        l_response.spanDiv = l_strSPANAdoptionDiv;

        //証拠金不足確定FLAG：
        //  ・（証拠金計算条件.is清算値速報受信済()==true  or  証拠金計算条件.is証拠金不足メール送信済()==true）の場合
        //      1：確定  
        //  ・以外
        //      0：未確定
        if ( l_calcCondition.isQuickReportReceived() || l_calcCondition.isIfoDepositMailFlag() ) 
        {
            l_response.fixedIfoDepositFlg = WEB3IfoDepositFixedIfoDepositFlgDiv.FIXED;
        }
        else
        {
            l_response.fixedIfoDepositFlg = WEB3IfoDepositFixedIfoDepositFlgDiv.NOT_FIXED;
        }
        
        //指数別証拠金
        l_response.ifoDepositPerIndexUnit = this.createIfoDepositPerIndexUnitList(l_calcCondition);


        return l_response;
    }

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
            
            //本日入金額：      
            //指定日(n) == 0の場合、先物OP顧客移動明細.get入金額[T+0]の戻り値
            //指定日(n) != 0の場合、先物OP顧客移動明細.get入金額[T+1]の戻り値
			if (i == 0)
            {
                // 入金額[T+0]を取得する
                double l_dblCashinAmount = l_transfer.getCurrentBizDateCashinAmount();

                l_reference.todayCashinAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblCashinAmount);
            }
            else if(i != 0)
            {
                // 入金額[T+1]を取得する
                double l_dblCashinAmount = l_transfer.getNextBizDateCashinAmount();

                l_reference.todayCashinAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblCashinAmount);
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
}
@
