head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����ꗗ���j�b�g(WEB3AdminTPPaymentRequisitionListUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
Revision History : 2008/10/28 �����F (���u) �d�l�ύX���f��No.042
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���������ڋq�����ꗗ���j�b�g)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionListUnit extends Message
{

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (���҃R�[�h)<BR>
     */
    public String traderCode;

    /**
     * (����)<BR>
     */
    public String attribute;

    /**
     * (���֋�)<BR>
     */
    public String debitAmount;

    /**
     * (���ʗ��֋�)<BR>
     */
    public String specialDebitAmount;

    /**
     * (�K�v�����z)<BR>
     */
    public String requiredPayAmt;

    /**
     * (��ꐅ���Ǐ؋��z)<BR>
     */
    public String firstDepositAmount;

    /**
     * (��ꐅ���Ǐ،o�ߓ���)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (��񐅏��Ǐؐ���(1))<BR>
     */
    public String secondDeposit1;

    /**
     * (��񐅏��Ǐؐ���(2))<BR>
     */
    public String secondDeposit2;

    /**
     * (��񐅏��Ǐؐ���������)<BR>
     */
    public String secondDepositNonPay;

    /**
     * @@roseuid 48EC703302F7
     */
    public WEB3AdminTPPaymentRequisitionListUnit()
    {

    }
}
@
