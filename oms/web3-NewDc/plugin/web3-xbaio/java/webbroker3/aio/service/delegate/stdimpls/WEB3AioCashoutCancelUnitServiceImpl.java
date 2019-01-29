head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�����UnitServiceImpl(WEB3AioCashoutCancelUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���E (���u) �V�K�쐬                                      
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashoutTradingPowerCheckUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

import java.util.Map;

/**
 * (�o�����UnitServiceImpl)<BR>
 * �o�����UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 *     TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B
 */
public class WEB3AioCashoutCancelUnitServiceImpl implements WEB3AioCashoutCancelUnitService 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B03AF200DA
     */
    public WEB3AioCashoutCancelUnitServiceImpl() 
    {
     
    }
    
    /**
     * �o������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���j�o������v �Q��
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param String - �p�X���[�h
     * @@throws WEB3BaseException
     * @@roseuid 4191DA5202E7
     */
    public void execute(AioOrderUnit l_orderUnit,String l_strAdminPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) get�⏕����(, )
        //�A�C�e���̒�`
        //�⏕�������擾����B 
        //[����] 
        //����ID�F �����P��.����ID 
        //�⏕����ID�F �����P��.�⏕����ID 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccMana = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = l_orderUnit.getAccountId();
        long l_lngSubAccountId = l_orderUnit.getSubAccountId();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_gentradeAccMana.getSubAccount(l_lngAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2) lock����(String, String, String)
        //�A�C�e���̒�`
        //���������b�N����B
        //[����] 
        //�،���ЃR�[�h�F �⏕��������擾�����،���ЃR�[�h 
        //���X�R�[�h�F �⏕��������擾�������X�R�[�h 
        //�����R�[�h�F �⏕��������擾���������R�[�h 
        String l_institutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_branchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_accountCode = l_subAccount.getMainAccount().getAccountCode();
        l_gentradeAccMana.lockAccount(l_institutionCode, l_branchCode, l_accountCode);
        
        //1.3) ����������e�C���X�^���X�𐶐�����B
        //[����]
        //����ID�F �����P��.����ID
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_orderUnit.getOrderId());

        //1.4) �o���]�̓`�F�b�N�X�V�C���^�Z�v�^( )
        //�A�C�e���̒�`
        //�C���^�Z�v�^�𐶐�����B
        WEB3AioCashoutTradingPowerCheckUpdateInterceptor 
            l_aioCashoutTradingPowerCheckUpdateInterceptor =
                new WEB3AioCashoutTradingPowerCheckUpdateInterceptor();
        
        //1.5) setThreadLocalPersistenceEventInterceptor
        //�A�C�e���̒�`
        //�C���^�Z�v�^���Z�b�g����B
        //[����] 
        //�o���]�̓`�F�b�N�X�V�C���^�Z�v�^�F�@@���������o���]�̓`�F�b�N�X�V�C���^�Z�v�^ 
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashoutTradingPowerCheckUpdateInterceptor);
            
            
        //1.6���O�C���^�C�v��������A����p�X���[�h�ݒ�𑮐��l���擾����
		OpLoginSecurityService l_securityService = (OpLoginSecurityService)
			Services.getService(OpLoginSecurityService.class);
		OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
			OpLoginAdminService.class);
        
		//LoginInfo->LoginType->LoginTypeAttribute 
		LoginInfo l_loginInfo = l_securityService.getLoginInfo();
		Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
		
		MainAccount l_mainAccount = l_subAccount.getMainAccount();
		
		//����p�X���[�h�ݒ���擾����
		String l_strAttribute =
			(String) l_mapAttributes.get(
				WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

		String l_strPassword = null;
		// ���O�C���p�X���[�h�̏ꍇ
		if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
		{
			l_strPassword = l_strAdminPassword;
		}
		// ����p�X���[�h�̏ꍇ
		else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
		{
			WEB3Crypt l_web3Crypt = new WEB3Crypt();
			l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
		}
		log.debug("����p�X���[�h�ݒ� = " + l_strAttribute);
	               
        //1.7) submitCancelOrder(SubAccount, CancelOrderSpec, 
        //�_���r���[::java::lang::String, boolean)(AIO�����}�l�[�W��::submitCancelOrder)
        //�A�C�e���̒�`
        //��������s����B 
        //[����] 
        //�⏕�����F�@@get�⏕�����i�j�̖߂�l 
        //����������e�F�@@����������e�I�u�W�F�N�g 
        //����p�X���[�h�ݒ� == �hDEFAULT�h �̏ꍇ�A����.�p�X���[�h 
		//����p�X���[�h�ݒ� == �h����p�X���[�h�g�p�h �̏ꍇ�A�ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
        //isSkip�����R���F�@@true
                       
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec,
                l_strPassword,
                true);
        
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitCancelOrder Error" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }  
        
        //1.8)  validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
        //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //�A�C�e���̒�`
        //�]�͂̍X�V������B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F null 
        //�������e�F null 
        //������ʁF 1001�i�o�������j 
        //�]�͍X�V�t���O�F true 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);

        log.exiting(STR_METHOD_NAME);
    }
}@
