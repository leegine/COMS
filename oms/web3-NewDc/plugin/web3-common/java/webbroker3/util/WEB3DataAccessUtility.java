head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DataAccessUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : DBアクセスユーティリティクラス(WEB3DataAccessUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.util;

import java.util.HashMap;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * DBアクセスユーティリティクラス。
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public final class WEB3DataAccessUtility
{

    /**
     * コンストラクタ。<BR>
     */
    private WEB3DataAccessUtility()
    {
    }

    /**
     * レコードを挿入する。<BR>
     *<BR>
     * @@param l_row 挿入対象の Row オブジェクト
     * @@return プライマリキーオブジェクト
     */
    public static Object insertRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        return l_qp.doInsertQuery(l_row);
    }

    /**
     * レコードを更新する。<BR>
     *<BR>
     * @@param l_row 更新対象の Row オブジェクト
     * @@return 更新件数
     */
    public static int updateRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doUpdateQuery(l_row);
    }

    /**
     * レコードの一部を更新する。<BR>
     *<BR>
     * @@param l_row 更新対象の Row オブジェクト
     * @@param l_changes 更新箇所（項目名と値のペア）
     * @@return 更新件数
     */
    public static int updateRow(Row l_row, HashMap l_changes) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        PrimaryKey l_pk = l_row.getPrimaryKey();
        return l_qp.doUpdateQuery(l_pk, l_changes);
    }

    /**
     * レコードを削除する。<BR>
     *<BR>
     * @@param l_row 削除対象の Row オブジェクト
     * @@return 削除件数
     */
    public static int deleteRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        PrimaryKey l_pk = l_row.getPrimaryKey();
        return l_qp.doDeleteQuery(l_pk);
    }
}
@
