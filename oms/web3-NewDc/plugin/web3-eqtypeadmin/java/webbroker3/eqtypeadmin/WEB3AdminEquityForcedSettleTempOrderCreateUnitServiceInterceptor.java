head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131
Revision History : 2008/01/18 �����F(���u) �d�l�ύX���f��No.176
*/

package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireHandler;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�������ω������쐬�ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �������ω������쐬�ꌏ�T�[�r�X�C���^�Z�v�^�B<BR>
 * <BR>
 * �������ω������쐬�ꌏ�T�[�r�X�ɑ΂��Đݒ肷��B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor implements Interceptor
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireHandler.class);

    /**
     * @@roseuid 462CA4140053
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor()
    {
     
    }
    
    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID�F�@@����Row.����ID<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]������Row�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|����Row�I�u�W�F�N�g�̓��e������J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq�I�u�W�F�N�g�ɊY������<BR>
     * �@@�@@�@@�،���ЃI�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq�I�u�W�F�N�g�ɊY������<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ����Row.�s��ID�ɊY������<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g.�s��R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "�M�p���"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "�Ɖ�"<BR>
     * �@@�@@���������ς́A�o�b�`���^�V�X�e���ً}��~���̏ꍇ�̂ݎ��s�s�̂��߁A<BR>
     * �@@�@@��������t�g�����U�N�V�����ɂ�"�Ɖ�"��ݒ肷��B<BR>
     * �@@�@@���i�������쐬���̔����R���Ń`�F�b�N����j<BR>
     * <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�������͎擾�����ڋq�I�u�W�F�N�g���ҏW�B<BR> 
     * <BR>
     * �T�j�@@�������ϒ����̔����R���X�L�b�v�t���O���Z�b�g����B <BR>
     * �@@ <BR>
     * �@@�@@"�������ϒ��������R���X�L�b�v�t���O"��LocalThread�ɃZ�b�g����B <BR>
     * �@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * �@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, <BR>
     * �@@�@@�@@�@@BooleanEnum.True ) <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 4603913E02C7
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �T�[�r�X�̈���[0]������Row�I�u�W�F�N�g�ɃL���X�g����B
        // ����ID�F�@@����Row.����ID
        EqtypeContractRow l_eqtypeContractRow =
            (EqtypeContractRow)l_serviceParams[0];

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        WEB3GentradeMarket l_market = null;
        long l_lngMarketId = l_eqtypeContractRow.getMarketId();
        try
        {
            //      �P�j�@@�ڋq�I�u�W�F�N�g�̎擾
            // �g���A�J�E���g�}�l�[�W��.get�ڋq()��call����B
            l_mainAccount = l_accMgr.getMainAccount(l_eqtypeContractRow.getAccountId());

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                l_lngMarketId);
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

        // ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext(); 
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq�I�u�W�F�N�g�ɊY������
        // �،���ЃI�u�W�F�N�g.�،���ЃR�[�h
        String l_strInstitution = l_mainAccount.getInstitution().getInstitutionCode();
        l_context.setInstitutionCode(l_strInstitution);

        // ����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq�I�u�W�F�N�g�ɊY������
        // ���X�I�u�W�F�N�g.���X�R�[�h
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        l_context.setBranchCode(l_strBranchCode);

        // ����J�����_�R���e�L�X�g.�s��R�[�h = ����Row.�s��ID�ɊY������
        // �s��I�u�W�F�N�g.�s��R�[�h
        l_context.setMarketCode(l_market.getMarketCode());

        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        // ����J�����_�R���e�L�X�g.������t���i = "�M�p���"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);

        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "�Ɖ�"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        // �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        // �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            // �R�j�@@��t�����A���t���[�����Z�b�g����B
            // �|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            // �S�j�@@���������b�N����B
            // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            // �������͎擾�����ڋq�I�u�W�F�N�g���ҏW�B
            l_accMgr.lockAccount(l_strInstitution, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�T�j�@@�������ϒ����̔����R���X�L�b�v�t���O���Z�b�g����B
        //�@@�@@"�������ϒ��������R���X�L�b�v�t���O"��LocalThread�ɃZ�b�g����B
        //�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute(
        //�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
        //�@@�@@�@@�@@BooleanEnum.True )
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            BooleanEnum.TRUE);

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
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 4603919702A8
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
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
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
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
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 460391DE0315
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_context, Throwable l_throwable)";
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
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
