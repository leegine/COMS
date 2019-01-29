head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteProductCodes.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 銘柄コードを定義したクラス(WEB3QuoteConstants2.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/03 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import webbroker3.quoteadaptor.IndexType;

/**
 * 時価サーバーから送られてくる銘柄コードを定義したクラス
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
final class WEB3QuoteProductCodes
{
    /**
     * 株式（接尾辞）
     */
    static final String EQUITY_SUFFIX = "0000";

    /**
     * 日経平均株価（指数）
     */
    static final String NIKKEI225_INDEX = "0018";

    /**
     * TOPIX（指数）
     */
    static final String TOPIX = "0005";

    /**
     * 日経300（指数）
     */
    static final String NIKKEI300_INDEX = "0016";

    /**
     * 店頭株価指数
     */
    static final String JASDAQ_INDEX = "9004";

    /**
     * 東証2部指数
     */
    static final String TSE_2ND_SECTION_INDEX = "9005";

    /**
     * 株の時価用銘柄コードを、
     * 業務ロジックの銘柄コードのフォーマットに変換する。
     * 
     * @@param productCode 時価用の銘柄コード
     * @@return 業務ロジック上の銘柄コード
     */
    static final String toEquityProductCode(String productCode)
    {
        if (productCode.length() == 9 && productCode.endsWith(EQUITY_SUFFIX))
        {
            return productCode.substring(0, 5);
        } else
        {
            StringBuffer message = new StringBuffer();
            message.append("Equity ProductCode [");
            message.append(productCode).append("]");
            message.append(" was not defined.");
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * 指数のIndexTypeから時価情報の銘柄コードに変換する。
     * 
     * @@param indexType 指数種類
     * @@return 時価情報の銘柄コード
     */
    static final String toIndexProductCode(IndexType indexType)
    {
        // TODO 銘柄コードが決まったらIndexTypeに移動する
        switch (indexType.intValue())
        {
            case IndexType.IntValues.NIKKEI225_INDEX :
                return NIKKEI225_INDEX;
            case IndexType.IntValues.TOPIX :
                return TOPIX;
            case IndexType.IntValues.NIKKEI300_INDEX :
                return NIKKEI300_INDEX;
            case IndexType.IntValues.JASDAQ_INDEX :
                return JASDAQ_INDEX;
            case IndexType.IntValues.TSE_2ND_SECTION_INDEX :
                return TSE_2ND_SECTION_INDEX;
            default :
                StringBuffer message = new StringBuffer();
                message.append("IndexType[").append(indexType).append("]");
                message.append(
                    " is unknown IndexType in WEB3QuoteDataSupplierService.");
                throw new IllegalArgumentException(message.toString());
        }
    }

}
@
