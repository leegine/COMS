head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.34.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	GetResponseByCommandIdResponseHolder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:53 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.HolderGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api.holders;


public final class GetResponseByCommandIdResponseHolder 
  implements weblogic.xml.schema.binding.Holder
{

  public com.gftforex.soap.api.GetResponseByCommandIdResponse value;

  public GetResponseByCommandIdResponseHolder() {}

  public GetResponseByCommandIdResponseHolder(com.gftforex.soap.api.GetResponseByCommandIdResponse value) {
    this.value = value;
  } 


  public java.lang.Object getValue() {
    return value;
  }

  public void setValue(java.lang.Object obj) {
    this.value = (com.gftforex.soap.api.GetResponseByCommandIdResponse) obj;
  }
}
@
