/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Index�N���X(IndexType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/05 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (�w���^�C�v)<BR>
 * <BR>
 * �w���^�C�v�̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class IndexType extends Enum
{
    
    /** �����R�[�h */
    private final String productCode;
    
    /**
     * (�w���^�C�v�̐����l��`�N���X)<BR>
     * <BR>
     * �w���^�C�v�̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /** 
         * ���o225
         */
        public static final int NK225 = 1;
        
        /** 
         * TOPIX
         */
        public static final int TOPIX = 2;
        
        /** 
         * ���o300
         */
        public static final int NK300 = 3;
        
    }
    
    /**
     * (�w���^�C�v�̖����R�[�h��`�N���X)<BR>
     * <BR>
     * �w���^�C�v�̖����R�[�h���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class ProductCodes
    {
        
        /** 
         * ���o225
         */
        public static final String NK225 = "0018";
        
        /** 
         * TOPIX
         */
        public static final String TOPIX = "0005";
        
        /** 
         * ���o300
         */
        public static final String NK300 = "0016";
        
    }
    
    /** 
     * ���o225
     */
    public static final IndexType NK225 = new IndexType(IntValues.NK225, "NK225", ProductCodes.NK225);
    
    /** 
     * TOPIX
     */
    public static final IndexType TOPIX = new IndexType(IntValues.TOPIX, "TOPIX", ProductCodes.TOPIX);
    
    /** 
     * ���o300
     */
    public static final IndexType NK300 = new IndexType(IntValues.NK300, "NK300", ProductCodes.NK300);

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private IndexType(int v, String s, String productCode)
    {
        super(v, s);
        this.productCode = productCode;
    }
    
    /**
     * (get�����R�[�h)<BR>
     * <BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * @return �����R�[�h
     */
    public String getProductCode()
    {
        return productCode;
    }
    
    /**
     * (to�w���^�C�v)<BR>
     * <BR>
     * �w�肵�������R�[�h�ɑΉ�����w���^�C�v���擾����B<BR>
     * <BR>
     * @param productCode �����R�[�h
     * @return �w�肵�������l�ɑΉ�����w���^�C�v
     */
    public static IndexType toIndexType(String productCode)
    {
        if (ProductCodes.NK225.equals(productCode))
        {
            return IndexType.NK225;
        } else if (ProductCodes.TOPIX.equals(productCode))
        {
            return IndexType.TOPIX;
        } else if (ProductCodes.NK300.equals(productCode))
        {
            return IndexType.NK300;
        }
        throw new RuntimeException("undefined product code. : " + productCode);
            
    }
    
}
