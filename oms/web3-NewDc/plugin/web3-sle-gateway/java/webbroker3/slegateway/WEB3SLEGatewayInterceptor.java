head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SLEGatewayInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3BaseSleRepliesCallback�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/09/20  ��(FLJ) �V�K�쐬
 */

package webbroker3.slegateway;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor; 
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.slebase.data.SleSendQRow;


/**
 * SLE���M�T�[�r�X�̃C���^�Z�v�^
 */
public class WEB3SLEGatewayInterceptor implements Interceptor{
	
	/**
	  * ���O�o�̓��[�e�B���e�B�B
	  */
	 private static WEB3LogUtility log =
			 WEB3LogUtility.getInstance(WEB3SLEGatewayInterceptor.class);

	 /**
	  * �R���X�g���N�^
	  */
	 public WEB3SLEGatewayInterceptor() {}
	 
	 
	/**
	 * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
	 �T�[�r�X���\�b�h�J�n���ɃR�[�������B 
	 ����J�����_�����p����R���e�L�X�g�𐶐�����B 
	�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j 

	�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
�@@		  ����J�����_�R���e�L�X�g.�،���ЃR�[�h = ����.send_q�̍s�I�u�W�F�N�g.get�،���ЃR�[�h() 
�@@		  ����J�����_�R���e�L�X�g.���X�R�[�h = ����.send_q�̍s�I�u�W�F�N�g.get���X�R�[�h() 
�@@		  ����J�����_�R���e�L�X�g.�s��R�[�h = ����.send_q�̍s�I�u�W�F�N�g.get�s��R�[�h()
  		  ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h34�F�O������SLE���M�h 
�@@		  ����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT 
�@@		  ����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h 
�@@		  ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null 
          ����t���ԋ敪�̓T�[�r�X���ɂăZ�b�g����B 
		  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� 
�@@�@@�@@		������ԃR���e�L�X�g���Z�b�g����B 
			�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
	�Q�j�@@��t�����A���t���[�����Z�b�g����B 
�@@       �|������ԊǗ�.setTimestamp()���R�[������B

	 * @@param l_method - (�T�[�r�X���\�b�h)<BR>
	 * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
	 * 
	 * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
	 * �T�[�r�X�̈����z��<BR>
	 * @@return Object
	 * @@roseuid 4295FFDD01AC
	 */
	public Object onCall(Method l_method, Object[] l_serviceParams) 
	{
		final String STR_METHOD_NAME = " onCall(Method, Object[])";
		log.entering(STR_METHOD_NAME);

		long l_lngExecId = 0;
		
		SleSendQRow l_sendqRow = (SleSendQRow) l_serviceParams[0];

		try
		{
	
			//�،���ЃR�[�h
			String l_strInstitutionCode = l_sendqRow.getInstitutionCode();
			//���X�R�[�h
			String l_strBranchCode = l_sendqRow.getBranchCode();
			//�s��R�[�h�@@
            String l_strMarketCode = l_sendqRow.getMarketCode();
            
			WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
			//�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B        
			//����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h
			l_context.setInstitutionCode(l_strInstitutionCode);
            
			//����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h
			l_context.setBranchCode(l_strBranchCode);
            
			//����J�����_�R���e�L�X�g.�s��R�[�h = null�h
//			l_context.setMarketCode(null);
            l_context.setMarketCode(l_strMarketCode);
            
			//����J�����_�R���e�L�X�g.��t���ԋ敪 = �h34�F�O������SLE���M�h??WEB3TradingTimeTypeDef�N���X�ɖ���`
//			l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            l_context.setTradingTimeType("34");
            
			//����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
			l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
			//����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h
			l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
				 
			l_context.setOrderAcceptTransaction(null);
            
        
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
