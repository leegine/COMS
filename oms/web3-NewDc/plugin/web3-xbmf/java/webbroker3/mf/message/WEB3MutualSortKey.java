head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���\�[�g�L�[�f�[�^�N���X(WEB3MutualSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ��O�� (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[ 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �����M���\�[�g�L�[�f�[�^�N���X<BR>
 * <BR>
 * �ꗗ�\�����̃\�[�g������N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSortKey extends Message 
{
    
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{������<BR>
     * �L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A:�����@@D:�~��<BR>
     */
    public String ascDesc;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9B8B4003A
     */
    public WEB3MutualSortKey() 
    {
     
    }
}
@
