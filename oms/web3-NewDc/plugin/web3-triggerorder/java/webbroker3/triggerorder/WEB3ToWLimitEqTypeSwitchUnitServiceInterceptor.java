head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l���������ؑֈꌏ�T�[�r�X�C���^�Z�v�^(WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/17 ꎉ�(���u) �V�K�쐬 �i���f���jNo.176
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l���������ؑֈꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToEquityManualOrderMainServiceInterceptor.class);

    /**
     *����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     *�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     *<BR>
     *�P�j�@@�T�[�r�X�̈���[0]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     *<BR>
     *�Q�j�@@�����P��.getDataSourceObject()���R�[������B  <BR>
     *<BR>
     *�R�j�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B  <BR>
     *<BR>
     *�@@�@@�@@[getBranch()�ɐݒ肷�����]  <BR>
     *�@@�@@�@@arg0�F�@@�����P��.getBranchId()�̖߂�l  <BR>
     *<BR>
     *�S�j�@@getBranch()�̖߂�l.getInstitution()���R�[������B  <BR>
     *<BR>
     *�T�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B  <BR>
     *<BR>
     *�@@�@@�@@[getMarket()�ɐݒ肷�����]  <BR>
     *�@@�@@�@@�����P��Row.getMarketId()  <BR>
     *<BR>
     *�U�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR>
     *�@@�|�����P�ʂ̓��e��������ԃR���e�L�X�g��  <BR>
     *�@@�@@�@@�v���p�e�B���Z�b�g����B  <BR>
     *<BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode()  <BR>
     *�@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode() <BR>
     *�@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode() <BR>
     *�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h <BR>
     *�@@����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.������t���i = (*1)  <BR>
     *�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h <BR>
     *<BR>
     *�@@(*1)������t���i  <BR>
     *�@@�@@�@@�E�����J�e�S���i�����P��.getOrderCateg()�j���h���������h�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B  <BR>
     *�@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A  <BR>
     *�@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B  <BR>
     *<BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�  <BR>
     *�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B  <BR>
     *�@@�@@�@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     *<BR>
     *�V�j�@@�X�L�b�vvalidate���ꎷ�s�����戵��~���Z�b�g����B <BR>
     *�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ� <BR>
     *�@@�X�L�b�vvalidate���ꎷ�s�����戵��~��"TRUE"���Z�b�g����B <BR>
     *�@@�@@-ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     *�@@�@@�@@�@@WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,<BR>
     *�@@�@@�@@�@@BooleanEnum.TRUE) <BR>
     *<BR>
     *�W�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
     *�@@�|������ԊǗ�.setTimestamp()���R�[������B  <BR>
     *<BR>
     *�X�j�@@���������b�N����B  <BR>
     *�@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����J�����_�����p����R���e�L�X�g�𐶐�����B
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //�P�j�@@�T�[�r�X�̈���[0]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_serviceParam[0];

        //�Q�j�@@�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //�R�j�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B
        //�@@�@@�@@[getBranch()�ɐݒ肷�����]
        //�@@�@@�@@arg0�F�@@�����P��.getBranchId()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            Branch l_branch =
                l_gentradeAccountManaer.getBranch(l_eqTypeOrderUnit.getBranchId());

            //�S�j�@@getBranch()�̖߂�l.getInstitution()���R�[������B
            Institution l_institution = l_branch.getInstitution();

            //�T�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B
            //�@@�@@�@@[getMarket()�ɐݒ肷�����]
            //�@@�@@�@@�����P��Row.getMarketId()
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            Market l_market = l_finObjManager.getMarket(l_eqtypeOrderUnitRow.getMarketId());

            //�U�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //�|�����P�ʂ̓��e��������ԃR���e�L�X�g��
            //�@@�@@�@@�v���p�e�B���Z�b�g����B
            //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode()
            l_context.setInstitutionCode(l_institution.getInstitutionCode());

            //�@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()
            l_context.setBranchCode(l_branch.getBranchCode());

            //�@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode()
            l_context.setMarketCode(l_market.getMarketCode());

            //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //�@@����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //�@@����J�����_�R���e�L�X�g.������t���i = (*1)
            // (*1)������t���i
            // �@@�@@�@@�E�����J�e�S���i�����P��.getOrderCateg()�j���h���������h�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B
            //�@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B
            if (OrderCategEnum.ASSET.equals(l_eqTypeOrderUnit.getOrderCateg()))
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else
            {
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }

            //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);

            //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
            //�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B
            //�@@�@@�@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //�V�j�@@�X�L�b�vvalidate���ꎷ�s�����戵��~���Z�b�g����B
            //�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //�@@�X�L�b�vvalidate���ꎷ�s�����戵��~��"TRUE"���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP, BooleanEnum.TRUE);

            //�W�j�@@��t�����A���t���[�����Z�b�g����B
            //�@@�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //�X�j�@@���������b�N����B
            //�@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            MainAccount l_mainAcc =
                l_gentradeAccountManaer.getMainAccount(l_eqTypeOrderUnit.getAccountId());
            l_gentradeAccountManaer.lockAccount(l_institution.getInstitutionCode(),
                l_branch.getBranchCode(),
                l_mainAcc.getAccountCode()
                );
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
     * WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);

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
     * WEB3LocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
