head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountBaseInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq��{���(WEB3SLAccountBaseInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.754
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ڋq��{���)<BR>
 * �ڋq��{���<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLAccountBaseInfoUnit extends Message
{
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

    /**
     * (���N�����N��)<BR>
     * ���N�����N��<BR>
     */
    public String eraBorn;

    /**
     * (���N����)<BR>
     * ���N����<BR>
     */
    public String bornDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public String sex;

    /**
     * (�X�֔ԍ�)<BR>
     * �X�֔ԍ�<BR>
     */
    public String zipCode;

    /**
     * (�Z���P�i�����j)<BR>
     * �Z���P�i�����j<BR>
     */
    public String address1;

    /**
     * (�Z���Q�i�����j)<BR>
     * �Z���Q�i�����j<BR>
     */
    public String address2;

    /**
     * (�Z���R�i�����j)<BR>
     * �Z���R�i�����j<BR>
     */
    public String address3;

    /**
     * (email�A�h���X)<BR>
     * email�A�h���X<BR>
     */
    public String mailAddress;

    /**
     * @@roseuid 46E0BE480000
     */
    public WEB3SLAccountBaseInfoUnit()
    {

    }
}
@
