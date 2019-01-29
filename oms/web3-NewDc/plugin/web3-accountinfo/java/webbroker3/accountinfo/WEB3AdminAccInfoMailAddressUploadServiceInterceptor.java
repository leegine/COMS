head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���A�h���X�A�b�v���[�h�T�[�r�X�C���^�Z�v�^(WEB3AdminAccInfoMailAddressUploadServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ������ (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
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
 * (���[���A�h���X�A�b�v���[�h�T�[�r�X�C���^�Z�v�^)<BR>
 *  ���[���A�h���X�A�b�v���[�h�T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoMailAddressUploadServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadServiceInterceptor.class);
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadServiceInterceptor()
    {

    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *   OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h<BR> 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR> 
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�Ǘ��҂̏ꍇ�͊Ǘ��҃I�u�W�F�N�g����擾����
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //����J�����_�����p����R���e�L�X�g�𐶐�����B
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �،���ЃR�[�h
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            //����J�����_�R���e�L�X�g.���X�R�[�h = ���X�R�[�h
            l_context.setBranchCode(l_admin.getBranchCode());
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
         
            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
            //  ������ԃR���e�L�X�g���Z�b�g����B
            //  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);        
            //��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
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
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
}
@