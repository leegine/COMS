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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl�N���X(WEB3IfoDepositTransitionReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/27 hijikata(SRA) �[��Ή� ���f��No.064, No.083, No.086
 Revesion History : 2007/08/01 hijikata(SRA) �[��Ή� ���f��No.096, No.099
 Revesion History : 2007/10/18 k.yamashita(SRA) ���f��No.117

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
 * (�؋������ڎQ�Ɖ�ʕ\���T�[�r�XImpl)<BR>
 * �؋������ڎQ�Ɖ�ʕ\���T�[�r�X�����N���X�B<BR>
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
     * �؋������ڎQ�Ɖ�ʕ\���T�[�r�X���������{����B<BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^���؋������ڎQ�Ɖ�ʕ\�����N�G�X�g��
     * �L���X�g����this.create�؋������ڎQ�Ɖ��( )���R�[������B<BR>
     * 
     * @@param l_request
     *  - (���N�G�X�g�f�[�^)
     * ���N�G�X�g
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
     * (create�؋������ڎQ�Ɖ��)<BR>
     * �؋������ڎQ�Ɖ�ʕ\���T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋������ڎQ�Ɖ�ʕ\���T�[�r�X�jcreate�؋������ڎQ�Ɖ�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * �؋������ڎQ�Ɖ�ʕ\�����N�G�X�g
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse
     * @@roseuid 414543C30005
     */
    protected WEB3IfoDepositTransitionReferenceResponse createIfoDepositTransitionReferenceResponse(WEB3IfoDepositTransitionReferenceRequest l_request)
        throws WEB3BaseException
    {

        // ���X�|���X���b�Z�[�W����
        WEB3IfoDepositTransitionReferenceResponse l_response =
            (WEB3IfoDepositTransitionReferenceResponse) l_request
                .createResponse();

		// ��t���ԃ`�F�b�N�^�V�X�e��������~�`�F�b�N
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // �⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        // �؋����������J�݂̏ꍇ�A���X�|���X�ɒl��ݒ肵�Ȃ��ŕԂ��B
        if (l_subAccount == null)
        {
            return l_response;
        }

        // �؋����v�Z�T�[�r�X�𐶐�
        WEB3IfoDepositCalcService l_calcService =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        // �؋����v�Z���擾����
        WEB3IfoDepositCalc l_calc =
            l_calcService.getIfoDepositCalc(l_subAccount);

        // �؋������ږ��ׂ̈ꗗ���쐬����
        WEB3IfoDepositTransitionReferenceUnit[] l_ifoDepositTransitionReferences =
            createIfoDepositTransitionReferences(l_calc);

        // �؋����v�Z�������擾����B
        WEB3IfoDepositCalcCondition l_calcCondition =
            l_calc.getIfoDepositCalcCondition();

        // �������z���擾����
        double l_dblNonPayAmount = l_calc.calcNonPayAmount();

        // ���������z���擾����
        double l_dblCurrentBizDateDemandAmount =
            l_calc.getCurrentBizDateDemandAmount();

        // ���������z���擾����
        double l_dblNextBizDateDemandAmount =
            l_calc.calcNextBizDateDemandAmount();

        // ���X�������z���擾����
        double l_dblNext2BizDateDemandAmount =
            l_calc.calcNext2BizDateDemandAmount();

        // ���������z���[�ꁄ���擾����
        double l_dblNextBizDateDemandAmountEvening =
            l_calc.calcNextBizDateDemandAmountEvening();

        // �؋����U�֗]�͊z���Z�o����
        double l_dblIfoDepositTransferableAmount =
            l_calc.calcIfoDepositTransferableAmount();

        // ��n�����擾����
        Date l_datDeliveryDate =
            l_calcCondition.getBizDate(l_calcCondition.getIfoDepositBaseDate());

        // SPAN�敪���擾����
        String l_strSPANAdoptionDiv = WEB3IfoDepositSPANDivDef.NOT_ADOPTION;
        if (l_calcCondition.isSPANUsable())
        {
            l_strSPANAdoptionDiv = WEB3IfoDepositSPANDivDef.ADOPTION;
        }

        // �؋������ږ���
        l_response.ifoDepositUnit =
            l_ifoDepositTransitionReferences;

        // �������z
        l_response.nonPayAmt =
            WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount);

        // ���������z
        l_response.todayClaimAmt =
            WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount);

        // ���������z
        l_response.tomorrowClaimAmt =
            WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount);

        // ���X�������z
        l_response.dayAfterTomorrowClaimAmt =  
            WEB3StringTypeUtility.formatNumber(l_dblNext2BizDateDemandAmount);	

        // ���������z���[�ꁄ
        l_response.tomorrowClaimAmtEve =  
            WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmountEvening);	

        //�U�֗]�͊z
        l_response.depositChangePower = 
            WEB3StringTypeUtility.formatNumber(l_dblIfoDepositTransferableAmount);

        // ��n��
        l_response.deliveryDate = l_datDeliveryDate;

        // SPAN�敪
        l_response.spanDiv = l_strSPANAdoptionDiv;

        //�؋����s���m��FLAG�F
        //  �E�i�؋����v�Z����.is���Z�l�����M��()==true  or  �؋����v�Z����.is�؋����s�����[�����M��()==true�j�̏ꍇ
        //      1�F�m��  
        //  �E�ȊO
        //      0�F���m��
        if ( l_calcCondition.isQuickReportReceived() || l_calcCondition.isIfoDepositMailFlag() ) 
        {
            l_response.fixedIfoDepositFlg = WEB3IfoDepositFixedIfoDepositFlgDiv.FIXED;
        }
        else
        {
            l_response.fixedIfoDepositFlg = WEB3IfoDepositFixedIfoDepositFlgDiv.NOT_FIXED;
        }
        
        //�w���ʏ؋���
        l_response.ifoDepositPerIndexUnit = this.createIfoDepositPerIndexUnitList(l_calcCondition);


        return l_response;
    }

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
            
            //�{�������z�F      
            //�w���(n) == 0�̏ꍇ�A�敨OP�ڋq�ړ�����.get�����z[T+0]�̖߂�l
            //�w���(n) != 0�̏ꍇ�A�敨OP�ڋq�ړ�����.get�����z[T+1]�̖߂�l
			if (i == 0)
            {
                // �����z[T+0]���擾����
                double l_dblCashinAmount = l_transfer.getCurrentBizDateCashinAmount();

                l_reference.todayCashinAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblCashinAmount);
            }
            else if(i != 0)
            {
                // �����z[T+1]���擾����
                double l_dblCashinAmount = l_transfer.getNextBizDateCashinAmount();

                l_reference.todayCashinAmt =
                    WEB3StringTypeUtility.formatNumber(l_dblCashinAmount);
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
}
@
