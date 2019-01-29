head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAndExecutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������͏��(WEB3FeqOrderAndExecutionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;


/**
 * (�O�����������͏��)<BR>
 * �O�����������͏��N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqOrderAndExecutionUnit extends WEB3FeqOrderCommonUnit 
{
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;
    
    /**
     * (���n�v)<BR>
     * ���n�v
     */
    public String passProfit;
    
    /**
     * (���n�v��)<BR>
     * ���n�v��
     */
    public String passProfitTax;
    
    /**
     * (���בփ��[�g)<BR>
     * ���בփ��[�g
     */
    public String execExchangeRate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��
     */
    public Date localDeliveryDate;
    
    /**
     * (���ڍ�)<BR>
     * �O���������ڍ׃I�u�W�F�N�g
     */
    public WEB3FeqExecDetailInfoUnit execDetailInfoUnit;
    
    /**
     * (�O�����������͏��)<BR>
     * �R���X�g���N�^
     * @@roseuid 4283146B01B1
     */
    public WEB3FeqOrderAndExecutionUnit() 
    {
     
    }
    
    /**
     * �V�����C���X�^���X�Ŏ��g�Ɠ������e�̃I�u�W�F�N�g��ԋp����B
     * @@return webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit
     * @@roseuid 42B65CCC000F
     */
    public Object clone() 
    {
        WEB3FeqOrderAndExecutionUnit l_unit = new WEB3FeqOrderAndExecutionUnit();
        
        l_unit.traderCode = this.traderCode;
        
        l_unit.passProfit = this.passProfit;
            
        l_unit.passProfitTax = this.passProfitTax;
    
        l_unit.execExchangeRate = this.execExchangeRate;
    
        l_unit.localDeliveryDate = this.localDeliveryDate;
        
        l_unit.orderId = this.orderId;
        
        l_unit.managementCode = this.managementCode;
    
        l_unit.requestNumber = this.requestNumber;
    
        l_unit.orderNumber = this.orderNumber;
    
        l_unit.branchCode = this.branchCode;
    
        l_unit.accountCode = this.accountCode;
    
        l_unit.accountName = this.accountName;
    
        l_unit.productCode = this.productCode;
    
        l_unit.localProductCode = this.localProductCode;
    
        l_unit.productName = this.productName;
    
        l_unit.marketCode = this.marketCode;
    
        l_unit.taxType = this.taxType;
    
        l_unit.dealingType = this.dealingType;
    
        l_unit.settleDiv = this.settleDiv;
    
        l_unit.execCondType = this.execCondType;
    
        l_unit.expirationDate = this.expirationDate;
    
        l_unit.orderCondType = this.orderCondType;
    
        l_unit.orderCondPrice = this.orderCondPrice;
    
        l_unit.condOperator = this.condOperator;
    
        l_unit.wLimitOrderPriceDiv = this.wLimitOrderPriceDiv;
    
        l_unit.wLimitPrice = this.wLimitPrice;
    
        l_unit.orderQuantity = this.orderQuantity;
    
        l_unit.orderPriceDiv = this.orderPriceDiv;
    
        l_unit.orderPrice = this.orderPrice;
    
        l_unit.currencyCode = this.currencyCode;
    
        l_unit.estimatedPrice = this.estimatedPrice;
    
        l_unit.foreignEstimatedPrice = this.foreignEstimatedPrice;
    
        l_unit.orderDate = this.orderDate;
    
        l_unit.orderBizDate = this.orderBizDate;
    
        l_unit.accountDiv = this.accountDiv;
        
        l_unit.execDetailInfoUnit = this.execDetailInfoUnit;
         
        return l_unit;
    }
}
@
