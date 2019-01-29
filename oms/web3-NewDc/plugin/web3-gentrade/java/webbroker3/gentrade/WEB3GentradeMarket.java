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
filename	WEB3GentradeMarket.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場(WEB3GentradeMarket)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/05 今井　@高史(SRA) 新規作成
Revesion History : 2004/02/17 今井　@高史(SRA) 実装
Revesion History : 2005/07/07 孟東 (中訊) isSuspension()を追加
Revesion History : 2005/07/07 孟東 (中訊) isSystemInterLock()を追加
Revesion History : 2006/12/25 栄イ(中訊) 仕様変更 モデル219を対応
Revesion History : 2007/12/17 柴双紅 (中訊) 仕様変更 モデルNo.284,No.300
Revesion History : 2010/01/12 趙林鵬 (中訊)【共通】仕様変更・モデルNo.349
**/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EquityPTSMarketDivDef;
import webbroker3.common.define.WEB3FeqDayTradeMarketDivDef;
import webbroker3.common.define.WEB3FeqOrderRequestDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (市場)<BR>
 */
public class WEB3GentradeMarket
    extends MarketImpl
    implements WEB3GentradeMarketValues
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMarket.class);

    /**
     * コンストラクタ<BR>
     *<BR> 
     * @@param long l_lngMarketId
     * @@roseuid 400FA00200B2
     */
    public WEB3GentradeMarket(long l_lngMarketId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_lngMarketId);
    }

    /**
     * コンストラクタ<BR>
     *<BR> 
     * @@param Institution l_institution
     * @@param String l_strMarketCode
     */
    public WEB3GentradeMarket(
        Institution l_institution,
        String l_strMarketCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institution, l_strMarketCode);
    }

    /**
     * (is取引停止)<BR>
     * 市場が取引停止かを判定する。<BR>
     * <BR>
     * （this.市場行.取引停止 == 1：取引停止中）の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR> 
     * <BR>
     * @@return boolean
     */
    public boolean isSuspension()
    {
        final String STR_METHOD_NAME = "isSuspension()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnSuspension = false;
        String l_strSuspension = null;

        MarketRow l_marketRow = (MarketRow)(this.getDataSourceObject());
        l_strSuspension = l_marketRow.getSuspension();
        
        if(WEB3SuspensionDef.SUSPENSION.equals(l_strSuspension)) 
        {
            l_blnSuspension = true;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnSuspension;
    }

    /**
     * (isシステム連動 )<BR>
     * 該当の市場がシステム連動しているかを判定する。<BR>
     * this.市場行.外国株式市場連動区分 == ”非連動” の場合、<BR>
     * falseを返却する。<BR> 
     * 以外、trueを返却する。<BR> 
     * <BR>
     * @@return boolean
     */
    public boolean isSystemInterLock()
    {
        final String STR_METHOD_NAME = "isSystemInterLock()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnInterLock = true;
        String l_strRequestDiv = null;

        MarketRow l_marketRow = (MarketRow)(this.getDataSourceObject());
        l_strRequestDiv = l_marketRow.getFeqOrderRequestDiv();

        if(WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(l_strRequestDiv)) 
        {
            l_blnInterLock = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnInterLock;
    }
    /**
     * (is優先市場コード)<BR>
     * 指定された市場コードが優先市場コードかを判定する。 <BR>
     * <BR>
     * １）　@引数の市場コードがnullの場合、 <BR>
     * 　@　@falseを返却する。 <BR>
     * <BR>
     * ２）　@引数の市場コードが"99：優先市場"の場合、 <BR>
     * 　@　@trueを返却する。 <BR>
     * <BR>
     * ３）　@上記以外の場合、 <BR>
     * 　@　@falseを返却する。 <BR>
     * <BR>
     * @@param l_strMarketCode 市場コード
     * @@return boolean
     */

    public static boolean isPriorityMarket(String l_strMarketCode)
    {
        boolean l_blnPriorityMarket = false;

        //引数の市場コードがnullの場合、falseを返却する。
        if (l_strMarketCode == null)
        {
            l_blnPriorityMarket = false;
        }

        //引数の市場コードが"99：優先市場"の場合、trueを返却する。
        else if (WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            l_blnPriorityMarket = true;
        }

        //上記以外の場合、falseを返却する。
        return l_blnPriorityMarket;
    }

    /**
     * (isPTS市場)<BR>
     * PTS市場であるか判定する。<BR>
     * PTS市場の場合、trueを、PTS市場でない場合、falseを返却する。<BR>
     * <BR>
     * １）　@市場用プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@市場ID = this.getMarketId()の戻り値<BR>
     * 　@　@　@プリファ@レンス項目名 = プリファ@レンス名.PTS市場区分<BR>
     * 　@　@　@項目名連番 = 1<BR>
     * <BR>
     * ２）　@取得レコード.プリファ@レンスの値が"PTS市場である"場合、<BR>
     * 　@　@　@trueを返却する。<BR>
     * <BR>
     * 　@　@　@上記以外、または該当レコードが存在しない場合、falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPTSMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSMarket()";
        log.entering(STR_METHOD_NAME);

        MarketPreferencesRow l_marketPreferencesRow;

        try
        {
            //市場用プリファ@レンステーブルから以下条件全てに該当するレコードを取得する
            //[条件]
            // 市場ID = this.getMarketId()の戻り値
            // プリファ@レンス項目名 = プリファ@レンス名.PTS市場区分
            // 項目名連番 = 1
            l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                this.getMarketId(),
                WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strPtsMarketDiv = l_marketPreferencesRow.getValue();
        if (WEB3EquityPTSMarketDivDef.PTS_MARKET_YES.equals(l_strPtsMarketDiv))
        {
            //取得レコード.プリファ@レンスの値が"PTS市場である"場合、trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //PTS市場でない場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is日計り市場)<BR>
     * 日計り市場であるか判定する。<BR>
     * 日計り市場の場合、trueを、日計り市場でない場合、falseを返却する。<BR>
     * <BR>
     * １）　@市場用プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@市場ID = this.getMarketId()の戻り値<BR>
     * 　@　@　@プリファ@レンス項目名 = プリファ@レンス名.外株日計り市場区分<BR>
     * 　@　@　@項目名連番 = 1<BR>
     * <BR>
     * ２）　@取得レコード.プリファ@レンスの値が"外株日計り市場である"場合、<BR>
     * 　@　@　@trueを返却する。<BR>
     * <BR>
     * 　@　@　@上記以外、または該当レコードが存在しない場合、falseを返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDayTradeMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTradeMarket()";
        log.entering(STR_METHOD_NAME);

        MarketPreferencesRow l_marketPreferencesRow = null;
        //１）　@市場用プリファ@レンステーブルから以下条件全てに該当するレコードを取得する。
        try
        {
            l_marketPreferencesRow =
                MarketPreferencesDao.findRowByMarketIdNameNameSerialNo(
                    this.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@取得レコード.プリファ@レンスの値が"外株日計り市場である"場合、、
        //trueを返却する。
        if (l_marketPreferencesRow != null
            && WEB3FeqDayTradeMarketDivDef.EXECUTE.equals(l_marketPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外、または該当レコードが存在しない場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
