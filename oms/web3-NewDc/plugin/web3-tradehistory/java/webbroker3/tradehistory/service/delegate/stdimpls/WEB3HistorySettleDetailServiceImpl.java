head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϖ��׃T�[�r�X�����N���X(WEB3HistorySettleDetailService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.data.SettleDetailHistoryParams;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���ϖ��׃T�[�r�XImpl)<BR>
 * ���ϖ��׃T�[�r�X�����N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3HistorySettleDetailServiceImpl extends WEB3ClientRequestService implements WEB3HistorySettleDetailService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailServiceImpl.class);      

    /**
     * @@roseuid 41789C4801B5
     */
    public WEB3HistorySettleDetailServiceImpl() 
    {
     
    }
    
    /**
     * ���ϖ��ו\���������s���B<BR>
     * <BR>
     * �p�����[�^�̃��N�G�X�g�f�[�^�����ϖ��׃��N�G�X�g�ɃL���X�g����<BR>
     * get���ϖ��׉��()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413EBAB1033F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3HistorySettleDetailRequest)
        {
            
            l_response = this.getSettleDetailScreen((WEB3HistorySettleDetailRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���ϖ��׉��)<BR>
     * ���ϖ��׉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���ϖ��׃T�[�r�X)get���ϖ��׉�ʁv�Q��<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �u(���ϖ��׃T�[�r�X)get���ϖ��׉�ʁv<BR>
     *         ��̈ʒu    :  1.5 is�����{(�ڋq : �ڋq) <BR>
     *         false���ԋp���ꂽ�ꍇ�́A<BR>
     *         �u����񍐏������{�ڋq�G���[�v��<BR>
     *         ��O���X���[����B  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_01059           <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���ϖ��׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse
     * @@roseuid 413FB0F9033C
     */
    protected WEB3HistorySettleDetailResponse getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailScreen(WEB3HistorySettleDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();

        //1.2 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 get���ϖ��ח���(�ڋq, String)        
        SettleDetailHistoryParams l_settleDetailHistoryParams = 
            WEB3HistoryTradeHistoryDataManager.getSettleDetailHistory(
                l_request.detailsManageNo);
        if (l_settleDetailHistoryParams == null)
        {
            log.error("�f�[�^�s�����G���[");
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);  
        }
     
        //1.6 createResponse()
        WEB3HistorySettleDetailResponse l_response = 
            (WEB3HistorySettleDetailResponse)l_request.createResponse();

        //1.7 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����
        l_response.remarkName = l_request.remarkName;
        l_response.productCode = l_settleDetailHistoryParams.getProductCode();
        l_response.productName = l_request.productName;
        l_response.marketDiv = l_settleDetailHistoryParams.getMarketDiv();
        l_response.quantity = l_request.quantity;
        l_response.taxType = l_settleDetailHistoryParams.getAccountDiv();
        l_response.repaymentDiv = l_settleDetailHistoryParams.getRepaymentType();
        l_response.openExecDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getOpenExecDate());
        l_response.closeExecDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getCloseExecDate());
        l_response.contractPrice = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getContractPrice());
        l_response.closeContractPrice = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseContractPrice());
        l_response.deliveryDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getDeliveryDate());
        l_response.contractAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getContractAmount());
        l_response.closeContractAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseContractAmount());
        l_response.openCommission = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getOpenCommission());
        l_response.closeCommission = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseCommission());
        l_response.openCommissionTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getOpenCommissionTax());
        l_response.closeCommissionTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseCommissionTax());
        l_response.managementFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getManagementFee());
        l_response.managementFeeTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getManagementFeeTax());
        l_response.nameTransferFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNameTransferFee());
        l_response.nameTransferFeeTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNameTransferFeeTax());
        
        if (l_settleDetailHistoryParams.getLoanEquityFeeIsNull())
        {
            l_response.loanEquityFee = null;
        }
        else
        {
            l_response.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getLoanEquityFee());   
        }        
        l_response.debitDailyInterest = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getDebitDailyInterest());
        l_response.creditDailyInterest = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCreditDailyInterest());
        l_response.dividendAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getDividendAmount());
        l_response.adjustAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getAdjustAmount());
        l_response.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNetAmount());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
