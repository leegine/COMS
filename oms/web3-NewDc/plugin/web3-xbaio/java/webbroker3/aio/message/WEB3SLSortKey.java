head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���p�\�[�g�L�[(WEB3SLSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����i���u�j�V�K�쐬 ���f��No.756
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�S�ۃ��[���p�\�[�g�L�[)<BR>
 * �S�ۃ��[���\�[�g�L�[�N���X�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3SLSortKey extends Message
{
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * �����^�~��<BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 46E0BE47070C
     */
    public WEB3SLSortKey()
    {

    }
}
@
