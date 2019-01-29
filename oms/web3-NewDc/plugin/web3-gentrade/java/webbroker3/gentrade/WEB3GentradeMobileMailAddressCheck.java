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
filename	WEB3GentradeMobileMailAddressCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレス携帯チェッククラス(WEB3GentradeMobileMailAddressCheck.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/26 張騰宇 (中訊) 新規作成 モデル347
Revision History : 2010/02/23 趙林鵬 (中訊) 仕様変更 モデル350，352
*/

package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メールアドレス携帯チェック)<BR>
 * 携帯メールアドレスのチェック機@能を実装するユーティリティ・クラス。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3GentradeMobileMailAddressCheck
{
    /**
     * ログ出力<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMobileMailAddressCheck.class);

    /**
     * (validate携帯アドレス)<BR>
     * 入力したメールアドレスが携帯メールアドレスにであるかチェックする。<BR>
     * <BR>
     * １）引数.メールアドレスの"@@"後の部分を取得する（※１）。<BR>
     * <BR>
     * ２）携帯メールアドレスにであるかチェックする。<BR>
     * ２－１）システムプリファ@レンステーブルより以下の条件で検索する。<BR>
     * 　@　@[条件] <BR>
     * 　@　@名称（環境変数名）like 'mobile.mail.%'<BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ２－２）取得したレコードの要素数分LOOP処理。<BR>
     * 　@　@　@　@　@システムプリファ@レンステーブル.値がnullでない場合、<BR>
     * 　@　@　@　@　@システムプリファ@レンステーブル.値は１）で取得した<BR>
     * 　@　@　@　@　@メールアドレス"@@"後の部分に含まれている場合、エラーをスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_03168<BR>
     * <BR>
     * （※１）の例：携帯メールアドレス：test1＠pdx.ne.jp<BR>
     * 　@　@　@　@　@　@　@　@携帯メールアドレス"@@"後の部分：pdx.ne.jp<BR>
     * 　@　@　@　@　@　@　@　@携帯メールアドレス接尾語：pdx<BR>
     * @@param l_strMailAddress - メールアドレス
     * @@throws WEB3BaseException
     */
    public static void validateMobileAddress(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMobileAddress(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strMailAddress == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //引数.メールアドレスの"@@"後の部分を取得する（※１）。
        int l_intIndexOf = l_strMailAddress.indexOf("@@");
        String l_strSub = l_strMailAddress.substring(l_intIndexOf + 1);

        //システムプリファ@レンステーブルより以下の条件で検索する。
        //[条件]
        //名称（環境変数名）like 'mobile.mail.%'
        String l_strWhere = " name like ? || '%'";
        Object[] l_objConds =  new Object[]{"mobile.mail."};
        QueryProcessor l_queryProcessor = null;

        List l_lisRecordexcs = null;

        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere,
                l_objConds);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果が取得できなかった場合、エラーをスローする。
        if (l_lisRecordexcs.isEmpty())
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                 "テーブルに該当するデータがありません。");
        }

        //取得したレコードの要素数分LOOP処理。
        int l_intSize = l_lisRecordexcs.size();
        for (int i = 0; i < l_intSize; i++)
        {
            SystemPreferencesRow l_systemPreferencesRow = (SystemPreferencesRow)l_lisRecordexcs.get(i);
            String l_strValue = l_systemPreferencesRow.getValue();

            //システムプリファ@レンステーブル.値がnullでない場合、
            if (l_strValue != null)
            {
                //システムプリファ@レンステーブル.値は１）で取得した
                //メールアドレス"@@"後の部分に含まれている場合、エラーをスローする。
                int l_intFlag = l_strSub.indexOf(l_strValue);
                if (l_intFlag >= 0)
                {
                    log.debug("入力されるメールアドレスは携帯メールアドレスである。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                         WEB3ErrorCatalog.BUSINESS_ERROR_03168,
                         WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                         "入力されるメールアドレスは携帯メールアドレスである。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePCメールアドレス)<BR>
     * 入力したメールアドレスがPCメールアドレスにであるかチェックする。<BR>
     * <BR>
     * 　@　@PCメールアドレスチェック<BR>
     * 　@　@[this.validate携帯メールアドレス() に指定する引数]<BR>
     * 　@　@　@l_str： this.メールアドレス<BR>
     * <BR>
     * 　@　@１）PCメールアドレスの場合（this.validate携帯メールアドレス が正常終了）<BR>
     * 、処理を行わず終了する。<BR>
     * <BR>
     * 　@　@２）携帯メールアドレスの場合（this.validate携帯メールアドレスがエラー発生）<BR>
     * 、エラーをスローする。<BR>
     * <BR>
     * @@param l_strMailAddress - (メールアドレス)<BR>
     * メールアドレス<BR>
     * @@throws WEB3BaseException
     */
    public static void validatePCMailAddress(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePCMailAddress(String)";
        log.entering(STR_METHOD_NAME);

        //PCメールアドレスチェック
        //[this.validate携帯メールアドレス() に指定する引数]
        //l_str： this.メールアドレス
        validateMobileAddress(l_strMailAddress);

        log.exiting(STR_METHOD_NAME);
    }
}
@
