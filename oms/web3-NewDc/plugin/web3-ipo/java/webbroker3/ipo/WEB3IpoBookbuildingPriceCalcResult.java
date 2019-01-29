head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingPriceCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�������z�v�Z���ʃf�[�^�N���X(WEB3IpoBookbuildingPriceCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �\�������z�v�Z���ʃf�[�^�N���X
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingPriceCalcResult 
{
    
    /**
     * �v�Z�P��
     */
    private double price = 0;
    
    /**
     * ��l�i�����j
     */
    private double currentPrice = 0;
    
    /**
     * (�\�������z)<BR>
     * ����
     */
    private double bookbuildingPrice = 0;
    
    /**
     * �v�Z�P���i�����i�j
     */
    private double calcUnitPriceTruePrice = 0;
    
    /**
     * (�\�������z�v�Z����)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40D7E5230385
     */
    public WEB3IpoBookbuildingPriceCalcResult() 
    {
     
    }
    
    /**
     * (get�v�Z�P��)<BR>
     * �v�Z�P�����擾����B<BR>
     * <BR>
     * this.�v�Z�P����ԋp����B
     * @@return double
     * @@roseuid 40D7D5BF0162
     */
    public double getPrice() 
    {
        return this.price;
    }
    
    /**
     * (get��l�i�����j)<BR>
     * ��l�i�����j���擾����B<BR>
     * <BR>
     * this.��l�i�����j��ԋp����B
     * @@return double
     * @@roseuid 40D7D5DC03D3
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
    
    /**
     * (get�\�������z)<BR>
     * �\�������z���擾����B<BR>
     * <BR>
     * this.�\�������z��ԋp����B
     * @@return double
     * @@roseuid 40D7D5DD03D3
     */
    public double getBookbuildingPrice() 
    {
        return this.bookbuildingPrice;
    }
    
    /**
     * (set�v�Z�P��)<BR>
     * �v�Z�P�����Z�b�g����B<BR>
     * <BR>
     * this.�v�Z�P���Ɉ����̌v�Z�P�����Z�b�g����B
     * @@param l_dblPrice - �v�Z�P��
     * @@roseuid 40D7D5F80327
     */
    public void setPrice(double l_dblPrice) 
    {
        this.price = l_dblPrice;
    }
    
    /**
     * (set��l�i�����j)<BR>
     * ��l�i�����j���Z�b�g����B<BR>
     * <BR>
     * this.��l�i�����j�Ɉ����̊�l�i�����j���Z�b�g����B
     * @@param l_dblCurrentPrice - ��l�i�����j
     * @@roseuid 40D7D62A03B4
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (set�\�������z)<BR>
     * �\�������z���Z�b�g����B<BR>
     * <BR>
     * this.�\�������z�Ɉ����̐\�������z���Z�b�g����B
     * @@param l_dblBookbuildingPrice - �\�������z
     * @@roseuid 40D7D62B03B4
     */
    public void setBookbuildingPrice(double l_dblBookbuildingPrice) 
    {
        this.bookbuildingPrice = l_dblBookbuildingPrice;
    }
    
    /**
     * (get�v�Z�P���i�����i�j)<BR>
     * �v�Z�P���i�����i�j���擾����B<BR>
     * <BR>
     * this.�v�Z�P���i�����i�j��ԋp����B
     * @@return double
     * @@roseuid 40EE8BBE004F
     */
    public double getCalcUnitPriceReal() 
    {
        return this.calcUnitPriceTruePrice;
    }
    
    /**
     * (set�v�Z�P���i�����i�j)<BR>
     * �v�Z�P���i�����i�j���Z�b�g����B<BR>
     * <BR>
     * this.�v�Z�P���i�����i�j�Ɉ����̌v�Z�P�����Z�b�g����B
     * @@param l_dblCalcUnitPriceReal - �v�Z�P���i�����i�j
     * @@roseuid 40EE8BBE0050
     */
    public void setCalcUnitPriceReal(double l_dblCalcUnitPriceReal) 
    {
        this.calcUnitPriceTruePrice = l_dblCalcUnitPriceReal;
    }

    /**
     * (validate�\�������z)<BR>
     * �\�������z�̍ő包���`�F�b�N���s���B<BR>
     * <BR>
     * this.�\�������z�̐��������ő�l�i�P�Q���j�𒴂��Ă���ꍇ�́A��O���X���[����B<BR>
     * @@roseuid 40EE8BBE0050
     */
    public void validateBookbuildingPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateBookbuildingPrice()";
        
        long l_lngBookbuildingPrice = (long)this.bookbuildingPrice;
        if (l_lngBookbuildingPrice > 999999999999L)
        {
            String l_strErrorMessage = "�\�������z[" + this.bookbuildingPrice + "]�̐��������ő�l�i�P�Q���j�𒴂��Ă��܂��B";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01986,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strErrorMessage);            
        }
    }
}
@
