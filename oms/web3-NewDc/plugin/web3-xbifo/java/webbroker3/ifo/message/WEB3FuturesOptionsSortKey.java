head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����\�[�g�L�[(WEB3FuturesOptionsSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���o�� (���u) �V�K�쐬
              001: 2004/08/05 ���Ō� (���u) �Ή��o�O U00021
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
 * �ꗗ�\�����̃\�[�g������N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3FuturesOptionsSortKey extends Message
{
    
    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{����<BR>
     * ���L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 40C0754400AB
     */
    public WEB3FuturesOptionsSortKey() 
    {
     
    }
}
@
