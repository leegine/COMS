head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ�s(WEB3MarginCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p������ψꗗ�s�j�B<br>
 * <br>
 * �M�p������ψꗗ�s�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginGroup extends Message 
{
    
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
     * (������)
     */
    public String contractQuantity;
    
    /**
     * (���ό��P��)
     */
    public String averageContractPrice;
    
    /**
     * (���ݒl)
     */
    public String currentPrice;
    
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
     * (�������׈ꗗ)<BR>
     * �M�p����������ׂ̈ꗗ
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * @@roseuid 414032D10087
     */
    public WEB3MarginCloseMarginGroup() 
    {
     
    }
}
@
