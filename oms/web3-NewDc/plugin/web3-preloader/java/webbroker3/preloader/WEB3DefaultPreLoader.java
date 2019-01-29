head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3DefaultPreLoader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3DefaultPreLoaderクラス(WEB3DefaultPreLoader.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
                  : 2005/04/19 山田　@卓司 (FLJ) プリロードをリードオンリーモードで実行するように変更
 */
package webbroker3.preloader;

import java.util.Iterator;
import java.util.List;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

/**
 * WEB3PreLoaderインターフェースのデフォルト実装クラス。<BR>
 * <BR>
 * 通常、キャッシュ・プリロードを行うテーブルごとにこのクラスのインスタンスを作成し、
 * キャッシュへのローディングを行う。
 * <BR>
 * ＜ローディングの手順＞<BR>
 * 1.コンストラクタで設定されたRowTypeのテーブルのデータを
 * 　@QueryProcessor#doFindAllQuery(RowType, String, String String, Object[])
 * 　@メソッドで検索する。<BR>
 * 　@＜設定するパラメータ＞<BR>
 * 　@RowType = コンストラクタで指定したパラメータオブジェクト#getRowType()<BR>
 *     （未指定の場合は、コンストラクタで設定したRowType）<BR> 
 * 　@String（Where句） = コンストラクタで指定したパラメータオブジェクト#getWhere()
 * 　@　@（未指定の場合は<code>null</code>）<BR>
 * 　@String（OrderBy句） = コンストラクタで指定したパラメータオブジェクト#getOrderBy()
 * 　@　@（未指定の場合は<code>null</code>）<BR>
 * 　@String（Conditions） = コンストラクタで指定したパラメータオブジェクト#getConditions()
 * 　@　@（未指定の場合は<code>null</code>）<BR>
 * 　@Object[]（バインド変数） = コンストラクタで指定したパラメータオブジェクト#getBindVars()
 * 　@　@（未指定の場合は<code>null</code>）<BR>
 * 
 * 2.「1.」で検索された各レコードに対して、
 * 　@コンストラクタで指定したWEB3PreLoaderCallback#process(Row)メソッドを
 * 　@呼び出す。<BR>
 * 　@＜設定するパラメータ＞<BR>
 * 　@Row = 「1.」で検索された各レコードのRowオブジェクト<BR> 
 * 　@　@（未指定の場合はWEB3DefaultPreLoaderCallbckのインスタンス）
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3DefaultPreLoader implements WEB3PreLoader
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3DefaultPreLoader.class);
    
    /**
     * 最初にロードするテーブルのRowType
     */
    private final RowType rowType;

    /**
     * 各レコードをキャッシュに登録するための処理が記述されたCallbackクラスのインスタンス
     */
    private final WEB3PreLoaderCallback callback;

    /**
     * 最初にロードするときの条件を保持するパラメータオブジェクト
     */
    private final WEB3DefaultPreLoaderParams params;

    /**
     * コンストラクタ
     * 
     * @@param l_rowType 最初にロードするテーブルのRowType
     */
    public WEB3DefaultPreLoader(RowType l_rowType)
    {
        this(
            l_rowType,
            new PreLoaderCallbackImpl(),
            WEB3DefaultPreLoaderParams.getDefaultParams());
    }

    /**
     * コンストラクタ
     * 
     * @@param l_rowType 最初にロードするテーブルのRowType
     * @@param l_callback 各レコードをキャッシュに登録するための処理が記述されたCallbackクラスのインスタンス
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback)
    {
        this(
            l_rowType,
            l_callback,
            WEB3DefaultPreLoaderParams.getDefaultParams());
    }

    /**
     * コンストラクタ
     * 
     * @@param l_rowType 最初にロードするテーブルのRowType
     * @@param l_params 最初にロードするときの条件を保持するパラメータオブジェクト
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3DefaultPreLoaderParams l_params)
    {
        this(l_rowType, new PreLoaderCallbackImpl(), l_params);
    }

    /**
     * コンストラクタ
     * 
     * @@param l_rowType 最初にロードするテーブルのRowType
     * @@param l_callback 各レコードをキャッシュに登録するための処理が記述されたCallbackクラスのインスタンス
     * @@param l_params 最初にロードするときの条件を保持するパラメータオブジェクト
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback,
        WEB3DefaultPreLoaderParams l_params)
    {
        rowType = l_rowType;
        callback = l_callback;
        params = l_params;
    }

    /**
     * 登録されているRowTypeを返す。
     * 
     * @@return RowType
     */
    public RowType getRowType()
    {
        return rowType;
    }

    /**
     * 登録されているCallbackオブジェクトを返す。
     * 
     * @@return Callbackオブジェクト
     */
    public WEB3PreLoaderCallback getCallback()
    {
        return callback;
    }

    /**
     * 登録されたパラメータオブジェクトを返す。
     * 
     * @@return パラメータオブジェクト
     */
    public WEB3DefaultPreLoaderParams getParams()
    {
        return params;
    }

    /**
     * キャッシュへのローディングを行う。
     * 
     * @@see webbroker3.preloader.WEB3PreLoader#execute()
     */
    public void execute()
    {

        boolean l_hasAlreadyStarted = WEB3CacheStatisticsUtils.startCollecting(); 

        LOG.info("Start loading. [table name=" + rowType.getTableName()
                 + ", callback class=" + getCallback().getClass().getName()
                 + ", initial parameters=" + getParams().toString() + "]");
        
        try
        {
            
            long l_lngStart = System.currentTimeMillis();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            TransactionCallback l_callback = new PreLoaderTransactionCallback();
            l_qp.doTransaction(l_callback);
            
            long l_lngEnd = System.currentTimeMillis();
            
            String l_strTableName = rowType.getTableName();
            long l_lngRowCacheCnt = WEB3CacheStatisticsUtils.getCachedItemCount(l_strTableName, "row");
            long l_lngEnumCacheCnt = WEB3CacheStatisticsUtils.getCachedItemCount(l_strTableName, "enum");
            
            
            LOG.info("Loading completed. table_name=" + l_strTableName 
                     + ", row=" + l_lngRowCacheCnt 
                     + ", enum=" + l_lngEnumCacheCnt 
                     + ", process_time=" + (l_lngEnd - l_lngStart) + "[msec]");
            
        } catch (DataException l_de)
        {
            LOG.error("Unexpected Exception occured while loading.", l_de);
        } finally {
            
            WEB3CacheStatisticsUtils.stopCollecting(l_hasAlreadyStarted);

        }
    }
    
    /**
     * WEB3DefaultPreLoaderCallbackのデフォルト実装クラス
     * 
     * @@author Takuji Yamada (FLJ)
     */
    private static class PreLoaderCallbackImpl
        extends WEB3DefaultPreLoaderCallback
    {

        /**
        * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
        */
        protected void load(Row l_row) throws DataException
        {
        }

    }

    /**
     * ローディング処理を行うTransactionCallbackクラス
     * 
     * @@author Takuji Yamada (FLJ)
     */
    private class PreLoaderTransactionCallback implements TransactionCallback
    {
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            RowType l_rowType = (params.getRowType() != null) ? 
                    params.getRowType() : getRowType();
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_rows =
                l_qp.doFindAllQuery(
                    l_rowType,
                    params.getWhere(),
                    params.getOrderBy(),
                    params.getConditions(),
                    params.getBindVars());
            if (l_rows != null)
            {
                try
                {
                    
                    l_qp.forceReadonlyMode(getRowType(), true);
                    
                    for (Iterator l_it = l_rows.iterator(); l_it.hasNext();)
                    {
                        Row l_row = (Row) l_it.next();
                        getCallback().process(l_row);
                    }

                } catch (DataException l_de)
                {
                    throw new DataCallbackException(l_de.getMessage(), l_de);
                } finally
                {
                    l_qp.forceReadonlyMode(getRowType(), false);
                }
            }
            return null;
        }
    }

}
@
