head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAllTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������L���[TransactionCallback(WEB3EquityOrderAllTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �������F(SRA) �V�K�쐬
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (������������L���[TransactionCallback)<BR>
 * <BR>
 * ������������L���[TransactionCallback�N���X�B<BR>
 * ������������L���[�̋��L���b�N�����{���AMAXAS�Ƃ̊Ԃ̔r��������s�����߂̃N���X�B<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 * @@version 1.0
 */
public class WEB3EquityOrderAllTransactionCallback implements TransactionCallback
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAllTransactionCallback.class);
    
    /**
     * (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     */
    private EqTypeOrderUnit orderUnit;
    
    /**
     * (������������L���[TransactionCallback)<BR>
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���L���b�N�ΏۃL���[�ɑ΂��钍���P�ʃI�u�W�F�N�g�B
     */
    public WEB3EquityOrderAllTransactionCallback(
        EqTypeOrderUnit l_orderUnit)
    {
        orderUnit = l_orderUnit;
    }
    
    /**
     * (process)<BR>
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �P�j�@@������������L���[�e�[�u���ɑ΂��A�ȉ��̏������w�肵<BR>
     * �@@�@@�@@select for update���g�p���ċ��L���b�N��������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �v���p�e�B�̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �v���p�e�B�̒����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �v���p�e�B�̒����P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*1) = �v���p�e�B�̒����P��.����Rev.<BR>
     * �@@�@@���@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@(*1)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@���������T�[�r�X.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * @@return Object
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    public Object process()
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(orderUnit.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            throw new DataCallbackException(l_nfe.getMessage(), l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)orderUnit.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        WEB3EquityFrontOrderService l_service =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        int l_intIndex = l_service.getIndexOfOrderRevInCorpCode();
        int l_intFigure = l_service.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code=?");
        l_strWhere.append(" and branch_code=?");
        l_strWhere.append(" and order_request_number=?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=?");
        l_strWhere.append(" and status=?");
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_processor.doFindAllQuery(
            HostEqtypeOrderAllRow.TYPE,
            l_strWhere.toString(),
            null,
            "for update",
            l_objWhere);
        int l_intSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intSize = l_lisSearchResult.size();
        }
        log.debug("���b�N����������������L���[�e�[�u���̌���:[" + l_intSize + "]");
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
