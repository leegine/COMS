head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���������(WEB3AioCashoutCancelUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�o���������)<BR>
 * �o��������׃N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelUnit extends Message
{    
    /**
     * (�o�������̒���ID)
     */
    public String orderId;
    
    /**
     * (��t����)<BR>
     * �����̎�t����
     */
    public Date receptionDate;
    
    /**
     * (�U���\���)
     */
    public Date transScheduledDate;
    
    /**
     * (�o�����z)
     */
    public String cashoutAmt;
    
    /**
     * (�R���X�g���N�^)
     * @@return WEB3AioCashoutCancelUnit
     * @@roseuid 40F66F490336
     */
    public WEB3AioCashoutCancelUnit() 
    {
     
    }
}
@
