head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϖ���(WEB3SLRepayUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�،��S�ۃ��[���ԍϖ���)<BR>
 * �،��S�ۃ��[���ԍϖ��׃N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayUnit extends Message
{
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;

    /**
     * (�ԍϗ\���)<BR>
     * �ԍϗ\���<BR>
     */
    public Date repayScheduledDate;

    /**
     * (�ԍϊz)<BR>
     * �ԍϊz<BR>
     */
    public String repayAmt;

    /**
     * (��t����)<BR>
     * �����̎�t����<BR>
     */
    public Date receptionDate;

    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     */
    public String cancelFlag;

    /**
     * (�،��S�ۃ��[���ԍϖ���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46CA4FA70263
     */
    public WEB3SLRepayUnit()
    {

    }
}
@
