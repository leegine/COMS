head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoIndexMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP指数マスタクラス(WEB3IfoIndexMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李強 新規作成
              001: 2004/08/13 王暁傑 対応バグ BUG94  
                       public WEB3IfoIndexMaster(String l_strUnderlyingProductCode,String l_strFuturesOptionDiv)を追加 
Revesion History : 2008/07/24 安陽 (中訊) 仕様変更 モデル888
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;

import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.data.IfoIndexMasterDao;


/**
 * (先物OP指数マスタ)<BR>
 * 先物OP指数マスタクラス<BR>
 * (DBレイアウト 「先物OP指数マスタテーブル.xls」参照)<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3IfoIndexMaster 
{

    /**
     * (先物OP指数マスタRow)<BR>
     * <BR>
     * 先物OP指数マスタ行オブジェクト<BR>
     * （自動生成DAOクラス）<BR>
     */
    private IfoIndexMasterRow futuresOptionIndexMasterRow;
    
    /**
     * @@roseuid 40C075110203
     */
    public WEB3IfoIndexMaster() 
    {
     
    }
    
    /**
     * (先物OP指数マスタ)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の指数ＩＤに一致する先物OP指数マスタオブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて先物OP指数マスタテーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（先物OP指数マスタRow）を<BR>
     * this.先物OP指数マスタRowにセットする。<BR>
     * @@param l_lngIndexID - 指数ＩＤ
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E73F302D3
     */
    public WEB3IfoIndexMaster(long l_lngIndexID) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {
        futuresOptionIndexMasterRow = 
            IfoIndexMasterDao.findRowByPk(l_lngIndexID);       
       
    }

    /**
     * (先物OP指数マスタ)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の原資産銘柄コード、先物/オプション区分に一致する先物OP指数マスタオブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて先物OP指数マスタテーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（先物OP指数マスタRow）を<BR>
     * this.先物OP指数マスタRowにセットする。<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@param l_strFuturesOptionDiv - 先物／オプション区分
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E73F302D3
     */
    public WEB3IfoIndexMaster(String l_strUnderlyingProductCode,String l_strFuturesOptionDiv) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {
        futuresOptionIndexMasterRow = 
            IfoIndexMasterDao.findRowByUnderlyingProductCodeFutureOptionDiv(l_strUnderlyingProductCode, l_strFuturesOptionDiv);           
    }
        
    /**
     * this.先物OP指数マスタRowを返却する。<BR>
     * @@return Object
     * @@roseuid 405E73F302D2
     */
    public Object getDataSourceObject() 
    {
        return futuresOptionIndexMasterRow;
    }

}
@
