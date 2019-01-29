head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistTransferInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U������N���X(WEB3AdminInformProfDistSellTransSrcInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 �Ӑ�(���u) �V�K�쐬 ���f��No.045
Revision History    : 2007/06/12 �Ӑ�(���u) �d�l�ύX ���f��No.079
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.DirectDebitRow;


/**
 * (�����E���z���E���p����U������N���X)<BR>
 * �����E���z���E���p����U������N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistTransferInfo extends Message
{
    /**
     * (�o�^�N����)<BR>
     * �o�^�N����
     */
    public Date registDate;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j
     */
    public String accountName;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j
     */
    public String accountNameKana;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;

    /**
     * (�w��敪)<BR>
     * �w��敪
     */
    public String specifyDiv;

    /**
     * (���i)<BR>
     * ���i
     */
    public String product;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (������)<BR>
     * ������
     */
    public String productName;

    /**
     * (�U�֋敪)<BR>
     * �U�֋敪
     */
    public String transferDiv;

    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h
     */
    public String financialInstitutionCode;

    /**
     * (��s��)<BR>
     * ��s��
     */
    public String financialInstitutionName;

    /**
     * (�x�X�R�[�h�^�ʒ��L��)<BR>
     * �x�X�R�[�h�^�ʒ��L��
     */
    public String financialBranchCode;

    /**
     * (�x�X��)<BR>
     * �x�X��
     */
    public String financialBranchName;

    /**
     * (�a���敪)<BR>
     * �a���敪
     */
    public String financialAccountDiv;

    /**
     * (�����ԍ��^�ʒ��ԍ�)<BR>
     * �����ԍ��^�ʒ��ԍ�
     */
    public String financialAccountCode;

    /**
     * (�U����敪)<BR>
     * �U����敪
     */
    public String transferAccountDiv;

    /**
     * (�戵�敪)<BR>
     * �戵�敪
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 4655937501F2
     */
    public WEB3AdminInformProfDistTransferInfo()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �e���ڂɁA����.�����U�֓o�^�}�X�^Row�̓����ڂ̒l���Z�b�g����B<BR>
     * ���Ή����鍀�ڂ��Ȃ��ꍇ�́Anull��ݒ肷��B<BR>
     * ���ڋq�R�[�h�́A����.�����U�֓o�^�}�X�^Row.�ڋq�R�[�h�̓�6�����Z�b�g����B<BR>
     * @@param l_directDebitRow - (�����U�֓o�^�}�X�^Row)<BR>
     * �����U�֓o�^�}�X�^Row<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4643C4F0030A
     */
    public WEB3AdminInformProfDistTransferInfo(DirectDebitRow l_directDebitRow)
        throws WEB3BaseException
    {
        // �o�^�N����
        this.registDate = l_directDebitRow.getSonarCreatedTimestamp();

        // ���X�R�[�h
        this.branchCode = l_directDebitRow.getBranchCode();

        // �ڋq�R�[�h
        this.accountCode = l_directDebitRow.getAccountCode().substring(0,6);

        // �ڋq���i�����j
        this.accountName = null;

        // �ڋq���i�J�i�j
        this.accountNameKana = null;

        // ���҃R�[�h
        this.traderCode = l_directDebitRow.getTraderCode();

        // �w��敪
        this.specifyDiv = l_directDebitRow.getDesignateDiv();

        // ���i
        this.product = l_directDebitRow.getComodity();

        // �����R�[�h
        this.productCode = l_directDebitRow.getFundCode();

        // ������
        this.productName = null;

        // �U�֋敪
        this.transferDiv = l_directDebitRow.getTransferDiv();

        // ��s�R�[�h
        this.financialInstitutionCode = l_directDebitRow.getFinInstitutionCode();

        // ��s��
        this.financialInstitutionName = l_directDebitRow.getFinInstitutionName();

        // �x�X�R�[�h�^�ʒ��L��
        this.financialBranchCode = l_directDebitRow.getFinBranchCode();

        // �x�X��
        this.financialBranchName = l_directDebitRow.getFinBranchName();

        // �a���敪
        this.financialAccountDiv = l_directDebitRow.getFinSaveDiv();

        // �����ԍ��^�ʒ��ԍ�
        this.financialAccountCode = l_directDebitRow.getFinAccountNo();

        // �U����敪
        this.transferAccountDiv = l_directDebitRow.getTransCommission();

        // �戵�敪
        this.tradeHandleDiv = l_directDebitRow.getTransDealDiv();
    }
}
@
