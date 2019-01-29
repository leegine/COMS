head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒmUnitService�C���^�Z�v�^(WEB3FuturesOrderExecNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�o���ʒmUnitService�C���^�Z�v�^)<BR>
 * <BR>
 * Plugin���ɐ敨�o���ʒmUnitService�ɑ΂��Đݒ肷��B<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyUnitServiceInterceptor
    implements Interceptor
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderExecNotifyUnitServiceInterceptor.class);

    /**
     * @@roseuid 40F7A0370242
     */
    public WEB3FuturesOrderExecNotifyUnitServiceInterceptor()
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
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
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
     * <BR>
     * �R�j�@@�����ԗp�^�C���X�^���v�Ɏ��ۂ̎��Ԃ��Z�b�g����B <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@TIMESTAMP_TAG�ɐݒ肳��Ă�����̂Ɠ����������Z�b�g����B<BR>
     * �@@�@@��REAL_TIMESTAMP���g�p��������͂܂��c���Ă���ׁA<BR>
     * �@@�@@�@@�f�O���[�h�𔭐������Ȃ��悤�Z�b�g����B<BR>
     * �@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�C�X.REAL_TIMESTAMP<BR>
     * <BR>
     * �S�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B <BR>
     * �@@�|������ԊǗ�.set�������v�Z�p�����( )�ɂ�<BR>
     * �@@�@@�@@�������v�Z�̊�����ɁA�����P��.�������{HHMMSS�Ƃ���"000000"���Z�b�g����B <BR>
     * <BR>
     * �T�j�@@���������b�N����B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     *      �������͒����P�ʂ��ҏW�B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40A8438C0215
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if ((l_serviceParam == null))
        {
            log.error("l_serviceParam is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if ((!(l_serviceParam[0] instanceof OrderUnit))
            || (!(l_serviceParam[1] instanceof Timestamp)))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        OrderUnit l_orderUnit = (OrderUnit)l_serviceParam[0];
        WEB3GentradeTradingClendarContext l_cradingClendarContext =
            new WEB3GentradeTradingClendarContext();

        //�،���Е��X�h�c
        long l_lngBranchId = l_orderUnit.getBranchId();

        Branch l_branch = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        MainAccount l_mainAccount = null;
        WEB3GentradeAccountManager l_accountManager = null;
        String l_strBranchInstitutionCode = null;
        String l_strBranchCode = null;
        try
        {
            // throw NotFoundException
            l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId);

            //get product manager
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();

            //���X�،���ЃR�[�h
            l_strBranchInstitutionCode =
                l_branch.getInstitution().getInstitutionCode();
            l_cradingClendarContext.setInstitutionCode(
                l_strBranchInstitutionCode);

            //���X�R�[�h
            l_strBranchCode = l_branch.getBranchCode();
            l_cradingClendarContext.setBranchCode(l_strBranchCode);

            //�s��R�[�h "0�FDEFAULT"
            l_cradingClendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //��t���ԋ敪 "12�F�����w���敨OP"
            l_cradingClendarContext.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //���i�R�[�h
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            Product l_product = null;
            // throw NotFoundException
            l_product = l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();
            String l_strUnderlyingProductCode = l_productRow.getUnderlyingProductCode();
            l_cradingClendarContext.setProductCode(l_strUnderlyingProductCode);

            //������t���i
            l_cradingClendarContext.setOrderAcceptProduct(
                WEB3OrderAccProductDef.FUTURE);
            //������t�g�����U�N�V����
            l_cradingClendarContext.setOrderAcceptTransaction(null);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_cradingClendarContext);

            //���t���[�����Z�b�g����B
             WEB3GentradeTradingTimeManagement.setTimestamp();

            //�����Ԃ��Z�b�g����B
            Timestamp l_tm = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3IfoAttributeNameDef.REAL_TIMESTAMP,
                l_tm);
                
            //�����P�ʂ��甭�������擾����B
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_bizDate = 
                WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
                
            //������ԊǗ�.set�������v�Z�p�����()�̃R�[�� 
            WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
                new Timestamp(l_bizDate.getTime()));
                
            // �S�j�@@���������b�N����B<BR> 
            // �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
            // �������͒����P�ʂ��ҏW�B<BR>
            l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            long l_lngAccountId = l_orderUnit.getAccountId();
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (WEB3SystemLayerException l_systemLayerException)
        {
            log.error(l_systemLayerException.getMessage(),l_systemLayerException);
            throw new WEB3BaseRuntimeException(
                l_systemLayerException.getErrorInfo(),
                this.getClass().getName() + "onCall");
        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(),l_notFoundException);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "onCall");
        }
        
        try
        {
            l_accountManager.lockAccount(
                l_strBranchInstitutionCode,
                l_strBranchCode,
                l_mainAccount.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40A8438C0235
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40A8438C0244
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
