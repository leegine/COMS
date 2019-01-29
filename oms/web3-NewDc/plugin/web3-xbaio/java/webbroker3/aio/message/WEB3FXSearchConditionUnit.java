head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSearchConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FX検索条件(WEB3FXSearchConditionUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX検索条件) <BR>
 *  FX検索条件  <BR>
 * (一覧表示機@能にて一意となる検索条件を表す)
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXSearchConditionUnit extends Message
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FXSearchConditionUnit.class);
        
    /**
     * (部店コード) <BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (識別コード) <BR>
     * 識別コード
     */
    public String requestNumber;

    /**
     * (FX検索条件) <BR>
     * コンストラクタ。
     * 
     * @@roseuid 41B5B5050321
     */
    public WEB3FXSearchConditionUnit()
    {
    }

    /**
     * (validate) <BR>
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） 部店コードのチェック <BR>
     * １－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * ２） 識別コードのチェック <BR>
     * ２－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00829 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41BE4FC000D7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック 
        // １－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //２）　@識別コードのチェック 
        //２－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            log.debug("識別コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}@
