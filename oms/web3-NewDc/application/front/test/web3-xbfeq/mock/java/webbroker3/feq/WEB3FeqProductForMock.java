head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄(WEB3FeqProductForMock)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  王暁傑(中訊) 新規作成
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式銘柄) <BR>
 * 外国株式銘柄
 * 
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3FeqProductForMock extends WEB3FeqProduct
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqProductForMock.class);
    
    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqProductForMock(ProductRow l_prow)
        throws DataQueryException, DataNetworkException
    {
        super(l_prow);
    }

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqProductForMock(FeqProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);
    }   
    
    /**
     * (get取引銘柄) <BR>
     * （getFeqTradedProduct） <BR>
     * 取引銘柄を取得する。 <BR>
     *  <BR>
     * １）　@市場コードリセット <BR>
     * 　@取引時間管理.reset市場コード()にて、市場をセットする。 <BR>
     *  <BR>
     * 　@[reset市場コード()に指定する引数] <BR>
     * 　@市場コード：　@this.get市場コード() <BR>
     *  <BR>
     * ２）　@取引銘柄取得 <BR>
     * 　@外国株式プロダクトマネージャ.getFeqTradedProduct()にて <BR>
     * 　@外国株式取引銘柄を取得し返却する。 <BR>
     *  <BR>
     * 　@[getFeqTradedProduct()に指定する引数] <BR>
     * 　@証券会社：　@this.getInstitution() <BR>
     * 　@銘柄コード：　@this.getProductCode() <BR>
     * 　@市場コード：　@this.get市場コード() <BR>
     * @@return WEB3FeqTradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 4282C53802E1
     */
    public WEB3FeqTradedProduct getFeqTradedProduct() throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqProduct",
            "getFeqTradedProduct",
            new Class[] {}))
        {
        	log.debug("webbroker3.feq.WEB3FeqProductForMock.getFeqTradedProduct()");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getFeqTradedProduct",
                    new Class[] {}).asWEB3BaseException();
        	
        	return (WEB3FeqTradedProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqProduct",
                "getFeqTradedProduct",
                new Class[] {}).asObject();       
        }
        return super.getFeqTradedProduct();
    }
    
    /**
     * (get通貨) <BR>
     * 通貨を取得する。 <BR>
     *  <BR>
     * 通貨オブジェクトを生成し返却する。 <BR>
     *  <BR>
     * [コンストラクタに指定する引数] <BR>
     * 証券会社コード：　@getInstitution().getInstitutionCode() <BR>
     * 通貨コード：　@get通貨コード() <BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42898A9001D4
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqProduct",
            "getCurrency",
            new Class[] {}))
        {
        	log.debug("webbroker3.feq.WEB3FeqProductForMock.getCurrency()");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProduct",
                    "getCurrency",
                    new Class[] {}).asWEB3BaseException();
        	
        	return (WEB3GentradeCurrency)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqProduct",
                "getCurrency",
                new Class[] {}).asObject();            
        }
        return super.getCurrency();
    }
    public String getStandardName()
    {
        throw new UnsupportedOperationException("getStandardNameは利用できない！チームリーダへ連絡してください！");
    }
}
@
