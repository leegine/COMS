head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�������e�N���X(WEB3IpoBookbuildingChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

/**
 * �u�b�N�r���f�B���O�������e�N���X�B<BR>
 * <BR>
 * �� �ȉ��̃��\�b�h�Ɏw�肷������N���X<BR>
 * �@@IPO�\���}�l�[�W��.validate�u�b�N�r���f�B���O����()<BR>
 * �@@IPO�\���}�l�[�W��submit�u�b�N�r���f�B���O����()
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingChangeOrderSpec extends ChangeOrderSpec 
{
    
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
     * �����
     */
    private Trader trader;
    
    /**
     * ��l�i�����j
     */
    private double currentPrice = 0;
        
    /**
     * (�u�b�N�r���f�B���O�������e)<BR>
     * �R���X�g���N�^�B<BR>
     * �u�b�N�r���f�B���O�������e�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̃R���X�g���N�^�isuper�j���R�[���A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * [super�Ɏw�肷�����]<BR>
     * �����h�c�F�@@IPO�\���h�c<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Ɉ����̐��ʁA�w�l�A���҂��Z�b�g����B
     * @@param l_trader - (�����)<BR>
     * ����ҁi���ҁj�I�u�W�F�N�g
     * 
     * @@param l_lngIpoOrderId - IPO�\���h�c
     * @@param l_dblQuantity - ����
     * @@param l_dblLimitPrice - �w�l
     * @@param l_dblCurrentPrice - ��l�i�����j
     * @@return webbroker3.ipo.WEB3IPOBookBuildingChangeSpec
     * @@roseuid 40C039C90162
     */
    public WEB3IpoBookbuildingChangeOrderSpec(Trader l_trader, long l_lngIpoOrderId, double l_dblQuantity, double l_dblLimitPrice, double l_dblCurrentPrice) 
    {
        super(l_lngIpoOrderId);
        this.quantity = l_dblQuantity;
        this.limitPrice = l_dblLimitPrice;
        this.trader = l_trader;
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (is�w�l)<BR>
     * �iisLimitOrder�j<BR>
     * <BR>
     * �ithis.�w�l == 0�j�̏ꍇfalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B
     * @@return boolean
     * @@roseuid 40C038CB0191
     */
    public boolean isLimitOrder() 
    {
        return this.limitPrice != 0;
    }
    
    /**
     * (get����)<BR>
     * ���ʂ��擾����B
     * @@return double
     * @@roseuid 40C03947001A
     */
    public double getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (get�w�l)<BR>
     * �w�l���擾����B
     * @@return double
     * @@roseuid 40C039500394
     */
    public double getLimitPrice() 
    {
        return this.limitPrice;
    }
    
    /**
     * (get�����)<BR>
     * �igetTrader�j<BR>
     * <BR>
     * this.����҂�ԋp����B
     * @@return Trader
     * @@roseuid 40D95A340094
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (get��l�i�����j)<BR>
     * ��l�i�����j���擾����B<BR>
     * <BR>
     * this.��l�i�����j��ԋp����B
     * @@return double
     * @@roseuid 40D96F9902ED
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
}
@
