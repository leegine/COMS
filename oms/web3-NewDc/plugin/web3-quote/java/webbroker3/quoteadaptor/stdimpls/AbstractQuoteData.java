head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AbstractQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �l�X�Ȏ������̊��N���X(AbstractQuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/19 �R�c�@@��i(FLJ) �V�K�쐬
                   2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
*/
package webbroker3.quoteadaptor.stdimpls;

import webbroker3.quoteadaptor.*;

/**
 * ���ׂĂ̎������̊��N���X<br>
 * 
 * @@author Takuji Yamada]
 * @@version 1.0
 */
abstract class AbstractQuoteData
{

    /** �������t */
    String quoteDate;

    /** ���A���敪 */
    RealType realType;

    /** ��ʋ敪 */
    DataType dataType;

    /** �s��R�[�h */
    String marketCode;

    /** �����R�[�h */
    String productCode;

    /** ���� */
    String monthOfDelivery;

    /** �v�b�g���R�[�� */
    PutAndCall putAndCall;

    /** �s�g���i */
    double strikePrice;

    /** �n�l */
    double openPrice;

    /** �n�l���� */
    String openPriceTime;

    /** ���l */
    double highPrice;

    /** ���l���� */
    String highPriceTime;

    /** ���l */
    double lowPrice;

    /** ���l���� */
    String lowPriceTime;

    /** ���ݒl */
    double currentPrice;

    /** ���ݒl���� */
    String currentPriceTime;

    /** ���ݒl�t���O */
    CurrentPriceFlag currentPriceFlag;

    /** �O���� */
    double change;

    /** �o���� */
    double volume;

    /** �o�������� */
    String volumeTime;

    /** ���C�z�l�^�C�g�� */
    AskPriceTitle askPriceTitle;

    /** ���C�z�l */
    double askPrice;

    /** ���C�z�l���� */
    String askPriceTime;

    /** ���C�z�l�^�C�g�� */
    BidPriceTitle bidPriceTitle;

    /** ���C�z�l */
    double bidPrice;

    /** ���C�z�l���� */
    String bidPriceTime;

    /** ��l�i */
    double basePrice;

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    protected AbstractQuoteData()
    {
        // double�̃v���p�e�B��Double.NaN�ŏ�����
        realType = RealType.UNDEFINED;
        dataType = DataType.UNDEFINED;
        putAndCall = PutAndCall.UNDEFINED;
        strikePrice = Double.NaN;
        openPrice = Double.NaN;
        highPrice = Double.NaN;
        lowPrice = Double.NaN;
        currentPrice = Double.NaN;
        currentPriceFlag = CurrentPriceFlag.NORMAL;
        change = Double.NaN;
        volume = Double.NaN;
        askPriceTitle = AskPriceTitle.UNDEFINED;
        askPrice = Double.NaN;
        bidPriceTitle = BidPriceTitle.UNDEFINED;
        bidPrice = Double.NaN;
        basePrice = Double.NaN;
    }

    /**
     * Map���ɓo�^���鎞�̃L�[�ƂȂ镶����𐶐�����B
     * �O������Ƃ��āA�L�[���\������v�f�Ɋ��ɒl���Z�b�g����Ă��邱�ƁB
     * 
     * @@return �L�[�ƂȂ镶����
     */
    String createKey()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(dataType.intValue()));
        if (!DataType.INDEX.equals(dataType))
        {
            sb.append(marketCode);
        }
        sb.append(productCode);
        if (DataType.INDEX_FUTURE.equals(dataType)
            || DataType.INDEX_OPTION.equals(dataType))
        {
            sb.append(monthOfDelivery);
            if (DataType.INDEX_OPTION.equals(dataType))
            {
                sb.append(putAndCall.stringValue());
                sb.append(String.valueOf(strikePrice));
            }

        }
        return sb.toString();
    }
    
    /**
     * �w�肳�ꂽ������񂩂�p�����[�^�̒l���Z�b�g����B
     * 
     * @@param from ���f�[�^
     */
    void setParams(AbstractQuoteData from)
    {
        this.quoteDate = from.quoteDate;
        this.realType = from.realType;
        this.dataType = from.dataType;
        this.marketCode = from.marketCode;
        this.productCode = from.productCode;
        this.monthOfDelivery = from.monthOfDelivery;
        this.putAndCall = from.putAndCall;
        this.strikePrice = from.strikePrice;
        this.openPrice = from.openPrice;
        this.openPriceTime = from.openPriceTime;
        this.highPrice = from.highPrice;
        this.highPriceTime = from.highPriceTime;
        this.lowPrice = from.lowPrice;
        this.lowPriceTime = from.lowPriceTime;
        this.currentPrice = from.currentPrice;
        this.currentPriceTime = from.currentPriceTime;
        this.currentPriceFlag = from.currentPriceFlag;
        this.change = from.change;
        this.volume = from.volume;
        this.volumeTime = from.volumeTime;
        this.askPriceTitle = from.askPriceTitle;
        this.askPrice = from.askPrice;
        this.askPriceTime = from.askPriceTime;
        this.bidPriceTitle = from.bidPriceTitle;
        this.bidPrice  = from.bidPrice;
        this.bidPriceTime = from.bidPriceTime;
        this.basePrice = from.basePrice;
    }
    
    

    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("quoteDate=").append(quoteDate);
        sb.append(",realType=").append(realType);
        sb.append(",dataType=").append(dataType);
        sb.append(",marketCode=").append(marketCode);
        sb.append(",productCode=").append(productCode);
        sb.append(",monthOfDelivery=").append(monthOfDelivery);
        sb.append(",putAndCall=").append(putAndCall);
        sb.append(",strikePrice=").append(strikePrice);
        sb.append(",openPrice=").append(openPrice);
        sb.append(",openPriceTime=").append(openPriceTime);
        sb.append(",highPrice=").append(highPrice);
        sb.append(",highPriceTime=").append(highPriceTime);
        sb.append(",lowPrice=").append(lowPrice);
        sb.append(",lowPriceTime=").append(lowPriceTime);
        sb.append(",currentPrice=").append(currentPrice);
        sb.append(",currentPriceTime=").append(currentPriceTime);
        sb.append(",currentPriceFlag=").append(currentPriceFlag);
        sb.append(",change=").append(change);
        sb.append(",volume=").append(volume);
        sb.append(",volumeTime=").append(volumeTime);
        sb.append(",askPriceTitle=").append(askPriceTitle);
        sb.append(",askPrice=").append(askPrice);
        sb.append(",askPriceTime=").append(askPriceTime);
        sb.append(",bidPriceTitle=").append(bidPriceTitle);
        sb.append(",bidPrice=").append(bidPrice);
        sb.append(",bidPriceTime=").append(bidPriceTime);
        sb.append(",basePrice=").append(basePrice);
        sb.append("}");
        return sb.toString();
    }
}
@
