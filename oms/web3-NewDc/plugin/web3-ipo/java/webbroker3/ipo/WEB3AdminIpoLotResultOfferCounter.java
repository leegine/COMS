head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferCounter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʁE�w���\���󋵏W�v���ʃf�[�^(Counter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo;

import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3LogUtility;


/**
 * ���I���ʁE�w���\���󋵏W�v���ʃf�[�^�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultOfferCounter 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotResultOfferCounter.class);
    
    /**
     * IPO�����I�u�W�F�N�g
     */
    private WEB3IpoProductImpl ipoProduct;
    
    /**
     * ���I�ҍw���\������
     */
    private double prizerApplicationQuantity = 0;
    
    /**
     * ���I�ҍw���\������
     */
    private double prizerOfferNumber = 0;
    
    /**
     * ���I�Ҏ��ސ���
     */
    private double prizerDeclineQuantity = 0;
    
    /**
     * ���I�Ҏ��ތ���
     */
    private double prizerDeclineNumber = 0;
    
    /**
     * ���I�Җ��萔��
     */
    private double prizerUndecideQuantity = 0;
    
    /**
     * ���I�Җ��茏��
     */
    private double prizerUndecideNumber = 0;
    
    /**
     * �⌇�ҍw���\������
     */
    private double waitingApplicationQuantity = 0;
    
    /**
     * �⌇�ҍw���\������
     */
    private double waitingOfferNumber = 0;
    
    /**
     * �⌇�Ҏ��ސ���
     */
    private double waitingDeclineQuantity = 0;
    
    /**
     * �⌇�Ҏ��ތ���
     */
    private double waitingDeclineNumber = 0;
    
    /**
     * �⌇�Җ��萔��
     */
    private double waitingUndecideQuantity = 0;
    
    /**
     * �⌇�Җ��茏��
     */
    private double waitingUndecideNumber = 0;
    
    /**
     * ���I�Ҍ���
     */
    private double rejectedNumber = 0;
    
    /**
     * �⌇���I����
     */
    private double waitingPrizeQuantity = 0;
    
    /**
     * �⌇���I����
     */
    private double waitingPrizeNumber = 0;
    
    /**
     * �⌇���I����
     */
    private double waitingRejectedNumber = 0;
    
    /**
     * �f�t�H���g�R���X�g���N�^�B
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.WEB3AdminIpoLotResultOfferCounter
     * @@roseuid 40EBA80E01B4
     */
    public WEB3AdminIpoLotResultOfferCounter(WEB3IpoProductImpl l_ipoProduct) 
    {
        this.ipoProduct = l_ipoProduct;
    }
    
    /**
     * (get���I�ҍw���\������)<BR>
     * this.���I�ҍw���\�����ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB5FB0221
     */
    public double getPrizerApplicationQuantity() 
    {
        return this.prizerApplicationQuantity;
    }
    
    /**
     * (get���I�ҍw���\������)<BR>
     * this.���I�ҍw���\��������ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB61D03E7
     */
    public double getPrizerOfferNumber() 
    {
        return this.prizerOfferNumber;
    }
    
    /**
     * (get���I�Ҏ��ސ���)<BR>
     * this.���I�Ҏ��ސ��ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB656002D
     */
    public double getPrizerDeclineQuantity() 
    {
        return this.prizerDeclineQuantity;
    }
    
    /**
     * (get���I�Ҏ��ތ���)<BR>
     * this.���I�Ҏ��ތ�����ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB66303D7
     */
    public double getPrizerDeclineNumber() 
    {
        return this.prizerDeclineNumber;
    }
    
    /**
     * (get���I�Җ��萔��)<BR>
     * this.���I�Җ��萔�ʂ�ԋp����B <BR>
     * @@return double
     * @@roseuid 40EBB67A0137
     */
    public double getPrizerUndecideQuantity() 
    {
        return this.prizerUndecideQuantity;
    }
    
    /**
     * (get���I�Җ��茏��)<BR>
     * this.���I�Җ��茏����ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6850231
     */
    public double getPrizerUndecideNumber() 
    {
        return this.prizerUndecideNumber;
    }
    
    /**
     * (get���I�ҍ��v����)<BR>
     * ���I�ҍ��v�������擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.get���I�ҍw���\������() + this.get���I�Ҏ��ތ���() <BR>
     * + this.get���I�Җ��茏��()<BR>
     * @@return double
     * @@roseuid 40EBBE5C00AA
     */
    public double getPrizerTotalNumber() 
    {
        log.debug("this.getPrizerOfferNumber() = " + this.getPrizerOfferNumber());
        log.debug("this.getPrizerDeclineNumber() = " + this.getPrizerDeclineNumber());
        log.debug("this.getPrizerUndecideNumber() = " + this.getPrizerUndecideNumber());
        return (this.getPrizerOfferNumber() + this.getPrizerDeclineNumber() + this.getPrizerUndecideNumber());
    }
    
    /**
     * (get���I�ҍ��v����)<BR>
     * ���I�ҍ��v���ʂ��擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.get���I�ҍw���\������() + this.get���I�Ҏ��ސ���() + <BR>
     * this.get���I�Җ��萔��()<BR>
     * @@return double
     * @@roseuid 40EBC4630185
     */
    public double getPrizerTotalQuantity() 
    {
        log.debug("this.getPrizerApplicationQuantity() = " + this.getPrizerApplicationQuantity());
        log.debug("this.getPrizerDeclineQuantity() = " + this.getPrizerDeclineQuantity());
        log.debug("this.getPrizerUndecideQuantity() = " + this.getPrizerUndecideQuantity());
        return (this.getPrizerApplicationQuantity() + this.getPrizerDeclineQuantity() + this.getPrizerUndecideQuantity());
    }
    
    /**
     * (get���I�ҍw���m�茏��)<BR>
     * ���I�ҍw���\���������擾����B<BR>
     * <BR>
     * �ȉ���ԋp����B<BR>
     * <BR>
     * this.get���I�ҍw���\������()<BR>
     * @@return double
     * @@roseuid 40EBC49402CD
     */
    public double getPrizerDecisionNumber() 
    {
        return this.getPrizerOfferNumber();
    }
    
    /**
     * (get���I�ҍw���m�萔��)<BR>
     * ���I�ҍw���\�����ʂ��擾����B<BR>
     * <BR>
     * �ȉ���ԋp����B<BR>
     * <BR>
     * this.get���I�ҍw���\������()<BR>
     * @@return double
     * @@roseuid 40EBC49402DD
     */
    public double getPrizerDecisionQuantity() 
    {
        return this.getPrizerApplicationQuantity();
    }
    
    /**
     * (get���I�Ҏ��ޗ��I����)<BR>
     * ���I�Ҏ��ޗ��I������ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă���ꍇ�ithis.IPO����.is�w���\���I���i���Аݒ�j() == true�j<BR>
     * �@@�|�ithis.���I�Ҏ��ތ��� + this.���I�Җ��茏���j��ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă��Ȃ��ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == false�j<BR>
     * �@@�|this.���I�Ҏ��ތ�����ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBC4E8000E
     */
    public double getPrizerDeclineRejectedNumber() 
    {
        final String STR_METHOD_NAME = " getPrizerDeclineRejectedNumber()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineNumber = " + this.prizerDeclineNumber);
            log.debug("this.prizerUndecideNumber = " + this.prizerUndecideNumber);
            return (this.prizerDeclineNumber + this.prizerUndecideNumber);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineNumber = " + this.prizerDeclineNumber);
            return this.prizerDeclineNumber;
        }
    }
    
    /**
     * (get���I�Ҏ��ޗ��I����)<BR>
     * ���I�Ҏ��ޗ��I���ʂ�ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă���ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == true�j<BR>
     * �@@�|�ithis.���I�Ҏ��ސ��� + this.���I�Җ��萔�ʁj��ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă��Ȃ��ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == false�j<BR>
     * �@@�|this.���I�Ҏ��ސ��ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBC4E8001E
     */
    public double getPrizerDeclineRejectedQuantity() 
    {
        final String STR_METHOD_NAME = " getPrizerDeclineRejectedQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineQuantity = " + this.prizerDeclineQuantity);
            log.debug("this.prizerUndecideQuantity = " + this.prizerUndecideQuantity);
            return (this.prizerDeclineQuantity + this.prizerUndecideQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.prizerDeclineQuantity = " + this.prizerDeclineQuantity);
            return this.prizerDeclineQuantity;
        }
    }
    
    /**
     * (get�⌇�ҍw���\������)<BR>
     * this.�⌇�ҍw���\�����ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0176
     */
    public double getWaitingApplicationQuantity() 
    {
        return this.waitingApplicationQuantity;
    }
    
    /**
     * (get�⌇�ҍw���\������)<BR>
     * this.�⌇�ҍw���\��������ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0177
     */
    public double getWaitingOfferNumber() 
    {
        return this.waitingOfferNumber;
    }
    
    /**
     * (get�⌇�Ҏ��ސ���)<BR>
     * this.�⌇�Ҏ��ސ��ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0185
     */
    public double getWaitingDeclineQuantity() 
    {
        return this.waitingDeclineQuantity;
    }
    
    /**
     * (get�⌇�Ҏ��ތ���)<BR>
     * this.�⌇�Ҏ��ތ�����ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0186
     */
    public double getWaitingDeclineNumber() 
    {
        return this.waitingDeclineNumber;
    }
    
    /**
     * (get�⌇�Җ��萔��)<BR>
     * this.�⌇�Җ��萔�ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0187
     */
    public double getWaitingUndecideQuantity() 
    {
        return this.waitingUndecideQuantity;
    }
    
    /**
     * (get�⌇�Җ��茏��)<BR>
     * this.�⌇�Җ��茏����ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB6BF0188
     */
    public double getWaitingUndecideNumber() 
    {
        return this.waitingUndecideNumber;
    }
    
    /**
     * (get�⌇�ҍ��v����)<BR>
     * �⌇�ҍ��v�������擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.get�⌇�ҍw���\������() + this.get�⌇�Ҏ��ތ���() + <BR>this.get�⌇�Җ��茏��()<BR>
     * @@return double
     * @@roseuid 40EBD684002D
     */
    public double getWaitingTotalNumber() 
    {
        log.debug("this.getWaitingOfferNumber() = " + this.getWaitingOfferNumber());
        log.debug("this.getWaitingDeclineNumber() = " + this.getWaitingDeclineNumber());
        log.debug("this.getWaitingUndecideNumber() = " + this.getWaitingUndecideNumber());
        return this.getWaitingOfferNumber() + this.getWaitingDeclineNumber() + this.getWaitingUndecideNumber();
    }
    
    /**
     * (get�⌇�ҍ��v����)<BR>
     * �⌇�ҍ��v���ʂ��擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.get�⌇�ҍw���\������() + this.get�⌇�Ҏ��ސ���() + <BR>this.get�⌇�Җ��萔��()<BR>
     * @@return double
     * @@roseuid 40EBD684002E
     */
    public double getWaitingTotalQuantity() 
    {
        log.debug("this.getWaitingApplicationQuantity() = " + this.getWaitingApplicationQuantity());
        log.debug("this.getWaitingDeclineQuantity() = " + this.getWaitingDeclineQuantity());
        log.debug("this.getWaitingUndecideQuantity() = " + this.getWaitingUndecideQuantity());
        return this.getWaitingApplicationQuantity() + this.getWaitingDeclineQuantity() + this.getWaitingUndecideQuantity();
    }
    
    /**
     * (get�⌇�ҍw���m�茏��)<BR>
     * �⌇�ҍw���m�茏�����擾����B<BR>
     * <BR>
     * this.�⌇���I������ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBD684002F
     */
    public double getWaitingDecisionNumber() 
    {
        return this.waitingPrizeNumber;
    }
    
    /**
     * (get�⌇�ҍw���m�萔��)<BR>
     * �⌇�ҍw���m�萔�ʂ��擾����B<BR>
     * <BR>
     * this.�⌇���I���ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBD684003D
     */
    public double getWaitingDecisionQuantity() 
    {
        return this.waitingPrizeQuantity;
    }
    
    /**
     * (get�⌇�Ҏ��ޗ��I����)<BR>
     * �⌇�Ҏ��ޗ��I������ԋp����B<BR>
     * <BR>
     * �ithis.�⌇�Ҏ��ތ��� + this.�⌇���I�����j��ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBD684003E
     */
    public double getWaitingDeclineRejectedNumber() 
    {
        log.debug("this.waitingDeclineNumber = " + this.waitingDeclineNumber);
        log.debug("this.waitingRejectedNumber = " + this.waitingRejectedNumber);
        return this.waitingDeclineNumber + this.waitingRejectedNumber;
    }
    
    /**
     * (get�⌇�Ҏ��ޗ��I����)<BR>
     * �⌇�Ҏ��ޗ��I���ʂ�ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă���ꍇ�ithis.IPO����.is�w���\���I���i���Аݒ�j() == true�j<BR>
     * �@@�|�ithis.�⌇�Ҏ��ސ��� + this.�⌇�Җ��萔�ʁj��ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă��Ȃ��ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == false�j<BR>
     * �@@�|this.�⌇�Ҏ��ސ��ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBD684003F
     */
    public double getWaitingDeclineRejectedQuantity() 
    {
        final String STR_METHOD_NAME = " getWaitingDeclineRejectedQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.waitingDeclineQuantity = " + this.waitingDeclineQuantity);
            log.debug("this.waitingUndecideQuantity = " + this.waitingUndecideQuantity);
            return (this.waitingDeclineQuantity + this.waitingUndecideQuantity);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("this.waitingDeclineQuantity = " + this.waitingDeclineQuantity);
            return this.waitingDeclineQuantity;
        }
    }
    
    /**
     * (get���I����)<BR>
     * this.���I������ԋp����B<BR>
     * @@return double
     * @@roseuid 40EBB93A029E
     */
    public double getRejectedNumber() 
    {
        return this.rejectedNumber;
    }
    
    /**
     * (add���I�ҍw���\������)<BR>
     * ���I�ҍw���\�����ʂɈ����̐��ʂ����Z����B<BR>
     * ���I�ҍw���\���������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.���I�ҍw���\������ = �ithis.get���I�ҍw���\������() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.���I�ҍw���\������ = �ithis.get���I�ҍw���\������() + 1�j<BR>
     * <BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBB70B005C
     */
    public void addPrizerApplicationQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.prizerApplicationQuantity = this.getPrizerApplicationQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.prizerOfferNumber = this.getPrizerOfferNumber() + 1;
    }
    
    /**
     * (add���I�Ҏ��ސ���)<BR>
     * ���I�Ҏ��ސ��ʂɈ����̐��ʂ����Z���A<BR>
     * ���I�Ҏ��ތ������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.���I�Ҏ��ސ��� = �ithis.get���I�Ҏ��ސ���() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.���I�Ҏ��ތ��� = �ithis.get���I�Ҏ��ތ���() + 1�j<BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBB7F20260
     */
    public void addPrizerDeclineQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.prizerDeclineQuantity = this.getPrizerDeclineQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.prizerDeclineNumber = this.getPrizerDeclineNumber() + 1;
    }
    
    /**
     * (add���I�Җ��萔��)<BR>
     * ���I�Җ��萔�ʂɈ����̐��ʂ����Z���A<BR>
     * ���I�Җ��茏�����C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.���I�Җ��萔�� = �ithis.get���I�Җ��萔��() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.���I�Җ��茏�� = �ithis.get���I�Җ��茏��() + 1�j<BR>
     * <BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBB84E01E3
     */
    public void addPrizerUndecideQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.prizerUndecideQuantity = this.getPrizerUndecideQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.prizerUndecideNumber = this.getPrizerUndecideNumber() + 1;
    }
    
    /**
     * (add�⌇�ҍw���\������)<BR>
     * �⌇�ҍw���\�����ʂɈ����̐��ʂ����Z���A<BR>
     * �⌇�ҍw���\���������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.�⌇�ҍw���\������ = <BR>
     *  �ithis.get�⌇�ҍw���\������() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.�⌇�ҍw���\������ = <BR>
     *  �ithis.get�⌇�ҍw���\������() + 1�j<BR>
     * <BR>
     * @@param l_dblQuantity - �w���\������
     * @@roseuid 40EBB8830185
     */
    public void addWaitingApplicationQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.waitingApplicationQuantity = this.getWaitingApplicationQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.waitingOfferNumber = this.getWaitingOfferNumber() + 1;
    }
    
    /**
     * (add�⌇�Ҏ��ސ���)<BR>
     * �⌇�Ҏ��ސ��ʂɈ����̐��ʂ����Z���A<BR>
     * �⌇�Ҏ��ތ������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.�⌇�Ҏ��ސ��� = �ithis.get�⌇�Ҏ��ސ���() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.�⌇�Ҏ��ތ��� = �ithis.get�⌇�Ҏ��ތ���() + 1�j<BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBB8830187
     */
    public void addWaitingDeclineQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.waitingDeclineQuantity = this.getWaitingDeclineQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.waitingDeclineNumber = this.getWaitingDeclineNumber() + 1;
    }
    
    /**
     * (add�⌇�Җ��萔��)<BR>
     * �⌇�Җ��萔�ʂɈ����̐��ʂ����Z���A<BR>
     * �⌇�Җ��茏�����C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.�⌇�Җ��萔�� = �ithis.get�⌇�Җ��萔��() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.�⌇�Җ��茏�� = �ithis.get�⌇�Җ��茏��() + 1�j<BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBB8830195
     */
    public void addWaitingUndecideQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.waitingUndecideQuantity = this.getWaitingUndecideQuantity() + l_dblQuantity;
        
        //�����C���N�������g
        this.waitingUndecideNumber = this.getWaitingUndecideNumber() + 1;
    }
    
    /**
     * (add�⌇���I����)<BR>
     * �⌇���I���ʂɈ����̐��ʂ����Z���A<BR>
     * �⌇���I�������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@���ʒǉ�<BR>
     * this.�⌇���I���� = �ithis.�⌇���I����() + ���ʁj<BR>
     * <BR>
     * �Q�j�@@�����C���N�������g<BR>
     * this.�⌇���I���� = �ithis.�⌇���I����() + 1�j<BR>
     * @@param l_dblQuantity - ����
     * @@roseuid 40EBBCAD00D9
     */
    public void addWaitingPrizeQuantity(double l_dblQuantity) 
    {
        //���ʒǉ�
        this.waitingPrizeQuantity = this.waitingPrizeQuantity + l_dblQuantity;
        log.debug("***************" + this.waitingPrizeQuantity);
        
        //�����C���N�������g
        this.waitingPrizeNumber = this.waitingPrizeNumber + 1;
        log.debug("***************" + this.waitingPrizeNumber);
    }
    
    /**
     * (add�⌇���I����)<BR>
     * �⌇���I�������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@�����C���N�������g<BR>
     * this.�⌇���I���� = �ithis.�⌇���I����() + 1�j<BR>
     * @@roseuid 40F79E0A0076
     */
    public void addWaitingRejectedNumber() 
    {
        this.waitingRejectedNumber = this.waitingRejectedNumber + 1;
        log.debug("**************" + this.waitingRejectedNumber);
    }
    
    /**
     * (add���I����)<BR>
     * ���I�������C���N�������g����B<BR>
     * <BR>
     * �P�j�@@�����C���N�������g<BR>
     * this.���I���� = �ithis.get���I����() + 1�j<BR>
     * @@roseuid 40EBB8EA0147
     */
    public void addRejectedNumber() 
    {
        this.rejectedNumber = this.getRejectedNumber() + 1;
    }
    
    /**
     * (get�����m�萔��)<BR>
     * �����m�萔�ʂ��擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.get���I�ҍw���m�萔��() + this.get�⌇�ҍw���m�萔��()<BR>
     * @@return double
     * @@roseuid 40EBD89103A8
     */
    public double getAllotQuantity() 
    {
        return (this.getPrizerDecisionQuantity() + this.getWaitingDecisionQuantity());
    }
    
    /**
     * (get�J��\����)<BR>
     * �J��\���ʂ��擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * this.getIPO����.IPO�����s.���Ў戵���� - <BR>�ithis.get�����m�萔��() + this.get�J��҂�����()�j <BR>
     * @@return double
     * @@roseuid 40EBD8F202DD
     */
    public double getAdvanceQuantity() 
    {
        final String STR_METHOD_NAME = " getAdvanceQuantity()";
        log.entering(STR_METHOD_NAME);
        
        IpoProductRow l_ipoProductRow = (IpoProductRow)this.ipoProduct.getDataSourceObject();
        long l_lngDealingQuantity = l_ipoProductRow.getDealingQuantity();
        double l_lngQuantity = this.getAllotQuantity() + this.getAdvanceWaitQuantity();
        
        log.exiting(STR_METHOD_NAME);
        return (l_lngDealingQuantity - l_lngQuantity);
    }
    
    /**
     * (get�J��҂�����)<BR>
     * �J��҂����ʂ��擾����B<BR>
     * <BR>
     * ���w���\�����I�����Ă���ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == true�j<BR>
     * �@@�|0��ԋp����B<BR>
     * <BR>
     * ���w���\�����I�����Ă��Ȃ��ꍇ<BR>�ithis.IPO����.is�w���\���I���i���Аݒ�j() == false�j<BR>
     * �@@�|this.���I�Җ��萔�ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EBDB8B027F
     */
    public double getAdvanceWaitQuantity() 
    {
        final String STR_METHOD_NAME = " getAdvanceWaitQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if (this.ipoProduct.isOfferEnd())
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.prizerUndecideQuantity;
        }
    }
}
@
