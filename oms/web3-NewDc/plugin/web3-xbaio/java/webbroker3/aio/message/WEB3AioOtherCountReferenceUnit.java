head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOtherCountReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���̑������Ɖ���(WEB3AioOtherCountReferenceUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���̑������Ɖ���) <BR>
 * ���̑������Ɖ���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AioOtherCountReferenceUnit extends Message
{
    /**
     * (�������ʌ������ꗗ) <BR>
     * �������ʌ������ꗗ
     */
    public WEB3AioDailyOrderCountUnit dailyOrderCountUnits;

    /**
     * (�������ʌ������ꗗ) <BR>
     * �������ʌ������ꗗ
     */
    public WEB3AioMonthlyOrderCountUnit monthlyOrderCountUnits;

    /**
     * (���̑������Ɖ���) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3AioOtherCountReferenceUnit()
    {
    }
}@
