head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductBasicInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������{���(WEB3AdminBondProductBasicInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��������{���)<BR>
 * ��������{���N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductBasicInfo extends Message
{
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * (���s��)<BR>
     * ���s�N����
     */
    public Date issueDate;
    
    /**
     * (���s���i)<BR>
     * ���s���i
     */
    public String issuePrice;
    
    /**
     * (���s�z)<BR>
     * ���s�z
     */
    public String issueAmount;
    
    /**
     * (�P�ʊz��)<BR>
     * �P�ʊz��
     */
    public String parValue;
    
    /**
     * (���ғ�)<BR>
     * ���ҔN����
     */
    public Date maturityDate;
    
    /**
     * (���҉��i)<BR>
     * ���҉��i
     */
    public String redemptionPrice;
    
    /**
     * (���t�^�C�v)<BR>
     * ���t�^�C�v<BR>
     * <BR>
     * 0:�Œ藘�t����,1:������,2:�ϓ����t����
     */
    public String couponType;
    
    /**
     * (����)<BR>
     * ����
     */
    public String coupon;
    
    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     * <BR>
     * �s�莞��99999999
     */
    public String yearlyInterestPayments;
    
    /**
     * (������1)<BR>
     * ������1<BR>
     * <BR>
     * "0000"�̎��͕\�����Ȃ�
     */
    public String interestPaymentDay1;
    
    /**
     * (������2)<BR>
     * ������2<BR>
     * <BR>
     * "0000"�̎��͕\�����Ȃ�
     */
    public String interestPaymentDay2;
    
    /**
     * (HOST������1)<BR>
     * HOST������1
     */
    public String hostProductName1;
    
    /**
     * (HOST�ȗ�������)<BR>
     * HOST�ȗ�������
     */
    public String hostShortProductName;
    
    /**
     * (HOST������2)<BR>
     * HOST������2
     */
    public String hostProductName2;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     */
    public String bondCategCode;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (���s�s��R�[�h)<BR>
     * ���s�s��R�[�h<BR>
     * <BR>
     * 1�F�����s�ꔭ�s�E�������s��<BR>
     * 2�F�����s�ꔭ�s�E���O���s��<BR>
     * 3�F���O�s�ꔭ�s
     */
    public String issueMarketCode;
    
    /**
     * (���s�̃R�[�h)<BR>
     * ���s�̃R�[�h<BR>
     * <BR>
     * 1�F��  2�F���{�@@��   3�F�n�������c�� <BR>             
     * 4�F���v���Ɖ�Ё@@5�F��ʎ��Ɖ��<BR>
     * 6�F���Z�@@�ց@@7�F���ۋ@@�ց@@8�F���̑�
     */
    public String issueAssociationCode;
    
    /**
     * (�o�ߗ��q�v�Z�^�C�v)<BR>
     * �o�ߗ��q�v�Z�^�C�v
     */
    public String accruedInterestCalcType;
    
    /**
     * (�o�ߗ��q�N�Z��)<BR>
     * �o�ߗ��q�N�Z��
     */
    public Date accruedInterestStartDay;
    
    /**
     * (���ꗘ���敪)<BR>
     * ���ꗘ���敪<BR>
     * <BR>
     * 0�F���ꗘ���������@@1�F�����E�I���X�L�b�v <BR>
     * 2�F�����X�L�b�v�@@3�F�I���X�L�b�v�@@4�F�����s�� <BR>
     * 5�F�������z�����@@6�F�V���[�g�C���^���X�g
     */
    public String specialPaymentDiv;
    
    /**
     * (�t���[�e�B���O���[�g�E�������ԋ敪)<BR>
     * �t���[�e�B���O���[�g�E�������ԋ敪
     */
    public String floatingInterestPeriodDiv;
    
    /**
     * (�t���[�e�B���O���[�g�E��������)<BR>
     * �t���[�e�B���O���[�g�E��������
     */
    public String floatingInterestPeriod;
    
    /**
     * (�t���[�e�B���O���[�g�E�������)<BR>
     * �t���[�e�B���O���[�g�E�������
     */
    public String floatingInterestType;
    
    /**
     * (�t���[�e�B���O���[�g�E�����X�v���b�h)<BR>
     * �t���[�e�B���O���[�g�E�����X�v���b�h
     */
    public String floatingInterestSpread;
    
    /**
     * (�t���[�e�B���O���[�g�E�~�j�}���N�[�|��)<BR>
     * �t���[�e�B���O���[�g�E�~�j�}���N�[�|��
     */
    public String floatingMinCoupon;
    
    /**
     * (�Ɛŋ敪)<BR>
     * �Ɛŋ敪<BR>
     * <BR>
     * 0�F�ƐŁ@@1�F���Z�҉ېŁE�񋏏Z�ҖƐ�<BR>
     * 2�F���Z�ҖƐŁE�񋏏Z�҉ېŁ@@3�F�ې�
     */
    public String taxFreeDiv;
    
    /**
     * (S&P)<BR>
     * S&P
     */
    public String sAndP;
    
    /**
     * (Moody's)<BR>
     * MOODY'S
     */
    public String moodys;
    
    /**
     * (CUSIP)<BR>
     * CUSIP
     */
    public String cusip;
    
    /**
     * @@roseuid 44E3363A0167
     */
    public WEB3AdminBondProductBasicInfo() 
    {
     
    }
}
@
