head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�������������ꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToStopEquityOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�t�w�l�������������ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �t�w�l�������������ꌏ�T�[�r�X�C���^�Z�v�^<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB8D000F
     */
    public WEB3ToStopEquityOrderUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     *   �|���������P��.����ID�ɊY������A�ڋq�I�u�W�F�N�g���擾����B<BR>
     *   �|���������b�N����B<BR>
     *      �g���A�J�E���g�}�l�[�W��.lock����(�ڋq.�،���ЃR�[�h, <BR>
     *      �ڋq.���X�R�[�h, �ڋq.�����R�[�h)���R�[������B<BR>
     * �@@�|���������P�ʃI�u�W�F�N�g���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     * �@@�@@�@@�����P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h =<BR>
     * �@@�@@�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����E�M�p"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �����P��.�����J�e�S����蔻�肵�ĕҏW(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �����P��.�����J�e�S���A������ʂ��<BR>
     *   ���肵�ĕҏW(*2)<BR>
     * <BR>
     * �@@(*1)������t���i <BR>
     * �@@�@@�@@�����J�e�S�� == "��������"�̏ꍇ�́A"����"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�M�p���"�B <BR>
     * <BR>
     * �@@(*2)������t�g�����U�N�V����<BR>
     * �@@�@@�@@�����J�e�S�� == "�ԍ�"�̏ꍇ�́A"�ԍ�"�B<BR>
     * �@@�@@�@@������� == �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
     * �@@�@@�@@������� == �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
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
     * @@roseuid 434C7BF602F4
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
            //�|�T�[�r�X�̈���[0]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B 
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_serviceParams[0];
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
            String l_strInstitutionCode = null; //�،���ЃR�[�h
            String l_strBranchCode  = null; //���X�R�[�h

            long l_lngBronchId = l_row.getBranchId(); 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            //���������P��.����ID�ɊY������A�ڋq�I�u�W�F�N�g���擾����B
            MainAccount l_mainAcc = l_accountMananger.getMainAccount(l_orderUnit.getAccountId());
            
            //���������b�N����B
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_lngBronchId);
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
            
            l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
            l_strBranchCode = l_branch.getBranchCode();
                        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
                
            //����J�����_�R���e�L�X�g.���X�R�[�h 
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h 
            l_context.setMarketCode(l_market.getMarketCode());        
            
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �����P��.�����J�e�S����蔻�肵�ĕҏW(*1)
            //(*1)������t���i 
            //�����J�e�S�� == "��������"�̏ꍇ�́A"����"�B 
            //��L�ȊO�̏ꍇ�́A"�M�p���"�B 
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //�����P��.�����J�e�S���A������ʂ��<���肵�ĕҏW(*2)
            //(*2)������t�g�����U�N�V����
            //�����J�e�S�� == "�ԍ�"�̏ꍇ�́A"�ԍ�"�B
            //������� == �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            //������� == �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            }
            else if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if(OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType())
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
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
     * @@roseuid 434C7BF60362
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
     * @@roseuid 434C7BF60391
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
