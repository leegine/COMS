head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g���̓T�[�r�X�����N���X(WEB3AdminAioSettleReportInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ��O�� (���u) �V�K�쐬  
                   2004/10/27 ���E(���u) ���r���[                  
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.message.WEB3AdminAioSettleReportInputResponse;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ϘA�g���|�[�g���̓T�[�r�XImpl)<BR>
 * ���ϘA�g���|�[�g���̓T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioSettleReportInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioSettleReportInputService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportInputServiceImpl.class);
    
    /**
     * ���ϘA�g���|�[�g���̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ϘA�g���|�[�g���́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4100FF47030D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AdminAioSettleReportInputResponse l_adminSettleReportInputResponse = null;        
        
        //1.1 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = null;
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.3 ���X�R�[�h���擾����B
        String l_strBranchCode = null;        
        l_strBranchCode = l_web3Administrator.getBranchCode();
        
        //1.4 ��g���ϋ@@�֖��ׂ̔z����擾����B
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                WEB3AioMultiBankSettleControlService.class);     
        
        WEB3AioSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnits = 
            l_aioMultiBankSettleControlService.getAffiliatedPaySchemeDetails(l_strInstitutionCode);
        
        //1.5 ���X�|���X�f�[�^�𐶐�����B
        l_adminSettleReportInputResponse = (WEB3AdminAioSettleReportInputResponse) 
            l_request.createResponse();
        
        //1.6  �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���X�|���X.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l        
        l_adminSettleReportInputResponse.branchCode = l_strBranchCode;
        
        //���X�|���X.���ϋ@@�ֈꗗ = �}���`�o���N���ϐ���T�[�r�XImpl.get��g���ϋ@@�֖���()�̖߂�l
        l_adminSettleReportInputResponse.settleInstitutionUnits = l_aioSelectSettleInstitutionUnits;

        log.debug("���X�|���X.���X�R�[�h = " + l_adminSettleReportInputResponse.branchCode);
        log.debug("���X�|���X.���ϋ@@�ֈꗗ = " + l_adminSettleReportInputResponse.settleInstitutionUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminSettleReportInputResponse;
    }
}
@
