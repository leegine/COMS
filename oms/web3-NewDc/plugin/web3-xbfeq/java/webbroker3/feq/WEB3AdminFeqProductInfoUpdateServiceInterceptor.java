head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfoUpdateServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������X�V�C���^�Z�v�^(WEB3AdminFeqProductInfoUpdateServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �A�C��(���u) �V�K�쐬
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�������������X�V�C���^�Z�v�^)<BR>
 * �O�������������X�V�C���^�Z�v�^<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfoUpdateServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqProductInfoUpdateServiceInterceptor.class);

    /**
     * @@roseuid 42D0CE42032C
     */
    public WEB3AdminFeqProductInfoUpdateServiceInterceptor() 
    {
     
    }
    
    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���O�C���Z�b�V������胍�O�C���h�c���擾�C<BR>
     * �@@���O�C���h�c�ɊY������Ǘ��҂̏����A<BR>
     * �@@�@@�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 4295FFDD01AC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�Ǘ���
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            // ���X�R�[�h
            String l_strBranchCode = l_administrator.getBranchCode();
        
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = null�h
            l_context.setMarketCode(null);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            log.exiting(STR_METHOD_NAME);
            return l_context;        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 4295FFDD01AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4295FFDD01BB
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

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