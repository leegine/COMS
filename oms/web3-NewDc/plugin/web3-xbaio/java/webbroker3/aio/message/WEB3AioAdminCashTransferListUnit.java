head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAdminCashTransferListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ����(WEB3AioAdminCashTransferListUnit)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.693
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���o���ꗗ����)<BR>
 * ���o���ꗗ���׃N���X<BR>
 * 
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioAdminCashTransferListUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * �|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|<BR>
     * ��������<BR>
     * 101�F SONAR����<BR>
     * 102�F �o�[�`��������<BR>
     * 103�F �l�b�g����<BR>
     * 104�F �U��(����؋�������a���)<BR>
     * 105�F �ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)<BR>
     * 106�F ���̑��U��(X����a���)<BR>
     * ���o����<BR>
     * 201�F �o��<BR>
     * 202�F �U��(�a������犔��؋���)<BR>
     * 203�F �ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)<BR>
     * 204�F ���̑��U��(�a�������X)<BR>
     * �|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|<BR>
     */
    public String orderType;

    /**
     * (��n��)<BR>
     * �����̎�n��<BR>
     */
    public Date deliveryDate;

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
     * (�ڋq����)<BR>
     * �ڋq����<BR>
     */
    public String accountName;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;

    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * �|�|�|�|�|<BR>
     * 1�F ����<BR>
     * 2�F ������<BR>
     * 9�F �G���[<BR>
     * �|�|�|�|�|<BR>
     */
    public String cashinoutStatus;

    /**
     * (�������z)<BR>
     * �������z<BR>
     */
    public String cashinAmt;

    /**
     * (�o�����z)<BR>
     * �o�����z<BR>
     */
    public String cashoutAmt;

    /**
     * (���͌o�H)<BR>
     * ���͌o�H<BR>
     */
    public String orderRoutDiv;

    /**
     * (���͎�)<BR>
     * ���͎�<BR>
     */
    public String operatorCode;

    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     */
    public String financialInstitutionCode;

    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     */
    public String financialBranchCode;

    /**
     * (�������)<BR>
     * �������<BR>
     */
    public String accountTypeCode;

    /**
     * (�����ԍ�)<BR>
     * �����ԍ�<BR>
     */
    public String financialAccountCode;

    /**
     * @@roseuid 45C3F15701A5
     */
    public WEB3AioAdminCashTransferListUnit() 
    {
        
    }
}
@
