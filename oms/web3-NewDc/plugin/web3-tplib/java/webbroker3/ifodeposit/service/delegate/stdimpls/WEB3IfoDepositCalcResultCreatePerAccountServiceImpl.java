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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceImpl�N���X(WEB3IfoDepositCalcResultCreatePerAccountServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/24 ��(FLJ) �V�K�쐬
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
 * (�ڋq�؋����v�Z���ʍ쐬�T�[�r�XImpl)<BR>
 * �ڋq�؋����v�Z���ʍ쐬�T�[�r�X�����N���X�B<BR>
 * 
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultCreatePerAccountServiceImpl implements WEB3IfoDepositCalcResultCreatePerAccountService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultSaveServiceImpl.class);//

    /**
     *	�R���X�g���N�^�[  
     */
    public WEB3IfoDepositCalcResultCreatePerAccountServiceImpl()
    {
        super();
    }

    /**
     * �ڋq�؋����v�Z���ʍ쐬�T�[�r�X�����{����B
     * 
     * @@param l_request
     *  - (���N�G�X�g�f�[�^)
     * ���N�G�X�g
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
            l_param.setDepositInfoId(IfoDepositCalcResultDao.newPkValue());//�؋������h�c
            l_param.setAccountId(l_subAccount.getAccountId());//�����h�c
            l_param.setBaseDate(l_strBaseDate);//���
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_e.getMessage(),
                    l_e);
        }

        try
        {
            //�؋����v�Z�T�[�r�X���擾����
            WEB3IfoDepositCalcService l_calcService = (WEB3IfoDepositCalcService) Services.getService(WEB3IfoDepositCalcService.class);

            // �؋����v�Z���擾����
            WEB3IfoDepositCalc l_calc = l_calcService.getIfoDepositCalc(l_subAccount);

            // �؋������ږ��ׂ̈ꗗ���쐬����
            WEB3IfoDepositTransitionReferenceUnit[] l_ifoDepositTransitionReferences = createIfoDepositTransitionReferences(l_calc);

            //�w���ʏ؋������ږ���t+0���擾����
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit0 = l_ifoDepositTransitionReferences[0].ifoDepositTranRefPerIndexUnit;
            //NK225�؋������ږ���t+0 �� �~�jNK225�؋������ږ���t+0 ���擾����
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

            //�w���ʏ؋������ږ���t+1���擾����
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit1 = l_ifoDepositTransitionReferences[1].ifoDepositTranRefPerIndexUnit;
            //NK225�؋������ږ���t+1 �� �~�jNK225�؋������ږ���t+1 ���擾����
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

            //�w���ʏ؋������ږ���t+2���擾����
            WEB3IfoDepositTranRefPerIndexUnit[] l_perIndexUnit2 = l_ifoDepositTransitionReferences[2].ifoDepositTranRefPerIndexUnit;
            //NK225�؋������ږ���t+2 �� �~�jNK225�؋������ږ���t+2 ���擾����
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

            // �؋����v�Z�������擾����B
            WEB3IfoDepositCalcCondition l_calcCondition = l_calc.getIfoDepositCalcCondition();

            // �敨OP�ڋq�ړ����ׂ��擾����
            WEB3IfoCustomerTransfer l_transfer = l_calc.getIfoCustomerTransfer();

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // �������z���擾����
            double l_dblNonPayAmount = l_calc.calcNonPayAmount();

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // ���������z���擾����
            double l_dblCurrentBizDateDemandAmount = l_calc.getCurrentBizDateDemandAmount();

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // ���������z���擾����
            double l_dblNextBizDateDemandAmount = l_calc.calcNextBizDateDemandAmount();

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // �؋����U�֗]�͊z���Z�o����
            double l_dblIfoDepositTransferableAmount = l_calc.calcIfoDepositTransferableAmount();

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // ��n�����擾����
            Date l_datDeliveryDate = l_calcCondition.getBizDate(l_calcCondition.getIfoDepositBaseDate());

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            //�؋����s���m��FLAG
            String l_insufficientFlag = null;
            //(�؋����v�Z����.is�[����{ == true and �؋����v�Z����.is���Z�l�����M��() == true) or 
            //(�؋����v�Z����.is�[����{ == false and �؋����v�Z����.is�؋����s�����[�����M��() == true)�̏ꍇ�A
            // 1�F�m��B�ȊO�A0�F���m��B
            if ((l_calcCondition.isEveningSessionEnforcemented() && l_calcCondition.isQuickReportReceived()) ||
                 (!l_calcCondition.isEveningSessionEnforcemented() && l_calcCondition.isIfoDepositMailFlag()) )
            {
                l_insufficientFlag = WEB3IfoDepositFixedIfoDepositFlgDiv.FIXED;
            }
            else
            {
                l_insufficientFlag = WEB3IfoDepositFixedIfoDepositFlgDiv.NOT_FIXED;
            }

            //�w���ʏ؋���
            WEB3IfoDepositPerIndexUnit[] l_ifoDepositPerIndexUnit = this.createIfoDepositPerIndexUnitList(l_calcCondition);
            //�w���ʏ؋����iNK225�j �� �w���ʏ؋����i�~�jNK225�j ���擾����
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

            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // ���X�������z���擾����
            double l_dblNextNextBizDateDemandAmount = l_calc.calcNext2BizDateDemandAmount();
            
            //--���W�b�N��WEB3IfoDepositTransitionReferenceServiceImpl(�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X)���Q��
            // ���X�������z�i�[��j���擾����
            double l_dblNextNextEveningBizDateDemandAmount = l_calc.calcNextBizDateDemandAmountEvening();

            Date l_datSysDate = new Date();

            l_param.setBalance0(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(0)));//�c���iT+0�j
            l_param.setBalance1(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(1)));//�c���iT+1�j
            l_param.setBalance2(WEB3StringTypeUtility.formatNumber(l_transfer.getBalance(2)));//�c���iT+2�j
            l_param.setIfoDepositBalance0(l_ifoDepositTransitionReferences[0].ifoDepositBal);//�؋����c(T+0)
            l_param.setIfoDepositBalance1(l_ifoDepositTransitionReferences[1].ifoDepositBal);//�؋����c(T+1)
            l_param.setIfoDepositBalance2(l_ifoDepositTransitionReferences[2].ifoDepositBal);//�؋����c(T+2)
			l_param.setIfoDepositBalanceF0(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(0)));//�؋����c(T+0)(���m��)
			l_param.setIfoDepositBalanceF1(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(1)));//�؋����c(T+1)(���m��)
			l_param.setIfoDepositBalanceF2(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositBalanceTemp(2)));//�؋����c(T+2)(���m��)
            l_param.setTodayTransferAmt0(l_ifoDepositTransitionReferences[0].todayCahangeAmt);//�{���U�֊z(T+0)
            l_param.setTodayTransferAmt1(l_ifoDepositTransitionReferences[1].todayCahangeAmt);//�{���U�֊z(T+1)
            l_param.setTodayTransferAmt2(l_ifoDepositTransitionReferences[2].todayCahangeAmt);//�{���U�֊z(T+2)
            l_param.setTodayFutSettleProfit0(l_ifoDepositTransitionReferences[0].todayFutSettleProfitLoss);//�{���敨���ϑ��v(T+0)
            l_param.setTodayFutSettleProfit1(l_ifoDepositTransitionReferences[1].todayFutSettleProfitLoss);//�{���敨���ϑ��v(T+1)
            l_param.setTodayFutSettleProfit2(l_ifoDepositTransitionReferences[2].todayFutSettleProfitLoss);//�{���敨���ϑ��v(T+2)
            l_param.setTodayOpNetAmt0(l_ifoDepositTransitionReferences[0].todayOpNetAmt);//�{���I�v�V������n���(T+0)
            l_param.setTodayOpNetAmt1(l_ifoDepositTransitionReferences[1].todayOpNetAmt);//�{���I�v�V������n���(T+1)
            l_param.setTodayOpNetAmt2(l_ifoDepositTransitionReferences[2].todayOpNetAmt);//�{���I�v�V������n���(T+2)
            l_param.setFutEvalProfit0(l_ifoDepositTransitionReferences[0].futEvalProfitLoss);//�敨�]�����v(T+0)
            l_param.setFutEvalProfit1(l_ifoDepositTransitionReferences[1].futEvalProfitLoss);//�敨�]�����v(T+1)
            l_param.setFutEvalProfit2(l_ifoDepositTransitionReferences[2].futEvalProfitLoss);//�敨�]�����v(T+2)
            l_param.setLongFutEvalProfit0(l_ifoDepositTransitionReferences[0].lfEvalProfitLoss);//�����敨�]�����v(T+0)
            l_param.setLongFutEvalProfit1(l_ifoDepositTransitionReferences[1].lfEvalProfitLoss);//�����敨�]�����v(T+1)
            l_param.setLongFutEvalProfit2(l_ifoDepositTransitionReferences[2].lfEvalProfitLoss);//�����敨�]�����v(T+2)
            l_param.setShortFutEvalProfit0(l_ifoDepositTransitionReferences[0].sfEvalProfitLoss);//�����敨�]�����v(T+0)
            l_param.setShortFutEvalProfit1(l_ifoDepositTransitionReferences[1].sfEvalProfitLoss);//�����敨�]�����v(T+1)
            l_param.setShortFutEvalProfit2(l_ifoDepositTransitionReferences[2].sfEvalProfitLoss);//�����敨�]�����v(T+2)
            l_param.setAcceptanceIfoDepositBal0(l_ifoDepositTransitionReferences[0].acceptanceIfoDepositBal);//����؋����c(T+0)
            l_param.setAcceptanceIfoDepositBal1(l_ifoDepositTransitionReferences[1].acceptanceIfoDepositBal);//����؋����c(T+1)
            l_param.setAcceptanceIfoDepositBal2(l_ifoDepositTransitionReferences[2].acceptanceIfoDepositBal);//����؋����c(T+2)
            l_param.setAcceptanceIfoDepositBalF0(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(0)));//����؋����c(T+0)(���m��)
            l_param.setAcceptanceIfoDepositBalF1(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(1)));//����؋����c(T+1)(���m��)
            l_param.setAcceptanceIfoDepositBalF2(WEB3StringTypeUtility.formatNumber(l_calc.calcReceiptIfoDepositBalanceTemp(2)));//����؋����c(T+2)(���m��)
            l_param.setNetOpValueTotalAmt0(l_ifoDepositTransitionReferences[0].netOptionlValueTotalAmt);//�l�b�g�E�I�v�V�������l���z(T+0)
            l_param.setNetOpValueTotalAmt1(l_ifoDepositTransitionReferences[1].netOptionlValueTotalAmt);//�l�b�g�E�I�v�V�������l���z(T+1)
            l_param.setNetOpValueTotalAmt2(l_ifoDepositTransitionReferences[2].netOptionlValueTotalAmt);//�l�b�g�E�I�v�V�������l���z(T+2)
			l_param.setNetOpValueTotalAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(0)));//�l�b�g�E�I�v�V�������l���z(T+0)(���m��)
			l_param.setNetOpValueTotalAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(1)));//�l�b�g�E�I�v�V�������l���z(T+1)(���m��)
			l_param.setNetOpValueTotalAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcNetOptionTotalAmountTemp(2)));//�l�b�g�E�I�v�V�������l���z(T+2)(���m��)
            l_param.setIfoDepositNecessaryAmt0(l_ifoDepositTransitionReferences[0].ifoDepositNecessaryAmt);//�؋������v�z(T+0)
            l_param.setIfoDepositNecessaryAmt1(l_ifoDepositTransitionReferences[1].ifoDepositNecessaryAmt);//�؋������v�z(T+1)
            l_param.setIfoDepositNecessaryAmt2(l_ifoDepositTransitionReferences[2].ifoDepositNecessaryAmt);//�؋������v�z(T+2)
            l_param.setIfoDepositNecessaryAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(0)));//�؋������v�z(T+0)(���m��)
            l_param.setIfoDepositNecessaryAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(1)));//�؋������v�z(T+1)(���m��)
            l_param.setIfoDepositNecessaryAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcIfoDepositRequiredAmountTemp(2)));//�؋������v�z(T+2)(���m��)
            l_param.setIfoDepositPower0(l_ifoDepositTransitionReferences[0].ifoDepositPower);//�؋����]�͊z(T+0)
            l_param.setIfoDepositPower1(l_ifoDepositTransitionReferences[1].ifoDepositPower);//�؋����]�͊z(T+1)
            l_param.setIfoDepositPower2(l_ifoDepositTransitionReferences[2].ifoDepositPower);//�؋����]�͊z(T+2)
            l_param.setIfoDepositTransferPower0(l_ifoDepositTransitionReferences[0].depositChangePower);//�؋����U�֗]�͊z(T+0)
            l_param.setIfoDepositTransferPower1(l_ifoDepositTransitionReferences[1].depositChangePower);//�؋����U�֗]�͊z(T+1)
            l_param.setIfoDepositTransferPower2(l_ifoDepositTransitionReferences[2].depositChangePower);//�؋����U�֗]�͊z(T+2)
            l_param.setBullQuantityNk2250(l_nk225TranUnit0.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�j(T+0)
            l_param.setBullQuantityNk2251(l_nk225TranUnit1.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�j(T+1)
            l_param.setBullQuantityNk2252(l_nk225TranUnit2.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�j(T+2)
            l_param.setBearQuantityNk2250(l_nk225TranUnit0.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�j(T+0)
            l_param.setBearQuantityNk2251(l_nk225TranUnit1.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�j(T+1)
            l_param.setBearQuantityNk2252(l_nk225TranUnit2.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�j(T+2)
            l_param.setLongPosNk2250(l_nk225TranUnit0.longPositionContract);//���|�W�V�������ʁiNK225�j(T+0)
            l_param.setLongPosNk2251(l_nk225TranUnit1.longPositionContract);//���|�W�V�������ʁiNK225�j(T+1)
            l_param.setLongPosNk2252(l_nk225TranUnit2.longPositionContract);//���|�W�V�������ʁiNK225�j(T+2)
            l_param.setPartLongPosNk2250(l_nk225TranUnit0.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+0)
            l_param.setPartLongPosNk2251(l_nk225TranUnit1.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+1)
            l_param.setPartLongPosNk2252(l_nk225TranUnit2.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+2)
            l_param.setShortPosNk2250(l_nk225TranUnit0.shortPositionContract);//���|�W�V�������ʁiNK225�j(T+0)
            l_param.setShortPosNk2251(l_nk225TranUnit1.shortPositionContract);//���|�W�V�������ʁiNK225�j(T+1)
            l_param.setShortPosNk2252(l_nk225TranUnit2.shortPositionContract);//���|�W�V�������ʁiNK225�j(T+2)
            l_param.setPartShortPosNk2250(l_nk225TranUnit0.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+0)
            l_param.setPartShortPosNk2251(l_nk225TranUnit1.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+1)
            l_param.setPartShortPosNk2252(l_nk225TranUnit2.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�j(T+2)
            l_param.setBullQuantityNk225Mini0(l_miniNk225TranUnit0.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�~�j�j(T+0)
            l_param.setBullQuantityNk225Mini1(l_miniNk225TranUnit1.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�~�j�j(T+1)
            l_param.setBullQuantityNk225Mini2(l_miniNk225TranUnit2.bullQuantity);//�敨�����܂���OP�v�b�g�����\���ʁiNK225�~�j�j(T+2)
            l_param.setBearQuantityNk225Mini0(l_miniNk225TranUnit0.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�~�j�j(T+0)
            l_param.setBearQuantityNk225Mini1(l_miniNk225TranUnit1.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�~�j�j(T+1)
            l_param.setBearQuantityNk225Mini2(l_miniNk225TranUnit2.bearQuantity);//�敨�����܂���OP�R�[�������\���ʁiNK225�~�j�j(T+2)
            l_param.setLongPosNk225Mini0(l_miniNk225TranUnit0.longPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+0)
            l_param.setLongPosNk225Mini1(l_miniNk225TranUnit1.longPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+1)
            l_param.setLongPosNk225Mini2(l_miniNk225TranUnit2.longPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+2)
            l_param.setPartLongPosNk225Mini0(l_miniNk225TranUnit0.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+0)
            l_param.setPartLongPosNk225Mini1(l_miniNk225TranUnit1.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+1)
            l_param.setPartLongPosNk225Mini2(l_miniNk225TranUnit2.partLongPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+2)
            l_param.setShortPosNk225Mini0(l_miniNk225TranUnit0.shortPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+0)
            l_param.setShortPosNk225Mini1(l_miniNk225TranUnit1.shortPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+1)
            l_param.setShortPosNk225Mini2(l_miniNk225TranUnit2.shortPositionContract);//���|�W�V�������ʁiNK225�~�j�j(T+2)
            l_param.setPartShortPosNk225Mini0(l_miniNk225TranUnit0.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+0)
            l_param.setPartShortPosNk225Mini1(l_miniNk225TranUnit1.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+1)
            l_param.setPartShortPosNk225Mini2(l_miniNk225TranUnit2.partShortPositionContract);//���|�W�V�������ʁi���������j�iNK225�~�j�j(T+2)

			l_param.setBuyContractAmt0(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(0)));//���|�W�V�������z(T+0)
			l_param.setBuyContractAmt1(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(1)));//���|�W�V�������z(T+1)
			l_param.setBuyContractAmt2(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmt(2)));//���|�W�V�������z(T+2)
			l_param.setBuyContractAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(0)));//���|�W�V�������z(T+0)(���m��)
			l_param.setBuyContractAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(1)));//���|�W�V�������z(T+1)(���m��)
			l_param.setBuyContractAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcBuyContractAmtTemp(2)));//���|�W�V�������z(T+2)(���m��)
			l_param.setSellContractAmt0(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(0)));//���|�W�V�������z(T+0)
			l_param.setSellContractAmt1(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(1)));//���|�W�V�������z(T+1)
			l_param.setSellContractAmt2(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmt(2)));//���|�W�V�������z(T+2)
			l_param.setSellContractAmtF0(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(0)));//���|�W�V�������z(T+0)(���m��)
			l_param.setSellContractAmtF1(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(1)));//���|�W�V�������z(T+1)(���m��)
			l_param.setSellContractAmtF2(WEB3StringTypeUtility.formatNumber(l_calc.calcSellContractAmtTemp(2)));//���|�W�V�������z(T+2)(���m��)

            l_param.setNonPayAmt(WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount));//�������z
            l_param.setTodayClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount));//�{�������z
            l_param.setTomorrowClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount));//���������z
            l_param.setDeliveryDate(l_datDeliveryDate);//��n��
            l_param.setIfoDepositInsufficientFlag(l_insufficientFlag);//�؋����s���m��FLAG
            l_param.setIfoDepositIndexNk225(l_nk225Unit.regIfoDeposit);//�w���ʏ؋����iNK225�j
            l_param.setIfoDepositIndexNk225Mini(l_miniNk225Unit.regIfoDeposit);//�w���ʏ؋����iNK225�~�j�j
            l_param.setAfterTomorrowClaimAmt(WEB3StringTypeUtility.formatNumber(l_dblNextNextBizDateDemandAmount));//���X�������z
            l_param.setTomorrowClaimAmtEvening(WEB3StringTypeUtility.formatNumber(l_dblNextNextEveningBizDateDemandAmount));//���������z�i�[��j
            l_param.setStatus(WEB3IfoDepositCalcResultSaveDiv.STATUS_FINISH);//����
            l_param.setCreatedTimestamp(l_datSysDate);//�쐬���t
            l_param.setLastUpdatedTimestamp(l_datSysDate);//�X�V���t
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
    // �ȉ��́u�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X�v����R�s�[�������e�ł��B
    // �u�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X�v �Ɉȉ��̃��\�b�h�̏C������������A��������X�V���Ă��������B
    // webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * (create�؋������ږ��׈ꗗ)<BR>
     * T+0�`T+2�܂ł̏؋������ږ��׃I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋������ڎQ�Ɖ�ʕ\���T�[�r�X�jcreate�؋������ږ��׈ꗗ�v�Q�ƁB<BR>
     * @@param ifoDepositCalc - (�؋����v�Z)
     * �؋����v�Z�N���X�B
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReference[]
     * @@roseuid 41452A4A02E3
     */
    protected WEB3IfoDepositTransitionReferenceUnit[] createIfoDepositTransitionReferences(WEB3IfoDepositCalc l_ifoDepositCalc)
    {
        
        // �؋������ږ��׃��X�g
        List l_references = new ArrayList();

        // �敨OP�ڋq�ړ����ׂ��擾����
        WEB3IfoCustomerTransfer l_transfer =
            l_ifoDepositCalc.getIfoCustomerTransfer();

        // �؋����v�Z�������擾����
        WEB3IfoDepositCalcCondition l_calcCondition =
            l_ifoDepositCalc.getIfoDepositCalcCondition();

        // �w����͈̔�(n = 0�`2�܂�)��Loop����
        for (int i = 0; i < 3; i++)
        {

            WEB3IfoDepositTransitionReferenceUnit l_reference =
                new WEB3IfoDepositTransitionReferenceUnit();

            // �c�Ɠ����擾����
            Date l_datBizDate = l_calcCondition.getBizDate(i);

            // �؋����c�����Z�o����
            double l_dblIfoDepositBalance =
                l_ifoDepositCalc.calcIfoDepositBalance(i);

            // �敨�]�����v���Z�o����
            double l_dblFuturesAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesAppraisalProfitLoss();

            // �敨�����]�����v���Z�o����
            double l_dblFuturesBuyAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesBuyAppraisalProfitLoss();

            // �敨�����]�����v���Z�o����
            double l_dblFuturesSellAppraisalProfitLoss =
                l_ifoDepositCalc.calcFuturesSellAppraisalProfitLoss();
                
            // ����؋����c�����Z�o����
            double l_dblReceiptIfoDepositBlance =
                l_ifoDepositCalc.calcReceiptIfoDepositBalance(i);

            // ���|�W�V�������ʂ��Z�o����
            double l_dblBuyContractQty = l_ifoDepositCalc.calcBuyContractQty(i);

            // �������̔��|�W�V�������ʂ��Z�o����
            double l_dblOrderingBuyContractQty =
                l_ifoDepositCalc.calcBuyOrderQty(i);

            // �������̔��|�W�V�������ʂ��Z�o����
            double l_dblSellContractQty =
                l_ifoDepositCalc.calcSellContractQty(i);

            // �������̔��|�W�V�������ʂ��Z�o����
            double l_dblOrderingSellContractQty =
                l_ifoDepositCalc.calcSellOrderQty(i);

            // �|�W�V�����o�����X���Z�o����
            WEB3IfoPositionBalance l_positionBalance =
                l_ifoDepositCalc.calcPositionBalance(i);

            // SPAN�؋������擾����
            double l_dblSPANIfoDeposit = -1.0;
            if (l_calcCondition.isSPANUsable())
            {
                l_dblSPANIfoDeposit = l_ifoDepositCalc.getSPANIfoDeposit(i);
            }

            // �l�b�g�I�v�V�������l���z���Z�o����
            double l_dblNetOptionTotalAmount =
                l_ifoDepositCalc.calcNetOptionTotalAmount(i);

            // �؋������v�z���Z�o����
            double l_dblIfoDepositRequiredAmount =
                l_ifoDepositCalc.calcIfoDepositRequiredAmount(i);

            // �؋����]�͊z���Z�o����
            double l_dblIfoDepositTradingPowerAmount =
                l_ifoDepositCalc.calcIfoDepositTradingPowerAmount(i);
            
			// �؋����U�֗]�͊z���Z�o����
			double l_dblIfoDepositTransferableAmount =
				l_ifoDepositCalc.calcIfoDepositTransferableAmount(i);

            // �I�v�V�����]�����v���Z�o����
            double l_dblOptionAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionAppraisalProfitLoss();

            // �I�v�V���������]�����v���Z�o����
            double l_dblOptionBuyAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionBuyAppraisalProfitLoss();

            // �I�v�V���������]�����v���Z�o����
            double l_dblOptionSellAppraisalProfitLoss =
                l_ifoDepositCalc.calcOptionSellAppraisalProfitLoss();

            // ���t
            l_reference.bizDate = l_datBizDate;

            // �؋����c
            l_reference.ifoDepositBal =
                WEB3StringTypeUtility.formatNumber(l_dblIfoDepositBalance);

            //�{���U�֊z�F      
            //�w���(n) == 0�̏ꍇ�A�敨OP�ڋq�ړ�����.get�U�֊z[T+0]�̖߂�l
            //�w���(n) != 1�̏ꍇ�A�敨OP�ڋq�ړ�����.get�U�֊z[T+1]�̖߂�l   
            if (i == 0)
            {
                // �U�֊z[T+0]���擾����
                double l_dblIfoTransferAmount = l_transfer.getCurrentBizDateTransferAmount();

                l_reference.todayCahangeAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblIfoTransferAmount);
            }
            else if(i !=0)
            {
                // �U�֊z[T+1]���擾����
                double l_dblIfoTransferAmount = l_transfer.getNextBizDateTransferAmount();

                l_reference.todayCahangeAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblIfoTransferAmount);
            }

            //�{���敨���ϑ��v�F
            //�w���(n) == 0�̏ꍇ�Anull
            //�w���(n) == 1�̏ꍇ�A�敨OP�ڋq�ړ�����.get�敨���ϑ��v[T+1]�̖߂�l
            //�w���(n) == 2�̏ꍇ�A�敨OP�ڋq�ړ�����.get�敨���ϑ��v[T+2]�̖߂�l            
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

            //�{���I�v�V������n����F    
            //�w���(n) == 0�̏ꍇ�Anull
            //�w���(n) == 1�̏ꍇ�A�敨OP�ڋq�ړ�����.get�I�v�V������n���[T+1] + �敨OP�ڋq�ړ�����.get�I�v�V����������n���[T+1]
            //�w���(n) == 2�̏ꍇ�A�敨OP�ڋq�ړ�����.get�I�v�V������n���[T+2] + �敨OP�ڋq�ړ�����.get�I�v�V����������n���[T+2]

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

            // �敨�]�����v
            l_reference.futEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesAppraisalProfitLoss);

            // �����敨�]�����v
            l_reference.lfEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesBuyAppraisalProfitLoss);

            // �����敨�]�����v
            l_reference.sfEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblFuturesSellAppraisalProfitLoss);

            // ����؋����Z
            l_reference.acceptanceIfoDepositBal =
                WEB3StringTypeUtility.formatNumber(l_dblReceiptIfoDepositBlance);

            // ���|�W�V��������
            l_reference.longPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblBuyContractQty);

            // ���|�W�V�������ʁi���������j
            l_reference.partLongPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblOrderingBuyContractQty);

            // ���|�W�V��������
            l_reference.shortPositionContract =
                WEB3StringTypeUtility.formatNumber(l_dblSellContractQty);

            // ���|�W�V�������ʁi���������j
            l_reference.partShortPositionContract =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOrderingSellContractQty);

            if (l_positionBalance != null)
            {
                // �|�W�V�����o�����X
                l_reference.positionBalance =
                    WEB3StringTypeUtility.formatNumber(
                        l_positionBalance.positionBalance);

                // �|�W�V�����o�����X�敪
                l_reference.positionBalanceDiv =
                    l_positionBalance.positionBalanceType;

            }

            // SPAN�؋���
            if (l_calcCondition.isSPANUsable())
            {
                l_reference.spanIfoDeposit =
                    WEB3StringTypeUtility.formatNumber(l_dblSPANIfoDeposit);
            }

            // �l�b�g�I�v�V�������l���z
            l_reference.netOptionlValueTotalAmt =
                WEB3StringTypeUtility.formatNumber(l_dblNetOptionTotalAmount);

            // �؋������v�z
            l_reference.ifoDepositNecessaryAmt =
                WEB3StringTypeUtility.formatNumber(
                    l_dblIfoDepositRequiredAmount);

            // �؋����]�͊z
            if (i != 0)
            {
				l_reference.ifoDepositPower =
					WEB3StringTypeUtility.formatNumber(
						Math.max(0, l_dblIfoDepositTradingPowerAmount));
            }
            
			// �؋����U�֗]�͊z
			l_reference.depositChangePower =
				WEB3StringTypeUtility.formatNumber(
					Math.max(0, l_dblIfoDepositTransferableAmount));

            // �I�v�V�����]�����v
            l_reference.opEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionAppraisalProfitLoss);
            
            // �����I�v�V�����]�����v
            l_reference.loEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionBuyAppraisalProfitLoss);
            
            // �����I�v�V�����]�����v
            l_reference.soEvalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_dblOptionSellAppraisalProfitLoss);
                    
            // �w���ʏ؋������ږ���
            l_reference.ifoDepositTranRefPerIndexUnit =
                this.createIfoDepositTranRefPerIndexUnitList(l_ifoDepositCalc, i);

            // add(�؋����ړ�����)
            l_references.add(l_reference);

        }

        // �z����쐬���A�ԋp����
        return (WEB3IfoDepositTransitionReferenceUnit[]) l_references.toArray(
            new WEB3IfoDepositTransitionReferenceUnit[0]);

    }
    
    /**
     * (create�w���ʏ؋����ꗗ)<BR>
     * �w���ʏ؋����I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋������ڎQ�Ɖ�ʕ\���T�[�r�X�jcreate�w���ʏ؋����ꗗ�v�Q�ƁB<BR>
     * @@param ifoDepositCalcCondition - (�؋����v�Z����)
     * �؋����v�Z�����N���X�B
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit[]
     */
    protected WEB3IfoDepositPerIndexUnit[] createIfoDepositPerIndexUnitList(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        if(l_ifoDepositCalcCondition.isSPANUsable())
        {
            return null;
        }
        
        List l_list = new ArrayList();
        
        // �w���ʏ؋����v�Z�������擾����B
        WEB3IfoDepositCalcConditionPerIndex[] l_calcConditionPerIndexList = 
            l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();
        
        // �w���ʏ؋����v�Z�������Ƃ�Loop����    
        for(int i= 0; i < l_calcConditionPerIndexList.length; i++)
        {
            WEB3IfoDepositPerIndexUnit l_ifoDepositPerIndexUnit = new WEB3IfoDepositPerIndexUnit();
           
            // �����Y�����R�[�h 
            l_ifoDepositPerIndexUnit.targetProductCode =
                l_calcConditionPerIndexList[i].getUnderlyingProductCode();
            
            // �K��؋���
            l_ifoDepositPerIndexUnit.regIfoDeposit =
                WEB3StringTypeUtility.formatNumber(
                    l_calcConditionPerIndexList[i].getIfoDepositPerUnit());
            
            l_list.add(l_ifoDepositPerIndexUnit);
        }
        
        // �z����쐬���A�ԋp����
        return (WEB3IfoDepositPerIndexUnit[]) l_list.toArray(new WEB3IfoDepositPerIndexUnit[0]);
    }
    
    /**
     * (create�w���ʏ؋������ږ��׈ꗗ)<BR>
     * �w����ɕR�t���w���ʏ؋������ږ��׃I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋������ڎQ�Ɖ�ʕ\���T�[�r�X�jcreate�w���ʏ؋������ږ��׈ꗗ�v�Q�ƁB<BR>
     * @@param ifoDepositCalc - (�؋����v�Z)
     * �؋����v�Z�N���X�B
     * @@param l_intReservedDate - (�w���)
     * �w����B
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit[]
     */
    protected WEB3IfoDepositTranRefPerIndexUnit[] createIfoDepositTranRefPerIndexUnitList(
        WEB3IfoDepositCalc l_ifoDepositCalc,
        int l_intReservedDate)
    {
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = 
            l_ifoDepositCalc.getIfoDepositCalcCondition();

        //�V�K���]�͉\�t���O
        boolean l_blnNewOpenTradingPowerAvailable =
            this.isNewOpenTradingPowerAvailable(l_ifoDepositCalc,l_intReservedDate);

        List l_list = new ArrayList();

        //�w���ʐ敨OP���ʏW�v�}�b�v
        Map ifoSummaryContractPerIndexMap = new HashMap();

        //�w���ʐ敨OP���ʏW�v�ꗗ���擾����B
        WEB3IfoSummaryContractPerIndex[] ifoSummaryContractPerIndexList = l_ifoDepositCalc.getIfoSummaryContractPerIndexList();

        if(ifoSummaryContractPerIndexList != null)
        {
            //�w���ʐ敨OP���ʏW�v.�����Y�����R�[�h���L�[�ɁA�w���ʐ敨OP���ʏW�v���}�b�s���O����B
            for(int i = 0; i < ifoSummaryContractPerIndexList.length; i++)
            {
                ifoSummaryContractPerIndexMap.put(ifoSummaryContractPerIndexList[i].getTargetProductCode(),ifoSummaryContractPerIndexList[i]);
            }

        }

        // �w���ʏ؋����v�Z�������擾����B
        WEB3IfoDepositCalcConditionPerIndex[] l_calcConditionPerIndexList = 
            l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

        // �w���ʏ؋����v�Z�������Ƃ�Loop����    
        for(int i= 0; i < l_calcConditionPerIndexList.length; i++)
        {
            WEB3IfoDepositTranRefPerIndexUnit l_ifoDepositTranRefPerIndexUnit =
                new WEB3IfoDepositTranRefPerIndexUnit();

            WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndex = null;

            // �����Y�����R�[�h 
            String l_strTargetProductCode =
                l_calcConditionPerIndexList[i].getUnderlyingProductCode();

            // �敨�����܂���OP�v�b�g�����\����
            double l_dblBullQuantity = 0;

            // �敨�����܂���OP�R�[�����\����
            double l_dblBearQuantity = 0;

            //���|�W�V��������
            double longPositionContract = 0;

            //���������|�W�V��������
            double partLongPositionContract = 0;

            //���|�W�V��������
            double shortPositionContract = 0;

            //���������|�W�V��������
            double partShortPositionContract = 0;

            //�V�K���]�͉\�ȏꍇ
            if(l_blnNewOpenTradingPowerAvailable)
            {
                // �敨�����܂���OP�v�b�g�����\����
                l_dblBullQuantity =
                    l_ifoDepositCalc.calcPossibleBuyQty(l_strTargetProductCode,l_intReservedDate);
                // �敨�����܂���OP�R�[�����\����
                l_dblBearQuantity =
                    l_ifoDepositCalc.calcPossibleSellQty(l_strTargetProductCode,l_intReservedDate);
            }

            //�w���ʐ敨OP���ʏW�v�}�b�v���w�肳�ꂽ�����Y�����R�[�h�̃}�b�s���O��ێ�����ꍇ
            if(ifoSummaryContractPerIndexMap.containsKey(l_calcConditionPerIndexList[i].getUnderlyingProductCode()))
            {
                ifoSummaryContractPerIndex = (WEB3IfoSummaryContractPerIndex)ifoSummaryContractPerIndexMap.get(l_calcConditionPerIndexList[i].getUnderlyingProductCode());

                //���|�W�V��������
                longPositionContract = ifoSummaryContractPerIndex.calcBuyContractQty(l_intReservedDate);
                //���������|�W�V��������
                partLongPositionContract = ifoSummaryContractPerIndex.calcBuyOrderQty(l_intReservedDate);
                //���|�W�V��������
                shortPositionContract = ifoSummaryContractPerIndex.calcSellContractQty(l_intReservedDate);
                //���������|�W�V��������
                partShortPositionContract = ifoSummaryContractPerIndex.calcSellOrderQty(l_intReservedDate);
            }

            // �����Y�����R�[�h
            l_ifoDepositTranRefPerIndexUnit.targetProductCode = l_strTargetProductCode;

            // �敨�����܂���OP�v�b�g�����\����
            l_ifoDepositTranRefPerIndexUnit.bullQuantity = 
                WEB3StringTypeUtility.formatNumber(Math.max(0,l_dblBullQuantity));

            // �敨�����܂���OP�R�[�����\����
            l_ifoDepositTranRefPerIndexUnit.bearQuantity = 
                WEB3StringTypeUtility.formatNumber(Math.max(0,l_dblBearQuantity));

            //SPAN�g�p�\�A�܂��́A�w��� == 0 �̏ꍇ
            if(l_ifoDepositCalcCondition.isSPANUsable() || l_intReservedDate == 0)
            {
                l_ifoDepositTranRefPerIndexUnit.bullQuantity = null;
                l_ifoDepositTranRefPerIndexUnit.bearQuantity = null;
            }

            //���|�W�V��������
            l_ifoDepositTranRefPerIndexUnit.longPositionContract = 
                WEB3StringTypeUtility.formatNumber(longPositionContract);

            //���������|�W�V��������
            l_ifoDepositTranRefPerIndexUnit.partLongPositionContract = 
                WEB3StringTypeUtility.formatNumber(partLongPositionContract);

            //���|�W�V��������
            l_ifoDepositTranRefPerIndexUnit.shortPositionContract = 
                WEB3StringTypeUtility.formatNumber(shortPositionContract);

            //���������|�W�V��������
            l_ifoDepositTranRefPerIndexUnit.partShortPositionContract = 
                WEB3StringTypeUtility.formatNumber(partShortPositionContract);
            
            l_list.add(l_ifoDepositTranRefPerIndexUnit);
        }

        // �z����쐬���A�ԋp����
        return (WEB3IfoDepositTranRefPerIndexUnit[]) l_list.toArray(
            new WEB3IfoDepositTranRefPerIndexUnit[0]);
    }
    
    /**
     * (is�V�K���]�͉\)<BR>
     * �V�K���]�͂��\�ł��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A�V�K���]�͕s�Ƃ���<BR>
�@@�@@ *�@@false��ԋp����B�ȊO��true��ԋp����B<BR>
     * <BR>
�@@�@@ *�@@�E�؋����v�Z.get�؋����v�Z����().is�V�K���]�͉\() == false<BR>
�@@�@@ *�@@�E�؋����v�Z.calc����؋����c��(����.�w���) < <BR> 
     *    �؋����v�Z.get�؋����v�Z����().get�K�v�Œ�؋���()<BR>
�@@�@@ *�@@�E�؋����v�Z.calc�������z() > 0<BR>
     * <BR>
     * @@param ifoDepositCalc - (�؋����v�Z)
     * �؋����v�Z�N���X�B
     * @@param l_intReservedDate - (�w���)
     * �w����B
     * @@return boolean
     */
    protected boolean isNewOpenTradingPowerAvailable(
        WEB3IfoDepositCalc l_ifoDepositCalc,
        int l_intReservedDate)
    {
    
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = 
            l_ifoDepositCalc.getIfoDepositCalcCondition();
    
        // �V�K���]�͂��\�ł��邩�𔻒肷��
        boolean l_blnNewOpenTradingPowerAvailable = 
            l_ifoDepositCalcCondition.isNewOpenTradingPowerAvailable();  

        // �K�v�Œ�؋������擾����
        double l_dblMinIfoDep = l_ifoDepositCalcCondition.getMinIfoDeposit();
                
        // ����؋����c�����Z�o����
        double l_dblReceiptIfoDepositBlance =
            l_ifoDepositCalc.calcReceiptIfoDepositBalance(l_intReservedDate);
            
        // �������z���擾����
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
