head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �������菈��RAC�R���e�L�X�g�C���^�Z�v�^(WEB3EquityDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/11 ��(FLT) �V�K�쐬
 */
package webbroker3.equity;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.equity.data.*;
import webbroker3.equity.service.delegate.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;

/**
 * �i�������菈��RAC�R���e�L�X�g�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3EquityDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;
        //�o���ʒm(�����o���ʒm�ꌏ�T�[�r�X�A�M�p�o���ʒm�ꌏ�T�[�r�X�A�����J�z�ꌏ�T�[�r�X)
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof OrderUnit)
        {
            OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
            l_accountId = l_orderUnit.getAccountId();

        }
        //����������t�ꌏ�T�[�r�X�i���������t�ꌏ�T�[�r�X�܁j
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeOrderAcceptParams)
        {
            //�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
                (HostEqtypeOrderAcceptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostEqtypeOrderAcceptParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostEqtypeOrderAcceptParams.getBranchCode();
            String l_strAccountCode = l_hostEqtypeOrderAcceptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }
        //���������ʒm�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeCloseOrderNotifyParams)
        {
            //�T�[�r�X�̈���[0]�����������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                (HostEqtypeCloseOrderNotifyParams) l_serviceParams[0];

            String l_strInstitutionCode = l_hostEqtypeCloseOrderNotifyParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostEqtypeCloseOrderNotifyParams.getBranchCode();
            String l_strAccountCode = l_hostEqtypeCloseOrderNotifyParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }
        //�M�p����������n�����ʒm�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3MarginSwapOrderNotifyDataAdapter)
        {
            WEB3MarginSwapOrderNotifyDataAdapter l_adapter
                = (WEB3MarginSwapOrderNotifyDataAdapter) l_serviceParams[0];
            HostEqtypeSwapReceiptParams l_swapReceiptParams = l_adapter.
                getDataSourseObject();
            String l_strInstitutionCode = l_swapReceiptParams.
                getInstitutionCode();
            String l_strBranchCode = l_swapReceiptParams.getBranchCode();
            String l_strAccountCode = l_swapReceiptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //�M�p����������n��t�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeSwapAcceptRow)
        {
            HostEqtypeSwapAcceptRow l_eqtypeSwapAcceptRow = (HostEqtypeSwapAcceptRow)
                l_serviceParams[0];
            String l_strInstitutionCode = l_eqtypeSwapAcceptRow.
                getInstitutionCode();
            String l_strBranchCode = l_eqtypeSwapAcceptRow.getBranchCode();
            String l_strAccountCode = l_eqtypeSwapAcceptRow.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //�M�p�����������ʒm����(�����A�����)�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeOrderClmdReceiptParams)
        {
            HostEqtypeOrderClmdReceiptParams l_params = (
                HostEqtypeOrderClmdReceiptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_params.getInstitutionCode();
            String l_strBranchCode = l_params.getBranchCode();
            String l_strAccountCode = l_params.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //���������ʒm�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3EquityOrderInputNotifyAdapter)
        {
            WEB3EquityOrderInputNotifyAdapter l_adapter = (
                WEB3EquityOrderInputNotifyAdapter) l_serviceParams[0];
            HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.
                getHostEqtypeOrderReceipt();
            String l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
            String l_strBranchCode = l_orderReceiptParams.getBranchCode();
            String l_strAccountCode = l_orderReceiptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //�M�p��������ʒm�ꌏ�T�[�r�X
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3MarginOrderNotifyDataAdapter)
        {
            WEB3MarginOrderNotifyDataAdapter l_adapter = (
                WEB3MarginOrderNotifyDataAdapter) l_serviceParams[0];
            HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.
                getDataSourseObject();
            String l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
            String l_strBranchCode = l_orderReceiptParams.getBranchCode();
            String l_strAccountCode = l_orderReceiptParams.getAccountCode();
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
