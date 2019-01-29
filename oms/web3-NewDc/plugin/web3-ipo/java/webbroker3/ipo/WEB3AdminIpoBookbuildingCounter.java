head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingCounter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�󋵏W�v���ʃf�[�^(WEB3AdminIpoBookbuildingCounter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo;




/**
 * �u�b�N�r���f�B���O�󋵏W�v���ʃf�[�^�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingCounter 
{
   
    /**
     * ���I�Ώې\������
     */
    private double lotTargetOrderNumber = 0;
    
    /**
     * �������
     */
    private double cancelNumber = 0;
    
    /**
     * ���I�Ώې\������
     */
    private double lotTargetOrderQuantity = 0;
    
    /**
     * �������
     */
    private double cancelQuantity = 0;
    
    /**
     * �\�����i�i�v�Z�P���j�̍��v�l
     */
    private double orderPriceTotal = 0;
    
    /**
     * �o���]�͕ێ��ҍ��v�l��
     */
    private long tradingPowerHolderTotalNumber = 0;
    
    /**
     * �o���]�͕ێ��ҍ��v����
     */
    private double tradingPowerHolderTotalQuantity = 0;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@return webbroker3.ipo.WEB3AdminIPOBookBuildingStateTotalResult
     * @@roseuid 40E36D9803B6
     */
    public WEB3AdminIpoBookbuildingCounter() 
    {
     
    }
    
    /**
     * (get���I�Ώې\������)<BR>
     * ���I�Ώې\���������擾����B<BR>
     * <BR>
     * this.���I�Ώې\��������ԋp����B<BR>
     * @@return double
     * @@roseuid 40E370C500C8
     */
    public double getLotTargetOrderNumber() 
    {
        return this.lotTargetOrderNumber;
    }
    
    /**
     * (get�������)<BR>
     * ����������擾����B<BR>
     * <BR>
     * this.���������ԋp����B<BR>
     * @@return double
     * @@roseuid 40E370E60145
     */
    public double getCancelNumber() 
    {
        return this.cancelNumber;
    }
    
    /**
     * (get�S�\������)<BR>
     * �S�\���������擾����B<BR>
     * <BR>
     * �ithis.get���I�Ώې\������() + this.get�������()�j��ԋp����B<BR>
     * @@return double
     * @@roseuid 40E3715E0193
     */
    public double getAllOrderNumber() 
    {
        return (this.getLotTargetOrderNumber() + this.getCancelNumber());
    }
    
    /**
     * (get���ϐ\�����i)<BR>
     * ���ϐ\�����i���擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.�\�����i���v / this.get���I�Ώې\������()<BR>
     * <BR>
     * �� �v�Z���ʂ̏����_�ȉ���3�ʂ��l�̌ܓ��B<BR>
     * �@@�i�����_�ȉ���2�ʂ܂ŗL���j<BR>
     * @@return double
     * @@roseuid 40E3721100B8
     */
    public double getAverageOrderPrice() 
    {
    	// 2004/12/06 ��Q�Ǘ��[No.U00519 �����_��3�ʂ��l�̌ܓ����ĕԋp����悤�ɏC�� ���@@SRA START
    	return Math.round(this.orderPriceTotal / this.getLotTargetOrderNumber() * 100) / 100.0;
//        return (this.orderPriceTotal / this.getLotTargetOrderNumber());
		// 2004/12/06 ��Q�Ǘ��[No.U00519 �����_��3�ʂ��l�̌ܓ����ĕԋp����悤�ɏC�� ���@@SRA END
    }
    
    /**
     * (get���I�Ώې\������)<BR>
     * ���I�Ώې\�����ʂ��擾����B<BR>
     * <BR>
     * this.���I�Ώې\�����ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40E372D0025E
     */
    public double getLotTargetOrderQuantity() 
    {
        return this.lotTargetOrderQuantity;
    }
    
    /**
     * (get�������)<BR>
     * ������ʂ��擾����B<BR>
     * <BR>
     * this.������ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40E372E503B6
     */
    public double getCancelQuantity() 
    {
        return this.cancelQuantity;
    }
    
    /**
     * (get�S�\������)<BR>
     * �S�\�����ʂ��擾����B<BR>
     * <BR>
     * �ithis.get���I�Ώې\������() + this.get�������()�j��ԋp����B<BR>
     * @@return double
     * @@roseuid 40E372FA027D
     */
    public double getAllOrderQuantity() 
    {
        return (this.getLotTargetOrderQuantity() + this.getCancelQuantity());
    }
    
    /**
     * (get�o���]�͕ێ��ҍ��v����)<BR>
     * �o���]�͕ێ��ҍ��v���ʂ��擾����B<BR>
     * <BR>
     * this.�o���]�͕ێ��ҍ��v���ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40ED29960169
     */
    public double getTradingPowerHolderTotalQuantity() 
    {
        return this.tradingPowerHolderTotalQuantity;
    }
    
    /**
     * (get�o���]�͕ێ��ҍ��v�l��)<BR>
     * �o���]�͕ێ��ҍ��v�l�����擾����B<BR>
     * <BR>
     * this.�o���]�͕ێ��ҍ��v�l����ԋp����B<BR>
     * @@return long
     * @@roseuid 40ED29A9039C
     */
    public long getTradingPowerHolderTotalNumber() 
    {
        return this.tradingPowerHolderTotalNumber;
    }
    
    /**
     * (add���I�Ώې\������)<BR>
     * ���I�Ώې\�����ʂɈ����̐��ʂ����Z���A<BR>
     * ���I�Ώې\���������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@�\�����ʒǉ�<BR>
     * this.���I�Ώې\������ = �ithis.get���I�Ώې\������() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�\�������C���N�������g<BR>
     * this.add���I�Ώې\������()<BR>
     * @@param l_dblQuantity - (����)<BR>
     * �\������<BR>
     * @@roseuid 40E373300164
     */
    public void addLotTargetOrderQuantity(double l_dblQuantity) 
    {
        //�\�����ʒǉ�
        this.lotTargetOrderQuantity = this.getLotTargetOrderQuantity() + l_dblQuantity;

        //�\�������C���N�������g
        this.addLotTargetOrderNumber();
    }
    
    /**
     * (add�������)<BR>
     * ������ʂɈ����̐��ʂ����Z���A<BR>
     * ����������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@������ʒǉ�<BR>
     * this.������� = �ithis.get�������() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@��������C���N�������g<BR>
     * this.add�������()<BR>
     * @@param l_dblQuantity - (����)<BR>
     * �\������<BR>
     * @@roseuid 40E3742E0089
     */
    public void addCancelQuantity(double l_dblQuantity) 
    {
        //������ʒǉ�
        this.cancelQuantity = this.getCancelQuantity() + l_dblQuantity;
        
        //��������C���N�������g
        this.addCancelNumber();
    }
    
    /**
     * (add�\�����i)<BR>
     * �\�����i���v�l�ɁA�����̐\�����i�����Z����B<BR>
     * <BR>
     * this.�\�����i���v = this.get�\�����i���v() + �\�����i<BR>
     * @@param l_dblOrderPrice - �\�����i�i�v�Z�P���j
     * @@roseuid 40E3746B01D1
     */
    public void addOrderPrice(double l_dblOrderPrice) 
    {
        this.orderPriceTotal = this.orderPriceTotal + l_dblOrderPrice;
    }
    
    /**
     * (add���I�Ώې\������)<BR>
     * ���I�Ώې\���������C���N�������g����B<BR>
     * <BR>
     * this.���I�Ώې\������ = �ithis.get���I�Ώې\������() + 1�j<BR>
     * @@roseuid 40E508C802C2
     */
    public void addLotTargetOrderNumber() 
    {
        this.lotTargetOrderNumber = this.getLotTargetOrderNumber() + 1;
    }
    
    /**
     * (add�������)<BR>
     * ����������C���N�������g����B<BR>
     * <BR>
     * this.������� = �ithis.get�������() + 1�j<BR>
     * @@roseuid 40E5093A010D
     */
    public void addCancelNumber() 
    {
        this.cancelNumber = this.getCancelNumber() + 1;
    }
    
    /**
     * (add�o���]�͕ێ��ҍ��v�l��)<BR>
     * �o���]�͕ێ��ҍ��v�l�����C���N�������g����B<BR>
     * <BR>
     * this.�o���]�͕ێ��ҍ��v�l�� = �ithis.get�o���]�͕ێ��ҍ��v�l��() + 1�j<BR>
     * @@roseuid 40ED2930005F
     */
    public void addTradingPowerHolderTotalNumber() 
    {
        this.tradingPowerHolderTotalNumber = this.getTradingPowerHolderTotalNumber() + 1;
    }
    
    /**
     * (add�o���]�͕ێ��ҍ��v����)<BR>
     * �o���]�͕ێ��ҍ��v���ʂɈ����̐��ʂ����Z���A<BR>
     * �o���]�͕ێ��ҍ��v�l�����C���N�������g����B<BR>
     * <BR>
     * �P�j�@@�\�����ʒǉ�<BR>
     * this.�o���]�͕ێ��ҍ��v���� = �ithis.get�o���]�͕ێ��ҍ��v����() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�l���C���N�������g<BR>
     * this.add�o���]�͕ێ��ҍ��v�l��()<BR>
     * <BR>
     * <BR>
     * @@param l_dblQuantity - (����)<BR>
     * �\������<BR>
     * @@roseuid 40ED295F0198
     */
    public void addTradingPowerHolderTotalQuantity(double l_dblQuantity) 
    {
        this.tradingPowerHolderTotalQuantity = this.getTradingPowerHolderTotalQuantity() + l_dblQuantity;
        
        this.addTradingPowerHolderTotalNumber();
    }
}
@
