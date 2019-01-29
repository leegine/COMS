head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPClosingContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʕԍώw����(WEB3TPClosingContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (���ʕԍώw����)
 */
public class WEB3TPClosingContractSpec 
{
    
    /**
     * (����ID)
     */
    private long contractId;
    
    /**
     * (���P��)
     */
    private double contractPrice;
    
    /**
     * (�ԍϒ�������)
     */
    private double quantity;
    
    /**
     * (�ԍϖ�萔��)
     */
    private double executedQuantity;
    
    /**
     * @@roseuid 4104AB480203
     */
    public WEB3TPClosingContractSpec() 
    {
     
    }
    
    /**
     * (create���ʕԍώw����)<BR>
     * ���ʕԍώw����𐶐�����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec
     * @@roseuid 40EE0BC20066
     */
    public static WEB3TPClosingContractSpec create() 
    {
        return new WEB3TPClosingContractSpec();
    }
    
    /**
     * (get����ID)<BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40EDF6880289
     */
    public long getContractId() 
    {
        return contractId;
    }
    
    /**
     * (set����ID)<BR>
     * �����̌���ID���Z�b�g����B<BR>
     * @@param l_lngContractId - (����ID)
     * @@roseuid 40EDF68802A9
     */
    public void setContractId(long l_lngContractId) 
    {
        contractId = l_lngContractId;
    }
    
    /**
     * (get���P��)<BR>
     * ���P�����擾����B<BR>
     * @@return double
     * @@roseuid 40EDF68802F7
     */
    public double getContractPrice() 
    {
        return contractPrice;
    }
    
    /**
     * (set���P��)<BR>
     * �����̌��P�����Z�b�g����B<BR>
     * @@param l_dblContractPrice - (���P��)
     * @@roseuid 40EDF6880306
     */
    public void setContractPrice(double l_dblContractPrice) 
    {
        contractPrice = l_dblContractPrice;
    }
    
    /**
     * (get�ԍϒ�������)<BR>
     * �ԍϒ������ʂ��擾����B<BR>
     * @@return double
     * @@roseuid 40EDF6880326
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set�ԍϒ�������)<BR>
     * �����̕ԍϒ������ʂ��Z�b�g����B<BR>
     * @@param l_dblQuantity - (�ԍϒ�������)
     * @@roseuid 40EDF6880345
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get�ԍϖ�萔��)<BR>
     * �ԍϖ�萔�ʂ��擾����B<BR>
     * @@return double
     * @@roseuid 40EDF6880354
     */
    public double getExecutedQuantity() 
    {
        return executedQuantity;
    }
    
    /**
     * (set�ԍϖ�萔��)<BR>
     * �����̕ԍϖ�萔�ʂ��Z�b�g����B<BR>
     * @@param l_dblExecutedQuantity - (�ԍϖ�萔��)
     * @@roseuid 40EDF6880374
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity) 
    {
        executedQuantity = l_dblExecutedQuantity;
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractId", getContractId())
            .append("contractPrice", getContractPrice())
            .append("quantity", getQuantity())
            .append("executedQuantity", getExecutedQuantity())
            .toString();
    }
}
@
