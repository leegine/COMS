head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SonarOperatorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : SONAR���ҏ��(WEB3SonarOperatorInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/10/31 �đo�g(���u) �V�K�쐬 ���f��048
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (SONAR���ҏ��)<BR>
 * SONAR���ҏ��<BR>
 * <BR>
 * @@author      �đo�g
 * @@version     1.0
 */
public class WEB3SonarOperatorInfo extends Message
{
    /**
     * ���҃R�[�h
     */
    public String operatorCode;

    /**
     * ���Җ�(�J�i)
     */
    public String nameKana;

    /**
     * ���Җ�(����)
     */
    public String nameKanji;
}@
