head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AccOpenDivUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݋敪(WEB3AccOpenDivUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/12 �Ԑi (���u) �V�K�쐬 ���f��057
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����J�݋敪)<BR>
 * �����J�݋敪�N���X<BR>
 * <BR>
 * @@author �Ԑi
 * @@version 1.0
 */
public class WEB3AccOpenDivUnit extends Message
{
    /**
    * SerialVersionUID
    */
    public static  final long serialVersionUID = 200903121110L;

    /**
     * (�������)<BR>
     * �������<BR>
     * 01�FFX<BR>
     * 02�FCFD<BR>
     * 03�F���FX<BR>
     */
    public String accType;

    /**
     * (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * 0�F���J�݁@@<BR>
     * 1�F�J�ݍ�<BR>
     * 2�F����<BR>
     * 3�F�U�֒�~<BR>
     */
    public String accOpenDiv;

    /**
     * @@roseuid 4021A07F0167
     */
    public WEB3AccOpenDivUnit()
    {

    }
}@
