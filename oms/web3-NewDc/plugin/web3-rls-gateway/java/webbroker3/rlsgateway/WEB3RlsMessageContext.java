head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �R���e�L�X�g���x�[�X�N���X(WEB3RlsMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �R���e�L�X�g���x�[�X�N���X
 *
 * @@author FLJ��
 * @@version 1.0
 */
public abstract class WEB3RlsMessageContext
{
    /**
     * �⏕����
     */
    private SubAccount subAccount;

    /**
     * �����t�����^�C�v
     */
    private int conOrderType;

    /**
     * �⏕�������擾����
     */
    public SubAccount getSubAccount()
    {
        return subAccount;
    }

    /**
     * �⏕������ݒ肷��
     */
    public void setSubAccount(SubAccount l_SubAccount)
    {
        subAccount = l_SubAccount;
    }

    /**
     * �����t�����^�C�v
     */
    public int getConOrderType()
    {
        return conOrderType;
    }

    /**
     * �����t�����^�C�v��ݒ肷��
     */
    public void setConOrderType(int l_intConOrderType)
    {
        conOrderType = l_intConOrderType;
    }

    /**
     * WEB3RlsMessageContext
     */
    public WEB3RlsMessageContext(SubAccount l_SubAccount,
                                 int l_intConOrderType)
    {
        subAccount = l_SubAccount;
        conOrderType = l_intConOrderType;
    }

}
@
