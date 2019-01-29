head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z�T�[�r�X�C���^�[�Z�v�^(WEB3IfoDepositCalcServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����v�Z�T�[�r�X�C���^�[�Z�v�^)<BR>
 * �؋����v�Z�T�[�r�X�C���^�[�Z�v�^�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcServiceInterceptor
    implements Interceptor
{

    /**
     * ����J�����_�R���e�L�X�g�̑�����
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.tradingcalendarcontext";

    /**
     * ��t���Ԃ̑�����
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.timestamp";

    /**
     * �I�t�Z�b�g�̑�����
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.ifodeposit.attributes.offset";

    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositCalcServiceInterceptor.class);

    /**
     * @@roseuid 41861FE0034A
     */
    public WEB3IfoDepositCalcServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@�X���b�h���[�J�����A����J�����_�R���e�L�X�g�I�u�W�F�N�g���擾 <BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�Ɍ��݃Z�b�g����Ă�����e��ۑ����Ă����B<BR>
     * �@@�ďo���T�[�r�X�Ŏ���J�����_�R���e�L�X�g�ɓ��e���ݒ肳��Ă���ꍇ�́A�ʓr�ۑ����Ă����B<BR>
     * �@@��onReturn()�A����сAonThrowable()�̍ۂɕۑ����e�ɖ߂��������s��<BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��⏕�����I�u�W�F�N�g�ɃL���X�g����B  <BR>
     * �@@�|�L���X�g�����⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B <BR>
     * �@@���⏕�����I�u�W�F�N�g.getMainAccount()���R�[�� <BR>
     * <BR>
     * �@@������ԃR���e�L�X�g�̃v���p�e�B���ȉ��ɃZ�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.getBranch().getBranchCode() <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*3)<BR>
     * <BR>
     * �@@(*1)�@@�P�j�Ŏ擾��������J�����_�R���e�L�X�g.���i�R�[�h == null�̏ꍇ�̂݁h0:DEFAULT�h���Z�b�g<BR>
     * �@@�@@�ȊO�͂P�j�̎���J�����_�R���e�L�X�g.���i�R�[�h���Z�b�g<BR>
     * <BR>
     * �@@(*2)�@@�P�j�Ŏ擾��������J�����_�R���e�L�X�g.������t���i == null�̏ꍇ�̂݁A�h05�F�敨�h���Z�b�g<BR>
     * �@@�@@�ȊO�͂P�j�̎���J�����_�R���e�L�X�g.������t���i���Z�b�g<BR>
     * <BR>
     * �@@(*3)�P�j�Ŏ擾��������J�����_�R���e�L�X�g.������t�g�����U�N�V���� == null�̏ꍇ�̂݁A�h07�F�Ɖ�h���Z�b�g<BR>
     * �@@�@@�ȊO�͂P�j�̎���J�����_�R���e�L�X�g.������t�g�����U�N�V�������Z�b�g<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �S�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�X���b�h���[�J���Ɏ�����ԃR���e�L�X�g�I�u�W�F�N�g���ݒ肳��Ă��Ȃ��ꍇ�̂݁A�^�C���X�^���v��ݒ肷��B <BR>
     * <BR>
     * �@@�|�P�j�Ŏ擾����������ԃR���e�L�X�g == null�̏ꍇ�A������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param method
     * @@param arguments
     * @@return Object
     * @@roseuid 41452B27014D
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {
        /*
         * �X���b�h���[�J�����A�I���W�i��������ԃR���e�L�X�g�I�u�W�F�N�g���擾
         */
        WEB3GentradeTradingClendarContext l_originalContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        // �ݒ肳��Ă���ThreadLocal�������ړ�
        removeAttributes();

        // ����J�����_�R���e�L�X�g�𐶐�
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //�T�[�r�X�̈���[0]��⏕�����I�u�W�F�N�g�ɃL���X�g����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arguments[0];
        //�ڋq�I�u�W�F�N�g���擾
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        //����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.getBranch().getBranchCode()
        l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        /*
         * �擾��������J�����_�R���e�L�X�g.���i�R�[�h == null�̏ꍇ�̂݁h0:DEFAULT�h���Z�b�g
         * �ȊO�͂P�j�̎���J�����_�R���e�L�X�g.���i�R�[�h���Z�b�g
         */
        if (l_originalContext == null || l_originalContext.getProductCode() == null)
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
        else
        {
            l_context.setProductCode(l_originalContext.getProductCode());
        }

        /*
         * �擾��������J�����_�R���e�L�X�g.������t���i == null�̏ꍇ�̂݁A�h05�F�敨�h���Z�b�g
         * �ȊO�͂P�j�̎���J�����_�R���e�L�X�g.������t���i���Z�b�g
         */
        if (l_originalContext == null || l_originalContext.getOrderAcceptProduct() == null)
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        }
        else
        {
            l_context.setOrderAcceptProduct(l_originalContext.getOrderAcceptProduct());
        } 
        
        /*
         * �擾��������J�����_�R���e�L�X�g.������t�g�����U�N�V���� == null�̏ꍇ�̂݁A�h07�F�Ɖ�h���Z�b�g
         * �ȊO�͂P�j�̎���J�����_�R���e�L�X�g.������t�g�����U�N�V�������Z�b�g
         */
        if (l_originalContext == null || l_originalContext.getOrderAcceptTransaction() == null)
        {
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        }
        else
        {
            l_context.setOrderAcceptTransaction(l_originalContext.getOrderAcceptTransaction());
        }

        // ������ԃR���e�L�X�g���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        /*
         * �擾����������ԃR���e�L�X�g == null�̏ꍇ�A������ԊǗ�.setTimestamp()���R�[������B
         */
        if (l_originalContext == null)
        {
            //xTrade�����p����Ɩ��������Z�b�g����
            try
            {
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
        }

        log.debug("--------------------------------------------------");
        log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
        log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
        log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
        log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
        log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
        log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
        log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
        log.debug("--------------------------------------------------");

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
     * @@param context
     * @@param returnValue
     * @@roseuid 41452B27016C
     */
    public void onReturn(Object context, Object returnValue)
    {
        resetAttributes();
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
     * @@param context
     * @@param thrownObject
     * @@roseuid 41452B27017C
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        resetAttributes();
    }

    /**
     * �ݒ肳��Ă���ThreadLocal�������ړ�����
     */
    private void removeAttributes()
    {
        
        // �ݒ肳��Ă���ThreadLocal�������擾
        Object l_objTempTradingCalendarContext =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        Object l_objTempTimestamp =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        Object l_objTempOffset =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        
        // �擾����ThreadLocal������ʂ̖��O�ŕۑ�
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
            l_objTempTradingCalendarContext);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_ATTRIBUTE_NAME,
            l_objTempTimestamp);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            OFFSET_ATTRIBUTE_NAME,
            l_objTempOffset);
    }

    /**
     * ThreadLocal�������Đݒ肷��B
     */
    private void resetAttributes()
    {

        // �ޔ����Ă����������擾
        Object l_objContext =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME);
        Object l_objTimestamp =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                TIMESTAMP_ATTRIBUTE_NAME);
        Object l_objOffset =
            ThreadLocalSystemAttributesRegistry.getAttribute(
                OFFSET_ATTRIBUTE_NAME);
        
        // �擾�����������Đݒ�
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_objContext);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_objTimestamp);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            l_objOffset);
        
        // �ޔ����Ă����������폜
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_ATTRIBUTE_NAME,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            OFFSET_ATTRIBUTE_NAME,
            null);

    }

}
@
