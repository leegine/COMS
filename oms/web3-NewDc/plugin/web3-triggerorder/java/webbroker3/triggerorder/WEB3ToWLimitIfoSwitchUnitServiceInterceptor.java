head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����敨OP�ؑֈꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToWLimitIfoSwitchUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 ������(���u) �V�K�쐬
Revesion History : 2007/1/31 �����Q(���u) ���f��No.217 ����
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * (W�w�l�����敨OP�ؑֈꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * W�w�l�����敨OP�ؑֈꌏ�T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToWLimitIfoSwitchUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitIfoSwitchUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 44E90CEB0138
     */
    public WEB3ToWLimitIfoSwitchUnitServiceInterceptor() 
    {
             
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]��敨OP�����P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|�敨OP�����P��.����ID�ɊY������A�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�|���������b�N����B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�ڋq.�،���ЃR�[�h, �ڋq.���X�R�[�h, �ڋq.�����R�[�h)��<BR>
     * �@@�@@  �R�[������B <BR>
     * �@@�|�敨OP�����P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = <BR>
     * �@@�@@�@@�����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = <BR>
     * �@@�@@�@@�����P��.�����J�e�S����蔻�肵�ĕҏW(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "����"<BR>
     * <BR>
     * �@@(*1)������t���i <BR>
     * �@@�@@�@@�����J�e�S�� == �i"�敨�V�K������"or"�敨�ԍϒ���"�j�̏ꍇ�́A"�敨"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�I�v�V����"�B <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@�X�L�b�vvalidate���ꎷ�s�����戵��~���Z�b�g����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�<BR>
     * �@@�X�L�b�vvalidate���ꎷ�s�����戵��~��"TRUE"���Z�b�g����B<BR>
     * �@@�@@-ThreadLocalSystemAttributesRegistry.setAttribute(<BR>
     * �@@�@@�@@�@@WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,<BR>
     * �@@�@@�@@�@@BooleanEnum.TRUE)<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 44924515035A
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.error("�p�����[�^�l�s���B");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        if (! (l_serviceParam[0] instanceof IfoOrderUnit))
        {
            log.error("�p�����[�^�̌^�s���B");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        try
        {
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  
            //  �|�T�[�r�X�̈���[0]��敨OP�����P�ʃI�u�W�F�N�g�ɃL���X�g����B
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_serviceParam[0];
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();       

            long l_lngBranchId = l_row.getBranchId(); 
            long l_lngProductId = l_row.getProductId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //  �|�敨OP�����P��.����ID�ɊY������A�ڋq�I�u�W�F�N�g���擾����B
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //  �|���������b�N����B  
            //    �g���A�J�E���g�}�l�[�W��.lock����(�ڋq.�،���ЃR�[�h, �ڋq.���X�R�[�h, �ڋq.�����R�[�h)�� 
            //    �R�[������B  
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            //  �|�敨OP�����P�ʃI�u�W�F�N�g���A
            //       ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBranchId);
            
            WEB3IfoProductManagerImpl l_ifoProductManagetImpl = 
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getProductManager();
            
            WEB3IfoProductImpl l_product = 
                (WEB3IfoProductImpl)l_ifoProductManagetImpl.getProduct(l_lngProductId);
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            //���X�R�[�h
            String l_strBranchCode = l_branch.getBranchCode();

            //  ����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
            //      �����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //  ����J�����_�R���e�L�X�g.���X�R�[�h = 
            //      �����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h 
            l_context.setBranchCode(l_strBranchCode);
            
            //  ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //  ����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //  ����J�����_�R���e�L�X�g.�����R�[�h = �����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h 
            l_context.setProductCode(l_product.getUnderlyingProductCode());
            
            //  ����J�����_�R���e�L�X�g.������t���i = �����P��.�����J�e�S����蔻�肵�ĕҏW(*1) 
            //  (*1)������t���i  
            //      �����J�e�S�� == �i"�敨�V�K������"or"�敨�ԍϒ���"�j�̏ꍇ�́A"�敨"�B  
            //      ��L�ȊO�̏ꍇ�́A"�I�v�V����"�B 
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
               || OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            }
            
            //  ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "����" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //
            //  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
            //    �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            //�Q�j�@@�X�L�b�vvalidate���ꎷ�s�����戵��~���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
                BooleanEnum.TRUE);
            
            //�R�j�@@��t�����A���t���[�����Z�b�g����B  
            //  �|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
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
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 449245150379
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NANE = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NANE);
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NANE);              
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
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 449245150399
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
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
