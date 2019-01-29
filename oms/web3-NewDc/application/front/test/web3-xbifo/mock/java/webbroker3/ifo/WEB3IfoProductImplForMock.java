head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoProductImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP銘柄オブジェクトクラスForMock(WEB3IfoProductImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP銘柄オブジェクトクラスForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoProductImplForMock extends WEB3IfoProductImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3IfoProductImplForMock.class);

    /**
     * コンストラクタ。<BR>
     * @@param l_lngProductID - 銘柄ＩＤ
     */
    public WEB3IfoProductImplForMock(long l_lngProductID) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(IfoProductDao.findRowByPk(l_lngProductID)); 
    }
    
    public WEB3IfoProductImplForMock(ProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
    }

    public WEB3IfoProductImplForMock(IfoProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
    }
    
    public Market getPrimaryMarket()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductImpl", "getPrimaryMarket",
                new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductImpl", "getPrimaryMarket",
                new Class[]
                {}))
        {
            log.debug("webbroker3.ifo.WEB3IfoProductImplForMock.getPrimaryMarket()");
            return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoProductImpl",
                    "getPrimaryMarket", new Class[]
                    {}).asObject();
        }
        return super.getPrimaryMarket();
    }
    public String getStandardName()
    {
        throw new UnsupportedOperationException("getStandardNameは利用できない！チームリーダへ連絡してください！");
    }
}
@
