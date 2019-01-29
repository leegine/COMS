head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�XImpl(WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStateDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�󋵏Ɖ�T�[�r�X�@@�����N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountDataUlStateInquiryService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.class);
    
    /**
     * @@roseuid 416F39290213
     */
    public WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�󋵏Ɖ�����s���B<BR>
     * <BR>
     * �i�T�[�r�X���p�Ǘ��ҁj�ڋq�f�[�^�A�b�v���[�h�󋵏Ɖ�V�[�P���X�}�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 411AC7D800E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3AdminSrvRegiUploadStateResponse l_response = null;
        if (l_request instanceof WEB3AdminSrvRegiUploadStateRequest)
        {
            //getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //validate����(String, boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
            
            //validate������t�\( )
            WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
            
            //get�A�b�v���[�h��(String)
            WEB3AdminSrvRegiUploadStateRequest l_uploadStateRequest = 
                (WEB3AdminSrvRegiUploadStateRequest)l_request;
            AdministratorUploadParams l_uploadParams = this.getUploadState(l_uploadStateRequest.uploadId);
            
            //�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^UL�󋵏Ɖ�X�|���X( )
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();

            //(*)�v���p�e�B�Z�b�g
            
            //������ԋ敪
            if (l_uploadParams.getUploadEndTimestamp() == null
                && l_uploadParams.getMessageCode() == null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOADING;
            }
            if (l_uploadParams.getUploadEndTimestamp() != null
                && l_uploadParams.getMessageCode() != null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOAD_ERROR;
            }
            if (l_uploadParams.getUploadEndTimestamp() != null
                && l_uploadParams.getMessageCode() == null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOAD_COMPLETE;
            }
            
            //��������
            if (l_uploadParams.getUploadEndTimestamp() != null)
            {
                l_response.endCount = WEB3StringTypeUtility.formatNumber(l_uploadParams.getUploadCount());
            }
            else
            { 
                l_response.endCount = null;
            }
            
            //�J�n����
            l_response.startDate = l_uploadParams.getUploadStartTimestamp();
            
            //end����
            l_response.endDate = l_uploadParams.getUploadEndTimestamp();
            
            //error code
            l_response.errorCode = l_uploadParams.getMessageCode();
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���B";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�A�b�v���[�h��)<BR>
     * �A�b�v���[�hID�ɑΉ�����A�b�v���[�hParams���擾���ĕԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �A�b�v���[�hID �� ����.�A�b�v���[�hID<BR>
     * @@param l_strUploadId - (�A�b�v���[�hID)<BR>
     * ���N�G�X�g�̃A�b�v���[�hID<BR>
     * @@return AdministratorUploadParams
     * @@roseuid 411C3E8E00E0
     */
    public AdministratorUploadParams getUploadState(String l_strUploadId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadState(String )";
        log.entering(STR_METHOD_NAME );

        AdministratorUploadParams l_params = null;
        try
        {
            l_params = (AdministratorUploadParams)
                AdministratorUploadDao.findRowByPk(Long.parseLong(l_strUploadId));
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch(DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }   
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
