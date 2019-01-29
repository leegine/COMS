head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.39;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3QtpRichPushPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QTP���b�`�N���C�A���g�f�[�^�v�b�V���f�[�^�x�[�X�A�N�Z�X�Ǘ��T�[�r�X�C���^�[�t�F�[�X(WEB3QtpRichPushPersistentDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 ��(FLJ) �V�K�쐬
                   2009/06/02 ��(FTL) ���Ή�
 */

package webbroker3.rcp.service.delegate;

import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * QTP���b�`�N���C�A���g�f�[�^�v�b�V���f�[�^�x�[�X�A�N�Z�X�Ǘ��T�[�r�X�C���^�[�t�F�[�X
 * 
 * @@author ��
 * @@version 1.0
 */
public interface WEB3QtpRichPushPersistentDataManager
        extends Service
{
    /**
     * QTP����������t���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityOrderAcceptRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V�������������n��t�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpSwapOrderAcceptRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V��������������ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityChangeCancelRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V�������o���ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityContRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V�����������ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityLapseRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V���敨OP������t�ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoOrderAcceptRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V���敨OP��������ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoChangeCancelRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoContRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�v�b�V���敨OP�����ʒm�f�[�^�ǂݍ���<br/>
     *
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoLapseRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTP���b�`�N���C�A���g�f�[�^�v�b�V������VIEW�̓��e���擾����B
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@return Map
     */
    public Map getQtpRichPushHistoryTop()
        throws DataNetworkException, DataQueryException;

}
@
