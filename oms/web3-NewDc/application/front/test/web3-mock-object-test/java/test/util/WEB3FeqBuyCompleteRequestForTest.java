head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBuyCompleteRequestForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package test.util;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3FeqBuyCompleteRequestForTest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyComplete";

    /**
     * SerialVersionUID<BR>
     */
   public static final long serialVersionUID = 200611301602L;   
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * (確認時単価)<BR>
     * 確認時単価<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqBuyCompleteRequestForTest.class);
    
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);
        
        // ２）銘柄コードチェック 
        // this.注文ID == null の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.orderId)) {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文ID = " + this.orderId);
        }
        
        // ３）確認時単価チェック 
        // this.確認時単価 == null の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.checkPrice)) {
            log.debug("確認時単価が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + STR_METHOD_NAME,
                "確認時単価 = " + this.checkPrice);
        }
        
        // ４）確認時発注日チェック 
        // this.確認時発注日 == null の場合、例外をスローする。
        if (this.checkDate == null) {
            log.debug("確認時発注日が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + STR_METHOD_NAME,
                "確認時発注日 = " + this.checkDate);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
