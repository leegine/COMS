head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  (�M�p����������)<BR>
                 :   �������ꎞ�i�[�N���X(WEB3MarginContractInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.util.Date;


/**
 * �i�M�p����������j�B<BR>
 * <BR>
 * �������ꎞ�i�[�N���X
 * @@version 1.0
 */
public class WEB3MarginContractInfo 
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
    public String standardName;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String accountType;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����
     * �iContractTypeEnum�ɂĒ�`�j
     */
    public String contractType;
    
    /**
     * (�ٍϋ敪)<BR>
     * 1�F���x�M�p�@@2�F��ʐM�p
     */
    public String repaymentType;
    
    /**
     * (�ٍϊ����l)<BR>
     * ���w��B<BR>
     * �������̏ꍇ�h9999999�h
     */
    public String repaymentNum;
    
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
    public String quantity;
    
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
     * (�]�����v)
     */
    public String evaluationIncome;
    
    /**
     * (�]�����v(���o��l��))<BR>
     * ���o��������������]�����v
     */
    public String takeExpensesOffEvaluationIncome;
    
    /**
     * (���萔��)
     */
    public String setupFee;
    
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
    public String transferFee;
    
    /**
     * (�Ǘ���)
     */
    public String managementFee;
    
    /**
     * (���̑�)
     */
    public String other;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * 0�F���ύρ@@1�F�����ρ@@2�F���ϒ�
     */
    public String closingStatusType;
    
    /**
     * (�ԍω\�t���O)<BR>
     * true�F�ԍω\�@@false�F�ԍϕs��
     */
    public boolean closingPossibleFlag;
    
    /**
     * (�������n�\�t���O)<BR>
     * true�F�������n�\�@@false�F�������n�s��
     */
    public boolean swapPossibleFlag;
    
    /**
     * (�M�p����������)<BR>
     * �R���X�g���N�^
     * @@roseuid 40EB7FD70197
     */
    public WEB3MarginContractInfo() 
    {
     
    }
}
@
