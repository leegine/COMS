head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研 証券ソリューションシステム第二部
 Author Name         : Daiwa Institute of Research
 File Name           : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.java)
 Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
 */

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄクラス<BR>
 * <BR>
 *
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadRequest extends WEB3AdminSrvRegiOtherOrgIdCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101401L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class);

    /**
     * (サービス利用ソートキー)<BR>
     * 対象項目<BR>
     * 通番<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;

    /**
     * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
     * デフォルトコンストラクタ<BR>
     *
     * @@roseuid 47B8D6BD00BC
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １） スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２） サービス利用ソートキーのチェック<BR>
     * ２－１） this.サービス利用ソートキー==nullの場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00231<BR>
     * ２－２） this.サービス利用ソートキーの要素数==0の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00232<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3E8680235
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        // サービス利用ソートキーのチェック
        // this.サービス利用ソートキー==nullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName()+ "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // this.サービス利用ソートキーの要素数==0の場合、例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName()+ "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
    }

    /**
     * (createレスポンス)<BR>
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞレスポンスを生成して返却する。<BR>
     * <BR>
     *
     * @@return WEB3GenResponse
     * @@roseuid 47B8D6C90037
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdDownloadResponse(this);
    }
}
@
