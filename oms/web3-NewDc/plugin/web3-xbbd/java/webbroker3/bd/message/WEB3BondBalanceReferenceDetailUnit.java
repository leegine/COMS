head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.38.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ��(WEB3BondBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���c���Ɖ��)<BR>
 * ���c���Ɖ��<BR>
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceDetailUnit extends Message
{
    /**
     * �ۗL���YID<BR>
     */
    public String id;
    
    /**
     * (���^�C�v )<BR>
     * ���^�C�v <BR>
     * <BR>
     * 0�F����<BR>
     * 4�F�O����<BR>
     * 8�FWB<BR>
     * 9�FCB<BR>
     * 10�F������<BR>
     */
    public String bondKind;
    
    /**
     * (���)<BR>
     * ���<BR>
     */
    public String bondCategCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (�񍆃R�[�h)<BR>
     * �񍆃R�[�h<BR>
     */
    public String productIssueCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     */
    public String taxType;
    
    /**
     * (���p�\����)<BR>
     * ���p�\����<BR>
     */
    public String sellAbleQty;
    
    /**
     * (���p�s�\����)<BR>
     * ���p�s�\����<BR>
     */
    public String sellDisableQty;
    
    /**
     * (�ʉ�)<BR>
     * �ʉ�<BR>
     */
    public String currencyCode;
    
    /**
     * (���p�i�]���j�P��)<BR>
     * ���p�i�]���j�P��<BR>
     */
    public String sellEvaluationPrice;
    
    /**
     * (�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j<BR>
     */
    public String yenEstimatedAsset;
    
    /**
     * (�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j<BR>
     */
    public String foreignEstimatedAsset;
    
    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;
    
    /**
     * (���s���i)<BR>
     * ���s���i<BR>
     */
    public String issuePrice;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;
    
    /**
     * (�N�ԗ�������)<BR>
     * �N�ԗ�������<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (�������P)<BR>
     * �������P�iMM/dd�`���j�j<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (�������Q)<BR>
     * �������Q�iMM/dd�`���j�j<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (�N����)<BR>
     * �N����<BR>
     */
    public String coupon;
       
    /**
     * (���p�\�敪)<BR>
     * ���p�\�敪<BR>
     * <BR>
     * 0�F�s��<BR>
     * 1�F��<BR>
     */
    public String sellPossDiv;
    
    /**
     * (���c���Ɖ��)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3BondBalanceReferenceDetailUnit()
    {
        
    }

}
@
