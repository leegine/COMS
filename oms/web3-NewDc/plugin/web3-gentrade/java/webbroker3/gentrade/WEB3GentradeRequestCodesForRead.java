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
filename	WEB3GentradeRequestCodesForRead.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理対象データコード(WEB3GentradeRequestCodesForRead.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.RequestCodesForReadDao;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (処理対象データコード) <BR>
 * 処理対象データコードテーブルの１レコードを表現する。<BR>
 * 発注ルートバックアップ対応のAPで使用する。<BR>
 * <BR>
 * （DBレイアウト 「処理対象データコードテーブル仕様.xls」参照）<BR>
 */
public class WEB3GentradeRequestCodesForRead implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeRequestCodesForRead.class);

    /**
     * 処理対象データコードRowオブジェクト
     * （DAO自動生成クラス）
     */
    private RequestCodesForReadRow requestCodesForReadRow;

    /**
     * コンストラクタ。<BR>
     * 引数の条件に一致する処理対象データコードオブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて処理対象データコードテーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（処理対象データコードRow）を、<BR>
     * 　@this.処理対象データコードRowにセットする。<BR>
     * @@param l_strRequestCode - データコード<BR>
     * @@param l_strPType - ポリモルフィックタイプ<BR>
     *    （発注ルートバックアップ対応APのメッセージPTYPE）<BR>
     * @@return WEB3GentradeRequestCodesForRead
     * @@throws WEB3SystemLayerException
     * @@roseuid 42401BE50205
     */
    public WEB3GentradeRequestCodesForRead(
        String l_strRequestCode,
        String l_strPType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeRequestCodesForRead(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            this.requestCodesForReadRow =
                RequestCodesForReadDao.findRowByPk(l_strRequestCode,l_strPType);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数のRowオブジェクトをプロパティにセットする。<BR>
     * @@param l_requestCodesForReadRow - 処理対象データコードRowオブジェクト
     * @@return WEB3GentradeRequestCodesForRead
     * @@roseuid 42401BE501E5
     */
    public WEB3GentradeRequestCodesForRead(RequestCodesForReadRow l_requestCodesForReadRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeRequestCodesForRead(RequestCodesForReadRow)";
        if (l_requestCodesForReadRow == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理対象データコードRowオブジェクト = null");
        }
        this.requestCodesForReadRow = l_requestCodesForReadRow;
    }

    /**
     * this.処理対象データコードRowを返却する。
     * @@return java.lang.Object
     * @@roseuid 4240C9F20346
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.requestCodesForReadRow;
    }

    /**
     * (get処理対象データコードRow一覧)<BR>
     * （staticメソッド）<BR>
     * 処理対象データコードテーブルより、<BR>
     * 指定されたポリモルフィックタイプ（PTYPE）に対するレコードを全て取得し返す<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@処理対象データコードテーブルより、引数のポリモルフィックタイプに<BR>
     *   該当するレコードを全レコード取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@ポリモルフィックタイプ = 引数のポリモルフィックタイプ<BR>
     * <BR>
     * ２）　@取得したレコードを配列にして返す。<BR>
     * 　@　@　@※該当するレコードが存在しない場合は、例外をthrowする。<BR>
     * @@param l_strPType - (ポリモルフィックタイプ)<BR>
     *     対象サービスのメッセージPTYPE。<BR>
     * @@return 処理対象データコードRow[]
     * @@throws WEB3BaseException
     * @@roseuid 424018640221
     */
    public static RequestCodesForReadRow[] getRequestCodesForReadRowList(String l_strPType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRequestCodesForReadRowList(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@DB検索
            //処理対象データコードテーブルより、引数のポリモルフィックタイプに
            //該当するレコードを全レコード取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    RequestCodesForReadRow.TYPE,
                    "ptype = ?",
                    new Object[] { l_strPType });
            
            //２）　@取得したレコードを配列にして返す。
            //   ※該当するレコードが存在しない場合は、例外をthrowする。
            int l_intSize = l_lstRecords.size();
            if(l_intSize == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3GentradeRequestCodesForRead.class.getName() + "." + STR_METHOD_NAME,
                    "処理対象データコードテーブルより、指定されたポリモルフィックタイプ（PTYPE）に対するレコードを取得しない");
            }
            RequestCodesForReadRow[] l_requestCodesForReadRows = new RequestCodesForReadRow[l_intSize];
            for(int i = 0 ; i < l_intSize; i++)
            {
                l_requestCodesForReadRows[i] = (RequestCodesForReadRow)l_lstRecords.get(i);
            }
            log.exiting(STR_METHOD_NAME);
            return l_requestCodesForReadRows;
            
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeRequestCodesForRead.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get処理対象データコード一覧)<BR>
     * （staticメソッド）<BR>
     * 処理対象データコードテーブルより、<BR>
     * 指定されたポリモルフィックタイプ（PTYPE）に対するデータコードの一覧を<BR>
     * 取得し、データコードのString配列を返す。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@get処理対象データコードRow一覧(引数のポリモルフィックタイプ)を実行する。<BR>
     * <BR>
     * ２）　@１）の戻り値の処理対象データコードテーブルRowの配列から、<BR>
     * 　@　@　@データコードのみでStringの配列を作成し返す。<BR>
     * <BR>
     * @@param l_strPType - (ポリモルフィックタイプ)<BR>
     *     対象サービスのメッセージPTYPE。<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 424018640231
     */
    public static String[] getRequestCodesForReadList(String l_strPType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRequestCodesForReadList(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@DB検索
        //get処理対象データコードRow一覧(引数のポリモルフィックタイプ)を実行する。
        RequestCodesForReadRow[] l_requestCodesForReadRow = 
            getRequestCodesForReadRowList(l_strPType);
        
        //２）　@１）の戻り値の処理対象データコードテーブルRowの配列から、
        //データコードのみでStringの配列を作成し返す
        int l_intSize = l_requestCodesForReadRow.length;
        String[] l_strRequestCodes = new String[l_intSize];
        for(int i = 0 ; i < l_intSize; i++)
        {
            l_strRequestCodes[i] = l_requestCodesForReadRow[i].getRequestCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestCodes;
    }

    /**
     * (get発注経路区分)<BR>
     * this.処理対象データコードRow.発注経路区分を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 42401C9D02BA
     */
    public String getSubmitOrderRouteDiv()
    {
        return this.requestCodesForReadRow.getSubmitOrderRouteDiv();
    }
}
@
