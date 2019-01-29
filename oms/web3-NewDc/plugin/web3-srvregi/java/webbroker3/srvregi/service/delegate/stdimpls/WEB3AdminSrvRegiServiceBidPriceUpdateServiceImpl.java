head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�XImpl(WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl.java)
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
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X�����N���X<BR>                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceBidPriceUpdateService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl.class);

    /**
     * @@roseuid 416F392703C8
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate���D�z�X�V( )�܂��́A<BR> 
     * submit���D�z�X�V( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EA6402D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminSrvRegiSuccBidConfirmRequest)
        {
            log.debug("WEB3AdminSrvRegiSuccBidConfirmRequest");
            WEB3AdminSrvRegiSuccBidConfirmResponse l_srvRegiCustomerRegistConfirmResponse = 
                validateBidPriceUpdate((WEB3AdminSrvRegiSuccBidConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiSuccBidCompleteRequest)
        {
            log.debug("WEB3AdminSrvRegiSuccBidCompleteRequest");
            WEB3AdminSrvRegiSuccBidCompleteResponse l_srvRegiCustomerRegistCompleteResponse = 
                submitBidPriceUpdate((WEB3AdminSrvRegiSuccBidCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistCompleteResponse;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate���D�z�X�V)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj���D�z�X�V�R���v�Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj���D�z�X�V�R���v): <BR>
     *         1.8.<���򏈗�><BR>
     *           get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ<BR>
     *           ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EA820291
     */
    protected WEB3AdminSrvRegiSuccBidConfirmResponse validateBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        l_request.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);
        
        //1.5 �،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get�T�[�r�X�}�X�^�[(String, String, boolean)        
        //�T�[�r�X���Ǘ�
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = 
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);
        
        //1.7 get�T�[�r�X���I���(�ʔ� : long, is�s���b�N : boolean)
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = l_srvRegiServiceMaster.getSrvLotInfo(Long.parseLong(l_request.lotteryId), false);
        
        //1.8 <���򏈗�>
        //get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ��O���X���[����B
        if (l_srvRegiServiceLotInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.9 create���X�|���X( )
        WEB3AdminSrvRegiSuccBidConfirmResponse l_srvRegiSuccBidConfirmResponse = 
                (WEB3AdminSrvRegiSuccBidConfirmResponse)l_request.createResponse();
                        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvRegiSuccBidConfirmResponse; 
    }
    
    /**
     * (submit���D�z�X�V)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj���D�z�X�V�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj���D�z�X�V�v): <BR>
     *         1.9.<���򏈗�><BR>
     *          get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ<BR>
     *          ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EA8202A1
     */
    protected WEB3AdminSrvRegiSuccBidCompleteResponse submitBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        l_request.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);
        
        //1.5 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6 �،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 get�T�[�r�X�}�X�^�[(String, String, boolean)        
        //�T�[�r�X���Ǘ�
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = 
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);
        
        //1.8 get�T�[�r�X���I���(�ʔ� : long, is�s���b�N : boolean)
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = l_srvRegiServiceMaster.getSrvLotInfo(Long.parseLong(l_request.lotteryId), true);
        
        //1.9 <���򏈗�>
        //get�T�[�r�X���I���( )�̖߂�l=null�̏ꍇ��O���X���[����B
        if (l_srvRegiServiceLotInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.10 <�p�����[�^�E�Z�b�g>
        //�ō����D�z       
        if (l_request.maxSuccBidding != null)
        {
            l_srvRegiServiceLotInfo.setHighSuccessBid(new Long(l_request.maxSuccBidding));
        }
        else
        {
            l_srvRegiServiceLotInfo.setHighSuccessBid(null);
        }
        
        //�Œᗎ�D�z
        if (l_request.minSuccBidding != null)
        {
            l_srvRegiServiceLotInfo.setLowSuccessBid(new Long(l_request.minSuccBidding));
        }
        else
        {
            l_srvRegiServiceLotInfo.setLowSuccessBid(null);
        }
        
        //���d���ϊz
        if (l_request.weightedAverage != null)
        {
            l_srvRegiServiceLotInfo.setAverageSuccessBid(new Long(l_request.weightedAverage));          
        }
        else
        {
            l_srvRegiServiceLotInfo.setAverageSuccessBid(null);
        }

        //1.10.1 save�T�[�r�X���I���( )
        l_srvRegiServiceLotInfo.saveSrvLotInfo();
        
        //1.11 create���X�|���X( )
        WEB3AdminSrvRegiSuccBidCompleteResponse l_srvRegiSuccBidCompleteResponse = 
                (WEB3AdminSrvRegiSuccBidCompleteResponse)l_request.createResponse();
                        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvRegiSuccBidCompleteResponse; 
    }
}
@
