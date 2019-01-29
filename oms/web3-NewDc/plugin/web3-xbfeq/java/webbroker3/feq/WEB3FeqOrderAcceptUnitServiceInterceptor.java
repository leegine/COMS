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
filename	WEB3FeqOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�P���T�[�r�X�C���^�Z�v�^(WEB3FeqOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�P���T�[�r�X�C���^�Z�v�^)<BR>
 * �O������������t�P���T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUnitServiceInterceptor.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3FeqOrderAcceptUnitServiceInterceptor() 
    {
     
    }

    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����.�T�[�r�X�̈���[0]���A�����P�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�P�Ŏ擾���������P�ʂ��A<BR>
     * �@@�@@�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.get���X�R�[�h()�̖߂�l<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.get�s��().get�s��R�[�h()�̖߂�l<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "10�F�O������"<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "04�F�O����"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@WEB3GentradeTradingTimeManagement.setBusinessTimestamp()<BR>
     * �@@���R�[�����A�Ɩ��������Z�b�g����B<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����()���R�[������B<BR>
     * �@@�@@�� �����͎擾���������P�ʂ��ҏW�B<BR>
     * <BR> 
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>       
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     *  �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@����.�T�[�r�X�̈���[0]���A�����P�ʂ��擾����B
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_serviceParams[0];
            
            //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.get�،���ЃR�[�h()�̖߂�l 
            l_context.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.get���X�R�[�h()�̖߂�l 
            l_context.setBranchCode(l_feqOrderUnit.getBranchCode());
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.get�s��().get�s��R�[�h()�̖߂�l 
            l_context.setMarketCode(l_feqOrderUnit.getMarket().getMarketCode());
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = "10�F�O������"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = "04�F�O����"
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
            l_context.setOrderAcceptTransaction(null);
            
            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
    	            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
    	            l_context);
            
            //�R�j WEB3GentradeTradingTimeManagement.setBusinessTimestamp()
            //���R�[�����A�Ɩ��������Z�b�g����B
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp(); 
            
            //�S�j ���������b�N����B
            //�|�g���A�J�E���g�}�l�[�W��.lock����()���R�[������B
            //�������͎擾���������P�ʂ��ҏW�B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_accountManager.lockAccount(
                l_feqOrderUnit.getInstitutionCode(), 
                l_feqOrderUnit.getBranchCode(), 
                l_feqOrderUnit.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 429FED390042
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 429FED39004E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
