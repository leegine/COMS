head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�R�t�ڋq���N���X(WEB3TraderAccountInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬�E���f��No.039
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CC�I�y���[�^�R�t�ڋq���)<BR>
 * CC�I�y���[�^�R�t�ڋq���N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3TraderAccountInfo extends Message
{

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String acceptCode;

    /**
     * (���O�i�����j)<BR>
     * ���O�i�����j
     */
    public String nameKanji;

    /**
     * (���O�i�J�i�j)<BR>
     * ���O�i�J�i�j
     */
    public String nameKana;

    /**
     * (����ID)<BR>
     * ����ID
     */
    public long accountID;

    /**
     * (CC�I�y���[�^�R�t�ڋq���)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3TraderAccountInfo()
    {

    }

}
@
