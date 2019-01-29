head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3TPReCalcNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^)
 */
public class WEB3TPReCalcNotifyUnitServiceInterceptor implements Interceptor
{

    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyUnitServiceInterceptor.class);

    /**
     * @@roseuid 4235480002F8
     */
    public WEB3TPReCalcNotifyUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]��⏕�����I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|�L���X�g�����⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@���⏕�����I�u�W�F�N�g.getMainAccount()���R�[��<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq�I�u�W�F�N�g.getInstitution().getInstitutionCode() <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq�I�u�W�F�N�g.getBranch().getBranchCode()<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h1:�����h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param arg0
     * @@param arg1
     * @@return Object
     * @@roseuid 41F49A490084
     */
    public Object onCall(Method arg0, Object[] arg1)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        /*
         * ������ԃR���e�L�X�g�ɓ��e���Z�b�g����
         */
        // ������ԃR���e�L�X�g�𐶐�
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //�T�[�r�X�̈���[0]��⏕�����I�u�W�F�N�g�ɃL���X�g����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arg1[0];
        //�ڋq�I�u�W�F�N�g���擾
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //�،���ЃR�[�h
        l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        //���X�R�[�h
        l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        //�s��R�[�h = 1:����
        l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
        //��t���ԋ敪 = 01�F�����E�M�p
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //���i�R�[�h = 0�FDEFAULT
        l_context.setProductCode("0");

        //������ԃR���e�L�X�g���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        log.debug("--------------------------------------------------");
        log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
        log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
        log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
        log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
        log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
        log.debug("--------------------------------------------------");

        /*
         * �X���b�h���[�J���Ɏ�����ԃR���e�L�X�g�I�u�W�F�N�g���ݒ肳��Ă��Ȃ��ꍇ�A�^�C���X�^���v��ݒ肷��B
         */

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)
     * �]�͍Čv�Z�ʒm�ꌏ�T�[�r�X�I�����ɃR�[�������B 
     * 
     * @@param arg0
     * @@param arg1
     * @@roseuid 41F49A490093
     */
    public void onReturn(Object arg0, Object arg1)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable) 
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B
     * 
     * @@param arg0
     * @@param arg1
     * @@roseuid 41F49A4900B2
     */
    public void onThrowable(Object arg0, java.lang.Throwable arg1)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
