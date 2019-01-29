head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�������I���T�[�r�X�����N���X(WEB3AioCashinSelectServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���z (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[ 
                   2006/04/14 ������ �d�l�ύX�E���f��526
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.message.WEB3AioCashinSelectRequest;
import webbroker3.aio.message.WEB3AioCashinSelectResponse;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AioCashinSelectService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�I�����C�������I���T�[�r�XImpl)<BR>
 * �I�����C�������I���T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSelectServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSelectService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSelectServiceImpl.class);
         
    /**
     * �I�����C�������I���T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�I�����C�������I���j�I����ʕ\���f�[�^�擾�v �Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1F35B0215
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
        WEB3AioCashinSelectRequest l_aioCashinSelectRequest = (WEB3AioCashinSelectRequest)l_request;
        
        //1.1�⏕�����I�u�W�F�N�g���擾����
        //[����] 
        //�⏕�����^�C�v�F1�i�a��������j 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //AIO�����}�l�[�W���I�u�W�F�N�g���擾����
        /*
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.AIO);         
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //1.2�������ʃ`�F�b�N�����{����
        l_aioOrderManager.validateOrder(l_subAccount);
        */
		// 1.2������t�\���ǂ����̃`�F�b�N���s���B 
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
                      
        //�}���`�o���N���ϐ���T�[�r�X�����I�u�W�F�N�g���擾����
        WEB3AioMultiBankSettleControlService l_multiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
            WEB3AioMultiBankSettleControlService.class);
        
        //1.3get�L�����A�敪(String)
        OpLoginSecurityService l_opLoginSecurityService = 
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        String l_strCareerDiv = l_multiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        
        //1.4�I���\�Ȍ��ϋ@@�ւ̈ꗗ���擾����
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        WEB3AioSelectSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnit = 
            l_multiBankSettleControlService.getSelectPaySchemeDetails(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        
        //1.5���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinSelectResponse l_response = 
            (WEB3AioCashinSelectResponse)l_aioCashinSelectRequest.createResponse();
        
        //1.6���X�|���X.���ϋ@@�ֈꗗ = �}���`�o���N���ϐ���T�[�r�XImpl.get�I�����ϋ@@�֖���()�̖߂�l
        l_response.selectSettleInstitutionUnit = l_aioSelectSettleInstitutionUnit;
                 
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
