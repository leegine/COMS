head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectCompareKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���I�u�W�F�N�g�\�[�g�p�L�[�N���X(WEB3RichPushObjectCompareKey.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/01 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp;

import java.sql.*;

/**
 * <p>
 * ���b�`�N���C�A���g�v�b�V���I�u�W�F�N�g�\�[�g�p�L�[�N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RichPushObjectCompareKey
    implements Comparable
{

    /**
     * ����ID
     */
    private long account_id;

    /**
     * ���X�g�X�V����
     */
    private Timestamp last_updated_timestamp;

    /**
     * �v�b�V���f�[�^�^�C�v
     */
    private String type;

    /**
     * �v�b�V���f�[�^ROWID
     */
    private String tpk;

    /**
     * �v�b�V���f�[�^ROWID���擾����
     *
     * @@return Timestamp
     */
    public String getTpk()
    {
        return tpk;
    }

    /**
     * �v�b�V���f�[�^�^�C�v���擾����
     *
     * @@return Timestamp
     */
    public String getType()
    {
        return type;
    }

    /**
     * ���X�g�X�V�������擾����
     *
     * @@return Timestamp
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        return last_updated_timestamp;
    }

    /**
     * ����ID���擾����
     *
     * @@return long
     */
    public long getAccountId()
    {
        return account_id;
    }

    /**
     * ���̃I�u�W�F�N�g�Ǝw�肳�ꂽ�I�u�W�F�N�g�̏������r���܂��B
     * ���� �F�i����ID,���X�g�X�V����,�v�b�V���f�[�^�^�C�v�j ����
     * @@param o Object
     * @@return int
     */
    public int compareTo(Object o)
    {
        if (! (o instanceof WEB3RichPushObjectCompareKey))
        {
            throw new RuntimeException("illegal parameter:" + o);

        }
        WEB3RichPushObjectCompareKey n = (WEB3RichPushObjectCompareKey) o;
        int accountIdCmp = (account_id < n.getAccountId() ? -1 :
                            (account_id == n.getAccountId() ? 0 : 1));
        if (accountIdCmp != 0)
        {
            return accountIdCmp;
        }
        else
        {
            int timeCmp = last_updated_timestamp.compareTo(n.getLastUpdatedTimestamp());
            if (timeCmp != 0)
            {
                return timeCmp;
            }
            else
            {
                int typeCmp = type.compareTo(n.getType());
                return typeCmp != 0 ? typeCmp :
                    tpk.compareTo(n.getTpk());
            }
        }
    }

    /**
     * constructor
     */
    public WEB3RichPushObjectCompareKey(long l_lngAccountId,
                                        Timestamp l_lastUpdatedTimestamp,
                                        String l_strType,
                                        String l_strTpk)
    {
        this.account_id = l_lngAccountId;
        this.last_updated_timestamp = l_lastUpdatedTimestamp;
        this.type = l_strType;
        this.tpk = l_strTpk;
    }

}
@
