head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX��������(WEB3AdminSrvRegiCustomerSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX��������)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���������f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerSearchCondition extends Message 
{
    
    /**
     * (�\���o�^ID)
     */
    public String applyRegId;
    
    /**
     * (���X�R�[�h)
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX��������)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE7B0C0361
     */
    public WEB3AdminSrvRegiCustomerSearchCondition() 
    {
     
    }
}
@
