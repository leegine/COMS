head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����J�݉��菈��RAC�R���e�L�X�g�C���^�Z�v�^(WEB3AccOpenDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/23 ���u���i���{���u�j �V�K�쐬
 */

package webbroker3.accountopen;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.accountopen.data.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * �i�敨OP����RAC�R���e�L�X�g�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3AccOpenDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AccOpenDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;

        //�����J�ݓ`�[�o�^��t�ꌏ
        if (l_serviceParams.length > 0 &&
             l_serviceParams[0] instanceof HostAccOpenAcceptParams)
        {
            //�T�[�r�X�̈���[0]��敨OP������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams =
                (HostAccOpenAcceptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
            String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
            String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }

        if (log.ison())
        {
            log.debug("WEB3EquityDescendRacCtxInterceptor get account_id=" + l_accountId);
        }
        if (l_accountId > 0)
        {
            WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
                getService(WEB3DescendRacCtxService.class);
            if (l_ctx_serv != null)
            {
                l_ctx_serv.setAccountIdCtx(l_accountId);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    public void onReturn(Object l_returnValue, Object l_context)
    {
        //RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        //RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    private long getAccountId(String l_strInstitutionCode,
                              String l_strBranchCode,
                              String l_strAccountCode)

    {
        final String STR_METHOD_NAME =
            "getAccountId(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            long l_lngInstitutionId = l_institution.getInstitutionId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            log.exiting(STR_METHOD_NAME);
            return l_mainAccount.getAccountId();

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            return VID;
        }
    }
}
@
