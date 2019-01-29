head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���ʒm�P���T�[�r�X�C���^�Z�v�^ (WEB3FeqExecutionNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
Revesion History : 2010/01/25 �����F (���u) �d�l�ύX���f��536
Revesion History : 2010/03/05 ���g (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.541
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�������o���ʒm�P���T�[�r�X�C���^�Z�v�^)<BR>
 * �O�������o���ʒm�P���T�[�r�X�C���^�Z�v�^<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3FeqExecutionNotifyUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecutionNotifyUnitServiceInterceptor.class);
        
    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����.�T�[�r�X�̈���[0]���A�����P�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@����.�T�[�r�X�̈���[1]���A�O���o���ʒm�L���[���擾����B<BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     *   �|�P�A�Q�Ŏ擾���������P�ʂƊO���o���ʒm�L���[���A<BR>
     *     �ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>    
     * ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �O���o���ʒm�L���[.get�،���ЃR�[�h()�̖߂�l<BR>
     * ����J�����_�R���e�L�X�g.���X�R�[�h = �O���o���ʒm�L���[.get���X�R�[�h()�̖߂�l<BR>
     * ����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.get�s��().get�s��R�[�h()�̖߂�l<BR>
     * ����J�����_�R���e�L�X�g.��t���ԋ敪 = "10�F�O������"<BR>
     * ����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT <BR>
     * ����J�����_�R���e�L�X�g.������t���i = "04�F�O����"<BR>
     * ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     * <BR> 
     * �|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �S�j�@@���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �T�jThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�validate�����בփ��[�g��"TRUE"���Z�b�g����B<BR>
     * -ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG, <BR>
     * �@@�@@�@@�@@BooleanEnum.TRUE)<BR>
     * �U�jThreadLocalSystemAttributesRegistry.setAttribute()�ɂāA<BR>
     * �����ב֓o�^�t���O��"����.�����ב֓o�^�t���O"���Z�b�g����B<BR>
     * -ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,new Boolean(����.�����ב֓o�^�t���O))<BR>
     * <BR>
     * �V�j�@@���������b�N����B <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�� �����͊O���o���ʒm�L���[���ҏW�B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {          
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length != 4)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3GentradeTradingClendarContext l_context = null;
        try
		{  
            l_context = new WEB3GentradeTradingClendarContext();
            
        	//����.�T�[�r�X�̈���[0]���A�����P�ʂ��擾����B
	        WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_serviceParams[0];
	        
	        //����.�T�[�r�X�̈���[1]���A�O���o���ʒm�L���[���擾����B
	        HostFeqOrderExecNotifyParams l_params = (HostFeqOrderExecNotifyParams)l_serviceParams[1];
	        
	        if (l_params != null)
	        {
		        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �O���o���ʒm�L���[.get�،���ЃR�[�h()�̖߂�l
		        l_context.setInstitutionCode(l_params.getInstitutionCode());
		        
		        //����J�����_�R���e�L�X�g.���X�R�[�h = �O���o���ʒm�L���[.get���X�R�[�h()�̖߂�l
		        l_context.setBranchCode(l_params.getBranchCode());
	        }
	        
	        //����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.get�s��().get�s��R�[�h()�̖߂�l
	        if (l_orderUnit != null)
	        {
				l_context.setMarketCode(l_orderUnit.getMarket().getMarketCode());
	        }
			
	        //����J�����_�R���e�L�X�g.��t���ԋ敪 = "10�F�O������"
	        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
	        
	        //����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT 
	        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
	        
	        //����J�����_�R���e�L�X�g.������t���i = "04�F�O����"
	        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
	        
	        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
	        l_context.setOrderAcceptTransaction(null);
	        
	        // ������ԃR���e�L�X�g���Z�b�g����
	        ThreadLocalSystemAttributesRegistry.setAttribute(
	            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
	            l_context);
	    
	        //������ԊǗ�
	        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�validate�����בփ��[�g��"TRUE"���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
                BooleanEnum.TRUE);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
                l_serviceParams[3]);

	        //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            String l_strInstitutionCode = l_params.getInstitutionCode();
            String l_strBranchCode = l_params.getBranchCode();
            String l_strAccountCode = l_params.getAccountCode();
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            if (l_accountManager == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                    "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
            }
            l_accountManager.lockAccount(
            	l_strInstitutionCode,
            	l_strBranchCode,
            	l_strAccountCode);//WEB3BaseException
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �O���������o�H�敪 <BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG<BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 429FED390042
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
        
        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
        	WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        //�����בփ��[�g�`�F�b�N�t���O
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �O���������o�H�敪 <BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG<BR>
     * WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG<BR>
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 429FED39004E
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
        
        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        //�����בփ��[�g�`�F�b�N�t���O
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
