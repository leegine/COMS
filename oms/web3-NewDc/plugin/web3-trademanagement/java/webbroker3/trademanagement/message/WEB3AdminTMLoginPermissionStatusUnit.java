head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginPermissionStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン許可状況(WEB3AdminTMLoginPermissionStatusUnit.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMLoginPermissionDivDef;

/**
 * （ログイン許可状況）<BR>
 * <BR>
 * WEB3AdminTMLoginPermissionStatusUnit<BR>
 * <BR>
 * WEB3AdminTMLoginPermissionStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLoginPermissionStatusUnit extends Message
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginPermissionStatusUnit.class);
    /**
     * （部店コード）<BR>
     * <BR>
     * 部店コード<BR>
     * <BR>
     * branchCode<BR>
     */
    public String branchCode;

    /**
     * （部店名）<BR>
     * <BR>
     * 部店名<BR>
     * <BR>
     * branchName<BR>
     */
    public String branchName;

    /**
     * （ログイン許可区分）<BR>
     * <BR>
     * 0：　@停止<BR>
     * 1：　@可能<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （loginPermissionDiv）<BR>
     * <BR>
     * 0：　@Def.DISABLE<BR>
     * 1：　@Def.ENABLE <BR>
     * <BR>
     * ※The latest DB data is set in the AP layer.<BR>
     */
    public String loginPermissionDiv;

    /**
     * （変更後ログイン許可区分）<BR>
     * 変更後のログイン許可区分<BR>
     * <BR>
     * 0：　@停止<BR>
     * 1：　@可能<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （afterLoginPermissionDiv）<BR>
     * <BR>
     * 0 : Def.DISABLE<BR>
     * 1 : Def.ENABLE <BR>
     * <BR>
     * ※The input value in PR layer is set.<BR>
     */
    public String afterLoginPermissionDiv = null;

    /**
     * コンストラクタ<BR>
     * <BR>
     * @@roseuid 41776D77037F
     */
    public WEB3AdminTMLoginPermissionStatusUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）ログイン許可区分チェック<BR>
     * 　@this.ログイン許可区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.ログイン許可区分が以下の値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「ログイン許可区分エラー(未定義の値)」の例外をスローする。<BR>
     * 　@　@　@　@　@・"0：停止"<BR>
     * 　@　@　@　@　@・"1：可能"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01409<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *   1-1)If this.branchCode == null<BR>
     *           Throw the following error [branchCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 2)loginPermissionDiv check<BR>
     *     2-1)If "this.loginPermissionDiv != null" and<BR>
     *              "this.loginPermissionDiv is not one of the following"<BR>
     *              Throw the following error [loginPermissionDiv error
     * (undefined)]<BR>
     *        (1)0 : Def.DISABLE<BR>
     *        (2)1 : Def.ENABLE<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01409<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41775E310295
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //  1-1when branchCode = null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1loginPermissionDiv != null throw the Exception.
        if (this.loginPermissionDiv != null)
        {
            if ((!WEB3AdminTMLoginPermissionDivDef.DISABLE.equals(this.loginPermissionDiv))
                && (!WEB3AdminTMLoginPermissionDivDef.ENABLE.equals(this.loginPermissionDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01409,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
