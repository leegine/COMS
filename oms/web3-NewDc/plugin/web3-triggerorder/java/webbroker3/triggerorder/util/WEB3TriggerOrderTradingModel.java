head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.06.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3TriggerOrderTradingModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : Webbroker3-TriggerOrder vOCNX(WEB3TriggerOrderTradingModel)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 €Ε (u) VKμ¬ 
*/

package webbroker3.triggerorder.util;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;

import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;

/**
 * @@author €Ε
 * @@version 1.0
 */
public class WEB3TriggerOrderTradingModel
{
    protected static OrderManager orderManager = new WEB3ToSuccOrderManagerImpl();
    
    /**
     * 
     */
    public WEB3TriggerOrderTradingModel()
    {
        super();     
    }    
    
    /**
     * (getOrderManager)<BR>
     * gK[ΆΕgp·ιOrderManagerπζΎ·ι<BR>
     * <BR>
     * @@return OrderManager
     */
    public static OrderManager getOrderManager()
    {
        return orderManager;    
    }
}
@
