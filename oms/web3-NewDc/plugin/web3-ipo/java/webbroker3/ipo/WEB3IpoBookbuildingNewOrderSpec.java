head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�\�����e�N���X(WEB3IpoBookbuildingNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;

/**
 * �u�b�N�r���f�B���O�\�����e�N���X�B<BR>
 * <BR>
 * �� �ȉ��̃��\�b�h�Ɏw�肷������N���X<BR>
 * �@@IPO�\���}�l�[�W��.validate�u�b�N�r���f�B���O�\��()<BR>
 * �@@IPO�\���}�l�[�W��submit�u�b�N�r���f�B���O�\��()
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingNewOrderSpec extends NewOrderSpec 
{

    /**
     * �����h�c
     */
    private long productId;
    
    /**
     * ����
     */
    private double quantity;
    
    /**
     * �w�l<BR>
     * <BR>
     * �� ���s�̏ꍇ0�B
     */
    private double limitPrice;
    
    /**
     * ��l�i�����j
     */
    private double currentPrice = 0;
    
    /**
     * (�u�b�N�r���f�B���O�\�����e)<BR>
     * �R���X�g���N�^�B<BR>
     * �u�b�N�r���f�B���O�������e�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̃R���X�g���N�^�isuper�j���R�[���A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * [super�Ɏw�肷�����]<BR>
     * ���ҁF�@@����.����<BR>
     * �����L�������F�@@null<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Ɉ����̖����h�c�A���ʁA�w�l���Z�b�g����B<BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g
     * @@param l_lngProductId - �����h�c
     * @@param l_dblQuantity - ����
     * @@param l_dblLimitPrice - �w�l
     * @@param l_dblCurrentPrice - ��l�i�����j
     * @@return webbroker3.ipo.WEB3IpoBookbuildingNewOrderSpec
     * @@roseuid 40C03AEA001A
     */
    public WEB3IpoBookbuildingNewOrderSpec(Trader l_trader, long l_lngProductId, double l_dblQuantity, double l_dblLimitPrice, double l_dblCurrentPrice) 
    {
        super(l_trader, null);
        this.productId = l_lngProductId;
        this.quantity = l_dblQuantity;
        this.limitPrice = l_dblLimitPrice;
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (is�w�l)<BR>
     * �iisLimitOrder�j<BR>
     * <BR>
     * �ithis.�w�l == 0�j�̏ꍇfalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B
     * @@return boolean
     * @@roseuid 40C03AE903A4
     */
    public boolean isLimitOrder() 
    {
        return this.limitPrice != 0;
    }
    
    /**
     * (get�����h�c)<BR>
     * �����h�c
     * @@return long
     * @@roseuid 40C03B2B021D
     */
    public long getProductId() 
    {
        return this.productId;
    }
    
    /**
     * (get����)<BR>
     * ���ʂ��擾����B
     * @@return double
     * @@roseuid 40C03AE903C3
     */
    public double getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (get�w�l)<BR>
     * �w�l���擾����B
     * @@return double
     * @@roseuid 40C03AE903E3
     */
    public double getLimitPrice() 
    {
        return this.limitPrice;
    }
    
    /**
     * (get��l�i�����j)<BR>
     * ��l�i�����j���擾����B<BR>
     * <BR>
     * this.��l�i�����j��ԋp����B
     * @@return double
     * @@roseuid 40D8F5D30017
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
}
@
