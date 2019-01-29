head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontSwitchStartDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 切替起動区分 定数定義インタフェイス(WEB3AdminFrontSwitchStartDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.define;

/**
 * 切替起動区分 定数定義インタフェイス
 *
 * @@author SCS 佐藤
 * @@version 1.0
 */
public interface WEB3AdminFrontSwitchStartDivDef {
    
    /**
     * 発注経路新規切替起動
     */
    public final static String FRONT_ORDER_ROUTE_SWITCH = "0";
    
    /**
     * 通番照会要求再起動
     */
    public final static String NUBER_REF_REQ_RESTART = "1";
    
    /**
     * 通知代行解除要求再起動
     */
    public final static String NOTICEAGENCY_REL_REQ_RESTART = "2";
    
    /**
     * 通知代行要求再起動
     */
    public final static String NOTICEAGENCY_REQ_RESTART = "3";
    
    /**
     * 通知再送要求（受付系）再起動
     */
    public final static String NOTICERESEND_REQ_ACC_RESTART = "4";
    
    /**
     * 通知再送要求（約定系）再起動
     */
    public final static String NOTICERESEND_REQ_CONT_RESTART = "5";
    
    /**
     * 全訂正処理再起動
     */
    public final static String ALLCORR_REQ_RESTART = "6";
    
    /**
     * 切替処理完了
     */
    public final static String FRONT_ORDER_CHANGE_COMPLETE = "7";
    
    /**
     * 切替不可
     */
    public final static String FRONT_ORDER_NO_CHANGE = "9";
}
@
