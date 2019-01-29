head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  (�M�p��������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
                 :  �M�p��������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�N���X
                 :  (WEB3MarginOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ������ (���u) �V�K�쐬
                   2004/12/16 ���� (SRA) �c�Č��Ή��̂��ߏC��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyDataAdapter;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p��������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyUnitServiceInterceptor implements Interceptor 
{ 
    /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOrderNotifyUnitServiceInterceptor.class);
                
    /**
     * @@roseuid 4142B32E01F5
     */
    public WEB3MarginOrderNotifyUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A[�M�p��������ʒm�ꌏ�T�[�r�X]���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��M�p����������͒ʒm�f�[�^�A�_�v�^�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|�M�p����������͒ʒm�f�[�^�A�_�v�^.�����������͒ʒm�L���[Params��<BR>
     *        ���e���<BR>
     * �@@�@@����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�@@���@@<BR>
     *                  �����������͒ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@�@@�@@���@@<BR>
     *                  �����������͒ʒm�L���[Params.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�@@�@@�@@���@@<BR>
     *                  �M�p����������͒ʒm�f�[�^�A�_�v�^.get�s��R�[�h()<BR>
     * �@@�@@�@@�@@�@@�@@�@@��t���ԋ敪    ���@@"01�F�����E�M�p"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@���@@"0�FDEFAULT"<BR>
     * �@@�@@�@@�@@�@@�@@�@@������t���i�@@  ���@@"03�F�M�p���"<BR>
     * �@@�@@�@@�@@�@@�@@�@@������t�g�����U�N�V�����@@���@@(*)<BR>
     * <BR>
     * �@@�@@(*)������t�g�����U�N�V����<BR>
     * �@@�@@�@@[�M�p����������͒ʒm�f�[�^�A�_�v�^.is�V�K������ == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@[a.�M�p����������͒ʒm�f�[�^�A�_�v�^.is���� == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@"01�F���t"���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@[a.�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@"02�F���t"���Z�b�g�B<BR>
     * �@@�@@�@@[�M�p����������͒ʒm�f�[�^�A�_�v�^.is�ԍϒ��� == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@"03�F�ԍ�"���Z�b�g�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�������Z�b�g����B<BR>
     * <BR>
     * �Q�|�P�j�@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�|�Q�j�@@��t�����Ƃ��āASONAR���ł̔�������(����������<BR>
     * (biz_datetime))���g�p����ׁA<BR>
     * �@@�@@�@@�@@�@@������ԊǗ�.setTimestamp()<BR>
     *              �ɂĐݒ肳�ꂽ��t�������㏑������B�i�����@@���Q�Ɓj<BR>
     * <BR>
     * �@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA<BR>
     * �@@�@@�@@�@@�@@�����������͒ʒm�L���[Params.�������� ���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * <BR>
     * �� ���� ��<BR>
     * SONAR�������̂����󒍓���(create_datetime)��<BR>
     * �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A<BR>
     * ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B<BR>
     * �������ASONAR�������̂�����������(biz_datetime)��<BR>
     * SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A<BR>
     * YYYYMMDD�ɂ͓������t���A<BR>
     * HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A<BR>
     * ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)��<BR>
     * ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �������͊����������͒ʒm�L���[Params���ҏW�B <BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 4057B1EE010C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {  
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);    
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        if (!(l_serviceParams[0] instanceof WEB3MarginOrderNotifyDataAdapter))
        {
            log.error("�p�����[�^�^�C�v�s���B",
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME));
            return null;
        } 
        WEB3MarginOrderNotifyDataAdapter l_adapter = (WEB3MarginOrderNotifyDataAdapter)l_serviceParams[0];
        HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.getDataSourseObject();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
      
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext(); 
        // * �@@�|�M�p����������͒ʒm�f�[�^�A�_�v�^.�����������͒ʒm�L���[Params��<BR>
        // *   ���e���<BR>
        // * �@@����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
        // * �@@�،���ЃR�[�h�@@���@@<BR>
        // *   �����������͒ʒm�L���[Params.�،���ЃR�[�h<BR>
        // * �@@���X�R�[�h�@@�@@�@@���@@<BR>
        // *   �����������͒ʒm�L���[Params.���X�R�[�h<BR>
        // * �@@�s��R�[�h�@@�@@�@@���@@<BR>
        // *   �M�p����������͒ʒm�f�[�^�A�_�v�^.get�s��R�[�h()<BR>
        // * �@@��t���ԋ敪    ���@@"01�F�����E�M�p"<BR>
        // * �@@�����R�[�h�@@�@@�@@���@@"0�FDEFAULT"<BR>
        // * �@@������t���i�@@  ���@@"03�F�M�p���"<BR>
        // * �@@������t�g�����U�N�V�����@@���@@(*)<BR>  
        l_context.setInstitutionCode(l_orderReceiptParams.getInstitutionCode());
        l_context.setBranchCode(l_orderReceiptParams.getBranchCode());
        try
        {
            l_context.setMarketCode(l_adapter.getMarketCode());
        }
        catch (WEB3BaseException l_exp)
        {
            throw new WEB3BaseRuntimeException(
                l_exp.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        //* �@@(*)������t�g�����U�N�V����<BR>
        //* �@@[�M�p����������͒ʒm�f�[�^�A�_�v�^.is�V�K������ == true�̏ꍇ]<BR>
        //* �@@[a.�M�p����������͒ʒm�f�[�^�A�_�v�^.is���� == true�̏ꍇ]<BR>
        //* �@@"01�F���t"���Z�b�g�B<BR>
        //* �@@[a.�ȊO�̏ꍇ]<BR>
        //* �@@"02�F���t"���Z�b�g�B<BR>
        //* �@@�M�p����������͒ʒm�f�[�^�A�_�v�^.is�ԍϒ��� == true�̏ꍇ]<BR>
        //* �@@"03�F�ԍ�"���Z�b�g�B<BR>
        String l_transaction = null;

        if (l_adapter.isOpenMarginOrder())
        {
            if (l_adapter.isLong())
            {
                l_transaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else
            {
                l_transaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }
        if (l_adapter.isCloseMarginOrder())
        {
            l_transaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN; 
        }
        l_context.setOrderAcceptTransaction(l_transaction);

        //* �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
        //* ����J�����_�R���e�L�X�g���Z�b�g����B<BR>
        //* �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
        //* <BR>
        //* �Q�j�@@��t�������Z�b�g����B<BR>
        //* <BR>
        //* �Q�|�P�j�@@������ԊǗ�.setTimestamp()���R�[������B<BR>
        //* <BR>
        //* �Q�|�Q�j�@@��t�����Ƃ��āASONAR���ł̔�������(����������<BR>
        //* (biz_datetime))���g�p����ׁA<BR>
        //* ������ԊǗ�.setTimestamp()<BR>
        //* �ɂĐݒ肳�ꂽ��t�������㏑������B�i�����@@���Q�Ɓj<BR>
        //* <BR>
        //* ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA<BR>
        //* �����������͒ʒm�L���[Params.�������� ���Z�b�g����B<BR>
        //* �ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG<BR>
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderReceiptParams.biz_datetime);   
			//l_orderReceiptParams.biz_datetime);      
        }
        catch (WEB3BaseException l_wbex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_wbex);
            throw new WEB3BaseRuntimeException(
                l_wbex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbex.getMessage(),
                l_wbex);  
        }

        //* �� ���� ��<BR>
        //* SONAR�������̂����󒍓���(create_datetime)��<BR>
        //* �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A<BR>
        //* ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B<BR>
        //* �������ASONAR�������̂�����������(biz_datetime)��<BR>
        //* SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A<BR>
        //* YYYYMMDD�ɂ͓������t���A<BR>
        //* HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A<BR>
        //* ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)��<BR>
        //* ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>    
        
        // �R�j�@@���������b�N����B
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_orderReceiptParams.getInstitutionCode(),
                l_orderReceiptParams.getBranchCode(),
                l_orderReceiptParams.getAccountCode());
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
                
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 4057B1EE011B
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {        
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4125A8CD01D7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
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
