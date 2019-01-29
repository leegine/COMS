head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������T�[�r�X�C���^�Z�v�^(WEB3BondCancelInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 ��іQ (���u) �V�K�쐬
Revesion History : 2007/07/25 �Ӑ� (���u) �d�l�ύX�E���f��221
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (������T�[�r�X�C���^�Z�v�^)<BR>
 * ������T�[�r�X�C���^�Z�v�^
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondCancelServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondCancelServiceInterceptor.class);   
    
    /**
     * @@roseuid 44FBFD3802CE
     */
    public WEB3BondCancelServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�� <BR>
     * �@@�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR> 
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0�FDEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 =<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����ID�ɊY������������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�������P��.���^�C�v == "�O����"�̏ꍇ�A"25�F��"���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����B<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "28�F��" <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "06�F���"<BR>
     * <BR>
     * �@@-ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g�� <BR>
     * �@@�Z�b�g����B<BR>
     * �@@�msetAttribute�ɓn���p�����^�n <BR>
     * �@@�@@�ݒ�L�[: ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �@@�@@�ݒ�l: ������ԃR���e�L�X�g<BR>  
     * <BR>
     * �Q�j��t�����A���t���[�����Z�b�g����B <BR>
     * �@@������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j���N�G�X�g�f�[�^�̌^���u������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>  
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>  
     * �@@�@@��������OpLoginSecurityService���ҏW�B<BR> 
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 44B740450055
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        try
        {              
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //�@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g��  
            //�@@�v���p�e�B���Z�b�g����B  
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException

            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();   
            
            //�����R�[�h���擾����
            String l_strAccountCode = l_mainAccount.getAccountCode();
                
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW  
            l_context.setBranchCode(l_strBranchCode);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = "0�FDEFAULT"  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 =
            //      ���N�G�X�g�f�[�^.����ID�ɊY������������P�ʃI�u�W�F�N�g���擾����B
            //       �������P��.���^�C�v == "�O����"�̏ꍇ�A"25�F��"���Z�b�g����B
            //       �������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����B
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager();
            //������m�F���N�G�X�g�̏ꍇ
            if (l_serviceParam[0] instanceof WEB3BondCancelConfirmRequest)
            {
                //���N�G�X�g�f�[�^.����ID�ɊY������������P�ʃI�u�W�F�N�g���擾����
                WEB3BondCancelConfirmRequest l_confirmRequest =
                    (WEB3BondCancelConfirmRequest)l_serviceParam[0];
                WEB3BondOrderUnit l_bondOrderUnit =
                    l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_confirmRequest.orderId));
                if (l_bondOrderUnit.getBondType() == null)
                {
                    //�������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����
                    l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                }
                else
                {
                    String l_strBondType = String.valueOf(l_bondOrderUnit.getBondType().intValue());
                    if (WEB3BondTypeListDef.FOREIGN_BOND.equals(l_strBondType))
                    {
                        //�������P��.���^�C�v == "�O����"�̏ꍇ�A"25�F��"���Z�b�g����B
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
                    }
                    else
                    {
                        //�������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                    }
                }
            }
            else if (l_serviceParam[0] instanceof WEB3BondCancelCompleteRequest)
            {
                //������������N�G�X�g�̏ꍇ
                //���N�G�X�g�f�[�^.����ID�ɊY������������P�ʃI�u�W�F�N�g���擾����
                WEB3BondCancelCompleteRequest l_confirmRequest =
                    (WEB3BondCancelCompleteRequest)l_serviceParam[0];
                WEB3BondOrderUnit l_bondOrderUnit =
                    l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_confirmRequest.orderId));
                if (l_bondOrderUnit.getBondType() == null)
                {
                    //�������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����
                    l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                }
                else
                {
                    String l_strBondType = String.valueOf(l_bondOrderUnit.getBondType().intValue());
                    if (WEB3BondTypeListDef.FOREIGN_BOND.equals(l_strBondType))
                    {
                        //�������P��.���^�C�v == "�O����"�̏ꍇ�A"25�F��"���Z�b�g����B
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
                    }
                    else
                    {
                        //�������P��.���^�C�v �� "�O����"�̏ꍇ�A"36�F������"���Z�b�g����
                        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOMESTIC_BOND);
                    }
                }
            }

            //�@@�@@�@@����J�����_�R���e�L�X�g.������t���i = "28�F��"  
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "06�F���" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
    
            //-ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g��  
            //�Z�b�g����B    
            //�ݒ�L�[: ������ԊǗ�.TRADING_CAL_CONTEXT_PATH   
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            //�Q�j�@@��t�����A���t���[�����Z�b�g����B  
            //�@@������ԊǗ�.setTimestamp()���R�[������B 
            WEB3GentradeTradingTimeManagement.setTimestamp(); //WEB3BaseException
            
            //�R�j�@@���N�G�X�g�f�[�^�̌^���u������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B  
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
            //��������OpLoginSecurityService���ҏW�B 
            if (l_serviceParam[0] instanceof WEB3BondCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
            }   
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 44B7405C01AD
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null); 
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR> 
     * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 44B740660101
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null); 
        
        log.exiting(STR_METHOD_NAME);  
    }

}
@
