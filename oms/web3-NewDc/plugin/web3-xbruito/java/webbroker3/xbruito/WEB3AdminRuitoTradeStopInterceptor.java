head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җݓ������ʔ�����~�T�[�r�X�C���^�Z�v�^ (WEB3AdminRuitoTradeStopInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

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
 * (�Ǘ��җݓ������ʔ�����~�T�[�r�X�C���^�Z�v�^)<BR>
 * �Ǘ��җݓ������ʔ�����~�T�[�r�X�C���^�Z�v�^�N���X
 */
public class WEB3AdminRuitoTradeStopInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopInterceptor.class);

    /**
     *�T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     *<BR>
     *����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     *�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j   <BR>
     *<BR>
     *�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     *�@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     *<BR>
     *�@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     *�@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR> 
     *�@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR> 
     *�@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR> 
     *�@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = 00�F���̑�<BR> 
     *�@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR> 
     *�@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�i���ׂāj�h<BR> 
     *<BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     *�@@�@@�msetAttribute�ɓn���p�����^�n<BR> 
     *�@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR> 
     *�@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR> 
     *<BR>
     *�Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
     *�@@�|������ԊǗ�.setTimestamp()���R�[������B<BR> 
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g 
     * @@param l_serviceParam - �T�[�r�X���\�b�h���� 
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
         
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();   
            // �،���ЃR�[�h���擾                                                                                                                   
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            // ���X�R�[�h���擾                                                                                                                     
            String l_strBranchCode = l_admin.getBranchCode();

            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            //�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
    
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h�ҏW  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h�ҏW
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = 00�F���̑� 
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�i���ׂāj�h 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.DEFAULT);
            
            log.debug("�،���ЃR�[�h = "+l_context.getInstitutionCode());
            log.debug("���X�R�[�h = "+l_context.getBranchCode());
            log.debug("�s��R�[�h = "+l_context.getMarketCode());
            log.debug("�����R�[�h = "+l_context.getProductCode());
            log.debug("��t���ԋ敪 = "+l_context.getTradingTimeType());
            log.debug("������t���i = "+l_context.getOrderAcceptProduct());
            log.debug("������t�g�����U�N�V���� = "+l_context.getOrderAcceptTransaction());
    
            //�Q�j�@@��t�����A���t���[�����Z�b�g����B
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�        
        }        
        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
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
     *�T�[�r�X���\�b�h�I�����ɃR�[�������B<BR> 
     *<BR>
     *����J�����_�R���e�L�X�g�N���A�����B<BR> 
     *<BR>
     *�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     *<BR>
     *�@@������ԊǗ�.TIMESTAMP_TAG<BR> 
     *�@@������ԊǗ�.OFFSET_TAG<BR> 
     *�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR> 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
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
     *�T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     *<BR>
     *����J�����_�R���e�L�X�g�N���A�����B<BR> 
     *<BR>
     *�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     *<BR>
     *�@@������ԊǗ�.TIMESTAMP_TAG <BR>
     *�@@������ԊǗ�.OFFSET_TAG<BR> 
     *�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR> 
     * @@param l_obj - onCall���^�[���l 
     * @@param l_throwable - ��O�I�u�W�F�N�g
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
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
}
@
