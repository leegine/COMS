head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSetAbleCommissionCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ݒ�\�萔������(WEB3SrvRegiSetAbleCommissionCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�ݒ�\�萔������)<BR>
 * �T�[�r�X���p�ݒ�\�萔�������N���X<BR>
 *
 *  @@author �s�p
 * @@version 1.0
 */

public class WEB3SrvRegiSetAbleCommissionCondition extends Message
{
    
    /**
     * (���i���ދ敪)
     */
    public String productKindDiv;
    
    /**
     * (���i��)
     */
    public String productName;
    
    /**
     * (�T�[�r�X���p�ݒ�\�萔������)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4186E7870164
     */
    public WEB3SrvRegiSetAbleCommissionCondition() 
    {
     
    }
}
@
