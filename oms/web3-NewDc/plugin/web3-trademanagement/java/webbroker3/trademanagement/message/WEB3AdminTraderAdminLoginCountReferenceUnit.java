head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IP�ʃ��O�C���񐔏��(WEB3AdminTraderAdminLoginCountReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (IP�ʃ��O�C���񐔏��)<BR>
 * IP�ʃ��O�C���񐔏��N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountReferenceUnit extends Message
{

    /**
     * (�����N)<BR>
     * ���O�C�������񐔂̏��ʁB<BR>
     */
    public String rank;

    /**
     * (IP�A�h���X)<BR>
     * IP�A�h���X�B<BR>
     */
    public String ipAddress;

    /**
     * (���O�C��������)<BR>
     * IP�A�h���X���̃��O�C�������񐔂�ێ��B<BR>
     */
    public String loginCount;

    /**
     * @@roseuid 48D75CD60359
     */
    public WEB3AdminTraderAdminLoginCountReferenceUnit()
    {

    }
}
@
