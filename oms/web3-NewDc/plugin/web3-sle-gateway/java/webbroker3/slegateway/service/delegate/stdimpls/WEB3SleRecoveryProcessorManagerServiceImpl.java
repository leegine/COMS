head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRecoveryProcessorManagerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SleRecoveryProcessorManagerServiceImpl�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.slegateway.WEB3SleProcessorsImpl;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;
import webbroker3.slegateway.service.delegate.WEB3SleRecoveryProcessorManagerService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���J�o���[�����̊Ǘ��N���X
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3SleRecoveryProcessorManagerServiceImpl implements WEB3SleRecoveryProcessorManagerService
{
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleRecoveryProcessorManagerServiceImpl.class);

    private static final boolean DBG = m_log.ison();

    /**
     * (Recovery���M���N�G�X�g)
     */
    private WEB3ProcessSleRecoveryRequest m_req;

    /**
     * �T�[�r�X������
     * 
     * @@param l_web3ProcessSleRecoveryRequest ���J�o���[�@@�\���N�����邽�߂̃��b�Z�[�W
     */
    public void initService(WEB3ProcessSleRecoveryRequest l_web3ProcessSleRecoveryRequest)
    {
        m_req = l_web3ProcessSleRecoveryRequest;
    }

    /**
     * ���J�o���[�������N������
     */
    public void startProcessor()
    {
        //���J�o���[�g�����U�N�V�����N������
        try
        {

            m_log.entering("startProcessor()");

            WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback l_recoveryTransaction = new WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback(new WEB3SleProcessorsImpl());
            
            l_recoveryTransaction.setThreadNo(this.m_req.threadNo);
            l_recoveryTransaction.setFromAccountId(this.m_req.fromAccountId);
            l_recoveryTransaction.setToAccountId(this.m_req.toAccountId);
            
            QueryProcessor qp = Processors.getDefaultProcessor();
            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_recoveryTransaction);

        }
        catch (DataException de)
        {
            m_log.error(de.getMessage(), de);
        }

        m_log.exiting("startProcessor()");

    }

    /* (�� Javadoc)
     * @@see webbroker3.common.service.delegate.WEB3BusinessService#execute(webbroker3.common.message.WEB3GenRequest)
     */
    public WEB3GenResponse execute(WEB3GenRequest arg0) throws WEB3BaseException
    {
        return null;
    }

}@
