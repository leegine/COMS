head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@�\�[�g�L�[(WEB3AdminFPTSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
*/

package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�Ǘ��ҋ����@@�\�[�g�L�[)<BR>
 * �Ǘ��ҋ����@@�\�[�g�L�[<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTSortKey extends Message
{

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     */
    public String keyItem;

    /**
     * (�����~��)<BR>
     * A�F ����<BR>
     * D�F �~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 46FDDD36033C
     */
    public WEB3AdminFPTSortKey()
    {

    }
}
@
