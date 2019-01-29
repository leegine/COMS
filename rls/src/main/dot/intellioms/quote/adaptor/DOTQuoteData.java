/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteData�N���X(DOTQuoteData.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;




/**
 * (�������)<BR>
 * <BR>
 * �����T�[�o�����M����1���̎�������\���N���X�B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteData extends Serializable
{


    /**
     * (get�����f�[�^�擾��)<BR>
     * <BR>
     * �����f�[�^�擾�����擾����B<BR>
     * 
     * @return �����f�[�^�擾��
     */
    public Date getQuoteDate();

    /**
     * (get���A���敪)<BR>
     * <BR>
     * ���A���敪���擾����B<BR>
     * 
     * @return ���A���敪
     */
    public RealType getRealType();

    /**
     * (get��ʃR�[�h)<BR>
     * <BR>
     * ��ʃR�[�h���擾����B<BR>
     * 
     * @return ��ʃR�[�h
     */
    public DataType getDataType();

    /**
     * (get�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h���擾����B<BR>
     * 
     * @return �s��R�[�h
     */
    public String getMarketCode();

    /**
     * (get�����R�[�h)<BR>
     * <BR>
     * �����R�[�h���擾����B<BR>
     * 
     * @return �����R�[�h
     */
    public String getProductCode();
    
    /**
     * (get����)<BR>
     * <BR>
     * �������擾����B<BR>
     * �����A�w���̏ꍇ��<code>null</code>��Ԃ��B<BR>
     * 
     * @return ����
     */
    public String getMonthOfDelivery();
    
    /**
     * (get�v�b�g&�R�[��)<BR>
     * <BR>
     * �v�b�g���R�[�����擾����B<BR>
     * �����A�w���A�����w���敨�̏ꍇ��<code>PutAndCall.UNDEFINED</code>��Ԃ��B<BR>
     * 
     * @return �v�b�g���R�[��
     */
    public PutAndCall getPutAndCall();
    
    /**
     * (get�s�g���i)<BR>
     * <BR>
     * �s�g���i���擾����B<BR>
     * �����A�w���A�����w���敨�̏ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     * 
     * @return �s�g���i
     */
    public double getStrikePrice();

    /**
     * (get�n�l)<BR>
     * <BR>
     * �n�l���擾����B<BR>
     * �n�l���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     * 
     * @return ���l
     */
    public double getOpenPrice();
    
    /**
     * (get�n�l����)<BR>
     * <BR>
     * �n�l�������擾����B<BR>
     * �n�l�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * 
     * @return �n�l����
     */
    public Timestamp getOpenPriceTime();
    
    /**
     * (get���l)<BR>
     * <BR>
     * ���l���擾����B<BR>
     * ���l���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     * 
     * @return ���l
     */
    public double getHighPrice();
    
    /**
     * (get���l����)<BR>
     * <BR>
     * ���l�������擾����B<BR>
     * ���l�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * 
     * @return ���l����
     */
    public Timestamp getHighPriceTime();
    
    /**
     * (get���l)<BR>
     * <BR>
     * ���l���擾����B<BR>
     * ���l���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     * 
     * @return ���l
     */
    public double getLowPrice();
    
    /**
     * (get���l����)<BR>
     * <BR>
     * ���l�������擾����B<BR>
     * ���l�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * 
     * @return ���l
     */
    public Timestamp getLowPriceTime();
    
    /**
     * (get���ݒl)<BR>
     * <BR>
     * ���ݒl���擾����B<BR>
     * ���ݒl���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return ���ݒl
     */
    public double getCurrentPrice();
    
    /**
     * (get���ݒl����)<BR>
     * <BR>
     * ���ݒl�������擾����B<BR>
     * ���ݒl�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     *
     * @return ���ݒl����
     */
    public Timestamp getCurrentPriceTime();
    
    /**
     * (get���ݒl�t���O)<BR>
     * <BR>
     * ���ݒl�t���O���擾����B<BR>
     *
     * @return ���ݒl����
     */
    public CurrentPriceFlag getCurrentPriceFlag();
    
    /**
     * (get�O����)<BR>
     * <BR>
     * �O������擾����B<BR>
     * �O���䂪�ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return �O����
     */
    public double getChange();
    
    /**
     * (get�o����)<BR>
     * <BR>
     * �o�������擾����B<BR>
     * �o�������ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return �o����
     */
    public double getVolume();
    
    /**
     * (get�o��������)<BR>
     * <BR>
     * �o�����������擾����B<BR>
     * �o�����������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     *
     * @return �o��������
     */
    public Timestamp getVolumeTime();
    
    /**
     * (get���C�z�l�^�C�g��)<BR>
     * <BR>
     * ���C�z�l�^�C�g�����擾����B<BR>
     *
     * @return ���C�z�l�^�C�g��
     */
    public AskPriceTitle getAskPriceTitle();
    
    /**
     * (get���C�z�l)<BR>
     * <BR>
     * ���C�z�l���擾����B<BR>
     * ���C�z�l���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return ���C�z�l
     */
    public double getAskPrice();
    
    /**
     * (get���C�z�l����)<BR>
     * <BR>
     * ���C�z�l�������擾����B<BR>
     * ���C�z�l�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     *
     * @return ���C�z�l����
     */
    public Timestamp getAskPriceTime();
    
    /**
     * (get���C�z�l�^�C�g��)<BR>
     * <BR>
     * ���C�z�l�^�C�g�����擾����B<BR>
     *
     * @return ���C�z�l�^�C�g��<BR>
     */
    public BidPriceTitle getBidPriceTitle();
    
    /**
     * (get���C�z�l)<BR>
     * <BR>
     * ���C�z�l���擾����B<BR>
     * ���C�z�l���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return ���C�z�l
     */
    public double getBidPrice();
    
    /**
     * (get���C�z�l����)<BR>
     * <BR>
     * ���C�z�l�������擾����B<BR>
     * ���C�z�l�������ݒ肳��Ă��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     *
     * @return ���C�z�l����
     */
    public Timestamp getBidPriceTime();
    
    /**
     * (get��l�i)<BR>
     * <BR>
     * ��l�i���擾����B<BR>
     * ��l�i���ݒ肳��Ă��Ȃ��ꍇ��<code>Double.NaN</code>��Ԃ��B<BR>
     *
     * @return ��l�i���擾����B
     */
    public double getBasePrice();
    
    /**
     * (get�V�[�P���XNO)<BR>
     * <BR>
     * �V�[�P���XNO���擾����B<BR>
     *
     * @return �V�[�P���XNO
     */
    public long getSequenceNo();
    
    /**
     * (get�X�V����)<BR>
     * <BR>
     * �X�V���ԁi����������M�������ԁj���擾����B<BR>
     *
     * @return �X�V����
     */
    public Timestamp getUpdatedTime();
    
}
