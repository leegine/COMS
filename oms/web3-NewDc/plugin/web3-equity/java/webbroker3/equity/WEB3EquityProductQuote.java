head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������(WEB3EquityProductQuote.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 �������F(SRA) �V�K�쐬
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
                   2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.935
*/
package webbroker3.equity;

import java.sql.Timestamp;

/**
 * �i���������������j�B<BR>
 * <BR>
 * ����������������ێ�����f�[�^�N���X�B<BR>
 * �������擾���\�b�h�̖߂�l��ێ�����B
 * @@version 1.0
 */
public class WEB3EquityProductQuote
{
    /**
     * (����)<BR>
     * �����B<BR>
     */
    private double quote;

    /**
     * (�O����)<BR>
     * �O����B<BR>
     */
    private double comparedPreviousDay;

    /**
     * (�������\����)<BR>
     * �������\���ԁB<BR>
     */
    private Timestamp quoteTime;

    /**
     * (�����擾�敪)<BR>
     * �����擾�敪�B<BR>
     * �i1�F�����@@2�F�O���I�l�@@3�F�����I�l�j<BR>
     */
    private String quoteFromDiv;

    /**
     * (�����敪)<BR>
     * �����敪�B <BR>
     * �i1�F���ݒl�@@2�F���C�z�l�@@3�F���C�z�l�@@4�F�O���I�l�j<BR>
     */
    private String quoteTypeDiv;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h�B<BR>
     */
    private String marketCode;

    /**
     * �i���ݒl�j<BR>
     * ���ݒl<BR>
     */
    private String boardCurrentPrice;
    
    /**
     * �i���ݒl�����j<BR>
     * ���ݒl����<BR>
     */
    private Timestamp boardCurrentPriceTime;
    
    /**
     * �i���ݒl�敪�j<BR>
     * ���ݒl�敪 <BR>
     *�i0�F�ʏ� <BR>
     *�@@1�F�I�l�j<BR>
     */
    private String boardCurrentPriceDiv;
    
    /**
     * �i���ݒl�O����j<BR>
     * ���ݒl�O����<BR>
     */
    private String boardChange;
    
    /**
     * �i�o�����j<BR>
     * �o����<BR>
     */
    private String volume;
    
    /**
     * �i�o���������j<BR>
     * �o��������<BR>
     */
    private Timestamp volumeTime;
    
    /**
     * �i���C�z�l�^�C�g���敪�j<BR>
     * ���C�z�l�^�C�g���敪 <BR>
     *�i1�F���C�z <BR>
     *�@@2�F������~ <BR>
     *�@@3�F�񂹒� <BR>
     *�@@5�F���ʋC�z�j<BR>
     */
    private String askPriceTitle;
    
    /**
     * �i���C�z�l�j<BR>
     * ���C�z�l<BR>
     */
    private String askPrice;
    
    /**
     * �i���C�z�l�����j<BR>
     * ���C�z�l����<BR>
     */
    private Timestamp askPriceTime;
    
    /**
     * �i���C�z�l�^�C�g���敪�j<BR>
     * ���C�z�l�^�C�g���敪 <BR>
     * �i3�F�񂹒� <BR>
     * �@@4�F���C�z <BR>
     * �@@5�F���ʋC�z�j<BR>
     */
    private String bidPriceTitle;
    
    /**
     * �i���C�z�l�j<BR>
     * ���C�z�l<BR>
     */
    private String bidPrice;
    
    /**
     * �i���C�z�l�����j<BR>
     * ���C�z�l����<BR>
     */
    private Timestamp bidPriceTime;
    
    /**
     * �i��l�i�j<BR>
     * ��l�i<BR>
     */
    private String basePrice;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3EquityProductQuote()
    {
    }

    /**
     * (set����)<BR>
     * <BR>
     * �������Z�b�g����B<BR>
     * <BR>
     * @@param l_dblQuote - (����)
     */
    public void setQuote(double l_dblQuote)
    {
        this.quote = l_dblQuote;
    }

    /**
     * (get����)<BR>
     * <BR>
     * �������擾����B<BR>
     * @@return double
     */
    public double getQuote()
    {
        return this.quote;
    }

    /**
     * (set�O����)<BR>
     * <BR>
     * �O������Z�b�g����B<BR>
     * <BR>
     * @@param l_dblComparedPreviousDay - (�O����)
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay)
    {
        this.comparedPreviousDay = l_dblComparedPreviousDay;
    }

    /**
     * (get�O����)<BR>
     * <BR>
     * �O������擾����B<BR>
     * @@return double
     */
    public double getComparedPreviousDay()
    {
        return this.comparedPreviousDay;
    }

    /**
     * (set�������\����)<BR>
     * <BR>
     * �������\���Ԃ��Z�b�g����B<BR>
     * <BR>
     * @@param l_tsQuoteTime - (�������\����)
     */
    public void setQuoteTime(Timestamp l_tsQuoteTime)
    {
        this.quoteTime = l_tsQuoteTime;
    }

    /**
     * (get�������\����)<BR>
     * <BR>
     * �������\���Ԃ��擾����B<BR>
     * @@return Timestamp
     */
    public Timestamp getQuoteTime()
    {
        return this.quoteTime;
    }

    /**
     * (set�����擾�敪)<BR>
     * <BR>
     * �����擾�敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strQuoteFromDiv - (�����擾�敪)
     */
    public void setQuoteFromDiv(String l_strQuoteFromDiv)
    {
        this.quoteFromDiv = l_strQuoteFromDiv;
    }

    /**
     * (get�����擾�敪)<BR>
     * <BR>
     * �����擾�敪���擾����B<BR>
     * @@return String
     */
    public String getQuoteFromDiv()
    {
        return this.quoteFromDiv;
    }

    /**
     * (set�����敪)<BR>
     * <BR>
     * �����敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strQuoteTypeDiv - (�����敪)
     */
    public void setQuoteTypeDiv(String l_strQuoteTypeDiv)
    {
        this.quoteTypeDiv = l_strQuoteTypeDiv;
    }

    /**
     * (get�����敪)<BR>
     * <BR>
     * �����敪���擾����B<BR>
     * @@return String
     */
    public String getQuoteTypeDiv()
    {
        return this.quoteTypeDiv;
    }

    /**
     * (set�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h���Z�b�g����B<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * (get�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h���擾����B<BR>
     * @@return String
     */
    public String getMarketCode()
    {
        return this.marketCode;
    }
    
    /**
     * (set���ݒl)<BR>
     * ���ݒl���Z�b�g����B<BR>
     * <BR>
     * @@param l_strBoardCurrentPrice - (���ݒl)<BR>
     * ���ݒl�B
     */
    public void setBoardCurrentPrice(String l_strBoardCurrentPrice)
    {
        this.boardCurrentPrice = l_strBoardCurrentPrice;
    }
    
    /**
     * (get���ݒl)<BR>
     * <BR>
     * ���ݒl���擾����B<BR>
     * @@return String
     */
    public String getBoardCurrentPrice()
    {
        return this.boardCurrentPrice;
    }
    
    /**
     * (set���ݒl����)<BR>
     * ���ݒl�������Z�b�g����B<BR>
     * <BR>
     * @@param l_tsBoardCurrentPriceTime - (���ݒl����)<BR>
     * ���ݒl�����B
     */
    public void setBoardCurrentPriceTime(Timestamp l_tsBoardCurrentPriceTime)
    {
        this.boardCurrentPriceTime = l_tsBoardCurrentPriceTime;
    }
    
    /**
     * (get���ݒl����)<BR>
     * <BR>
     * ���ݒl�������擾����B<BR>
     * @@return Timestamp
     */
    public Timestamp getBoardCurrentPriceTime()
    {
        return this.boardCurrentPriceTime;
    }
    
    /**
     * (set���ݒl�敪)<BR>
     * ���ݒl�敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strBoardCurrentPriceDiv - (���ݒl�敪)<BR>
     * ���ݒl�敪�B
     */
    public void setBoardCurrentPriceDiv(String l_strBoardCurrentPriceDiv)
    {
        this.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv;
    }
    
    /**
     * (get���ݒl�敪)<BR>
     * <BR>
     * ���ݒl�敪���擾����B<BR>
     * @@return String
     */
    public String getBoardCurrentPriceDiv()
    {
        return this.boardCurrentPriceDiv;
    }
    
    /**
     * (set���ݒl�O����)<BR>
     * ���ݒl�O������Z�b�g����B<BR>
     * <BR>
     * @@param l_strBoardChange - (���ݒl�O����)<BR>
     * ���ݒl�O����B
     */
    public void setBoardChange(String l_strBoardChange)
    {
        this.boardChange = l_strBoardChange;
    }
    
    /**
     * (get���ݒl�O����)<BR>
     * <BR>
     * ���ݒl�O������擾����B<BR>
     * @@return String
     */
    public String getBoardChange()
    {
        return this.boardChange;
    }
    
    /**
     * (set�o����)<BR>
     * �o�������Z�b�g����B<BR>
     * <BR>
     * @@param l_strVolume - (�o����)<BR>
     * �o�����B
     */
    public void setVolume(String l_strVolume)
    {
        this.volume = l_strVolume;
    }
    
    /**
     * (get�o����)<BR>
     * <BR>
     * �o�������擾����B<BR>
     * @@return String
     */
    public String getVolume()
    {
        return this.volume;
    }
    
    /**
     * (set�o��������)<BR>
     * �o�����������Z�b�g����B<BR>
     * <BR>
     * @@param l_tsBoardChange - (�o��������)<BR>
     * �o���������B
     */
    public void setVolumeTime(Timestamp l_tsVolumeTime)
    {
        this.volumeTime = l_tsVolumeTime;
    }
    
    /**
     * (get�o��������)<BR>
     * <BR>
     * �o�����������擾����B<BR>
     * @@return Timestamp
     */
    public Timestamp getVolumeTime()
    {
        return this.volumeTime;
    }
    
    /**
     * (set���C�z�l�^�C�g���敪)<BR>
     * ���C�z�l�^�C�g���敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strAskPriceTitle - (���C�z�l�^�C�g���敪)<BR>
     * ���C�z�l�^�C�g���敪�B
     */
    public void setAskPriceTitle(String l_strAskPriceTitle)
    {
        this.askPriceTitle = l_strAskPriceTitle;
    }
    
    /**
     * (get���C�z�l�^�C�g���敪)<BR>
     * <BR>
     * ���C�z�l�^�C�g���敪���擾����B<BR>
     * @@return String
     */
    public String getAskPriceTitle()
    {
        return this.askPriceTitle;
    }
    
    /**
     * (set���C�z�l)<BR>
     * ���C�z�l���Z�b�g����B<BR>
     * <BR>
     * @@param l_strAskPrice - (���C�z�l)<BR>
     * ���C�z�l�B
     */
    public void setAskPrice(String l_strAskPrice)
    {
        this.askPrice = l_strAskPrice;
    }
    
    /**
     * (get���C�z�l)<BR>
     * <BR>
     * ���C�z�l���擾����B<BR>
     * @@return String
     */
    public String getAskPrice()
    {
        return this.askPrice;
    }
    
    /**
     * (set���C�z�l����)<BR>
     * ���C�z�l�������Z�b�g����B<BR>
     * <BR>
     * @@param l_tsAskPriceTime - (���C�z�l����)<BR>
     * ���C�z�l�����B
     */
    public void setAskPriceTime(Timestamp l_tsAskPriceTime)
    {
        this.askPriceTime = l_tsAskPriceTime;
    }
    
    /**
     * (get���C�z�l����)<BR>
     * <BR>
     * ���C�z�l�������擾����B<BR>
     * @@return Timestamp
     */
    public Timestamp getAskPriceTime()
    {
        return this.askPriceTime;
    }
    
    /**
     * (set���C�z�l�^�C�g���敪)<BR>
     * ���C�z�l�^�C�g���敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strBidPriceTitle - (���C�z�l�^�C�g���敪)<BR>
     * ���C�z�l�^�C�g���敪�B
     */
    public void setBidPriceTitle(String l_strBidPriceTitle)
    {
        this.bidPriceTitle = l_strBidPriceTitle;
    }
    
    /**
     * (get���C�z�l�^�C�g���敪)<BR>
     * <BR>
     * ���C�z�l�^�C�g���敪���擾����B<BR>
     * @@return String
     */
    public String getBidPriceTitle()
    {
        return this.bidPriceTitle;
    }
    
    /**
     * (set���C�z�l)<BR>
     * ���C�z�l���Z�b�g����B<BR>
     * <BR>
     * @@param l_strBidPrice - (���C�z�l)<BR>
     * ���C�z�l�B
     */
    public void setBidPrice(String l_strBidPrice)
    {
        this.bidPrice = l_strBidPrice;
    }
    
    /**
     * (get���C�z�l)<BR>
     * <BR>
     * ���C�z�l���擾����B<BR>
     * @@return String
     */
    public String getBidPrice()
    {
        return this.bidPrice;
    }
    
    /**
     * (set���C�z�l����)<BR>
     * ���C�z�l�������Z�b�g����B<BR>
     * <BR>
     * @@param l_tsBidPriceTime - (���C�z�l����)<BR>
     * ���C�z�l�����B
     */
    public void setBidPriceTime(Timestamp l_tsBidPriceTime)
    {
        this.bidPriceTime = l_tsBidPriceTime;
    }
    
    /**
     * (get���C�z�l����)<BR>
     * <BR>
     * ���C�z�l�������擾����B<BR>
     * @@return Timestamp
     */
    public Timestamp getBidPriceTime()
    {
        return this.bidPriceTime;
    }
    
    /**
     * (set��l�i)<BR>
     * ��l�i���Z�b�g����B<BR>
     * <BR>
     * @@param l_strBasePrice - (��l�i)<BR>
     * ��l�i�B
     */
    public void setBasePrice(String l_strBasePrice)
    {
        this.basePrice = l_strBasePrice;
    }
    
    /**
     * (get��l�i)<BR>
     * <BR>
     * ��l�i���擾����B<BR>
     * @@return String
     */
    public String getBasePrice()
    {
        return this.basePrice;
    }
}
@
