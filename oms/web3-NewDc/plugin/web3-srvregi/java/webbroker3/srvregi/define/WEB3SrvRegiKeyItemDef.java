head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[����(WEB3SrvRegiKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 ���o��(sinocom) �V�K�쐬
Revesion History : 2007/06/19 �����Q(sinocom) �d�l�ύX���f��No.249
Revesion History : 2007/07/25 ����(sinocom) �d�l�ύX���f��No.292
Revesion History : 2008/03/03 ���g(sinocom) �d�l�ύX���f��No.330
Revesion History : 2008/03/26 ���g(sinocom) �����̖��002
*/
package webbroker3.srvregi.define;

/**
 * �L�[����
 *
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3SrvRegiKeyItemDef
{
    /**
     * branch�F���X  �@@
     */
    public final static String BRANCH_CODE = "branchCode";

    /**
     * account�F�ڋq  �@@
     */
    public final static String ACCOUNT_CODE = "accountCode";

    /**
     * appliLotDiv�F�\�����I�敪 �@@
     */
    public final static String APPLI_LOT_DIV = "applyLotteryDiv";

    /**
     * appliDate�F�\����  �@@
     */
    public final static String APPLI_DATE = "applyDate";

    /**
     * appliStartDate�F�K�p�J�n��  �@@
     */
    public final static String APPLI_START_DATE = "trialStartDate";

    /**
     * appliEndDate�F�K�p�I����  �@@
     */
    public final static String APPLI_END_DATE = "trialEndDate";

    /**
     * paymentDiv �F�o�^�敪  �@@
     */
    public final static String PAYMENT_DIV = "registDiv";

    /**
     * transactionDiv �F�����敪
     */
    public final static String TRANSACTION_DIV = "transactionDiv";

    /**
     * useAmt�F���p����  �@@
     */
    public final static String USE_AMT = "chargeAmt";

    /**
     * lastUpdatedTimestamp�F�ŏI�X�V��  �@@
     */
    public final static String LAST_UPDATED_TIMESTAMP  = "lastUpdateTime";

    /**
     * lastUpdater�F�ŏI�X�V��  �@@
     */
    public final static String LAST_UPDATER = "lastUpdater";

    /**
     * appliAttribute�F�\�������敪
     */
    public final static String APPLI_ATTRIBUTE = "appliAttribute";

    /**
     * seqNumber�F�ʔ�
     */
    public final static String SEQUENCE_NUMBER = "seqNumber";

    /**
     * id�FID
     */
    public final static String ID = "id";

    /**
     * status�F�X�e�[�^�X
     */
    public final static String STATUS = "status";

    /**
     * appliStartDate�F�K�p����From
     */
    public final static String APPLI_START_DATE_FROM = "appliStartDate";

    /**
     * appliEndDate�F�K�p����To
     */
    public final static String APPLI_END_DATE_TO = "appliEndDate";
}
@
