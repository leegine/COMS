head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	DataType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : ○○○○○クラス(DataType.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

/**
 * 種別コードのEnumクラス
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class DataType extends Enumerated
{

    /**
     * 種別コードのEnumクラスで使用する定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {
        /**
         * 種別コード：未定義
         */
        public static final int UNDEFINED = 0;

        /**
         * 種別コード：株式
         */
        public static final int EQUITY = 1;

        /**
         * 種別コード：指数
         */
        public static final int INDEX = 2;

        /**
         * 種別コード：株価指数先物
         */
        public static final int INDEX_FUTURE = 3;

        /**
         * 種別コード：株価指数オプション
         */
        public static final int INDEX_OPTION = 4;

    }

    /**
     * 種別コード：未定義
     */
    public static final DataType UNDEFINED =
        new DataType(IntValues.UNDEFINED, "UNDEFINED");

    /**
     * 種別コード：株式
     */
    public static final DataType EQUITY =
        new DataType(IntValues.EQUITY, "EQUITY");

    /**
     * 種別コード：指数
     */
    public static final DataType INDEX = new DataType(IntValues.INDEX, "INDEX");

    /**
     * 種別コード：株価指数先物
     */
    public static final DataType INDEX_FUTURE =
        new DataType(IntValues.INDEX_FUTURE, "INDEX_FUTURE");

    /**
     * 種別コード：株価指数オプション
     */
    public static final DataType INDEX_OPTION =
        new DataType(IntValues.INDEX_OPTION, "INDEX_OPTION");

    private DataType(int i, String s)
    {
        super(i, s);
    }

    /**
     * 銘柄タイプを種別コードに変換します。<br>
     * 
     * @@param productType 銘柄タイプ
     * @@return 種別コード
     */
    public static DataType toDataType(ProductTypeEnum productType)
    {
        switch (productType.intValue())
        {
            case ProductTypeEnum.IntValues.EQUITY :
                return DataType.EQUITY;
            default :
                return DataType.UNDEFINED;
        }
    }

    public static DataType getDataType(TradedProduct tradedProduct)
    {
        switch (tradedProduct.getProduct().getProductType().intValue())
        {
            case ProductTypeEnum.IntValues.EQUITY :
                return DataType.EQUITY;
            case ProductTypeEnum.IntValues.IFO :
                {
                    IfoProduct product =
                        (IfoProduct) tradedProduct.getProduct();
                    IfoDerivativeTypeEnum derivativeType =
                        product.getDerivativeType();
                    switch (derivativeType.intValue())
                    {
                        case IfoDerivativeTypeEnum.IntValues.FUTURES :
                            return DataType.INDEX_FUTURE;
                        case IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS :
                        case IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS :
                            return DataType.INDEX_OPTION;
                        default :
                            return DataType.UNDEFINED;
                    }
                }
            default :
                return DataType.UNDEFINED;
        }
    }
}
@
