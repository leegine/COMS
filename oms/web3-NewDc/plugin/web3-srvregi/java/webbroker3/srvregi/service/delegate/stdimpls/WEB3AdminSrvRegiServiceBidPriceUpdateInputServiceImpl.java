head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�XImpl(WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateInputService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓T�[�r�X�����N���X<BR>                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceBidPriceUpdateInputService 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.class);
        
    /**
     * @@roseuid 416F3927031C
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���͏Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq���D�z�X�V���́v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq���D�z�X�V���́v): <BR>
     *         1.8.<���򏈗�> <BR>      
     *          get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ<BR>
     *          ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EC2C01B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidInputRequest l_adminSrvRegiSuccBidInputRequest;
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
       
        l_adminSrvRegiSuccBidInputRequest = (WEB3AdminSrvRegiSuccBidInputRequest)l_request;        
        
        //1.1.validate( )
        l_adminSrvRegiSuccBidInputRequest.validate();
        
        //1.2.setBusinessTimestamp( )
        WEB3SrvRegiTradingTimeManagement.setTimestamp();
        
        //1.3.getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4.validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);
        
        //1.5.get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        
        //1.6.get�T�[�r�X�}�X�^�[(String, String, boolean)
        WEB3SrvRegiServiceMaster l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
            l_strInstitutionCode, l_adminSrvRegiSuccBidInputRequest.serviceDiv, false);
        
        //1.7.get�T�[�r�X���I���(�ʔ� : long, is�s���b�N : boolean)
        WEB3SrvRegiServiceLotInfo l_serviceLotInfo =
            l_serviceMaster.getSrvLotInfo(Long.parseLong(l_adminSrvRegiSuccBidInputRequest.lotteryId), false);
        
        //1.8.get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ��O���X���[����B
        if(l_serviceLotInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.9.create���X�|���X()
        WEB3AdminSrvRegiSuccBidInputResponse l_response =
            (WEB3AdminSrvRegiSuccBidInputResponse)l_adminSrvRegiSuccBidInputRequest.createResponse();
        
        //1.10 <�v���p�e�B�E�Z�b�g>    
        //�T�[�r�X�敪
        l_response.serviceDiv = l_adminSrvRegiSuccBidInputRequest.serviceDiv;
        
        //�T�[�r�X����
        l_response.serviceName = l_serviceMaster.getSrvName();
        
        //�\�����ԁi���j
        l_response.applyStartDate = l_serviceLotInfo.getAppliDateFrom();
        
        //�\�����ԁi���j
        l_response.applyEndDate = l_serviceLotInfo.getAppliDateTo();
        
        //���I��
        l_response.lotteryDate = l_serviceLotInfo.getLotDate();
        
        //�K�p�J�n��
        l_response.trialStartDate = l_serviceLotInfo.getAppliStartDate();
        
        //�K�p�I����
        l_response.trialEndDate = l_serviceLotInfo.getAppliEndDate();
        
        //���p����
        l_response.chargeAmt = String.valueOf(l_serviceLotInfo.getUseAmt());
        
        //���D�P��
        if(l_serviceLotInfo.getBiddingPrice() != null)
        {
            l_response.biddingPriceUnit = l_serviceLotInfo.getBiddingPrice().toString(); 
        }
                  
        //�o����
        l_response.paymentDate = l_serviceLotInfo.getPaymentDate();
        
        //��W�g        
        if(l_serviceLotInfo.getPublicOfferingQty() != null)
        {
            l_response.applyMax = l_serviceLotInfo.getPublicOfferingQty().toString(); 
        }
        
        //�ō����D�z
        if(l_serviceLotInfo.getHighSuccessBid() != null)
        {
            l_response.maxSuccBidding = l_serviceLotInfo.getHighSuccessBid().toString(); 
        }
        
        //�Œᗎ�D�z
        if(l_serviceLotInfo.getLowSuccessBid() != null)
        {
            l_response.minSuccBidding = l_serviceLotInfo.getLowSuccessBid().toString(); 
        }
        
        //���d���ϊz
        if(l_serviceLotInfo.getAverageSuccessBid() != null)
        {
            l_response.weightedAverage = l_serviceLotInfo.getAverageSuccessBid().toString();
        }

        //�^�p�敪���擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪()
        l_response.useDiv = l_serviceLotInfo.getInvestDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
