head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSplitNewStockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 ネットトレードシステム開発部
 File Name        : 分割新株式の定数定義インターフェース(WEB3TPSplitNewStockDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/12/19 山野(DIR) 新規作成 */
package webbroker3.tradingpower.define;

/**
 * （分割新株式の定数定義インターフェース）
 */
public interface WEB3TPSplitNewStockDef 
{
     /**
      * (分割新株式でない場合)<BR>
      */
     public final static String DEFAULT = "0";

     /**
      * (分割新株式の場合)<BR>
      */
     public final static String SPLIT_NEW_STOCK = "1";
}
@
