head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenExclusiveAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��p�U����������(WEB3AccOpenExclusiveAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 �ęԍg (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��p�U����������)<BR>
 * ��p�U���������񃁃b�Z�[�W<BR>
 * <BR>
 * @@author �ęԍg(���u)
 * @@version 1.0
 */
public class WEB3AccOpenExclusiveAccountInfo extends Message
{
    /**
     * ��p�U���������s�R�[�h<BR>
     * �i��p�U���������s�R�[�h�j<BR>
     */
    public String exclusiveAccountFinancialInstitutionCode;
    
    /**
     * ��p�U��������c��<BR>
     * (��p�U��������c��)<BR>
     */
    public String exclusiveAccountNumber;
    
    /**
     * ��p�U��������x���敪<BR>
     * (��p�U��������x���敪)<BR>
     * 0�F�x���Ȃ� 1�F����
     */
    public String exclusiveAccountWarningDiv;

}
@
