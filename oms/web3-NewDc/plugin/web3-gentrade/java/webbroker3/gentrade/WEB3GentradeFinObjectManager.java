head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeFinObjectManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張金融オブジェクトマネージャ(WEB3GentradeFinObjectManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 中尾　@寿彦(SRA) 新規作成
Revesion History : 2004/08/25 鄒政 (中訊) 変更 RuntimeSystemException --> WEB3BaseRuntimeException
Revesion History : 2004/10/09 孟東 (中訊) getMarketBySONAR(String , String )を追加
Revesion History : 2005/07/07 孟東 (中訊) getFeqMarkets(String)を追加
Revesion History : 2005/07/07 孟東 (中訊) getOpenFeqMarkets(String)を追加
Revesion History : 2008/02/01 栄イ (中訊) getLinkFeqMarkets(String)を追加
*/
package webbroker3.gentrade;

import java.util.List;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.FinObjectManagerImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (拡張金融オブジェクトマネージャ)<BR>
 *<BR> 
 * 金融オブジェクト（市場）に対する操作を表現します。<BR>
 * xTradeのFinObjectManagerを拡張したクラス。<BR>
 *<BR> 
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3GentradeFinObjectManager extends FinObjectManagerImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFinObjectManager.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3GentradeFinObjectManager()
    {
        super();
    }

    /**
     * (get市場)<BR>
     *<BR> 
     * 証券会社コード、市場コードより市場オブジェクトを取得します。<BR>
     * （getMarketのオーバーロード）<BR>
     * @@param l_institutionCode 証券会社コード
     * @@param l_marketCode 市場コード
     * @@return 市場オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Market getMarket(String l_strInstitutionCode, String l_strMarketCode)
        throws NotFoundException
    {
        String l_strMethodName = "getMarket(String, String)";
        log.entering(l_strMethodName);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Institution l_institution = l_accountMgr.getInstitution(l_strInstitutionCode);

        log.exiting(l_strMethodName);

        return getMarket(l_institution, l_strMarketCode);
    }

    /**
     * 証券会社オブジェクト、市場コードより市場オブジェクトを取得します。<BR>
     * （getMarketのオーバーロード）<BR>
     * @@param l_institution 証券会社オブジェクト
     * @@param l_strMarketCode 市場コード
     * @@return 市場オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Market getMarket(Institution l_institution, String l_strMarketCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMarket(Institution, String)";
        
        try
        {
            return new WEB3GentradeMarket(l_institution, l_strMarketCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Market object found with the given code : " + l_strMarketCode);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Market Object for code : " + l_strMarketCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * 市場IDより市場オブジェクトを取得します。<BR>
     * （getMarketのオーバーロード）<BR>
     * @@param l_lngMarketId 市場ID
     * @@return 市場オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Market getMarket(long l_lngMarketId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMarket(long)";
        
        try
        {
            return new WEB3GentradeMarket(l_lngMarketId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Market object found with the given id : " + l_lngMarketId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Market Object for code : " + l_lngMarketId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * 市場オブジェクトの配列を返す。<BR>
     * （getMarketsのオーバーロード）<BR>
     *<BR> 
     * @@return 市場オブジェクトの配列
     */
    public Market[] getMarkets() 
    {
        final String STR_METHOD_NAME = "getMarkets()";

        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(MarketRow.TYPE, null, "market_id", null, null);
            int l_intSize = l_lisRows.size();
            Market markets[] = new Market[l_intSize];
            for (int i = 0; i < l_intSize; i++)
            {
                markets[i] = new WEB3GentradeMarket(((MarketRow)l_lisRows.get(i)).getMarketId());
            }

            return markets;
        }
        catch (DataException de)
        {
            log.error("Exception while fetching all rows in market_table. ", de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * トレーダIDより扱者オブジェクトを返す。<BR>
     * （getTraderのオーバーロード）<BR>
     *<BR> 
     * @@param l_lngTraderId トレーダID
     * @@return 扱者オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Trader getTrader(long l_lngTraderId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTrader(long)";
        
        try
        {
            return new WEB3GentradeTrader(l_lngTraderId, false);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Trader  object found with the trader id : " + l_lngTraderId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Trader  Object for id : " + l_lngTraderId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * 証券会社オブジェクト、トレーダコード、部店コードより扱者オブジェクトを返す。<BR>
     * （getTraderのオーバーロード）<BR>
     *<BR> 
     * @@param l_inst 証券会社オブジェクト
     * @@param l_strTraderCode トレーダコード
     * @@param l_strBranchCode 部店コード
     * @@return 扱者オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Trader getTrader(Institution l_inst, String l_strTraderCode, String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTrader(Institution, String, String)";

        try
        {
            return new WEB3GentradeTrader(l_inst, l_strTraderCode, l_strBranchCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Trader  object found with the InstitutionCode,trader code, branchCode : " +
                l_inst.getInstitutionCode() + "," + l_strTraderCode + "," + l_strBranchCode);
        }
        catch (DataException de)
        {
            String msg = "Error while getting InstitutionCode,Trader code, branchCode : " +
                l_inst.getInstitutionCode() + "," + l_strTraderCode + "," + l_strBranchCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * ログインIDより扱者オブジェクトを返す。<BR>
     * （getTraderByLoginIdのオーバーロード）<BR>
     *<BR> 
     * @@param l_lngLoginId ログインID
     * @@return 扱者オブジェクト
     * @@throws NotFoundException 該当するデータが見つからなかった場合
     */
    public Trader getTraderByLoginId(long l_lngLoginId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTraderByLoginId(long)";

        try
        {
            return new WEB3GentradeTrader(l_lngLoginId, true);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("No Trader  object found with the login id : " + l_lngLoginId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Trader  Object for login id : " + l_lngLoginId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get市場BySONAR)<BR>
     * <BR>
     * 引数の証券会社コード、市場コード(SONAR)より市場オブジェクトを取得します。<BR>
     * <BR>
     * １）　@市場マスタテーブルから、以下の条件に合致するデータを取得する。<BR>
     * <BR>
     * 　@　@　@＜抽出条件＞<BR>
     * 　@　@　@証券会社コード　@＝　@引数の証券会社コード<BR>
     * 　@　@　@かつ　@市場コード(SONAR)　@＝　@引数の市場コード(SONAR)<BR>
     * <BR>
     * 　@　@　@該当データなしの場合、または該当データ複数件の場合は、例外をthrowする。<BR>
     * <BR>
     * ２）　@取得した市場オブジェクトを返す。<BR> 
     *<BR> 
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strMarketCodeSONAR - (市場コード(SONAR))
     * @@return 市場オブジェクト
     */
    public Market getMarketBySONAR(String l_strInstitutionCode, String l_strMarketCodeSONAR)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getMarketBySONAR(String, String)";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeMarket l_genMarket = null;
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            //＜抽出条件＞
            //証券会社コード　@＝　@引数の証券会社コード
            //かつ　@市場コード(SONAR)　@＝　@引数の市場コード(SONAR)
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    MarketRow.TYPE,
                    "institution_code = ? and sonar_market_code = ?",
                    new Object[] {l_strInstitutionCode, l_strMarketCodeSONAR});

            //該当データなしの場合、または該当データ複数件の場合は、例外をthrowする。
            if(l_lisRecords.size() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            MarketRow l_marketRow = (MarketRow)l_lisRecords.get(0);
            
            l_genMarket = new WEB3GentradeMarket(l_marketRow.getMarketId());
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);                
        return l_genMarket;        
    }

    /**
     * (get外株市場)<BR>
     *（getFeqMarkets）<BR>
     * <BR>
     * 外株市場を取得する。<BR>
     * <BR>
     * 以下の条件に当てはまる市場行（：MarketParams）を<BR>
     * 市場テーブルより取得する。 <BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード：　@証券会社コード<BR>
     * 市場コード：　@※アルファ@ベットで始まる市場コード<BR>
     * <BR>
     * 取得した市場行にて市場オブジェクトを生成し配列にて返却する。<BR>
     * <BR> 
     * @@param l_strInstitutionCode (証券会社コード)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeMarket[] l_genMarkets = null;
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            // [条件]
            // 証券会社コード：　@証券会社コード
            // 市場コード：　@※アルファ@ベットで始まる市場コード
            //institution_code = ? and (lower(substr(market_code,1,1)) >= 'a' and 
            //lower(substr(market_code,1,1)) <= 'z')
            String l_strWhere = "institution_code = ? and " +
                "(lower(substr(market_code, ?, ?)) >= ? and " + 
                "lower(substr(market_code, ?, ?)) <= ?) ";
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    MarketRow.TYPE,
                    l_strWhere,
                    new Object[] {
                        l_strInstitutionCode,
                        new Integer(1),
                        new Integer(1),
                        "a", 
                        new Integer(1),
                        new Integer(1),
                        "z"});

            //該当データなしの場合、例外をthrowする。
            if((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            int l_intSize = l_lisRecords.size();
            l_genMarkets = new WEB3GentradeMarket[l_intSize];

            MarketRow l_marketRow;
            for(int i = 0; i < l_intSize; i++)
            {
                l_marketRow = (MarketRow)l_lisRecords.get(i);
                l_genMarkets[i] = 
                    new WEB3GentradeMarket(l_marketRow.getMarketId());
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);                

        return l_genMarkets;
    }

    /**
     * (get取扱可能外株市場)<BR>
     *（getOpenFeqMarkets）<BR>
     * <BR>
     * 取扱可能な外株市場を取得する。<BR>
     * <BR>
     * this.get外株市場()の戻り値より、<BR>
     * 取引停止でないもの（市場.is取引停止 == false）を配列にて返却する。<BR> 
     * <BR> 
     * @@param l_strInstitutionCode (証券会社コード)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getOpenFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOpenFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMarket[] l_genMarkets = null;

        ArrayList l_lisOpenFeqMarkets = new ArrayList();

        l_genMarkets = 
            (WEB3GentradeMarket[])this.getFeqMarkets(l_strInstitutionCode);

        int l_intSize = l_genMarkets.length;

        for(int i = 0; i < l_intSize; i++)
        {
            if(!l_genMarkets[i].isSuspension())
            {
                l_lisOpenFeqMarkets.add(l_genMarkets[i]);
            }
        }

        WEB3GentradeMarket[] l_returnMarkets = 
            new WEB3GentradeMarket[l_lisOpenFeqMarkets.size()];
        
        l_lisOpenFeqMarkets.toArray(l_returnMarkets);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_returnMarkets;
    }

    /**
     * (get市場連動外株市場)<BR>
     *（getLinkFeqMarkets）<BR>
     * <BR>
     * 外株市場のうち、市場連動のものを取得する。 <BR>
     * <BR>
     * １）this.get外株市場()をコールする。 <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@引数.証券会社コード <BR>
     * <BR>
     * ２）１で取得した市場のうち、 <BR>
     * 　@　@市場プリファ@レンステーブル.市場連動区分レコードが存在するものを取得し、 <BR>
     * 　@　@結果を配列として返却する。 <BR>
     * <BR>
     * 　@　@　@[ 市場プリファ@レンステーブル　@検索条件 ] <BR>
     * 　@　@　@　@テーブル.市場ID　@=　@1の戻り値.getMarketId() <BR>
     * 　@　@　@　@プリファ@レンス項目名　@=　@feq.sle.broker <BR>
     * 　@　@　@　@項目名連番　@=　@１ <BR>
     * <BR>
     * @@param l_strInstitutionCode (証券会社コード)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getLinkFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getLinkFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMarket[] l_genMarkets = null;

        ArrayList l_lisLinkFeqMarkets = new ArrayList();

        l_genMarkets =
            (WEB3GentradeMarket[])this.getFeqMarkets(l_strInstitutionCode);

        int l_intSize = l_genMarkets.length;

        for(int i = 0; i < l_intSize; i++)
        {
            MarketPreferencesRow l_row = null;
            long l_lngMarketId = l_genMarkets[i].getMarketId();
            String l_strPreferencesName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
            try
            {
                l_row = MarketPreferencesDao.findRowByPk(
                    l_lngMarketId,
                    l_strPreferencesName,
                    1);
            }
            catch (DataFindException l_ex)
            {
                log.debug("この市場は市場連動対象ではありません(市場コード: "
                    + l_genMarkets[i].getMarketCode() + ")");
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_row != null)
            {
                l_lisLinkFeqMarkets.add(l_genMarkets[i]);
            }
        }

        WEB3GentradeMarket[] l_returnMarkets =
            new WEB3GentradeMarket[l_lisLinkFeqMarkets.size()];

        l_lisLinkFeqMarkets.toArray(l_returnMarkets);

        log.exiting(STR_METHOD_NAME);

        return l_returnMarkets;
    }
}
@
