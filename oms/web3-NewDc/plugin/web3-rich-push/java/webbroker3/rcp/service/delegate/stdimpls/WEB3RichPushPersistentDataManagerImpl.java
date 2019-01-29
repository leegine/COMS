head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushPersistentDataManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���i����������t�j�T�[�r�X�����N���X(WEB3RichPushEquityOrderAcceptServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import webbroker3.rcp.data.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * �i���b�`�N���C�A���g�v�b�V���i����������t�j�T�[�r�X�����N���X�j�B
 * @@author ��
 * @@version 1.0
 */
public class WEB3RichPushPersistentDataManagerImpl
    implements WEB3RichPushPersistentDataManager
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushPersistentDataManagerImpl.class);

    /**
     * ����������t���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityOrderAcceptRichPushData(long l_lngFromAccountId,
                                                 long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getEquityOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. ����������t���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushEquityOrderAcceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("����������t���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �����������n��t���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getSwapOrderAcceptRichPushData(long l_lngFromAccountId,
                                               long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getSwapOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. �����������n��t���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushSwapOrderAcceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�����������n��t���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * ������������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityChangeCancelRichPushData(long l_lngFromAccountId,
                                                  long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getEquityChangeCancelRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. ������������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushEquityChangeCancelParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("������������ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �����o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityContRichPushData(long l_lngFromAccountId,
                                          long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getEquityMarginContRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. �����o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy = null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushEquityContParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�����o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * ���������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityLapseRichPushData(long l_lngFromAccountId,
                                           long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getEquityMarginLapseRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.���������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushEquityLapseParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("���������ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �敨OP������t�ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoOrderAcceptRichPushData(long l_lngFromAccountId,
                                              long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getIfoOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.�敨OP������t�ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushIfoOrderAcceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�敨OP������t�ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �敨OP��������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoChangeCancelRichPushData(long l_lngFromAccountId,
                                             long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getIfoChangeCancelRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.�敨OP��������ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushIfoChangeCancelParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�敨OP��������ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �敨OP�o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoContRichPushData(long l_lngFromAccountId,
                                             long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getIfoContRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.�敨OP�o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushIfoContParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�敨OP�o���ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �敨OP�����ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoLapseRichPushData(long l_lngFromAccountId,
                                             long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "getIfoLapseRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.�敨OP�����ʒm���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

		String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushIfoLapseParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. �擾�����v�b�V���f�[�^�̃��R�[�h��Ԃ�
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("�敨OP�����ʒm���b�`�N���C�A���g�v�b�V���f�[�^���݂��܂���.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }


    /**
     * ���b�`�N���C�A���g�f�[�^�v�b�V������VIEW�̓��e���擾����B
     *
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@return Map
     */
    public Map getRichPushHistoryTop() throws
        DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "getRichPushHistoryTop()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            RichPushHistoryTopRow.TYPE);

        Hashtable l_mapSearchResult = new Hashtable();
        if (l_lisSearchResult != null)
        {
            for (int i = 0; i < l_lisSearchResult.size(); i++)
            {
                RichPushHistoryTopRow l_row = (RichPushHistoryTopRow) l_lisSearchResult.
                    get(i);
                l_mapSearchResult.put(l_row.getPrimaryKey(), l_row);
            }
        }
        log.exiting(STR_METHOD_NAME);

        return l_mapSearchResult;

    }

}
@
