head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMonthlyOrderCountUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �������ʌ������(WEB3FXAccOpenApplyUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������ʌ������) <BR>
 * �������ʌ������N���X 
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AioMonthlyOrderCountUnit extends Message
{
    /**
     * (�������ꗗ) <BR>
     * �������ꗗ
     */
    public WEB3AioOtherCountInfomationUnit[] otherCountInfomationUnits;

    /**
     * (�������ʌ������) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3AioMonthlyOrderCountUnit()
    {
    }
}@
