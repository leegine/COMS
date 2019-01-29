head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�����̓T�[�r�X�����N���X(WEB3AioCashinNoticeInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���E (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[ 
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashinNoticeInputResponse;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�����̓T�[�r�XImpl)<BR>
 * �����A�����̓T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeInputServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinNoticeInputService 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeInputServiceImpl.class);
        
    /**
     * �����A�����̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A�����́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC430168
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)������t�\���ǂ����̃`�F�b�N���s���B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.2)�⏕�������擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.3)�����i�ڋq�j�I�u�W�F�N�g���擾����B 
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount) this.getMainAccount();
        
        // 1.4)�����ԍ��i�ڋq�R�[�h�j���擾����B 
        String l_strAccountCode = l_gentradeMainAccount.getAccountCode().substring(0, 6);
        
        // 1.5)�ڋq�̕\�������擾����B 
        String l_strPersonNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        // 1.6)�ڋq�s�I�u�W�F�N�g���擾����B 
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_gentradeMainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
        
        // 1.7)���[���A�h���X���擾����B
        String l_strEmailAddress = l_mainAccountParams.getEmailAddress();
        
        // 1.8)�U����L���@@�֖��ׂ̔z����擾����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_AioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        WEB3AioFinancialInstitutionUnit[] l_aioFinancialInstitutionUnit = 
            l_AioOrderManager.createFinancialInstitutionDetails(l_subAccount);
        
        // 1.9)���X�|���X�f�[�^�𐶐�����B
        WEB3AioCashinNoticeInputResponse l_aioCashinNoticeInputResponse = 
            (WEB3AioCashinNoticeInputResponse)l_request.createResponse();

        // 1.10)(*)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //���X�|���X.�ڋq�� �� �ڋq.get�ڋq�\����()�̖߂�l
        l_aioCashinNoticeInputResponse.accountName =  l_strPersonNameDetails;
        
        //���X�|���X.�ڋq�R�[�h �� �ڋq.getAccountCode()�̖߂�l
        l_aioCashinNoticeInputResponse.accountCode = l_strAccountCode;
        
        //���X�|���X.���[���A�h���X �� MainAccountParams.getEmailAddress()�̖߂�l
        l_aioCashinNoticeInputResponse.emailAddress = l_strEmailAddress;
        
        //���X�|���X.�U������Z�@@�ֈꗗ �� ���o�������}�l�[�W��.create�U������Z�@@�֖���()�̖߂�l
        l_aioCashinNoticeInputResponse.financialInstitutionUnits = l_aioFinancialInstitutionUnit;    

        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeInputResponse;
    }
}
@
