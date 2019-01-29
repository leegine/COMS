head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����w���敨�V�K�������T�[�r�X�C���^�Z�v�^(WEB3FuturesOpenMarginServiceInterceptor.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * (�����w���敨�V�K�������T�[�r�X�C���^�Z�v�^)<BR>
 */
public class WEB3FuturesOpenContractServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractServiceInterceptor.class);

    /**
     * @@roseuid 40F7B0E1005D
     */
    public WEB3FuturesOpenContractServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     *  OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     * �@@�@@�i���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇ�A�h01�F���t�i�V�K�����j�h<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���敪 == �h�����h�̏ꍇ�A�h02�F���t�i�V�K�����j�h�j<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h<BR>
     * �@@�|���N�G�X�g�f�[�^.�����R�[�h�����͂���Ă���ꍇ�́A<BR>
     * �@@�@@�@@�����R�[�h�ɊY������敨OP�����I�u�W�F�N�g,get�����Y�����R�[�h()�B<BR>
     * �@@�|�ȊO�A���N�G�X�g�f�[�^.�w����ʁB<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �R�j���N�G�X�g�f�[�^�̌^���u�����w���敨�V�K�������������N�G�X�g�v<BR>
     *    �̏ꍇ�̂݁A���������b�N����B<BR>
     *  �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     *  ��������OpLoginSecurityService���ҏW�B <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>.
     * @@return Object
     * @@roseuid 40A849FD0002
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        Object l_request = l_serviceParam[0];
        String l_strProductCode = null; //�����Y�����R�[�h
        String l_orderAccept = null; //������t
        WEB3FuturesOpenMarginConfirmRequest l_confirmRequest = null;
        WEB3FuturesOpenMarginCompleteRequest l_completeRequest = null;
        //        if (l_request instanceof WEB3FuturesOpenMarginConfirmResponse)
        if (l_request instanceof WEB3FuturesOpenMarginConfirmRequest)
        {
            l_confirmRequest = (WEB3FuturesOpenMarginConfirmRequest) l_request;

            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_confirmRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_confirmRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }

        else if (l_request instanceof WEB3FuturesOpenMarginCompleteRequest)
        {
            l_completeRequest = (WEB3FuturesOpenMarginCompleteRequest) l_request;

            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_completeRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_completeRequest.contractType))
            {
                l_orderAccept = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + "onCall"));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME);
        }
        try
        {
            long l_lngAccountId; //�����R�[�h
            String l_institutionCode = null; // �،���ЃR�[�h
            String l_branchCode = null; //���X�R�[�h

            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_institutionCode = l_acc.getInstitution().getInstitutionCode();
            l_branchCode = l_acc.getBranch().getBranchCode();
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            
            if (l_confirmRequest != null && l_confirmRequest.futProductCode != null)
            {
                WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_acc.getInstitution(),l_confirmRequest.futProductCode);
                l_strProductCode = l_ifoProductImpl.getUnderlyingProductCode();
            }
            else if (l_completeRequest != null && l_completeRequest.futProductCode != null)
            {
                WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_acc.getInstitution(),l_completeRequest.futProductCode);
                l_strProductCode = l_ifoProductImpl.getUnderlyingProductCode();
            }
            else if (l_confirmRequest != null && l_confirmRequest.futProductCode == null)           
            {
                l_strProductCode = l_confirmRequest.targetProductCode;
            }
            else if (l_completeRequest != null && l_completeRequest.futProductCode == null)
            {
                l_strProductCode = l_completeRequest.targetProductCode;
            }

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            //�����P��.getProductId()�ɂĖ����h�c���擾����B
            //���Y�����R�[�h���擾
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_institutionCode);

            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_branchCode);

            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            //����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h
            l_context.setProductCode(l_strProductCode);

            //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� 
            l_context.setOrderAcceptTransaction(l_orderAccept);

            //�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();


            if (l_request instanceof WEB3FuturesOpenMarginCompleteRequest)
            {
                //���N�G�X�g�f�[�^�̌^���u�����w���敨�V�K�������������N�G�X�g�v�̏ꍇ
                l_accMgr.lockAccount(l_institutionCode, l_branchCode, l_acc.getAccountCode());
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "�f�[�^�s�����G���[�B",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,STR_METHOD_NAME);            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),STR_METHOD_NAME);
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
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40A849FD0012
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40A849FD0031
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
