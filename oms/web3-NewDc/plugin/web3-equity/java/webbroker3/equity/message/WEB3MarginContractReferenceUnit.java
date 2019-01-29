head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������Ɖ��(WEB3MarginContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p��������Ɖ�ׁj�B<br>
 * <br>
 * �M�p��������Ɖ�׃N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractReferenceUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * �����h�c
     */
    public String id;
    
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (������)
     */
    public String productName;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String taxType;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     * �iContractTypeEnum�ɂĒ�`�j<BR>
     */
    public String contractType;
    
    /**
     * (�ٍ�)<BR>
     * �M�p����ٍ�
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (����)
     */
    public Date openDate;
    
    /**
     * (���P��)
     */
    public String contractPrice;
    
    /**
     * (������)
     */
    public String contractOrderQuantity;
    
    /**
     * (����)<BR>
     * ��ʐM�p�A�������̏ꍇ�h99991231�h
     */
    public Date closeDate;
    
    /**
     * (�����)
     */
    public String contractExecPrice;
    
    /**
     * (���萔��)
     */
    public String contractCommission;
    
    /**
     * (������)
     */
    public String interestFee;
    
    /**
     * (�t����)
     */
    public String payInterestFee;
    
    /**
     * (�݊���)
     */
    public String loanEquityFee;
    
    /**
     * (������)
     */
    public String setupFee;
    
    /**
     * (�Ǘ���)
     */
    public String managementFee;
    
    /**
     * (���̑�)
     */
    public String otherwise;
    
    /**
     * (�]�����v)
     */
    public String appraisalProfitLoss;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * 0�F���ύρ@@1�F�����ρ@@2�F���ϒ�
     */
    public String settlementState;
    
    /**
     * (�ԍω\�t���O)<BR>
     * true�F�ԍω\�@@false�F�ԍϕs��
     */
    public boolean closeMarginFlag;
    
    /**
     * (�������n�\�t���O)<BR>
     * true�F�������n�\�@@false�F�������n�s��
     */
    public boolean swapFlag;
    
    /**
     * (�M�p��������Ɖ��)<BR>
     * �R���X�g���N�^�B
     * @@return webbroker3.margin.message.WEB3MarginContractReferenceUnit
     * @@roseuid 40E3F0B7013F
     */
    public WEB3MarginContractReferenceUnit() 
    {
     
    }
}
@
