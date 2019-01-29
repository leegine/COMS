head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToSuccIfoOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/05/06 ���z(���u) �V�K�쐬���f��No.311,341
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�������敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �A�������敨OP�����ꌏ�T�[�r�X�C���^�Z�v�^�B<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 481EA5360027
     */
    public WEB3ToSuccIfoOrderUnitServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]��敨OP�\�񒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|�敨OP�\�񒍕��P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP" <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h =<BR>
     * �@@�@@�敨OP�\�񒍕��P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i =<BR>
     * �@@�@@�敨OP�\�񒍕��P��.�敨�^�I�v�V�����敪��蔻�肵�ĕҏW(*1) <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =<BR>
     * �@@�@@�敨OP�\�񒍕��P��.������ʂ�蔻�肵�ĕҏW(*2) <BR>
     * <BR>
     * �@@(*1)������t���i <BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ�́A"�敨"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�I�v�V����"�B  <BR>
     * <BR>
     * �@@(*2)������t�g�����U�N�V���� <BR>
     * �@@�@@�@@������� ==
     * �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B <BR>
     * �@@�@@�@@������� ==
     * �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A"�ԍ�"�B  <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g��
     * �Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@�\�񒍕��ɓ��L�Ȕ����R�����s�����߂ɁA<BR>
     * �@@�@@�@@�\�񒍕������t���O���Z�b�g����B <BR>
     * �@@�@@�@@�����ɒ����͍݂邪�A�V�K�����Ƃ��Ĕ����R�����s�����߁B<BR>
     * �@@ <BR>
     * �@@�@@"�\�񒍕������t���O"��LocalThread�ɃZ�b�g����B <BR>
     * �@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * �@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, <BR>
     * �@@�@@�@@�@@BooleanEnum.True ) <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 47DF4EEC0027
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams[0] == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�|�T�[�r�X�̈���[0]��敨OP�\�񒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = null;
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit = null;
        if (l_serviceParams[0] instanceof WEB3ToSuccIfoOrderUnitImpl)
        {
            l_orderUnit =
                (WEB3ToSuccIfoOrderUnitImpl)l_serviceParams[0];
            l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //���X���擾
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvIfoOrderUnitRow.getBranchId());

            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            //����J�����_�R���e�L�X�g.�،���ЃR�[�h =
            //  �敨OP�\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.�،���ЃR�[�h
            l_context.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            //����J�����_�R���e�L�X�g.���X�R�[�h =
            //  �敨OP�\�񒍕��P��.���XID�ɊY������ ���X�I�u�W�F�N�g.���X�R�[�h
            l_context.setBranchCode(l_branch.getBranchCode());

            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����w���敨OP"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //����J�����_�R���e�L�X�g.�����R�[�h =
            //  �敨OP�\�񒍕��P��.����ID�ɊY������敨OP�����I�u�W�F�N�g.�����Y�����R�[�h
            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_rsvIfoOrderUnitRow.getProductId());
            l_context.setProductCode(l_ifoProductImpl.getUnderlyingProductCode());

            //����J�����_�R���e�L�X�g.������t���i =
            //  �敨OP�\�񒍕��P��.�敨�^�I�v�V�����敪��蔻�肵�ĕҏW(*1)
            //�@@(*1)������t���i
            //�@@�@@�@@�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ�́A"�敨"�B
            //�@@�@@�@@��L�ȊO�̏ꍇ�́A"�I�v�V����"�B
            String l_strOrderAcceptProduct = null;
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.FUTURE;
            }
            else
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.OPTION;
            }

            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =
            //  �敨OP�\�񒍕��P��.������ʂ�蔻�肵�ĕҏW(*2)
            //�@@(*2)������t�g�����U�N�V����
            //�@@�@@�@@������� ==
            //   �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            //    �@@������� ==
            //   �i"�敨�V�K��������"or"OP�V�K��������"�j�̏ꍇ�́A"���t�i�V�K�����j"�B
            //    �@@��L�ȊO�̏ꍇ�́A"�ԍ�"�B
            String l_strOrderAcceptTransaction = null;
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType())
                || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_rsvIfoOrderUnitRow.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
            else
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.CLOSE_MARGIN;
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

            //�R�j�@@�\�񒍕��ɓ��L�Ȕ����R�����s�����߂ɁA
            //�@@�@@�@@�\�񒍕������t���O���Z�b�g����B
            //�@@�@@�@@�����ɒ����͍݂邪�A�V�K�����Ƃ��Ĕ����R�����s�����߁B
            //
            //�@@�@@"�\�񒍕������t���O"��LocalThread�ɃZ�b�g����B
            //�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute(
            //�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER,
            //�@@�@@�@@�@@BooleanEnum.True )
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER,
                BooleanEnum.TRUE);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
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
     * WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * @@roseuid 47DF4EEC002A
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object, Object)";
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
        //WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, null);

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
     * WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 47DF4EEC002D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object, Throwable)";
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
        //WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
