head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϊ����T�[�r�X�����N���X(WEB3AioCashinSettleCompleteServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���z (���u) �V�K�쐬
                   2004/10/22 ���E(���u) ���r���[
                   2005/1/11 ���E (���u) �c�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCompleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ϊ����T�[�r�XImpl)<BR>
 * ���ϊ����T�[�r�X�����N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSettleCompleteService 
{    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCompleteServiceImpl.class);
    
    /**
     * ���ϊ����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ϊ����jexecute�v �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C������)���ϊ��� ( )�v) <BR>
     * �@@�@@�@@�@@: �i�I�����C������)���ϊ��� / �i���ϊ����jexecute<BR>   
     *     �C���X�^���X�̐����Ɏ��s�i���R�[�h�Ȃ��j�����ꍇ�A��O���X���[����B<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C������)���ϊ��� ( )�v) <BR>
     * �@@�@@�@@�@@: �i�I�����C������)���ϊ��� / �i���ϊ����jexecute<BR>   
     *  �@@(*1)<BR>
     * ����FLAG�i�����j != '2'�i�������M�j or<BR>
     * ����FLAG�i���ϊJ�n�j != '2'�i�������M�j or<BR>
     * ����FLAG�i���ό��ʁj != '2'�i�������M�j or<BR>
     * �����敪 != '1'�iOK�j<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00764<BR>
     * <BR>
     * ==========================================================<BR>
     *<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B07B80264
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
        WEB3AioCashinSettleCompleteRequest l_aioCashinSettleCompleteRequest = (WEB3AioCashinSettleCompleteRequest)l_request;
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_aioCashinSettleCompleteRequest.validate();
        
        //1.2 ���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����
        //�m�����n 
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.�،���ЃR�[�h
        String l_strInstitutionCode = l_aioCashinSettleCompleteRequest.institutionCode; 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        String l_strBranchCode = l_aioCashinSettleCompleteRequest.branchCode;
        //���ʃR�[�h�F ���N�G�X�g�f�[�^.���ʃR�[�h 
        String l_strOrderRequestNumber = l_aioCashinSettleCompleteRequest.orderRequestNumber;
        
        //�C���X�^���X�̐����Ɏ��s�i���R�[�h�Ȃ��j�����ꍇ�A��O���X���[����
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus =       
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode, l_strBranchCode, l_strOrderRequestNumber);                                              
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow =
            (BankCashTransferStatusRow)l_aioFinInstitutionCashTransStatus.getDataSourceObject();
        //1.3 �����敪 = '0'�i�������j�̏ꍇ
        if (WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            //1.3.1 ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊ�����ԁi�G���[�j�ɍX�V����
            //�m�����n 
            //���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
                (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
            l_aioMultiBankSettleControlService.updateSettleComplete(l_aioFinInstitutionCashTransStatus);
        }      
        
        //=======remain zhou-yong NO.1 begin ========
        
        //����FLAG�i�����j != '2'�i�������M�j or
        //����FLAG�i���ϊJ�n�j != '2'�i�������M�j or
        //����FLAG�i���ό��ʁj != '2'�i�������M�j or
        //�����敪 != '1'�iOK�j
        //�̏ꍇ�A��O���X���[����
        if (!WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getOrderStatusFlag()) ||
            !WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getStartStatusFlag()) ||
            !WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getResultStatusFlag()) ||
            !WEB3TransactionStatusDef.OK.equals(
            l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            log.debug("__Judge the Flag__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00764,
                this.getClass().getName() + "." + l_strMethodName,
                "����FLAG�i�����j != '2'�i�������M�jbut is " + l_bankCashTransferStatusRow.getOrderStatusFlag()
                + "����FLAG�i���ϊJ�n�j != '2'�i�������M�jbut is " + l_bankCashTransferStatusRow.getStartStatusFlag()
                + "����FLAG�i���ό��ʁj != '2'�i�������M�jbut is " + l_bankCashTransferStatusRow.getResultStatusFlag()
                + "�����敪 != '1'�iOK�jbut is " + l_bankCashTransferStatusRow.getTransactionStatus());
        }

        //=======remain zhou-yong NO.1 end ========
        
        //1.4 �ȉ��̏����Œ����P�ʃe�[�u������A���R�[�h���擾����B
        //�m�����n
        //���ʃR�[�h = ���N�G�X�g�f�[�^.���ʃR�[�h
        //.com�f�r�b�g���ώ���ԍ� = ���Z�@@�֘A�g���o����..com�f�r�b�g���ώ���ԍ�    
        String l_strCenterPayId = l_bankCashTransferStatusRow.getCenterPayId();      
        AioOrderUnitRow l_aioOrderUnitRow;
        try
        {
            List l_lisAioOrderUnitRows = 
                AioOrderUnitDao.findRowsByOrderRequestNumberComDebitNumber(
            l_strOrderRequestNumber, l_strCenterPayId);
            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.size() == 0)
            {
                log.debug("�����P�ʃe�[�u������A���R�[�h���擾���Ȃ� !");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
        }            
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }


        //1.5 ���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinSettleCompleteResponse l_aioCashinSettleCompleteResponse = 
            (WEB3AioCashinSettleCompleteResponse)l_aioCashinSettleCompleteRequest.createResponse();
            
        //1.6 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X..com�f�r�b�g���ώ���ԍ� = ���Z�@@�֘A�g���o����..com�f�r�b�g���ώ���ԍ�
        l_aioCashinSettleCompleteResponse.comDebitNumber = l_bankCashTransferStatusRow.getCenterPayId();
        //���X�|���X.�������z = ���Z�@@�֘A�g���o����.���z
        l_aioCashinSettleCompleteResponse.cashinAmt = String.valueOf(l_bankCashTransferStatusRow.getAmount());
        //���X�|���X.�U���\��� = ���Z�@@�֘A�g���o����.�U�������\���
        l_aioCashinSettleCompleteResponse.transScheduledDate = l_bankCashTransferStatusRow.getComondebiCaptureDate();
        //���X�|���X.�،������U�֓� = ���Z�@@�֘A�g���o����.��n�\���
        l_aioCashinSettleCompleteResponse.accountTransDate = l_bankCashTransferStatusRow.getDeliveryScheduledDate();
        //���X�|���X.�X�V���� = �����P��Params.�X�V���t
        l_aioCashinSettleCompleteResponse.lastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();
        //���X�|���X.����ID = �����P��Params.����ID
        l_aioCashinSettleCompleteResponse.orderId = String.valueOf(l_aioOrderUnitRow.getOrderId());
        //���X�|���X.���ϋ@@��I�c = �����P��Params.���ϋ@@��I�c
        String l_strPaySchemeId = l_bankCashTransferStatusRow.getPaySchemeId();
        l_aioCashinSettleCompleteResponse.paySchemeId = l_strPaySchemeId;
        //���X�|���X.���ϋ@@�֖� = ��g���ϋ@@��Params.����
        WEB3AioSettleInstitution l_web3AioSettleInstitution =
            new WEB3AioSettleInstitution(l_strPaySchemeId);
        l_aioCashinSettleCompleteResponse.paySchemeName = l_web3AioSettleInstitution.getName();       

        log.exiting(l_strMethodName);

        return l_aioCashinSettleCompleteResponse;                                
    }
}
@
