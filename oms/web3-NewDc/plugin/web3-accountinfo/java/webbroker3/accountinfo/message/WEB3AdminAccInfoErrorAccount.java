head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoErrorAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���G���[�ڋq(WEB3AdminAccInfoErrorAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  䈋�(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��҂��q�l���G���[�ڋq)<BR>
 * �Ǘ��҂��q�l���G���[�ڋq<BR>
 * @@author 䈋�
 * @@version 1.0 
 */
public class WEB3AdminAccInfoErrorAccount extends Message
{
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3AdminAccInfoErrorAccount() {}
}
@
