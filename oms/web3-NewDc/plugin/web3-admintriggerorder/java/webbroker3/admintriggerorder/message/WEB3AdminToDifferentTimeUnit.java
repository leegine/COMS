head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToDifferentTimeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 乖離時間(WEB3AdminToDifferentTimeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.admintriggerorder.define.WEB3AdminToDifferentTimeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (乖離時間)<BR>
 * 乖離時間クラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToDifferentTimeUnit extends Message
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToDifferentTimeUnit.class);

    
    /**
     * (乖離時間From)<BR>
     * 乖離時間From<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * 1：　@時価受信時間<BR>
     * 2：　@トリガー起動時間<BR>
     */
    public String differentTimeFrom;
    
    /**
     * (乖離時間To)<BR>
     * 乖離時間To<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * 2：　@トリガー起動時間<BR>
     * 3：　@発注完了時間<BR>
     */
    public String differentTimeTo;
    
    /**
     * (乖離時間)<BR>
     * 乖離時間<BR>
     * (秒単位で指定)<BR>
     */
    public String differentTime;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C70271
     */
    public WEB3AdminToDifferentTimeUnit() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@乖離時間チェック<BR>
     * 　@１－１）　@this.乖離時間＝nullの場合、<BR>
     * 　@「乖離時間がnull」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02360<BR>
     * <BR>
     * 　@１－２）　@this.乖離時間が以下の条件に該当する場合、<BR>
     * 　@「乖離時間エラー」の例外をスローする。<BR>
     * 　@　@・乖離時間 ≠ 整数<BR>
     * 　@　@・乖離時間 <= 0<BR>
     * 　@　@・乖離時間.length > 4<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02361<BR>
     * <BR>
     * ２）　@乖離時間Fromチェック<BR>
     * 　@２－１）　@this.乖離時間From＝nullの場合、<BR>
     * 　@「乖離時間Fromがnull」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02362<BR>
     * <BR>
     * ３）　@乖離時間Toチェック<BR>
     * 　@３－１）　@this.乖離時間To＝nullの場合、<BR>
     * 　@「乖離時間Toがnull」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02363<BR>
     * <BR>
     * ４）　@乖離時間From/To整合性チェック<BR>
     * 　@４－１）　@this.乖離時間From/Toが下記以外の場合、<BR>
     * 　@「乖離時間整合性エラー」の例外をスローする。<BR>
     * <BR>
     * 　@　@・乖離時間From＝"時価受信時間"、かつ、乖離時間To＝"トリガー起動時間"<BR>
     * 　@　@・乖離時間From＝"時価受信時間"、かつ、乖離時間To＝"発注完了時間"<BR>
     * 　@　@・乖離時間From＝"トリガー起動時間"、かつ、乖離時間To＝"発注完了時間"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02364<BR>
     * @@roseuid 43DF0A8A03AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@乖離時間チェック
        //　@１－１）　@this.乖離時間＝nullの場合、
        //　@「乖離時間がnull」の例外をスローする。
        if (this.differentTime == null)
        {
            log.debug("乖離時間が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02360,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乖離時間が未指定です。");
        }
        
        //１－２）　@this.乖離時間が以下の条件に該当する場合、
        //「乖離時間エラー」の例外をスローする。
        //　@・乖離時間 ≠ 整数
        //　@・乖離時間 <= 0
        //　@・乖離時間.length > 4
        if (!WEB3StringTypeUtility.isInteger(this.differentTime)
            || Integer.parseInt(this.differentTime) <= 0
            || this.differentTime.length() > 4)
        {
            log.debug("乖離時間の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02361,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乖離時間の入力が不正です。");
        }
        
        //２）　@乖離時間Fromチェック
        //　@２－１）　@this.乖離時間From＝nullの場合、
        //　@「乖離時間Fromがnull」の例外をスローする。
        if (this.differentTimeFrom == null)
        {
            log.debug("乖離時間Fromが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乖離時間Fromが未指定です。");
        }
        
        //３）　@乖離時間Toチェック
        //　@３－１）　@this.乖離時間To＝nullの場合、
        //　@「乖離時間Toがnull」の例外をスローする。
        if (this.differentTimeTo == null)
        {
            log.debug("乖離時間Toが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乖離時間Toが未指定です。");
        }
        
        //４）　@乖離時間From/To整合性チェック
        //　@４－１）　@this.乖離時間From/Toが下記以外の場合、
        //　@「乖離時間整合性エラー」の例外をスローする。
        //　@　@・乖離時間From＝"時価受信時間"、かつ、乖離時間To＝"トリガー起動時間"
        //　@　@・乖離時間From＝"時価受信時間"、かつ、乖離時間To＝"発注完了時間"
        //　@　@・乖離時間From＝"トリガー起動時間"、かつ、乖離時間To＝"発注完了時間"
        if (!((WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(this.differentTimeTo))
            || (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(this.differentTimeTo))
            || (WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(this.differentTimeTo))))
        {
            log.debug("乖離時間整合性エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02364,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乖離時間整合性エラー。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
