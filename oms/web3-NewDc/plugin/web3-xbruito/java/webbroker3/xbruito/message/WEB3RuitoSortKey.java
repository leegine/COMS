head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ��\�[�g�L�[�N���X(WEB3RuitoSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * �ݓ��\�[�g�L�[<BR>
 */
public class WEB3RuitoSortKey extends Message
{

    /**
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X����<BR>
     * �V���{�������L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922BD901A5
     */
    public WEB3RuitoSortKey()
    {

    }
}
@
