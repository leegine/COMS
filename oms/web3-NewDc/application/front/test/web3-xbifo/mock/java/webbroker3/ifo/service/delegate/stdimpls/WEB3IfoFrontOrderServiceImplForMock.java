head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFrontOrderServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注サービスImplForMock(WEB3IfoFrontOrderServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/29 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP発注サービスImplForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImplForMock extends WEB3IfoFrontOrderServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoFrontOrderServiceImplForMock.class);

    /**
     * (is市場通知中注文IN休憩時間帯_ForMock)<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "isMarketNotifyingOrderInBreakTimeZone",
            new Class[] {IfoOrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            log.exiting(STR_METHOD_NAME);
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "isMarketNotifyingOrderInBreakTimeZone",
                new Class[] {IfoOrderUnit.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);

    }


    /**
     * (get先物OP注文取引キュー_ForMock)<BR>
     * 指定の注文単位に該当する先物OP注文取引キューを取得し返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return HostFotypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(
        IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHostFotypeOrderAll(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}))
            {

            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3）MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (HostFotypeOrderAllParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getHostFotypeOrderAll(l_orderUnit);
    }

    /**
     * (get訂正時注文Rev_ForMock)<BR>
     * 引数の訂正後注文単位オブジェクトより、<BR>
     * 訂正時に注文単位テーブル.注文Revに設定する文字列を取得し返す。<BR>
     * @@param l_orderUnit - (訂正後注文単位)<BR>
     * 訂正後の注文単位オブジェクト。<BR>
     * （xTrade標準項目に、訂正後の値が設定されているオブジェクト）<BR>
     * ※更新インタセプタ.mutate()内部からコールされることを前提としている。<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderRev(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeOrderRev",
            new Class[] {IfoOrderUnit.class}))
        {
        
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3）MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeOrderRev",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeOrderRev(l_orderUnit);
    }

    /**
     * (get訂正取消時発注経路区分(Mock))<BR>
     * 引数の訂正取消対象の注文単位オブジェクトより、発注可能な発注経路区分を取得し返却する。<BR>
     * －原則として、現在有効な発注経路を返却する。(BR)
     * －発注経路変更不可の経路を通して発注した注文の場合は、注文時の経路を返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}))
            {

            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3）MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeSubmitOrderRouteDiv",
                new Class[] {IfoOrderUnit.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeSubmitOrderRouteDiv(l_orderUnit);
    }
    
    public String getOrderMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
				new Class[] { IfoOrderUnit.class }, new Object[] { l_orderUnit });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
				new Class[] { IfoOrderUnit.class }))
		{
			log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplForMock.getOrderMQDataCode(IfoOrderUnit)");

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
					new Class[] { IfoOrderUnit.class }).asWEB3BaseException();

			return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl", "getOrderMQDataCode",
					new Class[] { IfoOrderUnit.class }).asObject();

		}
		return super.getOrderMQDataCode(l_orderUnit);
	}


    /**
     * (get発注経路区分(Mock))<BR>
     * 発注可能な発注経路区分を取得し返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        String l_strInstitutionCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getSubmitOrderRouteDiv",
            new Class[] {String.class, String.class},
            new Object[]{l_strInstitutionCode, l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}))
            {
            
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}).asWEB3BaseException();

            //3）MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getSubmitOrderRouteDiv",
                new Class[] {String.class, String.class}).asObject();
            }

        log.exiting(STR_METHOD_NAME);
        return super.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
    }

    /**
     * (getフロント発注取引所区分コード(Mock))<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WebⅢの市場コード。
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode",
            new Class[] {String.class},
            new Object[]{l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode",
            new Class[] {String.class}))
        {
            //2)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getFrontOrderExchangeCode",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrontOrderExchangeCode(l_strMarketCode);
    }

    /**
     * (getフロント発注システム区分(Mock))<BR>
     * 引数の市場コードにより、フロント発注システム区分を取得し返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード<BR>
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderSystemCode(String)--->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode",
            new Class[] {String.class},
            new Object[]{l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode",
            new Class[] {String.class}))
        {
            //2)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getFrontOrderSystemCode",
                new Class[] {String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrontOrderSystemCode(l_strMarketCode);
    }

    /**
     * (get社内処理項目(Mock))<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「社内処理項目」設定用文字列を<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCorpCode(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getCorpCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getCorpCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getCorpCode(l_orderUnit);

    }

    /**
     * (get（被訂正）社内処理項目)<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrgCorpCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrgCorpCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrgCorpCode(l_orderUnit);
    }
    
    public void lockHostFotypeOrderAll(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "lockHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class},
                new Object[]{l_orderUnit});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "lockHostFotypeOrderAll",
                new Class[] {IfoOrderUnit.class}))
        {
        	log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplForMock.lockHostFotypeOrderAll(IfoOrderUnit)");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "lockHostFotypeOrderAll",
                    new Class[] {IfoOrderUnit.class}).asWEB3BaseException();
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "lockHostFotypeOrderAll",
                    new Class[] {IfoOrderUnit.class}).asVoid();
        	return;
        }
        
    	super.lockHostFotypeOrderAll(l_orderUnit);
    }

    /**
     * (get発注先切替(Mock))<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return 発注先切替
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderSwitching(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderSwitching",
            new Class[] {IfoOrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (WEB3GentradeOrderSwitching)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getOrderSwitching",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderSwitching(l_orderUnit);
    }
 
    /**
     * (get訂正取消時MQデータコード(mock))<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeCancelMQDataCode(IfoOrderUnit)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode",
            new Class[] {IfoOrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode",
            new Class[] {IfoOrderUnit.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeCancelMQDataCode",
                new Class[] {IfoOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                "getChangeCancelMQDataCode",
                new Class[] {IfoOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getChangeCancelMQDataCode(l_orderUnit);
    }

}
@
