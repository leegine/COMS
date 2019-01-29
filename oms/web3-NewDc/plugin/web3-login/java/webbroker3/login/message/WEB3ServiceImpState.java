head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3ServiceImpState.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス実施状態をxTradeクライアントに渡す為のメッセージクラス(WEB3ServiceImpState.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/10 菊地(SRA)
 */

package webbroker3.login.message;


import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス実施状態)<BR>
 * サービス実施状態をxTradeクライアントに渡す為のメッセージクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3ServiceImpState extends Message 
{
   /**
    * 時価情報リアルディレイ区分（リアル）<BR>
    */
   public static final String QUICK_REAL = "07_Quick_Real";
   
   /**
    * 時価情報リアルディレイ区分（ディレイ）<BR>
    */
   public static final String QUICK_DELAY = "07_Quick_Delay";
   
   /**
    * (時価情報リアルディレイ区分)<BR>
    * "07_Quick_Real" ：リアル<BR>
    * "07_Quick_Delay"：ディレイ<BR>
    * ※変更する必要もないと思うので、現行に合わせる事とする。<BR>
    * 　@全体と調整する際、変更が必要となるかも知れない。<BR>
    */
   public String quickDiv;
   
   /**
    * @@roseuid 4021A3810186
    */
   public WEB3ServiceImpState() 
   {
    
   }
}
@
