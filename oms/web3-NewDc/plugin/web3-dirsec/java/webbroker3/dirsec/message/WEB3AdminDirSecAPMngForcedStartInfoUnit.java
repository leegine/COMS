head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���菈�����(WEB3AdminDirSecAPMngForcedStartInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 �k�v�u(���u) �V�K�쐬 ���f�� 132
Revision History : 2008/07/30 ����(���u) ���f�� 136
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���菈�����)<BR>
 * ���菈�����N���X�B<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartInfoUnit extends Message
{

    /**
     * (�`�[�R�[�h)<BR>
     * �`�[�R�[�h�B<BR>
     */
    public String requestCode;

    /**
     * PTYPE�B<BR>
     */
    public String pType;

    /**
     * AP���菈�����B<BR>
     */
    public String apName;

    /**
     * (���ʃR�[�h�L���敪)<BR>
     * ���ʃR�[�h�L���敪�B<BR>
     * <BR>
     * 0�F����<BR>
     * 1�F�L��<BR>
     */
    public String requestNumberDiv;

    /**
     * (�X���b�h�ԍ��L���敪)<BR>
     * �X���b�h�ԍ��L���敪�B<BR>
     * <BR>
     * 0�F����<BR>
     * 1�F�L��<BR>
     */
    public String threadNumberDiv;

    /**
     * (���菈�����)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4875CFFF0390
     */
    public WEB3AdminDirSecAPMngForcedStartInfoUnit()
    {

    }
}
@
