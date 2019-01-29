head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知リクエストクラス(WEB3IfoExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
Revesion History : 2007/11/19 孟亞南 仕様変更モデルNo.796,814
*/

package webbroker3.ifo.message;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP出来終了通知リクエスト)<BR>
 * 先物OP出来終了通知リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoExecEndNotifyRequest extends WEB3BackRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "ifo_execEndNotify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112125L;

    /**
     * (証券会社コード)<BR>
     * 出来終了処理の対象となる証券会社コード。<BR>
     */
    public String institutionCode;

    /**
     * (From口座ID)<BR>
     */
    public long rangeFrom;

    /**
     * (To口座ID)<BR>
     */
    public long rangeTo;

    /**
     * (先物／オプション区分)<BR>
     * 0：DEFAULT　@1：先物　@2：オプション <BR>
     * （WEB3FuturesOptionDivDefにて定義）<BR>
     */
    public String fuOpDiv;

    /**
     * (出来終了区分)<BR>
     * 1：夕場前出来終了（夕場実施する会社）　@DEFAULT 0（出来終了（最終））<BR>
     */
    public String execEndDiv;

    /**
     * @@roseuid 40C0AE4E01D4
     */
    public WEB3IfoExecEndNotifyRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3IfoExecEndNotifyResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １） 証券会社コードチェック<BR>
     * <BR>
     * 　@this.証券会社コード == null の場合、「証券会社コードがnull」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_02775<BR>
     * <BR>
     * ２） 先物／オプション区分チェック<BR>
     * <BR>
     * 　@this.先物／オプション区分 != （"1：先物" or "2：オプション"） の場合、<BR>
     * 　@「先物／オプション区分が不正です」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_02953<BR>
     * <BR>
     * ３） 出来終了区分チェック<BR>
     * 　@this.出来終了区分 != （"1：夕場前出来終了" or "0：出来終了（最終）"） の場合、<BR>
     * 　@「出来終了区分が不正です」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_02954<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //証券会社コードチェック
        if (this.institutionCode == null)
        {
            //this.証券会社コード == null の場合
            //「証券会社コードがnull」の例外をthrowする。
            log.debug("証券会社コードがnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02775,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードがnull");
        }

        //先物／オプション区分チェック
        //this.先物／オプション区分 != （"1：先物" or "2：オプション"） の場合
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
            && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            //「先物／オプション区分が不正です」の例外をthrowする。
            log.debug("先物／オプション区分が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02953,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "先物／オプション区分が不正です。");
        }

        //出来終了区分チェック
        //this.出来終了区分 != （"1：夕場前出来終了" or "0：出来終了（最終）"） の場合
        if (!WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(this.execEndDiv)
            && !WEB3OrderexecutionEndTypeDef.DEFAULT.equals(this.execEndDiv))
        {
            //「出来終了区分が不正です」の例外をthrowする
            log.debug("出来終了区分が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "出来終了区分が不正です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
