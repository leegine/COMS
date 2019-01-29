head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード区分 定数定義インタフェイス(WEB3SrvRegiUploadDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/3/11 王志葵(中訊) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * アップロード区分 定数定義インタフェイス
 *
 * @@author 王志葵
 * @@version 1.0
 */
public interface WEB3SrvRegiUploadDivDef
{

    /**
     * 0：登録
     */
    public static final String REGIST_RECORD = "0";

    /**
     * 1：変更
     */
    public static final String CHANGE_RECORD = "1";
}
@
