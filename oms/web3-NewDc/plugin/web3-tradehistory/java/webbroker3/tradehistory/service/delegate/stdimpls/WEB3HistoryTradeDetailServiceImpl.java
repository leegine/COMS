head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : ������׃T�[�r�XImpl(WEB3HistoryTradeDetailServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 ���q (���u) �V�K�쐬
                   2006/10/19  �����F (���u) ���f�� 057
                   2006/10/26  �����F (���u) ���f�� 064
*/

package webbroker3.tradehistory.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradehistory.data.TradeDetailHistoryParams;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;


/**
 * (������׃T�[�r�XImpl)<BR>
 * ������׃T�[�r�X�����N���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailServiceImpl extends WEB3ClientRequestService implements WEB3HistoryTradeDetailService 
{
    /**
      * ���O�o�̓��[�e�B���e�B�B<BR>
      */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeDetailServiceImpl.class);      
    /**
     * @@roseuid 41789C47004E
     */
    public WEB3HistoryTradeDetailServiceImpl() 
    {
     
    }
    
    /**
     * ������׉�ʕ\���������s���B<BR>
     * <BR>
     * �p�����[�^�̃��N�G�X�g�f�[�^��������׃��N�G�X�g�ɃL���X�g����<BR>
     * get������׉��()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D975A018A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3HistoryTradeDetailRequest)
        {
            l_response = this.getTradeDetailScreen((WEB3HistoryTradeDetailRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get������׉��)<BR>
     * ������׉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(������׃T�[�r�X)get������׉�ʁv�Q��<BR>
     *  <BR>
     *  =============================================== <BR>
     *         �V�[�P���X�} :  �u(������׃T�[�r�X)get������׉�ʁv<BR>
     *         ��̈ʒu    :  1.5 is�����{(�ڋq : �ڋq)  <BR>
     *         false���ԋp���ꂽ�ꍇ�́A<BR>
     *         �u����񍐏������{�ڋq�G���[�v�̗�O���X���[����B<BR>
     *         class         :  WEB3BusinessLayerException<BR>
     *         tag            :  BUSINESS_ERROR_01059         <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ������׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse
     * @@roseuid 413FB0410158
     */
    protected WEB3HistoryTradeDetailResponse getTradeDetailScreen(WEB3HistoryTradeDetailRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1. validate()
        l_request.validate();

        //1.2. get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        SubAccount l_subAccount =this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3. getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4. validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5. getTradeDetailHistory(�ڋq, String)
        TradeDetailHistoryParams  l_tradeDetailHistoryParams = 
            WEB3HistoryTradeHistoryDataManager.getTradeDetailHistory(
                l_request.detailsManageNo);
        if (l_tradeDetailHistoryParams == null)
        {
            log.error("�f�[�^�s�����G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.createResponse()
        WEB3HistoryTradeDetailResponse l_response = 
            (WEB3HistoryTradeDetailResponse)l_request.createResponse();

        //1.7.�v���p�e�B�Z�b�g
        //���X�|���X.�|��E�v��	���@@���N�G�X�g.�|��E�v��
        l_response.remarkName = l_request.remarkName;
        //���X�|���X.�����R�[�h	���@@������ח���Params.�����R�[�h
        l_response.productCode = l_tradeDetailHistoryParams.getProductCode();
        //���X�|���X.������		���@@���N�G�X�g.������
        l_response.productName = l_request.productName;
        //���X�|���X.�s��敪		���@@������ח���Params.�s��敪
        l_response.marketDiv = l_tradeDetailHistoryParams.getMarketDiv();
        //���X�|���X.����		���@@������ח���Params.��������
        l_response.quantity =WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getQuantity());
        //���X�|���X.�P��		���@@������ח���Params.���P��
        l_response.price =WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getPrice());
        //���X�|���X.�����敪		���@@������ח���Params.�����敪
        l_response.taxType = l_tradeDetailHistoryParams.getAccountDiv();
        //���X�|���X.����		���@@������ח���Params.����
        l_response.execDate = WEB3DateUtility.toDay(l_tradeDetailHistoryParams.getExecDate());
        //���X�|���X.��n��		���@@������ח���Params.��n��
        l_response.deliveryDate = WEB3DateUtility.toDay(l_tradeDetailHistoryParams.getDeliveryDate());
        //���X�|���X.�����z		���@@������ח���Params.�����z
        l_response.execAmount = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getExecutedAmount());
        //���X�|���X.�萔��		���@@������ח���Params.�萔��
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getCommissionFee());
        //���X�|���X.�萔�������	���@@������ח���Params.�萔�������
        l_response.commissionTax = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getCommissionFeeTax());
        //���X�|���X.��n���z		���@@������ח���Params.��n���z
        l_response.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getNetAmount());

        //�ȉ��ǉ�
        //���X�|���X.���i�R�[�h     ���@@������ח���Params.���i�R�[�h
        String l_strCommodityCode = l_tradeDetailHistoryParams.getCommodityCode();
        l_response.commodityCode = l_strCommodityCode;

        //���X�|���X.�ʉݒP��      ���@@������ח���Params.�ʉݒP��
        l_response.monetaryUnit = l_tradeDetailHistoryParams.getMonetaryUnit();

        //���X�|���X.�s��敪�i�O���j  ���@@������ח���Params.�s��敪�i�O���j
        l_response.fstkExchDiv = l_tradeDetailHistoryParams.getFstkExchDiv();

        //���X�|���X.�����z�i�O�݁j  ���@@������ח���Params.�����z�i�O�݁j�j
        l_response.monetaryUnitExecutedAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitExecutedAmount());

        //���X�|���X.���n�萔���i�O�݁j ���@@������ח���Params.���n�萔���i�O�݁j
        l_response.monetaryUnitComission =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitComission());

        //���X�|���X.���n����Łi�O�݁j ���@@������ח���Params.���n����Łi�O�݁j
        l_response.monetaryUnitTradeTax =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitTradeTax());

        //���X�|���X.���̑��萔���i�O�݁j    ���@@������ח���Params.���̑��萔���i�O�݁j
        l_response.monetaryUnitInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitInterest());

        //���X�|���X.���n��n����i�O�݁j���@@������ח���Params.���n��n����i�O�݁j
        l_response.localSettleAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getLocalSettleAmount());

        //���X�|���X.���n��n����i�~�݁j���@@������ח���Params.���n��n����i�~�݁j
        l_response.localSettleAmountYen =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getLocalSettleAmountYen());

        //���X�|���X.���ב�      ���@@������ח���Params.���ב�
        l_response.execExchange =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getExecExchange());

        //���X�|���X.�����萔���i�~�݁j ���@@������ח���Params.�����萔���i�~�݁j
        l_response.domesticCommission =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getDomesticCommission());

        //���X�|���X.�z��
        l_response.faceAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getDenomination());

        //���X�|���X.���ϋ敪
        l_response.settleDiv = l_tradeDetailHistoryParams.getPaymentDiv();

        //���X�|���X.�o�ߗ��q(�~��)
        l_response.yenAccruedInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getAccruedInterestYen());

        //���X�|���X.�o�ߗ��q(�O��)
        l_response.foreignAccruedInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getAccruedInterest());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
