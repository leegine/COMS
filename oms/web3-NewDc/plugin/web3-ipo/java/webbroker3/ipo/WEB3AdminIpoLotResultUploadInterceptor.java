head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʃA�b�v���[�h�C���^�Z�v�^�N���X(WEB3AdminIpoLotResultUploadInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 ���C�g (���u) �V�K�쐬
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>068
*/

package webbroker3.ipo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
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
 * �Ǘ���IPO���I���ʃA�b�v���[�h�C���^�Z�v�^�N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoServiceInterceptor.class);
    
    /**
     * @@roseuid 411301770221
     */
    public WEB3AdminIpoLotResultUploadInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B  <BR>
     *  <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j  <BR>
     *  <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B  <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������� <BR>
     * �R���e�L�X�g�̃v���p�e�B���Z�b�g����B  <BR>
     *  <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService��� �ҏW<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h  <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h20�FIPO�h  <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h <BR>
     *  <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * ������ԃR���e�L�X�g���Z�b�g����B  <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     *  <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B  <BR>
     *  <BR>
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 40F23F640020
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {

        final String STR_METHOD_NAME = " onCall(Method,Object[])";

        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode = null; //���X�R�[�h

        try
        {
                  	
            //�Ǘ��҂̏ꍇ�͊Ǘ��҃I�u�W�F�N�g����擾����
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            // �،���ЃR�[�h���擾
            l_strInstitutionCode = l_admin.getInstitutionCode();
            // ���X�R�[�h���擾 
            l_strBranchCode = l_admin.getBranchCode();              
        	
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            log.debug("������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g: ENTER");
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h20�FIPO�h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.IPO);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            log.debug("������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g: END");

            //������ԃR���e�L�X�g���Z�b�g����B
            log.debug("������ԃR���e�L�X�g���Z�b�g: ENTER");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("������ԃR���e�L�X�g���Z�b�g: END");

            //�Q�j�@@��t�����A���t���[�����Z�b�g����B
            //WEB3SystemLayerException
            log.debug("��t�����A���t���[�����Z�b�g: ENTER");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.debug("��t�����A���t���[�����Z�b�g: END");

            log.exiting(STR_METHOD_NAME);
            return l_context;
            
        }
        catch (WEB3BaseException l_ex)
        {   
            
            log.error(l_ex.getMessage(), l_ex);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 40F23F640030
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {

        final String STR_METHOD_NAME = " onReturn(Object,Object)";
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B  <BR>
     *  <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 40F23F640033
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {

        final String STR_METHOD_NAME = " onThrowable(Object,Throwable)";
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
