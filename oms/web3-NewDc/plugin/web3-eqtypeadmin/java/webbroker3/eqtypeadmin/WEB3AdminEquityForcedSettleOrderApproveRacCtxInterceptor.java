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
filename	WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔FRAC�R���e�L�X�g�C���^�Z�v�^(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.170
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔FRAC�R���e�L�X�g�C���^�Z�v�^)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔FRAC�R���e�L�X�g�C���^�Z�v�^ <BR>
 * <BR>
 * ���Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X��addInterceptor����B<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor implements Interceptor
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �P�j�@@RAC�R���e�L�X�g�ɒl���Z�b�g����B<BR>
     * �@@�P�|�P�j�@@�T�[�r�X���\�b�h����[0]���������ϒ���Row�ɃL���X�g����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B<BR>
     * �@@�@@WEB3DescendRacCtxService.setAccountIdCtx()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�������ϒ���Row.����ID<BR>
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

        long l_accountId = -1;

        //�P�|�P�j�@@�T�[�r�X���\�b�h����[0]���������ϒ���Row�ɃL���X�g����B
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof AdminEqForcedSettleOrderRow)
        {
            AdminEqForcedSettleOrderRow l_orderRow = (AdminEqForcedSettleOrderRow)l_serviceParams[0];
            l_accountId = l_orderRow.getAccountId();
        }

        if (l_accountId > 0)
        {
            //�P�|�Q�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B
            WEB3DescendRacCtxService l_descendRacCtxService =
                (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
            if (l_descendRacCtxService != null)
            {
                l_descendRacCtxService.setAccountIdCtx(l_accountId);
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
