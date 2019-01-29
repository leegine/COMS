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
filename	WEB3GentradeTrader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 扱者(WEB3GentradeTrader.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 本郷　@千草(SRA) 新規作成
Revesion History : 2004/11/09 鄒政 (中訊) get扱者を追加
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;

/**
 * 顧客の扱者を表現する。<BR>
 * xTradeでは、ログイン認証にて、代行ログインが出来るという関連で扱者を表現している<BR>
 * が、業務的な意味での扱者として、部店・顧客に関連付ける。<BR>
 * xTradeのTradeを拡張したクラス。<BR>
 */
public class WEB3GentradeTrader extends TraderImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTrader.class);

    /**
     * コンストラクタ。<BR>
     *<BR> 
     * @@param l_institution 証券会社オブジェクト
     * @@param l_strTraderCode 扱者コード
     * @@param l_strBranchCode 部店コード
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     */
    public WEB3GentradeTrader(
        Institution l_institution,
        String l_strTraderCode,
        String l_strBranchCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_institution, l_strTraderCode, l_strBranchCode);
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_lngLoginId - ログインID
     * @@param l_isLoginId - ログイン有無
     * @@throws DataQueryException 
     * @@throws DataNetworkException
     * @@roseuid 400F9DFE039B
     */
    public WEB3GentradeTrader(long l_lngLoginId, boolean l_isLoginId)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngLoginId, l_isLoginId);
    }
    
    /**
     * (get扱者)<BR>
     * <BR>
     * （staticメソッド）<BR>
     *  <BR>
     * １）　@検索条件文字列の先頭に、証券会社条件を挿入する。<BR>
     *  <BR>
     * "証券会社コード = <BR>
     * " + 管理者（オペレータ）.get証券会社コード(). 証券会社コード<BR> 
     *   + 検索条件文字列<BR>
     *  <BR>
     * ２）　@扱者テーブル検索<BR>
     * QueryProcessor.doFindAllQuery( )により、扱者行を取得する。<BR> 
     *  <BR>
     *   [doFindAllQuery()に指定する引数] <BR>
     *   rowType：　@扱者Row.TYPE <BR>
     *   where：　@１）で編集した検索条件文字列 <BR>
     *   orderBy：　@ソート条件 <BR>
     *   conditions：　@null <BR>
     *   bindVars：　@検索条件データコンテナ <BR>
     *  <BR>
     * ３）　@検索結果より、条件に一致する要素の扱者List（：ArrayList）を<BR>
     * 編集する。扱者List（：ArrayList）を生成し、２）で取得した<BR>
     * 各要素について、３－１）～３－３）を実施する。
     *  <BR>
     * ３－１）　@対象要素の行オブジェクトを指定し、扱者オブジェクトを<BR>
     * 生成する。<BR>
     * <BR>
     * ３－２）　@ログイン回数の判定 ※ログイン回数条件が<BR>
     * 指定されている場合<BR>
     * （ログインエラー回数 != null && ログインエラー回数 > 0） &&<BR>
     * （扱者のログインエラー回数※1 ＜ 引数のログインエラー回数）<BR>
     * であれば、対象要素について、以降の処理は行わない。<BR>
     *  <BR>
     * ※1 扱者のログインエラー回数の取得 <BR>
     * 扱者.getLoginId()にて取得したログインＩＤに一致する行を<BR>
     * ログインテーブルから取得する。取得した行のログインエラー回数。<BR>
     *  <BR>
     * ３－３）　@部店の判定 ※オペレータが全部店許可でない場合、<BR>
     * 他部店の扱者行は取扱い不可。<BR>
     * （管理者（オペレータ）.is全部店許可 == false） && <BR>
     * （３－１）で生成した管理者.get部店コード() != 扱者.get部店コード()）<BR>
     * であれば、対象要素について、以降の処理は行わない。（continue;）<BR>
     *  <BR>
     * ３－４）　@扱者List（：ArrayList）に対象要素を追加（：add）する。<BR>
     *  <BR>
     * ４）　@扱者配列返却<BR>
     * 扱者List（：ArrayList）を配列に変換（toArray()）し、返却する。<BR>
     * <BR>
     * @@param l_operator - (管理者（オペレータ）) <BR>
     * @@param l_strWhere - (検索条件文字列) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_bindVars - (検索条件データコンテナ) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_strOrderBy - (ソート条件) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_loginErrorTimes - (ログインエラー回数) <BR>
     *    ※ 指定しない場合null <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeTrader[] getTraders(
        WEB3Administrator l_operator,
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy,
        Integer l_loginErrorTimes)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTraders(WEB3Administrator, String, String[], String, Integer)";
        log.entering(STR_METHOD_NAME);
        
        //管理者（オペレータ）に証券会社コードを取得 
        AdministratorParams l_administratorParams =
            (AdministratorParams) l_operator.getDataSourceObject();
        String l_strInstitutionCode = l_administratorParams.getInstitutionCode();
            
        //１）　@検索条件文字列の先頭に、証券会社条件を挿入する
        String l_strTraderWhere;
        if (l_strWhere == null)
        {
            l_strTraderWhere = " institution_code = ? ";
        }
        else
        {
            l_strTraderWhere = " institution_code = ?  " + l_strWhere;
        }
        String[] l_strTraderBindVars;
        if (l_bindVars == null)
        {
            l_strTraderBindVars = new String[] { l_strInstitutionCode };
        }
        else
        {
            int l_intLength = l_bindVars.length;
            l_strTraderBindVars = new String[l_intLength + 1];
            l_strTraderBindVars[0] = l_strInstitutionCode;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strTraderBindVars[i + 1] = l_bindVars[i];
            }
        }
        
        List l_lstTraders = new ArrayList();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        try
        {
            // ２）　@扱者テーブル検索
            //[doFindAllQuery()に指定する引数] 
            //rowType：　@扱者Row.TYPE 
            //where：　@１）で編集した検索条件文字列 
            //orderBy：　@ソート条件 
            //conditions：　@null 
            //bindVars：　@検索条件データコンテナ
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    TraderRow.TYPE,
                    l_strTraderWhere,
                    l_strOrderBy,
                    null,
                    l_strTraderBindVars);
            
            //３）　@検索結果より、条件に一致する要素の扱者List（：ArrayList）を編集する
            int l_intSize = l_lstRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //３－１）　@対象要素の行オブジェクトを指定し、扱者オブジェクトを生成する
                TraderRow l_tmpRow =
                    (TraderRow)l_lstRecords.get(i);
                
                
                // 扱者のログインエラー回数の取得
                LoginRow l_loginRow = 
                    LoginDao.findRowByPk(l_tmpRow.getLoginId());
                
                //３－２）　@ログイン回数の判定
                if ((l_loginErrorTimes != null)
                    && (l_loginErrorTimes.intValue() > 0)
                    && (l_loginRow.getFailureCount() < l_loginErrorTimes.intValue()))
                {
                    continue;
                }
                
                //３－３）　@部店の判定
                if ((l_operator.isAllBranchsPermission() == false)
                    && (WEB3Toolkit.isEquals(l_operator.getBranchCode(),l_tmpRow.getBranchCode()) == false))
                {
                    continue;
                }
                
                //３－４）扱者List（：ArrayList）に対象要素を追加（：add）する。
                Institution l_institution = 
                    l_finApp.getAccountManager().getInstitution(l_tmpRow.getInstitutionCode());
                WEB3GentradeTrader l_tmpGentradeTrader =
                    new WEB3GentradeTrader(
                        l_institution,
                        l_tmpRow.getTraderCode(),
                        l_tmpRow.getBranchCode());
                l_lstTraders.add(l_tmpGentradeTrader);
                
            }
            
            //４）　@扱者配列返却
            l_intSize = l_lstTraders.size();
            WEB3GentradeTrader[] l_genTraders = new WEB3GentradeTrader[l_intSize];
            for(int i = 0; i < l_intSize; i ++)
            {
                l_genTraders[i] = (WEB3GentradeTrader)l_lstTraders.get(i);
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_genTraders;
            
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME, nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTrader.class.getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTrader.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }
}
@
