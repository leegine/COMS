head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.03.03.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	HachuUlimitVolBeanHolder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 13:33:45 on 2008/07/29
 * by weblogic.xml.schema.binding.internal.codegen.HolderGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package jp.co.hitachi.www.TFX.WebService.holders;


public final class HachuUlimitVolBeanHolder 
  implements weblogic.xml.schema.binding.Holder
{

  public jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean value;

  public HachuUlimitVolBeanHolder() {}

  public HachuUlimitVolBeanHolder(jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean value) {
    this.value = value;
  } 


  public java.lang.Object getValue() {
    return value;
  }

  public void setValue(java.lang.Object obj) {
    this.value = (jp.co.hitachi.www.TFX.WebService.HachuUlimitVolBean) obj;
  }
}
@
