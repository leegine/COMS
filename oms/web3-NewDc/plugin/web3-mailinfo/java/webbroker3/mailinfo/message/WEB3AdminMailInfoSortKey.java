head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����\�[�g�L�[(WEB3AdminMailInfoSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���[�����\�[�g�L�[)<BR>
 * ���[�����\�[�g�L�[�@@�f�[�^�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoSortKey extends Message 
{
    
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X��<BR>
     * �̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A:�����@@D:�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (���[�����\�[�g�L�[)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413C13D8032C
     */
    public WEB3AdminMailInfoSortKey() 
    {
     
    }
}
@
