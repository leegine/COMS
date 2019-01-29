head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���������������ꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToSuccEquityOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ������(���u) �V�K�쐬
Revesion History : 2008/05/05 ���z(���u) �d�l�ύX���f��No.314
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;


/**
 * (�A���������������ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �A���������������ꌏ�T�[�r�X�C���^�Z�v�^�B
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityOrderUnitServiceInterceptor implements Interceptor 
{
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccEquityOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 4348DACE037A
     */
    public WEB3ToSuccEquityOrderUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�������\�񒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|�����\�񒍕��P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�����\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     * �@@�@@�@@�����\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h =<BR>
     * �@@�@@�@@�����\�񒍕��P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *   �����P��.�����J�e�S����蔻�肵�ĕҏW(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = <BR>
     *   �����P��.�����J�e�S����蔻�肵�ĕҏW(*2)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *   �����P��.�����J�e�S���A������ʂ�蔻�肵�ĕҏW(*3)<BR>
     * <BR>
     * �@@(*1)��t���ԋ敪<BR>
     * �@@�@@�@@�����J�e�S�� == "�������n"�̏ꍇ�́A"�������n"�B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�����E�M�p"<BR>
     * <BR>
     * �@@(*2)������t���i <BR>
     * �@@�@@�@@�����J�e�S�� == "��������"�̏ꍇ�́A"����"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�M�p���"�B <BR>
     * <BR>
     * �@@(*3)������t�g�����U�N�V����<BR>
     * �@@�@@�@@�����J�e�S�� == "�ԍ�"�̏ꍇ�́A"�ԍ�"�B<BR>
     * �@@�@@�@@�����J�e�S�� == "�������n"�̏ꍇ�́A"�������n"�B<BR>
     * �@@�@@�@@������� == 
     * �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
     * �@@�@@�@@������� == 
     * �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *       �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 432183C903C7
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);  
        
        if (l_serviceParams[0] == null)
        {   
            log.error("�p�����[�^�l�s���B");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }

        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
        //�|�T�[�r�X�̈���[0]�������\�񒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        if (l_serviceParams[0] instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_orderUnit =
                (WEB3ToSuccEqTypeOrderUnitImpl) l_serviceParams[0];
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //���X���擾
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvEqOrderUnitRow.getBranchId());

            //�s����擾
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_rsvEqOrderUnitRow.getMarketId());
    
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
            //  �����\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
            l_context.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            //����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
            //  �����\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
            l_context.setBranchCode(l_branch.getBranchCode());

            //����J�����_�R���e�L�X�g.�s��R�[�h =<BR>
            //  �����\�񒍕��P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h<BR>
            l_context.setMarketCode(l_market.getMarketCode());
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
            //�����P��.�����J�e�S����蔻�肵�ĕҏW(*1)<BR>
            // �@@(*1)��t���ԋ敪<BR>
            // �@@�@@�@@�����J�e�S�� == "�������n"�̏ꍇ�́A"�������n"�B<BR>
            // �@@�@@�@@��L�ȊO�̏ꍇ�́A"�����E�M�p"<BR>
            String l_strTradingTimeType = null;
            if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SWAP;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            }

            l_context.setTradingTimeType(l_strTradingTimeType);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = "DEFAULT"<BR>
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = <BR>
            //�����P��.�����J�e�S����蔻�肵�ĕҏW(*2)<BR>
            // �@@(*2)������t���i <BR>
            // �@@�@@�@@�����J�e�S�� == "��������"�̏ꍇ�́A"����"�B <BR>
            // �@@�@@�@@��L�ȊO�̏ꍇ�́A"�M�p���"�B <BR>
            String l_strOrderAcceptProduct = null;
            if (OrderCategEnum.ASSET.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.MARGIN;
            }

            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
            //�����P��.�����J�e�S���A������ʂ�蔻�肵�ĕҏW(*3)<BR>
            // �@@(*3)������t�g�����U�N�V����<BR>
            // �@@�@@�@@�����J�e�S�� == "�ԍ�"�̏ꍇ�́A"�ԍ�"�B<BR>
            // �@@�@@�@@�����J�e�S�� == "�������n"�̏ꍇ�́A"�������n"�B<BR>
            // �@@�@@�@@������� == 
            // �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
            // �@@�@@�@@������� == 
            // �i"����������"or"�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B<BR>
            String l_strOrderAcceptTransaction = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN;
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnitRow.getOrderCateg()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.SWAP_MARGIN;
            }
            else if (OrderTypeEnum.EQUITY_BUY.equals(l_rsvEqOrderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_LONG.equals(l_rsvEqOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_rsvEqOrderUnitRow.getOrderType())
                || OrderTypeEnum.MARGIN_SHORT.equals(l_rsvEqOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }

            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in getAccountManager", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);    
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
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 432183C903CA
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����

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
     * @@roseuid 432183C903D6
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����

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
