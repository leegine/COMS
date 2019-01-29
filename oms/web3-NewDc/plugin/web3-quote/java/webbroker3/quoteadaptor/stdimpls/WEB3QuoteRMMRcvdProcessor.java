head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRMMRcvdProcessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteRMMRcvdProcessorクラス(WEB3QuoteRMMRcvdProcessor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/04/23 齋藤　@栄三(FLJ) 新規作成（時価システムQUICKへの移行）
 */
package webbroker3.quoteadaptor.stdimpls;


/**
 * (時価受信RMMプロセッサー)
 * 
 * 時価受信RMMプロセッサークラス。
 * 
 * @@author 齋藤(FLJ)
 * @@version 1.0
 */
public interface WEB3QuoteRMMRcvdProcessor
{
    
    /**
     * (receiveデータ)
     * <BR>
     * データを受け取る。<BR>
     * 
     * @@param l_data データ
     */
    public void receiveData(byte[] l_data);
}
@
