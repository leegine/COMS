head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingCalendarModelImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュールクラス(WEB3GentradeTradingCalendarModelImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 髙橋　@良和(SRA) 新規作成
Revesion History : 2004/08/11 孟 東(中訊) JavaDocを追加
Revesion History : 2004/08/11 孟 東(中訊) get発注日(long)メッソドを追加
Revesion History : 2007/12/17 武　@波(中訊) 【共通】仕様変更・モデルNo.297
Revesion History : 2007/12/17 武　@波(中訊) 【共通】仕様変更・モデルNo.306
*/
package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingCalendarModelImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
/**
 * （拡張取引カレンダモデル）<BR>
 *<BR>
 * xTradeのTradingCalendarModelを拡張したクラス。<BR>
 *<BR>
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public class WEB3GentradeTradingCalendarModelImpl
    extends TradingCalendarModelImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingCalendarModelImpl.class);

    /**
     * 取引カレンダ詳細クラスのシングルトンインスタンス<BR>
     */
    private TradingCalendarDetails tradingCalendarDetails =
        new WEB3GentradeTradingClendarDetailsImpl();
    
    /**
     * 取引カレンダ詳細を取得する。<BR>
     *（public TradingCalendarDetails getTradingCalendarDetails(long tradedProductId)<BR>
     * のオーバーライド）<BR>
     * <BR>
     * this.取引カレンダ詳細を返却する。<BR>
     * @@param l_lngTradedProductId - 取引銘柄ID<BR>
     * @@return TradingCalendarDetails <BR>
     */
    public TradingCalendarDetails getTradingCalendarDetails(long l_lngTradedProductId)
    {
        return this.tradingCalendarDetails;
    }
    
    /**
     * 取引カレンダ詳細を取得する。<BR>
     *（public TradingCalendarDetails getTradingCalendarDetails(Market l_market)<BR>
     * のオーバーライド）<BR>
     *<BR>
     * this.取引カレンダ詳細を返却する。<BR>
     * @@param l_market - 市場オブジェクト。<BR>
     * @@return TradingCalendarDetails <BR>
     */
    public TradingCalendarDetails getTradingCalendarDetails(Market l_market)
    {
        return this.tradingCalendarDetails;
    }

    /** 
     * 発注日を取得する。<BR>
     *（getCurrentBizDate(long tradedProductId）のオーバーライド）<BR>
     * <BR>
     * １）　@TradedProductDao.findRowByPk( )より、取引銘柄Rowを取得する。<BR>
     * 　@　@　@［findRowByPk( )の引数］<BR>
     * 　@　@　@　@取引銘柄ID：　@引数の取引銘柄ID<BR>
     * <BR>
     * ２）　@拡張金融オブジェクトマネージャ.getMarket( )より、市場オブジェクトを取得する。<BR>
     * 　@　@　@［getMarket( )の引数］<BR>
     * 　@　@　@　@市場ID：　@１）で取得した取引銘柄Row.市場ID<BR>
     * <BR>
     * ３）　@市場.isPTS市場( )をコールし、戻り値により以下のメソッドをコールする。<BR>
     * <BR>
     * 　@３－１）　@trueの場合<BR>
     * 　@　@　@　@　@　@取引時間管理.getPTS発注日(void)にdelegateする。<BR>
     * <BR>
     * 　@３－２）　@falseの場合 <BR>
     * 　@　@　@　@　@　@取引時間管理.get発注日(void)にdelegateする。<BR>
     * @@param l_lngTradedProductId - 取引銘柄ID<BR>
     * @@return Date <BR>
     */
    public Date getCurrentBizDate(long l_lngTradedProductId)
    {
        final String STR_METHOD_NAME = "getCurrentBizDate(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@TradedProductDao.findRowByPk( )より、取引銘柄Rowを取得する。
        //［findRowByPk( )の引数］
        //取引銘柄ID：　@引数の取引銘柄ID
        boolean l_blnIsPTSMarket = false;
        WEB3GentradeMarket l_market = null;
        try
        {
            TradedProductRow l_tradedProductRow =
                TradedProductDao.findRowByPk(l_lngTradedProductId);

            //２）　@拡張金融オブジェクトマネージャ.getMarket( )より、市場オブジェクトを取得する。
            //［getMarket( )の引数］
            //市場ID：　@１）で取得した取引銘柄Row.市場ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_genFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market =
                (WEB3GentradeMarket)l_genFinObjectManager.getMarket(l_tradedProductRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        try
        {
            //３）　@市場.isPTS市場( )をコールし、戻り値により以下のメソッドをコールする。
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        try
        {
            if (l_blnIsPTSMarket)
            {
                //３－１）　@trueの場合
                //取引時間管理.getPTS発注日(void)にdelegateする。
                return WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
            }
            else
            {
                //３－２）　@falseの場合
                //取引時間管理.get発注日(void)にdelegateする。
                return WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(wse.getErrorMessage(), wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                this.getClass().getName() + ".getCurrentBizDate()",
                wse.getMessage(),
                wse);

        }
    }
}
@
