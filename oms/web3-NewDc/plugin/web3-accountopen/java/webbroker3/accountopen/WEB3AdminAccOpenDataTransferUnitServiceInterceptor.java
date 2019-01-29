head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService�C���^�Z�v�^(WEB3AdminAccOpenDataTransferUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/26 ��іQ (���u) �V�K�쐬 �d�l�ύX�Ǘ��䒠���f��No.190
Revision History : 2009/08/31 ���g(���u) ���f�� 198
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.define.WEB3AdminAccountOpenAccTransferDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService�C���^�Z�v�^)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService�C���^�Z�v�^<BR>
 *   
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminAccOpenDataTransferUnitServiceInterceptor implements Interceptor {

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferUnitServiceInterceptor.class);

    /**
     * @@roseuid 41B5AB7E0148
     */
    public WEB3AdminAccOpenDataTransferUnitServiceInterceptor()
    {

    }

    /**
     * �Ǘ��Ҍ����J�݃f�[�^�ڊ�Unit�T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �@@�@@����.arg1[0]��������J�݌����q�̏،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * �@@�@@����.arg1[0]��������J�݌����q�̕��X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h<BR> 
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
�@@   * �|������ԊǗ�.setTimestamp()���R�[������B<BR> 
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂČ����J�݃f�[�^�ڊǂ̊��ϐ����Z�b�g����B<BR> 
     * [����]<BR>
     * �@@arg0�F�@@"web3.adminAccountOpenAccTransfer"<BR> 
     * �@@arg1�F�@@Boolean(true)<BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 41871ABA0235
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�،���ЃR�[�h
            String l_strInstitutionCode = null;     
            
            // ���X�R�[�h
            String l_strBranchCode = null;   

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            if (l_serviceParam != null && l_serviceParam.length > 0
                && l_serviceParam[0] instanceof WEB3AccOpenExpAccountOpen)
            {
                //����J�����_�R���e�L�X�g.�،���ЃR�[�h = ����.arg1[0]��������J�݌����q�̏،���ЃR�[�h
                l_strInstitutionCode =
                    ((WEB3AccOpenExpAccountOpen)l_serviceParam[0]).getInstitutionCode();

                //����J�����_�R���e�L�X�g.���X�R�[�h = ����.arg1[0]��������J�݌����q�̕��X�R�[�h
                l_strBranchCode =
                    ((WEB3AccOpenExpAccountOpen)l_serviceParam[0]).getBranchCode();
            }
            else
            {
                log.debug("�p�����[�^�^�C�v�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�p�����[�^�^�C�v�s���B");
            }

            l_context.setInstitutionCode(l_strInstitutionCode);

            l_context.setBranchCode(l_strBranchCode);
    
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //�T�[�r�X�̈���[0]�̌^���u�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g�v�̏ꍇ�̂݁A
            //�����J�݃f�[�^�ڊǂ̊��ϐ����Z�b�g����B
            //[����]
            //�@@arg0�F�@@"web3.adminAccountOpenAccTransfer"
            //�@@arg1�F�@@Boolean(true)

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
                new Boolean(true));

            log.exiting(STR_METHOD_NAME);
            return l_context; 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@"web3.adminAccountOpenAccTransfer"<BR>
     * <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 41871ABA0238
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //arg0�F�@@"web3.adminAccountOpenAccTransfer"
        //arg1�F�@@null
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@"web3.adminAccountOpenAccTransfer"<BR>
     * <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 41871ABA0245
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //�����J�݃f�[�^�ڊǂ̊��ϐ����Z�b�g����B
        //arg0�F�@@"web3.adminAccountOpenAccTransfer"
        //arg1�F�@@null
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER,
            null);

        log.exiting(STR_METHOD_NAME);   
    }
}
@
