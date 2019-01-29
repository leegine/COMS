/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteFormats�N���X(WEB3QuoteDataFormat.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/30 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

/**
 * (�������t�H�[�}�b�g�萔��`)<BR>
 * <BR>
 * �������̃t�H�[�}�b�g���`�����C���^�[�t�F�[�X�B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
interface DOTQuoteFormats
{

    /**
     * �V�[�P���XNO
     * 
     * �����T�[�o�����M�����V�[�P���XNO�{�}�ԁi2���j
     */
    public static final int AP_SEQUENCE_NO_SIZE = DOTQuoteConstants.SEQUENCE_NO_SIZE + 2;

    /** �X�V���� */
    public static final int UPDATED_TIME_SIZE = 23;

    /** �������t */
    public static final int QUOTE_DATE_SIZE = 8;

    /** ���A���敪 */
    public static final int REAL_TYPE_SIZE = 1;

    /** ��ʋ敪 */
    public static final int DATA_TYPE_SIZE = 1;

    /** �s��R�[�h */
    public static final int MARKET_CODE_SIZE = 2;

    /** �����R�[�h */
    public static final int PRODUCT_CODE_SIZE = 9;

    /** ���� */
    public static final int MONTH_OF_DELIVERY_SIZE = 6;

    /** �v�b�g���R�[�� */
    public static final int PUT_AND_CALL_SIZE = 1;

    /** �s�g���i */
    public static final int STRIKE_PRICE_SIZE = 12;

    /** �n�l */
    public static final int OPEN_PRICE_SIZE = 12;

    /** �n�l���� */
    public static final int OPEN_PRICE_TIME_SIZE = 4;

    /** ���l */
    public static final int HIGH_PRICE_SIZE = 12;

    /** ���l���� */
    public static final int HIGH_PRICE_TIME_SIZE = 4;

    /** ���l */
    public static final int LOW_PRICE_SIZE = 12;

    /** ���l���� */
    public static final int LOW_PRICE_TIME_SIZE = 4;

    /** ���ݒl */
    public static final int CURRENT_PRICE_SIZE = 12;

    /** ���ݒl���� */
    public static final int CURRENT_PRICE_TIME_SIZE = 4;

    /** ���ݒl�t���O */
    public static final int CURRENT_PRICE_FLAG_SIZE = 1;

    /** �O���� */
    public static final int CHANGE_SIZE = 12;

    /** �o���� */
    public static final int VOLUME_SIZE = 12;

    /** �o�������� */
    public static final int VOLUME_TIME_SIZE = 4;

    /** ���C�z�l�^�C�g�� */
    public static final int ASK_PRICE_TITLE_SIZE = 1;

    /** ���C�z�l */
    public static final int ASK_PRICE_SIZE = 12;

    /** ���C�z�l���� */
    public static final int ASK_PRICE_TIME_SIZE = 4;

    /** ���C�z�l�^�C�g�� */
    public static final int BID_PRICE_TITLE_SIZE = 1;

    /** ���C�z�l */
    public static final int BID_PRICE_SIZE = 12;

    /** ���C�z�l���� */
    public static final int BID_PRICE_TIME_SIZE = 4;

    /** ��l�i */
    public static final int BASE_PRICE_SIZE = 12;

    /** �����R�[�h�i�����j */
    public static final int EQUITY_PRODUCT_CODE_SIZE = 5;

    /** ���t�t�H�[�}�b�g�FyyyyMMdd */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** ���t�t�H�[�}�b�g�FHHmm */
    public static final String TIME_FORMAT = "HHmm";

    /** ���t�t�H�[�}�b�g�Fyyyy-MM-dd HH:mm:ss.SSS */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /** ���l�t�H�[�}�b�g�F#.## */
    public static final String DECIMAL_FORMAT = "#.##";

    /** 
     * 1���̃��R�[�h�̃T�C�Y
     * 
     * ���[���G���W�����1���̃��R�[�h�T�C�Y�́A��M�����������ɁA
     * �V�[�P���XNO�ƍX�V���Ԃ��ǉ�����邽�߁A �����T�[�o�����M����
     * �������̃T�C�Y�Ƃ͈قȂ�B
     */
    public static final int AP_RECORD_SIZE = AP_SEQUENCE_NO_SIZE + UPDATED_TIME_SIZE
        + DOTQuoteConstants.RECORD_SIZE;

}