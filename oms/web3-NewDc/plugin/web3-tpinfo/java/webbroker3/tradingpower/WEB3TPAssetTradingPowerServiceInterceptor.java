head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�]�͏���ʕ\���T�[�r�X�C���^�[�Z�v�^(WEB3TPAssetTradingPowerServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Y�]�͏���ʕ\���T�[�r�X�C���^�[�Z�v�^)<BR>
 * ���Y�]�͏���ʕ\���T�[�r�X�C���^�[�Z�v�^�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetTradingPowerServiceInterceptor
    implements Interceptor
{

    /**
     * ����J�����_�R���e�L�X�g�̑�����
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.tradingcalendarcontext";

    /**
     * ��t���Ԃ̑�����
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.timestamp";

    /**
     * �I�t�Z�b�g�̑�����
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.tradingpower.attributes.offset";

    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3TPAssetTradingPowerServiceInterceptor.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * (�R���X�g���N�^)
     */
    public  WEB3TPAssetTradingPowerServiceInterceptor()
    {
    }

    /**
     * (onCall) <BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�ݒ肳��Ă���ThreadLocal�������ړ�����B <BR>
     * �@@?this.removeAttributes()���R�[�� <BR>
     * <BR>
     * �Q�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@?�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@?���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = "0" <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "32�F�]��" <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�" <BR>
     * <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� <BR>
     * �@@�@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �R�j��t�����A���t���[�����Z�b�g����B <BR>
     * �@@?������ԊǗ�.setBusinessTimestamp()���R�[������B <BR>
     * <BR>
     * @@param method
     * @@param arguments
     * @@return Object
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {

        // �ݒ肳��Ă���ThreadLocal�������ړ�
        removeAttributes();

        // ����J�����_�R���e�L�X�g�𐶐�
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null; // �،���ЃR�[�h
        String l_strBranchCode = null; // ���X�R�[�h

        // OpLoginSecurityService����،���ЃR�[�h�A���X�R�[�h���擾
        OpLoginSecurityService l_security =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_security.getAccountId();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                GtlUtils.getAccountManager().getMainAccount(l_lngAccountId);
            l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        } 
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
        }

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode( l_strInstitutionCode );
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode( l_strBranchCode );
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode( WEB3MarketCodeDef.DEFAULT );
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�FEQUITY�h
        l_context.setTradingTimeType( WEB3TradingTimeTypeDef.EQUITY );
        // ����J�����_�R���e�L�X�g.���i�R�[�h
        l_context.setProductCode( "0" );
        // ����J�����_�R���e�L�X�g.������t���i = "32�F�]��"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRADING_POWER);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        // ������ԃR���e�L�X�g���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
            
        //xTrade�����p����Ɩ��������Z�b�g����
        try 
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } 
        catch (WEB3SystemLayerException e) 
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "onCall");

        }
        
        if( DBG ) 
        {
            log.debug("--------------------------------------------------");
            log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
            log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
            log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
            log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
            log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
            log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
            log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
            log.debug("--------------------------------------------------");
        }
        
        return null;

    }

    /**
     * (onReturn) <BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�jThreadLocal�������Đݒ肷��B <BR>
     * �@@?this.resetAttributes()���R�[�� <BR>
     * <BR>
     * @@param context
     * @@param returnValue
     */
    public void onReturn(Object context, Object returnValue)
    {
        resetAttributes();
    }

    /**
     * (onThrowable) <BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�jThreadLocal�������Đݒ肷��B <BR>
     * ?this.resetAttributes()���R�[�� <BR>
     * <BR>
     * @@param context
     * @@param thrownObject
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        resetAttributes();
    }

    /**
     * (removeAttributes) <BR>
     * �ݒ肳��Ă���ThreadLocal�������ړ����� <BR>
     * <BR>
     * �P�j�ݒ肳��Ă���ThreadLocal�������擾 <BR>
     * <BR>
     * �@@�P?�P�j�������i=������ԊǗ�.TRADING_CAL_CONTEXT_PATH)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 <BR>
     * <BR>
     * �@@�P?�Q�j�������i=������ԊǗ�.TIMESTAMP_TAG)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 <BR>
     * <BR>
     * �@@�P?�R�j�������i=������ԊǗ�.OFFSET_TAG)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 <BR>
     * <BR>
     * �Q�j�擾����ThreadLocal������ʂ̖��O�ŕۑ� <BR>
     * <BR>
     * �@@�Q?�P�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)�ɒl���Z�b�g<BR> 
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�F�P?�P�j�̖߂�l <BR>
     * <BR>
     * �@@�Q?�Q�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TIMESTAMP_ATTRIBUTE_NAME)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.TIMESTAMP_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�F�P?�Q�j�̖߂�l <BR>
     * <BR>
     * �@@�Q?�R�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.OFFSET_ATTRIBUTE_NAME)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.OFFSET_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�F�P?�R�j�̖߂�l <BR>
     * <BR>
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
     * (resetAttributes) <BR>
     * ThreadLocal�������Đݒ肷��B <BR>
     * <BR>
     * �P�j�ޔ����Ă����������擾 <BR>
     * <BR>
     * �@@�P?�P�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 <BR>
     * <BR>
     * �@@�P?�Q�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TIMESTAMP_ATTRIBUTE_NAME)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 <BR>
     * <BR>
     * �@@�P?�R�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.OFFSET_ATTRIBUTE_NAME)�̒l���擾 <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.getAttribute()�ɂĒl���擾 
     * <BR>
     * �Q�j�擾�����������Đݒ� <BR>
     * <BR>
     * �@@�Q?�P�j�������i=������ԊǗ�.TRADING_CAL_CONTEXT_PATH)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �@@�@@�@@Value�F�P?�P�j�̖߂�l <BR>
     * <BR>
     * �@@�Q?�Q�j�������i=������ԊǗ�.TIMESTAMP_TAG)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@�@@�@@Value�F�P?�Q�j�̖߂�l <BR>
     * <BR>
     * �@@�Q?�R�j�������i=������ԊǗ�.OFFSET_TAG)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g<BR> 
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F������ԊǗ�.OFFSET_TAG <BR>
     * �@@�@@�@@Value�F�P?�R�j�̖߂�l <BR>
     * <BR>
     * �R�j�ޔ����Ă����������폜 <BR>
     * <BR>
     * �@@�R?�P�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�Fnull <BR>
     * <BR>
     * �@@�R?�Q�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.TIMESTAMP_ATTRIBUTE_NAME)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.TIMESTAMP_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�Fnull <BR>
     * <BR>
     * �@@�R?�R�j�������i=���Y�]�͏���ʕ\���C���^�Z�v�^.OFFSET_ATTRIBUTE_NAME)�ɒl���Z�b�g <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute()�ɂăZ�b�g <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@String�F���Y�]�͏���ʕ\���C���^�Z�v�^.OFFSET_ATTRIBUTE_NAME <BR>
     * �@@�@@�@@Value�Fnull <BR>
     * <BR>
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
