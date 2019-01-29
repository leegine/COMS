head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteStatusManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����T�[�o�ւ̐ڑ���Ԃ��Ǘ����邽�߂̃N���X(WEB3QuoteStatusManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusDao;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusParams;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusRow;
import webbroker3.util.WEB3LogUtility;


/**
 * �����T�[�o�ւ̐ڑ���Ԃ��Ǘ����邽�߂̃N���X 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteStatusManager
{
    
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3QuoteStatusManager.class);
    
    /**
     * �f�o�b�N�t���O
     */
    private static final boolean DBG = log.ison();
    
    /**
     * ���̃N���X�̃C���X�^���X
     */
    private static final WEB3QuoteStatusManager instance = 
        new WEB3QuoteStatusManager();
    
    /**
     * �R���X�g���N�^
     */
    private WEB3QuoteStatusManager()
    {
    }

    /**
     * WEB3QuoteStatusManager�̃C���X�^���X���擾����B
     * 
     * @@return WEB3QuoteStatusManager�̃C���X�^���X
     */
    public static WEB3QuoteStatusManager getInstance()
    {
        return instance;
    }
    
    /**
     * ���݂̐ڑ��󋵂��擾����B<BR>
     * <BR>
     * �ڑ��󋵂��o�^����Ă��Ȃ��ꍇ�́A
     * <code>WEB3QuoteStatusEnum.CLOSED</code>��Ԃ��B<BR>
     * 
     * @@return ���݂̐ڑ���
     */
    public QuoteStatus getStatus()
    {
        String hostName = getHostName();
        QuoteStatusRow row = getCurrentStatus(hostName);
        QuoteStatus currentStatus = (row != null) ? row.getStatus()
                : QuoteStatus.CLOSED;

        if (DBG)
        {
            log.debug("Quote Service current status is " + currentStatus);
        }
        
        return currentStatus;
    }
    
    /**
     * �ڑ��󋵂��w�肵���ڑ��󋵂ɕύX����B<BR>
     * 
     * @@param status �ύX��̐ڑ���
     */
    public void modifyStatus(final QuoteStatus status)
    {
        
        if (status == null)
        {
            throw new IllegalArgumentException("status must be not null.");
        }
        
        final String hostName = getHostName();
        try
        {
            final QueryProcessor qp = Processors.getDefaultProcessor();
            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback() {

                public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                {
                    QuoteStatusRow currentStatus = getCurrentStatus(hostName);
                    Timestamp now = GtlUtils.getSystemTimestamp();
                    if (currentStatus != null)
                    {
                        Map changes = new HashMap();
                        changes.put("status", status);
                        changes.put("last_updated_timestamp", now);
                        qp.doUpdateQuery(currentStatus.getPrimaryKey(), changes);
                    } else
                    {
                        QuoteStatusParams newRow = new QuoteStatusParams();
                        newRow.setHostName(hostName);
                        newRow.setStatus(status);
                        newRow.setCreatedTimestamp(now);
                        newRow.setLastUpdatedTimestamp(now);
                        qp.doInsertQuery(newRow);
                    }
                    return null;
                }
                });
        } catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass() + ".modifyStatus(WEB3QuoteStatusEnum)",
                    de);
        }
        
        if (DBG)
        {
            log.debug("Quote Service modified to [" + status + "]");
        }
    }
    
    /**
     * �z�X�g�����擾����
     * 
     * @@return �z�X�g��
     */
    private String getHostName()
    {
        try
        {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName();
        } catch (UnknownHostException uhe)
        {
            log.error(uhe.getMessage(), uhe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass() + ".getHostName()",
                    uhe);
        }
    }
    
    /**
     * �w�肵���z�X�g����QuoteStatusRow���擾����B
     * 
     * @@param hostName �z�X�g��
     * @@return QuoteStatusRow
     */
    private QuoteStatusRow getCurrentStatus(String hostName)
    {
        try
        {
            return QuoteStatusDao.findRowByHostName(hostName);
        } catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    getClass() + ".getStatus()", 
                    dfe);
        } catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass() + ".getStatus()", 
                    de);
        }
    }
    
}
@
