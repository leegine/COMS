head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�P���T�[�r�X�C���^�Z�v�^(WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.data.HostLockRegiAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���b�N�o�^������t�P���T�[�r�X�C���^�Z�v�^)<BR>
 * ���b�N�o�^������t�P���T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor implements Interceptor
{
    public WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor()
    {

    }
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor.class);
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR> 
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����b�N�qY�q�o�^������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR> 
     * �@@�|���b�N�qY�q�o�^������t�L���[Params�I�u�W�F�N�g�̓��e���A<BR>
     *    ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���b�N�qY�q�o�^������t�L���[�̓������� <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =���b�N�qY�q�o�^������t�L���[�̓������� <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h29�FSONAR�A�g�i�Ǘ��ҁj�h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B<BR> 
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h�i���P�j)���R�[������B <BR>
     * �@@�@@�i���P�j�����̓��b�N�qY�q�o�^������t�L���[����ҏW�B <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
                
        //����J�����_�R���e�L�X�g�𐶐�����
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        try
        {
            /*
             * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
             *      �|�T�[�r�X�̈���[0]�����b�N�qY�q�o�^������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
             *      �|���b�N�qY�q�o�^������t�L���[Params�I�u�W�F�N�g�̓��e���A
             *       ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
             */
            HostLockRegiAcceptParams l_parmas = (HostLockRegiAcceptParams)l_serviceParam[0];
            
            String l_strInstitutionCode = l_parmas.getInstitutionCode();
            String l_strBranchCode = l_parmas.getBranchCode();
                   
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���b�N�qY�q�o�^������t�L���[�̓�������
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //����J�����_�R���e�L�X�g.���X�R�[�h = ���b�N�qY�q�o�^������t�L���[�̓�������
            l_context.setBranchCode(l_strBranchCode);
        
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h29�FSONAR�A�g�i�Ǘ��ҁj�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SONAR_CON);
        
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE); 
        
            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );
            
            //��t�����A���t���[�����Z�b�g����            
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            /*
             * �R�j�@@���������b�N����B
             * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, 
             *   �����R�[�h�i���P�j)���R�[������B 
             * �@@�@@�i���P�j�����̓��b�N�qY�q�o�^������t�L���[����ҏW�B 
             */
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_manager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            l_manager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_parmas.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
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
     * @@roseuid 415BC5540295
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //�T�[�r�X���\�b�h�I�����ɃR�[�������B

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
     * @@roseuid 415BC55402A6
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //�T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B
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