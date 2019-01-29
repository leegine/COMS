head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.33.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	ResultInfoAddAccountAccountIdArrayAnonTypeHolder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 9:57:20 on 2008/07/16
 * by weblogic.xml.schema.binding.internal.codegen.HolderGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR362079 Thu Feb 21 18:25:04 2008
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api.holders;


public final class ResultInfoAddAccountAccountIdArrayAnonTypeHolder 
  implements weblogic.xml.schema.binding.Holder
{

  public long[] value;

  public ResultInfoAddAccountAccountIdArrayAnonTypeHolder() {}

  public ResultInfoAddAccountAccountIdArrayAnonTypeHolder(long[] value) {
    this.value = value;
  } 


  public java.lang.Object getValue() {
    return value;
  }

  public void setValue(java.lang.Object obj) {
    this.value = (long[]) obj;
  }
}
@
