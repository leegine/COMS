/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : BidPriceTitle�N���X(BidPriceTitle.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
                   2006/07/04 �R�c�@��i(FLJ) ���C�z�l�^�C�g���A���C�z�l�^�C�g�����ڒǉ��Ή�
                                             �u3:�񂹒��v�Ɓu5:���ʋC�z�v��ǉ�
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (���C�z�l�^�C�g��)<BR>
 * <BR>
 * ���C�z�l�^�C�g���̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class BidPriceTitle extends Enum
{

    /**
     * (���C�z�l�^�C�g���̐����l��`�N���X)<BR>
     * <BR>
     * ���C�z�l�^�C�g���̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * ����`
         */
        public static final int UNDEFINED = 0;
        
        /** 
         * �񂹒�
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * ���C�z
         */
        public static final int BID = 4;
        
        /** 
         * ���ʋC�z
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    
    /**
     * ����`
     */
    public static final BidPriceTitle UNDEFINED = new BidPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /** 
     * �񂹒�
     */
    public static final BidPriceTitle ITAYOSECHU = new BidPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /**
     * ���C�z
     */
    public static final BidPriceTitle BID = new BidPriceTitle(IntValues.BID, "BID");
    
    /** 
     * ���ʋC�z
     */
    public static final BidPriceTitle SPECIAL_QUOTATION = new BidPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private BidPriceTitle(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to���C�z�l�^�C�g��)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ����锄�C�z�l�^�C�g�����擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ����锄�C�z�l�^�C�g��
     */
    public static BidPriceTitle toBidPriceTitle(int intValue)
    {
        switch (intValue)
        {
            case IntValues.ITAYOSECHU : return ITAYOSECHU;
            case IntValues.BID : return BID;
            case IntValues.SPECIAL_QUOTATION : return SPECIAL_QUOTATION;
            case IntValues.UNDEFINED : return UNDEFINED;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
