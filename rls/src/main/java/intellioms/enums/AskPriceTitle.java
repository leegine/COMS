/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : AskPriceTitle�N���X(AskPriceTitle.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
                   2006/07/04 �R�c�@��i(FLJ) ���C�z�l�^�C�g���A���C�z�l�^�C�g�����ڒǉ��Ή�
                                             �u5:���ʋC�z�v��ǉ�
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
public final class AskPriceTitle extends Enum
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
         * ���C�z
         */
        public static final int ASK = 1;
        
        /** 
         * ������~
         */
        public static final int TRADING_SUSPENDED = 2;
        
        /** 
         * �񂹒�
         */
        public static final int ITAYOSECHU = 3;
        
        /** 
         * ���ʋC�z
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    /** 
     * ����`
     */
    public static final AskPriceTitle UNDEFINED = new AskPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /** 
     * ���C�z
     */
    public static final AskPriceTitle ASK = new AskPriceTitle(IntValues.ASK, "ASK");
    
    /** 
     * ������~
     */
    public static final AskPriceTitle TRADING_SUSPENDED = new AskPriceTitle(IntValues.TRADING_SUSPENDED, "TRADING_SUSPENDED");
    
    /** 
     * �񂹒�
     */
    public static final AskPriceTitle ITAYOSECHU = new AskPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /** 
     * ���ʋC�z
     */
    public static final AskPriceTitle SPECIAL_QUOTATION = new AskPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private AskPriceTitle(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to���C�z�l�^�C�g��)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ����锃�C�z�l�^�C�g�����擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ����锃�C�z�l�^�C�g��
     */
    public static AskPriceTitle toAskPriceTitle(int intValue)
    {
        switch (intValue)
        {
            case IntValues.ASK : return ASK;
            case IntValues.TRADING_SUSPENDED : return TRADING_SUSPENDED;
            case IntValues.ITAYOSECHU : return ITAYOSECHU;
            case IntValues.SPECIAL_QUOTATION : return SPECIAL_QUOTATION;
            case IntValues.UNDEFINED : return UNDEFINED;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
