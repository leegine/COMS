head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : AIO�\�[�g�L�[(WEB3AioSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (AIO�\�[�g�L�[)<BR>
 * AIO�\�[�g�L�[�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioSortKeyUnit extends Message
{

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * (����/�~��)<BR>
     * A�F ����<BR>
     * D�F �~��
     */
    public String ascDesc;

    /**
     * @@roseuid 41EC84F90186
     */
    public WEB3AioSortKeyUnit()
    {

    }
}
@
