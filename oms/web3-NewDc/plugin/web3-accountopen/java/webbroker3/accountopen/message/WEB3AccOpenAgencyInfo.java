head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAgencyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �@@�\�ʒm���(WEB3AccOpenAgencyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/08/11 �����F(���u) �V�K�쐬 ���f��168
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�@@�\�ʒm���)<BR>
 * �@@�\�ʒm���<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccOpenAgencyInfo extends Message
{
    /**
     * (�t���K�i1)<BR>
     * �t���K�i1<BR>
     */
    public String agencyAccNameKana1;

    /**
     * (�t���K�i2)<BR>
     * �t���K�i2<BR>
     */
    public String agencyAccNameKana2;

    /**
     * (����1)<BR>
     * ����1<BR>
     */
    public String agencyAccName1;

    /**
     * (����2)<BR>
     * ����2<BR>
     */
    public String agencyAccName2;

    /**
     * (�Z��1)<BR>
     * �Z��1<BR>
     */
    public String agencyAddress1;

    /**
     * (�Z��2)<BR>
     * �Z��2<BR>
     */
    public String agencyAddress2;

    /**
     * (��\�҂̖�E)<BR>
     * ��\�҂̖�E<BR>
     */
    public String agencyRepPost;

    /**
     * (��\�҂̃t���K�i1)<BR>
     * ��\�҂̃t���K�i1<BR>
     */
    public String agencyRepNameKana1;

    /**
     * (��\�҂̃t���K�i2)<BR>
     * ��\�҂̃t���K�i2<BR>
     */
    public String agencyRepNameKana2;

    /**
     * (��\�҂̎���1)<BR>
     * ��\�҂̎���1<BR>
     */
    public String agencyRepName1;

    /**
     * (��\�҂̎���2)<BR>
     * ��\�҂̎���2<BR>
     */
    public String agencyRepName2;
}
@
