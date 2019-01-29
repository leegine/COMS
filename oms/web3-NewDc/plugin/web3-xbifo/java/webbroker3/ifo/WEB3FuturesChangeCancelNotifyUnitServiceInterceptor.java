head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒmUnitService�C���^�Z�v�^(WEB3FuturesChangeCancelNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2007/04/24 �Ј���(���u) �d�l�ύX���f��No.637
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

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
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨��������ʒmUnitService�C���^�Z�v�^)<BR>
 * <BR>
 * Plugin���ɐ敨��������ʒmUnitService�ɑ΂��Đݒ肷��B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyUnitServiceInterceptor.class);
    /**
     * @@roseuid 40F7A0380232
     */
    public WEB3FuturesChangeCancelNotifyUnitServiceInterceptor() 
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
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�i��������j�h<BR>
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
     * �Q�j���t���[�����Z�b�g����B <BR>
     *�@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     *<BR>
     * �R�j��t�����Ƃ��āA�����P��.�쐬���� ���g�p����ׁi�������̎���������擾���邽�߁j�A <BR>
     *�@@�@@������ԊǗ�.setTimestamp()�ɂĐݒ肳�ꂽ��t�������㏑������B <BR>
     *<BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA�����P��.�쐬���� ���Z�b�g����B <BR>
     *�@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * 4�j�@@���������b�N����B <BR>
     *�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �������͒����P�ʂ��ҏW�B <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40A8A0B10052
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
                  "onCall(Method l_method, Object[] l_serviceParam) ";
              log.entering(STR_METHOD_NAME);                    
        if(l_serviceParam == null)
        {
            log.error("onCall",new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "onCall" ));
            return null;
            
        }
        if(l_serviceParam.length == 0)
        {         
            log.error("onCall", new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "onCall"));
            return null;    
        }
        
        OrderUnit l_orderUnit = (OrderUnit)l_serviceParam[0];
        
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        //�،���Е��X�h�c
        long l_lngBranchId = l_orderUnit.getBranchId();
        Branch l_branch = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //get product manager
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productMgr =
            (WEB3IfoProductManagerImpl) l_tradingMod.getProductManager();

        //���i�R�[�h
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        Product l_product = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_lngBranchId);
            l_product = l_productMgr.getProduct(l_lngProductId);

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);         
        }
        //���X�،���ЃR�[�h
        String l_strBranchInstitutionCode = l_branch.getInstitution().getInstitutionCode();
        
        l_context.setInstitutionCode(l_strBranchInstitutionCode);
        
        //���X�R�[�h
        String l_strBranchCode = l_branch.getBranchCode();
        l_context.setBranchCode(l_strBranchCode);
        
        //�s��R�[�h "0�FDEFAULT"
        l_context.setMarketCode
            (WEB3MarketCodeDef.DEFAULT);
        //��t���ԋ敪 "12�F�����w���敨OP�i�������)"
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);
               
        IfoProduct l_ifoProduct = (IfoProduct)l_product;
        String l_strUnderLyingProductCode = l_ifoProduct.getUnderlyingProductCode();
        l_context.setProductCode(l_strUnderLyingProductCode);
        //������t���i
        l_context.setOrderAcceptProduct(
            WEB3OrderAccProductDef.FUTURE);
        //������t�g�����U�N�V����
        l_context.setOrderAcceptTransaction(null);
        //
        ThreadLocalSystemAttributesRegistry.
            setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

       ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //��t�����A���t���[�����Z�b�g����
        
        String l_strAccountCode = null;
        try 
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            ThreadLocalSystemAttributesRegistry.setAttribute
                (WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderUnitRow.getCreatedTimestamp());  
            long l_lngAccountId = l_orderUnit.getAccountId();
            l_strAccountCode = l_accountManager.getMainAccount(l_lngAccountId).getAccountCode();
        } 
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);         
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);        
        } 

        log.exiting(STR_METHOD_NAME);
        return l_context; 
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
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40A8A0B10071
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String l_strMethodName = "onReturn()";
        log.entering(l_strMethodName); 
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);       
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(l_strMethodName);
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
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40A8A0B10091
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String l_strMethodName = "onThrowable()";
        log.entering(l_strMethodName);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(l_strMethodName);     
    }
}
@
