head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\�[�g�L�[(WEB3SrvRegiSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�\�[�g�L�[)<BR>
 * �T�[�r�X���p�\�[�g�L�[�@@�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiSortKey extends Message
{
   
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A:�����@@D:�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (�T�[�r�X���p�\�[�g�L�[)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 410762EE0150
     */
    public WEB3SrvRegiSortKey() 
    {
     
    }
}
@
