head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenApplyDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���\���ڋq���׈ꗗ�s(WEB3SLAccountOpenApplyDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.776
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�S�ۃ��[���\���ڋq���׈ꗗ�s)
 * �S�ۃ��[���\���ڋq���׈ꗗ�s
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLAccountOpenApplyDetailUnit extends Message
{
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ�<BR>
     */
    public String stockLoanAccount;

    /**
     * (�\����)<BR>
     * �\����<BR>
     */
    public String applyStatus;

    /**
     * (�\����)<BR>
     * �\����<BR>
     */
    public Date applyDateFrom;

    /**
     * (�J�ݓ�)<BR>
     * �J�ݓ�<BR>
     */
    public Date accountOpenDate;

    /**
     * (�����)<BR>
     * �����<BR>
     */
    public Date executeDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date cancelDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date closeDate;

    /**
     * (Y�q���b�N)<BR>
     * Y�q���b�N<BR>
     */
    public String yellowAccountLockDiv;

    /**
     * (�l�����b�N)<BR>
     * �l�����b�N<BR>
     */
    public String examinationLockDiv;

    /**
     * (�x�X���b�N)<BR>
     * �x�X���b�N<BR>
     */
    public String branchLockDiv;

    /**
     * (�Ǘ����b�N)<BR>
     * �Ǘ����b�N<BR>
     */
    public String mngLockDiv;

    /**
     * (�Ǘ����b�N���R(���֋�))<BR>
     * �Ǘ����b�N���R(���֋�)<BR>
     */
    public String mngExpenseLockReasonFlag;

    /**
     * (�Ǘ����b�N���R(�ۏ؋�����))<BR>
     * �Ǘ����b�N���R(�ۏ؋�����)<BR>
     */
    public String mngDepositLockReasonFlag;

    /**
     * (�Ǘ����b�N���R(�K�i�S�ەs��))<BR>
     * �Ǘ����b�N���R(�K�i�S�ەs��)<BR>
     */
    public String mngCollateralLockReasonFlag;

    /**
     * (�Ǘ����b�N���R(�a��ؒ���������))<BR>
     * �Ǘ����b�N���R(�a��ؒ���������)<BR>
     */
    public String mngReceiptLockReasonFlag;
}
@
