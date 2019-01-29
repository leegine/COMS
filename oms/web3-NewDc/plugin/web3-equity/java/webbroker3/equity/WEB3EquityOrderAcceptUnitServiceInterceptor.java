head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3EquityOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 羐� (���u) �V�K�쐬
Revesion History : 2004/10/22 �@@�� �C��    
Revesion History : 2004/12/15 �X�� (SRA)  �c�Č��Ή�
Revesion History : 2005/01/05 �����a�� (SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����������t�ꌏ�T�[�r�X�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderAcceptUnitServiceInterceptor.class);

    /**
     * @@roseuid 4142B67C0206
     */
    public WEB3EquityOrderAcceptUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)�B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@?�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@?����������t�L���[Params�I�u�W�F�N�g�̓��e���A <BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ����������t�L���[Params�̓����� <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = ����������t�L���[Params�̓����� <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null <BR>
     * �@@������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h <BR>
     * �@@������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null <BR>
     * <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@?������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     * �@@�@@�@@���R�[������B <BR>
     * �@@�@@�@@�������͊���������t�L���[Params���ҏW�B <BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 41083B040364
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����       
        
        //�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams = 
            (HostEqtypeOrderAcceptParams)l_serviceParams[0];
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h =  ����������t�L���[Params�̓�����
        l_context.setInstitutionCode(l_hostEqtypeOrderAcceptParams.getInstitutionCode());
        
        //����J�����_�R���e�L�X�g.���X�R�[�h = ����������t�L���[Params�̓�����
        l_context.setBranchCode(l_hostEqtypeOrderAcceptParams.getBranchCode());
        
        //����J�����_�R���e�L�X�g.�s��R�[�h = null
        l_context.setMarketCode(null);
        
        //������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //����J�����_�R���e�L�X�g.������t���i = null
        l_context.setOrderAcceptProduct(null);
        
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_context.setOrderAcceptTransaction(null);
        
        //������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            //�Q�j�@@��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //�R�j�@@���������b�N����
            l_accountManager.lockAccount(
                l_hostEqtypeOrderAcceptParams.institution_code,
                l_hostEqtypeOrderAcceptParams.getBranchCode(),
                l_hostEqtypeOrderAcceptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }

        
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }

    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ������t�T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 41083B040367
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 41083B04036A<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
