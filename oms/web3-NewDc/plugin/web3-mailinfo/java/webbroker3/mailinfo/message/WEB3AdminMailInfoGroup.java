head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ꗗ�s(WEB3AdminMailInfoGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���[�����ꗗ�s)<BR>
 * ���[�����ꗗ�s�@@�f�[�^�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoGroup extends Message 
{
    
    /**
     * (���M���[���敪)<BR>
     */
    public String sendMailDiv;
        
    /**
     * (����ID)<BR>
     */
    public String discernId;
    
    /**
     * (���[������)<BR>
     */
    public String mailName;
    
    /**
     * (���o�l)<BR>
     */
    public String mailFrom;
    
    /**
     * (����)<BR>
     */
    public String mailSubject;
    
    /**
     * (���[�����ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413C0E3B038A
     */
    public WEB3AdminMailInfoGroup() 
    {
     
    }
}
@
