head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������ڍו������(WEB3EquityExecuteDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������������ڍו������j�B<BR>
 * <BR>
 * �������������ڍו������@@�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityExecuteDetailUnit extends Message
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
     * (���������������ڍו������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 407CC7D90315
     */
    public WEB3EquityExecuteDetailUnit()
    {

    }
}
@
