head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStockLoanAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[�������J�ݏ��(WEB3AccInfoStockLoanAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.223
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�،��S�ۃ��[�������J�ݏ��)<BR>
 * �،��S�ۃ��[�������J�ݏ��N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccInfoStockLoanAccountInfo extends Message
{

    /**
     * (�J�ݏ�)<BR>
     * �J�ݏ�<BR>
     * <BR>
     * 0�F�@@���J��<BR>
     * 1�F�@@�J�ݍ�<BR>
     */
    public String stockLoanAccOpenDiv;

    /**
     * (�J�ݓ�)<BR>
     * �J�ݓ�<BR>
     */
    public Date stockLoanAccOpenDate;

    /**
     * @@roseuid 418F39F7034B
     */
    public WEB3AccInfoStockLoanAccountInfo()
    {

    }
}
@
