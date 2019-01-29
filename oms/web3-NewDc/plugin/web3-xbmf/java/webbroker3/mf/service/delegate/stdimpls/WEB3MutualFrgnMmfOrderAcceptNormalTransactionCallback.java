head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF��t���폈���ꌏTransactionCallback(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �đo�g (���u) �V�K�쐬 (���f��534)
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O��MMF��t���폈���ꌏTransactionCallback)<BR>
 * 
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback implements TransactionCallback
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.class);

    /**
     * (���M�����P��)<BR>
     * ���M�����P��<BR>
     */
    public MutualFundOrderUnit mutualFundOrderUnit;

    /**
     * (���M��t�m��C���^�Z�v�^)<BR>
     * ���M��t�m��C���^�Z�v�^<BR>
     */
    public WEB3MutualFundAcceptConfirmInterceptor mutualFundAcceptConfirmInterceptor;

    /**
     * (�O��MMF������t�L���[Params)<BR>
     * �O��MMF������t�L���[Params<BR>
     */
    public HostFrgnMmfOrderAcceptParams hostMutualFrgnMmfOrderAcceptParams;

    /**
     * @@roseuid 45C440D7011F
     */
    public WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback() 
    {
     
    }

    /**
     * (�O��MMF��t���폈���ꌏTransactionCallback)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * 1) this.���M�����P�� = ����.���M�����P��<BR>
     * <BR>
     * 2) this.���M��t�m��C���^�Z�v�^ = ����.���M��t�m��C���^�Z�v�^<BR>
     * <BR>
     * 3) this.�O��MMF������t�L���[Params = ����.�O��MMF������t�L���[Params<BR>
     * @@param mutualFundOrderUnit - (���M�����P��)<BR>
     * ���M�����P��<BR>
     * @@param mutualFundAcceptConfirmInterceptor - (���M��t�m��C���^�Z�v�^)<BR>
     * ���M��t�m��C���^�Z�v�^<BR>
     * @@param hostMutualFrgnMmfOrderAcceptParams - (�O��MMF������t�L���[Params)<BR>
     * �O��MMF������t�L���[Params<BR>
     * @@roseuid 45BEFEDF0118
     */
    public WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
        MutualFundOrderUnit mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor mutualFundAcceptConfirmInterceptor,
        HostFrgnMmfOrderAcceptParams hostMutualFrgnMmfOrderAcceptParams)
    {
        this.mutualFundOrderUnit = mutualFundOrderUnit;
        this.mutualFundAcceptConfirmInterceptor = mutualFundAcceptConfirmInterceptor;
        this.hostMutualFrgnMmfOrderAcceptParams = hostMutualFrgnMmfOrderAcceptParams;
    }

    /**
     * �V�[�P���X�} �u�i�O��MMF������t���폈���ꌏ�jprocess�v�Q�ƁB
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     * @@roseuid 45BEFF8101D0
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = " Object process()";
        log.entering(STR_METHOD_NAME);

        //���M������tUnitService���擾����
        WEB3MutualOrderAcceptUnitService l_OrderAcceptUnitService =
            (WEB3MutualOrderAcceptUnitService) Services.getService(
                WEB3MutualOrderAcceptUnitService.class);

        MutualFundOrderUnit l_mfOrderUnit = this.mutualFundOrderUnit;
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
            this.mutualFundAcceptConfirmInterceptor;
        HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
            this.hostMutualFrgnMmfOrderAcceptParams;
        String l_strAcceptStatus =
            l_orderAcceptParams.getAcceptStatus();

        try
        {
            //�����򏈗���������t����
            if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus))
            {
                //notify������t����(MutualFundOrderUnit, ���M��t�m��C���^�Z�v�^)
                l_OrderAcceptUnitService.notifyOrderAcceptComplete(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else if (WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
            {
                //notify������t���s(MutualFundOrderUnit, ���M��t�m��C���^�Z�v�^)
                l_OrderAcceptUnitService.notifyOrderAcceptFail(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch(WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�����敪�ύX
        HashMap l_map = new HashMap();
        String l_strUpdateWhere =
            " institution_code = ? " +
            " and branch_code = ? " +
            " and order_request_number = ? ";
        String[] l_strUpdateParams =
            {l_orderAcceptParams.getInstitutionCode(),
             l_orderAcceptParams.getBranchCode(),
             l_orderAcceptParams.getOrderRequestNumber()};
        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostFrgnMmfOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
