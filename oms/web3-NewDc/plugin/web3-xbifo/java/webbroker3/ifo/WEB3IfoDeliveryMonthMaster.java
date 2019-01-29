head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDeliveryMonthMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP限月マスタ(WEB3IfoDeliveryMonthMaster.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/24 安陽(中訊) 新規作成 モデル888, 892, 893
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.data.IfoDeliveryMonthMasterDao;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物OP限月マスタ)<BR>
 * 先物OP限月マスタクラス<BR>
 * <BR>
 * （DBレイアウト 「先物OP限月マスタテーブル.xls」参照）<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3IfoDeliveryMonthMaster
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDeliveryMonthMaster.class);

    /**
     * (先物OP限月マスタRow)<BR>
     * 先物OP限月マスタ行オブジェクト<BR>
     * （自動生成DAOクラス）<BR>
     */
    private IfoDeliveryMonthMasterRow ifoDeliveryMonthMasterRow;

    /**
     * this.先物OP限月マスタRowを返却する。<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.ifoDeliveryMonthMasterRow;
    }

    /**
     * (先物OP限月マスタ)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の原資産銘柄コード、先物/オプション区分、限月に一致する<BR>
     * 先物OP限月マスタオブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて先物OP限月マスタテーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（先物OP限月マスタRow）を<BR>
     * this.先物OP限月マスタRowにセットする。<BR>
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * 原資産銘柄コード<BR>
     * @@param l_strFuturesOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@param l_strMonthOfDelivery - (限月)<BR>
     * 限月<BR>
     * @@throws WEB3BaseException
     */
    public WEB3IfoDeliveryMonthMaster(
        String l_strUnderlyingProductCode, String l_strFuturesOptionDiv, String l_strMonthOfDelivery)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3IfoDeliveryMonthMaster(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            this.ifoDeliveryMonthMasterRow =
                IfoDeliveryMonthMasterDao.findRowByPk(
                    l_strUnderlyingProductCode,
                    l_strFuturesOptionDiv,
                    l_strMonthOfDelivery);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
    }
}@
