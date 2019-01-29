head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����v�����~�T�[�r�X�����N���X(WEB3AioCashinSettleCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���z (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[ 
                   2005/1/11 ���E (���u) �c�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashinSettleCancelRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCancelResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����v�����~�T�[�r�XImpl)<BR>
 * �����v�����~�T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCancelServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSettleCancelService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCancelServiceImpl.class);         
    
    /**
     * �����v�����~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����v�����~�jexecute�v �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C�������j�����v�����~ ( )�v) <BR>  
     * �@@�@@�@@�@@: �i�����v�����~�jexecute<BR>   
     *     �C���X�^���X�̐����Ɏ��s�i���R�[�h�Ȃ��j or<BR>
     * �i����FLAG�i�����j != '2'�i�������M�j or<BR>
     *   ����FLAG�i���ϊJ�n�j != '0'�i�������j or<BR>
     *   �����敪 != '0'�i�������j�j<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00764<BR>
     * <BR>
     * ==========================================================<BR>
     *<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4119A78202B1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }        
        WEB3AioCashinSettleCancelRequest l_aioCashinSettleCancelRequest = 
            (WEB3AioCashinSettleCancelRequest)l_request;
            
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_aioCashinSettleCancelRequest.validate();
        
        //1.2 ���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����
        //�m�����n 
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.�،���ЃR�[�h
        String l_strInstitutionCode = l_aioCashinSettleCancelRequest.institutionCode;
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        String l_strBrachCode = l_aioCashinSettleCancelRequest.branchCode;
        //���ʃR�[�h�F ���N�G�X�g�f�[�^.���ʃR�[�h 
        String l_strOrderRequestNumber = l_aioCashinSettleCancelRequest.orderRequestNumber;                
        //�C���X�^���X�̐����Ɏ��s�i���R�[�h�Ȃ��j or
        //�i����FLAG�i�����j != '2'�i�������M�j or
        //����FLAG�i���ϊJ�n�j != '0'�i�������j or
        //�����敪 != '0'�i�������j�j
        //�̏ꍇ�A��O���X���[����        
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = 
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode, l_strBrachCode, l_strOrderRequestNumber); 
            
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow)l_aioFinInstitutionCashTransStatus.getDataSourceObject();   
                         
        //========remain zhou-yong begin =========
        
        if (!WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusRow.getOrderStatusFlag()) ||
            !WEB3StartStatusFlgDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getStartStatusFlag()) ||
            !WEB3StatusDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00764,
                this.getClass().getName() + "." + l_strMethodName,
                "����FLAG�i�����j != '2'�i�������M�jbut is " + l_bankCashTransferStatusRow.getOrderStatusFlag() 
                + " and ����FLAG�i���ϊJ�n�j != '0'�i�������jbut is " + l_bankCashTransferStatusRow.getStartStatusFlag() 
                + "and �����敪 != '0'�i�������jbut is " + l_bankCashTransferStatusRow.getTransactionStatus());
        }                        
        
        //========remain zhou-yong end =========
        
        //1.3 ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕��v�����~��ԂɍX�V����
        //�m�����n���o���󋵁F���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(WEB3AioMultiBankSettleControlService.class);
        // create a new l_aioFinInstitutionCashTransStatus
        l_aioFinInstitutionCashTransStatus.createForUpdateParams();
        // excute update
        l_aioMultiBankSettleControlService.updateOrderRequireDiscontinuation(l_aioFinInstitutionCashTransStatus);
                                            
        //���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinSettleCancelResponse l_aioCashinSettleCancelResponse = 
            (WEB3AioCashinSettleCancelResponse)l_aioCashinSettleCancelRequest.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_aioCashinSettleCancelResponse;
    }
}
@
