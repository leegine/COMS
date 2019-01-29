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
filename	WEB3AdminEquityForcedSettleOrderRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω������쐬RAC�R���e�L�X�g�C���^�Z�v�^(WEB3AdminEquityForcedSettleOrderRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/11/20 ���L���E(���u) �V�K�쐬 �d�l�ύX���f��No.213
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω������쐬RAC�R���e�L�X�g�C���^�Z�v�^)<BR>
 * �Ǘ��ҁE�������ω������쐬RAC�R���e�L�X�g�C���^�Z�v�^ <BR>
 * <BR>
 * ���Ǘ��ҁE�������ω������쐬�ꌏ�T�[�r�X��addInterceptor����B<BR>
 * <BR>
 * @@author ���L���E
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderRacCtxInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderRacCtxInterceptor.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminEquityForcedSettleOrderRacCtxInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �P�j�@@RAC�R���e�L�X�g�ɒl���Z�b�g����B<BR>
     * �@@�P�|�P�j�@@�T�[�r�X���\�b�h����[0]������Row�ɃL���X�g����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B<BR>
     * �@@�@@WEB3DescendRacCtxService.setAccountIdCtx()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@����Row.����ID<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        long l_lngAccountId = -1;

        //�P�|�P�j�@@�T�[�r�X���\�b�h����[0]������Row�ɃL���X�g����B
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof EqtypeContractRow)
        {
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_serviceParams[0];
            l_lngAccountId = l_eqtypeContractRow.getAccountId();
        }

        if (l_lngAccountId > 0)
        {
            //�P�|�Q�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B
            WEB3DescendRacCtxService l_descendRacCtxService =
                (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
            if (l_descendRacCtxService != null)
            {
                l_descendRacCtxService.setAccountIdCtx(l_lngAccountId);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B  <BR>
     * <BR>
     * �P�j�@@RAC�R���e�L�X�g���N���A����B<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.clearAccountIdCtx();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B  <BR>
     * <BR>
     * �P�j�@@RAC�R���e�L�X�g���N���A����B  <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.clearAccountIdCtx();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
