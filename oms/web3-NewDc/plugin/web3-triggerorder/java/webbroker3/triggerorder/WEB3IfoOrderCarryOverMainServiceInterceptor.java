head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z���C���T�[�r�X�C���^�Z�v�^(WEB3IfoOrderCarryOverMainServiceImplInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/21 ��іQ(���u) �V�K�쐬 ���f��No.669
Revision History : 2007/06/28 ��іQ(���u) ���f��No.758
Revision History : 2007/07/03 ��іQ(���u) ���f��No.772
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����J�z���C���T�[�r�X�C���^�Z�v�^)<BR>
 * �敨OP�����J�z���C���T�[�r�X�C���^�Z�v�^�B<BR>
 * <BR>
 * �敨OP�����J�z���C���T�[�r�X�C���^�Z�v�^�ɑ΂��Đݒ肷��<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3IfoOrderCarryOverMainServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderCarryOverMainServiceInterceptor.class);

    /**
     * �敨OP�����J�z���C���T�[�r�X�C���^�Z�v�^<BR>
     */
    public WEB3IfoOrderCarryOverMainServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B  <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j  <BR>
     * <BR>
     * �P�j�@@���\�b�h����"execute"�̏ꍇ�́A�����̃T�[�r�X���\�b�h��ԋp����B�ireturn;�j<BR>
     * <BR>
     * �Q�j�@@�T�[�r�X�̈���[0]��敨OP�����J�z���C�����N�G�X�g�ɃL���X�g����B <BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �敨OP�����J�z<BR>
     * �@@�@@���C�����N�G�X�g.�،���ЃR�[�h  <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �،���ЃR�[�h�ɊY������<BR>
     * �@@�@@�،����.getBranches()�̖߂�l�� <BR>
     * �@@�@@0�Ԗڂ̗v�f�ɊY�����镔�X.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h  <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null (*1) <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null (*1) <BR>
     * <BR>
     * �@@(*1)  ������t�X�e�C�^�X�֘A���ڂ͎g�p���Ȃ����߁B<BR>
     * �@@�@@�@@�@@�i������Ԋ֘A�̍��ڂ̂݃Z�b�g�j<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �@@�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  <BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �S�j�@@RLS�ւ̔񓯊��ʒm�t���O���Z�b�g����B<BR>
     * �@@�@@�������J�z�́ARLS�ւ̒ʒm����ɔ񓯊��ōs���B<BR>
     * <BR>
     * �@@"�����J�zRLS�񓯊��ʒm"��LocalThread�ɃZ�b�g����B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * �@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,<BR>
     * �@@�@@�@@BooleanEnum.TRUE ) <BR>
     * <BR>
     * �T�j�@@���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
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

        //���\�b�h����"execute"�̏ꍇ�́A�����̃T�[�r�X���\�b�h��ԋp����B�ireturn;�j
        if (!"execute".equals(l_method.getName()))
        {
            log.exiting(STR_METHOD_NAME);
            return l_method;
        }

        //�T�[�r�X�̈���[0]��敨OP�����J�z���C�����N�G�X�g�ɃL���X�g����
        String l_strInstitutionCode = null;
        if (l_serviceParam[0] instanceof WEB3IfoOrderCarryOverMainRequest)
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                (WEB3IfoOrderCarryOverMainRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.institutionCode;
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        Branch[] l_branchs = null;
        try
        {
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);

            l_branchs = l_institution.getBranches();

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �敨OP�����J�z���C�����N�G�X�g.�،���ЃR�[�h
        l_context.setInstitutionCode(l_strInstitutionCode);

        //����J�����_�R���e�L�X�g.���X�R�[�h = �،���ЃR�[�h�ɊY������،����.getBranches()�̖߂�l��
        //0�Ԗڂ̗v�f�ɊY�����镔�X.���X�R�[�h
        l_context.setBranchCode(l_branchs[0].getBranchCode());

        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //����J�����_�R���e�L�X�g.������t���i = null (*1)
        l_context.setOrderAcceptProduct(null);

        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null (*1)
        l_context.setOrderAcceptTransaction(null);

        //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //�S�j�@@RLS�ւ̔񓯊��ʒm�t���O���Z�b�g����B
        //�������J�z�́ARLS�ւ̒ʒm����ɔ񓯊��ōs���B
        //"�����J�zRLS�񓯊��ʒm"��LocalThread�ɃZ�b�g����B
        //�|ThreadLocalSystemAttributesRegistry.setAttribute(
        //WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
        //BooleanEnum.TRUE )
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            BooleanEnum.TRUE);

        try
        {
            //���t���[�����Z�b�g����B
            //�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        return l_context;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ����.onCall���^�[���l��null�̏ꍇ�A�������Ȃ��ŏ������I������B�ireturn;�j<BR>
     * �ȊO�AThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param  l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        //����.onCall���^�[���l��null�̏ꍇ�A�������Ȃ��ŏ������I������B�ireturn;�j
        if(l_context != null)
        {
            return;
        }

        //�ȊO�AThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
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
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ����.onCall���^�[���l��null�̏ꍇ�A�������Ȃ��ŏ������I������B�ireturn;�j<BR>
     * �ȊO�AThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY<BR>
     * @@param  l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param  l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        //����.onCall���^�[���l��null�̏ꍇ�A�������Ȃ��ŏ������I������B�ireturn;�j
        if(l_obj != null)
        {
            return;
        }

        //�ȊO�AThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
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
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
