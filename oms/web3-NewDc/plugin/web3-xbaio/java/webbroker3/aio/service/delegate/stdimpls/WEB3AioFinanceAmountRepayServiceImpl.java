head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Z���z�ԍ�UnitServiceImpl(WEB3AioFinanceAmountRepayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬                                     
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.aio.data.PayRequiredAmountRow;
import webbroker3.aio.message.WEB3AioFinanceAmountRepayRequest;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayService;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Z���z�ԍσT�[�r�XImpl)<BR>
 * �Z���z�ԍσT�[�r�X�����N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayServiceImpl implements WEB3AioFinanceAmountRepayService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioFinanceAmountRepayServiceImpl.class);
    
    /**
     * @@roseuid 4510F52E0148
     */
    public WEB3AioFinanceAmountRepayServiceImpl() 
    {
     
    }
    
    /**
     * �،��S�ۃ��[���̕ԍϊz��ݒ肷��B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Z���z�ԍρjexecute�v�Q�ƁB<BR>
     * <BR> 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioFinanceAmountRepayRequest l_repayRequest = null;
        if (l_request instanceof WEB3AioFinanceAmountRepayRequest)
        {
            l_repayRequest = (WEB3AioFinanceAmountRepayRequest)l_request;
        }
        else 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�̃^�C�v���s���ł��B");
        }
        List l_lisPayRequiredAmount = null;
        try
        {
            //1.1 getDefaultProcessor( )
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //�����p
            Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
            Timestamp l_strDate = l_genBizDate.roll(0);
            String l_strFomateDate = WEB3DateUtility.formatDate(l_strDate, "yyyyMMdd");
            
            String l_strWhere = "proc_date = ? and status = ?";
            Object[] l_objWhere = {l_strFomateDate, WEB3StatusDef.NOT_DEAL};

            //1.2 doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //������ = �����c�Ɠ� 
            //�����敪 = "0"(�������̋敪) 
            //[����] 
            //Row�^�C�v�F �ԍϕK�v�z�f�[�^Row.TYPE 
            //Where�F "proc_date=? and status=?" 
            //���X�g�F �ȉ��̍��ڂ̃��X�g 
            //�����c�Ɠ�(YYYYMMDD) 
            //"0"
            l_lisPayRequiredAmount =
                l_queryProcessor.doFindAllQuery(
                    PayRequiredAmountRow.TYPE,
                    l_strWhere,
                    l_objWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.3 �擾�����ԍϕK�v�z�f�[�^Params����Loop����
        int l_intLength = 0;
        if (l_lisPayRequiredAmount != null && l_lisPayRequiredAmount.size() != 0)
        {
            l_intLength = l_lisPayRequiredAmount.size();
        }
        for (int i = 0; i < l_intLength; i++)
        {
            //1.3.1 execute(�ԍϕK�v�z�f�[�^Params : �ԍϕK�v�z�f�[�^Params)
            //�ԍϕK�v�z�f�[�^�X�V�������s���B 
            //[����] 
            //�ԍϕK�v�z�f�[�^Params�F�擾�����ԍϕK�v�z�f�[�^Params
            WEB3AioFinanceAmountRepayUnitService l_secFinanceLoanRepayUnitService =
                (WEB3AioFinanceAmountRepayUnitService)Services.getService(
                    WEB3AioFinanceAmountRepayUnitService.class);
            PayRequiredAmountRow l_payRequiredAmountRow = 
                (PayRequiredAmountRow)l_lisPayRequiredAmount.get(i);
            PayRequiredAmountParams l_payRequiredAmountParams = 
                new PayRequiredAmountParams(l_payRequiredAmountRow);
            l_secFinanceLoanRepayUnitService.execute(l_payRequiredAmountParams);
        }
        
        //1.4 ���X�|���X�I�u�W�F�N�g�𐶐����ԋp����B
        WEB3BackResponse l_response = l_repayRequest.createResponse();
         
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
