head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP���菈��RAC�R���e�L�X�g�C���^�Z�v�^(WEB3IfoDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/16 ���u���i���{���u�j �V�K�쐬
 */

package webbroker3.ifo;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.ifo.data.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * �i�敨OP����RAC�R���e�L�X�g�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3IfoDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3IfoDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;
        
        //�敨�o���ʒm�A�I�v�V�����o���ʒm�A�敨�E�I�v�V���������ʒm�A�敨�����E����ʒm�A�I�v�V���������E����ʒm
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof OrderUnit)
        {
            OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
            l_accountId = l_orderUnit.getAccountId();

        }
        //�敨OP������t�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostFotypeOrderAcceptParams)
        {
            //�T�[�r�X�̈���[0]��敨OP������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����
            HostFotypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
                (HostFotypeOrderAcceptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostEqtypeOrderAcceptParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostEqtypeOrderAcceptParams.getBranchCode();
            String l_strAccountCode = l_hostEqtypeOrderAcceptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }
        
        //�敨�E�I�v�V���������ʒm�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostFotypeOrderReceiptParams)
        {
            //�T�[�r�X�̈���[0]��敨OP������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����
            HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams =
                (HostFotypeOrderReceiptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostFotypeOrderReceiptParams.getInstitutionCode();
            String l_strBranchCode = l_hostFotypeOrderReceiptParams.getBranchCode();
            String l_strAccountCode = l_hostFotypeOrderReceiptParams.getAccountCode();
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
