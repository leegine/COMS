head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP口座開設区分(WEB3IfoAccountOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  王暁傑 (中訊) 新規作成
*/
package webbroker3.ifo.define;

/**
 * 先物OP口座開設区分
 *                                                                     
 * @@author wang-xiaojie
 * @@version 1.0
 */
public interface WEB3IfoAccountOpenDivDef
{
    /**
       * 0：DEFAULT（口座なし）
       */
    public static final String DEFAULT = "0";
      
    /**
       * 1：OP口座開設
       */
    public static final String OPTIONS = "1";

    /**
       * 2：先物口座開設
       */
    public static final String FUTURES = "2";
    
    /**
       * 3：先物OP口座開設
       */
    public static final String IFO = "3";        
}
@
