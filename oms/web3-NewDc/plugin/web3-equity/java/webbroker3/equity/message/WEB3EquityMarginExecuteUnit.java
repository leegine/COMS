head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������(WEB3EquityMarginExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����E�M�p�������)<BR>
 * �����E�M�p�������N���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteUnit extends Message
{

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date executionTimestamp;

    /**
     * (��芔��)<BR>
     * ��芔��<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;

    /**
     * (�����E�M�p�������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 455C428300FC
     */
    public WEB3EquityMarginExecuteUnit()
    {

    }
}
@
