head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioInputOutputHistoryServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�ɗ����T�[�r�X�C���^�Z�v�^(WEB3AioInputOutputHistoryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.message.WEB3AioInputOutputHistoryListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o�ɗ����T�[�r�X�C���^�Z�v�^)<BR>
 * ���o�ɗ����T�[�r�X�C���^�Z�v�^�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryServiceInterceptor implements Interceptor 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AioInputOutputHistoryServiceInterceptor.class);  
    /**
     * @@roseuid 41EC855D000F
     */
    public WEB3AioInputOutputHistoryServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *      OpLoginSecurityService�̓��e���<BR>
     *      ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *       OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22�F�ڋq�T�[�r�X�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )
     *       �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41B7B628023D
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall()";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParam[0] instanceof WEB3AioInputOutputHistoryListRequest)
        {
            WEB3AioInputOutputHistoryListRequest l_request = 
                (WEB3AioInputOutputHistoryListRequest)l_serviceParam[0];            
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B", 
                new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "onCall"));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME);
        }
        try
        {
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            String l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_acc.getBranch().getBranchCode();
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            // �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
            //       OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            // �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
            l_context.setBranchCode(l_strBranchCode);
            // �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT); 
            // �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
            // �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            // �@@����J�����_�R���e�L�X�g.������t���i = �h22�F�ڋq�T�[�r�X�h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
            // �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
 
            // �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )
            //       �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
            // �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute
                (WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch(NotFoundException l_ex)
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41B7B628025D
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn()";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41B7B628027C
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable()";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        log.exiting(STR_METHOD_NAME);
     
    }
}
@
