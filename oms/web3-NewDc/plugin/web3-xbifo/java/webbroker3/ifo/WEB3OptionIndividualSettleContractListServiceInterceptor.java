head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionIndividualSettleContractListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ԍϓ��̓T�[�r�X�C���^�Z�v�^(WEB3OptionSettleContractInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo;

import java.lang.reflect.Method;
import java.util.Hashtable;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
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
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;

/**
 * (OP�ʕԍψꗗ�\���T�[�r�X�C���^�Z�v�^)<BR>
 * �����w���I�v�V�����ʕԍψꗗ�\���T�[�r�X�C���^�Z�v�^�N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionIndividualSettleContractListServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionIndividualSettleContractListServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@�����̕ϐ����Z�b�g����B<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�<BR>
     * ThreadLocal�Ɏ����̕ϐ����Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@Hashtable�i�V�K�ɐ�������Hashtable�j<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = null(*) <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h03�F�ԍρh<BR>
     * <BR>
     *  (*)���i�R�[�h��execute( )����R�[������郁�\�b�h���ŁA <BR>
     * �@@�@@���ʂ��擾���Đݒ肷��B <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 409A1AF10128
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        //�����̕ϐ����Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, new Hashtable());

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            
        //����J�����_�R���e�L�X�g�𐶐�����
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        {
            //FinApp�T�[�r�X���擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException
            
            log.debug(">>>>get >> l_mainAccount =" + l_mainAccount);
            //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B

            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
            
            log.debug(">>>>l_mainAccount.getInstitution().getInstitutionCode() = " + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug(">>>>l_context.getInstitutionCode() =" + l_context.getInstitutionCode());
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            log.debug(">>>>l_mainAccount.getBranch().getBranchCode() = " + l_mainAccount.getBranch().getBranchCode());
            log.debug(">>>>l_context.getBranchCode() =" + l_context.getBranchCode());
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            log.debug(">>>>setMarketCode = " + WEB3MarketCodeDef.DEFAULT);
            log.debug(">>>>l_context.getMarketCode() =" + l_context.getMarketCode());
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            log.debug(">>>>setTradingTimeType = " + WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            log.debug(">>>>l_context.getTradingTimeType() =" + l_context.getTradingTimeType());
            //����J�����_�R���e�L�X�g.���i�R�[�h = null<BR>

            l_context.setProductCode(null);

            //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h<BR>
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            log.debug(">>>>l_context.getOrderAcceptProduct() = " + l_context.getOrderAcceptProduct());
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h03�F�ԍρh<BR>
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            log.debug(">>>>l_context.getOrderAcceptTransaction() = " + l_context.getOrderAcceptTransaction());
            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );
            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();            
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "�Y���f�[�^�Ȃ��B",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex)
                );
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
        }

        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 409A1AF10138
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
            WEB3IfoAttributeNameDef.CURRENT_PRICE,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 409A1AF1013B
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
            WEB3IfoAttributeNameDef.CURRENT_PRICE,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
