head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryDailyBalanceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��n���ʎc�����(WEB3HistoryDailyBalanceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  �� �� �@@(���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��n���ʎc�����)<BR>
 * ��n���ʎc�����N���X<BR>
 * 
 * @@author �� �� �@@
 * @@version 1.0
 */
public class WEB3HistoryDailyBalanceUnit extends Message 
{
    
    /**
     * (�ڋq����c������ID)<BR>
     * �ڋq����c������ID<BR>
     */
    public String transactionHistoryId;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * (�����c��)<BR>
     * �����c��<BR>
     */
    public String accountBalance;
    
    /**
     * (���v���׃����N�t���O)<BR>
     * ���v���׃����N�t���O<BR>
     * <BR>
     * false�F�@@�����N�Ȃ�<BR>
     * true�F�@@�����N����<BR>
     */
    public boolean profitLossLink;
    
    /**
     * (����������ꗗ)<BR>
     * ����������ꗗ<BR>
     */
    public WEB3HistoryTradeHistoryUnit[] tradeHistoryUnits;
    
    /**
     * @@roseuid 41789C48030D
     */
    public WEB3HistoryDailyBalanceUnit() 
    {
     
    }
}
@
