head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�����T�[�r�X�C���^�Z�v�^ (WEB3AdminIfoManualExpireServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
Revision History : 2007/07/09�@@�Ј���(���u) �d�l�ύX���f��No.002
Revision History : 2007/07/19�@@�����F(���u) ���f��003
*/

package webbroker3.ifoadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�C���^�Z�v�^ )<BR>
 * �Ǘ��ҁE�敨OP�蓮�����T�[�r�X�C���^�Z�v�^ <BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireServiceInterceptor.class);

    /**
     * @@roseuid 447AC9B30242
     */
    public WEB3AdminIfoManualExpireServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
�@@   * �|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�� <BR>
     *�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
�@@   * ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
�@@   * ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
�@@   * ����J�����_�R���e�L�X�g.�s��R�[�h = �hDEFAULT�h<BR>
�@@   * ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����w���敨OP�h<BR>
�@@   * ����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h<BR>
     * <BR>
�@@   * �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 446940CF0004
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            // �،���ЃR�[�h���擾
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            // ���X�R�[�h���擾
            String l_strBranchCode = l_admin.getBranchCode();
            //���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �hDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����w���敨OP�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            log.debug("�،���ЃR�[�h = " + l_context.getInstitutionCode());
            log.debug("���X�R�[�h = " + l_context.getBranchCode());
            log.debug("�s��R�[�h = " + l_context.getMarketCode());
            log.debug("��t���ԋ敪 = " + l_context.getTradingTimeType());
            log.debug("�����R�[�h = " + l_context.getProductCode());

            //�Q�j��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 446940CF0036
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME); 
     
    }
    
    /**
     * �T�[�r�X���\�b�h��O���ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 446940CF0058
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {   
        String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME); 
    }
}
@
