head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP���ʏW�v(WEB3IfoSummaryContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 nakazato(ACT) �V�K�쐬
Revesion History : 2007/07/06 hijikata(SRA) �[��Ή� ���f��No.061�E, No.069, No.074�A
Revesion History : 2007/08/02 hijikata(SRA) �[��Ή� ���f��No.104
*/

package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

/**
 * (�敨OP���ʏW�v)<BR>
 * ���ʏW�v��\���N���X�B<BR>
 */
public class WEB3IfoSummaryContract
{

    /**
     * (����ID)
     */
    private long productId;

    /**
     * (�����R�[�h)
     */
    private String productCode;

    /**
     * (�敨�I�v�V�������i�敪) <BR>
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     */
    private IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (����)
     */
    private double currentPrice;

    /**
     * (�w���搔)<BR>
     * 
     * TOPIX�F10000<BR>
     * ���o225�F1000<BR>
     * ���o300�F10000<BR>
     */
    private double unitSize;

    /**
     * (��������)
     */
    private double buyQuantity = 0;

    /**
     * (��������)
     */
    private double sellQuantity = 0;
    
    /**
     * (������������)
     */
    private double todayBuyQuantity = 0;

    /**
     * (������������)
     */
    private double todaySellQuantity = 0;
    
    /**
     * (�]�����v�v�Z�敪)<BR>
     * 0�F���������܂�<BR>
     * 1�F���������܂܂Ȃ�<BR>
     */
    private String profitLossCalcType;
    
    /**
     * (�������Z�l)
     */
    private double currentBizDateLiquidationPrice;
       
    /**
     * (�������ʁ��؋����s�����m�聄)
     */
    private double buyQuantityTemp = 0;

    /**
     * (�������ʁ��؋����s�����m�聄)
     */
    private double sellQuantityTemp = 0;
    

    /**
     * (add������)<BR>
     * 
     * ����/�����ł��邩�ɂ��ƂÂ��A�Y�����錚���ʂ̉��Z�������s���B<BR>
     * 
     * ������(����.is���� == true)�̏ꍇ<BR>
     * �@@�@@this.�������� = this.get��������( ) + ����.����<BR>
     * 
     * ������(����.is���� == false)�̏ꍇ<BR>
     * �@@�@@this.�������� = this.get��������( ) + ����.����<BR>
     * 
     * @@param l_blnIsBuy - (is����)<BR>
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_dblQuantity - (����)<BR>
     * @@roseuid 412B45740230
     */
    public void addQuantity(boolean l_blnIsBuy, double l_dblQuantity)
    {
    	
        //�����̏ꍇ
        if (l_blnIsBuy == true)
        {
            //this.�������ʂɈ���.�����ʂ����Z����B
            this.buyQuantity = this.buyQuantity + l_dblQuantity;

        }
        //�����̏ꍇ     
        else
        {
            //this.�������ʂɈ���.�����ʂ����Z����
            this.sellQuantity = this.sellQuantity + l_dblQuantity;
        }
    }
    
    /**
     * (add����������)<BR>
     * 
     * ����/�����ł��邩�ɂ��ƂÂ��A�Y�����铖�������ʂ̉��Z�������s���B<BR>
     * 
     * ������(����.is���� == true)�̏ꍇ<BR>
     * �@@�@@this.������������ = this.get������������( ) + ����.����<BR>
     * 
     * ������(����.is���� == false)�̏ꍇ<BR>
     * �@@�@@this.������������ = this.get������������( ) + ����.����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_dblQuantity - (����)
     */
    public void addTodayQuantity(boolean l_blnIsBuy, double l_dblQuantity)
    {
        //�����̏ꍇ
        if (l_blnIsBuy == true)
        {
            //this.�������ʂɈ���.�����ʂ����Z����B
            this.todayBuyQuantity = this.todayBuyQuantity + l_dblQuantity;
        }
        //�����̏ꍇ     
        else
        {
            //this.�������ʂɈ���.�����ʂ����Z����
            this.todaySellQuantity = this.todaySellQuantity + l_dblQuantity;
        }
    }
     
    /**
     * (add�����ʁ��؋����s�����m�聄)<BR>
     * 
     * ����/�����ł��邩�ɂ��ƂÂ��A�Y�����錚���ʁ��؋����s�����m�聄�̉��Z�������s���B<BR>
     * 
     * ������(����.is���� == true)�̏ꍇ<BR>
     * �@@�@@this.�������ʁ��؋����s�����m�聄 = this.get�������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     * 
     * ������(����.is���� == false)�̏ꍇ<BR>
     * �@@�@@this.�������ʁ��؋����s�����m�聄 = this.get�������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_dblQuantity - (����)
     */
    public void addQuantityTemp(boolean l_blnIsBuy, double l_dblQuantity)
    {
        //�����̏ꍇ
        if (l_blnIsBuy == true)
        {
            //this.�������ʁ��؋����s�����m�聄�Ɉ���.�����ʂ����Z����B
            this.buyQuantityTemp = this.getBuyQuantityTemp() + l_dblQuantity;
        }
        //�����̏ꍇ     
        else
        {
            //this.�������ʁ��؋����s�����m�聄�Ɉ���.�����ʂ����Z����
            this.sellQuantityTemp = this.getSellQuantityTemp() + l_dblQuantity;
        }
    }

   /**
     * (set����ID)<BR>
     * 
     * ����.����ID��this.����ID�ɃZ�b�g����B<BR>
     * @@param lo_lngProductId - (����ID)<BR>
     * @@roseuid 4124859802AA
     */
    public void setProductId(long l_lngProductId)
    {
        this.productId = l_lngProductId;
    }

    /**
     * (set�����R�[�h)<BR>
     * 
     * ����.�����R�[�h��this.�����R�[�h�ɃZ�b�g����B<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@roseuid 412485550095
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * (set�敨�I�v�V�������i�敪)<BR>
     *
     * ����.�敨�I�v�V�������i�敪��this.�敨�I�v�V�������i�敪�ɃZ�b�g����B<BR>
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i�敪)<BR>
     * 
     * 
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     * @@roseuid 412484CD01FB
     */
    public void setIfoDerivativeType(IfoDerivativeTypeEnum l_ifoDerivativeType)
    {
        this.ifoDerivativeType = l_ifoDerivativeType;
    }

    /**
     * (set����)<BR>
     * 
     * ����.������this.�����ɃZ�b�g����B<BR>
     * @@param l_dblCurrentPrice - (����)
     * @@roseuid 412485D301DF
     */
    public void setCurrentPrice(double l_dblCurrentPrice)
    {
        this.currentPrice = l_dblCurrentPrice;
    }

    /**
     * (set�w���搔)<BR>
     * 
     * ����.�w���搔��this.�w���搔�ɃZ�b�g����B<BR>
     * @@param l_dblUnitSize - (�w���搔)
     * @@roseuid 41248603021E
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        this.unitSize = l_dblUnitSize;
    }

    /**
     * (set��������)<BR>
     * 
     * ����.�������ʂ�this.�������ʂɃZ�b�g����B<BR>
     * @@param l_dblBuyQuantity - (��������)<BR>
     */
    public void setBuyQuantity(double l_dblBuyQuantity)
    {
        this.buyQuantity = l_dblBuyQuantity;
    }

    /**
     * (set��������)<BR>
     * 
     * ����.�������ʂ�this.�������ʂɃZ�b�g����B<BR>
     * @@param l_dblSellQuantity - (��������)<BR>
     */
    public void setSellQuantity(double l_dblSellQuantity)
    {
        this.sellQuantity = l_dblSellQuantity;
    }
    
    /**
     * (set�]�����v�v�Z�敪)<BR>
     *
     * ����.�]�����v�v�Z�敪��this.�]�����v�v�Z�敪�ɃZ�b�g����B<BR>
     * @@param l_strProfitLossCalcType - (�]�����v�v�Z�敪)<BR>
     * 
     * 0�F���������܂�<BR>
     * 1�F���������܂܂Ȃ�<BR>
     */
    public void setProfitLossCalcType(String l_strProfitLossCalcType)
    {
        this.profitLossCalcType = l_strProfitLossCalcType;
    }
    /**
     * (set�������Z�l)<BR>
     * 
     * ����.�������Z�l��this.�������Z�l�ɃZ�b�g����B<BR>
     * @@param dblCurrentBizDateLiquidationPrice - (�������Z�l)<BR>
     */
    public void setCurrentBizDateLiquidationPrice(double l_dblCurrentBizDateLiquidationPrice)
    {
        this.currentBizDateLiquidationPrice = l_dblCurrentBizDateLiquidationPrice;
    }


    /**
     * (get����ID)<BR>
     * 
     * this.����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 412486C40156
     */
    public long getProductId()
    {
        return this.productId;
    }

    /**
     * (get�����R�[�h)<BR>
     * 
     * this.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 412486F103E7
     */
    public String getProductCode()
    {
        return this.productCode;
    }

    /**
     * (get�敨�I�v�V�������i�敪)<BR>
     *
     * this.�敨�I�v�V�������i�敪��ԋp����B<BR>
     * @@return IfoDerivativeTypeEnum
     */
    public IfoDerivativeTypeEnum getIfoDerivativeType()
    {
        return this.ifoDerivativeType;
    }

    /**
     * (get����)<BR>
     * 
     * this.������ԋp����B<BR>
     * @@return double
     * @@roseuid 412487020185
     */
    public double getCurrentPrice()
    {
        return this.currentPrice;
    }

    /**
     * (get�w���搔)<BR>
     * 
     * this.�w���搔��ԋp����B<BR>
     * @@return double
     * @@roseuid 4124872802FD
     */
    public double getUnitSize()
    {
        return this.unitSize;
    }

    /**
     * (get��������)<BR>
     * 
     * this.�������ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 4124807001CE
     */
    public double getBuyQuantity()
    {
        return this.buyQuantity;
    }

    /**
     * (get��������)<BR>
     * 
     * this.�������ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 4124867C0378
     */
    public double getSellQuantity()
    {
        return this.sellQuantity;
    }
    
    /**
     * (get������������)<BR>
     * 
     * this.�����������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getTodayBuyQuantity()
    {
        return this.todayBuyQuantity;
    }

    /**
     * (get������������)<BR>
     * 
     * this.�����������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getTodaySellQuantity()
    {
        return this.todaySellQuantity;
    }
    
    /**
     * (get�]�����v�v�Z�敪)<BR>
     *
     * this.�]�����v�v�Z�敪��ԋp����B<BR>
     * @@return String
     */
    public String getProfitLossCalcType()
    {
        return this.profitLossCalcType;
    }

    /**
     * (get�������Z�l)<BR>
     *
     * this.�]�����v�v�Z�敪��ԋp����B<BR>
     * @@return String
     */
    public double getCurrentBizDateLiquidationPrice()
    {
        return this.currentBizDateLiquidationPrice;
    }
          
    /**
     * (get�������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     * @@roseuid 4124807001CE
     */
    public double getBuyQuantityTemp()
    {
        return this.buyQuantityTemp;
    }

    /**
     * (get�������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     * @@roseuid 4124867C0378
     */
    public double getSellQuantityTemp()
    {
        return this.sellQuantityTemp;
    }

    /**
     * (is�敨)<BR>
     * 
     * �Y���敨OP���ʏW�v���敨�ł��邩�𔻒肷��B<BR>
     * 
     * this.�敨�I�v�V�������i�敪==�h�敨�h�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp��
     * ��B<BR>
     * @@return boolean
     * @@roseuid 411A1EAF032C
     */
    public boolean isFutures()
    {
        //this.�敨�I�v�V�������i�敪==�h�敨�h�̏ꍇ
        if (this.ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES)
        {
            //true��ԋp����
            return true;
        }
        //����ȊO
        else
        {
            //false��ԋp����
            return false;
        }
    }
}
@
