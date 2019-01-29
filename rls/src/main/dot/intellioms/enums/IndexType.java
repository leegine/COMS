/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Indexクラス(IndexType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/05 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (指数タイプ)<BR>
 * <BR>
 * 指数タイプの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class IndexType extends Enum
{
    
    /** 銘柄コード */
    private final String productCode;
    
    /**
     * (指数タイプの整数値定義クラス)<BR>
     * <BR>
     * 指数タイプの整数値を定義した内部クラス。<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /** 
         * 日経225
         */
        public static final int NK225 = 1;
        
        /** 
         * TOPIX
         */
        public static final int TOPIX = 2;
        
        /** 
         * 日経300
         */
        public static final int NK300 = 3;
        
    }
    
    /**
     * (指数タイプの銘柄コード定義クラス)<BR>
     * <BR>
     * 指数タイプの銘柄コードを定義した内部クラス。<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class ProductCodes
    {
        
        /** 
         * 日経225
         */
        public static final String NK225 = "0018";
        
        /** 
         * TOPIX
         */
        public static final String TOPIX = "0005";
        
        /** 
         * 日経300
         */
        public static final String NK300 = "0016";
        
    }
    
    /** 
     * 日経225
     */
    public static final IndexType NK225 = new IndexType(IntValues.NK225, "NK225", ProductCodes.NK225);
    
    /** 
     * TOPIX
     */
    public static final IndexType TOPIX = new IndexType(IntValues.TOPIX, "TOPIX", ProductCodes.TOPIX);
    
    /** 
     * 日経300
     */
    public static final IndexType NK300 = new IndexType(IntValues.NK300, "NK300", ProductCodes.NK300);

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param v 整数値
     * @param s 文字列値
     */
    private IndexType(int v, String s, String productCode)
    {
        super(v, s);
        this.productCode = productCode;
    }
    
    /**
     * (get銘柄コード)<BR>
     * <BR>
     * 銘柄コードを取得する。<BR>
     * <BR>
     * @return 銘柄コード
     */
    public String getProductCode()
    {
        return productCode;
    }
    
    /**
     * (to指数タイプ)<BR>
     * <BR>
     * 指定した銘柄コードに対応する指数タイプを取得する。<BR>
     * <BR>
     * @param productCode 銘柄コード
     * @return 指定した整数値に対応する指数タイプ
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
