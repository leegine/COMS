head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXExtConnectionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX外部接続マネージャ(WEB3FXExtConnectionManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 張騰宇 (中訊) 新規作成 仕様変更・モデル1172 モデル1179
Revision History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1206
*/

package webbroker3.aio;

import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX外部接続マネージャ)<BR>
 * FX外部接続マネージャクラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FXExtConnectionManager
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXExtConnectionManager.class);

    /**
     * (get外部接続インスタンス)<BR>
     * 外部接続インスタンスを取得する。<BR>
     * <BR>
     * 引数.外部接続システムコードより、外部接続システムのインスタンスを返却する。 <BR>
     * １－１）外部接続システムコード　@＝　@01：GFTの場合 <BR>
     * 　@　@GFT接続システムのインスタンスを返却。 <BR>
     * <BR>
     * １－２）外部接続システムコード　@＝　@05:シンプレクスの場合 <BR>
     * <BR>
     * 　@　@１－２－１）引数.処理区分　@＝　@01：口座開設の場合 <BR>
     * 　@　@　@　@　@Simplex口座開設接続システムのインスタンスを返却。 <BR>
     * <BR>
     * 　@　@１－２－２）引数.処理区分　@＝　@07：振替可能額の場合 <BR>
     * 　@　@　@　@　@Simplex振替可能額接続システムのインスタンスを返却。 <BR>
     * <BR>
     * 　@　@１－２－３）引数.処理区分　@＝　@02：入金と04：出金の場合 <BR>
     * 　@　@　@　@　@例外「処理区分が不正です。」をスローする。 <BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_03184<BR>
     * <BR>
     * １－３）以上以外の場合、例外をスローする。 <BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_03161<BR>
     * <BR>
     * @@param l_strExtConnectionSystemCode - (外部接続システムコード)<BR>
     * 外部接続システムコード<BR>
     * @@param l_strOperationDiv - (処理区分)<BR>
     * 処理区分<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public static WEB3ExtConnection getExtConnectionInstance(String l_strExtConnectionSystemCode, String l_strOperationDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExtConnectionInstance(String, String)";
        log.entering (STR_METHOD_NAME);

        //外部接続システムコード　@＝　@01：GFT　@の場合
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(l_strExtConnectionSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return new WEB3GFTConnectionSystem();
        }
        //外部接続システムコード　@＝　@05:シンプレクスの場合
        if (WEB3ExtConnectSystemCodeDef.SIMPLEX.equals(l_strExtConnectionSystemCode))
        {
            //引数.処理区分　@＝　@01：口座開設の場合 
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(l_strOperationDiv))
            {
                log.exiting(STR_METHOD_NAME);
                //Simplex口座開設接続システムのインスタンスを返却。
                return new WEB3FXSimplexAccOpenConnectionSystem();
            }
            //引数.処理区分　@＝　@07：振替可能額の場合
            else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_strOperationDiv))
            {
                log.exiting(STR_METHOD_NAME);
                //Simplex振替可能額接続システムのインスタンスを返却。
                return new WEB3FXSimplexTransferAbleAmtConnectionSystem();
            }
            //引数.処理区分　@＝　@02：入金と04：出金の場合
            else if (WEB3AdminAioGftOperationDivDef.CASH_IN.equals(l_strOperationDiv)
                || WEB3AdminAioGftOperationDivDef.CASH_OUT.equals(l_strOperationDiv))
            {
                log.debug("処理区分が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03184,
                    "getExtConnectionInstance(String, String)" + "." + STR_METHOD_NAME,
                    "処理区分が不正です。");
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //以上以外の場合、例外をthrowする
        else
        {
            log.debug("外部接続システムコードの値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03161,
                "getExtConnectionInstance(String, String)" + "." + STR_METHOD_NAME,
                "外部接続システムコードの値が不正です。");
        }
    }
}
@
