head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コネクタで使う定数定義(WEB3RlsConnectorConstants.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 FLJ齋藤　@新規作成
*/
package webbroker3.rlsgateway.connector;

/**
*
* コネクタで使う定数定義
* @@author Eizo Saito (FLJ)
* @@version 1.0
*/
public interface WEB3RlsConnectorConstants
{

    /**
     * 銘柄ID、市場IDに含まれる会社コードの桁数
     */
    public static final int INSTITUTION_CODE_SIZE = 2;
    
    /**
     * 条件付き注文IDの桁数
     */
    public static final int RLS_COND_ORDER_SIZE = 18;
    
    /**
     * xTier app region
     */
    public static final String XTIER_APP_REGION = "client";
    
    /**
     * xTier kernel config file
     */
    public static final String XTIER_KERNEL_FILE = "/config/xtier_kernel.xml";
    
    /**
     * class path key
     */
    public static final String JAVA_CLASS_PATH = "java.class.path";
    
    /**
     * path separator
     */
    public static final String PATH_SEPARATOR = "path.separator";
    
    /**
     * xTier retry sequential count
     */
    public static final String XTIER_RETRY_SEQUENTIAL_COUNT = "webbroker3.xtier.retry.sequential.count";
    
    /**
     * Default xTier retry sequential count
     */
    public static final long DEFAULT_XTIER_RETRY_SEQUENTIAL_COUNT = 5;
    
    /**
     * xTier retry interval
     */
    public static final String XTIER_RETRY_INTERVAL = "webbroker3.xtier.retry.interval";
    
    /**
     * Default xTier retry interval
     */
    public static final long DEFAULT_XTIER_RETRY_INTERVAL = 300000;
}
@
