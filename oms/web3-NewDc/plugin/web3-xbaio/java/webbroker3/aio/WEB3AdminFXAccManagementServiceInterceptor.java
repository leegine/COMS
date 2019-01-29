head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccManagementServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����Ǘ��T�[�r�X�C���^�Z�v�^(WEB3AdminFXAccManagementServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 �����(���u) �V�K�쐬
*/
package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���FX�����Ǘ��T�[�r�X�C���^�Z�v�^) <BR>
 * �Ǘ���FX�����Ǘ��T�[�r�X�C���^�Z�v�^
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementServiceInterceptor.class);
    
    /**
     * @@roseuid 41E7934B0242
     */
    public WEB3AdminFXAccManagementServiceInterceptor()
    {
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��� <BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * OpLoginSecurityService���ҏW <BR>
     * ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     * ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * ����J�����_�R���e�L�X�g.������t���i = �h23�F�ב֕ۏ؋��h <BR>
     * ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *      �h01�F���t�i�V�K�����j(�T�[�r�X���p�\��)(�ב֕ۏ؋������J��)�h <BR>
     * <BR>
     * �|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j ��t�����A���t���[�����Z�b�g����B <BR>
     * �|������ԊǗ�.setTimestamp()���R�[������B
     * 
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41BD392C0154
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();  
                
            //�Ǘ��҃I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            
            // ����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l
            l_context.setBranchCode(l_admin.getBranchCode());
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h23�F�ב֕ۏ؋��h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.EXCHANGE_GUARANTEE);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h01�F���t�i�V�K�����j(�T�[�r�X���p�\��)(�ב֕ۏ؋������J��)�h 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                
            
            //�Q�j��t�����A���t���[�����Z�b�g����
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
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
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BD392C0173
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
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
     * 
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41BD392C0192
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(STR_METHOD_NAME); 
    }
}@
