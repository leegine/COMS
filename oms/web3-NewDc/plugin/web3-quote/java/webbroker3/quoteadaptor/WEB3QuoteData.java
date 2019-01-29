head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������N���X(WEB3QuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/10 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/03/30 �R�c�@@��i(FLJ) ���\�b�h��`��WEB3QuoteDataBody��WEB3QuoteDataHeader����ړ�
                 : 2005/03/30 �R�c�@@��i(FLJ) Javadoc�R�����g�C�� 
*/
package webbroker3.quoteadaptor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * WebBroker3�̎����T�[�r�X���񋟂��鎞�����̃C���^�[�t�F�[�X�B<br>
 * </p>
 * <p>
 * �����T�[�r�X���񋟂��鎞�����́A
 * ���̃C���^�[�t�F�[�X�Ə��i���̃g���[�f�B���O���W���[���ɒ�`���ꂽ�A
 * ���i���̎������̃C���^�[�t�F�[�X�i<code>EqTypeQuoteData</code>�Ȃǁj���������Ă���B<br>
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.1
 */
public interface WEB3QuoteData extends WEB3QuoteDataBody, WEB3QuoteDataHeader
{

    /**
     * �����f�[�^�擾�����擾����B
     * 
     * @@return �����f�[�^�擾��
     */
    public Date getQuoteDate();

    /**
     * ���A���敪���擾����B
     * 
     * @@return ���A���敪
     */
    public RealType getRealType();

    /**
     * ��ʃR�[�h���擾����B
     * 
     * @@return ��ʃR�[�h
     */
    public DataType getDataType();

    /**
     * �s��R�[�h���擾����B
     * 
     * @@return �s��R�[�h
     */
    public String getMarketCode();

    /**
     * �����R�[�h���擾����B
     * 
     * @@return �����R�[�h
     */
    public String getProductCode();
    
    /**
     * �������擾����B<br>
     * �����A�w���̏ꍇ�́A<code>null</code>��Ԃ��B
     * 
     * @@return ����
     */
    public String getMonthOfDelivery();
    
    /**
     * �v�b�g���R�[�����擾����B<br>
     * �����A�w���̏ꍇ�́A<code>PutAndCall.UNDEFINED</code>��Ԃ��B
     * 
     * @@return �v�b�g���R�[��
     */
    public PutAndCall getPutAndCall();
    
    /**
     * �s�g���i���擾����B<br>
     * �����A�w���̏ꍇ�́A�u0�v��Ԃ��B
     * 
     * @@return �s�g���i
     */
    public double getStrikePrice();

    /**
     * �n�l���擾����B<br>
     * �n�l���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���l
     */
    public double getOpenPrice();
    
    /**
     * �n�l�������擾����B
     * �n�l�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return �n�l����
     */
    public Timestamp getOpenPriceTime();
    
    /**
     * ���l���擾����B
     * ���l���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���l
     */
    public double getHighPrice();
    
    /**
     * ���l�������擾����B
     * ���l�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return ���l����
     */
    public Timestamp getHighPriceTime();
    
    /**
     * ���l���擾����B
     * ���l���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���l
     */
    public double getLowPrice();
    
    /**
     * ���l�������擾����B
     * ���l�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return ���l
     */
    public Timestamp getLowPriceTime();
    
    /**
     * ���ݒl���擾����B
     * ���ݒl���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���ݒl
     */
    public double getCurrentPrice();
    
    /**
     * ���ݒl�������擾����B
     * ���ݒl�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return ���ݒl����
     */
    public Timestamp getCurrentPriceTime();
    
    /**
     * ���ݒl�t���O���擾����B
     * 
     * @@return ���ݒl����
     */
    public CurrentPriceFlag getCurrentPriceFlag();
    
    /**
     * �O������擾����B
     * �O���䂪�ݒ肳��Ă��Ȃ��ꍇ�́A<code>Double.NaN</code>��Ԃ��B<br>
     * 
     * @@return �O����
     */
    public double getChange();
    
    /**
     * �o�������擾����B
     * �o�������ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return �o����
     */
    public double getVolume();
    
    /**
     * �o�����������擾����B
     * �o�����������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return �o��������
     */
    public Timestamp getVolumeTime();
    
    /**
     * ���C�z�l�^�C�g�����擾����B
     * 
     * @@return ���C�z�l�^�C�g��
     */
    public AskPriceTitle getAskPriceTitle();
    
    /**
     * ���C�z�l���擾����B
     * ���C�z�l���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���C�z�l
     */
    public double getAskPrice();
    
    /**
     * ���C�z�l�������擾����B
     * ���C�z�l�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return ���C�z�l����
     */
    public Timestamp getAskPriceTime();
    
    /**
     * ���C�z�l�^�C�g�����擾����B
     * 
     * @@return ���C�z�l�^�C�g��
     */
    public BidPriceTitle getBidPriceTitle();
    
    /**
     * ���C�z�l���擾����B
     * ���C�z�l���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ���C�z�l
     */
    public double getBidPrice();
    
    /**
     * ���C�z�l�������擾����B
     * ���C�z�l�������ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B<br>
     * 
     * @@return ���C�z�l����
     */
    public Timestamp getBidPriceTime();
    
    /**
     * ��l�i���擾����B
     * ��l�i���ݒ肳��Ă��Ȃ��ꍇ�́A�u0�v��Ԃ��B<br>
     * 
     * @@return ��l�i���擾����B
     */
    public double getBasePrice();
    
}
@
