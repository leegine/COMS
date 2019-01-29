head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.27;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3RichPushPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�f�[�^�v�b�V���f�[�^�x�[�X�A�N�Z�X�Ǘ��T�[�r�X�C���^�[�t�F�[�X(WEB3RichPushPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate;

import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * ���b�`�N���C�A���g�f�[�^�v�b�V���f�[�^�x�[�X�A�N�Z�X�Ǘ��T�[�r�X�C���^�[�t�F�[�X
 * @@author ��
 * @@version 1.0
 */
public interface WEB3RichPushPersistentDataManager
    extends Service
{
    /**
     * ���b�`�N���C�A���g�v�b�V������������t�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V�������������n��t�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V��������������ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V�������o���ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V�����������ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V���敨OP������t�ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V���敨OP��������ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;

    /**
     * ���b�`�N���C�A���g�v�b�V���敨OP�����ʒm�f�[�^�ǂݍ���
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
        DataCallbackException;


	/**
	 * ���b�`�N���C�A���g�f�[�^�v�b�V������VIEW�̓��e���擾����B
	 *
	 * @@throws DataNetworkException
	 * @@throws DataQueryException
	 * @@return Map
	 */
	public Map getRichPushHistoryTop() throws
		DataNetworkException, DataQueryException;
}
@
