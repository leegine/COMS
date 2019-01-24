/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3BaseQuoteData�N���X(DOTBaseQuoteData.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;




/**
 * (�x�[�X�������)<BR>
 * <BR>
 * �������̒��ۃN���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
abstract class DOTBaseQuoteData
{

    /** �������t */
    Date quoteDate;

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
    Timestamp openPriceTime;

    /** ���l */
    double highPrice;

    /** ���l���� */
    Timestamp highPriceTime;

    /** ���l */
    double lowPrice;

    /** ���l���� */
    Timestamp lowPriceTime;

    /** ���ݒl */
    double currentPrice;

    /** ���ݒl���� */
    Timestamp currentPriceTime;

    /** ���ݒl�t���O */
    CurrentPriceFlag currentPriceFlag;

    /** �O���� */
    double change;

    /** �o���� */
    double volume;

    /** �o�������� */
    Timestamp volumeTime;

    /** ���C�z�l�^�C�g�� */
    AskPriceTitle askPriceTitle;

    /** ���C�z�l */
    double askPrice;

    /** ���C�z�l���� */
    Timestamp askPriceTime;

    /** ���C�z�l�^�C�g�� */
    BidPriceTitle bidPriceTitle;

    /** ���C�z�l */
    double bidPrice;

    /** ���C�z�l���� */
    Timestamp bidPriceTime;

    /** ��l�i */
    double basePrice;

    /** �V�[�P���XNO */
    long sequenceNo;

    /** �X�V���� */
    long updatedTime;

    /**
     * �R���X�g���N�^
     */
    protected DOTBaseQuoteData()
    {
        putAndCall = PutAndCall.UNDEFINED;
        strikePrice = Double.NaN;
        openPrice = Double.NaN;
        highPrice = Double.NaN;
        lowPrice = Double.NaN;
        currentPrice = Double.NaN;
        currentPriceFlag = CurrentPriceFlag.UNDEFINED;
        change = Double.NaN;
        volume = Double.NaN;
        askPriceTitle = AskPriceTitle.UNDEFINED;
        askPrice = Double.NaN;
        bidPriceTitle = BidPriceTitle.UNDEFINED;
        bidPrice = Double.NaN;
        basePrice = Double.NaN;
    }

    /**
     * @see Object#toString()
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
        sb.append(",sequenceNo=").append(sequenceNo);
        sb.append(",updatedTime=").append(toTimestamp(updatedTime));
        sb.append("}");
        return sb.toString();
    }

    /**
     * (set�p�����[�^)<BR>
     * <BR>
     * �w�肳�ꂽ������񂩂炱�̎������Ɋe�p�����[�^�̒l���R�s�[����B<BR>
     *
     * @param from ���f�[�^
     */
    void setParams(DOTBaseQuoteData from)
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
        this.sequenceNo = from.sequenceNo;
        this.updatedTime = from.updatedTime;
    }

    /**
     * (create�L�[)<BR>
     * <BR>
     * ����������ӂɓ��肷��L�[���쐬����B<BR>
     *
     * @return �쐬�����L�[
     */
    String createKey()
    {

        StringBuffer sb = new StringBuffer();
        sb.append(realType.toValue());
        sb.append(dataType.toValue());
        if (DataType.IntValues.INDEX != dataType.toValue())
        {
            sb.append(marketCode);
        }
        sb.append(productCode);
        if (DataType.IntValues.INDEX_FUTURE == dataType.toValue()
            || DataType.IntValues.INDEX_OPTION == dataType.toValue())
        {
            sb.append(monthOfDelivery);
            if (DataType.IntValues.INDEX_OPTION == dataType.toValue())
            {
                sb.append(putAndCall.toStringValue());
                sb.append(strikePrice);
            }
        }

        return sb.toString();
    }

    /**
     * (toTimestamp)<BR>
     * <BR>
     * �w�肵���~���b�l������<code>Timestamp<code>���쐬����B<BR>
     * �w�肵���~���b�l���u0�v�̏ꍇ<code>null</code>��Ԃ��B<BR>
     *
     * @param time �~���b�l
     * @return �쐬����<code>Timestamp</code>
     */
    protected Timestamp toTimestamp(long time)
    {
        return (time != 0) ? new Timestamp(time) : null;
    }

}
