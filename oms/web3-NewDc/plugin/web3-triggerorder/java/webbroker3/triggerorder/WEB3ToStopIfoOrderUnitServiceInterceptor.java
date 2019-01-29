head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�����敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToStopIfoOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 ������(���u) �V�K�쐬
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
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
 * (�t�w�l�����敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �t�w�l�����敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUnitServiceInterceptor implements Interceptor 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopIfoOrderUnitServiceInterceptor.class);
    
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
     * �@@�@@�R�[������B <BR>
     * �@@�|���������P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �����P��.�����J�e�S����蔻�肵�ĕҏW(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �����P��.������ʂ�蔻�肵�ĕҏW(*2)<BR>
     * <BR>
     * �@@(*1)������t���i <BR>
     * �@@�@@�@@�����J�e�S�� == �i"�敨�V�K������"or"�敨�ԍϒ���"�j�̏ꍇ�́A"�敨"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�I�v�V����"�B <BR>
     * <BR>
     * �@@(*2)������t�g�����U�N�V����<BR>
     * �@@�@@�@@������� == �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
     * �@@�@@�@@������� == �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�ԍ�"�B <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam) ";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        try
        {
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
            //�T�[�r�X�̈���[0]��敨OP�����P�ʃI�u�W�F�N�g�ɃL���X�g����B 
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_serviceParams[0];
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            String l_strInstitutionCode = null; //�،���ЃR�[�h
            String l_strBranchCode  = null; //���X�R�[�h

            long l_lngBronchId = l_row.getBranchId(); 
            long l_lngProductId = l_row.getProductId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            //�敨OP�����P��.����ID�ɊY������A�ڋq�I�u�W�F�N�g���擾����B
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //���������b�N����B
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBronchId);            
            
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getProductManager();
            
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_lngProductId);
            l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            l_strBranchCode = l_branch.getBranchCode();
                        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //����J�����_�R���e�L�X�g.���X�R�[�h 
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);        
            
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �����P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h
            l_context.setProductCode(l_ifoProduct.getUnderlyingProductCode());
            
            //����J�����_�R���e�L�X�g.������t���i = �����P��.�����J�e�S����蔻�肵�ĕҏW(*1)
            //(*1)������t���i 
            //�����J�e�S�� == �i"�敨�V�K������"or"�敨�ԍϒ���"�j�̏ꍇ�́A"�敨"�B
            //��L�ȊO�̏ꍇ�́A"�I�v�V����"�B
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())
                || OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            }
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //�����P��.�����J�e�S���A������ʂ��<���肵�ĕҏW(*2)
            //(*2)������t�g�����U�N�V����
            //������� == �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            //������� == �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            //��L�ȊO�̏ꍇ�́A"�ԍ�"�B
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            else
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            }
                
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
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

        //--------------------
        //�X�L�b�vvalidate���ꎸ�������戵��~���Z�b�g����B
        //--------------------
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            BooleanEnum.TRUE);

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
     * @@param l_context
     * @@param l_returnValue
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, 
            null);        

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);
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
     * @@param l_obj
     * @@param l_throwable
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);        

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);
    }

}
@
