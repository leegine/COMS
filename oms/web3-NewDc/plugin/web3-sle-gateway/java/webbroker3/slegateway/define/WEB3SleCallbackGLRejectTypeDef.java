head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackGLRejectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コールバック用GL SLEの拒否タイプ定義インタフェイス(WEB3SleCallbackGLRejectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/1 李 (FTL) 新規作成
*/
package webbroker3.slegateway.define;


/**
 * SLEコールバック処理用のGL SLEの拒否タイプ定義インタフェイス
 *
 * @@author 李（FTL)
 * @@version 1.0
 */

public class WEB3SleCallbackGLRejectTypeDef {

    /**
     * GL022:全約定後訂正注文拒否
     */
    public static final String CHANGE_REJECT_AF_ALL_EXEC = "GL 022";
    
    /**
     * GL007：一部約定後訂正注文拒否
     */
    public static final String CHANGE_REJECT_AF_PART_EXEC = "GL 007";

    /**
     * GL013:取引所への物理接続が確立されていません｡
     */
    public static final String EXCHNAGE_PHY_CONN_FAIL = "GL 013";
    
    /**
     * No Chann:広発GF－ＦＩＸエンジン（プライマリ・セカンダリ）両系障害
     */
    public static final String GF_FIX_FAILOVER_FAIL = "No Chann";
    
    /**
     *  Communi: 広発（OMS）切替（プライマリ→セカンダリ）時障害
     */
    public static final String GF_OMS_FAILOVER_FAIL = " Communi";
    
    
    /** 国泰君安OMS二重注文エラーのエラーコード */
    public static final String GJS_NW_CODE_DUPLI = "-150906090";

}
@
