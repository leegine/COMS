head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�T�[�r�XImpl(WEB3TPReCalcNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ResourceBusyException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyRow;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z�ʒm�T�[�r�XImpl)
 */
public class WEB3TPReCalcNotifyServiceImpl implements WEB3TPReCalcNotifyService
{
    /**
     * �i���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyServiceImpl.class);

    /**
     * @@roseuid 423541390181
     */
    public WEB3TPReCalcNotifyServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ肵�Ă�Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�]�͍Čv�Z�ʒm�T�[�r�X�jexecute�v�Q�ƁB<BR>
     *  <BR>
     * @@param l_request - ���N�G�X�g
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41F4A5840007
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3TPReCalcNotifyRequest l_reCalcRequest = (WEB3TPReCalcNotifyRequest)l_request;
        l_reCalcRequest.validate();

        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_reCalcRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService)Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynTPReCalcNotifyServiceImpl(l_reCalcRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
}
@
