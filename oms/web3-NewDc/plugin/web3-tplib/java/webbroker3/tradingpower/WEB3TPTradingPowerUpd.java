head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpd.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͍X�V�T�[�r�X(WEB3TPTradingPowerUpd.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/8/03 �� (fitechlabs) �V�K�쐬
                    2006/09/11 ���G�� (���u) ���f��No.011
                    2007/01/22 ���؎q (���u) ���f��No.092
 Revesion History : 2007/07/28 �����Q (���u) ���f��No.115
 Revesion History : 2007/09/28 �И��� (���u) ���f��No.178                   
 Revesion History : 2008/01/23 �И��� (���u) ���f��No.232 233
 Revesion History : 2008/01/31 �g�E�N�| (���u) ���f��No.252
 Revesion History : 2008/04/01 �����Q (���u) ���f��No.267 268 269 270
 Revesion History : 2008/10/20 ������ (���u) ���f��No.326
 Revesion History : 2008/11/26 ������ (���u) ���f��No.374
 Revesion History : 2009/12/24 ���g (���u) ���f��No.432
 */
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationPerProduct;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3DateUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (�]�͍X�V�T�[�r�X)<BR>
 * <BR>
 * �]�͍X�V�T�[�r�X�����N���X<BR>
 *
 * @@author �� (fitechlabs)
 * @@version 1.0
 */
public class WEB3TPTradingPowerUpd
{

    /**
     * �ڋq���� <BR>
     */
    protected WEB3TPAccountInfo accountInfo;

    /**
     * �v�Z���� <BR>
     */
    protected WEB3TPCalcCondition calcCondition;

    /**
     * �]�͌v�Z���g�p���錻�������e <BR>
     */
    protected WEB3TPNewOrderSpec[] newOrderSpecs;

    /**
     * �،��]���z <BR>
     */
    protected WEB3TPSecurityValuation securityValuation;

    /**
     * ���ʏ�� <BR>
     */
    protected WEB3TPContractInfo contractInfo;

    /**
     * ������ <BR>
     */
    protected WEB3TPCashValuation cashValuation;

    /**
     * �������� <BR>
     */
    protected WEB3TPSettlement settlement;

    /**
     * @@roseuid 410DF8980247
     */
    public WEB3TPTradingPowerUpd()
    {

    }

    /**
     * �i�]�͍X�V�T�[�r�X�j<BR>
     *
     * �]�͍X�V�T�[�r�X�R���X�g���N�^<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�u�]�͍X�V�T�[�r�X�R���X�g���N�g�v�Q��<BR>
     *
     * @@param l_lngAccountId - ����ID
     * @@param l_blnMarginFlag - �M�p�ڋq�t���O
     * @@param l_calcCondition - �v�Z����
     * @@param l_newOrderSpecs - ���������e
     * @@roseuid 410DDDBE00AC
     */
    public WEB3TPTradingPowerUpd(long l_lngAccountId, boolean l_blnMarginFlag,
                                 WEB3TPCalcCondition l_calcCondition,
                                 WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {

        //�@@1.1 �ڋq����(����ID : long, �M�p�ڋq�t���O : boolean) -- �ڋq�����쐬
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
            l_lngAccountId,
            l_blnMarginFlag);
        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
            l_accountInfo, l_calcCondition);
        
        // 1.2 create���ʏ��(�ڋq���� : �ڋq����, �v�Z���� : �v�Z����, ���������e : ���������e[])
        // --���ʏ��쐬
        WEB3TPContractInfo l_contractInfo = WEB3TPContractInfo.create(this.accountInfo,
            this.calcCondition, this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
        this.contractInfo = l_contractInfo;
        // 1.3 do���ʏ�񃍁[�h( ) --���ʏ�񃍁[�h
        this.contractInfo.loadContractInfo();

        // 1.4 create�،��]��(�ڋq���� : �ڋq����, �v�Z���� : �v�Z����, ���������e : ���������e[], ���ʏ�� : ���ʏ��)
        // --�،��]���쐬
        WEB3TPSecurityValuation l_securityValuation = WEB3TPSecurityValuation.
            create(this.accountInfo,
                   this.calcCondition, this.newOrderSpecs, l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);
        this.securityValuation = l_securityValuation;
        // 1.5 do�������Ə،��]���z���[�h( )--�������Ə،��]���z���[�h
        this.securityValuation.doSecurityValuationPerProductLoad();

        // 1.6 create������((�ڋq���� : �ڋq����, �v�Z���� : �v�Z����, ���������e : ���������e[]))
        // --�������쐬
        WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
            this.accountInfo,
            this.calcCondition, this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);
        this.cashValuation = l_cashValuation;
        // 1.7 do���������[�h( )--���������[�h
        this.cashValuation.loadAll();

        // 1.8 ��������
        this.settlement = new WEB3TPSettlement(this.cashValuation, this.securityValuation,
                                               this.calcCondition);
    }

    /**
     * (calc�]�͍X�V���e<�����ڋq>�j<BR>
     *<BR>
     * �����ڋq�̗]�͍X�V���e���v�Z����<BR>
     * <BR>
     * �V�[�P���X�}�ucalc�]�͍X�V���e<�����ڋq>�v�Q��<BR>
     * @@return java.util.List
     * @@roseuid 410DDDBE00B3
     */
    public List calcTradingpowerUpdResultEquity()
    {
        //�]�͌v�Z����
        TpCalcResultEquityParams l_params = new
            TpCalcResultEquityParams();

        //�]�͌v�Z���ʏڍ�
        TpCalcResultEquityDetailParams l_paramsDetail = new
        TpCalcResultEquityDetailParams();

        //T+0��T+5���擾����
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        //1.1.����ID
        l_params.setAccountId(this.accountInfo.getAccountId());
        l_paramsDetail.setAccountId(this.accountInfo.getAccountId());

        // 1.3.�@@�w���=T+0�`T+5�܂Ń��[�v����
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
        {
            //1.3.1.get�c�Ɠ�(int)
            Date l_datBizDate = this.calcCondition.getBizDate(i);

            // 1.3.2�@@get�a����c��(�w��� : Date)
            double l_dblAccountBalance = this.cashValuation.calcCashBalance(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setAccountBalance0(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setAccountBalance1(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setAccountBalance2(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setAccountBalance3(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setAccountBalance4(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setAccountBalance5(l_dblAccountBalance);
                    break;
                default:
                    break;
            }

            // 1.3.3�@@get�����������(�w��� : Date)
            double l_dblTodaysUnexecutedTotal = this.cashValuation.
                calcTodaysUnexecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayUnexecutedAmount0(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayUnexecutedAmount1(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayUnexecutedAmount2(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayUnexecutedAmount3(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayUnexecutedAmount4(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayUnexecutedAmount5(l_dblTodaysUnexecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.3.4�@@get�������ϑ��(�w��� : Date)
            double l_dblTodaysExecutedTotal = this.cashValuation.
                calcTodaysExecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayExecutedAmount0(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayExecutedAmount1(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayExecutedAmount2(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayExecutedAmount3(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayExecutedAmount4(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayExecutedAmount5(l_dblTodaysExecutedTotal);
                    break;
                default:
                    break;
            }


            // 1.3.5�@@get���̑��S����(�w��� : Date)
            double l_dblOtherRestraintsTotal = this.cashValuation.
                calcOtherRestraintsTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setOtherRestraint0(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setOtherRestraint1(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setOtherRestraint2(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setOtherRestraint3(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setOtherRestraint4(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setOtherRestraint5(l_dblOtherRestraintsTotal);
                    break;
                default:
                    break;
            }
            
            //1.3.6 calc�a����S�ۏo���S����(Date)
            //�a����S�ۏo���S�������擾����B 
            //[����] 
            //Date = get�c�Ɠ�()�̖߂�l 
            double l_dblCashDepositRestraint = 
                this.cashValuation.caleCashDepositRestraint(l_datBizDate);
            
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setCashDepositRestraint0(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setCashDepositRestraint1(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setCashDepositRestraint2(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setCashDepositRestraint3(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setCashDepositRestraint4(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setCashDepositRestraint5(l_dblCashDepositRestraint);
                    break;
                default:
                    break;
            }
            
            // 1.3.7�@@get���v��S����(�w��� : Date)
            double l_dblDateTradeRestraint = this.settlement.
                getDayTradeRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayTradeRestraint0(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayTradeRestraint1(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayTradeRestraint2(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayTradeRestraint3(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayTradeRestraint4(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayTradeRestraint5(l_dblDateTradeRestraint);
                    break;
                default:
                    break;
            }
            
            // 1.3.8�@@calc�a��،��]���z(�w��� : Date)
            double l_dblValuationPrice = this.securityValuation.calcValuationPrice(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTrustSecurityAsset0(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTrustSecurityAsset1(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTrustSecurityAsset2(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTrustSecurityAsset3(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTrustSecurityAsset4(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTrustSecurityAsset5(l_dblValuationPrice);
                    break;
                default:
                    break;
            }

            // 1.3.9�@@calc�o���z(�w��� : Date)
            double l_dblCashOut = this.cashValuation.
                calcCashOut(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setPaymentAmountDesignate0(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setPaymentAmountDesignate1(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setPaymentAmountDesignate2(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setPaymentAmountDesignate3(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setPaymentAmountDesignate4(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setPaymentAmountDesignate5(l_dblCashOut);
                    break;
                default:
                    break;
            }
            
            //1.3.10. T+0 ���� T+5�̎����s(��c�Ɩ�c)
            if((WEB3DateUtility.compareToDay(l_datBizDate0, l_datBizDate) == 0)
                    || (WEB3DateUtility.compareToDay(l_datBizDate5, l_datBizDate) == 0))
            {
                //1.3.10.1.calc�a�莑�Y�]���z(�w��� : Date)
                // �����]���z
                double l_dblEquityAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.2. �����~�j�����]���z
                double l_dblMiniStockAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.MINI_STOCK);
                //1.3.10.3. �ݐϓ����]���z
                double l_dblRuitoAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.RUITO, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.4. �����M���]���z
                double l_dblMutualFundAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.MUTUAL_FUND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.5. ���]���z
                double l_dblBondAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.BOND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //�O����
                double l_dblFeqAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.FOREIGN_EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                switch (i)
                {
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_paramsDetail.setEquityAssetDelivered(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetDelivered(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetDelivered(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetDelivered(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetDelivered(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetDelivered(l_dblFeqAsset);
                        break;
                    case WEB3TPSpecifiedPointDef.T_5:
                        l_paramsDetail.setEquityAssetExecuted(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetExecuted(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetExecuted(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetExecuted(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetExecuted(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetExecuted(l_dblFeqAsset);
                        break;
                    default:
                        break;
                }
            }

        }

        Timestamp l_tsNow = this.calcCondition.getSystemTimeStamp();
        l_params.setCreatedTimestamp(l_tsNow);
        l_params.setLastUpdatedTimestamp(l_tsNow);
        l_paramsDetail.setCreatedTimestamp(l_tsNow);
        l_paramsDetail.setLastUpdatedTimestamp(l_tsNow);

        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * (calc�]�͍X�V���e<�M�p�ڋq>�j
     *
     * �M�p�ڋq�̗]�͍X�V���e���v�Z����<BR>
     * <BR>
     * �V�[�P���X�}�ucalc�]�͍X�V���e<�M�p�ڋq>�v�Q��<BR>
     * @@param l_strMarkToMarketDiv - �l�􂢋敪
     * @@return java.util.List
     * @@roseuid 410DDDBE00B4
     */
    public List calcTradingpowerUpdResultMargin(String l_strMarkToMarketDiv)
    {

        //�]�͌v�Z����
        TpCalcResultMarginParams l_params = new
            TpCalcResultMarginParams();

        //�]�͌v�Z���ʏڍ�
        TpCalcResultMarginDetailParams l_paramsDetail = new
        TpCalcResultMarginDetailParams();

        //T+0��T+5���擾����
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //����ID
        l_params.setAccountId(this.accountInfo.getAccountId());
        l_paramsDetail.setAccountId(this.accountInfo.getAccountId());

        // 1.1�@@�w���=T+0�`T+5�܂Ń��[�v����
        for (int i = 0; i <= 5; i++)
        {

            Date l_datBizDate = this.calcCondition.getBizDate(i);

            // 1.1.1�@@get�a����c��(�w��� : Date)
            double l_dblAccountBalance = this.cashValuation.calcCashBalance(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setAccountBalance0(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setAccountBalance1(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setAccountBalance2(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setAccountBalance3(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setAccountBalance4(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setAccountBalance5(l_dblAccountBalance);
                    break;
                default:
                    break;
            }

            // 1.1.2�@@get�����������(�w��� : Date)
            double l_dblTodaysUnexecutedTotal = this.cashValuation.
                calcTodaysUnexecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayUnexecutedAmount0(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayUnexecutedAmount1(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayUnexecutedAmount2(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayUnexecutedAmount3(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayUnexecutedAmount4(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayUnexecutedAmount5(l_dblTodaysUnexecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.1.3�@@get�������ϑ��(�w��� : Date)
            double l_dblTodaysExecutedTotal = this.cashValuation.
                calcTodaysExecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayExecutedAmount0(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayExecutedAmount1(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayExecutedAmount2(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayExecutedAmount3(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayExecutedAmount4(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayExecutedAmount5(l_dblTodaysExecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.1.4�@@get���v��S����(�w��� : Date)
            double l_dblDateTradeRestraint = this.settlement.
                getDayTradeRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayTradeRestraint0(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayTradeRestraint1(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayTradeRestraint2(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayTradeRestraint3(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayTradeRestraint4(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayTradeRestraint5(l_dblDateTradeRestraint);
                    break;
                default:
                    break;
            }

            // 1.1.5�@@get���̑��S����(�w��� : Date)
            double l_dblOtherRestraintsTotal = this.cashValuation.
                calcOtherRestraintsTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setOtherRestraint0(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setOtherRestraint1(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setOtherRestraint2(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setOtherRestraint3(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setOtherRestraint4(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setOtherRestraint5(l_dblOtherRestraintsTotal);
                    break;
                default:
                    break;
            }

            // 1.1.6�@@get�����ό��ʂ̏W�v(�w��� : Date)
            WEB3TPSummaryOpenContract l_openContract = this.
                contractInfo.getSummaryOpenContract(l_datBizDate);
            //���������ό��ʑ��
            double l_dblContractAmount = l_openContract.getContractAmount();
            //���������ϕK�v�ۏ؋�
            double l_dblMarginDeposit = l_openContract.getMarginDeposit();
            //���������ό����K�v�ۏ؋�
            double l_dblCashMarginDeposit = l_openContract.getCashMarginDeposit();
            //���������ʑ��
            double l_dblUnExecContractAmount = l_openContract.getUnExecContractAmount();
            //�������K�v�ۏ؋�
            double l_dblUnExecMarginDeposit = l_openContract.getUnExecMarginDeposit();
            //�����������K�v�ۏ؋�
            double l_dblUnExecCashMarginDeposit = l_openContract.getUnExecCashMarginDeposit();
            //�]����
            double l_dblAssetLoss = l_openContract.getAssetLoss();
            //�]���v
            double l_dblAssetProfit = l_openContract.getAssetProfit();
            //���萔��
            double l_dblSetupFee = l_openContract.getSetupFee();
            //�����E�t������
            double l_dblInterestLoss = l_openContract.getInterestLoss();
            //�����E�t�����v
            double l_dblInterestProfit = l_openContract.getInterestProfit();
            //���̑����ʏ��o��
            double l_dblOtherCost = l_openContract.getOtherCost();

            //�v�Z���ʃe�[�u�����K�v�Ȃ̂́A���������ό��� + ����������
            double l_dblContractAmountDB = l_dblContractAmount + l_dblUnExecContractAmount;
            double l_dblMarginDepositDB = l_dblMarginDeposit + l_dblUnExecMarginDeposit;
            double l_dblCashMarginDepositDB = l_dblCashMarginDeposit + l_dblUnExecCashMarginDeposit;
            
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setContractAmount0(l_dblContractAmountDB);
                    l_params.setMarginDeposit0(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit0(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount0(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit0(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit0(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setContractAmount1(l_dblContractAmountDB);
                    l_params.setMarginDeposit1(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit1(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss1(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost1(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount1(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit1(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit1(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss1(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit1(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee1(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss1(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit1(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost1(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setContractAmount2(l_dblContractAmountDB);
                    l_params.setMarginDeposit2(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit2(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss2(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost2(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount2(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit2(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit2(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss2(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit2(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee2(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss2(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit2(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost2(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setContractAmount3(l_dblContractAmountDB);
                    l_params.setMarginDeposit3(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit3(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss3(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost3(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount3(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit3(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit3(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss3(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit3(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee3(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss3(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit3(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost3(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setContractAmount4(l_dblContractAmountDB);
                    l_params.setMarginDeposit4(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit4(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss4(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost4(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount4(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit4(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit4(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss4(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit4(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee4(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss4(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit4(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost4(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setContractAmount5(l_dblContractAmountDB);
                    l_params.setMarginDeposit5(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit5(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss5(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost5(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount5(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit5(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit5(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss5(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit5(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee5(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss5(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit5(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost5(l_dblOtherCost);
                    break;
                default:
                    break;
            }

            // 1.1.7�@@get����n���ʂ̏W�v(�w��� : Date)
            WEB3TPSummaryUndeliveredContract l_undeliveredContract = this.
                contractInfo.getSummaryUndeliveredContract(l_datBizDate);
            //����n���ʑ��
            double l_dblUndeliveredContractAmount = l_undeliveredContract.
                getContractAmount();
            //����n���ʌ��ϑ�
            double l_dblUndeliveredContractLoss = l_undeliveredContract.
                getContractLoss();
            //����n���ʌ��ωv
            double l_dblUndeliveredContractProfit = l_undeliveredContract.
                getContractProfit();
            //����n���ʕK�v�ۏ؋�
            double l_dblUndeliMarginDeposit = l_undeliveredContract.getMarginDeposit();
            //����n���ʌ����K�v�ۏ؋�
            double l_dblUndeliCashMarginDeposit = l_undeliveredContract.
                getCashMarginDeposit();
            //���ʌ��ϑ�<����>
            double l_dblTodayRepayContractLoss = l_undeliveredContract.getTodayRepayContractLoss();
            //���ʌ��ωv<����>
            double l_dblTodayRepayContractProfit = l_undeliveredContract.getTodayRepayContractProfit();
            //���ό��ʑO�����i�]��<����>
            double l_dblTodayRepayContractPreAsset = l_undeliveredContract.getTodayRepayContractPrevAsset();
            //���ʌ��ϑ�<�w���>
            double l_dblDesignateContractLoss = l_undeliveredContract.getDesignateDateContractLoss();
            //���ʌ��ωv<�w���>
            double l_dblDesignateContractProfit = l_undeliveredContract.getDesignateDateContractProfit();

            //get���v��ԍρE�������n���ʂ̏W�v(�w��� : Date)
            WEB3TPSummaryDayTradeSwapContract l_dayTradeSwapContract =
                this.contractInfo.getSummaryDayTradeSwapContract(l_datBizDate);

            //�������n���ʌ��ϑ�
            BigDecimal l_bdSwapContractSettleLoss =
                new BigDecimal(Double.toString(l_dayTradeSwapContract.getSwapContractSettleLoss()));
            
            BigDecimal l_bdUndeliveredContractLoss =
                new BigDecimal(Double.toString(l_dblUndeliveredContractLoss));

            double l_dblUndeliContractLoss =
                l_bdUndeliveredContractLoss.add(l_bdSwapContractSettleLoss).doubleValue();

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setUndeliContractAmount0(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss0(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit0(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit0(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit0(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);
                    l_paramsDetail.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
                    l_paramsDetail.setTodayRepayContractPreAsset(l_dblTodayRepayContractPreAsset);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setUndeliContractAmount1(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss1(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit1(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit1(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit1(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate1(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate1(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setUndeliContractAmount2(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss2(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit2(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit2(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit2(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate2(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate2(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setUndeliContractAmount3(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss3(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit3(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit3(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit3(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate3(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate3(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setContractLossDesignate4(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate4(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setContractLossDesignate5(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate5(l_dblDesignateContractProfit);
                    break;
                default:
                    break;
            }

            //���v��ԍρE�������n���ʑ��
            double l_dblDayRepayContractAmount = l_dayTradeSwapContract.getContractAmount();
            //���v��ԍρE�������n���ʕK�v�ۏ؋�
            double l_dblDayRepayMarginDeposit = l_dayTradeSwapContract.getMarginDeposit();
            //���v��ԍρE�������n���ʌ����K�v�ۏ؋�
            double l_dblDayRepayCashMarginDeposit = l_dayTradeSwapContract.getCashMarginDeposit();

            //�������n���ʕ]����
            double l_dblSwapLoss = l_dayTradeSwapContract.getSwapContractAssetLoss();
            //�������n���ʕ]���v
            double l_dblSwapProfit = l_dayTradeSwapContract.getSwapContractAssetProfit();
            
            //�K�v�ۏ؋��iMarginDeposit�j= �����ό��ʕK�v�ۏ؋��{���v��ԍρE�������n���ʕK�v�ۏ؋�
            //�K�v�����ۏ؋��iCashMarginDeposit�j= �����ό��ʕK�v�����ۏ؋��{���v��ԍρE�������n���ʕK�v�����ۏ؋�
            //�����ό��ʕ]�����v = �����ό��ʕ]�����v�{�������n���ʕ]���v - �������n���ʕ]����
            //�����ό��ʕ]���v = �����ό��ʕ]���v�{�������n���ʕ]���v
            //�����ό��ʕ]���� = �����ό��ʕ]�����{�������n���ʕ]����
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayRepayContractAmount0(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit0(l_params.getMarginDeposit0() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit0(l_params.getCashMarginDeposit0() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss(l_params.getContractAssetProfitLoss() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit0(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit0(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss(l_paramsDetail.getContractAssetLoss() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit(l_paramsDetail.getContractAssetProfit() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayRepayContractAmount1(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit1(l_params.getMarginDeposit1() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit1(l_params.getCashMarginDeposit1() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss1(l_params.getContractAssetProfitLoss1() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit1(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit1(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss1(l_paramsDetail.getContractAssetLoss1() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit1(l_paramsDetail.getContractAssetProfit1() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayRepayContractAmount2(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit2(l_params.getMarginDeposit2() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit2(l_params.getCashMarginDeposit2() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss2(l_params.getContractAssetProfitLoss2() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit2(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit2(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss2(l_paramsDetail.getContractAssetLoss2() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit2(l_paramsDetail.getContractAssetProfit2() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayRepayContractAmount3(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit3(l_params.getMarginDeposit3() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit3(l_params.getCashMarginDeposit3() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss3(l_params.getContractAssetProfitLoss3() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit3(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit3(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss3(l_paramsDetail.getContractAssetLoss3() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit3(l_paramsDetail.getContractAssetProfit3() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayRepayContractAmount4(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit4(l_params.getMarginDeposit4() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit4(l_params.getCashMarginDeposit4() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss4(l_params.getContractAssetProfitLoss4() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit4(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit4(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss4(l_paramsDetail.getContractAssetLoss4() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit4(l_paramsDetail.getContractAssetProfit4() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayRepayContractAmount5(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit5(l_params.getMarginDeposit5() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit5(l_params.getCashMarginDeposit5() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss5(l_params.getContractAssetProfitLoss5() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit5(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit5(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss5(l_paramsDetail.getContractAssetLoss5() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit5(l_paramsDetail.getContractAssetProfit5() + l_dblSwapProfit);
                    break;
                default:
                    break;
            }

            // 1.1.9�@@calc��p�،��]���z(�w��� : Date)
            double l_dblSubstituteValuationPrice = this.securityValuation.
                calcSubstituteValuationPrice(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setSubstituteSecurityAsset0(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setSubstituteSecurityAsset1(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setSubstituteSecurityAsset2(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setSubstituteSecurityAsset3(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setSubstituteSecurityAsset4(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setSubstituteSecurityAsset5(l_dblSubstituteValuationPrice);
                    break;
                default:
                    break;
            }

            // 1.1.10�@@calc�o���z(�w��� : Date)
            double l_dblCashOut = this.cashValuation.calcCashOut(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setPaymentAmountDesignate0(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setPaymentAmountDesignate1(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setPaymentAmountDesignate2(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setPaymentAmountDesignate3(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setPaymentAmountDesignate4(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setPaymentAmountDesignate5(l_dblCashOut);
                    break;
                default:
                    break;
            }

            // 1.1.11�@@calc�������������S����(�c�Ɠ� : Date)
            double l_dblTodayDepFundRestraint = 
                this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setTodayDepFundRestraint0(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setTodayDepFundRestraint1(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setTodayDepFundRestraint2(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setTodayDepFundRestraint3(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setTodayDepFundRestraint4(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setTodayDepFundRestraint5(l_dblTodayDepFundRestraint);
                    break;
                default:
                    break;
            }

            // 1.1.11�@@calc��������p�،��]���z(�w��� : Date)
            double l_dblUnExecSubstituteValuationPrice = this.securityValuation.
                calcUnExecSubstituteValuationPrice(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setUnexecSubstiSecurityAsset0(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setUnexecSubstiSecurityAsset1(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setUnexecSubstiSecurityAsset2(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setUnexecSubstiSecurityAsset3(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setUnexecSubstiSecurityAsset4(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setUnexecSubstiSecurityAsset5(l_dblUnExecSubstituteValuationPrice);
                    break;
                default:
                    break;
            }
            
            // T+0 ���� T+5�̎����s(��c�Ɩ�c)
            if((WEB3DateUtility.compareToDay(l_datBizDate0, l_datBizDate) == 0)
                    || (WEB3DateUtility.compareToDay(l_datBizDate5, l_datBizDate) == 0))
            {
                // 1.1.12�@@calc�a�莑�Y�]���z(�w��� : Date)
                // �����]���z
                double l_dblEquityAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // �����~�j�����]���z
                double l_dblMiniStockAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.MINI_STOCK);
                // �ݐϓ����]���z
                double l_dblRuitoAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.RUITO, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // �����M���]���z
                double l_dblMutualFundAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.MUTUAL_FUND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // ���]���z
                double l_dblBondAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.BOND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // �O����
                double l_dblFeqAsset = this.securityValuation.calcAssetValuationPrice(
                    l_datBizDate, ProductTypeEnum.FOREIGN_EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                switch (i)
                {
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_paramsDetail.setEquityAssetDelivered(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetDelivered(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetDelivered(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetDelivered(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetDelivered(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetDelivered(l_dblFeqAsset);
                        break;
                    case WEB3TPSpecifiedPointDef.T_5:
                        l_paramsDetail.setEquityAssetExecuted(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetExecuted(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetExecuted(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetExecuted(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetExecuted(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetExecuted(l_dblFeqAsset);
                        break;
                    default:
                        break;
                }
            }

        }

        // get�����ԍό��ʑ���̏W�v
        double l_dblSummaryTodayRepayContractAmount = this.contractInfo.getSummaryTodayRepayContractAmount();

        //calc��p�،��]���z(�O���P���]��)
        double l_dblPrevPriceSubstituteValuation = this.securityValuation.calcPrevPriceSubstituteValuation();

        l_paramsDetail.setTodayRepayContractAmount(l_dblSummaryTodayRepayContractAmount);
        l_paramsDetail.setSubstituteAssetOldDayValue(l_dblPrevPriceSubstituteValuation);

        l_params.setMarkToMarketDiv(l_strMarkToMarketDiv);
        Timestamp l_tsNow = this.calcCondition.getSystemTimeStamp();
        l_params.setCreatedTimestamp(l_tsNow);
        l_params.setLastUpdatedTimestamp(l_tsNow);
        l_paramsDetail.setCreatedTimestamp(l_tsNow);
        l_paramsDetail.setLastUpdatedTimestamp(l_tsNow);

        //(*)����t���[
        if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(
            calcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV)))
        {
            try
            {
                //calc�o���S����(double, double, double, double)
                //double = �]�͌v�Z����(�M�p�ڋq)Params.�a����c��(T+1)
                //double = �]�͌v�Z����(�M�p�ڋq)Params.�����������(T+1)
                //double = �]�͌v�Z����(�M�p�ڋq)Params.�������ϑ��(T+1)
                //double = �]�͌v�Z���ʏڍ�(�M�p�ڋq)Params.���������Ώۖ����S����(T+1)
                double l_dblCalcCashoutRestraint = cashValuation.calcCashoutRestraint(
                    l_params.getAccountBalance1(),
                    l_params.getTodayUnexecutedAmount1(),
                    l_params.getTodayExecutedAmount1(),
                    l_paramsDetail.getTodayDepFundRestraint1());

                //�]�͌v�Z����(�M�p�ڋq)Params�I�u�W�F�N�g�ɒl���Z�b�g����
                //���̑��S����( T + 0 ) = ���̑��S����( T + 0 ) + calc�o���S����()�̕ԋp�l
                BigDecimal l_bdOtherRestraint0 =
                    new BigDecimal(Double.toString(l_params.getOtherRestraint0()));
                BigDecimal l_bdCalcCashoutRestraint =
                    new BigDecimal(Double.toString(l_dblCalcCashoutRestraint));
                l_params.setOtherRestraint0(
                    l_bdOtherRestraint0.add(l_bdCalcCashoutRestraint).doubleValue());

                //�]�͌v�Z���ʏڍ�(�M�p�ڋq)Params�I�u�W�F�N�g�ɒl���Z�b�g����
                //���������Ώۖ����S����( T + 0 ) = ���������Ώۖ����S����( T + 0 ) + calc�o���S����()�̕ԋp�l
                BigDecimal l_bdTodayDepFundRestraint0 =
                    new BigDecimal(Double.toString(l_paramsDetail.getTodayDepFundRestraint0()));
                l_paramsDetail.setTodayDepFundRestraint0(
                    l_bdTodayDepFundRestraint0.add(l_bdCalcCashoutRestraint).doubleValue());
            }
            catch (WEB3BaseException l_ex)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + ".calcTradingpowerUpdResultMargin",
                    "�\�����Ȃ��V�X�e���G���[���������܂����B");
            }
        }
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;

    }

    /**
     * �icalc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`�j<BR>
     * <BR>
     * �����ڋq�̗]�͍X�V���e���v�Z���� <BR>
     * ����蔄�t�������l���������v��S�������擾����B<BR>
     * �i�o���𔺂������̎���]�̓`�F�b�N���ɃR�[�������B�j<BR>
     * <BR>
     * �P�jthis.calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@�������ϑ����O���t�����l���t���O�Ffalse<BR>
     * <BR>
     * @@return List
     */
    public List calcTradingpowerUpdResultEquityIncUnexecSellOrder()
    {
    	return this.calcTradingpowerUpdResultEquityIncUnexecSellOrder(false);
    }

    /**
     * �icalc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`�j<BR>
     * <BR>
     * �����ڋq�̗]�͍X�V���e���v�Z����<BR>
     * ����蔄�t�������l���������v��S�������擾����B<BR>
     * �i�o���𔺂������̎���]�̓`�F�b�N���ɃR�[�������B�j<BR>
     * <BR>
     * �P�j�]�͍X�V���e<�����ڋq>���X�g���擾����B(�����v��S�����́A����蔄�t������l��)<BR>
     * �@@- this.�]�͍X�V���e<�����ڋq>()���R�[��<BR>
     * <BR>
     * �Q�jT+0�`T+5�܂ł́A���v��S�����`����蔄�t�����l���`���擾����B<BR>
     * <BR>
     * �@@��LOOP�����iT+0�`T+5�j��<BR>
     * �@@�@@- this.��������.get���v��S�����`����蔄�t�����l���`()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�w����FT+n<BR>
     * �@@�@@�@@�������ϑ����O���t�����l���t���O�F����.�������ϑ����O���t�����l���t���O<BR>
     * <BR>
     * �R�j�P�j�Ŏ擾�����]�͍X�V���e<�����ڋq>���X�g�̓��v��S����(T+0..5)�̒l���A<BR>
     * �@@�@@�Q�j�Ŏ擾�������v��S�����`����蔄�t�����l���`(T+0..5)�ŏ㏑������B<BR>
     * <BR>
     * �@@�R�|�P�j�]�͍X�V���e<�����ڋq>���X�g���A�]�͌v�Z����Params<�����ڋq>���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�擾�����]�͌v�Z����Params<�����ڋq>.���v��S����(T+0..5)�ɁA�l���Z�b�g����B<BR>
     * �@@�@@�@@- �]�͌v�Z����Params<�����ڋq>.set���v��S����(T+0..5)()���R�[��<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@double�F�Q�j�Ŏ擾�������v��S�����`����蔄�t�����l���`(T+0..5)<BR>
     * <BR>
     * �S�j���v��S�����`����蔄�t�����`���Z�b�g���ꂽ�]�͍X�V���e<�����ڋq>���X�g��ԋp����B<BR>
     * <BR>
     * @@param l_blnIsSettlement - (�������ϑ����O���t�����l���t���O)
     * @@return java.util.List
     */
    public List calcTradingpowerUpdResultEquityIncUnexecSellOrder(boolean l_blnIsSettlement)
    {
        /*
         * �]�͍X�V���e <�����ڋq>���X�g���擾����B
         */
        List l_calcResult = this.calcTradingpowerUpdResultEquity();

        /*
         * List���]�͌v�Z���� <�����ڋq>�I�u�W�F�N�g���擾�� 
         * ���v��S�����`����蔄�t�����l���`(T+0..5)���Z�b�g����B
         */
        //�]�͌v�Z����<�����ڋq>Params
        TpCalcResultEquityParams l_params = null;
        //�]�͌v�Z����<�����ڋq>�ڍ�Params
        TpCalcResultEquityDetailParams l_paramsDetail = null;

        for(Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object) l_iter.next();

            if(l_element instanceof TpCalcResultEquityParams)
            {
                //�]�͌v�Z����<�����ڋq>Params���擾
                l_params = (TpCalcResultEquityParams) l_element;

                //LOOP�����i�w���=T+0�`T+5�̊ԁj
                for(int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
                {
                    //�w���(Date�^)���擾
                    Date l_datBizDate = this.calcCondition.getBizDate(i);

                    //���v��S�����`����蔄�t�����l���`(�w��� : Date)���擾
                    double l_dblDateTradeRestraint = this.settlement
                        .getDayTradeRestraintIncUnexecSellOrder(l_datBizDate, l_blnIsSettlement);

                    //���v��S�����`����蔄�t�����l���`(T+0..5)���㏑��
                    switch(i)
                    {
                        case WEB3TPSpecifiedPointDef.T_0:
                            l_params
                                .setDayTradeRestraint0(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_1:
                            l_params
                                .setDayTradeRestraint1(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_2:
                            l_params
                                .setDayTradeRestraint2(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_3:
                            l_params
                                .setDayTradeRestraint3(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_4:
                            l_params
                                .setDayTradeRestraint4(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_5:
                            l_params
                                .setDayTradeRestraint5(l_dblDateTradeRestraint);
                            break;
                        default:
                            break;
                    }
                }
            }
            else if(l_element instanceof TpCalcResultEquityDetailParams)
            {
                l_paramsDetail = (TpCalcResultEquityDetailParams) l_element;
            }
        }

        //�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`���X�g��ԋp����B
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * �icalc�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`�j<BR>
     * <BR>
     * �M�p�ڋq�̗]�͍X�V���e���v�Z����<BR>
     * ����蔄�t�������l���������v��S�������擾����B<BR>
     * �i�o���𔺂������̎���]�̓`�F�b�N���ɃR�[�������B�j<BR>
     * <BR>
     * �P�j�]�͍X�V���e<�M�p�ڋq>���X�g���擾����B(�����v��S�����́A����蔄�t������l��)<BR>
     * �@@- this.�]�͍X�V���e<�M�p�ڋq>()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�l�􂢋敪�F0:�ʏ�<BR>
     * <BR>
     * �Q�jT+0�`T+5�܂ł́A���v��S�����`����蔄�t�����l���`���擾����B<BR>
     * <BR>
     * �@@��LOOP�����iT+0�`T+5�j��<BR>
     * �@@�@@- this.��������.get���v��S�����`����蔄�t�����l���`()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�w����FT+n<BR>
     * <BR>
     * �R�j�P�j�Ŏ擾�����]�͍X�V���e<�M�p�ڋq>���X�g�̓��v��S����(T+0..5)�̒l���A<BR>
     * �@@�@@�Q�j�Ŏ擾�������v��S�����`����蔄�t�����l���`(T+0..5)�ŏ㏑������B<BR>
     * <BR>
     * �@@�R�|�P�j�]�͍X�V���e<�M�p�ڋq>���X�g���A�]�͌v�Z����Params<�M�p�ڋq>���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�擾�����]�͌v�Z����Params<�M�p�ڋq>.���v��S����(T+0..5)�ɁA�l���Z�b�g����B<BR>
     * �@@�@@�@@- �]�͌v�Z����Params<�M�p�ڋq>.set���v��S����(T+0..5)()���R�[��<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@double�F�Q�j�Ŏ擾�������v��S�����`����蔄�t�����l���`(T+0..5)<BR>
     * <BR>
     * �S�j���v��S�����`����蔄�t�����`���Z�b�g���ꂽ�]�͍X�V���e<�M�p�ڋq>���X�g��ԋp����B<BR>
     * <BR>
     * @@return java.util.List
     */
    public List calcTradingpowerUpdResultMarginIncUnexecSellOrder()
    {
        /*
         * �]�͍X�V���e <�M�p�ڋq>���X�g���擾����B
         */
        List l_calcResult = this
            .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

        /*
         * List���]�͌v�Z���� <�M�p�ڋq>�I�u�W�F�N�g���擾�� ���v��S�����`����蔄�t�����l���`(T+0..5)���Z�b�g����B
         */
        //�]�͌v�Z����<�M�p�ڋq>Params
        TpCalcResultMarginParams l_params = null;
        //�]�͌v�Z����<�M�p�ڋq>�ڍ�Params
        TpCalcResultMarginDetailParams l_paramsDetail = null;

        for(Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object) l_iter.next();

            if(l_element instanceof TpCalcResultMarginParams)
            {
                //�]�͌v�Z����<�M�p�ڋq>Params���擾
                l_params = (TpCalcResultMarginParams) l_element;

                //LOOP�����i�w���=T+0�`T+5�̊ԁj
                for(int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
                {
                    //�w���(Date�^)���擾
                    Date l_datBizDate = this.calcCondition.getBizDate(i);

                    //���v��S�����`����蔄�t�����l���`(�w��� : Date)���擾
                    double l_dblDateTradeRestraint = this.settlement
                        .getDayTradeRestraintIncUnexecSellOrder(l_datBizDate);

                    //���v��S�����`����蔄�t�����l���`(T+0..5)���㏑��
                    switch(i)
                    {
                        case WEB3TPSpecifiedPointDef.T_0:
                            l_params
                                .setDayTradeRestraint0(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_1:
                            l_params
                                .setDayTradeRestraint1(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_2:
                            l_params
                                .setDayTradeRestraint2(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_3:
                            l_params
                                .setDayTradeRestraint3(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_4:
                            l_params
                                .setDayTradeRestraint4(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_5:
                            l_params
                                .setDayTradeRestraint5(l_dblDateTradeRestraint);
                            break;
                        default:
                            break;
                    }
                }
            }
            else if(l_element instanceof TpCalcResultMarginDetailParams)
            {
                l_paramsDetail = (TpCalcResultMarginDetailParams) l_element;
            }
        }

        //�]�͍X�V���e<�M�p�ڋq>�`����蔄�t�����l���`���X�g��ԋp����B
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * (get�������Ƒ�p�،��]���z)
     *
     * �������Ƒ�p�،��]���z���擾����B<BR>
     *
     * <BR>
     * �V�[�P���X�}�uget�������Ƒ�p�،��]���z�v�Q��<BR>
     * @@param l_lngProductId - ����ID
     * @@param l_intSpecifiedPoint - �w���
     * @@return double
     * @@roseuid 410DDDBE00B5
     */
    public double getSubstituteValuationPriceParProduct(long l_lngProductId,
        int l_intSpecifiedPoint)
    {

        // 1.1�@@�����ڋq�̏ꍇ�A0.0��Ԃ�
        if (!this.accountInfo.isMarginCustomer())
        {
            return 0.0;
        }

        // 1.2 get�Ώۖ���(����ID : long) --�Ώۖ������擾
        WEB3TPSecurityValuationProduct l_product = this.securityValuation.
            getProduct(l_lngProductId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        //�Ώۖ���==null�̏ꍇ
        if(l_product == null)
        {
            return 0.0;
        }
        
        // 1.3 get�������Ə،��]���z(�Ώۖ��� --�������Ə،��]���z�I�u�W�F�N�g���擾
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct = this.
            securityValuation.getSecurityValuationPerProduct(l_product);

        // 1.4 get�c�Ɠ�(�w��� : int) --�c�Ɠ����擾
        java.util.Date l_datBizDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        // 1.5�@@get�،��]���z(�w��� : Date, �a��敪) --�������Ƒ�p�،��]���z���擾
        return l_valuationPerProduct.getValuationPrice(l_datBizDate,
            WEB3TPDepositTypeDef.SUBSTITUTE);

    }

    /**
     * (get���ʖ����ꗗ)<BR>
     *
     * �ۗL���ʂ̖���ID�ꗗ���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�uget���ʖ����ꗗ�v�Q��<BR>
     * @@param l_intSpecifiedPoint - �w���
     * @@param l_contractType - ���敪
     * @@return long[]
     */
    public long[] getContractProducts(int l_intSpecifiedPoint, ContractTypeEnum l_contractType)
    {
        // 1.1�@@�����ڋq�̏ꍇ�Anull��Ԃ�
        if (!this.accountInfo.isMarginCustomer())
        {
            return null;
        }

        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);
        return this.contractInfo.getContractProducts(l_datDate, l_contractType);
    }

    /**
     * (save�]�͍X�V���e<�����ڋq>�j
     *
     * �����ڋq�̗]�͍X�V���e��DB�֕ۑ�����<BR>
     * <BR>
     * @@param java.util.List - �����ڋq�̗]�͍X�V���e
     * @@roseuid 410DDDBE00B9
     */
    public void saveTradingpowerUpdResultEquity(List l_list)
    {

        WEB3TPPersistentDataManager.getInstance().
            saveTradingpowerUpdResultEquity(l_list);

    }

    /**
     * (save�]�͍X�V���e<�M�p�ڋq>�j
     *
     * �M�p�ڋq�̗]�͍X�V���e��DB�֕ۑ�����<BR>
     * <BR>
     * @@param java.util.List - �M�p�ڋq�̗]�͍X�V���e
     * @@roseuid 410DDDBE00BB
     */
    public void saveTradingpowerUpdResultMargin(List l_list)
    {

        WEB3TPPersistentDataManager.getInstance().
            saveTradingpowerUpdResultMargin(l_list);

    }

    /**
     * (get�ڋq����)<BR>
     * �ڋq�������擾����B
     * @@return WEB3TPAccountInfo
     */
    public WEB3TPAccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }

    /**
     * (get�v�Z����)<BR>
     * �v�Z�������擾����B
     * @@return WEB3TPCalcCondition
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * (get������)<BR>
     * ���������擾����B
     * @@return WEB3TPCashValuation
     */
    public WEB3TPCashValuation getCashValuation()
    {
        return this.cashValuation;
    }

    /**
     * (get���ʏ��)<BR>
     * ���ʏ����擾����B
     * @@return WEB3TPContractInfo
     */
    public WEB3TPContractInfo getContractInfo()
    {
        return this.contractInfo;
    }

    /**
     * (get�،��]��)<BR>
     * �،��]�����擾����B
     * @@return WEB3TPSecurityValuation
     */
    public WEB3TPSecurityValuation getSecurityValuation()
    {
        return this.securityValuation;
    }

    /**
     * (get��������)<BR>
     * �������ς��擾����B
     * @@return WEB3TPSettlement
     */
    public WEB3TPSettlement getSettlement()
    {
        return this.settlement;
    }

    /**
     * (get���������e)<BR>
     * ���������e���擾����B
     * @@return WEB3TPNewOrderSpec[]
     */
    public WEB3TPNewOrderSpec[] getNewOrderSpecs()
    {
        return this.newOrderSpecs;
    }

}
@
