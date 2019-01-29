head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ԍό�]�͏���ʕ\���T�[�r�XImpl(WEB3TPAfterRepayTransitionReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) �V�K�쐬
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginAfterRepayCalcInfoResponse;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerAfterRepayService;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAfterRepayTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό�]�͏���ʕ\���T�[�r�XImpl)
 */
public class WEB3TPAfterRepayTransitionReferenceServiceImpl
        extends WEB3TPAssetTradingPowerServiceImpl
        implements WEB3TPAfterRepayTransitionReferenceService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3TPAssetTradingPowerServiceImpl.class);

    /**
     * @@roseuid 4255DAAC0262
     */
    public WEB3TPAfterRepayTransitionReferenceServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * ���V�[�P���X�}�u�i�ԍό�]�͏���ʕ\���T�[�r�X�jexecute�v�Q��<BR>
     * <BR>
     * @@param ���N�G�X�g
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //validate������t�\
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //(�ԍό�]��)�M�p����ԍϒ������N�G�X�g
        WEB3TPAfterRepayTransitionReferenceRequest l_afterRepayRequest = (WEB3TPAfterRepayTransitionReferenceRequest) l_request;

        //�M�p����ԍσT�[�r�X�擾
        WEB3MarginCloseMarginService l_closeMarginService = (WEB3MarginCloseMarginService) Services
            .getService(WEB3MarginCloseMarginService.class);

        //�M�p����ԍό�]�͌v�Z��񃌃X�|���X���擾
        WEB3MarginAfterRepayCalcInfoResponse l_calcInfoResponse = (WEB3MarginAfterRepayCalcInfoResponse) l_closeMarginService
            .execute(l_afterRepayRequest);

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //�ԍό�]�̓T�[�r�X���擾
        WEB3TPTradingPowerAfterRepayService l_afterRepayService = (WEB3TPTradingPowerAfterRepayService) Services
            .getService(WEB3TPTradingPowerAfterRepayService.class);

        //�ԍό㎑�Y�]�͏��I�u�W�F�N�g���擾
        WEB3TPTradingPowerCalcMarginAfterRepay l_calcMarginAfterRepay = l_afterRepayService
            .createWEB3TPTradingPowerCalcAfterRepay(
                    l_subAccount, l_calcInfoResponse.interceptor,
                    l_calcInfoResponse.orderSpec);

        //(�ԍό�]��)�]�͐��ډ�ʕ\�����X�|���X
        WEB3TPAfterRepayTransitionReferenceResponse l_afterRepayResponse = (WEB3TPAfterRepayTransitionReferenceResponse) l_afterRepayRequest
            .createResponse();

        //(�ԍό�]��)�]�͐��ډ�ʕ\�����X�|���X�ɒl���Z�b�g
        this.createTransitionReferenceMargin(
                l_calcMarginAfterRepay, l_afterRepayResponse);

        //T+0..5�̊ԁA�J��Ԃ�����
        for(int index = 0; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //�������ԍό��ϑ��v���Z�b�g
            l_afterRepayResponse.transitionReferenceUnits[index].orderRepaySettleProfitLoss = format(l_calcMarginAfterRepay
                .getOrderRepaySettleProfitLoss(index));
            //���񔭒����ԍό��ϑ��v���Z�b�g
            l_afterRepayResponse.transitionReferenceUnits[index].currOrderRepaySettleProfitLoss = format(l_calcMarginAfterRepay
                .getCurrOrderRepaySettleProfitLoss(index));
        }

        //(�ԍό�]��)�]�͐��ډ�ʕ\�����X�|���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_afterRepayResponse;
    }
}@
