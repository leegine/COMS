head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoPositionBalance.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�|�W�V�����o�����X(WEB3IfoPositionBalance.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) �V�K�쐬
*/
package webbroker3.ifodeposit;

/**
 * (�敨OP�|�W�V�����o�����X)<BR>
 * �|�W�V�����o�����X��\���N���X�B<BR>
 */
public class WEB3IfoPositionBalance
{
    /**
     * (�|�W�V�����o�����X)<BR>
     * 
     * �|�W�V�����o�����X����<BR>
     * ���|�W�V�����o�����X�敪���j���[�g�����̏ꍇ�A0<BR>
     */
    public double positionBalance;

    /**
     * (�|�W�V�����o�����X�敪)<BR>
     * 0�F�@@�j���[�g����<BR>
     * 1�F�@@������<BR>
     * 2�F�@@������<BR>
     */
    public String positionBalanceType;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3IfoPositionBalance()
    {

    }

    /**
     * (�敨OP�|�W�V�����o�����X)<BR>
     * 
     * �R���X�g���N�^�B<BR>
     * �����̒l�𓯃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_dblPositionBalance - (�|�W�V�����o�����X)<BR>
     * 
     * �|�W�V�����o�����X����<BR>
     * @@param l_strPositionBalanceType - �|�W�V�����o�����X�敪
     * @@roseuid 41354D510249
     */
    public WEB3IfoPositionBalance(double l_dblPositionBalance, String l_strPositionBalanceType)
    {
        /*
         * �����̒l�𓯃v���p�e�B�ɃZ�b�g
         */
        this.positionBalance = l_dblPositionBalance;
        this.positionBalanceType = l_strPositionBalanceType;
    }
}
@
