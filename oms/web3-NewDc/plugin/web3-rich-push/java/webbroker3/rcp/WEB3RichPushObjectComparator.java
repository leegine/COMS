head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュオブジェクトソート用コンパレータクラス(WEB3RichPushObjectComparator.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/01 劉(FLJ) 新規作成
 */
package webbroker3.rcp;

import java.util.*;

/**
 * <p>
 * リッチクライアントプッシュオブジェクトソート用コンパレータクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RichPushObjectComparator
    implements Comparator
{
    public int compare(Object object1, Object object2)
    {
        return ( (Comparable) object1).compareTo(object2);
    }
}
@
