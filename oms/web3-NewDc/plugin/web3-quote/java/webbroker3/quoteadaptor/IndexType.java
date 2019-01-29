head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	IndexType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価サービスで取扱う指数の種類を定義したクラス(IndexType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * 時価サービスで取扱う指数の種類を定義したクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class IndexType extends Enumerated
{

    /**
     * IndexTypeで使用するEnumeratedの整数値を定義したクラス
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {

        /**
         * 未定義
         */
        public static final int UNDEFINED = 0;

        /**
         * 日経平均
         */
        public static final int NIKKEI225_INDEX = 1;

        /**
         * TOPIX
         */
        public static final int TOPIX = 2;

        /**
         * 日経300指数
         */
        public static final int NIKKEI300_INDEX = 3;

        /**
         * 店頭株価指数
         */
        public static final int JASDAQ_INDEX = 4;

        /**
         * 東証2部株価指数
         */
        public static final int TSE_2ND_SECTION_INDEX = 5;

    };

    /**
     * 時価サービス上で使用する銘柄コードを定義した定数クラス<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    private static final class ProductCodes
    {

        /**
         * 未定義
         */
        private static final String UNDEFINED = "";

        /**
         * 日経平均
         */
        private static final String NIKKEI225_INDEX = "0018";

        /**
         * TOPIX
         */
        private static final String TOPIX = "0005";

        /**
         * 日経300
         */
        private static final String NIKKEI300_INDEX = "0016";

        /**
         * 店頭株価指数
         */
        private static final String JASDAQ_INDEX = "9004";

        /**
         * 東証2部株価指数
         */
        private static final String TSE_2ND_SECTION_INDEX = "9005";

    };

    /**
     * 未定義
     */
    public static final IndexType UNDEFINED =
        new IndexType(IntValues.UNDEFINED, "UNDEFINED", ProductCodes.UNDEFINED);

    /**
     * 日経平均
     */
    public static final IndexType NIKKEI225_INDEX =
        new IndexType(
            IntValues.NIKKEI225_INDEX,
            "NIKKEI225_INDEX",
            ProductCodes.NIKKEI225_INDEX);

    /**
     * TOPIX
     */
    public static final IndexType TOPIX =
        new IndexType(IntValues.TOPIX, "TOPIX", ProductCodes.TOPIX);

    /**
     * 日経300指数
     */
    public static final IndexType NIKKEI300_INDEX =
        new IndexType(
            IntValues.NIKKEI300_INDEX,
            "NIKKEI300_INDEX",
            ProductCodes.NIKKEI300_INDEX);

    /**
     * 店頭株価指数
     */
    public static final IndexType JASDAQ_INDEX =
        new IndexType(
            IntValues.JASDAQ_INDEX,
            "JASDAQ_INDEX",
            ProductCodes.JASDAQ_INDEX);

    /**
     * 東証2部株価指数
     */
    public static final IndexType TSE_2ND_SECTION_INDEX =
        new IndexType(
            IntValues.TSE_2ND_SECTION_INDEX,
            "TSE_2ND_SECTION_INDEX",
            ProductCodes.TSE_2ND_SECTION_INDEX);

    /**
     * 時価サービス上で使用する銘柄コード
     */
    private final String productCode;

    /**
     * コンストラクタ
     * 
     * @@param i 整数値
     * @@param s 文字列値
     * @@param productCode 時価サービス上で使用する銘柄コード
     */
    public IndexType(int i, String s, String productCode)
    {
        super(i, s);
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

}
@
