head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderRootNumberInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o�H�ʌ������ (WEB3AdminOROrderRootNumberInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����o�H�ʌ������)<BR>
 * <BR>
 * �����o�H�ʌ������N���X<BR>
 * <BR>
 * WEB3AdminOROrderRootNumberInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderRootNumberInfoUnit extends Message
{
    /**
     * (�����o�H�敪)<BR>
     * <BR>
     * �����o�H�敪<BR>
     * <BR>
     * orderRootDiv<BR>
     * <BR>
     */
    public String orderRootDiv;

    /**
     * �i���i�s��ʌ������ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminORProductMarketNumberInfoUnit[] productMarketNumberInfoList;

    /**
     * (�����o�H�ʌ������)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * WEB3AdminOROrderRootNumberInfoUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderRootNumberInfoUnit
     * @@roseuid 419B4D4600D7
     */
    public WEB3AdminOROrderRootNumberInfoUnit()
    {

    }
}
@
