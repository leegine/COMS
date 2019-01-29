head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3MarginSwapOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyDataAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n�����ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyUnitServiceInterceptor implements Interceptor 
{ 
    /**
    * (���O���[�e�B���e�B)�B<BR>
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyUnitServiceInterceptor.class);


    /**
     * (�R���X�g���N�^)�B
     */
    public WEB3MarginSwapOrderNotifyUnitServiceInterceptor() 
    {
    }


    /**
     * �ionCall�j�B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����A[�M�p����������n�����ʒm�ꌏ�T�[�r�X]���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]��M�p����������n���͒ʒm�f�[�^�A�_�v�^�ɃL���X�g����B <BR>
     * <BR>
     * �@@�|�M�p����������n���͒ʒm�f�[�^�A�_�v�^.�������n���͒ʒm�L���[Params�̓��e��� <BR>
     * �@@�@@����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h���@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@�@@���@@�������n���͒ʒm�L���[Params.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�@@�@@���@@�M�p����������n���͒ʒm�f�[�^�A�_�v�^<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@.get�s��().getMarketCode() <BR>
     * �@@�@@�@@�@@�@@�@@�@@��t���ԋ敪  ���@@"19�F�����E���n" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@���@@"0�FDEFAULT" <BR>
     * �@@�@@�@@�@@�@@�@@�@@������t���i�@@���@@"03�F�M�p���" <BR>
     * �@@�@@�@@�@@�@@�@@�@@������t�g�����U�N�V�����@@���@@"04�F�����E���n" <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g���Z�b�g����B <BR>
     * �@@�@@�@@�ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�������Z�b�g����B <BR>
     * <BR>
     * �Q�|�P�j�@@������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     * �@@�@@�@@�������͌������n���͒ʒm�L���[Params���ҏW�B
     * @@param l_method �i�T�[�r�X���\�b�h�j<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams �T�[�r�X���\�b�h����
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {  
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        //--------------------
        //�T�[�r�X���\�b�h�������������n���͒ʒm�f�[�^�A�_�v�^�ɃL���X�g����B
        //--------------------
        if (!(l_serviceParams[0] instanceof WEB3MarginSwapOrderNotifyDataAdapter))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MarginSwapOrderNotifyDataAdapter l_adapter
            = (WEB3MarginSwapOrderNotifyDataAdapter) l_serviceParams[0];
        HostEqtypeSwapReceiptParams l_swapReceiptParams = l_adapter.getDataSourseObject();


        //--------------------
        //�������n���͒ʒm�f�[�^�A�_�v�^����l���擾����B
        //--------------------
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        String      l_strInstitutionCode = null;    // �،���ЃR�[�h
        String      l_strBranchCode = null;         // ���X�R�[�h
        String      l_strMarketCode = null;         // �s��R�[�h
        String      l_strAccountCode = null;        // �����R�[�h

        l_strInstitutionCode = l_swapReceiptParams.institution_code;
        l_strBranchCode = l_swapReceiptParams.branch_code;
        try
        {
            l_strMarketCode = l_adapter.getMarket().getMarketCode();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }
        l_strAccountCode = l_swapReceiptParams.getAccountCode();
        
        
        //--------------------
        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //--------------------
        WEB3GentradeTradingClendarContext l_context
            = new WEB3GentradeTradingClendarContext();
            
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        l_context.setMarketCode(l_strMarketCode);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);


        //--------------------
        //��t�������Z�b�g����B
        //--------------------
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }


        //--------------------
        //���������b�N����
        //--------------------
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.error(STR_METHOD_NAME, l_be);
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be.getMessage(), l_be);
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }


    /**
     * �ionReturn�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context onCall���^�[���l<BR>
     * @@param l_returnValue �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {        
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ionThrowable�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[������� <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj onCall���^�[���l<BR>
     * @@param l_throwable ��O�I�u�W�F�N�g<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
