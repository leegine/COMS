head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������(WEB3AdminBondProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                     2006/10/08 ���� (���u) �d�l�ύX�E���f��106
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������)<BR>
 * �������N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductInfo extends Message
{

    /**
     * (�����R�[�h�iWEB3�j)<BR>
     * �����R�[�h�iWEB3�j
     */
    public String productCode;

    /**
     * (������)<BR>
     * �戵������
     */
    public String productName;

    /**
     * (���t�P��)<BR>
     * ���t�P��
     */
    public String buyPrice;

    /**
     * (���p�P��)<BR>
     * ���p�P��
     */
    public String sellPrice;

    /**
     * (����)<BR>
     * ����
     */
    public String coupon;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ�
     */
    public String currencyCode;

    /**
     * (���s��)<BR>
     * ���s��
     */
    public Date issueDate;

    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����
     */
    public String yearlyInterestPayments;

    /**
     * (������1)<BR>
     * ������1
     */
    public String interestPaymentDay1;

    /**
     * (������2)<BR>
     * ������2
     */
    public String interestPaymentDay2;

    /**
     * (���ғ�)<BR>
     * ���ғ�
     */
    public Date maturityDate;

    /**
     *(�d�����̈בփ��[�g)<BR>
     *�d�����̈בփ��[�g
     */
    public String fxRateAtStock;

    /**
     * (�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��
     */
    public WEB3AdminBondCustodianUnit custodianInfo;

    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g
     */
    public WEB3AdminBondFxRateInfo fxRateInfo;

    /**
     * (�������)<BR>
     * �R���X�g���N�^
     * @@roseuid 44DB2ED10264
     */
    public WEB3AdminBondProductInfo()
    {

    }
}
@
