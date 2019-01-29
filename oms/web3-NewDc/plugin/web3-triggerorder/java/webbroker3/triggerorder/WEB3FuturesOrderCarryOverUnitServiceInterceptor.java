head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����J�zUnitService�C���^�Z�v�^(WEB3FuturesOrderCarryOverUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 䈋� (���u) �V�K�쐬
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����J�zUnitService�C���^�Z�v�^)<BR>
 * <BR>
 * Plugin���敨�����J�zUnitService�ɑ΂��Đݒ肷��B<BR>
 * @@author  ���Ō�
 * @@version 1.0
 */
public class WEB3FuturesOrderCarryOverUnitServiceInterceptor
    implements Interceptor
{
    /**
       * ���O�o�̓��[�e�B���e�B�B<BR>
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderCarryOverUnitServiceInterceptor.class);
    /**
     * @@roseuid 40F7A0370399
     */
    public WEB3FuturesOrderCarryOverUnitServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|����.�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e<BR>��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>�����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>�����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@�����P��.getProductId()�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �������͒����P�ʂ��ҏW�B<BR> 
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40A88BFB032F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall() ";
        log.entering(STR_METHOD_NAME);

        OrderUnit l_orderUnit = null;
        if (l_serviceParam[0] instanceof OrderUnit)
        {
            l_orderUnit = (OrderUnit)l_serviceParam[0];
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,      
                 this.getClass().getName());
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        long l_lngBranchId = l_orderUnit.getBranchId();
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_nfe)
        {          
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }

        //���X�،���ЃR�[�h
        String l_strBranchInstitutionCode = l_branch.getInstitution().getInstitutionCode();

        //���X�R�[�h
        String l_strBranchCode = l_branch.getBranchCode();

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h<BR>
        l_context.setInstitutionCode(l_strBranchInstitutionCode);
        //����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h<BR>
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
        l_context.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //���i�R�[�h
        IfoProduct l_ifoProduct = (IfoProduct)l_orderUnit.getProduct();
        
        String l_strUnderLyingProductCode =
            l_ifoProduct.getUnderlyingProductCode();

        // ����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h
        l_context.setProductCode(l_strUnderLyingProductCode);
        // ����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            //���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40A88BFB033E
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + "'onReturn(Object l_context, Object l_returnValue)";
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40A88BFB035D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onThrowable(Object l_obj, Throwable l_throwable) ";
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
