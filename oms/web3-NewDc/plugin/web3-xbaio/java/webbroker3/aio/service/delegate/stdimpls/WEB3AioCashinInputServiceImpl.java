head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C���������̓T�[�r�X�����N���X(WEB3AioCashinInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���z (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[ 
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.message.WEB3AioCashinInputRequest;
import webbroker3.aio.message.WEB3AioCashinInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�I�����C���������̓T�[�r�XImpl)<BR>
 * �I�����C���������̓T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinInputServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinInputService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinInputServiceImpl.class);        
    
    /**
     * �I�����C���������̓T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�I�����C���������́j���͉�ʕ\���f�[�^�擾�v �Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �I�����C���������� �v<BR>
     *  �i�I�����C���������́j���͉�ʕ\���f�[�^�擾�@@)<BR>
     * �@@�@@�@@:  1.4.��Еʌ��ϋ@@�֏���(String, String, String)<BR>   
     *     �C���X�^���X�̐����Ɏ��s�����ꍇ�́A
     *     ���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA<BR>
     * ��O���X���[����B<BR>
     * 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00763<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �I�����C���������� �v<BR>
     *    �i�I�����C���������́j���͉�ʕ\���f�[�^�擾 )<BR>
     * �@@�@@�@@:  1.5.validate���ϋ@@�֎�t�\(String)<BR>   
     *     �߂�l�� false �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F240CB0198
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
        WEB3AioCashinInputRequest l_aioCashinInputRequest =
            (WEB3AioCashinInputRequest)l_request;
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_aioCashinInputRequest.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾���� 
        //[����]�⏕�����^�C�v�F1�i�a��������j 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //AIO�����}�l�[�W���I�u�W�F�N�g���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //1.3 �������ʃ`�F�b�N�����{����
        //l_aioOrderManager.validateOrder(l_subAccount);
		//1.3 ������t�\���ǂ����̃`�F�b�N���s���B
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        String l_strPaySchemeID = l_aioCashinInputRequest.paySchemeId;
        
        //1.4 ��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����
        ////�C���X�^���X�̐����Ɏ��s�����ꍇ�́A
        //���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA��O���X���[����
        WEB3AioCompanySettleInstitutionConditions l_AioCompanySettleInstitutionConditions = null;
        try
        {
            l_AioCompanySettleInstitutionConditions = 
                new WEB3AioCompanySettleInstitutionConditions(
                    l_strInstitutionCode, l_strBranchCode, l_strPaySchemeID);                            
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 ���ϋ@@�ւ���t���Ԃ��ǂ������`�F�b�N����
        //validate���ϋ@@�֎�t�\(String)
        //�߂�l�� false �̏ꍇ�A��O���X���[����
        boolean l_blnPaySchemeAcceptPossible = l_aioOrderManager.validatePaySchemeAcceptPossible(
            l_strInstitutionCode,
            l_strBranchCode,
            l_aioCashinInputRequest.paySchemeId);
        if (!l_blnPaySchemeAcceptPossible)
        {
            log.debug("error in validate���ϋ@@�֎�t�\");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "validate���ϋ@@�֎�t�\��false");
        }

        //1.6 ��g���ϋ@@�փC���X�^���X�𐶐�����
        //[����]���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        WEB3AioSettleInstitution l_aioSettleInstitution =
            new WEB3AioSettleInstitution(l_aioCashinInputRequest.paySchemeId);

        //1.7 ���ϋ@@�֖����擾����
        String l_strAioSettleInstitutionName = l_aioSettleInstitution.getName();

        //1.8 1�񓖂���̏���������z���擾����
        double l_dblMaxAmountOnce = l_AioCompanySettleInstitutionConditions.getMaxAmountOnce();

        //1.9 1�񓖂���̉����������z���擾����
        double l_dblMinAccoutOnce = l_AioCompanySettleInstitutionConditions.getMinAmountOnce();

        //1.10 �P�ʓ������z���擾����
        double l_dblAmountUnit = l_AioCompanySettleInstitutionConditions.getAmountUnit();

        //1.11 1��������̏���������z���擾����
        double l_dblMaxAmountDaily = l_AioCompanySettleInstitutionConditions.getMaxAmountDaily();

        //1.12 1���̏�������񐔂��擾����
        double l_dblMaxCount = l_AioCompanySettleInstitutionConditions.getMaxCount();

        //1.13 ���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinInputResponse l_aioCashinInputResponse = 
            (WEB3AioCashinInputResponse)l_aioCashinInputRequest.createResponse();

        //1.14 �ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����
        //���X�|���X.���ϋ@@��ID = ���N�G�X�g�f�[�^.���ϋ@@��ID
        l_aioCashinInputResponse.paySchemeId = l_aioCashinInputRequest.paySchemeId;
        //���X�|���X.���ϋ@@�֖� = ��g���ϋ@@��.get����()�̖߂�l
        l_aioCashinInputResponse.paySchemeName = l_strAioSettleInstitutionName;
        //���X�|���X.������z = ��Еʌ��ϋ@@�֏���.get����������z�i1�񓖂���j()�̖߂�l
        l_aioCashinInputResponse.maxAmount = WEB3StringTypeUtility.formatNumber(l_dblMaxAmountOnce);
        //���X�|���X.�������z = ��Еʌ��ϋ@@�֏���.get�����������z�i1�񓖂���j()�̖߂�l
        l_aioCashinInputResponse.minAmount = WEB3StringTypeUtility.formatNumber(l_dblMinAccoutOnce);
        //���X�|���X.�U���P�� = ��Еʌ��ϋ@@�֏���.get�P�ʓ������z()�̖߂�l
        l_aioCashinInputResponse.transferUnit = WEB3StringTypeUtility.formatNumber(l_dblAmountUnit);
        //���X�|���X.����������z = ��Еʌ��ϋ@@�֏���.get����������z�i1��������j()�̖߂�l
        l_aioCashinInputResponse.maxTotalAmount = WEB3StringTypeUtility.formatNumber(l_dblMaxAmountDaily);
        //���X�|���X.��������� = ��Еʌ��ϋ@@�֏���.get����񐔁i1��������j()�̖߂�l
        l_aioCashinInputResponse.cashinMaxTimes = WEB3StringTypeUtility.formatNumber(l_dblMaxCount);

        log.exiting(l_strMethodName); 

        return l_aioCashinInputResponse;                     

    }
}
@
