head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AcctIdTryOrderMapInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�A�J�E���gID�g���C�I�[�_�}�b�v���N���X(Web3AcctIdTryOrderMapInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */

package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * �A�J�E���gID�g���C�I�[�_�}�b�v���
 * **/
public class WEB3AcctIdTryOrderMapInfo
    implements Serializable
{
    /**
     * �A�J�E���g�����W�J�nID
     */
    private long accountIdStart;

    /**
     * �A�J�E���g�����W�I��ID
     */
    private long accountIdEnd;

    /**
     * APP�T�[�o�g���C�I�[�_
     */
    private int[] appServerTryOrder;

    public WEB3AcctIdTryOrderMapInfo()
    {

    }

    /**
     * TryOrderMapInfo
     *
     * @@param accountIdStart long
     * @@param accountIdEnd long
     * @@param appServerTryOrder int[]
     */
    public WEB3AcctIdTryOrderMapInfo(long accountIdStart, long accountIdEnd,
                                     int[] appServerTryOrder
                                     )
    {
        this.accountIdStart = accountIdStart;
        this.accountIdEnd = accountIdEnd;
        this.appServerTryOrder = appServerTryOrder;

    }

    /**
     * �A�J�E���g�����W�J�nID��ݒ肷��B
     *
     * @@param accountIdStart �A�J�E���g�����W�J�nID
     */
    public void setAccountIdStart(long accountIdStart)
    {
        this.accountIdStart = accountIdStart;
    }

    /**
     * �A�J�E���g�����W�J�nID���擾����
     *
     * @@return �A�J�E���g�����W�J�nID
     */
    public long getAccountIdStart()
    {
        return accountIdStart;
    }

    /**
     * �A�J�E���g�����W�I��ID���擾����
     *
     * @@return �A�J�E���g�����W�I��ID
     */
    public long getAccountIdEnd()
    {
        return accountIdEnd;
    }

    /**
     * �A�J�E���g�����W�ݒ�ID��ݒ肷��B
     *
     * @@param accountIdEnd �A�J�E���g�����W�ݒ�ID
     */
    public void setAccountIdEnd(long accountIdEnd)
    {
        this.accountIdEnd = accountIdEnd;
    }

    /**
     * APP�T�[�o�g���C�I�[�_���擾����B
     *
     * @@return �T�[�o�g���C�I�[�_
     */
    public int[] getAppServerTryOrder()
    {
        return appServerTryOrder;
    }

    /**
     * APP�T�[�o�g���C�I�[�_��ݒ肷��B
     *
     * @@param appServerTryOrder �T�[�o�g���C�I�[�_
     */
    public void setAppServerTryOrder(int[] appServerTryOrder)
    {
        this.appServerTryOrder = appServerTryOrder;
    }

}
@
