head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositDBManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋���DB�Ǘ��N���X(WEB3IfoDepositDBManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/08/22 ��(FLJ) �V�K�쐬
 */

package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import webbroker3.util.*;

/**
 * �i�؋���DB�Ǘ��N���X�j�B
 * @@version 1.0
 */
public class WEB3IfoDepositDBManager
{
    /**
     * l_instance
     */
    private static WEB3IfoDepositDBManager l_instance;

    /**
     * getInstance
     *
     * @@return WEB3IfoDepositDBManager
     */
    public static WEB3IfoDepositDBManager getInstance()
    {
        if (l_instance == null)
        {
            l_instance = new WEB3IfoDepositDBManager();
        }
        return l_instance;
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility m_log =
        WEB3LogUtility.getInstance(WEB3IfoDepositDBManager.class);

    /**
     * �����s���C���T�[�g����
     *
     * @@param list List
     * @@return boolean
     */
    public boolean insert(List list)
    {

        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new DbTransactionCallback(list, 0));
        }
        catch (Exception l_dfe)
        {
            m_log.error(l_dfe.getMessage(), l_dfe);
            return false;
        }
        return true;
    }

    /**
     * �����s���폜����
     *
     * @@param list List
     * @@return boolean
     */
    public boolean delete(List list)
    {
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new DbTransactionCallback(list, 1));

        }
        catch (Exception l_dfe)
        {
            m_log.error(l_dfe.getMessage(), l_dfe);
            return false;
        }
        return true;
    }

    public class DbTransactionCallback
        implements TransactionCallback
    {
        private List list = null;
        int mode = 0;

        public DbTransactionCallback(List list, int mode)
        {
            this.list = list;
            this.mode = mode;
        }

        public Object process() throws DataQueryException, DataNetworkException,
            DataCallbackException
        {

            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                //insert
                if (mode == 0)
                {

                    for (int i = 0; i < list.size(); i++)
                    {
                        Row row = (Row) list.get(i);
                        if (m_log.ison())
                        {
                            m_log.debug("insert row=" + row.getRowType() + ">>>" + row);
                        }
                        l_queryProcesser.doInsertQuery(row);
                    }
                }
                //delete
                else if (mode == 1)
                {

                    for (int i = list.size() - 1; i >= 0; i--)
                    {
                        Row row = (Row) list.get(i);
                        if (m_log.ison())
                        {
                            m_log.debug("delete row=" + row.getRowType() + ">>>" + row);
                        }
                        l_queryProcesser.doDeleteQuery(row.getPrimaryKey());
                    }

                }
            }
            catch (Exception l_exp)
            {
                l_exp.printStackTrace();
                throw new DataCallbackException(
                    l_exp.getMessage(),
                    l_exp);
            }

            return null;
        }
    }

}
@
