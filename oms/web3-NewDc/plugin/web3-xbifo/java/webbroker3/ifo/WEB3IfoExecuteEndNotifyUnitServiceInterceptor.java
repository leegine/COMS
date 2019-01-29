head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒmUnitService�C���^�Z�v�^�N���X(WEB3IfoExecuteEndNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/21 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�o���I���ʒmUnitService�C���^�Z�v�^)<BR>
 * �敨OP�o���I���ʒmUnitService�C���^�Z�v�^�N���X<BR>
 * Plugin���敨OP�o���I���ʒmUnitService�ɑ΂��Đݒ肷��B<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoExecuteEndNotifyUnitServiceInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUnitServiceInterceptor.class);

    /**
     * @@roseuid 40C0750E009C
     */
    public WEB3IfoExecuteEndNotifyUnitServiceInterceptor()
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
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     * OpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     *  �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@�����P��.getProductId()�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �������͒����P�ʂ��ҏW�B <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 4057BB0600AE
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        // �p�����[�^Null�o���Ȃ��B  
        if (l_serviceParam == null)
        {
            log.error("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ�
        //�p�����[�^�^�C�v�s��

        if (!(l_serviceParam[0] instanceof  IfoOrderUnit))
        {
            log.error("�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_web3GentradeTradingClendarContext =
            new WEB3GentradeTradingClendarContext();

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_serviceParam[0];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        Branch l_branch = null;
        WEB3IfoProductImpl l_ifoProduct = null;

        try
        {
            //throws NotFoundException             
            l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
            //�敨OP�����I�u�W�F�N�g�𐶐�����
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductId = l_ifoOrderUnitRow.getProductId();
            log.debug("l_lngProductId =" + l_lngProductId);
            l_ifoProduct =
                (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
            log.debug("l_ifoProduct =" + l_ifoProduct);

        }
        catch (NotFoundException l_nfe)
        {                    
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
        String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_branch.getBranchCode();

        l_web3GentradeTradingClendarContext.setBranchCode(l_strBranchCode);
        l_web3GentradeTradingClendarContext.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_web3GentradeTradingClendarContext.setMarketCode(
            WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�h<BR>
        l_web3GentradeTradingClendarContext.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //����J�����_�R���e�L�X�g.������t���i = null
        l_web3GentradeTradingClendarContext.setOrderAcceptProduct(null);
        //���������敨OP����.get�����Y�����R�[�h()�B

        //      �敨OP�����}�l�[�W�����擾����          
        String l_underlyingProductCode =
            l_ifoProduct.getUnderlyingProductCode();
        l_web3GentradeTradingClendarContext.setProductCode(
            l_underlyingProductCode);
        //������t���i
        l_web3GentradeTradingClendarContext.setOrderAcceptProduct(null);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_web3GentradeTradingClendarContext.setOrderAcceptTransaction(null);
        //�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_web3GentradeTradingClendarContext);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_wsle)
        { 
            log.error(STR_METHOD_NAME,l_wsle);                   
            throw new WEB3BaseRuntimeException(
                l_wsle.getErrorInfo(), STR_METHOD_NAME);
        }

        return l_web3GentradeTradingClendarContext;
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4057BB0600BE
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".onReturn(Object l_context, Object l_returnValue)";
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
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 4057BB0600DD
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
