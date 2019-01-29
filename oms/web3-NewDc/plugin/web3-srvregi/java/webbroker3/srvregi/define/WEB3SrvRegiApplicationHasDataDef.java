head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplicationHasDataDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込登録テーブルにデータが存在区分(WEB3SrvRegiApplicationHasDataDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/05/21 柴双紅(中訊) 仕様変更モデルNo.418
*/

package webbroker3.srvregi.define;

/**
 * 申込登録テーブルにデータが存在区分
 *
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3SrvRegiApplicationHasDataDef
{
    /**
     * 1：データが存在
     */
    public final static String HASDATA = "1";

    /**
     * 0：データが存在しない
     */
    public final static String NODATA = "0";
}@
