head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTranRefPerIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �w���ʏ؋������ږ��׃N���X(WEB3IfoDepositTranRefPerIndexUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/03 �L�R�@@�ˎq(SRA) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�w���ʏ؋������ږ���)<BR>
 * �w���ʏ؋������ږ��׃N���X�B<BR>
 * 
 * @@author Shoko Ariyama (SRA)
 */
public class WEB3IfoDepositTranRefPerIndexUnit extends Message
{

    /**
     * (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h�B<BR>
     */
    public String targetProductCode;

    /**
     * (�敨�����܂���OP�v�b�g�����\����)<BR>
     * �敨�����܂���OP�v�b�g�����\���ʁB<BR>
     */
    public String bullQuantity;

    /**
     * (�敨�����܂���OP�R�[�������\����)<BR>
     * �敨�����܂���OP�R�[�������\���ʁB<BR>
     */
    public String bearQuantity;

    /**
     * (���|�W�V��������)<BR>
     * ���|�W�V�������ʁB<BR>
     */
    public String longPositionContract;

    /**
     * (���|�W�V��������(��������))<BR>
     * ���������|�W�V�������ʁB<BR>
     */
    public String partLongPositionContract;

    /**
     * (���|�W�V��������)<BR>
     * ���|�W�V�������ʁB<BR>
     */
    public String shortPositionContract;

    /**
     * (���|�W�V��������(��������))<BR>
     * ���������|�W�V�������ʁB<BR>
     */
    public String partShortPositionContract;

    /**
     * �R���X�g���N�^�B
     */
    public WEB3IfoDepositTranRefPerIndexUnit()
    {

    }
}
@
