head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockStatusUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������b�N�敪�X�V�C���^�Z�v�^(WEB3AdminBondOrderLockStatusUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ꎉ�(���u) �V�K�쐬         
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;


/**
 * ���Ǘ��Ғ������b�N�敪�X�V�C���^�Z�v�^<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockStatusUpdateInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockStatusUpdateInterceptor.class);
    
    /**
     * @@roseuid 44E3362402EE
     */
    public WEB3AdminBondOrderLockStatusUpdateInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * <BR>
     * �P�j�������P�ʃI�u�W�F�N�g�̎擾 <BR>
     * �@@�@@�g���������}�l�[�W��.get�������P��()���R�[�����č������P�ʃI�u�W�F�N�g���擾����B <BR>
     * �@@�@@[����]  <BR>
     * �@@�@@�@@����ID�F ����.����ID<BR>
     * �@@�@@�@@�����O�Ɉ���.����ID���`�F�b�N����B<BR>
     * �@@�@@�@@����ID==null�̏ꍇ�A��O���X���[����B<BR>   
     * �@@�@@�@@����ID�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@tag: �@@BUSINESS_ERROR_00600<BR>
     * �@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@tag: �@@BUSINESS_ERROR_01476<BR>
     * <BR>
     * �Q�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�������P�ʂ̏����A�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �������P��.���XID�ɊY�����镔�X�I�u�W�F�N�g.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �������P��.���XID�ɊY�����镔�X�I�u�W�F�N�g.���X�R�[�h  <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "28:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00:DEFAULT"<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@���������b�N����B <BR>
     * �@@�S�|�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾<BR>
     * �@@[����] <BR>
     * �@@�@@�ڋqID�F�擾�����������P��.get�ڋqID()  <BR>
     * <BR>
     *   �S�|�Q�j�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     *   �@@[����]  <BR>
     * �@@�@@�E�،���ЃR�[�h�F�ڋq�I�u�W�F�N�g����擾 <BR>
     * �@@�@@�E���X�R�[�h�F�ڋq�I�u�W�F�N�g����擾 <BR>
     * �@@�@@�E�����R�[�h�F�ڋq�I�u�W�F�N�g����擾 <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 44D0816C0173
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_serviceParam.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        
        String l_strId = null;
        if (l_serviceParam[0] instanceof WEB3AdminBondOrderLockUnlockRequest)
        {
            WEB3AdminBondOrderLockUnlockRequest l_lockUnlockRequest = 
                (WEB3AdminBondOrderLockUnlockRequest) l_serviceParam[0];
            l_strId = l_lockUnlockRequest.id;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����ID==null�̏ꍇ�A��O���X���[����B 
        if (l_strId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        //����ID�����l�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(l_strId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�������ȊO�ł��B");
        }
        
        try
        {
            // �P�j�������P�ʃI�u�W�F�N�g�̎擾 
            //     �g���������}�l�[�W��.get�������P��()���R�[�����č������P�ʃI�u�W�F�N�g���擾����B 
            //        [����]  
            //        ����ID�F ����.����ID
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();  
            WEB3BondOrderUnit l_bondOrderUnit =
                l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_strId));
            
            // �Q�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            // �|�������P�ʂ̏����A�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_bondOrderUnit.getBranchId()); 
            
            String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_branch.getBranchCode();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �������P��.���XID�ɊY�����镔�X�I�u�W�F�N�g.�،���ЃR�[�h  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = �������P��.���XID�ɊY�����镔�X�I�u�W�F�N�g.���X�R�[�h  
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //�@@����J�����_�R���e�L�X�g.������t���i = "28:��" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00:DEFAULT" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            
            //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  
            //�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //�R�j�@@��t�����A���t���[�����Z�b�g����B  
            // �|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //�S�j�@@���������b�N����B
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //�S�|�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾 
            //       �@@[����] 
            //         �ڋqID�F�擾�����������P��.get�ڋqID() 
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(
                    l_bondOrderUnit.getAccountId());
            
            //�S�|�Q�j�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
            //      [����] 
            //      �E�،���ЃR�[�h�F�ڋq�I�u�W�F�N�g����擾 
            //      �E���X�R�[�h�F�ڋq�I�u�W�F�N�g����擾 
            //      �E�����R�[�h�F�ڋq�I�u�W�F�N�g����擾 
            l_gentradeAccountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
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
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_returnValue - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_context - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44D08174000C
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
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
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44D0817C027D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
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